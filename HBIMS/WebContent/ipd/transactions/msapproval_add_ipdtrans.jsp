<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<meta charset=utf-8>
<title>MS Approval Add Page</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/patientOccupationDetail.js"></script>
<script type="text/javascript">

var gblerr = "${msApprovalTransBean.strErrorMsg}";

function validate1(){
		var retVal = false;
		
		if(!validateThroughRegExp(document.forms[0].strCrNo,1))
		{
			return;
		}
		
		if(document.getElementsByName("strPatStatusCode")[0].value!="")
		{
			//hisValidator.addValidation("strOccEmpName", "req","Please Enter the Employee Name");
			var hisValidator = new HISValidator("msApprovalTransBean");
			hisValidator.addValidation("strCrNo", "req","CR No. is a mandatory Field.");
			hisValidator.addValidation("strDepartmentValue", "dontselect=0","Department is mandatory Field.");
			hisValidator.addValidation("strUnitValue", "dontselect=0" , "Unit is mandatory Field.");
			hisValidator.addValidation("strTreatment", "dontselect=0","Please Select a treatment category");		
			hisValidator.addValidation("strWard", "dontselect=0","Please Select a Ward");
			
			
			/*if(document.getElementsByName("strOccIsDept")[0]!=undefined && document.getElementsByName("strOccIsDept")[0].checked)
			{
				hisValidator.addValidation("strOccRelation","dontselect=0","Please Select Relation in Patient Occupation Detail");
				hisValidator.addValidation("strOccEmpName", "req", "Dependent  On  is a Mandatory Field in Patient Occupation Detail" ); 
			}*/
			
			hisValidator.addValidation("strOccBasic", "req","Please Enter the Basic Income");
			hisValidator.addValidation("strOccBasic", "amount=8,2","Please Enter a Valid Amount");
			
			//hisValidator.addValidation("strOccCity", "req","Please Enter the City");
			//hisValidator.addValidation("strOccState", "dontselect=0","Please Select a State");
			//hisValidator.addValidation("strOccOffName", "maxlen=70","Office Name Cannot be More than 70 Characters");
			//hisValidator.addValidation("strFormNo", "req","Please Enter the Form No.");
			///hisValidator.addValidation("strRequestDate", "req","Please Enter the Submit Date");
			///hisValidator.addValidation("strVerifiedDate", "req","Please Enter the Verified Date");
			
			hisValidator.addValidation("strVerifiedBy", "dontselect=0","Please Select Verified By");
			hisValidator.addValidation("strRemark", "maxlen=50","Remarks Cannot be More than 50 Characters");
			///hisValidator.addValidation("strWaitPeriod", "maxlen=2","Waiting Period Cannot be More than 2 digits");
			////hisValidator.addValidation("strOccOffPhNo", "maxlen=30","Phone No Cannot be More than 30 digits");
			hisValidator.addValidation("strRequestDate", "dtltet="+document.forms[0].strVerifiedDate.value,"Submit Date should not be greater than Verified Date");
			retVal=hisValidator.validate();
			if(retVal){
			
			var patBasicOrIncome = parseFloat(document.forms[0].strOccBasic.value);
			var govtBasicPay = parseFloat(document.forms[0].strGovermentEmployeeBasicPayLimit.value);
			var pvtMonthlyIncome = parseFloat(document.forms[0].strPrivateEmployeeMonthlyIncomeLimit.value);
			
			
			var conf = true;
			
			if(document.forms[0].strOccIsGovtEmp[0].checked == true){
				
				if(patBasicOrIncome < govtBasicPay){
					conf = confirm("The Basic Pay is Less Than Goverment Employee Basic Pay Limit.\nAre you Sure to Approve Forcefully");
				}
				
			}else{
				if(patBasicOrIncome < pvtMonthlyIncome){
					conf = confirm("The Monthly Income is Less Than Private Employee Monthly Income Limit.\nAre you Sure to Approve Forcefully");
				}
			}
			
			if(document.getElementsByName("strPriorityType")[1].checked == true)
			{
				if(document.getElementsByName("strUrgentTypeReason")[0].value == "")
				{
				alert("Please Enter Urgent Priority Reason..."); 
				return false;
				}
				
				if(document.getElementsByName("strUrgentTypeRemarks")[0].value == "0")
				{
				alert("Please Enter Uregent Priority Reamrks...");
				return false;
				}
			}
			
			
			if(!conf){
				return false;
			}
				/*if(document.getElementsByName("strOccRelation")[0].disabled)
				{
					document.getElementsByName("strOccRelation")[0].disabled=false;
				}*/
				alert(document.forms[0].strWard.value);
				document.forms[0].strWardCode.value= document.forms[0].strWard.value.split("^")[0];
				document.forms[0].strWardType.value= document.forms[0].strWard.value.split("^")[1];
				document.forms[0].hmode.value = "INSERT";
	        	document.forms[0].submit();
			}else{
				hisValidator.clearAllValidations();
				return false;
			}
	}
}

