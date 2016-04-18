<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcit.lms.entity.Author" %>
<%@ page import="com.gcit.lms.entity.Book" %>
<%@ page import="com.gcit.lms.service.AdministratorService" %>
<% 
	AdministratorService service = new AdministratorService();
	List<Book> books = service.getAllBooks();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System - Admin</h2>
<button type="button" onclick="javascript:location.href='administrator.html'">Back To Admin Page</button>
<br/>
<br/>
<h3>Enter Author Details Below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

${result}
</head>
<body>
	<form action="addAuthor" method="post">
		Author Name: <input type="text" name="authorName"> <br />
		Associate author to books:<br/>
		<select name="bookId" multiple>
			<option value="-1">No Book</option>
			<%for(Book b: books){ %>
			<option value="<%=b.getBookId()%>"><%=b.getTitle() %></option>
			<%} %>
		</select>
		<br/>
		<button type="submit">Add Author</button>
	</form>
</body>
</html>