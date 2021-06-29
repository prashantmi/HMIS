<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--<%@ taglib uri="/WEB-INF/csrfguard.tld" prefix="csrf" %>--%> 
<%@page %>

<html>
<head>
<!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <meta name="referrer" content="no-referrer" /> -->

<link rel="stylesheet"  type="text/css" href="/HIS/hisglobal/css/buttons.css" >
<link rel="stylesheet"  type="text/css" href="/HIS/hisglobal/css/tab.css" >
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/layout.css" >
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/basic.css">

<link rel="stylesheet" href="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/plugins/ui.multiselect.css">
<link rel="stylesheet" type="text/css" href="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/jquery-ui-themes-1.8.23/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/css/ui.jqgrid.css" />

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/js/basic.js"></script>

<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/js/jquery-ui-custom.min.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/plugins/ui.multiselect.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/src/grid.jqueryui.js"></script>	
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/js/jquery.layout.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/src/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/src/jquery.jqGrid.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>


<script type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="/HIS/hisglobal/utility/generictemplate/js/date_validator.js"></script>


<script type='text/javascript' src='/HIS/hisglobal/multilingualSupport/js/pramukhime.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/multilingualSupport/js/pramukhindic.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/multilingualSupport/js/pramukhime-common.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/multilingualSupport/js/multilingualSupport.js'></script>




<script type="text/javascript" src="/HISRegistration/hr/common/js/pisGlobal.js"></script>
		
<!--  theme changer start-->
<link rel="stylesheet" type="text/css" title="blue" href="/HIS/hisglobal/css/buttons.css" />
<link rel="stylesheet" type="text/css" title="blue" href="/HIS/hisglobal/css/layout.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HIS/hisglobal/css/buttons-green.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HIS/hisglobal/css/layout-green.css" />
<link rel="stylesheet" type="text/css" title="normal" href="/HIS/hisglobal/css/normalfont.css" />
<link rel="alternate stylesheet" type="text/css" title="large" href="/HIS/hisglobal/css/largefont.css" />
<link rel="alternate stylesheet" type="text/css" title="small" href="/HIS/hisglobal/css/smallfont.css" />
<script type="text/javascript" src="/HIS/hisglobal/js/themechanger.js"></script>
<!--  theme changer end-->	


<link href="/HISRegistration/hisglobal/css/global.css" rel="stylesheet" type="text/css">

<%-- <jsp:include page="/hr/common/jsp/globalFields.jsp"/> --%>

<link rel="stylesheet" type="text/css" media="screen" href="/HIS/hisglobal/css/jquery.ui.autocomplete.css"/>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery.ui.autocomplete.js"></script>
<%-- <script language="Javascript" src="/HISPis/hisglobal/js/security.js"></script> --%>

<%@ page import="hr.pis.config.PisConfig"%>
 
<style type="text/css">

 #divPrintId2{display:none ;}		
 @media print { 
		#nonprintableDiv1 {display: none !important;}
		#nonprintableDiv2 {display: none !important;}
		#nonprintableDiv3 {display: none !important;}
		#nonprintableDiv4 {display: none !important;}
		#nonprintableDiv5 {display: none !important;}
		#nonprintableDiv6 {display: none !important;}
		#nonprintableDiv7 {display: none !important;}
		#loading-image {display: none;}
		#rptPrintImageDivId {display: none;}
		#simplemodal-container{display: none;}
		#simplemodal-overlay{display: none;}
		#divPrintId2{display: block;}
		.ui-dialog{display: none !important;}
		.ui-widget-overlay{display: none !important;}
	}

</style>

<!-- <title>Employee Registration</title> -->
<title>Employee Registration</title>
<script>
	
$(window).on("load.loading1", function(){
	//alert("onload");
	empregistration.fetchDefaultValues();
	
}); 
	
$(document).ready(function() {
	settingForGuestOrUser();	
	funcTab1("#tab1");
	
})();


