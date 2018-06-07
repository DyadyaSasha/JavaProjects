package text;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

         /**
         * {@link Text} представляет текст
         */
        Text text = new Text();
        text.setText("Hello how are you?");
        text.setX(50);
        text.setY(50);
        /**
         * устанавливаем шрифт
         */
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        /**
         * цвет текста
         */
        text.setFill(Color.BROWN);
        /**
         * ширина оконтовки текста
         */
        text.setStrokeWidth(2);
        /**
         * цвет оконтовки текста
         */
        text.setStroke(Color.BLUE);
        /**
         * нижнее подчёркивание
         */
        text.setUnderline(true);
        /**
         * зачёркиваем текст
         */
        text.setStrikethrough(true);
        Group root = new Group(text);
        Scene scene = new Scene(root, 600, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Text Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
