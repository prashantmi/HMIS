<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head>
<meta charset="utf-8">
<title>Supplier Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script type="text/javascript">
	function view1() {
		document.getElementById("addressPlusId").style.display = "none";
		document.getElementById("addressMinusId").style.display = "block";
		document.getElementById("addressDtlId").style.display = "block";
	}
	function view2() {
		document.getElementById("addressPlusId").style.display = "block";
		document.getElementById("addressMinusId").style.display = "none";
		document.getElementById("addressDtlId").style.display = "none";
	}
	function bringSupplierBlackListedView() {
		if (document.forms[0].strSupplierStatus.value == '2') {
			document.getElementById("IsSupplierBlackListedId").style.display = "block";

		} else if (document.forms[0].strSupplierStatus.value == '1'
				&& document.forms[0].strIsBlackListMod.value == '1') {
			document.getElementById("IsSupplierBlackListedId").style.display = "block";

		} else {
			document.getElementById("IsSupplierBlackListedId").style.display = "none";
		}

	}
	
	function showEscLevel()
	{
	    var levelTwo		=	document.getElementById("LevelTwo");
	    var levelOne        =	document.getElementById("LevelOne");
	    
	    if(document.forms[0].strLevelOneOpen.value == '1')
	    {
	       levelOne.style.display="block";
	    }
	    if(document.forms[0].strLevelTwoOpen.value == '1')
	    {
	       levelTwo.style.display="block";
	    }
		
	}
	
	function blackList() {

		document.forms[0].strSupplierStatus.value = document.forms[0].strSupplierStatusCode.value;
		if (document.forms[0].strSupplierStatus.value == '2') {
			document.getElementById("IsSupplierBlackListedId").style.display = "block";
		}

		if (document.forms[0].IsSupplier.value == '1') {
			document.forms[0].strIsSupplier.checked = true;
		}
		if (document.forms[0].IsManufacturer.value == '1') {
			document.forms[0].strIsManufacturer.checked = true;
		}
		if (document.forms[0].IsAgent.value == '1') {
			document.forms[0].strIsAgent.checked = true;
		}
		if (document.forms[0].IsBuyer.value == '1') {
			document.forms[0].strIsBuyer.checked = true;
		}
		if (document.forms[0].IsLocalPurchaseSupp.value == '1') {
			document.forms[0].strLocalPurchaseSuppFlag.checked = true;
		}
		if (document.forms[0].IsForeignSupplier.value == '1') {
			document.forms[0].strForeignerSuppFlag.checked = true;
		}
		if (document.forms[0].strIsBlackListMod.value == '0') {
			document.forms[0].strActionDate.value = document.forms[0].strCurrentDate.value;
		}

	}
	function closePopUp() {
		window.close();
	}
