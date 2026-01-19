import javax.swing.*;

//public class StatsPanel extends JPanel {
//    static JLabel avgLabel;
//    static JTextArea text;
//
//    StatsPanel() {
//        setLayout(new FlowLayout());
//        avgLabel = new JLabel("Average"){
//
//        };
//        text = new JTextArea(){
//            {
//                setEditable(false);
//                setPreferredSize(new Dimension(200, 400));
//            }
//        };
////        text = new JTextArea();
//
//        add(avgLabel);
//        add(text);
//        setVisible(true);
//    }
//
//    public static void setText(Object[] values, String[] years) {
//        StringBuilder valuesString = new StringBuilder();
//        float avg = 0;
//        for (int i = 0; i < values.length; i++) {
//            if((float) values[i] != -99) {
//                valuesString.append(years[i]).append("\t").append(values[i]).append("\n");
//                avg += Float.parseFloat(values[i].toString());
//            }
//        }
//        avgLabel.setText("20 Year Average: %%s\n\n%%s%s".formatted(avg));
//        text.setText(text.getText().formatted(avg, valuesString.toString()));
//    }
//}

public class StatsPanel extends JPanel {
    static JTextArea label;

    StatsPanel() {
        setBorder(BorderFactory.createTitledBorder("Chart Stats"));

        label = new JTextArea(){
            {
                setEditable(false);
                setOpaque(false);
            }
        };
        add(label);
        setVisible(true);
    }

    public static void setText(Object[] values, String[] years) {
        StringBuilder valuesString = new StringBuilder();
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
