package src.GUI;

import src.Data.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Display {
    static JFrame frame = new JFrame("Data Viewer");
    static ArrayList<Data> data;
    static JPanel chartPanel;
    static TablePanel tablePanel;
    static StatsPanel statsPanel;

    public Display(DefaultTableModel model, ArrayList<Data> data) {
        Display.data = data;
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        tablePanel = new TablePanel(model);

        statsPanel = new StatsPanel();

        chartPanel = (JPanel) new JPanel().add(new ChartDisplayPanel(data,0));
        chartPanel.setLayout(new BoxLayout(chartPanel, BoxLayout.X_AXIS));
        chartPanel.setPreferredSize(new Dimension(800, 600));

        chartPanel.add(statsPanel);

        frame.add(tablePanel);
        frame.add(chartPanel);
        frame.add(new JLabel("Data courtesy of databank.worldbank.org/source/world-development-indicators/"));
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void refreshChart(int selectedColumn){
        chartPanel.removeAll();
        statsPanel = new StatsPanel();
        chartPanel.add(new ChartDisplayPanel(data,selectedColumn));
        chartPanel.add(statsPanel);
        chartPanel.revalidate();
        chartPanel.repaint();
    }
}
