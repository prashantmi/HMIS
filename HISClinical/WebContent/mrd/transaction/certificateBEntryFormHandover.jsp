<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Manisha Gangwar
 ## Module Name					: MRD
 ## Process/Database Object Name:Certificate B Entry Form  handover
 ## Purpose						:Certificate B Entry Form handover Process
 ## Date of Creation			:20-Oct-2015 

 -->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.MrdConfig"%>
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

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">


function openPopFileUpload(event)
{
	
	openPopup('<his:path src='registration/uploadFile.cnt'/>',event,300,600);
}

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];
	elmt.value=mode;
    document.forms[0].submit();
}

function hideToRelationship()
{	
	var hanoverToVal= document.getElementsByName("handoverTo")[0].value;
//alert(hanoverToVal);
        
	if (hanoverToVal==1)
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
	var hanoverToVal= document.getElementsByName("handoverTo")[0].value;
	var billNo=document.getElementsByName("billNo")[0].value;
	var relationship=document.getElementsByName("relationship")[0].value;
	var handoverToName=document.getElementsByName("handoverToName")[0].value;
	var handoverTOAddress=document.getElementsByName("handoverToAddress")[0].value;
	
	
	if(hanoverToVal==1)
	{
		if(relationship=="-1")
		{
			document.getElementsByName("relationship")[0].focus();
			//document.forms[0].relationCode[0].focus();
			alert("Please Select Relationship");
			return false;
		}
		else if(handoverToName=="")
		{
			document.getElementsByName("handoverToName")[0].focus();
			//document.forms[0].relativeName[0].focus();
			alert("Please Enter Relative Name");
			return false;
		}
		else if(handoverTOAddress=="")
		{
			document.getElementsByName("handoverToAddress")[0].focus();
			//document.forms[0].relativeAddr[0].focus();
			alert("Please Enter Relative Address");
			return false;
		}
		/* else if(billNo==0)		
		{
			alert("Billing Not Done. Can't Issue the Certificate.");
			return false;
		} */
		else
		{
			return true;			
		}
	}
	else if(hanoverToVal=="-1")		
	{
		alert("Please Select HandOver To");
		return false;
	}
	/* else if(billNo==0)		
	{
		alert("Billing Not Done. Can't Issue the Certificate.");
		return false;
	} */
	else
	{
		return true;
	}
}




window.onload=hideToRelationship;


</script>

<html:form action="/certificateBEntryForm">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>  
	
	<his:TitleTag name="Certificate B Entry Form Handover">	
	</his:TitleTag>
	<bean:define id="patCrNo" name="CertificateBEntryFormFB" property="patCrNo" type="java.lang.String" ></bean:define>
	
		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true">
	    <jsp:param name="patCrNo" value="<%=patCrNo %>" />
	</jsp:include>
	
	<%-- <logic:equal name="CertificateBEntryFormFB" property="mode" value="">
	<his:SubTitleTag name="Patient Recorded Details">
	</his:SubTitleTag>
	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
	<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="patientName"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" colspan="3">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="patName"/>
							</font>
						</div>	
					</td>	
					 <td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="age"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" colspan="3">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="patAge"/>
							</font>
						</div>	
					</td>
					</tr>
					<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="gender"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" colspan="3">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="patGenderCode"/>
							</font>
						</div>	
					</td>	
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="contactNo"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" colspan="3">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="patContactNo"/>
							</font>
						</div>	
					</td>
					</tr>
				
	</table>
	</logic:equal> --%>
	
	<his:SubTitleTag name="Certificate Detail">
	</his:SubTitleTag>
    
    	<his:ContentTag>
    		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<%-- <td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recordType"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="recordType"/>
							</font>
						</div>	
					</td>	
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recordDesc"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="recordDesc"/>
							</font>
						</div>	
					</td>
					</tr>
					<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="reqBy"/>
							</b>
							</font>
						</div>	
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="requestBy"/>
							</font>
						</div>	
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="name"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="requestByName"/>
							</font>
						</div>	
					</td>
					</tr>
					<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="reqReason"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="reqReason"/>
							</font>
						</div>	
					</td> --%>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="approvedBy"/>
							</b>
							</font>
						</div>	
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="approvedBy"/>
							</font>
						</div>	
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="remarks"/>
							</b>
							</font>
						</div>	
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="CertificateBEntryFormFB" property="remarks"/>
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
					
					<logic:equal name="CertificateBEntryFormFB" property="billNo" value="0">
						<td width="25%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="billingNotDOne"/> 
								</font>
							</div>	
						</td>			
					</logic:equal>
					
					<logic:notEqual name="CertificateBEntryFormFB" property="billNo" value="0">
						<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="billingDone"/> <bean:write name="CertificateBEntryFormFB" property="billNo"/>
									</font>
								</div>	
							</td>	
					</logic:notEqual>
				
				<td width="10%"  class="tdfonthead" nowrap="nowrap" >
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="uploadFile"/>
				</font>
				</div>
				</td>
	  			
	  			
	  			<td width="10%"  class="tdfont" nowrap="nowrap" >
	  			<div align="left">
				<img class="button"  class="button" src="/HIS/hisglobal/images/plus.gif"  border="0" tabindex="1" style="cursor:pointer" onkeypress="if(event.keyCode==13)openPopFileUpload(event);" onClick="openPopFileUpload(event);" >
				</div>
			</td></tr>
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
						 <html:select name="CertificateBEntryFormFB" property="handoverTo" tabindex="1" onchange="hideToRelationship()">
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
				         	<html:select name="CertificateBEntryFormFB" property="relationship">
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
				         <html:text property="handoverToName" name="CertificateBEntryFormFB" maxlength="60" tabindex="1" onkeypress="return validateAlphaOnly(this,event)"  ></html:text>
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
				         <html:textarea rows="3" cols="45"   name="CertificateBEntryFormFB" property ="handoverToAddress"  tabindex="1" style="vertical-align: middle;" 
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
				         <html:text property="handoverToContact" name="CertificateBEntryFormFB" maxlength="30" onkeypress="return validateNumeric(event)"  tabindex="1" ></html:text>
				         </div>
					 </td>
			 </tr>	
			</table>		
    	</his:ContentTag>
    	<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateSave()) submitForm('SAVEMODIFY')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVEMODIFY')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('GETDTL')" onkeypress="if(event.keyCode==13) submitForm('GETDTL')">
    	</his:ButtonToolBarTag>
    
    <html:hidden name="CertificateBEntryFormFB" property="hmode"/>
    <html:hidden name="CertificateBEntryFormFB" property="patCrNo"/>
    <html:hidden name="CertificateBEntryFormFB" property="patName"/>
     <html:hidden name="CertificateBEntryFormFB" property="approvedBy"/>
     <html:hidden name="CertificateBEntryFormFB" property="certificateId"/>
     <html:hidden name="CertificateBEntryFormFB" property="recordType"/>
     <html:hidden name="CertificateBEntryFormFB" property="billNo"/>
     <html:hidden name="CertificateBEntryFormFB" property="quantity"/>
      <html:hidden name="CertificateBEntryFormFB" property="selectRecord"/>
      <html:hidden name="CertificateBEntryFormFB" property="mode"/>
      <html:hidden name="CertificateBEntryFormFB" property="reqNo"/>
      <html:hidden name="CertificateBEntryFormFB" property="hospitalCode"/>
      
     
</body>
<his:status/>
</html:form>    