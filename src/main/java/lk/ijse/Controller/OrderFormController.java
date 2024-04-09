package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.model.Order;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.repository.OrderRepo;

import java.sql.SQLException;

public class OrderFormController {

        @FXML
        private JFXButton btnClear;

        @FXML
        private JFXButton btnDelete;

        @FXML
        private JFXButton btnSave;

        @FXML
        private JFXButton btnUpdate;

        @FXML
        private TableColumn<?, ?> colDate;

        @FXML
        private TableColumn<?, ?> colHandOverDate;

        @FXML
        private TableColumn<?, ?> colOrderId;

        @FXML
        private TableColumn<?, ?> colQty;

        @FXML
        private TableColumn<?, ?> colStatus;

        @FXML
        private Label lblDate;

        @FXML
        private Label lblTime;

        @FXML
        private TableView<?> tblCustomer;

        @FXML
        private TextField txtDate;

        @FXML
        private TextField txtHandOverDate;

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtQty;

        @FXML
        private TextField txtStatus;

        @FXML
        void btnClearOnAction(ActionEvent event) {
            txtId.setText("");
            txtDate.setText("");
            txtHandOverDate.setText("");
            txtQty.setText("");
            txtStatus.setText("");

        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {

        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            String id = txtId.getText();
            String date = txtDate.getText();
            String handOverDate = txtHandOverDate.getText();
            String Qty = txtQty.getText();
            String status = txtStatus.getText();

            Order order = new Order(id, date, handOverDate, Qty, status);

            try {
                boolean isSaved = OrderRepo.save(order);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " saved!!!.").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }

    private void clearFields() {
            txtId.setText("");
            txtDate.setText("");
            txtHandOverDate.setText("");
            txtQty.setText("");
            txtStatus.setText("");


    }

    @FXML
        void btnUpdateOnAction(ActionEvent event) {

        }

        @FXML
        void searchBy(ActionEvent event) {

        }



}
