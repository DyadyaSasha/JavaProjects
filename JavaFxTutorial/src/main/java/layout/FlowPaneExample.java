package layout;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneExample extends Application {
    @Override
    public void start(Stage stage) {
        //Creating button1
        Button button1 = new Button("Button1");

        //Creating button2
        Button button2 = new Button("Button2");

        //Creating button3
        Button button3 = new Button("Button3");

        //Creating button4
        Button button4 = new Button("Button4");

        //Creating a Flow Pane
        FlowPane flowPane = new FlowPane();

        //Setting the horizontal gap between the nodes
        flowPane.setHgap(25);

        //Setting the margin of the pane
        flowPane.setMargin(button1, new Insets(20, 0, 20, 20));

        //Retrieving the observable list of the flow Pane
        ObservableList list = flowPane.getChildren();

        //Adding all the nodes to the flow pane
        list.addAll(button1, button2, button3, button4);

        //Creating a scene object
        Scene scene = new Scene(flowPane);

        //Setting title to the Stage
        stage.setTitle("Flow Pane Example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
