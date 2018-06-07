package uicontrols;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Registration extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Text nameLabel = new Text("Name");
        /**
         * поле для ввода текста
         */
        TextField nameText = new TextField();

        Text dobLabel = new Text("Date of birth");

        /**
         * поле для ввода даты
         */
        DatePicker datePicker = new DatePicker();

        Text genderLabel = new Text("gender");

        /**
         * группа для радио кнопок
         */
        ToggleGroup groupGender = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("male");
        RadioButton femaleRadio = new RadioButton("female");
        maleRadio.setToggleGroup(groupGender);
        femaleRadio.setToggleGroup(groupGender);

        Text reservationLabel = new Text("Reservation");

        ToggleGroup groupReservation = new ToggleGroup();
        ToggleButton yes = new ToggleButton("Yes");
        ToggleButton no = new ToggleButton("No");
        yes.setToggleGroup(groupReservation);
        no.setToggleGroup(groupReservation);

        Text technologiesLabel = new Text("Technologies Known");

        /**
         * флажок
         */
        CheckBox javaCheckBox = new CheckBox("Java");
        javaCheckBox.setIndeterminate(false);

        CheckBox dotnetCheckBox = new CheckBox("DotNet");
        dotnetCheckBox.setIndeterminate(false);

        Text educationLabel = new Text("Educational qualification");
        ObservableList<String> names = FXCollections.observableArrayList(
                "Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd");
        /**
         * список с тектовыми записями
         */
        ListView<String> educationListView = new ListView<>(names);

        Text locationLabel = new Text("location");
        /**
         * всплывающий список с текстовыми записями
         */
        ChoiceBox<String> locationchoiceBox = new ChoiceBox<>();
        locationchoiceBox.getItems().addAll("Hyderabad", "Chennai", "Delhi", "Mumbai", "Vishakhapatnam");

        Button buttonRegister = new Button("Register");

        /**
         * располагаем элементы
         */
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500,500);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(nameLabel,0,0);
        gridPane.add(nameText,1,0);
        gridPane.add(dobLabel,0,1);
        gridPane.add(datePicker,1,1);
        gridPane.add(genderLabel, 0, 2);
        gridPane.add(maleRadio, 1, 2);
        gridPane.add(femaleRadio, 2, 2);
        gridPane.add(reservationLabel, 0, 3);
        gridPane.add(yes, 1, 3);
        gridPane.add(no, 2, 3);
        gridPane.add(technologiesLabel, 0, 4);
        gridPane.add(javaCheckBox, 1, 4);
        gridPane.add(dotnetCheckBox, 2, 4);
        gridPane.add(educationLabel, 0, 5);
        gridPane.add(educationListView, 1, 5);
        gridPane.add(locationLabel, 0, 6);
        gridPane.add(locationchoiceBox, 1, 6);
        gridPane.add(buttonRegister, 2, 8);

        /**
         * назначаем стили
         */
        buttonRegister.setStyle(
                "-fx-background-color: darkslateblue; -fx-textfill: white;");

        nameLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        dobLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        genderLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        reservationLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        technologiesLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        educationLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        locationLabel.setStyle("-fx-font: normal bold 15px 'serif' ");

        gridPane.setStyle("-fx-background-color: BEIGE;");

        Scene scene = new Scene(gridPane);

        primaryStage.setTitle("Registration Form");

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
