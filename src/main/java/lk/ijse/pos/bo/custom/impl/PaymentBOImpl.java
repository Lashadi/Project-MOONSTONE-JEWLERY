package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PaymentBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.PaymentDAO;
import lk.ijse.pos.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.pos.dto.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.PAYMENT);
    @Override
    public String generatePaymentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = paymentDAO.generateId();
        String currentPaymentId = null;
        if (resultSet.next()) {
            currentPaymentId = resultSet.getString(1);
            return nextPaymentId(currentPaymentId);
        }
        return nextPaymentId(null);
    }

    private String nextPaymentId(String currentPaymentId) {
        String next = null;
        if(currentPaymentId == null){
            next = "P001";
        }else{
            String data = currentPaymentId.replace("P", "");
            int id = Integer.parseInt(data);
            id++;

            if(id >= 1 && id <= 9){
                next = "P00" + id;
            } else if (id >= 10 && id <= 99) {
                next = "P0" + id;
            } else if (id >= 100 && id <= 999) {
                next = "P" + id;
            }
        }
        return next;
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PaymentDTO searchByPaymentId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
