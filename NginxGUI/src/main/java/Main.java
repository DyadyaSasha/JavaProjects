

import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args){
        new Main().prepareGUI();
    }

    private void prepareGUI() {

        JFrame mainFrame = new JFrame("NginxGUI");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(new Dimension(300, 300));
        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel westPanel = new JPanel();
        JList<String> westList = new JList<>();
        westList.setVisibleRowCount(8);
        westList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane westScrollPane = new JScrollPane(westList);
        westScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        westScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        westScrollPane.setPreferredSize(new Dimension(20, 80));

        Button buttonSettings = new Button("Settings");
        buttonSettings.addActionListener(event -> {
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setSize(20, 20);
            settingsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            settingsFrame.setVisible(true);
        });

        Button buttonAdd = new Button("Add");
        buttonAdd.addActionListener(event -> {

        });

        Box westBox = new Box(BoxLayout.X_AXIS);
        westBox.add(buttonSettings);
        westBox.add(buttonAdd);

        BoxLayout westLayout = new BoxLayout(westPanel, BoxLayout.Y_AXIS);
        westPanel.setLayout(westLayout);

        westPanel.add(westScrollPane);
        westPanel.add(westBox);

        JPanel centerPanel = new JPanel();
        JList<String> centerWestList = new JList<>();
        centerWestList.setVisibleRowCount(8);
        centerWestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane centerWestScrollPane = new JScrollPane(centerWestList);
        centerWestScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        centerWestScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        centerWestScrollPane.setPreferredSize(new Dimension(80, 80));

        JList<String> centerEastList = new JList<>();
        centerEastList.setVisibleRowCount(8);
        centerEastList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane centerEastScrollPane = new JScrollPane(centerEastList);
        centerEastScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        centerEastScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        centerEastScrollPane.setPreferredSize(new Dimension(80, 80));

        centerPanel.add(centerEastScrollPane);
        centerPanel.add(centerWestScrollPane);

        JPanel eastPanel = new JPanel();

        JList<String> eastList = new JList<>();
        eastList.setVisibleRowCount(8);
        eastList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane eastScrollPane = new JScrollPane(eastList);
        eastScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        eastScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        eastScrollPane.setPreferredSize(new Dimension(80, 80));


        Button buttonAdd2 = new Button("Add");
        buttonAdd.addActionListener(event -> {

        });

        BoxLayout eastLayout = new BoxLayout(eastPanel, BoxLayout.Y_AXIS);
        eastPanel.setLayout(eastLayout);

        eastPanel.add(eastScrollPane);
        eastPanel.add(buttonAdd2);

        mainFrame.add(westPanel);
        mainFrame.add(centerPanel);
        mainFrame.add(eastPanel);
        mainFrame.setVisible(true);
    }
}
