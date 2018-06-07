package layout;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackPaneExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(300,135,100, Color.DARKSLATEBLUE);
        circle.setStroke(Color.BLACK);

        Sphere sphere = new Sphere(50);
        Text text = new Text(20, 50, "Hello how are you");

        text.setFont(Font.font(null, FontWeight.BOLD, 15));
        text.setFill(Color.CRIMSON);

        StackPane stackPane = new StackPane();
        stackPane.setMargin(circle, new Insets(50,50,50,50));
        ObservableList list = stackPane.getChildren();
        list.addAll(circle, sphere, text);

        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stack Pane");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
