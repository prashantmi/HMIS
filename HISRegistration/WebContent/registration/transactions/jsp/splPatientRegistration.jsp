<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/js/basic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<script language="JavaScript" type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>

<script language="JavaScript" src="/HIS/hisglobal/js/generictemplate/validationFunctions.js"></script>
<script language="JavaScript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>
<script language="JavaScript" src="/HIS/hisglobal/js/date_validator.js"></script>
<script type="text/javascript" src="./../../registration/transactions/js/saveBarcodeSlipPrintEntryInHMIS.js" /></script> 
<!--By Surabhi for SNOMED CT word base  -->
<!-- <link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/searchtool.css"> -->
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/searchtoolnew.css">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>

<script type="text/javascript">

function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    var elmt=id.split('_');
    var elmtid=elmt[1];
    clear(elmtid);
    document.getElementById("txt-snomed-ct-search_"+elmtid).value = "";

});

function clear(id)
{
	//var elmtid=id.split('_');
	var trgt=id;
	var targetPrefferedTerm="";  
	 var targetConceptId="";
	 //document.getElementById(id).value="";
	 if(trgt=="1")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTVisitReason")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdVisitReason")[0];   //concept Id
		 document.getElementsByName("patVisitReason")[0].value="";
		
		}
	 
		    targetPrefferedTerm.value = "";
		    targetConceptId.value = "";
	 
}

window.onload = function(){	
	load1('1','');
}

function load1(elmtId,semantictag){
	
	if(elmtId==null || elmtId==undefined){
		elmtId="1"; semantictag="DISORDER";
	}else if(elmtId=="1"){
	 id="patVisitReason";
	}
	setTarget(id);
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};

	var callbck_index =function(ret_OUT){setValue(ret_OUT);};

	//selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	if(<%=RegistrationConfig.SNOMED_SERVICE_ON%> == $('[name="isSnomedServiceOn"]').val()){
		selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	}else{
		selectSNOMEDCTmulti('','','','-1','null','','');
		}
	
	//alert(document.getElementsByName("patVisitReason")[0].value);
	if(document.getElementsByName("patVisitReason")[0].value=="")
		{
		var s=document.getElementById("txt-snomed-ct-search_1").value;
		document.getElementsByName("patVisitReason")[0].value=s;
		
		}
	//alert(document.getElementsByName("patVisitReason")[0].value);
	//end
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	
}

function setTarget(id)
{
	if(id=="patVisitReason")
	{
		document.getElementsByName("targetId")[0].value="patVisitReason";
	}	
	
}

function setValue(selectedSNOMEDTerm)
{	
	//alert("set value= "+selectedSNOMEDTerm.term);
	var target=document.getElementsByName("targetId")[0].value;
		
	var targetPrefferedTerm="";
	var targetConceptId="";
	
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	{
		
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
	
		if(target == "patVisitReason") // Visit Reason
		{
			//alert("inside patVisitReason of setValue()");
			 
			 targetPrefferedTerm=document.getElementsByName("snomdPTVisitReason")[0];  //preffered term
			 targetConceptId=document.getElementsByName("snomdCIdVisitReason")[0];   //concept Id
		
			 if(document.getElementsByName("patVisitReason")[0].value=="") document.getElementsByName("patVisitReason")[0].value = str;
			else document.getElementsByName("patVisitReason")[0].value = document.getElementsByName("patVisitReason")[0].value + ", " + str;
			
		}
	 
		if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
		else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
		
		if(targetConceptId.value=="")  targetConceptId.value = str1;
		else targetConceptId.value = targetConceptId.value + "#" + str1;
		
	}
}
//Added by Surabhi on 1st Feb'17 for free text if snomed field is disabled  

function freeText()
{
	if(document.getElementsByName("patVisitReason")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_1").value;
	document.getElementsByName("patVisitReason")[0].value=s;
	
	}
}
//end
</script>
 <!--End: Surabhi  -->
 

<style type="text/css">

@media print { 
		#nonprintableDiv1 
		{
		 display: none; 
		}
		#nonprintableDiv2 
		{
		 display: none; 
		}
		#nonprintableDiv3 
		{
		 display: none; 
		}
		
}

.border .div-table-col{
border: 1px solid black;
}
</style>