function ajaxFunDeptUnit()
{ 
	var url="MsApprovalTransCNT.cnt?hmode=DEPTUNIT&modCrno="+document.forms[0].strCrNo.value+"&modDeptUnit="+document.forms[0].strDeptUnitCmb.value;
	ajaxFunction(url,'1');
}

function getAjaxResponse(res,mode)
{
	/*var res1 = res.split("^");
	var diagnosis = res1[0];
	var consultant = res1[1];
    if( diagnosis!= 'null' && diagnosis!="" && consultant !=  'null' && consultant != "" ){ 	
		document.getElementById("divdiagnosis").innerHTML =diagnosis;
    	document.getElementById("divconsultant").innerHTML =consultant;  
    } */
    if(mode=='3')
	{
    	//alert(res);
		try
		{  
			var objWard=document.getElementById("wardCombo");
			objWard.innerHTML="<select class='comboNormal' name='strWard' onChange=''>"+res+"</select>";
		}
		catch(e)
		{}
	}
    if(mode=='5')
	{
	    //alert(res);
		var objWard=document.getElementById("offlineUnitId");
		objWard.innerHTML="<select class='comboNormal' onchange='getWard()' name='strUnitValue'>"+res+"</select>";
		//alert(document.forms[0].strDepartmentValue.value);
		//alert("${msApprovalTransBean.strDepartmentValue}");
		if(!"${msApprovalTransBean.strUnitValue}"=="0" && document.forms[0].strDepartmentValue.value=="${msApprovalTransBean.strDepartmentValue}")
			document.forms[0].strUnitValue.value="${msApprovalTransBean.strUnitValue}";
		getWard();
	} 
}
function getWard()
{
	 
		 //getAgeSex();
		 var mode ="MODEWARD";
		 if(document.forms[0].strDepartmentValue.value!="0")
		 {
			 try
			 {
			  	document.forms[0].strAge.value=document.forms[0].strAge.value.replace(/[^0-9]/g,"");//replace all those character those are not number
		        var url="/HBIMS/ipd/transactions/MsApprovalTransCNT.cnt?hmode="+mode+"&treatmentCategCode="+document.forms[0].strPrimaryCategory.value+"&deptCode="+document.forms[0].strDepartmentValue.value+"&deptUnitCode="+document.forms[0].strUnitValue.value+"&unitCode="+document.forms[0].strUnitCode.value+"&age="+document.forms[0].strAge.value+"&sex="+document.forms[0].strSex.value;
		        ajaxFunction(url,'3');
		        //alert(url);
		     }
		     catch(err)
		     {
		         	alert(err);
		     }
	     }
}
function getSubmitPage()
{
		document.forms[0].hmode.value="GO";
		document.forms[0].submit();
}

