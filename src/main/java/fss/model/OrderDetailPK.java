package fss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class OrderDetailPK {

    @Column(name = "id")
    public int id;

    @Column(name = "id_pedido")
    public int orderId;

    @Column(name = "id_producto")
    public int productId;

}
