<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%--  
/**
 * @author Partha P. Chattaraj and T. Saratkumar
 * Date of Creation : 05-August-2013
 * Date of Modification :  
 * Version : 1.0
 * Module  : HEMMS Product
 */
 --%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
 <%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<title>Equipment Maintenance Contract Detail</title>
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

<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/newpopup.js"/>

<script language="JavaScript"
	src="../../bmed/transactions/js/bmed_main_itemMaintContactDtl_trans.js"></script>

<script type="text/javascript">

</script>

</head>
<body marginheight="0" marginwidth="0"  onLoad="changeDivImg();">
<html:form name="itemContractDetailsFB"
	action="/transactions/ItemMaintContractDtlsACTION"
	type="bmed.transactions.controller.fb.ItemMaintContractDtlsFB" enctype="multipart/form-data">
	<div class="errMsg" id="errMsg"><bean:write
		name="itemContractDetailsFB" property="strErrMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="itemContractDetailsFB" property="strWarningMsg" /></div>
	<div style="display: none;" class="normalMsg" id="normalMsg"><bean:write
		name="itemContractDetailsFB" property="strNormalMsg" filter="false" /></div>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Detail </td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Hospital Name</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strDeptId}</td>
			<td width="25%" class="LABEL">Lab Name</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strStoreId}</td>
		</tr>
            
		<tr>
			<td width="25%" class="LABEL">Equipment Name</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strItemNameId}</td>
			<td width="25%" class="LABEL">Equipment Model</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strItemBrandId}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Equipment Serial Number</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strBatchNo}</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Maintenance Contract Detail</td>
		</tr>
		
		<tr>
			<td width="25%" class="LABEL">Maintenance Contract Supplier</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strMantContractSuppId}</td>
			<td width="25%" class="LABEL">Maintenance Contract Type</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strMantContractTypeId}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Company Contact Number</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strCompanyContactNo}</td>
			<td width="25%" class="LABEL">Company Mail id</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strCompanyMailId}</td>
		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Maintenance Company Name</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strMaintenanceContractName}</td>
			<td width="25%" class="CONTROL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">From Date</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strFromDate}</td>
			<td width="25%" class="LABEL">To Date</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strToDate}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Preventive Maintenance</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strRoutineFrequency}&nbsp;${itemContractDetailsFB.strRoutineUnitId}</td>
			<td width="25%" class="LABEL">Response Time</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strResponseTime}&nbsp;${itemContractDetailsFB.strResponseTimeUnitId}</td>
		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Response Time</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strResponseTime}</td>
			<td width="25%" class="LABEL">Unit Name</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strResponseTimeUnitId}</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Maintenance Cost</td>
			<td class="CONTROL">${itemContractDetailsFB.strMaintenanceCost}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strRemarks}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Terms &amp; Condition</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strTermsAndCond}</td>
			<td width="25%" class="LABEL">Penalty Conditions</td>
			<td width="25%" class="CONTROL">${itemContractDetailsFB.strPeneltyCond}</td>
		</tr>
	</table>
	
	<table id="DocDownloadRow" class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
				<td colspan="4" width="25%">Document Details </td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">  Ref. No.</td>
			<td class="CONTROL" width="25%">${itemContractDetailsFB.strDocRefNo}</td>
			<td class="LABEL" width="25%">  Ref Date</td>
			<td class="CONTROL" width="25%">${itemContractDetailsFB.strDocRefDate}</td>
		</tr>
		<tr >
			<td colspan="4" class="LABEL" width="25%">	
				<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
					<tr>
						<td class="LABEL" width="50%">Contract Copy</td>
						<td class="CONTROL" width="20%">
							<div >
								<div id="IconDiv" style="position: relative;left: 3px;top: 8px;"><img src="../../hisglobal/images/global-icon.png"></div>
								<div  style="color: blue;left: 30px;position: relative;top: -10px;" >${itemContractDetailsFB.strUploadFileName}</div>
							</div>
						</td>
						<td class="CONTROL" width="30%">
							<input type="button" class="btn" value="View / Download" onclick="getUploadFile();">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="FOOTER">
			<td colspan="4"> </td>
		</tr>
	</table>  		 
		
	<div>
	<!--<div class="control_button">
	<table class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<div><a href="#" class="button" onClick="cancelPage();"><span
				class="back">Back</span></a></div>
			</td>
		</tr>
	</table>
	</div>
	</div>  -->
	
	
	<table border="0" class="TABLEWIDTH" align="center">
		
		<tr>
			<td align="center"><img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();"/></td>
		</tr>
	</table>

		<input type="hidden" name="hmode" />
		<input type="hidden" name="strIsMaintWarrantyDesk" value="${itemContractDetailsFB.strIsMaintWarrantyDesk}" />
		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="strUploadFileId" value="${itemContractDetailsFB.strUploadFileId}" />
		<input type="hidden" name="strCheck" value="0" />
		<input type="hidden" name="strIsReNew" value="0" />
		<input type="hidden" name="strIsReNewPK" value="0" />
		<input type="hidden" name="strIsCancel" value="0" />



		<div id="blanket" style="display: none;"></div>
		<div class="popUpDiv" id="popUpDiv" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>

				<div id="strRenew" style="display: block;"></div>
				</td>
			</tr>
		</table>
		</div>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>