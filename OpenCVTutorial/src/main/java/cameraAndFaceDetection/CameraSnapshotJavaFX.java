package cameraAndFaceDetection;

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
import org.opencv.videoio.VideoCapture;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class CameraSnapshotJavaFX extends Application {

    Mat matrix = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        CameraSnapshotJavaFX obj = new CameraSnapshotJavaFX();
        WritableImage writableImage = obj.captureSnapShot();
        obj.saveImage();
        ImageView imageView = new ImageView(writableImage);
        imageView.setFitHeight(400);
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);
        Group root = new Group(imageView);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Capturing an image");
        primaryStage.show();
    }

    private void saveImage() {
        Imgcodecs.imwrite("images1/camera.jpg",matrix);
    }

    private WritableImage captureSnapShot() {

        WritableImage writableImage = null;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /**
         * класс, предоставляющий доступ к камере
         */
        VideoCapture capture = new VideoCapture(0);
        Mat matrix = new Mat();
        /**
         * делаем снимок камерой
         */
        capture.read(matrix);

        if (capture.isOpened()) {
            if (capture.read(matrix)) {
                BufferedImage image = new BufferedImage(matrix.width(), matrix.height(), BufferedImage.TYPE_3BYTE_BGR);
                WritableRaster raster = image.getRaster();
                DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
                byte[] data = dataBuffer.getData();
                matrix.get(0, 0, data);
                this.matrix = matrix;

                writableImage = SwingFXUtils.toFXImage(image, null);
            }
        }
        /**
         * "закрываем" камеру
         */
        capture.release();
        return writableImage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