function submitFun()
{
	if(document.forms[0].strCrNo.readonly != true)
	{
		if(!validateThroughRegExp(document.forms[0].strCrNo,1))
		{
			return;
		}
		var hisValidator = new HISValidator("msApprovalTransBean");
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		var retVal = hisValidator.validate();
		if(retVal){
		 	document.forms[0].hmode.value="GO";
		 	document.forms[0].submit();
		}else{
			return false;
		}
	}else{
		return false;
	}
}
function view1(obj1,obj2,obj3)
{
		document.getElementById(obj1).style.display="none";
		document.getElementById(obj2).style.display="";
		document.getElementById(obj3).style.display="";
}
function view2(obj1,obj2,obj3)
{
		document.getElementById(obj1).style.display="";
		document.getElementById(obj2).style.display="none";
		document.getElementById(obj3).style.display="none";
}

function firstLoad()
{
	if(document.forms[0].strCrNo.value != ""){
		if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No. Entered
		{
			document.forms[0].strCrNo.readOnly = "true";
			document.getElementById("patName1").innerHTML=document.forms[0].strPatName.value;
			document.getElementById("patName2").innerHTML=document.forms[0].strPatName.value;
			//document.getElementById("globalDivId").style.display = "";
			document.getElementById("patEpDtl").style.display = "none";
			document.getElementById("patInformationIdPlus").style.display="";
			//document.getElementById("plusPatDem").style.display = "";
			//document.getElementById("minusPatDem").style.display = "none";
			document.getElementById("plusPatEp").style.display = "none";
			document.getElementById("minusPatEp").style.display = "none";
			document.getElementById("mandCRId").style.display = "none";
			document.getElementById("prevoiusDiagId").style.display="";
			document.getElementById("admissionAdviceId").style.display = "";
			//document.getElementById("patDemDtlId").style.display = "";
			//document.getElementById("patName").innerHTML = document.forms[0].strPatName.value;
			document.getElementById("patOcc").style.display = "";
			if(document.getElementsByName("isOffline")[0].value==1)
			{
				document.getElementsByName("strFormNo")[0].readOnly=true;
			}
			
			if(document.getElementsByName("strPriorityFlag")[0].value == "1")
			{
			    document.getElementsByName("strPriorityType")[1].checked = true;
			    document.getElementById("urge").style.display="block";
			}
			
			document.getElementsByName("strUrgentTypeRemarks")[0].selectedIndex = document.getElementsByName("strPriorityRemarksFlag")[0].value;
			
		}
		else//only predefined Digits in CR Field
		{
			SetCursorToTextEnd('strCrNoId');
			document.forms[0].strCrNo.readonly = false;
			//document.getElementById("globalDivId").style.display = "none";
			document.getElementById("patEpDtl").style.display = "none";
			document.getElementById("mandCRId").style.display = "";
			//document.getElementById("patDemDtlId").style.display = "none";
			document.getElementById("patEp").style.display = "none";
			document.getElementById("patOcc").style.display = "none";
			document.getElementById("admissionAdviceId").style.display = "none";
			
		}
	}else{
		//SetCursorToTextEnd('strCrNoId');
		document.forms[0].strCrNo.readonly = false;
		//document.getElementById("globalDivId").style.display = "none";
		document.getElementById("patEpDtl").style.display = "none";
		document.getElementById("mandCRId").style.display = "";
		//document.getElementById("patDemDtlId").style.display = "none";
		document.getElementById("patEp").style.display = "none";
		document.getElementById("patOcc").style.display = "none";
		document.getElementById("admissionAdviceId").style.display = "none";
		
	}
}

function clear1(){
	document.forms[0].strCrNo.value="";
	document.forms[0].hmode.value="ADD";
	document.forms[0].submit();
}


/**
	   * initGoBillFunc
	   * @param {Event} eve 
	   */
