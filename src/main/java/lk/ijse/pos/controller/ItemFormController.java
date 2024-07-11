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
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.tm.EmployeeTm;
import lk.ijse.pos.tm.ItemTm;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBoType(BOFactory.BOType.ITEM);

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Label stockCategoryValidate;

    @FXML
    private Label stockNameValidate;

    @FXML
    private Label stockQtyValidate;

    @FXML
    private Label stockUnitPriceValidate;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtItemCategory;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearchItem;

    @FXML
    private TextField txtUnitPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllItem();
        setCellValueFactory();
        txtItemCode.setText(generateItemCode());

    }

    private void clearTextFields() {
        txtItemCode.clear();
        txtItemName.clear();
        txtItemCategory.clear();
        txtQty.clear();
        txtUnitPrice.clear();
        txtSearchItem.clear();
        dpDate.setValue(null);
    }

    private String generateItemCode() {
        try {
            ResultSet resultSet = itemBO.generateItemId();
            String currentItemCode = null;
            if(resultSet.next()){
                currentItemCode = resultSet.getString(1);
                return nextItemCode(currentItemCode);
            }
            return nextItemCode(currentItemCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextItemCode(String currentItemCode) {
        String next = null;
        if (currentItemCode == null) {
            next = "I001";
        }else {
            String data = currentItemCode.replace("I", "");
            int id = Integer.parseInt(data);
            id++;
            if (id >= 1 && id <= 10) {
                next = "I00" + id;
            } else if (id >= 11 && id <= 100) {
                next = "I0" + id;
            } else if (id >= 101 && id <= 1000) {
                next = "I" + id;
            }
        }

        return next;
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colItemQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void loadAllItem() {
        tblItem.getItems().clear();
        try {
            List<ItemDTO> allItem = itemBO.getAllItem();
            for (ItemDTO itemDTO : allItem) {
                tblItem.getItems().add(new ItemTm(
                        itemDTO.getCode(),
                        itemDTO.getItemName(),
                        itemDTO.getCategory(),
                        itemDTO.getQty(),
                        itemDTO.getPrice()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemClearOnAction(ActionEvent event) {
        txtItemCode.clear();
        txtItemName.clear();
        txtItemCategory.clear();
        txtQty.clear();
        txtUnitPrice.clear();
    }

    @FXML
    void btnItemDeleteOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();

        try {
            boolean isDeleted = itemBO.deleteItem(itemCode);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted").show();
                clearTextFields();
                loadAllItem();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemSaveOnAction(ActionEvent event) {
        String id = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String category = txtItemCategory.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtUnitPrice.getText());
        Date date = Date.valueOf(dpDate.getValue());

        try {
            boolean isSaved = itemBO.saveItem(new ItemDTO(id, itemName, category, qty, price, date));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Saved").show();
                clearTextFields();
                loadAllItem();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemUpdateOnAction(ActionEvent event) {
        String id = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String category = txtItemCategory.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtUnitPrice.getText());
        Date date = Date.valueOf(dpDate.getValue());

        try {
            boolean isUpdated = itemBO.updateItem(new ItemDTO(id, itemName, category, qty, price, date));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated").show();
                clearTextFields();
                loadAllItem();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

    @FXML
    void tblClickOnAction(MouseEvent event) {
        TablePosition tp = tblItem.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<ItemTm,?>> columns = tblItem.getColumns();

        txtItemCode.setText(columns.get(0).getCellData(row).toString());
        txtItemName.setText(columns.get(1).getCellData(row).toString());
        txtItemCategory.setText(columns.get(2).getCellData(row).toString());
        txtQty.setText(columns.get(3).getCellData(row).toString());
        txtUnitPrice.setText(columns.get(4).getCellData(row).toString());

        tblItem.setCursor(Cursor.HAND);
    }

    @FXML
    void txtItemCodeSearchOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        try {
            ItemDTO itemDTO = itemBO.searchByItemId(itemCode);
            if (itemDTO != null) {
                txtItemCode.setText(itemDTO.getCode());
                txtItemName.setText(itemDTO.getItemName());
                txtItemCategory.setText(itemDTO.getCategory());
                txtQty.setText(String.valueOf(itemDTO.getQty()));
                txtUnitPrice.setText(String.valueOf(itemDTO.getPrice()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
