package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
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
    public ResultSet generateNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateId();
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
        return customerDAO.save(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTel(),
                dto.getEmail(),
                dto.getUserId()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTel(),
                dto.getEmail(),
                dto.getUserId()
        ));
    }

    @Override
    public CustomerDTO searchByICustomerId(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchById(id);
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getEmail(),
                customer.getUserId()
        );
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
}
