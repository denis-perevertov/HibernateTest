import entity.Order;
import entity.Product;
import entity.User;
import service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class HibernateTest {

    public static void main(String[] args) throws SQLException {

        OrderService orderService = new OrderService();

        Product test_product = orderService.productService.getById(11);
        User test_user = orderService.userService.getById(1);

        orderService.addProduct(test_user, test_product);

        orderService.removeUserProducts(test_user);


    }

}
