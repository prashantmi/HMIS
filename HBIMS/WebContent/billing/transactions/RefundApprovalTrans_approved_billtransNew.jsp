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
<head>
<meta charset="utf-8" /> 
<title>Refund Approval</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
 
 <link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">

<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/RefundApproval_TransBS.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>

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
<body onLoad="document.forms[0].strCrNo.focus(),showPatientDetails();openPrintPopUp();checkMsg();" onfocus="checkPopUpAndSetDefaultCrNo();">
<html:form action="transactions/RefundApprovalTransBSCNT.cnt"
	 method="post">
	 
	 <fieldset form="form1">
	
  <legend class='legendHeader' id='nonPrintableLegend'> Refund Request</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fas fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fas fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button" id="lastbuttons" class="btn btn-outline-success mt-1 btn-circle savebtn " tabindex='2' onclick='validate1();' name="patientAdmissionModiTransBean" style="display: none;">					
		<i class="fas fa-save iround"  title="Save" ></i>
	</button>
									                 
  </div>
  <div  id="details" style="display: none">
				
 				<div id='patdtltld'>
 				 <bean:write name="refundApprovalTransBean" property="strPatientDtls" filter="false"/>
 				 </div>
 			 
 </div>

<div  class="viewport" id="nonPrintable">
		<div class="container-fluid">
  
   <div id='errMsg' class="errMsg"><bean:write name="refundApprovalTransBean" property="strErrMsg" filter="false"/></div>
   <div id='normalMsg' class="alert alert-success" id="normalMsg" style="display:none;"><bean:write name="refundApprovalTransBean" property="strMsg" filter="false"/></div>
   <div id="warningMsg" class="warningMsg"><bean:write name="refundApprovalTransBean" property="strWarningMsg" filter="false" /></div>
	
	<div class="prescriptionTile" >
		<div id="nonPrintable">     
    		<div id="menu1" class=popup style="display:none"></div>


<logic:notEqual name="refundApprovalTransBean" property="strRefundMode"  value="1">

 
	<tag:tab tabLabel="Refund Approval" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
 </logic:notEqual>
								<div class="row rowFlex reFlex" id="goid">
									<div class="col-sm-2" align="right">
										<label><font color="red">*</font>CR No.</label>
									</div>
									<div class="col-sm-2">
										<crNo:crNo id="strCrNoId" className="form-control"
											value="${refundApprovalTransBean.strCrNo}"
											js="onkeypress='return initGoFunc(event);'"></crNo:crNo>

									</div>
									<div class="col-sm-2" style="padding-left: 0">
										<span class="fas fa-search"
											style="cursor: pointer; cursor: hand;"
											id="searhPatientImageId"
											title="Click here for Patient Search" name='searchPatient'
											data-toggle="modal" data-target="#searchModel"
											onclick="checkForError();"></span> <a href="#"
											class="btn btn-sm btn-success" onclick="return goFunc();"
											style="font-size: 1rem;"> GO&nbsp;<i
											class="fas fa-angle-double-right"></i>
										</a>
									</div>
									<div class="col-sm-6"></div>

								</div>

								
	
	
	<div id="billdtls" style="display:none;overflow: auto;height: 14rem;">
	<bean:write name="refundApprovalTransBean" property="strBill" filter="false"/>	
