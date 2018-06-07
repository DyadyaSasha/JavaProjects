package threeDShapes;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class CylinderExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Cylinder cylinder = new Cylinder();


        cylinder.setHeight(300);
        cylinder.setRadius(100);

        cylinder.setTranslateX(250);
        cylinder.setTranslateY(250);

        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(30);
        ryBox.setAngle(50);
        rzBox.setAngle(30);
        cylinder.getTransforms().addAll(rxBox, ryBox, rzBox);

        Group root = new Group(cylinder);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Cylinder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
