package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OtpFormController {

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnResend;

    @FXML
    private JFXButton btnVerify;

    @FXML
    void btnGoBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnResendOnAction(ActionEvent event) {

    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/froget_password_form.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.setTitle("Reset Password Form");

        stage.show();

    }

}
