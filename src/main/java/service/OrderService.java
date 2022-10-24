package service;

import dao.OrderDAO;
import entity.Order;
import entity.Product;
import entity.User;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public class OrderService implements OrderDAO {

    public UserService userService;
    public ProductService productService;
    Session session;
    Logger logger = LogManager.getLogger();

    public OrderService() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        userService = new UserService(session);
        productService = new ProductService(session);

    }

    @Override
    public void createOrder(User user) throws SQLException {

        List<Product> productList = user.getProductList();

        logger.info("Got the list of ordered products.");

        StringBuilder sb = new StringBuilder();
        double sum = 0.0;

        for(Product p : productList) {
            sb.append(p.getName()).append(", ");
            sum += p.getPrice();
        }

        if(productList.size() < 1) {
            logger.error("Shopping cart for user is empty, can't order, returning");
            return;
        }

        logger.info("Created description and total price for order.");

        String description = sb.deleteCharAt(sb.length()-2).toString().trim();

        Order order = new Order();

        order.setDescription(description);
        order.setTotalPrice(sum);
        order.setUser(user.getId());

        logger.info("Finished creating an order, now saving...");

        session.beginTransaction();
        session.persist(order);
        session.getTransaction().commit();

        logger.info("Order saved, deleting user products from shopping cart.");

        this.removeUserProducts(user);


    }

    @Override
    public List<Order> getUserOrders(User user) throws SQLException {

        Query q = session.createNativeQuery("SELECT * FROM orders WHERE user_id = ?", Order.class);

        q.setParameter(1, user.getId());

        List<Order> orderList = q.getResultList();

        return orderList;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> rootEntry = cq.from(Order.class);
        CriteriaQuery<Order> all = cq.select(rootEntry);

        TypedQuery<Order> allQuery = session.createQuery(all);

        return allQuery.getResultList();
    }

    public void addProduct(User user, Product product) {

        user.getProductList().add(product);

        session.beginTransaction();

        session.merge(user);

        session.getTransaction().commit();

        for(Product p : user.getProductList()) {
            System.out.println(p);
        }

    }

    public void removeProduct(User user, Product product) {

        user.getProductList().remove(product);

        session.beginTransaction();

        session.merge(user);

        session.getTransaction().commit();

    }

    public List<Product> getUserProducts(User user) {
        return user.getProductList();
    }

    public void removeUserProducts(User user) {

        user.getProductList().clear();

        session.beginTransaction();

        session.merge(user);

        session.getTransaction().commit();
    }

}
