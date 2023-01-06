const serviceConfigured = function(){
	$.get("auth/isReady", function(result){
		alert("configuration ok")
	}).fail(function(){
		alert("not configured");
	});
}