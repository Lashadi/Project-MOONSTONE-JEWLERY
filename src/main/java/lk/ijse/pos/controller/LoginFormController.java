package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UserBO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController  {

    UserBO userBO = (UserBO) BOFactory.getInstance().getBoType(BOFactory.BOType.USER);

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserId;

    String uId;
    String uName;
    String uPassword;

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

    private void gotoDashBoard() {

        try {
            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/global_form.fxml"));

            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("DashBoard Form");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/register_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Register Form");
        stage.show();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnSignInOnAction(event);

    }

    @FXML
    void txtUseNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    public void btnSignInClickOnAction(ActionEvent actionEvent) {

        String  userName = txtUserId.getText();
        String password = txtPassword.getText();

        try {
            ResultSet resultSet = userBO.checkCredential(userName, password);
            while(resultSet.next()){
                uId = resultSet.getString(1);
                uName = resultSet.getString(2);
                uPassword = resultSet.getString(3);


                if(password.equals(uPassword)){
                    ancLogin.getScene().getWindow().hide();
                    gotoDashBoard();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Incorrect Password!");
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"User id or password doesn't match.Try Again!").show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
