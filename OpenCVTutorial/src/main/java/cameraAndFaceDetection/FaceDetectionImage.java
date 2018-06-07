package cameraAndFaceDetection;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetectionImage {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("images/facedetection.jpg");
        /**
         * классификатор, с помощью которого будут распознаваться лица(в него передаём файл с конфигурацией определения лиц)
         */
        CascadeClassifier classifier = new CascadeClassifier("classifier/lbpcascade_frontalface.xml");
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(src, faceDetections);
        System.out.println(faceDetections.toArray().length);

        for(Rect rect : faceDetections.toArray()){
            Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0,0,255), 3);
        }

        Imgcodecs.imwrite("images1/facedetect.jpg", src);
    }
}
