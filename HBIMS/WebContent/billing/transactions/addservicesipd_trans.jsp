<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta charset = UTF-8>
<title>Add Services (IPD)</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/dropdown.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<script language="Javascript" src="../js/billing.js"></script>
<script language="Javascript" src="../js/tariffSearch.js"></script>

<script language="Javascript" src="../js/addservicesipd_trans.js"></script>



</head>

<body onload="onLoadLogics(),addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden', 'strCompChargeCheck', 'strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineActualTariffAmtVal','strOfflineTariffNetAmount'),new Array('t','t','t','t','t','t','s','t','t','t','t'),'1','1','I'),addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden', 'strCompChargeCheck', 'strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineActualTariffAmtVal','strOfflineTariffNetAmount'),new Array('t','t','t','t','t','t','s','t','t','t','t'),'2','1','I'),addRows( new Array('strFromDate','strFromHr','strFromMm','strToDate','strToHr','strToMm') , new Array('t','t','t','t','t','t'), '3' , '1' , 'I' ),addRows( new Array('strPrivateFromDate','strPrivateFromHr','strPrivateFromMm','strPrivateToDate','strPrivateToHr','strPrivateToMm') , new Array('t','t','t','t','t','t'), '4' , '1' , 'I' );">

<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>

<html:form action="/transactions/AddServicesIPDTransCNT" method="post">

<div id="blanket" style="display:none;"></div>
 	
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

	<!--      Drop Down Utility  End              -->

<div class="popUpDiv" id="genQtyCalculatorDivId" style="display:none;">
<table bgcolor="white">
<tr>
<td>

<div class='popup' id='genQtyCalculatorDivIdInner' style="display: block">
	<table width='500' cellpadding="0" cellspacing="0">
		<tr class='HEADER'>
			<th align="left">&nbsp;Calculate Quantity</th>
		</tr>
</table>

<table width='500' cellpadding="1px" cellspacing="1px" >

		<tr> 
			<td class="multiLabel" width="25%" >From Date</td>	
			<td class="multiLabel" width="20%" >From Time</td>
			<td class="multiLabel"  width="25%" >To Date</td>
			<td class="multiLabel" width="20%" >To Time</td>			 
			<td class="multiLabel" width="5%" >
			<img style='cursor:pointer' src="../../hisglobal/images/plus.gif" onclick="addRows( new Array('strFromDate','strFromHr','strFromMm','strToDate','strToHr','strToMm') , new Array('t','t','t','t','t','t'), '3' , '1' , 'R' );" >
			</td>		 
		</tr>
</table>

	<div id="id3"></div>

<table width='500' cellpadding="1px" cellspacing="1px" >
<tr>  
<td colspan="3" class="LABEL" >Date Format : "dd-Mon-yyyy" &amp; Time Format : "24HH:MM"</td>
</tr>
		<tr class='FOOTER'>
			<td colspan='3'>&nbsp;</td>
		</tr>
</table>
<table border="0" width='500' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center"><img style="cursor: pointer" name="saveButton" tabindex="1"
				src="../../hisglobal/images/ok.gif" 
				onClick="setQuantityValues('1');" onkeypress="setQuantityValues('1');"  title="Save Record" />
			
			<img style="cursor: pointer"  tabindex="1"
				src="../../hisglobal/images/btn-ccl.png" onClick="cancelQuantityValues('1');"
				 onkeypress="cancelQuantityValues('1');" title="Cancel Process"></td>
		</tr>
	</table>


	</div>

</td>
</tr>
</table>
</div>


<div class="popUpDiv" id="prvQtyCalculatorDivId" style="display:none;">
<table bgcolor="white">
<tr>
<td>

<div class='popup' id='prvQtyCalculatorDivIdInner' style="display: block">
	<table width='500' cellpadding="0" cellspacing="0">
		<tr class='HEADER'>
			<th align="left">&nbsp;Calculate Quantity</th>
		</tr>
</table>

<table width='500' cellpadding="1px" cellspacing="1px" >

		<tr> 
			<td class="multiLabel" width="25%" >From Date</td>	
			<td class="multiLabel" width="20%" >From Time</td>
			<td class="multiLabel"  width="25%" >To Date</td>
			<td class="multiLabel" width="20%" >To Time</td>			 
			<td class="multiLabel" width="5%" >
			<img style='cursor:pointer' src="../../hisglobal/images/plus.gif" onclick="addRows( new Array('strPrivateFromDate','strPrivateFromHr','strPrivateFromMm','strPrivateToDate','strPrivateToHr','strPrivateToMm') , new Array('t','t','t','t','t','t'), '4' , '1' , 'R' );" >
			</td>		 
		</tr>
