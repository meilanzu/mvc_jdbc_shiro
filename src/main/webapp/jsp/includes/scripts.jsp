<script type="text/javascript" src="/app/js/lib/jquery/jquery.js"></script>
<script type="text/javascript" src="/app/js/lib/mustache/mustache.js"></script>
<script type="text/javascript" src="/app/js/lib/json2.js"></script>

<script type="text/javascript">


Types = {};
TypesTransport = (function(){
	
	var baseUrl = '/app/api/type';
	
	function get(max, offset){
		var params = "?max=" + max + "&offset=" + offset; 
		return $.ajax({
			url   : baseUrl + params, 
			type  : 'get',
			cache : false
		});
	}

	
	return {
		get : get
	}
	
})();

</script>

