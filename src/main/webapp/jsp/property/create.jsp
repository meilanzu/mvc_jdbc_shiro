<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<html>
<head>
	<title>${title}</title>
</head>
<body>
	
	<div class="row">
		<div class="span12">
			<h1>${title}</h1>
		</div>
	</div>
	
	<div class="row" id="form">
		<div class="span12">
			
			<div class="alert alert-warning" style="display:none" id="error">
				<h4>Error</h4>
				<p>Something went wrong while saving the property, please check your values and try again</p>
			</div>
			
			<form action="/app/api/type/save" method="post" class="form-horizontal">
				<div class="control-group">
					<label class="control-label">Name</label>
					<div class="controls">
						<input type="text" name="name" value="" placeholder="Name" id="name"/>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">Author Id</label>
					<div class="controls">
						<input type="text" name="authorId" value="" placeholder="1" id="authorId">	
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">Raw</label>
					<div class="controls">
						<input type="text" name="raw" value="" placeholder="ex: { name : test}" id="raw"/>	
					</div>
				</div>
			</form>	
			<button class="btn" id="save">Save</button>
		</div>	
	</div>
	
	
	
	<div class="row" id="success" style="opacity:0">
		<div class="span12">
			<div class="alert alert-info">
				<h4>Success!</h4> Successfully Saved Property<br/>
				<a href="javascript:" id="browse" class="btn">Show Property : <span id="propertyName" class="code"></span></a>
			<div>
		</div>
	</div>
	
	
	
	<script type="text/javascript">
		var $submit       = $('#save'),
			$form         = $('#form'),
			$success      = $('#success'),
			$error        = $('#error'),
			$browse       = $('#browse'),
			$propertyName = $("#propertyName");
			
		var BASE_URL = "/app/api/type/";
			
		$(document).ready(function(){
			$submit.click(submit)
			
			function submit(){
				$error.hide();
				var data = getFormData();
				data = JSON.stringify(data);
				console.info(data);
				$.ajax({
					url         : BASE_URL,
					type        : "post",
					data        : data,
					dataType    : "text",
					contentType : "application/json",
					success     : success,
					error       : error,
				});
			}
			
			
			function error(){
				console.warn("ERROR");
				$error.show();
			}
			
			
			function success(data){
				if(typeof data === 'string')data = $.parseJSON(data);
				console.info("Success", data);
				$form.animate({
					"opacity" : 0
				}, 100, function(){
					$form.remove();
					$browse.attr('href', BASE_URL + "show/" + data.id);
					$propertyName.html(data.name);
					$success.animate({
						"opacity" : 1
					}, 50, function(){
						
					})
				})
			}
			
			
			function getFormData(){
				var data = {};
				$('.form-horizontal input[type="text"]').each(function(index, input){
					var $input = $(input);
					if(!$.isEmptyObject(input) && $input.val() !== ""){
						var value = $input.val();
						var name = $input.attr('name');
						if(name === "authorId") value = parseInt(value);
						data[name] = value;
					}else{
						$error.show();
					}
				});
				return data;
			}
		})
	</script>

</body>
</html>	