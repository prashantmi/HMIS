<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control", "no-cache"); %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<!--  
 
 										Developer : PAWAN KUMAR B N
 									    Created On: 19-09-2011
 										Module : Billing
 										Process: BILL SETUP Master
 										JSP URL : billing/masters/billing_opd_billsetup_mst.jsp

-->	






<html>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="/HBIMS/hisglobal/js/util.js"></script>

<head><meta charset=utf-8>
<title>OPD</title>

<script type="text/javascript">
	
	function validate1(){	

	 	var hisValidator = new HISValidator("billsetupbean");
	 
		if(document.billsetupbean.opdRefundPenalty.value !=""){
		 hisValidator.addValidation("opdRefundPenalty", "amount=4,1","Please Enter a Valid Penalty Percentage ");
			hisValidator.addValidation("opdRefundPenalty", "numltet=100","Penalty Percentage Should be Less than or Equal to 100");
		}
					
		var retVal = hisValidator.validate(); 
		
		
		if(retVal){
				document.forms[0].selectedTab.value = "OPDINSERT";
				document.forms[0].submit();
		}else{
		
		return false;
		}
	}
	
	function checkClick(id)
	{
		if(document.forms[0].opdDiscount.checked)
		{
			
			document.getElementById(id).style.display="block";
		}
		else 
		{
			document.getElementById(id).style.display="none";
		}
	}
	
	function initPage(){
	 	document.forms[0].selectedTab.value="INITIALPAGE";
		document.forms[0].submit();
	 }
	
	</script>


</head>

<body>

<html:form action="/masters/CNTBillSetup.cnt" method="post" >

<div class="errMsg"><bean:write name="billsetupbean" property="strErr"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="billsetupbean" property="strMsg"/></div>
 
 <tag:tab tabList="${billsetupbean.lhm}" selectedTab="opd" align="center"
	width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH">

	<tr class="HEADER">
		<td colspan="2" height="22">Billing OPD Screen</td>
	</tr>

	<tr>
		<td class="LABEL">Whether Third Party Billing Allowed</td>
		<td class="CONTROL"><html:radio name="billsetupbean" property="emgThirdPartyBilling" value="1" />Yes
<html:radio name="billsetupbean" property="emgThirdPartyBilling" value="0" />No
</td>
	</tr>

	<tr>
		<td  colspan="2" class="TITLE"><html:checkbox name="billsetupbean" property="opdDiscount" onclick="checkClick('disApplicable')" value="1" />Whether Discount is applicable
		</td>
		</tr>

	<tr>
		<td colspan="2">
		<logic:equal name="billsetupbean" property="opdDiscount"  value="1">
		<div id="disApplicable" style="display:block"> 
		<table width="100%" border="0">
			<tr>
				<td class="LABEL">
                Whether Provision To Enter Discount By The Billing Clerk</td>
				<td class="CONTROL"><html:radio name="billsetupbean" property="opdDiscountClerk" value="1" />Yes
<html:radio name="billsetupbean" property="opdDiscountClerk" value="0" />No</td>
			</tr>

		</table>
		</div>
		</logic:equal>
		<logic:notEqual name="billsetupbean" property="opdDiscount"  value="1">
		<div id="disApplicable" style="display:none"> 
		<table width="100%" border="0">
			<tr>
				<td class="LABEL">
                Whether Provision To Enter Discount By The Billing Clerk</td>
				<td class="CONTROL"><html:radio name="billsetupbean" property="opdDiscountClerk" value="1" />Yes
<html:radio name="billsetupbean" property="opdDiscountClerk" value="0" />No</td>
			</tr>

		</table>
		</div>
		</logic:notEqual>
		</td>
	</tr>

	<tr>
		<td class="LABEL">Penalty(in %)if patient demands for refund
		without Approval</td>
		<td class="CONTROL"><input type="text" name="opdRefundPenalty"
			class="txtFldNormal" value="${billsetupbean.opdRefundPenalty }" maxlength="4" onkeypress="return validateData(event,7);"> </td>
	</tr>

	<tr>
		
      <td class="LABEL">Required Round Off</td>
		<td class="CONTROL"><html:checkbox name="billsetupbean" property="opdRoundOff" value="1" /></td>
	</tr>
<tr>
		<td class="LABEL">Service Tax</td>
		<td class="CONTROL">
		
		<html:radio name="billsetupbean" property="opdServiceTax" value="0">No</html:radio>
			<html:radio name="billsetupbean" property="opdServiceTax" value="1">Yes</html:radio>
		 </td>
	</tr>
	<tr>
		<td class="LABEL">Free Category</td>
		<td class="CONTROL"><html:select name="billsetupbean" property="opdFreeCategory" >
			<bean:write property="strOpdFreeCategoryValue" name="billsetupbean"
				filter="false" />
		</html:select></td>
	</tr>
	<tr class="FOOTER">
		<td colspan="4" align="right"><font size="2" color="red">*</font>
		Mandatory Fields</td>

	</tr>

</table>

<table border="0" class="TABLEWIDTH" align="center">
		<tr>
             
			<!-- <td align="right">
			<img
				src="../../hisglobal/images/btn-sv.png" title="Save Record" style="cursor:pointer;cursor:hand;" onClick="return validate1();" />
			
			</td>
			<td align="left">
			<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel the Process"
				onClick="cancelFunc();" />
			</td>
			-->
			<br>
			<td align="right">
				<!-- <img src="/HBIMS/hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record" onClick="return validate1();" /> -->	
				<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>		
			</td>
			<td align="left">
				<!-- <img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel the Process" onClick="cancelFunc();" /> -->
				<a href="#" class="button"	onclick="cancelFunc();"><span class="cancel">Cancel</span></a> 
			</td>
		</tr>
	</table>
<input type="hidden" name="selectedTab">

</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>

</html>