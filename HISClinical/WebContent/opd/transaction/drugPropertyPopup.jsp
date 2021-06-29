<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/opd/opdJs/opd.js" />
<his:javascript src="/opd/opdJs/opdAjax.js" />

<%@ page import="opd.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function closeForm()
{
	self.close();
}

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
	elmt.value=mode;
	document.forms[0].submit();
	//self.close();
}

function sendData(valSelected)
{
	var url='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=POPULATESEARCH&selectedDrugIndex='+valSelected;
	httpRequest("GET",url,true);
	//self.close();
}
	
function handleResponse()
{
	if(request.readyState == 4)
	{
		//alert("ready state");
		if(request.status == 200)
		{
			//alert("request status");
			var resp=request.responseText;
			var drugName=resp.substring(0,resp.indexOf('^'));
			var drudId=resp.substring(resp.indexOf('^')+1);
	
			opener.setSearchValues(drudId,drugName);
 			isPopulated=true;
			//alert("hhhhhhhhh");
			//opener.document.forms[0].submit();
			window.close();
			//window.self.focus();
			//alert("after");
			//document.forms[0].submit();
			
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}
}

function validateCode()
{
	var valSelected=-1;
	/*
	// var isPopulated=false;
	for(i=0;i<self.document.getElementsByName('selectedDrugIndex').length;i++)
	{
		if(self.document.getElementsByName('selectedDrugIndex')[i].checked)
		{
			valSelected=self.document.getElementsByName('selectedDrugIndex')[i].value;
			break;
		}
	}
	*/
	valSelected=self.document.getElementsByName('selectedSearchIndex')[0].value;
	alert("valSelected "+valSelected);
	if(valSelected!="-1")
		sendData(valSelected);	
}

</script>

<html:form action="/patTreatmentDetailTile">
	<body >
		<his:TitleTag name="DRUG PROPERTY">
		</his:TitleTag>
			
			
	<bean:define id="map" name="<%=OpdConfig.COLUM_HEADER_MAP_SAFTY_ALERT%>" type="java.util.Map"> </bean:define>
	<%
		if(map.size()>0)
		{
	%>
			<his:SubTitleTag name="Drug Safty Alert">
		</his:SubTitleTag>
		<table width="100%">
		
		
		<logic:iterate id="mapEntry" name="map" type="java.util.Map.Entry">
			<bean:define id="headerName" name="mapEntry" property="key"></bean:define>
			<bean:define id="headerValue" name="mapEntry" property="value"></bean:define>
			<tr>
				<td width="10%" class="tdfonthead">
					<div align="right">
						<b>
						<%= headerName%>
						</b>
					</div>	
				</td>
				<td class="tdfont">
					<div align="left">
						<%= headerValue%>
					</div>
				</td>
				
			</tr>
		
		</logic:iterate>
	</table>
	<%		
		}
	%>		
	
	
	<bean:define id="map" name="<%=OpdConfig.COLUM_HEADER_MAP_DRUG_DOSE_INDICATION%>" type="java.util.Map"> </bean:define>
	<%
		if(map.size()>0)
		{
	%>
	
	<his:SubTitleTag name="Drug Dose Indication">
		</his:SubTitleTag>
		<table width="100%">
				
		<logic:iterate id="mapEntry" name="map" type="java.util.Map.Entry">
			<bean:define id="headerName" name="mapEntry" property="key"></bean:define>
			<bean:define id="headerValue" name="mapEntry" property="value"></bean:define>
			<tr>
				<td width="10%" class="tdfonthead">
					<div align="right">
						<b>
						<%= headerName%>
						</b>
					</div>	
				</td>
				<td class="tdfont">
					<div align="left">
						<%= headerValue%>
					</div>
				</td>
				
			</tr>
		
		</logic:iterate>
	</table>
	<%		
		}
	%>	
	<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateCode()" onkeypress="if(event.keyCode==13) validateCode()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('SEARCHDRUG')" onkeypress="if(event.keyCode==13) submitPage('SEARCHDRUG')">
        </his:ButtonToolBarTag>
	 <html:hidden name="PatientTreatmentDetailFB" property="hmode"/>
	 <html:hidden name="PatientTreatmentDetailFB" property="selectedSearchIndex"/>
	</body>
	
</html:form>		