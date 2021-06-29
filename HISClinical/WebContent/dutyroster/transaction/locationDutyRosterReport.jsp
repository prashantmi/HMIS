<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>    
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/dutyroster/js/locationDutyRoster.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" /> 
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig"%>
<%@page import="java.util.*" %>


<%@page import="dutyroster.transaction.controller.fb.DateWiseEmployeeDutyRosterFB;"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>



<his:css src="/css/calendar-blue2.css" />

 <body >

  <html:form action="/transaction/LocationDutyRoster" > 
  <%
  String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
  
		String printingDateAndTime = "Report Printing Date and Time :"+ systemDate;

			
	%>
  
   
    
  <div id="noPrint" align="right"><img class="button"
				src='<his:path src="/hisglobal/images/btn-pnt.png"/>' tabindex="1"
				style="cursor: pointer" onclick="printPage()"
				onkeypress="if(event.keyCode==13) printPage()"> <img
				class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
				tabindex="1" style="cursor: pointer" onclick="backButton()"
				onkeypress="if(event.keyCode==13) backButton()"></div>

			<br>
			<br>

			<table width="100%" cellpadding="1" cellspacing="1"
				bordercolor="black" style="color: black;">
				<tr>
					<td width="100%" class="reportNameHeader">
				<bean:write name="LocationRosterFB" property="reportHeader"/>
					</td>

				</tr>

				<br>
				<br>
				<br>

				<tr>
					<td class="reportDate"><%=printingDateAndTime%></td>
				</tr>
			</table>
			<br>
			<br>
     
    
	
		
	
	  
	 
		
		
		
		<bean:write name="LocationRosterFB" property="locationWiseRoster" filter="false"/>
		
    
      
      


    
    <html:hidden name="LocationRosterFB" property="hmode"/>
	<html:hidden name="LocationRosterFB"   property="concatedValueToBeSaved"/>
	<html:hidden name="LocationRosterFB"   property="modeOfRoster"/>
	
	<html:hidden name="LocationRosterFB"   property="fromDateCheck"/>
	<html:hidden name="LocationRosterFB"   property="toDateCheck"/>
		
   </html:form>
  </body>
</html>
		     
		   
		  