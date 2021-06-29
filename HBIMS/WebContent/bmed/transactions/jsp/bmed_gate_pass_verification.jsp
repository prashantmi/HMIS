<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<title>Equipment Gate Pass Request Verification Desk</title>
<his:css src="../../../hisglobal/css/Color.css" />
<his:css src="../../../hisglobal/css/master.css" />
<his:css src="../../hisglobal/css/hisStyle.css" />
<his:css src="../../hisglobal/css/hisStyleExt.css" />
<his:css src="../../hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<link href="../../hisglobal/css/emms.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript"
	src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>

<script type="text/javascript">
function validate()
{   

                var hisValidator = new HISValidator("stEquipgatepassrequestDeskName");
                  hisValidator.addValidation("strVeificationDate","date","Please Enter Verification Date");
	            hisValidator.addValidation("strGatePassVerifier","dontselect=0","Please Select requester Name");

		  var retVal = hisValidator.validate();           
          if(retVal)
          {
                 	   document.forms[0].hmode.value = "VERIFY";
                       document.forms[0].submit();
          }
          else
          {
             return false;
          }
}
</script>
<!-- 
/**
 * BY shefali
 * Date of Creation : 16/08/2013
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : Rajasthan
 */
 -->

</head>
<body class="background">
<html:form action="/transactions/EquipInwardOutwardGatePassCNT"
	name="stEquipgatepassrequestDeskName"
	type="bmed.transactions.controller.fb.EquipInwardOutwardGatePassFB"
	styleClass="formbg">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strMsgString" /></div>
	</center>

	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Request/Complaint Detail &gt;&gt;</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Id</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strComplaintId}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Date</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strComplaintDate}
			</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Description</td>
			<td colspan="3" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strComplaintDescription}</td>
		</tr>

		<tr class="HEADER">
			<td colspan="4">Equipment Installation Details &gt;&gt;</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Hospital
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strStoreName}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Lab
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strLabName}</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Block
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strBlockName}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Building
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strBuildingName}
			</td>

		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Floor</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strFloorName}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Room</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strRoomName}
			</td>
		</tr>

		<tr class="HEADER">
			<td colspan="4">Equipment Inventory Details &gt;&gt;</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Category</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strEquipmentCategory}</td>
			<td colspan="2" class="CONTROL" style="text-align: left;"></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Group</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strEquipGroup}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			SubGroup</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strEquipSubGroup}</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strItemName}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Model Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strItemModel}
			</td>

		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Supplier</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strSupplier}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">UID No.</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strEquipmentUIDNo}
			</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Serial
			No.</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strItemSerialNo}
			</td>
			<td colspan="2" class="CONTROL" style="text-align: left;"></td>

		</tr>

		<tr class="HEADER">
			<td colspan="4">Equipment Gate Pass Details &gt;&gt;</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">RequestDate</td>
			<td class="CONTROL">${EquipInwardOutwardGatePassFB.strRequestDate}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Request
			Time</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strRequestTime}</td>

		</tr>

		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">GatePass
			for</td>
			<td colspan="2" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strRequestFor}</td>
		</tr>

		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Issue
			to</td>
			<td colspan="2" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strGatePassIssueTo}</td>
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Receivers
			Address</td>
			<td colspan="2" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strGatePassReceiverAdd}</td>
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Purpose</td>
			<td colspan="2" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strGatePassPurpose}</td>
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Place
			where item(s) to be Carried:</td>
			<td colspan="2" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strToPlaceItemCarried}</td>
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Expected
			Date of Return of Item :</td>
			<td colspan="2" class="CONTROL">${EquipInwardOutwardGatePassFB.strExpectedReturnDate}</td>

			<td class="CONTROL">
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Remarks:</td>
			<td colspan="2" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strGatePassRemark}</td>
		</tr>


		<tr class="HEADER">
			<td colspan="4">Equipment Gate Pass Verify &gt;&gt;</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color="red">*</font>Verified</td>
			<td class="CONTROL"><input type="radio"
				name="strRequestVerifyStatus" value="1" checked>Yes <input
				type="radio" name="strRequestVerifyStatus" value="0">No</td>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color="red">*</font>Date of verification</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><dateTag:date
				name="strVeificationDate" id="strVeificationDate" /></td>

		</tr>

		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Remark
			If Any</td>
			<td colspan="2" class="CONTROL" style="text-align: left;"><input
				type="text" name="strVerificationRemark" id="strVerificationRemark"></td>
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;"><font
				color="red">*</font>Verified By:</td>
			<td colspan="1" class="CONTROL">
			<div id="SupplierDiv" align="left"><select
				name="strGatePassVerifier" id="strGatePassVerifier" />
				<bean:write name="EquipInwardOutwardGatePassFB"
					property="strGatePassVerifiercmb" filter="false" />
			</select></div>
			</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="validate();" /> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancel('LIST');" /></td>
		</tr>
	</table>

	<!--<div>
	<div class="legends"><font size="2" color="red">*</font>
	Mandatory Fields</div>
	<div class="control_button">
	<table class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<div><a href="#" class="button" onClick="validate();"><span
				class="save">Save</span></a> <a href="#" class="button"
				onClick="cancel('LIST');"><span class="back">Back</span></a></div>
			</td>
		</tr>
	</table>
	</div>
	</div>
	-->
	<html:hidden property="hmode" styleId="hmode" />

	<input type="hidden" name="strComplaintId" id="strComplaintId"
		value="${EquipInwardOutwardGatePassFB.strComplaintId}">
	<input type="hidden" name="strComplaintDate" id="strComplaintDate"
		value="${EquipInwardOutwardGatePassFB.strComplaintDate}">
	<input type="hidden" name="strComplaintDescription"
		id="strComplaintDescription"
		value="${EquipInwardOutwardGatePassFB.strComplaintDescription}">
	<input type="hidden" name="strStoreID" id="strStoreName"
		value="${EquipInwardOutwardGatePassFB.strStoreID}">
	<input type="hidden" name="strLabID" id="strLabName"
		value="${EquipInwardOutwardGatePassFB.strLabID}">
	<input type="hidden" name="strBlockID" id="strBlockName"
		value="${EquipInwardOutwardGatePassFB.strBlockID}">
	<input type="hidden" name="strBuildingID" id="strBuildingName"
		value="${EquipInwardOutwardGatePassFB.strBuildingID}">
	<input type="hidden" name="strFloorID" id="strFloorName"
		value="${EquipInwardOutwardGatePassFB.strFloorID}">
	<input type="hidden" name="strRoomID" id="strRoomName"
		value="${EquipInwardOutwardGatePassFB.strRoomID}">
	<input type="hidden" name="strEquipmentCategoryNo"
		id="strEquipmentCategory"
		value="${EquipInwardOutwardGatePassFB.strEquipmentCategoryNo}">
	<input type="hidden" name="strEquipGroupID" id="strEquipGroup"
		value="${EquipInwardOutwardGatePassFB.strEquipGroupID}">
	<input type="hidden" name="strEquipSubGroupID" id="strEquipSubGroup"
		value="${EquipInwardOutwardGatePassFB.strEquipSubGroupID}">
	<input type="hidden" name="strItemID" id="strItemName"
		value="${EquipInwardOutwardGatePassFB.strItemID}">
	<input type="hidden" name="strItemModelID" id="strItemModel"
		value="${EquipInwardOutwardGatePassFB.strItemModelID}">
	<input type="hidden" name="strSupplierID" id="strSupplier"
		value="${EquipInwardOutwardGatePassFB.strSupplierID}">
	<input type="hidden" name="strEquipmentUIDNo" id="strEquipmentUIDNo"
		value="${EquipInwardOutwardGatePassFB.strEquipmentUIDNo}">
	<input type="hidden" name="strItemSerialNo" id="strItemSerialNo"
		value="${EquipInwardOutwardGatePassFB.strItemSerialNo}">
	<input type="hidden" name="strRequestId" id="strRequestId"
		value="${EquipInwardOutwardGatePassFB.strRequestId}">
	<input type="hidden" name="strRequestId" id="strRequestId"
		value="${EquipInwardOutwardGatePassFB.strRequestId}">

	<input type="hidden" name="strGatePAssId" id="strGatePAssId"
		value="${EquipInwardOutwardGatePassFB.strGatePAssId}">

</html:form>

</body>
</html>