</table>

	<div id="id4"></div>

<table width='500' cellpadding="1px" cellspacing="1px" >
<tr>  
<td colspan="3" class="LABEL" >Date Format : "dd-Mon-yyyy" &amp; Time Format : "24HH:MM"</td>
</tr>
		<tr class='FOOTER'>
			<td colspan='3'>&nbsp;</td>
		</tr>
</table>
<table border="0" width='500' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center"><img style="cursor: pointer" name="saveButton" tabindex="1"
				src="../../hisglobal/images/ok.gif" 
				onClick="setQuantityValues('2');" onkeypress="setQuantityValues('2');"  title="Save Record" />
			
			<img style="cursor: pointer"  tabindex="1"
				src="../../hisglobal/images/btn-ccl.png" onClick="cancelQuantityValues('2');"
				 onkeypress="cancelQuantityValues('2');" title="Cancel Process"></td>
		</tr>
	</table>


	</div>

</td>
</tr>
</table>
</div>


	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="AddServicesIPDTransBean" property="strErrMsg" filter="false" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="AddServicesIPDTransBean" property="strNormalMsg" filter="false" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="AddServicesIPDTransBean" property="strWarningMsg" filter="false" /></div>
	<tag:tab tabLabel="Add Services (IPD)" selectedTab="FIRST"
		align="center" onlyTabIndexing="1" width="TABLEWIDTH">
	</tag:tab></center>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td>Add Services (IPD)</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" cellpadding="1px" cellspacing="1px" align="center">
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>CR No.</td>
			<td class="CONTROL" width="15%">
			<crNo:crNo
						id="strCrNoId" name="strCrNo"
						value="${AddServicesIPDTransBean.strCrNo}"
						js="onkeypress='return initGoFunc(event);'"></crNo:crNo> 
			</td>
			<td class="CONTROL" width="35%">
			<img src="../../hisglobal/images/Go.png" style="cursor: hand; cursor: pointer" title="Get Bill Details"
						name="go" onClick="return goFunc();" onKeyPress="return goFunc();"
						align="middle"  >
			</td>
		</tr>
	</table>


<logic:notEmpty property="strCrNo"  name="AddServicesIPDTransBean">

<bean:write name="AddServicesIPDTransBean" property="strPatientDetailsView"
		filter="false" />
	
<table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr> 
    <td width="5%" class="TITLE" align="center">
    <img src="../../hisglobal/images/plus.gif"  width="15" height="15" id="plusaccountDetailsDivId" style="display:block;cursor:pointer;cursor:hand" onClick="showDetails('accountDetailsDivId');">
    <img src="../../hisglobal/images/minus.gif" id="minusaccountDetailsDivId" style="display:none;cursor:pointer;cursor:hand" onClick="hideDetails('accountDetailsDivId')"></td>
    <td colspan="3" class="TITLE"><b>Account Details</b></td> 
   </tr>
 </table>		
