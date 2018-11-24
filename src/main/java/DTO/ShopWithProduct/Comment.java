package DTO.ShopWithProduct;

import DTO.ShopWithProduct.Basic.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long comment_id;
    private String comment_message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp comment_datetime;
    private Customer customer_comment;
}
