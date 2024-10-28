package src.Data;

import src.GUI.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    static JFrame frame = new JFrame("Data Viewer");
    static ArrayList<Data> data;

    public static void main(String[] args) throws IOException {
        data = FileReader.readFile("src/WorldInflationData_10yrs.csv");
        printData(data.get(0));

        DefaultTableModel model = addData(data);
        display(model);
    }

    static DefaultTableModel addData(ArrayList<Data> data) {
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < data.size(); i++) {
            Data d = data.get(i);
            model.addRow(new Object[]{});
        }
        return model;
    }

    static void display(TableModel model){
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel(model);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void printData(Data data) {
        System.out.println();
        System.out.println();
        System.out.println("Data:");
        System.out.println("Country: " + data.country());
        System.out.println("2014 " + data.yr2014());
        System.out.println("2015 " + data.yr2015());
        System.out.println("2016 " + data.yr2016());
        System.out.println("2017 " + data.yr2017());
        System.out.println("2018 " + data.yr2018());
        System.out.println("2019 " + data.yr2019());
        System.out.println("2020 " + data.yr2020());
        System.out.println("2021 " + data.yr2021());
        System.out.println("2022 " + data.yr2022());
        System.out.println("2023 " + data.yr2023());
    }
}
