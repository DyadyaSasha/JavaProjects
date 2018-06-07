package eventhandling;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConvinienceMethodsExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(300, 135, 25, Color.BROWN);
        circle.setStrokeWidth(20);

        Path path = new Path();
        MoveTo moveTo = new MoveTo(208, 71);
        LineTo line1 = new LineTo(421,161);
        LineTo line2 = new LineTo(226, 232);
        LineTo line3 = new LineTo(332, 52);
        LineTo line4 = new LineTo(369,250);
        LineTo line5 = new LineTo(208,71);
        path.getElements().add(moveTo);
        path.getElements().addAll(line1,line2,line3,line4,line5);

        PathTransition pathTransition = new PathTransition(Duration.millis(1000), path, circle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(5);

        Button playButton = new Button("Play");
        playButton.setLayoutX(300);
        playButton.setLayoutY(250);

        circle.setOnMouseClicked(event -> {
            System.out.println("Hello World!");
            circle.setFill(Color.DARKSLATEBLUE);
        });

        playButton.setOnMouseClicked(event -> {
            System.out.println("Play!");
            pathTransition.play();
        });

        Button stopButton = new Button("Stop");
        stopButton.setLayoutX(250);
        stopButton.setLayoutY(250);

        stopButton.setOnMouseClicked(event -> {
            System.out.println("Stop!");
            pathTransition.stop();
        });

        Group root = new Group(circle, playButton, stopButton);
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Convenience Methods Example");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
