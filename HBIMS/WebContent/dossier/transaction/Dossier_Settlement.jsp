<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>


<html>
<head>
<meta charset=UTF-8">
<title>Dossier Settlement</title>
<link href="/HBIMS/mms/css/master.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src=" ../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<!-- <script language="Javascript" src="../js/issue_trans.js"></script>
<script language="Javascript" src="../js/issueDetails_util.js"></script> -->
<script language="Javascript" src="../../hisglobal/js/validationCommon.js"></script>
<script language="Javascript" src="/HBIMS/transaction/dossier/Dossier_Settlement.js"></script>

<script language="Javascript" src="../js/dossier_trans.js"></script>
<script language="Javascript" src="../js/dossierItems_util.js"></script>
<script language="Javascript" src="../js/dossierDetails_util.js"></script>
<script language="Javascript" src="../js/dossierissueDetails_util.js"></script>

<script language="JavaScript" src="../js/mms.js"></script>
<script type="text/javascript">
var gblCRValue="";
var child = null;

function goFunc()
{
	//alert('1');
	
	var crNo = document.forms[0].strCrNo.value;
	if(crNo.length == '15')
		{
			document.forms[0].hmode.value = "INITVALGO";
			document.forms[0].submit();
		}else{
				alert('Cr No Must Be 15 Digit ');
				return false;
			}
}

function setvalue()
{
	//document.forms[0].strStoreId.disabled=false;

	//var store=document.forms[0].strId.value;
	//document.forms[0].strStoreId.value=store;
	
	
	
}

function showPatientListingWindow(mode , obj , userJsFuncName) 
 {
	 if(obj.value!="") 
		 {
		 	//alert("CR No. field should be blank to search patient records" );
			 gblCRValue=obj.value;
			 //alert(gblCRValue);
			 obj.value="";
			 if(document.getElementsByName("gblCRValue")!=undefined)
					document.getElementsByName("gblCRValue")[0].value=gblCRValue;
		 }
	 	if(obj.value == "")
	  	{
	 		var hmode = "PATIENTLISTING"; 
				var url = "../transactions/IpdCNT.cnt?hmode="+hmode+"&patList="+mode+"&usrFuncName="+userJsFuncName;				
				openPopUp(createFHashAjaxQuery(url),'700','220','1','',null);
					//var featuresList = "width=600,height=380,ALIGN=CENTER,left=300,top=300,scrollbars=yes";
	               // myWindow = window.open(url,'popupWindow',featuresList); 
	 	}
 }

function showImg(obj)
{
	 //alert("showImg");
	//document.getElementsByName("strCrNo")[0].value = "";
	//initPage();
	document.forms[0].strCrNo.disabled = false;
	if(obj.value == "1")
		document.getElementById("searchPatient1").style.display="";
	else
 		document.getElementById("searchPatient1").style.display="none";
}
</script>
</head>
<body  onLoad="getReportnew()">

<html:form action="/transaction/DossierSettlementCNT.cnt"  name="DossierSettlementBean" type="dossier.transaction.controller.fb.DossierSettlementFB" method="post" > 

	<div id="errMsg" class="errMsg"><bean:write name="DossierSettlementBean" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="DossierSettlementBean" property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="DossierSettlementBean" property="strNormalMsg" /></div>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
		<tr class="HEADER"> 
			 <td colspan="4">Dossier Settlement&gt;&gt;</td>
			 <td align="right" ><div style='display:none;'>
			 	<span>
			     	<input type="radio" name="strPatientType" checked="checked" value="1" onclick="">IPD</>
		     	   <input type="radio"  name="strPatientType" value="2" onclick="" >OPD</>
		     	
		     	</span>		 
		   </div></td> 
	    </tr>
    </table>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1" cellspacing="1">
	
	<tr>	 
	
    <td width="25%" class="LABEL" id="CrNOOrLf"><font color="red">*</font>CR No.</td>
    <td colspan="1" class="CONTROL" style="display:block"><div id="CRWise">
     <crNo:crNo value ="${DossierSettlementBean.strCrNo}" js=" onkeypress='return initGoFunc(event);'"></crNo:crNo>
   <!--  <input type="text" name="strCrNo" value ="${DossierSettlementBean.strCrNo}" js=" onkeypress='return initGoFunc(event);'"/>   -->
   <img style="cursor: pointer; cursor: hand;" src="../../hisglobal/images/viewDetails.gif" title="Click here for Patient Search" name="searchPatient" id="searchPatient1" onclick="showPatientListingWindow('5',document.forms[0].strCrNo,'setSelectedCrNo');" align="middle">
    <input type="image" style="cursor:pointer;cursor:pointer;margin-top: 3px;" title="Issue Process" 
		    align="top" src ="../../hisglobal/images/Go.png" name="go" value="Go" 
		    onclick="return goFunc();" onkeyup="goFuncOnEnter(event);">  
		</div>
  </td>
  
  
  </tr>
	</table>




