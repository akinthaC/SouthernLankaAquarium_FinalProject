package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SupAccTm {
    private String AccId;
    private String supId;
    private Date date ;
    private int Qty;
    private double amount;

}
