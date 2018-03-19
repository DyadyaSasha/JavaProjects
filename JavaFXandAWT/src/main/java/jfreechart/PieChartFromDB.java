package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class PieChartFromDB {

    public static void main(String[] args) throws SQLException, IOException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "user");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT  * FROM jfreechart");

        DefaultPieDataset dataset = new DefaultPieDataset();
        while (rs.next()){
            dataset.setValue(
                    rs.getString("mobile_brand"),
                    rs.getInt("unit_sale")
            );
        }

        connection.close();

        JFreeChart chart = ChartFactory.createPieChart(
                "Mobile Sales",
                dataset,
                true,
                true,
                false
        );

        ChartUtilities.saveChartAsJPEG(new File("PieChartFromDB.jpeg"), chart, 560, 370);
    }

}
