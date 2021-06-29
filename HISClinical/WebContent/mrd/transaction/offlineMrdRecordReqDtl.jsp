
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.OfflineMrdRecordReqDtlFB"%>
<html>

<head>z
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
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

window.onload=function()
{
	showHideMrdList();
}

function showHideMrdList()
{
	if(document.getElementsByName("isMrdLengthOne")[0].value==<%=MrdConfig.YES%>)
		document.getElementById("divMrdListId").style.display="none";
	else
		document.getElementById("divMrdListId").style.display="block";
}

function showEmployeepopup(e,fieldToPopulate,index)
{
	var dept = document.getElementsByName("deptname")[0].value;
	var path="/HISClinical/mrd/employeePopUp.cnt?fieldToPopulate="+fieldToPopulate+"&index="+index+"&deptname="+dept
	openPopup(createFHashAjaxQuery(path),e,300,600);
}

function clearForm()
{
	if(document.getElementsByName("isMrdLengthOne")[0].value==<%=MrdConfig.NO%>){
		document.getElementsByName("mrdCode")[0].value="-1";
	}
	else{
		document.getElementsByName("mrdCode")[0].value="";
	}
	document.getElementsByName("reqPurposeId")[0].value="-1";
	document.getElementsByName("forDays")[0].value="";
	document.getElementsByName("recordType")[0].value="-1";
	document.getElementsByName("remarks")[0].value="";
	document.getElementsByName("requestById")[0].value="";
	document.getElementsByName("requestByName")[0].value="";
	document.getElementsByName("requestByExternalName")[0].value="";
	submitForm21("CLEAR");
	
}

function validateSave()
{	
	//alert(document.getElementsByName("requestByFlag")[0].value);
	if( comboValidation(document.forms[0].recordType,"Record Type")
		&& comboValidation(document.forms[0].reqPurposeId,"Request Purpose")
		&& validateReqBy()
		&& isEmpty(document.forms[0].forDays,"For Days")
		&& validateMrd()
		&& validateRequestedRecord()	
		//&& validateIsHod() commented by sandip naik on 09/06/17
	){
		submitForm21('SAVE');
	}
	
}
function validateIsHod()
{
	//alert(document.getElementsByName("hodName")[0].value);
	//alert(document.getElementsByName("requestByName")[0].value);
	
	if(document.getElementsByName("hodName")[0].value.toUpperCase() == document.getElementsByName("requestByName")[0].value.toUpperCase())
		{
		document.getElementById("disclaimer").style.display="none";
			//alert("yes")
			return true;
		}
	else
		{
		document.getElementById("details").style.backgroundColor = "#FF6347";
		document.getElementById("cr").removeAttribute("class");
		document.getElementById("name").removeAttribute("class");
		document.getElementById("dept").removeAttribute("class");
		document.getElementById("hod").removeAttribute("class");
		document.getElementById("remarks").removeAttribute("class");
		document.getElementById("minus").removeAttribute("class");
		document.getElementById("gen").removeAttribute("class");
		document.getElementById("admno").removeAttribute("class");
		document.getElementById("mlc").removeAttribute("class");
		document.getElementById("disclaimer").style.display="block";
		//alert("The Request By do not have right to request the selected Record")
		
		return false;
		}
	
}
function validateReqBy() {
	var value;
	//alert(document.getElementsByName("requestByFlag")[0].value);
	if(document.getElementsByName("requestByFlag")[0].value== "0"){
	 	value=document.getElementsByName("requestByName")[0].value;
	 	 if(value=="" || value=="-1")
			{
				alert("Please enter Request By");
				return false;
			}	
	 	
	 	}
	 else{
	 	  value=document.getElementsByName("requestByExternalName")[0].value;
	 	   if(value=="" || value=="-1")
			{
				alert("Please enter Request By");
				return false;
			}	
	 }
	 return true;
}



function validateMrd(){
	if(document.getElementsByName("isMrdLengthOne")[0].value==<%=MrdConfig.NO%>){
		if(!comboValidation(document.forms[0].mrdCode,"MRD")){
			return false;
		}	
	}
	
	return true;
}

