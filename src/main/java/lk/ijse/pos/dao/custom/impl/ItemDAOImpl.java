package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT iCode FROM Item ORDER BY iCode DESC LIMIT 1");
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        List<Item> allItem = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Item");
        while (resultSet.next()) {
            allItem.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getDate(6)
            ));
        }
        return allItem;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item VALUES (?,?,?,?,?,?)",
                entity.getCode(),
                entity.getItemName(),
                entity.getCategory(),
                entity.getQty(),
                entity.getPrice(),
                entity.getDate());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET iName=?,iCategory=?,iQty=?,iPrice=?,iDate=? WHERE iCode=?",
                entity.getItemName(),
                entity.getCategory(),
                entity.getQty(),
                entity.getPrice(),
                entity.getDate(),
                entity.getCode());
    }

    @Override
    public Item searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Item WHERE iCode = ?",id);
        Item item = null;
        if (resultSet.next()) {
            String iCode = resultSet.getString(1);
            String ItemName = resultSet.getString(2);
            String category = resultSet.getString(3);
            int qty = Integer.parseInt(resultSet.getString(4));
            double price = Double.parseDouble(resultSet.getString(5));
            Date date = Date.valueOf(resultSet.getString(6));

            item = new Item(iCode, ItemName, category, qty, price, date);

        }
        return item;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Item WHERE iCode = ?",id);
    }

    @Override
    public List<String> getItemCodes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT iCode FROM Item");
        List<String> itemCodes = new ArrayList<>();

        while (resultSet.next()) {
            itemCodes.add(resultSet.getString(1));
        }
        return itemCodes;
    }

    @Override
    public boolean updateItemQty(List<OrderDetails> orderDetailsList) throws SQLException, ClassNotFoundException {
        for(OrderDetails orderDetails : orderDetailsList){
            if(!updateItemQty(orderDetails)){
                return false;
            }
        }
        return true;
    }

    private boolean updateItemQty(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET iQty = iQty - ? WHERE iCode = ?",
                orderDetails.getQty(),
                orderDetails.getItemCode());
    }
}
