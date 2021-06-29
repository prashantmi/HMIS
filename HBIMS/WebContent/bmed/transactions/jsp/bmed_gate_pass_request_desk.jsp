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
<title>Equipment Gate Pass Request Desk</title>
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
            var hisValidator = new HISValidator("stEquipgatepassrequestDeskName");
                  hisValidator.addValidation("strRequestDate","date","Please Enter Request Date");
              hisValidator.addValidation("strRequestTime","req","Please Enter Request Time");
	         
	          hisValidator.addValidation("strGatePassRequestFor","req","Please Enter Request for");
	          hisValidator.addValidation("strGatePassIssueTo","req","Please Enter Issue to Name");
	          hisValidator.addValidation("strGatePassReceiverAdd","req","Please Enter Receiver Address ");
	          hisValidator.addValidation("strGatePassPurpose","req","Please Enter Purpose");
	          hisValidator.addValidation("strToPlaceItemCarried","req","Please Enter place where Item is being carried");

              hisValidator.addValidation("strGatePassRequester","dontselect=0","Please Select requester Name");

		  var retVal = hisValidator.validate(); 
          if(retVal)
          {
                 	   document.forms[0].hmode.value = "SAVE";
                       document.forms[0].submit();
          }
          else
          {
             return false;
          }
}
function chkRecordSaved()
		{
			if(document.forms[0].strMsgString.length>1)
			{
				alert(document.forms[0].strMsgString.value);
				document.forms[0].hmode.value = "LIST";
				document.forms[0].submit();
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
<body onload="chkRecordSaved()" class="background">
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
			<td colspan="4">Equipment Gate Pass Details &gt;&gt;</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>RequestDate</td>
			<td class="CONTROL"><dateTag:date name="strRequestDate"
				id="strRequestDate"
				value="${EquipInwardOutwardGatePassFB.strRequestDate}" /></td>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>Request Time</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" name="strRequestTime" readonly id="strRequestTime"
				value="${EquipInwardOutwardGatePassFB.strRequestTime}" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>GatePass for</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="radio" value="Equipment_returnable" name="strRequestFor"
				id="strRequestFor" checked />Returnable <input type="radio"
				value="Equipment_Nonreturnable" name="strRequestFor"
				id="strRequestFor" />Non Returnable</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>Request for</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" size="50" name="strGatePassRequestFor"
				id="strGatePassRequestFor" maxlength="100"
				onkeypress="return validateData(event,9);" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>Issue to</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" size="50" name="strGatePassIssueTo"
				id="strGatePassIssueTo" maxlength="100"
				onkeypress="return validateData(event,9);" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>Receivers Address</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" size="50" name="strGatePassReceiverAdd"
				id="strGatePassReceiverAdd" maxlength="100"
				onkeypress="return validateData(event,3);" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>Purpose</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" size="50" name="strGatePassPurpose"
				id="strGatePassPurpose" maxlength="100"
				onkeypress="return validateData(event,10);" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>Place where item(s) to be Carried:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				size="50" type="text" name="strToPlaceItemCarried"
				id="strToPlaceItemCarried" maxlength="100"
				onkeypress="return validateData(event,9);" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Expected
			Date of Return of Item :</td>
			<td colspan="2" class="CONTROL"><dateTag:date
				name="strExpectedReturnDate" value="" /></td>

			<td class="CONTROL">
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Remarks:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" size="50" name="strGatePassRemark"
				id="strGatePassRemark" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color=red>*</font>Requested By:</td>
			<td colspan="1" class="CONTROL">
			<div id="EmpDiv" align="left"><select
				name="strGatePassRequester" id="strGatePassRequester">
				<bean:write name="EquipInwardOutwardGatePassFB"
					property="strGatePassRequesterCmb" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4"></td>
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
	<input type="hidden" name="strMsgString" id="strMsgString"
		value="${EquipInwardOutwardGatePassFB.strMsgString}" />


</html:form>

</body>
</html>

