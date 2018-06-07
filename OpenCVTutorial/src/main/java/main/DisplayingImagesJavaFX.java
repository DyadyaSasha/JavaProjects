package main;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swt.SWTFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DisplayingImagesJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        WritableImage writableImage = loadImage();
        ImageView imageView = new ImageView(writableImage);
        /**
         * координаты рсположения картинки
         */
        imageView.setX(50);
        imageView.setY(25);
        /**
         * задаём размеры картинки для отображения
         */
        imageView.setFitWidth(500);
        imageView.setFitHeight(400);
        /**
         * позволяет сохранять соотношение высоты и ширины картинки при масштабировании
         */
        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Loading Image");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private WritableImage loadImage() throws IOException{

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat image = Imgcodecs.imread("images/1.jpg");
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", image, matOfByte);

        byte byteArray[] = matOfByte.toArray();
        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufferedImage = ImageIO.read(in);

        /**
         * {@link SwingFXUtils} содержит методы преобразующие данные для Swing/AWT в данные для JavaFX
         */
        WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
        return writableImage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
