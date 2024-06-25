package lk.ijse.pos.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{
    public String generateNextCustomerId() throws SQLException;

    private String generateNextCustomerId(String currentCustomerId) {
        String next = null;
        if(currentCustomerId == null){
            next = "C001";
        }else {
            String data = currentCustomerId.replace("C", "");
            int id = Integer.parseInt(data);
            id++;

            if(id >= 1 && id <= 10){
                next = "C00" + id;
            } else if (id >= 11 && id <= 100) {
                next = "C0" + id;
            } else if (id >= 101 && id <= 1000) {
                next = "C" + id;
            }
        }
        return next;
    }

    public List<T> getAllCustomer() throws SQLException;

    public boolean save(T entity) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException;

    T searchById(String id) throws SQLException;

    public boolean delete(String id) throws SQLException;

}
