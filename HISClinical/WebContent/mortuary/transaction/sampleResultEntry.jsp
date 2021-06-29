<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="mortuary.transaction.controller.fb.SampleResultEntryFB"%>
<%@page import="hisglobal.vo.MortuaryExtLabRequestDtlVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">

window.onload=function()
{
	//document.getElementsByName("deceasedNo")[0].value=;
}
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validatePostmortemId()
{
	var valid=false;
	if(document.getElementsByName("postmortemId")[0].value=="")
	{
		alert("Please Enter The Postmortem No");
		document.getElementsByName("postmortemId")[0].focus();
		valid=false;
	}
	else
	{
		if(document.getElementsByName("postmortemId")[0].value.length==10)
			valid=true;
		else
		{
			alert("Invalid Postmortem No");
			document.getElementsByName("postmortemId")[0].focus();
			valid=false;
		}
	}
	return valid;
}

function validateSave()
{
	var valid=false;
	if(validateSample()
		&& validateInvestigation()
		&& isEmpty(document.forms[0].finalResult,"Final Result")
		)
		
		valid=true;
	else
		valid=false;	
	return valid;
}

function validateSample()
{
	var valid=true;
	var len=document.getElementsByName("selectedReturnedItem").length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selectedReturnedItem")[i].checked)
		{
			if(document.getElementsByName("receiveRemarks")[i].value=="")
			{
				alert("Please Enter The Received Remarks");
				document.getElementsByName("receiveRemarks")[i].focus();
				valid=false;
			}
		}
		
	}
	
	return valid;
}

function validateInvestigation()
{
	var valid=true;
	for(var i=0;i<document.getElementsByName("labTestId").length;i++ )
	{
		if(document.getElementsByName("labTestResults")[i].value=="")
		{
			alert("Please Enter The '"+ document.getElementsByName("labTestName")[i].value+"' Result");
			document.getElementsByName("labTestResults")[i].focus();
			valid=false;
			break;
		}
	}
	
	return valid;
}

function enabledField(obj)
{
	if(document.getElementsByName("selectedReturnedItem")[obj].checked)
	{
		document.getElementsByName("receiveRemarks")[obj].disabled=false;
	}	
	else
	{
		document.getElementsByName("receiveRemarks")[obj].disabled=true;
		document.getElementsByName("receiveRemarks")[obj].value="";
	}	
}

function clearForm()
{
	document.getElementsByName("finalResult")[0].value="";
	for(var i=0;i<document.getElementsByName("labTestResults").length;i++)
	{
		document.getElementsByName("labTestResults")[i].value="";
	}
	
	for(var i=0;i<document.getElementsByName("receiveRemarks").length;i++)
	{
		document.getElementsByName("receiveRemarks")[i].value="";
	}
}

function openSearchPopup(event)
{
	var path='/HISClinical/mortuary/sampleResultEntry.cnt?hmode=SEARCHPOPUP';
	openPrintPopup(path,300,700);
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
}

</script>

