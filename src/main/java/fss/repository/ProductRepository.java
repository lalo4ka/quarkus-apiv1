package fss.repository;


import fss.model.Product;
import fss.api.response.ProductSummary;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;
import jakarta.data.repository.Update;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository {
    
    @Find
    Optional<Product> findById(Integer id);
    
    @Find
    @OrderBy("zone")
    List<Product> findAllProducts();
    
    @Insert
    Product insert(Product p);
    
    @Update
    Product update(Product p);
    
    @Save
    Product upsert(Product p);
        
    
    @Delete
    Product delete(Product p);
    
    
        @Query("""
           select p.id, name, clave, unitSale, category, zone, price, unitSales
           from Product p, ProductPriceList ppl 
           where trim(category) != "" and ppl.id.idLista = :idLista and ppl.id.idProducto=p.id
           order by name
    """)        
    List<ProductSummary> findAllProductsSummarize(@Param("idLista") int idLista);
    
}
