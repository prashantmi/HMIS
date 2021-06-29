<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ page import ="hisglobal.utility.HisUtil" %> 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Patient Final Discharge</title>

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 
 
 
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet" />
   <script src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script> 
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>
<link href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css" rel="stylesheet">


<link href="../css/newlayout.css" rel="stylesheet" type="text/css">


<!-- <link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css"> -->



<style type='text/css'>
.th{
width: 100px;
}
</style>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>

<script language="Javascript" src="../js/patientDischargeBS.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newMultiRow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/dropdown.js"></script>
<script language="JavaScript" src="../../hisglobal/js/common.js"></script>



<script type="text/javascript">

$(document).ready(function () {
	  $("body").click(function(e) {     
	    if($(e.target).attr('id') === "patInfo") {
	        $("#patSideListId").show();
	    }
	    else {
	        $("#patSideListId").hide();
	    }
	  });
	});

var beforePrint = function() 
{  
	showPrintableSlip();
};
var afterPrint = function() 
{               
	hidePrintableSlip();
};
window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;

function getDischargeData() // fro Setting Data on Dischrge type "strTransferUnit" 
{
	
	
    var strReferral=true;
    var strDeath=true;
    var strAbsconded=true;
    var strNormalDischarge=true;
    if(document.forms[0].strDischargeTypeReferral.value==0)
		   strReferral=false;
    if(document.forms[0].strDischargeTypeDeath.value==0)
		   strDeath=false;
    if(document.forms[0].strDischargeTypeAbsconded.value==0)
    	strAbsconded=false;
    if(document.forms[0].strNormalDischargeType.value==0)
    	strNormalDischarge=false;
	getDisBy();
	document.forms[0].strCurrentDeptUnitRoom.value=document.forms[0].curDept_Unt_RomCode.value;
		if(	document.getElementsByName("strTransferUnit")[0].value==(strDeath?document.forms[0].strDischargeTypeDeath.value : "10"))		// Discharge Death - 10
		{
			if(document.getElementsByName("strDeathDetailsRequired")[0].value == "1")
			{
				document.getElementById("deathDetails").style.display="block";
				document.getElementById("abscondedDetails").style.display="none";
				document.getElementById("dischargeAdvBy").innerHTML = "<font color='red'>*</font>Death Verified By";
			}
			document.getElementById("strTreatmentResultid").style.display="";
		}
		else if(document.getElementsByName("strTransferUnit")[0].value==(strAbsconded?document.forms[0].strDischargeTypeAbsconded.value : "12"))		// Discharge Abscoded - 12
		{
			document.getElementById("deathDetails").style.display="none";
			//document.getElementById("abscondedDetails").style.display="";
			document.getElementById("dischargeAdvBy").innerHTML = "Discharge Advised By";
			document.getElementById("strTreatmentResultid").style.display="";
			
			
		}
		else
		{
			document.getElementById("deathDetails").style.display="none";
			document.getElementById("dischargeAdvBy").innerHTML = "<font color='red'>*</font>Discharge Advised By";
			document.getElementById("strTreatmentResultid").style.display="";
		}
		
		
		if(	document.getElementsByName("strTransferUnit")[0].value==(strNormalDischarge ? document.forms[0].strNormalDischargeType.value : "8")	// Normal Dicharge - 8 
			|| document.getElementsByName("strTransferUnit")[0].value==(strReferral?document.forms[0].strDischargeTypeReferral.value : "13"))	// Referred - 13
		{
			document.getElementById("adviseByID").style.display="block";
			document.getElementById("visitOPD").style.display="block";
	 		document.getElementById("idDischargeType").style.display="none";
	 		document.getElementById("strTreatmentResultid").style.display="";
		}
		else	// Case of Other Discharge Types
		{
			if(document.getElementsByName("strTransferUnit")[0].value==(strDeath?document.forms[0].strDischargeTypeDeath.value : "10"))		// Discharge Death - 10
			{
				if(	document.getElementsByName("strDeathDetailsRequired")[0].value == "1")
				{
					var currDtUtWrRmBd = document.forms[0].curDtUtWrRmBd.value;
					var mode="transOf";
					var url=document.forms[0].cnt.value+"?hmode="+mode+"&modName="+document.getElementsByName("strTransferUnit")[0].value+"&currDtl="+currDtUtWrRmBd;
					ajaxFunction(url,"119");
				}
				else
				{
					document.getElementById("adviseByID").style.display="block";
					document.getElementById("visitOPD").style.display="block";
			 		document.getElementById("idDischargeType").style.display="none";
				}
				
			}
			else
			{
				var currDtUtWrRmBd = document.forms[0].curDtUtWrRmBd.value;
				var mode="transOf";
				var url=document.forms[0].cnt.value+"?hmode="+mode+"&modName="+document.getElementsByName("strTransferUnit")[0].value+"&currDtl="+currDtUtWrRmBd;
				ajaxFunction(url,"119");
			}			
	    }
}