function settingForGuestOrUser()
{
	var local_flag_Guest_Or_User=$('[name="flagGuestOrUser"]')[0].value;
	if(local_flag_Guest_Or_User=='user')
	{
		$("#nonprintableDiv6").show();  // checked
		$("#nonprintableDiv5").hide();
		$("#nonprintableDiv7").hide();
	}
	else if(local_flag_Guest_Or_User=='guest')
	{
		$("#nonprintableDiv5").show();  // checked
		$("#nonprintableDiv6").hide();
		$("#nonprintableDiv7").hide();
	}
}


var localLanguage = <%=PisConfig.LOCAL_LANGUAGE%>; 

</script>
</head>

<body>
<center>
<s:actionerror/>

<s:form action="EmployeeRegistration">

<div id="nonprintableDiv6" style="display: none;">

<ul id="tabs1">
    <li><a href="#" name="tab1" title='Employee Registration' ><img src='/HIS/hisglobal/images/nonclinical/emp_add.png'  width="30" height="30" style='vertical-align: middle;cursor: pointer' title='Employee Registration' />&nbsp;&nbsp;Employee Registration</a></li>
</ul>

<div id="nonprintableDiv1" style="position:absolute; right:10px; top:4px; color: #025B85; font: bold 13px 'Lucida sans', Arial, Helvetica;" > 
	
	<div class="div-table">
		<div class="div-table-row">
				<div class="div-table-col" style="width: 100%">
					<s:if test="flagGuestOrUser=='user'">
						<input type="checkbox" name="strEmployeeUpdateFlag" tabindex="1" id="employeeUpdateFlagId">
						<s:text name="emp.reg.update.option"></s:text>&nbsp;&nbsp;
					</s:if>
					<s:if test="flagGuestOrUser=='guest'">
						<span id="regSlipGuestId"><img src='/HIS/hisglobal/images/nonclinical/nc_view.png' width='25' height='25' style='vertical-align:sub;cursor: pointer;' title='Registration Slip' /> 
						<s:text name="emp.reg.option.registrationslip"></s:text>
						</span>
					</s:if>
				</div>
				
			</div>
		<!--
		<div class="div-table-row" align="center" id="saveFooterId" style="display:none">
			<span id="submitId"><img src='/HIS/hisglobal/images/nonclinical/nc_save.png' width='18' height='18' style='vertical-align: middle;' title='Save' />&nbsp;<s:text name="emp.reg.footer.save"></s:text></span>
			&nbsp;&nbsp;
			<span id="clearId"><img src='/HIS/hisglobal/images/nonclinical/nc_clear.png' width='18' height='18' style='vertical-align: middle;' title='Clear' />&nbsp;<s:text name="emp.reg.footer.clear"></s:text></span>
			&nbsp;&nbsp;
			<span id="cancelId"><img src='/HIS/hisglobal/images/nonclinical/nc_cancel.png' width='18' height='18' style='vertical-align: middle;' title='Cancel' />&nbsp;<s:text name="emp.reg.footer.cancel"></s:text></span>
		</div>
		
		<div class="div-table-row" align="center" id="updateFooterId" style="display:none">
			<span id="updateId"><img src='/HIS/hisglobal/images/nonclinical/nc_edit.png' width='18' height='18' style='vertical-align: middle;' title='Update' />&nbsp;<s:text name="emp.reg.footer.update"></s:text></span>
			&nbsp;&nbsp;
			<span id="deleteId"><img src='/HIS/hisglobal/images/nonclinical/nc_trash.gif' width='18' height='18' style='vertical-align: middle;' title='Delete' />&nbsp;<s:text name="emp.reg.footer.delete"></s:text></span>
			&nbsp;&nbsp;
			<span id="clearUpdateId"><img src='/HIS/hisglobal/images/nonclinical/nc_clear.png' width='18' height='18' style='vertical-align: middle;' title='Clear' />&nbsp;<s:text name="emp.reg.footer.clear"></s:text></span>
			&nbsp;&nbsp;
			<span id="cancelUpdateId"><img src='/HIS/hisglobal/images/nonclinical/nc_cancel.png' width='18' height='18' style='vertical-align: middle;' title='Cancel' />&nbsp;<s:text name="emp.reg.footer.cancel"></s:text></span>
		</div>-->	
	</div>		
</div>

