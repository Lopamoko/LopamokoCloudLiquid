package Managers;

import DBLayout.HibernateUtil;
import DTO.ShopWithProduct.Customer.Customer;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;

import javax.persistence.NoResultException;

public class CustomerManager {
    public DTO.ShopWithProduct.Customer.Customer getCustomer(String customer_login, String customer_password, String customer_uuid){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        DataModel.Customer customer = session.createQuery("from Customer where customer_login = '"+customer_login+"' " +
                    "and customer_password = '"+customer_password+"' " +
                    "and customer_uuid = '"+customer_uuid+"'", DataModel.Customer.class).setMaxResults(1).getSingleResult();
            DTO.ShopWithProduct.Customer.Customer customerDto = new ModelMapper().map(customer, DTO.ShopWithProduct.Customer.Customer.class);
            session.close();
            return customerDto;
        }catch (NoResultException e){
            session.close();
            return new DTO.ShopWithProduct.Customer.Customer();
        }
    }

    public void updateCustomerInfo(String uuid,String first_name, String middle_name, String last_name, String phone, String email, boolean email_notification, String adress){
        System.out.println("im in update method");
        Session session = HibernateUtil.getSessionFactory().openSession();
        DataModel.Customer customer = session.createQuery("from Customer where customer_uuid = '"+uuid+"'", DataModel.Customer.class).setMaxResults(1).getSingleResult();
        if (first_name != null) customer.setCustomer_first_name(first_name);
        if (middle_name != null) customer.setCustomer_middle_name(middle_name);
        if (last_name != null) customer.setCustomer_last_name(last_name);
        if (email != null) customer.setCustomer_email(email);
        if (adress != null) customer.setCustomer_adress(adress);
        if (phone.length() > 4) customer.setCustomer_phone(phone);
        customer.setEmail_notification(email_notification);
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    public DTO.CustomerInfo.Customer getCustomerInfo(String uuid){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        DataModel.Customer customer = session.createQuery("from Customer where customer_uuid = '"+uuid+"'", DataModel.Customer.class).setMaxResults(1).getSingleResult();
        session.close();
        ModelMapper modelMapper = new ModelMapper();
        DTO.CustomerInfo.Customer user = modelMapper.map(customer, DTO.CustomerInfo.Customer.class);
        return user;
       }catch (Exception e){
            session.close();
           return new DTO.CustomerInfo.Customer();
       }
    }

    public DTO.ShopWithProduct.Customer.Customer getCustomerByLoginAndPassword(String customer_login, String customer_password, String customer_uuid){
        System.out.println("customer_uuid is " + customer_uuid);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            DataModel.Customer customer = session.createQuery("from Customer where customer_login = '"+customer_login+"' " +
                    "and customer_password = '"+customer_password+"' " , DataModel.Customer.class).setMaxResults(1).getSingleResult();
            session.beginTransaction();
            customer.setCustomer_uuid(customer_uuid);
            session.save(customer);
            session.getTransaction().commit();
            DTO.ShopWithProduct.Customer.Customer customerDto = new ModelMapper().map(customer, DTO.ShopWithProduct.Customer.Customer.class);
            session.close();
            return customerDto;
        }catch (NoResultException e){
            session.close();
            return new DTO.ShopWithProduct.Customer.Customer();
        }
    }

    public DTO.ShopWithProduct.Customer.Customer addCustomer(String customer_login, String customer_password, String customer_uuid){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            DataModel.Customer customer = session.createQuery("from Customer where customer_login = '"+customer_login+"' " +
                    "and customer_password = '"+customer_password+"' " +
                    "and customer_uuid = '"+customer_uuid+"'", DataModel.Customer.class).setMaxResults(1).getSingleResult();
            session.close();
            DTO.ShopWithProduct.Customer.Customer customerDto = new ModelMapper().map(customer, DTO.ShopWithProduct.Customer.Customer.class);
            return customerDto;
        }catch (NoResultException e){
            session.beginTransaction();
            DataModel.Customer customer = new DataModel.Customer(customer_login,customer_password,customer_uuid);
            session.save(customer);
            session.getTransaction().commit();
            session.close();
            return new ModelMapper().map(customer, Customer.class);
        }
    }
}
