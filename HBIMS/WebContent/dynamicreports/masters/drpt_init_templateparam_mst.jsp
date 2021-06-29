<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Dynamic Report Template Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">	

<style type="text/css">


.txtFldBig {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-style: normal;
	font-weight: normal;
	color: #000000;
	height: 16px;
	width: 200px;
}


</style>


<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar1.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>

<script language="JavaScript" src="../../dynamicreports/js/advance_popup.js"></script>
<script language="JavaScript" src="../../dynamicreports/js/jquery-1.2.6.pack.js"></script>

<script language="JavaScript" src="../../dynamicreports/js/drpt_init_templateparamMst.js"></script>

<!-- 
/**
 * @author Balasubramaniam M
 * Date of Creation : 17/4/2012
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : Dynamic Report Generation
 */
 -->
</head>
<body class="background">
	<html:form action="/masters/DynamicReportParamMstCNT"
		name="rptparamBean"
		type="dynamicreports.masters.controller.fb.DynamicReportParamMstFB" styleClass="formbg">

		<center>
			<div id="errMsg" class="errMsg">
				<bean:write name="rptparamBean" property="strErrMsg" />
			</div>
			<div id="warningMsg" class="warningMsg">
				<bean:write name="rptparamBean" property="strWarningMsg" />
			</div>
			<div id="normalMsg" class="normalMsg">
				<bean:write name="rptparamBean" property="strNormalMsg" />
			</div>


			
		</center>

		<table class="TABLEWIDTH" align="center">
			<tr class="HEADER">
				<td colspan="4">Dynamic Reports Template Parameter Mapping
					Master</td>
			</tr>


		</table>


		<table border="0" class="TABLEWIDTH" align="center" border='0'
			cellspacing='1px' cellpadding='1px'>

			<tr>
				<td class="LABEL" width="25%"><font color="red">*</font>Report Type</td>
				<td class="CONTROL" width="25%"><select name="strReportTypeId"
					class="comboNormal" onchange="getReportList(this);">
						<bean:write name="rptparamBean" property="strReportTypeValues"
							filter="false" />
				</select></td>

				<td class="LABEL" width="25%"><font color="red">*</font>Report Name</td>
				<td class="CONTROL" width="25%"><div id="reportNameDivId">
						<select name="strReportTemplateId" class="comboMax"><option
								value="0">Select Value</option>
						</select>
					</div></td>
			</tr>
		</table>

		<div id="reportDetaildDivId" style="display: none">
			<table border="0" class="TABLEWIDTH" align="center" border='0'
				cellspacing='1px' cellpadding='1px'>

				<tr>
					<td class="LABEL" width="25%">Display Name</td>
					<td class="CONTROL" width="25%"><div
							id="reportDisplayNameDivId">--</div></td>

					<td class="LABEL" width="25%">Report Width</td>
					<td class="CONTROL" width="25%"><div id="reportWidthDivId">--</div></td>
				</tr>
				
				<tr>
					<td class="LABEL" width="25%">Report Header Type</td>
					<td class="CONTROL" width="25%"><div
							id="reportHeaderTypeDivId">--</div></td>

					<td class="LABEL" width="25%">Border Required</td>
					<td class="CONTROL" width="25%"><div id="reportBorderDivId">--</div></td>
				</tr>

			</table>
		</div>

		<div id="previousLevelsDivId" style="display: none;"></div>

		<div id="procedureDetaildDivId" style="display: none">
			<table border="0" class="TABLEWIDTH" align="center" border='0'
				cellspacing='1px' cellpadding='1px'>

				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Procedure Name</td>
					<td class="CONTROL" width="25%"><select
						name="strProcedureName" class="comboMax" onchange="getProcInParamList(this);" >
							<bean:write name="rptparamBean" property="strProcedureValues"
								filter="false" />
					</select></td>

					<td class="LABEL" width="25%">
						<div id="isComboDivId" style="display: block;">Is Combo
							Dependent</div>
						<div id="isLastDivId" style="display: none;">Is Last Level</div>
					</td>
					<td class="CONTROL" width="25%">

						<div id="isComboCheckDivId" style="display: block;">
							<html:checkbox property="strIsCombo" name="rptparamBean"
								value="1"></html:checkbox>
						</div>
						<div id="isLastCheckDivId" style="display: none;">
							<html:checkbox property="strIsLast" name="rptparamBean" value="1"></html:checkbox>
						</div>

					</td>
				</tr>

			</table>
		</div>

