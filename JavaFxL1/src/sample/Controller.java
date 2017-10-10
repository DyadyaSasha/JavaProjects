package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {


    public Controller() throws FileNotFoundException {
    }

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


    @FXML
    private Button loadBt, okBt;
    @FXML
    private ComboBox combo;

    private File imageFile;


    void makestage(Image image, String operation) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        imageView.setX(10);
        imageView.setY(70);
        imageView.setFitWidth(800);
        imageView.setPreserveRatio(true);

        Group rt = new Group(root, imageView);

        Stage stage = new Stage();
        stage.setTitle(operation);
        stage.setScene(new Scene(rt, 830, 600));
        stage.show();
    }


    @FXML
    public void loadImg() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберете изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("jpg-file", "*.jpg"),
                new FileChooser.ExtensionFilter("png-file", "*.png"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            FileInputStream inputstream = new FileInputStream(file.getAbsolutePath());
            Image img = new Image(inputstream);
            imageFile = file;
            Main.setImageFile(file);
            makestage(img, "Initial image");
        }
//        imageFile = file;
    }


    public void ok() throws FileNotFoundException {
        String method = (String) combo.getValue();
        System.out.println(imageFile == null);
        System.out.println(Main.getImageFile() == null);

        if (method == null) {
            return;
        }

        if (method.equals("Ч/Б быстрый")) {
//            if (imageFile != null) {
            if (Main.getImageFile() != null){
//                FileInputStream inputstream = new FileInputStream(imageFile.getAbsolutePath());
                FileInputStream inputstream = new FileInputStream(Main.getImageFile().getAbsolutePath());
                Image img = new Image(inputstream);
                img = fast(img);
                makestage(img, "fast");

            }
        }

        if (method.equals("Ч/Б человеческий глаз")) {

//            if (imageFile != null) {
            if (Main.getImageFile() != null){
//                FileInputStream inputstream = new FileInputStream(imageFile.getAbsolutePath());
                FileInputStream inputstream = new FileInputStream(Main.getImageFile().getAbsolutePath());
                Image img = new Image(inputstream);
                img = humaneye(img);
                makestage(img, "human eye");
            }
        }

        if (method.equals("Ч/Б десатурация")) {
//            if (imageFile != null) {
            if (Main.getImageFile() != null){
//                FileInputStream inputstream = new FileInputStream(imageFile.getAbsolutePath());
                FileInputStream inputstream = new FileInputStream(Main.getImageFile().getAbsolutePath());
                Image img = new Image(inputstream);
                img = desaturation(img);
                makestage(img, "desaturation");
            }
        }

        if (method.equals("Ч/Б градация минимум")) {
//            if (imageFile != null) {
            if (Main.getImageFile() != null){
//                FileInputStream inputstream = new FileInputStream(imageFile.getAbsolutePath());
                FileInputStream inputstream = new FileInputStream(Main.getImageFile().getAbsolutePath());
                Image img = new Image(inputstream);
                img = gradationMin(img);
                makestage(img, "Gradation min");
            }
        }

        if (method.equals("Ч/Б градация максимум")) {
//            if (imageFile != null) {
            if (Main.getImageFile() != null){
//                FileInputStream inputstream = new FileInputStream(imageFile.getAbsolutePath());
                FileInputStream inputstream = new FileInputStream(Main.getImageFile().getAbsolutePath());
                Image img = new Image(inputstream);
                img = gradationMax(img);
                makestage(img, "Gradation max");
            }
        }

        if (method.equals("Показать гистограмму")) {
//            if (imageFile != null) {
            if (Main.getImageFile() != null){
//                FileInputStream inputstream = new FileInputStream(imageFile.getAbsolutePath());
                FileInputStream inputstream = new FileInputStream(Main.getImageFile().getAbsolutePath());
                Image img = new Image(inputstream);
                LineChart lnchart = histogram(img);

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Group rt = new Group(root, lnchart);

                Stage stage = new Stage();
                stage.setTitle("histogramm");
                stage.setScene(new Scene(rt, 830, 600));
                stage.show();
            }
        }

        if (method.equals("Эквализировать")) {
//            if (imageFile != null) {
            if (Main.getImageFile() != null){
//                FileInputStream inputstream = new FileInputStream(imageFile.getAbsolutePath());
                FileInputStream inputstream = new FileInputStream(Main.getImageFile().getAbsolutePath());
                Image img = new Image(inputstream);
                img = ecvalisation(img);
                makestage(img, "Ecvalisation");
            }
        }

    }

//    public  void setCombo() {
//        combo.getItems().addAll("Ч/Б быстрый",
//                "Ч/Б человеческий глаз", "Ч/Б десатурация",
//                "Ч/Б градация минимум", "Ч/Б градация максимум",
//                "Показать гистограмму", "Эквализировать");
//    }
}
