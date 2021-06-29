<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>



<!--  
 
 										Developer : PAWAN KUMAR B N
 									    Created On: 19-09-2011
 										Module : Billing
 										Process: BILL SETUP Master
 										JSP URL : billing/masters/billing_ipdcompulsorycharges_BillSetup_mst.jsp

-->	






<html>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>

<head><meta charset=utf-8>
<title>IPD Compulsory Charges</title>

<script type="text/javascript">
	var delIndex;
	function getIndex(_these){
		var nTmpI;
		var strSelectedObjName=document.getElementsByName(_these.name)
		for(nTmpI=0;nTmpI<strSelectedObjName.length;nTmpI++)
			if(_these==strSelectedObjName[nTmpI])
				break;
		return nTmpI;
	}
	function setNewBornFlag(_these){
		if(_these.checked)
			document.getElementsByName("secNewBornCompCharge")[getIndex(_these)].value="1"
		else
			document.getElementsByName("secNewBornCompCharge")[getIndex(_these)].value="0"
	}
	
	function setTransferFlag(_these){
		if(_these.checked)
			document.getElementsByName("secImposedChargeForTransfer")[getIndex(_these)].value="1"
		else
			document.getElementsByName("secImposedChargeForTransfer")[getIndex(_these)].value="0"
	}
	
	function setMultiRow(){
		
		
		<logic:equal name="billsetupbean" property="strTotalRows" 
        value="${billsetupbean.ipdSecMultiRow}"></logic:equal>
        
        <logic:equal name="billsetupbean" property="strTotalRows"   value="${billsetupbean.ipdThirdMultiRow}"></logic:equal>
            
       
        var rowLength2 = ${billsetupbean.strTotalRows};
		var rowLength3 = ${billsetupbean.strTotal3Rows};
		
		var a=(document.getElementsByName("thirdWardName").length);
		var b=(document.getElementsByName("secWardName").length);
		
		 
		//alert(a+" "+b);
		 		
		document.multirow.rowLength2.value = b-1;
		document.multirow.rowIndex2.value = b-1;
		document.multirow.rowLength3.value = a-1;
		document.multirow.rowIndex3.value = a-1;
		
	}
	
	
	function myFunc(mode , comObj, delIdx){
	
	delIndex = delIdx;
	if(mode == 1){
		var hmode = "IPDTARIFFVALUES";
		var url = "CNTBillSetup.cnt?selectedTab="+hmode+"&grpName="+comObj.value;
		alert("Going to IPD TARIFF VALUES");
		ajaxFunction(url,"1");

	}else if(mode == 2){
		var hmode = "IPDSECTARIFFVALUES";
		var url = "CNTBillSetup.cnt?selectedTab="+hmode+"&grpName="+comObj.value;
		ajaxFunction(url,"2");
		
	}else if(mode == 3){
		var hmode = "IPDTHIRDTARIFFVALUES";
		var url = "CNTBillSetup.cnt?selectedTab="+hmode+"&grpName="+comObj.value;
		ajaxFunction(url,"3");
	}else if(mode == 4){
		var hmode = "IPDSECTARIFFVALUES";
		var url = "CNTBillSetup.cnt?selectedTab="+hmode+"&grpName="+comObj.value;
		ajaxFunction(url,"4");
		
	}
	
	
	}

	function getAjaxResponse(res,mode){
		
		if(mode == 1){
			var objEle = document.getElementById("tariffId1" + delIndex);
			objEle.innerHTML = "<select name='tariffName' id='tariffName" + delIndex + "'>"+res+"</select>";

		}else if(mode == 2){
			var objEle = document.getElementById("thirdTariff" + delIndex);
			objEle.innerHTML = "<select name='thirdTariffName' class='comboBig' id='thirdTariffName" + delIndex + "'>"+res+"</select>";
			
		}else if(mode == 3){
		
			var objEle = document.getElementById("tariffId3" + delIndex);
			objEle.innerHTML = "<select name='thirdTariffName' id='thirdTariffName" + delIndex + "' class='comboBig' >"+res+"</select>";
		}else if(mode == 4){
			var objEle = document.getElementById("secTariff" + delIndex);
			objEle.innerHTML = "<select name='secTariffName' class='comboBig' id='secTariffName" + delIndex + "'>"+res+"</select>";
			
		}
	}

	
	function validate1(){	
	
		var hisValidator = new HISValidator("billsetupbean");

			hisValidator.addValidation("groupName", "dontselect=0","Please Select Admitted Patient's Group Name");
			hisValidator.addValidation("tariffName", "dontselect=0","Please Select Admitted Patient's Tariff Name");
			
			hisValidator.addValidation("secWardName", "dontselect=0","Please Select Ward Name in Compulsory Charges");
			hisValidator.addValidation("secGroupName", "dontselect=0","Please Select Group Name in Compulsory Charges");
			hisValidator.addValidation("secTariffName", "dontselect=0","Please Select Tariff Name in Compulsory Charges");
			
			hisValidator.addValidation("thirdWardName", "dontselect=0","Please Select Ward Name in Extra Charges");
			hisValidator.addValidation("thirdGroupName", "dontselect=0","Please Select Group Name in Extra Charges");
			hisValidator.addValidation("thirdTariffName", "dontselect=0","Please Select Tariff Name in Extra Charges");
			
		var retVal = hisValidator.validate(); 
	
		if(retVal){
				 				
				document.forms[0].selectedTab.value = "IPDCOMPCHRG";
				document.forms[0].submit();
				
		}else{
		
			return false;
		}
		
	}
	
	function initPage(){
	 	document.forms[0].selectedTab.value="INITIALPAGE";
		document.forms[0].submit();
	 }
	
	</script>

