package effects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BloomEffectExample extends Application {
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

        //Instantiating the Rectangle class
        Rectangle rectangle = new Rectangle();

        //Setting the position of the rectangle
        rectangle.setX(50.0f);
        rectangle.setY(80.0f);

        //Setting the width of the rectangle
        rectangle.setWidth(500.0f);

        //Setting the height of the rectangle
        rectangle.setHeight(120.0f);

        //Setting the color of the rectangle
        rectangle.setFill(Color.TEAL);

        //Instantiating the Bloom class
        Bloom bloom = new Bloom();

        //setting threshold for bloom
        bloom.setThreshold(0.1);

        //Applying bloom effect to text
        text.setEffect(bloom);

        //Creating a Group object
        Group root = new Group(rectangle, text);

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