function openDivScreen()
{
	
}
function setCode(objVal,index)
{
	if(objVal.value.toUpperCase()!=""){
		document.getElementById("strProvisionDiagnosis"+index).value=objVal.value.toUpperCase();
		
		if(document.getElementById("strProvisionDiagnosis"+index).value=="")
			document.getElementById("strProvisionDiagnosis"+index).value="0";
	}	
	else
	{
		document.getElementById("strProvisionDiagnosis"+index).value="0";
	}
}



function getAjaxResponse(res,mode)
{
	    if(mode == '9')
	    {
		     var objEle =  document.getElementById("infoMLC");
		     var objEle1 =  document.getElementById("visitOPD");
		     var myRes=new Array();
		     myRes=res.split("^");
		     if(myRes[0]!="")
		     	document.getElementById("infoMLC").style.display="block";
		     objEle.innerHTML  = myRes[0];
		     objEle1.innerHTML = myRes[1];
		}
		else
		{
			if(res.split("####")[0]=="Error")
			{
				document.getElementById("errMsg").innerHTML=res.split("####")[1];
			}
			else if(mode == '1')
			{
				var objEle = document.getElementById("diagnosisCombo");
				objEle.innerHTML = "<font color='red'>*</font><select class='comboBig' name='strProvisionDiagnosis' id='strProvisionDiagnosis#delIndex#' >"+res+"</select>";
				addRows(new Array('strProvisionDiagnosis'),new Array('s'),'1','1','I');
				//tmp();
			}
			else if(mode == '2')
			{
				var objEle = document.getElementById("diagnosisCombo");
				objEle.innerHTML = "<font color='red'>*</font><select class='comboBig' name='strProvisionDiagnosis' onchange='copyCode(this)' id='strProvisionDiagnosis#delIndex#'>"+res+"</select>";
				addRows(new Array('strProvisionDiagnosis'),new Array('s'),'1','1','I');
				document.getElementById("dropdown1").innerHTML = res;
				//document.getElementById("dropdown1").style.display="";				 
				 searchSelWithCode(tempCode, gblParentObj.name+""+gblIndex ,'1',gblParentObj);	
				 //document.getElementById(gblParentObj.name+""+gblIndex).focus();
				//tmp();	
			}
			
			else if(mode=='3')
			{
				var objEle =  document.getElementById("dischargeParam");
				if(res.trim()!="")
				{
					objEle.innerHTML = res;
					showDetails3();
				}
				document.getElementsByName("strTmpFlag")[0].value="1";
			}
			else if(mode == '500')
			{
	   			var val = res.split("###");    	
    			if(val[0] == "ERROR")
    			{
	    			document.getElementById("errMsg").innerHTML = val[1];
    			}
	 		}
		}
		if(mode == '20')
		{
				//alert(res);
				document.getElementById("dropdown1").innerHTML = res;
			 	searchSelWithCode(tempCode, gblParentObj.name+""+gblIndex ,'1',gblParentObj);	
		}
		if(mode == '4') 
		{
			document.getElementsByName("strICD10CodeValues")[0].value=res.split("~^~")[0];
			try
			{
				document.getElementsByName("strHospICDCodeValues")[0].value=res.split("~^~")[1];
			}
			catch(_err){
			}
	 		try
	 		{
				arrValues=res.split("#")[0].split("^");
			}
			catch(_err){
			}
			try
			{
				arrValues1=res.split("#")[1].split("^");
			}
			catch(_err)
			{
			}
			try
			{
				createArray('icd10Id');
			}
			catch(_err){
			}
 		}
		if(mode=='119')
		{
			if(res.trim()=="")
			{
				document.getElementById("adviseByID").style.display="block";
				document.getElementById("visitOPD").style.display="block";
				document.getElementById("idDischargeType").style.display="none";
			}
			else
			{
				var temp=res.split('^');
				var objVal = document.getElementById("idDischargeType");
				objVal.innerHTML = temp[0];
				document.getElementById("idDischargeType").style.display="block";
				if(temp[1]==0)
				{
					document.getElementById("adviseByID").style.display="none";
					document.getElementById("visitOPD").style.display="none";
				}
			}
		}
		if(mode=='10')
		{
			//alert("res"+res);
			document.getElementById("idDisBy").innerHTML = "<select class='browser-default custom-select' title='Doctors List' name='strRmk'>"+res+"</select>";
			if(typeof(document.forms[0].strConsultantId)=="undefined")
				document.forms[0].strRmk.value=0;
			else
				document.forms[0].strRmk.value=document.forms[0].strConsultantId.value;			
		}
}

