package comp31.asst2.models.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer_order")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer orderId;

    @ManyToOne
    @JoinColumn(name="ACCOUNT_ID")
    Account account;

    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<OrderItem> orderItems;

    public void addItem(OrderItem orderItem) {
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<>();
        }
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    public void addItems(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            addItem(orderItem);
        }
    }

}
