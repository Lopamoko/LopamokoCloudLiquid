package DTO.ProductInShop;

import DTO.ShopWithProduct.Basic.Information;
import DTO.ShopWithProduct.Comment;
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
    private String product_category;
    private String product_manufacturer;
    private String product_name;
    private List<ShopProduct> shops;
    private List<DTO.ShopWithProduct.Basic.Image> image;
    private List<Information> product_information;
    private List<DTO.ShopWithProduct.Rating> product_rating;
    private List<Comment> product_comment;
}
