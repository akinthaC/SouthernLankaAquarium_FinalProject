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
import javafx.util.Duration;
import lk.ijse.model.Employee;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.repository.EmployeeRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeFormController {
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableColumn<?, ?> ColEmpNIC;

    @FXML
    private TableColumn<?, ?> ColEmpName;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colEmpAddress;

    @FXML
    private TableColumn<?, ?> colEmpContact;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private TextField txtEmpContact;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpNIC;

    @FXML
    private TextField txtEmpName;

    public void initialize() throws IOException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllCustomers();
        getCurrentOrderId();

    }

    private void getCurrentOrderId() {
        try {
            String currentId = EmployeeRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtEmpId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("0");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "E0" + ++idNum;
        }
        return "E01";
    }

    private void loadAllCustomers() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<Employee> employeeList = EmployeeRepo.getAll();
            for (Employee employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getId(),
                        employee.getName(),
                        employee.getContact(),
                        employee.getNIC(),
                        employee.getAddress()

                );

                obList.add(tm);
            }

            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        ColEmpNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

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
        String id = txtEmpId.getText();

        try {
            boolean isDeleted = EmployeeRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String contact = txtEmpContact.getText();
        String NIC = txtEmpNIC.getText();
        String address = txtEmpAddress.getText();

        Employee employee = new Employee(id,name,contact,NIC,address);

        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String contact = txtEmpContact.getText();
        String NIC = txtEmpNIC.getText();
        String address = txtEmpAddress.getText();

        Employee employee = new Employee(id, name, contact, NIC, address);
        try {
            boolean isUpdate = EmployeeRepo.update(employee);
            if(isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtEmpId.getText();

        Employee employee = EmployeeRepo.searchById(id);
        if (employee != null) {
            txtEmpId.setText(employee.getId());
            txtEmpName.setText(employee.getName());
            txtEmpContact.setText(employee.getContact());
            txtEmpNIC.setText(employee.getNIC());
            txtEmpAddress.setText(employee.getAddress());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmpId.setText("");
        txtEmpName.setText("");
        txtEmpContact.setText("");
        txtEmpNIC.setText("");
        txtEmpAddress.setText("");

    }


    private void clearFields() {
        txtEmpId.setText("");
        txtEmpName.setText("");
        txtEmpContact.setText("");
        txtEmpNIC.setText("");
        txtEmpAddress.setText("");
    }

}
