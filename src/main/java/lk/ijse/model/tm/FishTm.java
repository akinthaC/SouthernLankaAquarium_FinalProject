package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FishTm {
    private String id;
    private String name ;
    private String Qty;
    private double normalPrice;
    private double wholesaleprice;

}
