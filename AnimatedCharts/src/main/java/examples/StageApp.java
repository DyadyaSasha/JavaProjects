package examples;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StageApp extends Application {

    public Parent createContent() {

        //create a button for initializing our new stage
        Button button = new Button("Create a Stage");
        button.setStyle("-fx-font-size: 24;");
        button.setDefaultButton(true);
        button.setOnAction((ActionEvent t) -> {
            final Stage stage = new Stage();

            //create root node of scene, i.e. group
            Group rootGroup = new Group();

            //create scene with set width, height and color
            Scene scene = new Scene(rootGroup, 200, 200, Color.WHITESMOKE);

            //set scene to stage
            stage.setScene(scene);

            //set title to stage
            stage.setTitle("New stage");

            //center stage on screen
            stage.centerOnScreen();

            //show the stage
            stage.show();

            //add some node to scene
            Text text = new Text(20, 110, "JavaFX");
            text.setFill(Color.DODGERBLUE);
            text.setEffect(new Lighting());
            text.setFont(Font.font(Font.getDefault().getFamily(), 50));

            //add text to the main root group
            rootGroup.getChildren().add(text);
        });
        return button;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}