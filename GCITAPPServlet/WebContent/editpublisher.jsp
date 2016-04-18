<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.gcit.lms.entity.Publisher" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    <%Publisher publisher = null;
    if(request.getAttribute("publisher")!=null){
    	publisher = (Publisher)request.getAttribute("publisher");
    	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<h2>Welcome to GCIT Library Management System - Admin</h2>
<h3>Edit Publisher Details Below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
${result}

</head>
<body>
	<form action="updatePublisher" method="post">		
		Publisher Name: <input type="text" name="publisherName" value ="<%=publisher.getPublisherName()%>"> <br />
		Publisher Address: <input type="text" name="publisherAddress" value ="<%=publisher.getPublisherAddress()%>"> <br />
		Publisher Phone: <input type="text" name="publisherPhone" value = "<%=publisher.getPublisherPhone()%>"> <br />	
		<input type="hidden" name="publisherId" value=<%=publisher.getPublisherId() %>>
		<button type="submit">Edit Publisher</button>
	</form>
</body>
</html>