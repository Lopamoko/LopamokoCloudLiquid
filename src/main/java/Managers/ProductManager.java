package Managers;


import DBLayout.HibernateUtil;
import DTO.ProductWithShop.Product;
import DTO.ShopWithProduct.Comment;
import DataModel.Customer;
import DataModel.Rating;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {


    public DTO.ShopWithProduct.Rating addRating(long product_id, String type, long customer_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        DataModel.Product product = session.createQuery("from Product where product_id = "+product_id+"", DataModel.Product.class).getSingleResult();
        Customer customer = session.createQuery("from Customer where customer_id = "+customer_id+"", Customer.class).getSingleResult();
        try {
            Rating rating = session.createQuery("from Rating where customer_rating.customer_id = " + customer_id + " and product_rating.product_id = " + product_id + "", Rating.class).getSingleResult();
            session.beginTransaction();
            rating.setRating_type(type);
            session.save(rating);
            session.getTransaction().commit();
            session.close();
            return new ModelMapper().map(rating, DTO.ShopWithProduct.Rating.class);
        }catch (NoResultException e) {
            session.beginTransaction();
            Rating rating = new Rating();
            rating.setRating_datetime(new Timestamp(System.currentTimeMillis()));
            rating.setRating_type(type);
            rating.setProduct_rating(product);
            rating.setCustomer_rating(customer);
            session.save(rating);
            session.getTransaction().commit();
            session.close();
            return new ModelMapper().map(rating, DTO.ShopWithProduct.Rating.class);
        }
    }
    public List addComment(long product_id,String message,long customer_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        DataModel.Product product = session.createQuery("from Product where product_id = "+product_id+"", DataModel.Product.class).getSingleResult();
        Customer customer = session.createQuery("from Customer where customer_id = "+customer_id+"", Customer.class).getSingleResult();

        session.beginTransaction();
        DataModel.Comment comment = new DataModel.Comment();
        comment.setComment_datetime(new Timestamp(System.currentTimeMillis()));
        comment.setComment_message(message);
        comment.setProduct_comment(product);
        comment.setCustomer_comment(customer);
        session.save(comment);
        session.getTransaction().commit();

        List comments = session.createQuery("from Comment where product_comment.product_id = "+product_id+"").list();
        session.close();
        ArrayList<Comment> commenters = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < comments.size(); i++){
            commenters.add(modelMapper.map(comments.get(i), Comment.class));
        }
        return commenters;
    }
    public List getComment(long product_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List comment = session.createQuery("from Comment where product_comment.id = "+product_id+"").list();
        session.close();
        ArrayList<Comment> comments = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < comment.size(); i++){
            comments.add(modelMapper.map(comment.get(i), Comment.class));
        }
        return comments;
    }
    public List getTopProduct(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List products = session.createQuery("from DataModel.Product").setMaxResults(10).list();
        session.close();
        ArrayList<Product> productDto = new ArrayList();
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < products.size(); i++){
            productDto.add(modelMapper.map(products.get(i), Product.class));
        }
        return productDto;
    }

    public List getAllProduct(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List products = session.createQuery("from DataModel.Product").list();
        session.close();
        ArrayList<Product> productDto = new ArrayList();
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < products.size(); i++){
            productDto.add(modelMapper.map(products.get(i), Product.class));
        }
        return productDto;
    }

    public List getCategories(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List categories = session.createQuery("from Category ").list();
        session.close();
        return categories;
    }

    public DTO.ProductInShop.Product getProductById(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        DataModel.Product product = session.createQuery("from Product where product_id = " + id , DataModel.Product.class).getSingleResult();
        session.close();
        return new ModelMapper().map(product, DTO.ProductInShop.Product.class);
    }

}