function autotab(original,destination)
{
		if (original.getAttribute&&original.value.length==original.getAttribute("maxlength"))
		destination.focus()
}

/*
function createArray(_tableId){
	var array1 = new Array('<font size="2" title=\'Enter ICD10 Code\' color="red">*</font><input type=text tabIndex=1 name=strProvisionDiagnosis >(ICD10 Code) <font size="2" color="red">*</font><input tabIndex=1 type=text  name=strIcdCode id=\'#strIcdCode#index#\' style="width: 300px"  ><INPUT TYPE=HIDDEN NAME=\'strICD10CodeHidden\'>');
	var array = array1;
	multiRow(_tableId,array, 1, 1, true);
	var objICD10Text = document.getElementsByName('strIcdCode');
	var strProvisionDiagnosis = document.getElementsByName('strProvisionDiagnosis');
	
	if(document.getElementsByName('strDiagnosisType')[1].checked==true){
		var obj1 = new actb(objICD10Text[objICD10Text.length-1],document.getElementsByName("strICD10CodeValues")[0].value.split("#")[0].split("^"));
		obj1.actb_userdefine_func = "copyCode";
		var obj2 = new actb(strProvisionDiagnosis[strProvisionDiagnosis.length-1],document.getElementsByName("strICD10CodeValues")[0].value.split("#")[1].split("^"));
		obj2.actb_userdefine_func = "copyText";
	} else {
		var obj1 = new actb(objICD10Text[objICD10Text.length-1],document.getElementsByName("strHospICDCodeValues")[0].value.split("#")[0].split("^"));
		obj1.actb_userdefine_func = "copyCode";
		var obj2 = new actb(strProvisionDiagnosis[strProvisionDiagnosis.length-1],document.getElementsByName("strHospICDCodeValues")[0].value.split("#")[1].split("^"));
		obj2.actb_userdefine_func = "copyText";
	}
}*/
function hideDivIpd()
{
	if(document.forms[0].callFromIpdDesk.value=="1")
	{
		document.getElementById("ipdhide").style.display="none";
		document.getElementById("hideClear").style.display="none";
		document.getElementById("hideMan").style.display="none";
	}
}

var t= new Date();
var h=t.getHours();
var m= t.getMinutes();
var s= t.getSeconds();
var ap=
function current_time()
{

var strAbsHour=document.getElementById('strAbsHour');
strAbsHour.value=h;
var strAbsMin=document.getElementById('strAbsMin');
strAbsMin.value=m;
var strAbsSec=document.getElementById('strAbsSec');
strAbsSec.value=s;

}
function getAmPm(){
	if(document.getElementsByName("strDisDate")==t)
		{
		var hours = h;
		var hours = (hours+24-2)%24; 
		var mid='am';
		if(hours==0)
			{ 
			hours=12;//At 00 hours we need to show 12 am
			}
		else if(hours>12)
		{
			hours=hours%12;
				mid='pm';
		}
	if(document.getElementsByName("strAbsAmPm").value!=mid){
		alert(mid+"can not be changed!!")
		}
	}
}
function check_hour()
{
	var hh=document.getElementById('strAbsHour');
	if(document.getElementsByName("strDisDate")==t && hh.value<h||hh.value==h)
		return true;
	else if(document.getElementsByName("strDisDate")==t && hh.value>h)
		{
		alert("Hours can not be more than current time!!");
		document.getElementById('strAbsHour').value="";
		document.getElementById('strAbsHour').focus();
	return false;
	}
	else return true;
}
function check_min()
{
	var mm=document.getElementById('strAbsMin');
	if(document.getElementsByName("strDisDate")==t && mm.value<m||mm.value==m)
		return true;
	else if(document.getElementsByName("strDisDate")==t && mm.value>m)
		{
		alert("Minutes can not be more than current time!!");
		document.getElementById('strAbsMin').value="";
		document.getElementById('strAbsMin').focus();
	return false;
	}
	else return true;
}
function time_blank()
{
	 if(document.getElementById('strAbsHour').value==""){
		alert("Hours can not be blanked");
		document.getElementById('strAbsHour').focus();
		return false;
				}
			 else if(document.getElementById('strAbsMin').value==""){
					alert("Minutes can not be blanked");
					document.getElementById('strAbsMin').focus();
					return false;
							}
			 else return true;
}

</script>


