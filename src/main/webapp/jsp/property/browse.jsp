<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					

			<% 
				System.out.println("here...");
			%>
			<c:if test="${TotalPages < total}">
			    <span>This condition is not true. This text should not be displayed.</span>
			</c:if>
			
			<fmt:parseNumber var="total" value="${total}" />
			
			<div class="btn-toolbar">
				<div class="btn-group">
					<fmt:parseNumber var="resultsPerPage" value="${resultsPerPage}" />
					<fmt:parseNumber var="currentPage" value="1" />
					
					<c:forEach var="property" items="${types}" varStatus="c">
						<c:if test="${c.index % resultsPerPage == 0}">
							<a href="/app/api/type/list?offset=${c.index}&max=${resultsPerPage}" class="btn">${currentPage}</a>
							<fmt:parseNumber var="currentPage" value="${currentPage + 1}" />
						</c:if>
					</c:forEach>
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