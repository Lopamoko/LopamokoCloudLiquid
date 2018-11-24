package DTO.ShopWithProduct;

import DTO.ShopWithProduct.Basic.Image;
import DTO.ShopWithProduct.Basic.Information;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long product_id;
    private String product_manufacturer;
    private String product_name;
    private List<Image> image;
    private List<Information> product_information;
    private String product_category;

}
