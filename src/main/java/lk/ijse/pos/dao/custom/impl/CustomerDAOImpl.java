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
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT cId FROM Customer ORDER BY cId DESC LIMIT 1");
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
        return SQLUtil.execute("INSERT INTO Customer VALUES (?,?,?,?,?,?)",
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getEmail(),
                entity.getUserId());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET cName = ?, cAddress = ?, cTel = ?, cEmail = ?, uId = ? WHERE cId = ?",
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getEmail(),
                entity.getUserId(),
                entity.getId());
    }

    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer WHERE cId = ?", id);
        Customer customer = null;
        if (resultSet.next()) {
            String cID = resultSet.getString(1);
            String cName = resultSet.getString(2);
            String cAddress = resultSet.getString(3);
            String cTel = resultSet.getString(4);
            String cEmail = resultSet.getString(5);
            String uID = resultSet.getString(6);
            customer = new Customer(cID, cName, cAddress, cTel, cEmail, uID);
        }
        return customer;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE cId = ?",id);
    }
}
