package effects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReflectionEffectExample extends Application {
    @Override
    public void start(Stage stage) {
        //Creating a Text object
        Text text = new Text();

        //Setting font to the text
        text.setFont(Font.font(null, FontWeight.BOLD, 40));

        //setting the position of the text
        text.setX(60);
        text.setY(150);

        //Setting the text to be embedded.
        text.setText("Welcome to Tutorialspoint");

        //Setting the color of the text
        text.setFill(Color.DARKSEAGREEN);

        //Instanting the reflection class
        Reflection reflection = new Reflection();

        //setting the bottom opacity of the reflection
        reflection.setBottomOpacity(0.0);

        //setting the top opacity of the reflection
        reflection.setTopOpacity(0.5);

        //setting the top offset of the reflection
        reflection.setTopOffset(0.0);

        //Setting the fraction of the reflection
        reflection.setFraction(0.7);

        //Applying reflection effect to the text
        text.setEffect(reflection);

        //Creating a Group object
        Group root = new Group(text);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Reflection effect example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
