<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% response.setHeader("Cache-Control", "no-cache"); %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag" %>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
	
<!--  
 
Developer : PAWAN KUMAR B N
Created On: 19-09-2011
Module : Billing
Process: BILL SETUP Master
JSP URL : billing/masters/billing_general_BillSetup_mst.jsp
Modified On: 02-01-2015
Purpose: Removed Consumables Charges Logic and added credit bill approval flag
Modified By: Amit Kumar Ateria
-->	
	
	
<html>
	
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">	
<link href="/HBIMS/billing/css/master.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
	
<script language="JavaScript" src="/HBIMS/hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="/HBIMS/hisglobal/js/tab.js"></script>
<script language="JavaScript" src="/HBIMS/hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="/HBIMS/hisglobal/js/util.js"></script>
<script language="Javascript" src="/HBIMS/hisglobal/js/validation.js"></script>

<head><meta charset=utf-8>
<title>Billing Config-Surcharge</title>
	
	<script type="text/javascript">
	
	function validate1()
	{		
		var hisValidator = new HISValidator("billsetupbean");
		
		hisValidator.addValidation("strSurCc", "req","Credit Card Surcharge(Upto Rs. 2000) is a Mandatory Field");
		hisValidator.addValidation("strSurDc", "req","Debit Card Surcharge(Upto Rs. 2000) is a Mandatory Field");
		hisValidator.addValidation("strSurIc", "req","International Credit Card Surcharge(Upto Rs. 2000) is a Mandatory Field");
		hisValidator.addValidation("strSurId", "req","International Debit Card Surcharge(Upto Rs. 2000) is a Mandatory Field");
		hisValidator.addValidation("strSurCc1", "req","Credit Card Surcharge(Above Rs. 2000) is a Mandatory Field");
		hisValidator.addValidation("strSurDc1", "req","Debit Card Surcharge(Above Rs. 2000) is a Mandatory Field");
		hisValidator.addValidation("strSurIc1", "req","International Credit Card Surcharge(Above Rs. 2000) is a Mandatory Field");
		hisValidator.addValidation("strSurId1", "req","International Debit Card Surcharge(Above Rs. 2000) is a Mandatory Field");
		
		
		
		
		var retVal = hisValidator.validate(); 
		
		
	
		if(retVal)
		{	
			
				document.forms[0].selectedTab.value = "SURINSERT";
				document.forms[0].submit();
		}
		else
		{
			return false;
		}
	}	
	function initPage()
	{
	 	document.forms[0].selectedTab.value="INITIALPAGE";
		document.forms[0].submit();
	}
	

</script>
	
	</head>
	
	
	<body>
<html:form action="/masters/CNTBillSetup.cnt" method="post" >
	
	<%
		//java.util.LinkedHashMap<String,String> lhm	=	new java.util.LinkedHashMap<String,String>();
		//lhm.put("General","general");
		//lhm.put("Opd","opd");
		//lhm.put("Ipd","ipd");
		//lhm.put("Emergency","emergency");
		//lhm.put("Bill Format","billFormat");
		//lhm.put("Jobs","jobs");
	%>
	<div class="errMsg"><bean:write name="billsetupbean" property="strErr"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="billsetupbean" property="strMsg"/></div>
	
	<tag:tab tabList="${billsetupbean.lhm}" selectedTab="surcharge" align="center" width="TABLEWIDTH"></tag:tab>
	
	   
  <table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
    <tr class="HEADER"> 
      <td colspan="2" height="22">Surcharge Screen </td>
    </tr>
    
    <tr>
    	<td class="LABEL" width="50%"><font color="red">*</font>Credit Card Surcharge(Upto Rs. ${billsetupbean.defsurlim} )</td>
    	<td class="CONTROL" width="50%">
    	<input type="text" name="strSurCc" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strSurCc}" >%
        </td>
    </tr>
    <tr>
    	<td class="LABEL"><font color="red">*</font>Credit Card Surcharge(Above Rs. ${billsetupbean.defsurlim})</td>
    	<td class="CONTROL">
    	<input type="text" name="strSurCc1" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strSurCc1}" >%
        </td>
    </tr>
    <tr>
    	<td class="LABEL"><font color="red">*</font>Debit Card Surcharge(Upto Rs. ${billsetupbean.defsurlim})</td>
    	<td class="CONTROL">
    	<input type="text" name="strSurDc" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strSurDc}" >%
        </td>
    </tr>
    <tr>
    	<td class="LABEL"><font color="red">*</font>Debit Card Surcharge(Above Rs. ${billsetupbean.defsurlim})</td>
    	<td class="CONTROL">
    	<input type="text" name="strSurDc1" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strSurDc1}" >%
        </td>
    </tr>
    <tr>
    	<td class="LABEL"><font color="red">*</font>International Credit Card Surcharge(Upto Rs. ${billsetupbean.defsurlim})</td>
    	<td class="CONTROL">
    	<input type="text" name="strSurIc" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strSurIc}" >%
        </td>
    </tr>
     <tr>
    	<td class="LABEL"><font color="red">*</font>International Credit Card Surcharge(Above Rs. ${billsetupbean.defsurlim})</td>
    	<td class="CONTROL">
    	<input type="text" name="strSurIc1" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strSurIc1}" >%
        </td>
    </tr>
    <tr>
    	<td class="LABEL"><font color="red">*</font>International Debit Card Surcharge(Upto Rs. ${billsetupbean.defsurlim})</td>
    	<td class="CONTROL">
    	<input type="text" name="strSurId" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strSurId}" >%
        </td>
    </tr>
    <tr>
    	<td class="LABEL"><font color="red">*</font>International Debit Card Surcharge(Above Rs. ${billsetupbean.defsurlim})</td>
    	<td class="CONTROL">
    	<input type="text" name="strSurId1" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strSurId1}" >%
        </td>
    </tr>
    </table>
    
    
    <table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
    	<tr class="FOOTER"> 
      		<td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields </tr>
  	</table>
	
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
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
		
	<input 	type="hidden" name="selectedTab" >
	<input type="hidden" name="defsurlim" value="${billsetupbean.defsurlim}" />
	
	
	</html:form>
	<tag:autoIndex></tag:autoIndex>
	</body>
	
</html>