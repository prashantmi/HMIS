<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="registration.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<script>
	
	
	
	
function validateReportDate()
{
					if(validateDate(document.getElementsByName('toDate')[0].value))
					{	
						valid=true;
					}	
					else
					{
						alert("To Date Can Not Be Greater Than System Date");
						valid=false;
						return valid;
					}
					if(validateFrmToDate(document.getElementsByName('fromDate')[0].value))
					{	
						valid=true;
					}	
					else
					{
						alert("From Date Can Not Be Greater Than To Date");
						valid=false;
					}
					

	return valid;		
}

function validateDate(obj)
{
	valid=true;
	var b=document.getElementsByName("sysDate")[0].value;
	
    var aArray=obj.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
      
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
     day=(adate-bdate)/86400000;
    
     if(day>0)
    	return false;
    else
    	return true;	
}
	
function validateFrmToDate(obj)
{
	valid=true;
	var b=document.getElementsByName("toDate")[0].value;
	
    var aArray=obj.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
      
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
     day=(adate-bdate)/86400000;
    
     if(day>0)
    	return false;
    else
    	return true;	
}
	function patWiseVisitDtlReportHandler()
	{
		
		var valid ='';
		if(document.getElementsByName('crOrMcts')[0].checked){
			valid = validateCrNo();
			
		}
		else
		{ 
			valid= validateMCTSNo();
			
		}
					
		
		if(valid==false)
		{
		return false;
		}
		else
		valid=validateReportDate();
		if(valid==false)
		{
		return false;
		} 
		document.getElementsByName("reportMode")[0].value="<%=Config.TEXT%>";
			var patCrNo=document.getElementsByName("patCrNo")[0].value;
			var len=patCrNo.length;
				return true;
				
	}
	
	function validateCrNo(){
		if(validateCRNoCHECK('<%=Config.CR_NO_FORMAT_SIZE%>')){ 
			return true;
		}
		else{
			return false;
		}	
	
	
	}
	function validateMCTSNo(){
	if(document.getElementsByName('strMctsNo')[0].value=="")
	{
		alert("Please enter MCTS No.")
		return false;
	}
	if(document.getElementsByName('strMctsNo')[0].value.length!=18)
	{
		alert("Please enter MCTS No. of 18 digits")
		return false;
	}
	
	
	}
	function showCrNoDiv()
	{
	document.getElementById("crdivid").style.display="";
	document.getElementById("mctsNo").style.display="none";
	}		
	function showMctsNoDiv()
	{
	document.getElementById("crdivid").style.display="none";
	document.getElementById("mctsNo").style.display="";
	}	
</script>
	
	<his:TitleTag name="Patient Wise Visit Detail Report " >
	</his:TitleTag>
	
		<%
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String crOrMcts="" ;

         %>
 <%
	String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
	%>
	
	<bean:define name="PatWiseVisitDtlReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="PatWiseVisitDtlReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
	 
	
	  <%
         if(frDate==null||frDate.equalsIgnoreCase("")){  
         	System.out.println("dt::::::::::::::::");        	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	System.out.println("dt:::::::::"+dt);        	
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase("")){  
		   	System.out.println("dt::::::::::::::::");        	
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	System.out.println("dt:::::::::"+dt);        	
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    
		%>  
	<his:SubTitleTag name="Report Details">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<bean:define name="PatWiseVisitDtlReportFB" property="sysDate" id="sysDate" type="java.lang.String" />
	<%

		if(sysDate==null||sysDate.equalsIgnoreCase(""))
		{
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
	%>
		<html:hidden name="MCTSRegistrationFB" property="sysDate" value="<%=sysDate%>"/>
	
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
			
 			<td class="tdfonthead" nowrap width="15%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="crNo" />
		      	</font>
		      		<html:radio name="PatWiseVisitDtlReportFB" property="crOrMcts" tabindex="1" value="0" onclick="showCrNoDiv()"/>
		      		</td>
		      	<td class="tdfonthead" nowrap width="15%">
		      		 
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="mctsNo"/></font> 
		        	<html:radio name="PatWiseVisitDtlReportFB" property="crOrMcts" tabindex="1" value="1" onclick="showMctsNoDiv()"/>
		        	</td>
			
				
				<td width="25%" class="tdfont">
					
						<logic:notEqual name="PatWiseVisitDtlReportFB" property="reportMode" value="GO">
						 <%String size=Integer.toString(Integer.parseInt(Config.CR_NO_FORMAT_SIZE)+2); %>
						 <div id="crdivid" style='<%=crOrMcts %>'>
							<html:text name="PatWiseVisitDtlReportFB" property="patCrNo" tabindex="1" maxlength="<%=Config.CR_NO_FORMAT_SIZE %>" size="<%=size %>" value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>" onkeypress="if(event.keyCode==13) return validateCrNo();  else return validateNumeric(event);">
							</html:text>(CR No.)
							</div>
						</logic:notEqual>
						
						 <div id="mctsNo" style='display:none' >
							<html:text name="PatWiseVisitDtlReportFB" property="strMctsNo" tabindex="1" maxlength="18" onkeypress="validateAlphaNumericWithSpecialCharacterOnly(event);">
							</html:text>(MCTS No.)
							</div>
						
				</td>
						
				
			<td width="25%" class="tdfonthead">
				</td>
				<td width="25%" class="tdfont">
				</td>
		</tr>
			
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
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value=""/>
				</div>
			</td>
				
			<td class="tdfonthead" width="25%">
    			<div id='divtoDate' style='<%=toDateLabel %>' align="right">
         			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="toDate"/>
		 			</font>
    			</div>
			</td>
		
			<td class="tdfont" width="25%">
		 		<div id='divtoDateControl' style='<%=toDateControl %>' align="left">    
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="" />
    	 		</div>
			</td>                 			
			
			
			</tr>
				</table>
		<table width="100%" cellspacing="1" cellpadding="0">
				
		<tr>            
 			<td class="tdfonthead" nowrap width="50%" align="center">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		Group by Department Unit
		      		<!--
		      		 and order by Department Unit
		      	-->
		      	</font>
		      	<html:radio name="PatWiseVisitDtlReportFB" property="choice" tabindex="1" value="0" /> 
		     </td>
		     <td class="tdfonthead" nowrap width="50%" align="center">
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		
		       	   Group by Date
		       	
		       		</font> 
		        	<html:radio name="PatWiseVisitDtlReportFB" property="choice" tabindex="1" value="1" />
			</td> 
 		</tr>
		</table>
	
	
		
		
		
	</his:ContentTag>
<html:hidden name="PatWiseVisitDtlReportFB" property="reportMode"/>
<html:hidden name="PatWiseVisitDtlReportFB" property="mode" value="PATWISEVISITDTLRPT"/>
<html:hidden name="PatWiseVisitDtlReportFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH_REPORT1%>"/>
<html:hidden name="PatWiseVisitDtlReportFB" property="jrxmlName"/>
<html:hidden name="PatWiseVisitDtlReportFB" property="patCrNo"/>
<html:hidden name="PatWiseVisitDtlReportFB" property="hmode" value="unspecified"/>
<input type="hidden" name="fromHour" > 
<input type="hidden" name="fromMin" > 
<input type="hidden" name="toHour" > 
<input type="hidden" name="toMin" > 
	