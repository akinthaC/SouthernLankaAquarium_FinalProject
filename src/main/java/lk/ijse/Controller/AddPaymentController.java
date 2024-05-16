package lk.ijse.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.Payment;
import lk.ijse.model.tm.PaymentTm;
import lk.ijse.repository.OrderRepo;
import lk.ijse.repository.PaymentRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class AddPaymentController {
    public JFXComboBox cmbOrder;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSave;


    @FXML
    private TableColumn<?, ?> colAdvance;

    @FXML
    private TableColumn<?, ?> colAmountTOPat;

    @FXML
    private TableColumn<?, ?> colOrdId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblAdvance;

    @FXML
    private Label lblAmountTOBePaid;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblPayId;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;
    public void initialize() throws IOException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllPayment();
        getOrderIds();
    }
    @FXML
    void QtyOnKeyReleased(KeyEvent event) {

    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id=lblPayId.getText();
        String ordid = (String) cmbOrder.getValue();;
        double  total = Double.parseDouble(lblTotalAmount.getText());
        double  advance = Double.parseDouble(lblAdvance.getText());
        double Repayment = Double.parseDouble(txtAmount.getText());
        double advance1 =advance+Repayment;
        double AmountToPaid = total-advance1;

        try {
            if(id.isEmpty() || ordid.isEmpty()  ) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }


        String Status;

        if (AmountToPaid == 0){
            Status ="Successes";
        }else {
            Status="Pending";
        }

        try {
            boolean isSaved = PaymentRepo.update1(id,advance1,AmountToPaid,Status);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment Successfully !!!.").show();
                clearFields();
                setCellValueFactory();
                loadAllPayment();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void clearFields() {
        txtAmount.setText("");
        cmbOrder.getSelectionModel().clearSelection();
        lblAdvance.setText("");
        lblAmountTOBePaid.setText("");
        lblTotalAmount.setText("");
        lblPayId.setText("");
    }

    @FXML
    void cmbOrdOnAction(ActionEvent event) throws SQLException {
        String id = (String) cmbOrder.getValue();
        try {

            Payment payment = PaymentRepo.searchByOrId(id);
            if (payment != null) {
                lblPayId.setText(payment.getId());
                lblTotalAmount.setText(String.valueOf(payment.getTotal()));
                lblAdvance.setText(String.valueOf(payment.getAdvance()));
                lblAmountTOBePaid.setText(String.valueOf(payment.getAmountToPaid()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        private void getOrderIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = PaymentRepo.getOrIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbOrder.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllPayment() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<Payment> paymentList = PaymentRepo.getAll();
            for (Payment payment : paymentList) {
                PaymentTm tm = new PaymentTm(
                        payment.getId(),
                        payment.getOrdid(),
                        payment.getDate(),
                        payment.getTotal(),
                        payment.getAdvance(),
                        payment.getType(),
                        payment.getAmountToPaid(),
                        payment.getStatus()
                );

                obList.add(tm);
            }

            tblPayment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrdId.setCellValueFactory(new PropertyValueFactory<>("ordid"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colAmountTOPat.setCellValueFactory(new PropertyValueFactory<>("amountToPaid"));


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
    void btnClearOnAction(ActionEvent event) {
        txtAmount.setText("");
        cmbOrder.getSelectionModel().clearSelection();
        lblAdvance.setText("");
        lblAmountTOBePaid.setText("");
        lblTotalAmount.setText("");
        lblPayId.setText("");

    }

}