function goFuncOnEnter(eve)
{
	   	
	   	var flag=validateData(eve,5);
  		 if(flag){
	   		
	   		if(eve.keyCode == 13){									
				return submitFun();
			}	   	
	   }else{
	   		return false;
	   }
	   		
}
function showPatDemoDtl(flag)
{
	if(flag=='1')//show
	{
		document.getElementById("globalDivId").style.display="";
		//document.getElementById("minusPatDem").style.display="";
		//document.getElementById("plusPatDem").style.display="none";
	}
	else
	{
		document.getElementById("globalDivId").style.display="none";
		//document.getElementById("minusPatDem").style.display="none";
		//document.getElementById("plusPatDem").style.display="";
	}
}
function showPatEpDtl(flag)
{
	if(flag=='1')//show
	{
		document.getElementById("patEpDtl").style.display="";
		document.getElementById("minusPatEp").style.display="";
		document.getElementById("plusPatEp").style.display="none";
	}
	else
	{
		document.getElementById("patEpDtl").style.display="none";
		document.getElementById("minusPatEp").style.display="none";
		document.getElementById("plusPatEp").style.display="";
	}
}

//Plus Click
function viewX()
{	
	document.getElementById("patInformationIdMinus").style.display="";
	document.getElementById("patInformationIdPlus").style.display="none";
	document.getElementById("id4").style.display="";
}
//Minus Click
function viewY()
{	
	document.getElementById("patInformationIdMinus").style.display="none";
	document.getElementById("patInformationIdPlus").style.display="";
	document.getElementById("id4").style.display="none";
}

function showReason()
{
    if(document.getElementsByName("strPriorityType")[1].checked == true)
    {
      document.getElementById("urge").style.display="";    
    }
    else
    {
     document.getElementById("urge").style.display="none";
    }
    
}
function getOfflineUnit()
{
		//if(document.forms[0].strDepartmentValue.value!="0")
		//{
			var mode ="UNITCOMBO";
			var url="/HBIMS/ipd/transactions/MsApprovalTransCNT.cnt?hmode="+mode+"&strDepartmentCode="+document.forms[0].strDepartmentValue.value+"&puk="+document.forms[0].strCrNo.value;
		    ajaxFunction(url,'5');
	    //}
}
function checkDuplicate()
{
		/*if(document.forms[0].strWard.value!=0)
		{
			var mode="DUPLICATE";
			var url="/HBIMS/ipd/transactions/MsApprovalTransCNT.cnt?hmode="+mode+"&wardCode="+document.forms[0].strWard.value+"&deptUnitCode="+document.forms[0].strUnitValue.value+"&crno="+document.forms[0].strCrNo.value;
			ajaxFunction(url,"4");
			
		}*/
		

}
function initBedAndRoom(){
	
	//document.getElementById("roomTypeDiv").innerHTML = "<input type='hidden' name='strRoomType' value='0'>";
	//document.getElementById("bedTypeDiv").innerHTML = "<input type='hidden' name='strBedType' value='0'>";
}
/*function showBedStatus()
{
		if(document.forms[0].strWard.selectedIndex != 0)
		{
			var hmode = "BEDSTATUS"; 
			//var mode = "ADMISSIONADVICE";
		    var treatmentcategory=document.forms[0].strPrimaryCategory.value;
			
			var url='MsApprovalTransCNT.cnt?hmode='+hmode+'&wardCode='+document.forms[0].strWard.value+'&deptname='+document.forms[0].strDepartment.value+'&sexCode='+document.forms[0].strSex.value+'&age='+document.forms[0].strAge.value+'&treatmentCategory='+document.forms[0].strPrimaryCategory.value
			         +'&unitvalue='+document.forms[0].strUnitValue.value+'&deptcode='+document.forms[0].strDepartmentValue.value+'&deptUnitCode='+document.forms[0].strUnitValue.value+'&crNo='+document.forms[0].strCrNo.value+"&ageCode="+document.forms[0].strUnitCode.value;
			
			var featuresList = "width=400,height=380,ALIGN=CENTER,left=300,top=300,scrollbars=yes"*/
			//myWindow = window.open(url,'popupWindow',featuresList); 
			/*myWindow.focus();
			if(! myWindow.opener)
			{
					myWindow.opener = window;
			}*/
			/*openModalPopUp(url,"400","350","1");
		}
		else
		{
			   alert('Please Select Ward...');
			   document.forms[0].strWard.focus();
			
		}
			return false;
}*/
function secondSubmit()
{
	
	//document.forms[0].strUnitValue.value=document.forms[0].strInitUnit.value;
		if(document.forms[0].strDepartmentValue.length>1)
		document.forms[0].strDepartmentValue.value="${msApprovalTransBean.strDepartmentValue}";
		//alert(document.forms[0].strDepartmentValue.value);
		if(document.forms[0].strDepartmentValue.value.length==3)
		{
			//alert("hi");
			getOfflineUnit();
			//showPatientDetails('pDtlsDivId');
			//showEpisodeDetails('episodeDtlDiv');
		}
}

