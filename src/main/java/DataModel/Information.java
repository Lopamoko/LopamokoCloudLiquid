package DataModel;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "information_id")
    private long information_id;

    @Column(name = "information_name")
    private String information_name;

    @Column(name = "information_value")
    private String information_value;

    @ManyToMany
    @JoinTable(name = "shop_information",
    joinColumns = @JoinColumn(name = "information_id"),
    inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<Shop> shop_information;

    @ManyToMany
    @JoinTable(name = "product_information",
    joinColumns = @JoinColumn(name = "information_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> product_information;

}
