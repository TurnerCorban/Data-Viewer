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
    JPanel panel;
    public ChartDisplayPanel(String series, ArrayList<Data> data, int rowkey){
        removeAll();
        Data d = data.get(rowkey);
        System.out.println("Inside ChartDisplayPanel");
        panel = new JPanel();
        DefaultCategoryDataset dataset = createDataset(d,data);
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
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.setVisible(true);
        panel.add(chartPanel);
        add(panel);
    }

    public static DefaultCategoryDataset createDataset(Data d, ArrayList<Data> data){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(d.yr2004(),d.series(),"2004");
        dataset.addValue(d.yr2005(),d.series(),"2005");
        dataset.addValue(d.yr2006(),d.series(),"2006");
        dataset.addValue(d.yr2007(),d.series(),"2007");
        dataset.addValue(d.yr2008(),d.series(),"2008");
        dataset.addValue(d.yr2009(),d.series(),"2009");
        dataset.addValue(d.yr2010(),d.series(),"2010");
        dataset.addValue(d.yr2011(),d.series(),"2011");
        dataset.addValue(d.yr2012(),d.series(),"2012");
        dataset.addValue(d.yr2013(),d.series(),"2013");
        dataset.addValue(d.yr2014(),d.series(),"2014");
        dataset.addValue(d.yr2015(),d.series(),"2015");
        dataset.addValue(d.yr2016(),d.series(),"2016");
        dataset.addValue(d.yr2017(),d.series(),"2017");
        dataset.addValue(d.yr2018(),d.series(),"2018");
        dataset.addValue(d.yr2019(),d.series(),"2019");
        dataset.addValue(d.yr2020(),d.series(),"2020");
        dataset.addValue(d.yr2021(),d.series(),"2021");
        dataset.addValue(d.yr2022(),d.series(),"2022");
        dataset.addValue(d.yr2023(),d.series(),"2023");
        return dataset;
    }
}