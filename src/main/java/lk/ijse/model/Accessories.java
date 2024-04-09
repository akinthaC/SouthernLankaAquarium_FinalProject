package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Accessories {
    private String id;
    private String supid;
    private String name ;
    private String Qty;
    private String normalPrice;
    private String wholesaleprice;
}
