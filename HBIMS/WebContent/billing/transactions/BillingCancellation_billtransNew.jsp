<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<title>Online Bill Cancellation</title>

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
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/BillCancellation_TransBS.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script type="text/javascript">
var beforePrint = function() 
{  
	showPrintableSlip();
};
var afterPrint = function() 
{               
	hidePrintableSlip();
};
/*if (window.matchMedia) 
{
	var mediaQueryList = window.matchMedia('print');
	mediaQueryList.addListener
	(
		function(mql) 
		{
		    if (mql.matches) 
		    {
		    	beforePrint();
		    } 
		    else 
		    {
		    	afterPrint();
		    }
		}
	);
 }*/

window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;
</script>

<style type="text/css">

@media print 
{ 
		#nonPrintable 
		{
		 display: none; 
		}		
}
.no-close .ui-dialog-titlebar-close {display: none }
</style>
</head>
<body onload="showPatientDetails('document.forms[0].strCrNo');openPrintPopUp();" onfocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">

<html:form action="transactions/BillingCancellationTransBSCNT.cnt" method="post"
	onsubmit="GO();">
	
	
		<fieldset form="form1">

			<legend class='legendHeader' id='nonPrintableLegend'>Online Bill Cancellation</legend>
			<div class="legend2" id='nonPrintableLegend2'>
				<!-- <button  type="button" id="cancelButton" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();"> -->
				<button type="button"
					class="btn btn-outline-danger mt-1 btn-circle cancelbtn"
					onclick="cancelFunc();">
					<i class="fas fa-ban iround" title="Cancel"></i>
				</button>
				<button type="button" id="lastbuttons"
					class="btn btn-outline-success mt-1 btn-circle savebtn "
					tabindex='2' onclick='return validate1();'>
					<i class="fas fa-save iround" title="Save"></i>
				</button>

			</div>

	<div id='normalMsg' class="normalMsg"><bean:write
		name="BillingCancellationTransBean" property="strMsg" filter="false" /></div>
	<div id='errMsg' class="errMsg"><bean:write
		name="BillingCancellationTransBean" property="strErrMsg"
		filter="false" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="BillingCancellationTransBean" property="strWarningMsg" filter="false" /></div>
		
		<div id="demographicInfo" style="display:none" align="center"><bean:write
		name="BillingCancellationTransBean" property="strPatientDetailsView"
		filter="false" /></div>
		
		
			<div class="viewport" id="nonPrintable">
				<div class="container-fluid ">
					<div class="prescriptionTile">
							<div class="row rowFlex reFlex newrow">
							<div class="col-sm-2">
								<label class="container" style="color: white;">Criteria
									1 <html:radio property="strCriteria"
										name="BillingCancellationTransBean" value="1"
										onclick="chkCriteria();">

									</html:radio><span class="checkmark"></span>
								</label>
							</div>
							<div class="col-sm-2" id="c2heading" style="display: block">
								<label class="container" style="color: white;">Criteria
									2 <html:radio property="strCriteria"
										name="BillingCancellationTransBean" value="2"
										onclick="chkCriteria('1');">
									</html:radio><span class="checkmark"></span>
								</label>
							</div>
							<div class="col-sm-8" align="right">
								<input type="checkbox" align="right" onclick="chkcredit();"
									name="crbill" id="crbill"
									value="${BillingCancellationTransBean.crbill}"><label style="color: white;">Credit
								Bill</label>
							</div>
						</div>
						<br>
						<div id="criteria1" style="display: block;">
						<div class="row rowFlex reFlex" id='goid'>
							<div class="col-sm-2" align="right">
								<font color="red">*</font>CR No.
							</div>
							<div class="col-sm-2">
								<crNo:crNo name="strCrNo" id="strCrNoId"
									value="${BillingCancellationTransBean.strCrNo}"
									js="onkeypress='return initGoFunc(event);'" className="form-control"></crNo:crNo>
							</div>
							<div class="col-sm-2">
							<span class="fas fa-search"
										style="cursor: pointer; cursor: hand;"
										id="searhPatientImageId" title="Click here for Patient Search"
										name='searchPatient' data-toggle="modal"
										data-target="#searchModel" onclick="fetchPatientList_BS1(1,1,6);" ></span>
								<a href="#" class="btn btn-sm btn-success"
									onclick="return GO();" onkeypress="return initGoFunc(event);" style="font-size: 1rem;">
									GO&nbsp;<i class="fas fa-angle-double-right"></i>
								</a>
							</div>
							<div class="col-sm-6"></div>
						</div>
						</div>
	
	<div id="criteria1" style="display: block;">
	<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px" style="display: none;">
		<tr>
			<td class="LABEL">
			<div align="right"><html:radio property="strCase"
				name="BillingCancellationTransBean" value="1"
				onclick="chkCase(this);">With CR No.</html:radio> <html:radio
				property="strCase" name="BillingCancellationTransBean" value="2"
				onclick="chkCase(this);">Without CR No.</html:radio></div>
			</td>
		</tr>
	</table>
	</div>
						<div id="criteria2" style="display: block">
							<div id="c2body" style="display: none">
								<div class="row rowFlex reFlex" id='goid'>
									<div class="col-sm-2" align="right">
										<font color="red">*</font>Receipt No.
									</div>
									<div class="col-sm-2" style="padding-left: 0;padding-right: 0;">
										<input type="text" class="form-control" name="strTransNo"
											value="${BillingCancellationTransBean.strTransNo}"
											maxlength="15" onkeypress="return validateData(event,5);"
											onkeyup="focusNext(this);">
									</div>
									<div class="col-sm-1" style="display: inline-flex;font-size: 26px;">
										<label>/</label>&nbsp;&nbsp;<input type="text" class="form-control" name="strRcptNo"
											value="${BillingCancellationTransBean.strRcptNo}"
											maxlength="2" onkeypress="return validateData(event,5);">
									</div>
									<div class="col-sm-7"></div>
								</div>
							</div>
						</div>
	<div id="details" style="display: none">
	<!-- <table class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td width="25" class="TITLE">
			<div id="plus" style="display: none"><img
				src="../../hisglobal/images/plus.gif" onclick="showinfo();"></div>
			<div id="minus" style="display: block"><img
				src="../../hisglobal/images/minus.gif" onclick="hideinfo();"></div>
			</td>
			<td colspan="4">Patient Demographic Information</td>

		</tr>
	</table> -->
	</div>

	
	<div id="billdtls" style="display:none"><bean:write
		name="BillingCancellationTransBean" property="strBillDtl"
		filter="false"/></div>
	<div id="trfDtls"> </div>
