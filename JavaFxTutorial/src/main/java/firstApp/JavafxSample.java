package firstApp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavafxSample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Line line = new Line();
        /**
         * указываем цвет линии
         */
        line.setStroke(Color.BLUE);
        /**
         * указываем ширину линии
         */
        line.setStrokeWidth(5);
        line.setStartX(100.0);
        line.setStartY(150.0);
        line.setEndX(500.0);
        line.setEndY(150.0);

        Text text = new Text();
        /**
         * шрифт и стиль текста
         */
        text.setFont(new Font(30));

        text.setX(50);
        text.setY(50);

        text.setText("Hello!");


        /**
         * передаём line, которая будет отображаться при отображении объекта root
         */
        Group root = new Group(line);

        ObservableList<Node> list = root.getChildren();
        list.add(text);

        Scene scene = new Scene(root, 600, 300);
        /**
         * устанавливаем фон у {@link Scene}
         */
        scene.setFill(Color.BROWN);



        /**
         * устанавливаем заголовок окна
         */
        primaryStage.setTitle("Sample App");
        /**
         * передаём объект {@link Scene} в объект {@link Stage}, чтобы отобразить его
         */
        primaryStage.setScene(scene);
        /**
         * отображаем содержание объекта {@link Stage}
         */
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
