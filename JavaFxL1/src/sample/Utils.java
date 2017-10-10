package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Utils {
    Image fast(Image img) {
        int width = (int) img.getWidth();
        int height = (int) img.getHeight();

        WritableImage wImage = new WritableImage(width, height);
        PixelReader pixelReader = img.getPixelReader();
        PixelWriter writer = wImage.getPixelWriter();

        Color color;
        double cR, cG, cB, c;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color = pixelReader.getColor(x, y);
                cR = color.getRed();
                cG = color.getGreen();
                cB = color.getBlue();
                c = (cR + cG + cB) / 3;
                writer.setColor(x, y, Color.color(c, c, c));
            }
        }

        return wImage;
    }

    Image humaneye(Image img) {
        int width = (int) img.getWidth();
        int height = (int) img.getHeight();

        WritableImage wImage = new WritableImage(width, height);
        PixelReader pixelReader = img.getPixelReader();
        PixelWriter writer = wImage.getPixelWriter();

        Color color;
        double cR, cG, cB, c;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color = pixelReader.getColor(x, y);
                cR = color.getRed();
                cG = color.getGreen();
                cB = color.getBlue();
                c = 0.3 * cR + 0.59 * cG + 0.11 * cB;
                writer.setColor(x, y, Color.color(c, c, c));
            }
        }

        return wImage;
    }


    Image desaturation(Image img) {
        int width = (int) img.getWidth();
        int height = (int) img.getHeight();

        WritableImage wImage = new WritableImage(width, height);
        PixelReader pixelReader = img.getPixelReader();
        PixelWriter writer = wImage.getPixelWriter();

        Color color;
        double cR, cG, cB, c;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color = pixelReader.getColor(x, y);
                cR = color.getRed();
                cG = color.getGreen();
                cB = color.getBlue();
                c = (Math.max(cR, Math.max(cG, cB)) + Math.min(cR, Math.min(cG, cB))) / 2;
                writer.setColor(x, y, Color.color(c, c, c));
            }
        }

        return wImage;
    }


    Image gradationMax(Image img) {
        int width = (int) img.getWidth();
        int height = (int) img.getHeight();

        WritableImage wImage = new WritableImage(width, height);
        PixelReader pixelReader = img.getPixelReader();
        PixelWriter writer = wImage.getPixelWriter();

        Color color;
        double cR, cG, cB, c;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color = pixelReader.getColor(x, y);
                cR = color.getRed();
                cG = color.getGreen();
                cB = color.getBlue();
                c = Math.max(cR, Math.max(cG, cB));
                writer.setColor(x, y, Color.color(c, c, c));
            }
        }

        return wImage;
    }


    Image gradationMin(Image img) {
        int width = (int) img.getWidth();
        int height = (int) img.getHeight();

        WritableImage wImage = new WritableImage(width, height);
        PixelReader pixelReader = img.getPixelReader();
        PixelWriter writer = wImage.getPixelWriter();

        Color color;
        double cR, cG, cB, c;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color = pixelReader.getColor(x, y);
                cR = color.getRed();
                cG = color.getGreen();
                cB = color.getBlue();
                c = Math.min(cR, Math.min(cG, cB));
                writer.setColor(x, y, Color.color(c, c, c));
            }
        }

        return wImage;
    }


    LineChart histogram(Image img) {

        int height = (int) img.getHeight();
        int width = (int) img.getWidth();

        double[] pixels;
        pixels = new double[256];

        PixelReader pixelReader = img.getPixelReader();
        Color color;
        int c;

        for (int i = 0; i < 256; i++) {
            pixels[i] = 0;
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color = pixelReader.getColor(x, y);
                c = (int) color.getRed();
                pixels[c] = pixels[c] + 1;
            }
        }

        for (int i = 0; i < 256; i++) {
            pixels[i] = pixels[i] / (height * width);
        }

        NumberAxis xAxis = new NumberAxis(0, 255, 15);
        xAxis.setLabel("Color");

        NumberAxis yAxis = new NumberAxis(0, 1, 0.1);
        yAxis.setLabel("Frequency");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("Histogram");

        for (int i = 0; i < 256; i++) {
            series.getData().add(new XYChart.Data(i, pixels[i]));
        }

        linechart.getData().add(series);

        return linechart;
    }


    Image ecvalisation(Image img) {

        int height = (int) img.getHeight();
        int width = (int) img.getWidth();

        WritableImage wImage = new WritableImage(width, height);
        PixelReader pixelReader = img.getPixelReader();
        PixelWriter writer = wImage.getPixelWriter();

        Color color;
        int c;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int s = 0;
                for (int i = 0; i <= y; i++) {
                    for (int j = 0; j <= x; j++) {
                        color = pixelReader.getColor(x, y);
                        c = (int) color.getRed();
                        s = s + c;
                    }
                }
                s = (int) s / (width * height);
                writer.setColor(x, y, Color.color(s, s, s));
            }
        }

        return wImage;
    }

}
