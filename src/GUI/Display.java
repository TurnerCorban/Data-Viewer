package src.GUI;

import org.jfree.chart.JFreeChart;
import src.Data.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Display {
    static JFrame frame = new JFrame("Data Viewer");
    static ArrayList<Data> data;
    JFreeChart chart;
    JTextArea textArea;
    JButton button;


    public Display(DefaultTableModel model, ArrayList<Data> data) {
        this.data = data;
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));



        TablePanel tablePanel = new TablePanel(model);

        ChartDisplayPanel chartPanel = new ChartDisplayPanel("Series",data,0);

        frame.add(tablePanel);
        frame.add(chartPanel);
        frame.add(new JLabel("Data courtesy of databank.worldbank.org/source/world-development-indicators/"));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void refreshChart(int selectedColumn){
        frame.add(new ChartDisplayPanel("Series", data, selectedColumn));
    }


}
