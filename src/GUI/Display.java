package src.GUI;

import src.Data.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import src.Data.Observer;

public final class Display{
    static JFrame frame;
    static ArrayList<Data> data;
    static JPanel dataPanel;
    static TablePanel tablePanel;
    static StatsPanel statsPanel;

    public static void display(DefaultTableModel model, ArrayList<Data> data) {
        frame = new JFrame("Data Viewer"){
            {
                setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            }
        };
        Display.data = data;


        tablePanel = new TablePanel(model);
        statsPanel = new StatsPanel();


        dataPanel = (JPanel) new JPanel().add(new ChartDisplayPanel(data,0));
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));

        dataPanel.add(statsPanel);

        frame.add(tablePanel);
        frame.add(dataPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void refreshChart(int selectedColumn){
        dataPanel.removeAll();
        statsPanel = new StatsPanel();
        dataPanel.add(new ChartDisplayPanel(data,selectedColumn));
        dataPanel.add(statsPanel);
        dataPanel.revalidate();
        dataPanel.repaint();
    }
}
