<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<title>Equipment Condemnation Approval</title>
<script type="text/javascript" src="../../bmed/js/jquery.js"></script>
<link href="../../hisglobal/css/emms.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../bmed/css/select2.css" rel="stylesheet" type="text/css">
<his:css src="../../../hisglobal/css/Color.css" />
<his:css src="../../hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript"
	src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>

<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../bmed/js/select2.js"></script>
<script language="Javascript"
	src="../../bmed/transactions/js/bmed_equip_condemnation_auction_desk.js"></script>

<!-- 
/**
 * BY Sarat
 * Date of Creation : 22/08/2014
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : OSMC
 */
 -->

<script type="text/javascript">
	function getDetailsOnBasisOfCommiteeId() {
		var mode;
		var url;
		if (document.forms[0].strCommitteeId.value != 0) {
			mode = "getSelectedCommitteeDetail";
			url = "EquipmentAuctionAndCondemnationDeskCNT.cnt?hmode=" + mode
					+ "&committeeId=" + document.forms[0].strCommitteeId.value;
			ajaxFunction(url, "1");
		}
		else {
			document.getElementById("strCommitteeDetail").innerHTML = "";
		}
	}
	function getAjaxResponse(res, mode) {
		var objVal;
		if (mode == "1") {
			objVal = document.getElementById("strCommitteeDetail");
			objVal.innerHTML = "Order No and Date:  " + res.split('^')[0] + " - "
					+ res.split('^')[1] + "<div>Formation Date: " + res.split('^')[2] +"</div>";
		}
	}
	$(document).ready(function() {
		$("#strCertificateUser").select2();
	});
	
	function validate(){   
	     
        var hisValidator = new HISValidator("stEquipCondemnAndAuctionDeskName");  
        hisValidator.addValidation("strCertificateNo", "req", "Please provide Certificate Number" );
        hisValidator.addValidation("strCertificateDate", "date","Certificate Date is a mandatory field.");
        hisValidator.addValidation("strCertificateUser", "dontselect=0", "Please Select a Certificate Prepared By Employee Name.");
    
        var retVal = hisValidator.validate(); 
             
      if(retVal){
                   document.forms[0].hmode.value = "saveIssueCertificate";
                   document.forms[0].submit();
      }else{
         return false;
      }
}
</script>


