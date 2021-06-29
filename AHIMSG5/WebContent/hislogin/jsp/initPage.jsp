<%@page session="false" %>

<HTML>
	<HEAD>
	<script>	
	history.forward();
	var lastKeyDown;
	function changeFrameSize()
	{	
	//	alert("in initpage");
		
			if(parent.document.getElementById("fs2").cols == "0,*")
			{
				parent.document.getElementById("fs2").cols = "230,*";
				parent.document.getElementById("f7").contentDocument.getElementById("Image").src="/HIS/hisglobal/images/arrsingle-left.png";
			
			}
			else
			{
				
				parent.document.getElementById("f7").contentDocument.getElementById("Image").src="/HIS/hisglobal/images/arrsingle-left.png";
			}
			  
	}	
	


	</script>
	<STYLE TYPE="text/css">
	.bg {
		background-attachment: fixed;
		background-image: url("/HIS/hisglobal/images/mainPage.png");
		background-repeat: no-repeat;
		background-position: center; 
		}
</STYLE>
	</HEAD>
	<BODY onload="showMenuFrame()"  class="bg" topmargin="0" valign="top">
		<FORM name="form1" METHOD='POST'>
		<BR>
		
		</FORM>
	</BODY>
</HTML>
