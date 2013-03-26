<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.lang.String" %>
<html>
<head>
	<title>${title}</title>
</head>

<body>

<div class="container">

	<div class="row">
		<div class="span12">
		</div>
	</div>
	
	<div class="row">
		<div class="span12">
			<h1>${title}</h1>
			<h4>Count : ${total}</h4>
			<h4>Results : ${resultsPerPage}</h4>
		</div>
	</div>
	
	<div class="row">
		<div class="span12">

			<div class="btn-toolbar">
				<div class="btn-group">

					<%  int total = Integer.parseInt(request.getAttribute("total").toString());
						int resultsPerPage = Integer.parseInt(request.getAttribute("resultsPerPage").toString());
						int activePage = Integer.parseInt(request.getAttribute("activePage").toString());
						System.out.println("ACTIVE PAGE : " + activePage);
						int currentPage = 1;
					    for(int m = 0; m < total; m++){ 
							if(m % resultsPerPage == 0){%>
								<%if(activePage == currentPage){%>
									<a href="/app/api/type/list?offset=<%=m%>&max=<%=resultsPerPage%>&page=<%=currentPage%>" class="btn active"><%=currentPage%></a>
								<%}else{%>
									<a href="/app/api/type/list?offset=<%=m%>&max=<%=resultsPerPage%>&page=<%=currentPage%>" class="btn"><%=currentPage%></a>
								<%}%>
								
							<%	
								currentPage++;
							}%>
					<%}%>

				</div>
			</div>
			
				
			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Author Id</th>
						<th>Raw</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="property" items="${types}">
						<tr>
							<td>${property.id}</td>
							<td>${property.name}</td>
							<td>${property.authorId}</td>
							<td>${property.raw}</td>
						</tr>									
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</div>
</body>
</html>