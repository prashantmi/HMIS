<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/tab.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/js/newMultiRow.js"/>
<his:javascript src="/hisglobal/js/multirow.js"/>
<his:javascript src="/hisglobal/js/dropdown.js"/>
<his:javascript src="/hisglobal/js/common.js"/>
<his:javascript src="/ipd/js/admAdvice.js"/>

<html>
<head>
<meta charset=utf-8>
<title>Admission Advice Online</title>

<link href="/HBIMS/ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function validate1()
{
	if(document.getElementById("dropdown1").style.display=="block")
	{
		alert("Please Enter Data in Diagnosis & Close the List Opened");
		return false;
	}
	var diag = document.getElementsByName("strProvisionDiagnosis");
	var hiddendiag = document.getElementsByName("strICD10CodeHidden");
	for(var nTmpI =0 ; nTmpI<diag.length-1; nTmpI++)
	{
		if(diag[nTmpI].value!=hiddendiag[nTmpI].value)
		{
			alert("Please Select Proper ICD10 Code");
			return false;
		}
		if(document.getElementsByName("strDiagTypeCode")[nTmpI].value=='0')
		{
			alert("Please Select Diagnostic Type");
			document.getElementsByName("strDiagTypeCode")[nTmpI].focus();
			return false;
		}
	}
	if(document.forms[0].strPackageApply.value!="0")
	{
		/*var temp=document.forms[0].strPackageApply.value.split("^");
		if(document.forms[0].strAdvanceAmount.value<temp[1] && temp[1]!="")
		{
			alert("Advance Amount cannot be less than applied package OT amount");
			return false;
		}*/
		if(document.forms[0].strpack.value!="0^0")
		{
			alert("A Package has already been availed by patient. Another one is not allowed");
			return false;
		}
	}
	var retVal = false;
	var hisValidator = new HISValidator(document.forms[0].name);
	hisValidator.addValidation("strPropAdmissionDate", "req","Prop Adminssion Date is a Mandatory Field");
	hisValidator.addValidation("strPropAdmissionDate", "date","Prop Adminssion Date should be a valid Date");
	hisValidator.addValidation("strPropAdmissionDate", "dtgtet=${advanceAdviceTransBean.strCtDate}" , "Prop Adminssion Date should be Greater than or Equal to Current Date");
	if(document.forms[0].strLenStay.value != ""){
		hisValidator.addValidation("strLenStay", "numgt=0","Length of Stay must be Grater Than 0");
		hisValidator.addValidation("strLenStay", "numltet=120","Length of Stay must be Less Than or Equal to 120");
	}
	hisValidator.addValidation("strTreatment", "dontselect=0","Please Select a treatment category");		
	hisValidator.addValidation("strWard", "dontselect=0","Please Select a Ward");
	
	hisValidator.addValidation("strRoomType", "numgt=0","Click Bed Status to Select a Room Type");
	hisValidator.addValidation("strBedType", "numgt=0","Click Bed Status to Select a Bed Type");
			
	//hisValidator.addValidation("strWaitPeriod", "req","Wait Period is a Mandatory Field");
	hisValidator.addValidation("strWaitPeriod", "numgt=0","Wait Period must be Grater Than 0");
	hisValidator.addValidation("strWaitPeriod", "numltet=100","Wait Period must be Less Than or Equal to 100");
	retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	if(retVal)
	{
		retVal=checkDuplicateDiagnosis();
		if(retVal)
			retVal=checkMultirow('strProvisionDiagnosis', 'You have Selected Duplicate Data in Provosion Diagnosis Combo');
	}
	if(retVal)
	{
			hisValidator.addValidation("strConsultantName", "dontselect=0","Please Select a Consultant");
			if(document.forms[0].strRemarks.value != "")
			{
				hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
			}
			retVal = hisValidator.validate(); 
			hisValidator.clearAllValidations();
		}
		if(retVal)
		{
				if(parseInt(document.forms[0].strLenStay.value) >= 30 )
				{
						if(!confirm("Length of Stay is More than 30 Days, Are you Sure?"))
						{
							document.forms[0].strLenStay.focus();
							return false;
						}
				}
				if(document.forms[0].flag.value==1)
				{
					if(document.forms[0].strTreatment.value == "0")
						document.forms[0].strTreatment.value=document.forms[0].strCategoryCode.value;
					
					document.getElementsByName("mode")[0].value = "ADMISSIONADVICE";
					document.forms[0].hmode.value = "INSERT";
					document.forms[0].action="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt";
					document.forms[0].submit();
				}
				else
				{
					alert('You cannot Generate Admission advice');
					return false;
				}
		}
		else
		{
			return false;
		}
}
function newRow()
{
	if(document.getElementById("dropdown1").style.display=="block")
	alert("Please Enter Data in Current Row");
	else
	{
		document.getElementById("diagLabel").style.display="";
		addRows(new Array('strProvisionDiagnosis','strIcdCode','strICD10CodeHidden','strDiagCodeType'),new Array('t','t','t','t'),'1','1','R','R');
	}
}