</script>
</head>
<body onload="showEscLevel();blackList();">
<html:form action="/masters/SupplierMstCNT" name="supplierBean"
	type="mms.masters.controller.fb.SupplierMstFB">

	<center><tag:tab tabLabel="Supplier Master"
		selectedTab="FIRST" align="center" width="75%">

	</tag:tab></center>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4" width="25%">Supplier Master&gt;&gt; View</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Item Category Name</td>
			<td class="CONTROL"><bean:write name="supplierBean"
				property="strItemCategoryName" /></td>
			<td width="25%" class="LABEL">Supplier Name</td>
			<td width="25%" class="CONTROL"><bean:write name="supplierBean"
				property="strSupplierName" filter="false" /></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Supplier Grade</td>
			<td width="25%" class="CONTROL"><bean:write name="supplierBean"
				property="strSupplierGrade" filter="false" /></td>
			<td width="25%" class="LABEL">Contact Person</td>
			<td width="25%" class="CONTROL" colspan="3"><bean:write
				name="supplierBean" property="strContactPerson" filter="false" /></td>
		</tr>
		<logic:notEqual name="supplierBean" property="strSupplierType"
			value="0">
			<tr>
				<td width="25%" class="LABEL">Supplier Type</td>
				<td width="25%" class="CONTROL"><bean:write name="supplierBean"
					property="strSupplierType" filter="false" /></td>
				<td width="25%" class="LABEL">Contract Number</td>
				<td width="25%" class="CONTROL" colspan="3"><bean:write
					name="supplierBean" property="strContractNo" filter="false" /></td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">Contract Date</td>
				<td width="25%" class="CONTROL"><bean:write name="supplierBean"
					property="strContractDate" filter="false" /></td>
				<td width="25%" class="LABEL">Expiry Date</td>
				<td width="25%" class="CONTROL" colspan="3"><bean:write
					name="supplierBean" property="strExpiryDate" filter="false" /></td>
			</tr>
		</logic:notEqual>

	</table>
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE" width="25%">
			<div id="addressPlusId" align="left" style="display: block;"><img
				src="../../hisglobal/images/plus.gif" onClick="view1();"
				style="cursor: pointer;" /> Address Details</div>
			<div id="addressMinusId" style="display: none;" align="left"><img
				src="../../hisglobal/images/minus.gif" onClick="view2();"
				style="cursor: pointer;" /> Address Details</div>
			</td>
		</tr>

	</table>
	<div id="addressDtlId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="LABEL" colspan="1" width="25%">Address</td>
			<td class="CONTROL" colspan="" width="25%"><bean:write
				name="supplierBean" property="strAddress" filter="false" /></td>
			<td class="LABEL" colspan="1" width="25%"></td>
			<td class="LABEL" colspan="1" width="25%"></td>

		</tr>
		<tr>
			<td class="LABEL" width="25%">City</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strCity" filter="false" /></td>
			<td class="LABEL" width="25%">Pin Code</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strPincode" filter="false" /></td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Country</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strCountryCode" filter="false" /></td>
			<td class="LABEL" width="25%">State</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strStateCode" filter="false" /></td>
		</tr>

		<tr>
			<td class="LABEL" width="25%">Phone No.</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strPhoneNo1" filter="false" /></td>
			<td width="25%" class="LABEL">Mobile No.</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strPhoneNo2" filter="false" /></td>

		</tr>
		<tr>
			<td class="LABEL" width="25%">Email Id</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strEmailId1" filter="false" /></td>
			<td class="LABEL" width="25%">Alternative Email Id</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strEmailId2" filter="false" /></td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Fax No.1</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strFaxNo1" filter="false" /></td>
			<td width="25%" class="LABEL">Fax No.2</td>
			<td class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strFaxNo2" filter="false" /></td>
		</tr>
		<tr>
			<td class="LABEL" colspan="1" width="25%">Website</td>
			<td class="CONTROL" colspan="" width="25%"><bean:write
				name="supplierBean" property="strWebsite" filter="false" /></td>
			<td class="LABEL" colspan="1" width="25%"></td>
			<td class="LABEL" colspan="1" width="25%"></td>

		</tr>

	</table>
	</div>
	<table cellpadding="1px" cellspacing="1px" class="TABLEWIDTH"
		align="center">
		<tr>
			<td class="TITLE" colspan="4">Supplier Type</td>
		</TR>
		<tr>

			<TD class="LABEL" width="25%">Supplier</TD>
			<TD class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strIsSupplier" filter="false" /></TD>
			<TD class="LABEL" width="25%">Manufacturer</TD>
			<TD class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strIsManufacturer" filter="false" /></TD>


		</TR>
		<TR>
			<TD class="LABEL" width="25%">Chemist</TD>
			<TD class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strIsAgent" filter="false" /></TD>
			<TD class="LABEL" width="25%">Buyer</TD>
			<TD class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strIsBuyer" filter="false" /></TD>

		</TR>
		<TR>
			<TD class="LABEL" width="25%">Whether Foreigner Supplier</TD>
			<TD class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strForeignerSuppFlag" filter="false" /></TD>
			<TD class="LABEL" width="25%">Turn Over(Last Fin Yr.)</TD>
			<TD class="CONTROL" width="25%"><bean:write name="supplierBean"
				property="strTurnOverWithUnit" filter="false" /></TD>

		</TR>
		<tr>
			<td class="LABEL" width="25%">Supplier Status</td>
			<td class="CONTROL" width="25%" colspan="3"><bean:write
				name="supplierBean" property="strSupplierStatus" filter="false" /></td>

		</tr>
		
		<TR>
			<TD class="LABEL" colspan="2">Whether Supplier Provides Maintenance</TD>
			<TD class="CONTROL" colspan="2">
			<bean:write
				name="supplierBean" property="strSupplierProvMaintenanceStatus" filter="false" />			
			</TD>

		</TR>
		
		<TR>
			<TD class="LABEL" colspan="2">Whether Escalation Matrix Available</TD>
			<TD class="CONTROL" colspan="2">
			<bean:write
				name="supplierBean" property="strEsclationAvlStatus" filter="false" />			
			</TD>

		</TR>

	</table>
	
  
	      
		<div id="LevelOne" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr>
		 <td class="TITLE" colspan="4">
		      <bean:write
				name="supplierBean" property="strLevelTwoStatus" filter="false" />
				</td>
			<tr>
					<td class="LABEL" colspan="1" width="25%">Name</td>
					<td class="CONTROL" colspan="" width="25%">
				        <bean:write	name="supplierBean" property="strCotactPersonForEscLevelOne" filter="false" />
				        </td>
					<td class="LABEL" width="25%">Designation</td>
					<td class="CONTROL" width="25%">
					
					 <bean:write	name="supplierBean" property="strContactPersonDesgForEscLevelOne" filter="false" />
					
					</td>

		   </tr>
		   <tr>
					<td class="LABEL" colspan="1" width="25%">E-MailId</td>
					<td class="CONTROL" colspan="" width="25%">
					
				 <bean:write	name="supplierBean" property="strCotactEmailIdForEscLevelOne" filter="false" />
				
				</td>
					<td class="LABEL" width="25%">Phone No</td>
					<td class="CONTROL" width="25%">
					
				<bean:write	name="supplierBean" property="strCotactNoForEscLevelOne" filter="false" />
				
				</td>

		   </tr>
		   <tr>
					<td class="LABEL" colspan="1" width="25%">Fax No</td>
					<td class="CONTROL" colspan="" width="25%">
					
				<bean:write	name="supplierBean" property="strCotactFaxForEscLevelOne" filter="false" />
				
				</td>
					<td class="CONTROL" colspan="3"></td>

		   </tr>
		   
		   </table>
		 </div>
			
	
		<div id="LevelTwo" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		    <tr>
	
				<td class="TITLE" colspan="4"><bean:write
				name="supplierBean" property="strLevelTwoStatus" filter="false" />
				</td>
	
			</tr>
		
			<tr>
					<td class="LABEL" colspan="1" width="25%">Name</td>
					<td class="CONTROL" colspan="" width="25%">
				        <bean:write	name="supplierBean" property="strCotactPersonForEscLevelTwo" filter="false" />
				        </td>
					<td class="LABEL" width="25%">Designation</td>
					<td class="CONTROL" width="25%">
					
					 <bean:write	name="supplierBean" property="strContactPersonDesgForEscLevelTwo" filter="false" />
					
					</td>

		   </tr>
		   <tr>
					<td class="LABEL" colspan="1" width="25%">E-MailId</td>
					<td class="CONTROL" colspan="" width="25%">
					
				 <bean:write	name="supplierBean" property="strCotactEmailIdForEscLevelTwo" filter="false" />
				
				</td>
					<td class="LABEL" width="25%">Phone No</td>
					<td class="CONTROL" width="25%">
					
				<bean:write	name="supplierBean" property="strCotactNoForEscLevelTwo" filter="false" />
				
				</td>

		   </tr>
		   <tr>
					<td class="LABEL" colspan="1" width="25%">Fax No</td>
					<td class="CONTROL" colspan="1" width="25%">
					
				<bean:write	name="supplierBean" property="strCotactFaxForEscLevelTwo" filter="false" />
				
				</td>
					<td class="CONTROL" colspan="2"></td>

		   </tr>
		   
		   </table>
		</div>	
	

	
	<div id="IsSupplierBlackListedId" style="display: none">
	<table align="center" cellspacing="1px" cellpadding="1px"
		class="TABLEWIDTH">
		<tr>

			<td class="TITLE" colspan="4"></td>
		</tr>
		<tr>
			<td class="LABEL" colspan="1">Action Date</td>
			<td class="CONTROL" colspan="1"><bean:write name="supplierBean"
				property="strActionDate" /></td>

			<td class="LABEL" colspan="1">Committee Name</td>
			<td class="CONTROL" colspan="1"><bean:write name="supplierBean"
				property="strCommitee" filter="false" /></td>

		</tr>
		<tr>

			<td class="LABEL" width="25%">Remarks</td>
			<td class="CONTROL" colspan="3"><bean:write name="supplierBean"
				property="strBlackListedRemarks" filter="false" /></td>

		</tr>
	</table>
	</div>
	<table cellpadding="1px" cellspacing="1px" class="TABLEWIDTH"
		align="center">
		<tr>

			<td class="TITLE" colspan="4"></td>
		</tr>
		
		<tr>
			<td class="LABEL">Record Status</td>
			<td class="CONTROL"><bean:write name="supplierBean"
				property="strIsValid" /></td>

			<td class="LABEL" width="25%">Effective From</td>
			<td class="CONTROL" width="25%" colspan=""> <bean:write
				name="supplierBean" property="strEffectiveFrom" /> </td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Remarks</td>
			<td class="CONTROL" width="25%" colspan="3"><bean:write
				name="supplierBean" property="strRemarks" /></td>
		</tr>
	</table>
	<table cellpadding="1px" cellspacing="1px" class="TABLEWIDTH"
		align="center">
		<tr class="FOOTER">
			<td colspan="4" width="25%"></td>
		</tr>
	</table>
	<!-- <table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">

		<tr>


			<td align="center" colspan="4" width="25%"><img
				src="../../hisglobal/images/btn-ccl.png" onClick="closePopUp();"
				style="cursor: pointer;"></td>
		</tr>
	</table> -->
