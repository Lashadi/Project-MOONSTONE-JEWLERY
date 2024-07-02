package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public Customer searchByTelephone(String telephone) throws SQLException {
        return null;
    }

    @Override
    public String generateId() throws SQLException {
        return "";
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> getCustomerList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");
        while (resultSet.next()) {
            getCustomerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return getCustomerList;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        return false;
    }

    @Override
    public Customer searchById(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