function getAdvanceAmount()
{
	 	if(document.getElementsByName("strTreatment")[0].value != "0")
		{
			    
				var mode ="ADVANCEAMOUNT";
				//var url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&puk="+document.getElementsByName("patCrNo")[0].value+"&catcode="+document.forms[0].strTreatment.value+"&wardType="+document.getElementsByName("strWard")[0].value.split("^")[1];
                 var url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&puk="+document.getElementsByName("patCrNo")[0].value+"&catcode="+document.forms[0].strTreatment.value+"&wardCode="+document.forms[0].strWard.value;
				ajaxFunction(url,"5");
		}
	   	else
	    {
		         document.getElementsByName("strAdvanceAmount")[0].value="0";
	    }
}


function getCombo()
{
   if(document.getElementsByName("strIssueEstCertificate")[0].checked == true)
   {
      	document.getElementById("td1").style.display = "block";
      	document.getElementById("td2").style.display = "block";
   }
   else
   {
   		document.getElementById("td1").style.display = "none";
   		document.getElementById("td2").style.display = "none";
   }
}


</script>
</head>
<body onLoad="invokeOnLoad()" onUnload="closePopUp();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/AdmissionAdviceTransCNT" method ="post">
<div class="errMsg" id="errMsg"><bean:write name="advanceAdviceTransBean" property="strMsgString"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="advanceAdviceTransBean" property="strWarning"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="advanceAdviceTransBean" property="strMsg"/></div>
	<tag:tab tabLabel="Admission Advice" selectedTab="FIRST" align="center" width="TABLEWIDTH" onlyTabIndexing="1"></tag:tab>

<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
	<tr class="HEADER">
		<td colspan="4">Admission Advice &gt;&gt;Add</td>
	</tr>
	 <tr>
		<td colspan="3" class="TITLE" width="93%">Patient Demographic Details</td>
		<td class="multiLabel" width="7%">
		<div id="plusId"><img name="plus"
			src="/HBIMS/hisglobal/images/plus.gif" style="cursor: pointer"
			onClick="showPatientDetails('pDtlsDivId');" style="cursor: pointer;">
		</div>
		<div id="minusId" style="display: none"><img name="minus"
			style="cursor: pointer" src="/HBIMS/hisglobal/images/minus.gif"
			onClick="hidePatientDetails('pDtlsDivId'); " style="cursor: pointer;">
		</div>
		</td>
	</tr> 
</table>
<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px"
	cellpadding="0px">
	<tr>
		<td class="LABEL" width="25%">
		<div align="right">CR No.</div>
		</td>
		<td class="CONTROL" width="25%"><bean:write
			name="advanceAdviceTransBean" property="patCrNo" /></td>
		<td class="LABEL" width="25%">
		<div align="right">Date</div>
		</td>
		<td class="CONTROL" width="25%"><bean:write
			name="advanceAdviceTransBean" property="strCtDate" /></td>
	</tr>
</table> -->

<div id="pDtlsDivId" >

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
	
	<pDtl:patDtl crNo="${advanceAdviceTransBean.patCrNo}" address="false"></pDtl:patDtl>
	

</table>


<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
	<tr>
		<td class="TITLE" colspan="4">Episode Details</td>
	</tr>
	<tr>
		<td width="25%" class="LABEL">
		<div align="right">Department</div>
		</td>
		<td width="25%" class="CONTROL"><bean:write
			name="advanceAdviceTransBean" property="strDepartment" /></td>
		<td width="25%" class="LABEL">
		<div align="right">Unit</div>
		</td>
		<td width="25%" class="CONTROL"><bean:write
			name="advanceAdviceTransBean" property="strUnit" /></td>
	</tr>
	<tr>
		<td width="25%" class="LABEL">
		<div align="right">Episode No.</div>
		</td>
		<td width="25%" class="CONTROL"><bean:write
			name="advanceAdviceTransBean" property="strEpisodeNumber" /></td>
		<td width="25%" class="LABEL" colspan="2"></td>
	</tr>
