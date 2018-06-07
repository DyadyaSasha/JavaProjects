package imageconversion;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;

public class ColorToBinary extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        WritableImage writableImage = loadAndConvert();
        ImageView imageView = new ImageView(writableImage);

        imageView.setX(10);
        imageView.setY(10);

        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Loading an image");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private WritableImage loadAndConvert() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("images/1.jpg");
        Mat dst = new Mat();

        /**
         * threshold() - используется для конвертации картинки из серого цвета в бинарное изображение
         */
        Imgproc.threshold(src, dst, 200, 1000, Imgproc.THRESH_BINARY);

        byte[] data = new byte[dst.rows()*dst.cols()*(int)(dst.elemSize())];
        dst.get(0,0,data);

        BufferedImage bufImage = new BufferedImage(dst.cols(), dst.rows(), BufferedImage.TYPE_BYTE_GRAY);
        bufImage.getRaster().setDataElements(0,0,dst.cols(),dst.rows(),data);

        WritableImage writableImage = SwingFXUtils.toFXImage(bufImage, null);
        return writableImage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
