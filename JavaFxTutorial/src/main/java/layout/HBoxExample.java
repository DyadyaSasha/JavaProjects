package layout;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        TextField textField = new TextField();
        Button playButton = new Button("play");

        Button stopButton = new Button("stop");

        /**
         * макет расположения элементов в горизонтали
         */
        HBox hBox = new HBox();
        /**
         * расстояние между элементами в Hbox элементе
         */
        hBox.setSpacing(10);

        /**
         * устанавливаем границы элементов в Hbox
         */
        hBox.setMargin(textField, new Insets(20,20,20,20));
        hBox.setMargin(playButton, new Insets(20, 20, 20, 20));
        hBox.setMargin(stopButton, new Insets(20, 20, 20, 20));

        ObservableList list = hBox.getChildren();
        list.addAll(textField,playButton,stopButton);

        Scene scene = new Scene(hBox);
        primaryStage.setTitle("HBox layout");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
