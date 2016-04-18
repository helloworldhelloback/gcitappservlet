<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcit.lms.entity.Genre" %>
<%@ page import="com.gcit.lms.service.AdministratorService" %>
<% 
	AdministratorService service = new AdministratorService();
	List<Genre> genres = service.getAllGenres();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function deleteGenre(genreId){
	$.ajax({
		  url: "deleteGenre",
		  data:{
			  genreId: genreId
		  }
		}).done(function(data) {
		  $('#genresTable').html(data);
		});
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System - Admin</h2>
${result}
</head>
<body>
<table border="2" id="genresTable">
	<tr>
		<th>Genre Name</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>	
	<% if (genres.isEmpty()) %>
	<%for (Genre g: genres){ %>
	<tr>
	<td><% out.println(g.getGenreName());%></td>
	<td><button type="button" onclick="javascript:location.href='editGenre?genreId=<%=g.getGenreId() %>'">EDIT</button>
	<td><button type="button" onclick="deleteGenre(<%=g.getGenreId() %>)">DELETE</button>
	</tr>
	<%} %>
</table>

</body>
</html>