package com.core.sentiment_analysis;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Sentiment_analysis {

    
    static int positive = 0;
    static int negative = 0;
    static InputStream inputfile;
    static XSSFWorkbook wb ;
	static XSSFSheet sheet ;
	static XSSFRow row  ;
	static XSSFCell cell ;
	static int neutral;
	
	public Sentiment_analysis(FileInputStream file) {
		// TODO Auto-generated constructor stub
		inputfile = file;
	}
	/*public static void main(String[] args) throws IOException,InvalidFormatException {
       
    	Sentiment_analysis sa = new Sentiment_analysis();
    	sa.sentiment_analyser();
        
    }*/
	
public void sentiment_analyser() throws IOException, InvalidFormatException 
{
	
	//String line = "";
    Train_model modeltrainer = new Train_model();
    modeltrainer.trainModel();
    //inputfile= new FileInputStream(new File("C:/Users/Dell/Desktop/comments.xlsm"));
    wb = new XSSFWorkbook(inputfile);
	sheet = wb.getSheet("comments");
    String query = null;
    Resource_Dictionary rd = new Resource_Dictionary();
    int result1;
    try
    {
    	
    	for(int i =0 ; i< sheet.getPhysicalNumberOfRows();i++)
    	{
    		row = sheet.getRow(i);
    		cell = row.getCell(0);
    		query = cell.toString();
    		result1 = modeltrainer.classifyNewTweet(query);
                if (result1 == 0) 
                {
                	negative++;
                } 
                else if(result1 == 1) 
                {
                	positive++;
                }
                else
                {
                	neutral++;
                }
                rd.resource_words(query, result1);
    	}
    	 rd.write_to_file(positive,negative,neutral);
    	 rd.categorize_comments();
    	 rd.barchart(); 
    }
    catch (FileNotFoundException e) {
		// TODO: handle exception
    	e.printStackTrace();
	}
    
     
}
}