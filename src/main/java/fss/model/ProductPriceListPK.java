package fss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ProductListPK is a composite primary key (id_producto,id_lista) used in the
 * Entity ProductList
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class ProductPriceListPK implements Serializable {

    @Column(name = "id_producto")
    public int idProducto;

    @Column(name = "id_lista")
    public int idLista;

}