<br>
						<div class="row rowFlex reFlex" id='goid'>
							<div class="col-sm-2" align="right">
								<font color="red">*</font>Cancelled By
							</div>
							<div class="col-sm-2">
								<select name="strCancelledBy" class="browser-default custom-select">
									<bean:write name="BillingCancellationTransBean"
										property="strCancelledBy" filter="false" />
								</select>
							</div>
							<div class="col-sm-2" align="right">
								<font color="red">*</font>Cancellation Reason
							</div>
							<div class="col-sm-2">
								<select name="strCancelReason"
									class="browser-default custom-select" onchange="showText();">
									<bean:write name="BillingCancellationTransBean"
										property="strCancelReason" filter="false" />
								</select>
							</div>
                          <div class="col-sm-2">
                            <input type="text" class="form-control" name="strOtherReason" value="" maxlength="50" onkeypress="">
                          </div>
						</div>
						
				
	
	
<div id="dialog-message" title="Save" style="display:none;">
  <p>
    <b>Are You Sure to Save it?</b>.
  </p>
</div>
<br>
	<div id="lastbuttons" style="display: block">
	<!-- <table border="0" class='TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td align="center"><img style="cursor: pointer; cursor: hand"
				src="../../hisglobal/images/btn-sv.png" title="click here to save the selected data"
				onClick="return validate1();"> <img
				style="cursor: pointer; cursor: hand" title="click here to clear the contents"  
				src="../../hisglobal/images/btn-clr.png"
				onclick="showFirstPage();"> <img
				style="cursor: pointer; cursor: hand" title="click here to cancel the process"
				src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();">
				
				
				<br>
				<a href="#" class="button" id="" onclick='return validate1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="showFirstPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table> -->
	</div>
	                         <hr>
                            <div class="row rowFlex reFlex">
								<div class="col-sm-10"></div>
								<div class="col-sm-2" align="right">
									<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>Fields Mandatory
								</div>
							</div>
	<div id="onlyClearbutton" style="display: none">
	<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td align="center">
			<!-- <img style="cursor: pointer; cursor: hand" title="click here to clear the contents"  
				src="../../hisglobal/images/btn-clr.png"
				onclick="showFirstPage();"> <img
				style="cursor: pointer; cursor: hand" title="click here to cancel the process"
				src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();">
				 -->
				 <br>
				<a href="#" class="button"	onclick="showFirstPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	</div>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="CrNo"
		value="${BillingCancellationTransBean.strCrNo }" />
	<input type="hidden" name="chkValue" />
	<input type="hidden" name="strCRNoSatus"
		value="${BillingCancellationTransBean.strCRNoSatus }" />
	<input type="hidden" name="gblCRValue"/>
	<input type="hidden" name="billcancelflag"
		value="${BillingCancellationTransBean.billcancelflag }" />
	<input type="hidden" name="isOpenPopUp" value="${BillingCancellationTransBean.isOpenPopUp}"/>
	<input type="hidden" name="filePath" value="${BillingCancellationTransBean.filePath}"/>
	<input type="hidden" name="printMode" value="${BillingCancellationTransBean.printMode}"/>
	</div>
	</div>
	</div>
	</fieldset>
</html:form>

<div class="popup" id="popup" style="display: none"></div>
<tag:autoIndex></tag:autoIndex> 
<div id='printableSlip'>
<logic:equal name="BillingCancellationTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="BillingCancellationTransBean"  property="printMode" value="1">
<logic:present name="BillingCancellationTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>

<div class="modal fade " id="searchModel" tabindex="-1" role="dialog"
		aria-labelledby="validateModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Paitent Listing</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body" id="fetchRecordDivId"></div>

				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	
</div>	
</body>
</html>