<table class="TABLEWIDTH" align="center" cellpadding="1" cellspacing="1">
		<tr class="FOOTER">
			<td colspan="4"><font color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1"
		cellspacing="1">
		<tr>

			<!-- <td align="center"><img
				style=" cursor: pointer"
				src="../../hisglobal/images/btn-clr.png" onClick="clearIssue();" title="Click to Clear Page">
			<img style=" cursor: pointer"
				src="../../hisglobal/images/btn-ccl.png" onClick="cancelIssue();" title="Click to cancel process">
			</td > -->
			
			<td align="center">
			<br>
			<a href="#" class="button"	onclick="clearIssue();"><span class="clear">Clear</span></a> 
			<a href="#" class="button" onclick="cancelIssue();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	
	<input type="hidden" name="hmode" />
	<input type="hidden" name="storeName" value="${DossierSettlementBean.storeName}" />
	<input type="hidden" name="itemCatName" value="${DossierSettlementBean.itemCatName}" />
	<input type="hidden" name="crNo" value="${DossierSettlementBean.strCrNo}" />
	<input type="hidden" name="strId" value="${DossierSettlementBean.strId}" />
	<input type="hidden" name="strConfCatCode" value="${DossierSettlementBean.strConfCatCode}" />
	<input type="hidden" name="itemCategory" value="${DossierSettlementBean.itemCategory}" />	
	<input type="hidden" name="strMode"   value="${DossierSettlementBean.strMode}">
	<input type="hidden" name="strIssueMode"   value="${DossierSettlementBean.strIssueMode}">
	<input type="hidden" name="strIssueNum" value="${DossierSettlementBean.strIssueNum}" />
	<input type="hidden" name="strIssueNumber" value="${DossierSettlementBean.strIssueNum}" />
	<input type="hidden" name="strVisitDtl" value="${DossierSettlementBean.strVisitDtl}" />
	<input type="hidden" name="strDoseFrqFlg" value="${DossierSettlementBean.strDoseFrqFlg}" />
	<input type="hidden" name="strReOrderFlgColor" value="${DossierSettlementBean.strReOrderFlgColor}" />
	
	<input type="hidden" name="strLFAccountNo" value="${DossierSettlementBean.strLFAccountNo}" />
	<input type="hidden" name="strLFAccountOpenDate" value="${DossierSettlementBean.strLFAccountOpenDate}" />
	<input type="hidden" name="strLFDepositedAmount" value="${DossierSettlementBean.strLFDepositedAmount}" />
	<input type="hidden" name="strLFBalanceAmount" value="${DossierSettlementBean.strLFBalanceAmount}" />
	<input type="hidden" name="strLFAccountStatus" value="${DossierSettlementBean.strLFAccountStatus}" />
	<input type="hidden" name="strPath" value="${DossierSettlementBean.strPath}" />
	<input type="hidden" name="printReq" value="${DossierSettlementBean.printReq}" />
	<input type="hidden" name="strSericeType" value="${DossierSettlementBean.strSericeType}" />
	<input type="hidden" name="gblCRValue" value="" />
<div id="blanket" style="display: none;"></div>
<div class="popUpDiv" id="popUpDiv" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
						
				<div id="issueDtlsDivId" style="display: block;"></div>
		
			</td>
		</tr>
	</table>
	</div>
	<div class="popUpDiv" id="popUpDiv1" style="display:none;">
   <table bgcolor="white">
     <tr>
      <td>
         <div id="transferDtlsDivId" style="display:block;"></div>
      </td>
     </tr>
    </table>
   </div>
   
   <div class="popUpDiv" id="popUpDiv" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
			<div id="searchItemsDtlsDivId" style="display: block;"></div>
			</td>
		</tr>
	</table>
	</div>
	
</html:form>
	<tag:autoIndex></tag:autoIndex>   
</body>
</html>
