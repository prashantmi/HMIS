<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,mrd.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript  src="/registration/js/reportsValidation.js"/>
<!--<his:javascript src="/opd/opdJs/opd.js"/>-->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<script>

	function submitPage(mode)
	{
		//alert("inside submitPage");
		elmt=document.getElementsByName("reportMode")[0];  
	    elmt.value=mode;
	    document.forms[0].submit();
	}
	function validateFromOrToDate(){
		  //   alert("inside validateFromOrToDate");
		     success=false;        	   
		           
		               valFromDate=document.getElementsByName('fromDate')[0];
			           valToDate=document.getElementsByName('toDate')[0];
			           valSysDate=document.getElementsByName('sysDate')[0];
		      
		               if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
		               {
		                success=true;
		               }             
		           return success;        
		    }
	
	function showUnit()
	{
		//val=document.getElementsByName("unit")[0].value='%';
		
 		//document.getElementById("divPatControl").innerHTML='<html:select name="admissionAndDischargeStaticReportFB" property="patPrimaryCatCode" tabindex="1"  styleClass ="regcbo"><html:option value="%">All</html:option></html:select>';
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
	
	function admissionAndDischargeStaticReportHandler()
	{
		//alert("inside admissionAndDischargeStaticReportHandler");
		document.getElementsByName('strDeptName')[0].value=document.getElementsByName('departmentCode')[0].options[document.getElementsByName('departmentCode')[0].selectedIndex].text;
		document.getElementsByName('strUnitName')[0].value=document.getElementsByName('unit')[0].options[document.getElementsByName('unit')[0].selectedIndex].text;
		
		
		success=false;
		if(document.getElementsByName('hospitalCode')[0].value==0 )
		{
			alert("Please Select the hospital");
			document.getElementsByName('hospaitalCode')[0].focus();
			return false;
		}
		if(validateGraphOrText())
	    { 
			if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
			{
		    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
		        if(validateTextual())
		        {      
		        	if(validateFromOrToDate())
		        	{
		           		return true;           	   
		           	}          	  
	            }
	        }//end of selection is for text based report	
	        if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
			{
		    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
		        if(validateGraphForAgeWiseReport())
		        {
			    	if(validateFromOrToDate())        
			        	success=true;	                
		        }
	        }//end of selection is for graph based report	 
	    }
		
		
		
		return success;
	}//end of function admissionAndDischargeStaticReportHandler()

</script>

<his:TitleTag name="Admission And Discharge Static Report">
</his:TitleTag>

         <%
			  String deptlabel="" ;
        	  String deptcontrol="" ;
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              
         %>


      
      <bean:define name="admissionAndDischargeStaticReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="admissionAndDischargeStaticReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
	<table width="100% cellspacing="1" cellpadding="0">
		<tr>
			<!-- New Change add Hospital Combo -->
		      <td width="25%" class="tdfonthead">
					<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="hospital" /> </font>
					</div>	
					</td>
					
				<td class="tdfont" width="25%">
					<div >
					<html:select name="admissionAndDischargeStaticReportFB" property="hospitalCode" tabindex="1">
						<html:option value="0">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>"
								property="value" labelProperty="label" />
						</logic:present>
					</html:select></div>
					
				</td>
				<td class="tdfonthead" width="25%" colspan="2"></td>
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
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
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
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" />
    	 		</div>
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
    		<td class="tdfont">
       			<div id="deptControl" align="left"  style='<%=deptcontrol%>'>
     		 		<html:select name="admissionAndDischargeStaticReportFB"  property="departmentCode"  tabindex="1"  styleClass ="regcbo" onchange="showUnit()">   
					  <html:option value="%">All</html:option>
					  <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" >
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 
	   			</div>       
    		</td>
 	
<%-- <his:statusTransactionInProcess> --%>
    		<td class="tdfonthead">
    			<div id="unitLabel" align="right" >
    				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>
    			</div>
    		</td>
    		<td class="tdfont">
    			<div id="unitControl" align="left" >
    				<html:select name="admissionAndDischargeStaticReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" >
						<html:option value="%">All</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" >
						  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" property = "value" labelProperty = "label"/>
						</logic:present>
					</html:select>
				</div>			  
    		</td>
<%-- </his:statusTransactionInProcess> --%>	
 		</tr>
 		
 		<tr>
 			
 			
 			
 			<td class="tdfonthead" width="25%">
 				<div id="divPat" style="display: block;" align="right">
 					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="patCat"/>
		 			</font>
 				</div>
 			</td>
 			<td class="tdfont" width="25%">
 				<div id="divPatControl" style="display: block;" align="left">
 					<html:select name="admissionAndDischargeStaticReportFB" property="patPrimaryCatCode" tabindex="1"  styleClass ="regcbo">
 						<html:option value="%">All</html:option>
 						<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY%>" >
 						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY %>" property = "value" labelProperty = "label"/>
 						</logic:present>
 					</html:select>
 				</div>
 			</td>
 			<td class="tdfonthead" width="25%" colspan="2"></td>
 		</tr>
		
 	</table>
</his:ContentTag> 		


<his:SubTitleTag name="Report Generation Option">
</his:SubTitleTag>

<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
    	<tr>       
        	<td width="25%" class="tdfonthead">
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="reportType"/>      
	        	</font>
        	</td>                
        	<td width="25%" class="tdfont" > 
	        	<html:select name="admissionAndDischargeStaticReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="admissionAndDischargeStaticReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>
<his:status/>

<html:hidden name="admissionAndDischargeStaticReportFB" property="reportMode" />
<html:hidden name="admissionAndDischargeStaticReportFB" property="mode" value="ADMISSIONANDDISCHARGESTATICREPORT" />
<html:hidden name="admissionAndDischargeStaticReportFB" property="jrxmlName" value="<%=MrdConfig.MRD_ADMISSION_DISCHARGE_STATIC_DATEWISE %>"/>
<html:hidden name="admissionAndDischargeStaticReportFB" property="jrxmlPath" value="<%=MrdConfig.MRD_JRXML_PATH%>"/>
<html:hidden name="admissionAndDischargeStaticReportFB" property="sysDate" value="<%=tDate%>"/>
<html:hidden name="admissionAndDischargeStaticReportFB" property="strDeptName" />
<html:hidden name="admissionAndDischargeStaticReportFB" property="strUnitName" />
<html:hidden name="admissionAndDischargeStaticReportFB" property="label" />

