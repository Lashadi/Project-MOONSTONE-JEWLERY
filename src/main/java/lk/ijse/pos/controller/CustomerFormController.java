package lk.ijse.pos.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.tm.CustomerTm;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBoType(BOFactory.BOType.CUSTOMER);

    @FXML
    private ComboBox<?> cmbUserId;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private Label customerAddressValidate;

    @FXML
    private Label customerEmailValidate;

    @FXML
    private Label customerIdValidate;

    @FXML
    private Label customerNameValidate;

    @FXML
    private Label customerTelValidate;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerTel;

    static UserDTO user;

    @FXML
    private TextField txtSearchCustomer;

    public static void setUser(UserDTO userDTO) {
        CustomerFormController.user = userDTO;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCustomerId.setText(generateNextCustomerId());
        loadAllCustomers();
        setCellValueFactory();
    }

    private String generateNextCustomerId() {
        try {
            ResultSet resultSet = customerBO.generateNextCustomerId();
            String currentCustomerId = null;
            if (resultSet.next()) {
                currentCustomerId = resultSet.getString(1);
                return nextCustomerId(currentCustomerId);
            }
            return nextCustomerId(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextCustomerId(String currentCustomerId) {
        String next = null;
        if(currentCustomerId == null){
            next = "C001";
        }else {
            String data = currentCustomerId.replace("C", "");
            int id = Integer.parseInt(data);
            id++;

            if(id >= 1 && id <= 10){
                next = "C00" + id;
            } else if (id >= 11 && id <= 100) {
                next = "C0" + id;
            } else if (id >= 101 && id <= 1000) {
                next = "C" + id;
            }
        }
        return next;
    }

    private void loadAllCustomers() {
        tblCustomer.getItems().clear();
        try {
            List<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            for (CustomerDTO customerDTO : allCustomer) {
                tblCustomer.getItems().add(new CustomerTm(
                        customerDTO.getId(),
                        customerDTO.getName(),
                        customerDTO.getAddress(),
                        customerDTO.getTel(),
                        customerDTO.getEmail()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void clearTextFields(){
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerTel.clear();
        txtCustomerEmail.clear();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerTel.clear();
        txtCustomerEmail.clear();

    }

    @FXML
    void btnCustomerDeleteOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();
        try {
            boolean isDeleted = customerBO.deleteCustomer(customerId);
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Customer is Deleted").show();
                clearTextFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomerSaveOnAction(ActionEvent event) {
        String cusId = txtCustomerId.getText();
        String cusName = txtCustomerName.getText();
        String cusAddress = txtCustomerAddress.getText();
        String cusTel = txtCustomerTel.getText();
        String cusEmail = txtCustomerEmail.getText();
        String userId = user.getUserId();

        try {
            boolean isSaved = customerBO.saveCustomer(new CustomerDTO(cusId, cusName, cusAddress, cusTel, cusEmail, userId));
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer has been saved successfully").show();
                clearTextFields();
                loadAllCustomers();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Customer not saved").show();
        }
    }

    @FXML
    void btnCustomerUpdateOnAction(ActionEvent event) {
        String cusId = txtCustomerId.getText();
        String cusName = txtCustomerName.getText();
        String cusAddress = txtCustomerAddress.getText();
        String cusTel = txtCustomerTel.getText();
        String cusEmail = txtCustomerEmail.getText();
        String userId = user.getUserId();

        try {
            boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(cusId, cusName, cusAddress, cusTel, cusEmail, userId));
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer has been saved successfully").show();
                clearTextFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        String cusId = txtCustomerId.getText();
        try {
            CustomerDTO customerDTO = customerBO.searchByICustomerId(cusId);
            if(customerDTO != null){
                txtCustomerId.setText(customerDTO.getId());
                txtCustomerName.setText(customerDTO.getName());
                txtCustomerAddress.setText(customerDTO.getAddress());
                txtCustomerTel.setText(customerDTO.getTel());
                txtCustomerEmail.setText(customerDTO.getEmail());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblCustomerOnClick(MouseEvent event) {
        TablePosition tp = tblCustomer.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<CustomerTm,?>> columns = tblCustomer.getColumns();


        txtCustomerId.setText(columns.get(0).getCellData(row).toString());
        txtCustomerName.setText(columns.get(1).getCellData(row).toString());
        txtCustomerAddress.setText(columns.get(2).getCellData(row).toString());
        txtCustomerTel.setText(columns.get(3).getCellData(row).toString());
        txtCustomerEmail.setText(columns.get(4).getCellData(row).toString());

        tblCustomer.setCursor(Cursor.HAND);
    }

    @FXML
    void txtCustomerIdSearchOnAction(ActionEvent event) {

    }

}
