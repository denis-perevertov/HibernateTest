package service;

import dao.ProductDAO;
import entity.Product;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ProductService implements ProductDAO {
    
    Session session;
    
    ProductService(Session session) { 
        this.session = session;
    }
    
    @Override
    public void add(Product product) throws SQLException {

        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
        
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);

        TypedQuery<Product> allQuery = session.createQuery(all);

        return allQuery.getResultList();
    }

    @Override
    public Product getById(int id) throws SQLException {

        Product product;

        session.beginTransaction();
        product = session.get(Product.class, id);
        session.getTransaction().commit();

        return product;
    }

    @Override
    public void update(Product product) throws SQLException {

        if(product == null) return;

        session.beginTransaction();
        session.merge(product);
        session.getTransaction().commit();

    }

    @Override
    public void remove(Product product) throws SQLException {

        if(product == null) return;

        session.beginTransaction();
        session.remove(product);
        session.getTransaction().commit();

    }
}
