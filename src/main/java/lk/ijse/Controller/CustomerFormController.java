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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import lk.ijse.model.Customer;
import lk.ijse.model.tm.CustomerTm;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.repository.OrderRepo;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerFormController {


    @FXML
    private ToggleGroup Customer;

    @FXML
    private RadioButton NormalCustomer;

    @FXML
    private RadioButton WholesaleCustomer;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;
    @FXML
    private TableColumn<?, ?> ColCusName;

    @FXML
    private TableColumn<?, ?> ColNIC;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private JFXComboBox<String> cmbType;

    public void initialize() throws IOException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllCustomers();
        getCurrentOrderId();
        getType();


    }

    private void getType() {

        ObservableList<String> obList = FXCollections.observableArrayList();


        List<String> idList = CustomerRepo.getStatus();

        for(String value : idList) {
            obList.add(value);
        }

        cmbType.setItems(obList);


    }

    private void getCurrentOrderId() {
        try {
            String currentId = CustomerRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {

            String[] split = currentId.split("[cC]+");

            int idNum = Integer.parseInt(split[1]);

            return "C" + String.format("%03d", ++idNum);

        }

        return "C001";
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.getAll();
            for (Customer customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getContact(),
                        customer.getNIC(),
                        customer.getAddress(),
                        customer.getType()
                );

                obList.add(tm);
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colCusId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColNIC.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

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
        String id = txtId.getText();


        try {
            boolean isDeleted = CustomerRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                setCellValueFactory();
                loadAllCustomers();
                clearFields();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id=txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String NIC = txtNIC.getText();
        String address = txtAddress.getText();
        String type = (String) cmbType.getValue();

        try {
            if(id.isEmpty() || name.isEmpty() || contact.isEmpty() || NIC.isEmpty() || address.isEmpty() || type.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }

        Customer customer = new Customer(id, name, contact, NIC, address, type);


        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Coustomer saved!!!.").show();
                setCellValueFactory();
                loadAllCustomers();
                clearFields();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id=txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String NIC = txtNIC.getText();
        String address = txtAddress.getText();
        String type = (String) cmbType.getValue();

        try {
            if(id.isEmpty() || name.isEmpty() || contact.isEmpty() || NIC.isEmpty() || address.isEmpty() || type.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }

        Customer customer = new Customer(id, name, contact, NIC, address, type);

        try {
            boolean isUpdated = CustomerRepo.update(customer);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
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
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtNIC.setText("");
        cmbType.getItems().clear();

    }


    @FXML
    void searchBy(ActionEvent event) throws SQLException {
        String id = txtId.getText();

        Customer customer = CustomerRepo.searchById(id);
        if (customer != null) {
            txtId.setText(customer.getId());
            txtName.setText(customer.getName());
            txtContact.setText(customer.getContact());
            txtNIC.setText(customer.getNIC());
            txtAddress.setText(customer.getAddress());
            cmbType.setValue(customer.getType());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }



    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtNIC.setText("");
        cmbType.getItems().clear();
    }


    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ID,txtId);
    }

    public void txtnameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NAME,txtName);
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.CONTACT,txtContact);
    }

    public void txtNicOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NIC,txtNIC);
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ADDRESS,txtAddress);
    }
}
