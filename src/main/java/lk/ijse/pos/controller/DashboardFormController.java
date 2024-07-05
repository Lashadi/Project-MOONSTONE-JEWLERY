package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private PieChart pieChart;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPieChart();
        setBarChart();
    }

    private void setBarChart() throws SQLException {
        /*ObservableList<XYChart.Series<String,Integer>> barChartData = ItemRepo.getBarChartData();

        barChart.setData(barChartData);*/
    }

    private void setPieChart() {
   /*     try {
            ObservableList<PieChart.Data> obList = FXCollections.observableArrayList();
            ArrayList<PieChart.Data> data = DashBoardRepo.getPieChartData();
            for (PieChart.Data datum : data) {
                obList.add(datum);
            }
            pieChart.setData(obList);
            pieChart.setTitle("Most Selling Product");
            pieChart.setStartAngle(180);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }*/
    }

}

