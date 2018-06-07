package main;

import javafx.embed.swt.SWTFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadImageViaOpenCV {
    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /**
         * {@link Imgcodecs} класс, позволяющий счтывать и сохранять изображения,
         * а также представлять изображения в виде числовой матрицы
         */
        Mat mat = Imgcodecs.imread("images/1.jpg");
//        System.out.println(mat.dump());
        System.out.println("Image Loaded");

        Imgcodecs.imwrite("images1/2.jpg", mat);
        System.out.println("Image Saved");

        /**
         * матрица байт картинки
         */
        MatOfByte matOfByte = new MatOfByte();
        /**
         * конвертируем матрицу mat в матрицу байт matOfByte, также указываем расширение
         */
        Imgcodecs.imencode(".jpg", mat, matOfByte);

        /**
         * переводим массив байт, представляющий строку в объект {@link InputStream}
         */
        byte byteArray[] = matOfByte.toArray();
        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufferedImage = ImageIO.read(in);

        /**
         * отображаем картинку с помощью Swing
         */
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
        frame.pack();
        frame.setVisible(true);

    }
}
