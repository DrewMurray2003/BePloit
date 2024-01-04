package comp31.asst2.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer productId;

    String name;
    Double price;
    String type;
    Integer quantity;
    String photoLink;

    public Product(String name, Double price, String type, Integer quantity, String photoLink) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        this.photoLink = photoLink;
    }

}
