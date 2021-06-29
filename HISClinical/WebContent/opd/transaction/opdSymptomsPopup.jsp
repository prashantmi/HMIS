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
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
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

function validateSymptoms()
{
	var len=document.getElementsByName("allergySymtoms").length
	var count=0;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("allergySymtoms")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{
		alert("Please Select a Symptom");
	}
	else
	{
		submitPage('ADDSYMPTOM');
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
					<bean:message key="addAllergySymptom"/>
				</his:name>
			</his:TitleTag>
			<% List lstCommpnSymptom=(List)session.getAttribute(OpdConfig.ALLERGY_COMMON_SYMPTOM_LIST);
				if(lstCommpnSymptom!=null && lstCommpnSymptom.size()>0)
				{
			%>
			<his:SubTitleTag name="Common Symptoms">
			</his:SubTitleTag>
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
							<bean:message key="symptoms"/>
						</div>
					</td>
				</tr>
				<%  
					for(int i=0;i<lstCommpnSymptom.size();i++)
					{
						Entry symptomCode = (Entry) lstCommpnSymptom.get(i);
				%>
				<tr>
					<td class="tdfont">
						<div align="center">
							<%Map map = (HashMap)request.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_LIST); %>
								<input type="checkbox" name="allergySymtoms" value='<%=symptomCode.getValue()+"#"+symptomCode.getLabel() %>'  
								<%if(map.get(symptomCode.getValue())!=null){ %> checked='checked' <%}%>	/>
						</div>
					</td>
					<td class="tdfont">
						<div align="left">
							<%=symptomCode.getLabel() %>
						</div>
					</td>
					
				</tr>
					<%} %>	
			</table>		
					
			</his:ContentTag>
			<%	} %>
			<% List lstAllergyTypeSymptom=(List)session.getAttribute(OpdConfig.ALLERGY_TYPE_SYMPTOM_LIST);
				if(lstAllergyTypeSymptom!=null && lstAllergyTypeSymptom.size()>0)
				{
			%>
			<his:SubTitleTag name="Allergy Type Symptoms">
			</his:SubTitleTag>
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
							<bean:message key="symptoms"/>
						</div>
					</td>
				</tr>
				<% 
					for(int i=0;i<lstAllergyTypeSymptom.size();i++)
					{
						Entry symptomCode = (Entry) lstAllergyTypeSymptom.get(i);
				%>
				<tr>
					<td class="tdfont">
						<div align="center">
						<%Map map = (HashMap)request.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_LIST); %>
						<input type="checkbox" name="allergySymtoms" value='<%=symptomCode.getValue()+"#"+symptomCode.getLabel() %>'  
								<%if(map.get(symptomCode.getValue())!=null){ %> checked='checked' <%}%>	/>
							
						</div>
					</td>
					<td class="tdfont">
						<div align="left">
							<%=symptomCode.getLabel() %>
						</div>
					</td>
					
				</tr>
					<%} %>
			</table>		
			</his:ContentTag>
			<%	} 
				if((lstCommpnSymptom==null || lstCommpnSymptom.size()==0) &&
						(lstAllergyTypeSymptom==null || lstAllergyTypeSymptom.size()==0))
				{
				%>
			<his:ContentTag>
				<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td class="tdfonthead" width="100%">
						<div align="center">
							No Symptoms Found
						</div>
					</td>
				</tr>
				</table>
			</his:ContentTag>
			<%	} %>
			
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSymptoms()" onkeypress="if(event.keyCode==13) validateSymptoms()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
           <html:hidden name="OpdEpisodeAllergyFB" property="hmode"/>
           <html:hidden name="OpdEpisodeAllergyFB" property="allergyTableId"/>
		</body>	
	</html:form>