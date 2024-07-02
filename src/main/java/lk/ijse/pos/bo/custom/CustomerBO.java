package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    public Customer searchByTelephone(String telephone) throws SQLException;

    public String generateNextCustomerId() throws SQLException;

    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException;

    public Customer searchByICustomerId(String id) throws SQLException;

    public boolean deleteCustomer(String id) throws SQLException;
}
