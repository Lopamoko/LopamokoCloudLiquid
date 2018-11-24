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
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private long comment_id;

    @Column(name = "comment_message")
    private String comment_message;

    @Column(name = "comment_datetime")
    private Timestamp comment_datetime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer_comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_comment;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop_comment;
}
