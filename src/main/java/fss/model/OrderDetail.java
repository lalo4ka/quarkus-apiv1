package fss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@JsonIgnoreProperties(value = {"product", "order"})
@Table(name = "contiene")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailPK id;    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "id_producto")    
    public Product product;
            
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "id_pedido")
    public Order order;

    @Column(name = "cnt")
    public BigDecimal qty;

    @Column(name = "cnt2")
    public BigDecimal qty2;

    @Column(name = "unidad")
    public String unit;

    @Column(name = "precio")
    public BigDecimal unitPrice;
    
    @Transient    
    public BigDecimal totalAmount;

    @Override
    public String toString() {
        return "OrderDetail{" + "product=" + product + ", qty=" + qty + ", qty2=" + qty2 + ", unit=" + unit + ", unitPrice=" + unitPrice + ", totalAmount=" + totalAmount + '}';
    }

    
    @PostLoad //Event::After an entity has been loaded from DB
    public void onPostLoad() {
        totalAmount = qty2.multiply(unitPrice);
    }

}
