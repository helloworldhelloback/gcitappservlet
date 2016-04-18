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
import com.gcit.lms.service.AdministratorService;

/**
 * Servlet implementation class AdminServlet
 */
//
@WebServlet({ "/addAuthor", "/viewAuthor", "/addBook", "/editAuthor", "/deleteAuthor",
	"/addPublisher","/editPublisher","/deletePublisher", "/getAllPublishers","/updatePublisher",
	"/addGenre","/editGenre","/deleteGenre", "/getAllGenres","/updateGenre",
	"/addLibraryBranch","/editLibraryBranch","/deleteLibraryBranch", "/getAllLibraryBranches","/updateLibraryBranch"
	})

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
		case "/editAuthor":
			editAuthor(request, response);
			break;			
		case "/deleteAuthor":
			deleteAuthor(request, response);
			break;
		case "/editPublisher":
			editPublisher(request, response);
			break;			
		case "/deletePublisher":
			deletePublisher(request, response);
			break;
		case "/editGenre":
			editGenre(request, response);
			break;			
		case "/deleteGenre":
			deleteGenre(request, response);
			break;
		case "/editLibraryBranch":
			editLibraryBranch(request, response);
			break;			
		case "/deleteLibraryBranch":
			deleteLibraryBranch(request, response);
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
		case "/addAuthor":
			addAuthor(request, response);
			break;
		case "/updateAuthor":
			updateAuthor(request, response);
			break;
		case "/addPublisher":
			addPublisher(request, response);
			break;
		case "/updatePublisher":
			updatePublisher(request, response);
			break;
		case "/addGenre":
			addGenre(request, response);
			break;
		case "/updateGenre":
			updateGenre(request, response);
			break;
		case "/addLibraryBranch":
			addLibraryBranch(request, response);
			break;
		case "/updateLibraryBranch":
			updateLibraryBranch(request, response);
			break;
		default:
			break;
		}
	}
