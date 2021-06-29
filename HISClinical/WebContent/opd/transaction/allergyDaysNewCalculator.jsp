<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

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
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
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
function calculateDays()
{
	var day, month, year;
	
	var calDays=0;
	var calYears=0.0;
	
	if(document.getElementsByName("day")[0].value=="")
		day=0;
	else
		day=document.getElementsByName("day")[0].value;	
		
	if(document.getElementsByName("month")[0].value=="")
		month=0;
	else
		month=document.getElementsByName("month")[0].value;
	
	if(document.getElementsByName("year")[0].value=="")
		year=0;
	else
		year=document.getElementsByName("year")[0].value;		
	
	
	calDays=(year*366)+(month*30.5)+(day*1);
	calYears=calDays/366;
	calYears=Math.round(calYears*100)/100;
	//alert(Math.round(calYears*100)/100);
	
	document.getElementsByName("calculatedDays")[0].value=calYears;//calDays;
	
} 

/*window.onload = function()
{
	calculateDays();
}*/


function isFormClose()
{
	var formclose=true;
	 calculateDays();
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

function validateDays()
{
	if(document.getElementsByName("calculatedDays")[0].value == 0)
	{
		alert("Please Enter at Least one Field");
		document.getElementsByName("year")[0].focus();
	}
	else
	{
		id=document.getElementsByName("allergyTableId")[0].value.split("^")[1];
		opener.document.getElementsByName("duration")[0].value=document.getElementsByName("calculatedDays")[0].value;
		submitPage('SHOWDAYS');
	}
}

</script>

<html:form action="/opdEpisodeAllergiesNew">
	<body onload="isFormClose()">
		<his:TitleTag name="Duration Calculator">
		</his:TitleTag>
		
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="year"/></b>
							</font>	
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="OpdEpisodeAllergyNewFB" property="year" onkeypress="return validateNumeric(event)" maxlength="2" size="5" onkeyup="calculateDays()" tabindex="1"></html:text>
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="month"/></b>
							</font>	
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="OpdEpisodeAllergyNewFB" property="month" onkeypress="return validateNumeric(event)" maxlength="2" size="5" onkeyup="calculateDays()" tabindex="1"></html:text>
						</div>
					</td>
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="day"/></b>
							</font>	
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="OpdEpisodeAllergyNewFB" property="day" onkeypress="return validateNumeric(event)" maxlength="3" size="5" onkeyup="calculateDays()" tabindex="1"></html:text>
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="totalNoOfYears"/></b>
							</font>	
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="OpdEpisodeAllergyNewFB" property="calculatedDays" readonly="true" size="5" ></html:text>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>	
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateDays()" onkeypress="if(event.keyCode==13) validateDays()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        <html:hidden name="OpdEpisodeAllergyNewFB" property="hmode"/>
        <html:hidden name="OpdEpisodeAllergyNewFB" property="calculatedDays"/>	
        <html:hidden name="OpdEpisodeAllergyNewFB" property="duration"/>
        <html:hidden name="OpdEpisodeAllergyNewFB" property="allergyTableId"/>
	</body>	
</html:form>