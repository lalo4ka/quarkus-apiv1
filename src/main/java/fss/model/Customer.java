package fss.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@Table(name = "cliente")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clienteid")
    @SequenceGenerator(name = "clienteid", allocationSize = 1)
    public int id;

    @Column(name = "nombre")
    public String name;

    @Column(name = "usuario")
    public String user;

    @Column(name = "password")
    public String password;

    @Column(name = "compras_mail")
    public String email;
    
    @Column(name = "ruta")
    public String route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lista")
    public PriceList priceList;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY) // mappedBy="customer" indica que el campo 'customer' en la clase Order es el lado propietario de la FK (customer_id)
    public List<Order> orders;

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", nombre=" + name + "}";
    }

}
