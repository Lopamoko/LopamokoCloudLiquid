package DataModel;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customer_id;

    @Column(name = "customer_login")
    private String customer_login;

    @Column(name = "customer_password")
    private String customer_password;

    @Column(name = "customer_uuid")
    private String customer_uuid;

    @Column(name = "customer_first_name")
    private String customer_first_name;

    @Column(name = "customer_middle_name")
    private String customer_middle_name;

    @Column(name = "customer_last_name")
    private String customer_last_name;

    @Column(name = "customer_email")
    private String customer_email;

    @Column(name = "customer_phone")
    private String customer_phone;

    @Column(name = "customer_bithdate")
    private Date customer_birthdate;

    @Column(name = "customer_adress")
    private String customer_adress;

    @Column(name = "email_notification")
    private boolean email_notification;

    @OneToMany(mappedBy = "customer_order")
    private List<Order> customer_orders;

    @OneToMany(mappedBy = "customer_rating")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "customer_comment")
    private List<Comment> comments;

    public Customer(String customer_login,String customer_password,String customer_uuid){
        this.customer_login = customer_login;
        this.customer_password = customer_password;
        this.customer_uuid = customer_uuid;
    }
    public Customer(){}

    public String getCustomer_uuid() {
        return customer_uuid;
    }

    public void setCustomer_uuid(String customer_uuid) {
        this.customer_uuid = customer_uuid;
    }

    public void setCustomer_first_name(String customer_first_name) {
        this.customer_first_name = customer_first_name;
    }

    public void setCustomer_middle_name(String customer_middle_name) {
        this.customer_middle_name = customer_middle_name;
    }

    public void setCustomer_last_name(String customer_last_name) {
        this.customer_last_name = customer_last_name;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public void setCustomer_birthdate(Date customer_birthdate) {
        this.customer_birthdate = customer_birthdate;
    }

    public void setCustomer_adress(String customer_adress) {
        this.customer_adress = customer_adress;
    }

    public void setEmail_notification(boolean email_notification) {
        this.email_notification = email_notification;
    }
}
