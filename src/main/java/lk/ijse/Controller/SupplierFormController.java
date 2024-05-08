package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import lk.ijse.model.Supplier;
import lk.ijse.model.SupplierTm;
import lk.ijse.repository.SupplierRepo;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SupplierFormController {

    @FXML
    private Label lblDate;
    
    @FXML   
    private Label lblTime;

    @FXML
    private TableColumn<?, ?> ColSupNIC;

    @FXML
    private TableColumn<?, ?> ColSupName;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colSUpContact;

    @FXML
    private TableColumn<?, ?> colSupAddress;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtSupAddress;

    @FXML
    private TextField txtSupContact;

    @FXML
    private TextField txtSupId;

    @FXML
    private TextField txtSupNIC;

    @FXML
    private TextField txtSupName;

    public void initialize() throws IOException {
       setDate();
       setTime();
       setCellValueFactory();
       loadAllCustomers();
       getCurrentOrderId();

    }

    private void getCurrentOrderId() {
        try {
            String currentId = SupplierRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtSupId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {

            String[] split = currentId.split("[sS]+");

            int idNum = Integer.parseInt(split[1]);

            return "S" + String.format("%03d", ++idNum);

        }

        return "S001";
    }

    private void loadAllCustomers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = SupplierRepo.getAll();
            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getContact(),
                        supplier.getNIC(),
                        supplier.getAddress()
                );

                obList.add(tm);
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColSupName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSUpContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        ColSupNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


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
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtSupId.getText();

        try {
            boolean isDeleted = SupplierRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id=txtSupId.getText();
        String name = txtSupName.getText();
        String contact = txtSupContact.getText();
        String NIC = txtSupNIC.getText();
        String address = txtSupAddress.getText();


        Supplier supplier = new Supplier(id, name, contact, NIC, address);


        try {
            boolean isSaved = SupplierRepo.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!!!.").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtSupId.setText("");
        txtSupName.setText("");
        txtSupContact.setText("");
        txtSupNIC.setText("");
        txtSupAddress.setText("");

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String id=txtSupId.getText();
        String name = txtSupName.getText();
        String contact = txtSupContact.getText();
        String NIC = txtSupNIC.getText();
        String address = txtSupAddress.getText();


        Supplier supplier = new Supplier(id, name, contact, NIC, address );

        try {
            boolean isUpdated = SupplierRepo.update(supplier);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtSupId.getText();

        Supplier supplier = SupplierRepo.searchById(id);
        if (supplier != null) {
            txtSupId.setText(supplier.getId());
            txtSupName.setText(supplier.getName());
            txtSupContact.setText(supplier.getContact());
            txtSupNIC.setText(supplier.getNIC());
            txtSupAddress.setText(supplier.getAddress());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtSupId.setText("");
        txtSupName.setText("");
        txtSupContact.setText("");
        txtSupNIC.setText("");
        txtSupAddress.setText("");

    }


    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ID,txtSupId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NAME,txtSupName);
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.CONTACT,txtSupContact);
    }

    public void txtNicOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NIC,txtSupNIC);
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ADDRESS,txtSupAddress);
    }
}
