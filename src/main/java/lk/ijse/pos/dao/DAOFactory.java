package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }
    public enum DAOType{
        CUSTOMER,EMPLOYEE,ITEM,ORDER,ORDER_DETAILS,USER
    }
    public SuperDAO getDaoType(DAOType daoType){
        switch (daoType){
            case USER:
                return new UserDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOimpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}
