package animation;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PauseTransitionExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(150, 135, 50, Color.BROWN);
        circle.setStrokeWidth(20);

        /**
         * пауза в аимации
         */
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(1000));

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), circle);
        translateTransition.setByX(300);
        translateTransition.setCycleCount(5);
        translateTransition.setAutoReverse(false);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), circle);
        scaleTransition.setByX(1.5);
        scaleTransition.setByY(1.5);
        scaleTransition.setCycleCount(5);
        scaleTransition.setAutoReverse(false);

        /**
         * воспроизводит анимции последовательно
         */
        SequentialTransition sequentialTransition = new SequentialTransition(circle, translateTransition, pauseTransition, scaleTransition);
        sequentialTransition.play();

        Group root = new Group(circle);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pause in animation");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
