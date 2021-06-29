<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.vo.EpisodeExtInvDtlVO"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<logic:equal name="ExternalInvestigationFB" property="isDirectCall" value="DIRECT">
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
	
</logic:equal>
<his:javascript src="/opd/js/externalInvestigation.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function submitForm21(mode)
{
    
   // alert("submitform...."+mode);
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
     document.getElementsByName("hmode")[0].value=mode;
    // doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
     //alert("hmode "+document.getElementsByName("hmode")[0].value);  
    
	 document.forms[0].submit();
	 
}
function validateLabContact(e)
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
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789,").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}//end of validateNumericWithCommaOnly
</script>

	<logic:equal name="ExternalInvestigationFB" property="isDirectCall" value="DIRECT">
		<form action="/HISClinical/opd/externalExamination.cnt" name="frmextinv" method="post">
	</logic:equal>	
	
	<bean:define name="ExternalInvestigationFB" property="recordDate" id="toDate" type="java.lang.String"/>
	<% if(toDate==null||toDate.equalsIgnoreCase(""))
	   {
			toDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	   }	 
					
	%>
			<his:TitleTag name="Externnal Examination">
			</his:TitleTag>
			
			<his:statusNew>
				<logic:equal name="ExternalInvestigationFB" property="hmode" value="GETCRNO">
					<logic:empty name="ExternalInvestigationFB" property="patCrNo" >
						<his:InputCrNoTag name="ExternalInvestigationFB"></his:InputCrNoTag>
					</logic:empty>
				</logic:equal>		
			</his:statusNew>
			
			<his:statusTransactionInProcess>
				<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
				<%EpisodeExtInvDtlVO[] epiExtVO=(EpisodeExtInvDtlVO[])session.getAttribute(OpdConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL);
					if(epiExtVO.length>0){ %>
					<his:SubTitleTag name="Added External Examination Detail">
					</his:SubTitleTag>
					
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="date"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="time"/>
												<bean:message key="timeFormat"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="para"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="40%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="value"/>
											</b>	
										</font>
									</div>
								</td>
								
							</tr>
							<logic:iterate id="epiExtInvVO" name="<%=OpdConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL %>" type="hisglobal.vo.EpisodeExtInvDtlVO">
								<tr>
									<td width="20%" class="tdfont">
										<div align="center">
											<%=epiExtInvVO.getRecordDate() %>
											<html:hidden name="ExternalInvestigationFB" property="addedRecordDate" value="<%=epiExtInvVO.getRecordDate() %>"/>											
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
											<%=epiExtInvVO.getRecordTime()  %>
											<html:hidden name="ExternalInvestigationFB" property="addedRecordTime" value="<%=epiExtInvVO.getRecordTime()  %>"/>
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
											<%=epiExtInvVO.getParaName() %>
											<html:hidden name="ExternalInvestigationFB" property="addedParaId" value="<%=epiExtInvVO.getParaName() %>"/>
										</div>
									</td>
									<td width="40%" class="tdfont">
										<div align="center">
											<%=epiExtInvVO.getParaValue() %>
										</div>
									</td>
								</tr>
							</logic:iterate>
						</table>	
							
					</his:ContentTag>
				<%}%>
				<his:SubTitleTag name="External Examination Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="20%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*</font>
											<bean:message key="date"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*</font>
											<bean:message key="time"/>
											<bean:message key="timeFormat"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*</font>
											<bean:message key="para"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="37%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*</font>
											<bean:message key="value"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="3%" class="tdfonthead"></td>
						</tr>
						<tr>
							<td width="20%" class="tdfont">
								<div align="center">
									<his:date name="recordDate" dateFormate="%d-%b-%Y" value="<%=toDate %>" ></his:date>
								</div>
							</td>
							<td width="20%" class="tdfont">
								<div align="center">
									<html:text name="ExternalInvestigationFB" property="recordTimeHr" size="3" maxlength="2" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
									:&nbsp;<html:text name="ExternalInvestigationFB" property="recordTimeMin" size="3" maxlength="2" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
								</div>
							</td>
							<td width="20%" class="tdfont">
								<div id="divParameterList" style="display: none; position: absolute;">
									<select name="externalParameterList" id="externalParameterList" multiple="multiple" size="4">
										<logic:iterate name="<%=OpdConfig.EXT_INV_PARAMETER_LIST%>" id="list">
											<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
										</logic:iterate>
									</select>
								</div>
								<div align="center">
									<html:text name="ExternalInvestigationFB" property="paraId" onkeypress="return validateAlphaNumOnly(this,event)" 
												onkeyup="gettext(event,this);"  onblur="callOnBlur()" tabindex="1"></html:text>
								</div>
							</td>
							<td width="37%" class="tdfont">
								<div align="center">
									<html:text name="ExternalInvestigationFB" property="paraValue" maxlength="500" size="50" tabindex="1"></html:text>
								</div>
							</td>
							<td width="3%" class="tdfont">
								<div align="center">
									<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm21('ADDROW') ;" onclick="if(validateAdd()) submitForm21('ADDROW')" tabindex="1">
								</div>	
							</td>
						</tr>
					</table>
				</his:ContentTag>	
				<%if(session.getAttribute(OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL)!=null){ %>
					<his:ContentTag>
						<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
							<logic:iterate id="arrExtInv" name="<%=OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL %>" type="hisglobal.vo.EpisodeExtInvDtlVO" indexId="idx">
								<tr>
									<td class="tdfont" width="20%" >
										<div align="center">
											<%=arrExtInv.getRecordDate() %>
											<html:hidden name="ExternalInvestigationFB" property="currentAddedRecordDate" value="<%=arrExtInv.getRecordDate() %>"/>
										</div>
									</td>
									<td class="tdfont" width="20%" >
										<div align="center">
											<%=arrExtInv.getRecordTime() %>
											<html:hidden name="ExternalInvestigationFB" property="currentAddedRecordTime" value="<%=arrExtInv.getRecordTime() %>"/>
										</div>
									</td>
									<td class="tdfont" width="20%" >
										<div align="center">
											<%=arrExtInv.getParaName() %>
											<html:hidden name="ExternalInvestigationFB" property="currentAddedParaId" value="<%=arrExtInv.getParaName() %>"/>
										</div>
									</td>
									<td class="tdfont" width="37%" >
										<div align="center">
											<%=arrExtInv.getParaValue() %>
										</div>
									</td>
									<td class="tdfont" width="3%" >
										<div align="center">
											<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow(<%=idx.toString() %>) ;" onclick=" deleteRow(<%=idx.toString() %>)" tabindex="1">
										</div>	
									</td>
								</tr>	
							</logic:iterate>
						</table>
					</his:ContentTag>	
				<%} %>
				
				<his:SubTitleTag name="Test Conducted">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="100%" >
								<div align="left">
									<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="inHouse"/>
										</b>	
									</font>
									<html:radio name="ExternalInvestigationFB" property="testConductedFrom" value="<%=OpdConfig.TEST_CONDUCTED_IN_HOUSE%>" onclick="showLabInfo()" ></html:radio>
									<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="from"/>
											<bean:message key="extLab"/>
										</b>
									</font>	
									<html:radio name="ExternalInvestigationFB" property="testConductedFrom" value="<%=OpdConfig.TEST_CONDUCTED_FROM_EXT_LAB%>" onclick="showLabInfo()" ></html:radio>
								</div>
							</td>
						</tr>
					</table>		
				</his:SubTitleTag>
				<div id="extLabInfoId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="labName"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalInvestigationFB" property="extLabName" maxlength="50" size="30" onkeypress="return validateAlphaNumOnly(this,event)" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="labContactNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalInvestigationFB" property="extLabContactNo" maxlength="30" onkeypress="return validateLabContact(event)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="labAddress"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalInvestigationFB" property="extLabAdd" maxlength="50" size="30" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfont">
								</td>
							</tr>
						</table>		
					</his:ContentTag>
				</div>
			</his:statusTransactionInProcess>		
			
			<his:ButtonToolBarTag>
				<logic:equal name="ExternalInvestigationFB" property="isDirectCall" value="DESK">	
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick =" if(validateAdd()) if(validateExtLabAdd()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) if(validateExtLabAdd()) submitForm21('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal>
				<logic:equal name="ExternalInvestigationFB" property="isDirectCall" value="DIRECT">
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick =" if(validateAdd()) if(validateExtLabAdd()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) if(validateExtLabAdd()) submitForm21('SAVE')">
					</his:statusTransactionInProcess>	
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<his:statusNew>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO')">
					</his:statusNew>
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
					</his:statusTransactionInProcess>
				</logic:equal>
			</his:ButtonToolBarTag>
			
			<div id="sid" class="hisStyle.css" style="display: none; position: absolute;">
				<select id="selid" size="10" tabindex="1" onKeyDown="hideta(event)" onchange="onChangeDrop()" 
					ondblclick="setClickedValue()" onclick="setClickedValue()">
					<option value="-1"></option>
				</select>
			</div>
			
		<html:hidden name="ExternalInvestigationFB" property="hmode" />
		<html:hidden name="ExternalInvestigationFB" property="tempMode" />
		<html:hidden name="ExternalInvestigationFB" property="patCrNo"/>
		<html:hidden name="ExternalInvestigationFB" property="deleteIndex"/>
		<html:hidden name="ExternalInvestigationFB" property="sysDate" value="<%=toDate%>"/>
		<html:hidden name="ExternalInvestigationFB" property="hiddenTimeHr" />
		<html:hidden name="ExternalInvestigationFB" property="hiddenTimeMin" />
		<html:hidden name="ExternalInvestigationFB" property="testConductedFrom" />
		<html:hidden name="ExternalInvestigationFB" property="isDirectCall" />
		
	
<logic:equal name="ExternalInvestigationFB" property="isDirectCall" value="DIRECT">
	<his:status/>
	</form>
</logic:equal>
