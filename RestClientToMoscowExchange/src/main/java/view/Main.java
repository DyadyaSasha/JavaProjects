package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        /**
         * указываем .fxml файл, в котором описаны объекты GUI
         */
        loader.setLocation(getClass().getClassLoader().getResource("view.fxml"));
        Parent root = loader.load();
        primaryStage.getIcons().add(new Image("images.jpg"));
        primaryStage.setTitle("Москавская биржа");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