<div id="content"> 
    <div id="tab1" style="display: none;">
        
    <div class="wrapper rounded" id="nonprintableDiv4">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
		
			
			<div class="div-table-row emptyBar ">
				<div class="div-table-col"></div>
			</div>
			
			
							
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%">
					<s:text name="emp.reg.isexistingemployee"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<input type = "CHECKBOX" name="strIsExistingEmployee" onchange="isExistingEmployeeCheck('inputPage');" id="isExistingEmployee" />
				</div>
				<div class="div-table-col label" style="width: 25%">
					
				</div>
				<div class="div-table-col width control" style="width: 25%">
					
				</div>
				
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%" id="divLastEmpTypeMandatory" style="display:none">
					<font color="red">*</font><s:text name="emp.reg.lastemploymenttype"></s:text>
				</div>
				<div class="div-table-col label" style="width: 25%" id="divLastEmpTypeNoMandatory">
					<s:text name="emp.reg.lastemploymenttype"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="strLastEmploymentType" style="width :145px" onchange="isExistingEmployeeCheck('inputPage');">
						<option value="-1">Select Value</option>
					</select>
					<input name="updateCheckstrLastEmploymentType" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					<font color="red">*</font><s:text name="emp.reg.natureofjob"></s:text>
				</div>
				<div class="div-table-col width control " style="width: 35%">
				<%-- 	<select name="intNatureOfJobId" id="intNatureOfJobId" style="width :145px" onchange="natureOfJobIdChanged();"> --%>
					<select name="intNatureOfJobId" id="intNatureOfJobId" style="width :145px">
						<option value="-1">Select Value</option>
					</select>
					<input name="updateCheckintNatureOfJobId" type="hidden">	
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%">
					<font color="red">*</font><s:text name="emp.reg.appellation1"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="intAppellationCode1" style="width :145px" >
						<option value="-1">Select Value</option>
					</select>
					<input name="updateCheckintAppellationCode1" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					<s:text name="emp.reg.appellation2"></s:text> 
				</div>
				<div class="div-table-col width control " style="width: 35%">
					<select name="intAppellationCode2" style="width :145px" >
						<option value="-1">Select Value</option>
					</select>
					<input name="updateCheckintAppellationCode2" type="hidden">
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%">
					<font color="red">*</font><s:text name="emp.reg.employeefullname"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<!-- <input name=strEmployeeFullName id=strEmployeeFullName maxlength="99" size="42" type="text" onkeyup="multilingualConversion(this,document.getElementById('strEmployeeFullRegionalLangName')),prepareShortName();"> -->
					<input name=strEmployeeFullName id=strEmployeeFullName maxlength="99" size="42" type="text" onkeyup="prepareShortName();">
					<input name="updateCheckstrEmployeeFullName" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					<font color="red">*</font><s:text name="emp.reg.employeeshortname"></s:text>
				</div>
				<div class="div-table-col width control " style="width: 35%">
<!-- 					<input name=strEmployeeShortName name=strEmployeeShortName id=strEmployeeShortName maxlength="30" size="35" type="text" onkeyup="multilingualConversion(this,document.getElementById('strEmployeeShortRegionalLangName'));"> -->
					<input name=strEmployeeShortName name=strEmployeeShortName id=strEmployeeShortName maxlength="30" size="35" type="text">
					<input name="updateCheckstrEmployeeShortName" type="hidden">
					
					<input type="checkbox" name="copyFullNameInShortName" tabindex="1" id="copyFullNameInShortNameFlagId">
					<font size=-3>
					<s:text name="emp.reg.emp.short.full.name.same"></s:text>
					</font>
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%">
					
				</div>
				<div class="div-table-col width control" style="width: 25%">
<!-- 					<input name=strEmployeeFullRegionalLangName id=strEmployeeFullRegionalLangName maxlength="99" size="42" type="text" onblur="callOnBlur();" onfocus="callOnClick();"> -->
					<input name="updateCheckstrEmployeeFullRegionalLangName" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					
				</div>
				<div class="div-table-col width control " style="width: 35%">
