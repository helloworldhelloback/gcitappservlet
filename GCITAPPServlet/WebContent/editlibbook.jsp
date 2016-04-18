    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.LibraryBranch" %>
    <%@ page import="com.gcit.lms.entity.Book" %>
        <%@ page import="com.gcit.lms.entity.Book_Copies" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    <%Book_Copies bookcopies = null;
    if(request.getAttribute("bookcopies")!=null){
    	bookcopies = (Book_Copies)request.getAttribute("bookcopies");
    	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<h2>Welcome to GCIT Library Management System - Librarian</h2>
<button type="button" onclick="javascript:location.href='home.jsp'">Back To Main Page</button>
<br/>
<br/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
${result}
</head>
<body>
<form action="updateLibBook" method="post">		
		Book Title: <label for="title"><%=bookcopies.getTitle()%> <br />
		No Of Copies: <input type="text" name="noOfCopies" value ="<%=bookcopies.getNoOfCopies()%>"> <br />
		<input type="hidden" name="bookId" value=<%=bookcopies.getBookId() %>>
		<input type="hidden" name="branchId" value=<%=bookcopies.getBranchId() %>>
		<button type="submit">Update Book</button>
	</form>

</body>
</html>