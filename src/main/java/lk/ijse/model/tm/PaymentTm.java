package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor@AllArgsConstructor
@ToString
public class PaymentTm {
    private String id;
    private String ordid;
    private String date ;
    private double total;
    private double advance;
    private String type;
    private double amountToPaid;
    private String status;
}
