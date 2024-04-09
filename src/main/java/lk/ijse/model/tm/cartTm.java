package lk.ijse.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class cartTm {
    private String code;
    private Date date;
    private Date handOver;
    private String description;
    private int qty;
    private double unitPrice;
    private double total;
    private String status;
    private String id;
    private JFXButton btnRemove;
}