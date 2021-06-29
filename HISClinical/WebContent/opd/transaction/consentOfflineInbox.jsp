<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page autoFlush="true" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@ page import="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>


<%@ page import ="opd.OpdConfig"%>

<%@page import="java.util.List"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
		
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>		
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/opd/opdJs/opd.js"/>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<logic:equal name="ConsentOfflineInboxFB" property="isDirectCall" value="DIRECT">
		
		<his:css src="/hisglobal/css/Color.css"/>
		<his:css src="/hisglobal/css/master.css"/>
		<his:css src="/hisglobal/css/hisStyle.css"/>
		<his:css src="/hisglobal/css/hisStyleExt.css"/>
		<his:css src="/hisglobal/css/calendar-blue2.css"/>
		
		<his:javascript src="/registration/js/popup.js" />
		<his:javascript src="/registration/js/calendar.js" />
		<his:javascript src="/registration/js/registration.js" />
		<his:javascript src="/registration/js/dateFunctions.js" />
		<his:javascript src="/registration/js/validationCalls.js" />
		<his:javascript src="/registration/js/commonFunctions.js" />
		<his:javascript src="/registration/js/validationCommon.js" />
		<his:javascript src="/opd/opdJs/opd.js"/>

</logic:equal>

		
<script type="text/javascript">

function clearCrNo()
{
	//alert("clear");
	document.getElementsByName("patCrNo")[0].value="";
}

