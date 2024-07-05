package lk.ijse.pos.dao.custom;

import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO  extends CrudDAO<User> {

    User searchByTelephone(String telephone) throws SQLException;

    ResultSet checkCredential(String userId, String password) throws SQLException, IOException, ClassNotFoundException;

}