</div>
</div>
</div>
<div class="prescriptionTile" >
<div id="trfDtls"> </div>
	<div id="combo" style="display: none">
	<logic:notEqual name="refundApprovalTransBean" property="strRefundMode"  value="1">
	<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
		<tr>
			<td width='24%' class='LABEL'>
			<div align="right"><font color="red">*</font>Refund By:</div>
			</td>
			<td width="76%" class="CONTROL"><select name="strRefBy">
				<bean:write name="refundApprovalTransBean"
					property="strRmk" filter="false" />
			</select></td>
		</tr>
		</table> 
		
	</logic:notEqual>	
		<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
		<tr>
			<%-- <td width="10%" class="LABEL">
			<div align="right"><font color="red">*</font>Refund Reason:</div>
			</td>
			<td width="40%" class="CONTROL"><select name="refRsn"
				onChange="showText();">
				<bean:write name="refundApprovalTransBean"
					property="strRsn" filter="false" />
				<option value="0">others</option>
			</select>others specify:<input name="strOtherReason" type="text"
				class="txtFldMax" value="" readonly></td> --%>
				
				
				<td width="25%" class="LABEL" id="td1" style="display: none;">
			<div align="right">
			Processing Fee Penalty:</div>
			</td>
			<td width="25%" class="CONTROL" id="td2" style="display: none;">
			<input name="strProcessFeePenalty" type="text"
			onkeypress="return initGoFunc(event);" tabindex="1" onkeyup="lTrim(this)" onblur="Trim(this)" value="0"	class="txtFldMin" value="" >
			</div>
			</td>
		</tr>
	</table> 
	<hr>
	<div class="row rowFlex reFlex">
		<div class="col-sm-2" align="left"><label><font color="red">*</font>Refund Reason:</label></div>
		<div class="col-sm-2" align="left">
			<select name="refRsn" class="browser-default custom-select" onChange="showText();">
				<bean:write name="refundApprovalTransBean" property="strRsn" filter="false" />
				<option value="0">Others</option>
			</select>
		</div>
		<div class="col-sm-2" align="left"><label>Others Specify:</label></div>
		<div class="col-sm-2" align="left"><input name="strOtherReason" type="text" class="form-control" value="" readonly></div>
		
		<div class='col-sm-3' align='right'><font color='red'>Net Refund Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)</font></div>
		<div class='col-sm-1' align='left'><input type='hidden' name='trf_grossRefund' id='trf_grossRefund' value='0.00'  readonly><font color='red'><div id='netRefCost'>0.00</div></font></div>
	</div>
	
	
	
	<div id="dialog-message" title="Save" style="display:none;">
  <p>
    <b>Are You Sure to Save it?</b>.
  </p>
</div>


								<hr>
								<div class="row rowFlex reFlex">
									<div class="col-sm-10"></div>
									<div class="col-sm-2" align="right">
										<i class="fas fa-asterisk"
											style="color: red; font-size: smaller;"></i>Fields Mandatory
									</div>
								</div>

								<input type="hidden" name="hmode"/>
	<input type="hidden" name="CrNo" value="${refundApprovalTransBean.strCrNo }"/>
	<input type="hidden" name="chkValue"/>
	
	<input type="hidden" name="strIpdRefundPenaltyAmt" value="${refundApprovalTransBean.strIpdRefundPenaltyAmt }"/>
	<input type="hidden" name="strOpdRefundPenaltyAmt" value="${refundApprovalTransBean.strOpdRefundPenaltyAmt }"/>
	<input type="hidden" name="strEmergencyRefundPenaltyAmt" value="${refundApprovalTransBean.strEmergencyRefundPenaltyAmt }"/>
	<input type="hidden" name="strRefundMode" value="${refundApprovalTransBean.strRefundMode }"/>
	<input type="hidden" name="isOpenPopUp" value="${refundApprovalTransBean.isOpenPopUp}"/>
	<input type="hidden" name="filePath" value="${refundApprovalTransBean.filePath}"/>
	<input type="hidden" name="printMode" value="${refundApprovalTransBean.printMode}"/>
	<input type="hidden" name="strBillNo" value="${refundApprovalTransBean.strBillNo}"/>
	<input type="hidden" name="strBillDate" value="${refundApprovalTransBean.strBillDate}"/>
	<input type="hidden" name="trf_grossRefund" value="${refundApprovalTransBean.trf_grossRefund}"/>
	
	<input type="hidden" value="0" name="popId">
    <input type="hidden" name="gblCRValue"/>

</div>
</div>
</div>
</div>

	</fieldset>
	</html:form>
	
<div id='printableSlip'>
<logic:equal name="refundApprovalTransBean"  property="isOpenPopUp" value="1">
<logic:equal name="refundApprovalTransBean"  property="printMode" value="1">
<logic:present name="refundApprovalTransBean"  property="filePath">
<jsp:include page="billing_receipt_printing_popup.jsp"></jsp:include>
</logic:present>
</logic:equal>
</logic:equal>
</div>


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
		
</body>
</html>