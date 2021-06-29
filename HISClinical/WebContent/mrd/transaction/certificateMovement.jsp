<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="mrd.transaction.controller.fb.CertificateMovementFB"%>
<%@page import="hisglobal.vo.CertificateIssueDtlVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function validateRecordSelection()
{
	var len;
	count=0;
	var valid=true;
	var val=true;
	
	len=document.getElementsByName("chk").length;
	
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("chk")[i].checked)
		{
			count++;
		}
	}
	<% Map mp=(HashMap<String, String>)session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE);
		if(mp==null){
	%>
		val=true;
	<%}else{%>
		val=false;
	<%}%>
	
	if(count == 0 && val==true)
	{
		alert("Please Select At Least a Certificate");
		valid=false;
	}
	return valid;
}

/*function validateAccept()
{
	var valid=false;
	if(validateRecordSelection() && validateCheckList())
		valid=true;
	else
		valid=false;
		
	return valid;		
}*/

/*function validateCheckList()
{
	var valid=false;
	if(document.getElementsByName("compCheckListId")[0].value=="")
	{
		alert("Please Checked The Compulsory Check List.\n To Check Please Click On 'CheckList' ");
		valid=false;
	}
	else
		valid=true;
	
	return valid;	
}*/

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

/*function showCheckList(event)
{
	var compCheckListId=document.getElementsByName("compCheckListId")[0].value;
	var path='/HISClinical/mrd/certificateMovement.cnt?hmode=GETCHECKLIST&compCheckListId='+compCheckListId;
	openPopup(createFHashAjaxQuery(path),event,300,600);
}*/


function checkedSelectedValue()
{
	var str=document.getElementsByName("tempChkValue")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('selectedCheckList');
	for(var i=0;i<arr.length;i++)
	{
		for(var j=0;j<chks.length;j++)
		{
			if(chks[j].value==arr[i])
			{
				chks[j].checked=true;
			}
		}
	}
}

window.onload=function()
{	for(var i=0;i<document.getElementsByName("checkListRemarks").length;i++)
		checkedSelectedValue();
	
	for(var i=0;i<document.getElementsByName("selectedCheckList").length;i++)
	{
		if(document.getElementsByName("selectedCheckList")[i].checked)
			 showRemarks(i);
	}
	
	showDiv();
	
}

function validateRejectReason()
{
	var valid=true;
	if(document.getElementsByName("returnReason")[0].value=="")
	{
		alert("Please Enter The Reject Reason")
		document.getElementsByName("returnReason")[0].focus();
		valid=false;
	}
	
	return valid;
}

/*function validateReject()
{
	var valid=false;
	if(validateRecordSelection() && validateRejectReason())
		valid=true;
	else
		valid=false;
	
	return valid;		
}*/

function showDiv()
{
	if(document.getElementsByName("acceptRejectFlag")[0].checked)
	{
		document.getElementById("divAccept").style.display="block";
		document.getElementById("divReject").style.display="none";
	}
	else
	{
		document.getElementById("divAccept").style.display="none";
		document.getElementById("divReject").style.display="block";
	}
}

function validateSave()
{
	var valid=false;
	if(document.getElementsByName("acceptRejectFlag")[0].checked)
	{
		if(validateRecordSelection() && validateCheckList())
			valid=true;
		else
			valid=false;	
	}
	else
	{
		if(validateRecordSelection() && validateRejectReason())
			valid=true;
		else
			valid=false;	
	}
	
	return valid;
}

function validateCheckList()
{
	var valid=true;
	var lenChk=document.getElementsByName("selectedCheckList").length;
	
	if(document.getElementsByName("recipientEmpNo")[0].value=="-1")
	{
		alert("Please Select The Receive From ")
		document.getElementsByName("recipientEmpNo")[0].focus();
		return false;
	}
	for(var i=0;i<lenChk;i++)
	{
		if(document.getElementsByName("isCompulsory")[i].value=='<%= MrdConfig.YES%>')
		{
			if(!document.getElementsByName("selectedCheckList")[i].checked)
			{
				alert("Please Checked The Compulsory Check List ");	
				return false;
			}
		}
	} 
	
	return valid;
}