<title>Patient Registration</title>
<script>
	var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>";
	var LOCAL_LANGUAGE = "<%= RegistrationConfig.LOCAL_LANGUAGE %>";
	var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>";
	var PATIENT_REG_CATEGORY_GROUP_PAID = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_PAID %>";
	var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE %>";
	/* $(window).on("load.loading1", function(){
		splregistration.fetchDefaultValues();
		$('[name="departmentUnitCode"]')[0].focus();
	});  */
</script>
<script>
var oldVal='';
$(document).ready(function(){
	$("select[name='paymentModeCode']").on('focus', function(){
		oldVal=$(this).val();
	});
});
function paymentModeCodeOnChangeValidate(e){ 
	 var val=$(e).val();  
	
	 
	 if(val.split('#')[1]=='1')
		{ 	if(val.split('#')[2]==$('#patPrimaryCatCodeId').val())
				 {
			splregistration.setPaymentModeRefId(e);
			return true;
				 }
					  
			   else
				   {
				   alert("Selected Payment Mode is not applicable for this Patient Billing Category!");
				   $(e).find('option').removeAttr('selected');
				   $(e).find('option[value='+oldVal+']').attr('selected','selected'); 
				  // $('#divFeeVal3').hide();
				  splregistration.setPaymentModeRefId(e);
				   return false;
				   
				   }
	 }
	 else
		 {
		 splregistration.setPaymentModeRefId(e);
			 return true;
		
		 }
		 
	}

</script>
<script>
	function checkMobileOrEmgCntNo(){
		var p1 = document.getElementsByName("patAddMobileNo")[0];
		var p2 = document.getElementsByName("patEmgCntNo")[0];
		if(p1.value.length == 10 && p2.value.length == 10)
			{
				if(p1.value == p2.value){
      				alert("Emergency Contact No. should be different from Mobile No.");
     				p2[0].focus();
				}
				
			}
				
		}

</script>
</head>

<body>
<center>
<%-- <s:actionerror/> --%>

