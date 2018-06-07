package drawingfunctions;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DrawingLine extends Application{

    Mat matrix = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        DrawingLine line = new DrawingLine();
        WritableImage writableImage = line.loadImage();

        ImageView imageView = new ImageView(writableImage);

        imageView.setX(10);
        imageView.setY(10);

        imageView.setFitHeight(600);
        imageView.setFitWidth(600);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Drawing Line on the image");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private WritableImage loadImage() throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("images/1.jpg");
        /**
         * рисуем линию
         * указываем исходную матрицу картинки, координаты точек линии, указываем цвет в {@link Scalar}(Blue-Green-Red) и толщину линии
         */
        Imgproc.line(src, new Point(10, 100), new Point(100, 150), new Scalar(0,255,0),2);
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", src, matOfByte);

        byte[] byteArray = matOfByte.toArray();

        InputStream is = new ByteArrayInputStream(byteArray);
        BufferedImage bufImage = ImageIO.read(is);
        this.matrix = src;
        WritableImage writableImage = SwingFXUtils.toFXImage(bufImage, null);
        return writableImage;

    }

    public static void main(String[] args) {
        launch(args);
    }

}
