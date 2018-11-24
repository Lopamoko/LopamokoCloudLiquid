package DataModel;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long order_id;

    @Column(name = "order_finish")
    private boolean order_finish;

    @Column(name = "order_status")
    private String order_status;

    @Column(name = "order_date")
    private Timestamp order_date;

    @ManyToMany
    @JoinTable(name = "shop_product_order",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = {@JoinColumn(name = "shop_product_id")})
    private List<ShopProduct> products;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer_order;

    public Order(Timestamp order_date) {
        this.order_date = order_date;
    }
    public Order(){}


}
