package fss.api;

import fss.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collections;


@Path("/products")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    
 @Inject
 ProductService productService; 
 

    @GET            
    @Path("/status")    
    public Response status() {        
        return Response.ok(Collections.singletonMap("status", "ok")).build();
    }

    @GET        
    public Response getAllProducts() {        
        return Response.ok(productService.getAllProductSummarize()).build();
    }      
    
    

//    @POST
//    public Response createProduct(Product product) { // Podrías recibir un DTO de entrada aquí también
//        Product createdProduct = productService.createProduct(product);
//        // Generalmente se devuelve la ubicación del nuevo recurso o el recurso creado
//        return Response.status(Response.Status.CREATED)
//                       .entity(new ProductSummary(createdProduct.id, createdProduct.name, createdProduct.unitSale)) // Devuelve el DTO del producto creado
//                       .build();
//    }
    
}
