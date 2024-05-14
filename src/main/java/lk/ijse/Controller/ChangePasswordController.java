package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChangePasswordController {

    public Label lblUserName;
    public Label lblText;
    @FXML
    private AnchorPane MainPain;

    @FXML
    private JFXButton btnSave;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane mainPain;

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
        lblUserName.setText(NewLoginFormController.userName1);
        setDate();
        setTime();
    }

    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            LocalTime currentTime = LocalTime.now();

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = currentTime.format(timeFormatter);

            lblTime.setText(formattedTime);
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);

        clock.play();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));


    }



    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        String password = txtPassword.getText();
        String reEnterPassword = txtConformPassword.getText();
        String userName = NewLoginFormController.userName1;

        try {

        if (password.equalsIgnoreCase(reEnterPassword)) {
            System.out.println("userName + password+reEnterPassword = " + userName + password + reEnterPassword);

            boolean isUpdated = UserRepo.update(password, userName);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Password Updated!!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Password not Updated!!").show();
            }
        }
        } catch (Exception e) {
                throw new RuntimeException(e);
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

    @FXML
    public void txtNewPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.PASSWORD,txtPassword1);
    }
    @FXML
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
