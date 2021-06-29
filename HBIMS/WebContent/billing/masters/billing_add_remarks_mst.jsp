<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head><meta charset=utf-8>
<title>Global Remarks Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>


<script type="text/javascript">

	function validate1()
	{	
	//alert("inside validate1");
	
	var hisValidator = new HISValidator("remarkBean");
		//alert("Objec :"+hisValidator);
		hisValidator.addValidation("strRemarksDesc", "req","Remarks Description is a Mandatory Field");
		hisValidator.addValidation("strRemarksDesc","maxlen=50","Remarks Description should have less than or equal to 50 Characters" );
		hisValidator.addValidation("strRemarksType", "dontselect=0","Please select a value from Remarks For Combo");
		hisValidator.addValidation("strEffectiveFrom", "req","Effective Date is a Mandatory Field");
		 hisValidator.addValidation("strEffectiveFrom", "date" , "Effective From should be a Valid Date");
	     hisValidator.addValidation("strEffectiveFrom", "dtgtet=${remarkBean.strCtDate}" , "Effective From should be Greater than or Equal to Current Date");
		 hisValidator.addValidation("strRemarksFor", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
		
		var retVal = hisValidator.validate(); 
	//	alert(retVal);
		
		if(retVal){
				document.forms[0].hmode.value = "INSERT";
				//alert("inside if");
				document.forms[0].submit();
		}else{
		
		return false;
		}
		
	}
	
	function firstFocus()
	{
	document.forms[0].counterName.focus();
	}

	function cancelPage()
	{
	
	document.forms[0].hmode.value='LIST';
	document.forms[0].submit();
	}
	
</script>

</head>
<body onLoad="document.forms[0].strRemarksDesc.focus()">
<html:form action="/masters/CNTRemarksMst.cnt"
	type="billing.masters.controller.fb.GlobalRemarksMstFB" name="remarkBean">
	
	
	<div class="errMsg"><bean:write name="remarkBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="remarkBean" property="strWarning"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="remarkBean" property="strMsg"/></div>
	
		<center>
	<tag:tab tabLabel="Add Global Remarks" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	</center>
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
	<tr class="HEADER">
			<td colspan="2">Global Remarks Master&gt;&gt; Add</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			<font color="red">*</font>Remarks Description
			</td>
			<td width="50%" class="CONTROL">
			<textarea name="strRemarksDesc" cols="20"
				rows="2"></textarea>
			</td>
		</tr>
		<tr>
			<td class="LABEL">
			<font color="red">*</font>Remarks For
			</td>
			<td class="CONTROL">
			<select name="strRemarksType" class="comboNormal">
				<option value="0" selected>Select Value</option>
				<option value="1">Discount</option>
				<option value="2">Cancellation</option>
				<option value="3">Refund</option>
				<option value="4">Miscellaneous</option>
			</select>
			</td>
		</tr>
		<tr>
			<td class="LABEL">
			<font color="red">*</font>Effective From
			</td>
			<td class="CONTROL">
			 <dateTag:date name="strEffectiveFrom" id="effDateId"
			 
				 value= "${remarkBean.strCrntDate}" />
				 
			
			
			</td>
		</tr>
		<tr>
			<td class="LABEL">
			Remarks(if any)
			</td>
			<td class="CONTROL">
			<textarea name="strRemarksFor" cols="20" rows="2" 
				id="remarks"></textarea>
			</td>
		</tr>
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	 <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" title="To Save Record" name="save" id="save"  src="../../hisglobal/images/btn-sv.png" onclick="return validate1();">
			<img style="cursor:pointer;cursor:hand" title="To Clear the fields" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset(),document.forms[0].strRemarksDesc.focus();" />
			<img style="cursor:pointer;cursor:hand" title="To Cancel and return to List Page"  name="cancel"   src="../../hisglobal/images/btn-ccl.png" onclick="cancelPage();">
			 -->
			
			<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset(),document.forms[0].strgroupName.focus();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelPage();"><span class="cancel">Cancel</span></a>	
			</td>
		</tr>
	</table>

<input type="hidden" name="hmode"/>
<input type="hidden" name="strCrntDate" value="${remarkBean.strCrntDate}" />
<input type="hidden" name="strHospitalCode" value ="${remarkBean.strHospitalCode}" />

<cmbPers:cmbPers></cmbPers:cmbPers>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>