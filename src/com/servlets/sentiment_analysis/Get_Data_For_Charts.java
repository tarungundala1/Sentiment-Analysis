package com.servlets.sentiment_analysis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.sentiment_analysis.PieChart;



/**
 * Servlet implementation class Get_Data_For_Charts
 */
@WebServlet("/Get_Data_For_Charts")
public class Get_Data_For_Charts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	static double positive, negative;
    public Get_Data_For_Charts() {
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
		PieChart pc = new PieChart("Comments");
		pc.set_variables(positive, negative);
		System.out.println("Get data charts");
		pc.main1();
		
	}
	public void set_variables(double positive, double negative)
	{
		Get_Data_For_Charts.positive = positive;
		Get_Data_For_Charts.negative = negative;
		System.out.println(Get_Data_For_Charts.positive+" "+Get_Data_For_Charts.negative);
	}

}