<div id = "accountDetailsDivId" style="display:none;">
 <bean:write name="AddServicesIPDTransBean" property="strAccountDetailsView" filter="false"/>
 </div>
 
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr> 
    <td width="5%" class="TITLE" align="center">
    <img src="../../hisglobal/images/plus.gif"  width="15" height="15" id="pluspreviousDetailsDivId" style="display:block;cursor:pointer;cursor:hand" onClick="getPreviousDtls();">
    <img src="../../hisglobal/images/minus.gif" height="15" width="15" id="minuspreviousDetailsDivId" style="display:none;cursor:pointer;cursor:hand" onClick="hideDetails('previousDetailsDivId')"></td>
    <td colspan="3" class="TITLE"><b>Previous Service Details</b></td> 
   </tr>
 </table>
  <div id='previousDetailsDivId' style="display: none;">
   
  </div>
 
 
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr> 
    <td width="5%" class="TITLE" align="center">
    <img src="../../hisglobal/images/plus.gif"  width="15" height="15" id="plusotherDetailsDivId" style="display:none;cursor:pointer;cursor:hand" onClick="showDetails('otherDetailsDivId');">
    <img src="../../hisglobal/images/minus.gif" height="15" width="15" id="minusotherDetailsDivId" style="display:block;cursor:pointer;cursor:hand" onClick="hideDetails('otherDetailsDivId')"></td>
    <td colspan="3" class="TITLE"><b>Other Detail</b></td> 
   </tr>
 </table>
   <div id='otherDetailsDivId' style="display: block;">
   
    <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">

                  <tr>
                  <td class="LABEL" width="25%"><font color="red">*</font>Discharge Date</td>
                  <td class="CONTROL" width="25%">
                  <div id="disDateCompDivId" style="display: none;">
                  <date:date name="strDischargeDate" jsFunc="getDefaultTariffList();" value="${AddServicesIPDTransBean.strCtDate}"></date:date>
                  </div> 
                 <div id="disDateDipslayDivId" style="display: block;">${AddServicesIPDTransBean.strCtDate}</div>
                  </td>
                   <td class="LABEL" width="50%"><font color="red">*</font>Patient Category</td>
                  <td class="CONTROL" width="50%"><select class="comboNormal" name="strPatientCategory" disabled="disabled"  onchange='getDefaultTariffList();'>
                  <bean:write name="AddServicesIPDTransBean" property="strPatientCategoryValues" filter="false"   />
                  </select> </td>
                      </tr>
                  </table>
   
   </div>
   
   <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr class="HEADER"> 
  <td >Tariff Details
  </td>
  </tr>
  </table>
   
  <table class="TABLEWIDTH" align="center" cellspacing ="1px" cellpadding="1px">
  <tr> 
    <td width="5%" class="TITLE" align="center">
    <html:checkbox name="AddServicesIPDTransBean" property="strIsGeneralRequired" value="1" onclick="showDetailsDependent(this , '1'  , 'generalDetailsDivId');" ></html:checkbox>
    <input type="hidden" name="strIsGeneralTariffAvailable" value="0">
  </td>
    <td colspan="3" class="TITLE"><b>General</b></td> 
    <td width='25%' class='TITLE'>
					<div id='gentrfCodeDivId' align="right"> Tariff Code <input type="text" class="txtFldMin" name="strGeneralTariffCode" onkeypress="return validateData(event,8);" onkeyup="getTariffByCode(this , event , '1');" >
					</div>
					</td>

                  <td width='50%' class='TITLE'>

                   <div id='gengroupDivId' align="right"> <b>Group</b> <select name="strGeneralOffLineGroup" class="comboNormal" onchange="getGeneralTariffByGroup();"> 

                  <bean:write name="AddServicesIPDTransBean" property="strOfflineGroupDetails" filter="false" />

                  </select> 
                  </div>
                  </td>
   </tr>
 </table>
   <div id='generalDetailsDivId' style="display: block;">
 	
  <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr> 
  <td class="LABEL" ><div align="left">Compulsory Charges  ( <a style="color: blue;cursor:pointer;cursor:hand" onclick="getQtyCalculator(this , '1');" onkeypress="getQtyCalculator(this , '1');" title="Click Here to Calculate Quantity">Calculate Quantity</a> )</div></td>
  </tr>
  </table>
	<div id="generalCompulsoryCharges" ></div>	  
	<table class="TABLEWIDTH" align="center" cellspacing ="1px" >
  <tr> 
  <td class="LABEL"  colspan="7"><div align="left">Other Charges </div></td>
  </tr>
                        <tr>
                        	  
                              <td class="multiLabel" width="20%">Tariff Name</td>

                              <td class="multiLabel" width="8%">Rate/Unit</td>

                              <td class="multiLabel" width="8%">Qty.</td>

                              <td class="multiLabel" width="10%">Unit</td>

                              <td class="multiLabel" width="8%">Ser. Tax(%)</td>

                              <td class="multiLabel" width="9%">Total Amt.(Rs)</td>

                              <td class="multiLabel" width="3%"><img style='cursor:pointer'

                                    src="../../hisglobal/images/plus.gif"
									onclick="generateRows('1');"
                                    ></td>

                        </tr>
  </table>	     
  	
    
      <div id="id1"></div>

<table class="TABLEWIDTH" align="center" cellspacing ="1px" cellpadding="1px">
<tr>
<td  class="LABEL" width="75%" colspan="3" > General Ward Amount Total
</td>
<td class="multiControl" style="font-weight: bold" ><div id="generalTotalDivId">0.00</div></td>
</tr>
</table>
 </div>	
 
