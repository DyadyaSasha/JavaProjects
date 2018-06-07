package animation;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScaleTransitionExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(300, 135, 50, Color.BROWN);
        circle.setStrokeWidth(20);

        /**
         * анимация масштабирования
         */
        ScaleTransition scaleTransition = new ScaleTransition();

        scaleTransition.setDuration(Duration.millis(1000));

        /**
         * устанавливаем, насколько нужно увеличть/уменьшить элемент
         */
        scaleTransition.setByX(1.5);
        scaleTransition.setByY(1.5);

        scaleTransition.setCycleCount(5);
        scaleTransition.setAutoReverse(false);
        scaleTransition.setNode(circle);
        scaleTransition.play();

        Group root = new Group(circle);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Scale animation");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
