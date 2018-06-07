package eventhandling;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class EventHandlersExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Box box = new Box(150, 150, 100);
        box.setTranslateX(350);
        box.setTranslateY(150);
        box.setTranslateZ(50);

        Text text = new Text("Type any letter to rotate the box, and click on the box to stop the rotation");
        text.setFont(Font.font(null, FontWeight.BOLD, 15));
        text.setFill(Color.CRIMSON);
        text.setX(20);
        text.setY(50);
        PhongMaterial material = new PhongMaterial(Color.DARKSLATEBLUE);
        box.setMaterial(material);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), box);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(7);

        TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(100);

        /**
         * назначаем конкретным элементам обработчики конкретных событий
         */
        textField.addEventHandler(KeyEvent.KEY_TYPED, event -> rotateTransition.play());
        box.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> rotateTransition.stop());

        Group root = new Group(box, textField, text);
        Scene scene = new Scene(root, 600, 300);

        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);
        scene.setCamera(camera);

        primaryStage.setTitle("Event Handling");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
