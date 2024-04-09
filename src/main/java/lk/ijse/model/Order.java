package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Order {
    private String id;
    private String date ;
    private String handOverDate;
    private String Qty;
    private String Status;
}
