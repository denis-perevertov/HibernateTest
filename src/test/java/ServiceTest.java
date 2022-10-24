import entity.Order;
import entity.User;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import service.OrderService;

import static org.junit.Assert.*;
import java.sql.*;
import java.util.List;

public class ServiceTest {

    static SessionFactory sessionFactory;
    static Session session;

    @BeforeClass
    public static void openConnection() {
        sessionFactory = new Configuration().configure("hibernate.cfg_test.xml").buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @AfterClass
    public static void closeConnection(){
        session.close();
        sessionFactory.close();
    }

    @Test
    public void SQLExceptionTest() {
        assertThrows(PersistenceException.class, () -> {
            String sql = "INSERT dsfjskd 29 2 oj ";

            Query q = session.createNativeQuery(sql, User.class);

            q.getResultList();
        });
    }

    @Test
    public void addUserTest() throws SQLException {

        session.beginTransaction();

        for(int i = 1; i <= 500; i++) {
            User user = new User(i, "TestName " + i, "TestSurname " + i, i+10);
            session.merge(user);
        }

        session.getTransaction().commit();

    }

    @Test
    public void getUserTest() throws SQLException {

        session.beginTransaction();

        User user = session.get(User.class, 1);

        session.getTransaction().commit();

        assertEquals(session.get(User.class,1), new User(1, "TestName 1", "TestSurname 1", 11));

    }

    @Test
    public void updateUserTest() throws SQLException {

        session.beginTransaction();

        User user_update = (User) session.get(User.class, 5);

        user_update.setName("TestNameUpdate 5");

        session.merge(user_update);

        session.getTransaction().commit();

    }
//
    @Test
    public void removeUserTest() throws SQLException {

        session.beginTransaction();

        User user = new User("sflkjd", "sldkjfld;s", 100);

        session.merge(user);

        session.getTransaction().commit();


        session.beginTransaction();

        User user_delete = session.load(User.class, 500);

        session.remove(user_delete);

        session.getTransaction().commit();

    }

    @Test
    public void addProductTest() throws SQLException {

        session.beginTransaction();

        for(int i = 1; i <= 500; i++) {
            Product product = new Product(i, "TestName " + i, "TestType " + i, "TestMan " + i, i*5);
            session.merge(product);
        }

        session.getTransaction().commit();

    }
//
    @Test
    public void getProductTest() throws SQLException {

        session.beginTransaction();

        Product product = session.get(Product.class, 1);

        session.getTransaction().commit();

        assertEquals(session.get(Product.class,1), product);

    }
//
    @Test
    public void updateProductTest() throws SQLException {

        session.beginTransaction();

        Product product_update = session.get(Product.class, 5);

        product_update.setName("TestNameUpdate 5");

        session.merge(product_update);

        session.getTransaction().commit();

    }
//
    @Test
    public void removeProductTest() throws SQLException {

        session.beginTransaction();

        Product product = new Product("TestName " + 502, "TestType " + 502, "TestMan " + 502, 502*5);

        session.merge(product);

        session.getTransaction().commit();


        session.beginTransaction();

        Product product_delete = session.load(Product.class, 500);

        session.remove(product_delete);

        session.getTransaction().commit();

    }

    @Test
    public void addProductIntoShoppingCartTest() {

        User user = new User(509, "TestUser", "TestUser", 510);
        Product product = new Product(509, "TestProduct", "TestProduct", "TestType", 300);

        OrderService orderService = new OrderService();

        orderService.addProduct(user, product);
        orderService.removeProduct(user, product);

    }

    @Test
    public void removeProductFromShoppingCartTest() {

        User user = new User(509, "TestUser", "TestUser", 510);
        Product product = new Product(509, "TestProduct", "TestProduct", "TestType", 300);

        OrderService orderService = new OrderService();

        orderService.addProduct(user, product);
        orderService.removeProduct(user, product);

    }

    @Test
    public void getAllUserProductsTest() {

        User user = new User(509, "TestUser", "TestUser", 510);
        Product product = new Product(509, "TestProduct", "TestProduct", "TestType", 300);

        OrderService orderService = new OrderService();

        orderService.addProduct(user, product);
        assertTrue(user.getProductList().size() > 0);
        orderService.removeProduct(user, product);

    }

    @Test
    public void removeAllUserProductsTest() {

        User user = new User(509, "TestUser", "TestUser", 510);
        Product product = new Product(509, "TestProduct", "TestProduct", "TestType", 300);

        OrderService orderService = new OrderService();

        orderService.addProduct(user, product);
        orderService.removeUserProducts(user);

        assertTrue(user.getProductList().size() == 0);

    }

    @Test
    public void createOrderTest() throws SQLException {

        User user = new User(509, "TestUser", "TestUser", 510);
        Product product = new Product(509, "TestProduct", "TestProduct", "TestType", 300);

        OrderService orderService = new OrderService();

        orderService.addProduct(user, product);
        orderService.createOrder(user);

    }

    @Test
    public void getAllUserOrdersTest() throws SQLException {

        User user = new User(509, "TestUser", "TestUser", 510);
        Product product = new Product(509, "TestProduct", "TestProduct", "TestType", 300);

        OrderService orderService = new OrderService();

        orderService.addProduct(user, product);
        orderService.createOrder(user);
        List<Order> orderList = orderService.getUserOrders(user);

        assertTrue(orderList.size() > 0);

    }

    @Test
    public void getAllOrdersTest() throws SQLException {

        User user = new User(509, "TestUser", "TestUser", 510);
        User user2 = new User(511, "TestUser2", "TestUser2", 510);
        Product product = new Product(509, "TestProduct", "TestProduct", "TestType", 300);

        OrderService orderService = new OrderService();

        orderService.addProduct(user, product);
        orderService.addProduct(user2, product);
        orderService.createOrder(user);
        orderService.createOrder(user2);

        List<Order> orderList = orderService.getAllOrders();

        assertTrue(orderList.size() > 1);

    }

}
