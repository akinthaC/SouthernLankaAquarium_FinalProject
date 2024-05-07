package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.utill.Regex;

public class FrogetPasswordFormController {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TextField txtConformPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserNmae;

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    public void txtUserNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.USERNAME,txtUserNmae);
    }

    public void txtNewPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.PASSWORD,txtPassword);
    }

    public void txtConformPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.PASSWORD,txtConformPassword);
    }
}
