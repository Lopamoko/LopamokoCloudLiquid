package Managers;


import DBLayout.HibernateUtil;
import DTO.ShopWithProduct.Comment;
import DataModel.Customer;
import DataModel.Rating;
import DataModel.Shop;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShopManager {


    public List getComment(long shop_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        List comment = session.createQuery("from Comment where shop_comment.id = "+shop_id+"").list();
        session.close();
        ArrayList<Comment> comments = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < comment.size(); i++){
            comments.add(modelMapper.map(comment.get(i), Comment.class));
        }
        return comments;

        }catch (Exception e){
            session.close();
            return null;
        }
    }


    public DTO.ShopWithProduct.Rating addRating(long shop_id, String type, long customer_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        DataModel.Shop shop = session.createQuery("from Shop where shop_id = "+shop_id+"", DataModel.Shop.class).getSingleResult();
        Customer customer = session.createQuery("from Customer where customer_id = "+customer_id+"", Customer.class).getSingleResult();
        try {
            Rating rating = session.createQuery("from Rating where customer_rating.customer_id = " + customer_id + " and shop_rating.shop_id = " + shop_id + "", Rating.class).getSingleResult();
            session.beginTransaction();
            rating.setRating_type(type);
            session.save(rating);
            session.getTransaction().commit();
            session.close();
            return new ModelMapper().map(rating, DTO.ShopWithProduct.Rating.class);
        }catch (Exception e) {
            session.beginTransaction();
            Rating rating = new Rating();
            rating.setRating_datetime(new Timestamp(System.currentTimeMillis()));
            rating.setRating_type(type);
            rating.setShop_rating(shop);
            rating.setCustomer_rating(customer);
            session.save(rating);
            session.getTransaction().commit();
            session.close();
            return new ModelMapper().map(rating, DTO.ShopWithProduct.Rating.class);
        }
    }
    public List addComment(long shop_id,String message,long customer_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        DataModel.Shop shop = session.createQuery("from Shop where shop_id = "+shop_id+"", DataModel.Shop.class).getSingleResult();
        Customer customer = session.createQuery("from Customer where customer_id = "+customer_id+"", Customer.class).getSingleResult();

        session.beginTransaction();
        DataModel.Comment comment = new DataModel.Comment();
        comment.setComment_datetime(new Timestamp(System.currentTimeMillis()));
        comment.setComment_message(message);
        comment.setShop_comment(shop);
        comment.setCustomer_comment(customer);
        session.save(comment);
        session.getTransaction().commit();

        List comments = session.createQuery("from Comment where shop_comment.shop_id = "+shop_id+"").list();
        session.close();
        ArrayList<Comment> commenters = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < comments.size(); i++){
            commenters.add(modelMapper.map(comments.get(i), Comment.class));
        }
        return commenters;
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    public List getAllShop(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        List<Shop> shops = session.createQuery("from DataModel.Shop").list();
        List<DTO.ShopWithProduct.Shop> shopWithProduct = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Shop shop : shops){
            shopWithProduct.add(mapper.map(shop, DTO.ShopWithProduct.Shop.class));
        }
        session.close();
        return shopWithProduct;

        }catch (Exception e){
            session.close();
            return null;
        }
    }
    public DTO.ShopWithProduct.Shop getShopById(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        Shop shop = session.createQuery("from Shop where shop_id = "+ id, Shop.class).getSingleResult();
        session.close();
        return new ModelMapper().map(shop, DTO.ShopWithProduct.Shop.class);

        }catch (Exception e){
            session.close();
            return null;
        }
    }
}
