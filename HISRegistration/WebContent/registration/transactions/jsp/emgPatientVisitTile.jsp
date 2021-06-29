<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" type="text/css" href="dateinput.css"/>
<link href="/HIS/hisglobal/css/dateinput_small_skin1.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/customToolTip/css/writeOnToolTip.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">
<link href='/HIS/hisglobal/css/qtip/jquery.qtip.min.css' rel='stylesheet' />

<%-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.tools.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/customToolTip/js/writeOnToolTip.js"></script>


<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script src='/HIS/hisglobal/js/qtip/jquery.qtip.min.js'></script>
<script src='/HIS/hisglobal/js/qtip/writeOnToolQTip.js'></script>
 --%>
 
 <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/basic.js"></script>
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
 <script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
 <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
 <script language="JavaScript" src="/HISRegistration/registration/transactions/js/regValidation.js"></script>
 <script src='/HIS/hisglobal/js/qtip/jquery.qtip.min.js'></script>
<script src='/HIS/hisglobal/js/qtip/writeOnToolQTip.js'></script>
<script type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>
<%-- <script type="text/javascript" src="/HIS/hisglobal/customToolTip/js/writeOnToolTip.js"></script> --%>
 <script type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>
 <script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
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
    document.getElementById("txt-snomed-ct-search_"+elmtid).value = "";
   
});

window.onload = function(){	
/*
	//By Mukund on 31.03.2017
	if(document.getElementById('divAppointReferElseId'))
	{
		$('#divAppointPatientId').show();
		$('#divReferPatientId').show();
	}
	else
	{
		$('#divAppointPatientId').hide();
		$('#divReferPatientId').hide();
	}
	
 */
	load1('1','');
	load1('2','');
	
}

function load1(elmtId,semantictag)
{
	if(elmtId==null || elmtId==undefined)
	{
		elmtId="1"; semantictag="DISORDER";
	}
	else if(elmtId=="1")
	{
	 id="patVisitReason";
	}
	else if(elmtId=="2")
	{
	 id="arrPatVisitReason";
	}
	setTarget(id);
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};

	var callbck_index =function(ret_OUT){setValue(ret_OUT);};

	//selectSNOMEDCTmulti('ACTIVE',semantictag,'PREFERRED_EXCLUDING_FSN','200','null',elmtId,callbck_index);
	selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	
	//Added by Surabhi on 1st Feb'17 for free text if snomed field is disabled  
	
	if(document.getElementsByName("patVisitReason")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_1").value;
	document.getElementsByName("patVisitReason")[0].value=s;
	}
	
	if(document.getElementsByName("arrPatVisitReason")[0].value=="")
	{
	var t=document.getElementById("txt-snomed-ct-search_2").value;
	document.getElementsByName("arrPatVisitReason")[0].value=t;
	}
	//end
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	
}

function setTarget(id)
{
	if(id=="patVisitReason")
	{
		document.getElementsByName("targetId")[0].value="patVisitReason";
	}
	else if(id=="arrPatVisitReason")
	{
		document.getElementsByName("targetId")[0].value="arrPatVisitReason";
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
		else if(target == "arrPatVisitReason") // Visit Reason
		{
			//alert("inside patVisitReason of setValue()");
			 
			 targetPrefferedTerm=document.getElementsByName("arrsnomdPTVisitReason")[0];  //preffered term
			 targetConceptId=document.getElementsByName("arrsnomdCIdVisitReason")[0];   //concept Id
		
			 if(document.getElementsByName("arrPatVisitReason")[0].value=="") document.getElementsByName("arrPatVisitReason")[0].value = str;
			else document.getElementsByName("arrPatVisitReason")[0].value = document.getElementsByName("arrPatVisitReason")[0].value + ", " + str;
			
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
		//alert("free text");
		document.getElementsByName("patVisitReason")[0].value=s;
		}
		
		if(document.getElementsByName("arrPatVisitReason")[0].value=="")
		{
		var t=document.getElementById("txt-snomed-ct-search_2").value;
		document.getElementsByName("arrPatVisitReason")[0].value=t;
		}
}
//end
</script>
 <!--End: Surabhi  -->
 <script>
/*Function Created for	: Automatic Selection & Deselection of new and old Dept Visit
 * By 					: Raj Kumar
 * On					: 3-Aprl-2019
 */
 $(function(){
	$('input[name="newDepartmentVisit"]').on('change',function(){
				if($('input[name="newDepartmentVisit"]').val()=='on')
				{
					$('input[name="oldDepartmentVisit"]').prop('checked', false);
					emgPatientVisitJsObj.showHideNewAndOldVisit();
					$("#divNewDeptVisitId").show();
		
					$("#departmentCodeId").show();
					$("#patVisitReasonId").show();
					$('[name="departmentCode"]').show();
					
							if($('input[name="newDepartmentVisit"]').is(":checked"))
								{
								$('[name="departmentCode"]').validatebox({validType: ['selectCombo[-1]']});
									
								}
						
					
				}
			});
	
	$('input[name="oldDepartmentVisit"]').on('change',function(){
		if($('input[name="oldDepartmentVisit"]').val()=='on')
		{
			 $('input[name="newDepartmentVisit"]').prop('checked', false);
			 emgPatientVisitJsObj.showHideNewAndOldVisit();
			 $("#divNewDeptVisitId").hide();
			 $("#patVisitReasonId").hide();
			
			  $('[name="departmentCode"]').hide();
			  $("#divCatGroupBeneficiaryId").hide();
			  $("#divCatGroupArogyaShreeBeneficiaryId").hide();
			  if($('input[name="oldDepartmentVisit"]').is(":checked"))
				{   
					 $('[name="departmentCode"]').validatebox({validType: ''});
					
				}
			 
		
		}
	});

	
}); 
</script>
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
		#nonprintableDiv4 
		{
		 display: none; 
		}
		
}

.border .div-table-col{
border: 1px solid black;
}
</style>
<title>Emergency Patient Visit</title>



</head>
<script>
	
	var CHILD_DEPT_CODE						=	"123,152,153";
	var IS_REFERRED_TRUE					=	<%= RegistrationConfig.IS_REFERRED_TRUE %>
	var IS_REFERRED_FALSE					=	<%= RegistrationConfig.IS_REFERRED_FALSE %>
	var IS_ASSOCIATED_FALSE					=	<%= RegistrationConfig.IS_ASSOCIATED_FALSE %>
	var IS_ASSOCIATED_TRUE					=	<%= RegistrationConfig.IS_ASSOCIATED_TRUE %>
	var IS_PAT_REFER_BY_LIST_TRUE			=	<%= RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE %>
	var MAX_AGE_TO_REGISTER_IN_CHILD_DEPT	=	"14";
	var IS_REFERRED_IN_OUT_ACCEPT_INTERNAL	=	<%= RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL %>
	var IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL	=	<%= RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL %>
	var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>";
	var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE %>";
	var PATIENT_REG_CATEGORY_GROUP_PAID 	= "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_PAID %>";

	$(window).on("load.loading1", function() {
		if($("#afterGoId").val()=="1"){
			emgPatientVisitJsObj.showHideNewAndOldVisit();
			//document.getElementById("cashCollection").style.display="block";		
			//By Mukund on  08.05.2017
			if($('[name="patPrimaryCatGrpCode"]').val()==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)
			{
				//alert("YO\n"+$('[name="renewalRequired"]').val()+"\n"+$('[name="regRenewalRequired"]').val());
						$('[name="creditLetterRefNo"]').prop('disabled', true);
						$('[name="letterType"]').prop('disabled', true);
						$('[name="creditLetterDate"]').prop('disabled', true);
						$('[name="clientCode"]').prop('disabled', true);
						$('[name="staffCardNo"]').prop('disabled', true);
						$('[name="staffCardName"]').prop('disabled', true);
						$('[name="cardvalidityDate"]').prop('disabled', true);
						$('[name="creditLimit"]').prop('disabled', true);
						$('[name="relationWithStaff"]').prop('disabled', true);
						
						$('#creditLetterRefNoId').bind('input', function() {
			
						    $('[name="letterType"]').prop('disabled', false);
							$('[name="creditLetterDate"]').prop('disabled', false);
							$('[name="clientCode"]').prop('disabled', false);
							$('[name="staffCardNo"]').prop('disabled', false);
							$('[name="staffCardName"]').prop('disabled', false);
							$('[name="cardvalidityDate"]').prop('disabled', false);
							$('[name="creditLimit"]').prop('disabled', false);
							$('[name="relationWithStaff"]').prop('disabled', false);
								 
							$('[name="letterType"]').val('-1');
							$('[name="relationWithStaff"]').val('-1');
							//$('[name="clientCode"]').val('-1');
							//$('[name="staffCardNo"]').val('');
							$('[name="staffCardName"]').val('');
							$('[name="creditLimit"]').val('');
			
						 	var today = moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
							var _todayPlus30Days=moment().add(29, 'days').format('DD-MMM-YYYY');//new Date();
							//_todayPlus30Days.setDate(_todayPlus30Days.getDate() + 29);
					    	//_todayPlus30Days=_todayPlus30Days.toLocaleFormat('%d-%b-%Y');
							$('#creditLetterDateId').DatePicker({
								format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
						  }).val(today);
			
							$('#cardvalidityDateId').DatePicker({
								format: 'd-M-Y',default_position :'below',start_date:_todayPlus30Days,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
							}).val(_todayPlus30Days);
 
						});
				}
				
			}//End Mukund on 09.05.2017
		});
	
	function setCompany() {
		
		//alert($("#clientCodeId option:selected").html());
		$('[name="clientName"]')[0].value = $("#clientCodeId option:selected").html();
		//alert("value :"+$('[name="clientName"]')[0].value);
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
	
	 
	 if(val.split('#')[1]=='1a')
		{ 	if(val.split('#')[2]==$('#patPriCat').val())
				 {
						emgPatientVisitJsObj.setPaymentModeRefId(e);
						return true;
				 }
					  
			   else
				   {
				   alert("Selected Payment Mode is not applicable for this Patient Billing Category!");
				   $(e).find('option').removeAttr('selected');
				   $(e).find('option[value='+oldVal+']').attr('selected','selected'); 
				  emgPatientVisitJsObj.setPaymentModeRefId(e);
				   return false;
				   
				   }
	 }
	 else
		 {
			emgPatientVisitJsObj.setPaymentModeRefId(e);
			 return true;
		
		 }
		 
	}
