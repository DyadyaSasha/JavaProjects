package eventhandling;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventFiltersExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(300,135, 25, Color.BROWN);
        circle.setStrokeWidth(20);

        Text text = new Text("Click on the circle to change its color");
        text.setFont(Font.font(null, FontWeight.BOLD, 15));
        text.setFill(Color.CRIMSON);
        text.setX(150);
        text.setY(50);

        /**
         * обозначаем обработчик события мыши
         */
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hello World!");
                circle.setFill(Color.DARKSLATEBLUE);
            }
        };

        /**
         * назначаем нажатие на элемент обработчик события через event-фильтр
         */
        circle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        Group root = new Group(circle, text);
        Scene scene = new Scene(root, 600,600);
        scene.setFill(Color.LAVENDER);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Event Filter");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
