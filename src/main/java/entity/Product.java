package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="products")
public class Product {

    @Id
    @Column (name="product_id")
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequence")
//    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
//            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "sequence"),
//            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1"),
//    })
    private int id;
    private String name;
    private String type;
    private String manufacturer;
    private double price;

    @ManyToMany(mappedBy = "productList")
    List<User> userList = new ArrayList<>();

    public Product() {}

    public Product(String name, String type, String manufacturer, double price) {
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public Product(int id, String name, String type, String manufacturer, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(type, product.type) && Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, manufacturer, price);
    }
}