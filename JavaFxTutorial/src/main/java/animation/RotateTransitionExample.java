package animation;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RotateTransitionExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
                150.0, 150.0);

        polygon.setFill(Color.BLUE);
        /**
         * Класс анимации вращения
         */
        RotateTransition rotateTransition = new RotateTransition();
        /**
         * продолжительность полного цикла анимации
         */
        rotateTransition.setDuration(Duration.millis(1000));
        /**
         * указываем элемент, для которого будет применяться анимация
         */
        rotateTransition.setNode(polygon);
        /**
         * устанавливаем угол, на который нужно повернуть элемент
         */
        rotateTransition.setByAngle(360);
        /**
         * устанавливаем, сколько раз должен происходить полный цикл анимации
         */
        rotateTransition.setCycleCount(3);
        /**
         * устанавливаем, нужно ли повторить анимацию в обратнуу сторону
         */
        rotateTransition.setAutoReverse(false);
        /**
         * проигрываем анимацию
         */
        rotateTransition.play();

        Group root = new Group(polygon);
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rotate transition");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
