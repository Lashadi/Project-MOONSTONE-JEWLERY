package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public String generatePaymentId() throws SQLException, ClassNotFoundException;

    public List<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;

    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public PaymentDTO searchByPaymentId(String id) throws SQLException, ClassNotFoundException;

    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException;
}
