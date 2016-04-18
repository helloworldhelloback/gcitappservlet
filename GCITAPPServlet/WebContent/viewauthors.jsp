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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function deleteAuthor(authorId){
	$.ajax({
		  url: "deleteAuthor",
		  data:{
			  authorId: authorId
		  }
		}).done(function(data) {
		  $('#authorsTable').html(data);
		});
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System - Admin</h2>
<button type="button" onclick="javascript:location.href='administrator.html'">Back To Admin Page</button>
<br/>
<br/>
${result}
<body>

<table border="2" id="authorsTable">
	<tr>
		<th>Author Name</th>
		<th>Book Title</th>
		<th>Edit</th>
		<th>Delete</th>
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
	<td><button type="button" onclick="javascript:location.href='editAuthor?authorId=<%=a.getAuthorId() %>'">EDIT</button>
	<td><button type="button" onclick="deleteAuthor(<%=a.getAuthorId() %>)">DELETE</button>
	</tr>
	<%} %>
</table>


</body>
</html>