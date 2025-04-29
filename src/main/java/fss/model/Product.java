    package fss.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "productos")
//@SQLDelete(sql = "UPDATE productos SET deleted = true WHERE id = ?")
//@SQLRestriction("deleted = false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productoid")
    @SequenceGenerator(name = "productoid", allocationSize = 1)
    public int id;

    @Column(name = "nombre")
    public String name;

    @Column(name = "zona")
    public String zone;

    @Column(name = "clave")
    public String clave;

    @Column(name = "unidad_venta")
    public String unitSale;
    
    @Column(name = "categoria")
    public String category;
        

//    @OneToMany(
//            mappedBy = "product",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY
//    )    
//    public List<ProductPriceList> productPriceList = new ArrayList<>();

    @Override
    public String toString() {
        return "ProductEntity{" + "id=" + id + ", name=" + name + ", zone=" + zone + ", clave=" + clave + ", unitSale=" + unitSale + '}';
    }

   
   

}
