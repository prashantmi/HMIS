<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"> 
 
function changeFrameSize(mode){
	var frameset=parent.document.getElementById("emrDetailFS")
	if(mode==1){
		document.getElementById("imgLeftId").style.display="none"
		document.getElementById("imgRightId").style.display="block"
		//document.getElementById("treeDivId").style.display="none"
		//document.getElementById("treeNodeId").width="0%"
		frameset.cols = "0,1%,*";
	}
	else{
		document.getElementById("imgLeftId").style.display="block"
		document.getElementById("imgRightId").style.display="none"
		frameset.cols = "18%,1%,*";
		//document.getElementById("treeDivId").style.display="block"	
		//document.getElementById("treeNodeId").width="100%"
	}

} 
</script> 
</head>

<table width="100%" height="100%" class="applicationBackgroundColor">
<tr height="100%" style="height: 400pt" >
<td valign="top"  align="left" width="100%">
	<div align="left" id="imgLeftId">
	<img class="button" src='<his:path src="/hisglobal/images/trileft.gif"/>' onclick="changeFrameSize(1)" title="Hide Menu"/>
	</div>
	<div  align="left" id="imgRightId" style="display: none">
	<img class="button" src='<his:path src="/hisglobal/images/tri.gif"/>' onclick="changeFrameSize(2)" title="Show Menu"/>
	</div>
</td>
</tr> 
</table>

