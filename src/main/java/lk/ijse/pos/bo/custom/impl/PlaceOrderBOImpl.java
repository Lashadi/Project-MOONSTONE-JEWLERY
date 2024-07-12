package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBo;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.PaymentDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.PlaceOrderDTO;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBo {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.ORDER_DETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.ITEM);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.PAYMENT);
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
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOderSaved = orderDAO.save(new Order(
                    dto.getOrder().getOrderId(),
                    dto.getOrder().getDate(),
                    dto.getOrder().getCustomerId()
                    ));
            if (isOderSaved) {
                ArrayList<OrderDetails>orderDetailsArrayList = new ArrayList<>();
                for (OrderDetailsDTO od : dto.getOrderDetails()){
                    OrderDetails orderDetails = new OrderDetails(
                            od.getOrderId(),
                            od.getItemCode(),
                            od.getUnitPrice(),
                            od.getQty(),
                            od.getTotalAmount());
                    orderDetailsArrayList.add(orderDetails);
                }
                boolean isOrderDetailsSaved = orderDetailsDAO.save(orderDetailsArrayList);
                if (isOrderDetailsSaved) {
                    ArrayList<OrderDetails> orderDetailsDArrayList = new ArrayList<>();
                    for (OrderDetailsDTO od : dto.getOrderDetails()){
                        OrderDetails orderDetails = new OrderDetails(
                                od.getOrderId(),
                                od.getItemCode(),
                                od.getUnitPrice(),
                                od.getQty(),
                                od.getTotalAmount());
                        orderDetailsDArrayList.add(orderDetails);
                    }
                    boolean isItemUpdated = itemDAO.updateItemQty(orderDetailsDArrayList);
                    if (isItemUpdated) {
                        boolean isPaymentSaved = paymentDAO.save(new Payment(
                                dto.getPayment().getPaymentId(),
                                dto.getPayment().getCustomerId(),
                                dto.getPayment().getOrderId(),
                                dto.getPayment().getPaymentAmount(),
                                dto.getPayment().getDate()));
                        if (isPaymentSaved) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        }catch (SQLException e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
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
