<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="../../hisglobal/css/jquery-ui.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/js/basic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.searchabledropdown-1.0.8.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-migrate-1.0.0.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.bpopup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.bpopup.min.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>

<script language="JavaScript" type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<%-- <script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
 --%><script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/avai/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

<script language="JavaScript" src="/HIS/hisglobal/js/generictemplate/validationFunctions.js"></script>
<script language="JavaScript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>
<script language="JavaScript" src="/HIS/hisglobal/js/date_validator.js"></script>
<script type="text/javascript" src="./../../registration/transactions/js/saveBarcodeSlipPrintEntryInHMIS.js" /></script> 
<!--By Surabhi for SNOMED CT word base  -->
<!-- <link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/searchtool.css"> -->
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/searchtoolnew.css">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css"> -->
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
	#nonprintableDiv1 {
		display: none;
	}
	#nonprintableDiv2 {
		display: none;
	}
	#nonprintableDiv3 {
		display: none;
	}
}

.border .div-table-col {
	border: 1px solid black;
}
</style>

<title>Patient Registration</title>
<script>
		var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>";
		var PATIENT_REG_CATEGORY_GROUP_PAID = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_PAID %>";
		var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE %>";

	/* $(window).on("load.loading1", function() {
		$('[name="departmentUnitCode"]')[0].focus();
		emgregistration.fetchDefaultValues();
		//checkForUnknownOnLoad();
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		if (document.getElementsByName("isUnknown")[0].checked == true) {
			document.getElementsByName("isMLC")[0].checked = true;
			$('[name="isMLC"]')[0].value = "1";
			document.getElementsByName("isMLC")[0].disabled = true;
		} else {
			document.getElementsByName("isMLC")[0].checked = false;
			$('[name="isMLC"]')[0].value = "0";

			document.getElementsByName("isMLC")[0].disabled = false;
		}
		enableRelation(document.getElementsByName("isRelative")[0]);

	}); */


	function setIsTrauma(obj)
	{
			document.getElementsByName("isTraumaPatient")[0].value="1"; 
		}

	function broughtDeadConsultant(obj){
		//var ConsultantName= deptObj.options[deptObj.selectedIndex].value;
		var ConsultantName =  obj.options[broughtByDeclaredId.selectedIndex].value;
		document.getElementsByName("broughtByConsultant")[0].value=ConsultantName;
		}

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
						 emgregistration.setPaymentModeRefId(e);
						return true;
				 }
					  
			   else
				   {
				   alert("Selected Payment Mode is not applicable for this Patient Billing Category!");
				   $(e).find('option').removeAttr('selected');
				   $(e).find('option[value='+oldVal+']').attr('selected','selected'); 
				   emgregistration.setPaymentModeRefId(e);
				   return false;
				   
				   }
	 }
	 else
		 {
			  emgregistration.setPaymentModeRefId(e);
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
	
		<s:form action="EmgRegistration">
			<input type="hidden" name="flagHasActionError" id="flagHasActionErrorId" value='<s:property value="%{hasActionErrors()}"/>'>
  			<s:if test="%{!hasActionErrors()}">
			<div class="wrapper rounded" id="nonprintableDiv1">
				<!-- <h1>Patient Registration</h1> -->
				
				<div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col" style="width:17%;">
							<s:text name="emergency"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="registration"/></div>
							
						<div class="div-table-col" style="width:83%;">
							<div class="div-table">
								<div class="div-table-row title">
									<div class="div-table-col " style="width: 5%;" align="right"><s:text name="unknown"/></div>
									<div class="div-table-col " style="width: 3%;">
										<input type="checkbox" name="isUnknown" tabindex="3" value="1" onclick='checkForUnknownOnLoad(this);onclickFocus();'>
									</div>

									 <div class="div-table-col " style="width: 10%;" align="right"><s:text name="global.is"/>&nbsp;<s:text name="brought"/>&nbsp;<s:text name="dead"/></div>
									<div class="div-table-col " style="width: 3%;">
										<input type="checkbox" name="isBroughtDead" tabindex="3" value="1" onclick="onBroughtDead(this);onclickFocus();">
									</div>  
									
									 <!--Added by Vasu on 01.May.18  -->
									<div class="div-table-col " style="width: 10%;" align="right"><s:text name="global.is"/>&nbsp;<s:text name="ambulatory"/></div>
									<div class="div-table-col " style="width: 3%;">
										<input type="checkbox" name="isAmbulatory" tabindex="3" onclick="checkForAmbulatory(this);onclickFocus();">
									</div> 
                                     <!-- End Vasu --> 
									<div class="div-table-col " style="width: 5%;" align="right"><s:text name="mlc"/></div>
									<div class="div-table-col " style="width: 3%;">
										<input type="checkbox" name="isMLC" tabindex="3" value="1" onclick='checkMLC(this)' />
									</div>
								</div>
							</div>
						  </div>
					</div>
					
					<div class="div-table-row ">
						<div class="div-table-col label" style="width: 48%">
							<font color="red">*</font><s:text name="visiting"/>&nbsp;<s:text name="global.unit"/>
						</div>
						<div class="div-table-col width control" style="width: 52%">
							<select name="departmentUnitCode" tabindex="1" class="select23prcnt" onchange='emgregistration.setDepartmentDependents(this)'>
								<option value="-1">Select Value</option>
							</select> <input type="hidden" name="ageRange">
						</div>
					</div>
				</div>

				<div class="div-table">
					<div class="div-table-row title">
			<div class="div-table-col" style="width: 50%">
				<div class="div-table-col">
				<s:text name="global.patient"/>&nbsp;<s:text name="global.details"/>		
				</div>
				
				<div class="div-table-col width label" style="width: 25%"><%--By monika for 27457--%>
						<font color="red">*</font><s:text name="global.patient"/>&nbsp;<s:text name="global.category"/>	
				</div>
				<div class="div-table-col width control" style="width: 16%" id="_divpatPrimaryCatCodeId">
					<select name="patPrimaryCatCode" id="patPrimaryCatCodeId" tabindex="1" class="select90prcnt">
						<option value="-1">Select Value</option>
					</select>					
				</div>
				<input name="patPrimaryCat" type="hidden" />
				<input name="patCatShortName" type="hidden" />
				<input name="patPrimaryCatGrp" type="hidden" />
				
				<div id="divCatCardId" class="div-table-col" style="width: 16%; display: none;">
					<div  class="div-table" >
						<div class="div-table-row title">
						<!--  #Start			:Sheeldarshi 
						#Modify Date(CR/PRS)	:02ndJun'15 
						#Reason					:Bug 8347  -->
							 <div id="divCatCardNoLabelId" class="div-table-col"  style="width: 30%">
								<font color="red">*</font><s:text name="global.cardno"/>
							</div> 
						<!-- End -->
							<div class="div-table-col control" style="width: 50%">
								<input id="shownPatIdNoId" tabindex="1" name="patIdNo"  type="text" />
							</div>
						</div>
					</div>
				</div>
				<div id="divImgCatCardId" class="div-table-col control" style="width: 16%; display: none;">
					<img id="imgCatCardId" align="left" src="../../hisglobal/images/search_icon1.gif" />
				</div>
				<div id="divSpareCatId" class="div-table-col" style="width: 16%;"></div>
				</div>
								
				<div class="div-table-col" style="width: 30% ">
					<div class="div-table-col label-yellow" id="divFeeLabel" style="width: 20%;display: none;"><font color="red">*</font><s:text name="registrationFee"/></div>
						<div class="div-table-col control" id="divFeeVal" style="width: 25%;display: none;">
					        <img id="INRId" align="left" src="../../hisglobal/images/INR.png" />&nbsp;&nbsp;&nbsp;
			 		        <div id="amount" class="div-table-col label-yellow" ></div>
							 <input name="patAmountCollected" id="patAmountCollectedId" tabindex="3"  type="hidden" readonly="readonly">
						</div>
						<div class="div-table-col control" id="divFeeVal2" style="width:25%;display: none;">
							<select name="paymentModeCode" tabindex="1" id="paymentModeCodeId" class="select100prcnt" onchange=' return paymentModeCodeOnChangeValidate(this)'>
								<option value=""></option>
							</select>
						</div>
						<div class="div-table-col control" id="divFeeVal3" style="width:25%;display: none;">
							<input tabindex="1" name="paymentModeRefId" maxlength="20" type="text" />
						</div>	
				</div>
								
				<div class="div-table-col" style="width: 10%">
					 <div align="right" class="div-table-col">
						<img class="button" style="cursor:pointer" src='../../hisglobal/images/camera2.gif'
							alt="Capture Photo" title="Capture Photo" onkeypress="if(event.keyCode==13) upload(event)" 
							onclick="upload(event)" >
					</div>	     
		 			<div align="right" class="div-table-col">
		 				<img class="button" style="cursor:pointer" src='../../hisglobal/images/view.psd.1.gif' alt="View Photo" title="View Photo" 
							onclick="show(event);"/>
		 			</div>
				</div>
				
			</div>
									<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="0">
									
					<div id="divCatGroupBeneficiaryId" style="display: none;">
					
			<div class="div-table">
			    <div class="div-table-row">
					<div class="div-table-col width16 label">
						<font color="red">*</font><s:text name="reference"/>&nbsp;<s:text name="letter"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" class="input75prcnt" maxlength="50" tabindex="1">
					</div>
					<!--Added by Mukund on 22.07.2016  -->
					<div class="div-table-col width16 label"><font color="red">*</font><s:text name="Letter"/>&nbsp;<s:text name="type"/>
					</div>
					<div class="div-table-col width16 control" id="divLetterType">
						<select name="letterType"  tabindex="1" id="letterTypeId"   style="width:160px;">
							<option value="-1">Select Value</option>
						</select>
					</div>
					<!--End:Mukund -->
					<div class="div-table-col width16 label">
						<font color="red">*</font><s:text name="letter"/>&nbsp;<s:text name="date"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditLetterDate" id="creditLetterDateId" readonly="readonly" class="input60prcnt" tabindex="1">
					</div>
				</div>
			
				<div class="div-table-row">
					<div class="div-table-col width16 label"><font color="red">*</font><s:text name="company"/>
					</div>
					<div class="div-table-col width16 control" id="divClient" >
						<select name="clientCode"  tabindex="1" id="clientCodeId" class="select77prcnt" onchange="setCompany();" style="height: 19px;width:160px;">
							<option value="-1">Select Value</option>
						</select>
					</div>
					<div class="div-table-col width16 control"  id="clientNameLabel" style="display: none">
					</div>
					<div class="div-table-col width16 label" id="staffCardNoLabel"><s:text name="staff"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="staffCardNo" id="staffCardNoId" class="input60prcnt" maxlength="50" tabindex="1">
					</div>
					<div class="div-table-col width16 label"><s:text name="staff"/>&nbsp;<s:text name="global.name"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="staffCardName" id="staffCardNameId" class="input75prcnt" maxlength="100" onkeyup="setPatNameSelf();">
					</div>
				</div>
				
				<div class="div-table-row" >
					<div class="div-table-col width16 label"><s:text name="relation"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="global.patient"/>
					</div>
					<div class="div-table-col width16 control" >
						<select name="relationWithStaff"  tabindex="2" id="relationWithStaffId" class="select77prcnt" onchange="setRelation()">
							<option value="-1">Select Value</option>
						</select>					
					</div>
					<div class="div-table-col width16 label"><s:text name="validfor"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="cardvalidityDate" id="cardvalidityDateId" readonly="readonly" class="input60prcnt">
					</div>
					<div class="div-table-col width16 label"><s:text name="credit"/>&nbsp;<s:text name="Limit"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditLimit" id="creditLimitId" class="input60prcnt numberinput" maxlength="11">
					</div>
				</div>
			</div>
			</div>
			
			<div id="divCatGroupArogyaShreeBeneficiaryId" style="display: none;">
			<div class="div-table">
				<div class="div-table-row" >
					<div class="div-table-col width16 label" ><s:text name="district"/>
					</div>
					<div class="div-table-col width16 control" >
						<select name="agsDistrictCode"  tabindex="2" id="agsDistrictCodeId" class="select77prcnt" >
							<option value="-1">Select Value</option>
						</select>
					</div>
					<div class="div-table-col width16 label"><font color="red">*</font><s:text name="arogyasri"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="agsNo" id="agsNoId" class="input75prcnt" maxlength="15" tabindex="1">
					</div>
					<div class="div-table-col width16 label"><s:text name="arogyamitra"/>&nbsp;<s:text name="global.counter"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="agsCounterNo" id="agsCounterNoId" class="input75prcnt" maxlength="3">
					</div>
				<!-- Added By Mukund on 22.07.2016-->
					<div class="div-table-col width16 label"><s:text name="credit"/>&nbsp;<s:text name="Limit"/>
					</div>
					<div class="div-table-col width20 control" >
						<input type="text" name="agsCreditLimit" id="agsCreditLimitId" class="input60prcnt numberinput" maxlength="11">
					</div>	
									
				</div>
			</div>
			</div>
			<div id="divCatGroupCreditSchemeBeneficiaryId" style="display: none;">
			<div class="div-table">
				<div class="div-table-row" >
					<div class="div-table-col width16 label"><font color="red">*</font><s:text name="patCardNo"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditSchemeCardNo" id="creditSchemeCardNoId" class="input75prcnt" maxlength="15" tabindex="1"> 
					</div>
					<div class="div-table-col width16 label"><s:text name="credit"/>&nbsp;<s:text name="Limit"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditSchemeCardLimit" id="creditSchemeCardLimitId" class="input60prcnt numberinput" maxlength="11">
					</div>					
				</div>
			</div>
			</div>
			<!--End:Mukund  -->
			<%--Modified by Mukund Vinayak on 08.06.2016 for get AadharCard impression before patientName  --%>
			
			<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="adhar"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patNationalId" maxlength="12" tabindex="2" type="text" class="input75prcnt">
						 <span id="divaadhaarRegisteredId" style="display:none">
							<!-- <img id="aadhaarRegisteredFlagId" name="aadhaarRegisteredFlag#emgregistration" src="/HISRegistration/hisglobal/images/search_icon1.gif" /> -->
							</span> 
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="otherId"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patDocType" tabindex="2" id="patDocTypeId" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> <input type="hidden" name="patDocTypeName" />
						</div>
						<div class="div-table-col control" style="width: 32%;">
							<div id="divCardNoId" class="div-table"
								style="width: 100%; display: none;">
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 50%;">
										<font color="red">*</font><s:text name="global.cardno"/>
									</div>
									<div class="div-table-col control" style="width: 50%;">
										<input name="patCardNo" type="text" class="input75prcnt" tabindex="1">
									</div>
								</div>
							</div>
						</div>
					</div>
			<!--By Mukund on 23.Mar.18  -->
			<div class="div-table-row" id="divAadharConsentId" style="display:none">
				<div class="div-table-col label" style='width:100%' >
					<p style="padding-right:13%">I, the holder of above Aadhaar Number, hereby give my consent to obtain my Aadhaar No and other demographic details for Authentication. I Agree<font color="red">*</font>
						<input name="isAadharConsent" id="isAadharConsentYes" value="1" onclick="checkUncheckAadharConsent(this)" type="radio">Yes
						<input name="isAadharConsent" id="isAadharConsentNo" value="0" onclick="checkUncheckAadharConsent(this)" type="radio">No</p>
				</div>
			</div>
			<!--End on 23.03.18  -->

					<div class="div-table-row">
						<div class="div-table-col label
						" style="width: 16%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/>&nbsp;<font color="red">*</font>(<s:text name="first"/>)</div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patFirstName"  tabindex="1" maxlength="33" type="text" 
								onkeyup="multilingualConversion(this,document.getElementById('patFirstNameInMultiLangId'));" class="input75prcnt">
												</div>
					
						
						<div class="div-table-col label" style="width: 16%;">(<s:text name="middle"/>)</div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patMiddleName" tabindex="2" maxlength="33" type="text" onclick="changeTabIndex()"
								onkeyup="multilingualConversion(this,document.getElementById('patMiddleNameInMultiLangId'));" class="input75prcnt">
						
						
						</div>
						<div class="div-table-col label" style="width: 16%;">(<s:text name="last"/>)</div>
						<div class="div-table-col control" style="width: 16%;">
						<input name="patLastName" tabindex="2" maxlength="33" type="text" 
							onkeyup="multilingualConversion(this,document.getElementById('patLastNameInMultiLangId'));" class="input75prcnt">
										
						</div>
					</div>
					
			<%--End: Mukund --%>
			
					<div class="div-table-row" style="display: none;">
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
					</div>
					

					<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><font color="red">*</font> 
							<input type="radio" name="isActualDob" value="0" onclick="showDivAgeDob();setMotherValidRule();" checked="checked" /><s:text name="age"/> &nbsp; 
							<input type="radio" name="isActualDob" value="1" onclick="showDivAgeDob();setMotherValidRule();" /><s:text name="bob"/> &nbsp;
						</div>

						<div class="div-table-col" style="width: 16%">
							<div id="divAge" class="div-table">
								<div class="div-table-row">
									<div class="div-table-col" style="width: 100%;">
										<input id="patAgeId" tabindex="1" name="patAge" type="text" class="input30prcnt" maxlength="5" onblur="setMotherValidRule();"> 
										<select id="patAgeUnitId" tabindex="1" name="patAgeUnit" class="select45prcnt" onblur="setMotherValidRule();showDivAgeDob();">
											<option value="-1">Select Value</option>
										</select>
									</div>
								</div>
							</div>

							<div id="divDob" class="div-table" style="display: none;">
								<div class="div-table-row">
									<span class="div-table-col control" style="width: 100%;">
									       <!-- <input id="patDOBId" tabindex="1" type="text" name="patDOB" onblur="setMotherValidRule();" class="input45prcnt">&nbsp;-->
									    	<div id="divNewDOB">
												<input id="patDOBId_Dup" tabindex="1" type="text" name="patDOB_Dup" onblur="setMotherValidRule();" class="input45prcnt" />
											</div>
											<div id="divNewDOBHidden">
												<!-- <input type='hidden' name='patDOB' id='patDOBId'/> -->
											</div>
											<script type="text/javascript">
											//DateValidator.setup("divNewDOB", "patDOB_Dup", "", "dd-Mon-yyyy", "input45prcnt", "patDOBId_Dup", "1", "divNewDOBHidden");
											</script>
									</span>	
								</div>
							</div>
						</div>
						<div class="div-table-col label" style="width: 16%;">
							<font color="red">*</font><s:text name="gender"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patGenderCode" id="patGenderCodeId" tabindex="1" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> 
							<input name="patGender" type="hidden">
							<input name="defaultpatGenderCode" id="defaultpatGenderCodeId"
								type="hidden" value="-1"> <input name="defaultpatGender"
								id="defaultpatGenderId" type="hidden">
								<input name="userBoundDefaultGender" type="hidden"> 
						</div>
						
						<div class="div-table-col label" style="width: 16%;"><s:text name="patCaste"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patCasteCode" id="patCasteCodeId" tabindex="2" onclick="changeTabIndex()" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> <input name="patCaste" type="hidden">
						</div>
						<%-- 
						<div class="div-table-col label" style="width: 16%;">
							<font color="red">*</font><s:text name="amountCollected"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAmountCollected" tabindex="3" type="text" class="input75prcnt" readonly="readonly">
						</div>--%>

					</div>


					<div class="div-table-row">
						

						
					</div>
					
					<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="fathersName"/></div>
							<div class="div-table-col control" style="width: 16%;">
							<input name="patGuardianName"  tabindex="1" id="patGuardianName" maxlength="60" type="text" class="input75prcnt">
						</div>
						<div class="div-table-col" style="width: 16%;"> 
							<div class="div-table">
							<div class="div-table-row">
								<div class="div-table-col" style="width: 25%;display: inline;" align="left">
								(OR)&nbsp;</div>
								<div class="div-table-col label" style="width: 75%;display: inline;">
								<s:text name="husbandName"/></div>
							</div>
							</div>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patHusbandName" tabindex="1" maxlength="60" type="text" class="input75prcnt">
						</div>
						<div class="div-table-col" style="width: 16%;"> 
							<div class="div-table">
							<div class="div-table-row">
								<div class="div-table-col" style="width: 25%;display: inline;" align="left">
								(OR)&nbsp;</div>
								<div class="div-table-col label" style="width: 75%;display: inline;">
								<s:text name="motherName"/></div>
							</div>
							</div>
						</div>				
						<div class="div-table-col control" style="width: 16%;">
							<input name="patMotherName"  tabindex="1" maxlength="60" type="text" class="input75prcnt">
						</div>
					</div>					

					<div class="div-table-row">
					<div class="div-table-col label" style="width: 16%;"><s:text name="maritalStatus"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
						<!-- Start:Sheeldarshi
						Reason:Bug 10060 - Spouse field should be disable as per the marital status field. -->
							<select name="patMaritalStatusCode" tabindex="2" onclick="changeTabIndex()" id="patMaritalStatusCodeId" class="select77prcnt" onchange="enableDisableSpouse();">
							<!-- End -->
								<option value="-1">Select Value</option>
							</select> <input type="hidden" name="patMaritalStatus">
						</div>
						

						<div class="div-table-col label" style="width: 16%;"><s:text name="religion"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patReligionCode" id="patReligionCodeId" tabindex="2" onclick="changeTabIndex()" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> <input type="hidden" name="patReligion">
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="birplace"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patBirthPlace" maxlength="50" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
						</div>
					</div>

					<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="patOccupation"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patOccupation" tabindex="2" onclick="changeTabIndex()" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select>
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="monthlyIncome"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patMonthlyIncome" maxlength="11" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="visitreason"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<!-- <textarea id="textArea" tabindex="2" onclick="changeTabIndex()"  name="patVisitReason" rows="1" cols="15" class="textarea75prcnt" maxlength="99"></textarea> -->
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
						
					</div>

					<%--<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="adhar"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patNationalId" maxlength="12" tabindex="2" type="text" class="input75prcnt">
						 <span id="divaadhaarRegisteredId" style="display:none">
							<img id="aadhaarRegisteredFlagId" name="aadhaarRegisteredFlag#emgregistration" src="/HISRegistration/hisglobal/images/search_icon1.gif" />
							</span> 
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="otherId"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patDocType" tabindex="2" id="patDocTypeId" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> <input type="hidden" name="patDocTypeName" />
						</div>
						<div class="div-table-col control" style="width: 32%;">
							<div id="divCardNoId" class="div-table"
								style="width: 100%; display: none;">
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 50%;">
										<font color="red">*</font><s:text name="global.cardno"/>
									</div>
									<div class="div-table-col control" style="width: 50%;">
										<input name="patCardNo" type="text" class="input75prcnt" tabindex="1">
									</div>
								</div>
							</div>
						</div>
					</div> --%>

					<div class="div-table-row">
						<!--  ## 		Modification Log							
					   	   ##		Modify Date				:12thDec'14 
		 	               ##		Reason	(CR/PRS)		:Bug fix 7722
		 	               ##		Modify By				:Sheeldarshi  -->
						<div class="div-table-col label" style="width: 16%;"><span id="idmark1mandatory" style="display:none;width:1%;"><font color="red">*</font></span><s:text name="identitymark1"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patIdMark1" maxlength="50" type="text" class="input75prcnt"/>
						</div>

						<div class="div-table-col label" style="width: 16%;"><span id="idmark2mandatory" style="display:none;width:1%;"><font color="red">*</font></span><s:text name="identitymark2"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patIdMark2" maxlength="50" type="text" class="input75prcnt"/>
						</div>
						<!--Added by Vasu for Trauma/Non Trauma patients -->
						<div class="div-table-col label" style="width:16%;"><s:text name="Is Trauma"/></div>
						<div class="div-table-col control" style="width:16%;">
			                 <s:radio label="Answer" name="isTraumaPatient" list="#{'1':'Yes','0':'No'}" value="0" onchange="setIsTrauma(this);"/>
				        </div>
						<!--End Vasu -->
					</div>
					<!-- ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi  -->
			<div class="div-table-row" id="divRMO" style="display: none;">
			<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="Authorized By"/>:</div>
			<div class="div-table-col control" style="width: 16%;">
					<select name="patRMOEmployee"  tabindex="1" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
			</div>
			</div>
			<!-- End:Sheeldarshi -->
				</div>
				<!-- End:Sheeldarshi -->
				<div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col" style="width: 32%;"><s:text name="adddetail"/></div>
						<div class="div-table-col label" style="width: 16%;">
							<font color="red">*</font> <s:text name="country"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patAddCountryCode" tabindex="1" id="patAddCountryCodeId" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> <input name="defaultpatAddCountryCode" type="hidden"> <input
								name="patAddCountry" type="hidden">
						</div>
						<div class="div-table-col label" style="width: 16%;">
							<font color="red">*</font><s:text name="state"/>
						</div>
						<div id="divStateComboId" class="div-table-col control"
							style="width: 16%;">
							<select name="patAddStateCode" tabindex="1" id="patAddStateCodeId" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> <input name="defaultpatAddStateCode" type="hidden"> <input
								name="patAddState" type="hidden">
						</div>
						<div id="divStateTextId" class="div-table-col control"
							style="width: 16%; display: none;">
							<input name="patAddState" id="patAddStateId" maxlength="50"
								type="text" class="input75prcnt">
						</div>
					</div>

					<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="hno"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddHNo" maxlength="60" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="street"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddStreet" maxlength="60" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="location"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddCityLoc" maxlength="60" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
						</div>

					</div>
					<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;">
							<font color="red">*</font><s:text name="district"/>
						</div>
						<div id="divDistrictComboId" class="div-table-col control"
							style="width: 16%;">
							<select name="patAddDistrictCode" tabindex="1" id="patAddDistrictCodeId"
								class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> <input name="defaultpatAddDistrictCode" type="hidden"> <input
								name="patAddDistrict" type="hidden">
						</div>
						<div id="divDistrictTextId" class="div-table-col control"
							style="width: 16%; display: none;">
							<input name="patAddDistrict" id="patAddDistrictId" maxlength="15"
								type="text" class="input75prcnt">
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="city"/><s:text name="slash"/><s:text name="village"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddCity" maxlength="60" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
						</div>
						<div class="div-table-col label" style="width: 16%;"><!-- <font color="#FF0000" size="2">*</font> --><s:text name="pin"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddPIN" maxlength="6" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
							<input name="defaultpatAddPIN" type="hidden">
						</div>

					</div>

					<div class="div-table-row">

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.landmark"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddLandMarks" maxlength="60" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
						</div>

						<div class="div-table-col label" style="width: 16%;"><s:text name="areaCategory"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patIsUrban" tabindex="2" onclick="changeTabIndex()" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select>
						</div>

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.email"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddEmailId" maxlength="70" tabindex="2" onclick="changeTabIndex()" type="text" class="input75prcnt">
						</div>

					</div>
					<div class="div-table-row">

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone"/>&nbsp;<s:text name="number"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddPhoneNo" id="patAddPhoneNo" tabindex="2" onclick="changeTabIndex()" maxlength="15" type="text" class="input75prcnt">
						</div>

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone"/>&nbsp;<s:text name="owner"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patAddPhoneOwner" tabindex="2" onclick="changeTabIndex()" class="select77prcnt">
								<option value="-1">Select Value</option>
								<option value="1">Self (patient)</option>
								<!-- <option value="2">ANM</option>-->
								<option value="3">Other</option>
								<!-- <option value="4">Neighbour</option> -->
								<option value="5">Family member</option>
							</select>
						</div>

						<div class="div-table-col label" style="width: 16%;"><font color="#FF0000" size="2">*</font><s:text name="mobileNo"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patAddMobileNo" maxlength="10" tabindex="1" type="text" class="input75prcnt" onkeyup="checkMobileOrEmgCntNo()">
						</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="emergency"/>&nbsp;<s:text name="contactNo"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<input name="patEmgCntNo" maxlength="10" class="input75prcnt" tabindex="1" onkeyup="checkMobileOrEmgCntNo()">
						</div>
					</div>
				</div>

				<div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col" style="width: 80%"><s:text name="referdtl"/></div>
						<div class="div-table-col label" style="width: 20%" align="right">
							<div style="display: none" id="isReferCompusoryId"><s:text name="referdtl"/>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="compulsory"/>
							</div>
						</div>

						<div class="div-table-col label" style="width: 20%">
							<s:text name="unit.isrefer"/><input type="checkbox" name="isReferred" tabindex="1" value="0" onclick='Isreferred(this);onclickFocusInstName();'>
						</div>
					</div>
				</div>
				<div id="divRefDtlId" class="div-table" style="display: none;">
					<div class="div-table-row">
						<div id="divReferredInstitute" class="div-table-col label"
							style="width: 50%;">
							<input type="radio" name="referringInstType" value="G" onclick="showdivhoscode(this);onclickFocusInstName();" checked="checked" tabindex="2" /><s:text name="associatedInst"/>&nbsp; 
							<input type="radio" name="referringInstType" value="O" onclick="showdivhoscode(this);onclickFocusInstNameOthr();" /><s:text name="other"/> &nbsp;
						</div>
						<div id="divRefHosCode" class="div-table-col" style="width: 50%;">
							<div class="div-table" style="width: 100%;">
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 50%;">
										<font color="red">*</font> <label for="Institute Name"><s:text name="global.institute"/>&nbsp;<s:text name="global.name"/>
											</label>
									</div>
									<div class="div-table-col control" style="width: 50%;">
										<select id="patRefGnctdHospitalCodeId" name="patRefGnctdHospitalCode" class="select77prcnt" tabindex="1">
											<option value="-1">Select Value</option>
										</select>
									</div>
								</div>
							</div>
						</div>

						<div id="divRefHosname" class="div-table-col"
							style="width: 50%; display: none;">
							<div class="div-table" style="width: 100%;">
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 50%;">
										<font color="red">*</font><label for="Institute Name"><s:text name="global.institute"/>&nbsp;<s:text name="global.name"/>
											</label>
									</div>
									<div class="div-table-col control" style="width: 50%;">
										<input name="patRefHospitalName" maxlength="50" type="text" size="20" tabindex="1" class="input60prcnt">
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

				<div class="div-table" id="divReferred" style="display: none;">
					<div class="div-table-row">
						<div class="div-table-col label" style="width: 25%;"><s:text name="referring"/>&nbsp;<s:text name="global.institute"/>&nbsp;<s:text name="crNo"/>
						</div>
						<div class="div-table-col control" style="width: 25%;">
							<input type="text" name="patRefGnctdHospitalCrno" maxlength="15" tabindex="2" class="input60prcnt">
						</div>
						<div class="div-table-col label" style="width: 25%;">
							<font color="red">*</font><s:text name="referredBy"/>
						</div>
						<div class="div-table-col control" style="width: 25%;">
							<input type="text" name="patRefDoctor" maxlength="60" tabindex="1" class="input60prcnt">
						</div>
					</div>
<!-- start : Surabhi
     reason : change the alignment referral institute unit -->
					<%-- <div class="div-table-row">
						<div class="div-table-col label" style="width: 25%;">
							<s:text name="referring"/>&nbsp;<s:text name="institute"/>&nbsp;<s:text name="global.department"/></div>
						<div class="div-table-col control" style="width: 25%;">
							<select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDept" tabindex="2" class="select77prcnt">
								<option value="-1">Select Value</option>
								<option value="0">Other</option>
							</select>
						</div>

						<div id="divRefHospitalDeptOtherId" class="div-table-col"
							style="width: 50%; display: none;">
							<div class="div-table">
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 50%;">
										<font color="red">*</font><s:text name="other"/>&nbsp;<s:text name="referred"/>&nbsp;<s:text name="global.department"/>
									</div>
									<div class="div-table-col control" style="width: 50%;">
										<input name="patRefHospitalDeptOther" maxlength="50" type="text" size="20" class="input75prcnt">
									</div>
								</div>

							</div>
						</div>

					</div>
					<div class="div-table-row">
						<div class="div-table-col label" style="width: 25%;">
							<s:text name="referring"/>&nbsp;<s:text name="institute"/>&nbsp;<s:text name="global.unit"/></div>
						<div class="div-table-col control" style="width: 25%;">
							<input name="patRefGnctdHospitalDeptUnit" maxlength="15" tabindex="2" type="text" size="20" class="input75prcnt">
						</div>
						<div class="div-table-col label" style="width: 25%;">&nbsp;</div>
						<div class="div-table-col control" style="width: 25%;">&nbsp;</div>

					</div> --%>
					<div class="div-table-row">
			
				<div class="div-table-col label" style="width: 25%;">
					<s:text name="referring"/>&nbsp;<s:text name="institute"/>&nbsp;<s:text name="global.department"/>
				</div>
				<div class="div-table-col control" style="width: 25%;">
				 <div class="div-table-col control" style="width: 60%;">
					<s:set name="optionsRefDeptDtl" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL]"></s:set>
										<s:if test="%{#optionsRefDeptDtl!= null}">
											<s:select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDept" 
												cssStyle="width:200px" list="optionsRefDeptDtl"  
												 listKey="value" listValue="label" 
											   headerKey="-1"  headerValue="Select Value" />
										</s:if>
										<s:else>
											<select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDept" style="width :145px">
												<option value="-1">Select Value</option>
												<option value="0">Other</option>
											</select>
										</s:else>
				</div>
					<div id="divRefHospitalDeptOtherId"  class="div-table-col control" style="width: 50%;">
								<input name="patRefHospitalDeptOther" maxlength="50" type="text" class="input100prcnt" size="20"  tabindex="1" >
					</div>
					
				</div>
				
				<div class="div-table-col" style="width: 50%;">
					<div class="div-table">
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%;"><s:text name="referring"/>&nbsp;<s:text name="institute"/>&nbsp;<s:text name="global.unit"/></div>
							<div class="div-table-col control" style="width: 50%;">
								<input name="patRefGnctdHospitalDeptUnit" maxlength="15" type="text" class="input60prcnt" size="20"  tabindex="2" >
							</div>
						</div>
						
					</div>
				</div>
				<!-- end -->
			</div>
				</div>

				<div class="div-table" >
				<div class="div-table-row title">
					<div class="div-table-col" style="width: 50%">
						<s:text name="brought"/>&nbsp;<s:text name="by"/>&nbsp;<s:text name="global.detail"/>
					</div>
					<div class="div-table-col label" style=" width: 30%" align="right">
							<div style="display:none"  id="isUnknownId"><s:text name="brought"/>&nbsp;<s:text name="by"/>&nbsp;<s:text name="global.detail"/>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="compulsory"/></div>
					</div>
					
					<div class="div-table-col label" style="width: 20%">
							 <s:text name="global.is"/>&nbsp;<s:text name="brought"/>&nbsp;<s:text name="by"/> <input type="checkbox" name="isBroughtBy" value="0" tabindex="1" onclick="broughtBy(this)">
					</div>
				</div>
		</div>

				<div id="broughtById">
					<div class="div-table">
						<div class="div-table-row ">
							<div class="div-table-col label" style="width: 25%;">
								<font color="#FF0000" size="2">*</font> <s:text name="brought"/>&nbsp;<s:text name="by"/>
							</div>

							<div id="knownRelative" style="">
								<div class="div-table-col control" style="width: 25%;">
									<select name="isRelative" id="isRelativeKnownId" onchange="enableRelation(this)" tabindex="1" class="select77prcnt">
										<option value="-1">Select Value</option>
										<option value="2">Police</option>
										<option value="3">Other</option>
										<option value="1">Relative</option>
									</select>
								</div>
							</div>

							


							<div class="div-table-col label" style="width: 25%;">
								<font color="#FF0000" size="2"></font> <s:text name="relationship"/>
							</div>

							<div class="div-table-col control" style="width: 25%;">
								<s:select cssClass="select85prcnt" list="#{'-1':'Select Value'}" name="broughtByRelationCode" id="broughtByRelationCodeId" tabindex="1" value="broughtByRelationCode" onchange="enableRelation()" disabled="disabled" />
							</div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col label" style="width: 25%;">
								<div id="nameBroughtBy">
									<font color="#FF0000" size="2"></font> <s:text name="global.name"/>
								</div>
							</div>

							<div class="div-table-col control" style="width: 25%;">
								<input name="broughtByName" maxlength="90" tabindex="1" type="text" class="input60prcnt">
							</div>
							<div class="div-table-col label" style="width: 25%;">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif"></font>
								<s:text name="brought"/>&nbsp;<s:text name="fromLocation"/>
							</div>
							<div class="div-table-col control" style="width: 25%;">
								<!-- 3 -->
								<input name="broughtByLocation" maxlength="100" tabindex="1" type="text" class="input60prcnt"/>

							</div>
						</div>
					</div>

					<div id="policeDetailDiv" style="display: none">
						<div class="div-table">
							<div class="div-table-row ">
								<div class="div-table-col label" style="width: 25%;">
									<div id="nameBroughtBy">
										<font color="#FF0000" size="2"></font><s:text name="designation"/>
									</div>
								</div>

								<div class="div-table-col control" style="width: 25%;">
									<input name="constableDesig" tabindex="1" maxlength="50" type="text" class="input60prcnt">
								</div>
								<div class="div-table-col label" style="width: 25%;">
									<font color="#FF0000" size="2"></font> <s:text name="badgeno"/>
								</div>

								<div class="div-table-col control" style="width: 25%;">
									<input name="constableBadgeNo" maxlength="50" tabindex="1" type="text" class="input60prcnt">
								</div>

							</div>
						</div>
					</div>
					<div id="phoneBroughtByDiv">
						<div class="div-table">
							<div class="div-table-row width100">
								<div class="div-table-col label" style="width: 25%;">
									<font color="#FF0000" size="2"></font><s:text name="mobileNo"/> 
								</div>

								<div class="div-table-col control" style="width: 25%;">
									<input name="broughtByPhone" maxlength="10" tabindex="1" type="text" class="input60prcnt">
								</div>
								<div class="div-table-col label" style="width: 25%;">
									<font color="#FF0000" size="2"></font> <s:text name="address"/> 
								</div>

								<div class="div-table-col control" style="width: 25%;">
									<textarea id="textArea" tabindex="1" name="broughtByAddress" rows="1" cols="15" class="textarea60prcnt"></textarea>
								</div>

							</div>
						</div>
					</div>

					<div id="policeStnDiv" style="display: none">
						<div class="div-table">
							<div class="div-table-row ">
								<div class="div-table-col label" style="width: 25%;">
									<s:text name="policestation"/></div>
								<div class="div-table-col control" style="width: 25%;">
									<input name="policeStation" tabindex="2" maxlength="50" type="text" class="input60prcnt">
								</div>


							</div>
						</div>
					</div>
					<div class="div-table">
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 25%;">Death Declared By</div>
							<div class="div-table-col control" style="width: 25%;">
								<select name="broughtByDeclared" tabindex="2" onclick="" id="broughtByDeclaredId" class="select77prcnt" onchange="broughtDeadConsultant(this)" > </select>
							</div>
						</div>
					</div>
				</div>

				<div class="div-table-button">
					<div class="div-table-row footerBar">
						<div class="div-table-col"></div>
					</div>
					<div class="div-table-row emptyBar">
						<div class="div-table-col"></div>
					</div>
					<div class="div-table-row" align="center">
						<a tabindex="1" href="#" class="button" id="submitId"><span class="save"><s:text name="save"/> </span></a>
						<a tabindex="1" href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/> </span></a>
						<a tabindex="1" href="#" class="button" id="cancelId" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp')"><span class="cancel"><s:text name="cancel"/> </span></a>
					</div>

				</div>
				
			</div>
			<div class="div-table">
				<div class="div-table-row emptyBar ">
					<div class="div-table-col"></div>
				</div>
			</div>
			<div class="div-table-simple" id="nonprintableDiv2">
				<div class="div-table-row">
					<div id='divErrorMsgId' class="div-table-col alignLeft fontError"
						style="width: 100%"></div>
				</div>
			</div>
			<div class="div-table-simple" id="fatherorSpouseError"
				style="display: none">
				<div class="div-table-row">
					<div class="div-table-col alignLeft fontError" style="width: 100%">Father Name Or Spouse Name Is Compulsary</div>
				</div>
			</div>
			<div class="div-table" id="nonprintableDiv3">
				<div class="div-table-row">
					<div id='divNormalMsgId' class="div-table-col alignLeft"
						style="width: 100%"></div>
				</div>

			</div>
			</s:if>
			<s:else>
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
								<s:text name="emergency"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="registration"/>
						</div>
					</div>
				</div>
				<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%">
					<s:actionerror/>
				</div>
			</s:else>
			<div id="divPrintId"></div>
			<input type="hidden" name="isIdRequired" />
			<input type="hidden" name="isMandataryReadOnlyTrue"
				id="isMandataryReadOnlyTrueId" value="0" />
			<input type="hidden" name="hiddenPatIdNo" />
			<input type="hidden" name="isReferCompulsory" />
			<input type="hidden" name="departmentCode" />
			<input type="hidden" name="hiddenCatOrRegstrdPopupFlag"
				id="hiddenCatOrRegstrdPopupFlagId" />
			<input type="hidden" name="strAreadyRegisteredFlag" />
			<input type="hidden" name="processID" value=""/>
			<input type="hidden" name="filname"  value=""/>
			<input type="hidden" name="crNo" value='<s:property value="patCrNo"/>'/>
			<input type="hidden" name="clientName" />
			<input type="hidden" name="relationNameWithStaff" />
			<input type="hidden" name="patActualAmount" />	
			<input type="hidden" name="patCatDocCode" />
			<input type="hidden" name="patCatDocIsAlternateId" />
			
			<input type="hidden" name="hiddenCatOrRegstrdPopupFlag" id="hiddenCatOrRegstrdPopupFlagId" />
			<input type="hidden" name="isApprovalReq" />	
			<input type="hidden" name="memSlNo" />	
			<input type="hidden" name="strEmployeePatCatGroup" />	
			<input type="hidden" name="aadhaarSearchableFromRenewalConfig" />
			<input type="hidden" name="seniorCitizenAgeLimit"  />
			<input type="hidden" name="seniorCitizenCatCode"  />
			<input type="hidden" name="patRelatedEmpName" />
			<input type="hidden" name="patMemRelationCode" />
			<input type="hidden" name="patRelatedEmpGender" />
			<input type="hidden" name="patOldCatCode" />
			<input type="hidden" name="patMemRelationName" />
			<input type="hidden" name="patMemDeptName" />
			<input type="hidden" name="isAadharConsentGiven" />
			<!-- <input type="hidden" name="isAmbulatoryCheckUncheck"> -->
			<s:hidden name="isAmbulatoryCheckUncheck"></s:hidden>
			<input type="hidden" name="selectedCRToVisit" />
			<input type="hidden" name="radioForDuplicate_parent" />
			<s:hidden name="isTrauma"></s:hidden>
			<input type="hidden" name="broughtByConsultant" />
			<%-- <s:hidden name="broughtByDeclared"></s:hidden> --%>
			<input type="hidden" name="barcodeSlipPrintFlag"  />
			<input type="hidden" name="targetId"  />
			<input type="hidden" name="isSnomedServiceOn" />
			
			<cmbPers:cmbPers></cmbPers:cmbPers>
			<s:token></s:token>  
		</s:form>
		<script type="text/javascript"
			src="./../../registration/transactions/js/emgPatientRegistration.js" /></script>
		<script type="text/javascript"
			src="./../../com/ecentric/js/aadhaarSearch.js" /></script>
	</center>
	<div id='loadingmessage' style='display: none'>
		<img src='/HISRegistration/hisglobal/images/ajax-loader.gif' />
	</div>
</body>
</html>