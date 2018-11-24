package DataModel;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_id")
    private long shop_id;

    @Column(name = "shop_name")
    private String shop_name;

    @Column(name = "shop_email")
    private String shop_email;

    @Column(name = "shop_phone")
    private String shop_phone;

    @OneToMany(mappedBy = "shop_list")
    private List<ShopProduct> products;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany
    @JoinTable(name = "shop_information",
    joinColumns = @JoinColumn(name = "shop_id"),
    inverseJoinColumns = @JoinColumn(name = "information_id"))
    private List<Information> shop_information;

    @OneToMany(mappedBy = "shop_rating")
    private List<Rating> shop_rating;

    @OneToMany(mappedBy = "shop_comment")
    private List<Comment> shop_comment;

    public long getShop_id() {
        return shop_id;
    }

    public void setShop_id(long shop_id) {
        this.shop_id = shop_id;
    }
}
