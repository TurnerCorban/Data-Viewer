package src.GUI;


import src.Data.Data;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class StatsPanel extends JPanel {
    static JTextArea label;

    StatsPanel() {
        setLayout(new BorderLayout());
        label = new JTextArea();
        label.setEditable(false);
        add(label);
        setVisible(true);
    }

    public static void setText(Object[] values, String[] years) {
        StringBuilder valuesString = new StringBuilder("");
        float avg = 0;
        for (int i = 0; i < values.length; i++) {
            if((float) values[i] != -99) {
                valuesString.append(years[i]).append("\t").append(values[i]).append("\n");
                avg += Float.parseFloat(values[i].toString());
            }
        }
        label.setText("20 Year Average: %s\n\n%s".formatted(avg, valuesString.toString()));
    }
}
