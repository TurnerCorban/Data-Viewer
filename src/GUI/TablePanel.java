package src.GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;

public class TablePanel extends JPanel{
    JTable table;
    JPanel filterPanel;
    TableModel model;
    JScrollPane scrollPane;
    TableRowSorter<TableModel> sorter;
    JTextField filterField;
    JComboBox<Object> filterComboBox;

    TablePanel(DefaultTableModel model) {
        this.model = model;
        String[] identifiers = {"Country Name","Series Name","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        model.setColumnIdentifiers(identifiers);

        setPreferredSize(new Dimension(1000, 400));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        sorter = new TableRowSorter<>(model);
        table = new JTable();
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSorter(sorter);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getSelectionModel().addListSelectionListener(new RowListener());

        scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(780, 400));

        changeRenderer(table); //Overrides the JTable's default renderer to display -1 as N/A

        filterField = new JTextField();
        filterField.setPreferredSize(new Dimension(300,20));
        filterField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                textFilter();
            }
            public void removeUpdate(DocumentEvent e) {
                textFilter();
            }
            public void changedUpdate(DocumentEvent e) {
                textFilter();
            }
        });

        filterComboBox = new JComboBox<>(new Object[]{"GDP","GDP growth","GDP per capita","Inflation GDP deflator","Inflation consumer prices"});
        filterComboBox.setPreferredSize(new Dimension(300,20));
        filterComboBox.setSelectedIndex(0);
        filterComboBox.addActionListener(_ -> updateFilter());

        filterPanel.add(new JLabel("Country Filter:"));
        filterPanel.add(filterField);
        filterPanel.add(new JLabel("Series Filter:"));
        filterPanel.add(filterComboBox);
        filterPanel.add(new JLabel("     Click on a row to update Graph"));

        add(scrollPane, BorderLayout.CENTER);
        add(filterPanel, BorderLayout.SOUTH);

    }
    void changeRenderer(JTable table) {         //Overrides the JTable's default renderer to display -1 as N/A
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                float notAvailable = 0;
                try {
                    notAvailable = Float.parseFloat(value.toString());
                } catch (NumberFormatException | NullPointerException _) {}
                if (notAvailable == -99.0)
                    value = "N/A";
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }

    private void textFilter() {
        RowFilter<TableModel, Integer> rf;
        try {
            rf = RowFilter.regexFilter(filterField.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    protected void updateFilter() {
        RowFilter<TableModel, Integer> rf = switch (filterComboBox.getSelectedIndex()) {
            case 0 -> RowFilter.regexFilter("Gross", 1);
            case 1 -> RowFilter.regexFilter("GDP growth", 1);
            case 2 -> RowFilter.regexFilter("GDP per", 1);
            case 3 -> RowFilter.regexFilter("Inflation GDP", 1);
            case 4 -> RowFilter.regexFilter("Inflation consumer prices", 1);
            case 5 -> RowFilter.regexFilter("Inflation GDP growth", 1);
            default -> null;
        };
        sorter.setRowFilter(rf);
    }

    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            for (int c : table.getSelectedRows()) {
                Display.refreshChart(c);
            }
        }
    }
}
