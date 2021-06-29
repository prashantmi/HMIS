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
<link rel="stylesheet"  type="text/css" href="/HISPis/hisglobal/css/buttons.css" >
<link rel="stylesheet"  type="text/css" href="/HISPis/hisglobal/css/tab.css" >
<link rel="stylesheet" type="text/css" href="/HISPis/hisglobal/css/layout.css" >
<link href="/HISPis/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HISPis/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HISPis/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HISPis/hisglobal/css/basic.css">
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="Javascript" src="/HISPis/hisglobal/js/security.js"></script>



<script language="JavaScript" type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='/HISPis/hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/masterutil/js/jquery/basic.js'></script>
<script type="text/javascript" src="/HISPis/hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="/HISPis/hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhime.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhindic.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhime-common.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/multilingualSupport.js'></script>

<!--  theme changer start-->
<link rel="stylesheet" type="text/css" title="blue" href="/HISPis/hisglobal/css/buttons.css" />
<link rel="stylesheet" type="text/css" title="blue" href="/HISPis/hisglobal/css/layout.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HISPis/hisglobal/css/buttons-green.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HISPis/hisglobal/css/layout-green.css" />
<link rel="stylesheet" type="text/css" title="normal" href="/HISPis/hisglobal/css/normalfont.css" />
<link rel="alternate stylesheet" type="text/css" title="large" href="/HISPis/hisglobal/css/largefont.css" />
<link rel="alternate stylesheet" type="text/css" title="small" href="/HISPis/hisglobal/css/smallfont.css" />
<script type="text/javascript" src="/HISPis/hisglobal/js/themechanger.js"></script>
<!--  theme changer end-->	

<link href="/HISPis/hisglobal/css/global.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/HISPis/hisglobal/js/pisGlobal.js"></script>
<jsp:include page="/hr/common/jsp/globalFields.jsp" />

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


</style>

<title>Employee Registration Validation</title>
<script>
	
$(window).on("load.loading1", function(){
	//alert("onload");
	
	empregistrationvalidation.fetchDefaultValues();
	
}); 
	
$(document).ready(function() {

	funcTab1("#tab1");
	//$('[name="strCurrentTab"]')[0].value='tab1';
	
	$('#tabs1 a').click(function(e) {
   	   //Setting Current Tab Value
   	   //$('[name="strCurrentTab"]')[0].value=$(this).attr('name');
	      
   });
	   
})();


	
</script>

</head>

<body>
<center>
<s:actionerror/>

 <div id="one" style="position: absolute;width: 100%"> 
 <div class="wrapper rounded" id="nonprintableDiv1">
 
<s:form action="EmployeeRegistrationValidation">

<ul id="tabs1">
    <li><a href="#" name="tab1"><s:text name="emp.reg.validation.heading.1"></s:text></a></li>
</ul>

