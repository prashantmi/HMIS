<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/accountDtl.tld" prefix="acDtl"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="DisDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/onlineClientDtl.tld" prefix="bDtl"%>
<%@page import="billing.BillConfigUtil"%>
<html>
<head><meta charset=utf-8>
<title>IPD Bill Management-Bill Approval</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>

<script language="Javascript" src="../../billing/js/IpdBillMangTrans.js"></script>
<script language="Javascript" src="../../billing/js/tariffSearch.js"></script>
<script language="Javascript" src="../../billing/js/tariffCodeSearch.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>

<script type="text/javascript">
 function viewBill(e)
 {
 	var strval=document.getElementsByName('strChkBoxComboValue')[0].value.split('^')
  	var deptcode=strval[0]; 
 	var wardcode=strval[1];  
  	var chk=document.getElementsByName('strChk')[0].value;   
  	var url="/HBIMS/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=VIEWRPT&deptCode="+deptcode+"&wardCode="+wardcode+"&chk="+chk; 
  	openDependentPopup(url,e,500,800);
 }
 
 function checkPoorFreeAmount()
 {
 if(document.getElementsByName('primaryCategoryCode')[0].value==<%=new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString()).getIpdFreeCategory()%>)
	 {
	 	var poorFreeWelfare = parseFloat(document.getElementsByName("strPoorFreeWelfareAmt")[0].value);
 		var poorFreeGrant =  parseFloat(document.getElementsByName("strPoorFreeGrantAmt")[0].value);
 		var balanceAmt =  parseFloat(document.getElementsByName("strNetPaybleAmt")[0].value);
	
	  
	
 		balanceAmt = balanceAmt + poorFreeWelfare + poorFreeGrant;
 	 
 	 	balanceAmt = roundValue(balanceAmt ,0);
 	
 		
 		document.getElementById("strNetPaybleAmt").value    = balanceAmt;
     	document.getElementById("strNetPaybleAmtDivId").innerHTML    = balanceAmt;
 	}
 }
	
</script>
</head>
<body onload="setInitValues(),getSumOfAmount(),checkPoorFreeAmount();checkPatientApprovalDtl();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="transactions/IpdBillManagementTransCNT.cnt" method="post">
 
      <div id="errMsg" class="errMsg"><bean:write     name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
   	 <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
 
 
 
     <!--        Drop Down Untility  Start                  -->
	
     <div id="blanket" style="display:none;"></div>
	<div class="popUpDiv" id="tariffDiscountDtlsForBillApproval" style="display:none;">
	<table bgcolor="white">
	<tr>
	<td>
		<div class='popup' id='tariffDiscountDtlsForBillApprovalInner' style="display:block">
	<table width='400' border='0' cellspacing ='1px'>
		<tr class="HEADER"> 
			<th colspan='4' align='left'>&nbsp;Tariff Discount Details</th>
		</tr>
		<tr>
			<td class='LABEL'><font color="red">*</font>Discount / Unit</td>
			<td class='CONTROL'><input type='text' name='strOffLineTariffDiscountUnit' 
				class='txtFldMax' onkeypress="return validateData(event,7);" /></td>
			<td class='LABEL'><font color="red">*</font>Discount Type</td>
			<td class='CONTROL'><select name='strOffLineTariffDiscountType' class='comboNormal'>
			<option value='1'>Fixed</option>
			<option value='2'>Percentage</option>
			</select> </td>
		</tr>
		<tr>
			<td class='LABEL'><font color="red">*</font>Discount By</td>
			<td class='CONTROL'><select class='comboMax' name="strOffLineTariffDiscountBy" title="_div_popup_cntl">
			<bean:write name="ipdBillManagementTransBean" property="strOfflineDiscountApprovedByDetails" filter="false"/>
			</select> </td>
			<td class='LABEL'><font color="red">*</font>Discount Reason</td>
			<td class='CONTROL'><select class='comboNormal' name="strOffLineTariffDiscountReason" onchange="setReasonText();" title="_div_popup_cntl">
				<bean:write name="ipdBillManagementTransBean" property="strOfflineDiscountRemarksDetails" filter="false"/>
			</select>
			<input type="text" name="strOffLineTariffDiscountReasonText" class='txtFldNormal' disabled="disabled"/>
			</td>
		</tr>
		<tr>
			<td colspan='2' class='LABEL'><font color="red">*</font>Discount Date</td>
			<td colspan='2' class='CONTROL'>
     			
     			<input type="text" name="strOffLineTariffDiscountDate" class='txtFldDate' value="${ipdBillManagementTransBean.strCtDate }"/> (dd-Mon-yyyy)
			</td>
		</tr>

		<tr class="TITLE">
			<td colspan='4'>&nbsp;</td>
		</tr>
	</table>
	<center>
	<table width='300'>
		<tr>
			<td>
			<center><img  style="cursor:pointer;cursor:hand" src='../../hisglobal/images/btn-ok.png'
				onClick='validateTariffDiscountDtlForBillApproval();'>
				<img style="cursor:pointer;cursor:hand" src='../../hisglobal/images/btn-ccl.png'
				onClick="hideDiscountDtlForBillApproval('tariffDiscountDtlsForBillApproval');"></center>
			</td>
		</tr>
	</table>
	</center>
	</div>
	</td>
	</tr>
