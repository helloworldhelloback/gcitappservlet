
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcit.lms.entity.Author" %>
<%@ page import="com.gcit.lms.entity.Book" %>
<%@ page import="com.gcit.lms.entity.LibraryBranch" %>
<%@page import="com.gcit.lms.entity.Book_Loan"%>
<%@ page import="com.gcit.lms.service.BorrowerService" %>

<%int cardNo  = (int)request.getAttribute("cardNo");%>
<% 
List<Book_Loan> bookloans=null;
bookloans = (List<Book_Loan>)request.getAttribute("bookloans");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
</head>
<body>

<table border="2" id="borrowersTable">
	<tr>
		<th>Book Title</th>
		<th>Author Name</th>
		<th>Available Copies</th>
		<th>Check Out</th>
	</tr>	
	<%for (Book_Loan bl: bookloans){ %>
	<tr>
	<td><% out.println(bl.getBook().getTitle()); %></td>
	<td><% out.println(bl.getAuthor().getAuthorName()); %></td>
	<td><% out.println(bl.getBookcopies().getNoOfCopies()); %></td> 
	<td><button type="button" onclick="javascript:location.href='checkoutBook?bookId=<%=bl.getBook().getBookId()%>&branchId=<%=bl.getBranchId() %>&cardNo=<%= bl.getCardNo()%>'">Check Out</button>
	</tr>
	<%} %>
</table>


</body>
</html>