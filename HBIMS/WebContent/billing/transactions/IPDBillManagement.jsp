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

function goFunc() 
{  
 		var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	 	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	    hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
        var retVal = hisValidator.validate(); 
	    document.forms[0].CrNo.value=document.forms[0].strCrNo.value;
	    if(retVal)
		{
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
		}
		else
		{		
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
function getgoDetails(chk)
{
	//alert(document.forms[0].chkadm.value);
	document.forms[0].hmode.value="GOADM";
	document.forms[0].submit();
	}
function showHelpDetails(divId){
	//alert('show'+divId);
	var divId='HelpId';
	document.getElementById(divId).style.display="block";
			
	document.getElementById('minus'+divId).style.display="block";
	document.getElementById('plus'+divId).style.display="none";
	
}

function hideHelpDetails(divId){
	var divId='HelpId';
	//alert('show'+divId);
	document.getElementById(divId).style.display="none";
		
	document.getElementById('minus'+divId).style.display="none";
	document.getElementById('plus'+divId).style.display="block";
	
}
 </script>
</head>
<body onLoad="SetCursorToTextEnd('strCrNoId');document.forms[0].strCrNo.focus();">
<html:form action="transactions/IpdBillManagementTransNewCNT.cnt" method="post">

<table class="TABLEWIDTH" cellspacing="1px" align="center">
	<tr class="HEADER" bgcolor="">
	<td colspan="6">
	IPD Bill Management New
	</td>
 </tr>
 <tr>
   <td class="LABEL" ><font color="red">*</font>CR No.</td>
	 <td class="CONTROL" colspan="4">
        <crNo:crNo value="${ipdBillManagementTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'" id="strCrNoId"></crNo:crNo>
		<img title="click here for Bill Details" src="../../hisglobal/images/Go.png" name="go"  onclick="return goFunc();" onkeypress="return goFunc();"/>			 
			</td>
		</tr>
		<tr>
					<td width="100%" colspan='6' >
						<bean:write name="ipdBillManagementTransBean" property="strPatAdmissionList" filter="false"/>
					</td>
				</tr>
</table>

<div id="admlistDivId" style="display: none;" ></div>
<table class="TABLEWIDTH" border="0" cellpadding="1px" cellspacing ="1px" align="center">
				<tr class="FOOTER">
					<td width='3%'>
						<div id="plusHelpId" align="center">
							<img style="cursor: pointer;cursor: hand" src="../../hisglobal/images/plus.gif" name="plusHelp" align="middle" onclick="showHelpDetails('HelpId');" />
						</div>
						<div id="minusHelpId" style="display: none" align="center">
							<img style="cursor: pointer;cursor: hand" src="../../hisglobal/images/minus1.gif" name="minusHelp" onclick="hideHelpDetails('HelpId');"> 
						</div>
					</td>
					<td><div align="left">Legends</div></td>
					<td><div align="right"><font size="2" color="red">*</font> Mandatory Fields</div></td>					
				</tr>
	</table>
<div id="HelpId" style="display:none">
	<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
			<tr>
				<td bgcolor='#FFD700'></td>
				<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Discharged Patient</td>
			</tr>
						<tr class=FOOTER ><td colspan='2'></td></tr>
	</table>
	</div>   
	
<div id='errMsg' class="errMsg"><bean:write name="ipdBillManagementTransBean" property="strErrMsg" filter="false"/></div>
<div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="CrNo" value="${ipdBillManagementTransBean.strCrNo }"/>
	
	</html:form>
	<tag:autoIndex></tag:autoIndex>  
</body>
</html>