<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="registration.controller.fb.HandoverToDeadBodyFB"%>
<%@page import="hisglobal.vo.PatientDeathDetailVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>

	<%@page import="opd.transaction.controller.fb.ConsentRequestFB"%>
<%@page import="hisglobal.vo.ConsentRequestVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
	
	<his:javascript src="/registration/js/registration.js" />
	<his:javascript src="/registration/js/validationCalls.js" />
	<his:javascript src="/registration/js/validationCommon.js" />
	<his:javascript src="/registration/js/commonFunctions.js" />
	<his:javascript src="/hisglobal/js/calendar.js"/>
	<his:javascript src="/registration/js/popup.js"/>
    <his:javascript src="/hisglobal/js/validationCommon.js"/>
    <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@ page import ="opd.OpdConfig"%>

<%@page import="java.util.List"%>
<script type="text/javascript">
function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function doPagination(e,p)
{	
	var len=document.getElementsByName("chk").length;
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("chk")[i].checked) 
			{
				
				if(document.getElementsByName("givenBy")[i].value=="-1")
				{
					alert("Please Select Given By");
					document.getElementsByName("givenBy")[i].focus();
					return false;
				}
				if(document.getElementsByName("givenBy")[i].value=="1")
				{
					if(document.getElementsByName("relativeName")[i].value=="")
					{
						alert("Please Enter Relative Name");
						document.getElementsByName("relativeName")[i].focus();
						return false;
					}
					if(document.getElementsByName("relativeAddr")[i].value=="")
					{
						alert("Please Enter Relative Address");
						document.getElementsByName("relativeAddr")[i].focus();
						return false;
					}
				}
			} 
		}
	
	
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function validateForm1(mode)
{
	var len=document.getElementsByName("chk").length;
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("chk")[i].checked) 
			{
				
				if(document.getElementsByName("givenBy")[i].value=="-1")
				{
					alert("Please Select Given By");
					document.getElementsByName("givenBy")[i].focus();
					return false;
				}
				if(document.getElementsByName("givenBy")[i].value=="1")
				{
					if(document.getElementsByName("relativeName")[i].value=="")
					{
						alert("Please Enter Relative Name");
						document.getElementsByName("relativeName")[i].focus();
						return false;
					}
					if(document.getElementsByName("relativeAddr")[i].value=="")
					{
						alert("Please Enter Relative Address");
						document.getElementsByName("relativeAddr")[i].focus();
						return false;
					}
					
				}
				
				
			} 
				
		}
		submitForm(mode);
}

	function abc()
	{
		var len=document.getElementsByName("chk").length;
		for(i=0;i<len;i++)
		{
			//alert(document.getElementsByName("chk")[i].value);
			if(document.getElementsByName("chk")[i].checked)  
			{	
				
				document.getElementsByName("givenBy")[i].disabled=false;
			}
			else
			{
				document.getElementsByName("givenBy")[i].value="-1";
				document.getElementsByName("givenBy")[i].disabled=true; 
				var releationshipRowId="releationshipRowId"+document.getElementsByName("chk")[i].value;
				var relationShipValueRowId="relationShipValueRowId"+document.getElementsByName("chk")[i].value;
					
				document.getElementById(releationshipRowId).style.display="none";
				document.getElementById(relationShipValueRowId).style.display="none";
			}
				
			
				
		}
	}
	
	function enableRelation()
	{
		var len=document.getElementsByName("chk").length;
		//alert("len "+len);
		
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("chk")[i].checked) 
			{
				
				if(document.getElementsByName("givenBy")[i].value=="1")
				{
					//alert("releationshipRowId before "+releationshipRowId);
					
					var releationshipRowId="releationshipRowId"+document.getElementsByName("chk")[i].value;
					var relationShipValueRowId="relationShipValueRowId"+document.getElementsByName("chk")[i].value;
					
					//alert("releationshipRowId "+releationshipRowId);
					
					document.getElementById(releationshipRowId).style.display="";
					document.getElementById(relationShipValueRowId).style.display="";
				}
				else
				{
					var releationshipRowId="releationshipRowId"+document.getElementsByName("chk")[i].value;
					var relationShipValueRowId="relationShipValueRowId"+document.getElementsByName("chk")[i].value;
					
					document.getElementById(releationshipRowId).style.display="none";
					document.getElementById(relationShipValueRowId).style.display="none";
				}
				
			}
			
				
		}
	}
	
	function validateNumberOnly(e)
	{
		var key;
		var keychar;
	
	

		if (window.event)
	  	 key = window.event.keyCode;
		else if (e)
		   key = e.which;
		else
	 	  return true;
		keychar = String.fromCharCode(key);

		keychar = keychar.toUpperCase();
	//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space (give here which character u want to allow 
	else if ((("1234567890-").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}

function validateForm(mode)
{
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	var len=document.getElementsByName("chk").length;
	var flag=false;
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName('chk')[i].checked)
			{
				flag=true;
			}
		}
		if(!flag) 
		{
		alert("Please Select Consent");	
		}
		else
		{
			validateForm1(mode);
		}

}

