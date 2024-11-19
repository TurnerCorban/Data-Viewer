package src.GUI;

import src.Data.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public final class Display{
    static JFrame frame;
    static ArrayList<Data> data;
    static JPanel dataPanel;
    static TablePanel tablePanel;
    static StatsPanel statsPanel;

    public static void display(DefaultTableModel model, ArrayList<Data> data) { // Utility Class that manages the creation of the rest of the GUI
        Display.data = data;

        tablePanel = new TablePanel(model);
        statsPanel = new StatsPanel();

        dataPanel = new JPanel(){
            {
                add(new ChartDisplayPanel(data, 0));
                add(statsPanel);
            }
        };

        frame = new JFrame("Data Viewer") {
            {
                setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
                add(tablePanel);
                add(dataPanel);
                pack();
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            }
        };
    }
}
