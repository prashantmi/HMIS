<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate issue after Request
 ## Purpose						:Certificate Issue Process
 ## Date of Creation			:05-Dec-2014 

 -->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="mrd.vo.EstimateCertificateIssueVO"%>
<%@page import="hisglobal.vo.ValueObject"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js" />


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

function hideToRelationship()
{	
	if ((document.getElementsByName("givenBy")[0].value)==1)
	{
		document.getElementById("relationShipId").style.display="";
		document.getElementById("contactId").style.display="";	
	}
	else
	{
		document.getElementById("relationShipId").style.display="none";;
		document.getElementById("contactId").style.display="none";	
	}
	
}
function validateSave()
{
	if(document.getElementsByName("givenBy")[0].value==1)
	{
		if(document.getElementsByName("relationCode")[0].value=="-1")
		{
		//document.forms[0].relationCode[0].focus();
		alert("Please Select Relationship");
		return false;
		}
		else if(document.getElementsByName("relativeName")[0].value=="")
		{
		//document.forms[0].relativeName[0].focus();
		alert("Please Enter Relative Name");
		return false;
		}
		else if(document.getElementsByName("relativeAddr")[0].value=="")
		{
		//document.forms[0].relativeAddr[0].focus();
		alert("Please Enter Relative Address");
		return false;
		}
		else if(document.getElementsByName("billNo")[0].value==0)		
		{
			alert("Billing Not Done. Can't Issue the Certificate.");
			return false;
		}
		else
		{
		return true;			
		}
	}
	else if(document.getElementsByName("givenBy")[0].value=="-1")		
	{
		alert("Please Select HandOver To");
		return false;
	}
	else if(document.getElementsByName("billNo")[0].value==0)		
	{
		alert("Billing Not Done. Can't Issue the Certificate.");
		return false;
	}
	else
	{
		return true;
	}
}

function clearForm()
{
	document.getElementsByName("relationCode")[0].value="-1";
	 document.getElementsByName("relativeName")[0].value="";
	 document.getElementsByName("relativeAddr")[0].value="";
	 document.getElementsByName("relativeContactNo")[0].value="";
	 
	}

function openCertificateIssuePopup(e)
{
	//var path="/HISClinical/mrd/estimateCertificateIssue.cnt?hmode=POPUP";
	//
	var path=' /HISClinical/mrd/medicalCertificate.cnt?hmode=POPUPCERT&patCrNo='+document.getElementsByName("patCrNo")[0].value
	+'&certificateNo='+document.getElementsByName("certificateId")[0].value
	+'&templateid='+2010016
	openPopup(createFHashAjaxQuery(path),e,300,700);
}


window.onload=hideToRelationship;


</script>

