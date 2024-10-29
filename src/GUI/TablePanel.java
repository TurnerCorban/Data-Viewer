package src.GUI;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TablePanel extends JPanel implements ActionListener {

    JTable table;
    JPanel filterpanel = new JPanel();
    DataFilterPanel[] filters;
    TableModel model;
    JScrollPane scrollpane;

    TablePanel(DefaultTableModel model) {
        this.model = model;
        DefaultTableModel df = new DefaultTableModel();
        String[] identifiers = {"Country Name","Series Name","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        model.setColumnIdentifiers(identifiers);

        setPreferredSize(new Dimension(1000, 400));
        setLayout(new BorderLayout());

        filterpanel = new JPanel();


        filterpanel.setLayout(new BoxLayout(filterpanel, BoxLayout.PAGE_AXIS));

        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);



        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1) {
                    final JTable table = (JTable)e.getSource();
                    final int row = table.getSelectedRow();
                    final int col = table.getSelectedColumn();
                    System.out.println(row + " " + col);
                }
            }
        });



        TableRowSorter<TableModel> sorter;
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        changeRenderer(table); //Overrides the JTable's default renderer to display -1 as N/A


        scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setPreferredSize(new Dimension(780, 400));

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

    }
}