function validateRequestedRecord()
{
	if(document.getElementsByName("isRecordRequested")[0].value==<%=MrdConfig.NO%>)
	{
		alert("Please Add At Least One Record For Request");
		return false;
	}
	return true;
}


function openSearchPopup(e)
{
	var recordType=document.getElementsByName("recordType")[0].value
	var isReqOnOff=document.getElementsByName("isReqOnlineOffline")[0].value
	var dept = document.getElementsByName("requestByEmpDept")[0].value
	//alert(dept);
	var empId = document.getElementsByName("requestById")[0].value
	//alert(empId);
	
	var sheetdept = "";
	if(document.getElementById("recordTable"))
		{	//alert("if");
			 sheetdept = document.getElementsByName("deptname")[0].value
			if(!isSelected(document.getElementsByName("recordType")[0],"Record Type")){
				return false;
			}
			var path="/HISClinical/mrd/offlineMrdRecordRequest.cnt?hmode=POPUP&recordType=" + recordType+"&isReqOnlineOffline="+ isReqOnOff+"&deptname="+ dept+"&employeeId="+empId+"&sheetdept="+sheetdept;
		}
	else
		{	//alert("else");
			if(!isSelected(document.getElementsByName("recordType")[0],"Record Type")){
				return false;
			}
			var path="/HISClinical/mrd/offlineMrdRecordRequest.cnt?hmode=POPUP&recordType=" + recordType+"&isReqOnlineOffline="+ isReqOnOff+"&deptname="+ dept+"&employeeId="+empId;
			//alert(empId);
		}
	openPopup(createFHashAjaxQuery(path),e,300,700);
}

function removeRecord(index){
	document.getElementsByName("recordIdToRemove")[0].value=index
	submitForm21('REMOVE');
}

