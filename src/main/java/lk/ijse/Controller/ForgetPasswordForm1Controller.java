package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Duration;
import lk.ijse.model.Supplier;
import lk.ijse.model.User;
import lk.ijse.repository.SupplierRepo;
import lk.ijse.repository.UserRepo;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class ForgetPasswordForm1Controller {

    public TextField txtEmail;
    public JFXButton btnSendOtp;
    @FXML
    private Button btnGoBack;
    public static String userName;
    public static int OTP;
    public static String email;




    @FXML
    private JFXComboBox<String> cmbserName;

    @FXML
    private AnchorPane rootNode;

    public void initialize() throws IOException {
        cmbserName.setEditable(true);
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
    void btnSendOtpOnAction(ActionEvent event) throws IOException {
        Object selectedItem1 = cmbserName.getSelectionModel().getSelectedItem();
        String Username = (String) selectedItem1;
        userName =Username;

        if (Username!=null){
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            cmbserName.setStyle("-fx-background-color: null");

            boolean ok =javaMailUtil.sendMail(txtEmail.getText(),otp);
            if (ok) {

                OTP = otp;
                System.out.println(">>>" + otp);

                Parent root = FXMLLoader.load(getClass().getResource("/view/forgetPasswordForm2.fxml"));

                Scene scene = btnSendOtp.getScene();
                root.setScaleX(0.5);
                root.setScaleY(0.5);
                root.setOpacity(0.0);

                AnchorPane parentContainer = (AnchorPane) scene.getRoot();

                // Remove the existing content
                parentContainer.getChildren().clear();

                // Add the new content
                parentContainer.getChildren().add(root);

                Timeline timeline = new Timeline();
                KeyValue scaleXValue = new KeyValue(root.scaleXProperty(), 1.0, Interpolator.EASE_BOTH);
                KeyValue scaleYValue = new KeyValue(root.scaleYProperty(), 1.0, Interpolator.EASE_BOTH);
                KeyValue opacityValue = new KeyValue(root.opacityProperty(), 1.0, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), scaleXValue, scaleYValue, opacityValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            }else {
                return;
            }
    }else {
        cmbserName.setStyle("-fx-background-color: Red");
    }
    }

    @FXML
    void filterUserName1(KeyEvent event) {
        ObservableList<String> filterData = FXCollections.observableArrayList();
        String enterText = cmbserName.getEditor().getText();

        try {

            List<String> idList = UserRepo.searchName();

            for (String name : idList){
                if (name.contains(enterText)){
                    filterData.add(name);
                }
            }
            cmbserName.setItems(filterData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String name = cmbserName.getValue();
        try {
            User user = UserRepo.searchByName(name);
            if(user!=null) {
                txtEmail.setText(user.getEmail());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.EMAIL,txtEmail);
    }


}
