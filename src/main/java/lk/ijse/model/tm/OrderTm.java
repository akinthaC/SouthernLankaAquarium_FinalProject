package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderTm {
    private String id;
    private String date ;
    private String handOverDate;
    private String Qty;
    private String Status;
}
