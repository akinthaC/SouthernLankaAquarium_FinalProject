package lk.ijse.model;

import javafx.beans.binding.StringExpression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String name;
    private String password;
    private String email;

}