</head>
<body onLoad="checkMsg();onLoadHideMLC();butdis1();hlpOnLoadDisParam();hideDivIpd();printReport();openPrintPopUp();" onfocus="checkPopUpAndSetDefaultCrNo();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/PatientFinalDischargeBSCNT.cnt" method="post">
	<div id="menu1"></div>
	<div id="ipdhide"></div>	
	<div id="patTldglbdiv"></div>
	
					
			
	<fieldset>
	<legend class='legendHeader' id='nonPrintableLegend'>Patient Final Discharge</legend>
		  <div class="legend2" id='nonPrintableLegend2'>
			<button  type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="closeTabOnDesk();">
				<i class="fa fa-ban iround"  title="Cancel"></i>
			</button>
			
			<button  type="button" id="savebutton" class="btn btn-outline-success mt-1 btn-circle savebtn"  tabindex='2' onClick="startValidation1();"   data-toggle="modal" data-target="#validateModal" style="display: none;">					
				<i class="fa fa-save iround"  title="Save"></i>
			</button>
																						                 
		  </div>
  		  
  		  <div class="container-fluid">
				<div class="viewport" id="nonPrintable">
		  		  <div class="alert alert-danger alert-dismissible fade show" id="errMsg" style="display: none;"><bean:write name="patientDischargeBean" property="strErrMsgString" /></div>
				  <div class="alert alert-success alert-dismissible fade show" id="normalMsg" style="display: none;"><bean:write name="patientDischargeBean" property="strNormalMsgString" /></div>
			
					<div id="patDtlHeader" style="display: none"></div>
						<div id="patDtlTld" style="display: block;">							
							<pDtl:patDtlNew crNo="${patientDischargeBean.strCrNo}" address="false"></pDtl:patDtlNew>
						</div>						
						<div id="admDtlTld" style="display: none">
							<aDtl:addDtlNew crNo="${patientDischargeBean.strCrNo}"></aDtl:addDtlNew>
						</div>
					<div class="prescriptionTile">
						<div id='goBox'>
							<div class="row rowFlex reFlex">												
                                   <div class="col-sm-2" align="right"><font  color="red">*</font><label>CR No.</label></div>								<div class="col-sm-2">
									<div id="patientCrEdId" >
										<crNo:crNo id="strCrNoId" value="${patientDischargeBean.strCrNo}" js=" onblur='Trim(this)' ;onkeyup='lTrim(this)'; onkeypress='return isEnter(event);return validateData(event,5);';" className='form-control'></crNo:crNo>
									</div>													
								</div>
								<div class="col-sm-2" style="padding-left: 0 "> <span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searhPatientImageId" title="Click here for Patient Search" name='searchPatient' data-toggle="modal" data-target="#searchModel"  onclick="fetchPatientList_BS1('1','1','Discharge')"></span>
													 <a href="#" class="btn btn-sm btn-success go" onclick="return goFunc();" data-toggle="modal" data-target="#validateModal">
                                                      GO&nbsp;<i class="fas fa-angle-double-right"></i>
						                             </a>
						                             </div>
								<!-- <div class="col-sm-2">
									<span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searhPatientImageId" title="Click here for Patient Search" name='searchPatient' data-toggle="modal" data-target="#searchModel"  onclick="showPatientListingWindow('5',document.forms[0].strCrNo,'setSelectedCrNo');"></span>
									<a href="#" class="btn btn-sm btn-success" onclick="return goFunc1('patientDischargeBean');"  data-toggle="modal" data-target="#validateModal" style="font-size: 1rem;">
				                           	GO&nbsp;<i class="fas fa-angle-double-right"></i>
				                    </a>
				               	</div> -->
								<div class="col-sm-2"></div>
								<div class="col-sm-4"></div>
							</div>															
						</div>						
				
					<div id="dischargeOnlineId">
					<div id="finalDischParam" >
					<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
						<tr>
							<td width='100%' class='TITLE' align='center'>
							<div id="plusonLineId3"><img style="cursor: pointer;"
								src="../../hisglobal/images/plus.gif" name="plusonLine3"
								onclick="getPatDischargeParameterDetail();">&nbsp;&nbsp;Discharge Parameter</div>
							<div id="minusonLineId3" style="display: none"><img
								style="cursor: pointer;" src="../../hisglobal/images/minus.gif"
								name="minusonLine3" onclick="hideDetails3();">&nbsp;&nbsp;Discharge Parameter</div>
							</td>
						</tr>
					</table>
					</div>
					<div id="dischargeParam"><bean:write name="patientDischargeBean" property="strPatientDisParam" filter="false" /></div>
				
				
					<div id="finalDiag" style="display: none;">
					<table class="TABLEWIDTH" align="center" cellspacing="0px">
						<tr>
							<td width="5%" class="TITLE"><img
								src="../../hisglobal/images/plus.gif" id="plusImg"
								onclick="showDetails4()" style="cursor: pointer; display: none" />
							<img src="../../hisglobal/images/minus.gif" id="minusImg"
								onclick="hideDetails4()" style="cursor: pointer;" /></td>
							<td class="TITLE" width="90%">
							<div>Final Diagnosis</div>
							</td>
							<td width="5%" class="TITLE"></td>
						</tr>
				
					</table>
					</div>
					
					<div id="finalDetail">
					<table class="TABLEWIDTH" align="center" cellspacing="1px">
						<bean:write name="patientDischargeBean"
							property="strProvisionDiagnosisValues" filter="false" />
						<tr>
						<logic:equal name="patientDischargeBean" property="strDiagType" value="0">
						<td colspan="2" class="TITLE" width="50%">
						  <div id='HospitalDiagnosisId'>
						  <html:radio name="patientDischargeBean" property="strDiagnosisType" value="1" 
						  onclick="">Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div>
						</td>
					 	<td colspan="1" class="TITLE" width="48%">
				    	 <div id='icdDiagnosisId'>
				    	 <html:radio name="patientDischargeBean" property="strDiagnosisType" value="0" 
				    	 onclick="">ICD10 Diagnosis</html:radio></div></td>
						<td colspan="1" class="TITLE" width="2%"><img src="../../hisglobal/images/plus.gif"
								onclick="addRows(new Array('strProvisionDiagnosis','strIcdCode','strICD10CodeHidden'),new Array('t','t','t'),'1','1','R');"
								style="cursor: pointer;" /></td>
						</logic:equal>
						<logic:notEqual name="patientDischargeBean" property="strDiagType" value="0">
						<logic:equal name="patientDischargeBean" property="strDiagType" value="2">
						<td colspan="1" class="TITLE" width="98%">
					  	<div id='hospitalDiagnosisId'>
					  	<html:radio name="patientDischargeBean" property="strDiagnosisType" value="1" 
					  		onclick="changeMultiRows();">Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div></td>
						 <td colspan="1" class="TITLE" width="2%"><img src="../../hisglobal/images/plus.gif"
								onclick="addRows(new Array('strProvisionDiagnosis','strIcdCode','strICD10CodeHidden'),new Array('t','t','t'),'1','1','R');"
								style="cursor: pointer;" /></td>
					 	</logic:equal>
						<logic:equal name="patientDischargeBean" property="strDiagType" value="1">
						<td colspan="1" class="TITLE" width="98%">
				    	 <div id='icdDiagnosisId'>
				    	 <html:radio name="patientDischargeBean" property="strDiagnosisType" value="0" 
				    	 onclick="changeMultiRows();">ICD10 Diagnosis</html:radio></div></td>
						<td colspan="1" class="TITLE" width="2%"><img src="../../hisglobal/images/plus.gif"
							onclick="addRows(new Array('strProvisionDiagnosis','strIcdCode','strICD10CodeHidden'),new Array('t','t','t'),'1','1','R');"
							style="cursor: pointer;" /></td>
						</logic:equal>
						</logic:notEqual>
						</tr>
					</table>
				
					<table id="icd10Id"  class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
						<tr></tr>
					</table>
					</div>
					<div id='id1'></div>
				</div>
					
							
							<div id="discDtlHeader" style="display: none">
			      				<p class="subHeaders"><i class="fas fa-university isubheaders">&nbsp;</i>Discharge Details</p>	                    
							</div>
				
							<div class="row rowFlex reFlex" id="transChng" style="display: none">												
								<div class="col-sm-2" align="right"><font color="red">*</font><label>Discharge Date</label></div>
								<div class="col-sm-4">
									<!-- <date:date name="strDisDate" value="${patientDischargeBean.strDisDate}" ></date:date>	-->
									<input type='text'  id='datetimepicker1' name="strDisDate" class='form-control' >
								</div>
								<!-- <div class="col-sm-2">
									<input class='txtFldSmall' name='strAbsHour' id='strAbsHour' maxlength='2'onkeypress='return validateData(event,5);' onkeyup="hour(this,event);autotab(this,document.forms[0].strAbsMin);return check_hour();" type='text' value="${patientDischargeBean.strAbsHour}" ><b>:</b>
									<input class='txtFldSmall' name='strAbsMin' id='strAbsMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup="min1(this,event);autotab(this,document.forms[0].strAbsSec);return check_min();" type='text' value="${patientDischargeBean.strAbsMin}"><b>:</b>
									<input class='txtFldSmall' name='strAbsSec' id='strAbsSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup="sec1(this,event)" type='text' value="${patientDischargeBean.strAbsSec}" >
			
									<select class='comboSmall' name='strAbsAmPm' id='strAbsAmPm' onChange='getAmPm'>			
										<option value='${patientDischargeBean.strAbs1}'> ${patientDischargeBean.strAbs1}</option>
										<option value='${patientDischargeBean.strAbs2}'>${patientDischargeBean.strAbs2}</option>
									</select>
									
								</div> -->
								<div class="col-sm-2"><div id="disTypeId" align="right"><font color="red">*</font><label>Discharge Type</label></div></div>
								<div class="col-sm-4">
										<div id="disTypeId1">
											<select name="strTransferUnit" class='browser-default custom-select' title="Nature of Discharge" onChange="setDisType();">
												<bean:write name="patientDischargeBean" property="strPatientDischargeTypeComboValues" filter="false" />
											</select>
										</div>
										<div id="disTypeOnlineId"></div>
								</div>												
							</div>
							
					
					<div id='idDischargeType'></div>
					<div id="infoMLC" style="display: block;"><bean:write name="patientDischargeBean" property="strHlpMLC" filter="false" /></div>				
					<div id="disBnR" style="display: none">
					<div style="display: none;" id="id2"></div>
					<bean:write name="patientDischargeBean" property="strDeathDetails" filter="false" />
					<bean:write name="patientDischargeBean" property="strAbscondedDetails" filter="false" />			
				
					<div id="online" style="display: none">
					<table class="TABLEWIDTH" border="0" align="center" border='0'
						cellspacing='1px'>
						<tr>
							<td width='25%' class='LABEL'>Discharge Advised By</td>
							<td width="25%" class="CONTROL"><bean:write name="patientDischargeBean" property="strAdvisedBy"></bean:write></td>
							<td width='25%' class='LABEL'>Discharge Advised Date</td>
							<td width="25%" class="CONTROL"><bean:write name="patientDischargeBean" property="strAdviceDate"></bean:write></td>
						</tr>
						<tr>
							<td width="25%" class="LABEL">
							<div align="right">Discharge Advice</div>
							</td>
							<td width="75%" colspan='3' class="CONTROL">
							<bean:write name="patientDischargeBean" property="strRemarksOnline" filter="false"></bean:write></td>
						</tr>
					</table>
					</div>
					<div id="offline">
					<div class="row rowFlex reFlex" >
						<div class="col-sm-2" align="right">
							<div id='adviseByID'>
								<div id="dischargeAdvBy" align="right"><font color="red">*</font><label>Discharge Advised By</label></div>
							</div>
						</div>
						<div class="col-sm-4">
							<div id="idDisBy">
									<select tabindex='1' class='browser-default custom-select' title="Employee Code and List of Doctors" name="strRmk">
										<bean:write name="patientDischargeBean" property="strRmk" filter="false" />
									</select>
							</div>
						</div>
						<div class="col-sm-2" id="strTreatmentResultid" align="right"><label>Treatment Result</label></div>
						<div class="col-sm-4">
								<select class='browser-default custom-select' title="Treatment Result" name="strTreatmentResult">
									<bean:write name="patientDischargeBean" property="strTreatmentResultComboValues" filter="false" />
								</select>
						</div>
					</div>
					<div class="row rowFlex reFlex">
						
						<div class="col-sm-2" id="dischargeAdvice" align="right">
							<font color="red">*</font><label>Discharge Advice</label>
						</div>
						<div class="col-sm-10">
							<textarea rows="3" cols="25" class="form-control" tabindex='1' name="strRsn"  id="remarksId"></textarea>		
							<div style="position: absolute;top: 0;right: 16px;">
								<button type="button" onclick="$(this).parent().parent().find('#remarksId').val('');" class="btn btn-sm btn-default reasonOfVisitCleanBtn">
								<i class="fa fa-times" aria-hidden="true"></i>
								</button>
							</div>
						</div>
					</div>
					</div>
					</div>
					
					
						<!-- <div id="tariffNamePartDivId">
							<table class="TABLEWIDTH" align="center" cellpadding="0px" cellspacing="0px">
								<tr>
									<td width="15%" class="CONTROL" style="color: blue; font-weight: bold">
									<div id="tariffFullNameDiv"></div>
									</td>
								</tr>
							</table>
						</div> -->
						
						<div id="visitOPD" style="display: block"><bean:write name="patientDischargeBean" property="strHlpOPD" filter="false" /></div>
					<hr>
						<div class="row rowFlex reFlex">
							<div class="col-sm-10"></div>
							<div class="col-sm-2" align="right"><i class="fa fa-asterisk"  style="color: red;font-size: smaller;"></i>Fields Mandatory</div>				
						</div>
					</div>							
					</div>					
			<input type="hidden" name="strDeathDateAndTime">
			<input type="hidden" name="strOnsetDeathDateAndTime">
			<input type="hidden" name="strVerifyDateAndTime">
			<input type="hidden" name="strHandoverDateAndTime">
			<input type="hidden" name="strShiftDateAndTime">
			
			<input type="hidden" name="strFOfflineDeath" >
			<input type="hidden" name="strDeathFlag" >
			<input type="hidden" name="strTmpFlag">
			<input type="hidden" name="strTmpFlag1">
			<input type="hidden" name="hmode">
			<input type="hidden" name="strAbscondedValue" value="${patientDischargeBean.strAbscondedValue}" />
			<input type="hidden" name="strTempVal" value="">
			<input type="hidden" name="strErrMsgString" value="${patientDischargeBean.strErrMsgString}">
			<input type="hidden" name="strNormalMsgString" value="${patientDischargeBean.strNormalMsgString}">
			<input type="hidden" name="strPopUp_id">
			<input type="hidden" name="cnt" value="">
			<input type="hidden" name="strBId" value="${patientDischargeBean.strBId}">
			<input type="hidden" name="strDischrg_Mode" value="${patientDischargeBean.strDischrg_Mode}">
			<input type="hidden" name="strDischrg_Param_Req" value="${patientDischargeBean.strDischrg_Param_Req}">
			<input type="hidden" name="strDischargeDiagnosisRequired" value="${patientDischargeBean.strDischargeDiagnosisRequired}">
			<input type="hidden" name="strDischargeAdviceFieldRequired" value="${patientDischargeBean.strDischargeAdviceFieldRequired}">
			<input type="hidden" name="strDischargeSummaryPrintRequired" value="${patientDischargeBean.strDischargeSummaryPrintRequired}">
			<input type="hidden" name="strDisplay_MLC_Dtls" value="${patientDischargeBean.strDisplay_MLC_Dtls}">
			<input type="hidden" name="strDisplay_OPD_Visit_Dtls" value="${patientDischargeBean.strDisplay_OPD_Visit_Dtls}">
			<input type="hidden" name="strSaveStatus" value="${patientDischargeBean.strSaveStatus}" />
			<input type="hidden" name="strAdmissionNo" value="${patientDischargeBean.strAdmissionNo}" />
			<input type="hidden" name="strNormalMsgString" value="${patientDischargeBean.strNormalMsgString}" />
			<input type="hidden" name="strIsDead" value="${patientDischargeBean.strIsDead}" />
			<input type="hidden" name="strApplicationMode" value="${patientDischargeBean.strApplicationMode}" />
			<input type="hidden" name="strDischargeType" value="${patientDischargeBean.strDischargeType}">
			<input type="hidden" name="strAdmStatCode" value="${patientDischargeBean.strAdmStatCode}" />
			<input type="hidden" name="strICD10CodeValues">
			<input type="hidden" name="strHospICDCodeValues">
			<input type="hidden" name="strCurrentDeptUnitRoom">
			<input type="hidden" name="strDischargeTypeLAMA" value="${patientDischargeBean.strDischargeTypeLAMA}">
			<input type="hidden" name="strDischargeTypeAbsconded" value="${patientDischargeBean.strDischargeTypeAbsconded}">
			<input type="hidden" name="strDischargeTypeReferral" value="${patientDischargeBean.strDischargeTypeReferral}">
			<input type="hidden" name="strDischargeTypeTransfer" value="${patientDischargeBean.strDischargeTypeTransfer}">
			<input type="hidden" name="strDischargeTypeDeath" value="${patientDischargeBean.strDischargeTypeDeath}">
			<input type="hidden" name="strDeathDetailsRequired" value="${patientDischargeBean.strDeathDetailsRequired}">
			<input type="hidden" name="strNormalDischargeType" value="${patientDischargeBean.strNormalDischargeType}">
			<input type="hidden" name="strProfileId" value="${patientDischargeBean.strProfileId}" />
			<input type="hidden" name="callFromIpdDesk" value="${patientDischargeBean.callFromIpdDesk}" />
			<input type="hidden" name="gblCRValue"/>
			<input type="hidden" name="strSlipCrNo" value="${patientDischargeBean.strSlipCrNo}" />
			<input type="hidden" name="strRemarksOnline" value="${patientDischargeBean.strRemarksOnline}" />
			<input type="hidden" name="strAdvisedBy" value="${patientDischargeBean.strAdvisedBy}" />
						
