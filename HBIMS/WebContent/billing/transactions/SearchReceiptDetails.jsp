<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
 Search Receipt Details Pop Up Jsp
  
 author : Debashis Sardar
 
 date: 10-Dec-2011
 -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<meta charset=utf-8>
<title>Receipt Details</title>

<link href="/HBIMS/billing/css/transaction.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="/HBIMS/hisglobal/transactionutil/js/master.js"></script>
<script language="Javascript" src="/HBIMS/hisglobal/js/validation.js"></script>
<script language="Javascript" src="/HBIMS/hisglobal/js/util.js"></script>
<script language="Javascript" src="/HBIMS/billing/js/SearchReceiptDetails.js"></script>
<tag:tab onlyTabIndexing="1"></tag:tab>
</head>

<body onload="configPatListType(),fetchPatientList('1','1');">
<script language="Javascript" src="/HBIMS/hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/BillingCNT.cnt"
	 method="post">
	
	<div id = "errMsg" class="errMsg"><bean:write name="billingBean" property="strErrMsg"/></div> 
 <div id='normalMsg'></div>
	
<table width="100%" cellspacing="1px">

	<tr>
		<td>
		<table width='100%' align="center" cellspacing="1px">
			<tr class="HEADER">
				<td colspan="4">Receipt Details</td>
			</tr>
			<tr>
			</tr>
		</table>
		<div id="fetchRecordDivId"></div>
				<table width='100%' align="center" cellspacing="1px">
		<tr>
		<td class='LABEL'>
		<b>Find By</b> <select name='strSearchType' class='comboNormal'><option value='0'>Select Value</option><option value='1'>CR No.</option><option value='2'>Patient Name</option><option value='3'>Admission No.</option></select> <input type='text' name='strSearchString' class="txtFldMax" style="height: 19px" onkeypress="return getPatListOnEnter(event);"> <label for="sn"><img style="cursor:pointer;cursor:hand" src="/HBIMS/hisglobal/images/btn-search.png" title="Search Record"
						name="search" onClick="getSearchPatListBySearch();"
						onkeypress="return getPatListOnEnter(event);" align="middle" height="19" width="70"></label>  &nbsp;</td>
		</tr>
		</table>
				
		
			<table width='100%'  cellspacing="0px">
				<tr class="FOOTER">
				<td width='3%'><div id="plusHelpId" align="center"><img
						src="/HBIMS/hisglobal/images/plus.gif" name="plusHelp" style="cursor:pointer;cursor:hand"
						align="middle"
						onclick="showHelpDetails('HelpId');" /></div>
					<div id="minusHelpId" style="display: none" align="center"><img
						src="/HBIMS/hisglobal/images/minus.gif" name="minusHelp" style="cursor:pointer;cursor:hand"
							onclick="hideHelpDetails('HelpId');"> </div></td>
						<td><div align="left">Help</div></td>
					
				</tr>
			</table>
			<div id="HelpId" style="display:none">
			<table width='100%' align="center" cellspacing="1px">
			<tr >
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 Use <b>%</b> to Find Any Data of Any Length (Including zero length)
			</td>
			</tr>
			<tr >
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Use <b>_</b> to Find Data on a Single Character Exclusion
			</td>
			</tr>
			<tr class=FOOTER>
			<td ></td>
			</tr>
			</table>
			</div>
			
		
		<table border="0" width='100%' align="center">
				<tr>
<td align="right"><img style="cursor:pointer;cursor:hand"
						src="/HBIMS/hisglobal/images/ok.gif" onClick="setRcptNo();" title="Select the Patient"></td>
					
					<td align="left"><img style="cursor:pointer;cursor:hand"
						src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="window.close();" title="Cancel and Close the Window"></td>
				</tr>
			</table>
					
		</td>
		</tr>
		</table>
		
		<input type="hidden" name="patListType" value="${billingBean.usrArg }" />
		<input type="hidden" name="userJsFunctionName" value="${billingBean.usrFuncName }" />	
		
		</html:form>
		<tag:autoIndex></tag:autoIndex> 
</body>
</html>