</table>
</div>



<div class="popUpDiv" id="multiRowAdderDivId" style="display:none;">
<table bgcolor="white">
<tr>
<td>

<div class='popup' id='multiRowAdderDivIdInner' style="display: block">
	<table width='400' cellpadding="0" cellspacing="0"
		background="../../hisglobal/images/blank.gif">
		<tr class='HEADER'>
			<th colspan='2' align="left">&nbsp;Add Rows</th>
			<th align="right"><img
				src="../../hisglobal/images/stop.png" align="middle"
				onclick="hideMultiRowAdder('multiRowAdderDivId');"></th>
		</tr>

		<tr>
			<td class='LABEL'><font color="red">*</font>Enter The No. of
			Rows to be Added</td>
			<td width="1">&nbsp;</td>
			<td class='CONTROL'><input type='text'
				name='strOffLineTariffNoOfRows' class='txtFldMin' maxlength="2"
				onkeypress="return validateData(event,5),addTariffRows(this,event,'multiRowAdderDivId');" /></td>

		</tr>

		<tr class='FOOTER'>
			<td colspan='3'>&nbsp;</td>
		</tr>
	</table>
	</div>

</td>
</tr>
</table>
</div>




 
    <div class='popup' id='ProcessServiceDtl' style="display:none">
	 <table width='400'  border='0' cellspacing ='1px'>
		<tr class="TITLE">
			<th colspan='2' align='left'>&nbsp;Process Service Details</th>
			<th align='right'><img style="cursor:pointer;cursor:hand" src='../../hisglobal/images/stop.png'
				onClick="hidePayDetails('ProcessServiceDtl');"></th>
	        </tr>
	 </table>
	 <table width='400'  border='0' cellspacing ='1px'>
		<tr>
			<td width="25%" class='LABEL'>Rate/Unit</td>
			<td width="25%" class='CONTROL'><div id ='PrDtl1'></div></td>
			<td width="25%" class='LABEL'>Discount/Unit</td>
			<td width="25%" class='CONTROL'><div id ='PrDtl2'></div></td>
		</tr>
		<tr>
			<td width="25%" class='LABEL'>Service Tax(%)</td>
		   	<td width="25%" class='CONTROL' ><div id ='PrDtl3'></div></td>
		  	<td width="25%" class='LABEL'>Penalty(%)</td>
			<td width="25%" class='CONTROL'><div id ='PrDtl4'></div></td>
		</tr>
        </table>
	 </div>
 
 
    <div class='popup' id='UnProcessServiceDtl' style="display:none">
	<table width='400'  border='0' cellspacing ='1px'>
		<tr class="TITLE">
			<th colspan='2' align='left'>&nbsp;Un-Process Service Details</th>
			<th align='right'><img style='cursor:pointer;cursor:hand' src='../../hisglobal/images/stop.png'
				onClick="hidePayDetails('UnProcessServiceDtl');"></th>
	        </tr>
	</table>
	<table width='400'  border='0' cellspacing ='1px'>
		<tr>
			<td width="25%" class='LABEL'>Rate/Unit</td>
			<td width="25%" class='CONTROL'><div id ='UpDtl1'></div></td>
			<td width="25%" class='LABEL'>Discount/Unit</td>
			<td width="25%" class='CONTROL'><div id ='UpDtl2'></div></td>
		</tr>
		<tr>
			<td width="25%" class='LABEL'>Service Tax(%)</td>
		   	<td width="25%" class='CONTROL' ><div id ='UpDtl3'></div></td>
		  	<td width="25%" class='LABEL'></td>
			<td width="25%" class='CONTROL'></td>
		</tr>
        </table>
	</div>
         
       
 <!--<tag:tab tabLabel="Bill Approval" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>-->
	  <tag:tab tabList="${ipdBillManagementTransBean.lhm}" selectedTab="BILLAPPROVAL" align="center"
	width="TABLEWIDTH"></tag:tab>
 <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr class="TITLE"> 
    <td width="50%">Bill&gt;&gt;Approval</td>
     <td width="50%" align="right">
    	<a id="viewBill" style="cursor: pointer;display:none;" onclick="viewBill(event)" 
					title="View Bill"><font color="white" style=""><u>View Bill</u></font></a>
    </td>
  </tr>
  
  <tr> 
    <td width="50%" class="LABEL">CR No.</td>
    <td width="50%" class="CONTROL">
    <bean:write name="ipdBillManagementTransBean" property="strCrNo"/>
     
    </td>   
  </tr>
  </table>
 
  <jsp:include page="IpdBillMgmtHeader.jsp"/>
  
  <div id ="otherCreditDetailsId" style="display: none;">
	  <table class="NEWTABLEWIDTH" align="center" cellpadding="0" cellspacing="">
	  
	  <tr> 
		  <td width="2%" colspan='1'> 
		  		<div id="plusCreditDtlsId" style="display: block;" class="lineContinue2"> 
					<img src="/HBIMS/hisglobal/images/plus.gif" name="plusonLine" style="cursor:hand;cursor:pointer" onclick="displayCreditDetails('creditDtlsDivId');" align="middle">					
				</div>
				<div id="minusCreditDtlsId" style="display: none;" class="lineContinue2">
					<img src="/HBIMS/hisglobal/images/minus.gif" style="cursor:hand;cursor:pointer" name="minusonLine" onclick="hideCreditDetails('creditDtlsDivId');">
				</div>
		  </td>
		  <td colspan="3" width='98%'><div class="lineContinue"><label class='DIVLABEL'>Client Details</label></div> </td>
	  </tr>		
	  </table>
	</div>
	
	
	<div id ="creditDtlsDivId" style="display: none;">	
			  		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr><td>
				<bDtl:onlnCltDtl crNo="${ipdBillManagementTransBean.strCrNo}" ></bDtl:onlnCltDtl>
			</td></tr>
		</table>
					
	</div> 
	
  <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr>
            <bean:write name="ipdBillManagementTransBean" property="strBillAprovalDtl1" filter="false"/>
  </tr>
 </table>


