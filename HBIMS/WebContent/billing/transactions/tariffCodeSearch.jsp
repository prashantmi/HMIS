<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<meta charset=utf-8><title>Tariff Code Search</title>
<%-- <link href="/HBIMS/billing/js/plugins/datatable/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

<script src="/HBIMS/billing/js/plugins/datatable/media/js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="/HBIMS/billing/js/plugins/datatable/media/js/jquery.dataTables.js" language="javascript" type="text/javascript"></script>
<script src="/HBIMS/billing/js/plugins/chosen/chosen.jquery.js" language="javascript" type="text/javascript"></script>--%>
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />

 
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
   <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>

<link href="../css/tariffSearch.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../js/tariffCodeSearch.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<style type="text/css">
 .content {display:none;}
.preload { width:100px;
    height: 100px;
    position: fixed;
    top: 50%;
    left: 50%;}
    
 </style>
</head>
<body onLoad="document.forms[0].strTariffName.focus(),isTariffCodeSearchRequired();myTariffCodeFunc('2');" onkeypress="ESCclose(event)">
<html:form action="/transactions/BillingCNT.cnt"
	method="post">
	
	<div id = "errMsg" class="errMsg"><bean:write name="billingBean" property="strErrMsg"/></div> 
	 <div id='normalMsg'></div>
	
	<table width="100%" align="center">
		<tr class="HEADER">
			<td colspan="4">Tariff Search</td>
		</tr>
		<tr style='display:none;'>
			<td colspan="1" class="LABEL" width="25%"><font color="red">*</font>Search By</td>
			<td colspan="1" class="CONTROL" width="25%">
				<select class="comboNormal" name="strSearchType" tabindex="1" onchange="myTariffCodeFunc(this.value);">
					<%-- <option value="1">Tariff Name</option>--%>
					<option value="2" selected="selected">Tariff</option> 
					<option value="3">Drug</option> 
				</select>
			</td>
			<td colspan="1" class="CONTROL" width="25%" style="display: none;">
				<input type="text" name="strTariffName" class="txtFldMax"
					 maxlength="40" value='${billingBean.searchText}' onkeypress="return validateData(event,19)" 
					 onkeyup="getTariffCodeSearchContent(event);" tabindex="1">
				<input type="text" style="display: none;" name="fake" tabindex="1">
			</td>
			<td colspan="1" class="multiControl"  width="25%" style="display: none;">
				 <img src="../../hisglobal/images/Search.png" name="go" align="middle" style="cursor: hand; cursor: pointer" 
				onClick="return searchTariffCodeValidate();" onkeypress="return searchTariffCodeValidate();"></td>
		</tr>
		<tr>
			<td colspan="4">
				<div id="tariffSearchDivID"></div>
			</td>
		</tr>
		<tr style='display:none;'>
		<td colspan="4">
		<table width='100%' align='center' cellpadding="0" cellspacing="0">
		<tr >
			<td width='90%' class="TITLE">&nbsp;Help</td>
			<td class="multiLabel" width='10%'>
				<div id="plusId">
				<img name="plus" style="cursor: hand; cursor: pointer"
					src="../../hisglobal/images/plus.gif" onClick="trfCodeshowHelp('helpDivId');"></div>
				<div id="minusId" style="display: none">
				<img name="minus" src="../../hisglobal/images/minus.gif" style="cursor: hand; cursor: pointer"
				onClick="trfCodehideHelp('helpDivId'); "></div>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<div id="helpDivId" style="display: none">
					<table width='100%' align="center" cellspacing="1px">
						<tr>
							<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>%</b> to Find Any Data of Any Length
							</td>
						</tr>
						<tr>
							<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>_</b> to Find Data on a Single Character Exclusion
							</td>
						</tr>
						<tr class=FOOTER>
							<td></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		</table>
		</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="center">
				<img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="window.close();" /></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="chargeTypeId" value="${billingBean.chargeTypeId }" />
	<input type="hidden" name="categoryCode" value="${billingBean.categoryCode }" />
	<input type="hidden" name="wardCode" value="${billingBean.wardCode }" />
	<input type="hidden" name="groupId" value="${billingBean.groupId }" />
	<input type="hidden" name="usrFuncName" value="${billingBean.usrFuncName }" />
	<input type="hidden" name="usrArg" value="${billingBean.usrArg }" />

</html:form>

</body>
</html>