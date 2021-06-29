<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%--
/**
 * Developer Name : Anshuman Singh
 * Process Name : Equipment Complaint Register
 * Product Name : HEMMS Product
 * Version		: 1.0
 * Date : 07/Aug/2014
 * Modify By/Date : --
 */ 
 --%>

<html>
<head>

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


<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tabIndex.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<his:javascript	src="/bmed/transactions/js/hemms_main_itemComplaintRegister_trans.js" />
<his:javascript	src="/bmed/js/maintenance_warranty_contract.js"/>
<script type="text/javascript">
function backPage()
{
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
}

</script>
</head>

<body marginheight="0" marginwidth="0"  onload="" class="background">
<tag:autoIndex>
<html:form name="ComplaintDeskFB" action="/transactions/ComplaintDeskACTION"
	type="bmed.transactions.controller.fb.ComplaintDeskFB" styleClass="formbg">
	
	<div class="ERR_DIV" id="errMsg"><bean:write
			name="ComplaintDeskFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="ComplaintDeskFB" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write
			name="ComplaintDeskFB" property="strNormalMsg" /></div>
	
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Complaint Register</td>
		</tr>
	</table>
	

				<!------------------Equipment Details--------------->
	<div class="line">
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px"  border="0">
			<tr>
				<td>Equipment Details</td>
			</tr>
		</table>
	</div>
			
			
	<table class="TABLEWIDTH">	
							<!-------------- Department -------------------->
		<tr id="divDeptNameAndStoreDetails" align="center">
			<td width="25%" class="LABEL">
				<font color="red">*</font>Hospital Name
			</td>
			<td width="25%" class="CONTROL">
				<label id="strHospital">${ComplaintDeskFB.strHospital} </label>
			</td>				
			<td width="25%" class="LABEL">
				<font color="red">*</font>Lab Name
			</td>
			<td width="25%" class="CONTROL">
				<label id="strHospital">${ComplaintDeskFB.strLabName} </label>
			</td>
		</tr>	
		<tr id="divDeptNameAndStoreDetails" align="center">
			<td width="25%" class="LABEL">
				<font color="red">*</font>Equipment Name
			</td>
			<td width="25%" class="CONTROL">
				<label id="strEquipName">${ComplaintDeskFB.strItemName} </label>
			</td>				
			<td width="25%" class="LABEL">
				<font color="red">*</font>Equipment Model
			</td>
			<td width="25%" class="CONTROL">
				<label id="strEquipModel">${ComplaintDeskFB.strEquipModel} </label>
			</td>
		</tr>	
		<tr id="divDeptNameAndStoreDetails" align="center">
			<td width="25%" class="LABEL">
				<font color="red">*</font>Serial No.
			</td>
			<td width="25%" class="CONTROL">
				<label id="strBatchNo">${ComplaintDeskFB.strItemBatchNo} </label>
			</td>				
			<td width="25%" class="LABEL">
				<font color="red">*</font>Supplier Name
			</td>
			<td width="25%" class="CONTROL">
				<label id="strSupplierName">${ComplaintDeskFB.strManufacturerName} </label>
			</td>
		</tr>	
	</table>

	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Complaint Details</td>
		</tr>
	</table>

							<!---------------------Complaint Description----------------------------->
	<table class="TABLEWIDTH">
		<tr id="dateAndTimeId">
			<td width="25%" class="LABEL">Complaint Id </td>
			<td width="25%" class="CONTROL">
				<label>${ComplaintDeskFB.strReqId}</label>
			</td>					

			<td width="25%" class="LABEL">Complaint Date & Time</td>
			<td width="25%" class="CONTROL">
				<label>${ComplaintDeskFB.strComplaintDate}</label>
			</td>					
					
		</tr>

		<tr >
			<td width="25%" class="LABEL">Current Status </td>
			<td width="25%" class="CONTROL">
				<label>${ComplaintDeskFB.strCurrentStatus}</label>
			</td>					

			<td width="25%" class="LABEL">Raised By</td>
			<td width="25%" class="CONTROL">
				<label>${ComplaintDeskFB.strRaisedBy}</label>
			</td>					
					
		</tr>

		<tr>
			<td width="25%" class="LABEL">Complaint Description</td>
			<td width="50%" colspan="3" class="CONTROL">
				<html:textarea name="ComplaintDeskFB" property="strComplaintDescription" cols="63" rows="2" disabled="true" />
			</td>
		</tr>			
				
		<tr>
			<td width="25%" class="LABEL">Is Item in Working Condition</td>
			<td width="25%" class="CONTROL">
				<label id="strIsWorking" /> ${ComplaintDeskFB.strIsWorking}</label>
			</td>
			<td width="25%" class="LABEL">Preferred Visit Time Between</td>
			<td width="25%" class="CONTROL">
				<label id="strPreferredFromTime">${ComplaintDeskFB.strPreferredFromTime}</label>
			</td>		
		</tr>
		<tr>
			<td width="25%" class="LABEL">Contact Person Name</td>
			<td width="25%" class="CONTROL">
				<label id="strContactPersonName">${ComplaintDeskFB.strContactPersonName}</label>
			</td>
			<td width="25%" class="LABEL">Contact No.</td>
			<td width="25%" class="CONTROL">
				<label id="strContactNo">${ComplaintDeskFB.strContactNo}</label>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Address/Landmark</td>
			<td width="25%" class="CONTROL">
				<html:textarea name="ComplaintDeskFB" property="strLandMarkDescription" disabled="true" cols="25" rows="2"  />
			</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">
				<html:textarea name="ComplaintDeskFB" property="strRemarks" cols="25" rows="2" disabled="true" />
			</td>
		</tr>
	</table>
	
		
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<tr class="FOOTER">
				<td></td>
			</tr>
		</table>
		<!--<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
					<div>
						<a href="#" class="button" onClick="backPage();">
							<span class="back">Back</span>
						</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
	  -->
	
		<table border="0" class="TABLEWIDTH" align="center">
		
		<tr>
			<td align="center"> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="backPage();" /></td>
		</tr>
	</table>

		<input type="hidden" name="hmode" />
		<input type="hidden" name="strPath"  value="${ComplaintDeskFB.strPath}"/>
		
	<cmbPers:cmbPers />
</html:form>
</tag:autoIndex>
</body>
</html>