package src.GUI;

import src.Data.Data;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TablePanel extends JPanel{

    JTable table;
    JPanel filterpanel = new JPanel();
    DataFilterPanel[] filters;
    TableModel model;
    JScrollPane scrollpane;
    JButton button;
    ArrayList<Data> data;
    TableRowSorter<TableModel> sorter;
    JTextField filterField;
    JComboBox filterComboBox;

    TablePanel(DefaultTableModel model) {
        this.model = model;
        DefaultTableModel df = new DefaultTableModel();
        String[] identifiers = {"Country Name","Series Name","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        model.setColumnIdentifiers(identifiers);

        setPreferredSize(new Dimension(1000, 400));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        filterpanel = new JPanel();
        //filters = Arrays.stream(model.getFilterSets())
        //                .map(DataFilterPanel::new)
        //                .peek(dfp -> filterpanel.add(dfp))
        //                .peek(dfp -> dfp.addActionListener(this))
        //                .toArray(DataFilterPanel[]::new);

        filterpanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        sorter = new TableRowSorter(model);
        table = new JTable();
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSorter(sorter);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setPreferredSize(new Dimension(780, 400));

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


        filterComboBox = new JComboBox(new Object[]{"GDP","GDP growth","GDP per capita","Inflation GDP deflator","Inflation consumer prices"});
        filterComboBox.setPreferredSize(new Dimension(300,20));
        filterComboBox.setSelectedIndex(0);
        filterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFilter();
            }
        });

        filterpanel.add(new JLabel("Country Filter:"));
        filterpanel.add(filterField);
        filterpanel.add(new JLabel("Series Filter:"));
        filterpanel.add(filterComboBox);

        add(scrollpane, BorderLayout.CENTER);
        add(filterpanel, BorderLayout.SOUTH);

    }
    void changeRenderer(JTable table) {         //Overrides the JTable's default renderer to display -1 as N/A
        TableCellRenderer old = table.getDefaultRenderer(table.getColumnClass(21));
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                float notAvailable = 0;
                try {
                    notAvailable = Float.parseFloat(value.toString());
                } catch (NumberFormatException | NullPointerException e) {}
                if (notAvailable == -1)
                    value = "N/A";
                Component compon = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return compon;
            }
        });
    }
    private void textFilter() {
        RowFilter<TableModel, Integer> rf = null;
        try {
            rf = RowFilter.regexFilter(filterField.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
    protected void updateFilter() {
        RowFilter<TableModel, Integer> rf = null;
        switch(filterComboBox.getSelectedIndex()){
            case 0:
                rf = RowFilter.regexFilter("Gross" , 1);
                break;
            case 1:
                rf = RowFilter.regexFilter("GDP growth", 1);
                break;
            case 2:
                rf = RowFilter.regexFilter("GDP Per", 1);
                break;
            case 3:
                rf = RowFilter.regexFilter("Inflation GDP", 1);
                break;
            case 4:
                rf = RowFilter.regexFilter("Inflation consumer prices", 1);
                break;
            case 5:
                rf = RowFilter.regexFilter("Inflation GDP growth", 1);
                break;
        }
        sorter.setRowFilter(rf);
    }
}
