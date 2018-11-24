package DTO.ShopWithProduct.Basic;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    private long information_id;
    private String information_name;
    private String information_value;

}