</head>
<body onload="chkRecordSavedAndTakePrint()" class="background">
	<html:form
		action="/transactions/EquipmentAuctionAndCondemnationDeskCNT"
		styleClass="formbg" name="stEquipCondemnAndAuctionDeskName"
		type="bmed.transactions.controller.fb.EquipmentAuctionAndCondemnationDeskFB">

		<center>
			<div id="errMsg" class="errMsg">
				<bean:write name="EquipmentAuctionAndCondemnationDeskFB"
					property="strErrMsg" />
			</div>
			<div id="warningMsg" class="warningMsg">
				<bean:write name="EquipmentAuctionAndCondemnationDeskFB"
					property="strWarning" />
			</div>
			<div id="normalMsg" class="normalMsg" style="display: none;">
				<bean:write name="EquipmentAuctionAndCondemnationDeskFB"
					property="strMsgString" />
			</div>
		</center>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr class="HEADER">
				<td colspan="4">Equipment Condemnation Approval Process</td>
			</tr>
		</table>

		<div id="prevDetails">
		<div class="line">
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td colspan="4">Equipment Detail</td>
				</tr>
			</table>
		</div>
		<!-- <table class="TABLEWIDTH" align="center" width="100%">
			<tr>
				<td colspan="7">
					<div style="width: 90%; border-color: aqua; table-layout: auto;">
						<span style="text-align: center;">Equipment Name</span> <span
							style="text-align: center;">Equipment Model</span> <span
							style="text-align: center;">Group / Sub-Group</span> <span
							style="text-align: center;">Serial No.</span> <span
							style="text-align: center;">UIN</span> <span
							style="text-align: center;">Purchase Date</span> <span
							style="text-align: center;">Status</span>
					</div>
				</td>
			</tr>
		</table> -->
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td colspan="1" class="LABEL">Hospital Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strHospital}
				</td>
				<td colspan="1" class="LABEL">Lab Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strLabName}
				</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL">Equipment Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipName}
				</td>
				<td colspan="" class="LABEL">Equipment Model</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL">Group Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipGroup}
				</td>
				<td colspan="1" class="LABEL">Sub Group Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipSubGroup}</td>
			</tr>
		</table>
		<div class="line">
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td colspan="4">Inventory Detail</td>
				</tr>
			</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<tr>
				<td colspan="1" class="LABEL">Serial No</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strBatchNo}
				</td>
				<td colspan="1" class="LABEL">UIN</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strUidNo}</td>
			</tr>

			<tr>
				<td colspan="1" class="LABEL">Manufacturer Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strSupplierName}
				</td>

				<td colspan="1" class="LABEL">Status</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strStatus}
				</td>
			</tr>
		</table>
		<div class="line">
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td colspan="4">Equipment Condemnation Request Detail</td>
				</tr>
			</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td colspan="1" class="LABEL">Request No. / Date</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strIndentificationNo}&nbsp;
					/&nbsp;${EquipmentAuctionAndCondemnationDeskFB.strIndentifyEquipDate}</td>
				<td colspan="1" class="LABEL">Identified By</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strIndentifiedByID}</td>
			</tr>
			<tr>
				<td colspan="2" class="LABEL">Remarks/Reason for Condemnation</td>
				<td colspan="2" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strIdentificationRemarks}</td>
			</tr>
		</table>
		<div class="line">
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td colspan="4">Approval &amp; Order Generation Detail</td>
				</tr>
			</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td colspan="1" class="LABEL">Order No./ Date</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strOrderNo}&nbsp;/&nbsp;
					${EquipmentAuctionAndCondemnationDeskFB.strOrderDate}</td>
				<td colspan="1" class="LABEL">Remarks</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strOrderGenerationRemarks}</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL">Order Generated By</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strOrderGeneratedByID}
				</td>
				<td colspan="1" class="LABEL"></td>
				<td colspan="1" class="CONTROL"></td>
			</tr>
		</table>
		<div class="line">
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td colspan="4">Committee Schedule Detail</td>
				</tr>
			</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td colspan="1" class="LABEL">Committee Name</td>
				<td colspan="1" class="CONTROL"><a
					onclick="getDetailsOnBasisOfCommiteeId();" href="#">${EquipmentAuctionAndCondemnationDeskFB.strCommitteeName}</a>
					<input type="hidden" name="strCommitteeId" id="strCommitteeId"
					value="${EquipmentAuctionAndCondemnationDeskFB.strCommitteeId}">
				</td>
				<td width="25%" class="CONTROL">
					<div id="strCommitteeDetail"></div>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="LABEL">Schedule Date</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strReviewScheduleDate}
				</td>
				<td colspan="1" class="LABEL">Time</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strReviewScheduleTime}
				</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL">Venue</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strReviewScheduleVenue}
				</td>
				<td colspan="1" class="LABEL">Remarks</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strReviewScheduleRemarks}
				</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL">Schedule Prepared By</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strReviewSchedulePreparedById}
				</td>
			</tr>
		</table>
		<div class="line">
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td colspan="4">Committee Recommendation Detail</td>
				</tr>
			</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td colspan="1" class="LABEL">Recommend for Condemnation</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strRecommendationStatus}
				</td>

				<td colspan="1" class="LABEL">Date of Recommendation</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strRecommendationDate}</td>
			</tr>

			<tr>
				<td colspan="1" class="LABEL">Recommendation</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strRecommendation}</td>
				<td colspan="1" class="LABEL">Remarks</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strRecommendationRemarks}
				</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Recommendation
					Prepared By</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strRecommendedById}</td>
			</tr>
		</table>
		<div class="line">
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td colspan="4">Approval by Competent Authority Detail</td>
				</tr>
			</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td colspan="1" class="LABEL">Approved</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strApprovalStatus}</td>

				<td colspan="1" class="LABEL"></td>
				<td colspan="1" class="CONTROL"></td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Approved
					By</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strApprovalUser}</td>
				<td colspan="1" class="LABEL">Approval Date</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strApprovalDate}</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL">Approval Order No.</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strApprovalOrderNo}</td>

				<td colspan="1" class="LABEL">Order Date</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strApprovalOrderDate}</td>
			</tr>

			<tr>
				<td colspan="2" class="LABEL">Remarks</td>
				<td colspan="2" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strApprovalRemarks}</td>
			</tr>
		</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr class="HEADER">
				<td colspan="4">Certificate Issue Detail&gt;&gt;</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL">Certificate No.</td>
				<td colspan="1" class="CONTROL"><input type="text"
					name="strCertificateNo"></td>
				<td colspan="1" class="LABEL">Certificate Date</td>
				<td colspan="1" class="CONTROL"><dateTag:date
						name="strCertificateDate"></dateTag:date></td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Certificate Generated
					By</td>
				<td colspan="1" class="CONTROL"><select name="strCertificateUser"
					id="strCertificateUser">
						<bean:write name="EquipmentAuctionAndCondemnationDeskFB"
							property="strApprovalUserCmb" filter="false" />
				</select></td>
				<td colspan="1" class="LABEL">Remarks</td>
				<td colspan="1" class="CONTROL"><textarea
						name="strCertificateRemarks" id="strCertificateRemarks" rows="2"
						cols="30" onKeyPress='return maxLength(this,"100");'></textarea></td>
			</tr>
			<tr style="display: none;">
				<td colspan="1" class="LABEL">Auction Date</td>
				<td colspan="1" class="CONTROL"><dateTag:date
						name="strAuctionDate"></dateTag:date></td>
				<td colspan="1" class="LABEL">Auction Amount</td>
				<td colspan="1" class="CONTROL"><input type="text"
					name="strAuctionAmount"></td>

			</tr>
			
		</table>
		<table class="TABLEWIDTH" align="center">

			<tr class="FOOTER">
				<td></td>
			</tr>
		</table>
		<div>
			<div class="legends">
				<font size="2" color="red">*</font> Mandatory Fields
			</div>
			<div class="control_button">
				<table class="TABLEWIDTH" align="center">
					<tr>
						<td align="center">
							<div>
								<a href="#" class="button" onClick="validate();"><span
									class="save">Save</span></a> <a href="#" class="button"
									onClick="cancel('LIST');"><span class="back">Back</span></a>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<html:hidden property="hmode" styleId="hmode" />
		<input type="hidden" name="strIndentificationNo"
			id="strIndentificationNo"
			value="${EquipmentAuctionAndCondemnationDeskFB.strIndentificationNo}">
		<input type="hidden" name="strEquipmentSerialNo"
			id="strEquipmentSerialNo"
			value="${EquipmentAuctionAndCondemnationDeskFB.strEquipmentSerialNo}">
		<input type="hidden" name="strBatchNo" id="strBatchNo"
			value="${EquipmentAuctionAndCondemnationDeskFB.strBatchNo}">
		<input type="hidden" name="strOrderId" id="strOrderId"
			value="${EquipmentAuctionAndCondemnationDeskFB.strOrderId}">
		<input type="hidden" name="strMsgString" id="strMsgString"
			value="${EquipmentAuctionAndCondemnationDeskFB.strMsgString}">
		<cmbPers:cmbPers />
	</html:form>

</body>
</html>