<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="opd.*,java.util.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="registration.RegistrationConfig"%>
<his:css src="/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript  src="/registration/js/reportsValidation.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function submitPage(mode)
{
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function showUnit()
{
	val=document.getElementsByName("departmentCode")[0].value;
	if(val!=-1)
	{
		submitPage('UNIT');
	}
	else
	{	
		submitPage('NEW');
	}
}

function icdCodeReportHandler()
{
	document.getElementsByName('jrxmlName')[0].value="<%=OpdConfig.ICD_CODE_REPORT%>";         
	
	success=false;
	if(document.getElementsByName('departmentCode')[0].value==-1)
	{
		alert("Select the Department");
		document.getElementsByName('departmentCode')[0].focus();
		return false;
	}
	
	if(validateGraphOrText())
    { 
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        if(validateTextual())
	        {      
	           		return true;           	   
	        }
        }//end of selection is for text based report	
        if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	        if(validateGraphForAgeWiseReport())
	        {
		    	if(validateTodayOrDate())        
		        success=true;	                
	        }
        }//end of selection is for graph based report	 
    }
    
	return success;
}

</script>

<his:TitleTag name="Icd Code Report"> </his:TitleTag>
	<%
		String fromDateLabel="" ;
		String toDateLabel="" ;
		String fromDateControl="" ;
		String toDateControl="" ;
		String fromControl="" ;
		String toControl="" ; 
		String deptlabel="" ;
		String deptcontrol="" ;
		String fromLabel="" ;
		String toLabel="" ;
	%>

	<bean:define name="ICDCodeReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	<bean:define name="ICDCodeReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
	<%
		String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		if(frDate==null||frDate.equalsIgnoreCase(""))	frDate = sysDate;
		if(tDate==null||tDate.equalsIgnoreCase(""))		tDate = sysDate;
	%>  
	<his:SubTitleTag name="Report Details">
	</his:SubTitleTag>   

	<his:ContentTag>
		<table width="100% cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="15%">
					<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="fromDate"/>
						</font>
					</div>
				</td>
				<td class="tdfont" width="12%">
					<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
						<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
					</div>
				</td>
				<td class="tdfont" width="20%">		
					<span id='divfromhrcontrol' style='<%=fromControl %>'align="left">	            
						<html:text name="ICDCodeReportFB" tabindex="1" property="fromHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
						<b>	<bean:message key="colon"/></b>
					</span>
					<span id='divfromMinControl' style='<%=fromControl %>' align="left">         
						<html:text name="ICDCodeReportFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>
					</span>
				</td>
				<td class="tdfonthead" width="15%">
					<div id='divtoDate' style='<%=toDateLabel %>' align="right">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="toDate"/>
						</font>
					</div>
				</td>
				<td class="tdfont" width="12%">
					<div id='divtoDateControl' style='<%=toDateControl %>' align="left">    
						<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
					</div>
				</td>
				<td class="tdfont" width="20%"> 	 
					<span id='divtohrcontrol' style='<%=toControl%>' align="left">	            
						<html:text name="ICDCodeReportFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
						<b><bean:message key="colon"/>	  </b>
					</span>
					<span id='divtoMinControl' style='<%=toControl%>' align="left">         
						<html:text name="ICDCodeReportFB" tabindex="1" property="toMin" maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>	  
					</span>
				</td>                 			
			</tr>
			<tr>
				<td class="tdfonthead">
	     			<div id ="deptLabel"  align="right"  style='<%=deptlabel%>'>
			        	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  		<bean:message key="department"/>
					  </font>
	      			</div>
	    		</td>
	    		<td class="tdfont" colspan="2">
	       			<div id="deptControl" align="left"  style='<%=deptcontrol%>'>
	     		 		<html:select name="ICDCodeReportFB"  property="departmentCode"  tabindex="1"  styleClass ="regcbo" onchange="showUnit()">   
						  <html:option value="-1">Select Value</html:option>
						  <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" >
						  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property = "value" labelProperty = "label"/>
						  </logic:present>
				  		</html:select> 
		   			</div>       
	    		</td>
	    		<td class="tdfonthead">
		    		<his:statusTransactionInProcess>
		    			<div id="unitLabel" align="right" >
		    				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  		<bean:message key="unit"/>
						  </font>
		    			</div>
					</his:statusTransactionInProcess>	
	    		</td>
	    		<td class="tdfont" colspan="2">
	    			<his:statusTransactionInProcess>
		    			<div id="unitControl" align="left" >
		    				<html:select name="ICDCodeReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" >
								<html:option value="%">All Unit</html:option>
								<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" >
								  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" property = "value" labelProperty = "label"/>
								  </logic:present>
							</html:select>
						</div>
					</his:statusTransactionInProcess>			  
	    		</td>
	 	 	</tr>
	 	
	 	</table>
	</his:ContentTag> 		

	<his:SubTitleTag name="Report Generation Options">
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>       
				<td  class="tdfonthead">
					<div align="right">
						<font color="#FF0000">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="reportType"/>      
					</font></div>
				</td>                
				<td  class="tdfont" colspan="2" > 
					<html:select name="ICDCodeReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=Config.TEXT%>">Textual</html:option>
					</html:select> 		
				</td>
				<td  class="tdfonthead">
					<div align="right">
						<font color="#FF0000">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="pdfOrWord"/>      
					</font></div>
				</td>
				<td  class="tdfont" colspan="2">
					<html:select name="ICDCodeReportFB" property="reportType" tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>        	
						<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
						<html:option value="<%=Config.RTF %>">HTML</html:option>
					</html:select>
				</td>
			</tr>    	
		</table> 
	</his:ContentTag>

	<html:hidden name="ICDCodeReportFB" property="reportMode"/>
	<html:hidden name="ICDCodeReportFB" property="mode" value="ICDCODEREPORT"/>
	<html:hidden name="ICDCodeReportFB" property="jrxmlPath" value="<%=OpdConfig.OPD_JRXML_PATH%>"/>
	<html:hidden name="ICDCodeReportFB" property="jrxmlName"/>

