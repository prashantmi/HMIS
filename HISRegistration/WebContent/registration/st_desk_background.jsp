<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Background Page</title>
<script src="/HIS/hisglobal/js/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$(document).ready(function() {
		var ht = Math.round($(window).height())-50 + "px";
		var wt = Math.round($(window).width())-50 + "px";
		$('body').height(ht);
		$('body').width(wt);
	});
	
});
</script>

</head>
<body style="background-image: url(/HIS/hisglobal/images/login/hiswatermark.png);background-repeat: no-repeat; background-position: center;">
</body>
</html>