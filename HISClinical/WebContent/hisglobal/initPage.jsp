<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="MenuConfig" class="startup.menu.menuConfigure" scope="request" />

<HTML>
	<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script>	
	history.forward();
	var lastKeyDown;
	function changeFrameSize(e)
	{
		var key = e.keyCode;
		//alert("init page"+key);
		if(window.event && window.event.keyCode == 17)  
		 lastKeyDown = 'Ctrl';
	  if(window.event && window.event.keyCode == 49)
	  {	
		if(lastKeyDown == 'Ctrl')
		{
			if(parent.document.getElementById("fs2").cols == "150,*")
			{
				parent.document.getElementById("fs2").cols = "0,*";
			}
			else
			{
				parent.document.getElementById("fs2").cols = "150,*";
			}
		}
	  }
	if(window.event && window.event.keyCode != 49 && window.event.keyCode != 17)
	{
	  	lastKeyDown = 'not';
	  	}
	}	
	</script>
	<STYLE TYPE="text/css">
	.bg {
		background-attachment: fixed;
		background-image: url(<%="../"+MenuConfig.getInitPageImage()%>);
		background-repeat: no-repeat;
		background-position: center; 
		}
</STYLE>
	</HEAD>
	<BODY  class="bg" topmargin="0" valign="top">
		<FORM name="form1" METHOD='POST'>
		<BR>
		
		</FORM>
	</BODY>
</HTML>
