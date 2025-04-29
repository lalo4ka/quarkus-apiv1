package fss.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import fss.api.response.OrdersByCustomer;
import fss.repository.CustomerRepository;
import fss.model.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author ehernandez
 */
@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;
    @Inject
    EntityManager em;
    @Inject
    CriteriaBuilderFactory cbf;
    @Inject
    EntityViewManager evm;

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.byId(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.all();
    }

    public List<Customer> getAllCustomersByRoutes(Set<String> rutas) {
        return customerRepository.allByRoute(rutas);
    }

    public Customer insert(Customer c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Customer update(Customer c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Customer upsert(Customer c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Optional<OrdersByCustomer> findOrdersByCustomer(Integer id) {

        LocalDate fromDate = LocalDate.of(2025, 04, 01);

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
            return Optional.empty();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(result);

    }

}
