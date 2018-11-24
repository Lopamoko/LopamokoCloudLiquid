package DTO;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ShopProductOrder {
    private long shop_product_order_id;
    private long order_id;
    private int shop_product_id;
    private int counter;
}