<div id="rptDisplayDetaildDivId" style="display: none">
			<table border="0" class="TABLEWIDTH" align="center" border='0'
				cellspacing='1px' cellpadding='1px'>

				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Report Display Name</td>
					<td class="CONTROL" width="25%"><input name="strReportLevelDisplayName"  type="text" class="txtFldBig" maxlength="250"  onkeypress="return validateData(event,18);" > </td>
					 
					<td class="LABEL" width="25%">Is Column Base Report</td>
					<td class="CONTROL" width="25%"><input name="strIsColumnBaseRpt"  type="checkbox" value='1' > </td>
					 					 
				</tr>

			</table>
		</div>

		<div id="reportParamDivId" style="display: none;"></div>


		<table border="0" class="TABLEWIDTH" align="center" border='0'
			cellspacing='1px' cellpadding='1px'>
			<tr class="FOOTER">
				<td colspan="4"></td>
			</tr>
		</table>

<div id="reportButtonsDivId" style="display: block;">
		<table border="0" class="TABLEWIDTH" align="center">
			<tr>
					<td align="center">
					<div class="legends">
						<font color='red'>*</font>Mandatory Fields
					</div>
					<div class="control_button" align="left">
						<a href="#" class="button" title="Next Record" tabindex="1"
							onClick="return validate();"
					onkeypress="return validateInsert();"><span class="next">Next</span></a>
						<a href="#" class="button" title="Reset Content" tabindex="1"
							onkeypress="document.forms[0].reset();"
					onClick="document.forms[0].reset();"><span class="clear">Clear</span></a>
						<a href="#" class="button" tabindex="1" onkeypress="cancel('LIST');" onClick="cancel('CANCELPAGE');"><span
							class="cancel">Cancel</span></a>
					</div>
				</td>
			</tr>
		</table>
	</div>	
		<input type="hidden" name="hmode" />
	 <input type="hidden" name="strReportTypeName" />
	 <input type="hidden" name="strReportName" />
	 <input type="hidden" name="strIsMergeIntermediate" value="0" />
	 
	 <input type="hidden" name="strReportHeaderTypeId" value="0" />
	 <input type="hidden" name="strReportHeaderParamReq" value="0" />
	 

<div id="blanket" style="display:none;"></div>
   <div class="popUpDiv" id="popUpDiv" style="display:none;">
   <table bgcolor="white">
     <tr>
      <td>
         <div id="comboDivId" style="display:block;">
         <table width="400px" height="400px" border="0px" cellpadding="1px" cellspacing="1px">
         <tr class="TITLE"><td>Combo Query Details</td></tr>
         <tr>
         <td class="CONTROL"><b>Combo Contents can be</b> <br><br> <b>Default Value :</b> 0^Select Value#1^some value ....<br><br>
         <b>Query :</b> Select id,name from Table where condition...  <br>if combo 1 value is dependent for the population of this combo <br>then 
         use #1# which represents the combo 1's value <br>where  #2# which represents the combo 2's value
         </td>
         </tr>
         <tr>
         <td>
         <textarea name="strTempQueryVal" rows="15" cols="50"></textarea>
         </td>
         </tr>
         <tr>
         <td class="TITLE">&nbsp;
         </td>
         </tr>
         <tr>
         <td align="center"> 
         
         <input type="button" value="Ok" style="cursor: pointer; " title="Save Contents" onClick="setQuery('1');">
         <input type="button" value="Cancel" style="cursor: pointer; " title="Cancel Popup" onClick="setQuery('0');">
         
         </td>
         </tr>
         </table>
         
         
         </div>
       </td>
     </tr>
    </table>
   </div>



  <div class="popUpDiv" id="viewContentPopupDivId" style="display:none;">
   <table bgcolor="white">
     <tr>
      <td>
         <div id="viewContentDivId" style="display:block;">
         </div>
         </td>
         </tr>
         </table>
         </div>
 
		
	</html:form>
	<tag:autoIndex></tag:autoIndex>
</body>
</html>