
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<%@ page import ="registration.*,hisglobal.presentation.*,hisglobal.vo.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function submitform()
{
	var fileName=document.getElementsByName("fileName")[0].value;
	var ext=fileName.substring(fileName.lastIndexOf("."),fileName.length).toUpperCase();
	
	if(fileName=="")
	{
		alert("Please Select a Audio/Video File");
	}
	else if(ext==".MPEG" || ext==".MPG" || ext==".MOV")
	{
		elem = document.getElementsByName('transactionMode')[0];
		elem.value = "SAVE" ;
		document.forms[0].submit();	
	}
	else 
	alert('Please Select Files With Following Formats:MPEG/MPG/MOV');
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

	<html:form enctype="multipart/form-data" action="/master/addAudioVideoPopup.cnt" method="post">
	
		<his:TransactionContainer>
			<his:ContentTag>
				<table>
					<tr>
						<td class="tdfonthead">
							<b>
								<bean:message key="selectAudioVideo" />
							</b>
							<html:file name="AddAudioVideoPopupFB" property="fileName" ></html:file>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			<his:ButtonToolBarTag>
				<html:button value="Attach"  property="mybutton" onclick="submitform()"/>
			</his:ButtonToolBarTag>
			
			<html:hidden name="AddAudioVideoPopupFB" property="transactionMode"/>
			
		</his:TransactionContainer>
	</html:form>
</body>