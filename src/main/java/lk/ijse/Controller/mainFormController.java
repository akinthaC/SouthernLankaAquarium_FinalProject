package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainFormController  {

    public Label lblName;
    public Label lblGmail;
    @FXML
    private JFXButton btnAccessories;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnFish;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnOrders;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnReport;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private AnchorPane mainPane;


    @FXML
    private Label lblWelcome;

    @FXML
    private Label lblWelcome1;



//    private static final String TEXT_TO_TYPE = "Welcome To The Aquarium \n Management System \n\n ";
//    private static final String TEXT_TO_TYPE1 = "(you can manage this by using this buttons) ";
//    private static final int SPEED = 200; // milliseconds per character
//
//    private int index;
//
//
//    private int index1;
//    private int index2;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        index = 0;
//
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.millis(SPEED), event -> {
//                    if (index < TEXT_TO_TYPE.length() + TEXT_TO_TYPE1.length()) {
//                        if (index < TEXT_TO_TYPE.length()) {
//                            lblWelcome.setText(TEXT_TO_TYPE.substring(0, index + 1));
//                        }
//                        if (index >= TEXT_TO_TYPE.length()) {
//                            lblWelcome1.setText(TEXT_TO_TYPE1.substring(0, index - TEXT_TO_TYPE.length() + 1));
//                        }
//                        index++;
//                    }
//                })
//        );
//        timeline.setCycleCount(TEXT_TO_TYPE.length() + TEXT_TO_TYPE1.length());
//        timeline.play();
//    }

    public void initialize() throws IOException {
        dashBoard();
        addHoverAnimation(btnAccessories);
        addHoverAnimation(btnDashboard);
        addHoverAnimation(btnCustomer);
        addHoverAnimation(btnFish);
        addHoverAnimation(btnEmployee);
        addHoverAnimation(btnOrders);
        addHoverAnimation(btnSupplier);
        addHoverAnimation(btnReport);
        addHoverAnimation(btnPayment);
        addHoverAnimation(btnLogOut);
        lblGmail.setText(NewLoginFormController.gmail1);
        lblName.setText("Hi.."+NewLoginFormController.userName1);
    }

    private void dashBoard() throws IOException {
        loadFormWithAtractiveAnimation("/view/dashboard_form.fxml");
    }
    private void addHoverAnimation(JFXButton button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), button);
        scaleIn.setFromX(1.0);
        scaleIn.setFromY(1.0);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), button);
        scaleOut.setFromX(1.1);
        scaleOut.setFromY(1.1);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        button.setOnMouseEntered(event -> {
            scaleIn.play();
        });

        button.setOnMouseExited(event -> {
            scaleOut.play();
        });
    }

    private void loadFormWithAtractiveAnimation(String formPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
        AnchorPane newPane = loader.load();

        newPane.setOpacity(0);
        mainPane.getChildren().add(newPane);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), newPane);
        translateTransition.setFromX(newPane.getWidth());
        translateTransition.setToX(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), newPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);


        ScaleTransition zoomIn = new ScaleTransition(Duration.seconds(0.5), newPane);
        zoomIn.setFromX(0.5);
        zoomIn.setFromY(0.5);
        zoomIn.setToX(1.0);
        zoomIn.setToY(1.0);

        // Combine all transitions
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(translateTransition,fadeTransition,zoomIn);
        parallelTransition.setOnFinished(event -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(newPane);
        });
        parallelTransition.play();
    }

    @FXML
    void btnAccessoriesOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/accessories_form.fxml");
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/customer_form.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/dashboard_form.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/employee_form.fxml");
    }

    @FXML
    void btnFishOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/fish_form.fxml");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/NEW.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.show();
    }


    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/order_form.fxml");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/payment_form.fxml");
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/RepotyForm.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/supplier_form.fxml");
    }
    void btnSupplierOnAction() throws IOException {
        loadFormWithAtractiveAnimation("/view/payment_form.fxml");
    }



}
