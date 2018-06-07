package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        setPrimaryStage(primaryStage);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("sample.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("Number Generator");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        Controller controller = loader.getController();
        controller.setMainApp(this);
        controller.setAnchorPane((AnchorPane) root);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
