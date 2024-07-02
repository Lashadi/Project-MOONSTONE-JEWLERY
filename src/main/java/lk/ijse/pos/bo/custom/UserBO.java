package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public User searchByTelephone(String telephone) throws SQLException;

    public String generateNextCustomerId() throws SQLException;

    public List<UserDTO> getAllCustomer() throws SQLException;

    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(UserDTO dto) throws SQLException;

    public User searchById(String id) throws SQLException;

    public boolean delete(String id) throws SQLException;
}
