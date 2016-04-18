<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.gcit.lms.entity.LibraryBranch" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    <%LibraryBranch librarybranch = null;
    if(request.getAttribute("librarybranch")!=null){
    	librarybranch = (LibraryBranch)request.getAttribute("librarybranch");
    	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System - Admin</h2>
<h3>Edit Library Branch Details Below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
${result}

</head>
<body>
	<form action="updateLibraryBranch" method="post">		
		Branch Name: <input type="text" name="librarybranchName" value ="<%=librarybranch.getLibrarybranchName()%>"> <br />
		Branch Address: <input type="text" name="librarybranchAddress" value ="<%=librarybranch.getLibrarybranchAddress()%>"> <br />

		<input type="hidden" name="librarybranchId" value=<%=librarybranch.getLibrarybranchId() %>>
		<button type="submit">Update Branch</button>
	</form>
</body>
</html>