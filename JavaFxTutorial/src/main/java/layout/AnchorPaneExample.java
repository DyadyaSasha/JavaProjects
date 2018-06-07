package layout;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnchorPaneExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Cylinder cylinder = new Cylinder(100,180);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.BLANCHEDALMOND);

        cylinder.setMaterial(material);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), cylinder);
        rotateTransition.setAxis(Rotate.X_AXIS);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();

        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(cylinder, 50.0);
        AnchorPane.setLeftAnchor(cylinder, 50.0);
        AnchorPane.setRightAnchor(cylinder,50.0);
        AnchorPane.setBottomAnchor(cylinder, 50.0);
        anchorPane.getChildren().addAll(cylinder);

        Scene scene = new Scene(anchorPane);
        primaryStage.setTitle("Anchor Pane");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
