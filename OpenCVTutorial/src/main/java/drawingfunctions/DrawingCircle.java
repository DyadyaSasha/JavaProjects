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

public class DrawingCircle extends Application {

    Mat matrix = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        DrawingCircle circle = new DrawingCircle();
        WritableImage writableImage = circle.loadImage();

        ImageView imageView = new ImageView(writableImage);

        imageView.setX(10);
        imageView.setY(10);

        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Drawing Circle on the image");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private WritableImage loadImage() throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("images/1.jpg");
        /**
         * рисуем круг
         * указываем исходную матрицу картинки, координаты круга, радиус, указываем цвет в {@link Scalar}(Blue-Green-Red) и толщину линии
         */
        Imgproc.circle(src, new Point(115, 100), 30, new Scalar(0,255,0),2);
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
