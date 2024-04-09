package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data@NoArgsConstructor@AllArgsConstructor
public class EmployeeTm {
    private String id;
    private String name;
    private String contact;
    private String NIC;
    private String address;

}
