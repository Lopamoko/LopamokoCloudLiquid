package DTO.ShopWithProduct.Basic;

import DTO.ShopWithProduct.Product;
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
    private Product product_list;
    private List<ShopProductInformation> shopProductInformations;
}
