<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.gcit.lms.entity.Genre" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    <%Genre genre = null;
    if(request.getAttribute("genre")!=null){
    	genre = (Genre)request.getAttribute("genre");
    	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System - Admin</h2>
<button type="button" onclick="javascript:location.href='administrator.html'">Back To Admin Page</button>
<br/>
<br/>
<h3>Edit Genre Details Below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

${result}
</head>
<body>
	<form action="updateGenre" method="post">		
		Genre: <input type="text" name="genreName" value ="<%=genre.getGenreName()%>"> <br />
		<input type="hidden" name="genreId" value=<%=genre.getGenreId() %>>
		<button type="submit">Edit Genre</button>
	</form>
</body>
</html>