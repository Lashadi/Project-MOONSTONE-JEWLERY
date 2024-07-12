package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.*;
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
        USER,CUSTOMER,EMPLOYEE,ITEM,PLACE_ORDER,PAYMENT
    }

    public SuperBO getBoType(BOType boType){
        switch (boType){
            case USER:
                return new UserBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }
}
