package com.core.sentiment_analysis;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

public class PieChart extends JFrame {
	   static double positive, negative;
	   public PieChart( ) 
	   { 
	      
	   }
	   public PieChart(String title)
	   {
		   super(title);
		   setContentPane(createDemoPanel( ));
	   }
	   public void set_variables(double positive, double negative)
	   {
		   this.positive = positive;
		   this.negative = negative;
		   
	   }
	   private static PieDataset createDataset( ) {
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      dataset.setValue( "Positive Comments" , positive );  
	      dataset.setValue( "Negative Comments" ,  negative);     
	      System.out.println("CreateDataset");
	      return dataset;
	   }
	   
	   private static JFreeChart createChart( PieDataset dataset ) {
	      JFreeChart chart = ChartFactory.createPieChart(      
	         "Comments",   // chart title 
	         dataset,          // data    
	         true,             // include legend   
	         true, 
	         false);
	      System.out.println("CreateChart");
	      return chart;
	   }
	   
	   public static JPanel createDemoPanel( ) {
	      JFreeChart chart = createChart(createDataset( ) );  
	      System.out.println("CreateDemoPanel");
	      return new ChartPanel( chart ); 
	   }

	   /*public static void main( String[ ] args ) {
		   PieChart demo = new PieChart("Comments");  
	      demo.setSize( 560 , 367 );    
	      RefineryUtilities.centerFrameOnScreen( demo );    
	      demo.setVisible( true );
	      demo.set_variables(74.0, 42.0);
	   
		   demo.main1();
	   }*/
	   public void main1()
	   {
		   PieChart demo = new PieChart("Comments");  
		   	  System.out.println("Main1");
		      demo.setSize( 560 , 367 );    
		      RefineryUtilities.centerFrameOnScreen( demo );    
		      demo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		      demo.setVisible( true );
		      
	   }
	}