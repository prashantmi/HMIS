<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<title>Auction Desk</title>
<his:css src="../../../hisglobal/css/Color.css" />
<his:css src="../../../hisglobal/css/master.css" />
<his:css src="../../hisglobal/css/hisStyle.css" />
<his:css src="../../hisglobal/css/hisStyleExt.css" />
<his:css src="../../hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript"
	src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<!-- <script 

language="Javascript"src="../../bmed/js/complaintHierarchyMst.js"></script

> -->
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../bmed/js/bmed_add_committee.js"></script>
<script language="Javascript" src="../../bmed/transactions/js/bmed_equip_condemnation_auction_desk.js"></script>
<his:javascript src="/hisglobal/js/multirow.js" />

<!-- 
/**
 * BY shefali
 * Date of Creation : 07/11/2013
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : Rajasthan
 */
 -->
<script type="text/javascript"><!--
 function validate()
{   

          
          if(true)
          {
                 	   document.forms[0].hmode.value = "SaveAuctionDtl";
                       document.forms[0].submit();
          }
          else
          {
             return false;
          }
}
 function getValueSelected(index)
 {
  if (document.getElementById("strComMemberName"+index).value=="0")
  {
   alert("please select a Emp Name");
  }
 }
 
 function hidetable1()
{
var table1 = document.getElementById("table1");
if(table1.style.display == "table")
table1.style.display="none";
else
table1.style.display="table";
}

function disableTypeDetail()
{
if( document.getElementById("strInstrumentType").value=="1" || document.getElementById("strInstrumentType").value=="2" )
{
document.getElementById("strInstrumentDetail").value="";
document.getElementById("strInstrumentDetail").disabled = true;
}

if( document.getElementById("strInstrumentType").value=="3")
{
 document.getElementById("strInstrumentDetail").disabled =false;
}
}
--></script>


</head>
<body onload="chkRecordSaved()">
<html:form action="/transactions/EquipmentAuctionAndCondemnationDeskCNT"
	name="stCommitteDesk"
	type="bmed.transactions.controller.fb.EquipmentAuctionAndCondemnationDeskFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="EquipmentAuctionAndCondemnationDeskFB" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="EquipmentAuctionAndCondemnationDeskFB" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg" style="display: none;"><bean:write
		name="EquipmentAuctionAndCondemnationDeskFB" property="strMsgString" /></div>
	<tag:tab tabLabel="Auction Desk" selectedTab="FIRST"
		align="center" width="TABLEWIDTH">
	</tag:tab></center>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Equipment Detail &gt;&gt;</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Hospital
			Name</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strStoreId}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Name</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipName}
			</td>
		</tr>
		<tr>
			<td colspan="" class="LABEL" style="text-align: right;">Equipment
			Model</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Group
			Name</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipGroup}
			</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Sub
			Group Name</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipSubGroup}</td>
		</tr>
		<tr class="HEADER">
			<td colspan="4">Inventory Detail &gt;&gt;</td>
		</tr>


		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Serial No</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strBatchNo}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">UIN</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strUidNo}</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Manufacturer
			Name</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strSupplierName}
			</td>

			<td colspan="1" class="LABEL" style="text-align: right;">Status</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strStatus}
			</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Recommendation Detail &gt;&gt;</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color='red'>*</font>Committee Name</td>
			<td colspan="2" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strCommitteeName}
			</td>
			
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color='red'>*</font>Recommendation Date</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strRecommendationDate}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color='red'>*</font>Recommendation Remarks</td>
			<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strRecommendationRemarks}
			</td>
		</tr>

	</table>
	<div id="committeedtl"></div>
	<table  class="TABLEWIDTH" align="center" width="100%">
	<tr class="HEADER">
		<td colspan="4">Auction Details &gt;&gt;</td>
		</tr>
		<tr>
		<td colspan="1" class="LABEL" style="text-align: right;">Auction ID:</td><td colspan="1" class="CONTROL" style="text-align:left;"><input type="text" name="strAuctionId" id="strAuctionId"></td>
		
		<td colspan="1" class="LABEL" style="text-align: right;">Date:</td><td colspan="1" class="CONTROL" style="text-align:left;"><dateTag:date name="strAuctionDate" id="strAuctionDate"></dateTag:date></td>
		</tr>
			<tr>
		<td colspan="1" class="LABEL" style="text-align: right;">Auction Amount:</td><td colspan="1" class="CONTROL" style="text-align:left;"><input type="text" name="strAuctionAmount" id="strAuctionAmount"></td>
		
		<td colspan="1" class="LABEL" style="text-align: right;">Auction To:</td><td colspan="1" class="CONTROL" style="text-align:left;"><input type="text" name="strAuctionTo" id="strAuctionTo"></td>
		</tr>
		<tr>
	  <td colspan="1" class="LABEL" style="text-align: right;">AuctionBy:</td><td colspan="1" class="CONTROL" style="text-align: left"><select name="strAuctionUser" id="strAuctionUser">
		<bean:write name="EquipmentAuctionAndCondemnationDeskFB" property="strApprovalUserCmb"  filter="false"/>
		</select>
		</td>
	 <td colspan="1" class="LABEL" style="text-align: right;">Auction Remarks :</td><td colspan="1" class="CONTROL" style="text-align: left"><input type="text" name="strAuctionRemarks" id="strAuctionRemarks">
		
		</tr>
		<tr>
		 <td colspan="1" class="LABEL" style="text-align: right;">Instrument Type:</td><td colspan="1" class="CONTROL" style="text-align: left"><select name="strInstrumentType" id="strInstrumentType" onchange="disableTypeDetail();">
		<option value="0">Select Value</option>
		<option value="1">DD</option>
		<option value="2">Cheque</option>
	    <option value="3">Cash</option>
		
		</select>
		</td>
		<td colspan="1" class="LABEL" style="text-align: right;">Instrument Detail :</td><td colspan="1" class="CONTROL" style="text-align: left"><input type="text" name="strInstrumentDetail" id="strInstrumentDetail" >
		</tr>
		<tr>
		<td colspan="1" class="LABEL" style="text-align: right;">Instrument Date:</td><td colspan="1" class="CONTROL" style="text-align:left;"><dateTag:date name="strInstrumentDate" id="strInstrumentDate"></dateTag:date></td>
		
		</tr>
	</table>
    	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px" border="0">
		<tr>
			<td  class="CONTROL"  style="text-align: center"colspan="2" width="25%"><br>
			<img src="../../hisglobal/images/btn-sv.png" title="Save Record"
				onClick="validate();" style="cursor: pointer;" /> <img
				src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer;"
				title="Cancel Process" onClick="cancel('LIST');" /></td>
		</tr> 
		<tr class="HEADER">
		<td colspan="4" align="right"><font color="red">*</font>Mandatory</td>
		</tr>
	</table>
	<html:hidden property="hmode" styleId="hmode" />
	<input type="hidden" name="strEquipModel" id="strEquipModel" value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
