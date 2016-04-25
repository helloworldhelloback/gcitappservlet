<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.Author" %>
    <%@ page import="com.gcit.lms.entity.Book" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
<%@ include file="include.html"%>
<%
	AdministratorService service = new AdministratorService();
	Integer authorsCount = service.getAuthorCount();
	List<Author> authors = new ArrayList<Author>();
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>)request.getAttribute("authors");
	}else{
		authors = service.getAllAuthors(1);	
	}
	int pageNo=1;
		
%>

<script type="text/javascript">
function deleteAuthor(authorId, pageNo){
	
	$.ajax({
		  url: "deleteAuthor",
		  data:{
			  authorId: authorId,
			  pageNo : pageNo
		  }
		}).done(function(data) {
		  $('#authorsTable').html(data);
		});
}
function searchAuthor(searchString){
	
	$.ajax({
		  url: "searchAuthor",
		  data:{
			  authorId: searchString
		  }
		}).done(function(data) {
		  $('#searchResults').html(data);
		});
}

</script>
<script type="text/javascript">
$(document).ready(function()
		{
		    // codes works on all bootstrap modal windows in application
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>


<h2>Welcome to GCIT Library Management System - Admin</h2>

${result}

<form action="searchAuthors" method="post">
	<div class="input-group">
		<input type="text" class="form-control" placeholder="Author Name"
			aria-describedby="basic-addon1" name="searchString" onchange="searchAuthor()">
		<button onclick="searchAuthor();">Search!</button>
	</div>
</form>



<div id="searchResults">
<nav>
	<ul class="pagination">
		<li><a href="#" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<%if(authorsCount!=null &&  authorsCount >0){
			pageNo = authorsCount % 10;
			int pages = 0;
			if(pageNo == 0){
				pages = authorsCount/10;
			}else{
				pages = (authorsCount/10) + 1;
			}			
			for(int i=1; i<=pages;i++){%>
				<li><a href="pageAuthors?pageNo=<%=i%>"><%=i %></a></li>
			<%}
			
		} %>
		<li>
      		<a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
	</ul>
</nav>

<div class="row">
	<div class="col-md-6">
		<table border="2" id="authorsTable" class="table">
			<tr>
				<th>Author Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>

			<%
				for (Author a : authors) {
			%>
			<tr>
				<td>
					<%
						out.println(a.getAuthorName());
					%>
				</td>


				<td align="center"><button type="button"
						class="btn btn btn-primary" data-toggle="modal"
						data-target="#myModal1"
						href="editAuthor?authorId=<%=a.getAuthorId()%>">EDIT</button></td>


				<td align="center"><button type="button" class="btn btn-sm btn-danger"
						onclick="deleteAuthor(<%=a.getAuthorId()%>, <%= pageNo%>)">DELETE</button></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</div>
</div>

<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>