</script>	

	<body>
		<html:form action="/offlineMrdRecordRequest">
			<his:TransactionContainer>
				<his:SubTitleTag name="Mrd Record Request">
				</his:SubTitleTag>
				
				<his:ContentTag>
						
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="recordType"/> 
									</font>
								</div>
							</td>
							<td width="25%" colspan="1" class="tdfont">
								<div align="left">
								<%-- 	<html:radio name="OfflineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET %>"></html:radio>
									<bean:message key="general"/>
									<html:radio name="OfflineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET %>"></html:radio>
									<bean:message key="mlc"/> --%>
									<html:select property="recordType" styleClass="regcbo" tabindex="1" onchange="if(this.value!='-1') submitForm21('GETMRD');">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.RECORD_TYPE %>">
										<html:options collection="<%=MrdConfig.RECORD_TYPE %>" property="value" labelProperty="label"/>
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="reqPurpose"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="OfflineMrdRecordReqDtlFB" property="reqPurposeId" tabindex="1" styleClass="regcbo">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.ARR_REQUEST_PURPOSE_MST_VO %>">
											<html:options collection="<%=MrdConfig.ARR_REQUEST_PURPOSE_MST_VO %>" property = "reqPurposeId" labelProperty = "purpose" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
						
						<tr>
							
							<td width="25%" class="tdfonthead"></td>							
							<td width="25%" class="tdfonthead"><input type="radio" name="reqByRadio" value="Internal" onclick="requestByType(this)" checked="checked">Internal <input type="radio" name="reqByRadio" value="External"  onclick="requestByType(this)">External
							<html:hidden name="OfflineMrdRecordReqDtlFB" property="requestByFlag"/>
							</td>
							
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="requestBy"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div id="requestByInternal" align="left">
									<html:hidden name="OfflineMrdRecordReqDtlFB" property="requestById" />
									<html:hidden name="OfflineMrdRecordReqDtlFB" property="requestByEmpDept" />
									<html:text name="OfflineMrdRecordReqDtlFB" property="requestByName" readonly="true"></html:text>
								 	<img class="button" src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1" border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event,'requestById@requestByName@requestByEmpDept','<%=0 %>');" 
										onclick="showEmployeepopup(event,'requestById@requestByName@requestByEmpDept','<%=0 %>');" alt="Search Employee" title="Search Employee">
								</div>
								<div id="requestByExternal"   align="left" style="display: none">
									
									<html:text name="OfflineMrdRecordReqDtlFB" property="requestByExternalName"/>
									 <html:hidden name="OfflineMrdRecordReqDtlFB" property="requestByName"/> 
									
								</div>
							</td>
							
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="for"/>
										<bean:message key="days"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="OfflineMrdRecordReqDtlFB" property="forDays" maxlength="3" size="5" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="remarks"/> 
									</font>
								</div>
							</td>
							<td width="25%" colspan="1" class="tdfont">
								<div align="left">
									<html:textarea property="remarks" rows="2" cols="30" tabindex="1" onkeypress="return CheckMaxLength(event,this,500,1)"/>
								</div>
							</td>
						</tr>
					</table>
					<div id="divMrdListId" style="display: none;">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="mrd"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="OfflineMrdRecordReqDtlFB" property="mrdCode" tabindex="1" styleClass="regcbo">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE %>">
												<html:options collection="<%=MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE %>" property = "value" labelProperty = "label" />
											</logic:present>
										</html:select>
									</div>
								</td>
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
						</table>
					</div>		
				</his:ContentTag>
				<his:SubTitleTagBroad name="Request Record Detail">
					<td width="10%" class="tdfonthead">
						<div align="center">
							<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openSearchPopup(event)">
						</div>
					</td>
				</his:SubTitleTagBroad>
				<his:statusTransactionInProcess>
					<logic:notEqual name="OfflineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0" id="recordTable">
								<tr>
									<td width="17%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="crNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="name"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="gender/age"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="13%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="admNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="mlcNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="deptname"/>(<bean:message key="deptunitname"/>)
												</b>	
											</font>
										</div>
									</td>
								<%-- 	<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="hod"/>
												</b>	
											</font>
										</div>
									</td> --%>
									<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="remarks"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="remove"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
								<logic:iterate id="addedRecord" name="<%=MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO %>" type="mrd.vo.CommonCaseSheetEnquiryVO" indexId="idx">
									<tr id="details">
										
										<td width="17%" class="tdfontheadExam" id="cr">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgnum_puk() %>
												</font>
											</div>
										</td>
										<td width="20%" class="tdfontheadExam" id="name">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgstr_fname() %>
													<%=addedRecord.getHrgstr_mname()==null?"":addedRecord.getHrgstr_mname() %>
													<%=addedRecord.getHrgstr_lname()==null?"":addedRecord.getHrgstr_lname() %>
												</font>
											</div>
										</td>
										<td width="10%" class="tdfontheadExam" id="gen">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getGstr_gender_name() %>/
													<%=addedRecord.getHrgnum_age()%>
													
												</font>
											</div>
										</td>
										<td width="13%" class="tdfontheadExam" id="admno">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHipnum_admno()%>
												</font>
											</div>
										</td>
										<td width="15%" class="tdfontheadExam" id="mlc">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgnum_mlc_no()==null?"-":addedRecord.getHrgnum_mlc_no()%>
												</font>
											</div>
										</td>
										<td width="15%" class="tdfontheadExam" id="dept">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getDeptname()==null?"-":addedRecord.getDeptname()%>(<%=addedRecord.getDeptunitname()==null?"-":addedRecord.getDeptunitname()%>)
												</font>
												<input type="hidden" name="deptname" value="<%=addedRecord.getDeptname()==null?"-":addedRecord.getDeptname()%>">
											</div>
										</td>
										<%-- <td width="15%" class="tdfontheadExam" id="hod">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHodName()==null?"-":addedRecord.getHodName()%>
													<input type="hidden" name="hodName" value="<%=addedRecord.getHodName()==null?"-":addedRecord.getHodName()%>">
												</font>
											</div>
										</td> --%>
										<td width="25%" class="tdfontheadExam" id="remarks">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)pageContext.findAttribute("OfflineMrdRecordReqDtlFB");%>
													<html:text property="reqRemarks" maxlength="50" size="30" value='<%=(fb.getReqRemarks()==null?"":fb.getReqRemarks()[idx])%>' tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>
												</font>
											</div>
										</td>
										<td width="5%" class="tdfontheadExam" id="minus">
											<div align="center">
												<img class="button" src='<his:path src="/hisglobal/images/minus.gif"/>' style=cursor:pointer tabindex="1" onclick ="removeRecord('<%=idx %>')" onkeypress="if(event.keyCode==13) removeRecord('<%=idx %>')">
											</div>
										</td>
									</tr>
								</logic:iterate>
								  
							</table>		
						</his:ContentTag>
						<table>
							<tr  style="display:none; visibility:hidden;" >
									<b style="color:red; display:none;" id="disclaimer">Disclaimer:- The Request By do not have right to request the selected Record. </b>
								</tr>
						</table>
						
					</logic:notEqual>	
					
					<logic:equal name="OfflineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="17%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="crNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="name"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="gender/age"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="13%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="fileNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="unit"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="hod"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="remarks"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="remove"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
								<logic:iterate id="addedRecord" name="<%=MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO %>" type="mrd.vo.CommonCaseSheetEnquiryVO" indexId="idx">
									<tr>
										
										<td width="17%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgnum_puk() %>
												</font>
											</div>
										</td>
										<td width="20%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgstr_fname() %>
													<%=addedRecord.getHrgstr_mname()==null?"":addedRecord.getHrgstr_mname() %>
													<%=addedRecord.getHrgstr_lname()==null?"":addedRecord.getHrgstr_lname() %>
												</font>
											</div>
										</td>
										<td width="10%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getGender() %>/
													<%=addedRecord.getAge()%>
												</font>
											</div>
										</td>
										<td width="13%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgstr_file_no()%>
												</font>
											</div>
										</td>
										<td width="15%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getDeptUnit()%>
												</font>
											</div>
										</td>
										<td width="15%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHodName()%>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)pageContext.findAttribute("OfflineMrdRecordReqDtlFB");%>
													<html:text property="reqRemarks" maxlength="50" size="30" value='<%=(fb.getReqRemarks()==null?"":fb.getReqRemarks()[idx])%>' tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>
												</font>
											</div>
										</td>
										<td width="5%" class="tdfontheadExam" >
											<div align="center">
												<img class="button" src='<his:path src="/hisglobal/images/minus.gif"/>' style=cursor:pointer tabindex="1" onclick ="removeRecord('<%=idx %>')" onkeypress="if(event.keyCode==13) removeRecord('<%=idx %>')">
											</div>
										</td>
									</tr>
								</logic:iterate>
							</table>		
						</his:ContentTag>
					</logic:equal>
				</his:statusTransactionInProcess>
			
				<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13) ocancelFunc()">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				</his:ButtonToolBarTag>
			
			
		</his:TransactionContainer>
			<html:hidden name="OfflineMrdRecordReqDtlFB" property="hmode"/>
			<html:hidden name="OfflineMrdRecordReqDtlFB" property="isMrdLengthOne"/>
			<logic:equal name="OfflineMrdRecordReqDtlFB" property="isMrdLengthOne" value="<%=MrdConfig.YES %>">
				<html:hidden name="OfflineMrdRecordReqDtlFB" property="mrdCode"/>
			</logic:equal>
			<html:hidden name="OfflineMrdRecordReqDtlFB" property="concatedIndex"/>
			<html:hidden name="OfflineMrdRecordReqDtlFB" property="isRecordRequested"/>
			<html:hidden name="OfflineMrdRecordReqDtlFB" property="recordIdToRemove"/>
			<html:hidden name="OfflineMrdRecordReqDtlFB" property="isReqOnlineOffline"/>
			<html:hidden name="OfflineMrdRecordReqDtlFB" property="hodName"/>
			<html:hidden name="OfflineMrdRecordReqDtlFB" property="deptname"/>
			
		</html:form>
		<his:status/>
	</body>
</html>