<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Details</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/cashcollection_trans.js"></script>


<tag:tab onlyTabIndexing="1"></tag:tab>

</head>
<body onload="fetchBillList('1','1');">
<html:form 
	action="/transactions/CashCollectionTransCNT" method="post"
	>
	
	<div id = "errMsg" class="errMsg"><bean:write name="cashCollectionTransBean" property="strErrMsg"/></div>
	 <div id='normalMsg'></div>
<table width="100%" cellspacing="1px">
    <tr>
		<td>
		<table width='100%' align="center" cellspacing="1px">
			<tr class="HEADER">
				<td colspan="4">Bill Details</td>
			</tr>
			<tr>
			</tr>
		</table>
		
		<table width='100%' align="center" cellspacing="1px">
		<tr>
		<td class='LABEL'>
		<b><font color="red">*</font>Patient Name :</b> <input type='text' name='strSearchString' class="txtFldMax" style="height: 19px"  onkeypress="return validateData(event,11),validateSearchText(event);"> <img style="cursor:hand;cursor:pointer;" src="../../hisglobal/images/btn-search.png"
						name="search" onClick="getSearchBillListBySearch();" style="cursor:pointer;cursor:hand"
						onKeyPress="getSearchBillListBySearch();" align="middle" height="19" width="70">  &nbsp;</td>
		</tr>
		</table>
		
		<div id="fetchRecordDivId"></div>
				
			
			<table width='100%'  cellspacing="0px">
				<tr class="FOOTER">
				<td width='3%'><div id="plusHelpId" align="center"><img
						src="../../hisglobal/images/plus.gif" name="plusHelp" style="cursor:pointer;cursor:hand"
						align="middle" 
						onclick="showHelpDetails('HelpId');" /></div>
					<div id="minusHelpId" style="display: none" align="center"><img
						src="../../hisglobal/images/minus.gif" name="minusHelp" style="cursor:pointer;cursor:hand"
					
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
						src="../../hisglobal/images/ok.gif" onClick="setBillNo();"></td>
					
					<td align="left"><img style="cursor:pointer;cursor:hand"
						src="../../hisglobal/images/btn-ccl.png" onClick="window.close();"></td>
				</tr>
			</table>
					
		</td>
		</tr>
		</table>
		
		
		<input type="hidden" name="userJsFunctionName" value="${cashCollectionTransBean.strBillUsrFuncName }" />		
		</html:form>
		<tag:autoIndex></tag:autoIndex>  
</body>
</html>