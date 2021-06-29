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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<%@ page import ="registration.*,hisglobal.presentation.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function submitTile(mode)
{
	document.getElementsByName("transactionMode")[0].value=mode;
	alert("TranscationMode="+document.getElementsByName("transactionMode")[0].value);
	document.forms[0].submit();  
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
    		
    		opener.submitDesc();
    	
	    	self.close();
    	}
    }
}

function clearValue()
{
	document.getElementsByName("imageDesc")[0].value="";
}

function validateAdd()
{
	if(document.getElementsByName("imageDesc")[0].value=="")
	{
		alert("Enter the Image Description");
		document.getElementsByName("imageDesc")[0].focus();
		return false;
	}
	else 
	submitTile('SAVE')
}
</script>

<body onload="isFormClose()">

	<html:form action="/master/addImageDescription.cnt" method="post">
		<his:TransactionContainer>
			<his:ContentTag name="Add Image Description">
				<table>
					<tr>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<b>
									<bean:message key="image"/>
									<bean:message key="description"/>
								</b>	
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="left">
								<html:text name="AddImageDescriptionFB" property="imageDesc">
								</html:text>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		
	<html:hidden name="AddImageDescriptionFB" property="transactionMode"/>
	
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="hisglobal/images/btn-add.png"/>' style=cursor:pointer onclick ="validateAdd() && submitTile('SAVE')" onkeypress="if(event.keyCode==13)validateAdd() && submitTile('SAVE')">
		<!--<img class="button" src='<his:path src="hisglobal/images/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
		-->
		<img class="button" src='<his:path src="hisglobal/images/btn-clr.png"/>' style=cursor:pointer onclick ="clearValue()" onkeypress="clearValue();">
	</his:ButtonToolBarTag>
	</his:TransactionContainer>
	</html:form>
</body>