</head>

<body onLoad="setMultiRow();">
<html:form action="/masters/CNTBillSetup.cnt" method="post" >



<div class="errMsg"><bean:write name="billsetupbean"
	property="strErr" /></div>
<div class="normalMsg" id='normalMsg'><bean:write
	name="billsetupbean" property="strMsg" /></div>

<tag:tab tabList="${billsetupbean.lhm}" selectedTab="ipd" align="center"
	width="TABLEWIDTH"></tag:tab> <tag:tab tabList="${billsetupbean.lhm1}"
	selectedTab="compulsoryCharge" align="center" width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr class="HEADER">
		<td colspan="4">Compulsory Charges Screen</td>
	</tr>
</table>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="TITLE" colspan="4" height="22">Compulsory 
		Charges</td>
	</tr>
</table>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td width="15%" class="multiLabel">Ward Type</td>
		<td width="30%" class="multiLabel">Group Name</td>
		<td width="50%" class="multiLabel">Tariff Name</td>
		<td width="5%" class="multiLabel"><img
			src="../../hisglobal/images/plus.gif"
			onClick="addRows(new Array('secWardName','secGroupName','secTariffName'),new Array('s','s','s'),'2','1','R');"></td>
	</tr>
</table>
<div id="id2">${billsetupbean.ipdSecMultiRow}</div>
<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr class="FOOTER">
		<td colspan="4" height="22"></td>
	</tr>
</table>
<!-- compulsory charges end -->

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="TITLE" colspan="4" height="22">Special
		Charges</td>
	</tr>
</table>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td width="15%" class="multiLabel">Ward Type</td>
		<td width="30%" class="multiLabel">Group Name</td>
		<td width="50%" class="multiLabel">Tariff Name</td>
		<td width="5%" class="multiLabel"><img
			src="../../hisglobal/images/plus.gif"
			onClick="addRows(new Array('thirdWardName','thirdGroupName','thirdTariffName'),new Array('s','s','s'),'3','1','R');"></td>
	</tr>
</table>

<div id="id3">${billsetupbean.ipdThirdMultiRow}</div>
<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr class="FOOTER">
		<td colspan="4" height="22"></td>
	</tr>
</table>

<table border="0" class="TABLEWIDTH" align="center">
	<tr>

		<td align="right"><img src="../../hisglobal/images/btn-sv.png"
			style="cursor: pointer; cursor: hand;" title="Save Record"
			onClick="return validate1();" /></td>
		<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
			onClick="cancelFunc();" style="cursor: pointer; cursor: hand;"
			title="Cancel the Process" /></td>
	</tr>
</table>

<input type="hidden" name="selectedTab">


</html:form>
<jsp:include page="billing_multirow_ipdcompulsorycharges_BillSetup_mst.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
<cmbPers:cmbPers />
</html>