<div id="content"> 
    <div id="tab1">  
    
    <div class="wrapper rounded" id="nonprintableDiv1">
	    
		<div class="div-table">
			
			
			<div class="div-table" >
				<div class="div-table-row emptyBar ">
					<div class="div-table-col"> </div>
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.search.emp.number"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateEmployeeNumberId">
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.isexistingemployee"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateIsExistingEmployeeId">
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%" >
					<s:text name="emp.reg.lastemploymenttype"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateLastEmploymentTypeId">
				
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.natureofjob"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateNatureOfJobId">
					
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.appellation1"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateAppellation1Id">
					
				</div>
			</div>
		
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.appellation2"></s:text> 
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateAppellation2Id">
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.employeefullname"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateEmployeeFullNameId">
				</div>
			</div>
		
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.validation.employeefullregionallanguagename"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateEmployeeFullRegionalLanguageNameId">
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.employeeshortname"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateEmployeeShortNameId">
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.validation.employeeshortregionallanguagename"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateEmployeeShortRegionalLanguageNameId">
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.suffix"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateSuffixId">
		
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.dob"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateDoBId">
	 			
	 			</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.gender"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateGenderId">
			
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.nationality"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateNationalityId">
					
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.department"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateDepartmentId">
					
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.designation"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateDesignationId">
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.oldemployeenumber"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateOldEmployeeNumberId">
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.empfinalstatus"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateEmployeeFinalStatusId">
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.mobilenumber"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateMobileNumberId">
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.emailid"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validateEmailId">
					
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 45%">
					<s:text name="emp.reg.panno"></s:text>
				</div>
				<div class="div-table-col width control" style="width: 1%">
					:
				</div>
				<div class="div-table-col width control" style="width: 54%" id="validatePANNumberId">
					
				</div>
			</div>
			
			<s:set name="employeeValidationStatusCategory" value="%{VOEmpReg.strEmployeeValidationStatusCategory}"/>
 
			<s:if test="%{#employeeValidationStatusCategory==1}">
	
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 45%">
						<font color="red">*</font><s:text name="global.validation.validateStatus"></s:text>
					</div>
					<div class="div-table-col width control" style="width: 1%">
						:
					</div>
					<div class="div-table-col width control" style="width: 54%">
						<s:radio name="VOEmpReg.strValidateStatus" list="#{'Y':'Yes','N':'No'}" />
					</div>
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 45%">
						<font color="red">*</font><s:text name="global.validation.validateBy"></s:text>
					</div>
					<div class="div-table-col width control" style="width: 1%">
						:
					</div>
					<div class="div-table-col width control" style="width: 54%">
						<s:textfield name="VOEmpReg.strValidatorName" size="40" maxlength="99"></s:textfield>
					</div>
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 45%">
						<font color="red">*</font><s:text name="global.validation.validateDt"></s:text>
					</div>
					<div class="div-table-col width control" style="width: 1%">
						:
					</div>
					<div class="div-table-col width control" style="width: 54%">
						<input id="validateDateId" type="text" name="dtValidateDate" size="12">
					</div>
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 45%">
						<s:text name="global.validation.validatorRemarks"></s:text>
					</div>
					<div class="div-table-col width control" style="width: 1%">
						:
					</div>
					<div class="div-table-col width control" style="width: 54%">
						<textarea  maxlength="99" name="strValidatorRemarks" rows="2" cols="40"></textarea>
					</div>
				</div>
			
			</s:if>
			<s:elseif test="%{#employeeValidationStatusCategory==2}">
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 45%">
						<s:text name="global.validation.validateStatus"></s:text>
					</div>
					<div class="div-table-col width control" style="width: 1%">
						:
					</div>
					<div class="div-table-col width control" style="width: 54%">
						<font color='green'><b><i>Validated</i></b></font>
					</div>
				</div>
			</s:elseif>
			<s:else>
    			<div class="div-table-row ">
					<div class="div-table-col label" style="width: 45%">
						<s:text name="global.validation.validateStatus"></s:text>
					</div>
					<div class="div-table-col width control" style="width: 1%">
						:
					</div>
					<div class="div-table-col width control" style="width: 54%">
						<font color='red'><b><i>Rejected</i></b></font>
					</div>
				</div>
			</s:else>
			
		</div>
		
			<div class="div-table-button">
				<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center">
					<s:if test="%{#employeeValidationStatusCategory==1}">
						<a href="#" class="button" id="submitId"><span class="save"><s:text name="emp.reg.footer.save"></s:text></span></a>
						<a href="#" class="button" id="clearId"><span class="clear"><s:text name="emp.reg.footer.clear"></s:text></span></a>
					</s:if>
					<a href="#" class="button" id="backId"><span class="back"><s:text name="emp.reg.validation.footer.back"></s:text></span></a>
					<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="emp.reg.footer.cancel"></s:text></span></a>
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

	<div id="divPrintId" ></div>
	
	<div id="divEmpDtlPreviewId" align='center'></div>
	
	<s:hidden name="VOEmpReg.strValidateId"></s:hidden>
	<s:hidden name="VOEmpReg.strValidatorRemarks"></s:hidden>
	<s:hidden name="VOEmpReg.dtValidateDate"></s:hidden>
	
	<s:hidden name="VOEmpReg.strEmployeeNumber" value="%{VOEmpReg.strEmployeeNumber}"></s:hidden>
	<s:hidden name="VOEmpReg.strIsExistingEmployee" value="%{VOEmpReg.strIsExistingEmployee}"></s:hidden>
	<s:hidden name="VOEmpReg.strEmployeeFullName" value="%{VOEmpReg.strEmployeeFullName}"></s:hidden>
	<s:hidden name="VOEmpReg.strEmployeeFullRegionalLangName" value="%{VOEmpReg.strEmployeeFullRegionalLangName}"></s:hidden>
	<s:hidden name="VOEmpReg.dtEmployeeDOB" value="%{VOEmpReg.dtEmployeeDOB}"></s:hidden>
	<s:hidden name="VOEmpReg.intNatureOfJobId" value="%{VOEmpReg.intNatureOfJobId}"></s:hidden>
	<s:hidden name="VOEmpReg.intAppellationCode1" value="%{VOEmpReg.intAppellationCode1}"></s:hidden>
	<s:hidden name="VOEmpReg.intAppellationCode2" value="%{VOEmpReg.intAppellationCode2}"></s:hidden>
	<s:hidden name="VOEmpReg.strEmployeeShortName" value="%{VOEmpReg.strEmployeeShortName}"></s:hidden>
	<s:hidden name="VOEmpReg.strEmployeeShortRegionalLangName" value="%{VOEmpReg.strEmployeeShortRegionalLangName}"></s:hidden>
	<s:hidden name="VOEmpReg.intSuffixCode" value="%{VOEmpReg.intSuffixCode}"></s:hidden>
	<s:hidden name="VOEmpReg.strGenderCode" value="%{VOEmpReg.strGenderCode}"></s:hidden>
	<s:hidden name="VOEmpReg.intNationalityId" value="%{VOEmpReg.intNationalityId}"></s:hidden>
	<s:hidden name="VOEmpReg.intDepartmentCode" value="%{VOEmpReg.intDepartmentCode}"></s:hidden>
	<s:hidden name="VOEmpReg.intDesignationCode" value="%{VOEmpReg.intDesignationCode}"></s:hidden>
	<s:hidden name="VOEmpReg.strOldEmployeeNumber" value="%{VOEmpReg.strOldEmployeeNumber}"></s:hidden>
	<s:hidden name="VOEmpReg.strEmployeeFinalStatus" value="%{VOEmpReg.strEmployeeFinalStatus}"></s:hidden>
	<s:hidden name="VOEmpReg.strLastEmploymentType" value="%{VOEmpReg.strLastEmploymentType}"></s:hidden>
	<s:hidden name="VOEmpReg.intMobileNumber" value="%{VOEmpReg.intMobileNumber}"></s:hidden>
	<s:hidden name="VOEmpReg.strPersonalEmailId" value="%{VOEmpReg.strPersonalEmailId}"></s:hidden>
	<s:hidden name="VOEmpReg.strPANNumber" value="%{VOEmpReg.strPANNumber}"></s:hidden>
	<s:hidden name="VOEmpReg.strEmployeeValidationStatusCategory" value="%{VOEmpReg.strEmployeeValidationStatusCategory}"></s:hidden>

<s:token />
<%--<input type="hidden" name="<csrf:tokenname/>" value="<csrf:tokenvalue/>"/>--%> 		
</s:form>
</div>
</div>
<script type="text/javascript" src="/HISPis/hr/pis/empReg/transactions/js/empRegistrationValidation.js" /></script>
</center>

						
<div id='loading-image' class="popUpDiv loading" style="display: none;" >
</div>



 
</body>
</html>