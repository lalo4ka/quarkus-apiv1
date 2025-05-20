package fss.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/*
* Cuando tienes una relación Many-to-Many en Hibernate (JPA) y la tabla de unión (Join Table) 
* necesita tener columnas adicionales aparte de las dos claves foráneas que conectan las entidades, 
* el enfoque estándar de @ManyToMany con @JoinTable no es suficiente, ya que @JoinTable solo permite 
* configurar las columnas de las claves foráneas.
*
* La forma correcta y recomendada de manejar esto es romper la relación Many-to-Many en dos relaciones 
* One-to-Many, mediadas por una entidad intermedia que represente explícitamente la tabla de unión y 
* contenga tanto las relaciones (@ManyToOne) a las dos entidades principales como las columnas adicionales.
*
* Esencialmente, @MapsId le dice a JPA que la columna de clave foránea definida 
* por la relación (@ManyToOne o @OneToOne) también sirve como parte o la totalidad 
* de la clave primaria de la entidad actual.
*/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "lista_producto")
public class ProductPriceList {

    @EmbeddedId //(composed by id_producto,id_lista)
    public ProductPriceListPK id;

    @ManyToOne(fetch = FetchType.LAZY) // Relación Many-to-One con la tabla productos
    @MapsId("idProducto") // Mapea este atributo al 'idProducto' dentro de la clave compuesta
    //@JoinColumn(name = "id_producto", referencedColumnName = "id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "lista_producto_id_producto_fkey")) // Columna FK en la tabla 'lista_producto'
    @JoinColumn(name = "id_producto")
    public Product product;

    @ManyToOne(fetch = FetchType.LAZY) // Relación Many-to-One con la tabla lista_cliente 
    @MapsId("idLista") // Mapea este atributo al 'idLista' dentro de la clave compuesta
    //@JoinColumn(name = "id_lista", referencedColumnName = "id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "lista_producto_id_lista_fkey"))        
    @JoinColumn(name = "id_lista")
    public PriceList priceList;

    @Column(name = "precio")
    public BigDecimal price;
    
    @Column(name = "precio_anterior")
    public BigDecimal lastPrice;
    
    @Column(name = "precio_nuevo_fecha")
    public LocalDate priceDate;

    @Override
    public String toString() {
        return "ProductListPrice{" + "id=" + id + ", precio=" + price + '}';
    }

}
