package DataModel;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    private long rating_id;

    @Column(name = "rating_type")
    private String rating_type;

    @Column(name = "rating_datetime")
    private Timestamp rating_datetime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer_rating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_rating;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop_rating;

}
