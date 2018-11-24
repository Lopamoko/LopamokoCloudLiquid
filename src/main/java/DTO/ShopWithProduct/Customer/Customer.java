package DTO.ShopWithProduct.Customer;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private long customer_id;
    private String customer_login;
    private String customer_password;
    private String customer_uuid;
    private String customer_first_name;
    private String customer_middle_name;
    private String customer_last_name;
    private String customer_email;
    private String customer_phone;
    private Date customer_birthdate;
}
