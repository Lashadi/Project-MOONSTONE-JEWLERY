package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.CUSTOMER);

    @Override
    public Customer searchByTelephone(String telephone) throws SQLException {
        return null;
    }

    @Override
    public String generateNextCustomerId() throws SQLException {
        return "";
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> allCustomer = customerDAO.getAll();
        for (Customer customer : allCustomer) {
            customerDTOS.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getTel(),
                    customer.getEmail(),
                    customer.getUserId()
            ));
        }
        return customerDTOS;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return false;
    }

    @Override
    public Customer searchByICustomerId(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return false;
    }
}
