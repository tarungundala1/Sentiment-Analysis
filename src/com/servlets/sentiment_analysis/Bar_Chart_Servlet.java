package com.servlets.sentiment_analysis;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ddf.EscherColorRef.SysIndexProcedure;

import com.core.sentiment_analysis.BarChart;

/**
 * Servlet implementation class Bar_Chart_Servlet1
 */
@WebServlet("/Bar_Chart_Servlet")
public class Bar_Chart_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	static int outcomecount, staffcount, assesmentcount,supportcount, coursecount;
    public Bar_Chart_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("IN dopost");
		BarChart bc = new BarChart("Chart for Comments", "");
		System.out.println("Outcomes-:"+outcomecount+"Course"+coursecount);
		bc.setvariable(outcomecount,staffcount,assesmentcount,supportcount,coursecount);
		bc.call_main();
	}
	public void set_variables(List<String> categorycount) {
		// TODO Auto-generated method stub
		String category;
		
		for(int i = 1 ; i < categorycount.size() ; i++)
		{
			category = categorycount.get(i);
			System.out.println("Category"+category);
			if(category.equalsIgnoreCase("outcomes"))
			{
				outcomecount++;
			}
			else if(category.equalsIgnoreCase("support"))
			{
				supportcount++;
			}
			else if(category.equalsIgnoreCase("staff"))
			{
				staffcount++;
			}
			else if(category.equalsIgnoreCase("assesment"))
			{
				assesmentcount++;
			}
			else if(category.equalsIgnoreCase("course"))
			{
				coursecount++;
			}
		}
		
	}
}
