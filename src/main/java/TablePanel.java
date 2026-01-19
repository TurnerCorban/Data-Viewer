import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class TablePanel extends JPanel implements Subject {
    JTable table;
    JPanel filterPanel;
    TableModel model;
    JScrollPane scrollPane;
    TableRowSorter<TableModel> sorter;
    JTextField filterField;
    JComboBox<Object> filterComboBox;
    private int rowkey;
    private final ArrayList<Observer> observers;

    TablePanel(DefaultTableModel model) {
        this.model = model;

        observers = new ArrayList<>(); //List of the observers watching this class

        String[] identifiers = {"Country","Series","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        model.setColumnIdentifiers(identifiers);

        setPreferredSize(new Dimension(1000, 400));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        sorter = new TableRowSorter<>(model);
        table = new JTable(){
            {
                setModel(model);
                getTableHeader().setReorderingAllowed(false);
                setRowSorter(sorter);
                setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                getSelectionModel().addListSelectionListener(new RowListener());
            }
        };

        scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED){
            {
                setPreferredSize(new Dimension(780, 400));
            }
        };

        filterField = new JTextField(){
            {
                setPreferredSize(new Dimension(300,20));
                getDocument().addDocumentListener(new DocumentListener() {
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

        filterComboBox = new JComboBox<>(new Object[]{"All","GDP","GDP growth","GDP per capita","Inflation GDP deflator","Inflation consumer prices"}){
            {
                setPreferredSize(new Dimension(300,20));
                setSelectedIndex(0);
                addActionListener(_ -> updateFilter());
            }
        };

        filterPanel = new JPanel(){
            {
                add(new JLabel("Country Filter:"));
                add(filterField);
                add(new JLabel("Series Filter:"));
                add(filterComboBox);
                add(new JLabel("     Click on a row to update Graph"));
            }
        };

        changeRenderer(table); //Overrides the JTable's default renderer to display -99 as N/A

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
            rf = RowFilter.regexFilter("(?i)" + filterField.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    protected void updateFilter() {
        RowFilter<TableModel, Integer> rf = switch (filterComboBox.getSelectedIndex()) {
            case 0 -> RowFilter.regexFilter("",1);
            case 1 -> RowFilter.regexFilter("Gross", 1);
            case 2 -> RowFilter.regexFilter("GDP growth", 1);
            case 3 -> RowFilter.regexFilter("GDP per", 1);
            case 4 -> RowFilter.regexFilter("Inflation GDP", 1);
            case 5 -> RowFilter.regexFilter("Inflation consumer prices", 1);
            case 6 -> RowFilter.regexFilter("Inflation GDP growth", 1);
            default -> null;
        };
        sorter.setRowFilter(rf);
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(rowkey);
        }

    }

    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            for (int c : table.getSelectedRows()) {
                rowkey = table.convertRowIndexToModel(c);
                notifyObservers();
                System.out.println(rowkey);
            }
        }
    }
}