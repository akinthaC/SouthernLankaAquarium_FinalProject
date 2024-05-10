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
import lk.ijse.model.Accessories;
import lk.ijse.model.Supplier;
import lk.ijse.model.tm.AccessoriesTm;
import lk.ijse.repository.AccessoriesRepo;
import lk.ijse.repository.SupplierRepo;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccessoriesFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cmbSupplier;

    @FXML
    private TableColumn<?, ?> colAccessoriesId;

    @FXML
    private TableColumn<?, ?> colAccessoriesName;

    @FXML
    private TableColumn<?, ?> colNoramalPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colWholeSalePrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblSupName;

    @FXML
    private TableView<AccessoriesTm> tblAccessories;

    @FXML
    private TextField txtAccessorieId;

    @FXML
    private TextField txtAccessoriesName;

    @FXML
    private TextField txtNormalPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtWholeSalePrice;

    public void initialize() throws IOException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllCustomers();
        getCurrentOrderId();
        getSupplierIds();

    }

    private void getSupplierIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = SupplierRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbSupplier.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentId = AccessoriesRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtAccessorieId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {

            String[] split = currentId.split("[aA]+");

            int idNum = Integer.parseInt(split[1]);

            return "A" + String.format("%03d", ++idNum);

        }

        return "A001";
    }

    private void loadAllCustomers() {
        ObservableList<AccessoriesTm> obList = FXCollections.observableArrayList();

        try {
            List<Accessories> accessoriesList = AccessoriesRepo.getAll();
            for (Accessories accessories : accessoriesList) {
                AccessoriesTm tm = new AccessoriesTm(
                        accessories.getId(),
                        accessories.getSupid(),
                        accessories.getName(),
                        accessories.getQty(),
                        accessories.getNormalPrice(),
                        accessories.getWholesaleprice()
                );

                obList.add(tm);
            }

            tblAccessories.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colAccessoriesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supid"));
        colAccessoriesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colNoramalPrice.setCellValueFactory(new PropertyValueFactory<>("normalPrice"));
        colWholeSalePrice.setCellValueFactory(new PropertyValueFactory<>("wholesaleprice"));


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
        txtAccessorieId.setText("");
        cmbSupplier.getSelectionModel().clearSelection();
        txtAccessoriesName.setText("");
        txtQtyOnHand.setText("");
        txtNormalPrice.setText("");
        txtWholeSalePrice.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtAccessorieId.getText();

        try {
            boolean isDeleted = AccessoriesRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Accessorie deleted!").show();
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
        txtAccessorieId.setText("");
        cmbSupplier.getSelectionModel().clearSelection();
        txtAccessoriesName.setText("");
        txtQtyOnHand.setText("");
        txtNormalPrice.setText("");
        txtWholeSalePrice.setText("");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id=txtAccessorieId.getText();
        String name = txtAccessoriesName.getText();
        String supid = cmbSupplier.getValue();
        String qty = txtQtyOnHand.getText();
        String normalPrice = txtNormalPrice.getText();
        String wholeSalePrice = txtWholeSalePrice.getText();

        try {
            if(id.isEmpty() || name.isEmpty() || supid.isEmpty() || qty.isEmpty() || normalPrice.isEmpty() || wholeSalePrice.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }


      Accessories accessories = new Accessories(id,supid, name, qty,normalPrice,wholeSalePrice);


        try {
            boolean isSaved = AccessoriesRepo.save(accessories);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Accessories saved!!!.").show();
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
    void btnUpdateOnAction(ActionEvent event) {
        String id=txtAccessorieId.getText();
        String name = txtAccessoriesName.getText();
        String supid = cmbSupplier.getValue();
        String qty = txtQtyOnHand.getText();
        String normalPrice = txtNormalPrice.getText();
        String wholeSalePrice = txtWholeSalePrice.getText();


        Accessories accessories = new Accessories(id,supid, name, qty,normalPrice,wholeSalePrice);

        try {
            boolean isUpdated = AccessoriesRepo.update(accessories);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Accessories updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void cmbSupplierOnAction(ActionEvent event) {
        String id = cmbSupplier.getValue();
        try {
            Supplier supplier = SupplierRepo.searchById(id);
            if(supplier!=null) {
                lblSupName.setText(supplier.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtAccessorieId.getText();

        Accessories accessories = AccessoriesRepo.searchById(id);
        if (accessories != null) {
            txtAccessorieId.setText(accessories.getId());
            cmbSupplier.setValue(accessories.getSupid());
            txtAccessoriesName.setText(accessories.getName());
            txtQtyOnHand.setText(accessories.getQty());
            txtNormalPrice.setText(accessories.getNormalPrice());
            txtWholeSalePrice.setText(accessories.getWholesaleprice());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Accessories not found!").show();
        }

    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ID,txtAccessorieId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NAME,txtAccessoriesName);
    }

    public void txtIQtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.QTY,txtQtyOnHand);
    }

    public void txtNormalPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.AMOUNT,txtNormalPrice);
    }

    public void txtIdWholeSalePriceKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.AMOUNT,txtWholeSalePrice);
    }
}
