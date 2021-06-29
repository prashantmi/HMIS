
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function validateAdvice()
{
	var len=document.getElementsByName("adviceChk").length
	var count=0;
	var advice="";
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("adviceChk")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{
		alert("Please Select a Advice");
	}
	else
	{
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("adviceChk")[i].checked)
			{
				advice=advice+document.getElementsByName("adviceChk")[i].value+", ";
			}
		}
	
		advice=advice.substring(0,advice.length-2);
		opener.document.getElementsByName("adviceText")[0].value=advice;
		submitPage('SHOWADVICE');
	}
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
    		self.close();
    	}
    }
}

</script>

<html:form action="/opdEpisodeAllergies">
	<body onload="isFormClose()">
		<his:TitleTag>
			<his:name>
				<bean:message key="advice"/>
			</his:name>
		</his:TitleTag>
		<%List lstAdvice=(List)session.getAttribute(OpdConfig.ESSENTIALBO_OPTION_ALLERGY_ADVICE); 
			if(lstAdvice.size()>0){
		%>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td class="tdfonthead" width="10%">
						<div align="center">
							<bean:message key="select"/>
						</div>
					</td>
					<td class="tdfonthead" width="90%">
						<div align="left">
							<bean:message key="advice"/>
						</div>
					</td>
				</tr>
				<%
					for(int i=0;i<lstAdvice.size();i++)
					{
						Entry advice = (Entry) lstAdvice.get(i);
				%>
					<tr>
						<td class="tdfont">
							<div align="center">
								<html:checkbox name="OpdEpisodeAllergyFB" property="adviceChk" value="<%=advice.getValue() %>" tabindex="1"></html:checkbox>
							</div>
						</td>	
						<td class="tdfont">
							<div align="left">
								<%=advice.getLabel() %>
							</div>
						</td>	
					</tr>
				<%} %>
			</table>
		</his:ContentTag>
		<%} %>
		<his:ButtonToolBarTag>
		<%if(lstAdvice.size()>0){ %>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateAdvice()" onkeypress="if(event.keyCode==13) validateAdvice()">
			<%} %>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        <html:hidden name="OpdEpisodeAllergyFB" property="hmode"/>
        <html:hidden name="OpdEpisodeAllergyFB" property="adviceText"/>
	</body>
	<his:status/>
</html:form>
				