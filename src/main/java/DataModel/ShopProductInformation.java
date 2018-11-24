package DataModel;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopproductinformation")
public class ShopProductInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "information_id")
    private long information_id;

    @Column(name = "information_name")
    private String information_name;

    @Column(name = "information_value")
    private String information_value;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private ShopProduct shopProduct;

}