</div>
						

<div id='mainDiv1' style=''>
<div class="modal fade " id="searchModel"   tabindex="-1" role="dialog" aria-labelledby="validateModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document" style="max-width: 1000px;">
    <div class="modal-content">
    <div class="modal-header">
	 <h4 class="modal-title">Patient Listing</h4>
		<button type="button" 
		class="close" data-dismiss="modal">&times;</button></div>
	
      <div  class="modal-body" id ="fetchRecordDivId"  >
     
      </div>
       
      <div class="modal-footer">
        <!-- <button  type="button" class="btn btn-success btn-sm ml-auto" style="font-size: 1rem" name="paientAdmissionTransBean" title="Select the Patient">Ok<i class="fa fa-check"></i></button>					
		<button  type="button" class="btn btn-danger btn-sm mr-auto" data-dismiss="modal" style="font-size: 1rem" name="paientAdmissionTransBean" title="Cancel and Close the Window">Cancel<i class="fa fa-times-circle-o"></i></button> -->	
      </div>
    </div>
  </div>
</div>
</div> 
		
<div id='mainDiv' style='display:none;'>
<div class="fade" id="validateModal"   tabindex="-1" role="dialog" aria-labelledby="validateModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <div class="modal-body">
           <h5 id='warn'></h5>
           <p id='len'></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>

      </div>
    </div>
  </div>
