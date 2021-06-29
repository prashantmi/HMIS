
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
	<his:javascript src="/registration/js/popup.js"/>
	<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<%@ page import ="registration.*,hisglobal.presentation.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function submitform()
{
	var imageName = document.getElementsByName("uploadImageName")[0].value;
	var ext = imageName.substring(imageName.lastIndexOf("."),imageName.length).toUpperCase();
	document.getElementsByName("imageName")[0].value=imageName;
	if(imageName==""){
		alert('Please Select A File');		
	}
	//else if(ext==".JPG" || ext==".JPEG" || ext==".GIF" || ext==".PNG" || ext==".BMP")
	else if(ext==".JPG" || ext==".JPEG")
	{
		elem = document.getElementsByName('transactionMode')[0];
		elem.value = "SAVE" ;
		document.forms[0].submit();	
	}
	else
	   // alert('Please Select Files With Following Formats:GIF/JPG/JPEG/PNG/BMP');
		 alert('Please Select Files With Following Formats:JPG/JPEG');
}

function isFormClose()
{
	var formclose=true;
  	<%     
		Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
    	if(objStatus.contains(Status.NEW)){
    	%>    	
    		formclose=false;    	   	
    	<%
    }    
    %>
    if(formclose)
    {
    	if(!window.opener.closed)
    	{
    		
    		opener.submit4image();
    	
	    	self.close();
    	}
    }
}
</script>

<body onload="isFormClose()">
	<html:form enctype="multipart/form-data" action="/master/imageUpload.cnt" method="post">
		<his:TransactionContainer>
			<his:ContentTag>
				<table>
					<tr>
						<td class="tdfonthead">
							<b>
								<bean:message key="selectImage" />
							</b>
							<html:file name="ImageUploadFB" property="uploadImageName" ></html:file>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			
			<his:ButtonToolBarTag>
				<html:button value="Attach"  property="mybutton" onclick="submitform()"/>
			</his:ButtonToolBarTag>
		</his:TransactionContainer>
		<html:hidden name="ImageUploadFB" property="transactionMode"/>
		<html:hidden name="ImageUploadFB" property="imageName"/>
		<html:hidden name="ImageUploadFB" property="hmode"/>
	</html:form>

</body>