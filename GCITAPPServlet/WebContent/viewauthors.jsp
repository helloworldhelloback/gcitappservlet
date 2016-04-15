<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.Author" %>
    <%@ page import="com.gcit.lms.entity.Book" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    <% 
    	AdministratorService service = new AdministratorService();
    	List<Author> authors = service.getAllAuthors();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System - Admin</h2>
<body>
<table>
	<tr>
		<th>Author Name</th>
		<th>Book Title</th>
	</tr>
	
		<%for (Author a: authors){ %>
		<tr>
		<td><% out.println(a.getAuthorName()); %></td>
		<td><%if(a.getBooks()!=null && a.getBooks().size() >0){
			for(Book b: a.getBooks()){
				out.println(b.getTitle());
				out.println(", ");
			}
		}	
		%></td>
		</tr>
		<%} %>
		
	

</table>

</body>
</html>