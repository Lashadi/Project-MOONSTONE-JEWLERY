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
import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.tm.CustomerTm;
import lk.ijse.pos.tm.EmployeeTm;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.pos.controller.CustomerFormController.user;

public class EmployeeFormController implements Initializable {

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getInstance().getBoType(BOFactory.BOType.EMPLOYEE);
    @FXML
    private TableColumn<?, ?> colEmployeeAddress;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colEmployeeTel;

    @FXML
    private Label employeeAddressValidate;

    @FXML
    private Label employeeIdValidate;

    @FXML
    private Label employeeNameValidate;

    @FXML
    private Label employeeTelValidate;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeTel;

    @FXML
    private TextField txtEmplyeeAddress;

    @FXML
    private TextField txtEmplyeeName;

    @FXML
    private TextField txtSearchEmplyee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtEmployeeId.setText(generateNextEmployeeId());
        loadAllEmployee();
        setCellValueFactory();
    }

    private void clearTextFields() {
        txtEmployeeId.clear();
        txtEmployeeTel.clear();
        txtEmplyeeAddress.clear();
        txtEmplyeeName.clear();
        txtSearchEmplyee.clear();
    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        colEmployeeTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllEmployee() {
        tblEmployee.getItems().clear();
        try {
            List<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for (EmployeeDTO employeeDTO : allEmployee) {
                tblEmployee.getItems().add(new EmployeeTm(
                        employeeDTO.getId(),
                        employeeDTO.getName(),
                        employeeDTO.getAddress(),
                        employeeDTO.getTel()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextEmployeeId() {
        try {
            ResultSet resultSet = employeeBO.generateEmployeeId();
            String currentEmployeeId = null;
            if (resultSet.next()) {
                currentEmployeeId = resultSet.getString(1);
                return nextId(currentEmployeeId);
            }
            return nextId(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentEmployeeId) {
        String next = null;
        if(currentEmployeeId == null){
            next = "E001";
        }else{
            String data = currentEmployeeId.replace("E", "");
            int id = Integer.parseInt(data);
            id++;

            if (id >= 1 && id <= 10) {
                next = "E00" + id;
            }else if (id >= 11 && id <= 100) {
                next = "E0" + id;
            }else if (id >= 101 && id <= 1000) {
                next = "E" + id;
            }
        }
        return next;
    }

    @FXML
    void btnEmployeeClearOnAction(ActionEvent event) {
        txtEmployeeId.clear();
        txtEmployeeTel.clear();
        txtEmplyeeAddress.clear();
        txtEmplyeeName.clear();
        txtSearchEmplyee.clear();
    }

    @FXML
    void btnEmployeeDeleteOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted").show();
                loadAllEmployee();
                clearTextFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnEmployeeSaveOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtEmplyeeName.getText();
        String address = txtEmplyeeAddress.getText();
        String tel = txtEmployeeTel.getText();
        String uId = user.getUserId();

        try {
            boolean isSaved = employeeBO.saveEmployee(new EmployeeDTO(id, name, address, tel, uId));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved").show();
                loadAllEmployee();
                clearTextFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeUpdateOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtEmplyeeName.getText();
        String address = txtEmplyeeAddress.getText();
        String tel = txtEmployeeTel.getText();
        String uId = user.getUserId();

        try {
            boolean isUpdated = employeeBO.updateEmployee(new EmployeeDTO(id, name, address, tel, uId));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated").show();
                loadAllEmployee();
                clearTextFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchEmployeeOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();

        try {
            EmployeeDTO employeeDTO = employeeBO.searchByEmployeeId(id);
            if (employeeDTO != null) {
                txtEmployeeId.setText(employeeDTO.getId());
                txtEmplyeeName.setText(employeeDTO.getName());
                txtEmplyeeAddress.setText(employeeDTO.getAddress());
                txtEmployeeTel.setText(employeeDTO.getTel());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtEmployeeIdSearchOnAction(ActionEvent event) {

    }

    @FXML
    void tblEmployeeClickOnAction(MouseEvent event) {
        TablePosition tp = tblEmployee.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<EmployeeTm,?>> columns = tblEmployee.getColumns();

        txtEmployeeId.setText(columns.get(0).getCellData(row).toString());
        txtEmplyeeName.setText(columns.get(1).getCellData(row).toString());
        txtEmplyeeAddress.setText(columns.get(2).getCellData(row).toString());
        txtEmployeeTel.setText(columns.get(3).getCellData(row).toString());

        tblEmployee.setCursor(Cursor.HAND);
    }
}
