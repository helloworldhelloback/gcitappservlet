<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="com.gcit.lms.entity.LibraryBranch" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
</head>
<body>
	<form action="addLibraryBranch" method="post">
		Branch Name: <input type="text" name="librarybranchName"> <br />
		Branch Address: <input type="text" name="librarybranchAddress"> <br />
		<button type="submit">Add Branch</button>
	</form>

</body>
</html>