<input type="hidden" name="strEquipGroup" id="strEquipGroup" value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
<input type="hidden" name="strEquipSubGroup" id="strEquipSubGroup" value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
<input type="hidden" name="strBatchNo" id="strBatchNo" value="${EquipmentAuctionAndCondemnationDeskFB.strBatchNo}">
<input type="hidden" name="strUidNo" id="strUidNo" value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
<input type="hidden" name="strHospitalId" id="strHospitalId" value="${EquipmentAuctionAndCondemnationDeskFB.strHospitalId}">

<input type="hidden" name="strItemId" id="strItemId" value="${EquipmentAuctionAndCondemnationDeskFB.strItemId}">
 
<input type="hidden" name="strItemBrandId" id="strItemBrandId" value="${EquipmentAuctionAndCondemnationDeskFB.strItemBrandId }">

<input type="hidden" name="strItemGroupId" id="strItemGroupId" value="${EquipmentAuctionAndCondemnationDeskFB.strItemGroupId }">

<input type="hidden" name="strItemSubGroupId" id="strItemSubGroupId" value="${EquipmentAuctionAndCondemnationDeskFB.strItemSubGroupId}">
<input type="hidden" name="strsupplierId" id="strsupplierId" value="${EquipmentAuctionAndCondemnationDeskFB.strsupplierId}">
<input type="hidden" name="strHospitalId" id="strHospitalId" value="${EquipmentAuctionAndCondemnationDeskFB.strHospitalId}">
<input type="hidden" name="strLabID" id="strLabID" value="${EquipmentAuctionAndCondemnationDeskFB.strLabID}">
<input type="hidden" name="strCommitteeId" id="strCommitteeId" value="${EquipmentAuctionAndCondemnationDeskFB.strCommitteeId}"/>
<input type="hidden" name="strIndentificationNo" id="strIndentificationNo" value="${EquipmentAuctionAndCondemnationDeskFB.strIndentificationNo}"/>
<input type="hidden" name="strMsgString" id="strMsgString" value="${EquipmentAuctionAndCondemnationDeskFB.strMsgString}">
<cmbPers:cmbPers />
</html:form>

</body>
</html>