</div>		
</div>	
	</fieldset>
	
	
</html:form>

<script>
    $('#datetimepicker1').datetimepicker({ footer: true, modal: true,format: 'dd-mmm-yyyy hh:MM TT' });
    
    
function setDisType() // Called on Change of Discharge Type "strTransferUnit"
{	
    var strAbsconded=true;
    if(document.forms[0].strDischargeTypeAbsconded.value==0)//No Parameter Set For Absconded in IN Patient Config Mst
		   strAbsconded=false;
	var strAdmStatus = document.getElementsByName("strAdmStatCode")[0].value;
	if(strAdmStatus==11 || strAdmStatus==15 || strAdmStatus==17)
	{// Admission Status 11-On Transit 15-On Leave, 17-??-On Transit(outside)
		document.getElementsByName("strTransferUnit")[0].value=(strAbsconded?document.forms[0].strDischargeTypeAbsconded.value : "12");	// Setting Discharge Type-"12" Absconded
	}	
	getDischargeData();
}

if(document.getElementsByName("strCrNo")[0].value!="" && checkCrdef(document.getElementById("strCrNoId"))==false)
	setDisType();

if(document.getElementsByName("strDischargeType")[0].value==1)
{
	document.getElementById("offline").style.display="none";
	document.getElementById("online").style.display="block";
	
	document.getElementById("disTypeOnlineId").innerHTML="<font size='2' color='blue'>Discharge Type: "+"${patientDischargeBean.strDischargeTypeName}"+"</font>";
	document.getElementById("disTypeId").style.display="none";
	document.getElementById("disTypeId1").style.display="none";
	document.getElementById("dischargeOnlineId").style.display="none";
	document.getElementById("strTreatmentResultid").style.display="none";
}
else if(document.getElementsByName("strCrNo")[0].value.length<=11)
{
	document.getElementById("dischargeOnlineId").style.display="none";
}
else
{
	document.getElementById("dischargeOnlineId").style.display="block";
}
//if(document.getElementsByName("strCrNo")[0].value.length>=12 && document.getElementsByName("strDischargeType")[0].value==2)
//	getICDDetails();
</script>

