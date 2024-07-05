package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {
    UserBO userBO = (UserBO) BOFactory.getInstance().getBoType(BOFactory.BOType.USER);


    @FXML
    private TextField txtNewUserId;

    @FXML
    private TextField txtNewUserName;

    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtNewUserId.setText(generateNextUserId());
    }

    private String generateNextUserId() {
        try {
            ResultSet resultSet = userBO.generateNextUserId();
            String currentUserId = null;
            if(resultSet.next()){
                currentUserId = resultSet.getString(1);
                return nextUserId(currentUserId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nextUserId(null);
    }

    private String nextUserId(String currentUserId) {
        String next = null;
        if (currentUserId == null) {
            next = "U001";
        } else {
            String data = currentUserId.replace("U", "");
            int id = Integer.parseInt(data);
            id++;

            if (id >= 1 && id <= 9) {
                next = "U00" + id;
            } else if (id >= 10 && id <= 99) {
                next = "U0" + id;
            } else if (id >= 100 && id <= 999) {
                next = "U" + id;
            }
        }
        return next;
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event){
        String userId = txtNewUserId.getText();
        String userName = txtNewUserName.getText();
        String password = txtPassword.getText();

        try {
            boolean isSaved = userBO.saveUser(new UserDTO(userId, userName, password));
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION, "User registered successfully").show();
                txtNewUserId.clear();
                txtNewUserName.clear();
                txtPassword.clear();
                generateNextUserId();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();        }
    }

}
