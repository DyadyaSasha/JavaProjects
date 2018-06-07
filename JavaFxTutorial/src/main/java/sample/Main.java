package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * чтобы запустить JavaFx приложение нужно создать класс наследник класса {@link Application},
 * в котором нужно определить методы main() и start();
 * в методе main() нужно вызвать статический метод launch() класса {@link Application},
 * который запускает приложение и выполняет переопределённый метод start()
 *
 * ***
 * JavaFx приложение имеет три метода, связанных с жизненным циклом оконного приложения:
 * init() - (вызывается перед методом start()) пустой метод, который должен быть переопределён, в нём можно создавать объекты  - необязательный метод
 *  {@link Stage} и {@link Scene}
 * start() - вызывается после init(), стартует приложение - обязательный метод
 * stop() - (пустой метод, который должен быть переопределён) в методе описываются действия, которые должно выполнить приложения перед закрытием  - необязательный метод
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        /**
         * объект {@link Scene} будет принимать UI виджеты, чтобы отобразить их
         * обязательно у Scene должен быть родительский элемент root
         */
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
