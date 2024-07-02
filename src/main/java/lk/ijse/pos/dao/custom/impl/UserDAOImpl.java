package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User searchByTelephone(String telephone) throws SQLException {
        return null;
    }

    @Override
    public String generateId() throws SQLException {
        return "";
    }

    @Override
    public List<User> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO User VALUES(?,?,?)",entity.getUserId(),entity.getUserName(),entity.getPassword());
    }

    @Override
    public boolean update(User entity) throws SQLException {
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