<body>
	<html:form action="/sampleResultEntry">
		<his:TransactionContainer>
			<his:TitleTag name="Sample Result Entry">
			</his:TitleTag>
			
			<his:statusDone> 
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="20%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="postmortemNo"/>
									</font>
								</div>
							</td>
							<td width="20%" class="tdfont">
								<div align="left">
									<html:text name="SampleResultEntryFB" property="postmortemId" maxlength="10" onkeypress= "return validateNumeric(event,this) " tabindex="1" ></html:text>
								</div>
							</td>
							<td width="40%" class="tdfont">
								<div align="left">
									<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style=cursor:pointer tabindex="1" onclick="if(validatePostmortemId()) submitPage('GETREQUEST')" onkeypress="if(event.keyCode==13){if(validatePostmortemId()) submitPage('GETREQUEST')}">
								</div>
							</td>
							<td width="20%" class="tdfont">
								<div align="center">
									<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' style=cursor:pointer tabindex="1" onclick="openSearchPopup(event)" onkeypress="if(event.keyCode==13){openSearchPopup(event)}">
								</div>
							</td>
						</tr>	
					</table>
				</his:ContentTag>
			</his:statusDone>
			<his:statusList>
			<%SampleResultEntryFB fb=(SampleResultEntryFB)pageContext.findAttribute("SampleResultEntryFB");
			String deceasedNo=fb.getDeceasedNo();
			String url="/mortuary/deceasedTile.cnt?deceasedNo="+deceasedNo;
			%>
			<jsp:include page="<%=url %>" flush="true" />
			<%MortuaryExtLabRequestDtlVO[] extVO=(MortuaryExtLabRequestDtlVO[])session.getAttribute(MortuaryConfig.ARR_RECEIVED_REPORT_BY_POSTMORTEM_NO_VO);
			if(extVO!=null){ %>
				<his:SubTitleTag name="Received Lab Test Report">
				</his:SubTitleTag>
				<%Map mapReqId=(Map)session.getAttribute(MortuaryConfig.MAP_REQUEST_ID_N_FINAL_RESULT_BY_POSTMORTEM_NO); 
				Iterator mapKeyItr = mapReqId.keySet().iterator();
				while(mapKeyItr.hasNext())
				{
					String key = (String)mapKeyItr.next();
					MortuaryExtLabRequestDtlVO vo=(MortuaryExtLabRequestDtlVO)mapReqId.get(key);
					
				%>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<%if(vo.getStatusCode().equals(MortuaryConfig.EXTERNAL_LAB_REQUESTE_STATUS_REPORT_RECEIVED)){ %>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="requestDate"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=vo.getRequestDate() %>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="extLab"/>
										<bean:message key="name"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=vo.getExtLabName() %>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="investigation"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" colspan="3" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="result"/>
									</font>
								</div>
							</td>
						</tr>	
						<%Map mapLabId=(Map)session.getAttribute(MortuaryConfig.MAP_REQUEST_ID_N_LAB_TEST_BY_POSTMORTEM_NO); 
						Iterator mapLabKeyItr = mapLabId.keySet().iterator();
						while(mapLabKeyItr.hasNext())
						{
							String labKey = (String)mapLabKeyItr.next();
							if(labKey.equals(key))
							{
								List<MortuaryExtLabRequestDtlVO> lst=(List<MortuaryExtLabRequestDtlVO>)mapLabId.get(labKey);
								for(int i=0;i<lst.size();i++)
								{
						%>
							<tr>
								<td width="25%" class="tdfont">
									<div align="center">
										<%=lst.get(i).getExtLabTest()%>
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="center">
										<%=lst.get(i).getExtLabTestResult()%>
									</div>
								</td>
							</tr>
						<%}}} %>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="final"/>
										<bean:message key="result"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="3">
								<div align="left">
									&nbsp;<%=vo.getResult() %>
								</div>
							</td>
						</tr>
						<%} %>
								
					</table>	
				</his:ContentTag>
				
				<%} %>
			<%} %>
			
			<%MortuaryExtLabRequestDtlVO[] reqVO=(MortuaryExtLabRequestDtlVO[])session.getAttribute(MortuaryConfig.ARR_REQUESTED_ID_BY_POSTMORTEM_NO_VO);
			if( reqVO!=null){ %>
				<his:SubTitleTag name="Sample Send Request Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="select"/>
										</B>
									</font>	
								</div>
							</td>
							<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="requestDate"/>
										</B>
									</font>	
								</div>
							</td>
							<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="extLab"/>
										</B>
									</font>	
								</div>
							</td>
						</tr>
						<logic:iterate name="<%=MortuaryConfig.ARR_REQUESTED_ID_BY_POSTMORTEM_NO_VO %>" id="requestedId" type="hisglobal.vo.MortuaryExtLabRequestDtlVO">
							<tr>
								<td width="20%" class="tdfont" >
									<div align="center">
										<html:radio name="SampleResultEntryFB" property="selectedRequestId" value="<%=requestedId.getRequestId() %>" onclick="submitPage('GETSAMPLENINV')" tabindex="1"></html:radio>
									</div>
								</td>
								<td width="40%" class="tdfont" >
									<div align="center">
										<%=requestedId.getRequestDate() %>
									</div>
								</td>
								<td width="40%" class="tdfont" >
									<div align="center">
										<%=requestedId.getExtLabName() %>
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				<%} %>	
			</his:statusList>
			
			<his:statusTransactionInProcess>
				<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
			<his:SubTitleTag name="Sample Return Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<B>
										<bean:message key="isReturn"/>
									</B>
								</font>	
							</div>
						</td>
						<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<B>
										<bean:message key="sample"/>
									</B>
								</font>	
							</div>
						</td>
						<td width="60%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<B>
										<bean:message key="received"/>
										<bean:message key="remarks"/>
									</B>
								</font>	
							</div>
						</td>
					</tr>
					<logic:iterate name="<%=MortuaryConfig.ARR_REQUESTED_SAMPLE_BY_REQUEST_ID_VO %>" id="requestedSample" type="hisglobal.vo.MortuaryExtReqSampleDtlVO" indexId="idx">
						<%String index=idx.toString(); 
							String str="enabledField('"+index+"')";%>
						<tr>
							<td width="10%" class="tdfont" >
								<div align="center">
									<html:checkbox name="SampleResultEntryFB" property="selectedReturnedItem" value="<%=requestedSample.getItemCode() %>" onclick="<%=str %>" tabindex="1"></html:checkbox>
								</div>
							</td>
							<td width="30%" class="tdfont" >
								<div align="center">
									<%=requestedSample.getItemName() %>
								</div>
							</td>	
							<td width="60%" class="tdfont" >
								<div align="center">
									<html:textarea name="SampleResultEntryFB" property="receiveRemarks" rows="1" cols="70" disabled="true" tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>		
			</his:ContentTag>
			
			<his:SubTitleTag name="Investigation Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<B>
										<bean:message key="investigation"/>
									</B>
								</font>	
							</div>
						</td>
						<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<B>
										<bean:message key="detection"/>
										<bean:message key="for"/>
									</B>
								</font>	
							</div>
						</td>
						<td width="50%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<B>
										<bean:message key="result"/>
									</B>
								</font>	
							</div>
						</td>
					</tr>
					<logic:iterate name="<%=MortuaryConfig.ARR_REQUESTED_INVESTIGATION_BY_REQUEST_ID_VO %>" id="requestedInv" type="hisglobal.vo.MortuaryExtLabInvReqDtlVO">
						<tr>
							<td width="20%" class="tdfont" >
								<div align="center">
									<%=requestedInv.getLabTestName() %>
									<html:hidden name="SampleResultEntryFB" property="labTestName" value="<%=requestedInv.getLabTestName() %>"/>
									<html:hidden name="SampleResultEntryFB" property="labTestId" value="<%=requestedInv.getLabTestId() %>"/>
								</div>
							</td>
							<td width="30%" class="tdfont" >
								<div align="center">
									<%=requestedInv.getLabTestRemrks() %>
								</div>
							</td>	
							<td width="50%" class="tdfont" >
								<div align="center">
									<html:textarea name="SampleResultEntryFB" property="labTestResults" rows="1" cols="70" tabindex="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>		
			</his:ContentTag>
			
			<his:SubTitleTag name="Final Result">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="100%" class="tdfont">
							<div align="center">
								<html:textarea name="SampleResultEntryFB" property="finalResult" rows="2" cols="100" tabindex="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							</div>	
						</td>
					</tr>
				</table>	
			</his:ContentTag>
			
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				
				<his:statusDone>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
				</his:statusDone>
				
				<his:statusList>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('NEW')" onkeypress="if(event.keyCode==13)submitPage('NEW')">
				</his:statusList>
				
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('FINALCANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
				</his:statusUnsuccessfull>
				
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('GETREQUEST')" onkeypress="if(event.keyCode==13)submitPage('GETREQUEST')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				</his:statusTransactionInProcess>
				
			</his:ButtonToolBarTag>
			
		</his:TransactionContainer>
		
		<html:hidden name="SampleResultEntryFB" property="hmode"/>
		<html:hidden name="SampleResultEntryFB" property="deceasedNo" />
		<html:hidden name="SampleResultEntryFB" property="postmortemId" />
		<html:hidden name="SampleResultEntryFB" property="requestId" />
		
	</html:form>
	<his:status/>
</body>
</html>			