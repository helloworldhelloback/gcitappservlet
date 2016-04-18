<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcit.lms.entity.LibraryBranch" %>
<%@ page import="com.gcit.lms.service.AdministratorService" %>
<% 
	AdministratorService service = new AdministratorService();
	List<LibraryBranch> librarybranches = service.getAllLibraryBranches();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System</h2>
<h3>Please choose your role:</h3>
</head>
<body>
<a href="administrator.html">Administrator</a>
<br/>
	<form action="selectLibBranch" method="post">
		<select name="librarybranchId">
			<%for(LibraryBranch l: librarybranches){ %>
				<option value="<%=l.getLibrarybranchId()%>"><%=l.getLibrarybranchName() %></option>
			<%} %>
		</select>
		<button type="submit">Librarian</button>
	</form>
<br/>
<a href="borrower.html">Borrower</a>
<br/>
</body>
</html>