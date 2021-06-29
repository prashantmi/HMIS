<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/tab.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script>

function clearForm()
{
document.getElementsByName("patCrNo")[0].value="";
document.getElementsByName("reqType")[0].checked="true";
}
function function1()
{
document.getElementsByName("reqType")[0].checked="true";
document.getElementsByName("reqType")[0].value="0";
}
function validateSave()
{
 var valid=false;
	if( isEmpty(document.forms[0].patCrNo,"CR No.")
		
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;	
}

function showDetail(flag)
{
	if(flag=="0")
	{
	document.getElementsByName("mode")[0].value="0";
	}
	else
	document.getElementsByName("mode")[0].value="1";
}

function submitForm(hmode)
{
	//document.getElementsByName("mode")[0].value="withCrNo";
	document.getElementsByName("hmode")[0].value=hmode;
	//document.forms[0].hmode = hmode;
	document.forms[0].submit();
}

</script>
<body onload="function1();">
<html:form action="/duplicateRecordPrintReq">
    <his:TitleTag name="Duplicate Record Printing Request"> 		
   </his:TitleTag> 
		   <table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
		   	<td width="75%" class="tdfont">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="withCrNo" />
								</font>	
								<html:radio name="duplicateRecordPrintReqFB" property="reqType"  value="0"  onclick="submitForm('CHANGEREQTYPE')" tabindex="1" ></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="withoutCrNo" />
								</font>
								<html:radio name="duplicateRecordPrintReqFB" property="reqType" value="1" onclick="submitForm('CHANGEREQTYPE')"  tabindex="1" ></html:radio>
							</div>
						</td>
					</tr>
				</table>
  
 <table width="100%" border="0"  cellspacing="1" cellpadding="5">
 <tr><td>
 <his:InputCrNoTag name="duplicateRecordPrintReqFB"></his:InputCrNoTag>
 </td>
<%--<td class="tdfonthead" nowrap="" width="17%">
<div align="left">
<font color="#FF0000" face="Verdana, Arial, Helvetica, sans-serif" size="1">*</font>
<b><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif" size="2">
<bean:message key="crNo" />
</font></b>
</div>
</td>
<td class="tdfont" nowrap="" width="17%">
<font color="#000000" face="Verdana, Arial, Helvetica, sans-serif" size="1em">
<input name="patCrNo" maxlength="13" tabindex="1" size="15" value="10115" onkeydown="setPrevValue(this, event);" onkeypress="if(event.keyCode!=13) return validateNumeric(event); else return submitFormOnValidate(validateCRNoCHECK('13'),'GETPATDTL'); " type="text">
</font>
</td>
<td class="tdfont" align="left" width="49%">
<img class="button" src="/HISClinical/hisglobal/images/GO.png" tabindex="1" style="cursor:pointer" onclick="if(validateSave()) submitForm('GETPATDTL')" onkeypress="if(validateSave()) submitForm('GETPATDTL')" size="7" border="0"> 
</td>
<td class="tdfont" width="17%">
<div align="right"><img class="button" src="/HISClinical/hisglobal/images/btn-search.png" tabindex="1" style="cursor:pointer" onclick="openPopup('/HISClinical/registration/searchByNamePopup.cnt',event,350,750)" onkeypress="if(event.keyCode==13) openPopup('/HISClinical/registration/searchByNamePopup.cnt',event,350,750)">
</div>				
</td> --%>
</tr>
 </table>
 <his:ButtonToolBarTag>
 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
 
 </his:ButtonToolBarTag>
 <his:status/>
   <html:hidden name="duplicateRecordPrintReqFB" property="hmode"/>
   <html:hidden name="duplicateRecordPrintReqFB" property="patCrNo"/>
   <html:hidden name="duplicateRecordPrintReqFB" property="reqType"/>
   <html:hidden name="duplicateRecordPrintReqFB" property="mode"/>
   
   
</html:form>
   </body>
   </html>
