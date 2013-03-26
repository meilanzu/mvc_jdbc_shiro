<html>
<head>
	<%@include file="/jsp/includes/head.jsp" %>
    <sitemesh:write property='head'/>
</head>
<body>
	<div class="container">
		
		<div class="row">
			<div class="span12">
				<%@include file="/jsp/includes/navigation.jsp" %>
			</div>
		</div>
	
		<sitemesh:write property='body'></sitemesh:write>
	
	</div>
	
</body>
</html>