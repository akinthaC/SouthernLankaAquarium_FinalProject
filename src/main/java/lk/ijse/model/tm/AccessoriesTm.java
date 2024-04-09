package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessoriesTm{
    private String id;
    private String supid;
    private String name ;
    private String Qty;
    private String normalPrice;
    private String wholesaleprice;
}
