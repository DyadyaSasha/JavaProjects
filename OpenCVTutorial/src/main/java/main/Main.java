package main;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Main {
    public static void main(String[] args) {
        /**
         * подгружаем нативную библиотеку(до этого она должна быть добавлена в Djava.library.path)
         */
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        /**
         * {@link Mat} представляет картинку в качестве матрицы
         */
        Mat matrix = new Mat(5,5, CvType.CV_8UC1, new Scalar(0));
        /**
         * берёт строку с указанным индексом и возвращает её
         */
        Mat row0 = matrix.row(0);
        /**
         * заполняем строку значением, указанным в методе
         */
        row0.setTo(new Scalar(1));
        Mat col3 = matrix.col(3);
        col3.setTo(new Scalar(3));

        System.out.println(matrix.dump());
    }
}
