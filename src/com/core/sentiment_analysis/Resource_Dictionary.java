package com.core.sentiment_analysis;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.servlets.sentiment_analysis.Get_Data_For_Charts;



public class Resource_Dictionary {
	static InputStream inputfile;
	static XSSFWorkbook wb,wb1;
	static XSSFSheet sheet, sheet1,sheet2, sheet3, sheet4;
	static XSSFRow row , row1;
	static XSSFCell cell , cell1, cell2, cell3;
	static String str = null , str1;
	static int number;
	static Map<String, Double> happy_list = new HashMap<String, Double>();
	static FileOutputStream outputwords ;
	static BufferedWriter fw; 
	static Map<String, Double> selected_list = new HashMap<String, Double>();
	static Map negative_list = new HashMap();
	static Map positive_list = new HashMap();
	String[] words ;
	Object[] keylist ;
	static int rownumber = 0;
	static double average_sentiment_score,sentiment_score,positive_score, negative_score;;
	int count;
	static List<Double> score_list = new ArrayList<Double>();
	static List<Integer> result_list = new ArrayList<Integer>(); 
	static List<String> querylist = new ArrayList<String>();
	Resource_Dictionary() throws IOException, InvalidFormatException
	{
		
		inputfile = new FileInputStream(new File("C:/Users/Dell/Desktop/comments.xlsm"));
    	 wb = new XSSFWorkbook(inputfile);
    	sheet = wb.getSheet("HAPPY");
    	outputwords = (new FileOutputStream("C:/Users/Dell/Desktop/outputwords.xlsx"));
    	wb1 = new XSSFWorkbook();
    	wb1.createSheet("Sheet1");
    	wb1.createSheet("Sheet2");
    	sheet1 = wb1.getSheet("Sheet1");
    	sheet2 = wb1.getSheet("Sheet2");
    	sheet3 = wb.getSheet("AllNegative");
    	sheet4 = wb.getSheet("AllPositive");
		for(int i = 0 ; i < sheet4.getPhysicalNumberOfRows() ; i++)
    	{
    		row = sheet4.getRow(i);
    		cell = row.getCell(0);
    		positive_list.put(i, cell.toString());
    		
    	}
	}
	@SuppressWarnings("deprecation")
	public void  resource_words(String query, int result1) throws IOException 
	{
		average_sentiment_score = 0;
		count = 0;
		sentiment_score = 0;
		//fw = new BufferedWriter(new FileWriter(outputwords));
    	words = query.split(" ");
    	keylist = happy_list.keySet().toArray();    	
		for(int i =1 ; i< sheet.getPhysicalNumberOfRows();i++)
    	{
    		row = sheet.getRow(i);
    		cell = row.getCell(0);
    		cell1 = row.getCell(1);
    		happy_list.put(cell.toString(), (double) cell1.getNumericCellValue());
    	}
    	for(int i = 0; i< keylist.length;i++)
    	{
    		for(int j = 0 ; j < words.length;j++)
    		{
    			if(words[j].equals(keylist[i]))
    			{
    				count++;
    				selected_list.put(words[j], happy_list.get(words[j]));
    				
    			}
    		}
    	}
    	
		for(int i = 0 ; i < keylist.length ; i++)
		{
			if(happy_list.get(keylist[i]) <= 4.0)
			{
				negative_list.put(keylist[i], happy_list.get(keylist[i]));
				//System.out.println("Negative Word-:"+keylist[i]+" Values-:"+negative_list.get(keylist[i]));
			}
			if(happy_list.get(keylist[i]) >= 4.99)
			{
				positive_list.put(keylist[i], happy_list.get(keylist[i]));
				//System.out.println("Positive Word-:"+keylist[i]+" Values-:"+positive_list.get(keylist[i]));
			}
		}
		count = 0;
		for(int i = 0 ; i<words.length;i++)
		{
			if(negative_list.containsKey(words[i]))
			{
				count++;
				//negative_score = negative_score + happy_list.get(words[i]);
				sentiment_score = sentiment_score - happy_list.get(words[i]);
			}
			if(positive_list.containsKey(words[i]))
			{
				count++;
				//positive_score = positive_score + happy_list.get(words[i]);
				sentiment_score = sentiment_score + happy_list.get(words[i]);
			}
		}
		
		average_sentiment_score = sentiment_score /count;
		System.out.println("Sentiment Score-:"+sentiment_score+" Average Score-:"+average_sentiment_score);
		score_list.add(average_sentiment_score);
		//sce.set_variables(average_sentiment_score);
		querylist.add(query);
 
	}
	public void write_to_file(double positive, double negative,double neutral) throws IOException
	{
		int k = 0;
		for(String key : selected_list.keySet())
		{
			row1 = sheet1.createRow(rownumber);
			cell2 = row1.createCell(3);
			cell3 = row1.createCell(4);
			cell3.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell2.setCellValue(key);
			cell3.setCellValue(selected_list.get(key));
			rownumber++;
		}
		
		row1 = sheet1.createRow(0);
	    cell = row1.createCell(0);
	    cell.setCellValue("Positive comments");
	    cell1 = row1.createCell(1);
	    cell1.setCellValue(positive);
	    cell2 = row1.createCell(3);
	    cell2.setCellValue("Word");
	    cell3 = row1.createCell(4);
	    cell3.setCellValue("Score");
	    row1 = sheet1.createRow(1);
	    cell = row1.createCell(0);
	    cell.setCellValue("Negative comments,");
	    cell1 = row1.createCell(1);
	    cell1.setCellValue(negative);
	    
	    for(int i = 0 ;i<score_list.size();i++)
	    {
	    	row = sheet2.createRow(i);
	    	cell = row.createCell(0);
	    	cell.setCellValue(i);
	    	cell1 = row.createCell(1);
	    	cell1.setCellValue(score_list.get(i));
	    }
		wb1.write(outputwords);
		wb1.close();
		outputwords.close();
		
		Get_Data_For_Charts gdfc = new Get_Data_For_Charts();
		gdfc.set_variables(positive, negative);
		
	}
	
	public void barchart()
	{
		
		try {
			Chart_Example ce = new Chart_Example("Sentiment score");
			ce.set_variables(score_list);
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void categorize_comments() throws IOException
	{
		Theme_Categorization theme_categorization = new Theme_Categorization();
		theme_categorization.compare_words_lists(querylist);
	}
	
}
