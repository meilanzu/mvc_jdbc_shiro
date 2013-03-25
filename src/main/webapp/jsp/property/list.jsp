<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<z:main>
	<div class="row">
		<div class="span12">
			<h1>${title}</h1>
			<h1>Count : ${total}</h1>
		</div>
	</div>
	<div class="row">
		<div class="span12">
			
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
			<div class="btn-toolbar">
				<div class="btn-group">
				
				</div>
			</div>
		</div>
	</div>
</z:main>