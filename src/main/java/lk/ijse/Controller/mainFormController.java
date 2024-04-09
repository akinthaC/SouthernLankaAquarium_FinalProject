package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class mainFormController {

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
    private AnchorPane rootNode;




    private void loadDashboardForm() throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);
    }

    @FXML
    void btnAccessoriesOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);

    }

    @FXML
    void btnFishOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.show();

    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) {

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/supplier_form.fxml"));

        mainPane.getChildren().clear();
        mainPane.getChildren().add(dashboardPane);

    }

}