<!-- 					<input name=strEmployeeShortRegionalLangName id=strEmployeeShortRegionalLangName maxlength="30" size="35" type="text" onblur="callOnBlur();" onfocus="callOnClick();"> -->
					<input name="updateCheckstrEmployeeShortRegionalLangName" type="hidden">
				</div>
			</div>
			
			
			
			<div class="div-table-row">
				 <div class="div-table-col label" style="width: 25%">
					<s:text name="emp.reg.suffix"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="intSuffixCode" style="width :145px" >
						<option value="-1">Select Value</option>
					</select>
					<input name="updateCheckintSuffixCode" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					<font color="red">*</font><s:text name="emp.reg.dob"></s:text>
				</div>
				<div class="div-table-col width control " style="width: 35%">
					<input id="empDOBId" type="text" name="dtEmployeeDOB" size="12">&nbsp;
				</div>
			</div>
		
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%">
					<font color="red">*</font><s:text name="emp.reg.gender"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="strGenderCode" style="width :145px" >
						<option value="-1">Select Value</option>
					</select>
					<input name="updateCheckstrGenderCode" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					<font color="red">*</font><s:text name="emp.reg.nationality"></s:text>
				</div>
				<div class="div-table-col width control " style="width: 35%">
					<select name="intNationalityId" style="width :145px" >
						<option value="-1">Select Value</option>
					</select>
					<input name="defaultintNationalityId" type="hidden">
					<input name="updateCheckintNationalityId" type="hidden">
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%">
					<font color="red">*</font><s:text name="emp.reg.department"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="intDepartmentCode" style="width :180px" >
						<option value="-1">Select Value</option>
					</select>
					<input name="updateCheckintDepartmentCode" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					<font color="red">*</font><s:text name="emp.reg.designation"></s:text>
				</div>
				<div class="div-table-col width control " style="width: 35%">
					<select name="intDesignationCode" style="width :180px" >
						<option value="-1">Select Value</option>
					</select>
					<input name="updateCheckintDesignationCode" type="hidden">
				</div>
			</div>
		
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%" id="divOldEmpMandatory" style="display:none">
					<font color="red">*</font><s:text name="emp.reg.oldemployeenumber"></s:text>
				</div>
				<div class="div-table-col label" style="width: 25%" id="divOldEmpNoMandatory">
					<s:text name="emp.reg.oldemployeenumber"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<input name=strOldEmployeeNumber maxlength="12" size="15" type="text" onkeyup="validateOldEmployeeNumber();">
					<input name="updateCheckstrOldEmployeeNumber" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					<font color="red">*</font><s:text name="emp.reg.empfinalstatus"></s:text>
				</div>
				<div class="div-table-col width control " style="width: 35%">
					<select name="strEmployeeFinalStatus" style="width :220px" >
						<option value="-1">Select Value</option>
					</select>
					<input name="defaultstrEmployeeFinalStatus" type="hidden">
					<input name="updateCheckstrEmployeeFinalStatus" type="hidden">
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%">
					<s:text name="emp.reg.mobilenumber"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<input name=intMobileNumber id=intMobileNumber maxlength="10" size="15" type="text">
					<input name="updateCheckintMobileNumber" type="hidden">
				</div>
				<div class="div-table-col label" style="width: 15%">
					<s:text name="emp.reg.emailid"></s:text>
				</div>
				<div class="div-table-col width control " style="width: 35%">
					<input name=strPersonalEmailId id=strPersonalEmailId maxlength="35" size="40" type="text">
					<input name="updateCheckstrPersonalEmailId" type="hidden">
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%">
					<s:text name="emp.reg.ispancardavailable"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 25%">
					<input type="checkbox" name="strPANNumberAvailableFlag" tabindex="2" id="strPANNumberAvailableFlagId">
				</div>
				<div class="div-table-col label" style="width: 15%" id="divPANNumberMandatory" style="display:none">
					<font color="red">*</font><s:text name="emp.reg.panno"></s:text>
				</div>
				<div class="div-table-col label" style="width: 15%" id="divPANNumberNoMandatory">
					<s:text name="emp.reg.panno"></s:text>
				</div>
				<div class="div-table-col width control " style="width: 35%">
					<input name=strPANNumber id=strPANNumber maxlength="10" size="15" type="text">
					<input name="updateCheckstrPANNumber" type="hidden">
				</div>
			</div>
			
			<div class="div-table-row emptyBar ">
				<div class="div-table-col"></div>
			</div>
			
	</div>
		  
		<div class="div-table-button">
				<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center" id="saveFooterId" style="display:none">
					<a href="#" class="button" id="submitId"><span class="save" style="cursor: pointer" title='Save'><s:text name="emp.reg.footer.save"></s:text></span></a>
					<a href="#" class="button" id="clearId"><span class="clear" style="cursor: pointer" title='Clear'><s:text name="emp.reg.footer.clear"></s:text></span></a>
					<a href="#" class="button" id="cancelId"><span class="cancel" style="cursor: pointer" title='Cancel'><s:text name="emp.reg.footer.cancel"></s:text></span></a>
					<!--  
					<a href="#" class="button" id="cancel55Id"><span class="cancel12" style="cursor: pointer" title='Cancel12'>ABCD</span></a>
					-->
				</div>
				
				<div class="div-table-row" align="center" id="updateFooterId" style="display:none">
					<a href="#" class="button" id="updateId"><span class="modify" style="cursor: pointer" title='Update'><s:text name="emp.reg.footer.update"></s:text></span></a>
					<a href="#" class="button" id="deleteId"><span class="delete" style="cursor: pointer" title='Delete'><s:text name="emp.reg.footer.delete"></s:text></span></a>
					<a href="#" class="button" id="clearUpdateId"><span class="clear" style="cursor: pointer" title='Clear'><s:text name="emp.reg.footer.clear"></s:text></span></a>
					<a href="#" class="button" id="cancelUpdateId"><span class="cancel" style="cursor: pointer" title='Cancel'><s:text name="emp.reg.footer.cancel"></s:text></span></a>
				</div>
			
		</div>
	
	</div>

    </div>
