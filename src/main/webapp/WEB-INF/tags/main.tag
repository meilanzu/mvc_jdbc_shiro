<%@tag description="main template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>

<html>
	<z:header/>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<z:navigation/>
			</div>
		</div>
		<jsp:doBody/>
	</div>	
</body>
</html>