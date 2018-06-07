package mainApp;

import gen.configure.LinkType;
import gen.configure.OperationType;
import gen.configure.TableType;
import gen.processor.Generator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import models.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Controller {

    private Main mainApp;
    private List<Button> buttons;
    private List<HBox> rgTextBoxes;
    private Set<String> functionMembers = new TreeSet<>((o1, o2) -> Integer.compare(0, o1.compareTo(o2)));
    private Set<RadioButton> radioButtons = new TreeSet<>(Comparator.comparing(Labeled::getText));
    private ToggleGroup rGroup = new ToggleGroup();
    private Map<RadioButton, List<TableView<Data>>> radioWithTables = new HashMap<>();
    private String pathToFile = null;


    @FXML
    RadioButton mod, modCounter;

    @FXML
    private CheckBox fileFlag;

    @FXML
    private TableView<Data> resultTable;

    @FXML
    private TextField tacts;

    @FXML
    private ToggleGroup connectionType, hOperation, hType;

    @FXML
    private Pane schema;

    @FXML
    private Label functionLabel, message, statusLabel, periodLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private HBox rg1Text, rg2Text, rg3Text, rg4Text, rg5Text, rg6Text, rg7Text, rg8Text;

    @FXML
    private VBox errorMessage, addrBox, hBox;

    @FXML
    private Button r2, r3, r4, r5, r6, r7, r8;

    @FXML
    private Spinner<Integer> power, capacity;


    public void initialize() {

        tacts.setText("10");

        rGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (rGroup.getSelectedToggle() != null) {
                RadioButton selectedRadio = null;
                for (Iterator<RadioButton> iterator = radioWithTables.keySet().iterator(); iterator.hasNext(); ) {
                    RadioButton currentRadio = iterator.next();
                    if (rGroup.getSelectedToggle() != null && currentRadio.getText().trim().equals(((RadioButton) rGroup.getSelectedToggle()).getText().trim())) {
                        selectedRadio = currentRadio;
                    }
                }
                TableView<Data> hTable = radioWithTables.get(selectedRadio).get(1);
                TableView<Data> addrTable = radioWithTables.get(selectedRadio).get(0);
                ObservableList<Node> children = addrBox.getChildren();
                Node removeNode = null;
                for (Iterator<Node> iterator = children.iterator(); iterator.hasNext(); ) {
                    Node child = iterator.next();
                    if (child instanceof TableView<?>) {
                        removeNode = child;
                    }
                }
                children.remove(removeNode);
                children.add(addrTable);

                children = hBox.getChildren();
                removeNode = null;
                for (Iterator<Node> iterator = children.iterator(); iterator.hasNext(); ) {
                    Node child = iterator.next();
                    if (child instanceof TableView<?>) {
                        removeNode = child;
                    }
                }
                children.remove(removeNode);
                children.add(hTable);
            }
        });

        functionMembers.addAll(Arrays.asList("1", "x^2"));

        errorMessage.setVisible(false);

        power.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8, 2, 1));
        capacity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8, 2, 1));

        buttons = new LinkedList<>(Arrays.asList(r2, r3, r4, r5, r6, r7, r8));
        IntStream.range(0, buttons.size()).filter(index -> index > 0).mapToObj(buttons::get).forEach(button -> button.setVisible(false));

        rgTextBoxes = new LinkedList<>(Arrays.asList(rg1Text, rg2Text, rg3Text, rg4Text, rg5Text, rg6Text, rg7Text, rg8Text));
        IntStream.range(0, rgTextBoxes.size()).filter(index -> index > 1).mapToObj(rgTextBoxes::get).forEach(rgTextBox -> rgTextBox.setVisible(false));


        power.valueProperty().addListener((observable, oldValue, newValue) -> {

            rGroup.getToggles().clear();

            ((TableView<Data>) hBox.getChildren().get(1)).getItems().clear();
            ((TableView<Data>) addrBox.getChildren().get(1)).getItems().clear();

            VBox rButtonBox = (VBox) anchorPane.lookup("#rButtonsBox");
            ObservableList<Node> rList = rButtonBox.getChildren();
            if (rList != null) {
                rList.clear();
                radioButtons.clear();
            }

            radioWithTables.clear();

            functionMembers.clear();
            functionMembers.addAll(Arrays.asList("x^".concat(newValue.toString()), "1"));

            String functionText = String.join("+", functionMembers);
            functionLabel.setText("Ф(X)=" + functionText);

            try {
                if (oldValue < newValue) {
                    IntStream.range(oldValue - 1, newValue - 1).mapToObj(buttons::get).forEach(button -> button.setVisible(true));
                    IntStream.range(oldValue, newValue).mapToObj(rgTextBoxes::get).forEach(rgTextBox -> rgTextBox.setVisible(true));

                } else {
                    IntStream.range(newValue - 1, oldValue - 1).mapToObj(buttons::get).forEach(button -> button.setVisible(false));
                    IntStream.range(newValue, oldValue).mapToObj(rgTextBoxes::get).forEach(rgTextBox -> rgTextBox.setVisible(false));
                }
                buttons.forEach(button -> button.setStyle(""));
            } catch (IndexOutOfBoundsException e) {
            }

        });

        capacity.valueProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Data> defaultValues = FXCollections.observableArrayList();
            for (int i = 0; i <= (int) Math.pow(2, ((Spinner<Integer>) anchorPane.lookup("#capacity")).getValue()) - 1; i++) {
                defaultValues.add(new Data(new Integer(i).toString(), new Integer(i).toString()));
            }
            for (List<TableView<Data>> tableViews : radioWithTables.values()) {
                tableViews.get(1).setItems(defaultValues);
            }
            rgTextBoxes.forEach(hBox1 -> {
                ((TextField)hBox1.getChildren().get(1)).setText("");
                hBox1.getChildren().get(1).setStyle("");
            });

            List<String> modStrings = Arrays.asList(mod.getText().split(" "));
            mod.setText("");
            mod.setText(String.join(" ", modStrings.subList(0, modStrings.size()-1)) + " " + (int) Math.pow(2, capacity.getValue()));

            List<String> modCounterStrings = Arrays.asList(modCounter.getText().split(" "));
            modCounter.setText("");
            modCounter.setText(String.join(" ", modCounterStrings.subList(0, modCounterStrings.size()-1)) + " " + (int) Math.pow(2, capacity.getValue()));

        });

        mod.setText(mod.getText()  + (int) Math.pow(2, capacity.getValue()));
        modCounter.setText(modCounter.getText() + (int) Math.pow(2, capacity.getValue()));

    }

    @FXML
    private void rButtonClick(ActionEvent actionEvent) {

        Button clickedButton = (Button) actionEvent.getSource();

        RadioButton rButton = new RadioButton(clickedButton.getText());
        rButton.setStyle("-fx-text-fill: red");
//        rGroup.getToggles().add(rButton);


        if (clickedButton.getStyle().equals("-fx-background-color: lightgreen")) {
            clickedButton.setStyle("");
            functionMembers.remove("x^" + (Integer.parseInt(clickedButton.getId().substring(1)) - 1));
            radioButtons.remove(rButton);
            Toggle toggleToRemove = null;
            for (Toggle toggle : rGroup.getToggles()) {
                if (((RadioButton) toggle).getText().trim().equals(clickedButton.getText().trim())) {
                    toggleToRemove = toggle;
                    rGroup.getToggles().remove(toggle);
                    break;
                }
            }
            if (toggleToRemove != null) {
                //    rGroup.getToggles().remove(toggleToRemove);
                //rGroup.getToggles().forEach(toggle -> System.out.println(((RadioButton) toggle).getText()));
            }
            RadioButton radioButtonToRemove = null;
            for (Iterator<RadioButton> iterator = radioWithTables.keySet().iterator(); iterator.hasNext(); ) {
                RadioButton radio = iterator.next();
                if (radio.getText().trim().equals(rButton.getText().trim())) {
                    radioButtonToRemove = radio;
                }
            }
            radioWithTables.remove(radioButtonToRemove);

        } else {
            clickedButton.setStyle("-fx-background-color: lightgreen");
            functionMembers.add("x^" + (Integer.parseInt(clickedButton.getId().substring(1)) - 1));
            radioButtons.add(rButton);
            rGroup.getToggles().add(rButton);


            TableView<Data> addrTable = new TableView<>();
            addrTable.setEditable(false);
            TableColumn<Data, String> addrIndexColumn = new TableColumn<>("index");
            addrIndexColumn.setMaxWidth(58);
            addrIndexColumn.setMinWidth(58);
            TableColumn<Data, String> addrValueColumn = new TableColumn<>("value");
            addrValueColumn.setMaxWidth(75);
            addrValueColumn.setMinWidth(75);
            addrIndexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
            addrValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
            addrTable.getColumns().addAll(addrIndexColumn, addrValueColumn);


            TableView<Data> hTable = new TableView<>();
            hTable.setEditable(true);
            hTable.getSelectionModel().setCellSelectionEnabled(true);
            TableColumn<Data, String> hIndexColumn = new TableColumn<>("index");
            hIndexColumn.setMinWidth(60);
            hIndexColumn.setMaxWidth(60);
            TableColumn<Data, String> hValueColumn = new TableColumn<>("value");
            hValueColumn.setMinWidth(69);
            hValueColumn.setMaxWidth(69);
            hIndexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));

            hValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
            hValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            hIndexColumn.setOnEditCommit(event ->
                    ((Data) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                            .setIndex(event.getNewValue())
            );
            hValueColumn.setOnEditCommit(event ->
                    ((Data) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                            .setValue(event.getNewValue())
            );


            ObservableList<Data> defaultValues = FXCollections.observableArrayList();
            for (int i = 0; i <= (int) Math.pow(2, ((Spinner<Integer>) anchorPane.lookup("#capacity")).getValue()) - 1; i++) {
                defaultValues.add(new Data(new Integer(i).toString(), new Integer(i).toString()));
            }

            hTable.getColumns().addAll(hIndexColumn, hValueColumn);
            hTable.setItems(defaultValues);

            radioWithTables.put(rButton, new LinkedList<>(Arrays.asList(addrTable, hTable)));

//            ObservableList<Node> children = addrBox.getChildren();
//            Node removeNode = null;
//            for(Iterator<Node> iterator = children.iterator(); iterator.hasNext();){
//                Node child = iterator.next();
//                if (child instanceof TableView<?>){
//                    removeNode = child;
//                }
//            }
//            children.remove(removeNode);
//            children.add(addrTable);
//
//            children = hBox.getChildren();
//            removeNode = null;
//            for(Iterator<Node> iterator = children.iterator(); iterator.hasNext();){
//                Node child = iterator.next();
//                if (child instanceof TableView<?>){
//                    removeNode = child;
//                }
//            }
//            children.remove(removeNode);
//            children.add(hTable);

        }

        String functionText = String.join("+", functionMembers);
        functionLabel.setText("Ф(X)=" + functionText);

        VBox rButtonBox = (VBox) anchorPane.lookup("#rButtonsBox");
        ObservableList<Node> rList = rButtonBox.getChildren();
        if (rList != null) {
            rList.clear();
            Iterator<RadioButton> iterator = radioButtons.iterator();
            if (iterator.hasNext()) iterator.next().setSelected(true);
            radioButtons.forEach(radioButton -> rList.add(radioButton));

        }

    }

    @FXML
    private void checkBoxClicked(ActionEvent actionEvent) {

        CheckBox fileFlag = (CheckBox) actionEvent.getSource();
        Button fileChooser = (Button) anchorPane.lookup("#fileChooser");

        if (fileFlag.isSelected()) {
            fileChooser.setDisable(false);
        } else {
            fileChooser.setDisable(true);
        }
    }

    @FXML
    private void chooseFile(ActionEvent actionEvent) {

        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Выберете файл с данными");
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        File textFile = directoryChooser.showDialog(mainApp.getPrimaryStage());



        if (textFile != null) {
            pathToFile = textFile.getAbsolutePath();
        }

    }

    @FXML
    private void resetRg(ActionEvent actionEvent) {
        rgTextBoxes.forEach(rgTextBox -> {
            for (Node child : rgTextBox.getChildren()) {
                if (child instanceof TextField) {
                    TextField rgField = (TextField) child;
                    rgField.clear();
                    rgField.setTooltip(null);
                    rgField.setStyle("");
                }
            }
        });
    }

    @FXML
    private void validateRg(KeyEvent keyEvent) {

        TextField rgField = (TextField) keyEvent.getSource();

        Integer rgValue = null;
        try {
            rgValue = !rgField.getText().equals("") ? Integer.parseInt(rgField.getText()) : null;
        } catch (NumberFormatException e) {
            message.setText("Регистры дожны иметь числовое значение");
            errorMessage.setVisible(true);
            return;
        }

        int upBound = (int) Math.pow(2, ((Spinner<Integer>) anchorPane.lookup("#capacity")).getValue()) - 1;
        Tooltip tooltip = new Tooltip("Значение должно быть в интервале [0.." + upBound + "]");

        if (rgValue != null) {
            if ((rgValue >= 0) && (rgValue <= upBound)) {
                rgField.setStyle("-fx-border-color: lightgreen");
                rgField.setTooltip(null);
            } else {
                rgField.setStyle("-fx-border-color: red");
                rgField.setTooltip(tooltip);
            }
        } else {
            rgField.setStyle("");
        }
    }

    @FXML
    private void resetTables(ActionEvent actionEvent) {

        ((TableView<Data>) addrBox.getChildren().get(1)).getItems().clear();

        ObservableList<Data> defaultValues = FXCollections.observableArrayList();
        for (int i = 0; i <= (int) Math.pow(2, ((Spinner<Integer>) anchorPane.lookup("#capacity")).getValue()) - 1; i++) {
            defaultValues.add(new Data(new Integer(i).toString(), new Integer(i).toString()));
        }

        for (List<TableView<Data>> tableViews : radioWithTables.values()) {
            tableViews.get(1).setItems(defaultValues);
        }

        ((RadioButton) rGroup.getSelectedToggle()).setStyle("-fx-text-fill: red");

    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    @FXML
    private void resetAll(ActionEvent actionEvent) {

        rgTextBoxes.forEach(hBox1 -> {
            ((TextField)hBox1.getChildren().get(1)).setText("");
            hBox1.getChildren().get(1).setStyle("");
        });
        resultTable.getItems().clear();
        radioButtons.clear();
        ((TableView)addrBox.getChildren().get(1)).getItems().clear();
        ((TableView)hBox.getChildren().get(1)).getItems().clear();
        errorMessage.setVisible(false);
        tacts.setText("10");

        power.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8, 2, 1));
        capacity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8, 2, 1));

        statusLabel.setText("");
        periodLabel.setText("");

        fileFlag.setSelected(false);
        anchorPane.lookup("#fileChooser").setDisable(true);

        hType.getToggles().get(0).setSelected(true);
        hOperation.getToggles().get(0).setSelected(true);
        connectionType.getToggles().get(0).setSelected(true);

    }

    @FXML
    private void getAddr(ActionEvent actionEvent) {
        String selectedRadioText = ((RadioButton) rGroup.getSelectedToggle()).getText().trim();
        RadioButton selectedRadio = null;
        for (Iterator<RadioButton> iterator = radioWithTables.keySet().iterator(); iterator.hasNext(); ) {
            RadioButton radio = iterator.next();
            if (selectedRadioText.equals(radio.getText().trim())) {
                selectedRadio = radio;
            }
        }
        Set<Integer> values = new HashSet<>();
        List<TableView<Data>> tables = radioWithTables.get(selectedRadio);
        for (Data data : tables.get(1).getItems()) {
            if (Integer.parseInt(data.getValue().trim()) < 0 || Integer.parseInt(data.getValue().trim()) > (int) Math.pow(2, ((Spinner<Integer>) anchorPane.lookup("#capacity")).getValue()) - 1) {
                message.setText("Числа в таблице должны быть в интервале: [0..2^" + capacity.getValue() + "]");
                errorMessage.setVisible(true);
                return;
            }
            values.add(Integer.parseInt(data.getValue().trim()));
        }

        if (values.size() != (int) Math.pow(2, ((Spinner<Integer>) anchorPane.lookup("#capacity")).getValue())) {
            message.setText("Числа в таблице должны быть уникальными");
            errorMessage.setVisible(true);
            return;
        }


        Data dataArray[] = new Data[tables.get(1).getItems().size()];
        for (Data data : tables.get(1).getItems()) {
            dataArray[Integer.parseInt(data.getValue().trim())] = new Data(data.getValue().trim(), data.getIndex().trim());
        }
        ObservableList<Data> addrValues = FXCollections.observableArrayList(dataArray);
        tables.get(0).setItems(addrValues);

        ((RadioButton) rGroup.getSelectedToggle()).setStyle("-fx-text-fill: lightgreen");

    }

    @FXML
    private void start(ActionEvent actionEvent) {

        statusLabel.setTextFill(Color.LIGHTGREEN);
        statusLabel.setText("Генирируется");

        for (Toggle radioButton : rGroup.getToggles()) {
            if (((RadioButton) radioButton).getStyle().equals("-fx-text-fill: red")) {
                message.setText("Нажмите \"Применить\", чтобы проверить H таблицу");
                errorMessage.setVisible(true);
                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("Ошибка");
                return;
            }
        }

        int[] rg = new int[power.getValue()];
        for (int i = 0; i < power.getValue(); i++) {
            String textField = ((TextField) rgTextBoxes.get(i).getChildren().get(1)).getText().trim();
            if (textField.equals("")) {
                rg[i] = 0;
            } else {
                rg[i] = Integer.parseInt(textField);
            }
        }

        boolean[] r = new boolean[power.getValue() - 1];
        for (int i = 0; i < power.getValue() - 1; i++) {
            Button currentRButton = buttons.get(i);
            if (currentRButton.isVisible()) {
                if (currentRButton.getStyle().trim().equals("")) {
                    r[i] = false;
                } else {
                    r[i] = true;
                }
            }
        }

        int[][] h = new int[power.getValue() - 1][(int) Math.pow(2, capacity.getValue())];
        ObservableList<Toggle> toggles = new SortedList<Toggle>(rGroup.getToggles(), Comparator.comparing(o -> ((RadioButton) o).getText()));
        for (int i = 0; i < r.length; i++) {
            if (r[i] == false) {
                h[i] = new int[]{};
            } else {
                for (RadioButton radioButton : radioWithTables.keySet()) {
                    if (radioButton.getText().trim().equals(buttons.get(i).getText().trim())) {
                        int[] array = new int[(int) Math.pow(2, capacity.getValue())];
                        for (Data data : radioWithTables.get(radioButton).get(1).getItems()) {
                            array[Integer.parseInt(data.getIndex())] = Integer.parseInt(data.getValue());
                        }
                        h[i] = array;
                        break;
                    }
                }
            }
        }
//        System.out.println(Arrays.deepToString(h));

        Generator generator = new Generator();

        TableType tableType = null;
        for(TableType tableType1 : TableType.values()){
            if (tableType1.getLabel().equals(((RadioButton) hType.getSelectedToggle()).getText().trim())){
                tableType = tableType1;
            }
        }

        List<String> modStrings = Arrays.asList(((RadioButton) hOperation.getSelectedToggle()).getText().split(" "));
        String matchString = String.join(" ", modStrings.subList(0, modStrings.size()-1));
        OperationType operationType = null;
        for(OperationType operationType1 : OperationType.values()){
            if (operationType1.getLabel().equals(matchString.trim())){
                operationType = operationType1;
            }
        }


        LinkType linkType = null;
        for (LinkType linkType1 : LinkType.values()){
            if(linkType1.getLabel().equals(((RadioButton) connectionType.getSelectedToggle()).getText().trim())){
                linkType = linkType1;
            }
        }

        System.out.println("rg: " + Arrays.toString(rg));
        System.out.println("r: " + Arrays.toString(r));
        System.out.println("h: " + Arrays.deepToString(h));
        System.out.println(linkType);
        System.out.println(tableType);
        System.out.println(operationType);
        System.out.println("разрядность: " + capacity.getValue());
        System.out.println("число тактов: " + Integer.parseInt(tacts.getText().trim()));

        if (pathToFile == null && fileFlag.isSelected()) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Ошибка");
            message.setText("Нажмите \"Выбрать файл\", чтобы выбрать файл");
            errorMessage.setVisible(true);
            return;
        }


        generator.init(capacity.getValue(), rg, r, h, linkType, tableType, operationType, pathToFile);


        int[] sequence;
        try {
            sequence = generator.generateSequence(Integer.parseInt(tacts.getText().trim()));
        } catch (IOException e) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Ошибка");
            message.setText("Неверно указан файл: " + pathToFile);
            errorMessage.setVisible(true);
            return;
        }

