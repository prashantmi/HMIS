<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="ipd.*" %>

<html>
<head>
<title>Admission Advice Online</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
 <link href="../css/newlayout.css" rel="stylesheet" type="text/css">
 

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
 
 
 <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />








<script language="JavaScript" src="../../hisglobal/js/validationBootstrap.js"></script>



<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/tab.js"/>
<his:javascript src="/hisglobal/js/newMultiRow.js"/>
<his:javascript src="/hisglobal/js/multirow.js"/>
<his:javascript src="/hisglobal/js/dropdown.js"/>
<his:javascript src="/hisglobal/js/common.js"/>
<his:javascript src="/ipd/js/admAdvice.js"/>


    
<style type="text/css">
.navbar-expand-sm .navbar-nav .nav-link {
    padding-right: 11.5rem;
    padding-left: 0.5rem;
}

.navbar{
padding-bottom: 0;
padding-top: 0;
background-image: linear-gradient(to right,#49B2F3, #02629C);
}

a.md-wg-deal-link, a {
font-size: 16px;
color: white;

}

a.md-wg-deal-link, a:hover {

color: white;

}

.gj-picker-md table tr td div, .gj-picker-md table tr th div {
    width: 30px;
    height: 20px;
    line-height: 20px;
    font-size: 10px;
}
</style>
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
	//hisValidator.addValidation("strPropAdmissionDate", "dtgtet=${advanceAdviceTransBean.strCtDate}" , "Prop Adminssion Date should be Greater than or Equal to Current Date");
	//Date VAlidation for date picker
	var propDate =$("#datepicker").val(); 
	var dd=new Date(propDate.split("-")[1]+","+(parseInt(propDate.split("-")[0])+1)+","+propDate.split("-")[2]);
 	if((new Date())>dd){
		alert("Prop Admission Date should be Greater than or Equal to Current Date");	
		return false;
	} 

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
					document.forms[0].action="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt";
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
				//var url="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+mode+"&puk="+document.getElementsByName("patCrNo")[0].value+"&catcode="+document.forms[0].strTreatment.value+"&wardType="+document.getElementsByName("strWard")[0].value.split("^")[1];
                 var url="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+mode+"&puk="+document.getElementsByName("patCrNo")[0].value+"&catcode="+document.forms[0].strTreatment.value+"&wardCode="+document.forms[0].strWard.value;
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
function view()
{
//document.getElementById("patientDemographicID").style.display="block";
//document.getElementById("patientEpisodeID").style.display="block";
document.getElementById("prevoiusDiagId").style.display="block";

document.getElementById("admissionAdviceId").style.display="block";
//document.getElementById("pDtlsDivId").style.display="";
document.getElementById("savebutton").style.display="";

document.getElementById("remarksId").style.display="block";
document.getElementById("checkid").style.display="block";
document.getElementById("billdet").style.display="block";
document.getElementById("savebutton").style.display="block";

}
</script>
</head>
<body onLoad="checkMsg();invokeOnLoad();" onUnload="closePopUp();">


<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/AdmissionAdviceTransBSCNT" method ="post">


<fieldset form="form1">
	
  <legend class='legendHeader' id='nonPrintableLegend'>Admission Advice</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<%-- <button  id="cancelButton" type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>	--%>
	<button  type="button" id="savebutton" class="btn btn-outline-success mt-1 btn-circle savebtn" tabindex='2' onclick='return validate1();' style="display: none;">					
		<i class="fa fa-save iround"  title="Save" ></i>
	</button>	
	
<!-- 	<button  type="button" id="admitButton" class="float-right btn btn-outline-success mt-1 btn-circle" tabindex='2' onClick="return validate1();">					
		<i class="fa fa-ambulance"  title="Save" ></i>
	</button>
	 -->
											                 
  </div>
  <div  class="viewport" id="nonPrintable">
		<div class="container-fluid">
  <div class="col-md-12">	
<div class="alert alert-danger alert-dismissible fade show" id="errMsg"  style="display:none;"><bean:write name="advanceAdviceTransBean" property="strMsgString"/></div>
<div class="alert alert-success alert-dismissible fade show" id="normalMsg" style="display:none;"><bean:write name="advanceAdviceTransBean" property="strWarning"/></div>
<%-- <div class="alert alert-warning alert-dismissible fade show"  id="wrnID" style="display:none;"><bean:write name="advanceAdviceTransBean" property="strMsgString" /></div> --%>

           
		<div id="pDtlsDivId" style="display:none;">
			<pDtl:patDtlNew crNo="${advanceAdviceTransBean.patCrNo}" address="false"></pDtl:patDtlNew>
	    </div>
									  			   
											
<div class="prescriptionTile" style="padding-bottom: 0;padding-top: 0;" id="mainDataTable">
  
  
  
   <div id="patientDemographicID" style="display: none">
	
  </div>
   

   
   <div id="admissionAdviceId" style="display: none">
   <div class="row rowFlex reFlex" >
   <div class="col-sm-9"><p class="subHeaders"><i class="fas fa-info-circle">&nbsp;</i>Admission Advice</p></div>
    <div class="col-sm-3" align="right"><label>Admission Advice List</label>&nbsp;&nbsp;<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#adviceListDiv" style="background-color: #3ac9d6 !important; border:#3ac9d6 !important;" onClick="return showList();" title="Click Here TO View List Of Patient">
      <i class="fas fa-list"></i></button></div>
   </div>
   <div id="billdet" style="display:none">
  	<nav class="navbar navbar-expand-sm bg-light">
  <ul class="navbar-nav">
  
  <% String hospcode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
    		IpdConfigUtil configIpd=new IpdConfigUtil(hospcode);
    		if(configIpd.getStrIntegrationBilling().equals("1")) { 	%>
  
    <li class="nav-item" >
      <a class="nav-link" href="#" data-toggle="modal" data-target="#accountdetailmodel"><i class="far fa-user-circle">&nbsp;</i>Account Details</a>
    </li>
    <%} %>
    <li class="nav-item">
      <a class="nav-link" href="#" data-toggle="modal" data-target="#admtypedetailmodel"><i class="far fa-user-circle">&nbsp;</i>Admission Type Details</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#" data-toggle="modal" data-target="#predigdetailmodel"><i class="far fa-user-circle">&nbsp;</i>Previous Diagnosis Details</a>
    </li>
   
  </ul>
</nav>
  	<!-- <div class="row rowFlex reFlex">
  	<div class="col-sm-2"><button type="button" class="btn btn-primary" style="background-color: #3ac9d6 !important;border:#3ac9d6 !important " data-toggle="modal" data-target="#accountdetailmodel"><i class="far fa-user-circle">&nbsp;</i>Account Details</button></div>
  	<div class="col-sm-3"><button type="button" class="btn btn-primary" style="background-color: #3ac9d6 !important;border:#3ac9d6 !important" data-toggle="modal" data-target="#admtypedetailmodel"><i class="far fa-user-circle">&nbsp;</i>Admission Type Details</button></div>
  	<div class="col-sm-3"><button type="button" class="btn btn-primary" style="background-color: #3ac9d6 !important;border:#3ac9d6 !important"  data-toggle="modal" data-target="#predigdetailmodel"><i class="far fa-user-circle">&nbsp;</i>Previous Diagnosis Details</button></div>
  	<div class="col-sm-4"></div>
  	</div> -->
  	
  	
  </div>
  <br>
   <div class="row rowFlex reFlex" >
   <div class="col-sm-2"><label><font color="red">*</font>Department</label></div>
   <div class="col-sm-2"><bean:write
			name="advanceAdviceTransBean" property="strDepartment" /></div>
   <div class="col-sm-2"><label><font color="red">*</font>Unit</label></div>
   <div class="col-sm-2" id="offlineUnitId"><bean:write
			name="advanceAdviceTransBean" property="strUnit" /></div>
   <div class="col-sm-2"><label>Episode No.</label></div>
   <div class="col-sm-2"><bean:write
			name="advanceAdviceTransBean" property="strEpisodeNumber" />
    </div>
   </div>
   
   <div class="row rowFlex reFlex" >
  <div class="col-sm-2"><label>Treatment Category</label></div>
   <div class="col-sm-2"><select name="strTreatment"
			class="comboNormal" onchange="getAdvanceAmount();">
			<bean:write name="advanceAdviceTransBean"
				property="strTreatmentCategoryValues" filter="false" />
		</select></div>
    <div class="col-sm-2"><label><font color="red">*</font>Ward</label></div>
     <div class="col-sm-2"><div id="wardCombo">
    		<select class="form-control custom-select" name="strWard" onChange="initBedAndRoom(),checkDuplicate();">
    			<option value="0">Select Value</option>
    		</select>
    	</div></div>
      <div class="col-sm-2"><label>Bed Status</label></div>
       <div class="col-sm-2"><button type="button" class="btn btn-primary"  data-target="#myModal"  style="background-color: #3ac9d6 !important;border:#3ac9d6 !important " onClick="return bedDetails();" title="Click Here To Check Bed Status"><i class="fas fa-bed"></i>&nbsp;&nbsp;Bed Status</button></div>
   </div>
   
   <div class="row rowFlex reFlex" >
   <div class="col-sm-2" style="display: none;"><label>Room/Bed Type</label></div>
    <div class="col-sm-2" style="display: none;"><div id="roomBedId"></div></div>
    
    	<% if(configIpd.getStrIntegrationBilling().equals("1")) {  %>
    	

       <div class="col-sm-2"><label><font color="red">*</font>Advance Amount</label></div>
        <div class="col-sm-2"><input type="text" class="form-control" name="strAdvanceAmount" value="${advanceAdviceTransBean.strAdvanceAmount}" onkeypress="return validateData(event,7);" /></div>
		 <%} else{ %>
		  
        <div class="col-sm-2" style="display:none;"><input type="text" class="form-control" name="strAdvanceAmount" value="0"/></div>
		 
		 <%} %>
		 
		   <div class="col-sm-2"><label><font color="red">*</font>Proposed Admission Date</label></div>
		   <div class="col-sm-2"><input  id='datepicker' name="strPropAdmissionDate" ></div>
   </div>
   
  
    <div id='diseaseTypeLabel'>
    <div id='diseaseTypeId' style="display: none">Disease Type<</div>
     </div>
     
     
  
     
     
    <div id='diseaseTypeWithoutLabel'>
       <div id='diseaseTypeValuesId' style='display: none'>
    <select name="strDiseaseType"  onchange="getWard();">
    			<bean:write name="advanceAdviceTransBean" property="strDiseaseTypeValues" filter="false"/>
    	</select>
    	</div>
    </div>
 

 <%--
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  
   <tr>
   
   
    <td width="25%" class="LABEL"><div align="right"><font color="red">*</font>Proposed Admission Date</div></td>
    <td width="25%" class="CONTROL">
    <date:date name="strPropAdmissionDate" value="${advanceAdviceTransBean.strCtDate}"></date:date></td>
    
  </tr>
   
 
  
  </table>--%>
  </div>
  
  	
   <div id="checkid" style="display: none">
 		  <div id="DiagDetailsId" style="display: none">
 	   	   </div>
 </div>
  
  <div id="prevoiusDiagId" style="display: none">
 
 <%--  <logic:equal name="advanceAdviceTransBean" property="strDiagType" value="0">
   <div class="row rowFlex reFlex">
  <div class="col-sm-6">
  <div id='hospitalDiagnosisId'>
	  <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="1" 
	  onclick="">&nbsp;&nbsp;Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div>
	  <div id='icdDiagnosisId'>
    	 <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="0" 
    	 onclick="">&nbsp;&nbsp;ICD10 Diagnosis</html:radio></div>	</div>
  <div class="col-sm-6"><button type="button" class="btn btn-primary" onclick="newRow();"><i class=""></i></button></div>
  </div>
 </logic:equal> --%>
  
 
	
	<bean:write name="advanceAdviceTransBean" property="strProvisionDiagnosisValues" filter="false"/>
	
	<%-- <logic:equal name="advanceAdviceTransBean" property="strDiagType" value="0">
	<td colspan="2" class="TITLE" width="50%">
	  <div id='hospitalDiagnosisId'>
	  <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="1" 
	  onclick="">&nbsp;&nbsp;Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div>	  
	 </td>
	 <td colspan="1" class="TITLE" width="48%">
    	 <div id='icdDiagnosisId'>
    	 <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="0" 
    	 onclick="">&nbsp;&nbsp;ICD10 Diagnosis</html:radio></div>
    </td>
    <td colspan="1" class="TITLE" width="2%">	 
    	 <img src="/HBIMS/hisglobal/images/plus.gif"  onclick="newRow();" style="cursor: pointer;">
	</td>
	</logic:equal> --%>
	<logic:notEqual name="advanceAdviceTransBean" property="strDiagType" value="0">
	<logic:equal name="advanceAdviceTransBean" property="strDiagType" value="2">
	<div class="row rowFlex reFlex">
	<div class="col-sm-6">
	  <div id='hospitalDiagnosisId'>
	  <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="1" 
	  onclick="changeMultiRows();">&nbsp;&nbsp;Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div></div>
	  
	  <div class="col-sm-6" align="right">
	  <button type="button" class="btn btn-primary" style="background-color: #3ac9d6 !important;border:#3ac9d6 !important " onclick="newRow();"><i class="fas fa-plus-circle"></i></button>
	 </div>
	 </div>
	 </logic:equal>
	 <%-- <logic:equal name="advanceAdviceTransBean" property="strDiagType" value="1">
	 <div class="row rowFlex reFlex">
    	 <div id='icdDiagnosisId'>
    	 <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="0" 
    	 onclick="changeMultiRows();">&nbsp;&nbsp;ICD10 Diagnosis</html:radio></div>
    	<button type="button" class="btn btn-primary" style="background-color: #3ac9d6 !important;border:#3ac9d6 !important " onclick="newRow();" ><i class="fas fa-plus-circle"></i></button>
	</div>
	</logic:equal> --%>
	</logic:notEqual>	
	
	<div id="diagLabel" style="display: none">
	 <div class="row rowFlex reFlex">
	 <div class="col-sm-4">Code</div>
	 <div class="col-sm-4">Diagnosis Name</div>
	 <div class="col-sm-4">Type</div>
	 </div>
	<!-- <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	<tr>
		<td class="TITLE" width="15%"><div align="center">Code</div></td>
		<td class="TITLE" width="67%" align="left">
		<div align="center">Diagnosis Name</div></td>
		<td class="TITLE" width="16%" align="center">
		<div align="center">Type</div></td>
		<td class="TITLE" width="2%" align="right"><div align="center"></div></td>		
	</tr>
	</table> -->
	</div>
	<table id="icd10Id"  class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr></tr>
		</table>
		<div id='id1'></div>		
	</div>	 
<div id="remarksId" style="display: none">

<div class="row rowFlex reFlex">
     <div class="col-sm-2" ><font color="red">*</font>Advice By</div>
	 <div class="col-sm-4" ><select class="form-control" name="strConsultantName">
				<bean:write name="advanceAdviceTransBean" property="strConsultantNameValues" filter="false" />
			</select></div>
	 <div class="col-sm-2">Remarks</div>
	 <div class="col-sm-4" ><textarea class="form-control" rows="2" cols="20" name="strRemarks"></textarea></div>
	 </div>
	 
</div>
<div id="tariffNamePartDivId">
	
	</div>
             <hr>
				<div class="row rowFlex reFlex">
					<div class="col-sm-10"></div>
					<div class="col-sm-2" align="right"><i class="fa fa-asterisk"  style="color: red;font-size: smaller;"></i>Fields Mandatory</div>				
				</div>
	<%-- <div id="buttonId" align="center">
	<logic:notEmpty name="advanceAdviceTransBean" property="patCrNo">
				<a href="#" class="button" id="" onclick='return validate1();'><span class="save">Save</span></a>
			<!-- 		<img src="/HBIMS/hisglobal/images/btn-sv.png" onClick="return validate1();" style="cursor: pointer;">-->
				</logic:notEmpty>
				<!-- <img src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelFunc();" style="cursor: pointer;"> -->
			
			
			<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
</div> --%>
	<input type="hidden" name="hmode" value="">
	<input type="hidden" name="mode">
	<input type="hidden" name="strUnitCode" value="${advanceAdviceTransBean.strUnitCode}">
	<input type="hidden" name="strAccountNo" value="${advanceAdviceTransBean.strAccountNo}">
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
	<input type="hidden" name="strPatName" value="${advanceAdviceTransBean.strPatName}">
	<input type="hidden" name="advamt" value="">
		
	
	</div>
	</div>
	</div>
	</div>
	</fieldset>
	<div class="modal fade" id="accountdetailmodel" role="dialog" >
  <div class="modal-dialog " role="document" style="">
    <div class="modal-content">
    <div class="modal-header">Account Details<button type="button" class="close" data-dismiss="modal">×</button></div>
    <div class="modal-body">
  	<div class="row rowFlex reFlex" >
		   <div class="col-sm-5" align="right"><label>Account No.</label></div>
		   <div class="col-sm-7"><bean:write name="advanceAdviceTransBean" property="strAccountNo" filter="false"/></div>
	
   </div>
   <div class="row rowFlex reFlex" >
		   <div class="col-sm-5" align="right"><label>Deposited Amount</label></div>
		   <div class="col-sm-7"><bean:write name="advanceAdviceTransBean" property="strAdvanceAmtpaid" filter="false"/></div>		   
   </div>
   <div class="row rowFlex reFlex" >
           <div class="col-sm-5" align="right"><label>Advance Deposit Date</label></div>
		   <div class="col-sm-7"><bean:write name="advanceAdviceTransBean" property="strAdvDepDate" filter="false"/></div>	  
   </div>
   <div class="row rowFlex reFlex">  
		   <div class="col-sm-5" align="right"><label>Account Type</label></div>
		   <div class="col-sm-7"><bean:write name="advanceAdviceTransBean" property="strAccType" filter="false"/></div>  
   </div>
  	</div>
       
    </div>
  </div>
</div>
<div class="modal fade" id="admtypedetailmodel" role="dialog" >
  <div class="modal-dialog " role="document" style="">
    <div class="modal-content">
    <div class="modal-header">Admission Type Details<button type="button" class="close" data-dismiss="modal">×</button></div>
    <div class="modal-body">
  	<div class="row rowFlex reFlex" >
		   <div class="col-sm-5" align="right"><label>Planned Surgery Date:</label></div>
		   <div class="col-sm-7"><input  id='datepicker1' name="strPlannedSurgeryDate" ></div>
	
   </div>
   <div class="row rowFlex reFlex" >
		   <div class="col-sm-5" align="right"><label>Admission Type</label></div>
		   <div class="col-sm-7"><div id="offlineUnitId">
	    	   <select name="strAdmissionTypeValues" class="form-control"  >
			   	<bean:write name="advanceAdviceTransBean" property="strAdmissionTypeValues" filter="false"/>
			   </select>
		   </div></div>		   
   </div>
   <div class="row rowFlex reFlex" >
           <div class="col-sm-5" align="right"><label>Approximate Length Of Stay</label></div>
		   <div class="col-sm-7"><input type="text" class="form-control" name="strMaxLenStay" maxlength="2" onkeypress="return validateData(event,5);"></div>	  
   </div>
   <div class="row rowFlex reFlex" align="right">  
		   <div class="col-sm-5"><label>Apply OT Package</label></div>
		   <div class="col-sm-7"><div id="pack" style="">
	    	   <select name="strPackageApply" class="form-control" onChange="setAdvAmt();">
			   	<bean:write name="advanceAdviceTransBean" property="strPackageComboValues" filter="false"/>
			   </select></div></div>  
   </div>
  	</div>
       
    </div>
  </div>
</div>

<div class="modal fade" id="predigdetailmodel" role="dialog" >
  <div class="modal-dialog " role="document" style="">
    <div class="modal-content">
    <div class="modal-header">Previous Diagnosis Details<button type="button" class="close" data-dismiss="modal">×</button></div>
    <div class="modal-body">
  	<div id="prevoiusDiagDetailsId" >
      <bean:write name="advanceAdviceTransBean" property="strPreviousDiagDtl" filter="false"/></div>
  	</div>
       
    </div>
  </div>
</div>

<!-- <div class="fade" id="bedstatusDiv">
					  <div class="modal-dialog modal-lg" role="document">
					    <div class="modal-content">
					    <div class="modal-header">Bed Status<button type="button" class="close" data-dismiss="modal">×</button></div>
					      <div id="bedstatus" class="modal-body">
					      </div>
					      <div class="modal-footer">
					      </div>
					    </div>
					  </div>
					</div> -->
					
					
					<div class="modal-container" style="display: none;">
					<div id="myModal" class="modal fade" role="dialog">
						<div class="modal-dialog modal-lg">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Bed  Status Dashboard</h5>
									<button type="button" onclick="unloadBootstrap();"
										class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body" id="modalSpaceId"></div>
							</div>
						</div>
					</div>
				</div>
	</html:form>

<jsp:include page="admissionadvice_dropdown_ipdtranswithoutDesk.jsp"></jsp:include>
<jsp:include page="admissionadvice_multirow_ipdtranswithoutDesk.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
<script type="text/javascript">
invokeOnLoad();

</script>
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
					
					
					<div class="modal fade" id="adviceListDiv">
					  <div class="modal-dialog modal-lg" role="document" style="max-width: 1000px;">
					    <div class="modal-content">
					    <div class="modal-header">Admission Advice List<div style="float:right;"><button type="button" class="btn btn-danger" data-dismiss="modal">cancel</button></div></div>
					      <div id="adlist" class="modal-body">
					           
					      </div>
					     
					        
					
					     
					    </div>
					  </div>
					</div>
					
					
					

</body>
</html>