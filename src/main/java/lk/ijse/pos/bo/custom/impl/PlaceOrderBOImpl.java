package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBo;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.PlaceOrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBo {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.ORDER_DETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.ITEM);

    @Override
    public ResultSet generateOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateId();
    }

    @Override
    public List<PlaceOrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean savePlaceOrder(PlaceOrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePlaceOrder(PlaceOrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PlaceOrderDTO searchByPlaceOrderId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
