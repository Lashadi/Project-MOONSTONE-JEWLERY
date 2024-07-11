package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
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
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.bo.custom.PlaceOrderBo;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.tm.AddToCartTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PlaceOrderFormController implements Initializable {

    PlaceOrderBo placeOrderBo = (PlaceOrderBo) BOFactory.getInstance().getBoType(BOFactory.BOType.PLACE_ORDER);
    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBoType(BOFactory.BOType.ITEM);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBoType(BOFactory.BOType.CUSTOMER);

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colDeleteItem;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private DatePicker dpOrderDate;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<AddToCartTm> tblOrderDetails;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtSearchItem;

    @FXML
    private TextField txtUnitPrice;

    private ObservableList<AddToCartTm> itemTmObservableList = FXCollections.observableArrayList();

    double netTotalAmount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtOrderId.setText(generateNextOrderId());
        getCustomerId();
        getItemCode();
        setCellValueFactory();
    }

    private String generateNextOrderId() {
        try {
            ResultSet resultSet = placeOrderBo.generateOrderId();
            String currentOrderId = null;
            if (resultSet.next()) {
                currentOrderId = resultSet.getString(1);
                return nextOrderId(currentOrderId);
            }
            return nextOrderId(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextOrderId(String currentOrderId) {
        String nextOrderId = null;
        if(currentOrderId == null){
            nextOrderId = "OR001";
        }else {
            String data = currentOrderId.replace("OR", "");
            int id = Integer.parseInt(data);
            id++;

            if(id >= 1 && id <= 9){
                nextOrderId = "OR00" + id;
            } else if (id >= 10 && id <= 99) {
                nextOrderId = "OR0" + id;
            } else if (id >= 100 && id <= 999) {
                nextOrderId = "OR" + id;

            }
        }
        return nextOrderId;
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colDeleteItem.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }

    private void getItemCode() {
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();

        try {
            List<String> itemCodes = itemBO.getItemCodes();
            for (String itemCode : itemCodes) {
                itemCodeList.add(itemCode);
            }
            cmbItemCode.setItems(itemCodeList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerId() {
        ObservableList<String> customerList = FXCollections.observableArrayList();

        try {
            List<String> customerIds = customerBO.getCustomerIds();
            for (String customerId : customerIds) {
                customerList.add(customerId);
            }
            cmbCustomerId.setItems(customerList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        String itemName = txtItemName.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double totalAmount = unitPrice*qty;
        JFXButton btnDelete = new JFXButton("Remove");
        btnDelete.setCursor(Cursor.HAND);

        btnDelete.setOnAction((e)->{
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", yes, no).showAndWait();
            if(type.orElse(no) == yes){
                int index = tblOrderDetails.getSelectionModel().getFocusedIndex();
                itemTmObservableList.remove(index);
                tblOrderDetails.refresh();
                calculateNetAmount();
            }
        });
        for(int i = 0; i<tblOrderDetails.getItems().size(); i++){
            if(itemCode.equals(colItemCode.getCellData(i))){
                qty += itemTmObservableList.get(i).getQty();
                totalAmount += unitPrice*qty;

                itemTmObservableList.get(i).setQty(qty);
                itemTmObservableList.get(i).setTotalAmount(totalAmount);

                tblOrderDetails.refresh();
                calculateNetAmount();
                txtQty.clear();
                cmbItemCode.requestFocus();
                return;
            }
        }
        AddToCartTm addToCartTm = new AddToCartTm(itemCode, itemName, unitPrice, qty, totalAmount, btnDelete);
        itemTmObservableList.add(addToCartTm);
        tblOrderDetails.setItems(itemTmObservableList);
        txtQty.clear();
        calculateNetAmount();
    }

    private void calculateNetAmount() {
        netTotalAmount = 0;
        for(int i=0;i<tblOrderDetails.getItems().size();i++){
            netTotalAmount +=(double)colTotalAmount.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotalAmount));
    }

    @FXML
    void btnOrderPlaceOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrintCertificateOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign =
                JRXmlLoader.load("src/main/resources/report/certificate.jrxml");
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("orderID",txtOrderId.getText());


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        data,
                        DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String customerId = cmbCustomerId.getValue();

        try {
            CustomerDTO customer = customerBO.searchByICustomerId(customerId);
            lblCustomerName.setText(customer.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();

        try {
            ItemDTO item = itemBO.searchByItemId(itemCode);
            txtItemName.setText(item.getItemName());
            txtUnitPrice.setText(String.valueOf(item.getPrice()));
            txtQtyOnHand.setText(String.valueOf(item.getQty()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lblNetTotalOnAction(MouseEvent event) {

    }

    @FXML
    void txtItemSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtOrderIdSerachOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

}