function showRemarks(obj)
{
	if(document.getElementsByName("selectedCheckList")[obj].checked)
	{
		document.getElementsByName("checkListRemarks")[obj].readOnly=false;
	}	
	else
	{
		document.getElementsByName("checkListRemarks")[obj].readOnly=true;
		document.getElementsByName("checkListRemarks")[obj].value="";
	}		
	
}
</script>

	<body>
		<html:form action="/certificateMovement">
		<%  int startIndex=-1, endIndex=-1;  %>
			<his:TransactionContainer>
			 
				<his:TitleTag name="Certificate Received Detail">
				</his:TitleTag>
				
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="selectseacrhType"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
											<html:radio name="CertificateMovementFB" property="selectType" value="<%=MrdConfig.CERTIFICATE_SELECT_TYPE_CR_NO %>" onclick="submitForm('CHANGEMODE')"></html:radio>
											<bean:message key="crNo"/>
											<bean:message key="wise"/>
											<html:radio name="CertificateMovementFB" property="selectType" value="<%=MrdConfig.CERTIFICATE_SELECT_TYPE_DEPT %>" onclick="submitForm('CHANGEMODE')"></html:radio>
											<bean:message key="unitWise"/>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="right">
										<logic:equal name="CertificateMovementFB" property="hmode" value="NEW">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="crNo"/>
												</b>	
											</font>
										</logic:equal>
										<logic:equal name="CertificateMovementFB" property="hmode" value="UNIT">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="unit"/>
												</b>	
											</font>
										</logic:equal>
									</div>
								</td>
								<td width="30%" class="tdfont" >
									<div align="left" >
										<logic:equal name="CertificateMovementFB" property="hmode" value="NEW">
											<%String size=Integer.toString(Integer.parseInt(Config.CR_NO_FORMAT_SIZE)+2); %>
							 				<html:text name="CertificateMovementFB" property="patCrNo" maxlength="<%=Config.CR_NO_FORMAT_SIZE %>" size="<%=size %>" value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>" onkeypress="if(event.keyCode==13)if(validateCRNoCHECK('<%=Config.CR_NO_FORMAT_SIZE%>')) submitForm('SHOWLIST'); return validateNumeric(event)">
											</html:text>
											<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateCRNoCHECK('<%=Config.CR_NO_FORMAT_SIZE%>')) submitForm('SHOWLIST')" align="top">
										</logic:equal>		
										<logic:equal name="CertificateMovementFB" property="hmode" value="UNIT">
											<html:select name="CertificateMovementFB" property="unitCode" onchange="submitForm('SHOWLIST')">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MrdConfig.ALL_UNIT_LIST_FOR_CERTIFICATE_MOVE%>">
												<html:options collection="<%=MrdConfig.ALL_UNIT_LIST_FOR_CERTIFICATE_MOVE %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
										</logic:equal>
									</div>
								</td>	
							</tr>
						</table>	
					</his:ContentTag>
					<his:statusTransactionInProcess>
					<%
						PaginationFB fbPageMC= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPageMC);
						fbPageMC.setCurrentPage(((CertificateMovementFB)request.getAttribute("CertificateMovementFB")).getCurrentPage());
						fbPageMC.setObjArrName(MrdConfig.LIST_OF_CERTIFICATE_FOR_MOVEMENT);
						fbPageMC.setAppendInTitle("Certificate");
						fbPageMC.setMaxRecords(10);
						%>
						<html:hidden name="CertificateMovementFB" property="currentPage"/>
						<his:PaginationTag name="fbPagination"></his:PaginationTag>
						
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="select"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="14%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="crNo"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="name"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="14%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="recordType"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="14%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="unit"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="38%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="returnReason"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<%
								CertificateIssueDtlVO[] certificateIssueDtlVO=(CertificateIssueDtlVO[])session.getAttribute(MrdConfig.LIST_OF_CERTIFICATE_FOR_MOVEMENT);
								startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
								endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
								Map map = new HashMap();
								if(session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE)!=null)
									map=(HashMap<String, String>)session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE); 
								
								
								for(int i=startIndex; i<=endIndex; i++)
								{
									CertificateIssueDtlVO certIssueDtlVO=certificateIssueDtlVO[i];	
										
							%>
						
							<tr>
								<td class="tdfont" width="5%" >
									<div align="center">
										<input type="checkbox" name="chk" value="<%=certIssueDtlVO.getCertificateId() %>" <%if(map.get(certIssueDtlVO.getCertificateId())!=null){ %> checked='checked' <%}%>/>
									</div>
								</td>
								<td class="tdfont" width="14%" >
									<div align="center">
										<%=certIssueDtlVO.getPatCrNo() %>
									</div>
								</td>
								<td class="tdfont" width="20%" >
									<div align="center">
										<%=certIssueDtlVO.getHandoverTo() %>
									</div>
								</td>
								<td class="tdfont" width="14%" >
									<div align="center">
										<%=certIssueDtlVO.getRecordTypeName() %>
									</div>
								</td>
								<td class="tdfont" width="14%" >
									<div align="center">
										<%=certIssueDtlVO.getDeptUnitName() %>
									</div>
								</td>
								<td class="tdfont" width="38%" >
									<div align="center">
										<%=certIssueDtlVO.getReturnReason()==null?"-":certIssueDtlVO.getReturnReason() %>
									</div>
								</td>
							</tr>
							<%} %>
								
						</table>
					</his:ContentTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<html:radio name="CertificateMovementFB" property="acceptRejectFlag" value="<%=MrdConfig.CERTIFICATE_ACCEPT %>" onclick="showDiv()"></html:radio>
												<bean:message key="accept"/>
												<html:radio name="CertificateMovementFB" property="acceptRejectFlag" value="<%=MrdConfig.CERTIFICATE_REJECT %>" onclick="showDiv()"></html:radio>
												<bean:message key="reject"/>
											</b>	
										</font>
									</div>	
								</td>
							</tr>
						</table>	
					</his:ContentTag>
					
					<div id="divAccept">
						<his:SubTitleTag name="Receive From Detail">
						</his:SubTitleTag>
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="50%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000" size="1">*</font>
												<bean:message key="rcvFrom"/>
											</font>
										</div>
									</td>
									<td width="50%" class="tdfont">
										<div align="left">
											<html:select name="CertificateMovementFB" property="recipientEmpNo">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MrdConfig.RECIEVING_HANDOVER_EMP_LIST_OPTION %>">
													<html:options collection="<%=MrdConfig.RECIEVING_HANDOVER_EMP_LIST_OPTION %>" property="value" labelProperty="label"/>
												</logic:present>	
											</html:select>
										</div>
									</td>
								</tr>
							</table>
						</his:ContentTag>			
						<his:SubTitleTag name="Check List">
						</his:SubTitleTag>
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="select"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="checklist"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="50%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="remarks"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
								<%String[] strRemarks=(String[])(((CertificateMovementFB)pageContext.findAttribute("CertificateMovementFB")).getCheckListRemarks()); %>
								<logic:iterate id="arrCheckListVO" name="<%=MrdConfig.ARR_CHECKLIST_FOR_MEDICAL_CERTIFICATE_VO %>" type="hisglobal.vo.RecordTypeCheckListMstVO" indexId="idx">
									<tr>
										<td width="10%" class="tdfont">
											<div align="center">
												<%String tempStr="showRemarks('"+idx.toString()+"')"; %>
												<html:checkbox name="CertificateMovementFB" property="selectedCheckList" value="<%=arrCheckListVO.getChecklistId()%>" tabindex="1" onclick="<%=tempStr %>"></html:checkbox>
												<html:hidden name="CertificateMovementFB" property="isCompulsory" value="<%=arrCheckListVO.getIsCompulsory() %>"/>
											</div>
										</td>
										<td width="40%" class="tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													&nbsp;&nbsp;<%if(arrCheckListVO.getIsCompulsory().equals(MrdConfig.YES)){ %>
														<font color="#FF0000" size="1">*</font>
													<%} %>
													<%=arrCheckListVO.getChecklistName() %>
												</font>
											</div>
										</td>
										<td width="50%" class="tdfont">
										<%	
										String str=strRemarks[idx.intValue()];
										if(str==null)str="";
											System.out.println(">>>>"+str);
										%>	
											<div align="center">
												<html:text name="CertificateMovementFB" property="checkListRemarks" size="40" maxlength="50" readonly="true" value="<%=str %>"></html:text>
											</div>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</his:ContentTag>	
					</div>
					<div id="divReject">
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="20%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000" size="1">*</font>
												<bean:message key="reject"/>
												<bean:message key="reason"/>
											</font>
										</div>
									</td>
									<td width="80%" class="tdfont">
										<div align="left">
											<html:textarea name="CertificateMovementFB" property="returnReason" tabindex="1" rows="1" cols="60" onclick="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))">
											</html:textarea>
										</div>
									</td>
								</tr>
							</table>		
						</his:ContentTag>
					</div>			
				</his:statusTransactionInProcess>
				
				<his:ButtonToolBarTag>
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave())submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVE')">
					</his:statusTransactionInProcess>
					
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
					
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
					</his:statusTransactionInProcess>	
				</his:ButtonToolBarTag>
				
				
			</his:TransactionContainer>
			
			<html:hidden name="CertificateMovementFB" property="hmode"/>
			<html:hidden name="CertificateMovementFB" property="tempMode"/>
			<html:hidden name="CertificateMovementFB" property="patCrNo"/>
			<html:hidden name="CertificateMovementFB" property="compCheckListId"/>
			<html:hidden name="CertificateMovementFB" property="tempChkValue"/>
			<html:hidden name="CertificateMovementFB" property="startIdx" value="<%=Integer.toString(startIndex)%>" />
		  	<html:hidden name="CertificateMovementFB" property="endIdx" value="<%=Integer.toString(endIndex)%>"/>
		</html:form>
		<his:status/>
	</body>

</html>