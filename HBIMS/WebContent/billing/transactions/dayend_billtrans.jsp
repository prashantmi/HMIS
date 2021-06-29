<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@ page import="java.io.*" %>
<%@ page import ="java.util.*,java.text.*" %>
<%@ page import ="hisglobal.config.*,hisglobal.vo.*" %>
<%@ page import ="billing.BillConfigUtil" %>

<html>
<head>
<meta charset=utf-8>
<title>Scroll-Day End Process</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script src="../../hisglobal/js/datepicker1.js"></script>
<script src="../../hisglobal/js/validation.js"></script>
<script src="../../hisglobal/masterutil/js/master.js"></script>
<script src="../../hisglobal/js/tab.js"></script>
<script src="../../hisglobal/js/calendar.js"></script>
<script src="../../hisglobal/js/multirow.js"></script>
<script src="../../hisglobal/js/datepicker1.js"></script>
<script src="../../hisglobal/js/validation.js"></script>
<script src="../../hisglobal/js/util.js"></script>
<script src="../../billing/js/DayEndTrans.js"></script>
</head>

<body onload="onBodyLoad();">
<html:form action="transactions/DayEndTransCNT.cnt" method="post">

	<div id="errMsg" class="errMsg"><bean:write name="dayEndTransBean" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="dayEndTransBean" property="strWarning"/></div>
    <div id="normalMsg" class="normalMsg"><bean:write  name="dayEndTransBean" property="strMsg"/></div>
 
<!--  
	<tag:tab tabLabel="Day End Process" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
-->
<% 
		UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
		
		System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
		
			
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(userVO.getCheckBackDateDayEndFlag().toString().equals("1") || userVO.getCheckBackDateDayEndFlag().toString().equals("2"))
			{
				out.println("<div align='center'><font color ='red' size='5'>Information: After performing Day End , Please Relogin</font></div>");
				
			}
		}
%>		
	<table class="TABLEWIDTH">
		<tr class="HEADER">
			<td colspan="4">Scroll Generation-Day End Process</td>
		</tr>
	</table>	

	<table class="TABLEWIDTH">	
			<tr>
				<td class="TITLE" width="5%"  ><img alt="Show" id="showPendingDayEndTableId" src="../../hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('pendingDayEndDetailsTable',this);"></td>
				<td class="TITLE" width="95%" >Pending Scroll/Day End Details</td>
			</tr>
 
		</table>
		
		<table class="TABLEWIDTH" id="pendingDayEndDetailsTable" style="display: none;">
			<tr>
				<bean:write name="dayEndTransBean" property="strPendingDayEndDetailsTable" filter="false"/>
					 
			</tr>	
		</table>
	
		<table class="TABLEWIDTH">	
			<tr>
				<td class="TITLE"   ></td>
			</tr>
		</table>
