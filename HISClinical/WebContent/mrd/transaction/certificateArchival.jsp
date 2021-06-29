
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.CertificateArchivalFB"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/jquery.dataTables.css"/>
<!-- <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/DataTables/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/bootstrap/dist/css/bootstrap.min.css"/> -->
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
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.dataTables.min.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/DataTables/js/dataTables.bootstrap.js"></script>
 -->

<script type="text/javascript">

window.onload=function()
{
	checkedSelectedValue();
	showRackInfo();
}

function cancelForm(mode)
{ 
	//alert(mode);
	document.getElementsByName("deskmode")[0].value=mode;
	
	document.forms[0].submit();
}

function cancelFunc()
{
	window.parent.closeTab();
}

function selectAllRecord(obj){
	var selectedRecord=document.getElementsByName("selectedRecord")
	if(obj.checked){
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].checked=true;
		}
	}
	else{
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].checked=false;
		}
	}
}


function showRackInfo()
{
	if(document.getElementsByName("rackId")[0].value=="-1")
		document.getElementById("divRackInfoId").style.display="none";
	else
		document.getElementById("divRackInfoId").style.display="none";//block";
}

function checkedSelectedValue()
{
	var str=document.getElementsByName("tempSelectedChk")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('selectedRecord');
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

function showRackShelf()
{
	showRackInfo();
	var rackId=document.getElementsByName("rackId")[0].value;
	//alert(rackId);
	if(rackId!="-1")
		//alert("21");
		submitForm21('GETSHELF');
}

function validateSave()
{
	var count=0;
	for(var i=0;i<document.getElementsByName("selectedRecord").length;i++)
	{
		if(document.getElementsByName("selectedRecord")[i].checked)
			count++;
	}
	
	if(count==0)
	{
		alert("Please Select At Least One Record")
		return false;
	}
	else
	{
		validateArchival()
	}
}

function validateArchival()
{
	if(comboValidation(document.forms[0].rackId,"Rack")
		&& comboValidation(document.forms[0].shelfId,"Shelf")
		&& comboValidation(document.forms[0].putBy,"Put By"))
	{	
		submitForm21('SAVE');
	}	
	
}

function clearForm()
{
	document.getElementsByName("rackId")[0].value="-1";
	document.getElementsByName("shelfId")[0].value="-1";
	document.getElementsByName("putBy")[0].value="-1";
}




$(document).ready(function(){
    console.log("DataTables framework loaded...");
    $('<thead></thead>').prependTo('#example').append($('#example tr:first'));
    $("#example").DataTable(); 
    $("#example_length").css({"font-weight":"bold","font-size":"12px"}) ;
    $("#example_filter").css({"font-weight":"bold","font-size":"12px"});
    $("#example_info").css({"font-weight":"bold","font-size":"12px"}) ;
    $("#example_paginate").css({"font-weight":"bold","font-size":"12px"}) ;

    console.log("Interactive table is ready!"); 
});



</script>
<style>

{

}

</style>

<body>
   <html:form action="/certificateArchivalInMrd">
	<his:TransactionContainer>
		<%String recordTypeName=(String)(((CertificateArchivalFB)pageContext.findAttribute("CertificateArchivalFB")).getRecordTypeName()); 
					recordTypeName=recordTypeName+" Archival In MRD";
		%>
		<his:SubTitleTag name="">
			<div align="left">
				<%=recordTypeName %>
			</div>
		</his:SubTitleTag>
		
		<his:statusTransactionInProcess>
			<his:ContentTag>
				<table id="example" width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="4%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<input type="checkbox" name="selectAll" onclick="selectAllRecord(this)" tabindex="1">
								</div>
							</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="crNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Admission No.
									</b>	
								</font>
							</div>
						</td>
						<td width="16%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="patientName"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Department/Unit
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Ward
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Admission Date Time
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Discharge Date Time
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Accept Date	Time
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<logic:present name="<%=MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_RECORD_TYPE_VO %>">
					<logic:iterate id="arrMrdRecordVO" name="<%=MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_RECORD_TYPE_VO %>" type="hisglobal.vo.MrdRecordDtlVO">
						<tr>
							<td width="4%" class="tdfontheadExam">
								<div align="center">
									<html:checkbox name="CertificateArchivalFB" property="selectedRecord" value="<%=arrMrdRecordVO.getMrdRecordId() %>"></html:checkbox>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatCrNo() %>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatAdmNo() %>
									</font>
								</div>
							</td>
							<td width="16%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getUnitName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getWardName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getAdmDateTime() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getDisDateTime() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getEntryDate() %>
									</font>
								</div>
							</td>
						</tr> 
					</logic:iterate>
					</logic:present>
                       <logic:present name="<%=MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_ADM_NO_VO %>">
					<logic:iterate id="arrMrdRecordVO" name="<%=MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_ADM_NO_VO %>" type="hisglobal.vo.MrdRecordDtlVO">
						<tr>
							<td width="4%" class="tdfontheadExam">
								<div align="center">
									<html:checkbox name="CertificateArchivalFB" property="selectedRecord" value="<%=arrMrdRecordVO.getMrdRecordId() %>"></html:checkbox>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatCrNo() %>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatAdmNo() %>
									</font>
								</div>
							</td>
							<td width="16%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getUnitName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getWardName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getAdmDateTime() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getDisDateTime() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getEntryDate() %>
									</font>
								</div>
							</td>
						</tr> 
					</logic:iterate>
					</logic:present>
					
					
					<logic:present name="<%=MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_CR_NO_VO %>">
					<logic:iterate id="arrMrdRecordVO" name="<%=MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_CR_NO_VO %>" type="hisglobal.vo.MrdRecordDtlVO">
						<tr>
							<td width="4%" class="tdfontheadExam">
								<div align="center">
									<html:checkbox name="CertificateArchivalFB" property="selectedRecord" value="<%=arrMrdRecordVO.getMrdRecordId() %>"></html:checkbox>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatCrNo() %>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatAdmNo() %>
									</font>
								</div>
							</td>
							<td width="16%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getPatName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getUnitName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getWardName() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getAdmDateTime() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getDisDateTime() %>
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrMrdRecordVO.getEntryDate() %>
									</font>
								</div>
							</td>
						</tr> 
					</logic:iterate>
					</logic:present>
					
					
					
				</table>
			</his:ContentTag>
			<%-- <logic:present name="<%=MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_RECORD_TYPE_VO %>"> --%>
			<his:SubTitleTag name="Record Archival Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="rack"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="CertificateArchivalFB" property="rackId" onchange="showRackShelf()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_OF_RACK_BASED_ON_MRD %>" >
										<html:options collection="<%=MrdConfig.LIST_OF_RACK_BASED_ON_MRD %>" property = "rackId" labelProperty = "rackName"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="shelf"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="CertificateArchivalFB" property="shelfId">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_OF_SHELF_BASED_ON_RACK %>" >
										<html:options collection="<%=MrdConfig.LIST_OF_SHELF_BASED_ON_RACK %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr style="display:none;">
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="putBy"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
							<%
								UserVO voUserVO = ControllerUTIL.getUserVO(request);
							
							%>
							<html:hidden name="CertificateArchivalFB" property="putBy" value='<%=(voUserVO.getUserEmpID()!=null && !voUserVO.getUserEmpID().trim().equals(""))?voUserVO.getUserEmpID():voUserVO.getUserId()%>' />
								<%-- <html:select name="CertificateArchivalFB" property="putBy" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" >
										<html:options collection="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>--%>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
						</td>
						<td width="25%" class="tdfont">
						</td>
					</tr>
				</table>
				<div id="divRackInfoId" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="rack"/>
										<bean:message key="information"/>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfont">
								<div align="left">
									&nbsp;<bean:write name="CertificateArchivalFB" property="rackInfo" />
								</div>
							</td>
						</tr>
					</table>		
				</div>
			</his:ContentTag>
			<%-- </logic:present> --%>
		</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)submitForm21('CANCEL')">
			</his:statusTransactionInProcess>
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc('CANCEL')" onkeypress="if(event.keyCode==13)submitForm21('CANCEL')">
			</his:statusUnsuccessfull>	
		</his:ButtonToolBarTag>
	<his:status/>
	
	</his:TransactionContainer>
	
		<html:hidden name="CertificateArchivalFB" property="hmode" value="unspecified"/>
		<html:hidden name="CertificateArchivalFB" property="recordType" />
		<html:hidden name="CertificateArchivalFB" property="recordTypeName"/>
		<html:hidden name="CertificateArchivalFB" property="mrdCode" />
		<html:hidden name="CertificateArchivalFB" property="tempSelectedChk" />
		<html:hidden name="CertificateArchivalFB" property="deskmode" />
	</html:form>
</body>
</html>	
		