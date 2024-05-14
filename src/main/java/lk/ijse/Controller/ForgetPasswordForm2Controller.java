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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.IOException;

public class ForgetPasswordForm2Controller {
    public AnchorPane AnchorPaneFogotPassword2;

    public Label lblStatus;
    public Label lblOtp;
    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnResend;

    @FXML
    private TextField otp1;

    @FXML
    private TextField otp2;

    @FXML
    private TextField otp3;

    @FXML
    private TextField otp4;

    @FXML
    private TextField otp5;

    @FXML
    private TextField otp6;

    @FXML
    private JFXButton btnVerify;


    public void initialize(){
        lblOtp.setText(String.valueOf(ForgetPasswordForm1Controller.OTP));
    }
    @FXML
    void btnGoBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/NEW.fxml"));
        Scene scene = btnGoBack.getScene();
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
    void btnResendOnAction(ActionEvent event) {


    }
    @FXML
    void txtOTP1OnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOTP2OnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOTP3OnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOTP4OnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOTP5OnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtOTP6OnKeyReleased(KeyEvent event) {

    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) throws IOException {
        String Otp1 = otp1.getText();
        String Otp2 = otp2.getText();
        String Otp3 = otp3.getText();
        String Otp4 = otp4.getText();
        String Otp5 = otp5.getText();
        String Otp6 = otp6.getText();

        String setOtp=Otp1+Otp2+Otp3+Otp4+Otp5+Otp6;
        System.out.println("setOtp = " + setOtp);
        System.out.println("setOtp = " + lblOtp);

        if (lblOtp.getText().equalsIgnoreCase(setOtp)){
            lblStatus.setStyle("-fx-text-fill: Green");
            lblStatus.setText("Correct OTP");

            Parent root = FXMLLoader.load(getClass().getResource("/view/froget_password_form3.fxml"));
            Scene scene = btnVerify.getScene();
            root.translateXProperty().set(scene.getWidth());

            AnchorPane parentContainer = (AnchorPane) scene.getRoot();
            parentContainer.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(event1 -> {
                parentContainer.getChildren().removeAll(AnchorPaneFogotPassword2);
            });
            timeline.play();
        }else {
            lblStatus.setStyle("-fx-text-fill: Red");
            lblStatus.setText("Invalid OTP");
        }

    }

}
