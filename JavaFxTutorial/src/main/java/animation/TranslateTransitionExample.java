package animation;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TranslateTransitionExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(150, 135, 100, Color.BROWN);
        circle.setStrokeWidth(20);

        /**
         * анимация перемещения
         */
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        /**
         * на скалько по данной оси перемещаем элемент
         */
        translateTransition.setByX(300);
        translateTransition.setCycleCount(5);
        translateTransition.setAutoReverse(false);
        translateTransition.setNode(circle);
        translateTransition.play();

        Group root = new Group(circle);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Transition animation");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
