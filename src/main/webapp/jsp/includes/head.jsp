<title>${title}</title>
<link href="/app/bootstrap/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css" />
<link href="/app/js/lib/jstree/themes/default/style.css" media="screen" rel="stylesheet" type="text/css" />
<style type="text/css">
	.icon-remove{
		cursor : pointer !important;
		cursor : hand !important;
	}
</style>
<script type="text/javascript" src="/app/js/lib/jquery/jquery.js"></script>
<script type="text/javascript" src="/app/js/lib/mustache/mustache.js"></script>
<script type="text/javascript" src="/app/js/lib/jstree/jstree.min.js"></script>
<script type="text/javascript" src="/app/js/lib/json2.js"></script>

<script type="text/javascript">

Types = {};
TypesTransport = (function(){
	
	var baseUrl = '/app/api/type';
	
	function get(max, offset){
		return $.ajax({
			url   : baseUrl + getParams(max, offset), 
			type  : 'get',
			cache : false
		});
	}

	function getParams(max, offset){
		if(max && offset >= 0) {
			return "?max=" + max + "&offset=" + offset;
		}
		return "";
	}
	
	return {
		get : get
	}
	
})();

</script>

