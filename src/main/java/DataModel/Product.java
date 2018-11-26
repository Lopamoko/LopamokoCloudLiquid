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
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long product_id;

    @Column(name = "product_category")
    private String product_category;

    @Column(name = "product_manufacturer")
    private String product_manufacturer;

    @Column(name = "product_name")
    private String product_name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product_list")
    private List<ShopProduct> shops;

    @OneToMany(mappedBy = "product_image")
    private List<Image> image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_information",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "information_id"))
    private List<Information> product_information;

    @OneToMany(mappedBy = "product_rating")
    private List<Rating> product_rating;

    @OneToMany(mappedBy = "product_comment")
    private List<Comment> product_comment;

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}
