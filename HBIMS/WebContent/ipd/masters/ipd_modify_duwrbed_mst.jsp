
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>DUWR Bed Master Modify Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>

<script type="text/javascript">

function validate1(){

		var retVal;
	
		var hisValidator = new HISValidator("duwrbedBean");
			//hisValidator.addValidation("strEffectiveFrom", "dtgtet=${duwrbedBean.strEffectiveFrom}", "Effective Date should be Greater than Previous Effective Date." );
			//hisValidator.addValidation("strEffectiveFrom", "dtgtet=${duwrbedBean.strCtDate}","Effective From Date should be Greater than or Equal to Current Date");	
			hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
			
			
		retVal = hisValidator.validate(); 
		
			if(retVal){
				
				document.forms[0].hmode.value = "UPDATE";
				document.forms[0].submit();
			}else{
		
			return false;
			}
}

</script>

</head>
<body onload="document.forms[0].strDeptUnitName.focus();">
<html:form action="/masters/CNTDUWRBedMst.cnt">
	<div class="errMsg"><bean:write name="duwrbedBean"
		property="strErr" /></div>
	<div class="warningMsg"><bean:write name="duwrbedBean"
		property="strWarning" /></div>
	<div class="normalMsg" id="normalMsg"></div>

	<tag:tab tabLabel="Modify DUWR Bed" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="3">DUWR Bed &gt;&gt; Modify</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Dept/Unit Name</td>
			<td width="50%" colspan="2" class="CONTROL"><bean:write
				name="duwrbedBean" property="strDeptUnitName" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Ward Name</td>
			<td width="50%" colspan="2" class="CONTROL"><bean:write
				name="duwrbedBean" property="strWardName" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Room Name</td>
			<td width="50%" colspan="2" class="CONTROL"><bean:write
				name="duwrbedBean" property="strRoomName" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Bed Name</td>
			<td width="50%" colspan="2" class="CONTROL"><bean:write
				name="duwrbedBean" property="strBedName" /></td>
		</tr>

		<tr>
			<td class="LABEL">Effective From</td>
			<td class="CONTROL" colspan="2"><bean:write name="duwrbedBean"
				property="strEffectiveFrom" filter="false" /></td>
		</tr>

		<tr>
			<td width="50%" class="LABEL">Remarks</td>
			<td width="50%" colspan="2" class="CONTROL"><textarea
				onkeyup="lTrim(this);" onblur="Trim(this);" rows="2" cols="20"
				name="strRemarks"><bean:write name="duwrbedBean"
				property="strRemarks" /></textarea></td>
		</tr>
		<tr>
			<td class="LABEL">Record Status</td>
			<td class="CONTROL" colspan="2"><html:radio name="duwrbedBean"
				property="strIsValid" value="1">Active</html:radio> <html:radio
				name="duwrbedBean" property="strIsValid" value="2">InActive</html:radio>
			</td>
		</tr>

		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<!-- <td align="right"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer" onClick=" return validate1();" /></td>
			<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer" onClick="cancel('LIST');" />
				</td> -->
				
		<td align="right">
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
		</td>
		<td align="left">
		<br><a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
		</td>
		</tr>
	</table>




	<input type="hidden" name="hmode" />
	<input type="hidden" name="chk" value="${duwrbedBean.chk[0]}" />
	<input type="hidden" name="strEffectiveFrom" value="${duwrbedBean.strEffectiveFrom}" />
	<cmbPers:cmbPers />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>