<div style="display: none;"> 
  <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
                  <tr>
                  <td class="LABEL" width="50%"><font color="red">*</font>Treatment Category</td>
                  <td class="CONTROL" width="50%"><select class="comboNormal" name="strNewTreatmentCategory"  onchange='validateTariffReset();'>
                  <bean:write name="ipdBillManagementTransBean" property="strTreatmentCategoryValues" filter="false"   />
                  </select> </td>
                  </tr>
                  </table>

 <logic:equal name="ipdBillManagementTransBean" property="strIpdBillManagementMode"  value="2">
 
 <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
   <tr>
                  <td class="LABEL" width="50%" colspan="2"><font color="red">*</font>Ward Type</td>
                  <td class="CONTROL" width="50%" colspan="2" ><select class="comboNormal" name="strWardType" onchange='validateTariffReset();' >
                    <bean:write name="ipdBillManagementTransBean" property="strWardTypeValues" filter="false" />
                  </select> </td>
                  </tr>
 </table>
 </logic:equal>
 
  </div> 
   
 <table class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
  <tr>
            <bean:write name="ipdBillManagementTransBean" property="strBillAprovalDtl2" filter="false"/>
  </tr>
  </table>
  <table class="TABLEWIDTH" border="0" align="center" border="0" cellspacing ="1px">
  <tr> 
    <td width="50%" class="LABEL"></td>
    <td width="50%" class="CONTROL"> <input type="radio" name="isApproved" value="1" checked="checked"/><b>Approved </b>
    <input type="radio" name="isApproved" value="2"/><b> Rejected</b>
     
    </td> 
    </tr>
   </table>
  <table class="TABLEWIDTH" border="0" align="center" border="0" cellspacing ="1px">
  <tr> 
    <td width="50%" class="LABEL">Approval By</td>
    <td width="50%" class="CONTROL">
       <select name="strApprovedByCombo" class="comboBig">
          <bean:write name="ipdBillManagementTransBean" property="strApprovedByCombo" filter="false"/>
       </select>
    </td> 
    </tr>
    <tr>
     <td  width="50%" class="LABEL"><font size="1" color="red">**</font>Remarks</td>
     <td  width="50%" class="CONTROL"> <textarea rows="1" cols="15" name="strRemarks"></textarea></td> 
  </tr>
  <tr class="FOOTER">  
    <td colspan="1" width='50%'><div align='left'><font size="2" color="red">**</font> Mandatory Rejected Remarks</div></td>
    <td colspan="1" width='50%'><div align='right'><font size="2" color="red">*</font> Mandatory Fields</div></td>
  </tr>
  </table>
  <table border="0" class="TABLEWIDTH" align="center"  border="0" cellspacing ="1px">
		<tr>
			<!-- <td align="right"><img style="cursor:hand;cursor:pointer" id="saveButtonId" name="save"  src="../../hisglobal/images/btn-sv.png" onclick=" return goFuncBillApproval();"/></td>
			<td align="left"><img  style="cursor:hand;cursor:pointer"  src="../../hisglobal/images/btn-ccl.png" onClick="cancel1('LIST')"></td>-->
			
			<td align="right"><a href="#" class="button" id="btn-sv" onclick=' return goFuncBillApproval();'><span class="save">Save</span></a></td>
			<td align="left"><a href="#" class="button" id="btn-ccl" onclick="cancel1('LIST');"><span class="cancel">Cancel</span></a></td>
		</tr>
	</table>
    <input type="hidden" name="hmode">
	<input type="hidden" name="strChk" value="${ipdBillManagementTransBean.strChk}">
	<input type="hidden" name="strChkBoxComboValue" value="${ipdBillManagementTransBean.strChkBoxComboValue}">
	<input type="hidden" name="strIpdThirdPartyBilling" value="${ipdBillManagementTransBean.strIpdThirdPartyBilling}">	
	<input type="hidden" name="strIsIpdDiscount" value="${ipdBillManagementTransBean.strIsIpdDiscount}" />
	<input type="hidden" name="strCrNo" value="${ipdBillManagementTransBean.strCrNo}">
	<input type="hidden" name="strWardCode" value="${ipdBillManagementTransBean.strWardCode}">	
	<input type="hidden" name="strTempTreatCat" value="0" />
	<input type="hidden" name="strTempWardCode" value="0" />
	<input type="hidden" name="strVal" value="${ipdBillManagementTransBean.strVal}">	
	<input type="hidden" name="searchColumn" value='<%=request.getParameter("searchColumn") %>' />
	<input type="hidden" name="search" value='<%=request.getParameter("search") %>' />
	<input type="hidden" name="blockNo" value='<%=request.getParameter("blockNo") %>' />
	<input type="hidden" name="prevNext" value='<%=request.getParameter("prevNext") %>' />
	<input type="hidden" name="rowNum" value='<%=request.getParameter("rowNum") %>'/>
	<input type="hidden" name="divisionId" value='<%=request.getParameter("divisionId") %>' />
	<input type="hidden" name="primaryCategoryCode" value="${ipdBillManagementTransBean.primaryCategoryCode}" />
	<input type="hidden" name="selectedTab">
	<input type="hidden" name="strIsCalledFromIpdBillNew" value="${ipdBillManagementTransBean.strIsCalledFromIpdBillNew}" />
    <input type="hidden" name="strAcctStatMode" value="${ipdBillManagementTransBean.strAcctStatMode}" />
    <input type="hidden" name="serviceFlag" value="${ipdBillManagementTransBean.serviceFlag}" />
   <cmbPers:cmbPers/>
</html:form>
	
	<tag:autoIndex></tag:autoIndex> 
</body>
</html>