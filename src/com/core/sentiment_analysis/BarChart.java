package com.core.sentiment_analysis;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart extends ApplicationFrame {
   static int outcomescount,staffcount,assesmentcount, supportcount, coursecount;
   public BarChart( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "Score",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
      final String categories = "Categories";                
      final String outcomes = "Outcomes";        
      final String staff = "Staff";
      final String course = "Course";
      final String assesment = "Assesment";
      final String support = "Support";
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      dataset.addValue( outcomescount , categories , outcomes );        
      dataset.addValue( staffcount , categories , staff );        
      dataset.addValue( coursecount , categories , course ); 
      dataset.addValue( assesmentcount , categories , assesment );
      dataset.addValue( supportcount , categories , support );

      return dataset; 
   }
   
   public static void main( String[ ] args ) {
      BarChart chart = new BarChart("Categories for Comments","");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
   public void call_main()
   {
	   main(null);
   }
public void setvariable(int outcomecount, int staffcount, int assesmentcount, int supportcount, int coursecount) {
	// TODO Auto-generated method stub
	
	this.outcomescount = outcomecount;
	this.supportcount = supportcount;
	this.assesmentcount = assesmentcount;
	this.coursecount = coursecount;
	this.staffcount = staffcount;
	
}
}