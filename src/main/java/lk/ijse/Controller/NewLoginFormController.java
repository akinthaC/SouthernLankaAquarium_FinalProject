package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Db.DbConnection;
import lk.ijse.Launcher;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewLoginFormController implements Initializable {

    @FXML
    private Label AA;

    @FXML
    private Label AAA;

    @FXML
    private Label AI;

    @FXML
    private Label AM;

    @FXML
    private Label AQ;

    @FXML
    private Label AR;

    @FXML
    private Label AU;

    @FXML
    private Label AUU;

    @FXML
    private Label LA;
    @FXML
    private Label LAA;

    @FXML
    private Label LK;

    @FXML
    private Label LL;

    @FXML
    private Label LN;

    @FXML
    private Label SE;

    @FXML
    private Label SH;

    @FXML
    private Label SN;

    @FXML
    private Label SO;

    @FXML
    private Label SR;

    @FXML
    private Label SS;

    @FXML
    private Label ST;

    @FXML
    private Label SU;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label labaleLogin;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private static final String TEXT_TO_TYPE = "Welcome To The System..";
    private static final int SPEED = 300; // milliseconds per character

    private int index;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(2000), SS);
        fadeTransition1.setFromValue(0.0);
        fadeTransition1.setToValue(1.0);
        fadeTransition1.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(3000), SO);
        fadeTransition2.setFromValue(0.0);
        fadeTransition2.setToValue(1.0);
        fadeTransition2.play();

        FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(4000), SU);
        fadeTransition3.setFromValue(0.0);
        fadeTransition3.setToValue(1.0);
        fadeTransition3.play();

        FadeTransition fadeTransition4 = new FadeTransition(Duration.millis(5000), ST);
        fadeTransition4.setFromValue(0.0);
        fadeTransition4.setToValue(1.0);
        fadeTransition4.play();

        FadeTransition fadeTransition5 = new FadeTransition(Duration.millis(6000), SH);
        fadeTransition5.setFromValue(0.0);
        fadeTransition5.setToValue(1.0);
        fadeTransition5.play();

        FadeTransition fadeTransition6 = new FadeTransition(Duration.millis(7000), SE );
        fadeTransition6.setFromValue(0.0);
        fadeTransition6.setToValue(1.0);
        fadeTransition6.play();

        FadeTransition fadeTransition7 = new FadeTransition(Duration.millis(8000), SR);
        fadeTransition7.setFromValue(0.0);
        fadeTransition7.setToValue(1.0);
        fadeTransition7.play();

        FadeTransition fadeTransition8 = new FadeTransition(Duration.millis(9000), SN);
        fadeTransition8.setFromValue(0.0);
        fadeTransition8.setToValue(1.0);
        fadeTransition8.play();

        FadeTransition fadeTransition9 = new FadeTransition(Duration.millis(10000), LL);
        fadeTransition9.setFromValue(0.0);
        fadeTransition9.setToValue(1.0);
        fadeTransition9.play();

        FadeTransition fadeTransition10 = new FadeTransition(Duration.millis(11000), LA);
        fadeTransition10.setFromValue(0.0);
        fadeTransition10.setToValue(1.0);
        fadeTransition10.play();

        FadeTransition fadeTransition11 = new FadeTransition(Duration.millis(12000), LN);
        fadeTransition11.setFromValue(0.0);
        fadeTransition11.setToValue(1.0);
        fadeTransition11.play();

        FadeTransition fadeTransition12 = new FadeTransition(Duration.millis(13000), LK);
        fadeTransition12.setFromValue(0.0);
        fadeTransition12.setToValue(1.0);
        fadeTransition12.play();

        FadeTransition fadeTransition13 = new FadeTransition(Duration.millis(14000), LAA);
        fadeTransition13.setFromValue(0.0);
        fadeTransition13.setToValue(1.0);
        fadeTransition13.play();

        FadeTransition fadeTransition14 = new FadeTransition(Duration.millis(15000), AA);
        fadeTransition14.setFromValue(0.0);
        fadeTransition14.setToValue(1.0);
        fadeTransition14.play();

        FadeTransition fadeTransition15 = new FadeTransition(Duration.millis(16000), AQ);
        fadeTransition15.setFromValue(0.0);
        fadeTransition15.setToValue(1.0);
        fadeTransition15.play();

        FadeTransition fadeTransition16 = new FadeTransition(Duration.millis(17000), AU);
        fadeTransition16.setFromValue(0.0);
        fadeTransition16.setToValue(1.0);
        fadeTransition16.play();

        FadeTransition fadeTransition17 = new FadeTransition(Duration.millis(18000), AAA);
        fadeTransition17.setFromValue(0.0);
        fadeTransition17.setToValue(1.0);
        fadeTransition17.play();

        FadeTransition fadeTransition18 = new FadeTransition(Duration.millis(19000), AR);
        fadeTransition18.setFromValue(0.0);
        fadeTransition18.setToValue(1.0);
        fadeTransition18.play();

        FadeTransition fadeTransition19 = new FadeTransition(Duration.millis(20000), AI);
        fadeTransition19.setFromValue(0.0);
        fadeTransition19.setToValue(1.0);
        fadeTransition19.play();

        FadeTransition fadeTransition20 = new FadeTransition(Duration.millis(21000), AUU);
        fadeTransition20.setFromValue(0.0);
        fadeTransition20.setToValue(1.0);
        fadeTransition20.play();

        FadeTransition fadeTransition21 = new FadeTransition(Duration.millis(22000), AM);
        fadeTransition21.setFromValue(0.0);
        fadeTransition21.setToValue(1.0);
        fadeTransition21.play();

        index = 0;


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(SPEED), event -> {
                    if (index < TEXT_TO_TYPE.length()) {
                        labaleLogin.setText(labaleLogin.getText() + TEXT_TO_TYPE.charAt(index));
                        index++;
                    }
                })
        );
        timeline.setCycleCount(TEXT_TO_TYPE.length() + 1);
        timeline.play();
    }
    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userName=txtUserName.getText();
        String password=txtPassword.getText();

        try {
            checkCredential(userName, password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void checkCredential(String userName, String password) throws SQLException, IOException {
        String sql = "SELECT userName, password FROM user WHERE userName = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userName);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPw = resultSet.getString("password");

            if(password.equals(dbPw)) {
                navigateToTheDashboard();

            } else {
                new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
        }
    }



    @FXML
    void hyperlinkFrogetPasswordOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reset_password.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.setTitle("Forget Password");

        stage.show();

    }

    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }


    public void txtLoginUserNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.USERNAME,txtUserName);

    }

    public void txtLoginPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.PASSWORD,txtPassword);
    }
}
