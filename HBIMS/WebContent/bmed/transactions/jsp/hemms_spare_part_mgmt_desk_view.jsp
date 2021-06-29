<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<%--
/**
 * Developer Name : T. Saratkumar
 * Process Name : Centralized Spare Parts Management Desk Add
 * Date : 30/Aug/2013
 * Modify By/Date : 
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

<his:javascript  src="/hisglobal/masterutil/js/master.js" />
<his:javascript  src="/hisglobal/js/util.js" />
<his:javascript  src="/hisglobal/js/tab.js" />
<his:javascript  src="/hisglobal/js/calendar.js" />
<his:javascript  src="/hisglobal/js/validation.js" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript  src="/hisglobal/js/commonFunctions.js" />


<his:javascript	src="/bmed/transactions/js/hemms_spare_part_mgmt_desk_trans.js" />

</head>

<body marginheight="0" marginwidth="0" onload="onLoadFunc()" class="background">

<html:form name="hemmsSparePartMgmtDeskFB" action="/transactions/HemmsSparePartMgmtDeskACTION" styleClass="formbg"
	type="bmed.transactions.controller.fb.HemmsSparePartMgmtDeskFB">
	
	
		<table  class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
		<td>Centralized Spare Part View</td>
		</tr>
		</table>
		<div class="line">
		<table  class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr>
		<td colspan="4">Spare-Part Equipment Details</td>
		</tr>
		</table>
		</div>
			<table  class="TABLEWIDTH" align="left" border="0" cellpadding="1px" cellspacing="1px">
	
<%-- Department Name & Store Name --%>
		
					<tr>
						<td width="25%" class="LABEL">Hospital Name</td>
						
						<td width="25%" class="CONTROL">${hemmsSparePartMgmtDeskFB.strDeptCode}</td>				
					
						<td width="25%" class="LABEL">Group Name</td>
						<td id="groupNameId" width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strGroupName}</td>
					</tr>	
<%-- Equipment Category --%>
					<tr>
						<td width="25%" class="LABEL">Sub Group Name</td>
						<td id="subGroupNameId" width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strSubGroupName}</td>
					
						<td width="25%" class="LABEL">Equipment Name</td>
						<td id="itemCategoryNameId" width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strEquipName}</td>
					</tr>
<%-- Engineering Item Type & Engineering Item Sub-Type --%>
					<tr>
						<td width="25%" class="LABEL">Spare-Part Name</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strSparePartName}</td>
						
						<td width="25%" class="LABEL">${ hemmsSparePartMgmtDeskFB.strSrlNoLabel}</td>
						<td id="enggItemSubTypeCmbDivId" width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strSparePartSerialNo}</td>
					</tr>
					
					<tr id="qtyRow">
						<td width="25%" class="LABEL">Issued Quantity</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strIsuedQty}</td>

						<td width="25%" class="LABEL">In Hand Quantity</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strInHandQty}</td>
					</tr>
					
<%-- Equipment Name &  Equipment Brand Name --%>
					
					<tr>
						<td width="25%" class="LABEL">Manufacturer Name</td>
						<td id="itemNameId"  width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strManufacturerName}</td>
					
						<td width="25%" class="LABEL">Supplier Name</td>
						<td id="itemModelId"  width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strSuppliedName}</td>
					</tr>
<%-- Equipment Supplier Name &  Equipment Brand Name --%>
					
					<tr>
						<td width="25%" class="LABEL">Warranty From</td>
						<td id="equipmentSupplierId"  width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strWarrantyFromDate}</td>
					
						<td width="25%" class="LABEL">Warranty UpTo</td>
						<td id="equipmentSlNoId"  width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strWarrantyUpto}</td>
					</tr>
					
					<tr>
						<td width="25%" class="LABEL">Specification</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strSpecification}</td>
						
						<td id="statusTD" width="25%" class="LABEL">Status</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strStatus}</td>
						
					</tr>
					
				</table>
			
		
<%-- Spare Part Stock Details --%>
	    <div class="line">
	    <table  class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr><td>Rate &amp; P.O. Details</td></tr>
		</table>
		</div>

			
			<table  class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">

<%-- Spare-Part Name & Spare-Part Serial No --%>
					
					<tr>
						<td width="25%" class="LABEL">Price(In Rs.)</td>
						<td id="sparePartId" width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strRate}</td>
					
						<td width="25%" class="LABEL">
						Received Date</td>
					
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strReceivedDate}</td>
					
					</tr>
					
<%-- Manufacturer Name & Manufacturer Serial No --%>
					
					<tr>
						<td width="25%" class="LABEL">Bid No.</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strTenderNo}</td>
					
						<td width="25%" class="LABEL">
						Bid Date</td>
					
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strTenderDate}</td>
					</tr>
					
<%-- Warranty From Date,Warranty Upto &  Unit --%>
					
					<tr>
						<td width="25%" class="LABEL">Bill No.</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strBillNo}</td>					
					
						<td width="25%" class="LABEL">Bill Date</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strBillDate}</td>
					</tr>

					<tr>
						<td width="25%" class="LABEL">PO No.</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strPoNo}</td>					
					
						<td width="25%" class="LABEL">PO Date</td>
						<td width="25%" class="CONTROL">${ hemmsSparePartMgmtDeskFB.strPoDate}</td>
					</tr>
	<tr class="FOOTER"><td colspan="4"></td></tr>
<%-- Specification and Performed Date --%>
</table>
<!--<div>
	<div class="control_button">
	<table class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<div><a
				href="#" class="button" onClick="cancelPage();"><span
				class="back">Back</span></a></div>
			</td>
		</tr>
	</table>
	</div>
	</div>  -->
	
	<table border="0" class="TABLEWIDTH" align="center">
		
		<tr>
			<td align="center"> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();"/></td>
		</tr>
	</table>
		

		
		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="ManufactNo" value=""  />
		<input type="hidden" name="strConsumableFlag" value="${hemmsSparePartMgmtDeskFB.strConsumableFlag}"  />
	
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="hemmsSparePartMgmtDeskFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="hemmsSparePartMgmtDeskFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="hemmsSparePartMgmtDeskFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode" />
			<input type="hidden" name="strPath" value="${hemmsSparePartMgmtDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${hemmsSparePartMgmtDeskFB.strChk}">

</html:form>
</body>
</html>