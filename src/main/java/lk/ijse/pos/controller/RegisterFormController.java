package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;

public class RegisterFormController {
    UserBO userBO = (UserBO) BOFactory.getInstance().getBoType(BOFactory.BOType.USER);


    @FXML
    private TextField txtNewUserId;

    @FXML
    private TextField txtNewUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnConfirmOnAction(ActionEvent event){
        String userId = txtNewUserId.getText();
        String userName = txtNewUserName.getText();
        String password = txtPassword.getText();

        boolean isSaved = false;
        try {
            isSaved = userBO.save(new UserDTO(userId, userName, password));

            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION, "User registered successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();        }
    }

}
