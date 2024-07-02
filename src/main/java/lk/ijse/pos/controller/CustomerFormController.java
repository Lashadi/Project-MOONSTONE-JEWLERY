package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.tm.CustomerTm;

import java.net.URL;
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

    @FXML
    private TextField txtSearchCustomer;

    String cusId = txtCustomerId.getText();
    String cusName = txtCustomerName.getText();
    String cusAddress = txtCustomerAddress.getText();
    String cusTel = txtCustomerTel.getText();
    String cusEmail = txtCustomerEmail.getText();
    String userId = new LoginFormController().uId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllCustomers();
        setCellValueFactory();
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

    }

    @FXML
    void btnCustomerDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerSaveOnAction(ActionEvent event) {
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

    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void tblCustomerOnClick(MouseEvent event) {

    }

    @FXML
    void txtCustomerIdSearchOnAction(ActionEvent event) {

    }

}
