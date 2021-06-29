<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>




<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient List Popup</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script type="text/javascript">

	function setCrNo(){
		
		objRadioVal = document.getElementsByName("selectedpat");
		
		if(objRadioVal.length > 0){
			
			var val = "";			
			for(i = 0; i< objRadioVal.length ; i++){
				
					if(objRadioVal[i].checked == true)
						val = objRadioVal[i].value;
				
			}
			
			window.opener.document.forms[0].strCrNo.value = val;
			window.opener.document.forms[0].hstrCrNo.value = document.forms[0].hcrno.value;
			window.opener.document.forms[0].hstrAdviceNo.value = document.forms[0].hadvno.value;
			window.opener.document.forms[0].hstrBookDate.value =  document.forms[0].hbkdate.value;
			window.opener.submitFun();
			window.close();
		}else{
		
			alert("No Record Found!");
			
			window.close();
			
		}
		
	}


</script>

</head>
<body>
<html:form action="/transactions/MsApprovalTransCNT" method="post">
<div class="normalMsg" id="normalMsg"></div>		
<table width="100%" align="center">
  <tr class="HEADER">
  <td colspan ="3">Patient List</td>
  </tr>
 
  </table>			
 
  
<bean:write name ='msApprovalTransBean' property ='strAllotementlist' filter="false"/>


<table width="100%" align="center">
<tr class="HEADER" ><td colspan="4">&nbsp;</td></tr>
</table>
  <table border="0" class="TABLEWIDTH" align="center">
 
		
		<tr>

			<td align="right"><img style="cursor:hand;cursor:pointer"
				src="../../hisglobal/images/btn-ok.png"
				onClick="setCrNo();" /></td>
			<td align="left">
			<img style="cursor:hand;cursor:pointer" src="../../hisglobal/images/btn-ccl.png"
			onClick="window.close();" />
			</td>
		</tr>
	</table>

	<input type="hidden" name="hmode" />
	  <cmbPers:cmbPers></cmbPers:cmbPers>
</html:form>


<tag:autoIndex></tag:autoIndex>
</body>
</html>