function getconsentGivenDetail(event)
{
	//var sysDate=document.getElementsByName("sysDate")[0].value;
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	//var admissionDate=document.getElementsByName("admissionDate")[0].value;
	//alert("patCrNo "+patCrNo);
	var path='/HISClinical/opd/consentRequest.cnt?hmode=VIEW&patCrNo='+patCrNo;
	//alert("path "+path);
	openDependentPopup(createFHashAjaxQuery(path),event,250,600,false);
}

window.onload=function(){ 
						abc();
						enableRelation();
					  }

</script>
<his:TransactionContainer>
<his:TitleTag name="Consent Requests">
</his:TitleTag>


<bean:define id="patCrNo" property="patCrNo" name="consentRequestFB"></bean:define>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>

 <% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((ConsentRequestFB)request.getAttribute("consentRequestFB")).getCurrentPage());
				fbPage.setObjArrName(OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST);
				fbPage.setAppendInTitle("Consent Request");
				fbPage.setMaxRecords(2);
			%>
			<html:hidden name="consentRequestFB" property="currentPage"/>

			<his:PaginationTag name="fbPagination"></his:PaginationTag>

<his:statusTransactionInProcess>
	
	<his:SubTitleTag name="Consent Request Detail">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="getconsentGivenDetail(event)" >
	      							<bean:message key="receivedConsent"/>
	      						</a>
							</b>
						</font>		
					</div>
				</td>
			</tr>
		</table>
	</his:SubTitleTag> 
	<his:ContentTag>	
		<logic:present name="<%=OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST %>">
		<logic:notEmpty name="<%=OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST %>">
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
			
				<td width="12%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="select"/></b>
					</font>
					</div>
		  		</td>
				<td width="12%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="serviceType"/></b>
					</font>
					</div>
		  		</td>
				<td width="12%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="service"/></b>
					</font>
					</div>
		  		</td>
	  		
				<td width="12%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="consentName"/></b>
					</font>
					</div>
		  		</td>
				<td width="12%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="raisedBy"/></b>
					</font>
					</div>
		  		</td>
				<td width="12%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="date&time"/></b>
						</font>
						</div>
		  		</td>
				<td width="12%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="status"/></b>
					</font>
					</div>
		  		</td>
		  		<td width="12%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="givenBy"/></b>
					</font>
					</div>
		  		</td>
		</tr>
	</table>
	
	 <%
	 			List consentRequestVos=(List)session.getAttribute(OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST);
						
						int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
						int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						
						Map map = new HashMap();
						if(session.getAttribute(OpdConfig.SELECTED_CONSENT_REQUEST)!=null)
							map=(HashMap)session.getAttribute(OpdConfig.SELECTED_CONSENT_REQUEST);
						
						for(int j=startIndex;j<=endIndex;j++)
						{
							Integer ind=new Integer(j);
							String index=ind.toString();
							ConsentRequestVO consentRequestVO=(ConsentRequestVO)consentRequestVos.get(j);
	%>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
	<tr>
	
	<td width="12%" class="tdfont">
				         <div align="center">
				          <input type="checkbox" name="chk" value="<%=consentRequestVO.getServiceConsentId()%>" onclick="abc()" <%if(map.get(consentRequestVO.getServiceConsentId())!=null){ %> checked='checked' <%}%> >
				          <html:hidden name="consentRequestFB" property="serviceConsentId" value="<%=consentRequestVO.getServiceConsentId() %>" />	
				          <html:hidden name="consentRequestFB" property="consentId" value="<%=consentRequestVO.getConsentId() %>" />
				          
				         </div>
				    	 </td>
	 <td width="12%" class="tdfont">
				         <div align="center">	
				         <%=consentRequestVO.getServiceTypeDesc() %>
				         <html:hidden name="consentRequestFB" property="serviceTypeId" value="<%=consentRequestVO.getServiceTypeId() %>"/>
				         </div>
					 </td>
	 <td width="12%" class="tdfont">
				         <div align="center">	
				         <%=consentRequestVO.getServiceDesc() %>
				          <html:hidden name="consentRequestFB" property="serviceId" value="<%=consentRequestVO.getServiceId() %>"/>
				         </div>
					 </td>
	<td width="12%" class="tdfont">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% String Path="/HISClinical/opd/consentRequest.cnt?hmode=POPUP&patCrNo="+patCrNo+"&templateId="+consentRequestVO.getConsentId();%>
					<a style="cursor:pointer" onclick="openPopup('<%=Path%>',event,300,600)" >
					<%=consentRequestVO.getTemplateDesc()%>
					</a>
					</font>
					</div>
		  		</td>
	<td width="12%" class="tdfont">
				         <div align="center">	
				         <%=consentRequestVO.getRaisedBy() %>
				        <html:hidden name="ConsentRequestFB" property="requestID" value="<%=consentRequestVO.getRequestID() %>"/>
				         </div>
					 </td>
	<td width="12%" class="tdfont">
				         <div align="center">
				         <%=consentRequestVO.getRaisedDate() %>	
				         </div>
					 </td>
	<td width="12%" class="tdfont">
				         <div align="center">	
				         <%=consentRequestVO.getConsentStatus() %>
				         </div>
					 </td>
	<td width="12%" class="tdfont">
		 <html:select name="consentRequestFB" property="givenBy" tabindex="1" disabled="true" onchange="enableRelation()" value="<%=consentRequestVO.getGivenBy() %>">
		<html:option value="-1">Select Value</html:option>
		<html:option value="0">Patient</html:option>
		<html:option value="1">Relative</html:option>
		</html:select>
	</td>
	
	</tr>
	</table>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="releationshipRowId<%=consentRequestVO.getServiceConsentId()%>" style="display: none;">
	<tr >
		<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="realtionship"/></b>
					</font>
					</div>
		  		</td>
		<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<font color="#FF0000">*</font>
					<bean:message key="relativename"/>
					</b>
					</font>
					</div>
		  		</td>
		<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<font color="#FF0000">*</font>
					<bean:message key="relativeaddress"/>
					</b>
					</font>
					</div>
		  		</td>
		  	<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="relativeContactNo"/></b>
					</font>
					</div>
		  		</td>
		  	<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="relativeIdMark"/></b>
					</font>
					</div>
		  		</td>
		  	
			
		</tr>
		<tr id="relationShipValueRowId<%=consentRequestVO.getServiceConsentId() %>" style="display: none;">
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:select name="consentRequestFB" property="relationCode" value="<%=consentRequestVO.getRelationCode() %>">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.ALL_RELATIONSHIP_LIST%>" >
								<html:options collection="<%=OpdConfig.ALL_RELATIONSHIP_LIST%>" property="value" labelProperty="label"/>
								</logic:present>
							</html:select>
				         </div>
					 </td>
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:text property="relativeName" name="consentRequestFB" maxlength="80" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)"  value="<%=consentRequestVO.getRelativeName() %>"></html:text>
				         </div>
					 </td>
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:text property="relativeAddr" name="consentRequestFB" maxlength="100" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)" value="<%=consentRequestVO.getRelativeAddr() %>" ></html:text>
				         </div>
					 </td>
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:text property="relativeContactNo" name="consentRequestFB" maxlength="50" onkeypress="return validateNumberOnly(event)"  tabindex="1" value="<%=consentRequestVO.getRelativeContactNo() %>"></html:text>
				         </div>
					 </td>
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:text property="relativeIdRemark" name="consentRequestFB" maxlength="50" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)" value="<%=consentRequestVO.getRelativeIdRemark() %>" ></html:text>
				         </div>
					 </td>
			
					 
			</tr>
			</table>
			<%} %>
			
		</logic:notEmpty>
		</logic:present>	
</his:ContentTag>		
</his:statusTransactionInProcess>


<his:ButtonToolBarTag>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
	<his:statusTransactionInProcess>
	<logic:present name="<%=OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST %>">
	<logic:notEmpty name="<%=OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST %>">	
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"  tabindex="1" onclick =  "validateForm('UPDATE');" onkeypress="if(event.keyCode==13) validateForm('UPDATE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</logic:notEmpty>
	</logic:present>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusTransactionInProcess>
</his:ButtonToolBarTag>
</his:TransactionContainer>

<html:hidden property="hmode" name="consentRequestFB" />
<html:hidden property="templateId" name="consentRequestFB"/>
<html:hidden property="patCrNo" name="consentRequestFB" />
<html:hidden property="episodeCode" name="consentRequestFB"/>
<html:hidden property="episodeVisitNo" name="consentRequestFB"/>
<html:hidden property="hiddenPatDeadStatus" name="consentRequestFB"/>
