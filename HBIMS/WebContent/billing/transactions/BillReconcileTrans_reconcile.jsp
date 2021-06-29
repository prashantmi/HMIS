<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>


<html>
<head><meta charset=utf-8>
<title>Bill Reconciliation</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<script language="Javascript" src="../js/billing.js"></script>
<script language="Javascript" src="../js/tariffSearch.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../js/billReconcileTrans_reconcile.js"></script>


</head>
<body onLoad="setCrNoReadOnlyLogic(),document.forms[0].strCrNo.focus(),onLoadLogics(),addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','t','s','t','t','t','t','t','t'),'3','1','I');">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="transactions/BillReconcileTransCNT.cnt"  method="post">
	
		
<div id="blanket" style="display:none;"></div>
		
<div class="popUpDiv" id="tariffDiscountDtls" style="display:none;">
<table bgcolor="white">
<tr>
<td>
<div class='popup' id='tariffDiscountDtlsInner' style="display: block">
	<table width='400' >
		<tr class='HEADER'>
			<th colspan='4' align='left'>&nbsp;Tariff Discount Details</th>
		</tr>
		<tr>
			<td class='LABEL'><font color="red">*</font>Discount / Unit</td>
			<td class='CONTROL'><input type='text'
				name='strOffLineTariffDiscountUnit' class='txtFldMax'
				onkeypress="return validateData(event,7);" /></td>
			<td class='LABEL'><font color="red">*</font>Discount Type</td>
			<td class='CONTROL'><select title="_div_popup_cntl"
				name='strOffLineTariffDiscountType' class='comboNormal'>
				<option value='1'>Fixed</option>
				<option value='2'>Percentage</option>
			</select></td>
		</tr>
		<tr>
			<td class='LABEL'><font color="red">*</font>Discount By</td>
			<td class='CONTROL'><select title="_div_popup_cntl"
				class='comboMax' name="strOffLineTariffDiscountBy">
				<bean:write name="billReconcileTransBean"
					property="strOfflineDiscountApprovedByDetails" filter="false" />
			</select></td>
			<td class='LABEL'><font color="red">*</font>Discount Reason</td>
			<td class='CONTROL'><select title="_div_popup_cntl"
				class='comboNormal' name="strOffLineTariffDiscountReason"
				onchange="setReasonText();">
				<bean:write name="billReconcileTransBean"
					property="strOfflineDiscountRemarksDetails" filter="false" />
			</select> <input type="text" name="strOffLineTariffDiscountReasonText"
				class='txtFldNormal' disabled="disabled" /></td>
		</tr>
		<tr>
			<td colspan='2' class='LABEL'><font color="red">*</font>Discount
			Date</td>
			<td colspan='2' class='CONTROL'><input type="text" class="txtFldDate" maxlength="11" name="strOffLineTariffDiscountDate" value="${billReconcileTransBean.strCtDate }"> (dd-Mon-yyyy)</td>
		</tr>

		<tr class='FOOTER'>
			<td colspan='4'>&nbsp;</td>
		</tr>
	</table>
	<center>
	<table width='300' border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<td>
			<center><img style="cursor: hand; cursor: pointer"
				src='../../hisglobal/images/btn-sv.png'
				onClick='validateTariffDiscountDetails();'> <img
				style="cursor: hand; cursor: pointer"
				src='../../hisglobal/images/btn-ccl.png'
				onClick="hideOffLineTariffDiscountDetails('tariffDiscountDtls');"></center>
			</td>
		</tr>
	</table>
	</center>
	</div>
</td>
</tr>
</table>
</div>
	
	

<div class="popUpDiv" id="multiRowAdderDivId" style="display:none;">
<table bgcolor="white">
<tr>
<td>

<div class='popup' id='multiRowAdderDivIdInner' style="display: block">
	<table width='400' cellpadding="0" cellspacing="0"
		background="../../hisglobal/images/blank.gif">
		<tr class='HEADER'>
			<th colspan='2' align="left">&nbsp;Add Rows</th>
			<th align="right"><img
				src="../../hisglobal/images/stop.png" align="middle"
				onclick="hideMultiRowAdder('multiRowAdderDivId');"></th>
		</tr>

		<tr>
			<td class='LABEL'><font color="red">*</font>Enter The No. of
			Rows to be Added</td>
			<td width="1">&nbsp;</td>
			<td class='CONTROL'><input type='text'
				name='strOffLineTariffNoOfRows' class='txtFldMin' maxlength="2"
				onkeypress="return validateData(event,5),addTariffRows(this,event,'multiRowAdderDivId');" /></td>

		</tr>

		<tr class='FOOTER'>
			<td colspan='3'>&nbsp;</td>
		</tr>
	</table>
	</div>

