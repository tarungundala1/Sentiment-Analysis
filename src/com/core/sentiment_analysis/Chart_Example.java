package com.core.sentiment_analysis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.View;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author imssbora
 */
public class Chart_Example extends JFrame {
  private static final long serialVersionUID = 6294689542092367723L;
  static List<Double> score_list = new ArrayList<Double>();
  static List<Integer> result1 = new ArrayList<Integer>();
  static JFreeChart chart;
  static XYPlot plot ;
  static XYDataset dataset ;
  static Map<Double, Integer> scores_and_result = new HashMap<Double, Integer>();
  public Chart_Example(String title) throws IOException {

    // Create dataset
    dataset = createDataset();

    // Create chart
     chart = ChartFactory.createScatterPlot(
        "Sentiment Score", 
        "X-Axis", "Y-Axis", dataset);
    //Changes background color
    
     plot = (XYPlot)chart.getPlot();
	    plot.setBackgroundPaint(new Color(255,228,196));
			   plot.getRenderer().setPaint(Color.red);
	    plot.addRangeMarker(new ValueMarker(4.99, Color.BLUE, new BasicStroke(2.0f)));
      // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
    
    System.out.println("IN constructor");
  }
  public void set_variables(List<Double> score_list2)
  {
	  Iterator ie = score_list2.iterator();
	   while (ie.hasNext()) {
		   score_list.add((double) ie.next());
		
	}
		 
		  
  }

  private XYDataset createDataset() {
    XYSeriesCollection dataset = new XYSeriesCollection();
    XYSeries series1 = new XYSeries("Sentiment_Score");
    for(int i  = 0 ; i < score_list.size() ; i++)
    {
    	series1.add(i, score_list.get(i));
    }
    dataset.addSeries(series1);
    System.out.println("IN Dataset");
    return dataset;
  }

  public static void main(String[] args) throws ServletException 
  {
	  System.out.println("IN main");
	try {
		Chart_Example example = new Chart_Example("Sentiment Score");
		example.setSize(800, 400);
	      example.setLocationRelativeTo(null);
	      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      example.setVisible(true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void call_from_bar_chart() throws ServletException
  {
	 System.out.println("IN bar_charts");
	 String[] args = {"abc"};
	Chart_Example.main(args);
  }
}