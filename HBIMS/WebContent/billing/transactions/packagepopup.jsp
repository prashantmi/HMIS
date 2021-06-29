<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<meta charset=utf-8>
<title>Tariff Search</title>


<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../js/tariffCodeSearch.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../js/key_Event_billing.js"></script>

<script type="text/javascript">

function getPatientPackageDtl()
{
	var strCrNo= document.forms[0].strCrNo.value;
	var strPackageId= document.forms[0].strPackageId.value;
	var strAccNo= document.forms[0].strAccNo.value;
	var hmode = "GETPACKAGEDTLS"; 
	var url = "BillingCNT.cnt?hmode="+hmode+"&strCrNo="+strCrNo+"&strPackageId="+strPackageId+"&strAccNo="+strAccNo;
	ajaxFunction2(url,"5","getTariffCodeDetailAjaxResponse1");
	
}
function getTariffCodeDetailAjaxResponse1(res,mode)
{
	var err = document.getElementById("errMsg");
 	var temp = res.split("####");
    if(mode == '5')
	{

		var objEle = document.getElementById("strPacakageDivID");
		objEle.innerHTML = res;
	}
}

</script>
 <style type="text/css">
 .content {display:none;}
.preload { width:100px;
    height: 100px;
    position: fixed;
    top: 50%;
    left: 50%;}
 </style>
</head>
<body onLoad="getPatientPackageDtl();"  onkeypress="ESCclose(event)">
<html:form action="/transactions/BillingCNT.cnt"
	method="post">
	
	
	<div id = "errMsg" class="errMsg"><bean:write name="billingBean" property="strErrMsg"/></div> 
	 <div id='normalMsg'></div>
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
	<td colspan="4">
				<div id="strPacakageDivID"></div>
			</td>
			</tr>
	</table>	
	
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center"><img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="window.close();" /></td>
		</tr>
	</table>
	
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strCrNo" value="${billingBean.strCrNo }" />
	<input type="hidden" name="strPackageId" value="${billingBean.strPackageId }" />
	<input type="hidden" name="strAccNo" value="${billingBean.strAccNo }" />

</html:form>

</body>
</html>