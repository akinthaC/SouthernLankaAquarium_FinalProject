package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.model.Accessories;
import lk.ijse.model.Order;
import lk.ijse.model.Payment;
import lk.ijse.model.Supplier;
import lk.ijse.model.tm.AccessoriesTm;
import lk.ijse.model.tm.PaymentTm;
import lk.ijse.repository.AccessoriesRepo;
import lk.ijse.repository.OrderRepo;
import lk.ijse.repository.PaymentRepo;
import lk.ijse.repository.SupplierRepo;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PaymentFormController {

    public TableColumn colAmountTOPat;
    public TableColumn colStatus;
    public JFXComboBox cmbType;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cmpOrderId;

    @FXML
    private TableColumn<?, ?> colAdvance;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOrdId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDiscription;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAdvance;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtTotal;


    public void initialize() throws IOException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllPayment();
        getCurrentOrderId();
        getOrderIds();
        getPayType();

    }


    private void getPayType() {
        ObservableList<String> obList = FXCollections.observableArrayList();


        List<String> idList = PaymentRepo.getType();

        for(String value : idList) {
            obList.add(value);
        }

        cmbType.setItems(obList);

    }

    private void getOrderIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = OrderRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmpOrderId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentId = PaymentRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtPaymentId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {

            String[] split = currentId.split("[pP]+");

            int idNum = Integer.parseInt(split[1]);

            return "P" + String.format("%03d", ++idNum);

        }

        return "P001";
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
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
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
        txtDate.setText(String.valueOf(now));

    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtPaymentId.setText("");
        cmpOrderId.getSelectionModel().clearSelection();
        txtDate.setText("");
        txtTotal.setText("");
        txtAdvance.setText("");
        cmbType.getSelectionModel().clearSelection();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtPaymentId.getText();

        try {
            boolean isDeleted = PaymentRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment information deleted!").show();
                clearFields();
                setCellValueFactory();
               loadAllPayment();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id=txtPaymentId.getText();
        String ordid =  cmpOrderId.getValue();
        String date = txtDate.getText();
        double  total = Double.parseDouble(txtTotal.getText());
        double  advance = Double.parseDouble(txtAdvance.getText());
        String type = (String) cmbType.getValue();
        double AmountToPaid = total-advance;
        String Status;


        if (AmountToPaid == 0){
            Status ="Successes";
        }else {
            Status="Pending";
        }

        try {
            if(id.isEmpty() || ordid.isEmpty() || date.isEmpty() || type.isEmpty()  ) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }


        Payment payment = new Payment(id, ordid, date, total, advance, type,AmountToPaid,Status);


        try {
            boolean isSaved = PaymentRepo.save(payment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment Successfully !!!.").show();
                clearFields();
                setCellValueFactory();
                loadAllPayment();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void clearFields() {
        txtPaymentId.setText("");
        cmpOrderId.getSelectionModel().clearSelection();
        txtDate.setText("");
        txtTotal.setText("");
        txtAdvance.setText("");
        cmbType.getSelectionModel().clearSelection();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id=txtPaymentId.getText();
        String ordid =  cmpOrderId.getValue();
        String date = txtDate.getText();
        double  total = Double.parseDouble(txtTotal.getText());
        double  advance = Double.parseDouble(txtAdvance.getText());
        String type = (String) cmbType.getValue();
        double AmountToPaid = total-advance;

        String Status;
        if (AmountToPaid == 0){
            Status ="Successes";
        }else {
            Status="Pending";
        }


        Payment payment = new Payment(id, ordid, date, total, advance, type,AmountToPaid,Status);

        try {
            boolean isUpdated = PaymentRepo.update(payment);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllPayment();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

   @FXML
    void cmpOrderIdOnAction(ActionEvent event) {


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtPaymentId.getText();

        Payment payment = PaymentRepo.searchById(id);
        if (payment != null) {
            txtPaymentId.setText(payment.getId());
            cmpOrderId.setValue(payment.getOrdid());
            txtDate.setText(payment.getDate());
            txtTotal.setText(String.valueOf(payment.getTotal()));
            txtAdvance.setText(String.valueOf(payment.getAdvance()));
            cmbType.setValue(payment.getType());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Payment info not found!").show();
        }

    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ID,txtPaymentId);
    }

    public void txtDateOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.DATE,txtDate);
    }

    public void txtTotalAmountOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.AMOUNT,txtTotal);
    }

    public void txtAdvanceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.AMOUNT,txtAdvance);
    }

    public void cmbTypeOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addPayment.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.setTitle("AddNewQty Form");

        stage.show();
        loadAllPayment();
        setCellValueFactory();


    }
}
