package com.servlets.sentiment_analysis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.sentiment_analysis.Chart_Example;

/**
 * Servlet implementation class Bar_Chart_Servlet
 */
@WebServlet("/Scatter_Plot_Servlet")
public class Scatter_Plot_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Chart_Example charts = new Chart_Example("Bar Chart");
		charts.call_from_bar_chart();
	}
}
