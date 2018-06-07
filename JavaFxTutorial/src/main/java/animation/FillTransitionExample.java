package animation;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FillTransitionExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(300,135, 100, Color.BROWN);
        circle.setStrokeWidth(20);

        /**
         * анимация изменения наполнения(цвета) элемента
         */
        FillTransition fillTransition = new FillTransition( Duration.millis(1000), circle);
        /**
         * начальное значение цвета
         */
        fillTransition.setFromValue(Color.BLUEVIOLET);
        /**
         * конечное значение цвета
         */
        fillTransition.setToValue(Color.CORAL);
        fillTransition.setCycleCount(5);
        fillTransition.setAutoReverse(false);
        fillTransition.play();

        Group root = new Group(circle);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Fill animation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
