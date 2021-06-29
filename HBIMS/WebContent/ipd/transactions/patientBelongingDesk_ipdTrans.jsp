<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Patient Belonging Desk</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation1.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newMultiRow.js"></script>
<script language="JavaScript" src="../../ipd/js/patbelongingtrans.js"></script>
<script language="JavaScript" src="../../ipd/js/nursingDesk.js"></script>

</head>
<body onload="viewDesk()">
<center><msg:msg type="${patbelongingTransBean.strMsgType}"
	message="${patbelongingTransBean.strMsgString}"></msg:msg></center>

<html:form action="/transactions/PatBelongingTransCNT.cnt" method="post" onsubmit="goFunc();">
	<div class="normalMsg" id="normalMsg"><bean:write
		name="patbelongingTransBean" property="strNormalMsg" /></div>
	<div class="errMsg" id="errMsg"><bean:write
		name="patbelongingTransBean" property="strErrMsg" /></div>
	<tag:tab tabLabel="Patient Belonging" selectedTab="FIRST"
		align="center" width="TABLEWIDTH">
	</tag:tab>
	<table class="TABLEWIDTH" align="center" border="0" cellspacing="0px"
		cellpadding="1px">
		<tr class="HEADER">
			<td width="50%">Patient Belonging</td>
			<td width="50%" align='right'><input type=radio name=strCheckbox
				value=0 onclick="chkClicked(this)"> Belonging <input
				type=radio name=strCheckbox value=1 onclick="chkClicked(this)">
			Belonging Return <input type="hidden" name="strValue"
				value="${patbelongingTransBean.strValue}" /> <input name=strTmpLabel
				type=hidden value="${ patbelongingTransBean.strValueLable}">
			<script>
		   if(document.getElementsByName("strTmpLabel")[0].value=="Received"){
		   		document.getElementsByName("strCheckbox")[0].checked=true;
		   }else{
		   		document.getElementsByName("strCheckbox")[1].checked=true;
		   }
		   function chkClicked(_these){
		   		if(_these.value==0){
		   			document.forms[0].hmode.value="ADDNURSINGDESK";
		   		}else{
		   			document.forms[0].hmode.value="ADDRETURNNURSINGDESK";
		   		}
		   		document.forms[0].submit();
		   }
		   </script></td>
		</tr>
	</table>
	<div id="CrNoId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td width="50%" class="LABEL">CR No.</td>
			<td class="CONTROL" nowrap="nowrap"><INPUT type="hidden"
				name="strCrNo" value="${patbelongingTransBean.strCrNo}" /> <bean:write
				name="patbelongingTransBean" property="strCrNo"></bean:write></td>
		</tr>
	</table>
	</div>
	<div id="id4" style="display: none;">
	<div id="demDtldiv">
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td colspan="8" class="TITLE">Demographic Detail</td>
		</tr>
	</table>
	</div>
	<div id="id8">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr><td>
				<pDtl:patDtl crNo="${patbelongingTransBean.strCrNo}" address="true"></pDtl:patDtl>
			</td></tr>
		</table>
	
	</div>

	<div id="admDtlTldglbdiv">
	  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td colspan="8" class="TITLE">Admission Details</td>
		</tr>
	</table>
	</div>
	<div id="admDtlTld">
	  <aDtl:addDtl crNo="${patbelongingTransBean.strCrNo}"></aDtl:addDtl></div>
	<div id="id5">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="TITLE">
			<td colspan="8">Patient Belonging/Issued Items Details</td>
		</tr>
		<tr class="CONTROL">
			<td colspan="8"><div align="center" id="belIssMsg" style="display: none;"><font color="red">No Record...</font></div></td>
		</tr>
	</table>
	<div id="divBelonging1">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="TITLE">
			<td colspan="8">Patient Belonging Details</td>
		</tr>
	</table>
	<table class="TABLEWIDTH"  align="center" cellspacing="1px" cellpadding="1px" id="divBelonging">
		<tr>
			<td width="35%" class='multiLabel'><font color="red">*</font>Item Name</td>
			<td width="30%" class='multiLabel'><font color="red">*</font>Quantity</td>
			<td width="30%" class='multiLabel'>
			<div id="remarksIdMandatory"><font color="red">*</font></div>Remarks</td>
			<td width="5%" class='multiLabel'>
			<img onkeypress="onPressingEnter(this,event)" style="cursor: pointer;" 
			src="../../hisglobal/images/plus.gif" onclick="addBelonging('divBelonging')"></td>
		</tr>
	</table>
	</div>	
	<div id="divIssueDtl">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="TITLE">
			<td colspan="8">Patient Issued Item Details</td>
		</tr>
	</table>	
	<table class="TABLEWIDTH" id="divIssuedItem" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td width="35%" class='multiLabel'><font color="red">*</font>Item Name</td>
			<td width="30%" class='multiLabel'><font color="red">*</font>Quantity</td>
			<td width="30%" class='multiLabel'>
			<div id="remarksIssueIdMandatory"><font color="red">*</font></div>Remarks</td>
			<td width="5%" class='multiLabel'>
			<img onkeypress="onPressingEnter(this,event)" style="cursor: pointer;" 
			src="../../hisglobal/images/plus.gif" onclick="addIssuedItem('divIssuedItem')"></td>
		</tr>
	</table>
	<div id="id7">
	<bean:write name="patbelongingTransBean" property="strPatIssuedItemDtl" filter="false"/></div>
	</div>	
	<div id="id1"></div>
	</div>
	<div id="id6">
	<bean:write name="patbelongingTransBean" property="patBelongingDetail" filter="false" /></div>
	</div>
	
	<div id="retrmkDiv" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td align="left" width="50%" class="multiLabel">
			<div align="right">Return Remarks</div>
			</td>
			<td colspan="3" class="CONTROL">
			<div align="left"><textarea name="strReturnRmks" rows="2"></textarea>
			</div>
			</td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="FOOTER">
			<td colspan="8"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td align="center"><img style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/btn-sv.png"
				onclick="return validate1();"> <img
				style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/btn-ccl.png"
				onclick="cancelToDesk();"></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" value="" />

	<input type="hidden" name="strAdmnNo" value="${patbelongingTransBean.strAdmnNo}" />
	<input type="hidden" name="strDeptUnitCode" value="${patbelongingTransBean.strDeptUnitCode}" />
	<input type="hidden" name="strWardCode" value="${patbelongingTransBean.strWardCode}" />
	<input type="hidden" name="chk" value="${patbelongingTransBean.chk}" />	
	<input type="hidden" name="strRemarksMandatoryFlag" value="${patbelongingTransBean.strRemarksMandatoryFlag}" />
	<input type="hidden" name=strIssuedItemValues value='${patbelongingTransBean.strIssuedItemValues }'>
	<input type="hidden" name=strBelongingItemValues value='${patbelongingTransBean.strBelongingItemValues }'>
	<input type="hidden" name=strIssuedItemRequired value='${patbelongingTransBean.strIssuedItemRequired }'>
	<input type="hidden" name=strBelongingRequired value='${patbelongingTransBean.strBelongingRequired }'>
	<input type="hidden" name="strBelMode" value="${patbelongingTransBean.strBelMode}" />	
	<cmbPers:cmbPers />
</html:form>
<script>
	if(document.getElementsByName("strIssuedItemRequired")[0].value==0)
		document.getElementById("divIssueDtl").style.display="none"
	if(document.forms[0].strRemarksMandatoryFlag.value==0){
		document.getElementById("remarksIssueIdMandatory").style.display="none"
		document.getElementById("remarksIdMandatory").style.display="none"
	}
	if(document.getElementsByName("strBelongingRequired")[0].value==0)
	{
		document.getElementById("divBelonging").style.display="none"
		document.getElementById("divBelonging1").style.display="none"
	}
	if(document.forms[0].strRemarksMandatoryFlag.value==0){
		document.getElementById("remarksIssueIdMandatory").style.display="none"
		document.getElementById("remarksIdMandatory").style.display="none"
	}
	if(document.getElementsByName("strBelongingRequired")[0].value==0 && document.getElementsByName("strIssuedItemRequired")[0].value==0)
		{
			document.getElementById("belIssMsg").style.display="block"
		}
	</script>
<tag:autoIndex></tag:autoIndex>
</body>
</html>