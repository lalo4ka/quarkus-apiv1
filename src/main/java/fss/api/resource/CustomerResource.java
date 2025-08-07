package fss.api.resource;

import fss.api.ApiResponse;
import fss.api.ApiResponse;
import fss.api.response.customer.OrdersByCustomer;
import fss.service.CustomerService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;


@Path("/api/v1/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Customer API", description = "Operations related to customers")
public class CustomerResource {

    private static final Logger LOG = Logger.getLogger(CustomerResource.class);

    @Inject
    CustomerService customerService;   

//    @GET
//    @Operation(summary = "Get all customers")
//    @APIResponse(responseCode = "200", description = "List of customers retrieved successfully")
//    public Response getAllCustomers() {
//                                
//    }

//    @POST
//    @Path("/routes")
//    @Operation(summary = "Get customers by routes")
//    @APIResponse(responseCode = "200", description = "Customers retrieved successfully")
//    public Response getCustomersByRoutes(Set<String> routes) {
//        LOG.info("Retrieving customers for routes: " + String.join(", ", routes));
//
//        List<CustomerDTO> customers = customerService.getAllCustomersByRoutes(routes).stream()
//                .map(customerMapper::toDTO)
//                .collect(Collectors.toList());
//
//        return Response.ok(ApiResponse.success(customers)).build();
//    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get customer by ID")
    @APIResponse(responseCode = "200", description = "Customer retrieved successfully")
    @APIResponse(responseCode = "404", description = "Customer not found")
    public Response getCustomerById(@PathParam("id") Integer id) {
        OrdersByCustomer customerView = customerService.findCustomerWithOrders(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + id));

        return Response.ok(ApiResponse.success(customerView)).build();

    }

    @GET
    @Path("/{id}/orders")
    @Operation(summary = "Get orders by customer ID")
    @APIResponse(responseCode = "200", description = "Orders retrieved successfully")
    @APIResponse(responseCode = "404", description = "Customer or orders not found")
    public Response getOrdersByCustomer(@PathParam("id") Integer id) {
        OrdersByCustomer orders = customerService.findOrdersByCustomer(id)
                .orElseThrow(() -> new NotFoundException("Orders not found for customer ID: " + id));

        return Response.ok(ApiResponse.success(orders)).build();
    }

    // Add other CRUD operations
}
