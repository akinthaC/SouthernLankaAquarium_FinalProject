package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor@AllArgsConstructor
@Data
public class OrderDetail {
    private String ordId;
    private String empId;
    private String fishId;
    private String accId;
    private int qty;
    private String status;
    private String description;
}
