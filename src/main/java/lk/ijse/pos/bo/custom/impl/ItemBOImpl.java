package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDaoType(DAOFactory.DAOType.ITEM);

    @Override
    public ResultSet generateItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateId();
    }

    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> list = new ArrayList<>();
        List<Item> all = itemDAO.getAll();
        for (Item item : all) {
            list.add(new ItemDTO(
                    item.getCode(),
                    item.getItemName(),
                    item.getCategory(),
                    item.getQty(),
                    item.getPrice(),
                    item.getDate()
            ));
        }
        return list;
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(
                dto.getCode(),
                dto.getItemName(),
                dto.getCategory(),
                dto.getQty(),
                dto.getPrice(),
                dto.getDate()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(
                dto.getCode(),
                dto.getItemName(),
                dto.getCategory(),
                dto.getQty(),
                dto.getPrice(),
                dto.getDate()
        ));
    }

    @Override
    public ItemDTO searchByItemId(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.searchById(id);
        return new ItemDTO(
                item.getCode(),
                item.getItemName(),
                item.getCategory(),
                item.getQty(),
                item.getPrice(),
                item.getDate()
        );
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
}
