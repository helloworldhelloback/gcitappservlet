<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.LibraryBranch" %>
    <%@ page import="com.gcit.lms.entity.Book" %>
    <%@ page import="com.gcit.lms.entity.Author" %>
    <%@ page import="com.gcit.lms.entity.Book_Copies" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    <%LibraryBranch librarybranch = null;
    if(request.getAttribute("librarybranch")!=null){
    	librarybranch = (LibraryBranch)request.getAttribute("librarybranch");
    	}%>
    <% 
    List<Book_Copies> bookcopies=null;
    if(librarybranch!=null){
    	LibrarianService service = new LibrarianService();
    	bookcopies = service.getAllBooksCopiesByBranchId(librarybranch.getLibrarybranchId());
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<button type="button" onclick="javascript:location.href='home.jsp'">Back To Main Page</button>
<br/>
<br/>
</head>
<body>
<table border="2" id="authorsTable">
	<tr>
		<th>Book Title</th>
		<th>Book Copies</th>
		<th>Edit</th>

	</tr>	
	<%for (Book_Copies bc: bookcopies){ %>
	<tr>
	<td><% out.println(bc.getTitle()); %></td>	
	<td><% out.println(bc.getNoOfCopies()); %></td>
	
	<td><button type="button" onclick="javascript:location.href='editLibBook?bookId=<%=bc.getBookId() %>&branchId=<%= bc.getBranchId() %>'">EDIT Books</button></td>
		
	
	</tr>
	<%} %>
</table>
</body>
</html>