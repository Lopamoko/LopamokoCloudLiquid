package Managers;

import DBLayout.HibernateUtil;
import DataModel.Customer;
import DataModel.Order;
import DataModel.ShopProductOrder;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    public List getAllOrderByCustomerId(long customer_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        List<Order> order = session.createQuery("from Order where customer_order.customer_id = " + customer_id, Order.class).list();
        List<DTO.Order> dtoOrder = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (int i = 0; i < order.size(); i++){
            dtoOrder.add(mapper.map(order.get(i), DTO.Order.class));
        }
        session.close();
        return dtoOrder;
        }catch (Exception e){
            session.close();
            return null;
        }
    }
    public DTO.Order getCurrentOrderByCustomerId(long customer_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Order order = session.createQuery("from Order where customer_order.customer_id = "+customer_id+" and order_status = 'Выбор товара' and order_finish = false", Order.class).setMaxResults(1).getSingleResult();
        session.close();
        return new ModelMapper().map(order, DTO.Order.class);
        }catch (NoResultException e){
            session.close();
            return createNewOrder(customer_id);
        }
    }

    public DTO.Order createNewOrder(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        Order order = new Order(new Timestamp(System.currentTimeMillis()));
        Customer customer = session.createQuery("from Customer where customer_id = " + id, Customer.class).setMaxResults(1).getSingleResult();
        order.setCustomer_order(customer);
        order.setOrder_status("Выбор товара");
        order.setOrder_finish(false);
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        session.close();
        return new ModelMapper().map(order, DTO.Order.class);

        }catch (Exception e){
            session.close();
            return null;
        }
    }

    public DTO.Order addToOrder(long shop_product_id, long order_id){
       Session session = HibernateUtil.getSessionFactory().openSession();
       try {
           ShopProductOrder shopProductOrder = new ShopProductOrder();
           shopProductOrder.setOrder_id(order_id);
           shopProductOrder.setShop_product_id(shop_product_id);

           session.beginTransaction();
           session.save(shopProductOrder);
           session.getTransaction().commit();
           Order order = session.createQuery("from Order where order_id = " + order_id, Order.class).setMaxResults(1).getSingleResult();
           session.close();
           return new ModelMapper().map(order, DTO.Order.class);
       }catch (Exception e){
           session.close();
           return null;
       }
    }
    public DTO.Order removeFromOrder(long shop_product_id, long order_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        session.beginTransaction();
        session.createNativeQuery("delete  from shop_product_order as t1 where t1.shop_product_id = "+ shop_product_id +" and t1.order_id = "+order_id+"").executeUpdate();
        session.getTransaction().commit();
        Order order = session.createQuery("from Order where order_id = " + order_id, Order.class).setMaxResults(1).getSingleResult();
        session.close();
        return new ModelMapper().map(order, DTO.Order.class);
        }catch (Exception e){
            session.close();
            return null;
        }
    }
    public DTO.Order finishOrder(long order_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Order order = session.createQuery("from Order where order_id = " + order_id, Order.class).setMaxResults(1).getSingleResult();
            order.setOrder_finish(true);
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
            session.close();
            return new ModelMapper().map(order, DTO.Order.class);
        }catch (Exception e){
            session.close();
            return null;
        }
    }
}
