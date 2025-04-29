package fss.api;

import fss.api.response.OrdersByCustomer;
import fss.model.Customer;
import fss.service.CustomerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerService customerService;


    @POST
    @Path("/routes")
    public Response allCustomersByRoutes(Set<String> rutas) {
        List<Customer> allCustomersByRoutes = customerService.getAllCustomersByRoutes(rutas);
        
        allCustomersByRoutes.stream().forEach(customer->{
        System.out.printf("ID:%05d | Nombre:%s | Ruta:%s %n",customer.id,customer.name, customer.route);
        });
                        
        return Response.ok(Collections.singletonMap("status", "ok")).build();
    }

    @GET
    @Path("{id}/orders")
    public Response getOrdersByCustomer(Integer id) {         
        
        OrdersByCustomer orders = customerService.findOrdersByCustomer(id)
                .orElseThrow(() -> new NotFoundException("Data not found"));       

        return Response.ok(orders).build();                
        
    }
    
    
    //    @POST
//    @Path("/new")
//    public String create(Book book) {
//        library.add(book);
//        return "Added " + book.isbn;
//    }

}
