<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:main>
	<div class="row">
		<div class="span12">
			<h1>${title}</h1>
		</div>
	</div>
	<div class="row">
		<div class="span12">
			<p>
				<label>Id : </label>
				<h5>${id}</h5>
			</p>			
			<p>
				<label>Name : </label>
				<h5>${name}</h5>
			</p>
			<p>
				<label>Author Id : </label>
				<h5>${authorId}</h5>
			</p>
			<p>
				<label>Raw : </label>
				<h5>${raw}</h5>
			</p>			
		</div>
	</div>
</z:main>