package layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(new TextField("Bottom"));
        borderPane.setCenter(new TextField("Center"));
        borderPane.setLeft(new TextField("Left"));
        borderPane.setRight(new TextField("right"));

        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Border pane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
