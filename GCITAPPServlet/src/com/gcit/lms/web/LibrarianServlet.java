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

import com.gcit.lms.entity.Book_Copies;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.service.AdministratorService;
import com.gcit.lms.service.LibrarianService;

@WebServlet({ "/selectLibBranch", "/editLibBook", "viewLibBooks"})
public class LibrarianServlet extends HttpServlet {

	private static final long serialVersionUID = -6688300013004311686L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		
		case "/editLibBook":
			editLibBook(request, response);
			break;	
		case "/viewLibBooks":
			viewLibBooks(request, response);
			break;	
			
			
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
		case "/selectLibBranch":
			selectLibBranch(request, response);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////Select Branch////////////////////////////////////////////////
	
	private void selectLibBranch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
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
	}
		
		
	//////////////////////////////////////////////////////////////////////////////////////////
	private void editLibBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		System.out.println("LibraryBranch ID:" +bookId);
		System.out.println("LibraryBranch ID:" +branchId);
		LibrarianService service = new LibrarianService();
		Book_Copies bookcopies = null;
		
		try {
			bookcopies = service.getBookCopyByID(bookId, branchId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("bookcopies", bookcopies);
		RequestDispatcher rd = request.getRequestDispatcher("/editlibbook.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	////////////////////////////////////////////////////////////////////////////////////////////
	private void viewLibBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
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
		RequestDispatcher rd = request.getRequestDispatcher("/viewlibbooks.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
