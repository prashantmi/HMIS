<!-- 
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	MRD
## Process/Database Object Name	:	Medical Certificate Request
## Purpose						:	online request raise from OPD Doctor Desk or OPD Bay Desk or IPD Doctor Desk. Doctor provide request slip to patient with complete medical certificate information like rest dates, fitness dates etc.
## Date of Creation				: 	19-November-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 

 -->
 <%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@ page import ="java.util.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<html>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function validateSave()
{
	if(document.getElementsByName("diagnosis")[0].value=="")
	{
		alert("Please Enter the Diagnosis");
		document.getElementsByName("diagnosis")[0].focus();
		return false;
	}
	else if (document.getElementsByName("adviceDays")[0].value=="")
	{
		alert("Please Enter the Advice Days");
		document.getElementsByName("adviceDays")[0].focus();
		return false;
	}
	else if (document.getElementsByName("advisedBy")[0].value=="")
	{
		alert("Please Selest Advised By Doctor");
		document.getElementsByName("advisedBy")[0].focus();
		return false;
	}
	else if (document.getElementsByName("medicalFitnessFlag")[0].value==1)
	{
		if(!(document.getElementsByName("fitness")[0].checked))
		{
			alert("Please Check Fitness Date");
			document.getElementsByName("medicalFitnessFlag")[0].focus();
			return false;
		}
		else {return true;}
	}
	else { return true; }
  
}

function getToDate()
{
	if(document.getElementsByName("adviceDays")[0].value > 0)
		{
			var r = new Date();
		 	var fromDate = document.getElementsByName("fromDate")[0].value;
		 	var res = fromDate.split("-"); 
		 	
		 	r.setDate(res[0]);
		 	//alert(r.getDate());
		 	
		 	r.setMonth(parseInt(getMonthInt(res[1]))-1); 
		 	//alert(r.getMonth());
		 	
		 	r.setYear(res[2]);
		 	//alert(r.getFullYear());
		 	//alert("date"+r.toString());
			var advisedDays= document.getElementsByName("adviceDays")[0].value;
			//alert("advice"+advisedDays);
		 	 r.setDate(parseInt(r.getDate()) + parseInt(advisedDays));
		 	var toDate = r.getDate()+"-"+getMonthStr((parseInt(r.getMonth())))+"-"+r.getFullYear();
			document.getElementById("toDateId").value = toDate;	
			getFitnessDate();
		}
		else{ document.getElementById("toDateId").value = "";}
}
function getFitnessDate()
{ 	
	if(document.getElementsByName("fitness")[0].checked)
	{
		if(document.getElementsByName("adviceDays")[0].value > 0)
		{
			var r = new Date();
		 	var fromDate = document.getElementsByName("fromDate")[0].value;
		 	var res = fromDate.split("-"); 
		 	r.setDate(res[0]);
		 	r.setMonth(parseInt(getMonthInt(res[1]))-1); 
		 	r.setYear(res[2]);
			var advisedDays= document.getElementsByName("adviceDays")[0].value;
		 	 r.setDate(parseInt(r.getDate()) + parseInt(advisedDays)+1);
		 	var toDate = r.getDate()+"-"+getMonthStr((parseInt(r.getMonth())))+"-"+r.getFullYear();
			document.getElementById("fitnessDateId").value = toDate;
		}	
	}
	else{document.getElementById("fitnessDateId").value = "";}
}

//this function converts the month(in integer) into month(in String)
function getMonthStr(int)
{
	var month = "NA";

	switch(int)
	{
		case 0:
			month = "Jan";
			break;
		case 1:
			month = "Feb";
			break;
		case 2:
			month = "Mar";
			break;
		case 3:
			month = "Apr";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "Jun";
			break;
		case 6:
			month = "Jul";
			break;
		case 7:
			month = "Aug";
			break;
		case 8:
			month = "Sep";
			break;
		case 9:
			month = "Oct";
			break;
		case 10:
			month = "Nov";
			break;
		case 11:
			month = "Dec";
			break;
	}
	return month;
}
 function setSelectedFitnessRow(obj)
 {
 	//alert("generateFitness..obj"+obj);
 	document.getElementsByName("selectedFitnessRow")[0].value= obj;
 }
 
 function onLoad()
 {
 	//alert("hello "+document.getElementsByName("medicalFitnessFlag")[0].value);
 	if((document.getElementsByName("medicalFitnessFlag")[0].value)=="1")
 	{
 		document.getElementsByName("fitness")[0].checked= true;
 		document.getElementsByName("fitness")[0].disabled= true;
 		getFitnessDate();
 		
 	}
 }
 
 function clearForm()
 {
	 if((document.getElementsByName("medicalFitnessFlag")[0].value)=="1")
	 	{	
	 		setSelectedFitnessRow('1');	
 			submitForm21('FITNESS');
	 	}
	 else
		 {
		 	submitForm21('NEW');
		 }
 }
