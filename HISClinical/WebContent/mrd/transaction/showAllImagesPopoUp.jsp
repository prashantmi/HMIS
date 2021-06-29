<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">
function showNextImage()
{
	var strIndex=document.getElementsByName("imageIndex")[0].value;
	var size=<%=((List)session.getAttribute(Config.IMAGE_BYTE_ARRAY)).size()%>
	var sizeInt=parseInt(size);
	sizeInt=sizeInt-1;
	var index=parseInt(strIndex);
	index=index+1;
	//alert("sizeint "+sizeInt)
	//alert("index "+index)
		if(index<=sizeInt)
		{
			document.getElementsByName("imageIndex")[0].value=index;
			submitForm('ALLIMAGES');
		}
		else
		{
			alert("No More Images Available")
		}
		
}

function showPreviousImage()
{
	var strIndex=document.getElementsByName("imageIndex")[0].value;
	var index=parseInt(strIndex);
	index=index-1;
	if(index>=0)
	{
		document.getElementsByName("imageIndex")[0].value=index;
		submitForm('ALLIMAGES');
	}
	else
	{
		alert("No More Images Available")
	}
}

</script>

<html:form action="/emrDesk">



<his:ContentTag>
		<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
		   <tr>
			<td style="background-color:#FFECD9; " align="center" width="100%" height="100%" >
       		 <div align="center">
      		  <table border="1" width="50%" height="50%">
	  			  <tr>
	   				 <td>
	   				 <bean:define id="imageIndexId" name="EmrCommonDeskFB" property="imageIndex" ></bean:define>
	   				 <%String url="/image/showImage?"+Config.REQ_PARAMETER_IMAGE_INDEX+"="+imageIndexId; %>
	    				<img style=cursor:pointer src="<his:path src='<%=url%>'/>" height="100%" width="100%" title="Click To Enlarge" alt="No image Loaded" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event)" onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event)">
	   				 </td>
	   			 </tr>
	   		 </table>
	   		</div>
	   	 </td>
		</tr>
		
		</table>
		
</his:ContentTag>
<his:ContentTag>
		<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
		   <tr>
		   		<td class="tdfont" width="45%" align="center" >
		   			<div align="right" >
		   				<img class="button"
						src="<his:path src='/hisglobal/images/arrsingle-left.png'/>" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event);" onclick="showPreviousImage();" >
		   			</div>
		   		</td>
		   		<td class="tdfont" width="10%" align="center" >
		   		</td>
		   		<td class="tdfont" width="45%" align="center">
		   			<div align="left" >
		   				<img class="button"
						src="<his:path src='/hisglobal/images/arrsingle-right.png'/>" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event);" onclick="showNextImage();" >
					</div>		
		   		</td>
		   </tr>
		</table>
</his:ContentTag>
<html:hidden name="EmrCommonDeskFB" property="hmode"/>
<html:hidden name="EmrCommonDeskFB" property="imageIndex"/>
</html:form>
</html>