</div>

</div>
	
	<div class="div-table-simple" id="nonprintableDiv2">
		<div class="div-table-row">
			<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%"></div>
		</div>
	</div>
	
	<div class="div-table" id="nonprintableDiv3">
		<div class="div-table-row">
			<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%"></div>
		</div>
	</div>

	<div class="div-table" id="nonprintableDiv5" style="width: 40%; vertical-align: middle;">
		<div class='div-table-row'>
			<div class='div-table-col label' style='width: 100%; position:absolute; right:12px; top:8px;'>
				<img src='/HIS/hisglobal/images/nonclinical/nc_cancel.png' width='20' height='20' onclick='mainMenu();' style='vertical-align: middle;cursor: pointer;' title='Cancel' />
			</div>
		</div>
		<br><br><br><br><br><br><br><br>
		<div class="div-table-row">
			<div class="div-table-col label wrapper rounded" style="width: 48%; float: left;" onclick='showEmployeeRegistrationPage();'>
				<center>
				<br>
				<img src='/HIS/hisglobal/images/nonclinical/emp_add.png'  width="100" height="100" style='vertical-align: middle;cursor: pointer;' title='Employee Registration' />
				<br>
				<s:text name="emp.reg.option.guest.employeeregistration"></s:text>
				<br><br>
				</center>
			</div>
			<div class="div-table-col label" style="width: 4%; float: left;">
				&nbsp;
			</div>
			<div class="div-table-col label wrapper rounded" style="width: 48%; float: left;" onclick='showEmployeeRegistrationModifyPage();'>
				<center>
				<br>
				<img src='/HIS/hisglobal/images/nonclinical/emp_update.png'  width="100" height="100" style='vertical-align: middle;cursor: pointer;' title='Modify Registration Details' />
				<br>
				<s:text name="emp.reg.option.guest.updateemployeeregistration"></s:text>
				<br><br>
				</center>
			</div>
		</div>
	</div>
	
	<div id="nonprintableDiv7" style="width: 55%; vertical-align: middle;">
	<br><br>
		<div class="wrapper rounded" style="padding: 8px;">
			
			<div style="position:absolute; right:14px; top:12px; color: #025B85; font: bold 13px 'Lucida sans', Arial, Helvetica;" > 
				<span id="goGuestModifyId" style="cursor: pointer" title='Go'><img src='/HIS/hisglobal/images/nonclinical/next.png' width='20' height='20' style='vertical-align: middle;' title='Go' /> 
				<s:text name="emp.reg.search.emp.search.go"></s:text>
				</span>
				&nbsp;
				<span id="cancelGuestModifyId" style="cursor: pointer" title='Cancel'><img src='/HIS/hisglobal/images/nonclinical/nc_cancel.png' width='18' height='18' style='vertical-align: middle;' title='Cancel' /> 
				<s:text name="emp.reg.footer.cancel"></s:text>
				</span>
			</div>

			<ul id="tabs1">
			    <li><a href="#" title="tab2"><s:text name="emp.reg.guest.heading.1"></s:text></a></li>
			</ul>
			
			<div id="content" > 
   				<div id="tab2">
   
   
   					<div class="div-table wrapper rounded">
						
						<div class="div-table-row">
								<div class="div-table-col">&nbsp; </div>
						</div>
						
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%">
								<font color="red">*</font><s:text name="emp.reg.search.emp.number"></s:text>
							</div>
							<div class="div-table-col control" style="width: 50%">