</table>
</div>
<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		<td colspan="4" class="TITLE">Admission Advice</td>
	</tr>
	<tr>
		<td class="LABEL" width="25%">
		<div align="right"><font color="red">*</font>Treatment Category</div>
		</td>
		<td class="CONTROL" width="25%"><select name="strTreatment"
			class="comboNormal" onchange="getAdvanceAmount();">
			<bean:write name="advanceAdviceTransBean"
				property="strTreatmentCategoryValues" filter="false" />
		</select></td>
		<td class="LABEL" width="25%" id='diseaseTypeLabel'>
		<div align="right" id='diseaseTypeId' style="display: none">Disease	Type</div>
		</td>
		<td class="CONTROL" width="25%" id='diseaseTypeWithoutLabel'>
		<div id='diseaseTypeValuesId' style='display: none'><select
			name="strDiseaseType" class="comboNormal" onchange="getWard();">
			<bean:write name="advanceAdviceTransBean"
				property="strDiseaseTypeValues" filter="false" />
		</select></div>
		</td>
	</tr>
	<tr>
		<td width="25%" class="LABEL">
		<div align="right"><font color="red">*</font>Ward</div>
		</td>
		<td width="25%" class="CONTROL">
		<div id="wardCombo">
		  <select class="comboNormal" name="strWard"
			onChange="initBedAndRoom(),checkDuplicate();">
			<option value="0">Select Value</option>
		</select></div>
		</td>
		<td width="25%" class="LABEL"><b>Bed Status</b> </td>
		<td width="25%" class="multiControl"><img
			src="/HBIMS/hisglobal/images/Bed_.gif" style="cursor:pointer;width: 25px;"
			onClick="return showBedStatus();" align="middle" onmouseover=""
			style="cursor: pointer;" /></td>		
	</tr>
	<tr>
		<td width="25%" class="LABEL">
		<div align="right">Room/Bed Type</div>
		</td>
		<td width="25%" class="CONTROL">
		<div id="roomBedId"></div>
		</td>
		<td width="25%" class="LABEL"><b>Advice List</b></td> 
		<td width="25%" class="multiControl">
		<img
			src="/HBIMS/hisglobal/images/Check_List.png" style="cursor: pointer"
			onClick="return showList();" align="middle" onmouseover=""
			style="cursor: pointer;" /></td>		
	</tr>
	
	<tr>
	<td width="25%" class="LABEL"><div align="right"><font color="red">*</font>Advance Amount</div></td>
    <td width="25%" class="CONTROL">
    <input type="text" name="strAdvanceAmount" value="0" onkeypress="return validateData(event,7);" />
    </td>
    <td width="25%" class="LABEL"></td>
    <td width="25%" class="CONTROL">
    </td>
	</tr>
	
	<tr>
		<td width="25%" class="LABEL">
		<div align="right">Admission Priority</div>
		</td>
		<td width="25%" class="CONTROL"><html:radio
			name="advanceAdviceTransBean" property="strAdmissionPriority"
			value="0">Normal</html:radio> <html:radio
			name="advanceAdviceTransBean" property="strAdmissionPriority"
			value="1">Urgent</html:radio> <input type="hidden" name="strLenStay"
			maxlength="3" onkeypress="return validateData(event,5);"></td>
			<td width="25%" class="LABEL">
		<div align="right"><font color="red">*</font>Prop Admission Date</div>
		</td>
		<td width="25%" class="CONTROL"><date:date
			name="strPropAdmissionDate"
			value="${advanceAdviceTransBean.strCtDate}"></date:date></td>
		<!--  <td width="25%" class="LABEL" colspan="1"><div align="center">Length of Stay (in Days)</div></td>
    <td width="25%" class="CONTROL" colspan="3"></td>-->
	</tr>
	
	
	
 <!--  <tr>
    <td width="25%" class="LABEL"><div align="right">Whether Issue Estimation Certificate:</div></td>
    <td width="25%" class="CONTROL">
    <input type="checkbox" name="strIssueEstCertificate" onclick="getCombo();"/>
    </td>
    <td width="25%" class="LABEL" ><div align="right" style="display: none;" id="td1">Whether Issue Estimation Certificate</div></td>
    <td width="25%" class="CONTROL" ><div  style="display: none;" id="td2"> 
    <select name="strTariffNameValues" class="comboNormal"  >
	<bean:write name="advanceAdviceTransBean" property="strTariffNameValues" filter="false"/>
	</select>
	</div>
	</td>

  </tr>   -->
	
</table>

