package effects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GaussianBlurEffectExample extends Application {
    @Override
    public void start(Stage stage) {
        //Creating a Text object
        Text text = new Text();

        //Setting font to the text
        text.setFont(Font.font(null, FontWeight.BOLD, 40));

        //setting the position of the text
        text.setX(60);
        text.setY(150);

        //Setting the text to be added.
        text.setText("Welcome to Tutorialspoint");

        //Setting the color of the text
        text.setFill(Color.DARKSEAGREEN);

        //Instantiating the GaussianBlur class
        GaussianBlur gaussianBlur = new GaussianBlur();

        //Setting the radius to apply the Gaussian Blur effect
        gaussianBlur.setRadius(10.5);

        //Applying Gaussian Blur effect to the text
        text.setEffect(gaussianBlur);

        //Creating a Group object
        Group root = new Group(text);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Sample Application");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
