package effects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GlowEffectExample extends Application {
    @Override
    public void start(Stage stage) {
        //Creating an image
        Image image = new Image("http://www.tutorialspoint.com/green/images/logo.png");

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //setting the fit width of the image view
        imageView.setFitWidth(200);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Instantiating the Glow class
        Glow glow = new Glow();

        //setting level of the glow effect
        glow.setLevel(0.9);

        //Applying bloom effect to text
        imageView.setEffect(glow);

        //Creating a Group object
        Group root = new Group(imageView);

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
