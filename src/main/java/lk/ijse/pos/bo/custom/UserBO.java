package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public User searchByTelephone(String telephone) throws SQLException;

    public ResultSet generateNextUserId() throws SQLException, ClassNotFoundException;

    public List<UserDTO> getAllUser() throws SQLException;

    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateUser(UserDTO dto) throws SQLException;

    public User searchByIdUserId(String id) throws SQLException;

    public boolean deleteUser(String id) throws SQLException;
}