<div id="checkid" style="display: none">
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  <tr>
    <td colspan="4" class="TITLE" width="100%"><img name="minus"  src="/HBIMS/hisglobal/images/minus.gif" 
       onClick="view2('DiagPlusId','DiagMinusId','DiagDetailsId'); " 
       style="cursor: pointer;display:none;" id="DiagMinusId">
       <img name="plus" src="/HBIMS/hisglobal/images/plus.gif" 
       onClick="view1('DiagPlusId','DiagMinusId','DiagDetailsId');" 
       style="cursor: pointer;display:" id="DiagPlusId">&nbsp;&nbsp;
       <a style="cursor: pointer; color: " title="Show Previous Diagnosis" 
    		onclick="view1('DiagPlusId','DiagMinusId','DiagDetailsId');">
    		Admission Type Details</a></td>
  </tr>
  </table>
 		  <div id="DiagDetailsId" style="display: none">
 		   <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
 		   <tr>
           <td width="25%" class="LABEL"><div align="right">Planned Surgery Date:</div></td>
    	   <td width="25%" class="CONTROL">
    	   <date:date name="strPlannedSurgeryDate" value=""></date:date>
    	   </td>
    	   
    	   <td width="25%" class="LABEL"><div align="right">Admission Type</div></td>
    	   <td width="25%" class="CONTROL"><div id="offlineUnitId">
    	   <select name="strAdmissionTypeValues" class="comboNormal"  >
		   <bean:write name="advanceAdviceTransBean" property="strAdmissionTypeValues" filter="false"/>
		   </select>
		   </div>
		   </td>
   		   </tr>
   		   
   		   <tr>
   		   <td width="25%" class="LABEL"><div align="right">Approximate Length Of Stay</div></td>
    	   <td width="25%" class="CONTROL">
    	   	<input type="text" name="strMaxLenStay" maxlength="2" onkeypress="return validateData(event,5);">
    	   </td>
    	   <td width="25%" class="LABEL">Apply OT Package</td>
    	   <td width="25%" class="CONTROL"><div id="pack" style="display:none">
	    	   <select name="strPackageApply" class="comboNormal" onChange="setAdvAmt();" >
			   	<bean:write name="advanceAdviceTransBean" property="strPackageComboValues" filter="false"/>
			   </select></div>
    	   </td>
    	   </tr>    	    
    	   </table>
 	   	   </div>
  </div>

<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  <tr>
    <td colspan="4" class="TITLE" width="100%"><img name="minus"  src="/HBIMS/hisglobal/images/minus.gif" 
       onClick="view2('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId'); " 
       style="cursor: pointer;display:none;" id="prevoiusDiagMinusId">
       <img name="plus" src="/HBIMS/hisglobal/images/plus.gif" 
       onClick="view1('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId');" 
       style="cursor: pointer;display:" id="prevoiusDiagPlusId">&nbsp;&nbsp;
       <a style="cursor: pointer; " title="Show Previous Diagnosis" 
    		onclick="view1('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId');">
    		Previous Diagnosis Details</a></td>
  </tr>
  </table>

<div id="prevoiusDiagDetailsId" style="display: none">
	<bean:write	name="advanceAdviceTransBean" property="strPreviousDiagDtl"	filter="false" />
</div>
<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<bean:write name="advanceAdviceTransBean" property="strProvisionDiagnosisValues" filter="false" />
	<tr>
		<logic:equal name="advanceAdviceTransBean" property="strDiagType" value="0">
		<td colspan="2" class="TITLE" width="50%">
		<div id='hospitalDiagnosisId'>
		<html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="1"
			onclick="">Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div>
		</td>
		<td colspan="2" class="TITLE" width="48%">
		<div id='icdDiagnosisId'>
		<html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="0"
			onclick="">ICD10 Diagnosis</html:radio></div>
		</td>
		<td width="2%" class="TITLE">
		<img src="/HBIMS/hisglobal/images/plus.gif" onclick="newRow();" style="cursor: pointer;"></td>
		</logic:equal>
		<logic:notEqual name="advanceAdviceTransBean" property="strDiagType" value="0">
		<logic:equal name="advanceAdviceTransBean" property="strDiagType" value="2">
		<td colspan="2" class="TITLE" width="98%">
		<div id='hospitalDiagnosisId'>
		<html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="1"
			onclick="changeMultiRows();">Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div>
		</td>
		<td width="2%" class="TITLE">
		<img src="/HBIMS/hisglobal/images/plus.gif" onclick="newRow();" style="cursor: pointer;"></td>
		</logic:equal>
		<logic:equal name="advanceAdviceTransBean" property="strDiagType" value="1">
		<td colspan="2" class="TITLE" width="98%">
		<div id='icdDiagnosisId'>
		<html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="0"
			onclick="changeMultiRows();">ICD10 Diagnosis</html:radio></div>
		</td>
		<td width="2%" class="TITLE">
		<img src="/HBIMS/hisglobal/images/plus.gif" onclick="newRow();" style="cursor: pointer;"></td>
		</logic:equal>
		</logic:notEqual>
		
	</tr>
