<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import ="java.util.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>

<html> 
<head>    


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script> 
<script language="JavaScript" src="/HISClinical/mrd/reports/js/mrdReport.js"></script>
	
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:css src="/hisglobal/css/tab.css" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/mrd/reports/js/mrdReport.js" />

<script>
      
function validateReportOptions()
{
	if(!validateFromToDates())	return false;
	return true;
}
</script>

</head>

<body> 

<html:form action="/report/hospitalPerformanceReport" >

<his:TransactionContainer>

<his:TitleTag name="Hospital Performance Report">
</his:TitleTag>

	<%
		String fromDateLabel="" ;
		String toDateLabel="" ;
		String fromDateControl="" ;
		String toDateControl="" ;
		fromDateLabel="" ;
		toDateLabel="" ;
		fromDateControl="" ;
		toDateControl="" ;
	%>

		<bean:define name="HospitalPerformanceReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
		<bean:define name="HospitalPerformanceReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
	<%
		if(frDate==null||frDate.equalsIgnoreCase(""))
			frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		if(tDate==null||tDate.equalsIgnoreCase(""))
			tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	%>  
    	
<his:SubTitleTag name="Report Details">
</his:SubTitleTag>   
<his:ContentTag>
 	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>            
 			<td class="tdfonthead" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>
					</font>
        		</div>
        		
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
		 		
			</td>
 		            
 			<td class="tdfonthead" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
 		</tr>
</table> 	

 <his:SubTitleTag name="Report Generation Options">
</his:SubTitleTag> 
		<table width="100%" cellspacing="1" cellpadding="0">
    <tr>   
		<td width="25%" class="tdfonthead">
        	<font color="#FF0000">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          	<bean:message key="reportType"/>      
	        </font>
        </td>                
        <td width="25%" class="tdfont" > 
	        <html:select name="HospitalPerformanceReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
		       	<html:option value="<%=Config.TEXT%>">Textual</html:option>
			</html:select> 		
        </td>
        <td width="25%" class="tdfonthead">
        	<font color="#FF0000">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        	<bean:message key="pdfOrWord"/>      
	        </font>
        </td>
        <td width="25%" class="tdfont">
        		<html:select name="HospitalPerformanceReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	</td>
    	</tr>    	
	</table>               
</his:ContentTag>


<his:ButtonToolBarTag>        
	<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>'style=cursor:pointer onkeypress="if(event.keyCode==13) submitReport();" tabindex="1" onclick = "submitReport();" />         
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')"/>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');"/>
</his:ButtonToolBarTag>

<html:hidden name="HospitalPerformanceReportFB" property="sysDate" value="<%=tDate%>"/>
<html:hidden name="HospitalPerformanceReportFB" property="hmode"/>

</his:TransactionContainer>

</html:form>
</body>
</html>
