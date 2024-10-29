package src.GUI;

import src.Data.Data;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class TablePanel extends JPanel implements ActionListener {

    JTable table;
    JPanel filterpanel = new JPanel();
    DataFilterPanel[] filters;
    TableModel model;
    JScrollPane scrollpane;
    JButton button;
    ArrayList<Data> data;
    TableRowSorter<TableModel> sorter;

    TablePanel(DefaultTableModel model) {
        this.model = model;
        DefaultTableModel df = new DefaultTableModel();
        String[] identifiers = {"Country Name","Series Name","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        model.setColumnIdentifiers(identifiers);

        setPreferredSize(new Dimension(1000, 400));
        setLayout(new BorderLayout());

        filterpanel = new JPanel();
        //filters = Arrays.stream(model.getFilterSets())
        //                .map(DataFilterPanel::new)
        //                .peek(dfp -> filterpanel.add(dfp))
        //                .peek(dfp -> dfp.addActionListener(this))
        //                .toArray(DataFilterPanel[]::new);



        filterpanel.setLayout(new BoxLayout(filterpanel, BoxLayout.PAGE_AXIS));



        sorter = new TableRowSorter(model);
        table = new JTable();
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSorter(sorter);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setPreferredSize(new Dimension(780, 400));

        changeRenderer(table); //Overrides the JTable's default renderer to display -1 as N/A


        button = new JButton("Display Selected Graph");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedColumn = 0;
                if (table.getSelectedColumn() >= selectedColumn) {
                    selectedColumn = table.getSelectedColumn();
                }
                System.out.println(selectedColumn);
                Display.refreshChart(selectedColumn);
            }
        });

        add(filterpanel, BorderLayout.NORTH);
        add(scrollpane, BorderLayout.CENTER);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        HashMap<Integer, Predicate[]> columnToFilterMap = new HashMap<>();
        for (DataFilterPanel filter : filters) {
            Predicate[] filters = new Predicate[0]; //= filters.getCheckedFilters();
            for (int i = 0; i < model.getColumnCount(); i++){
                if(model.getColumnClass(i).equals(filter.getSample().getClass()))
                    columnToFilterMap.put(i, filters);
            }
        }
    }
}
