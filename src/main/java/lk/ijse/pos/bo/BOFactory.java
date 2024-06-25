package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.UserBOImpl;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    public BOFactory() {}

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOType{
        USER,CUSTOMER
    }

    public SuperBO getBoType(BOType boType){
        switch (boType){
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
