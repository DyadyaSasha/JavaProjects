package animation;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ParallelTransitionExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle rectangle = new Rectangle(75,75,100,100);
        rectangle.setFill(Color.BLUEVIOLET);

        Path path = new Path();
        MoveTo moveTo = new MoveTo(100, 150);
        CubicCurveTo cubicCurveTo = new CubicCurveTo(400,40,175,250,500,150);
        path.getElements().add(moveTo);
        path.getElements().add(cubicCurveTo);

        PathTransition pathTransition = new PathTransition(Duration.millis(1000), path, rectangle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(5);
        pathTransition.setAutoReverse(false);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), rectangle);
        translateTransition.setByX(300);
        translateTransition.setCycleCount(5);
        translateTransition.setAutoReverse(false);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), rectangle);
        scaleTransition.setByY(1.5);
        scaleTransition.setByX(1.5);
        scaleTransition.setAutoReverse(false);

        /**
         * позволяет воспроизводить сразу несколько анимаций одновременно
         */
        ParallelTransition parallelTransition = new ParallelTransition(rectangle, pathTransition, translateTransition, scaleTransition);
        parallelTransition.play();

        Group root = new Group(rectangle);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Parallel animation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
