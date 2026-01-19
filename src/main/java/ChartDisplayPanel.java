import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class ChartDisplayPanel extends JPanel implements Observer {
    static JPanel chartDisplayPanel;
    static ChartPanel chartPanel;
    static int rowkey;
    private static ArrayList<Data> data;

    private static final int labelRotation = 45;

    public ChartDisplayPanel(ArrayList<Data> data, int rowkey){ // Default constructor
        ChartDisplayPanel.rowkey = rowkey;
        ChartDisplayPanel.data = data;

        add(chartDisplayPanel = new JPanel()); // Instantiates a new chartDisplayPanel and adds it
        displayChart(data, rowkey);

        // Adds itself as an observer to TablePanel to watch for updates
        Observer observer = this;
        DisplayDriver.tablePanel.addObserver(observer);
    }

    public void displayChart(ArrayList<Data> data, int rowkey){
        Data d = data.get(rowkey);
        DefaultCategoryDataset dataset = createDataset(d);
        JFreeChart chart = ChartFactory.createLineChart( // Creates a line chart with the selected row
                d.country(),
                "Years",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );

        CategoryPlot plot = chart.getCategoryPlot(); // Gets the plot from the chart to edit it

        // Adds vertical lines to the plot and changes the stroke of the default horizontal lines. Default is a dotted line
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlineStroke(new BasicStroke(1)); // Sets the stroke of the horizontal grid line to 1 (solid)

        // Changes the plot's renderer to add points
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-2d, -2d, 4d, 4d));

        // Rotates the X axis labels so they are displayed
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createDownRotationLabelPositions(labelRotation));

        ((NumberAxis)plot.getRangeAxis()).setAutoRangeIncludesZero(false); // Set JFreeChart's auto range (default view) to not always include zero

        chartPanel = new ChartPanel(chart);
        chartDisplayPanel.setVisible(true);
        chartDisplayPanel.add(chartPanel);
    }

    public static DefaultCategoryDataset createDataset(Data d){
        Object[] values = {d.yr2004(), d.yr2005(),d.yr2006(),d.yr2007(),d.yr2008(),d.yr2009(),d.yr2010(),d.yr2011(),d.yr2012(),d.yr2013(),d.yr2014(),d.yr2015(),d.yr2016(),d.yr2017(),d.yr2018(),d.yr2019(),d.yr2020(),d.yr2021(),d.yr2022(),d.yr2023()};
        String[] years = {"2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        StatsPanel.setText(values, years);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < values.length; i++){
            Object temp = values[i];
            if ((Float) temp == -999){ // Only adds the value to the dataset when real (not -99)
                temp = null;
            }
            assert values[i] instanceof Number;
            dataset.addValue((Number) temp, d.series(), years[i]);
        }
        return dataset;
    }

    @Override
    public void update(int rowkey) { // When the Observer Pattern is updated, refresh the chart
        chartDisplayPanel.remove(chartPanel);
        ChartDisplayPanel.rowkey = rowkey;
        displayChart(data, rowkey);
        chartDisplayPanel.revalidate();
        chartDisplayPanel.repaint();
    }
}