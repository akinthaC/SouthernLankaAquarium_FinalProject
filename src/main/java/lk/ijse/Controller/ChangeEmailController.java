package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.repository.UserRepo;
import lk.ijse.utill.Regex;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChangeEmailController {

    @FXML
    private AnchorPane MainPain;

    @FXML
    private JFXButton btnChangeEmail;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblText;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUserName;

    @FXML
    private AnchorPane mainPain;

    @FXML
    private TextField txtConformEmail;

    @FXML
    private TextField txtEmail;

    public void initialize(){
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
    void btnChangeEmailOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String reEnterEmail = txtConformEmail.getText();
        String userName = NewLoginFormController.userName1;
        try {
            if(email.isEmpty() || reEnterEmail.isEmpty()  ) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }


        try {

            if (email.equalsIgnoreCase(reEnterEmail)) {

                boolean isUpdated = UserRepo.update1(email, userName);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Email Updated!!").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Email not Updated!!").show();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtConformEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.utill.TextField.EMAIL,txtConformEmail);


        if (txtEmail.getText().equals(txtConformEmail.getText())) {
            lblText.setStyle("-fx-text-fill: Green");
            lblText.setText("Email is Matched");
        } else {
            lblText.setStyle("-fx-text-fill: Red");
            lblText.setText("Email does not match");
        }

    }

    @FXML
    void txtNewEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.utill.TextField.EMAIL,txtEmail);

    }

}
