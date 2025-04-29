package fss.service;

import fss.model.Product;
import fss.api.response.ProductSummary;
import fss.repository.ProductRepository;
import jakarta.data.Sort;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.hibernate.query.Order;

/**
 *
 * @author ehernandez
 */
@ApplicationScoped
public class ProductService {

    @Inject
    private ProductRepository productRepository;
    
    

    // @Transactional asegura que todo el método se ejecute en una transacción
//    @Transactional
//    public Product createProduct(Product product) {
//        // Aquí podrías tener lógica de negocio antes/después de guardar
//        // Por ejemplo, validar campos, calcular algo, etc.
//        return productRepository.save(product);
//    }

//    @Transactional
//    public Optional<Product> getProductById(Integer id) {
//        return productRepository.findById(id);
//    }

    // Si quieres devolver un DTO/Record
//    @Transactional
//    public Optional<ProductSummary> getProductSummaryById(Integer id) {
//        return productRepository.findById(id)
//                .map(p -> new ProductSummary(p.id, p.name, p.unitSale));
//        // Mapeo de Entidad a DTO/Record
//    }
//
//    @Transactional
//    public List<Product> getAllProducts() {
//        return productRepository.findAll().toList(); // Convierte el Stream a List
//    }

    // Si quieres devolver una lista de DTOs/Records
    @Transactional
    public List<ProductSummary> getAllProductSummaries() {        
        return productRepository.findAllProductsSummarize();
    }

//    @Transactional
//    public void deleteProduct(Integer id) {
//        // Aquí podrías tener lógica de negocio antes de eliminar
//        productRepository.deleteById(id);
//    }
    
//   public void updateProductStock(Long id, int newStock) {
//        productRepository.findById(id).ifPresent(product -> {
//            product.setStock(newStock);
//            productRepository.save(product); // save también se usa para actualizar entidades existentes
//        });
//    }    

}
