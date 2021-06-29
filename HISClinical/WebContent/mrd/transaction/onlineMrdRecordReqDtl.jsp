<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.OnlineMrdRecordReqDtlFB"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.MrdRecordRequestDtlVO"%>
<%@page import="hisglobal.vo.UserVO"%>
<html>
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
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
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
window.onload=function()
{
	showHideMrdList();
	if(document.getElementsByName("isUserEmp")[0].value=="0"){
		document.getElementById("isNotEmp").style.display="block";
		document.getElementById("isEmp").style.display="none";
	}
}


function showHideMrdList()
{
	if(document.getElementsByName("isMrdLengthOne")[0].value==<%=MrdConfig.YES%>)
		document.getElementById("divMrdListId").style.display="none";
	else
		document.getElementById("divMrdListId").style.display="block";
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
	submitForm21("CLEAR");
	
}

function validateSave()
{
	//alert("hello")
	//if(document.getElementsByName("isUserEmp")[0].value=="0")
		//{	//alert("if");
		//	return false;
		//}
	//else
		{
		//alert("else");
			if( comboValidation(document.forms[0].recordType,"Record Type")
				&& comboValidation(document.forms[0].reqPurposeId,"Request Purpose")
				&& isEmpty(document.forms[0].forDays,"For Days")
				&& validateMrd()
				&& validateRequestedRecord()
				&& validateHod()
			){	//alert("if");
				submitForm21('SAVE');
			}
		}
}
function validateHod()
{	//alert("hod");
	var reqdept = document.getElementsByName("reqdeptname")[0].value
	//alert(reqdept);
	var sheetdept = document.getElementsByName("deptname")[0].value
	//document.getElementsByName("hodname")[0].value = "ASHU KRISHNA";
	//document.getElementsByName("reqname")[0].value = "dsf";
	var hodname = document.getElementsByName("hodname")[0].value
	//alert(hodname);
	var reqname = document.getElementsByName("reqname")[0].value
	//alert(reqname);
	//var reqdepthod = document.getElementsByName("reqDeptHod")[0].value
	//alert(reqdepthod);
	//alert("hod"+hodname)
	//alert("reqname"+reqname)
	//alert("reqdepthod"+reqdepthod)
	//alert(hodname)
	if(reqdept == sheetdept)
		{
			if(reqname == hodname)
				{
				//	alert("all same")
					document.getElementsByName("reqByIsHod")[0].value = "1";
					 document.getElementsByName("DESIGNATED_APPROVED_BY")[0].value = "Self Approved";
					return true;
				}
			else
				{
				//alert("else")
				document.getElementsByName("reqByIsHod")[0].value = "0";
					 //document.getElementsByName("DESIGNATED_APPROVED_BY")[0].value =  document.getElementsByName("reqDeptHod")[0].value;
					return true;
				}
		
		}
	else
		{
		//alert("inside else")
			document.getElementsByName("reqByIsHod")[0].value = "0";
			// document.getElementsByName("DESIGNATED_APPROVED_BY")[0].value =  document.getElementsByName("reqDeptHod")[0].value;
			return true;
		}
	
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

//var count = -1;
function openSearchPopup(e)
{
	//count++;
	//alert("ttdfdfdft");
	var recordType=document.getElementsByName("recordType")[0].value
	var userEmpId=document.getElementsByName("userEmpId")[0].value
	var isReqOnOff=document.getElementsByName("isReqOnlineOffline")[0].value
	var deptname=document.getElementsByName("reqdeptname")[0].value
	
	
	if(document.getElementById("recordTable"))
	{
		var sheetdept = document.getElementsByName("deptname")[0].value
		if(!isSelected(document.getElementsByName("recordType")[0],"Record Type")){
			return false;
		}
		var path="/HISClinical/mrd/offlineMrdRecordRequest.cnt?hmode=POPUP&recordType=" + recordType+"&userEmpId="+ userEmpId+"&isReqOnlineOffline="+ isReqOnOff+"&sheetdept="+sheetdept;
	}
else
	{
		if(!isSelected(document.getElementsByName("recordType")[0],"Record Type")){
			return false;
		}
		var path="/HISClinical/mrd/offlineMrdRecordRequest.cnt?hmode=POPUP&recordType=" + recordType+"&userEmpId="+ userEmpId+"&isReqOnlineOffline="+ isReqOnOff;
	}
	
	
	
	openPopup(createFHashAjaxQuery(path),e,300,700);
}

function removeRecord(index){
	document.getElementsByName("recordIdToRemove")[0].value=index
	submitForm21('REMOVE');
}


function getRecordStatus(requestId,reqStatus,recordType,e)
{
	var path="/HISClinical/mrd/onlineMrdRecordRequest.cnt?hmode=GETREQUESTSTATUS&reqStatus=" +reqStatus+"&requestId="+requestId+"&recordType="+recordType;
	openPopup(createFHashAjaxQuery(path),e, "800", "1200");
}


function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function openExtendPopup(requestId,reqStatus,recordType,forDays,e)
{
	var path="/HISClinical/mrd/onlineMrdRecordRequest.cnt?hmode=EXTEND&reqStatus=" +reqStatus+"&requestId="+requestId+"&recordType="+recordType+"&forDays="+forDays;
	openPopup(createFHashAjaxQuery(path),e, "800", "1200");
	}
	
function displayProfileFile(path)
{
	//alert(path)
	window.open(createFHashAjaxQuery(path),'DisplayFile','status=yes,scrollbars=yes,height='+screen.availHeight+',width='+screen.availWidth+ ',left=10,top=10,,dependent=yes,resizable=yes');
}
</script>	

<% MrdRecordRequestDtlVO mrdRecordVO = (MrdRecordRequestDtlVO)session.getAttribute(MrdConfig.LOGIN_USER_REQUEST_BY_DETAILS); %> 
	<body>
		<html:form action="/onlineMrdRecordRequest">
			<his:TransactionContainer>
			<%if(session.getAttribute(MrdConfig.ARR_ONLINE_PENDING_REQUEST_LIST)!=null){ %>
				<%
						PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((OnlineMrdRecordReqDtlFB)request.getAttribute("OnlineMrdRecordReqDtlFB")).getCurrentPage());
						fbPage.setObjArrName(MrdConfig.ARR_ONLINE_PENDING_REQUEST_LIST);
						fbPage.setAppendInTitle("Previous Requests");
						fbPage.setMaxRecords(5);
						%>
						<html:hidden name="OnlineMrdRecordReqDtlFB" property="currentPage"/>
						<his:PaginationTag name="fbPagination"></his:PaginationTag>
				<his:ContentTag>
				
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="requestDate"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="recordType"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="purpose"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="for"/>
											<bean:message key="days"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="extending"/>
											<bean:message key="days"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="request"/>
											<bean:message key="status"/>
										</b>	
									</font>
								</div>
							</td>
							
						</tr>
						<%List lst=(List)session.getAttribute(MrdConfig.ARR_ONLINE_PENDING_REQUEST_LIST);
							int	startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
							int	endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
							for(int i=startIndex;i<=endIndex;i++)
							{
								MrdRecordRequestDtlVO pendingRecordReq=(MrdRecordRequestDtlVO)lst.get(i);
						%>		
						
							<tr>
								<td width="20%" class="tdfontheadExam" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=pendingRecordReq.getReqDate() %>
										</font>
									</div>
								</td>
								<td width="20%" class="tdfontheadExam" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=pendingRecordReq.getRecordTypeName()%>
										</font>
									</div>
								</td>
								<td width="20%" class="tdfontheadExam" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=pendingRecordReq.getPurpose()%>
										</font>
									</div>
								</td>
								<td width="10%" class="tdfontheadExam" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=pendingRecordReq.getForDays()%>
											
										</font>
									</div>
								</td>
								<td width="20%" class="tdfontheadExam" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
											<%if(pendingRecordReq.getReqStatus().equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS)){ 
											if(pendingRecordReq.getExtendDays() != null && !(pendingRecordReq.getExtendDays().equals(""))){%>
											<b><%=pendingRecordReq.getExtendDays() %> <a style="cursor: pointer" onclick="openExtendPopup(<%= pendingRecordReq.getRequestId()%>,<%=pendingRecordReq.getReqStatus() %>,<%=pendingRecordReq.getRecordType() %>, <%=pendingRecordReq.getForDays()%>,  <%=pendingRecordReq.getExtendDays()%>,event)">Update</a></b>
											<%}else{ %>
											<b><a style="cursor: pointer" onclick="openExtendPopup(<%= pendingRecordReq.getRequestId()%>,<%=pendingRecordReq.getReqStatus() %>,<%=pendingRecordReq.getRecordType() %>, <%=pendingRecordReq.getForDays()%>,event)">Extend</a></b>
											<%}}else{ }%>
										</font>
									</div>
								</td>
								<td width="40%" class="tdfontheadExam" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										 <%String status=pendingRecordReq.getReqStatus();
													if(status.equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED))
													status = MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED_LABEL;
													else if(status.equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS))
													status = MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS_LABEL;
													else if(status.equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED))
													status = MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED_LABEL;%>
											<%if(!pendingRecordReq.getReqStatus().equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED)){ %>
											<b><a style="cursor: pointer" onclick="getRecordStatus(<%= pendingRecordReq.getRequestId()%>,<%=pendingRecordReq.getReqStatus() %>,<%=pendingRecordReq.getRecordType() %>,event)"> <%=status %> </a></b>
											<%}else{ %>
												 <%=status %> 
											<%} %>
										</font>
									</div>
								</td>
								
							</tr>
						
						<%} %>
					</table>
				</his:ContentTag>		
				<%} %>
				
				<his:SubTitleTag name="Request By Detail">
				</his:SubTitleTag>
				<his:ContentTag>
				<logic:present name="<%=MrdConfig.LOGIN_USER_REQUEST_BY_DETAILS %>">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0" id="isEmp">
						<tr>
							<td width="17%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="name"/>
												</b>	
											</font>
										</div>
									</td>
							<td width="17%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="designation"/>
												</b>	
											</font>
										</div>
									</td>
							<td width="17%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="deptname"/>
													<bean:message key="unit"/> <%-- added by sandip naik on 09/06/2017 --%>
													
													
												</b>	
											</font>
										</div>
									</td>
							<td width="17%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="hod"/>
												</b>	
											</font>
										</div>
									</td>
							
						</tr>
						<tr>
							<td width="17%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=mrdRecordVO.getLoginRequestByName()==null?"-":mrdRecordVO.getLoginRequestByName() %>
													
													<input type="hidden" name="reqname" value="<%=mrdRecordVO.getLoginRequestByName()==null?"-":mrdRecordVO.getLoginRequestByName() %>">
												</font>
											</div>
										</td>
										<td width="17%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=mrdRecordVO.getLoginRequestByDesig()==null?"-":mrdRecordVO.getLoginRequestByDesig() %>
													
												</font>
											</div>
										</td>
										<td width="17%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=mrdRecordVO.getLoginRequestByDept()==null?"-":mrdRecordVO.getLoginRequestByDept() %>
													
													<input type="hidden" name="reqdeptname" value="<%=mrdRecordVO.getLoginRequestByDept()==null?"-":mrdRecordVO.getLoginRequestByDept() %>">
												</font>
											</div>
										</td>
										<td width="17%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=mrdRecordVO.getLoginRequestByDeptHod()==null?"-":mrdRecordVO.getLoginRequestByDeptHod() %>
													
													<input type="hidden" name="hodname" value="<%=mrdRecordVO.getLoginRequestByDeptHod()==null?"-":mrdRecordVO.getLoginRequestByDeptHod() %>">
												</font>
											</div>
										</td>
						</tr>
					</table>
					</logic:present>
					<logic:notPresent  name="<%=MrdConfig.LOGIN_USER_REQUEST_BY_DETAILS %>">
						<div id="isNotEmp" style="display: none">
								<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									The Logged-In User is not an employee
								</font>
					</div>
					
					</logic:notPresent>
					
				</his:ContentTag>
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
								<%-- 	<html:radio name="OnlineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET %>"></html:radio>
									<bean:message key="general"/>
									<html:radio name="OnlineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET %>"></html:radio>
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
									<html:select name="OnlineMrdRecordReqDtlFB" property="reqPurposeId" tabindex="1" styleClass="regcbo">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.ARR_REQUEST_PURPOSE_MST_VO %>">
											<html:options collection="<%=MrdConfig.ARR_REQUEST_PURPOSE_MST_VO %>" property = "reqPurposeId" labelProperty = "purpose" />
										</logic:present>
									</html:select>
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
									<html:text name="OnlineMrdRecordReqDtlFB" property="forDays" maxlength="3" size="5" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
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
										<html:select name="OnlineMrdRecordReqDtlFB" property="mrdCode" tabindex="1" styleClass="regcbo">
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
					<logic:notEqual name="OnlineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
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
									<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="deptUnitName"/><%-- edited by sandip naik on 17/06/2017 --%>
											
												</b>	
											</font>
										</div>
									</td>
								<%-- 	<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="hod"/> edited by sandip naik on 17/06/2017
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
													<%=addedRecord.getGstr_gender_name() %>/
													<%=addedRecord.getHrgnum_age()%>
												
												</font>
											</div>
										</td>
										<td width="13%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHipnum_admno()%>
												</font>
											</div>
										</td>
										<td width="15%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgnum_mlc_no()==null?"-":addedRecord.getHrgnum_mlc_no()%>
												</font>
											</div>
										</td>
										<td width="15%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getDeptname()==null?"-":addedRecord.getDeptname()%>(<%=addedRecord.getDeptunitname()==null?"-":addedRecord.getDeptunitname()%>)
												</font>
												<input type="hidden" name="deptname" value="<%=addedRecord.getDeptname()==null?"-":addedRecord.getDeptname()%>">
											</div>
										</td>
									<%-- 	<td width="15%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHodName()==null?"-":addedRecord.getHodName()%>
													<input type="hidden" name="reqDeptHod" value="<%=addedRecord.getHodName()%>">
												</font>
											</div>
										</td> --%>
										<td width="25%" class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)pageContext.findAttribute("OnlineMrdRecordReqDtlFB");%>
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
					</logic:notEqual>
					
					<logic:equal name="OnlineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="crNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="name"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="7%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
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
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="unit"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="hod"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
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
										
										<td class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgnum_puk() %>
												</font>
											</div>
										</td>
										<td  class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgstr_fname() %>
													<%=addedRecord.getHrgstr_mname()==null?"":addedRecord.getHrgstr_mname() %>
													<%=addedRecord.getHrgstr_lname()==null?"":addedRecord.getHrgstr_lname() %>
												</font>
											</div>
										</td>
										<td class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getGender() %>/
													<%=addedRecord.getAge()%>
												</font>
											</div>
										</td>
										<td class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHrgstr_file_no()%>
												</font>
											</div>
										</td>
										<td  class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getDeptUnit()%>
												</font>
											</div>
										</td>
										<td  class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=addedRecord.getHodName()%>
												</font>
											</div>
										</td>
										<td  class="tdfontheadExam" >
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)pageContext.findAttribute("OnlineMrdRecordReqDtlFB");%>
													<html:text property="reqRemarks" maxlength="50" size="30" value='<%=(fb.getReqRemarks()==null?"":fb.getReqRemarks()[idx])%>' tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>
												</font>
											</div>
										</td>
										<td class="tdfontheadExam" >
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
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				</his:ButtonToolBarTag>
			
			
		</his:TransactionContainer>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="hmode"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="isMrdLengthOne"/>
			<logic:equal name="OnlineMrdRecordReqDtlFB" property="isMrdLengthOne" value="<%=MrdConfig.YES %>">
				<html:hidden name="OnlineMrdRecordReqDtlFB" property="mrdCode"/>
			</logic:equal>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="concatedIndex"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="isRecordRequested"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="recordIdToRemove"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="requestId"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="reqStatus"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="userEmpId"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="isReqOnlineOffline"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="DESIGNATED_APPROVED_BY"/>
			<html:hidden name="OnlineMrdRecordReqDtlFB" property="reqByIsHod"/>
				<html:hidden name="OnlineMrdRecordReqDtlFB"  property="isUserEmp" />
			
		</html:form>
		<his:status/>
	</body>
</html>