<html:form action="/medicalCertificate">
<body>  
	
	<his:TitleTag name="Medical and Fitness Certificate Issue">	
	</his:TitleTag>
	<bean:define id="patCrNo" name="medicalCertificateFB" property="patCrNo" type="java.lang.String" ></bean:define>
		<bean:define id="certificateId" name="medicalCertificateFB" property="certificateId" type="java.lang.String" ></bean:define>
		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true">
	    <jsp:param name="patCrNo" value="<%=patCrNo %>" />
	</jsp:include>
	
	
	<his:SubTitleTag name="Request Detail">
	</his:SubTitleTag>
    
    	<his:ContentTag>
    		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="diagnosis"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="75%" class="tdfonthead" colspan="3">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="medicalCertificateFB" property="diagnosis"/>
							</font>
						</div>	
					</td>
				</tr>
				<tr>	
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="procedure"/>
								</b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfonthead" colspan="3">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="medicalCertificateFB" property="surgery"/>
							</font>
						</div>	
					</td>
					</tr>
					<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="advisedForDays"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="medicalCertificateFB" property="adviceDays"/>
							</font>
						</div>	
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="duration"/>
							</b>
							</font>
						</div>	
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="medicalCertificateFB" property="duration"/>
							</font>
						</div>	
					</td>
					</tr>
					<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="advisedBy"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="medicalCertificateFB" property="advisedBy"/>
							</font>
						</div>	
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="fitnessDate"/>
							</b>
							</font>
						</div>	
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="medicalCertificateFB" property="fitnessDate"/>
							</font>
						</div>	
					</td>
					</tr>
					<tr>
					
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="billingStatus"/>
								</b>
							</font>
						</div>
					</td>
					
					<logic:equal name="medicalCertificateFB" property="billNo" value="0">
						<td width="25%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="billingNotDOne"/> 
								</font>
							</div>	
						</td>			
					</logic:equal>
					
					<logic:notEqual name="medicalCertificateFB" property="billNo" value="0">
						<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="billingDone"/> <bean:write name="medicalCertificateFB" property="billNo"/>
									</font>
								</div>	
							</td>	
					</logic:notEqual>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="certificateGeneration"/>
								</b>
							</font>
						</div>
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">						
							<% String Path="/HISClinical/mrd/medicalCertificate.cnt?hmode=POPUPCERT&patCrNo="+patCrNo+"&templateId="+2010016+"&certificateNo="+certificateId;%>
								<a style="cursor:pointer" onclick="openPopup(createFHashAjaxQuery('<%=Path%>'),event,300,600)" >
								Generate Certificate</a>
							</font>
						</div>	
					</td>
				</tr>
				<logic:notEqual name="medicalCertificateFB" property="recordType" value="12">
					<tr>
						<td width="25%"  class="tdfonthead">
							<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="patient"/>&nbsp;&nbsp;<bean:message key="designation"/>
							</b>
							</font>
							</div>
				  		</td>
				  		<td width="25%" class="tdfont">
					         <div align="left">	
					         <html:text property="patDesignation" name="medicalCertificateFB" maxlength="50" tabindex="1" onkeypress="return validateAlphaOnly(this,event)"  ></html:text>
					         </div>
						 </td>
						 <td width="25%"  class="tdfonthead">
							<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="patient"/>&nbsp;&nbsp;<bean:message key="organisation"/>
							</b>
							</font>
							</div>
				  		</td>
				  		<td width="25%" class="tdfont">
					         <div align="left">	
					        <html:textarea rows="2" cols="35"   name="medicalCertificateFB" property ="patOrganisation"  tabindex="1" style="vertical-align: middle;" 
										onkeypress="return (validateTextArea(event,this,'100') && validateAlphaOnly(this,event))"/>
					         </div>
						 </td>
					</tr>
				</logic:notEqual>
		 </table>
		 <his:SubTitleTag name="Handover Detail">
		 </his:SubTitleTag>	
		 <table width="100%" border="0"  cellspacing="1" cellpadding="0">		
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="handoverto"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						 <html:select name="medicalCertificateFB" property="givenBy" tabindex="1" onchange="hideToRelationship()">
						<html:option value="0">Patient</html:option>
						<html:option value="1">Relative</html:option>
						</html:select>
					</td>
					<td width="50%" class="tdfont" colspan="2">						 
					</td>				
				</tr>
					
				<tr id="relationShipId"  style="display: none;">
				
					<td width="25%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
						<font color="#FF0000">*</font>
						<bean:message key="realtionship"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="25%" class="tdfont">
				         <div align="left">	
				         	<html:select name="medicalCertificateFB" property="relationCode">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.ALL_RELATIONSHIP_LIST%>" >
								<html:options collection="<%=OpdConfig.ALL_RELATIONSHIP_LIST%>" property="value" labelProperty="label"/>
								</logic:present>
							</html:select>
				         </div>
					 </td>
					<td width="25%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
						<font color="#FF0000">*</font>
						<bean:message key="relativename"/>
						</b>
						</font>
						</div>
			  		</td>
			  		<td width="25%" class="tdfont">
				         <div align="left">	
				         <html:text property="relativeName" name="medicalCertificateFB" maxlength="60" tabindex="1" onkeypress="return validateAlphaOnly(this,event)"  ></html:text>
				         </div>
					 </td>
			</tr>
			<tr id="contactId" style="display: none;">
				<td width="25%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
						<font color="#FF0000">*</font>
						<bean:message key="AddressOfRelative"/>
						</b>
						</font>
						</div>
			  		</td>
			  		<td width="25%" class="tdfont">
				         <div align="left">	
				         <html:textarea rows="3" cols="45"   name="medicalCertificateFB" property ="relativeAddr"  tabindex="1" style="vertical-align: middle;" 
									onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericWithDotsOnly(event))"/>				         
				         </div>
					 </td>
				  	<td width="25%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="relativePhoneNo"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="25%" class="tdfont">
				         <div align="left">	
				         <html:text property="relativeContactNo" name="medicalCertificateFB" maxlength="30" onkeypress="return validateNumeric(event)"  tabindex="1" ></html:text>
				         </div>
					 </td>
			 </tr>	
			</table>		
    	</his:ContentTag>
    	<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateSave()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm21('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
    	</his:ButtonToolBarTag>
    
    <html:hidden name="medicalCertificateFB" property="hmode"/>
    <html:hidden name="medicalCertificateFB" property="patCrNo"/>
     <html:hidden name="medicalCertificateFB" property="advisedBy"/>
     <html:hidden name="medicalCertificateFB" property="certificateId"/>
       <html:hidden name="medicalCertificateFB" property="recordType"/>
     <html:hidden name="medicalCertificateFB" property="billNo"/>
     <html:hidden name="medicalCertificateFB" property="quantity"/>
      <html:hidden name="medicalCertificateFB" property="selectRecord"/>
      <html:hidden name="medicalCertificateFB" property="templateId"/>
     
</body>
<his:status/>
</html:form>    