package layout;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button[] buttons = new Button[] {
                new Button("SunDay"),
                new Button("MonDay"),
                new Button("TuesDay"),
                new Button("WednesDay"),
                new Button("ThursDay"),
                new Button("FriDay"),
                new Button("SaturDay")
        };

        TilePane tilePane = new TilePane();
        tilePane.setOrientation(Orientation.HORIZONTAL);
        tilePane.setTileAlignment(Pos.CENTER_LEFT);
        tilePane.setPrefRows(4);

        tilePane.getChildren().addAll(buttons);

        Scene scene = new Scene(tilePane);
        primaryStage.setTitle("Tile Pane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
