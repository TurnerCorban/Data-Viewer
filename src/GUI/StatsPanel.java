package src.GUI;

import javax.swing.*;

public class StatsPanel extends JPanel {
    static JTextArea statsArea;

    StatsPanel() {
        setBorder(BorderFactory.createTitledBorder("Chart Stats")); // Create a border around the StatsPanel

        statsArea = new JTextArea(){
            {
                //Makes the JTextArea behave more like a large JLabel
                setEditable(false);
                setOpaque(false);
            }
        };
        add(statsArea);
        setVisible(true);
    }

    public static void setText(Object[] values, String[] years) {
        StringBuilder valuesString = new StringBuilder(); // Uses a string builder for step by step string creation.
        float avg = 0;
        for (int i = 0; i < values.length; i++) {
            if((float) values[i] != -999) { // Only adds to valuesString and avg if the value exists (!= -99)
                valuesString.append(years[i]).append("\t").append(values[i]).append("\n"); // Appends the valid years and values
                avg += Float.parseFloat(values[i].toString()); // Calculates the average of the currently selected row in TablePanel
            }
        }
        statsArea.setText("20 Year Average: %s\n\n%s".formatted(avg, valuesString.toString())); // Outputs avg (first %s) then two newlines then the contents of valuesString (2nd %s)
    }
}
