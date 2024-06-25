package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;

public interface UserDAO  extends CrudDAO<User> {
    User searchByTelephone(String telephone) throws SQLException;

}
