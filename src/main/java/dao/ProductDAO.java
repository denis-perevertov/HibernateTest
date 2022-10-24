package dao;

import entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    void add(Product product) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    Product getById(int id) throws SQLException;
    void update(Product product) throws SQLException;
    void remove(Product product) throws SQLException;

}