</script>
</head>
<body onLoad="firstLoad();secondSubmit(); ">
<html:form action="/transactions/MsApprovalTransCNT" method="post" onsubmit="submitFun();">

	<tag:tab tabLabel="MS Approval Add Page" selectedTab="FIRST" width="TABLEWIDTH"></tag:tab>

	<div class="errMsg" id="errMsg"><bean:write
		name="msApprovalTransBean" property="strErrorMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="msApprovalTransBean" property="strMsg" /></div>
	<table class='TABLEWIDTH' align='center' cellspacing='0' cellpadding="0">
		<tr class="HEADER">
			<td colspan="4" width="100%">Approval For Private Ward&gt;&gt;Add Page</td>
		</tr>		
	</table>
	<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">
		<tr>
			<td class="LABEL" width="50%"><font color="red" id="mandCRId">*</font>CR No.</td>
			<td class="CONTROL" width="50%"><crNo:crNo id="strCrNoId"
				value="${msApprovalTransBean.strCrNo}"
				js="onkeypress='return goFuncOnEnter(event)';">
			</crNo:crNo> <img style="cursor: hand; cursor: pointer"
				src="../../hisglobal/images/Go.png" align="top"
				onclick="submitFun();"></td>
		</tr>
	</table>
	
	
	
	<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0">	
		<tr id="patInformationIdPlus" style="display: none;" class="HEADER">
			<td width="100%" ><div id="plusImageId">
			<img src="../../hisglobal/images/plus.gif" onClick="viewX();" style="cursor: pointer;"/>
			&nbsp;&nbsp;
			CR No.:&nbsp;<bean:write name="msApprovalTransBean" property="strCrNo"/>
			&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label id="patName1"></label></div></td>
		</tr>
		<tr id="patInformationIdMinus" style="display: none;" class="HEADER">
			<td width="100%" >
			<div id="minusImageId">
			<img src="../../hisglobal/images/minus.gif" onClick="viewY();" style="cursor: pointer;"/>&nbsp;&nbsp;
			CR No. :&nbsp;<bean:write name="msApprovalTransBean" property="strCrNo"/>
			&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label id="patName2"></label></div></td>
		</tr>
	</table>
	<div id="id4" style="display: none;">
	
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<pDtl:patDtl crNo="${msApprovalTransBean.strCrNo}" address="false"></pDtl:patDtl>
	</table>
	</div>
	
	
	<div id="patEp" style="display: none;">
	<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">
		<tr class="TITLE">
			<td colspan="4" width="100%">
			<div id="plusPatEp">
			<img alt="Show Patient Episode Details" src="../../hisglobal/images/plus.gif" style="cursor: pointer;"
			onclick="showPatEpDtl('1')">&nbsp;&nbsp;Episode Details</div>
			<div id="minusPatEp">
			<img alt="Hide Patient Episode Details" src="../../hisglobal/images/minus.gif" style="cursor: pointer;"
			onclick="showPatEpDtl('2')">&nbsp;&nbsp;Episode Details</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="patEpDtl" style="display: none;">
	<table class='TABLEWIDTH' align='center' cellspacing='1px'
		cellpadding="1px">
		<tr>
			<td class="LABEL" width="25%">Episode No</td>
			<td class="CONTROL"  width="25%"><bean:write
				name="msApprovalTransBean" property="strEpisodeNumber" /></td>
				
			<td class="LABEL" width="25%">Consultant</td>
			<td class="CONTROL"  width="25%">
			<div id="divconsultant"><bean:write name="msApprovalTransBean"
				property="strConsultantName" /></div>
			</td>	
		</tr>
		<tr>
			<td class="LABEL" width="25%">Proposed Department Unit</td>
			<td class="CONTROL" width="25%"><bean:write
				name="msApprovalTransBean" property="strDeptUnitCode" /></td>
			<td class="LABEL" width="25%">Proposed Unit</td>
			<td class="CONTROL" width="25%"><bean:write
				name="msApprovalTransBean" property="strWardName" /></td>	
		</tr>
		
		<tr>
			<td class="LABEL" width="25%"><bean:write
				name="msApprovalTransBean" property="strdateLable" /></td>
			<td width="25%" class="CONTROL"><font color="blue"><bean:write
				name="msApprovalTransBean" property="strPropAdminssionDate" /></font></td>
				
			<td class="LABEL" width="25%">Proposed Surgery Date</td>
			<td width="25%" class="CONTROL"><font color="blue"><bean:write
				name="msApprovalTransBean" property="strSurgeryDate" /></font></td>	
	   </tr>
	   
	   <tr>
			<td class="LABEL" width="25%">Admission Type</td>
			<td width="25%" class="CONTROL"><bean:write
				name="msApprovalTransBean" property="strAdmissionType" /></td>
				
			<td class="LABEL" width="25%">Approximate Length Of Stay</td>
			<td width="25%" class="CONTROL"><bean:write
				name="msApprovalTransBean" property="strLengthOfStay" /></td>	
	   </tr>
		
		<!--  <tr>
			<td class="LABEL" width="25%">Provisional Diagnosis</td>
			<td class="CONTROL" colspan="3" width="75%">
			<div id="divdiagnosis">
				<bean:write
				name="msApprovalTransBean" property="strProvisionDiagnosis" filter="false"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Consultant</td>
			<td class="CONTROL" colspan="3" width="25%">
			<div id="divconsultant"><bean:write name="msApprovalTransBean"
				property="strConsultantName" /></div>
			</td>
		</tr>-->
	</table>
	</div>
	
	<div id="admissionAdviceId" style="display: none">
   <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
     <tr> 
    <td colspan="4" class="TITLE">Admission Advice</td>   
  </tr>
  <tr>
    <td width="25%" class="LABEL"><div align="right"><font color="red">*</font>Department</div></td>
    <td width="25%" class="CONTROL">
    <html:select name="msApprovalTransBean" property="strDepartmentValue" onchange="getOfflineUnit()" styleClass="comboNormal">
    <bean:write name="msApprovalTransBean" property="strDeptComboValues" filter="false"></bean:write>
    </html:select>
    </td>
    <td width="25%" class="LABEL"><div align="right"><font color="red">*</font>Unit</div></td>
    <td width="25%" class="CONTROL"><div id="offlineUnitId">
    <select class='comboNormal' name='strUnitValue'><option value="0">Select Value</option></select></div></td>   
   </tr>
   <tr>
   <td width="25%" class="LABEL"><div align="right">Episode No.</div></td>
    <td width="25%" class="CONTROL">
    <logic:notEqual value="0" name="msApprovalTransBean" property="epFlag">
    <bean:write name="msApprovalTransBean" property="strEpisodeNumber"/>
    </logic:notEqual></td> 
    <td  class="LABEL" width="25%"><div align="right">Treatment Category</div></td>
    <td  class="CONTROL" width="25%">
    <select name="strTreatment" class="comboNormal">
    <bean:write name="msApprovalTransBean" property="strTreatmentCategoryValues" filter="false"/></select></td>
  </tr>
  </table>
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px" >
  <tr>
  <td  class="LABEL" width='25%'><div align="right"><font color="red">*</font>Ward</div></td>
    <td  class="CONTROL" width='25%'>
    	<div id="wardCombo">
    		<select class="comboNormal" name="strWard" onChange="">
    			<option value="0">Select Value</option>
    		</select>
    	</div>
    </td>
    <%-- <td  class="LABEL" width="25%"><div align="right">Bed Status</div></td>
    <td  class="multiControl" width="25%">     
    <img src="/HBIMS/hisglobal/images/Bed_.gif"  style="cursor:pointer;width: 25px;" onClick="return showBedStatus();" 
    align="middle" onmouseover="" title="Click Here To Check Bed Status"/>
    </td>--%>
  </tr>
  </table>
  </div>
	
	<div id="prevoiusDiagId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  	<tr>
    <td colspan="4" class="TITLE" width="100%"><img name="minus"  src="../../hisglobal/images/minus.gif" 
       onClick="view2('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId'); " 
       style="cursor: pointer;display:none;" id="prevoiusDiagMinusId">
       <img name="plus" src="../../hisglobal/images/plus.gif" 
       onClick="view1('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId');" 
       style="cursor: pointer;display:" id="prevoiusDiagPlusId">&nbsp;&nbsp;
          <a style="cursor: pointer; color: " title="Show Previous Diagnosis" 
    	   onclick="view1('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId');">
    	   Previous Diagnosis Details
    	  </a></td>
  	</tr>
 	 </table>
 	 </div>
 	 
 	 
 	 <div id="prevoiusDiagDetailsId" style="display: none">
 	 <bean:write name="msApprovalTransBean" property="strPreviousDiagnosisDetail" filter="false"/>
 	 </div>
 	 
  
	<div id="patOcc">
	<table class='TABLEWIDTH' align='center' cellspacing='1px'>
		<tr class='TITLE'>
			<td colspan="4">Occupation Details</td>
		</tr>
	</table>
	<bean:write name="msApprovalTransBean" property="strOccupation"	filter="false" />
	<table class='TABLEWIDTH' align='center' cellspacing='1px'>
		<tr class='TITLE'>
			<td colspan="4" width="100%">Form Details</td>
		</tr>
		<tr>
			<td class="LABEL" nowrap width="25%"><font color="red">*</font>Form No.</td>
			<td class="CONTROL" width="25%">
			<html:text name="msApprovalTransBean" property="strFormNo" maxlength="15"
				onkeypress='return validateData(event,5);' /></td>
			<td class="LABEL" width="25%"><font color="red">*</font>Submit Date</td>
			<td class="CONTROL" width="25%">
			<date:date name="strRequestDate" value="${msApprovalTransBean.strRequestDate}"></date:date></td>
		</tr>
		<tr>
			<td class="LABEL" nowrap width="25%"><font color="red">*</font>Verified By</td>
			<td class="CONTROL" width="25%">
			<SELECT name="strVerifiedBy" class="comboNormal">
				<bean:write name="msApprovalTransBean"
					property="strEmployeeComboValues" filter="false" />
			</SELECT></td>
			<td class="LABEL" width="25%">Verified Date</td>
			<td class="CONTROL" width="25%">
			<input type="hidden" name="strVerifiedDate" value="${msApprovalTransBean.strVerifiedDate}">
			<font color="blue"><bean:write name="msApprovalTransBean" property="strVerifiedDate" /></font></td>
		</tr>
		<tr>
		<td class="LABEL" nowrap width="25%">Priority Type :</td>
			<td class="CONTROL" width="25%">
			<input type="radio" name="strPriorityType" checked="checked" value="0"  onclick="showReason();">&nbsp;Normal&nbsp;<input type="radio" name="strPriorityType"  value="1" onclick="showReason();">Urgent
			</td>
			<td class="LABEL" width="25%"></td>
			<td class="CONTROL" width="25%">
			</td>
		</tr>
		
		<tr style="display: none;" id="urge">
	  	 <td class="LABEL" nowrap="nowrap"  width="25%"><font color="red">*</font>Priority Reason:</td>
			<td class="CONTROL" width="25%">
			<textarea cols="10" rows="1" name="strUrgentTypeReason">${msApprovalTransBean.strUrgentTypeReason}</textarea>
		 </td>
			
			<td class="LABEL" width="25%"><font color="red">*</font>Remarks:</td>
			<td class="CONTROL" width="25%" >
				<select class="comboNormal" name="strUrgentTypeRemarks" >
					<option value="0">Select Value</option>
  					<option value="1">VIP</option>
  					<option value="2">Staff</option>
  					<option value="3">Employee</option>
  				</select>
			</td>
		</tr>

		
		
		<tr>
			<td class="LABEL" width="25%" colspan="1">Waiting Period For Planned Surgery Date</td>
			<td class="CONTROL" width="75%" colspan="3">
			<input type="text" class="txtFldMin" onkeypress='return validateData(event,5);'
				maxlength="2" name="strWaitPeriod"
				value="${msApprovalTransBean.strWaitPeriod}"></td>
		</tr>
		<tr>
			<td class="LABEL">Remarks</td>
			<td colspan="4" class="CONTROL">
			<html:textarea name="msApprovalTransBean" property="strRemark" /></td>
		</tr>
	</table>
	</div>
	<table class='TABLEWIDTH' align='center' cellspacing='1px'
		cellpadding="1px">
		<tr class="FOOTER">
			<td colspan="5">&nbsp;</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<!-- <img style="cursor: pointer" src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" />
			<img style="cursor: pointer" src="../../hisglobal/images/btn-clr.png" onclick="clear1();" />
			<img style="cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');" />
			 -->
			<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="clear1();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>

	<input type="hidden" name="hmode">
	<html:hidden name="msApprovalTransBean" property="strWardCode" />
	<html:hidden name="msApprovalTransBean" property="strWardType" />
	<html:hidden name="msApprovalTransBean" property="strOccID" />
	<html:hidden name="msApprovalTransBean" property="strAdmNo" />
	<html:hidden name="msApprovalTransBean" property="strApprovedStatus" />
	<html:hidden name="msApprovalTransBean" property="strPropAdminssionDate" />
	<html:hidden name="msApprovalTransBean" property="strPatStatusCode" />
	<html:hidden name="msApprovalTransBean" property="strDeptUnitCmb" />
	<html:hidden name="msApprovalTransBean" property="strAdviceNo" />
	<html:hidden name="msApprovalTransBean" property="strBooking_date" />	
	<html:hidden name="msApprovalTransBean" property="strGovermentEmployeeBasicPayLimit" />
	<html:hidden name="msApprovalTransBean" property="strPrivateEmployeeMonthlyIncomeLimit" />
	<html:hidden name="msApprovalTransBean" property="isOffline" />
	<html:hidden name="msApprovalTransBean" property="strPriorityFlag" />
	<html:hidden name="msApprovalTransBean" property="strPriorityRemarksFlag"/>
	<input type="hidden" name="strUnitCode" value="${msApprovalTransBean.strUnitCode}">
	<input type="hidden" name="strTreatmentCategory" value="${msApprovalTransBean.strTreatmentCategory}">
	<input type="hidden" name="strDepartment" value="${msApprovalTransBean.strDepartment}">	
	<input type="hidden" name="strEpisodeNumber" value="${msApprovalTransBean.strEpisodeNumber}">
	<input type="hidden" name="strVisitValue" value="${msApprovalTransBean.strVisitValue}">
	<input type="hidden" name="strSex" value="${msApprovalTransBean.strSex}">
	<input type="hidden" name="strAge" value="${msApprovalTransBean.strAge}">
	<input type="hidden" name="strPrimaryCategory" value="${msApprovalTransBean.strPrimaryCategory}">
	<input type="hidden" name="strSecondaryCategory" value="${msApprovalTransBean.strSecondaryCategory}">
	<input type="hidden" name="strRoomType" value="">
	<input type="hidden" name="strBedType" value="">
	<input type="hidden" name="flag" value="">
	<input type="hidden" name="gblCRValue"/>
	
	<cmbPers:cmbPers></cmbPers:cmbPers>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>