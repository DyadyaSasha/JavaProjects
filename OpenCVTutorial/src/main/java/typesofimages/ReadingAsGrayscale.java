package typesofimages;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.image.BufferedImage;

public class ReadingAsGrayscale extends Application {

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

        primaryStage.setTitle("Reading image as grayscale");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private WritableImage loadAndConvert() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /**
         * считываем картинку и переводим её в матрицу в чёрно-белом виде
         */
        Mat mat = Imgcodecs.imread("images/1.jpg", Imgcodecs.IMREAD_GRAYSCALE);
//        Mat mat = Imgcodecs.imread("images/1.jpg", Imgcodecs.IMREAD_COLOR);

        byte byteArray[] = new byte[mat.rows()*mat.cols()*(int)(mat.elemSize())];
        mat.get(0,0,byteArray);
        BufferedImage bufferedImage = new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_BYTE_GRAY);
//        BufferedImage bufferedImage = new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.getRaster().setDataElements(0,0, mat.cols(), mat.rows(), byteArray);

        WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
        return writableImage;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
