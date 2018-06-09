package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    private Frame mainFrame;
    private Label headerLabel;
    private Label statusLabel;
    private Panel controlPanel;

    public Main(){
        prepareGUI();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.showControls();
    }

    private void showControls() {
        headerLabel.setText("Control in action: Label");
        Label label = new Label();
        label.setText("Welcome to TutorialsPoint AWT Tutorial.");
        // выравнивание
        label.setAlignment(Label.CENTER);
        // фон
        label.setBackground(Color.GRAY);
        // цвет шрифта
        label.setForeground(Color.RED);
        // добавляем label на панель, чтобы затем отобразить
        controlPanel.add(label);
        // делаем окошко видимым
        mainFrame.setVisible(true);
    }

    private void prepareGUI(){
        // создаём окно и даём ему имя
        mainFrame = new Frame("Java AWT Examples");
        // устанавливаем размеры окна
        mainFrame.setSize(new Dimension(800,800));
        // устанавливаем расположение эоементов в окне
        mainFrame.setLayout(new GridLayout(3,1));
        // добавляем событие, обрабатывающее закрытие окна
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        headerLabel = new Label();
        headerLabel.setAlignment(Label.CENTER);
        statusLabel = new Label("status label");
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setSize(350,100);

        // создаём панель, на которой будем располагать элементы
        controlPanel = new Panel();
        // устанавливаем правило расположения элементов на панели
        controlPanel.setLayout(new FlowLayout());

        Button okButton = new Button("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("OK Button Clicked");
            }
        });
        Button submitButton = new Button("Submit");
        submitButton.addActionListener(actionEvent -> statusLabel.setText("Submit Button clicked."));
        Button cancelButton = new Button("Cancel");
        cancelButton.addActionListener(actionEvent -> statusLabel.setText("Cancel Button clicked."));

        // флажок
        Checkbox checkbox1 = new Checkbox("Apple");
        checkbox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                headerLabel.setText("Apple Checkbox: "
                        + (e.getStateChange()== ItemEvent.SELECTED ? "checked":"unchecked"));
            }
        });
        Checkbox checkbox2 = new Checkbox("Mango");
        checkbox2.addItemListener(itemEvent -> headerLabel.setText("Mango Checkbox: " + (itemEvent.getStateChange() == ItemEvent.SELECTED ? "checked" : "unchecked")));
        Checkbox checkbox3 = new Checkbox("Peer");
        checkbox3.addItemListener(itemEvent -> headerLabel.setText("Peer Checkbox: " + (itemEvent.getStateChange() == ItemEvent.SELECTED ? "checked" : "unchecked")));

        // флажки, объединённые в одну CheckboxGroup представляют собой радиокнопки
        CheckboxGroup radioGroup = new CheckboxGroup();
        // булево значение обозначает состояние радиокнопки(выбрана/не выбрана)
        Checkbox radio1 = new Checkbox("radio1", radioGroup, true);
        radio1.addItemListener(itemEvent -> statusLabel.setText("radio1 checked"));
        Checkbox radio2 = new Checkbox("radio2", radioGroup, false);
        radio2.addItemListener(itemEvent -> statusLabel.setText("radio2 checked"));
        Checkbox radio3 = new Checkbox("radio3", radioGroup, false);
        radio3.addItemListener(itemEvent -> statusLabel.setText("radio3 checked"));

        // список для выбора(первый аргумент - max число элементов, второй - можно ли выбирать сразу несколько элементов )
        List fruitList = new List(4, false);
        fruitList.add("Apple");
        fruitList.add("Grape");
        fruitList.add("Mango");
        fruitList.add("Peer");
        fruitList.addActionListener(e -> statusLabel.setText(fruitList.getSelectedItem()));

        List vegetableList = new List(4, true);
        vegetableList.add("Onion");
        vegetableList.add("Potato");
        vegetableList.add("Cucumber");
        vegetableList.add("Tomato");
        vegetableList.addActionListener(e -> {
            String text = "";
            for (String item : vegetableList.getSelectedItems()){
                text += item + " ";
            }
            statusLabel.setText(text);
        });

        // поле для ввода однострочного текста
        TextField textField = new TextField(6);
        textField.addActionListener(e -> headerLabel.setText(textField.getText()));

        // поле для ввода многострочного текста(первый аргумент - начальный текст, второй - высота(колво строк), третий - ширина(кол-во символов в строке))
        TextArea textArea = new TextArea("This is Text Area", 5, 10);
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                headerLabel.setText(headerLabel.getText() + textArea.getText());
            }

        });

        // выпадающий список для выбора одного элемента
        Choice choice = new Choice();
        choice.add("Apple");
        choice.add("Grape");
        choice.add("Mango");
        choice.add("Peer");
        choice.addItemListener(e -> statusLabel.setText(choice.getSelectedItem()));

        // прямоугольная область для изображения графических элементов
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.GRAY);
        canvas.setSize(100, 100);

        //кнопка для вызова диалогового окна
        Button showDialog = new Button("dialog window");
        showDialog.addActionListener(e -> {
            new AboutDialog(mainFrame).setVisible(true);
        });

        // кнопка для вызова окна выбора файла
        Button openFile = new Button("Open File");
        openFile.addActionListener(actionEvent -> {
            FileDialog fileDialog = new FileDialog(mainFrame, "Select File");
            fileDialog.setVisible(true);
            statusLabel.setText(fileDialog.getDirectory() + fileDialog.getFile());
        });

        // планка, на которой будут располагаться кнопки Menu
        MenuBar menuBar = new MenuBar();
        // кнопки с меню, которые располагаются на MenuBar
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu aboutMenu = new Menu("About");

        // MenuItem - элемент в Menu (задаём имя и кнопку, с помощью которой можно сразу обратиться к MenuItem)
        MenuItem newMenuItem = new MenuItem("New", new MenuShortcut(KeyEvent.VK_N));
        // задаём имя для действия, которое будут вызываться в обработчике событий
        newMenuItem.setActionCommand("New");

        MenuItem openMenuItem = new MenuItem("Open", new MenuShortcut(KeyEvent.VK_N));
        newMenuItem.setActionCommand("Open");

        MenuItem saveMenuItem = new MenuItem("Save", new MenuShortcut(KeyEvent.VK_N));
        newMenuItem.setActionCommand("Save");

        MenuItem exitMenuItem = new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_N));
        newMenuItem.setActionCommand("Exit");

        MenuItem cutMenuItem = new MenuItem("Cut");
        cutMenuItem.setActionCommand("Cut");

        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.setActionCommand("Copy");

        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.setActionCommand("Paste");

        // обработчик для кнопок меню MenuItem
        MenuItemListener menuItemListener = new MenuItemListener();
        // добавляем обработчик для MenuItem
        newMenuItem.addActionListener(menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);
        cutMenuItem.addActionListener(menuItemListener);
        copyMenuItem.addActionListener(menuItemListener);
        pasteMenuItem.addActionListener(menuItemListener);

        // MenuItem, которое работает как флажок CheckBox
        CheckboxMenuItem showWindowMenu = new CheckboxMenuItem("Show About", true);
        showWindowMenu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(showWindowMenu.getState()){
                    menuBar.add(aboutMenu);
                } else {
                    menuBar.remove(aboutMenu);
                }
            }
        });

        // добавляем к меню(Menu) элементы меню (MenuItem)
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        // добавляем линию между элементами меню
        fileMenu.addSeparator();
        fileMenu.add(showWindowMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        // добавляем к панели меню(MenuBar) меню(Menu)
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);

        // всплывающее меню
        PopupMenu popupMenu = new PopupMenu("Edit");

        MenuItem pop1 = new MenuItem("pop1");
        pop1.setActionCommand("pop1");

        MenuItem pop2 = new MenuItem("pop2");
        pop2.setActionCommand("pop2");

        pop1.addActionListener(menuItemListener);
        pop2.addActionListener(menuItemListener);

        popupMenu.add(pop1);
        popupMenu.add(pop2);

        controlPanel.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // всплывающее окно будет выводиться по нажатию правой кнопки мыши
                if(e.getButton() == MouseEvent.BUTTON3){
                    // выводим всплывающее меню в указаннном элементе в указанных координат
                    popupMenu.show(controlPanel, e.getX(), e.getY());
                }
            }
        }

        );



        // добавляем в окно панель с меню
        mainFrame.setMenuBar(menuBar);
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        controlPanel.add(okButton);
        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);
        controlPanel.add(checkbox1);
        controlPanel.add(checkbox2);
        controlPanel.add(checkbox3);
        controlPanel.add(radio1);
        controlPanel.add(radio2);
        controlPanel.add(radio3);
        controlPanel.add(fruitList);
        controlPanel.add(vegetableList);
        controlPanel.add(textField);
        controlPanel.add(textArea);
        controlPanel.add(choice);
        controlPanel.add(canvas);
        controlPanel.add(new ImageComponent("images/kekistan.jpg"));
        controlPanel.add(showDialog);
        controlPanel.add(openFile);
        controlPanel.add(popupMenu);


        //mainFrame.setVisible(true);

    }

    class ImageComponent extends Component{

        BufferedImage image;

        //данный метод вызывается всегда при отрисовке элемента
        @Override
        public void paint(Graphics graphics){
            graphics.drawImage(image, 0,0, null);
        }

        public ImageComponent(String path){
            try {
                image = ImageIO.read(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Dimension getPreferredSize() {
            if (image == null) {
                return new Dimension(100,100);
            } else {
                return new Dimension(image.getWidth(), image.getHeight());
            }
        }

    }

    class AboutDialog extends Dialog{

        public AboutDialog(Frame parentFrame){
            super(parentFrame, true);
            setBackground(Color.gray);
            setLayout(new BorderLayout());
            Panel panel = new Panel();
            panel.add(new Button("Close"));
            add("South", panel);
            setSize(200,200);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // закрываем текущее окно
                    dispose();
                }
            });
        }


        @Override
        public boolean action(Event evt, Object what) {
            // кнопка ищется по имени
            if(what.equals("Close")){
                dispose();
                return true;
            }
            return false;
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.white);
            g.drawString("QWQWU", 25,75);
            g.drawString("qwerqt", 60, 90);
        }
    }

    class MenuItemListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // получаем имя команды, которое было назначено с помощью метода setActionCommand()
            statusLabel.setText(e.getActionCommand() + " MenuItem clicked.");
        }
    }

}
