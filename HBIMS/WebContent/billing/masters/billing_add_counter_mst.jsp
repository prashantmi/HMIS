<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Counter Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<style type="text/css">@import url(../../hisglobal/css/calendar-tas.css);</style>

<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript">
function submitData(mode)
{
	 
	var hisValidator = new HISValidator("counterBean");
	hisValidator.addValidation("counterName","req","Counter Name is a Mandatory  Field");
	hisValidator.addValidation("counterName","maxlen=35","Counter name should not be greater than 35 characters");
	hisValidator.addValidation("ipAddress","req","I.P. Address is a Mandatory  Field");
	hisValidator.addValidation("strLocation","req","Location is a Mandatory  Field");
	//hisValidator.addValidation("strBuildingId","dontselect=0","Please select a building name");
	//hisValidator.addValidation("strBlockId","dontselect=0","Please select a block name");
	//hisValidator.addValidation("strFloorId","dontselect=0","Please select a floor name");
	
	hisValidator.addValidation("effectiveFrm","date","Please Select Effective From Date");
	hisValidator.addValidation("effectiveFrm","dtgtet="+"${counterBean.strCrntDate}","Effective From Date should be greater than Or equal to Current Date.");
	hisValidator.addValidation("remarks","maxlen=50","Remarks should not be greater than 50 characters");
	var retVal = hisValidator.validate();
	if(retVal){		
		
		if(isValidIPAddress(document.counterBean.ipAddress.value)){			
			document.counterBean.hmode.value=mode;		
			document.counterBean.submit();
		} else {
			alert("Please Enter Valid I.P. Address eg: 255.255.255.255");
			document.counterBean.ipAddress.focus();
			return false;
		}
	
	} else{		
		return false;
	}	
}


function isValidIPAddress(ipaddr) {
   var re = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
   if (re.test(ipaddr)) {
      var parts = ipaddr.split(".");
      if (parseInt(parseFloat(parts[0])) == 0) { return false; }
      for (var i=0; i<parts.length; i++) {
         if (parseInt(parseFloat(parts[i])) > 255) { return false; }
      }
      return true;
   } else {
      return false;
   }
}


	function combo1(mode)
	{ 

	
	 var url;
	 var a="BLOCKNAME";
	 url="CNTcounterMst.cnt?hmode="+a+"&buildingName="+document.forms[0].strBuildingId.value;
	 if(mode=="BLOCKNAME"){
		document.forms[0].strFloorId.value='0';
	   url="CNTcounterMst.cnt?hmode="+mode+"&buildingName="+document.forms[0].strBuildingId.value;
	 ajaxFunction(url,"1");
	}
	else if(mode=="FLOORNAME"){
	var blockId = document.forms[0].strBlockId.value; 
	url="CNTcounterMst.cnt?hmode="+mode+"&buildingName="+document.forms[0].strBuildingId.value+"&blockName="+blockId;

	ajaxFunction(url,"2");
	}
	}
	
	function getAjaxResponse(res,mode)
	
	{
	
	var objVal;
	if(mode=="1"){   
	
	objVal= document.getElementById("BlockId");
	objVal.innerHTML = "<select name ='strBlockId' onChange ='combo1(\"FLOORNAME\");'>" + res + "</select>";
	}
	else if(mode=="2"){
	var objVal = document.getElementById("FloorId");
	
	objVal.innerHTML = "<select name='strFloorId'>" + res + "</select>";
	
	}
	}
	function firstFocus()
	{
	document.forms[0].counterName.focus();
	}

	
</script>
</head>

<body onload="firstFocus();">
<html:form name="counterBean" action="/masters/CNTcounterMst.cnt"
	type="billing.masters.controller.fb.CounterMstFB">
	<div class="errMsg" id="errMsg"><bean:write name="counterBean" property="errmsg"/></div>
	<div class="warningMsg" id="warningMsg"><bean:write name="counterBean" property="warningMsg"/></div>
	<div class="normalMsg" id="normalMsg" ><bean:write name="counterBean" property="strMsg"/></div>
	
	<tag:tab tabLabel="Add Counter" align="center" 
		width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Counter Master&gt;&gt;Add</td>
		</tr>
		
		<tr>
			<td class="LABEL"><font color="red">*</font>Counter Name</td>
			<td class="CONTROL"><input type="text" class="txtFldMax" name="counterName" maxlength="35" value="" onkeypress="return validateData(event,9);"></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>I P Address</td>
			<td class="CONTROL"><input type="text" class="txtFldMax" name="ipAddress" value=""
				maxlength="15" onkeypress="return validateData(event,7);"></td>
		</tr>
		<tr class="HEADER">
			<td colspan="2">Location</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Location</td>
			 <td width="50%" class ="CONTROL">
			 <textarea name="strLocation" rows="2"></textarea>
      </td>
		</tr>
						
		<tr class="HEADER">
			<td colspan="2"></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Effective From</td>
			<td class="CONTROL"><dateTag:date name="effectiveFrm"
				value="${counterBean.strCrntDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><textarea name="remarks" rows="2"></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font color="red">* </font>Mandatory Field</td>
		</tr>
	</table>
	<div align="center">
	<!-- <img style="cursor:pointer;cursor:hand" title="To Save Record" src="../../hisglobal/images/btn-sv.png" onClick="return submitData('SAVEADD');" />
	<img style="cursor:pointer;cursor:hand" title="To Clear the fields" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset(),document.forms[0].counterName.focus();" />
	<img style="cursor:pointer;cursor:hand" title="To Cancel and return to List Page" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" />
	-->
						<br><a href="#" class="button" id="" onclick=" return submitData('SAVEADD');"><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset(),document.forms[0].counterName.focus();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	</div>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="strCrntDate" value="${counterBean.strCrntDate}" />
	<input type="hidden" name="moduleId" value="${counterBean.moduleId}" />
	<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>

