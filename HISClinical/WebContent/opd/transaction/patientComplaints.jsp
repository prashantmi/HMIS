
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
<logic:equal name="PatientComplaintsFB" property="isDirectCall" value="DIRECT">
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
	<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
</logic:equal>
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/opd/js/patientComplaints.js" />

<script type="text/javascript">

</script>

	<logic:equal name="PatientComplaintsFB" property="isDirectCall" value="DIRECT">
		<form action="/HISClinical/opd/patientComplaints.cnt" method="post">
	</logic:equal>	
	
	<bean:define name="PatientComplaintsFB" property="recordDate" id="toDate" type="java.lang.String"/>
	<% if(toDate==null||toDate.equalsIgnoreCase(""))
	   {
			toDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	   }	 
					
	%>
			<his:TitleTag name="Patient Complaints">
			</his:TitleTag>
			
			<his:statusNew>
				<logic:equal name="PatientComplaintsFB" property="hmode" value="GETCRNO">
					<logic:empty name="PatientComplaintsFB" property="patCrNo" >
						<his:InputCrNoTag name="PatientComplaintsFB"></his:InputCrNoTag>
					</logic:empty>
				</logic:equal>		
			</his:statusNew>
			
			<his:statusTransactionInProcess>
				<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
				<%EpisodeExtInvDtlVO[] epiExtVO=(EpisodeExtInvDtlVO[])session.getAttribute(OpdConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL);
					if(epiExtVO!=null && epiExtVO.length>0){ %>
					<his:SubTitleTag name="Previous Complaints">
					</his:SubTitleTag>
					
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							 <tr>
								<td width="15%" class="tdfonthead" style="display:none">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="date"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead" style="display:none">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="time"/>
												<bean:message key="timeFormat"/>
											</b>	
										</font>
									</div>
								</td> 
								<td width="40%" class="tdfonthead"> 
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="symptoms"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="site"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="duration"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate id="epiExtInvVO" name="<%=OpdConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL %>" type="hisglobal.vo.EpisodeExtInvDtlVO">
								<tr>
									<td width="15%" class="tdfont" style="display:none">
										<div align="center">
											<%--epiExtInvVO.getRecordDate() --%>
											<html:hidden name="PatientComplaintsFB" property="addedRecordDate" value="<%=epiExtInvVO.getRecordDate() %>"/>											
										</div>
									</td>
									<td width="15%" class="tdfont" style="display:none">
										<div align="center">
											<%--epiExtInvVO.getRecordTime()  --%>
											<html:hidden name="PatientComplaintsFB" property="addedRecordTime" value="<%=epiExtInvVO.getRecordTime()  %>"/>
										</div>
									</td>
									<td width="30%" class="tdfont" class="tdfont">
										<div align="center">
											<%=epiExtInvVO.getParaName() %>
											<html:hidden name="PatientComplaintsFB" property="addedParaId" value="<%=epiExtInvVO.getParaName() %>"/>
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
											<%=(epiExtInvVO.getParaValue()!=null && !epiExtInvVO.getParaValue().equals(""))?epiExtInvVO.getParaValue():""%>
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
											<%=(epiExtInvVO.getRemarks()!=null && !epiExtInvVO.getRemarks().equals(""))?OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[Integer.parseInt(epiExtInvVO.getRemarks())]:""%>
										</div>
									</td>
								</tr>
							</logic:iterate>
						</table>	
							
					</his:ContentTag>
				<%}%>
				<his:SubTitleTag name="Patients Complaints">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="20%" class="tdfonthead" style="display:none">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*</font>
											<bean:message key="date"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="display:none">
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
							<td width="40%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*</font>
											<bean:message key="symptoms"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="site"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="27%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="duration"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="3%" class="tdfonthead"></td>
						</tr>
						<tr>
							<td width="20%" class="tdfont" style="display:none">
								<div align="center">
									<his:date name="recordDate" dateFormate="%d-%b-%Y" value="<%=toDate %>" ></his:date>
								</div>
							</td>
							<td width="20%" class="tdfont" style="display:none">
								<div align="center">
									<html:text name="PatientComplaintsFB" property="recordTimeHr" size="3" maxlength="2" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
									:&nbsp;<html:text name="PatientComplaintsFB" property="recordTimeMin" size="3" maxlength="2" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
								</div>
							</td>
							<td width="40%" class="tdfont">
								<div id="divParameterList" style="display: none; position: absolute;">
									<select name="externalParameterList" id="externalParameterList" multiple="multiple" size="4">
										<logic:iterate name="<%=OpdConfig.EXT_INV_PARAMETER_LIST%>" id="list">
											<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
										</logic:iterate>
									</select>
								</div>
								<div align="center">
									<html:hidden name="PatientComplaintsFB" property="paraId" />
									<html:text name="PatientComplaintsFB" property="paraName" onkeypress="return validateAlphaNumOnly(this,event)" 
												maxlength="200" size="35" tabindex="1" value="" onchange="setCorrParaId(this)"></html:text>
									<script type="text/javascript">
										NewDropDownSearch.setup(document.getElementsByName("paraName")[0],document.getElementsByName("externalParameterList")[0],NewDropDownSearch.SEARCH_TYPE["START"],true,true);
									</script>

								</div>
							</td>
							<td width="30%" class="tdfont">
								<div align="center">
									<html:text name="PatientComplaintsFB" property="paraValue" maxlength="500" size="25" tabindex="1" onkeypress="return notSpecChar(this,event)"></html:text>
								</div>
							</td>
							<td width="27%" class="tdfont">
								<div align="center">
									<html:select name="PatientComplaintsFB" property="remarks" tabindex="1">
										<html:option value="">Select Value</html:option>
										<html:option value="0"><%=OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[0]%></html:option>
										<html:option value="1"><%=OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[1]%></html:option>
										<html:option value="2"><%=OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[2]%></html:option>
										<html:option value="3"><%=OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[3]%></html:option>
										<html:option value="4"><%=OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[4]%></html:option>
										<html:option value="5"><%=OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[5]%></html:option>
										<html:option value="6"><%=OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[6]%></html:option>
									</html:select>
								</div>
							</td>
							<td width="3%" class="tdfont">
								<div align="center">
									<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm('ADDROW') ;" onclick="if(validateAdd()) submitForm('ADDROW')" tabindex="1">
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
									<td width="15%" class="tdfont" style="display:none">
										<div align="center">
											<%--arrExtInv.getRecordDate() --%>
											<html:hidden name="PatientComplaintsFB" property="currentAddedRecordDate" value="<%=arrExtInv.getRecordDate() %>"/>
										</div>
									</td>
									<td width="15%" class="tdfont" style="display:none">
										<div align="center">
											<%--arrExtInv.getRecordTime() --%>
											<html:hidden name="PatientComplaintsFB" property="currentAddedRecordTime" value="<%=arrExtInv.getRecordTime() %>"/>
										</div>
									</td>
									<td width="30%" class="tdfont" class="tdfont">
										<div align="center">
											<%=arrExtInv.getParaName() %>
											<html:hidden name="PatientComplaintsFB" property="currentAddedParaId" value="<%=arrExtInv.getParaName() %>"/>
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
											<%=(arrExtInv.getParaValue()!=null && !arrExtInv.getParaValue().equals(""))?arrExtInv.getParaValue():""%>
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
											<%=(arrExtInv.getRemarks()!=null && !arrExtInv.getRemarks().equals(""))?OpdConfig.PATIENT_COMPLAINTS_DURATION_VALUES_ARR[Integer.parseInt(arrExtInv.getRemarks())]:""%>
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
				
				</his:statusTransactionInProcess>		
			
			<his:ButtonToolBarTag>
				<logic:equal name="PatientComplaintsFB" property="isDirectCall" value="DESK">	
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateAdd()) if(validateExtLabAdd()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) if(validateExtLabAdd()) submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal>
				<logic:equal name="PatientComplaintsFB" property="isDirectCall" value="DIRECT">
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateAdd()) if(validateExtLabAdd()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) if(validateExtLabAdd()) submitForm('SAVE')">
					</his:statusTransactionInProcess>	
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<his:statusNew>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO')">
					</his:statusNew>
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
					</his:statusTransactionInProcess>
				</logic:equal>
			</his:ButtonToolBarTag>
			
			<div id="sid" class="hisStyle.css" style="display: none; position: absolute;">
				<select id="selid" size="10" tabindex="1" onKeyDown="hideta(event)" onchange="onChangeDrop()" 
					ondblclick="setClickedValue()" onclick="setClickedValue()">
					<option value="-1"></option>
				</select>
			</div>
			
		<html:hidden name="PatientComplaintsFB" property="hmode" />
		<html:hidden name="PatientComplaintsFB" property="tempMode" />
		<html:hidden name="PatientComplaintsFB" property="patCrNo"/>
		<html:hidden name="PatientComplaintsFB" property="deleteIndex"/>
		<html:hidden name="PatientComplaintsFB" property="sysDate" value="<%=toDate%>"/>
		<html:hidden name="PatientComplaintsFB" property="hiddenTimeHr" />
		<html:hidden name="PatientComplaintsFB" property="hiddenTimeMin" />
		<html:hidden name="PatientComplaintsFB" property="testConductedFrom" />
		<html:hidden name="PatientComplaintsFB" property="isDirectCall" />
		
	
<logic:equal name="PatientComplaintsFB" property="isDirectCall" value="DIRECT">
	<his:status/>
	</form>
</logic:equal>
