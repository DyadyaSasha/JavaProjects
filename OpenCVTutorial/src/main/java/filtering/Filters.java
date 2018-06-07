package filtering;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Filters {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("images/1.jpg");
        Mat dst = new Mat();
        /**
         * двусторонний фильтр(то же самое что и при использовании medianBlur())
         */
        Imgproc.bilateralFilter(src, dst, 15, 80, 80, Core.BORDER_DEFAULT);
        Imgcodecs.imwrite("images1/bilateralfilter.jpg", dst);
        /**
         * данный фильтр схож с усреднённым размытием
         */
        Imgproc.boxFilter(src, dst, 50, new Size(45,45), new Point(-1,-1), true, Core.BORDER_DEFAULT);
        Imgcodecs.imwrite("images1/boxfilter.jpg", dst);
        /**
         * SQRBox фильтр
         */
        Imgproc.sqrBoxFilter(src, dst, -1, new Size(1,1));
        Imgcodecs.imwrite("images1/sqrboxfilter.jpg", dst);
        /**
         * filter2D
         */
        Mat kernel = Mat.ones(2,2, CvType.CV_32F);
        for(int i = 0; i< kernel.rows(); i++){
            for(int j = 0; j < kernel.cols(); j++){
                double[] m = kernel.get(i,j);
                for (int k = 1; k < m.length; k++){
                    m[k] = m[k]/(2*2);
                }
                kernel.put(i, j, m);
            }
        }
        Imgproc.filter2D(src, dst, -1, kernel);
        Imgcodecs.imwrite("images1/filter2d.jpg", dst);
    }
}