<div class="modal fade" id="printModal" role="dialog" >
  <div class="modal-dialog modal-lg" role="document" style="">
    <div class="modal-content">
    <div class="modal-header"><button type="button" data-dismiss="modal" onclick="modalSlipPrint()" class="btn btn-primary" ><i class="fa fa-print" title="Print Last Slip"></i>&nbsp;Print</button></div>
    <div class="modal-body" id='printableSlip'>
	<logic:equal name="patientDischargeBean"  property="strSaveStatus" value="1">
         <tiles:insert  page="/ipd/transactions/PatientFinalDischargeBSCNT.cnt?hmode=DISCHARGESLIP&strCrNo=${patientDischargeBean.strSlipCrNo}&strAdmNo=${patientDischargeBean.strAdmissionNo}&duplicateMode=0"/>
           </logic:equal>
	</div>
       
    </div>
  </div>
</div>

<%--  <logic:equal name="patientDischargeBean"  property="strSaveStatus" value="1">
<script>	

		$('#printModal').modal('show');
</script>
  </logic:equal>  --%>
<%-- <div id='printableSlip' style='display: none'>
<logic:equal name="patientDischargeBean"  property="strSaveStatus" value="1">
<tiles:insert  page="/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode=DISCHARGESLIP&strCrNo=${patientDischargeBean.strSlipCrNo}&strAdmNo=${patientDischargeBean.strAdmissionNo}&duplicateMode=0"/>
</logic:equal>
</div> --%>
<jsp:include page="admissionadvice_dropdown_ipdtranswithoutDesk.jsp"></jsp:include>
<jsp:include page="patFinalDischarge_multirow_ipdtranswithoutDesk.jsp"></jsp:include>

</body>
</html>