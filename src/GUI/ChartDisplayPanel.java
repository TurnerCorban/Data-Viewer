package src.GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import src.Data.Data;

import javax.swing.*;
import java.util.ArrayList;

public class ChartDisplayPanel extends JPanel{
    static JPanel panel;
    static ChartPanel chartPanel;

    public ChartDisplayPanel(ArrayList<Data> data, int rowkey){
        Data d = data.get(rowkey);
        panel = new JPanel();
        DefaultCategoryDataset dataset = createDataset(d);
        JFreeChart chart = ChartFactory.createLineChart(
                d.country(),
                "Years",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
        chartPanel = new ChartPanel(chart);
        panel.setVisible(true);
        panel.add(chartPanel);
        add(panel);
    }

    public static DefaultCategoryDataset createDataset(Data d){
        Object[] values = {d.yr2004(), d.yr2005(),d.yr2006(),d.yr2007(),d.yr2008(),d.yr2009(),d.yr2010(),d.yr2011(),d.yr2012(),d.yr2013(),d.yr2014(),d.yr2015(),d.yr2016(),d.yr2017(),d.yr2018(),d.yr2019(),d.yr2020(),d.yr2021(),d.yr2022(),d.yr2023()};
        String[] years = {"2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        StatsPanel.setText(values, years);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        float avg = 0;
        for (int i = 0; i < values.length; i++){
            Object temp = values[i];
            if ((Float) temp == -99){
                temp = null;
            }
            assert values[i] instanceof Number;
            dataset.addValue((Number) temp, d.series(), years[i]);
        }
        return dataset;
    }
}