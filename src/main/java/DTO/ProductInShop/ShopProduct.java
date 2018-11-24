package DTO.ProductInShop;

import DTO.ShopWithProduct.Basic.ShopProductInformation;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ShopProduct {
    private long shop_product_id;
    private Shop shop_list;
    private List<ShopProductInformation> shopProductInformations;
}
