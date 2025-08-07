package fss.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import fss.api.response.customer.OrdersByCustomer;
import fss.repository.CustomerRepository;
import fss.model.Customer;
import fss.model.OrderDetail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.jboss.logging.Logger;

/**
 *
 * @author ehernandez
 */
@ApplicationScoped
public class CustomerService {

    private static final Logger LOG = Logger.getLogger(CustomerService.class);

    @Inject
    CustomerRepository customerRepository;

    @Inject
    EntityManager em;

    @Inject
    CriteriaBuilderFactory cbf;

    @Inject
    EntityViewManager evm;

    @Transactional
    public Optional<Customer> getCustomerById(Integer id) {
        LOG.debug("Finding customer with ID: " + id);
        return customerRepository.byId(id);
    }

    @Transactional
    public Optional<OrdersByCustomer> findCustomerWithOrders(Integer id) {
        LOG.debugv("Finding customer with ID: {} with Orders", id);

        try {
            CriteriaBuilder<Customer> cb = cbf.create(em, Customer.class)
                    .from(Customer.class)                    
                    .where("id")
                    .eq(id);

            OrdersByCustomer result = evm
                    .applySetting(EntityViewSetting.create(OrdersByCustomer.class), cb)
                    .getSingleResult();
            return Optional.ofNullable(result);
        } catch (NotFoundException e) {
            LOG.debugv("No customer found with ID: {}", id);
            return Optional.empty();
        }
    }

    public List<Customer> getAllCustomers() {
        LOG.debug("Retrieving all customers");
        return customerRepository.all();
    }

    public List<Customer> getAllCustomersByRoutes(Set<String> routes) {
        LOG.debug("Finding customers for routes: " + String.join(", ", routes));
        return customerRepository.allByRoute(routes);
    }

    @Transactional
    public Customer insert(Customer customer) {
        LOG.info("Creating new customer: " + customer.name);
        return customerRepository.insert(customer);
    }

    @Transactional
    public Customer update(Customer customer) {
        LOG.info("Updating customer with ID: " + customer.id);
        // Validate that the customer exists
        customerRepository.byId(customer.id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customer.id));

        return customerRepository.update(customer);
    }

    @Transactional
    public Customer upsert(Customer customer) {
        if (customer.id > 0 && customerRepository.byId(customer.id).isPresent()) {
            LOG.info("Updating existing customer with ID: " + customer.id);
            return customerRepository.update(customer);
        } else {
            LOG.info("Creating new customer");
            return customerRepository.insert(customer);
        }
    }

    public Optional<OrdersByCustomer> findOrdersByCustomer(Integer id) {

        LocalDate fromDate = LocalDate.of(2025, 01, 01);

        CriteriaBuilder<Customer> cb = cbf.create(em, Customer.class)
                .from(Customer.class)
                .where("orders.date").ge(fromDate) //greaterThan
                .where("id").eq(id); //equal

        OrdersByCustomer result;

        try {
            CriteriaBuilder<OrdersByCustomer> customerView
                    = evm.applySetting(EntityViewSetting.create(OrdersByCustomer.class), cb);
            result = customerView.getSingleResult();
        } catch (NoResultException ex) {
            LOG.debug("No orders found for customer with ID: " + id);
            return Optional.empty();
        } catch (Exception ex) {
            LOG.error("Error finding orders for customer with ID: " + id, ex);
            ex.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(result);

    }

}
