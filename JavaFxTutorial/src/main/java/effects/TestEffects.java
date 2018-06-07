package effects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TestEffects extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("https://www.tutorialspoint.com/green/images/logo.png");
        /**
         * нужно, чтобы отобразить картинку
         */
        ImageView imageView = new ImageView(image);
        imageView.setX(100);
        imageView.setY(70);
        /**
         * параметры подгонки изображения
         */
        imageView.setFitHeight(200);
        imageView.setFitWidth(400);
        /**
         * делаем, чтобы изобажение подогналось под {@link ImageView}
         */
        imageView.setPreserveRatio(true);

        /**
         * эффект, позволяющий регулировать цвет
         */
        ColorAdjust colorAdjust = new ColorAdjust();

        /**
         * устанавливаем значение контрастности
         */
        colorAdjust.setContrast(0.4);
        /**
         * устанавливаем значение выцветания
         */
        colorAdjust.setHue(-0.05);
        /**
         * устанавливаем значение яркости
         */
        colorAdjust.setBrightness(0.9);
        /**
         * устанавливаем значение насыщенности
         */
        colorAdjust.setSaturation(0.8);

        /**
         * применяем эффекты на {@link ImageView}
         */
        imageView.setEffect(colorAdjust);

        Rectangle rectangle = new Rectangle();

        /**
         * эффект для представления прямоугольной области
         */
        ColorInput colorInput = new ColorInput();
        colorInput.setX(50);
        colorInput.setY(200);

        /**
         * высота и ширина прямоугольной области
         */
        colorInput.setHeight(50);
        colorInput.setWidth(400);

        /**
         * назначаем цвет
         */
        colorInput.setPaint(Color.CHOCOLATE);
        /**
         * назначаем настроенный эффект
         */
        rectangle.setEffect(colorInput);

        /**
         * эффект для представления прямоугольной области для картинки
         */
        ImageInput imageInput = new ImageInput();
        imageInput.setX(150);
        imageInput.setY(300);

        Rectangle rectangle1 = new Rectangle();

        /**
         * устанавливаем картинку для отображения
         */
        imageInput.setSource(image);

        /**
         * назначаем настроенный эффект
         */
        rectangle1.setEffect(imageInput);




        Group root = new Group(
                imageView, rectangle, rectangle1
        );
        Scene scene = new Scene(root, 600, 300);

        primaryStage.setTitle("Effects");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