</script>
	<body onload="onLoad();">
		<html:form action="/medicalCertificateRequest">
		        <%
			 
        	  String deptcontrol="" ;
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              
         %>


      
      <bean:define name="MedicalCertificateRequestFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="MedicalCertificateRequestFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
		  	System.out.println("tDate:::::::::"+tDate);
		   }
    	%>  
			<his:TransactionContainer>		
			
			<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
			
		<logic:equal property="medicalFitnessFlag" name="MedicalCertificateRequestFB" value="0">	
			<logic:notEmpty name="<%=MrdConfig.PREVIOUS_MEDICAL_CERTIFIATE_DTL %>">
			<his:TitleTag name="Previous Medical Certificate Request">		
				</his:TitleTag>			
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>	
							<td  class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>	<bean:message key="advisedForDiagnosis"/></b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b><bean:message key="status"/></b>
										</font>
								</div>		
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b><bean:message key="advisedDuration"/></b>
										</font>
								</div>	
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b><bean:message key="fitnessDate"/></b>
										</font>
								</div>	
							</td>
							
						</tr>
						<%int i=0; %>
						<logic:iterate name="<%=MrdConfig.PREVIOUS_MEDICAL_CERTIFIATE_DTL %>" id="arrPatDtl" type="hisglobal.vo.PatMedicalDtlVO" indexId="idx">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatDtl" property="diagnosis"/>
									<html:hidden name="arrPatDtl" property="medicalCertificateId"/>
									</font>
								</div>	
							</td>
							<td width="15%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatDtl" property="requestStatus"/>
									</font>
								</div>	
							</td>
							<td width="35%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatDtl" property="fromDate"/> -
									<bean:write name="arrPatDtl" property="toDate"/>
									</font>
								</div>	
							</td>
							<logic:notEmpty name="arrPatDtl" property="fitnessDate" >
								<td width="25%" class="tdfonthead">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrPatDtl" property="fitnessDate"/>
										</font>
									</div>	
								</td>
							</logic:notEmpty>					
							<logic:empty name="arrPatDtl" property="fitnessDate" >
								<td width="25%" class="tdfonthead">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:hidden name="MedicalCertificateRequestFB" property="selectedFitnessRow" value="" />
										<a onclick="setSelectedFitnessRow('<%=i %>'); submitForm21('FITNESS');">Generate Fitness</a>
										</font>
									</div>	
								</td>
							</logic:empty>
						</tr>	
						<% ++i; %>
						</logic:iterate>
					</table>		
				</his:ContentTag>			
			</logic:notEmpty>		
		
			
			<his:TitleTag name="Medical Certificate Request">
			</his:TitleTag>	
			<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="diagonosis"/></b>								
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" style="vertical-align: middle;" colspan="3">
						<div align="left">
							<html:textarea rows="2" cols="50" name="MedicalCertificateRequestFB" property="diagnosis" tabindex="1" style="vertical-align; middle;" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>											
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:message key="procedure"/></b>								
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" style="vertical-align: middle;" colspan="3">
						<div align="left">
							<html:textarea rows="2" cols="50" name="MedicalCertificateRequestFB" property="surgery" tabindex="1" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>											
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">								
								<b>&nbsp;<bean:message key="advisedForDays"/></b>								
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" id="adviceDaysId">
							<html:text name="MedicalCertificateRequestFB" property="adviceDays" tabindex="1" maxlength="100" size="15" onkeypress="return validateNumeric(event)" onchange="getToDate();" style="vertical-align: middle;"/>											
						</div>
					</td>
		 			<td class="tdfonthead" width="25%">
		        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="fromDate"/>
							</font>
		        		</div>
		        		
					</td>
					<td class="tdfont" width="25%">
			    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
							<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" />
						</div>
				 	</td>
				</tr>
				<tr>				
					<td class="tdfonthead" width="25%">
		    			<div id='divtoDate' style='<%=toDateLabel %>' align="right">
		         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="toDate"/>
				 			</font>
		    			</div>
		    		</td>
				
					<td class="tdfont" width="25%">
				 		<div id='divtoDateControl' style='<%=toDateControl %>' align="left"> 
				 		<html:text styleId="toDateId" name="MedicalCertificateRequestFB" property="toDate" tabindex="1" maxlength="100" size="15" style="vertical-align: middle;" readonly="true"/>  
		    	 		</div>
		    	  	</td> 
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<his:checkbox name="MedicalCertificateRequestFB" value="1"  property="fitness" onClick="getFitnessDate()"></his:checkbox>
								<b>&nbsp;<bean:message key="fitnessDate"/></b>								
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" style="vertical-align: middle;">
						<div id='divFitnessDateControl' align="left">
							<html:text styleId="fitnessDateId" name="MedicalCertificateRequestFB" property="fitnessDate" tabindex="1" maxlength="50" size="15" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;" readonly="true"/>											
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="advisedBy"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" colspan="3">
						<div align="left">
							<html:select name="MedicalCertificateRequestFB" property="advisedBy" onclick="checkExisting()" tabindex="1">
								
								<logic:present name="<%=MrdConfig.MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL %>">
									<html:options collection="<%=MrdConfig.MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL %>" property = "value" labelProperty = "label"/>
								</logic:present>
							</html:select>
						</div>
					</td>					
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" colspan="3">
						<div align="left">
							<html:textarea rows="2" cols="50" name="MedicalCertificateRequestFB" property="remarks" tabindex="1" style="vertical-align: middle;" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
						</div>
					</td>
				</tr>					
			</table>	
			</his:ContentTag>		
		</logic:equal>
			
		<logic:equal property="medicalFitnessFlag" name="MedicalCertificateRequestFB" value="1">
			<his:TitleTag name="Fitness Certificate Request">
			</his:TitleTag>	
			<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="diagonosis"/></b>								
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" style="vertical-align: middle;" colspan="3">
						<div align="left">
							<html:textarea rows="2" cols="50" name="MedicalCertificateRequestFB" property="diagnosis" tabindex="1" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;" readonly="true" />											
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:message key="procedure"/></b>								
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" style="vertical-align: middle;" colspan="3">
						<div align="left">
							<html:textarea rows="2" cols="50" name="MedicalCertificateRequestFB" property="surgery" tabindex="1" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;" readonly="true"/>											
						</div>
					</td>
				</tr>
				
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">								
								<b>&nbsp;<bean:message key="advisedForDays"/></b>								
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" id="adviceDaysId">
							<html:text name="MedicalCertificateRequestFB" property="adviceDays" tabindex="1" maxlength="100" size="15" onkeypress="return validateNumeric(event)" onchange="getToDate();" style="vertical-align: middle;" readonly="true"/>											
						</div>
					</td>
		 			<td class="tdfonthead" width="25%">
		        		<div id='divfromDate' align="right">
			        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
			        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="fromDate"/>
							</font>
		        		</div>
		        		
					</td>
					<td class="tdfont" width="25%">
			    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
							<html:text name="MedicalCertificateRequestFB" property="fromDate" tabindex="1" maxlength="100" size="15" style="vertical-align: middle;" readonly="true"/>
						</div>
				 	</td>
				</tr>
				<tr>				
					<td class="tdfonthead" width="25%">
		    			<div id='divtoDate' align="right">
		         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="toDate"/>
				 			</font>
		    			</div>
		    		</td>
				
					<td class="tdfont" width="25%">
				 		<div id='divtoDateControl' style='<%=toDateControl %>' align="left"> 
				 		<html:text styleId="toDateId" name="MedicalCertificateRequestFB" property="toDate" tabindex="1" maxlength="100" size="15" style="vertical-align: middle;" readonly="true"/>  
		    	 		</div>
		    	  	</td> 
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<his:checkbox name="MedicalCertificateRequestFB" value="1"  property="fitness" onClick="getFitnessDate()" readonly="true"></his:checkbox>
								<b>&nbsp;<bean:message key="fitnessDate"/></b>								
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" style="vertical-align: middle;">
						<div id='divFitnessDateControl' align="left">
								<html:text styleId="fitnessDateId" name="MedicalCertificateRequestFB" property="fitnessDate" tabindex="1" maxlength="50" size="15" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;" readonly="true"/>											
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="advisedBy"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" colspan="3">
						<div align="left">
							<html:select name="MedicalCertificateRequestFB" property="advisedBy" onclick="checkExisting()" tabindex="1">
								
								<logic:present name="<%=MrdConfig.MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL %>">
									<html:options collection="<%=MrdConfig.MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL %>" property = "value" labelProperty = "label"/>
								</logic:present>
							</html:select>
						</div>
					</td>					
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" colspan="3">
						<div align="left">
							<html:textarea rows="3" cols="50" name="MedicalCertificateRequestFB" property="remarks" tabindex="1" style="vertical-align: middle;" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
						</div>
					</td>
				</tr>			
			</table>
			</his:ContentTag>
		</logic:equal>
			
			
			<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateSave()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm21('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="clearForm();" onkeypress="if(event.keyCode==13) clearForm();">
			</his:ButtonToolBarTag>
			 <html:hidden name="MedicalCertificateRequestFB" property="medicalFitnessFlag" />
			 <html:hidden name="MedicalCertificateRequestFB" property="fitnessDate" />
			 <html:hidden name="MedicalCertificateRequestFB" property="hmode" />
			 <html:hidden name="MedicalCertificateRequestFB" property="episodeCode" />
			 <html:hidden name="MedicalCertificateRequestFB" property="episodeVisitNo" />
			 <html:hidden name="MedicalCertificateRequestFB" property="departmentUnitCode" />
			 <html:hidden name="MedicalCertificateRequestFB" property="admissionNo" />			
			 <html:hidden name="MedicalCertificateRequestFB" property="strCategoryCode" />
			 <html:hidden name="MedicalCertificateRequestFB" property="wardCode" />
			 <html:hidden name="MedicalCertificateRequestFB" property="medicalCertificateId" />
			 <html:hidden name="MedicalCertificateRequestFB" property="strPatName" />
			 <html:hidden name="MedicalCertificateRequestFB" property="selectedFitnessRow" />
			 
			 
			 
			</his:TransactionContainer>
		</html:form>
	</body>		
			
</html>			