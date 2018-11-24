package DBLayout;
import DataModel.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateUtil {
    @Autowired
    private static SessionFactory sessionFactory;

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
                configuration.setProperty("hibernate.enable_lazy_load_no_trans","true");
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Shop.class);
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(Image.class);
                configuration.addAnnotatedClass(Information.class);
                configuration.addAnnotatedClass(ShopProduct.class);
                configuration.addAnnotatedClass(ShopProductId.class);
                configuration.addAnnotatedClass(ShopProductInformation.class);
                configuration.addAnnotatedClass(Comment.class);
                configuration.addAnnotatedClass(Rating.class);
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(ShopProductOrder.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
