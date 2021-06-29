<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient Belonging</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation1.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/patbelongingtrans.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>

</head>
<body onload="view(),addRows(new Array('strItemName','strItemQuantity','strRemarks'),
			       new Array('t','t','t'),'1','1','I');">
<center><msg:msg type="${patbelongingTransBean.strMsgType}"
	message="${patbelongingTransBean.strMsgString}"></msg:msg></center>

<html:form action="/transactions/PatBelongingTransCNT.cnt" method="post" onsubmit="goFunc();">
	
	
	<div class="normalMsg" id="normalMsg"><bean:write name="patbelongingTransBean" property="strNormalMsg"/></div>
	<div class="errMsg" id="errMsg"><bean:write name="patbelongingTransBean" property="strErrMsg"/></div>
	
	<tag:tab tabLabel="Patient Belonging" selectedTab="FIRST"
		align="center" width="TABLEWIDTH">
	</tag:tab>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing="0px" cellpadding="1px">
		<tr class="HEADER">
			<td width="90%">Patient Belonging</td>
            <td width="10%" align='center'>
                 <html:select property="strValue" name="patbelongingTransBean" onchange="fun();">
						<html:option value="0">Received</html:option>
						<html:option value="1">Return</html:option>
				 </html:select>
		    </td>
	    </tr>
	</table>

	<div id="CrNoId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>CR No.</td>
			<td class="CONTROL" nowrap="nowrap"><crNo:crNo
			js="onkeypress='return validateData(event,5);', onkeyup='goFuncOnEnter(event);'"
				value="${patbelongingTransBean.strCrNo}"
				></crNo:crNo>
			<img src="../../hisglobal/images/Go.png" align="top"
				onclick="return goFunc();" style="cursor: pointer; cursor: hand"></td>

		</tr>
	</table>
	</div>

	<div id="id4" style="display: none;">
	<div id="demDtldiv">
	<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td colspan="8" class="TITLE">Demographic Detail</td>
		</tr>
	</table> -->
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
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="TITLE">
			<td colspan="8">Patient Belonging Details</td>
		</tr>
		<tr>
			
			<td width="24%" class='multiLabel'><font color="red">*</font>Item Name</td>
			<td width="24%" class='multiLabel'><font color="red">*</font>Item Quantity</td>
			<td width="24%" class='multiLabel'><font color="red">*</font>Remarks</td>
			<!-- <td width="24%" class='multiLabel'>Item ReturnTo</td> -->
			
			<td width="4%" class="multiLabel"><img style="cursor: hand;cursor: pointer"
				src="../../hisglobal/images/plus.gif" 
				onClick="addRows(new Array('strItemName','strItemQuantity','strRemarks'),
			       new Array('t','t','t'),'1','1','R');"></td>
		</tr>
	</table>
	<div id="id1"></div>
	</div>
	<div id="id6"><bean:write name="patbelongingTransBean"
		property="patBelongingDetail" filter="false" /></div>
	</div>
	
	
	<div id="retrmkDiv" style="display: none">
<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
<tr>
      <td align="left" width="50%" class="multiLabel"><div align="right">Return Remarks </div></td>
      <td colspan="3" class="CONTROL">
	  <div align="left" >
        <textarea name="strReturnRmks"  rows="2" ></textarea>
      </div></td>
    </tr>
</table>
	</div>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="FOOTER">
			<td colspan="8"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center"><img style="cursor: hand;cursor: pointer"
				src="../../hisglobal/images/btn-sv.png" onclick="return validate1();"> <img style="cursor: hand;cursor: pointer"
				src="../../hisglobal/images/btn-clr.png" onClick="fun();"> <img style="cursor: hand;cursor: pointer"
				src="../../hisglobal/images/btn-ccl.png" onclick="menuPage();"></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" value="" />
	
	<input type="hidden" name="strAdmnNo" value="${patbelongingTransBean.strAdmnNo}" />
	<input type="hidden" name="strDeptUnitCode" value="${patbelongingTransBean.strDeptUnitCode}" />
	<input type="hidden" name="strWardCode" value="${patbelongingTransBean.strWardCode}" />
</html:form>
<jsp:include page="patientBelonging_multirow_ipdTrans.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>

