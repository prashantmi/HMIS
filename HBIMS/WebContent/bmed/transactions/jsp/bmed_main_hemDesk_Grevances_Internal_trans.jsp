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
 * Date of Creation : 13-May-2011
 * Date of Modification :  
 * Version : 
 * Module  : BMED [HEM Desk]
 */
 -->
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/transactions/js/bmed_HemDesk_trans.js" />
</head>
<body marginheight="0" marginwidth="0">
<html:form name="hemDeskFB"
	action="/transactions/HemDeskACTION"
	type="bmed.transactions.controller.fb.HemDeskFB">
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Grievances(Internal)">
		</his:TitleTag>

		<his:SubTitleTag name="Internal Grievances">
		</his:SubTitleTag>

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Id</td>
					<td width="25%" class="CONTROL_TD">${hemDeskFB.strComplaintId}</td>
					<td width="25%" class="LABEL_TD">Complaint Date</td>
					<td width="25%" class="CONTROL_TD">${hemDeskFB.strComplaintDate}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Department Name</td>
					<td width="25%" class="CONTROL_TD">${hemDeskFB.strDeptName}</td>
					<td width="25%" class="LABEL_TD">Store Name</td>
					<td width="25%" class="CONTROL_TD">${hemDeskFB.strStoreName}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Item Name</td>
					<td class="CONTROL_TD">${hemDeskFB.strItemName}</td>
					<td class="LABEL_TD">Batch No.</td>
					<td class="CONTROL_TD">${hemDeskFB.strItemBatchNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Serial No</td>
					<td class="CONTROL_TD">${hemDeskFB.strItemSerialNo}</td>
					<td width="25%" class="LABEL_TD">Manufacturer Serial No.</td>
					<td class="CONTROL_TD">${hemDeskFB.strManufacturerSerialNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD" colspan="3">${hemDeskFB.strComplaintDescription}</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="Warranty Details">
		</his:SubTitleTag>
		<his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="hemDeskFB" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Maintenance Contract Details">
		</his:SubTitleTag>
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="hemDeskFB" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
		<his:ContentTag>
		 <bean:write name="hemDeskFB" property="strPrevEsclationDtl" filter="false"/>
		</his:ContentTag>
		
		<his:ContentTag name="Escalation Form (Internal)">
			<table class="TABLE_STYLE">
			     <tr>
			<td width="25%" class="LABEL_TD">Request Id</td>
			<td width="25%" class="CONTROL_TD"></td>

			<td width="25%" class="LABEL_TD">Department</td>
			<td width="25%" class="CONTROL_TD"><div id='deptId'></div></td>
		</tr>
		
		<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering
					Item Type</td>
					<td width="25%" class="CONTROL_TD">
					<select
						name="strEnggItemTypeId" class="COMBO_NORMAL"
						onchange="getEnggItemSubTypeCombo();">
						<bean:write name="hemDeskFB"
							property="strEnggItemTypeCmb" filter="false" />
					</select>
					</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering
					Item Sub-Type</td>
					<td width="25%" class="CONTROL_TD">
					<div id="engineeringItemSubTypeId"><select
						name="strEnggItemSubTypeId" class="COMBO_NORMAL" >
						<bean:write name="hemDeskFB"
							property="strEnggItemSubTypeCmb" filter="false" />
						<option value="0">Select Value</option>
					</select></div>
					</td>
				</tr>
				
				
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Service Engineering
					Name</td>
					<td width="25%" class="CONTROL_TD">
					<div id="serviceEnggNameID">
						<select	name="strServiceEnggId" class="COMBO_NORMAL">
							<bean:write name="hemDeskFB" property="strSeriveEnggCmb" filter="false" />
							<option value="0">Select Value</option>
						</select>
					</div>
					</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Escalation Level</td>
					<td width="25%" class="CONTROL_TD">
					<div id="esclationLevelId">
						<select
							name="strEscalationLevelId" class="COMBO_NORMAL" >
							<bean:write name="hemDeskFB" property="strEscalationLevelCmb" filter="false" />
							<option value="0">Select Value</option>
						</select>
					</div>
					</td>
				</tr>
				
				
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Name</td>
					<td width="25%" class="CONTROL_TD">
					    <input type='text' class="TEXT_FIELD_MAX" name='strName' value='' onkeypress='return validateData(event,4);' maxlength='50'>
					</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Designation</td>
					<td width="25%" class="CONTROL_TD">
					   <input type='text' class="TEXT_FIELD_MAX" name='strDesignation' value='' onkeypress='return validateData(event,4);' maxlength='50'>
					</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Contact No</td>
					<td width="25%" class="CONTROL_TD">
					     <div id="contactNo"></div>
					</td>
					<td width="25%" class="LABEL_TD">EMail Id</td>
					<td width="25%" class="CONTROL_TD">
					     <div id="emilaId"></div>
					</td>
				</tr>
				
				<tr>
		           <td width ="25%%" class ="LABEL_TD" valign="middle" ><font  color="red">*</font>Mode of Escalation</td>
		           <td width ="25%" class ="CONTROL_TD">
		    
		               <select class="COMBO_NORMAL" name="strModeofEscId">
		               	  <option value="0">Select Value</option>
		               	  <option value="1">Phone</option>
		               	  <option value="2">E-Mail</option>
		               	  <option value="3">Fax</option>
		               	  <option value="4">Post</option>
		               </select>
		               
		            </td>
                   <td class="CONTROL_TD" width="25%"></td>
                    <td class="CONTROL_TD" width="25%"></td>
				</tr>
				
				
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Date of Escalation</td>
					<td width="25%" class="CONTROL_TD">
					  <div id="orderDateDiv">
					     <dateTag:date	name="strEscDate"	 value=""></dateTag:date>
					  </div>
					</td>
					
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Time of Escalation</td>
						
					<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strEscTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
					</td>
					
					
				</tr>
				
		    
			     <tr>
					<td width="50%" colspan='2' class="LABEL_TD">Remarks</td>
					<td width="50%" colspan='2' class="CONTROL_TD">
						<html:textarea name="hemDeskFB" property="strRemarks" cols="25" rows="2"  />
					</td>
					
					
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
				src="/HBIMS/hisglobal/images/btn-sv.png"
				onClick="return validate1();" />
			<img style="cursor: pointer;"
				src="/HBIMS/hisglobal/images/btn-clr.png" onClick="clearPage();">
			<img style="cursor: pointer;"
				src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelFunc();">
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="hemDeskFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="hemDeskFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="hemDeskFB" property="strNormalMsg" /></div>
			<br />
		<input type="hidden" name="hmode" />
			<input type="hidden" name="strPath" value="${hemDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${hemDeskFB.strChk}">
			<input type="hidden" name="strEscDtl" id="strEscDtl" value="">
			<input type="hidden" name="strReqType" id="strReqType" value="${hemDeskFB.strReqType}">
		
		
		<html:hidden name="hemDeskFB" property="strComplaintId" />
		<html:hidden name="hemDeskFB" property="strHemDesk" />
		<html:hidden name="hemDeskFB" property="strUploadFileId" />
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>