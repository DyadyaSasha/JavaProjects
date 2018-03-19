package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
//import org.jfree.chart.ChartUtils;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PieChartDemo extends ApplicationFrame {

    public PieChartDemo(String title) {
//      т.к. в ApplicationFrame нет конструктора по умолчанию, то нужно вызывать его конструктор явно
        super(title);
        setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
//      создаём объект конкретного вида графика, который можно разместить на графическом окне
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static JFreeChart createChart(PieDataset dataset) {
//      создаём по датасету конкретный график
        JFreeChart chart = ChartFactory.createPieChart("Mobile Sales", dataset, true, true, false);
        return chart;
    }

    public static PieDataset createDataset() {
//      указываем датасет, по которому будем строить график
        DefaultPieDataset dataset = new DefaultPieDataset();
//      заполняем датасет значениями
        dataset.setValue("IPhone 5s", new Double(20));
        dataset.setValue("SamSung Grand", new Double(20));
        dataset.setValue("MotoG", new Double(40));
        dataset.setValue("Nokia Lumia", new Double(10));
        return dataset;
    }


    public static void main(String[] args) throws IOException {
        PieChartDemo demo = new PieChartDemo("Mobile Sales");
        demo.setSize(640, 480);
//      располагаем график по середине
        RefineryUtilities.centerFrameOnScreen(demo);
//      делаем график видимым
        demo.setVisible(true);
//      сохраняем график в виде картинки(для версии 1.5)
//        ChartUtils.saveChartAsJPEG(new File("PieChart.jpeg"), createChart(createDataset()), 640, 480);
        ChartUtilities.saveChartAsJPEG(new File("PieChart.jpeg"), createChart(createDataset()), 640, 480);
    }
}
