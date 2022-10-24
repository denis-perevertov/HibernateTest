package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Table (name="orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "sequence")
//    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
//            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "sequence"),
//            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1"),
//    })
    private int order_id;
    private int user_id;
    private String description;
    private double total_price;

    public Order() {
    }

    public int getId() {
        return order_id;
    }

    public void setId(int order_id) {
        this.order_id = order_id;
    }

    public int getUser() {
        return user_id;
    }

    public void setUser(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", description='" + description + '\'' +
                ", total_price=" + total_price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return order_id == order.order_id && user_id == order.user_id && Double.compare(order.total_price, total_price) == 0 && Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, user_id, description, total_price);
    }
}