</script>
<body>
	<s:form action="EmgPatientVisit">
		<%-- <s:set name="theme" value="simple" scope="page" /> --%>
		<s:set name="afetrGoForJsp" value="afterGo"></s:set>
		
		<div class="wrapper rounded" id="nonprintableDiv1">

			  <%-- <div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col title width85"><s:text name="emergency"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.revisit"/></div>
					<div id="divCashCollection" class="div-table-col title width15" >
					<!-- <a id="cashCollection" style="cursor: pointer;color: white;display: none;" onclick="openDependentPopup('/HISRegistration/registration/transactions/CASHCOLLECTIONPOPUPEmgPatientVisit.action',event,250,600);" 
					title="Cash Collection Detail"><font color="white" style=""><u>Total Cash</u></font></a> -->
					</div>
				</div>
			</div>   --%>
         <!-- Added by Vasu dated on 07.May.18 -->
         <s:if test="%{#afetrGoForJsp!=0}">
                <div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col" style="width:25%;">
							<s:text name="emergency"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="revisit"/></div>
							
						<div class="div-table-col" style="width:75%;">
							<div class="div-table">
								<div class="div-table-row title">
									<div class="div-table-col " style="width: 23%;" align="right"><s:text name=""/></div>
									<div class="div-table-col " style="width: 10%;">
										<!-- <input type="checkbox" name="isUnknown" tabindex="3" value="1" onclick='checkForUnknownOnLoad(this);onclickFocus();'> -->
									</div>

									 <%-- <div class="div-table-col " style="width: 23%;" align="right"><s:text name="global.is"/>&nbsp;<s:text name="brought"/>&nbsp;<s:text name="dead"/></div>
									<div class="div-table-col " style="width: 10%;">
										<input type="checkbox" name="isBroughtDead" tabindex="3" value="1" onclick="onBroughtDead(this);onclickFocus();">
									</div>  --%>
									
									<div class="div-table-col " style="width: 23%;" align="right"><s:text name="global.is"/>&nbsp;<s:text name="ambulatory"/></div>
									<div class="div-table-col " style="width: 10%;">
										<input type="checkbox" name="isAmbulatory" checked = "checked" tabindex="3" onclick="checkForAmbulatory(this);">
									</div> 
									<div class="div-table-col " style="width: 23%;" align="right"><s:text name="mlc"/></div>
									<div class="div-table-col " style="width: 10%;">
										<input type="checkbox" name="isMLC" tabindex="3"  onclick='checkMLC(this)' />
									</div>
								</div>
							</div>
						  </div>
					</div>
				</div> 
				</s:if>
				<s:else>
				  <div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col title width85"><s:text name="emergency"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.revisit"/></div>
					<div id="divCashCollection" class="div-table-col title width15" >
					<!-- <a id="cashCollection" style="cursor: pointer;color: white;display: none;" onclick="openDependentPopup('/HISRegistration/registration/transactions/CASHCOLLECTIONPOPUPEmgPatientVisit.action',event,250,600);" 
					title="Cash Collection Detail"><font color="white" style=""><u>Total Cash</u></font></a> -->
					</div>
				</div>
			</div> 
				</s:else> 
         <!-- End Vasu -->
			<his:InputCrNoTag />
			
			
			<input type="hidden" id="patPriCat" value="<%= session.getAttribute("PatPriCategory") %>" />	

			<s:hidden name="afterGo" id="afterGoId" value="%{afterGo}" />
			<s:hidden name="modeCase" value="%{modeCase}"></s:hidden>
			<s:hidden name="isPatReferByList" value="%{isPatReferByList}" />
			<s:hidden name="strRenewalType" value="%{strRenewalType}" />
			<s:hidden name="emgRenewalRequired" value="%{emgRenewalRequired}" />
			<s:hidden name="patAmountHospitalWise" value="%{patAmountHospitalWise}" />
			<s:hidden name="patAmountDeptWise" value="%{patAmountDeptWise}" />
			<s:hidden name="patRenewalAmountDeptWise" value="%{patRenewalAmountDeptWise}" />
			<s:hidden name="print" id="printId" value="%{print}" />
			<s:hidden name="goWithoutDeptVisitOn" value="%{goWithoutDeptVisitOn}" />
			<s:hidden name="errorMessage" value="%{errorMessage}"/>
			<s:hidden name="patPrimaryCatGrpCode" value="%{patPrimaryCatGrpCode}" id="patPrimaryCatGrpCodeId"/>
			<s:hidden name="sysdate" value="%{sysdate}" id="sysdateId"/>
			<s:hidden name="patActualAmount" value="%{patActualAmount}" />		
			<s:hidden name="patRenewalActualAmount" value="%{patRenewalActualAmount}" />	
			<%-- <s:hidden name="clientCode" value="%{clientCode}"/>		 --%>
			
			<s:set name="afetrGoForJsp" value="afterGo"></s:set>
			<s:set name="modeCaseForJsp" value="modeCase"></s:set>
			<s:set name="renewalTypeForJsp" value="strRenewalType"></s:set>
			<s:set name="emgRenewalRequiredForJsp" value="emgRenewalRequired"></s:set>
			<s:set name="patAmountHospitalWiseForJsp" value="patAmountHospitalWise"></s:set>
			<s:set name="patAmountDeptWiseForJsp" value="patAmountDeptWise"></s:set>
			<s:set name="patRenewalAmountDeptWiseForJsp" value="patRenewalAmountDeptWise"></s:set>
			<s:set name="isPatReferByListForJsp" value="isPatReferByList"></s:set>
			<s:set name="printForJsp" value="print"></s:set>
			<s:set name="goWithoutDeptVisitOnForJsp" value="goWithoutDeptVisitOn"></s:set>
			<s:set name="errorMessageForJsp" value="errorMessage"></s:set>
			<s:set name="patPrimaryCatGrpCodeForJsp" value="patPrimaryCatGrpCode"></s:set>
			<s:set name="patActualAmountForJsp" value="patActualAmount"></s:set>
			
			<%-- <s:include value="/registration/transactions/jsp/patientDetailsTile.jsp"/> --%>
			
			
			<s:if test="%{#afetrGoForJsp!=0}">
				<%-- <s:action name="patientDetail" executeResult="true"></s:action> --%>
				<%-- <s:include value="/registration/transactions/jsp/patientDetailsTile.jsp"/> --%>
				<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
			
				<div class="div-table" >
					<%-- <s:if test="modeCaseForJsp==1"> --%>
						
						<div class="div-table-col width24 label"><s:text name="emergency"/>&nbsp;<s:text name="new"/>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.visit"/></div>
						<div class="div-table-col width23 control" >
							<s:if test="%{(newDepartmentVisit=='On' || newDepartmentVisit=='on')}">
								<s:if test="%{#modeCaseForJsp!=2}">
									<input type="checkbox" name="newDepartmentVisit" checked="checked" disabled="disabled" onclick="emgPatientVisitJsObj.showHideNewAndOldVisit();" />
								</s:if>
								<s:else>
									<input type="checkbox" name="newDepartmentVisit" checked="checked" onclick="emgPatientVisitJsObj.showHideNewAndOldVisit();" />
								</s:else>
							</s:if>
							<s:else>
								<s:if test="%{#modeCaseForJsp!=2}">
									<input type="checkbox" name="newDepartmentVisit" disabled="disabled" onclick="emgPatientVisitJsObj.showHideNewAndOldVisit();" />
								</s:if>
								<s:else>
									<input type="checkbox" name="newDepartmentVisit" onclick="emgPatientVisitJsObj.showHideNewAndOldVisit();" />
								</s:else>
							</s:else>
						</div>
						<div class="div-table-col width24 label"><s:text name="emergency"/>&nbsp;<s:text name="old"/>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.visit"/></div>
						<div class="div-table-col width24 control" >
							<s:if test="%{oldDepartmentVisit=='On'||oldDepartmentVisit=='on'}">
								<s:if test="%{#modeCaseForJsp!=2}">
									<input type="checkbox" name="oldDepartmentVisit" checked="checked" disabled="disabled" onclick="emgPatientVisitJsObj.showHideNewAndOldVisit();" />
								</s:if>
								<s:else>
									<input type="checkbox" name="oldDepartmentVisit" checked="checked" onclick="emgPatientVisitJsObj.showHideNewAndOldVisit();" />
								</s:else>
							</s:if>
							<s:else>
								<s:if test="%{#modeCaseForJsp!=2}">
									<input type="checkbox" name="oldDepartmentVisit" disabled="disabled" onclick="emgPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:if>
								<s:else>
									<input type="checkbox" name="oldDepartmentVisit" onclick="emgPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:else>
							</s:else>
						</div>
						
				</div>
				
				<!-- Credit Beneficiary Details begins here	 -->
				<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY}">
				<div id="divCatGroupBeneficiaryId">
							<div class="div-table">
								<div class="div-table-row">
									<div class="div-table-col title width100 "><s:text name="beneficiary"/>&nbsp;<s:text name="global.detail"/></div>
								</div>
								
								
								<!-- Added BY Garima for Credit Beneficiary Details -->
								
								<div class="div-table-row">
								<%-- <s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY}"> --%>
									<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="1">
										<div class="div-table-col width16 label">
											<font color="red">*</font>Reference Letter No
										</div>
										<div class="div-table-col width16 control" >
											<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" value="<s:property value="creditLetterRefNo" />" class="input90prcnt letterNo" maxlength="50" tabindex="1">
										</div>
										<div class="div-table-col width16 label"><font color="red">*</font><s:text name="Letter"/>&nbsp;<s:text name="type"/>
										</div>
										<div class="div-table-col width16 control" id="divLetterType">
										<s:select name="letterType" id="letterTypeId" tabindex="1" list="%{#session.letterTypeOptionList}" 
																listKey="value" listValue="label" 
																headerKey="-1" headerValue="Select Value" />
										</div>
										<div class="div-table-col width16 label">
											<font color="red">*</font>Letter Date
										</div>
										<div class="div-table-col width16 control" >
											<!-- <input type="date" name="creditLetterDate" id="creditLetterDateId" value="Today" tabindex="1" class="input60prcnt letterDate" onblur="dateNotInRange(this)" style="text-align: left;"> -->
											<input id="creditLetterDateId" tabindex="1" type="text" name="creditLetterDate" onchange="dateNotInRange(this);" class="input60prcnt" value="<s:property value="creditLetterDate" />" />&nbsp;
										</div>
								</div>
								
							   	
							   	<div class="div-table-row">
										<div class="div-table-col width16 label"><s:text name="company"/>
										</div>
										<!--#Start					:Sheeldarshi 
											#Modify Date(CR/PRS)	:15thJuly'15 
											#Reason					:Change for adding Organisation combo on All Registration Pages
										-->
										 <%-- <div class="div-table-col width16 control"  id="clientNameLabel"><s:property value="%{clientName}"/> --%>
										<!-- </div>  -->
										
										
										<!-- added the organisation combo by Surabhi -->
										<div class="div-table-col width16 control"  >
										<%-- <s:select key="clientName" headerKey="-1" headerValue="Select Value" 
				 								 list="%{#session.clientOptionList}" listKey="value" listValue="label" name="clientName" cssClass="select63prcnt" onchange="setCompany();" id="clientCodeId"> 
				 					</s:select> --%>
				 					<s:select key="clientCode" headerKey="-1" headerValue="Select Value" 
				 								 list="%{#session.clientOptionList}" listKey="value" listValue="label" id="clientCodeId" name="clientCode" cssClass="select90prcnt" onchange="setCompany();"> 
				 				</s:select>	
										<%-- <div class="div-table-col width16 control"  >
										<s:select key="clientCode" headerKey="-1" headerValue="Select Value" 
				 								 list="%{#session.clientOptionList}" listKey="value" listValue="label" name="clientCode" cssClass="select63prcnt" onchange="setCompany();" id="clientCodeId"> 
				 					</s:select>	 --%>
				 					<!-- End -->
									</div>
										<%-- <div class="div-table-col width16 label" id="refNoLabel"><font color="red">*</font><s:text name="reference"/>&nbsp;<s:text name="letter"/>&nbsp;<s:text name="number"/>
										</div>
										<div class="div-table-col width16 control" >
											<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" class="input60prcnt letterNo" maxlength="50">
										</div>
										<div class="div-table-col width16 label"><font color="red">*</font><s:text name="letter"/>&nbsp;<s:text name="date"/>
										</div>
										<div class="div-table-col width16 control" >
											<input type="date" name="creditLetterDate" id="creditLetterDateId"  class="input77prcnt letterDate">
										</div> --%>
										<div class="div-table-col width16 label" id="staffCardNoLabel"><s:text name="staff"/>&nbsp;<s:text name="number"/>
										</div>
										<div class="div-table-col width16 control" >
											<input type="text" name="staffCardNo" id="staffCardNoId" value="<s:property value="staffCardNo" />" class="input60prcnt" maxlength="50">
										</div>
										<div class="div-table-col width16 label"><s:text name="staff"/>&nbsp;<s:text name="global.name"/>
										</div>
										<div class="div-table-col width16 control" >
											<input type="text" name="staffCardName" id="staffCardNameId" value="<s:property value="staffCardName" />" class="input67prcnt" maxlength="100">
										</div>
									</div>
							   	
								<%-- <div class="div-table-row">
									<div class="div-table-col width16 label"><s:text name="company"/>
									</div>
									<!--#Start					:Sheeldarshi 
										#Modify Date(CR/PRS)	:15thJuly'15 
										#Reason					:Change for adding Organisation combo on All Registration Pages
									-->
									 <div class="div-table-col width16 control"  id="clientNameLabel">
									<!-- </div>  -->
									<div class="div-table-col width16 control"  >
									<s:select key="clientCode" headerKey="-1" headerValue="Select Value" 
			 								 list="%{#session.clientOptionList}" listKey="value" listValue="label" name="clientCode" cssClass="select63prcnt" onchange="setCompany();" id="clientCodeId"> 
			 					</s:select>	
			 					<!-- End -->
								</div>
									<div class="div-table-col width16 label" id="refNoLabel"><font color="red">*</font><s:text name="reference"/>&nbsp;<s:text name="letter"/>&nbsp;<s:text name="number"/>
									</div>
									<div class="div-table-col width16 control" >
										<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" class="input60prcnt letterNo" maxlength="50">
									</div>
									<div class="div-table-col width16 label"><font color="red">*</font><s:text name="letter"/>&nbsp;<s:text name="date"/>
									</div>
									<div class="div-table-col width16 control" >
										<input type="date" name="creditLetterDate" id="creditLetterDateId"  class="input77prcnt letterDate">
									</div>
								</div> --%>
								
								
								<div class="div-table-row">
										<div class="div-table-col width16 label"><s:text name="relation"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="global.patient"/>
										</div>
										<div class="div-table-col width16 control" >
											<s:select key="relationWithStaff" headerKey="-1" headerValue="Select Value" 
				 								 list="%{#session.optionRelationDetail}" listKey="value" listValue="label" name="relationWithStaff" cssClass="select63prcnt"> 
				 							</s:select>				
										</div>
										<div class="div-table-col width16 label"><s:text name="validfor"/>
										</div>
										<div class="div-table-col width16 control" >
										<input type="text" name="cardvalidityDate" id="cardvalidityDateId"  class="input60prcnt" value="<s:property value="cardvalidityDate" />" />
										</div>
										<div class="div-table-col width16 label"><s:text name="credit"/>&nbsp;<s:text name="Limit"/>
										</div>
										<div class="div-table-col width16 control" >
										<input type="text" name="creditLimit" id="creditLimitId" class="input60prcnt numberinput" maxlength="11" value='<s:property value='creditLimit'/>' />
										</div>
										
									</div>
								
								<%-- <div class="div-table-row">
									<div class="div-table-col width16 label"><s:text name="relation"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="global.patient"/>
									</div>
									<div class="div-table-col width16 control" >
										<s:select key="relationWithStaff" headerKey="-1" headerValue="Select Value" 
			 								 list="%{#session.optionRelationDetail}" listKey="value" listValue="label" name="relationWithStaff" cssClass="select63prcnt"> 
			 							</s:select>				
									</div>
									<div class="div-table-col width16 label" id="staffCardNoLabel"><font color="red">*</font><s:text name="staff"/>&nbsp;<s:text name="number"/>
									</div>
									<div class="div-table-col width16 control" >
										<input type="text" name="staffCardNo" id="staffCardNoId" value="<s:property value="staffCardNo" />" class="input60prcnt" maxlength="50">
									</div>
									<div class="div-table-col width16 label"><s:text name="staff"/>&nbsp;<s:text name="global.name"/>
									</div>
									<div class="div-table-col width16 control" >
										<input type="text" name="staffCardName" id="staffCardNameId" value="<s:property value="staffCardName" />" class="input67prcnt" maxlength="100">
									</div>
								</div> --%>
								
								<%-- <div class="div-table-row" style="display: none">										
									<div class="div-table-col width16 label"><s:text name="validfor"/>
									</div>
									<div class="div-table-col width16 control" >
										<input type="text" name="cardvalidityDate" id="cardvalidityDateId" value="<s:property value="cardvalidityDate" />" readonly="readonly" class="input60prcnt">
									</div>
									
								</div> --%>
							</div>
				</div>
				</s:if>
				<s:else>
									<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="0">
				</s:else>
			
				<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE}">
				<div id="divCatGroupArogyaShreeBeneficiaryId">
				<div class="div-table">
					<div class="div-table-row">
									<div class="div-table-col title width100 "><s:text name="credit"/>&nbsp;<s:text name="beneficiary"/>&nbsp;<s:text name="without"/>&nbsp;<s:text name="reference"/>&nbsp;<s:text name="global.detail"/></div>
					</div>
					<div class="div-table-row" >
						<div class="div-table-col width16 label" ><s:text name="district"/>
						</div>
						<div class="div-table-col width16 control" >
							<s:select key="agsDistrictCode" headerKey="-1" headerValue="Select Value" 
			 								 list="%{#session.stateWiseDistrictList}" listKey="value" listValue="label" name="agsDistrictCode" cssClass="select90prcnt"> 
			 				</s:select>		
						</div>
						<div class="div-table-col width16 label"><s:text name="arogyasri"/>&nbsp;<s:text name="number"/>
						</div>
						<div class="div-table-col width16 control" >
							<input type="text" name="agsNo" id="agsNoId" value="<s:property value="agsNo" />" maxlength="15" size="20">
						</div>
						<div class="div-table-col width16 label"><s:text name="arogyamitra"/>&nbsp;<s:text name="global.counter"/>&nbsp;<s:text name="number"/>
						</div>
						<div class="div-table-col width16 control" >
							<input type="text" name="agsCounterNo" id="agsCounterNoId" value="<s:property value="agsCounterNo" />"  maxlength="3" size="10">
						</div>					
					</div>
					<div class="div-table-row">
						<div class="div-table-col width16 label"><s:text name="credit"/>&nbsp;<s:text name="Limit"/>
						</div>
						<div class="div-table-col width16 control" >
										<input type="text" name="agsCreditLimit" id="agsCreditLimitId" value="<s:property value="agsCreditLimit" />" class="input60prcnt numberinput" maxlength="11">
										</div>
					</div>
				</div>
				</div>
				</s:if>
				
			
				<s:if test="%{newDepartmentVisit=='On' || newDepartmentVisit=='on'  || #goWithoutDeptVisitOnForJsp==1}">
					<div class="div-table" id="divNewDeptVisitId">
						<div class="div-table-row title">
							<div class="div-table-col width80">
								<s:text name="emergency"/>&nbsp;<s:text name="new"/>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.visit"/>&nbsp;<s:text name="stamp"/>
							</div>
							<div class="div-table-col label width20">
								<s:text name="global.is"/>&nbsp;<s:text name="provisional"/>&nbsp;<s:text name="mlc"/><input type="checkbox" name="mlcFlag" value="0" onclick="emgPatientVisitJsObj.checkUncheck(this);">
							</div>
						</div>
						<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY}">
							<s:set name="newDeptVisitWidth" value="'width10'"></s:set>
						</s:if>
						<s:else>
							<s:set name="newDeptVisitWidth" value="'width12'"></s:set>
						</s:else>
						<div class="div-table-row">
							<div class="div-table-col label <s:property value="#newDeptVisitWidth"/>">
								<font color="red" size="2">*</font><s:text name="global.department"/>
							</div>
							<div class="div-table-col control <s:property value="#newDeptVisitWidth"/>">
								<s:if test="%{#isPatReferByListForJsp != @registration.config.RegistrationConfig@IS_PAT_REFER_BY_LIST_TRUE}">
									<div class="div-table">
										<div class="div-table-row">
											<div class="div-table-col width100">
												<s:set name="deptOption" value="#session[@registration.config.RegistrationConfig@EMGREGISTRATIONDESK_OPTION_DEPARTMENT]"></s:set>
												<s:if test="%{#deptOption!= null}">
													<s:select name="departmentCode" id="departmentCodeId" cssClass="select90prcnt" list="deptOption" 
															listKey="value" listValue="label" 
															headerKey="-1" headerValue="Select Value" />
												</s:if>
												<s:else>
													<select name="departmentCode" class="select90prcnt"><option value="-1">Select Value</option> </select>
												</s:else>
											</div>
											<s:hidden name="toDepartmentCode" value="%{departmentUnitCode}"/>
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="div-table">
										<div class="div-table-row">
											<div class="div-table-col width100">
												<s:property value="toDepartment"/>
											</div>
											<s:hidden name="toDepartmentCode" value="%{toDepartmentCode}"/>
										</div>
									</div>
								</s:else>
							</div>
							<%-- <s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY}">
								<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="1">
									<div class="div-table-col <s:property value="#newDeptVisitWidth"/> label">
										<font color="red">*</font><s:text name="reference"/>&nbsp;<s:text name="letter"/>&nbsp;<s:text name="number"/>
									</div>
									<div class="div-table-col <s:property value="#newDeptVisitWidth"/> control" >
										<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" class="input90prcnt letterNo" maxlength="50">
									</div>
									<div class="div-table-col <s:property value="#newDeptVisitWidth"/> label">
										<font color="red">*</font><s:text name="letter"/>&nbsp;<s:text name="date"/>
									</div>
									<div class="div-table-col <s:property value="#newDeptVisitWidth"/> control" >
										<!-- <input type="date" name="creditLetterDate" id="creditLetterDateId" value="Today" class="input60prcnt letterDate" onblur="dateNotInRange(this)" style="text-align: left;"> -->
										<input id="creditLetterDateId" tabindex="1" type="text" name="creditLetterDate" onblur="dateNotInRange(this);" class="input45prcnt">&nbsp;
									</div>
							</s:if>
							<s:else>
								<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="0">
							</s:else> --%>
							
							<div class="div-table-col label <s:property value="#newDeptVisitWidth"/>" style="height:25px;"><s:text name="visitreason"/></div>
							<div class="div-table-col control <s:property value="#newDeptVisitWidth"/>" style="height:28px;">
								<!-- <input type="text" name="patVisitReason" class="tooltipClass" id="patVisitReasonId" 
											maxlength="99" size="10" title="Visit Reason" style="height:19px;"> -->
											<!--By Surabhi on 07.11.2016-->
											<div align="left" id="dialog-form_1" >
												<input type="hidden" name="snomdPTVisitReason" />
										        <input type="hidden" name="snomdCIdVisitReason" />
												<input type="hidden" name="patVisitReason" />
																
												<div id="snomed-ct-search">
													<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
													<input placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input tooltipClass" name="txt-snomed-ct-search_1"  style="width:65%;color:#000000;" type="text" onchange="load1('1','')"/>
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
							
							<div class="div-table-col label <s:property value="#newDeptVisitWidth"/>"><s:text name="amountCollected"/></div>
							<div class="div-table-col control <s:property value="#newDeptVisitWidth"/>" >
								<s:if test="%{#renewalTypeForJsp== 1 || #renewalTypeForJsp==2}">
									<s:textfield name="patAmountCollected" value="%{patAmountHospitalWiseForJsp}" size="10" readonly="true"></s:textfield>
								</s:if>
								<s:elseif test="%{#renewalTypeForJsp== 3 || #renewalTypeForJsp==4}">
									<s:textfield name="patAmountCollected" value="%{patAmountDeptWiseForJsp}" size="10" readonly="true"></s:textfield>
								</s:elseif>
								<s:else>
									<s:textfield name="patAmountCollected" value="0.00" size="10" readonly="true"></s:textfield>
								</s:else>
							</div>
							
							<!-- 	Added by warish: cash combo -->
								<div class="div-table-col control" id="divFeeVal2" size="20" style="width:10%;">
								<s:set name="paymentMode" value="#session[@registration.config.RegistrationConfig@PAYMENT_MODE_OPTION_LIST_PROCESSED]"></s:set>
													<s:if test="%{#paymentMode!= null}">
														<s:select name="paymentModeCode" id="divFeeVal2" cssClass="select90prcnt" list="paymentMode" 
																listKey="value" listValue="label" 
																headerKey="1" onchange=' return paymentModeCodeOnChangeValidate(this)'/>
													</s:if>
				                        </div>
				                     <div class="div-table-col control" id="divFeeVal3"  size="10" style="width:15%;display:none;">
					                <input tabindex="1" name="paymentModeRefId" maxlength="20" type="text" />
				                 </div>
						</div>
						
					</div>
				</s:if>
				
				<s:set name="reducedWidth" value="0"></s:set>
				<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY
								&& (#renewalTypeForJsp== 3 || #renewalTypeForJsp==4)}">
					<s:set name="reducedWidth" value="4"></s:set>
				</s:if>
					
				<s:if test="%{oldDepartmentVisit=='On' || oldDepartmentVisit=='on' || #goWithoutDeptVisitOnForJsp==1}">
					<div class="div-table" id="divOldDeptVisitId1">
						<div class="div-table-row">
							<div class="div-table-col title width100 "><s:text name="emergency"/>&nbsp;<s:text name="old"/>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.visit"/>&nbsp;<s:text name="global.details"/></div>
						</div>
					</div>
						<s:set name="voArrEpisode" value="#session[@registration.config.RegistrationConfig@REGISTRATIONDESK_EPISODEVO_ARR]"></s:set>
						<s:if test="%{#voArrEpisode!= null && #voArrEpisode.length != 0}">
							<s:set name="varStatus" value="InProcess"></s:set>
							<div class="div-table-listing rounded" id="divOldDeptVisitId2">
								<div class="div-table-row listHeader">
									<div class='div-table-col alignCenter width5' ><s:text name="status"/></div>
									<div class='div-table-col alignCenter width<s:property value="%{22- #reducedWidth}"/>'><s:text name="global.department"/></div>
									<div class='div-table-col alignCenter width<s:property value="%{25- #reducedWidth}"/>' ><s:text name="global.unit"/>&nbsp;<s:text name="slash"/>&nbsp;<s:text name="global.room"/></div>
									<div class='div-table-col alignCenter width<s:property value="%{13- #reducedWidth}"/>' ><s:text name="lastVisitdate"/></div>
									<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY
													&& (#renewalTypeForJsp== 3 || #renewalTypeForJsp==4) }">
													<div class='div-table-col alignCenter width20'><font color="red">*</font><s:text name="reference"/>&nbsp;<s:text name="letter"/>&nbsp;<s:text name="number"/>/<s:text name="date"/></div>
										<%-- <div class='div-table-col alignCenter width10'><font color="red">*</font><s:text name="reference"/>&nbsp;<s:text name="letter"/>&nbsp;<s:text name="number"/></div>
										<div class='div-table-col alignCenter width10'><font color="red">*</font><s:text name="letter"/>&nbsp;<s:text name="date"/></div> --%>
									</s:if>
									<div class="div-table-col alignCenter width6"><s:text name="global.is"/>&nbsp;<s:text name="mlc"/></div>
									<div class='div-table-col alignCenter width<s:property value="%{15- #reducedWidth}"/>' ><s:text name="visitreason"/></div>
									<div class='div-table-col alignCenter width<s:property value="%{14- #reducedWidth}"/>' ><s:text name="renewalfee"/></div>
								</div>
								<s:iterator id="voPatReferId"  value="voArrEpisode" status="statusArrEpisodeVO">
									<s:if test="%{isGeneral== @registration.config.RegistrationConfig@UNIT_TYPE_SPECIALITY}">
										<s:set name="backgroundColor" value="#FFC0CB"></s:set>
									</s:if>
									<s:else>
										<s:set name="backgroundColor" value=""></s:set>
									</s:else>	
									
									<div class='div-table-row listData <s:property value="backgroundColor"/>'>
										<div class="div-table-col alignCenter" style="width: 5%;">
											
											<s:set name="fontColor" value=""></s:set>
											
											<s:if test="%{deptUnitIsOnDuty == @registration.config.RegistrationConfig@DEPT_UNIT_IS_ON_DUTY_TRUE}">
												<s:if test="%{#renewalTypeForJsp== 1 || #renewalTypeForJsp==2}">
													<s:if test="%{#emgRenewalRequiredForJsp == @registration.config.RegistrationConfig@RENEWAL_REQUIRED_TRUE}">
														<s:set name="fontColor" value="'colorRed'"></s:set>
														<s:hidden name="renewalRequired" value="%{emgRenewalRequiredForJsp}"/>
														<input type="checkbox" name="departmentsToVisitStamp" onclick='emgPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);' value="<s:property value="episodeCode"/>"/>
													</s:if>
													<s:else>
														<s:hidden name="renewalRequired" value="%{emgRenewalRequiredForJsp}"/> 
														<s:hidden name="opdRenewalRequired" value="%{emgRenewalRequiredForJsp}"/>
														<input type="checkbox" name="departmentsToVisitStamp" value="<s:property value="episodeCode"/>" onclick="emgPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);" />
													</s:else>
												</s:if>
												<s:elseif test="%{#renewalTypeForJsp== 3 || #renewalTypeForJsp==4}">
													<s:if test="%{renewalRequired == @registration.config.RegistrationConfig@RENEWAL_REQUIRED_TRUE}">
														<s:set name="fontColor" value="'colorRed'"></s:set>
														<%-- <s:hidden name="renewalRequired" value="%{renewalRequired}"/> --%>
														<input type="checkbox" name="departmentsToVisitStamp" onclick='emgPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);' value="<s:property value="episodeCode"/>"/>
													</s:if>
													<s:else>
														<s:hidden name="renewalRequired" value="%{emgRenewalRequiredForJsp}"/>
														<input type="checkbox" name="departmentsToVisitStamp" value="<s:property value="episodeCode"/>" onclick="emgPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);" />
													</s:else>
													<input type="hidden" name="renewalRequired"  value="<s:property value="renewalRequired"/>" />
												</s:elseif>
												<s:else>
													<input type="checkbox" name="departmentsToVisitStamp" value="<s:property value="episodeCode"/>" onclick="emgPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);" />
													<%-- <s:hidden name="renewalRequired" value="%{renewalRequired}"/> --%>
													<input type="hidden" name="renewalRequired"  value="<s:property value="renewalRequired"/>" />
												</s:else>
												
											</s:if>
											<!-- end unit not visited today and unit is on duty  -->
											<s:else>
												<s:if test="%{isGeneral== @registration.config.RegistrationConfig@UNIT_TYPE_SPECIALITY}">
													<img src="../../hisglobal/images/stop.png" title="Unit not on duty">
												</s:if>
												<!-- end Unit type special -->
												
												<!-- start unit type general -->
												<s:if test="%{isGeneral== @registration.config.RegistrationConfig@UNIT_TYPE_GENERAL}">
													
													<s:hidden name="hiddenEpisode" value="%{hiddenEpisode}" />
													<!-- start  other general unit working -->
													<s:if test="%{isOtherUnitWorking != @registration.config.RegistrationConfig@NO_DEPT_UNIT_WORKING_TRUE}">
														<input type="checkbox" name="departmentsToVisitStamp" value="<s:property value="episodeCode"/>" onclick="emgPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);" />
														<s:hidden name="renewalRequired" value="%{renewalRequired}"/>
													</s:if>
													<!-- end other general unit working -->
													
													<!-- start other general unit not working -->
													<s:else>
														<img src="../../hisglobal/images/stop.png" title="No Unit Working In The Department" >
													</s:else>
													<!-- end other general Unit Working -->
												</s:if>
												<!-- end unit type general -->
												
											</s:else>
											<!-- End unit not on duty -->
												
											
											
										</div>
										<div class='div-table-col alignCenter width<s:property value="%{22- #reducedWidth}"/> <s:property value="fontColor"/>'>
											<s:property value="department"/>
										</div>
										<div class='div-table-col alignCenter width<s:property value="%{25- #reducedWidth}"/> <s:property value="fontColor"/>'>
											<s:property value="departmentUnit"/>/<s:property value="room"/>
										</div>
										<div class='div-table-col alignCenter width<s:property value="%{13- #reducedWidth}"/> <s:property value="fontColor"/>'>
											<s:property value="episodeDate"/>
										</div>
										<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY
															&& (#renewalTypeForJsp== 3 || #renewalTypeForJsp==4)}">
											<s:if test="%{#fontColor=='colorRed'}">
												<input type="hidden" name="arrCreditBillFlag" value="1" id='arrCreditBillFlagId<s:property value="%{#statusArrEpisodeVO.index}"/>' >
												<!-- <div class='div-table-col alignCenter width10'>
													<input type="text" name="arrCreditLetterRefNo" id="arrCreditLetterRefNoId" class="input90prcnt letterNo" maxlength="50">
												</div>
												<div class='div-table-col alignCenter width10'>
													<input type="date" name="arrCreditLetterDate" id="arrCreditLetterDate" value="Today" class="input90prcnt letterDate" onblur="dateNotInRange(this)" style="text-align: left;">
												</div> -->
												<div class='div-table-col alignCenter width20 <s:property value="fontColor"/>'>
													<s:property value="creditLetterRefNo"/>/<s:property value="creditLetterDate"/>&nbsp;
														<input type="hidden" name="arrCreditLetterRefNo" id="arrCreditLetterRefNoId" value='<s:property value="creditLetterRefNo"/>' class="input90prcnt">
													    <input type="hidden" name="arrCreditLetterDate" id="arrCreditLetterDate" value='<s:property value="creditLetterDate"/>' class="input90prcnt" onblur="dateNotInRange(this)" style="text-align: left;">
												   </div>
											</s:if>
											<s:else>
												<input type="hidden" name="arrCreditBillFlag" value="0" id='arrCreditBillFlagId<s:property value="%{#statusArrEpisodeVO.index}"/>' >
												<%-- <div class='div-table-col alignCenter width10'>
													<s:property value="creditLetterRefNo"/>&nbsp;
													<input type="hidden" name="arrCreditLetterRefNo" id="arrCreditLetterRefNoId" value='<s:property value="creditLetterRefNo"/>' class="input90prcnt">
												</div>
												<div class='div-table-col alignCenter width10'>
													<s:property value="creditLetterDate"/>&nbsp;
													<input type="hidden" name="arrCreditLetterDate" id="arrCreditLetterDate" value='<s:property value="creditLetterDate"/>' class="input90prcnt" onblur="dateNotInRange(this)" style="text-align: left;">
												</div> --%>
												<div class='div-table-col alignCenter width20'>
													<s:property value="creditLetterRefNo"/>/<s:property value="creditLetterDate"/>&nbsp;
														<input type="hidden" name="arrCreditLetterRefNo" id="arrCreditLetterRefNoId" value='<s:property value="creditLetterRefNo"/>' class="input90prcnt">
													    <input type="hidden" name="arrCreditLetterDate" id="arrCreditLetterDate" value='<s:property value="creditLetterDate"/>' class="input90prcnt" onblur="dateNotInRange(this)" style="text-align: left;">
												   </div>
											</s:else>
											
										</s:if>
										<s:else>
											<input type="hidden" name="arrCreditBillFlag" value="0" id='arrCreditBillFlagId<s:property value="%{#statusArrEpisodeVO.index}"/>' >
										</s:else>
										<div class="div-table-col alignCenter width6 <s:property value="fontColor"/>">
											<s:if test="%{mlcFlag==null || mlcFlag == '' ||  mlcFlag == 0}">
												<input type="checkbox" name="arrMlcFlag" value="0" onclick="emgPatientVisitJsObj.checkUncheck(this);">
											</s:if>
											<s:elseif test="%{mlcFlag==null || mlcFlag == '' ||  mlcFlag == 2}">
												<input type="checkbox" name="arrMlcFlag" value="1" checked="checked">
											</s:elseif>
											<s:else>
												<input type="checkbox" name="arrMlcFlag" class="readOnlyClass" value="1" checked="checked" disabled="disabled">
											</s:else>
										</div>
										<div class="div-table-col alignCenter width<s:property value="%{15- #reducedWidth}"/>" >
											<!--By Surabhi on 07.11.2016-->
												<%-- <input type="text" name="arrPatVisitReason" class="tooltipClass" id='arrPatVisitReasonId<s:property value="%{#statusArrEpisodeVO.index}"/>' 
														maxlength="99" size="10" title="Visit Reason" onkeypress="checkToolTip(this)"> --%>
												<div align="left" id="dialog-form_2" >
													<input type="hidden" name="arrsnomdPTVisitReason" value='<s:property value="arrsnomdPTVisitReason" />' id='arrsnomdPTVisitReasonId<s:property value="%{#statusArrEpisodeVO.index}"/>' />
											        <input type="hidden" name="arrsnomdCIdVisitReason" value='<s:property value="arrsnomdCIdVisitReason" />' id='arrsnomdCIdVisitReasonId<s:property value="%{#statusArrEpisodeVO.index}"/>' />
													<input type="hidden" name="arrPatVisitReason" value='<s:property value="arrPatVisitReason" />' id='arrsnomdPTVisitReasonId<s:property value="%{#statusArrEpisodeVO.index}"/>' />
																	
													<div id="snomed-ct-search">
														<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
														<%-- <s:if test="%{visitedToday == @registration.config.RegistrationConfig@DEPT_UNIT_VISITED_TODAY_TRUE}">
																				<div style="width:100%" align="center"><input type="text" readonly value='<s:property value="arrPatVisitReason" />' /></div></s:if>
														<s:else> --%><input placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_2" value='<s:property value="arrPatVisitReason" />' class="clearable ui-autocomplete-input tooltipClass" name="txt-snomed-ct-search_2"  style="width:65%;color:#000000;" type="text" onchange="load1('2','')"/>
													<%-- </s:else> --%></div>
													 <div id="norecorddiv_2">
														<label style="display: none;" id="reccnt1">No. of records : </label>
														<span style="display: inline;" id="reccount1" ></span>
														<label style="display: none;" id="nosuggestion1">No suggestions found</label>
														<label style="display: none;" id="norec1">No results found</label>
									                    <label style="display: none;" id="msg3chars1">Please enter atleast 3 characters</label>
								                 	</div>         
							                   		<div class="concept" id="conceptdiv_2"></div>
							                    </div>
												<!--End Surabhi  -->
										</div>
										<div class="div-table-col alignRight width<s:property value="%{14- #reducedWidth}"/> <s:property value="fontColor"/>">
											<s:set name="epAmount" value="0.00"></s:set>
											<s:if test="%{#fontColor=='colorRed'}">
												<s:if test="%{#renewalTypeForJsp== 1 || #renewalTypeForJsp==2}">
													<s:property value="patAmountHospitalWiseForJsp"/>
													<s:set name="epAmount" value="patAmountHospitalWiseForJsp"></s:set>
												</s:if>
												<s:elseif test="%{#renewalTypeForJsp== 3 || #renewalTypeForJsp==4}">
													<s:property value="patRenewalAmountDeptWiseForJsp"/>
													<s:set name="epAmount" value="patRenewalAmountDeptWiseForJsp"></s:set>
												</s:elseif>
												<s:else>
													0.00
													<s:set name="epAmount" value="'0.00'"></s:set>
												</s:else>
											</s:if>
											<s:else>
												0.00
											</s:else>
											<input type="hidden" name="hiddenEpisodePatAmount" value="<s:property value="%{#epAmount}"/>">
										</div>
									</div>
									
								</s:iterator>
								
								<div class="div-table-row listdata">
									<div class="div-table-col width100 alignRight">
										<s:text name="renewalfee"/>:<input type="text" name="grandTotal" value="0.00" disabled="disabled">
										<s:hidden name="hcode" value="%{hcode}" />	
									</div>
								</div>
							</div>
							
						
							
						</s:if>
						<s:else>
							<div class="div-table" id="divOldDeptVisitId3">
								<div class="div-table-row">
									<div class="div-table-col width100 alignCenter colorRed">No Record Found</div>
								</div>
							</div>
						</s:else>
					
				</s:if>
						
			</s:if>
			
			<s:if test="%{#afetrGoForJsp!=0 && #isPatReferByListForJsp != @registration.config.RegistrationConfig@IS_PAT_REFER_BY_LIST_TRUE}">
				<div class="div-table" >
					<div class="div-table-row title">
						<div class="div-table-col" style="width: 80%">
								<s:text name="refer"/>&nbsp;<s:text name="detail"/>
						</div>
						<div class="div-table-col label" style="width: 20%">
								<s:text name="isreferred"/><input type="checkbox" name="isReferred" value="0" onclick="emgPatientVisitJsObj.checkIsReferred(this)">
						</div>
					</div>
				</div>
				<div id="divRefDtlChkboxId" class="div-table" style="display: none;">
					<div id="divCheckReferralId" class="div-table-row" >
						<div class="div-table-col width100">&nbsp;&nbsp;&nbsp;
							<s:text name="referInternal"/><input type="radio" name="isRefferInOut" value="I" onclick="emgPatientVisitJsObj.showInternalExternal(this)" />&nbsp;&nbsp;
					    	<s:text name="referExternal"/> <input type="radio" name="isRefferInOut" value="O" onclick="emgPatientVisitJsObj.showInternalExternal(this)" />
						</div>
					</div>
					
					<div id="divInternalReferId" style="display:none">
						<s:set name="voArrOpenEpisode" value="#session[@registration.config.RegistrationConfig@ARR_OPD_OPEN_EPISODE_VO]"></s:set>
						<s:if test="%{#afetrGoForJsp!=0 && #voArrOpenEpisode!= null}">
							<div class="div-table-listing rounded" >
								<div class="div-table-row listHeader" >
									<div class="div-table-col alignCenter" style="width: 5%"><s:text name="select"/></div>
									<div class="div-table-col alignCenter" style="width: 45%"><s:text name="referFromDept"/> </div>
									<div class="div-table-col alignCenter" style="width: 50%"><s:text name="referFromDeptUnit"/> </div>
								</div>
								<s:iterator id="voArrOpenEpisodeId"  value="voArrOpenEpisode" status="statusArrOpenEpisodeRefr">
									<s:set name="epCode" value="episodeCode"></s:set>
									<s:set name="frmDep" value="fromDepartment"></s:set>
									<s:set name="frmDepUnit" value="fromDepartmentUnit"></s:set>
									<s:set name="frmDepCode" value="fromDepartmentCode"></s:set>
									<s:set name="frmDepUnitCode" value="fromDepartmentUnitCode"></s:set>
									
									<div class="div-table-row listData">
										<div class="div-table-col alignCenter" style="width: 4%;">
											<input type="radio" name="refferringOPDEpisode" value='<s:property value="epCode"/>'>
											<s:hidden name="episodeCode" value="%{epCode}"></s:hidden>
										</div>
										<div class="div-table-col alignCenter" style="width: 45%">
											<s:property value="frmDep"/>
											<s:hidden name="frmDepCode" value="%{frmDepCode}"></s:hidden>
										</div>
										
										<div class="div-table-col alignCenter" style="width: 50%">
											<s:property value="frmDepUnit"/>
											<s:hidden name="frmDepUnitCode" value="%{frmDepUnitCode}"></s:hidden>
										</div>
									</div>
								
								</s:iterator>
							</div>
						</s:if>
					</div>
					
					
					<div id="divExternalReferalId" class="div-table-row" style="display:none">
						<div class="div-table-col width100">
							<div id="divRefDtlId" class="div-table" >
								<div class="div-table-row separatorBar ">
									<div class="div-table-col"> </div>
								</div>
								<div class="div-table-row" >
									<div id="divReferredInstitute" class="div-table-col label" style="width: 50%;">
										<s:text name="associatedInst"/> <input type="radio" name="referringInstType" value="G" onclick="emgPatientVisitJsObj.showdivhoscode(this)" checked="checked" /> &nbsp;
								    	<s:text name="other"/> <input type="radio" name="referringInstType" value="O" onclick="emgPatientVisitJsObj.showdivhoscode(this)" /> &nbsp;
									</div>
									<div class="div-table-col" style="width: 50%;">
										<div id="divRefHosCode" class="div-table" style="width: 100%;">
											<div class="div-table-row" >
												<div class="div-table-col label" style="width: 50%;"> <font color="red">*</font>
												<label for="Institute Name"><s:text name="institute"/>&nbsp;<s:text name="global.name"/></label>	
												</div>
												<div class="div-table-col control" style="width: 50%;">
													<s:set name="optionsRefHospital" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_REF_HOSPITAL]"></s:set>
													<s:if test="%{#optionsRefHospital!= null}">
														<s:select id="patRefGnctdHospitalCodeId" name="patRefGnctdHospitalCode" 
															cssStyle="width:145px" list="optionsRefHospital"  
															 listKey="value" listValue="label" 
														   headerKey="-1"  headerValue="Select Value" />
													</s:if>
													<s:else>
														<select id="patRefGnctdHospitalCodeId" name="patRefGnctdHospitalCode" >
															<option value="-1">Select Value</option>
														</select>
													</s:else>
												</div>
											</div>
										</div>
										<div id="divRefHosname" class="div-table" style="width: 100%;">
											<div class="div-table-row">
												<div class="div-table-col label" style="width: 50%;"> 
												<font color="red">*</font><label for="Institute Name"><s:text name="institute"/>&nbsp;<s:text name="global.name"/></label>	
												</div>
												<div class="div-table-col control" style="width: 50%;">
													<input name="patRefHospitalName" maxlength="100" type="text" size="20">
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="div-table" id="divReferred" style="display: none;">
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 25%;"><s:text name="referring"/>&nbsp;<s:text name="institute"/>&nbsp;<s:text name="crNo"/>&nbsp;</div>
									<div class="div-table-col control" style="width: 25%;">
										<input type	="text" name="patRefGnctdHospitalCrno" maxlength ="18"  >
									</div>
									<div class="div-table-col label" style="width: 25%;">
										<font color="red">*</font><s:text name="referredBy"/>
									</div>
									<div class="div-table-col control" style="width: 25%;">
										<input type="text" 	name="patRefDoctor" maxlength="60">
									</div>
								</div>
								
								<%-- <div class="div-table-row">
									<div class="div-table-col label" style="width: 25%;">
										<s:text name="referring"/>&nbsp;<s:text name="institute"/>&nbsp;<s:text name="global.department"/>
									</div>
									<div class="div-table-col control" style="width: 25%;">
										<s:set name="optionsRefDeptDtl" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL]"></s:set>
										<s:if test="%{#optionsRefDeptDtl!= null}">
											<s:select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDept" 
												cssStyle="width:145px" list="optionsRefDeptDtl"  
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
									
									<div id="divRefHospitalDeptOtherId" class="div-table-col" style="width: 50%; display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col label" style="width: 50%;"><font color="red">*</font><s:text name="other"/>&nbsp;<s:text name="referred"/>&nbsp;<s:text name="global.department"/></div>
												<div class="div-table-col control" style="width: 50%;">
													<input name="patRefHospitalDeptOther" maxlength="50" type="text" size="20">
												</div>
											</div>
											
										</div>
									</div>
									
								</div> --%>
								
								<div class="div-table-row">
			
				<div class="div-table-col label" style="width: 25%;">
					<s:text name="referring"/>&nbsp;<s:text name="institute"/>&nbsp;<s:text name="global.department"/>
				</div>
				<div class="div-table-col control" style="width: 25%;">
				 <div class="div-table-col control" style="width: 50%;">
					<s:set name="optionsRefDeptDtl" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL]"></s:set>
										<s:if test="%{#optionsRefDeptDtl!= null}">
											<s:select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDept" 
												cssStyle="width:145px" list="optionsRefDeptDtl"  
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
				
			</div>
								<%-- <div class="div-table-row">
									<div class="div-table-col label" style="width: 25%;">
										<s:text name="referring"/>&nbsp;<s:text name="institute"/>&nbsp;<s:text name="global.unit"/>
									</div>
									<div class="div-table-col control" style="width: 25%;">
										<input name="patRefGnctdHospitalDeptUnit" maxlength="15" type="text" size="20" >
									</div>
									<div class="div-table-col label" style="width: 25%;">&nbsp;</div>
									<div class="div-table-col control" style="width: 25%;">&nbsp;</div>
									
								</div> --%>
							</div>
						</div>
					</div>
				</div>
				</s:if>
				 <!-- Added by Vasu on 07.May.18 -->
				 <s:if test="%{#afetrGoForJsp!=0}">
				   <div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col" style="width: 80%"><s:text name="brought"/>&nbsp;<s:text name="by"/>&nbsp;<s:text name="global.details"/>
						</div>
						<div class="div-table-col label" style="width: 20%" align="right">
							<div style="display: none" id="isUnknownId" ><s:text name="brought"/>&nbsp;<s:text name="by"/>&nbsp;<s:text name="global.detail"/>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="compulsory"/></div>
						</div>

						<div class="div-table-col label" style="width: 20%">
							<s:text name="global.is"/>&nbsp;<s:text name="brought"/>&nbsp;<s:text name="by"/><input type="checkbox" name="isBroughtBy" tabindex="2" value="0" onclick='broughtBy(this);'>
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
									<select name="isRelative" id="isRelativeKnownId" onchange="enableRelation(this);" tabindex="1" class="select77prcnt">
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
								 <%-- <s:select cssClass="select85prcnt" list="#{'-1':'Select Value'}" name="broughtByRelationCode" id="broughtByRelationCodeId" tabindex="1"  value="broughtByRelationCode" onchange="enableRelation()" disabled="disabled" />  --%>
							    <s:select cssClass="select85prcnt" list="%{#session.optionRelationDetail}" name="broughtByRelationCode" id="broughtByRelationCodeId" tabindex="1"  value="" listKey="value" listValue="label" headerKey="-1" headerValue="Select Value" onchange="enableRelation()" disabled="disabled" /> 
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
				</div> 
				</s:if>
				 <!-- End Vasu -->
			
				<s:if test="%{#afetrGoForJsp!=0 && #voArrEpisode!= null && #voArrEpisode.length > 0}">
				<div class="div-table" id="divLegendsId" style="cursor: pointer;" title="Click To Show Legends">
					<div class="div-table-row ">
						<div class="div-table-col title width100"><s:text name="legends"/></div>
					</div>
				</div>
				</s:if>
				<div class="div-table-simple" id="divLegendsDtlId" style="display: none;" >
					<div class="div-table-row">
						<div class="div-table-col width10 alignCenter"><input type="checkbox"></div>
						<div class="div-table-col width90">Visit stamp allowed</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width10 alignCenter"><img src="../../hisglobal/images/stop.png"/></div>
						<div class="div-table-col width90">No unit working in the department</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width10 alignCenter colorRed">Red</div>
						<div class="div-table-col width90">Registration expired, renewal required for this department</div>
					</div>
				</div>
		</div>
		
	
		
		<div class="div-table-button" id="nonprintableDiv2">
			<div class="div-table-row footerBar">
					<div class="div-table-col" > </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<s:if test="%{#afetrGoForJsp!=0}">
					<%-- <s:if test="%{oldDepartmentVisit=='On' && newDepartmentVisit==''}">
						<a href="#" class="button" id="submitOldId"><span class="save">Save</span></a>
					</s:if>
					<s:elseif test="%{newDepartmentVisit=='On' && oldDepartmentVisit==''}">
						<a href="#" class="button" id="submitNewId"><span class="save">Save</span></a>
					</s:elseif > --%>
					<s:if test="%{(newDepartmentVisit=='On' || newDepartmentVisit=='on') && (oldDepartmentVisit=='On' || oldDepartmentVisit=='on')}">
						<a href="#" class="button" id="submitBothId" onclick="freeText();"><span class="save"><s:text name="save"/></span></a>
					</s:if>
				</s:if>
				<a href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
				<a href="#" class="button" id="cancelId" onclick="initPage();"><span class="cancel"><s:text name="cancel"/></span></a>
			</div>
		</div>
		<div class="div-table-simple" id="nonprintableDiv3">
			<div class="div-table-row">
				<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%">
					<s:property value="%{#errorMessageForJsp}"/>
				</div>
			</div>
		</div>
		
		<div class="div-table" id="nonprintableDiv4">
			<div class="div-table-row">
				<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%"></div>
			</div>
			
		</div>
		<div class="div-table" id="divPrintId" style="font-weight: normal;color: black;">
			<%-- <s:if test="%{#printForJsp==1}">
				<s:property value="printDivContent"/>
			</s:if> --%>
		</div>

		
		<s:hidden id="hiddenPrintId" name="printHtml" value="%{printDivContent}" />
		
		<s:hidden name="patFirstName" value="%{patFirstName}" />
		<s:hidden name="patMiddleName" value="%{patMiddleName}"/>
		<s:hidden name="patLastName" value="%{patLastName}"/>
		<s:hidden name="patGender" value="%{patGender}"/>           
		<s:hidden name="patAge" value="%{patAge}"/>
		<s:hidden name="patPrimaryCatCode" value="%{patPrimaryCatCode}"/>
		<s:hidden name="patPrimaryCat" value="%{patPrimaryCat}"/>
		<s:hidden name="patGuardianName" value="%{patGuardianName}" />
		<%-- <s:hidden name="patAmountCollected" value="%{patAmountCollected}" /> --%>
		
		<s:hidden name="selectedFromDept" value="%{selectedFromDept}" />
		<s:hidden name="patBillAmountWithoutGrouping" value="%{patBillAmountWithoutGrouping}" />
		<s:hidden name="referInternalExternal" value="%{referInternalExternal}" />
		<s:hidden name="referingRowIndex" value="%{referingRowIndex}" />
		<s:hidden name="onlineReferedIndex" value="%{onlineReferedIndex}" />
		<s:hidden name="entryDate" value="%{entryDate}" />
		<s:hidden name="episodeVisitNo" value="%{episodeVisitNo}" />
		<s:hidden name="onRequestVisit" value="%{onRequestVisit}" />
		<s:hidden name="captureMandatoryField" value="%{captureMandatoryField}" />
		<s:hidden name="selectedCheckBox" value="%{selectedCheckBox}" />
		
		<s:hidden name="saveSuccessful" value="%{saveSuccessful}" />
		<s:hidden name="isPrintCard" value="%{isPrintCard}" />
		<s:hidden name="indexID" value="%{indexID}" />
		<s:hidden name="selectedReferal" value="%{selectedReferal}" />
		
		<s:hidden name="clientName"/>
		<s:hidden name="isDuplicateRegistered" value="%{isDuplicateRegistered}"/>
		<s:hidden name="selectedDuplicatePatCrNo" value="%{selectedDuplicatePatCrNo}"/>
        <s:hidden name="isAmbulatoryCheckUncheck"/>
        <s:hidden name="isMLCFlag"/> 
        <input type="hidden" name="targetId" />
   <cmbPers:cmbPers></cmbPers:cmbPers>
   <s:token></s:token>
	</s:form>

	<script type="text/javascript"
		src="./../../registration/transactions/js/emgPatientVisit.js" /></script>
</body>
</html>