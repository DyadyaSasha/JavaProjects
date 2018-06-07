package animation;

import javafx.animation.StrokeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StrokeTransitionExample extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(300, 135, 100, Color.BROWN);
        circle.setStrokeWidth(20);

        /**
         * анимация изменения контура элемента
         */
        StrokeTransition strokeTransition = new StrokeTransition(Duration.millis(1000), circle);
        strokeTransition.setFromValue(Color.BLACK);
        strokeTransition.setToValue(Color.BROWN);
        strokeTransition.setCycleCount(5);
        strokeTransition.setAutoReverse(false);
        strokeTransition.play();

        Group root = new Group(circle);
        Scene scene = new Scene(root, 600,600);
        primaryStage.setTitle("Stroke animation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
