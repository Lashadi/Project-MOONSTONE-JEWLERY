package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.PlaceOrderDTO;
import lk.ijse.pos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBo extends SuperBO {

    ResultSet generateOrderId() throws SQLException, ClassNotFoundException;

    public List<PlaceOrderDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean savePlaceOrder(PlaceOrderDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updatePlaceOrder(PlaceOrderDTO dto) throws SQLException, ClassNotFoundException;

    public PlaceOrderDTO searchByPlaceOrderId(String id) throws SQLException, ClassNotFoundException;
}
