package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupAcc {
    private String AccId;
    private String supId;
    private Date date ;
    private int Qty;
    private double amount;
}
