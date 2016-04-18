<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function deleteLibraryBranch(librarybranchId){
	$.ajax({
		  url: "deleteLibraryBranch",
		  data:{
			  librarybranchId: librarybranchId
		  }
		}).done(function(data) {
		  $('#librarybranchesTable').html(data);
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

<table border="2" id="librarybranchesTable">
	<tr>
		<th>Branch Name</th>
		<th>Branch Address</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>	
	<%for (LibraryBranch l: librarybranches){ %>
	<tr>
	<td><% out.println(l.getLibrarybranchName());%></td>
	<td><% out.println(l.getLibrarybranchAddress());%></td>
	<td><button type="button" onclick="javascript:location.href='editLibraryBranch?librarybranchId=<%=l.getLibrarybranchId() %>'">EDIT</button>
	<td><button type="button" onclick="deleteLibraryBranch(<%=l.getLibrarybranchId() %>)">DELETE</button>
	</tr>
	<%} %>
</table>
</body>
</html>