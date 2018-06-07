package animation;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SequentialTransitionExample extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(150, 135, 100, Color.BROWN);
        circle.setStrokeWidth(20);

        /**
         * задаём передвижение по кривой
         */
        Path path = new Path();
        MoveTo moveTo = new MoveTo(100,150);
        CubicCurveTo cubicCurveTo = new CubicCurveTo(400,40,175,250,500,150);
        path.getElements().add(moveTo);
        path.getElements().add(cubicCurveTo);

        /**
         * анимация передвижения
         */
        PathTransition pathTransition = new PathTransition(Duration.millis(1000), path, circle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        pathTransition.setCycleCount(5);
        pathTransition.setAutoReverse(false);

        pathTransition.play();

        Group root = new Group(circle);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sequential animation");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
