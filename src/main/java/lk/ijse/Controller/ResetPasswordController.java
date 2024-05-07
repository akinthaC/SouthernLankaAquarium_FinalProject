package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.utill.Regex;

import java.io.IOException;

public class ResetPasswordController {

    public AnchorPane rootNode;
    @FXML
    private Button btnGoBack;

    @FXML
    private JFXButton btnResetPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    void btnGoBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/otp_form.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.setTitle("ResetPassword Form");

        stage.show();

    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.EMAIL,txtEmail);
    }
}
