<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.gcit.lms.entity.Publisher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
${result}

</head>
<body>
	<form action="addPublisher" method="post">
		Publisher Name: <input type="text" name="publisherName"> <br />
		Publisher Address: <input type="text" name="publisherAddress"> <br />
		Publisher Phone: <input type="text" name="publisherPhone"> <br />
		<button type="submit">Add Publisher</button>
	</form>
</body>
</html>