<table class="TABLEWIDTH" align="center" cellspacing ="1px" cellpadding="1px">
  <tr> 
    <td width="5%" class="TITLE" align="center">
    <html:checkbox name="AddServicesIPDTransBean" property="strIsPrivateRequired" value="1" onclick="showDetailsDependent(this , '2'  , 'privateDetailsDivId');" ></html:checkbox>
  	<input type="hidden" name="strIsPrivateTariffAvailable" value="0">
   </td>
    <td colspan="3" class="TITLE"><b>Private</b></td> 
    <td width='25%' class='TITLE'>
					<div id='privatetrfCodeDivId'  align="right"> Tariff Code <input type="text" class="txtFldMin" name="strPrivateTariffCode" onkeypress="return validateData(event,8);" onkeyup="getTariffByCode(this , event , '2');" >
					</div>
					</td>

                  <td width='50%' class='TITLE'>

                   <div id='privategroupDivId' align="right"> <b>Group</b> <select name="strPrivateOffLineGroup" class="comboNormal" onchange="getPrivateTariffByGroup();"> 

                  <bean:write name="AddServicesIPDTransBean"  property="strOfflineGroupDetails" filter="false" />

                  </select> 
                  </div>
                  </td>
   </tr>
 </table>
   <div id='privateDetailsDivId' style="display: none;">
 	
  <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr> 
  <td class="LABEL" ><div align="left">Compulsory Charges  ( <a style="color: blue;cursor:pointer;cursor:hand" onclick="getPvtQtyCalculator(this , '2');" onkeypress="getPvtQtyCalculator(this , '2');" title="Click Here to Calculate Quantity">Calculate Quantity</a> )</div></td>
  </tr>
  </table>
	
	<div id="privateCompulsoryCharges" ></div>
	  
	<table class="TABLEWIDTH" align="center" cellspacing ="1px" >
  <tr> 
  <td class="LABEL"  colspan="7"><div align="left">Other Charges </div></td>
  </tr>
                        <tr>
                        	  
                              <td class="multiLabel" width="20%">Tariff Name</td>

                              <td class="multiLabel" width="8%">Rate/Unit</td>

                              <td class="multiLabel" width="8%">Qty.</td>

                              <td class="multiLabel" width="10%">Unit</td>

                              <td class="multiLabel" width="8%">Ser. Tax(%)</td>

                              <td class="multiLabel" width="9%">Total Amt.(Rs)</td>

                              <td class="multiLabel" width="3%"><img style='cursor:pointer'

                                    src="../../hisglobal/images/plus.gif"
									onclick="generateRows('2');"
                                    ></td>

                        </tr>
  </table>	     
  		
    
      <div id="id2"></div>

<table class="TABLEWIDTH" align="center" cellspacing ="1px" cellpadding="1px">
<tr>
<td  class="LABEL" width="75%" colspan="3" >Private Ward Amount Total
</td>
<td class="multiControl" style="font-weight: bold" ><div id="privateTotalDivId">0.00</div></td>
</tr>
</table>
 </div>
 
<table class="TABLEWIDTH" align="center" cellspacing ="1px" cellpadding="1px">
<tr>
<td  class="LABEL" width="75%" colspan="3" > Grand Total
</td>
<td class="multiControl" width="25%" style="font-weight: bold;color: red" ><div id="grantTotalDivId" >0.00</div></td>
</tr>
</table>


</logic:notEmpty>

 
	<table class="TABLEWIDTH" align="center" cellspacing="0px">
		<tr class="FOOTER">
			<td><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>
	
	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center"><img style="cursor: pointer" name="saveButton" tabindex="1"
				src="../../hisglobal/images/btn-sv.png" 
				onClick="return findBillServices();" onkeypress="return findBillServices();"  title="Save Record" />
			<img style="cursor: pointer"  tabindex="1"
				src="../../hisglobal/images/btn-clr.png" onClick="clearFunc();"
				 onkeypress="clearFunc();" title="Clear Content">
			<img style="cursor: pointer"  tabindex="1"
				src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();"
				 onkeypress="cancelFunc();" title="Cancel Process"></td>
		</tr>
	</table>
	
	

<input type="hidden" name="hmode" />
<input type="hidden" name="strCtDate"
		value="${AddServicesIPDTransBean.strCtDate}" />
 
</html:form>
 <jsp:include page="addservicesipd_multirow_trans.jsp"/>
	<jsp:include page="addservicesipd_dropdown_trans.jsp"/>

</body>

</html>