package DataModel;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "shop_product")
public class ShopProduct {
    @Id
    @Column(name = "shop_product_id")
    private long shop_product_id;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop_list;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_list;

    @ManyToMany
    @JoinTable(name = "shop_product_order",
    joinColumns = {@JoinColumn(name = "shop_product_id")},inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    @OneToMany(mappedBy = "shopProduct")
    private List<ShopProductInformation> shopProductInformations;

    public Shop getShop_list() {
        return shop_list;
    }

    public void setShop_list(Shop shop_list) {
        this.shop_list = shop_list;
    }

    public Product getProduct_list() {
        return product_list;
    }

    public void setProduct_list(Product product_list) {
        this.product_list = product_list;
    }

    public ShopProduct() {
    }
}
