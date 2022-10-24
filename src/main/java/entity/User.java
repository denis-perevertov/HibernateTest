package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column (name="user_id")
//    @GeneratedValue(strategy=GenerationType.AUTO, generator = "sequence")
//    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
//            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "sequence"),
//            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1"),
//    })
    private int id;

    private String name;
    private String surname;
    private int age;
    @Transient
    private String gender;
    @Transient
    private Date birth_date;
    @Transient
    private String phone_number;
    @Transient
    private String country;
    @Transient
    private String city;
    @Transient
    private String nationality;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="shopping_cart",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    List<Product> productList = new ArrayList<>();

    public User() {}

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(int id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(int id, String name, String surname, int age, String gender,
                Date birth_date, String phone_number, String country,
                String city, String nationality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.country = country;
        this.city = city;
        this.nationality = nationality;
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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getBirth_date() {
        return birth_date;
    }
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age);
    }

    public String fullInfo() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age + '\'' +
                ", gender=" + gender + '\'' +
                ", birth_date=" + birth_date + '\'' +
                ", phone_number=" + phone_number + '\'' +
                ", country=" + country + '\'' +
                ", city=" + city + '\'' +
                ", nationality=" + nationality +
                '}';


    }
}
