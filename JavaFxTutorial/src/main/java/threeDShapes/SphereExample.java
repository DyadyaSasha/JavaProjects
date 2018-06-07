package threeDShapes;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class SphereExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Sphere sphere = new Sphere(230);

        sphere.setTranslateX(250);
        sphere.setTranslateY(250);

        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(180);
        ryBox.setAngle(150);
        rzBox.setAngle(230);
        sphere.getTransforms().addAll(rxBox, ryBox, rzBox);


        Group root = new Group(sphere);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Sphere");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
