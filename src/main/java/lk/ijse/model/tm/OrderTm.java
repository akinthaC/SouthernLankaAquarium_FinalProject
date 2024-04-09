package lk.ijse.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderTm {
    private String id;
    private Date date ;
    private Date handOverDate;
    private int Qty;
    private String Status;
    private String Description;
    private String CusId;
}