//////////////////////////////////////Author//////////////////////////////////////////////////////////////////
	private void addAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Author Post");
		String authorName = request.getParameter("authorName");
		String[] bookIdArray =  request.getParameterValues("bookId");
		String addAuthorResult = "";
		for(String b : bookIdArray)
		System.out.println("your book id is="+b);
		if (authorName != null && authorName.length() > 3 && authorName.length() < 45) {
			Author a = new Author();
			a.setAuthorName(authorName);
			try {
				if(bookIdArray!=null && bookIdArray.length>0)
					if(bookIdArray[0] =="-1")
						service.createAuthor(a);
					else
						service.createAuthorWithBookAssociation(a, bookIdArray);
				returnPath = "/viewauthors.jsp";
				addAuthorResult = "Author added sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/addauthor.jsp";
				addAuthorResult = "Author add failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/addauthor.jsp";
			addAuthorResult = "Author Name cannot be empty or more than 45 chars in length or less than 3 characters";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);		
		rd.forward(request, response);
	}
	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) {
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deleteAuthor(authorId);
			List<Author> authors = service.getAllAuthors();
			
			str.append("<tr><th>Author Name</th><th>Book Title</th><th>Edit</th><th>Delete</th></tr>");
			for(Author a: authors){
				//str.append("<tr><td>"+a.getAuthorName()+"</td><td>Book Name</td>");
				str.append("<tr><td>"+a.getAuthorName()+"</td><td>");
				if(a.getBooks()!=null && a.getBooks().size() >0){
					for(Book b: a.getBooks()){
						str.append(b.getTitle());
						str.append(", ");
					}
				}	 
				str.append("</td>");
				str.append("<td><button type='button' onclick=\"javascript:location.href='editAuthor?authorId="+a.getAuthorId()+"'\">EDIT</button><td>"									 
						+ "<button type='button' onclick=\"deleteAuthor("+a.getAuthorId()+")\">DELETE</button></tr>)");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().append(str.toString());
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void editAuthor(HttpServletRequest request, HttpServletResponse response){
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		System.out.println("Author ID:" +authorId);
		AdministratorService service = new AdministratorService();
		Author author = null;
		try {
			author = service.getAuthorByID(authorId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("author", author);
		RequestDispatcher rd = request.getRequestDispatcher("/editauthor.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void updateAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Author Edit Post");
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		String authorName = request.getParameter("authorName");
		String addAuthorResult = "";
		if (authorName != null && authorName.length() > 3 && authorName.length() < 45) {
			Author a = new Author();
			a.setAuthorId(authorId);
			a.setAuthorName(authorName);
			try {
				service.updateAuthor(a);
				returnPath = "/viewauthors.jsp";
				addAuthorResult = "Author updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/editauthor.jsp";
				addAuthorResult = "Author update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/editauthor.jsp";
			addAuthorResult = "Author Name cannot be empty or more than 45 chars in length or less than 3 characters";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);		
		rd.forward(request, response);
	}
//////////////////////////////////////Publisher//////////////////////////////////////////////////////////////////
	private void addPublisher(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Publisher Post");
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		String addPublisherResult = "";
		System.out.println("publisher hello");
		if (publisherName != null && publisherName.length() > 3 && publisherName.length() < 45) {
			Publisher p = new Publisher();
			p.setPublisherName(publisherName);
			p.setPublisherAddress(publisherAddress);
			p.setPublisherPhone(publisherPhone);
			try {
				service.createPublisher(p);
				returnPath = "/viewpublishers.jsp";
				addPublisherResult = "Publisher added sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/addpublisher.jsp";
				addPublisherResult = "Publisher add failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/addpublisher.jsp";
			addPublisherResult = "Publisher Name cannot be empty or more than 45 chars in length or less than 3 characters";
			System.out.println("publisher else="+addPublisherResult);
		}
		System.out.println("publisher result="+addPublisherResult);
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addPublisherResult);		
		rd.forward(request, response);
	}
	private void editPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		System.out.println("Publisher ID:" +publisherId);
		AdministratorService service = new AdministratorService();
		Publisher publisher = null;
		try {
			publisher = service.getPublisherByID(publisherId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("publisher", publisher);
		RequestDispatcher rd = request.getRequestDispatcher("/editpublisher.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void updatePublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Publisher Edit Post");
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		String addPublisherResult = "";
		if (publisherName != null && publisherName.length() > 3 && publisherName.length() < 45) {
			Publisher p = new Publisher();
			p.setPublisherId(publisherId);
			p.setPublisherName(publisherName);
			p.setPublisherAddress(publisherAddress);
			p.setPublisherPhone(publisherPhone);
			try {
				service.updatePublisher(p);
				returnPath = "/viewpublishers.jsp";
				addPublisherResult = "Publisher updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/editpublisher.jsp";
				addPublisherResult = "Publisher update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/editpublisher.jsp";
			addPublisherResult = "Publisher Name cannot be empty or more than 45 chars in length or less than 3 characters";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addPublisherResult);		
		rd.forward(request, response);
	}
	private void deletePublisher(HttpServletRequest request, HttpServletResponse response) {
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deletePublisher(publisherId);
			List<Publisher> publishers = service.getAllPublishers();
			
			str.append("<tr><th>Publisher Name</th><th>Book Title</th><th>Edit</th><th>Delete</th></tr>");
			for(Publisher p: publishers){
				str.append("<tr><td>"+p.getPublisherName()+"</td>");				
				str.append("<td>"+p.getPublisherAddress()+"</td>");
				str.append("<td>"+p.getPublisherPhone()+"</td>");
				str.append("<td><button type='button' onclick=\"javascript:location.href='editPublisher?publisherId="+p.getPublisherId()+"'\">EDIT</button><td>"									 
						+ "<button type='button' onclick=\"deletePublisher("+p.getPublisherId()+")\">DELETE</button></tr>)");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().append(str.toString());
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}		
	////////////////////////////////////////////Genre//////////////////////////////////////////////////////////////////
	private void addGenre(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Genre Post");
		String genreName = request.getParameter("genreName");
		String addGenreResult = "";
		System.out.println("genre hello");
		if (genreName != null && genreName.length() > 3 && genreName.length() < 45) {
			Genre p = new Genre();
			p.setGenreName(genreName);
			try {
				service.createGenre(p);
				returnPath = "/viewgenres.jsp";
				addGenreResult = "Genre added sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/addgenre.jsp";
				addGenreResult = "Genre add failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/addgenre.jsp";
			addGenreResult = "Genre Name cannot be empty or more than 45 chars in length or less than 3 characters";
			System.out.println("genre else="+addGenreResult);
		}
		System.out.println("genre result="+addGenreResult);
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addGenreResult);		
		rd.forward(request, response);
	}
	private void editGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		Integer genreId = Integer.parseInt(request.getParameter("genreId"));
		System.out.println("Genre ID:" +genreId);
		AdministratorService service = new AdministratorService();
		Genre genre = null;
		try {
			genre = service.getGenreByID(genreId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("genre", genre);
		RequestDispatcher rd = request.getRequestDispatcher("/editgenre.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void updateGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Genre Edit Post");
		Integer genreId = Integer.parseInt(request.getParameter("genreId"));
		String genreName = request.getParameter("genreName");
		String addGenreResult = "";
		if (genreName != null && genreName.length() > 3 && genreName.length() < 45) {
			Genre g = new Genre();
			g.setGenreId(genreId);
			g.setGenreName(genreName);
			try {
				service.updateGenre(g);
				returnPath = "/viewgenres.jsp";
				addGenreResult = "Genre updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/editgenre.jsp";
				addGenreResult = "Genre update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/editgenre.jsp";
			addGenreResult = "Genre Name cannot be empty or more than 45 chars in length or less than 3 characters";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addGenreResult);		
		rd.forward(request, response);
	}
	private void deleteGenre(HttpServletRequest request, HttpServletResponse response) {
		Integer genreId = Integer.parseInt(request.getParameter("genreId"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deleteGenre(genreId);
			List<Genre> genres = service.getAllGenres();
			
			str.append("<tr><th>Genre Name</th><th>Edit</th><th>Delete</th></tr>");
			for(Genre p: genres){
				str.append("<tr><td>"+p.getGenreName()+"</td>");
				str.append("<td><button type='button' onclick=\"javascript:location.href='editGenre?genreId="+p.getGenreId()+"'\">EDIT</button><td>"									 
						+ "<button type='button' onclick=\"deleteGenre("+p.getGenreId()+")\">DELETE</button></tr>)");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().append(str.toString());
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	///////////////////////////////////////////Library Branch///////////////////////////////////////////////////////
	private void addLibraryBranch(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("LibraryBranch Post");
		String librarybranchName = request.getParameter("librarybranchName");
		String librarybranchAddress = request.getParameter("librarybranchAddress");
		String addLibraryBranchResult = "";
		System.out.println("librarybranch hello");
		if (librarybranchName != null && librarybranchName.length() > 3 && librarybranchName.length() < 45) {
			LibraryBranch l= new LibraryBranch();
			l.setLibrarybranchName(librarybranchName);
			l.setLibrarybranchAddress(librarybranchAddress);
			try {
				service.createLibraryBranch(l);
				returnPath = "/viewlibrarybranches.jsp";
				addLibraryBranchResult = "LibraryBranch added sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/addlibrarybranch.jsp";
				addLibraryBranchResult = "LibraryBranch add failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/addlibrarybranch.jsp";
			addLibraryBranchResult = "LibraryBranch Name cannot be empty or more than 45 chars in length or less than 3 characters";
			System.out.println("librarybranch else="+addLibraryBranchResult);
		}
		System.out.println("librarybranch result="+addLibraryBranchResult);
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addLibraryBranchResult);		
		rd.forward(request, response);
	}
	private void editLibraryBranch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
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
		RequestDispatcher rd = request.getRequestDispatcher("/editlibrarybranch.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void updateLibraryBranch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("LibraryBranch Edit Post");
		Integer librarybranchId = Integer.parseInt(request.getParameter("librarybranchId"));
		String librarybranchName = request.getParameter("librarybranchName");
		String librarybranchAddress = request.getParameter("librarybranchAddress");
		String addLibraryBranchResult = "";
		if (librarybranchName != null && librarybranchName.length() > 3 && librarybranchName.length() < 45) {
			LibraryBranch p = new LibraryBranch();
			p.setLibrarybranchId(librarybranchId);
			p.setLibrarybranchName(librarybranchName);
			p.setLibrarybranchAddress(librarybranchAddress);

			try {
				service.updateLibraryBranch(p);
				returnPath = "/viewlibrarybranches.jsp";
				addLibraryBranchResult = "LibraryBranch updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/editlibrarybranch.jsp";
				addLibraryBranchResult = "LibraryBranch update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/editlibrarybranch.jsp";
			addLibraryBranchResult = "LibraryBranch Name cannot be empty or more than 45 chars in length or less than 3 characters";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addLibraryBranchResult);		
		rd.forward(request, response);
	}
	private void deleteLibraryBranch(HttpServletRequest request, HttpServletResponse response) {
		Integer librarybranchId = Integer.parseInt(request.getParameter("librarybranchId"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deleteLibraryBranch(librarybranchId);
			List<LibraryBranch> librarybranches = service.getAllLibraryBranches();
			
			str.append("<tr><th>Branch Name</th><th>Branch Address</th><th>Edit</th><th>Delete</th></tr>");
			for(LibraryBranch l: librarybranches){
				str.append("<tr><td>"+l.getLibrarybranchName()+"</td>");				
				str.append("<td>"+l.getLibrarybranchAddress()+"</td>");
				str.append("<td><button type='button' onclick=\"javascript:location.href='editLibraryBranch?librarybranchId="+l.getLibrarybranchId()+"'\">EDIT</button><td>"									 
						+ "<button type='button' onclick=\"deleteLibraryBranch("+l.getLibrarybranchId()+")\">DELETE</button></tr>)");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().append(str.toString());
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
}