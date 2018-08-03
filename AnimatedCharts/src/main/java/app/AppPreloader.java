package app;

import javafx.application.Preloader;
import javafx.stage.Stage;

public class AppPreloader extends Preloader {

    private Stage preloaderStage;

    @Override
    public void init() throws Exception {
//       TODO: подготовка прелоадера
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

//  TODO: нужны описания методов и на каком этапе они работают
    @Override
    public void handleProgressNotification(ProgressNotification info) {
        super.handleProgressNotification(info);
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        super.handleStateChangeNotification(info);
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        super.handleApplicationNotification(info);
    }

    @Override
    public boolean handleErrorNotification(ErrorNotification info) {
        return super.handleErrorNotification(info);
    }
}
