package lk.ijse.pos.bo.custom;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {

    ResultSet generateItemId() throws SQLException, ClassNotFoundException;

    List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;

    ItemDTO searchByItemId(String id) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    List<String> getItemCodes() throws SQLException, ClassNotFoundException;

    ObservableList<XYChart.Series<String, Integer>> getBarChartData() throws SQLException, ClassNotFoundException;

    ArrayList<PieChart.Data> getPieChartData() throws SQLException, ClassNotFoundException;
}