//        System.out.println(Arrays.toString(sequence));

        resultTable.setEditable(false);
        TableColumn<Data, String> resultIndexColumn = new TableColumn<>("index");
        resultIndexColumn.setMinWidth(100);
        resultIndexColumn.setMaxWidth(100);
        TableColumn<Data, String> resultValueColumn = new TableColumn<>("value");
        resultIndexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
        resultValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        resultTable.getColumns().clear();
        resultTable.getColumns().addAll(resultIndexColumn, resultValueColumn);
//
        Data dataArray[] = new Data[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            dataArray[i] = new Data(Integer.toString(i), Integer.toString(sequence[i]));
        }
//        System.out.println(Arrays.deepToString(dataArray));
        ObservableList<Data> resultData = FXCollections.observableArrayList(dataArray);
//        resultData.forEach(data -> System.out.println(data.getIndex() + " : " + data.getValue()));
        resultTable.setItems(resultData);


//        System.out.println(Arrays.toString(sequence));
        statusLabel.setTextFill(Color.LIGHTGREEN);
        statusLabel.setText("Генерация завершена");
    }

    @FXML
    private void period(ActionEvent actionEvent) {

        periodLabel.setTextFill(Color.LIGHTGREEN);
        periodLabel.setText("Период высчитывается");

        for (Toggle radioButton : rGroup.getToggles()) {
            if (((RadioButton) radioButton).getStyle().equals("-fx-text-fill: red")) {
                periodLabel.setTextFill(Color.RED);
                periodLabel.setText("Ошибка");
                message.setText("Нажмите \"Применить\", чтобы проверить H таблицу");
                errorMessage.setVisible(true);
                return;
            }
        }

        int[] rg = new int[power.getValue()];
        for (int i = 0; i < power.getValue(); i++) {
            String textField = ((TextField) rgTextBoxes.get(i).getChildren().get(1)).getText().trim();
            if (textField.equals("")) {
                rg[i] = 0;
            } else {
                rg[i] = Integer.parseInt(textField);
            }
        }

        boolean[] r = new boolean[power.getValue() - 1];
        for (int i = 0; i < power.getValue() - 1; i++) {
            Button currentRButton = buttons.get(i);
            if (currentRButton.isVisible()) {
                if (currentRButton.getStyle().trim().equals("")) {
                    r[i] = false;
                } else {
                    r[i] = true;
                }
            }
        }

        /**
         * возведение в степени двойки
         * 1<<capacity.getValue();
         */
        int[][] h = new int[power.getValue() - 1][(int) Math.pow(2, capacity.getValue())];
        ObservableList<Toggle> toggles = new SortedList<Toggle>(rGroup.getToggles(), Comparator.comparing(o -> ((RadioButton) o).getText()));
        for (int i = 0; i < r.length; i++) {
            if (r[i] == false) {
                h[i] = new int[]{};
            } else {
                for (RadioButton radioButton : radioWithTables.keySet()) {
                    if (radioButton.getText().trim().equals(buttons.get(i).getText().trim())) {
                        int[] array = new int[(int) Math.pow(2, capacity.getValue())];
                        for (Data data : radioWithTables.get(radioButton).get(1).getItems()) {
                            array[Integer.parseInt(data.getIndex())] = Integer.parseInt(data.getValue());
                        }
                        h[i] = array;
                        break;
                    }
                }
            }
        }
//        System.out.println(Arrays.deepToString(h));

        Generator generator = new Generator();

        TableType tableType = null;
        for(TableType tableType1 : TableType.values()){
            if (tableType1.getLabel().equals(((RadioButton) hType.getSelectedToggle()).getText().trim())){
                tableType = tableType1;
            }
        }

        List<String> modStrings = Arrays.asList(((RadioButton) hOperation.getSelectedToggle()).getText().split(" "));
        String matchString = String.join(" ", modStrings.subList(0, modStrings.size()-1));
        OperationType operationType = null;
        for(OperationType operationType1 : OperationType.values()){
            if (operationType1.getLabel().equals(matchString.trim())){
                operationType = operationType1;
            }
        }

        LinkType linkType = null;
        for (LinkType linkType1 : LinkType.values()){
            if(linkType1.getLabel().equals(((RadioButton) connectionType.getSelectedToggle()).getText().trim())){
                linkType = linkType1;
            }
        }

        System.out.println("rg: " + Arrays.toString(rg));
        System.out.println("r: " + Arrays.toString(r));
        System.out.println("h: " + Arrays.deepToString(h));
        System.out.println(linkType);
        System.out.println(tableType);
        System.out.println(operationType);
        System.out.println("разрядность: " + capacity.getValue());
        System.out.println("число тактов: " + Integer.parseInt(tacts.getText().trim()));

        generator.init(capacity.getValue(), rg, r, h, linkType, tableType, operationType, null);

        int period = generator.computePeriod();

        periodLabel.setTextFill(Color.LIGHTGREEN);
        periodLabel.setText(Integer.toString(period));
    }

    @FXML
    private void errorOk(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
    }

    @FXML
    private void resetResultTable(ActionEvent actionEvent) {
        resultTable.getItems().clear();
    }
}
