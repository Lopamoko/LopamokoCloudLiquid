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
public class Shop {
    private long shop_id;
    private String shop_name;
    private String shop_email;
    private String shop_phone;
    private DTO.ShopWithProduct.Basic.Image image;
    private List<Information> shop_information;
    private List<DTO.ShopWithProduct.Rating> shop_rating;
    private List<Comment> shop_comment;
}
