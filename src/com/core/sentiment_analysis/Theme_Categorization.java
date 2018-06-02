package com.core.sentiment_analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.servlets.sentiment_analysis.Bar_Chart_Servlet;

public class Theme_Categorization 
{
	XSSFRow row;
	XSSFCell cell1,cell2,cell3,cell4,cell5;
	XSSFWorkbook wb,wb1;
	XSSFSheet sheet1, sheet2,sheet3,sheet4,sheet5,sheet6;
	FileInputStream inputfile;
	FileOutputStream outputfile;
	List<String> outcomes = new ArrayList<String>();
	List<String> staff = new ArrayList<String>();
	List<String> assesment = new ArrayList<String>();
	List<String>  course = new ArrayList<String>();
	List<String> support = new ArrayList<String>();
	int i, j;
	float outcomespercentage = 0.0f, staffpercentage = 0.0f,supportpercentage = 0.0f,assesmentpercentage = 0.0f,coursepercentage = 0.0f;
	String comment;
	
	List<String[]> categorycount = new ArrayList<String[]>();
	int outcomescount, staffcount, assesmentcount,coursecount, supportcount;
	Bar_Chart_Servlet bcs = new Bar_Chart_Servlet();
	public void compare_words_lists(List<String> querylist) throws IOException
	{
		inputfile = new FileInputStream(new File("C:/Users/Dell/Desktop/Theme-categories.xlsx"));
		outputfile = new FileOutputStream(new File("C:/Users/Dell/Desktop/Categories.xlsx"));
		wb = new XSSFWorkbook(inputfile);
		wb1 = new XSSFWorkbook();
		wb1.createSheet("Sheet1");
		sheet6 = wb1.getSheet("Sheet1");
		sheet1 = wb.getSheet("Sheet1");
		sheet2 = wb.getSheet("Sheet2");
		sheet3 = wb.getSheet("Sheet3");
		sheet4 = wb.getSheet("Sheet4");
		sheet5 = wb.getSheet("Sheet5");
		for(i = 0 ; i < sheet1.getPhysicalNumberOfRows() ; i++)
		{
			row = sheet1.getRow(i);
			cell1 = row.getCell(0);
			course.add(cell1.toString());
			
		}
		for(i = 0 ; i < sheet2.getPhysicalNumberOfRows() ; i++)
		{
			row = sheet2.getRow(i);
			cell2 = row.getCell(0);
			support.add(cell2.toString());
			
		}
		for(i = 0 ; i < sheet3.getPhysicalNumberOfRows() ; i++)
		{
			row = sheet3.getRow(i);
			cell3 = row.getCell(0);
			assesment.add(cell3.toString());
		}
		for(i = 0 ; i < sheet4.getPhysicalNumberOfRows() ; i++)
		{
			row = sheet4.getRow(i);
			cell4 = row.getCell(0);
			outcomes.add(cell4.toString());
		}
		for(i = 0 ; i < sheet5.getPhysicalNumberOfRows() ; i++)
		{
			row = sheet5.getRow(i);
			cell5 = row.getCell(0);
			staff.add(cell5.toString());
			
		}
		System.out.println("Cell1-: "+cell1+" Cell2-: "+cell2+" Cell3-: "+cell3+" Cell4-: "+cell4+" Cell5-:"+cell5);
		String[] category = new String[querylist.size()];
		String[] words = null;
		for(i = 0 ; i < querylist.size() ; i++)
		{
			
			category[i] = "Comment"+i;
			category[i].trim();
			comment = (String)querylist.get(i);
			words = comment.split(" ");
			supportcount = 0;coursecount = 0;assesmentcount = 0;staffcount = 0;outcomescount = 0;
			for(j = 0 ; j < outcomes.size() ; j++)
			{
				if(comment.contains(outcomes.get(j)))
				{
					outcomescount++;

					//System.out.println("Outcomes matching words");
					//System.out.println("Words-:"+outcomes.get(j));
				}
			}
			try
			{
				outcomespercentage = (float)outcomescount / (float)(words.length - outcomescount);
				System.out.println("Percentage-:"+(outcomespercentage)+" Outcomes count-:"+outcomescount+" Unmatchedd words-:"+(words.length-outcomescount));
			}
			catch (ArithmeticException e) 
			{
				// TODO: handle exception
				System.out.println("Percentage-:"+(outcomespercentage)+" Outcomes count-:"+outcomescount+" Unmatchedd words-:"+(words.length-outcomescount));
			}
			for(j = 0 ; j < staff.size() ; j++)
			{
				if(comment.contains(staff.get(j)))
				{
					staffcount++;
					/*System.out.println("Staff matching words");
					System.out.println("Words-:"+staff.get(j));*/
				}
			}
			try
			{
				staffpercentage = (float)staffcount / (float)(words.length - staffcount);
				System.out.println("Percentage-:"+(staffpercentage)+" Staff count-:"+staffcount+" Unmatchedd words-:"+(words.length-staffcount));
			}
			catch (ArithmeticException e) 
			{
				// TODO: handle exception
				System.out.println("Percentage-:"+(staffpercentage)+" Staff count-:"+staffcount+" Unmatchedd words-:"+(words.length-staffcount));
			}
			//System.out.println("Staff Count-:"+staffcount+" Staff Size-:"+staff.size());
			
			for(j = 0 ; j < assesment.size() ; j++)
			{
				if(comment.contains(assesment.get(j)))
				{
					assesmentcount++;
					
					/*System.out.println("Assesment matching words");
					System.out.println("Words-:"+assesment.get(j));*/
				}
			}
			try
			{
				assesmentpercentage = (float)assesmentcount / (float)(words.length - assesmentcount);
				System.out.println("Percentage-:"+(assesmentpercentage)+" Assesment count-:"+assesmentcount+" Unmatchedd words-:"+(words.length-assesmentcount));
			}
			catch (ArithmeticException e) 
			{
				// TODO: handle exception
				System.out.println("Percentage-:"+(assesmentpercentage)+" Assesment count-:"+assesmentcount+" Unmatchedd words-:"+(words.length-assesmentcount));
			}
			//System.out.println("Assesment Count-:"+assesmentcount+" Assesment Size-:"+assesment.size());
			
			for(j = 0 ; j < course.size() ; j++)
			{
				if(comment.contains(course.get(j)))
				{
					coursecount++;
					/*System.out.println("Course matching words");
					System.out.println("Words-:"+course.get(j));*/
				}
			}
			try
			{
				coursepercentage = (float)coursecount / (float)(words.length - coursecount);
				System.out.println("Percentage-:"+(coursepercentage)+" Course count-:"+coursecount+" Unmatchedd words-:"+(words.length-coursecount));
			}
			catch (ArithmeticException e) 
			{
				// TODO: handle exception
				System.out.println("Percentage-:"+(coursepercentage)+" Course count-:"+coursecount+" Unmatchedd words-:"+(words.length-coursecount));
			}
			//System.out.println("Course Count-:"+coursecount+" Course Size-:"+course.size());
			
			for(j = 0 ; j < support.size() ; j++)
			{
				if(comment.contains(support.get(j)))
				{
					supportcount++;
					/*System.out.println("Support matching words");
					System.out.println("Words-:"+support.get(j));*/
				}
			}
			try
			{
				supportpercentage = (float)supportcount / (float)(words.length - supportcount);
				System.out.println("Percentage-:"+(supportpercentage)+" Course count-:"+supportcount+" Unmatchedd words-:"+(words.length-supportcount));
			}
			catch (ArithmeticException e) 
			{
				// TODO: handle exception
				System.out.println("Percentage-:"+(supportpercentage)+" Course count-:"+supportcount+" Unmatchedd words-:"+(words.length-supportcount));
			}
			//System.out.println("Support Count-:"+supportcount+" Support Size-:"+support.size());
			
			//if(outcomescount > (staffcount | assesmentcount | supportcount | coursecount))
			if(outcomespercentage > 0.30)
			{
				if(category[i].equals(null))
				{
					category[i] = category[i]+ "/Outcomes";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
				else
				{
					category[i] = category[i]+"/Outcomes";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
			}
			if(staffpercentage > 0.30)//if(staffcount > (outcomescount | assesmentcount | supportcount | coursecount))
			{
				if(category[i].equals(null))
				{
					category[i] = category[i]+"/Staff";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
				else
				{
					category[i] = category[i]+"/Staff";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
			}
			if(assesmentpercentage > 0.30)//if(assesmentcount > (outcomescount | staffcount | supportcount | coursecount)) 
				
			{
				if(category[i].equals(null))
				{
					category[i] = category[i]+"/Assesment";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
				else
				{
					category[i] = category[i]+"/Assesment";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
			}
			if(supportpercentage > 0.30)//if(supportcount > (outcomescount | staffcount | assesmentcount | coursecount)) 
				
			{
				if(category[i].equals(null))
				{
					category[i] = category[i]+"/Support";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
				else
				{
					category[i] = category[i]+"/Support";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
			}
			if(coursepercentage > 0.30)//if(coursecount > (outcomescount | staffcount | assesmentcount | supportcount))
				
			{
				if(category[i].equals(null))
				{
					category[i] = category[i]+"/Course";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
				else
				{
					category[i] = category[i]+"/Course";
					//System.out.println("Comment No-:"+i+" Category-:"+category[i]);
				}
								
			}
			//for(int i = 0 ; i<category.length;i++)
			{
				row = sheet6.createRow(i);
				cell1 = row.createCell(0);
				cell2 = row.createCell(1);
				
				cell1.setCellValue(querylist.get(i));
				cell2.setCellValue(category[i]);

				System.out.println("Category-:"+category[i]);
			}
			//categorycount.add(category[i]);
			//System.out.println("Category Count-:"+categorycount.size());
			
		}
		wb1.write(outputfile);
		wb1.close();
		outputfile.close();
		//bcs.set_variables(category);
	}
}
