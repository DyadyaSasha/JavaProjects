package animation;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeTransitionExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(300, 135, 100, Color.BROWN);
        circle.setStrokeWidth(20);

        /**
         * Анимация выцветания
         */
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), circle);
        /**
         * начальное значение прозрачности
         */
        fadeTransition.setFromValue(1.0);
        /**
         * конечное значение прозрачности
         */
        fadeTransition.setToValue(0.3);
        fadeTransition.setCycleCount(5);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();

        Group root = new Group(circle);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Fade animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
