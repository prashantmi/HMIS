<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<!-- 
/**
 * @author Amit Kumar
 * Date of Creation : 28-April-2011
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 -->
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
<title>Equipment Guaranty Detail</title>
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

<script language="JavaScript" src="../../bmed/transactions/js/bemd_main_ItemNonItemWarrantyDtl_trans.js"></script>

<script type="text/javascript">

</script>

</head>
<body marginheight="0" marginwidth="0"  onLoad="changeDivImg();" class="background">
<html:form name="itemWarrantyDetailsFB"
	action="/transactions/ItemWarrantyDtlsACTION"
	type="bmed.transactions.controller.fb.ItemWarrantyDtlsFB" enctype="multipart/form-data">
	   <div class="errMsg" id="errMsg"><bean:write
			name="itemWarrantyDetailsFB" property="strErrMsg" /></div>
		<div class="warningMsg" id="warningMsg"><bean:write
			name="itemWarrantyDetailsFB" property="strWarningMsg" /></div>
		<div style="display:none;" class="normalMsg" id="normalMsg"><bean:write
			name="itemWarrantyDetailsFB" property="strNormalMsg" /></div>
				
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Guaranty Detail </td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Hospital Name</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strDeptId}</td>
			<td width="25%" class="LABEL">Lab Name</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strStoreId}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Equipment	Name</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strItemNameId}</td>
			<td width="25%" class="LABEL">Equipment Model</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strItemBrandId}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Equipment Serial No.</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strBatchNo}</td>
			<td width="25%" class="LABEL">Program Name</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strProgramName}</td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Manufacturer Name</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strSupplierId}</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Guarantee Start Date</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strWarrantyStartDate}</td>
			<td width="25%" class="LABEL">Guarantee UpTo</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strWarrantyUpTo}&nbsp;${itemWarrantyDetailsFB.strWarrantyId}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Terms &amp; Condition</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strTermsAndCond}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${itemWarrantyDetailsFB.strRemarks}</td>
		</tr>
	</table>
	
	<table id="DocDownloadRow" class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
				<td colspan="4" width="25%">Document Details </td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">  Ref. No.</td>
			<td class="CONTROL" width="25%">${itemWarrantyDetailsFB.strDocRefNo}</td>
			<td class="LABEL" width="25%">  Ref Date</td>
			<td class="CONTROL" width="25%">${itemWarrantyDetailsFB.strDocRefDate}</td>
		</tr>
		<tr >
			<td colspan="4" class="LABEL" width="25%">	
				<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
					<tr>
						<td class="LABEL" width="50%">Contract Copy</td>
						<td class="CONTROL" width="20%">
							<div >
								<div id="IconDiv" style="position: relative;left: 3px;top: 8px;"><img src="../../hisglobal/images/global-icon.png"></div>
								<div  style="color: blue;left: 30px;position: relative;top: -10px;" >${itemWarrantyDetailsFB.strUploadFileName}</div>
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
		
		<!-- <div>
		<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div><a href="#" class="button" onClick="cancelPage('LIST');"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div>
		</div> -->
		
		<table border="0" class="TABLEWIDTH" align="center">
		
		<tr>
			<td align="center"><img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage('LIST');"/></td>
		</tr>
	</table>

		<input type="hidden" name="hmode" />

		<input type="hidden" name="strStockInfoVal" value="0" />
		<input type="hidden" name="strUploadFileId" value="${itemWarrantyDetailsFB.strUploadFileId}" />
		<input type="hidden" name="strCheck" value="0" />
		<input type="hidden" name="strIsReNew" value="0" />
		<input type="hidden" name="strIsExtend" value="0" />
		<input type="hidden" name="strIsExtendPK" value="0" />
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