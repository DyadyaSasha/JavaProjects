package effects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShadowEffectExample extends Application {
    @Override
    public void start(Stage stage) {
        //Creating a Text object
        Text text = new Text();

        //Setting font to the text
        text.setFont(Font.font(null, FontWeight.BOLD, 40));

        //setting the position of the text
        text.setX(60);
        text.setY(50);

        //Setting the text to be embedded.
        text.setText("Welcome to Tutorialspoint");

        //Setting the color of the text
        text.setFill(Color.DARKSEAGREEN);

        //Drawing a Circle
        Circle circle = new Circle();

        //Setting the center of the circle
        circle.setCenterX(300.0f);
        circle.setCenterY(160.0f);

        //Setting the radius of the circle
        circle.setRadius(100.0f);

        //Instantiating the Shadow class
        Shadow shadow = new Shadow();

        //setting the type of blur for the shadow
        shadow.setBlurType(BlurType.GAUSSIAN);

        //Setting color of the shadow
        shadow.setColor(Color.ROSYBROWN);

        //Setting the height of the shadow
        shadow.setHeight(5);

        //Setting the width of the shadow
        shadow.setWidth(5);

        //Setting the radius of the shadow
        shadow.setRadius(5);

        //Applying shadow effect to the text
        text.setEffect(shadow);

        //Applying shadow effect to the circle
        circle.setEffect(shadow);

        //Creating a Group object
        Group root = new Group(circle, text);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Bloom effect example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
