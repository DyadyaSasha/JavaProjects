package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.*;

public class PieChartFromFile {
    public static void main(String[] args) {


        try {
            InputStream is = new FileInputStream("mobile.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String line;
            DefaultPieDataset dataset = new DefaultPieDataset();
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            String[] strings = out.toString().split(",");

            for (int i = 1; i <= strings.length; i += 2) {
                dataset.setValue(strings[i - 1], Double.parseDouble(strings[i]));
            }

            JFreeChart chart = ChartFactory.createPieChart(
                    "Mobile Sales",
                    dataset,
                    true,
                    true,
                    false
            );

            ChartUtilities.saveChartAsJPEG(new File("PieChartFromFile.jpeg"), chart, 560, 370);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
