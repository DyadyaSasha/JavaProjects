package layout;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        TextField textField = new TextField();
        Button playButton = new Button("play");

        Button stopButton = new Button("stop");

        /**
         * макет расположения элементов по вертикали
         */
        VBox vBox = new VBox();
        /**
         * расстояние между элементами в Hbox элементе
         */
        vBox.setSpacing(10);

        /**
         * устанавливаем границы элементов в Hbox
         */
        vBox.setMargin(textField, new Insets(20,20,20,20));
        vBox.setMargin(playButton, new Insets(20, 20, 20, 20));
        vBox.setMargin(stopButton, new Insets(20, 20, 20, 20));

        ObservableList list = vBox.getChildren();
        list.addAll(textField,playButton,stopButton);

        Scene scene = new Scene(vBox);
        primaryStage.setTitle("VBox layout");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
