package src.Data;

import src.GUI.Display;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static JFrame frame = new JFrame("Data Viewer");
    static ArrayList<Data> data;

    public static void main(String[] args) throws IOException {
        data = FileReader.readFile("src/Countries.csv");
        System.out.println(data.size());

        DefaultTableModel model = addData(data);
        new Display(model, data);
    }

    static DefaultTableModel addData(ArrayList<Data> data) {
        DefaultTableModel model = new DefaultTableModel(0,22){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        for (Data d : data) {
            model.addRow(new Object[]{d.country(), d.series(), d.yr2004(), d.yr2005(), d.yr2006(), d.yr2007(), d.yr2008(), d.yr2009(), d.yr2010(), d.yr2011(), d.yr2012(), d.yr2013(), d.yr2014(), d.yr2015(), d.yr2016(), d.yr2017(), d.yr2018(), d.yr2019(), d.yr2020(), d.yr2021(), d.yr2022(), d.yr2023()});
        }
        return model;
    }
}
