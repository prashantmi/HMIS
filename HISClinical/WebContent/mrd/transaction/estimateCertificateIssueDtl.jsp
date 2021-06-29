<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate issue after Request
 ## Purpose						:Certificate Issue Process
 ## Date of Creation			:22-Nov-2014 

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

function hideForRelationship()
{	
	if ((document.getElementsByName("givenBy")[0].value)==1)
	{
		document.getElementById("relationShipId").style.display="";
		document.getElementById("contactId").style.display="";	
	}
	else
	{
		document.getElementById("relationShipId").style.display="none";
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
	var path='/HISClinical/mrd/estimateCertificateIssue.cnt?hmode=POPUP&strHiddenPatDtl='+document.getElementsByName('strHiddenPatDtl')[0].value;
	
	openPopup(createFHashAjaxQuery(path),e,300,700);
}


window.onload=hideForRelationship;


</script>

<html:form action="/estimateCertificateIssue">
<body>  
	
	<his:TitleTag name="Estimate Certificate Issue">	
	</his:TitleTag>
	<bean:define id="patCrNo" name="EstimateCertificateIssuetFB" property="patCrNo" type="java.lang.String" ></bean:define>
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
									<bean:message key="requestedProcedure"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%-- <bean:write name="EstimateCertificateIssuetFB" property="tariffName"/>
						 --%>	
						 <logic:present name="<%=MrdConfig.ALL_REQUESTED_TARIFFS_LIST %>" >
						 	<logic:iterate name="<%=MrdConfig.ALL_REQUESTED_TARIFFS_LIST %>" id="arrPatDtl" type="mrd.vo.EstimateCertificateIssueVO" indexId="idx">
						 	<bean:write name="arrPatDtl" property="tariffName"/> <br/> 
				            </logic:iterate>
						 </logic:present>
						 
						 </font>
						</div>	
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="requestedDate"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="EstimateCertificateIssuetFB" property="requestedDate"/>
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
							<bean:write name="EstimateCertificateIssuetFB" property="advisedBy"/>
							</font>
						</div>	
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="billingStatus"/>
								</b>
							</font>
						</div>
					</td>
				
					<% 
					String str="";	
					EstimateCertificateIssueVO[] billNoQtyVOArr=(EstimateCertificateIssueVO[])session.getAttribute(MrdConfig.ESTIMATE_CERTIFIATE_ISSUE_PAT_DTL);
					if(billNoQtyVOArr!=null)
					{
					EstimateCertificateIssueVO vobillNoQty =(EstimateCertificateIssueVO)billNoQtyVOArr[0];
					String BillNo=vobillNoQty.getBillnoqty();
					String val[]=BillNo.replace("^","#").split("#");
					String deptUnitCode=vobillNoQty.getDeptUnitCode();
					String wardCode=vobillNoQty.getWardCode();
					//System.out.println("billNoQty"+billNoQty);
					if(val[0].equals("0"))
					{
					%>
					 <html:hidden name="EstimateCertificateIssuetFB" property="deptUnitCode" value="<%=deptUnitCode %>" />
	 					<html:hidden name="EstimateCertificateIssuetFB" property="wardCode" value="<%=wardCode %>" />
					<td width="25%" class="tdfonthead">
						
					  <html:hidden name="EstimateCertificateIssuetFB" property="billNo"  value="<%=val[0]%>"/>
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="billingNotDOne"/>
							
							</font>
						</div>	
					</td>
					<%
					}
					else{
					%>
					 <html:hidden name="EstimateCertificateIssuetFB" property="deptUnitCode" value="<%=deptUnitCode %>" />
	 				<html:hidden name="EstimateCertificateIssuetFB" property="wardCode" value="<%=wardCode %>" />
					<td width="25%" class="tdfonthead">
					  <html:hidden name="EstimateCertificateIssuetFB" property="billNo"  value="<%=val[0]%>"/>
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="billingDone"/>	<%=val[0] %>
							</font>
						</div>	
					</td>
					<%
					}}
					%>
				
				
				</tr>	
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="dept/unit"/>
								</b>
							</font>
						</div>
					</td>
					<logic:iterate name="<%=MrdConfig.ESTIMATE_CERTIFIATE_ISSUE_PAT_DTL %>" id="arrPatDtl" type="mrd.vo.EstimateCertificateIssueVO" indexId="idx">
				
					
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="arrPatDtl" property="deptName"/>/
							<bean:write name="arrPatDtl" property="unitName"/>
							</font>
						</div>	
					</td>
					
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
								<html:hidden name="MedicalCertificateRequestFB" property="selectedRow" value="" />
								<a onclick="openCertificateIssuePopup(event)">Generate Certificate</a>
							</font>
						</div>	
					</td>										
					</logic:iterate>
				</tr>
				<logic:iterate name="<%=MrdConfig.ESTIMATE_CERTIFIATE_ISSUE_PAT_DTL %>" id="arrPatDtl" type="mrd.vo.EstimateCertificateIssueVO" indexId="idx">
					<logic:notEmpty name="arrPatDtl" property="admissionNo">
					<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="admissionNo"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="arrPatDtl" property="admissionNo"/>
							</font>
						</div>	
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="ward"/>
									<bean:message key="name"/>
									
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="arrPatDtl" property="patwardname"/>
							</font>
						</div>	
					</td>
					</tr>
					<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="bedNo"/>
									
								</b>
							</font>
						</div>
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="arrPatDtl" property="bedname"/>
							</font>
						</div>	
					</td>
					<td width="25%" class="tdfonthead" colspan="2">	
					</td>
					</tr>
				</logic:notEmpty>
				</logic:iterate>
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
						 <html:select name="EstimateCertificateIssuetFB" property="givenBy" tabindex="1" onchange="hideForRelationship()">
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
				         	<html:select name="EstimateCertificateIssuetFB" property="relationCode">
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
				         <html:text property="relativeName" name="EstimateCertificateIssuetFB" maxlength="60" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)"  ></html:text>
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
				         <html:textarea rows="3" cols="45"   name="EstimateCertificateIssuetFB" property ="relativeAddr"  tabindex="1" style="vertical-align: middle;" 
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
				         <html:text property="relativeContactNo" name="EstimateCertificateIssuetFB" maxlength="30" onkeypress="return validateNumeric(event)"  tabindex="1" ></html:text>
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
    
    <html:hidden name="EstimateCertificateIssuetFB" property="hmode"/>
    <html:hidden name="EstimateCertificateIssuetFB" property="patCrNo"/>
     <html:hidden name="EstimateCertificateIssuetFB" property="tariffName"/>
     <html:hidden name="EstimateCertificateIssuetFB" property="requestedDate"/>
     <html:hidden name="EstimateCertificateIssuetFB" property="advisedBy"/>
     <html:hidden name="EstimateCertificateIssuetFB" property="selectRecord"/>
     <html:hidden name="EstimateCertificateIssuetFB" property="certificateId"/>
      <html:hidden name="EstimateCertificateIssuetFB" property="strHiddenPatDtl"/>
   
     
     
</body>
<his:status/>
</html:form>    