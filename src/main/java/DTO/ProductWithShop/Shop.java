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

public class Shop {
        private long shop_id;
        private String shop_name;
        private String shop_email;
        private String shop_phone;
        private Image image;
        private List<Information> shop_information;
        private List<Rating> shop_rating;
        private List<Comment> shop_comment;
}
