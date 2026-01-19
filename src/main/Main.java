package src.main;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static ArrayList<Data> data;
    static DefaultTableModel model;

    final static String filename = "src/resources/Countries.csv";

    public static void main(String[] args) throws IOException {
        data = FileReader.readFile(filename);
        model = addData(data);
        DisplayDriver.display(model, data);
    }

    static DefaultTableModel addData(ArrayList<Data> data) {
        DefaultTableModel model = new DefaultTableModel(0,22){
            @Override
            public boolean isCellEditable(int row, int column) { //prevents editing of table cells
                return false;
            }
        };
        for (Data d : data) {
            model.addRow(new Object[]{d.country(), d.series(), d.yr2004(), d.yr2005(), d.yr2006(), d.yr2007(), d.yr2008(), d.yr2009(), d.yr2010(), d.yr2011(), d.yr2012(), d.yr2013(), d.yr2014(), d.yr2015(), d.yr2016(), d.yr2017(), d.yr2018(), d.yr2019(), d.yr2020(), d.yr2021(), d.yr2022(), d.yr2023()});
        }
        return model;
    }
}
