package DTO;

import DTO.ShopWithProduct.Basic.ShopProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Order {
    private long order_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private Timestamp order_date;
    private List<ShopProduct> products;
    private boolean order_finish;
    private String order_status;
}
