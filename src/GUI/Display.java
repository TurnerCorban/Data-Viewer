package src.GUI;

import org.jfree.chart.JFreeChart;
import src.Data.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Display{
    static JFrame frame = new JFrame("Data Viewer");
    static ArrayList<Data> data;
    JFreeChart chart;
    JTextArea textArea;
    JButton button;
    static int rowkey = 56;


    public Display(DefaultTableModel model, ArrayList<Data> data) {
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        TablePanel tablePanel = new TablePanel(model);

        ChartDisplayPanel chartPanel = new ChartDisplayPanel("Series1",data,rowkey);

        chartPanel.setVisible(true);

        button = new JButton("Refresh Graph");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //chartPanel = new ChartDisplayPanel("Series1",data,rowkey);

            }
        });

        frame.add(tablePanel);
        frame.add(chartPanel);
        frame.add(button);
        frame.add(new JLabel("Data courtesy of databank.worldbank.org/source/world-development-indicators/"));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
