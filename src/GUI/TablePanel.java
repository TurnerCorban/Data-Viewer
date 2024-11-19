package src.GUI;

import src.Data.Observer;
import src.Data.Subject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class TablePanel extends JPanel implements Subject {
    JTable table;
    JPanel filterPanel;
    TableModel model;
    JScrollPane scrollPane;
    TableRowSorter<TableModel> sorter;
    JTextField filterField;
    JButton removeFiltersButton;
    JComboBox<Object> filterComboBox;
    RowFilter<TableModel, Integer> seriesFilter;
    RowFilter<TableModel, Integer> textFilter;
    JLabel reminder;
    private int rowkey;
    private final ArrayList<src.Data.Observer> observers;

    TablePanel(DefaultTableModel model) {
        this.model = model;
        observers = new ArrayList<>(); //List of the observers watching this class

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 400));

        String[] identifiers = {"Country","Series","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        model.setColumnIdentifiers(identifiers);

        sorter = new TableRowSorter<>(model);
        table = new JTable(){
            {
                setModel(model);
                getTableHeader().setReorderingAllowed(false);
                setRowSorter(sorter);
                setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                getSelectionModel().addListSelectionListener(new RowListener()); // Listens for the table being clicked
            }
        };

        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);

        scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        filterField = new JTextField(){
            {
                setPreferredSize(new Dimension(300,20));
                getDocument().addDocumentListener(new DocumentListener() { // Calls textFilter() whenever there is an update in the filterField
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
            }
        };

        filterComboBox = new JComboBox<>(new Object[]{"GDP","GDP growth","GDP per capita","Inflation GDP deflator","Inflation consumer prices"}){
            {
                setPreferredSize(new Dimension(300,20));
                setSelectedIndex(0);
                addActionListener(_ -> seriesFilter()); // Calls seriesFilter() whenever the combobox is changed
            }
        };

        // Creates a panel for the filters and adds them
        filterPanel = new JPanel(){
            {
                add(new JLabel("Country Filter:"));
                add(filterField);
                add(new JLabel("Series Filter:"));
                add(filterComboBox);
                add(removeFiltersButton = new JButton("Remove Filters"));
            }
        };

        changeRenderer(table); // Overrides the JTable's default renderer to display -999 as N/A

        removeFiltersButton.addActionListener(_ -> removeFilters());

        add(filterPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(reminder = new JLabel("Click on a row in the table to update the graph"){
            {
                setHorizontalAlignment(JLabel.CENTER);
            }
        }, BorderLayout.SOUTH);

    }

    void changeRenderer(JTable table) {         // Overrides the JTable's default renderer to display -1 as N/A
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                float notAvailable = 0;
                try {
                    notAvailable = Float.parseFloat(value.toString());
                } catch (NumberFormatException | NullPointerException _) {}
                if (notAvailable == -999.0)
                    value = "N/A";
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }

    private void textFilter() {
        try {
            textFilter = RowFilter.regexFilter("(?i)" + filterField.getText(), 0); // "(?i)" makes the filter case-insensitive
        } catch (java.util.regex.PatternSyntaxException e) { // Exits the method if the exception is thrown
            return;
        }
        sorter.setRowFilter(textFilter);
    }

    protected void seriesFilter() { // Gets the
        seriesFilter = switch (filterComboBox.getSelectedIndex()) { // Gets the index from the combobox and uses a switch statement to find the
            case 0 -> RowFilter.regexFilter("GDP [^a-z]", 1); // [^a-z] negates every standard character directly after, so other series won't show up in the filter
            case 1 -> RowFilter.regexFilter("GDP growth", 1);
            case 2 -> RowFilter.regexFilter("GDP per", 1);
            case 3 -> RowFilter.regexFilter("Inflation GDP", 1);
            case 4 -> RowFilter.regexFilter("Inflation consumer prices", 1);
            case 5 -> RowFilter.regexFilter("Inflation GDP growth", 1);
            case 6 -> RowFilter.regexFilter("Inflation consumer", 1);
            default -> null;
        };
        sorter.setRowFilter(seriesFilter);
    }

    private void removeFilters(){
        sorter.setRowFilter(null);
    }

    private class RowListener implements ListSelectionListener { // Runs when the tablemodel is clicked
        public void valueChanged(ListSelectionEvent event) {
            for (int c : table.getSelectedRows()) { // Gets the row being selected whenever the tablemodel is clicked
                rowkey = table.convertRowIndexToModel(c); // Converts the selected row to be relative to the model instead of the graph
                notifyObservers();  // Uses an Observer Pattern method to update the graph
            }
        }
    }

    // Observer Pattern methods
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(rowkey);
        }
    }
}