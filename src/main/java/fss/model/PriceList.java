package fss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"productos"})
@Table(name = "lista_cliente")
public class PriceList {

    @Id
    public int id;

    @Column(name = "nombre")
    public String name;

    @Column(name = "fecha")
    public LocalDate date;

    @OneToMany(
            mappedBy = "priceList",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )        
    public List<ProductPriceList> list = new ArrayList<>();

    @Override
    public String toString() {
        return "PriceList{" + "id=" + id + ", nombre=" + name + '}';
    }

    

}
