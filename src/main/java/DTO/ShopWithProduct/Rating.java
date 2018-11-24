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
public class Rating {
    private long rating_id;
    private String rating_type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp rating_datetime;
    private Customer customer_rating;

    public String getRating_type() {
        return rating_type;
    }
}
