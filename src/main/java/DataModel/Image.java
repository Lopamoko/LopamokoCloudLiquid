package DataModel;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private long image_id;

    @Column(name = "image_url")
    private String image_url;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Shop shop_image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_image;

}
