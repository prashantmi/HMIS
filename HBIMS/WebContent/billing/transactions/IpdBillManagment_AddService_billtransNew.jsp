<%try{ %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/accountDtl.tld" prefix="acDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/onlineClientDtl.tld" prefix="bDtl"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<title>IPD Bill Management</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
 <link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css">
 

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
  <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />



<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multiRowTLD.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<script language="Javascript" src="../../billing/js/IpdBillMangTransBS.js"></script>
<script language="Javascript" src="../../billing/js/tariffSearch.js"></script>
<script language="Javascript" src="../../billing/js/tariffCodeSearch.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<style type="text/css">
.table-responsive {
    max-height:320px;
}
hr {
    margin-top: 0;
    margin-bottom: 0;
   
}

.tableBodyScroll tbody {
  display: block;
  max-height: 290px;
  overflow-y: scroll;
}

.tableBodyScroll thead,
tbody tr {
  display: table;
  table-layout: fixed;
}

th {
    border-bottom: 2px solid #1f1f20;
    line-height: 2rem;
    color:black;
   
}

td {
    border-bottom:0.1px solid #c9c9d0;
}
</style>
<script type="text/javascript">
	 function viewBill(event)
 {
  var deptcode=document.getElementsByName('strDepartment')[0].value
  
 var wardcode= document.getElementsByName('strSpecialWardType')[0].value
  var accountno=document.getElementsByName('strAcctNo')[0].value
  var chk=document.getElementsByName('strChk')[0].value
  var reportid='1';
  var reportFormat='pdf';
  
  var url="/HBIMS/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=VIEWRPT&deptCode="+deptcode+"&wardCode="+wardcode+"&chk="+chk;
  
  openDependentPopup(url,event,500,800)
 }

</script>
</head>
<%--  <body onload="checkPatientApprovalDtl();addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffDate','strPriority','strDiscount','strDiscountType','strDiscountAmt','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineActualTariffAmtVal','strOfflineTariffNetAmount'),new Array('t','t','t','t','t','s','d','s','t','s','t','t','t','t','t'),'1','1','I');"> --%>
<body onload="checkPatientApprovalDtl();calcTotalRecAmountNew();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="transactions/IpdBillManagementTransBSCNT.cnt" method="post">

<fieldset>

			<legend class='legendHeader' id='nonPrintableLegend'>IPD
				Bill Management</legend>
			<div class="legend2" id='nonPrintableLegend2'>
				<!-- <button  type="button" id="cancelButton" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();"> -->
				<button type="button"
					class="btn btn-outline-danger mt-1 btn-circle cancelbtn"
					onclick="openMenu('SIGLEMENUHOME','')">
					<i class="fas fa-ban iround" title="Cancel"></i>
				</button>
				<button type="button" id="savebutton"
					class="btn btn-outline-success mt-1 btn-circle savebtn"
					tabindex='2' onclick='return goFuncAddService();' data-toggle="modal">
					<i class="fas fa-save iround" title="save"></i>
				</button>
			</div>

			<div class="viewport" id="nonPrintable">
				<div class="container-fluid ">
				  <jsp:include page="IpdBillMgmtHeader.jsp"/>
					<div class="prescriptionTile">
					
 <div id="SHORTCUTDIV1" align="center"></div>
 <div id="blanket" style="display:none;"></div>
 <div id="consumableChargeDiv" style="display: none;"></div>
 
 <div id="chargeDtlDiv" style="display: none;" class="popUpDiv">
 <table bgcolor="white">
	<tr>
	<td>
	 	<table width='200' cellpadding="1px" cellspacing="1px"> 
		<tr class="HEADER">
		<th colspan='4' align='left' width='95%'>&nbsp;Charge Details</th>
		<th width="5%"><img	src="../../hisglobal/images/stop.png" align="middle" onclick="hideChargeDtl();"></th>
		</tr>
		<tr>
				<td colspan='3' class="LABEL">Tariff Charges</td>
				<td colspan='3' class="CONTROL"><div id="tariffCost"></div></td>
		</tr>	
		<tr>
			<td colspan='3' class="LABEL">Consumable Charges </td>
			<td colspan="3" class="CONTROL"><div id="consuambleCost"></div></td>
		</tr>
		</table>
	</td>
	</tr>
 </table>
 </div> 

 
  <!--        Previous Details  Start                  -->
 
 <div class="popUpDiv" id="previousDtls" style="display:none;">
<table bgcolor="white">
<tr>
<td>
<div class='popup' id='previousDtlsInner' style="display: none;">

<table width='600' cellpadding="1px" cellspacing="1px">
		<tr class='HEADER'>
			<th colspan='4' align='left' width='95%'>&nbsp;Previous Details</th>
			<th width="5%">
			<img
				src="../../hisglobal/images/stop.png" align="middle"
				onclick="closePreviousPopup();">
			</th>
		</tr>
</table>
<table width='600' cellpadding="1px" cellspacing="1px" >		
	<tr >
	<td colspan="2" class="LABEL">Date   
	</td>
	<td colspan="3" class="CONTROL"> <select class="comboBig" name="strPreviousDates"  onchange="getPreviousDetails(this);">
									 <bean:write name="ipdBillManagementTransBean" property="strPreviousDateValues" filter="false" />
									</select>
	</td >
	</tr>
	</table>
	
	<div id="previousDtlsContentDiv" ></div>
	<table width='600' cellpadding="1px" cellspacing="1px"> 
	<tr class='HEADER'>
			<th colspan='6' align='left'>&nbsp;<img src='../../hisglobal/images/info.png'>Previous Tariff Details Deletion Allowed Only for Offline raised Tariffs. Online Tariffs can either be deleted from respective raising modules or through refunding of unprocessed services at the time of Bill Approval.</th>
		</tr>	
	<tr>
	<td colspan="6" align="center"> 
	<div id="deleteImg" style="display: none;" >
	<img src="../../hisglobal/images/btn-mo.png"  onclick="deletePreviousDtls();" title="Click to Delete the Selected Record(s)"  style="cursor: pointer;"  />
	</div> &nbsp;  
	</td>
	</tr>
	
</table>
</div>
</td>
</tr>
</table>
</div>
 	 
 	  <!--        Drop Down Untility  Start                  -->
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
				<bean:write name="ipdBillManagementTransBean"
					property="strOfflineDiscountApprovedByDetails" filter="false" />
			</select></td>
			<td class='LABEL'><font color="red">*</font>Discount Reason</td>
			<td class='CONTROL'><select title="_div_popup_cntl"
				class='comboNormal' name="strOffLineTariffDiscountReason"
				onchange="setReasonText();">
				<bean:write name="ipdBillManagementTransBean"
					property="strOfflineDiscountRemarksDetails" filter="false" />
			</select> <input type="text" name="strOffLineTariffDiscountReasonText"
				class='txtFldNormal' disabled="disabled" /></td>
		</tr>
		<tr>
			<td colspan='2' class='LABEL'><font color="red">*</font>Discount
			Date</td>
			<td colspan='2' class='CONTROL'><input type="text" class="txtFldDate" maxlength="11" name="strOffLineTariffDiscountDate" value="${ipdBillManagementTransBean.strCtDate }"> (dd-Mon-yyyy)</td>
		</tr>

		<tr class='FOOTER'>
			<td colspan='4'>&nbsp;</td>
		</tr>
	</table>
	<center>
	<table width='300' border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<td>
			<center>
				<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/ok.gif' onClick='validateTariffDiscountDetails();'> 
				<img style="cursor: hand; cursor: pointer" src='../../hisglobal/images/btn-ccl.png' onClick="hideOffLineTariffDiscountDetails('tariffDiscountDtls');">
			</center>
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
	
	<!--      Drop Down Untility  End              -->
 
      <div id="errMsg" class="errMsg"><bean:write     name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
   	 <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
	
 <!--<tag:tab tabLabel="Add Services"  onlyTabIndexing="1"  selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>-->
	<tag:tabNew tabList="${ipdBillManagementTransBean.lhm}" selectedTab="ADDSERVICE" align="center"
	width="TABLEWIDTH"></tag:tabNew>
	<!-- <hr style="border-top: 2px solid rgb(198, 196, 196)"> -->
	<div class="row newrow1"></div>
	<br>
 <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px" style="display: none;">
  <tr class="HEADER"> 
    <td width="50%">Add&gt;&gt;Service</td>
    <td width="50%" align="right">
    	<a id="viewBill" style="cursor: pointer;" onclick="viewBill(event)" 
					title="View Bill"><font color="blue" style=""><u>View Bill</u></font></a>
    </td>
  </tr>
  
  <tr> 
    <td width="50%" class="LABEL">CR No.</td>
    <td width="50%" class="CONTROL">
    <bean:write  name="ipdBillManagementTransBean" property="strCrNo" />
    </td>   
  </tr>
  </table>
  


  <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px" style='display: none;'>
	  <tr> 
	    <td><div class="line"><label class="DIVLABEL"><b> &nbsp;Previous Details (<span style="color:blue;cursor: pointer;" onclick="popup('previousDtls', '80','200');"> Click here to view Previous Details </span>)</b></label></div></td> 
	  </tr>                
  </table>
   
 <div id ="otherCreditDetailsId" style="display: none;">
	  <table class="NEWTABLEWIDTH" align="center" cellpadding="0" cellspacing="">
	  
	  <tr> 
		  <td width="2%" colspan='1'> 
		  		<div id="plusCreditDtlsId" style="display: block;" class="lineContinue2"> 
					<img src="/HBIMS/hisglobal/images/plus.gif" name="plusonLine" style="cursor:hand;cursor:pointer" onclick="displayCreditDetails('creditDtlsDivId');" align="middle">					
				</div>
				<div id="minusCreditDtlsId" style="display: none;" class="lineContinue2">
					<img src="/HBIMS/hisglobal/images/minus.gif" style="cursor:hand;cursor:pointer" name="minusonLine" onclick="hideCreditDetails('creditDtlsDivId');">
				</div>
		  </td>
		  <td colspan="3" width='98%'><div class="lineContinue"><label class='DIVLABEL'>Client Details</label></div> </td>
	  </tr>		
	  </table>
	</div>
	
	
	<div id ="creditDtlsDivId" style="display: none;">	
			  		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr><td>
				<bDtl:onlnCltDtl crNo="${ipdBillManagementTransBean.strCrNo}" ></bDtl:onlnCltDtl>
			</td></tr>
		</table>					
	</div>
   
                       <div id='otherDetailsDivId' style="display: block;">
							<div class="row rowFlex reFlex">
							<div class="col-sm-6">
							<div class="row rowFlex reFlex">
								<div class="col-sm-3" align="right">
									<label><font color="red">*</font>Treat Category</label>
								</div>
								<div class="col-sm-3">
									<div id="treatCatComboDivId">
										<select class="browser-default custom-select" name="strNewTreatmentCategory">
											<bean:write name="ipdBillManagementTransBean"
												property="strTreatmentCategoryValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-3"  align="right">
									<label><font color="red">*</font>Charge Type</label>
								</div>
								<div class="col-sm-3">
									<div id="wardTypeDivId">
										<select class="browser-default custom-select" name="strWardType">
											<bean:write name="ipdBillManagementTransBean"
												property="strWardTypeValues" filter="false" />
										</select>
									</div>
								</div>
								</div></div>
								<div class="col-sm-6">
								<div class="row rowFlex reFlex">
								<div class="col-sm-2" align="right">
								<label class="">Group</label>
							</div>
							<div class="col-sm-4">
							<div id='groupDivId' >
								<select name="strOffLineGroup" class="browser-default custom-select"
									onchange="getTariffDtls();" tabindex="1">
									<bean:write name="ipdBillManagementTransBean"
										property="strOfflineGroupDetails" filter="false" />
								</select> <input type="hidden" name="strHidden"
									value="${ipdBillManagementTransBean.strHidden}">
							</div></div>
								<div class="col-sm-3" align="right" >
								<label class="blinking">Enter Tariff Code </label>
							</div>
							<div class="col-sm-3" id='trfCodeDivId'>
						   <input type="text" class="form-control" id="strTariffCode"
									name="strTariffCode" autocomplete="false" tabindex="1"
									maxlength="15" onkeypress="return validateData(event,8);"
									onkeyup="getTariffByCodeNew(this , event,1),showTariffCodeSearchPopup(event,'strTariffCode','0');">
							</div>
							</div>
							</div>
							</div>
							<div class="row newrow1"></div>
<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  	<tr style="display: none;">  
   		<td class="LABEL" width="25%"><font color="red">*</font>Department</td>
        <td class="CONTROL" width="25%">
	        <div id="deptComboDivId" >
		        <select class="comboNormal" name="strDepartment" onchange="getSpecialWardDtls();" >
		        	<bean:write name="ipdBillManagementTransBean" property="strDepartmentValues" filter="false"   />
		        </select>
	        </div>
            <div id="deptLabelDivId" style="display: none;" ></div>                  
      	</td>
      	<td class="LABEL" width="25%"><font color="red">*</font>Ward Name</td>
		<td class="CONTROL" width="25%">
 			<div id="specialWardTypeDivId" >
				<select class="comboNormal" name="strSpecialWardType" >
					<bean:write name="ipdBillManagementTransBean" property="strSpecialWardTypeValues" filter="false"   />
 				</select>                  
			</div>
   			<div id="specialWardTypeLabelDivId" style="display: none;" ></div>                  
 		</td>                         
    </tr>
	
	
	
	<tr style="display: none;">
 		<td class="LABEL" width="25%"><font color="red">*</font>Start Date</td>
 		<td class="CONTROL" width="25%">
  			<div id="startDateDivId" >
 				<date:date name="strStartDate"  value="${ipdBillManagementTransBean.strEndDate}"></date:date> 
			</div>
 			<div id="startDateLabelDivId" style="display: none;" ></div>
		</td>
		<td class="LABEL" width="25%"><font color="red">*</font>End Date</td>
		<td class="CONTROL" width="25%">
  			<div id="endDateDivId" >
				<date:date name="strEndDate"  value="${ipdBillManagementTransBean.strCtDate}"></date:date>
			</div>
     		<div id="endDateLabelDivId" style="display: none;" ></div>
		</td>
 	</tr>                      
 </table> 
 <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px" style="display: none;">
  <tr>                       
   <td class="multiControl" colspan="2" width="50%">                      
   	<img src="../../hisglobal/images/Go.png" tabindex="1" id="goButtonId" style="cursor: pointer;" onkeypress="goFuncValidation();" onclick="goFuncValidation();"   title="Click here to get Compulsory Charges and Special Charges">                      
 </td>
</tr>
</table>   
</div>
  
	   
	   	<bean:write name="ipdBillManagementTransBean" property="strAllTrfHLP" filter="false"/>
  
  <div id="xyz"  style="display: none;">
	  <table class="TABLEWIDTH" align="center" cellspacing ="1px">
	  	<tr> 
		    <td colspan="3"><b></b></td> 
	   </tr>
	  </table>
  </div>   
  <logic:equal name="ipdBillManagementTransBean" property="strIpdBillManagementMode"  value="2">   
  <div id="compulsoryChargesDivId"  style="display: none;">
	  <table class="TABLEWIDTH" align="center" cellspacing ="1px">
	  	<tr> 
		    <td width="5%" class="TITLE" align="center">
			    <img src="../../hisglobal/images/plus.gif"  width="15" height="15" id="pluscompulsoryChargesDetailsDivId" style="display:none;cursor:pointer;cursor:hand" onClick="showDetails('compulsoryChargesDetailsDivId');">
			    <img src="../../hisglobal/images/minus.gif" width="15" height="15" id="minuscompulsoryChargesDetailsDivId" style="display:block;cursor:pointer;cursor:hand" onClick="hideDetails('compulsoryChargesDetailsDivId')"></td>
		    <td colspan="3" class="TITLE"><b>Compulsory Charges Detail</b></td> 
	   </tr>
	  </table>
	  <div id='compulsoryChargesDetailsDivId' style="display: block;"></div>   
  </div>      
  <div id="specialChargesDivId"  style="display: none;">   
   <table class="TABLEWIDTH" align="center" cellspacing ="1px">
	   <tr> 
		    <td width="5%" class="TITLE" align="center">
			    <img src="../../hisglobal/images/plus.gif"  width="15" height="15" id="plusspecialChargesDetailsDivId" style="display:none;cursor:pointer;cursor:hand" onClick="showDetails('specialChargesDetailsDivId');">
			    <img src="../../hisglobal/images/minus.gif" width="15" height="15" id="minusspecialChargesDetailsDivId" style="display:block;cursor:pointer;cursor:hand" onClick="hideDetails('specialChargesDetailsDivId')"></td>
		    <td colspan="3" class="TITLE"><b>Special Charges Detail</b></td> 
	   	</tr>
 	</table>
   <div id='specialChargesDetailsDivId' style="display: block;"></div>   
   </div>   
   </logic:equal>
      
  <div id="AvailedTariffDtlsId" style="display: none;">
  	<bean:write name="ipdBillManagementTransBean" property="strParticulaDtl" filter="false"/>
  </div> 
  
  <div id='offlineTariffDivId' style="display: none;">
	  <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">                 
		  <tr>                        	  
	          <td class="multiLabel" width="14%">Tariff Name</td>
	          <td class="multiLabel" width="8%">Rate/Unit</td>
	          <td class="multiLabel" width="8%">Qty.</td>
	          <td class="multiLabel" width="8%">Unit</td>                              
	          <td class="multiLabel" width="12%">Date</td>
	          <td class='multiLabel' width="9%">Priority<td>                              
	          <td class='multiLabel' width="8%">Discount/unit<td>                              
	          <td class='multiLabel' width="9%">Discount Type<td>                              
	          <td class='multiLabel' width="8%">Discount Amt<td>                              
	          <td class="multiLabel" width="4%">Ser. Tax(%)</td>
	          <td class="multiLabel" width="9%">Total Amt.(Rs)</td>
	          <td class="multiLabel" width="3%">
	          <img style='cursor:pointer' src="../../hisglobal/images/plus.gif" onclick="generateRows()"></td>
		   </tr>
	   </table>
   	   <div id="id1"></div>
   </div>
	
  
  
    <bean:define id="userValueId"  value='<%=(String)session.getAttribute("USERVALUE") %>' ></bean:define>
                <br>                        
                        <div class="row rowFlex reFlex">
                         <div class="col-sm-3" style="color: #173479;font-weight: bold;">
									Advance/Client Amount(<i class="fas fa-rupee-sign"></i>):&nbsp;<label style="color: red;">
									<bean:write     name="ipdBillManagementTransBean" property="strAdvanceamt"/></label>
								</div>
								<div class="col-sm-7"></div>
								 <div class="col-sm-2" style="color: #173479;font-weight: bold;" align="right">
									Net Amount(<i class="fas fa-rupee-sign"></i>):&nbsp;<label id='totalRecAmtDivId'>0.00</label>
								   <input type='hidden' name='totalRecAmtDivId1' id='totalRecAmtDivId1' value="">
								</div>
							</div>
	

  <table id="revtab" style="display:none;">
  <tr id="revtab0">
  </tr>
  </table>
   
   
	<div id="loadingmessage" class="wrapper rounded" style="display:none;width:180px;height:100px;position:absolute;top:40%;left:42%;padding:2px;z-index:100;">
				<div class="div-table">
					<div class="div-table-row">
						<div class="div-table-col title width100 ">
								Please Wait...
						</div>
					</div>
					<div class="div-table-row alignCenter" >
						<div class="div-table-col width100 " style="margin-top: 7px;">
								<img src="/HIS/hisglobal/images/ajax-loader.gif" width="50" height="50" />
						</div>
					</div>
				</div>
	</div>

<hr>
                    <div class="row rowFlex reFlex">
							<div class="col-sm-10"></div>
							<div class="col-sm-2" align="right">
								<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>Fields Mandatory
							</div>
						</div>
	<input type="hidden" name="hmode">
	<input type="hidden" name="strChk" value="${ipdBillManagementTransBean.strChk}">
	<input type="hidden" name="chk" value="${ipdBillManagementTransBean.strChk}">
	<input type="hidden" name="strChkBoxComboValue" value="${ipdBillManagementTransBean.strChkBoxComboValue}">
	<input type="hidden" name="strWardCode" value="${ipdBillManagementTransBean.strWardCode}">
	<input type="hidden" name="strHospitalCode" value="${ipdBillManagementTransBean.strHospitalCode}">
	<input type="hidden" name="strIpdRoundOff" value="${ipdBillManagementTransBean.strIpdRoundOff}">
	<input type="hidden" name="strIpdThirdPartyBilling" value="${ipdBillManagementTransBean.strIpdThirdPartyBilling}">	
	<input type="hidden" name="strExcessCreditLimit" value="${ipdBillManagementTransBean.strExcessCreditLimit}" />	 
	<input type="hidden" name="strCtDate" value="${ipdBillManagementTransBean.strCtDate}" />	
	<input type="hidden" name="strIpdBillManagementMode" value="${ipdBillManagementTransBean.strIpdBillManagementMode}" />
	<input type="hidden" name="strTempTreatCat" value="0" />
	<input type="hidden" name="strTempWardCode" value="0" />	
	<input type="hidden" name="searchColumn" value='<%=request.getParameter("searchColumn") %>' />
	<input type="hidden" name="search" value='<%=request.getParameter("search") %>' />
	<input type="hidden" name="blockNo" value='<%=request.getParameter("blockNo") %>' />
	<input type="hidden" name="prevNext" value='<%=request.getParameter("prevNext") %>' />
	<input type="hidden" name="rowNum" value='<%=request.getParameter("rowNum") %>'/>
	<input type="hidden" name="divisionId" value='<%=request.getParameter("divisionId") %>' />
	<input type="hidden" name="dietChargeId" value="${ipdBillManagementTransBean.dietChargeId}" />
	<input type="hidden" name="strConsumableCharge" value="${ipdBillManagementTransBean.strConsumableCharge}" />
	<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${ipdBillManagementTransBean.strOfflineTotalPayAmountWithoutConsumable}" />
	<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${ipdBillManagementTransBean.strGroupIdForConsumableConcatenated}" />
	<input type="hidden" name="strConsumableChargesGroupId" value="${ipdBillManagementTransBean.strConsumableChargesGroupId}" />
	<input type="hidden" name="strConsumableChargesTariffCode" value="${ipdBillManagementTransBean.strConsumableChargesTariffCode}" />
	<input type="hidden" name="strUrgSur" value="${ipdBillManagementTransBean.strUrgSur}">
	<input type="hidden" name="strNumRows" value="${ipdBillManagementTransBean.strNumRows}">
	<input type="hidden" name="selectedTab">
	<input type="hidden" name="strIsCalledFromIpdBillNew" value="${ipdBillManagementTransBean.strIsCalledFromIpdBillNew}" />
	<input type="hidden" name="strAcctStatMode" value="${ipdBillManagementTransBean.strAcctStatMode}" />
	<input type="hidden" name="grpid" value="${ipdBillManagementTransBean.grpid}" />
	<input type="hidden" name="strAdvanceamt" value="${ipdBillManagementTransBean.strAdvanceamt}" />
	<input type="hidden" name="strArogyaIpdCreditLimit" value="${ipdBillManagementTransBean.strArogyaIpdCreditLimit}" />
	<input type="hidden" name="strCurrentRowIndex" value="" />
	<input type="hidden" name="strCurrentRowTrfCode" value="" />
	<input type="hidden" name="serviceFlag" value="${ipdBillManagementTransBean.serviceFlag}" />
		<cmbPers:cmbPers/>
		
		</div>
		</div>
		</div>
		</fieldset>
</html:form>
   
</body>
</html>
<%}catch(Exception e)
{
e.printStackTrace();	
}%>