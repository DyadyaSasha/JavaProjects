package transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;


public class TransformationExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle rectangle = new Rectangle(150, 75, 200, 150);
        rectangle.setFill(Color.BLUE);
        rectangle.setStroke(Color.BLACK);

        Rectangle rectangle1 = new Rectangle(150, 75, 200, 150);
        rectangle1.setFill(Color.BURLYWOOD);
        rectangle1.setStroke(Color.BLACK);
        /**
         * класс, предоставляющий утилиты для поворота объекта
         */
        Rotate rotate = new Rotate();
        /**
         * угол поворота
         */
        rotate.setAngle(20);

        /**
         * устанавливаем точку, с которой будет отсчитываться угол поворота
         */
        rotate.setPivotX(150);
        rotate.setPivotY(225);

        /**
         * поворачиваем фигуру
         */
        rectangle1.getTransforms().addAll(rotate);

        Circle circle = new Circle(300, 400, 50);
        circle.setFill(Color.BLUE);
        circle.setStrokeWidth(20);

        Circle circle1 = new Circle(300, 400, 50);
        circle1.setFill(Color.BURLYWOOD);
        circle1.setStrokeWidth(20);

        /**
         * класс для масштабирования
         */
        Scale scale = new Scale();
        /**
         * устанавливаем во сколько раз увеличиваем размер фигуры по осям
         */
        scale.setX(1.5);
        scale.setY(1.5);

        /**
         * указываем координаты, с которых начнётся преобразование
         */
        scale.setPivotX(300);
        scale.setPivotY(400);

        circle.getTransforms().addAll(scale);

        Circle circle2 = new Circle(300, 600, 50, Color.BROWN);
        circle2.setStrokeWidth(20);

        Circle circle3 = new Circle(300, 600, 50, Color.CADETBLUE);
        circle3.setStrokeWidth(20);

        /**
         * Класс для перемещения объекта
         */
        Translate translate = new Translate();
        /**
         * указываем на сколько нужно переместить фигуру по осям
         */
        translate.setX(300);
        translate.setY(50);

        circle3.getTransforms().addAll(translate);

        /**
         * класс {@link javafx.scene.transform.Shear} используется для наклона фигуры: https://www.tutorialspoint.com/javafx/shearing_transformation.htm
         */
        Group root = new Group(
                rectangle, rectangle1, circle,
                circle1, circle2, circle3
        );

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Transformation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
