package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.*;
import com.gcit.lms.service.*;

@WebServlet({"/borrower","/checkoutBook"})
public class BorrowerServlet extends HttpServlet{

	private static final long serialVersionUID = 7066535929376629325L;
	
	public BorrowerServlet(){
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		
		/*
		case "/viewLibBooks":
			viewLibBooks(request, response);
			break;	
			*/
			
		default:
			break;
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		case "/checkoutBook":
			checkoutBook(request, response);
			break;			
		case "/borrower":
			borrower(request, response);
			break;	
		default:
			break;
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	private void borrower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		Integer librarybranchId = Integer.parseInt(request.getParameter("librarybranchId"));
		System.out.println("LibraryBranch ID:" +librarybranchId);
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		System.out.println("CardNo:" +cardNo);
		BorrowerService service = new BorrowerService();
		List<Book_Loan> bookloans=null;		
		try {
				bookloans= service.getAllBookCopiesByBranchId(librarybranchId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println("bookloans:"+bookloans.get(0).getBookId());
		System.out.println("bookloans:"+bookloans.get(0).getCardNo());
		request.setAttribute("bookloans", bookloans);
		request.setAttribute("cardNo", cardNo);
		RequestDispatcher rd = request.getRequestDispatcher("/borrower.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/////////////////////////////////////Checkout Book//////////////////////////////////////////////////////
	private void checkoutBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		/*
		Integer librarybranchId = Integer.parseInt(request.getParameter("librarybranchId"));
		System.out.println("LibraryBranch ID:" +librarybranchId);
		AdministratorService service = new AdministratorService();
		LibraryBranch librarybranch = null;
		
		try {
			librarybranch = service.getLibraryBranchByID(librarybranchId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("librarybranch", librarybranch);
		RequestDispatcher rd = request.getRequestDispatcher("/librarian.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/		
	}
	
}
