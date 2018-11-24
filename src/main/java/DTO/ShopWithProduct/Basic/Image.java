package DTO.ShopWithProduct.Basic;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private long image_id;
    private String image_url;

}
