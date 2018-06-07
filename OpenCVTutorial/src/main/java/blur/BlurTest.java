package blur;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class BlurTest {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("images/1.jpg");
        Mat dst = new Mat();
        Size size = new Size(45,45);
        Point point = new Point(20,30);

        /**
         * размытие картинки
         */
        Imgproc.blur(src, dst, size, point, Core.BORDER_DEFAULT);
        Imgcodecs.imwrite("images1/blur.jpg", dst);
        /**
         * гауссово размытие (удаляет-снижает высокочастотные компоненты)
         */
        Imgproc.GaussianBlur(src, dst, new Size(45, 45), 0);
        Imgcodecs.imwrite("images1/gausblur.jpg", dst);
        /**
         * медианное размытие
         */
        Imgproc.medianBlur(src,dst,15);
        Imgcodecs.imwrite("images1/medianblur.jpg", dst);
    }
}
