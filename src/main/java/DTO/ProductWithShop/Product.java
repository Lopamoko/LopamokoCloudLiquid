package DTO.ProductWithShop;

import DTO.ShopWithProduct.Basic.Image;
import DTO.ShopWithProduct.Basic.Information;
import DTO.ShopWithProduct.Comment;
import DTO.ShopWithProduct.Rating;
import lombok.*;

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
    private String product_category;
    private List<Information> product_information;
    private List<Shop> shops;
    private List<Image> image;
    private List<Comment> product_comment;
    private List<Rating> product_rating;

    public List<Rating> getProduct_rating() {
        return product_rating;
    }
}