<!-- Day End Process ends	-->
	
	<logic:notEqual value="1" property="strUserMode" name="dayEndTransBean">
		<table class="TABLEWIDTH">	
		 
			<tr>
				<td width="50%"   class="LABEL">Scroll Generation/Day End Date</td>
				<td width="50%"   class="CONTROL">
				
				<logic:equal property="strDateType" name="dayEndTransBean" value="1">
				<input type="hidden" name="strDayEndDate"  value="${dayEndTransBean.strCtDate }"> 
				<bean:write name="dayEndTransBean" property="strCtDate"/> 
				</logic:equal>
				
				<logic:equal property="strDateType" name="dayEndTransBean" value="2">
				<date:date name="strDayEndDate" value="${dayEndTransBean.strCtDate }"  jsFunc="getDayEndAmount();"></date:date>
				</logic:equal>
				
				
				</td>
			</tr>
		
			<tr>
				<td width="50%"   class="LABEL"><font color="red">*</font>User Name</td>
				<td width="50%"   class="CONTROL"><select name="strUsrId" onchange="hidePendingDayEndDetails();getDayEndAmountUserNCounterWise();" class="comboNormal">
					<bean:write name="dayEndTransBean" property="strUserVal"  filter="false" /></select></td>
			</tr>
			</table>	
		
		<logic:equal property="strProcessType" name="dayEndTransBean" value="2">
		<table class="TABLEWIDTH">
			<tr>		
				<td width="50%"   class="LABEL"><font color="red">*</font>Counter Name</td>
				<td width="50%"   class="CONTROL"><select name="strCountId" class="comboNormal" onchange="getDayEndAmountUserNCounterWise();">
					<bean:write name="dayEndTransBean" property="strCounterVal" filter="false" /></select></td>
			</tr>
		</table>	
		</logic:equal>
	</logic:notEqual>
	
	<logic:equal value="1" property="strUserMode" name="dayEndTransBean">
		<table class="TABLEWIDTH">
		
		<tr>
				<td width="50%"   class="LABEL">Scroll Generation/Day End Date</td>
				<td width="50%"   class="CONTROL">
				
				<logic:equal property="strDateType" name="dayEndTransBean" value="1">
				<input type="hidden" name="strDayEndDate"  value="${dayEndTransBean.strCtDate }"> 
				<bean:write name="dayEndTransBean" property="strCtDate"/> 
				</logic:equal>
				
				<logic:equal property="strDateType" name="dayEndTransBean" value="2">
				<date:date name="strDayEndDate" value="${dayEndTransBean.strCtDate }" jsFunc="getDayEndAmount();"></date:date>
				</logic:equal>
				
				
				</td>
			</tr>
			
			<tr>
				<td width="50%" class="LABEL">User Name</td>
				<td width="50%" class="CONTROL"><bean:write
					name="dayEndTransBean" property="strUserName" filter="false" /></td>
			</tr>
			
		</table>
		
		<logic:equal property="strProcessType" name="dayEndTransBean" value="2">
		<table class="TABLEWIDTH">
			<tr>		
				<td width="50%" class="LABEL">Counter Name</td>
				<td width="50%" class="CONTROL"><bean:write
					name="dayEndTransBean" property="strCounterName" filter="false" /></td>
			</tr>
		</table>	
		</logic:equal>
		
	</logic:equal>
	
	<table class="TABLEWIDTH">		
		
		<tr>
			<td width="50%" class="LABEL" colspan="2" align="center">Total Scroll/Day End Cash Amount(<img src="../../hisglobal/images/INR.png">)</td>
			<td width="50%" class="CONTROL" colspan="2">
			<div id="dayEndAmtDiv"><font color='red'><b><bean:write	name="dayEndTransBean" property="strDayEndAmount" filter="false" /></b></font></div>
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL" colspan="2" align="center">Total Scroll(Cheque/DD/Credit & Debit Card/Wallet/Virtual Account/Jan Arogya/Prisoner/Others) Amount(<img src="../../hisglobal/images/INR.png">)</td>
			<td width="50%" class="CONTROL" colspan="2">
			<div id="dayEndAmtDiv2"><font color='red'><b><bean:write	name="dayEndTransBean" property="strDayEndInstAmt" filter="false" /></b></font></div>
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL" colspan="2" align="center">Total Scroll/Day End Credit(CM Fund) Amount(<img src="../../hisglobal/images/INR.png">)</td>
			<td width="50%" class="CONTROL" colspan="2">
			<div id="dayEndAmtDiv1"><font color='red'><b><bean:write	name="dayEndTransBean" property="strDayEndCreditCol" filter="false" /></b></font></div>
			</td>
		</tr>
		<tr style="display: none;">
			<td width="50%" class="LABEL" colspan="2" align="center">Details Required</td>
			<td width="50%" class="CONTROL" colspan="2">
			<html:checkbox property="strIsDetailsRequired" value="1" name="dayEndTransBean" onclick="getVisibilityDetails(this);" ></html:checkbox>
			</td>
		</tr>
		
		</table>
		
		<div id="paymentDetailsRequiredDivId" style="display: none;">
			<table class="TABLEWIDTH">
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">Is Payment Mode Required</td>
					<td width="50%" class="CONTROL" colspan="2">
					<html:checkbox property="strIsPayModeRequired" value="1" name="dayEndTransBean" ></html:checkbox>
					</td>
				</tr>
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">Is Payment Detail Required</td>
					<td width="50%" class="CONTROL" colspan="2">
					<html:checkbox property="strIsPayDetailRequired" value="1" name="dayEndTransBean" ></html:checkbox>
					</td>
				</tr>
			</table>				
		</div>
		
			<div id="DenominationDivId">
			<table class="TABLEWIDTH">		
				<tr class="TITLE" onclick="showBreakupDiv()" style="cursor: pointer;">
					<td width="100%" colspan="4" align="left"> 
					<label class="blink"><font color='ffcc00' style="cursor: pointer;">Amount Breakup Details -Denomination Details</font></label>
					<font color='#FF8C00' size="3" id="plussign">&nbsp;</font>
					<font color='#FF8C00' size="3" id="minusign" style="display: none;">&nbsp;</font></td>					
				</tr>
			</table>
			<table class="TABLEWIDTH">	
			    <tr>
					<td width="50%" class="LABEL" colspan="2" align="center">2000 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strTwoThousandNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>	
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">1000 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strThousandNotes" maxlength="5" onblur="checkFieldForCash(this)" disabled="disabled" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">500 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strFiveHundNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>	
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">200 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strTwoHundNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>	
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">100 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strHundNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">50 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strFiftyNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">20 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
					<input style="width: 30px;height: 15px;" type="text" name="strTwentyNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">10 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strTenNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">5 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strFiveNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">2 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strTwoNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">1 (Note(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
					<input style="width: 30px;height: 15px;" type="text" name="strOneNotes" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">10 (Coin(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strtenCoins" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">5 (Coin(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strFiveCoins" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">2 (Coin(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
					<input style="width: 30px;height: 15px;" type="text" name="strTwoCoins" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>		
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">1 (Coin(s)) ×</td>
					<td class="CONTROL" colspan="2" width="50%">
						<input style="width: 30px;height: 15px;" type="text" name="strOneCoins" maxlength="5" onblur="checkFieldForCash(this)" onclick="makingEmptyFieldForCash(this)" onkeyup="sumAmount()" onkeypress="return validateData(event,5)" value="0">
					</td>
				</tr>
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">Total Denomination Amount Entered</td>
					<td class="CONTROL" colspan="1" width="5%">
						<input style="width: 80px;height: 15px;" type="text" name="strTotalSum" maxlength="9" readonly="readonly" value="0">
					</td>
					<td class="CONTROL" colspan="1" width="45%">
					<a class="roll-link" onclick="clearvalues();" style="cursor:pointer;color:#E93A30">
						<span data-title="Clear Values"><b>Clear Values</b></span></a>
					
					</td>
				</tr>
				
			</table>
		</div>		
		
	<table class="TABLEWIDTH">
		
		<tr style="display: none;">
			<td width="50%" class="LABEL" colspan="2" align="center">Remarks (If Any)</td>
			<td width="50%" class="CONTROL" colspan="2"><textarea name="strRemarks"></textarea></td>
		</tr>
		
		<tr>
			<td class="LABEL" colspan="2" width="50%">Report Format</td>
			<td class="CONTROL" colspan="2" width="50%">
				<select name="strReportFormat" class="comboSmall" onchange=""><option value="html">Html</option><option value="pdf">Pdf</option></select>
			</td>			
		</tr> 		
		<tr style='display:none;'>
			<td class="LABEL" colspan="2" width="50%">Footer Required</td>
			<td class="CONTROL" colspan="2" width="50%">
				<html:checkbox property="strIsFooter" name="dayEndTransBean" value="1"></html:checkbox>
			</td>			
		</tr>
		<tr>
			<td class="LABEL" colspan="2" width="50%">User Remarks</td>
			<td class="CONTROL" colspan="2" width="50%"><input class="txtFldMax" type="text" name="strUserRemarks" ></td>
			
		</tr>
		
		<tr class="FOOTER">
		    <td colspan="2" ><div align='left'><font size="2" color="red">#</font>Total Scroll/Day End Cash Amount is Rounded Off while the Amount in bracket is Actual Amount</div></td>
			<td colspan="2" ><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	
	<div id="saveid">
	<table class="TABLEWIDTH">
		<tr>
			<td align="center">
				<!-- <img style="cursor: pointer" src="../../hisglobal/images/btn-sv.png" name="save" onclick="return validateFunc();"> 
				<img style="cursor: pointer" src="../../hisglobal/images/btn-clr.png" name="clearImg" onclick="resetPage();"> 
				<img name="cancel" src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();"> -->
				
				<br><a href="#" class="button" id="" onClick="return validateFunc();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="resetPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
				</td>
		</tr>
	</table>
	</div>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="userId" value="${dayEndTransBean.strUserId}">
	<input type="hidden" name="strCounterId" value="${dayEndTransBean.strCounterId}">
	<input type="hidden" name="userMode" value="${dayEndTransBean.strUserMode}">
	<input type="hidden" name="strSessionFromTime" value="">
	<input type="hidden" name="strSessionToTime" value="">
	<input type="hidden" name="billModuleId" value="${dayEndTransBean.strBillModuleId}">
	<input type="hidden" name="strDayEndAmount" value="${dayEndTransBean.strDayEndAmount}">
	<input type="hidden" name="strDayEndUserName" value="${dayEndTransBean.strDayEndUserName}">
	<input type="hidden" name="strDayEndCounterName" value="${dayEndTransBean.strDayEndCounterName}">
	<input type="hidden" name="strDayEndCreditCol" value="${dayEndTransBean.strDayEndCreditCol}">
	<input type="hidden" name="strDayEndTimeBoundFlag" value="${dayEndTransBean.strDayEndTimeBoundFlag}">
	<input type="hidden" name="strDayEndAllowedFlag" value="${dayEndTransBean.strDayEndAllowedFlag}">
	<input type="hidden" name="strDayEndAllowedTime" value="${dayEndTransBean.strDayEndAllowedTime}">
	<input type="hidden" name="strCurrentTime" value="${dayEndTransBean.strCurrentTime}">
	<input type="hidden" name="strDayEndInstAmt" value="${dayEndTransBean.strDayEndInstAmt}">
	<input type="hidden" name="strNegativeDayEndAllowed" value="${dayEndTransBean.strNegativeDayEndAllowed}">
	<input type="hidden" name="strDenominationAllowed" value="${dayEndTransBean.strDenominationAllowed}">
	

</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>