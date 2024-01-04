package comp31.asst2.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer orderItemId;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    Product product;

    Integer quantity;

    public OrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}