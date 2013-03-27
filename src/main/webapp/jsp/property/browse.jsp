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
		</div>
	</div>
	
	<div class="row">
		<div class="span12">

			<div id="tree"></div>
			
		</div>
	</div>
	
	
	<script type="text/template" id="template">
	
	
	</script>
	
	
	
	<script type="text/javascript">
	
		var FIRST_PAGE = 1;
		var $tree = $("#tree");
		
		var data =[
		              {
		                  "data" : "weekly",
		                  "attr":{"rel":"directory"},
		                  "children" : [ {"data":"w-file1","attr":{"rel":"file"}}, {"data":"w-file2","attr":{"rel":"disabled"}} ]
		              },
		              {
		                  "data" : "daily",
		                  "attr":{"rel":"directory"},
		                  "children" : [ {"data":"d-file1","attr":{"rel":"file"}}, {"data":"d-file2","attr":{"rel":"file"}} ]
		              }
		          ];
		
		
		$(document).ready(function(){
			TypesTransport.get(7, 0)
				.done(renderTypes(FIRST_PAGE))
				.error(error)
				
				console.log($tree, data);
				
				$("#tree").jstree({
				    plugins: ['themes', 'json_data', 'checkbox',"types"],
					json_data: {data: data},
				    themes: {
				        theme: 'default'
				    },
				    checkbox: {
				        real_checkboxes: true,
				            two_state: true
				    },
				    "types":{
				            "types":{
				                  "disabled" : {
				                       "check_node" : false,
				                      "uncheck_node" : false
				                    } ,
				                                  "directory" : {
				                       "check_node" : false,
				                      "uncheck_node" : false
				                    } 
				            } 
				    }    

				});
		});
		
		
		function renderTypes(currentPage){
			return function(data){
				console.info(typeof data);
				if(typeof data == 'string')data = $.parseJSON(data);
				console.log(typeof data);
			}
		}
		
		
		function error(){
			console.warn('error')
		}
		
		
		
	</script>
	
</body>
</html>