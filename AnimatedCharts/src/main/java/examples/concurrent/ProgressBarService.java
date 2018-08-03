package examples.concurrent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressBarService extends Application {
    StackPane root = new StackPane();
    VBox mainBox = new VBox();
    ProgressBar bar = new ProgressBar(0.0);
    CounterService cs = new CounterService();

    @Override
    public void init() throws Exception {
        super.init();

        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(10);

        Button btn = new Button();
        btn.setText("Count to Ten Million!");
        btn.setOnAction(event -> {
            System.out.println("Count Started");
            bar.progressProperty().bind(cs.progressProperty());
            if (cs.getState() == Worker.State.READY) {
                cs.start();
            }
        });

        Button restartBtn = new Button();
        restartBtn.setText("Restart");
        restartBtn.setOnAction(event -> {
            System.out.println("Count Started");
            bar.progressProperty().bind(cs.progressProperty());
            cs.restart();
        });

        mainBox.getChildren().add(btn);
        mainBox.getChildren().add(restartBtn);
        mainBox.getChildren().add(bar);
        root.getChildren().add(mainBox);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Service Example");

        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }
}

class CounterService extends Service {
    @Override
    protected Task createTask() {
        CounterTask ct = new CounterTask();
        return ct;
    }
}

class CounterTask extends Task {
    @Override
    public Void call() {
        final int max = 10000000;
        updateProgress(0, max);
        for (int i = 1; i <= max; i++) {
//            Platform замедляет выполнение и фризит приложение
            Platform.runLater(() -> System.out.println("ssss"));
//            System.out.println(Platform.isFxApplicationThread());
            updateProgress(i, max);
        }
        return null;
    }
}