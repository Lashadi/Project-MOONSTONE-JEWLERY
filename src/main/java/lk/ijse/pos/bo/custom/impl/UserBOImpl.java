package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.USER);
    @Override
    public User searchByTelephone(String telephone) throws SQLException {
        return null;
    }

    @Override
    public String generateNextCustomerId() throws SQLException {
        return "";
    }

    @Override
    public List<UserDTO> getAllCustomer() throws SQLException {
        return List.of();
    }

    @Override
    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUserId(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException {
        return false;
    }

    @Override
    public User searchById(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
