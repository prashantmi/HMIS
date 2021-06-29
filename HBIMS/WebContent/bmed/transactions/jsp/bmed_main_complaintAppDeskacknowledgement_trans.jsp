<%@page import="hisglobal.hisconfig.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
 * Date of Creation : 10-May-2011
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 -->
<html>
<head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript
	src="/bmed/transactions/js/bmed_HemComplaintApp_Desk_trans.js" />
</head>
<body marginheight="0" marginwidth="0" onLoad="check();">
<html:form name="hemComplaintAppDeskFB"
	action="/transactions/HemComplaintApprovalDeskACTION"
	type="bmed.transactions.controller.fb.HemComplaintApprovalDeskFB">
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Acknowledgment">
		</his:TitleTag>

		<his:SubTitleTag name="Complaint Details">
		</his:SubTitleTag>

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Complaint Type</td>
					<td width="25%" class="CONTROL_TD">${hemComplaintAppDeskFB.strComplaintType}</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Id</td>
					<td width="25%" class="CONTROL_TD">${hemComplaintAppDeskFB.strComplaintId}</td>
					<td width="25%" class="LABEL_TD">Complaint Date</td>
					<td width="25%" class="CONTROL_TD">${hemComplaintAppDeskFB.strComplaintDate}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Hospital Name</td>
					<td width="25%" class="CONTROL_TD">${hemComplaintAppDeskFB.strStoreName}</td>
					<td width="25%" class="LABEL_TD">Lab Name</td>
					<td width="25%" class="CONTROL_TD">${hemComplaintAppDeskFB.strLabName}</td>
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Engineering Item Type</td>
					<td width="25%" class="CONTROL_TD">${hemComplaintAppDeskFB.strEnggItemType}</td>
					<td width="25%" class="LABEL_TD">Engineering Item Sub Type</td>
					<td width="25%" class="CONTROL_TD">${hemComplaintAppDeskFB.strEnggItemSubType}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Equipment Name</td>
					<td class="CONTROL_TD">${hemComplaintAppDeskFB.strItemName}</td>
					<td class="LABEL_TD">Equipment Model</td>
					<!--<td class="CONTROL_TD">${hemComplaintAppDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
					<td class="CONTROL_TD">${hemComplaintAppDeskFB.strItemModel}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">UIN</td>
					<td class="CONTROL_TD">${hemComplaintAppDeskFB.strEquipmentUIDNo}</td>
					<td width="25%" class="LABEL_TD">Equipment Serial No.</td>
					<td class="CONTROL_TD">${hemComplaintAppDeskFB.strItemBatchNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Manufacturer/Supplier Name</td>
					<td class="CONTROL_TD">${hemComplaintAppDeskFB.strManufacturerName}</td>
					<td width="25%" class="LABEL_TD">Complaint Description</td>
					<td rowspan="3" class="CONTROL_TD">${hemComplaintAppDeskFB.strComplaintDescription}</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="Warranty Details">
		</his:SubTitleTag>
		<his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="hemComplaintAppDeskFB" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Maintenance Contract Details">
		</his:SubTitleTag>
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="hemComplaintAppDeskFB" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
		
		<his:ContentTag name="Others Complaint">
			<bean:write name="hemComplaintAppDeskFB" property="strComplaintAppDtls" filter="false"/>
			
		</his:ContentTag>
		
		<his:ContentTag name="Complaint Acknowledgment">
			<table class="TABLE_STYLE">
			     <tr>
			<td width="50%" class="LABEL_TD"><font color="red">*</font>Acknowledgment</td>
			<td width="50%" class="CONTROL_TD">
			   <input type="radio" name=strIsApproved onClick="changeMode(this);" value="1" />Yes&nbsp;&nbsp;
			   <input type="radio" name="strIsReject" onClick="changeMode(this);" value="2" />No
			</td>

			
		</tr>
		
		    <tr>
					<td width="50%" class="LABEL_TD">Remarks</td>
					<td width="50%" class="CONTROL_TD"><textarea rows="2" cols="20" name="strRemarks" onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea> </td>
				</tr>
			     
			</table>
		</his:ContentTag>
		
		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-sv.png"
				onClick="return validate1();" />
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-clr.png" onClick="ClearPage();">
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();">
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="hemComplaintAppDeskFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="hemComplaintAppDeskFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="hemComplaintAppDeskFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode" />
			<input type="hidden" name="strPath" value="${hemComplaintAppDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${hemComplaintAppDeskFB.strChk}">
			<input type="hidden" name="strDeptId" value="${hemComplaintAppDeskFB.strDeptId}">
			<input type="hidden" name="strComplaintStatus" value="${hemComplaintAppDeskFB.strComplaintStatus}">
			
			<input type="hidden" name="strStatus">
		
		<html:hidden name="hemComplaintAppDeskFB" property="strComplaintId" />
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>