</td>
</tr>
</table>
</div>

<div id="menu1" class='popup' style="display: none"></div>
<div id="normalMsg" class="normalMsg"><bean:write name="billReconcileTransBean" property="strMsg"/></div>
<div class="errMsg" id="errMsg"><bean:write name="billReconcileTransBean" property="strErrMsg"/></div> 
 <tag:tab tabLabel="Bill Reconciliation" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
 <table class="TABLEWIDTH" cellspacing="1px" cellpadding="1px" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Bill&gt;&gt;Reconciliation</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
    <td colspan="3" class="CONTROL" >
    <crNo:crNo name="strCrNo" value="${billReconcileTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
  <img style="cursor:pointer;cursor:hand;" src="../../hisglobal/images/viewDetails.gif"  title="click here for patient search" name='searchPatient' onclick="showPatientListingWindow('4',document.forms[0].strCrNo,'setSelectedCrNo');"/>
  <img style="cursor: pointer;cursor: hand" src ="../../hisglobal/images/Go.png" align="top"	name="go" onclick="return goFunc();"
						onKeyPress="return goFunc();" > &nbsp;&nbsp;
  </td>   
  </tr>
  </table>
  <div id="tldglbdiv"  style="display:none">
  <table class="TABLEWIDTH" align="center" border='0' cellspacing="1px" cellpadding="1px">
  <tr><td width='5%' class="TITLE" align="center">	<div id="plusonLineId" style="display:none"><img 
  						style="cursor: pointer;cursor: hand"
						src="../../hisglobal/images/plus.gif" name="plusonLine"
						  onclick="showDetails();" /></div>
					<div id="minusonLineId"><img style="cursor: pointer;cursor: hand"
					 src="../../hisglobal/images/minus.gif" name="minusonLine"
						 onclick="hideDetails();"></div>
					</td><td colspan="7" class="TITLE">Patient Details</td>
     </tr></table></div>
   
    <div id="patdtltld"  style="display: none">
       <bean:write name="billReconcileTransBean" property="strPatientDetailsView" filter="false" />
    </div>
   <div id="divBillDtl" style="display:none">
  <table class="TABLEWIDTH" align="center" border='0' cellspacing="1px" cellpadding="1px">
  <tr><td width='5%' class="TITLE" align="center">	<div id="plusonLineId2" style="display: none"><img
						style="cursor: pointer;cursor: hand"
						src="../../hisglobal/images/plus.gif" name="plusonLine2"
						  onclick="showDetails2();" /></div>
					<div id="minusonLineId2"><img style="cursor: pointer;cursor: hand"
					src="../../hisglobal/images/minus.gif" name="minusonLine2"
						 onclick="hideDetails2();"></div>
					</td><td colspan="7" class="TITLE">Bill Details</td>
     </tr></table></div>
  
  <div id="billDtl"></div>
  
   <div id="trfDtl"> </div>
     
     <div id='offlineTariffDivId' style="display:none">
			<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px" cellpadding="1px">
			<tr><td width='5%' class="TITLE" align="center"><div id="plusonLineId1" ><img
						style="cursor: pointer;cursor: hand"
						src="../../hisglobal/images/plus.gif" name="plusonLine1"
						 onclick="showDetails1();" /></div>
					<div id="minusonLineId1" style="display:none"><img style="cursor: pointer;cursor: hand"
					src="../../hisglobal/images/minus.gif" name="minusonLine1"  
					    onclick="hideDetails1('1');"></div>
					</td>  
			<td class="TITLE" width='50%'>
			New Tariff Entry
			</td>
			
			<td width='25%' class='TITLE'>
					<div id='trfCodeDivId' align="right"> Tariff Code <input type="text" class="txtFldMin" name="strTariffCode" onkeypress="return validateData(event,8);" onkeyup="getTariffByCode(this , event);" >
					</div>
					</td>
			
			<td width='25%' class="TITLE">
			 <div align = 'right' id='groupDivId'> <b>Group Name</b> &nbsp;&nbsp;<select name="strOffLineGroup" class="comboNormal" onchange="getTariffDtls();">
			<bean:write name="billReconcileTransBean"
				property="strOfflineGroupDetails" filter="false" />
			</select></div>
			</td>
			</tr>
			</table>
			
			<div id="divNewTrfEntry" style="display:none">
			 <table class="TABLEWIDTH" align="center" border="0" cellspacing="1px" cellpadding="1px">
				<tr>
					<td class="multiLabel" width="30%">Tariff Name</td>
					<td class="multiLabel" width="10%">Rate/Unit</td>
					<td class="multiLabel" width="8%">Qty.</td>
					<td class="multiLabel" width="17%">Unit</td>
					<td class="multiLabel" width="10%">S. Tax(%)</td>
					<td class="multiLabel" width="11%">Dis. Amt.</td>
					<td class="multiLabel" width="9%">Net Amt.</td>
					<td class="multiLabel" width="3%"><img style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/plus.gif"
				onclick="generateRows()"></td>
				</tr>
			</table></div>
			<div id="id3" style="display:none"></div>
			<div id="id4" style="display:none">
			<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px" cellpadding="1px">
				<tr>
					<td  width="80%" class="LABEL">Total Amount (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
					<td width="15%" class="CONTROL" style="font-weight: bold"><input type='hidden' name='strOfflineTotalRecAmount' id='strOfflineTotalRecAmount' value='0.00'><div id='totalRecAmtDivId'>0.00</div></td>
				</tr>
			</table></div>
			
			<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px" cellpadding="1px">
				<tr>
					<td  width="80%" class="LABEL">Net Reconcile Amount (<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
					<td width="15%" class="CONTROL" style="font-weight: bold; color: red"><input type='hidden' name='strOfflineNetRecAmount' id='strOfflineNetRecAmount' value='0.00'><div id='netRecAmountDivId'>0.00</div></td>
				</tr>
			</table>
			
	<div id ="disBnR" style="display:none">
    <table class="TABLEWIDTH" border="0" cellspacing="1px" cellpadding="1px" align="center">
	<tr> <td width='24%' class='LABEL'><div align="right"><font color="red">*</font>Reconciliation By:</div></td>
	<td width="76%" class="CONTROL"><select name="strReconcilationBy"><bean:write name="billReconcileTransBean" property="strReconcilationByValues" filter="false"/></select></td></tr>
	<tr><td width="24%" class="LABEL"><font color="red">*</font>Reconciliation Reason:</td> 
	<td width="76%" class="CONTROL">
	<select name="strReconcilationReason" onChange="setReconcilationReason();">
	<bean:write name="billReconcileTransBean" property="strReconcilationReasonValues" filter="false"/>
	</select>others specify:<input name="strReconcilationReasonText" type="text"  class="txtFldMax" value=""></td></tr>
    </table></div>
    </div>
  <table class="TABLEWIDTH" border="0" cellspacing="1px" cellpadding="1px" align="center">
  <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>	
   
			
			
  <table border="0" class="TABLEWIDTH" cellspacing="1px" align="center">
		<tr>
		
			<td align="center">
			<img style="cursor:pointer;cursor:hand;" src="../../hisglobal/images/btn-sv.png" title="click here for insert data"  name='save' id='save' onclick="return saveData();"/>
			<img style="cursor:pointer;cursor:hand;" src="../../hisglobal/images/btn-clr.png" title="click here for reset value" name="clearImg" onclick="showFirstPage();"/>
			<img style="cursor:pointer;cursor:hand;" src="../../hisglobal/images/btn-ccl.png" title="click here to forward control to main page" name="cancel"  onclick="initPage();">
			</td>
		</tr>
  </table>
	    <input type="hidden" name="hmode">
		<input type="hidden" name="strBillNoVal" value="">
		<input type="hidden" name="strErrMsg" value="${billReconcileTransBean.strErrMsg}">
		<input type="hidden" value="0" name="currDivId">
		<input type="hidden" name="strTariffDetailsValue" value="">
		
</html:form>
<jsp:include page="multirow_BillReconcileTrans_reconcile.jsp"/>
<jsp:include page="dropdown_BillReconcileTrans_reconcile_trans.jsp"/>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>