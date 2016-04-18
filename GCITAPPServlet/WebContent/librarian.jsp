
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.LibraryBranch" %>
    <%@ page import="com.gcit.lms.entity.Book" %>
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
<h2>Welcome to GCIT Library Management System - Librarian</h2>
<button type="button" onclick="javascript:location.href='home.jsp'">Back To Main Page</button>
<br/>
<br/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
${result}
</head>
<body>
<button type="button" onclick="javascript:location.href='editLibraryBranch?librarybranchId=<%=librarybranch.getLibrarybranchId() %>'">EDIT Library Branch</button>
<button type="button" onclick="javascript:location.href='viewLibBooks?librarybranchId=<%=librarybranch.getLibrarybranchId() %>'">EDIT Books</button>
	

</body>
</html>