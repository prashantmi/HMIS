
<html>
<head>

<script>
window.history.forward() ;
function setFrameVis() {
    if (document.getElementById) {
        if (getCookie("frameHidden") == "true") {
            hideFrame()
        }  
    }
}
var origCols;
function toggleFrame(elem) {
    if (origCols) {
        elem.firstChild.nodeValue = "<<Hide Navbar";
        setCookie("frameHidden", "false", getExpDate(180, 0, 0));
        showFrame();
    } else {
        elem.firstChild.nodeValue = "Show Navbar>>";
        setCookie("frameHidden", "true", getExpDate(180, 0, 0));
        hideFrame();
    }
}


</script>

<title>C-DAC Administrator Console</title>
</head>
<frameset id="fs1" rows="67,*" cols="*" target="_parent" border="0" bordercolor="" framespacing="0" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
  		
  		<frameset rows="67,*" id="fs108" cols="*" framespacing="0" frameborder="0" border="0" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
				<frame src="../HisGlobal/header.jsp" name="iframe3" frameborder="" scrolling="no" noresize="noresize" marginwidth="0" marginheight="0" id="f3" title="iframe3" border="0" topmargin="0" leftmargin="0" >
  		
  	
  		</frameset>
  		
  	<frameset rows="*" cols="230,*" framespacing="0" frameborder="0"  border="0" bordercolor="black" id="tmp" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
    		<frameset rows="*," id="fs11" cols="75" framespacing="0" frameborder="0" border="0" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
				<frame src="menu.jsp" id="menu"  scrolling="no" name="iframe1" frameborder="0" marginwidth="0" marginheight="0"  title="iframe1" topmargin="0" leftmargin="0" border="0">
			</frameset>

			<frameset rows="*," id="fs11" cols="*," framespacing="0" frameborder="0" border="0" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
		
								
				<frame src="content.jsp" name="iframe2" frameborder="no" bordercolor=" " id="content" title="iframe2" border="0" >
  			</frameset>
  				
	</frameset>
  		
</frameset>


	
</html>

