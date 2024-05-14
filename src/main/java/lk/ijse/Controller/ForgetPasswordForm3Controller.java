package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.repository.UserRepo;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.SQLException;

public class ForgetPasswordForm3Controller {

    public Label lblText;
    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnSave;

    @FXML
    private PasswordField txtConformPassword;

    @FXML
    private TextField txtConformPassword1;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPassword1;

    public void initialize(){
        txtPassword1.setVisible(false);
        txtConformPassword1.setVisible(false);
    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/NEW.fxml"));
        Scene scene = btnBack.getScene();
        root.translateXProperty().set(scene.getWidth());

        AnchorPane parentContainer = (AnchorPane) scene.getRoot();

        // Remove the existing content
        parentContainer.getChildren().clear();

        // Add the new content
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String password = txtPassword.getText();
        String reEnterPassword = txtConformPassword.getText();
        String userName = ForgetPasswordForm1Controller.userName;

        if (password.equalsIgnoreCase(reEnterPassword)){
            boolean isUpdated= UserRepo.update(password,userName);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Password Updated!!").show();
            }
        }

    }

    @FXML
    void showPasswordOnMousePresseds(MouseEvent event) {
        txtPassword.setVisible(false);
        txtPassword1.setText(txtPassword.getText());
        txtPassword1.setVisible(true);

    }

    @FXML
    void showPasswordOnMousePresseds1(MouseEvent event) {
        txtConformPassword.setVisible(false);
        txtConformPassword1.setText(txtConformPassword.getText());
        txtConformPassword1.setVisible(true);
    }

    @FXML
    void showPasswordOnMouseReleased(MouseEvent event) {
        txtPassword.setVisible(true);
        txtPassword1.setVisible(false);
    }

    @FXML
    void showPasswordOnMouseReleased1(MouseEvent event) {
        txtConformPassword.setVisible(true);
        txtConformPassword1.setVisible(false);
    }

    @FXML
    void txtConformPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.utill.TextField.PASSWORD,txtConformPassword1);
    }

    @FXML
    void txtNewPasswordOnKeyReleased1(KeyEvent event) {
        Regex.setTextColor(lk.ijse.utill.TextField.PASSWORD,txtPassword);
    }


    public void txtNewPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.PASSWORD,txtPassword1);
    }

    public void txtConformPasswordOnKeyReleased1(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.PASSWORD,txtConformPassword);


        if (txtPassword.getText().equals(txtConformPassword.getText())) {
            lblText.setStyle("-fx-text-fill: Green");
            lblText.setText("Password is Matched");
        } else {
            lblText.setStyle("-fx-text-fill: Red");
            lblText.setText("Password does not match");
        }
    }
}
