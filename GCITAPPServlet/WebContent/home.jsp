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

<form action="administrator.html" method="post">		
		<button type="submit">Administrator</button>
</form>
<br/>
	<form action="selectLibBranch" method="post">
		<button type="submit">Librarian</button>
		<select name="librarybranchId">
			<%for(LibraryBranch l: librarybranches){ %>
				<option value="<%=l.getLibrarybranchId()%>"><%=l.getLibrarybranchName() %></option>
			<%} %>
		</select>		
	</form>
<br/>
<form action="borrower" method="post">
	<button type="submit">Borrower</button>
	<select name="librarybranchId">
			<%for(LibraryBranch l: librarybranches){ %>
				<option value="<%=l.getLibrarybranchId()%>"><%=l.getLibrarybranchName() %></option>
			<%} %>
		</select>
	CardNo: <input type="text" name="cardNo">		
</form>
<br/>
</body>
</html>