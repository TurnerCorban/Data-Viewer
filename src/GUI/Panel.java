package src.GUI;

import src.Data.Data;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class Panel extends JPanel {


    public Panel(TableModel model) {
        JPanel panel = new JPanel();

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        table.setAutoCreateColumnsFromModel(true);



        panel.add(scrollPane);
        panel.setVisible(true);
    }

}
