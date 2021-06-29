
var departmentJSONArray=[];

var empregistrationvalidation={
		
	  fetchDefaultValues:function ()
	  {
		  
		  //Date Fields
		  $("#validateDateId").datepicker({changeMonth: true, changeYear: true, maxDate: '-d', dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
		  
		  //Date Fields
		  $("#validateDateId").validatebox({required: true, validType: ['date[\'dtValidateDate\',\'dd-mmm-yyyy\']']});
		  $('[name="VOEmpReg.strValidatorName"]').validatebox({required : true, validType: 'alphaWithSpace'});
		  $('[name="strValidatorRemarks"]').validatebox({validType: 'alphaNumSpecialChar'});
		  
		  var $radios = $('[name="VOEmpReg.strValidateStatus"]');
		  $radios.filter('[value=Y]').prop('checked', true);
		  
		  
		  
		  var v_EmployeeNumber                    =$('[name="VOEmpReg.strEmployeeNumber"]')[0].value;
		  var v_IsExistingEmployee                =$('[name="VOEmpReg.strIsExistingEmployee"]')[0].value;
		  var v_LastEmploymentType                =$('[name="VOEmpReg.strLastEmploymentType"]')[0].value;
		  var v_NatureOfJob                       =$('[name="VOEmpReg.intNatureOfJobId"]')[0].value;
		  var v_Appellation1                      =$('[name="VOEmpReg.intAppellationCode1"]')[0].value;
		  var v_Appellation2                      =$('[name="VOEmpReg.intAppellationCode2"]')[0].value;
		  var v_EmployeeFullName                  =$('[name="VOEmpReg.strEmployeeFullName"]')[0].value;
		  var v_EmployeeFullRegionalLanguageName  =$('[name="VOEmpReg.strEmployeeFullRegionalLangName"]')[0].value;
		  var v_EmployeeShortName                 =$('[name="VOEmpReg.strEmployeeShortName"]')[0].value;
		  var v_EmployeeShortRegionalLanguageName =$('[name="VOEmpReg.strEmployeeShortRegionalLangName"]')[0].value;
		  var v_Suffix                            =$('[name="VOEmpReg.intSuffixCode"]')[0].value;
		  var v_DoB                               =$('[name="VOEmpReg.dtEmployeeDOB"]')[0].value;
		  var v_Gender                            =$('[name="VOEmpReg.strGenderCode"]')[0].value;
		  var v_Nationality                       =$('[name="VOEmpReg.intNationalityId"]')[0].value;
		  var v_Department                        =$('[name="VOEmpReg.intDepartmentCode"]')[0].value;
		  var v_Designation                       =$('[name="VOEmpReg.intDesignationCode"]')[0].value;
		  var v_OldEmployeeNumber                 =$('[name="VOEmpReg.strOldEmployeeNumber"]')[0].value;
		  var v_EmployeeFinalStatus               =$('[name="VOEmpReg.strEmployeeFinalStatus"]')[0].value;
		  var v_MobileNumber		              =$('[name="VOEmpReg.intMobileNumber"]')[0].value;
		  var v_EmailId               			  =$('[name="VOEmpReg.strPersonalEmailId"]')[0].value;
		  var v_PANNumber                         =$('[name="VOEmpReg.strPANNumber"]')[0].value;
		  
		  
		  $("#validateEmployeeNumberId").html(v_EmployeeNumber);
		  $("#validateIsExistingEmployeeId").html(v_IsExistingEmployee);
		  $("#validateLastEmploymentTypeId").html(v_LastEmploymentType);
		  $("#validateNatureOfJobId").html(v_NatureOfJob);
		  $("#validateAppellation1Id").html(v_Appellation1);
		  $("#validateAppellation2Id").html(v_Appellation2);
		  $("#validateEmployeeFullNameId").html(v_EmployeeFullName);
		  $("#validateEmployeeFullRegionalLanguageNameId").html(v_EmployeeFullRegionalLanguageName);
		  $("#validateEmployeeShortNameId").html(v_EmployeeShortName);
		  $("#validateEmployeeShortRegionalLanguageNameId").html(v_EmployeeShortRegionalLanguageName);
		  $("#validateSuffixId").html(v_Suffix);
		  $("#validateDoBId").html(v_DoB);
		  $("#validateGenderId").html(v_Gender);
		  $("#validateNationalityId").html(v_Nationality);
		  $("#validateDepartmentId").html(v_Department);
		  $("#validateDesignationId").html(v_Designation);
		  $("#validateOldEmployeeNumberId").html(v_OldEmployeeNumber);
		  $("#validateEmployeeFinalStatusId").html(v_EmployeeFinalStatus);
		  $("#validateMobileNumberId").html(v_MobileNumber);
		  $("#validateEmailId").html(v_EmailId);
		  $("#validatePANNumberId").html(v_PANNumber);
			
	  },
	  setFormFieldsAfterSave:function()
	  {
		  $('.validatebox-text').removeClass('validatebox-invalid');
		  var $radios = $('[name="VOEmpReg.strValidateStatus"]');
		  $radios.filter('[value=Y]').prop('checked', true);
		  
		  //$('input[name=VOEmpReg.strValidateStatus]:checked').val("1");
		  //$('[name="VOEmpReg.strValidateStatus"]')[0].value="1";
		  $('[name="VOEmpReg.strValidatorName"]')[0].value="";
		  $('[name="strValidatorRemarks"]')[0].value="";
		  
		  empregistrationvalidation.fetchDefaultValues();
		  
		 
		 // setMandatoryReadOnlyTrueFalse(false);
	  }
	   
	};



//On Click of Back
$('#backId').click(function(e){
	$("#EmployeeRegistrationValidation").attr('action',"/HISPis/pis/masters/EmployeeRegistrationValidation.action");
	$('#EmployeeRegistrationValidation').submit();
});

//On Click of Cancel
$('#cancelId').click(function(e){
	goToDefaultPage('1');
	e.preventDefault();
});

$('#clearId').click(function(e){
	
	empregistrationvalidation.setFormFieldsAfterSave();
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");
}); 

$('#submitId').click(function(e){
	
	$("#EmployeeRegistrationValidation").attr('action',"/HISPis/pis/pr/transactions/saveEmployeeRegistrationValidation.action");
	if($('#EmployeeRegistrationValidation').form('validate')==true)
	{
		$('[name="VOEmpReg.strValidateId"]')[0].value='999999999999';
		$('[name="VOEmpReg.dtValidateDate"]')[0].value=$('[name="dtValidateDate"]')[0].value;
		
		
		$('[name="VOEmpReg.strValidatorRemarks"]')[0].value=$('[name="strValidatorRemarks"]')[0].value;
		
		
		$('#EmployeeRegistrationValidation').submit();
	}
	//e.preventDefault();
});