function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value=mode
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
/*
	function abc()
	{
		var len=document.getElementsByName("chk").length;
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("chk")[i].checked)  
				document.getElementsByName("givenBy")[i].disabled=false;
			else
				document.getElementsByName("givenBy")[i].disabled=true; 
		}
	}
*/	
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
		
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("chk")[i].checked) 
			{
				
				if(document.getElementsByName("givenBy")[i].value=="1")
				{
					var releationshipRowId="releationshipRowId"+i;
					var relationShipValueRowId="relationShipValueRowId"+i;
					
					document.getElementById(releationshipRowId).style.display="";
					document.getElementById(relationShipValueRowId).style.display="";
				}
				else
				{
					var releationshipRowId="releationshipRowId"+i;
					var relationShipValueRowId="relationShipValueRowId"+i;
					
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

window.onload=function(){ 
						abc();
						enableRelation();
					  }


</script>


<html:form action="/consentOfflineInbox">


<his:TransactionContainer>

<his:TitleTag name="Consent Inbox">
</his:TitleTag>


<his:InputCrNoTag name="ConsentOfflineInboxFB"> </his:InputCrNoTag>

	
<logic:notEmpty name="ConsentOfflineInboxFB" property="patCrNo">
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
</logic:notEmpty>

<logic:notEmpty name="ConsentOfflineInboxFB" property="patCrNo">
<bean:define id="patCrNo" name="ConsentOfflineInboxFB" property="patCrNo"></bean:define>
<his:statusTransactionInProcess>

<%
	List receivedConsentRequestVO = (List)session.getAttribute(OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST);
	List pendingConsentRequestVO = (List)session.getAttribute(OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST);
	if(receivedConsentRequestVO.size()>0 || pendingConsentRequestVO.size()>0)
	{
%>
			<his:SubTitleTag name="Consent Request Detail">
			</his:SubTitleTag> 	

			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
			
				<td width="5%"  class="tdfonthead">
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
		  		<td width="19%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="givenBy"/></b>
					</font>
					</div>
		  		</td>
		</tr>
	</table>
	<logic:iterate id="consentRequestVO" indexId="idx" name="<%=OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST %>" type="hisglobal.vo.ConsentRequestVO" >
	 <%String index=idx.toString(); %>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
	<tr>
	
	<td width="5%" class="tdfont">
				         <div align="center">
				          <html:checkbox property="chk" name="ConsentOfflineInboxFB" value="<%=index%>" onclick="abc()"></html:checkbox>	
				        	<html:hidden name="consentRequestVO" property="consentId"/>
				        	<html:hidden name="consentRequestVO" property="episodeCode"/>
				        	<html:hidden name="consentRequestVO" property="episodeVisitNo"/>
				         </div>
				    	 </td>
	 <td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="serviceTypeDesc"/>
				         <html:hidden name="consentRequestVO" property="serviceTypeId"/>
				         </div>
					 </td>
	 <td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="serviceDesc"/>
				         <html:hidden name="consentRequestVO" property="serviceId"/>
				         </div>
					 </td>
	<td width="12%" class="tdfont">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% String Path=createFHashAjaxQuery("consentOfflineInbox.cnt?hmode=POPUP&i="+idx.toString()+"&patCrNo="+patCrNo+"&templateId="+consentRequestVO.getConsentId());%>
					<a style="cursor:pointer" onclick="openPopup('<%=Path%>',event,300,600)" >
					<%=consentRequestVO.getTemplateDesc()%>
					</a>
					</font>
					</div>
		  		</td>
	<td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="raisedBy"/>
				         <html:hidden name="consentRequestVO" property="requestID"/>
				         </div>
					 </td>
	<td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="raisedDate"/>
				         </div>
					 </td>
	<td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="consentStatus"/>
				         </div>
					 </td>
	<td width="19%" class="tdfont">
	<div align="center">
		 <html:select name="ConsentOfflineInboxFB" property="givenBy" tabindex="1" disabled="true" onclick="enableRelation()">
		<html:option value="-1">Select Value</html:option>
		<html:option value="0">Patient</html:option>
		<html:option value="1">Relative</html:option>
		</html:select>
		</div>
	</td>
	
	</tr>
	</table>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="releationshipRowId<%=idx %>" style="display: none;">
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
		<tr id="relationShipValueRowId<%=idx %>" style="display: none;">
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:select name="ConsentOfflineInboxFB" property="relationCode" >
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.ALL_RELATIONSHIP_LIST%>" >
								<html:options collection="<%=OpdConfig.ALL_RELATIONSHIP_LIST%>" property="value" labelProperty="label"/>
								</logic:present>
							</html:select>
				         </div>
					 </td>
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:text property="relativeName" name="ConsentOfflineInboxFB" maxlength="80" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)" ></html:text>
				         </div>
					 </td>
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:text property="relativeAddr" name="ConsentOfflineInboxFB" maxlength="100" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)" ></html:text>
				         </div>
					 </td>
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:text property="relativeContactNo" name="ConsentOfflineInboxFB" maxlength="50" onkeypress="return validateNumberOnly(event)"  tabindex="1"></html:text>
				         </div>
					 </td>
			<td width="20%" class="tdfont">
				         <div align="center">	
				         <html:text property="relativeIdRemark" name="ConsentOfflineInboxFB" maxlength="50" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)" ></html:text>
				         </div>
					 </td>
			
					 
			</tr>
			</table>
			</logic:iterate>
		
	
	
	
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
	<logic:iterate id="consentRequestVO" indexId="idx" name="<%=OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST %>" type="hisglobal.vo.ConsentRequestVO" >
	<tr>
	
	<td width="5%" class="tdfont">
				         <div align="center">
				          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b></b>
					</font>	
				        
				         </div>
				    	 </td>
	 <td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="serviceTypeDesc"/>
				         </div>
					 </td>
	 <td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="serviceDesc"/>
				         </div>
					 </td>
	<td width="12%" class="tdfont">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% String Path="/HISClinical/opd/consentOfflineInbox.cnt?hmode=POPUP&i="+idx.toString()+"&patCrNo="+patCrNo+"&templateId="+consentRequestVO.getConsentId()+"&i="+idx;%>
					<a style="cursor:pointer" onclick="openPopup('<%=Path%>',event,300,600)" >
					<%=consentRequestVO.getTemplateDesc()%>
					</a>
					</font>
					</div>
		  		</td>
					<td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="raisedBy"/>
				         </div>
					 </td>
					<td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="raisedDate"/>
				         </div>
					 </td>
					<td width="12%" class="tdfont">
				         <div align="center">	
				         <bean:write name="consentRequestVO" property="consentStatus"/>
				         </div>
					 </td>	
					 <td width="19%" class="tdfont">
				         <div align="center">
				          <bean:write name="consentRequestVO" property="relationshipName"/>				        
				         </div>
				    	 </td>
			</tr>
			</logic:iterate>
			</table>
		
			
<%
	}
%>

</his:statusTransactionInProcess>

</logic:notEmpty>
	



<his:ButtonToolBarTag>
		<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
	</his:statusUnsuccessfull>
	<his:statusNew>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('INIT')" onkeypress="if(event.keyCode==13)submitForm('INIT')">
	       	  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:statusNew>
	<his:statusTransactionInProcess>
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"  tabindex="1" onclick =  "validateForm('UPDATE');" onkeypress="if(event.keyCode==13) validateForm('UPDATE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');">
	</his:statusTransactionInProcess>
	
</his:ButtonToolBarTag>
</his:TransactionContainer>

<html:hidden property="hmode" name="ConsentOfflineInboxFB" />
<html:hidden property="templateId" name="ConsentOfflineInboxFB"/>
<html:hidden property="patCrNo" name="ConsentOfflineInboxFB" />
<html:hidden property="episodeCode" name="ConsentOfflineInboxFB"/>
<html:hidden property="episodeVisitNo" name="ConsentOfflineInboxFB"/>
<html:hidden property="isDirectCall" name="ConsentOfflineInboxFB"/>
<his:status/>
</html:form>