<br>
<div align="center" id="">					 
					 	<a href="#" class="button" onclick="closePopUp();"><span class="cancel">Cancel</span></a>
				</div>
	<input type="hidden" name="hmode" />
	<input type="hidden" value="${supplierBean.strChk}" name="strChk" />

<input type="hidden" value="${supplierBean.strSupplierStatus}"
		name="strSupplierStatus" />

	<input type="hidden" value="${supplierBean.strSupplierStatusCode}"
		name="strSupplierStatusCode" />
	<input type="hidden" value="${supplierBean.strIsBlackListMod}"
		name="strIsBlackListMod" />
	<input type="hidden" value="${supplierBean.strIsValid}"
		name="strIsValid" />
	<input type="hidden" value="${supplierBean.strIsSupplier}"
		name="IsSupplier" />
	<input type="hidden" value="${supplierBean.strIsManufacturer}"
		name="IsManufacturer" />
	<input type="hidden" value="${supplierBean.strIsAgent}" name="IsAgent" />
	<input type="hidden" value="${supplierBean.strIsBuyer}" name="IsBuyer" />
			<input type="hidden" value="${supplierBean.strLevelOneOpen}" name="strLevelOneOpen" />
	<input type="hidden" value="${supplierBean.strLevelTwoOpen}" name="strLevelTwoOpen" />
	<input type="hidden" value="${supplierBean.strForeignerSuppFlag}"
		name="IsForeignSupplier" />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>