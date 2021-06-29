<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<!--
  IPD Bill Management New
  
  author: Debashis Sardar
  
  dated: 12th Mar 2013
-->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>

<head>
<meta charset=utf-8>
<title>IPD Bill Management New</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<style type='text/css'>

</style>

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../js/IpdBillMangTrans.js"></script>
<script language="Javascript" >

window.history.forward();

function goFunc() {
  
 	var hisValidator = new HISValidator("CompulsoryChargesByConsultantBean");  
	 	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	     hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
        var retVal = hisValidator.validate(); 
	    document.forms[0].CrNo.value=document.forms[0].strCrNo.value;
	    if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
		}else{
		
		return false;
		}
 }
function initGoFunc(eve){
	   	
	    var flag=validateData(eve,5);
  		 if(flag){	
	   	
	   	if(eve.keyCode == 13){
												
				goFunc();
				
				return false;
			}	   	

  }else{
	   		return false;
	   }		

	   }


</script>
</head>
<body onLoad="SetCursorToTextEnd('strCrNoId');document.forms[0].strCrNo.focus(); ">
<html:form action="transactions/CompulsoryChargesByConsultantCNT.cnt" method="post">


<logic:notEqual name="CompulsoryChargesByConsultantBean"  property="strIsDesk" value="true">
			
<table class="TABLEWIDTH" cellspacing="1px" align="center">
	<tr class="HEADER" bgcolor="">
	<td colspan="6">
	Compulsory Charges By Consultant
	</td>
 </tr>
 <tr>
   <td class="LABEL" ><font color="red">*</font>CR No.</td>
	 <td class="CONTROL" colspan="4">
        <crNo:crNo value="${CompulsoryChargesByConsultantBean.strCrNo}" js="onkeypress='return initGoFunc(event);'" id="strCrNoId"></crNo:crNo>
		<img title="click here for Bill Details" src="../../hisglobal/images/Go.png" name="go"  onclick="return goFunc();" onkeypress="return goFunc();"/>			 
			</td>
		</tr>
</table>


<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
</table>
	</logic:notEqual>
	
	<logic:equal name="CompulsoryChargesByConsultantBean"  property="strIsDesk" value="true">
			
<table class="TABLEWIDTH" cellspacing="1px" align="center">
	<tr class="HEADER" bgcolor="">
	<td colspan="6">
	Compulsory Charges By Consultant
	</td>
 </tr>
 
</table>
	</logic:equal>
<div id='errMsg' class="errMsg"><bean:write name="CompulsoryChargesByConsultantBean" property="strErrMsg" filter="false"/></div>
<div id="normalMsg" class="normalMsg"><bean:write  name="CompulsoryChargesByConsultantBean" property="strMsg"/></div>
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="CrNo" value="${CompulsoryChargesByConsultantBean.strCrNo }"/>
	<input type="hidden" name="strIsDesk" value="${CompulsoryChargesByConsultantBean.strIsDesk}">
	
	</html:form>
	<tag:autoIndex></tag:autoIndex>  
</body>
</html>