<!-- 								<input type="text" name="strGestModifyEmployeeNumber" id="strGestModifyEmployeeNumber" maxlength="12" size="15"> -->
								<input id="strGestModifyEmployeeNumber" name="strGestModifyEmployeeNumber" maxlength='<%=PisConfig.EMP_NO_LENGTH %>' size='<%=PisConfig.EMP_NO_SIZE %>' type="text" onkeypress='<%="return validateData(event,"+PisConfig.EMP_NO_TYPE_CODE+")" %> ' >
							</div>
						</div>
						
						<div class="div-table-row emptyBar ">
								<div class="div-table-col"> </div>
						</div>
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%">
								<s:text name="emp.reg.guest.emp.reg.modify.operator"></s:text>
							</div>
							<div class="div-table-col control" style="width: 50%">
								
							</div>
						</div>
						<div class="div-table-row emptyBar ">
								<div class="div-table-col"> </div>
						</div>
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%">
								<font color="red">*</font><s:text name="emp.reg.dob"></s:text>
							</div>
							<div class="div-table-col control" style="width: 50%">
								<input id="guestModifyEmpDOBId" type="text" name="dtGestModifyEmployeeDoB" size="12">
							</div>
						</div>
						<div class="div-table-row">
								<div class="div-table-col width100 fontError" id="guestLoginErrorMsgId">&nbsp;</div>
						</div>
						
					</div>
   
   				</div>
   			</div>
   			
		</div>
	</div>
	
	
	<div id="divPrintId" ></div>
	<div class='wrapper rounded' id="divPrintId1" style="display: none;text-align: center;" align="center"></div>	
	<div class='wrapper rounded' id="divPrintId2" style="text-align: center;" align="center"></div>
	
	<div id="divEmpDtlPreviewId" align='center'></div>

	<!--  <div id="divEmpDtlAfterSaveForPrintId" align='center'></div>-->
			
	<input type="hidden" name="strEmployeeNumber" />
	<input type="hidden" name="flagGuestOperationAddOrModify" />
	<s:hidden name="flagGuestOrUser" value="%{flagGuestOrUser}"></s:hidden>

<s:token />
<%--<input type="hidden" name="<csrf:tokenname/>" value="<csrf:tokenvalue/>"/>--%> 								
</s:form>
<script type="text/javascript" src="/HISRegistration/hr/pis/empReg/transactions/js/empRegistration.js" /></script>
<%-- <script type="text/javascript" src="/HISRegistration/hr/pis/empReg/transactions/js/empRegistrationValidate.js" /></script> --%>
</center>

						
						
<div id='loading-image' class="popUpDiv loading" style="display: none;" >
</div>


<!--  
<s:url id="localeEN" namespace="/pis/transactions" action="changeLocaleEmployeeRegistration" >
   <s:param name="request_locale" >en</s:param>
</s:url>

<s:url id="localeDE" namespace="/pis/transactions" action="changeLocaleEmployeeRegistration" >
   <s:param name="request_locale" >de</s:param>
</s:url>
 
<s:a href="%{localeEN}" >English</s:a>

<s:a href="%{localeDE}" >Telugu</s:a>
-->
 
</body>
</html>