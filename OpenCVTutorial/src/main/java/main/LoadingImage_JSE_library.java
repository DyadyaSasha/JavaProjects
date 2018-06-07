package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadingImage_JSE_library {
    public static void main(String[] args) throws IOException {

        File input = new File("images/1.jpg");
        /**
         * считываем данные о картинке и сохраняем в буфер
         */
        BufferedImage image = ImageIO.read(input);
        File output = new File("images1/1.jpg");
        /**
         * сохраняем картинку в указанный файл в указанном формате
         */
        ImageIO.write(image, "jpg", output);

        System.out.println("Image Saved");

    }
}