</table>
<div id="diagLabel" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="0" cellspacing="1px">
	<tr>
		<td class="TITLE" width="10%"><div align="center">Code</div></td>
		<td class="TITLE" width="73%" align="left">
		<div align="center">Diagnosis Name</div></td>
		<td class="TITLE" width="15%" align="center">
		<div align="center">Type</div></td>
		<td class="TITLE" width="2%" align="right"><div align="center"></div></td>		
	</tr>
</table>
</div>
<table id="icd10Id" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
	<tr></tr>
</table>
<div id='id1'></div>
<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		<td colspan="2" width="25%" class="LABEL">
		<div align="center">Remarks</div>
		</td>
		<td colspan="2" class="CONTROL"><textarea rows="2" cols="20"
			name="strRemarks"></textarea></td>
	</tr>
	<tr>
		<td colspan="2" width="50%" class="LABEL">
		<div align="center"><font color="red">*</font>Consultant Name</div>
		</td>
		<td colspan="2" width="50%" class="multiControl"><div align="left">
		<select name="strConsultantName">
			<bean:write name="advanceAdviceTransBean"
				property="strConsultantNameValues" filter="false" />
		</select></div></td>
	</tr>
	<tr class="FOOTER">
		<td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
</table>

<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px"
	cellpadding="0px">
	<tr>
		<!-- <td align="right"><img src="/HBIMS/hisglobal/images/btn-sv.png"
			onClick="return validate1();" style="cursor: pointer;"></td>
		<td align="left"><img src="/HBIMS/hisglobal/images/btn-ccl.png"
			onClick="closeTabOnDesk();" style="cursor: pointer;"></td> -->
			<br>
			<td align="right"><a href="#" class="button" id="" onclick='return validate1();'><span class="save">Save</span></a></td>
			<td align="left"><a href="#" class="button" onclick="closeTabOnDesk();"><span class="cancel">Cancel</span></a></<td>
	</tr>
</table>

	<input type="hidden" name="hmode" value="">
	<input type="hidden" name="mode">
	<input type="hidden" name="strUnitCode" value="${advanceAdviceTransBean.strUnitCode}">
	<input type="hidden" name="strTreatmentCategory" value="${advanceAdviceTransBean.strTreatmentCategory}">
	<input type="hidden" name="strDepartment" value="${advanceAdviceTransBean.strDepartment}">
	<input type="hidden" name="strCrNo" value="${advanceAdviceTransBean.strCrNo}">
	<input type="hidden" name="strDepartmentValue" value="${advanceAdviceTransBean.strDepartmentValue}">
	<input type="hidden" name="strUnitValue" value="${advanceAdviceTransBean.strUnitValue}">	
	<input type="hidden" name="strEpisodeNumber" value="${advanceAdviceTransBean.strEpisodeNumber}">
	<input type="hidden" name="strVisitValue" value="${advanceAdviceTransBean.strVisitValue}">
	<input type="hidden" name="strSex" value="${advanceAdviceTransBean.strSex}">
	<input type="hidden" name="strAge" value="${advanceAdviceTransBean.strAge}">
	<input type="hidden" name="strPrimaryCategory" value="${advanceAdviceTransBean.strPrimaryCategory}">
	<input type="hidden" name="strSecondaryCategory" value="${advanceAdviceTransBean.strSecondaryCategory}">
	<input type="hidden" name="strRoomType" value="">
	<input type="hidden" name="strBedType" value="">
	<input type="hidden" name="patCrNo" value="${advanceAdviceTransBean.patCrNo}">
	<input type="hidden" name="flag" value="">
	<input type="hidden" name="strMode" value="${advanceAdviceTransBean.strMode}">
	<input type="hidden" name="advamt" value="">
<!--  <script type="text/javascript">
invokeOnLoad();

//if(document.forms[0].strCrNo.value!=""){
	//changeMultiRows();
//}
</script>-->
</html:form>
<jsp:include page="admissionadvice_dropdown_ipdtranswithoutDesk.jsp"></jsp:include>
<jsp:include page="admissionadvice_multirow_ipdtranswithoutDesk.jsp"></jsp:include>
</body>
</html>