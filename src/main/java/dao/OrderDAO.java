package dao;

import entity.Order;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    void createOrder(User user) throws SQLException;

    List<Order> getUserOrders(User user) throws SQLException;
    List<Order> getAllOrders() throws SQLException;

}