<s:form action="SplRegistration">
    <input type="hidden" name="flagHasActionError" id="flagHasActionErrorId" value='<s:property value="%{hasActionErrors()}"/>'>
  	<s:if test="%{!hasActionErrors()}">
    <div class="wrapper rounded" id="nonprintableDiv1">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						New Special Patient Registration 
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 48%">
						<font color="red">*</font>Visiting Unit 
				</div>
				<div class="div-table-col width control" style="width: 50%">
					<select name="departmentUnitCode" style="width: 75%" tabindex="1" class="select23prcnt" onchange='splregistration.setDepartmentDependents(this)'>
						<option value="-1">Select Value</option>
					</select>
					<input type="hidden" name="ageRange">
				</div>
			</div>
		</div>

		<div class="div-table">
			<div class="div-table-row title">
				<div class="div-table-col" style="width: 60%">
					<div class="div-table-col width label">
						<s:text name="global.patient"/>&nbsp;<s:text name="global.details"/>		
					</div>
				<div class="div-table-col width label" style="width: 16%">
						<font color="red">*</font>Patient Category 
				</div>
				<div class="div-table-col width control" style="width: 16%">
					<select name="patPrimaryCatCode" id="patPrimaryCatCodeId" tabindex="1" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
					<input name="patPrimaryCat" type="hidden" />
					<input name="patCatShortName" type="hidden" />
					<input name="patPrimaryCatGrp" type="hidden" />
				</div>
				
				<div id="divCatCardId" class="div-table-col" style="width: 32%; display: none;">
					<div  class="div-table" >
						<div class="div-table-row title">
							<div id="divCatCardNoLabelId" class="div-table-col label" style="width: 50%">
								<font color="red">*</font>Card No
							</div>
							<div class="div-table-col control" style="width: 50%">
								<input id="shownPatIdNoId" name="patIdNo"  type="text" class="input75prcnt" />
							</div>
						</div>
					</div>
				</div>
				<div id="divImgCatCardId" class="div-table-col control" style="width: 32%; display: none;">
					<img id="imgCatCardId" align="left" src="../../hisglobal/images/search_icon1.gif" />
				</div>
				<div id="divSpareCatId" class="div-table-col" style="width: 32%;"></div>
				
				<div id="divAlreadyRegisteredId" class="div-table-col" style="width: 32%; display: none;">
					<div  class="div-table" >
						<div class="div-table-row title">
							<div  class="div-table-col label" style="width: 50%;" align="right">Already Registered</div>
							<div  class="div-table-col control" style="width: 50%;">
								<input type="checkbox" name="alreadyRegisteredFlag" id="alreadyRegisteredFlagId" tabindex="2">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="div-table-col" style="width: 30%">
			<!-- start warish for show fee label -->
				<div class="div-table-col label-yellow" id="divFeeLabel" style="width: 20%;"><font color="red">*</font><s:text name="registrationFee"/> 
				</div>
				<div class="div-table-col control" id="divFeeVal" style="width:25%;">
	 		        <img id="INRId" align="left" src="../../hisglobal/images/INR.png" />&nbsp;&nbsp;&nbsp;
	 		        <div id="amount" class="div-table-col label-yellow" class="input100prcnt"></div>
					<input name="patAmountCollected" id="patAmountCollectedId" tabindex="3"  type="hidden"  readonly="readonly">
				
				</div>
				<!-- End:warish-->
			<div class="div-table-col control" id="divFeeVal2" style="width:20%;display: none;">
				<select name="paymentModeCode" tabindex="1" class="select100prcnt" onchange=' return paymentModeCodeOnChangeValidate(this)'>
					<option value=""></option>
				</select>
			</div>
			<div class="div-table-col control" id="divFeeVal3" style="width:20%;display: none;">
				<input tabindex="1" name="paymentModeRefId" maxlength="20" type="text" />
			</div>
			</div>
			</div>
			<!-- <div class="div-table-row"></div> -->
			<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="0">
			<div class="div-table-row" id="divCatGroupBeneficiaryId" style="display: none;">
				<div class="div-table-col width16 label">
					<font color="red">*</font>Reference Letter No
				</div>
				<div class="div-table-col width16 control" >
					<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" class="input75prcnt" maxlength="50">
				</div>
				<div class="div-table-col width16 label">
					<font color="red">*</font>Letter Date
				</div>
				<div class="div-table-col width16 control" >
					<input type="text" name="creditLetterDate" id="creditLetterDateId" readonly="readonly" class="input60prcnt">
				</div>
			</div>
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;">Aadhar No</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patNationalId" tabindex="2" maxlength="18" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;"> Any Other ID</div>
				<div class="div-table-col control" style="width: 16%;">
					<select name="patDocType" tabindex="2" id="patDocTypeId" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
					<input type="hidden" name="patDocTypeName"  />
				</div>
				<div class="div-table-col control" style="width: 32%;">
					<div id="divCardNoId" class="div-table" style="width: 100%; display: none;">
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%; "><font color="red">*</font>Card No.</div>
							<div class="div-table-col control" style="width: 50%;">
								<input name="patCardNo" tabindex="2" type="text" class="input75prcnt">
							</div>
						</div>
					</div>
				</div>
			</div>
			
			 <!--  <div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;">Patient Name :</div>
				<div class="div-table-col" style="width: 16%;">&nbsp;<font color="red">*</font>First</div>
				<div class="div-table-col" style="width: 16%;">&nbsp;Middle</div>
				<div class="div-table-col" style="width: 16%;">&nbsp;Last</div>
			</div>   -->
			<div class="div-table-row">
				<!-- <div class="div-table-col" style="width: 16%;"></div> -->
				<div class="div-table-col label
				" style="width: 16%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/>&nbsp;<font color="red">*</font>(<s:text name="first"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
					<!-- <input name="patFirstName"  tabindex="1" maxlength="33" type="text" class="input75prcnt"
						onkeyup="multilingualConversion(this,document.getElementById('patFirstNameInMultiLangId'));"> -->
						<input name="patFirstName"  tabindex="1" maxlength="33" type="text" class="input75prcnt" />
				</div>
				<div class="div-table-col label" style="width: 16%;">(<s:text name="middle"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
					<!-- <input name="patMiddleName" tabindex="2" maxlength="33" type="text" class="input75prcnt"
						onkeyup="multilingualConversion(this,document.getElementById('patMiddleNameInMultiLangId'));"> -->
						<input name="patMiddleName" tabindex="2" maxlength="33" type="text" class="input75prcnt"/>
				</div>
				<div class="div-table-col label" style="width: 16%;">(<s:text name="last"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
				<!-- <input name="patLastName" tabindex="2" maxlength="33" type="text" class="input75prcnt"
					onkeyup="multilingualConversion(this,document.getElementById('patLastNameInMultiLangId'));"> -->
					<input name="patLastName" tabindex="2" maxlength="33" type="text" class="input75prcnt"/>
				</div>
			</div>
			<!-- <div class="div-table-row">
				<div class="div-table-col" style="width: 16%;"></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patFirstNameInMultiLang" id="patFirstNameInMultiLangId" tabindex="1" maxlength="33" type="text" class="input75prcnt"
						 onblur="callOnBlur();" onfocus="callOnClick();">
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patMiddleNameInMultiLang" id="patMiddleNameInMultiLangId" tabindex="2" maxlength="33" type="text" class="input75prcnt"
						 onblur="callOnBlur();" onfocus="callOnClick();">
				</div>
				<div class="div-table-col control" style="width: 16%;">
				<input name="patLastNameInMultiLang" id="patLastNameInMultiLangId" tabindex="2" maxlength="33" type="text" class="input75prcnt"
					 onblur="callOnBlur();" onfocus="callOnClick();" >
				</div>
			</div> -->
			
			<!-- <div class="div-table-row">
				<div class="div-table-col" style="width: 16%;"></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patFirstName" maxlength="33" tabindex="1" type="text">
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patMiddleName" maxlength="33" tabindex="2" type="text">
				</div>
				<div class="div-table-col control" style="width: 16%;">
				<input name="patLastName"  maxlength="33" tabindex="2" type="text">
				</div>
			</div> -->
			
			<div class="div-table-row">
			    <div class="div-table-col label" style="width: 16%;"><font color="red">*</font>
			    	<input type="radio" name="isActualDob" value="0" onclick="showDivAgeDob();" checked="checked"/>Age &nbsp;
			    	<input type="radio" name="isActualDob" value="1" onclick="showDivAgeDob();"/>DOB &nbsp;
			    </div>
			    
			    <div class="div-table-col" style="width: 16%" >
					<div id="divAge" class="div-table">
						<div class="div-table-row">
							<div class="div-table-col" style="width: 100%;">
						       <input id="patAgeId" tabindex="1" name="patAge" type="text"  class="input30prcnt" maxlength="3" onblur="setMotherValidRule();" >
						    
						    	<select id="patAgeUnitId" tabindex="1" name="patAgeUnit" class="select45prcnt"  onblur="setMotherValidRule();">
									<option value="-1">Select Value</option>
								</select>
							</div>	
						</div>
					</div>
				
					<div id="divDob" class="div-table" style="display: none;">
						<div class="div-table-row">
						<!-- 	<div class="div-table-col control" style="width: 100%;">
						       <input id="patDOBId" tabindex="1" type="text" name="patDOB" onblur="setMotherValidRule();" class="input50prcnt">&nbsp;
							</div> -->
							<div class="div-table-col control" style="width: 100%;">
						       <!-- <input id="patDOBId" tabindex="1" type="text" name="patDOB" onblur="setMotherValidRule();" class="input45prcnt">&nbsp;-->
						    	<div id="divNewDOB">
									<input id="patDOBId_Dup" tabindex="1" type="text" name="patDOB_Dup" onblur="setMotherValidRule();" class="input45prcnt" />
								</div>
								<div id="divNewDOBHidden">
									<input type='hidden' name='patDOB' id='patDOBId'/>
								</div>
							</div>	
						</div>
					</div>
				</div>
			    <div class="div-table-col label" style="width: 16%;"><font color="red">*</font>Gender
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<select name="patGenderCode" tabindex="1" id="patGenderCodeId" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input name="patGender" type="hidden">
						<input name="defaultpatGenderCode" id="defaultpatGenderCodeId" type="hidden" value="-1">
						<input name="defaultpatGender" id="defaultpatGenderId" type="hidden">
				</div>
				
				<div class="div-table-col label" style="width: 16%;">Patient Caste</div>
				<div class="div-table-col control" style="width: 16%;">
						<select name="patCasteCode" tabindex="2" id="patCasteCodeId" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input name="patCaste" type="hidden">
				</div>
			</div>
	
			
			<!-- <div class="div-table-row"></div> -->
			    
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font>Father's Name</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patGuardianName" tabindex="1" id="patGuardianName" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;">Spouse's Name</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patHusbandName" tabindex="1" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;">Mother's Name</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patMotherName" maxlength="60" tabindex="2" type="text" class="input75prcnt">
				</div>
			</div>
			
			<div class="div-table-row">
				
				<div class="div-table-col label" style="width: 16%;">Marital Status
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<select  id="patMaritalStatusCodeId" name="patMaritalStatusCode" tabindex="2" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input type="hidden" name="patMaritalStatus">
				</div>
				
				<div class="div-table-col label" style="width: 16%;">Religion</div>
				<div class="div-table-col control" style="width: 16%;">
						<select name="patReligionCode" tabindex="2" id="patReligionCodeId" class="select77prcnt" >
							<option value="-1">Select Value</option>
						</select>
						<input type="hidden" name="patReligion">
				</div>
				<div class="div-table-col label" style="width: 16%;">Birth Place</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patBirthPlace" tabindex="2" maxlength="50" type="text" class="input75prcnt">
				</div>
			</div>
			
				
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;">Patient Occupation
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<select name="patOccupation" tabindex="2" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
				</div>
				<div class="div-table-col label" style="width: 16%;">Monthly Income
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patMonthlyIncome" tabindex="2" maxlength="11" type="text" class="input75prcnt">
				</div>
				
				
				<div class="div-table-col label" style="width: 16%;">Visit Reason :</div>
				<div class="div-table-col control" style="width: 16%;">
					<!-- <textarea id="textArea" tabindex="2" name="patVisitReason"  class="textarea75prcnt"></textarea> -->
					<!-- <textarea id="textArea"  tabindex="2" onclick="changeTabIndex()"  maxlength="99" name="patVisitReason" class="textarea100prcnt"></textarea> -->
					<!--By Surabhi on 07.11.2016-->
					<div align="left" id="dialog-form_1" >
						<input type="hidden" name="snomdPTVisitReason" />
						<input type="hidden" name="snomdCIdVisitReason" />
						<input type="hidden" name="patVisitReason" />
										
						<div id="snomed-ct-search">
							<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
							<!-- <input maxlength="100" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input tooltipClass" name="txt-snomed-ct-search_1"  style="width:65%;color:#000000;" onchange="load1('1','')"/> -->
						<textarea name="txt-snomed-ct-search_1" rows="2" cols="5" id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input tooltipClass" value="" onfocus="load1('1','');" style="width:86%;height:80%" autocomplete="off"></textarea>
						</div>
						 <div id="norecorddiv_1">
							<label style="display: none;" id="reccnt">No. of records : </label>
							<span style="display: inline;" id="reccount" ></span>
							<label style="display: none;" id="nosuggestion">No suggestions found</label>
							<label style="display: none;" id="norec">No results found</label>
							<label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
						</div>         
						<div class="concept" id="conceptdiv_1"></div>
					</div>
					<!--End Surabhi  -->
				</div>
				<div class="div-table-col" style="width: 64%;"></div>
			
				<!-- <div class="div-table-col label" style="width: 16%;"><font color="red">*</font>Registration Fee 
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAmountCollected" tabindex="3" type="text" class="input75prcnt" readonly="readonly" >
				</div> -->
				
			</div>
			
			
			
			
		</div>
	
		<div class="div-table">
			<div class="div-table-row title">
				<div class="div-table-col" style="width:32%;">
						Address Details 
				</div>
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font> Country
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<select name="patAddCountryCode" tabindex="1" id="patAddCountryCodeId" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input name="defaultpatAddCountryCode" type="hidden">
						<input name="patAddCountry" type="hidden">
					</div>
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font>State 
					</div>
					<div id="divStateComboId" class="div-table-col control" style="width: 16%;">
						<select name="patAddStateCode" tabindex="1" id="patAddStateCodeId" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input name="defaultpatAddStateCode" type="hidden">
						<input name="patAddState"  type="hidden">
						
					</div>
					<div id="divStateTextId" class="div-table-col control" style="width: 16%; display: none;">
						<input name="patAddState" tabindex="1" id="patAddStateId" maxlength="50" type="text" class="input75prcnt">
					</div>
			</div>

			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;">H No
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddHNo" tabindex="2" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;">Street
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddStreet" tabindex="2" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;">Location
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddCityLoc" tabindex="2" maxlength="60" type="text" class="input75prcnt">
				</div>
				
			</div>
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font>District</div>
				<div id="divDistrictComboId" class="div-table-col control" style="width: 16%;">
					<select name="patAddDistrictCode" id="patAddDistrictCodeId" tabindex="1" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
					<input name="defaultpatAddDistrictCode" type="hidden">
					<input name="patAddDistrict" type="hidden">
				</div>
				<div id="divDistrictTextId" class="div-table-col control" style="width: 16%; display: none;">
					<input name="patAddDistrict" id="patAddDistrictId" tabindex="1" maxlength="15" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;">City/Village
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddCity" tabindex="2" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;">Pin Code 
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddPIN" tabindex="2" maxlength="6" type="text" class="input75prcnt">
				</div>
				
			</div>
				
			<div class="div-table-row" >

				<div class="div-table-col label" style="width: 16%;">Land Mark </div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddLandMarks" tabindex="2" maxlength="60" type="text" class="input75prcnt">
				</div>
				
				<div class="div-table-col label" style="width: 16%;"> 	Area Category  </div>
				<div class="div-table-col control" style="width: 16%;">
					<select name="patIsUrban" tabindex="2" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
				</div>
				
				<div class="div-table-col label" style="width: 16%;">Email </div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddEmailId" tabindex="2" maxlength="70" type="text" class="input75prcnt">
				</div>
				
			</div>
			<div class="div-table-row">
						
				<div class="div-table-col label" style="width: 16%;">Phone No </div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddPhoneNo" id="patAddPhoneNo" tabindex="2" maxlength="16" type="text" class="input75prcnt">
				</div>
				
				<div class="div-table-col label" style="width: 16%;"> Phone Owner  </div>
				<div class="div-table-col control" style="width: 16%;">
					<select name="patAddPhoneOwner" tabindex="2" class="select77prcnt">
						<option value="-1">Select Value</option>
						<option value="1">Self (patient)</option>
						<option value="2">ANM</option>
						<option value="3">Doctor or any other health provider</option>
						<option value="4">Neighbour</option>
						<option value="5">Family member</option>
					</select>
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font>Mobile No</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddMobileNo" tabindex="2" maxlength="10" type="text" class="input75prcnt" onkeyup="checkMobileOrEmgCntNo()">
				</div>
			</div>
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="emergency"/>&nbsp;<s:text name="contactNo"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patEmgCntNo" maxlength="10" class="input75prcnt" tabindex="1" onkeyup="checkMobileOrEmgCntNo()">
				</div>
			</div>
		</div>
		
		<div class="div-table" >
				<div class="div-table-row title">
					<div class="div-table-col" style="width: 80%">
							Refer Details
					</div>
					<div class="div-table-col label" style=" width: 20%" align="right">
							<div style="display:none"  id="isReferCompusoryId">Refer Detail is Compulsory</div>
					</div>
					
					<div class="div-table-col label" style="width: 20%">
							Is Referred<input type="checkbox" name="isReferred" tabindex="2" value="0" onclick="Isreferred(this)">
					</div>
				</div>
		</div>
		<div id="divRefDtlId" class="div-table" style="display: none;">
			<div class="div-table-row" >
				<div id="divReferredInstitute" class="div-table-col label" style="width: 50%;">
					<input type="radio" name="referringInstType" value="G" onclick="showdivhoscode(this)" tabindex="2" checked="checked" />Associated Institute &nbsp;
			    	<input type="radio" name="referringInstType" value="O" onclick="showdivhoscode(this)" tabindex="2" />Other &nbsp;
				</div>
				<div id="divRefHosCode" class="div-table-col" style="width: 50%;">
					<div class="div-table" style="width: 100%;">
						<div class="div-table-row" >
							<div class="div-table-col label" style="width: 50%;"> <font color="red">*</font>
							<label for="Institute Name">Institute Name</label>	
							</div>
							<div class="div-table-col control" style="width: 50%;">
								<select id="patRefGnctdHospitalCodeId" name="patRefGnctdHospitalCode" tabindex="1" class="select77prcnt"  >
									<option value="-1">Select Value</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				
				<div id="divRefHosname" class="div-table-col" style="width: 50%; display: none;" >
					<div class="div-table" style="width: 100%;">
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%;"> 
							<font color="red">*</font><label for="Institute Name">Institute Name</label>	
							</div>
							<div class="div-table-col control" style="width: 50%;">
								<input name="patRefHospitalName" maxlength="50" type="text" class="input75prcnt"  size="20"  tabindex="1">
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		
		<div class="div-table" id="divReferred" style="display: none;">
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%;">Referring Institute CR No.</div>
				<div class="div-table-col control" style="width: 25%;">
					<input type	  ="text" 	name="patRefGnctdHospitalCrno" tabindex="2" maxlength ="18"  class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 25%;">
					<font color="red">*</font>Doctor Name
				</div>
				<div class="div-table-col control" style="width: 25%;">
					<input type	  ="text" 	name="patRefDoctor" tabindex="1" maxlength="60" class="input75prcnt">
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%;">
					Referring Institute Department.
				</div>
				<div class="div-table-col control" style="width: 25%;">
					<select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDept" tabindex="2" class="select77prcnt">
						<option value="-1">Select Value</option>
						<option value="0">Other</option>
					</select>
				</div>
				
				<div id="divRefHospitalDeptOtherId" class="div-table-col" style="width: 50%; display: none;">
					<div class="div-table">
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%;"><font color="red">*</font>Other Refd. Dept</div>
							<div class="div-table-col control" style="width: 50%;">
								<input name="patRefHospitalDeptOther" maxlength="50" type="text" size="20" tabindex="1" class="input75prcnt">
							</div>
						</div>
						
					</div>
				</div>
				
			</div>
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%;">
					Referring Institute Unit.
				</div>
				<div class="div-table-col control" style="width: 25%;">
					<input name="patRefGnctdHospitalDeptUnit" tabindex="2"  maxlength="15" type="text" class="input75prcnt" >
				</div>
				<div class="div-table-col label" style="width: 25%;">&nbsp;</div>
				<div class="div-table-col control" style="width: 25%;">&nbsp;</div>
				
			</div>
		</div>
		

		
		<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a tabindex="1" href="#" class="button" id="submitId"><span class="save">Save</span></a>
				<a tabindex="1" href="#" class="button" id="clearId" ><span class="clear">Clear</span></a>
				<a tabindex="1" href="#" class="button" id="cancelId" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel">Cancel</span></a>
			</div>
			
		</div>
	</div>
	<div class="div-table" >
		<div class="div-table-row emptyBar ">
			<div class="div-table-col"> </div>
		</div>
	</div>
	<div class="div-table-simple" id="nonprintableDiv2">
		<div class="div-table-row">
			<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%"></div>
		</div>
	</div>
	<div class="div-table-simple" id="fatherorSpouseError" style="display: none">
		<div class="div-table-row">
			<div  class="div-table-col alignLeft fontError" style="width: 100%">Father Name Or Spouse Name Is Compulsary</div>
		</div>
	</div>
	
	<div class="div-table" id="nonprintableDiv3">
		<div class="div-table-row">
			<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%"></div>
		</div>
		
	</div>
	</s:if>
	<s:else>
		<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						New Patient Registration 
				</div>
			</div>
		</div>
		<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%">
			<s:actionerror/>
		</div>
	</s:else>
	<div id="divPrintId" ></div>
	<input type="hidden" name="isIdRequired" />	
	<input type="hidden" name="isMandataryReadOnlyTrue" id="isMandataryReadOnlyTrueId" value="0" />	
	<input type="hidden" name="hiddenPatIdNo" />
	<input type="hidden" name="isReferCompulsory" />	
	<input type="hidden" name="departmentCode" />	
	<input type="hidden" name="prevCrNo" />	
	<input type="hidden" name="alreadyRegisteredFromRenewalConfig" />	
	<input type="hidden" name="strAreadyRegisteredFlag" />
	<input type="hidden" name="barcodeSlipPrintFlag"  />
	<input type="hidden" name="seniorCitizenCatCode"  />
	<input type="hidden" name="patActualAmount" />
	<input type="hidden" name="targetId" />	
	<input type="hidden" name="isSnomedServiceOn" />
	
	
	
	<input type="hidden" name="hiddenCatOrRegstrdPopupFlag" id="hiddenCatOrRegstrdPopupFlagId" />
	
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>	
</s:form>
<script type="text/javascript" src="./../../registration/transactions/js/splPatientRegistration.js" /></script>
</center>
<div id='loadingmessage' style='display:none'>
       <img src='/HISRegistration/hisglobal/images/ajax-loader.gif'/>
</div>
</body>
</html>