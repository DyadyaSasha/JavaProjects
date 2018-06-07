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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DrawingRectangle extends Application {

    Mat matrix = null;

    @Override
    public void start(Stage stage) throws Exception {

        DrawingRectangle obj = new DrawingRectangle();
        WritableImage writableImage = obj.LoadImage();

        ImageView imageView = new ImageView(writableImage);

        imageView.setFitHeight(600);
        imageView.setFitWidth(600);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Drawing Rectangle on the image");

        stage.setScene(scene);

        stage.show();
    }
    public WritableImage LoadImage() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME );

        Mat matrix = Imgcodecs.imread("images/1.jpg");

        /**
         * рисуем прямоугольник
         */
        Imgproc.rectangle (
                matrix,
                new Point(130, 50),
                new Point(300, 280),
                new Scalar(0, 0, 255),
                5
        );

        /**
         * рисуем эллипс
         */
        Imgproc.ellipse (
                matrix,
                new RotatedRect (
                        new Point(200, 150),
                        new Size(260, 180), 180
                ),
                new Scalar(0, 0, 255),
                10
        );


        List<MatOfPoint> list = new ArrayList();
        list.add(
                new MatOfPoint (
                        new Point(75, 100), new Point(350, 100),
                        new Point(75, 150), new Point(350, 150),
                        new Point(75, 200), new Point(350, 200),
                        new Point(75, 250), new Point(350, 250)
                )
        );
        /**
         * рисуем прерывистую линию
         */
        Imgproc.polylines (
                matrix,                    // Matrix obj of the image
                list,                      // java.util.List<MatOfPoint> pts
                false,                     // isClosed
                new Scalar(0, 255, 0),     // Scalar object for color
                2                          // Thickness of the line
        );

        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, matOfByte);

        byte[] byteArray = matOfByte.toArray();

        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufImage = ImageIO.read(in);

        this.matrix = matrix;

        WritableImage writableImage = SwingFXUtils.toFXImage(bufImage, null);
        return writableImage;
    }
    public static void main(String args[]) {
        launch(args);
    }
}
