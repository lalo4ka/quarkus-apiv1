package fss.service;

import fss.api.response.product.ProductList;
import fss.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;

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
     @Transactional
    public List<ProductList> getAllProductSummarize() {
        List<ProductList> products;
        try {
            products = productRepository.findAllProductsSummarize(1);
        } catch (Exception ex) {
            products = Collections.emptyList();
            ex.printStackTrace();
        }
        return products;
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
