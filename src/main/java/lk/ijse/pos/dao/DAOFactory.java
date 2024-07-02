package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }
    public enum DAOType{
        CUSTOMER,ITEM,ORDER,USER
    }
    public SuperDAO getDaoType(DAOType daoType){
        switch (daoType){
            case USER:
                return new UserDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
