package lk.ijse.pos.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.pos.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GlobalFormController implements Initializable {

    @FXML
    private Pane paginPane;

    @FXML
    private AnchorPane mianpane;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(paginPane,"customer_form.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException{
        Navigation.switchPaging(paginPane,"employee_form.fxml");
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException{
        Navigation.switchPaging(paginPane,"dashboard_form.fxml");
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(paginPane,"item_form.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException{
        Navigation.switchNavigation("login_form.fxml", mianpane);
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException{
        Navigation.switchPaging(paginPane,"order_form.fxml");
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(paginPane,"supplier_form.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paginPane.setVisible(true);
        try {
            Navigation.switchPaging(paginPane,"dashboard_form.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

