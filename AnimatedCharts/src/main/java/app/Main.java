package app;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init() throws Exception {
//  TODO: загрузка данных перед началом работы приложения
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getClassLoader().getResource("templates/sample.fxml"));
        loader.setLocation(getClass().getClassLoader().getResource("templates/rootWindow.fxml"));
        primaryStage.setTitle("Animated Charts");
        Parent root = loader.load();
        Scene scene = new Scene(root, 1100, 500);
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(1300);
        primaryStage.setScene(scene);
        primaryStage.show();


    }



    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, AppPreloader.class, args);
    }
}
