<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcit.lms.entity.Publisher" %>
<%@ page import="com.gcit.lms.service.AdministratorService" %>
<% 
	AdministratorService service = new AdministratorService();
	List<Publisher> publishers = service.getAllPublishers();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function deletePublisher(publisherId){
	$.ajax({
		  url: "deletePublisher",
		  data:{
			  publisherId: publisherId
		  }
		}).done(function(data) {
		  $('#publishersTable').html(data);
		});
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System - Admin</h2>
${result}
<body>

<table border="2" id="publishersTable">
	<tr>
		<th>Publisher Name</th>
		<th>Publisher Address</th>
		<th>Publisher Phone</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>	
	<%for (Publisher p: publishers){ %>
	<tr>
	<td><% out.println(p.getPublisherName());%></td>
	<td><% out.println(p.getPublisherAddress());%></td>
	<td><% out.println(p.getPublisherPhone());%></td>
	<td><button type="button" onclick="javascript:location.href='editPublisher?publisherId=<%=p.getPublisherId() %>'">EDIT</button>
	<td><button type="button" onclick="deletePublisher(<%=p.getPublisherId() %>)">DELETE</button>
	</tr>
	<%} %>
</table>
</body>
</html>