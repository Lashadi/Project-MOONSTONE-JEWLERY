package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public ResultSet generateEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateId();
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employees = new ArrayList<>();
        List<Employee> all = employeeDAO.getAll();
        for (Employee employee : all) {
            employees.add(new EmployeeDTO(
                    employee.getId(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getTel(),
                    employee.getUserId()
            ));
        }
        return employees;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTel(),
                dto.getUserId()
        ));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTel(),
                dto.getUserId()
        ));
    }

    @Override
    public EmployeeDTO searchByEmployeeId(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchById(id);
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getAddress(),
                employee.getTel(),
                employee.getUserId()
        );
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }
}
