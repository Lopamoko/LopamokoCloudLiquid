package DataModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "shop_product_order", schema = "public", catalog = "postgres")
public class ShopProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_product_order_id")
    private long shop_product_order_id;

    @Column(name = "order_id")
    private long order_id;

    @Column(name = "shop_product_id")
    private long shop_product_id;

    @Column(name = "counter")
    private int counter;

}
