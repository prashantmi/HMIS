<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,inpatient.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="inpatient.InpatientConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript  src="/registration/js/reportsValidation.js"/>
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/masterXml.css" />

<script>


function DoctorCallBookLogReportHandler()
{
		document.getElementsByName('jrxmlName')[0].value="<%=InpatientConfig.CALL_LOGBOOK_REPORT%>";
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

<his:TitleTag name="Doctor Call Log Report">
</his:TitleTag>

          <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
               
         %>
        
      <bean:define name="DoctorCallBookLogReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="DoctorCallBookLogReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   <%
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
         	System.out.println("dt::::::::::::::::");        	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	System.out.println("dt:::::::::"+dt);        	
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		   	System.out.println("dt::::::::::::::::");        	
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	System.out.println("dt:::::::::"+dt);        	
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
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
		    	
				<td class="tdfonthead" width="25%">
	 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="ward"/></font>
				</td>
				<td class="tdfont" width="25%" >
	               <html:select name="DoctorCallBookLogReportFB"  property="wardCode" tabindex="1" styleClass="regcbo" >
			      	<html:option value="%">All</html:option>
			      	<logic:present name="<%=InpatientConfig.WARD_LIST%>" >
			       	<html:options collection="<%=InpatientConfig.WARD_LIST%>" property="value" labelProperty="label"></html:options>
			       	</logic:present>
				</html:select>
		</td>	 
				<td class="tdfonthead" width="25%">	
					
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="doctor"/></font>
					</td>
					<td class="tdfont" width="25%">
					
				<html:select name="DoctorCallBookLogReportFB"  property="empNo"  style="" tabindex="1" styleClass="regcbo" >
		        	<html:option value="%">All</html:option>
		        	<html:options collection="<%=InpatientConfig.CONSULTANT_LIST%>" property="value" labelProperty="label"></html:options>
				</html:select>  
			</td>
					</tr>
					<tr>
					<td class="tdfonthead" width="25%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="crNo"/></font>
					</td>
					<td class="tdfont" width="25%" >
					<div align="left">
			         <html:text name="DoctorCallBookLogReportFB" property="crNo"  maxlength="13" size="20"
							onkeypress="return validateNumeric(event)">
						</html:text>
						</div>
					</td>
					<td class="tdfonthead" width="25%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="admNo"/></font>
					</td>
						
				<td class="tdfont" width="25%">
					<div align="left">
			         <html:text name="DoctorCallBookLogReportFB" property="admnNo"  maxlength="13" size="20"
							onkeypress="return validateNumeric(event)">
						</html:text>
						</div>
					</td>
	     		 </tr>
        </table>
        	<table width="100%" cellspacing="1" cellpadding="0">
    <tr>   
		<td width="25%" class="tdfonthead">
        	<font color="#FF0000">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          	<bean:message key="reportType"/>      
	        </font>
        </td>                
        <td width="25%" class="tdfont" > 
	        <html:select name="DoctorCallBookLogReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
		       	<html:option value="-1">Select Value</html:option>
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
        		<html:select name="DoctorCallBookLogReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>
<his:status/>



<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="DOCTORCALLBOOKLOGREPORT">
<html:hidden name="DoctorCallBookLogReportFB" property="jrxmlPath" value="<%=InpatientConfig.INPATIENT_JRXML_PATH%>"/>
<html:hidden name="DoctorCallBookLogReportFB" property="jrxmlName"/>
<html:hidden name="DoctorCallBookLogReportFB" property="sysDate" value="<%=tDate%>"/>

