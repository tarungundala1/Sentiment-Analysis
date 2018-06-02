package com.servlets.sentiment_analysis;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.core.sentiment_analysis.Get_File_Path;
import com.core.sentiment_analysis.Sentiment_analysis;


/**
 * Servlet implementation class Get_Data_File
 */
@WebServlet("/Get_Data_File")
public class Get_Data_File extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Get_Data_File() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Get_File_Path filepath = new Get_File_Path();
		String path = filepath.get_file_path();
		try
		{
		FileInputStream inputfile = new FileInputStream(path);
		Sentiment_analysis sentiment_analysis = new Sentiment_analysis(inputfile);
		
			sentiment_analysis.sentiment_analyser();
			System.gc();
			response.sendRedirect("Page3.html");
		} catch (InvalidFormatException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
