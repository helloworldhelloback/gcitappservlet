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
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdministratorService;
import com.gcit.lms.service.LibrarianService;

@WebServlet({ "/selectLibBranch", "/editLibBook", "/viewLibBooks", "/updateLibBook"})
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
		case "/updateLibBook":
			updateLibBook(request, response);
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
		//System.out.println("Book ID:" +request);
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		System.out.println("Book ID:" +bookId);
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));		
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
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	private void updateLibBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LibrarianService service = new LibrarianService();
		AdministratorService service2 = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("update lib Edit Post");
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));		
		Integer noOfCopies = Integer.parseInt(request.getParameter("noOfCopies"));
		//String title = request.getParameter("title");
		LibraryBranch librarybranch = null;
		String addBookCopiesResult = "";
		if (noOfCopies != null && noOfCopies >= 0 ) {
			Book_Copies bc = new Book_Copies();
			bc.setBookId(bookId);
			bc.setBranchId(branchId);
			bc.setNoOfCopies(noOfCopies);
			//bc.setTitle(title);
			try {
				service.updateBookCopy(bc);
				librarybranch = service2.getLibraryBranchByID(branchId);
				returnPath = "/viewlibbooks.jsp";
				addBookCopiesResult = "Book Copy updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/editlibbook.jsp";
				addBookCopiesResult = "Book Copy update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/editlibbook.jsp";
			addBookCopiesResult = "Number of copies cannot be empty or more less than 0";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("librarybranch", librarybranch);
		request.setAttribute("result", addBookCopiesResult);		
		rd.forward(request, response);
	}
}
