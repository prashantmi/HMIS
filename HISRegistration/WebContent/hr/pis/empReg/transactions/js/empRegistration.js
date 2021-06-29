//alert("inside empRegistration.js");
$.datepicker.setDefaults( 
	    {	showOn: 'both',
	    	defaultDate: new Date(),
	    	buttonImageOnly: true, 
	    	buttonText : "Select Date",
	    	buttonImage: "/HIS/hisglobal/images/nonclinical/nc_calendar_icon.gif",
	    	yearRange: "-100:+100"
	    	});


//Logic for validating birth date i.e person who is going to be registered, should be of at least 18 Years
var age = 18;
var maxBirthDate = new Date();
maxBirthDate.setFullYear(maxBirthDate.getFullYear() - age);


var departmentJSONArray=[];

var empregistration={
		
		fetchDefaultValues:function ()
		{
			
			//alert("hello");
			isExistingEmployeeCheck("fetchDefaultValues");
			
			//For Multilingual Initialization			
			//initMultilingual(localLanguage); //Modified by Ashwini Mishra on 06-08-2015
			//For Setting Visibility of Save or Update Footer
			setSaveOrUpdateFooterVisibility();
			
			isPANNumberAvailableCheck();
			
			
		        
			//Date Fields
			//$("#empDOBId").datepicker({changeMonth: true, changeYear: true, maxDate: '-d', dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
			$("#empDOBId").datepicker({changeMonth: true, changeYear: true, maxDate: maxBirthDate, dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
			
			
			//Combo Fields
			$('[name="intNatureOfJobId"]').validatebox({validType: ['selectCombo[-1]'] });
			$('[name="intAppellationCode1"]').validatebox({validType: ['selectCombo[-1]'] });
			$('[name="strGenderCode"]').validatebox({validType: ['selectCombo[-1]'] });
			$('[name="intDepartmentCode"]').validatebox({validType: ['selectCombo[-1]'] });
			$('[name="intDesignationCode"]').validatebox({validType: ['selectCombo[-1]'] });
			$('[name="strEmployeeFinalStatus"]').validatebox({validType: ['selectCombo[-1]'] });
			
			//Textbox Fields
			$('[name="strEmployeeFullName"]').validatebox({required: true, validType: 'alphaWithSpace'});
			$('[name="strEmployeeFullRegionalLangName"]').validatebox({required: true});
			$('[name="strEmployeeShortName"]').validatebox({required: true, validType: 'alphaWithSpace' });
			$('[name="strEmployeeShortRegionalLangName"]').validatebox({required: true});
			$('[name="dtEmployeeDOB"]').validatebox({required: true });
			$('[name="intMobileNumber"]').validatebox({required: false, noSpace: true, validType: ['numeric','minLength[10]']});
			$('[name="strPersonalEmailId"]').validatebox({required: false, validType: 'email'});
		
			
			//Date Fields
			$("#empDOBId").validatebox({required: true, validType: ['date[\'dtEmployeeDOB\',\'dd-mmm-yyyy\']']});
			
			
			
			var action 	= "/HISRegistration/pis/masters/populateformvaluesEmployeeRegistration.action";
			
			//For Submission
			//$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,data:$('form').serialize, success:function(data)
			$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
				{
						var returnDocument=data;
						//alert(returnDocument);
						var rootNode=returnDocument.getElementsByTagName("root")[0];
						//alert(rootNode);
						for(var i=0;i<rootNode.childNodes.length;i++ )
						{
							var elementNode=rootNode.childNodes[i];
							var elementName=elementNode.nodeName;
							if(elementName=='intDepartmentCode')
							{
								//alert("hello department");
								empregistration.processGeneralNode(elementName,elementNode);
							}
							else if(elementName=='defaults')
							{
								
							}
							else
							{
								empregistration.processGeneralNode(elementName,elementNode);
							}
						}
						
						empregistration.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
						
				},error: function(errorMsg,textstatus,errorthrown) {
		            alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		            
		        }
			});
			
			
	  },
	  
	  /*onchange_natureOfJobIdChanged:function () {
			 
	

		        var natureOfJobId=$('[name="intNatureOfJobId"]')[0].value;	
		        alert("natureOfJobId"+natureOfJobId);
		    	var action 	= "/HISPis/pis/pr/transactions/getNatureOfJobEmployeeRegistration.action?"+"&natureOfJobId="+natureOfJobId;
		    	$("#intNatureOfJobId").html("<option value='-1'>Select Value</option>");
		    	
		    	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
					{
							var returnDocument=data;
							var rootNode=returnDocument.getElementsByTagName("root")[0];
							for(var i=0;i<rootNode.childNodes.length;i++ )
							{
								var elementNode=rootNode.childNodes[i];
								var elementName=elementNode.nodeName;
								if(elementName=='intDepartmentCode')
								{
									//alert("hello department");
									empregistration.processGeneralNode(elementName,elementNode);
								}
								else if(elementName=='defaults')
								{
									
								}
								else
								{
									empregistration.processGeneralNode(elementName,elementNode);
								}
							}
							
							 empregistration.fetchDefaultValues();
							
					},error: function(errorMsg,textstatus,errorthrown) {
			            alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
			            
			        }
				});
				
		    	
		 },*/
	  processGeneralNode:function(elementName,elementNode)
	  {
		var optionText="<option value='-1'>Select Value</option>";
		
		if(elementNode!=null){
			for(var i=0;i<elementNode.childNodes.length;i++ )
			{
				var optionNode=elementNode.childNodes[i];
				optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
				
			}
		}
		
		if(document.getElementsByName(elementName).length!=0)
				document.getElementsByName(elementName)[0].innerHTML=optionText;
		
		//alert("elementName...."+elementName+".......optionText......"+optionText);
		
	  },
	  setdefaultVariables:function(elementNode)
	  {
		for(var i=0;i<elementNode.attributes.length;i++ )
		{
			
			eval("var "+elementNode.attributes[i].name+"='"+elementNode.attributes[i].value+"'");
			//document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].value=eval(elementNode.attributes[i].name);
			if(elementNode.attributes[i].name=="defaultintNationalityId"){
				document.getElementsByName("defaultintNationalityId")[0].value=eval(elementNode.attributes[i].name);
				document.getElementsByName("intNationalityId")[0].value=eval(elementNode.attributes[i].name);
			}else if(elementNode.attributes[i].name=="defaultstrEmployeeFinalStatus"){
				document.getElementsByName("defaultstrEmployeeFinalStatus")[0].value=eval(elementNode.attributes[i].name);
				document.getElementsByName("strEmployeeFinalStatus")[0].value=eval(elementNode.attributes[i].name);
				
			}
			else if(elementNode.attributes[i].name=="defaultstrLastEmploymentType"){
				//document.getElementsByName("defaultstrLastEmploymentType")[0].value=eval(elementNode.attributes[i].name);
				document.getElementsByName("strLastEmploymentType")[0].value=eval(elementNode.attributes[i].name);
				
			}
			else if(elementNode.attributes[i].name=="defaultintNatureOfJobId"){
				//document.getElementsByName("defaultintNatureOfJobId")[0].value=eval(elementNode.attributes[i].name);
				document.getElementsByName("intNatureOfJobId")[0].value=eval(elementNode.attributes[i].name);
				
			}
			
		}
		//$("#patAddCountryCodeId").bind("change",opdregistration.onchange_patAddCountryCode);
		
		
	  },
	  saveEmployeeDtl:function()
	  {
		  //alert("saveEmployeeDtl");
		  //For Submission
		  var action 	= "/HISRegistration/pis/masters/saveEmployeeRegistration.action";
		 sortandEncodebase64($("#EmployeeRegistration"));
		  $('#submitId').hide();
		  $.ajax({url: action,type:"POST",async:true,dataType:"xml" ,data:$("#EmployeeRegistration").serialize(), success:function(data){
			    var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				
				var elementNode=rootNode.childNodes[0];
				var elementName=elementNode.nodeName;
				if(elementName=='savedMsgDtl')
				{
					empregistration.initializeAfterSave(elementName,elementNode);
					//createEmployeeDetailsAfterSaveForPrint(elementName,elementNode);				
				}
				
				
				$("#divEmpDtlPreviewId").hide();  // checked
				//$("#nonprintableDiv1").show();
				//$("#content").show();
				//$("#tabs").show();
				//$("#nonprintableDiv1").fadeIn(1500);
				$("#divRptId").children().find('.label, .control').css('font-size','12px');
				$("#reportForm").children().find('.label, .control').css('font-size','12px');
				$("#divHospitalName").css('font-size','14px');
				$("#reportForm").children().css('color','black');
				$("#reportForm").children().children().css('color','black');
				
				$("#divPrintId").fadeIn(1500); // Show first tab content
				$("#nonprintableDiv3").show();
				
				$('#submitId').show();
				
			},error: function(errorMsg,textstatus,errorthrown) {
				alert("1");
				
				$('#submitId').show();
				//alert('saveEmployeeDtl '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
				showtamperErrorPage("saveEmployeeDtl " + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
				//showAjaxError("saveEmployeeDtl " + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
				//alert("error data tempered");
				
			}});
	  },
	  updateEmployeeDtl:function()
	  {
		  //For Submission
		  var action 	= "/HISRegistration/pis/masters/updateEmployeeRegistration.action";
		  $('#updateId').hide();
		  $.ajax({url: action,type:"POST",async:true,dataType:"xml" ,data:$("#EmployeeRegistration").serialize(), success:function(data){
			    var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				
				var elementNode=rootNode.childNodes[0];
				var elementName=elementNode.nodeName;
				if(elementName=='savedMsgDtl')
				{
					empregistration.initializeAfterSave(elementName,elementNode);
				}
				$("#nonprintableDiv3").show();
				$('#updateId').show();
				
			},error: function(errorMsg,textstatus,errorthrown) {
				$('#updateId').show();
			    alert('updateEmployeeDtl '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
			    
			}});
	  },
	  deleteEmployeeDtl:function()
	  {
		  //For Submission
		  var action 	= "/HISRegistration/pis/masters/deleteEmployeeRegistration.action";
		  $('#deleteId').hide();
		  $.ajax({url: action,type:"POST",async:true,dataType:"xml" ,data:$("#EmployeeRegistration").serialize(), success:function(data){
			    var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				
				var elementNode=rootNode.childNodes[0];
				var elementName=elementNode.nodeName;
				if(elementName=='savedMsgDtl')
				{
					$('[name="flagGuestOperationAddOrModify"]')[0].value='Delete';
					empregistration.initializeAfterSave(elementName,elementNode);
					
				}
				
				
					
				$("#nonprintableDiv3").show();
				
				$('#deleteId').show();
				
			},error: function(errorMsg,textstatus,errorthrown) {
				$('#deleteId').show();
			    alert('deleteEmployeeDtl '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
			    
			}});
	  },
	  initializeAfterSave:function(saveMsgElementName,saveMsgElementNode)
	  {
		  //$('#fatherorSpouseError').hide();
		  var isSavedSuccussful	   = saveMsgElementNode.getAttribute("isSavedSuccussful");
		  var msg				   = saveMsgElementNode.getAttribute("savedMessage");
		  var isFormFieldTobeReset = saveMsgElementNode.getAttribute("isFormFieldTobeReset");
		  var isPrintFlag	   	   = saveMsgElementNode.getAttribute("isPrintFlag");
		  var printDivContent	   = saveMsgElementNode.getAttribute("printDivContent");
		  
		  if(isSavedSuccussful=="1")
		  {
			  $("#divNormalMsgId").html(msg);
			  $("#divErrorMsgId").html("");
			  if(isPrintFlag=="1"){
				  $("#divPrintId").html(printDivContent);
				   //get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
				  //window.print();
			  }else{
				  $("#divPrintId").html("");
			  }
			  
			  if(isFormFieldTobeReset=="1"){
				  //if(confirm(msg+"\nDo you Want To Reset The Form"))
				  var local_flag_Guest_Or_User=$('[name="flagGuestOrUser"]')[0].value;
				  if(local_flag_Guest_Or_User=='user')
				  {
					  empregistration.setFormFieldsAfterSave();
				  }
				  else if(local_flag_Guest_Or_User=='guest')
				  {
					  //alert("Operation = "+$('[name="flagGuestOperationAddOrModify"]')[0].value);
					  if($('[name="flagGuestOperationAddOrModify"]')[0].value=='Add' || $('[name="flagGuestOperationAddOrModify"]')[0].value=='Delete')
					  {
						  //alert("Add");
						  $("#regSlipGuestId").hide(); // This is in case of Delete
						  empregistration.setFormFieldsAfterSave();
					  }
					  else if($('[name="flagGuestOperationAddOrModify"]')[0].value=='Modify')
					  {
						  //alert("Modify");
						  getEmpDetailsAfterUpdationForGuest();
					  }
				  }
					
			  }
		  }
		  else{
			  	$("#divNormalMsgId").html("");
				$("#divErrorMsgId").html(msg);
				$("#divPrintId").html("");
				$("#nonprintableDiv2").show();
			  
			  	$("#content").show();
			  	$("#tabs").show();
			  	$("#tabs1").show();
				$("#nonprintableDiv1").fadeIn(1500);
			  
				// if(confirm(msg+"\nDo you Want To Reset The Form"))
				// opdregistration.setFormFieldsAfterSave();
		  }
		  
	  },
	  setFormFieldsAfterSave:function()
	  {
		  //$('#fatherorSpouseError').hide();
		  if($('[name="strIsExistingEmployee"]')[0].disabled)
		  {
			  $('[name="strIsExistingEmployee"]')[0].disabled=false;
		  }

		  if ($('[name="dtEmployeeDOB"]').attr('readonly') == 'readonly')
		  { 
			  $('[name="dtEmployeeDOB"]').removeAttr('readonly');
		  }

		  $('.validatebox-text').removeClass('validatebox-invalid');
		  //$('.validatebox-text').removeClass('validatebox-invalid');
		 
		  var local_flag_Guest_Or_User=$('[name="flagGuestOrUser"]')[0].value;
			if(local_flag_Guest_Or_User=='user')
			{
				 $('[name="strEmployeeUpdateFlag"]')[0].checked=false;
			}
		  
		  $('[name="strIsExistingEmployee"]')[0].checked=false;
		  $('[name="copyFullNameInShortName"]')[0].checked=false;
		  $('[name="intNatureOfJobId"]')[0].value="-1";
		  $('[name="intAppellationCode1"]')[0].value="-1";
		  $('[name="intAppellationCode2"]')[0].value="-1";
		  $('[name="strEmployeeFullName"]')[0].value="";
		  //$('[name="strEmployeeFullRegionalLangName"]')[0].value="";
		  $('[name="strEmployeeShortName"]')[0].value="";
		 // $('[name="strEmployeeShortRegionalLangName"]')[0].value="";
		 // $('[name="intSuffixCode"]')[0].value="-1";
		  $('[name="dtEmployeeDOB"]')[0].value="";
		  $('[name="strGenderCode"]')[0].value="-1";
		  $('[name="intNationalityId"]')[0].value=$('[name="defaultintNationalityId"]')[0].value;
		  $('[name="intDepartmentCode"]')[0].value="-1";
		  $('[name="intDesignationCode"]')[0].value="-1";
		  $('[name="strOldEmployeeNumber"]')[0].value="";
		  $('[name="strEmployeeFinalStatus"]')[0].value=$('[name="defaultstrEmployeeFinalStatus"]')[0].value;
		  $('[name="strLastEmploymentType"]')[0].value="-1";
		  
		  $('[name="intMobileNumber"]')[0].value="";
		  $('[name="strPersonalEmailId"]')[0].value="";
		  $('[name="strPANNumberAvailableFlag"]')[0].checked=false;
		  $('[name="strPANNumber"]')[0].value="";
		  
		  
		  empregistration.fetchDefaultValues();
		  
		 
		 // setMandatoryReadOnlyTrueFalse(false);
	  },
	  setFormFieldsAfterUpdate:function()
	  {
				  
		  $('.validatebox-text').removeClass('validatebox-invalid');
		  //$('.validatebox-text').removeClass('validatebox-invalid');
		  //$('[name="strEmployeeUpdateFlag"]')[0].checked=false;
		  //$('[name="strIsExistingEmployee"]')[0].checked=false;
		  $('[name="copyFullNameInShortName"]')[0].checked=false;
		  $('[name="intNatureOfJobId"]')[0].value="-1";
		  $('[name="intAppellationCode1"]')[0].value="-1";
		  $('[name="intAppellationCode2"]')[0].value="-1";
		  $('[name="strEmployeeFullName"]')[0].value="";
		  $('[name="strEmployeeFullRegionalLangName"]')[0].value="";
		  $('[name="strEmployeeShortName"]')[0].value="";
		  $('[name="strEmployeeShortRegionalLangName"]')[0].value="";
		  $('[name="intSuffixCode"]')[0].value="-1";
		 // $('[name="dtEmployeeDOB"]')[0].value="";
		  $('[name="strGenderCode"]')[0].value="-1";
		  $('[name="intNationalityId"]')[0].value=$('[name="defaultintNationalityId"]')[0].value;
		  $('[name="intDepartmentCode"]')[0].value="-1";
		  $('[name="intDesignationCode"]')[0].value="-1";
		  //$('[name="strOldEmployeeNumber"]')[0].value="";
		  $('[name="strEmployeeFinalStatus"]')[0].value=$('[name="defaultstrEmployeeFinalStatus"]')[0].value;
		  //$('[name="strLastEmploymentType"]')[0].value="-1";
		  // setMandatoryReadOnlyTrueFalse(false);
		  
		  $('[name="intMobileNumber"]')[0].value="";
		  $('[name="strPersonalEmailId"]')[0].value="";
		  $('[name="strPANNumberAvailableFlag"]')[0].checked=false;
		  $('[name="strPANNumber"]')[0].value="";
		  
	  }
	 
	 
	  
	};


//$("#intNatureOfJobId").bind("change",empregistration.onchange_natureOfJobIdChanged);
//$("#intNatureOfJobId").bind("change",empregistration.natureOfJobIdChanged);
//$("#strEmpOfficeCodeId").bind("change",empPosting.onchange_EmpOffice);
//$('#intNatureOfJobId').change(natureOfJobIdChanged);
  //$('#intNatureOfJobId').bind("change", natureOfJobIdChanged);
   /* $('#intPSId').change(psIDChanged);
    $('#strPayInPB').change(payInPBChanged);*/

/*
$('#cancel55Id').click(function(e){
	
	$("#EmployeeRegistration").attr('action',"/HISPis/pis/pr/transactions/EmployeeRegistration.action?aa=12&dv=34");
	$('#EmployeeRegistration').submit();
	
});
*/
    
 function natureOfJobIdChanged() {
	  
	 var objNatureJob = document.getElementById("intNatureOfJobId");
	 var objDept = document.getElementById("intDepartmentCode");
	 var natureOfJobId = objNatureJob.options[objNatureJob.selectedIndex].text;
	   // alert("nature of job->"+natureOfJobId);
	 
      // var natureOfJobId=$('[name="intNatureOfJobId"]')[0].value;	
           
     	var action 	= "/HISRegistration/pis/masters/getDeptDesigFinalStatusEmployeeRegistration.action?"+"&natureOfJobId="+natureOfJobId;
    	
    	$("#intDepartmentCode").html("<option value='-1'>Select Value</option>");
    	$("#intDesignationCode").html("<option value='-1'>Select Value</option>");
    	
    	//alert(action);
    	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
    		{
    				var returnDocument=data;
    				
    				var rootNode=returnDocument.getElementsByTagName("root")[0];
    				for(var i=0;i<rootNode.childNodes.length;i++ )
    				{
    					var elementNode=rootNode.childNodes[i];
    					var elementName=elementNode.nodeName;
    					
    					empregistration.processGeneralNode(elementName,elementNode);
    					
    					if(objNatureJob.options[objNatureJob.selectedIndex].text=="Student")
    					document.getElementsByName('intDepartmentCode')[0].options[1].selected = true;
    						
    				}
    				 
    				
    		},error: function(errorMsg,textstatus,errorthrown) {
                alert('onchange_natureOfJobIdChanged '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
               
            }});
    	
    	     //empregistration.natureOfJobIdChanged_Desig();   
		
    	
 }
 
/*function natureOfJobIdChanged_Desig() {
	 
	 var objNatureJob =document.getElementsByName("intNatureOfJobId")[0];
     var natureJobCode = objNatureJob.options[objNatureJob.selectedIndex].value;
	 
        var natureOfJobId=$('[name="intNatureOfJobId"]')[0].value;	
        alert("natureOfJobId"+natureOfJobId);
    	var action 	= "/HISPis/pis/pr/transactions/getDesigEmployeeRegistration.action?"+"&natureOfJobId="+natureOfJobId;
    	
    	
    	$("#intDesignationCode").html("<option value='-1'>Select Value</option>");
    	
    	//alert(action);
    	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
    		{
    				var returnDocument=data;
    				var rootNode=returnDocument.getElementsByTagName("root")[0];
    				for(var i=0;i<rootNode.childNodes.length;i++ )
    				{
    					var elementNode=rootNode.childNodes[i];
    					var elementName=elementNode.nodeName;
    					
    					empregistration.processGeneralNode(elementName,elementNode);
    				}
    				
    		},error: function(errorMsg,textstatus,errorthrown) {
                alert('natureOfJobIdChanged_Desig '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
             
				
            }});
    	
    	 empregistration.natureOfJobIdChanged_finalStatus();
		
    	
 }

function natureOfJobIdChanged_finalStatus() {
	 
	 var objNatureJob =document.getElementsByName("intNatureOfJobId")[0];
    var natureJobCode = objNatureJob.options[objNatureJob.selectedIndex].value;
	 
       var natureOfJobId=$('[name="intNatureOfJobId"]')[0].value;	
       //alert("natureOfJobId"+natureOfJobId);
   	var action 	= "/HISPis/pis/pr/transactions/getfinalStatusEmployeeRegistration.action?"+"&natureOfJobId="+natureOfJobId;
   	
   	
   	$("#strEmployeeFinalStatus").html("<option value='-1'>Select Value</option>");
   	
   	//alert(action);
   	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
   		{
   				var returnDocument=data;
   				var rootNode=returnDocument.getElementsByTagName("root")[0];
   				for(var i=0;i<rootNode.childNodes.length;i++ )
   				{
   					var elementNode=rootNode.childNodes[i];
   					var elementName=elementNode.nodeName;
   					
   					empregistration.processGeneralNode(elementName,elementNode);
   				}
   				
   				
   		},error: function(errorMsg,textstatus,errorthrown) {
               alert('natureOfJobIdChanged_Desig '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
               
           }});
   	
		
   	
}*/
     

//On Click of Cancel
$('#cancelId').click(function(e){
	
	var local_flag_Guest_Or_User=$('[name="flagGuestOrUser"]')[0].value;
	if(local_flag_Guest_Or_User=='user')
	{
		goToDefaultPage('1');
		e.preventDefault();
	}
	else if(local_flag_Guest_Or_User=='guest')
	{
		empregistration.setFormFieldsAfterSave();
		$("#nonprintableDiv5").show();  // checked
		$("#nonprintableDiv5").fadeIn(1500);
		$("#nonprintableDiv6").hide();
		$("#nonprintableDiv3").hide();
		$("#nonprintableDiv2").hide();
		$("#divPrintId").hide();
		$("#divEmpDtlPreviewId").hide();
		
		$("#regSlipGuestId").hide();
		
		$("#guestLoginErrorMsgId").html("");
		$('[name="strGestModifyEmployeeNumber"]')[0].value="";
	}
});

//On Click of Cancel
$('#cancelGuestModifyId').click(function(e){
	
	empregistration.setFormFieldsAfterSave();
	$("#nonprintableDiv5").show();  // checked
	$("#nonprintableDiv5").fadeIn(1500);
	$("#nonprintableDiv6").hide();
	$("#nonprintableDiv7").hide();
	$("#nonprintableDiv3").hide();
	$("#nonprintableDiv2").hide();
	$("#divPrintId").hide();
	$("#divEmpDtlPreviewId").hide();
	
	$("#guestLoginErrorMsgId").html("&nbsp;");
	$('[name="strGestModifyEmployeeNumber"]')[0].value="";
	$('[name="dtGestModifyEmployeeDoB"]')[0].value="";
	$("#guestModifyEmpDOBId").datepicker({changeMonth: true, changeYear: true, maxDate: '-d', dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
	
	$('[name="strGestModifyEmployeeNumber"]').validatebox({required: false});
	$("#guestModifyEmpDOBId").validatebox({required: true});
	
	
});

$('#clearId').click(function(e){
	
	empregistration.setFormFieldsAfterSave();
	clearEssentialDivs();
	
	//$("#EmployeeRegistration").attr('action',"/HISPis/pis/pr/transactions/EmployeeRegistration.action");
	//document.forms[0].action = "saveVerificationDoc.action";
	//$('#EmployeeRegistration').submit();
	//e.preventDefault();
}); 

//On Click of Cancel
$('#cancelUpdateId').click(function(e){
	
	var local_flag_Guest_Or_User=$('[name="flagGuestOrUser"]')[0].value;
	if(local_flag_Guest_Or_User=='user')
	{
		goToDefaultPage('1');
		e.preventDefault();
	}
	else if(local_flag_Guest_Or_User=='guest')
	{
		empregistration.setFormFieldsAfterSave();
		$("#nonprintableDiv5").show();  // checked
		$("#nonprintableDiv5").fadeIn(1500);
		$("#nonprintableDiv6").hide();
		$("#nonprintableDiv3").hide();
		$("#nonprintableDiv2").hide();
		$("#divPrintId").hide();
		$("#divEmpDtlPreviewId").hide();
		
		$("#regSlipGuestId").hide();
		
		$("#guestLoginErrorMsgId").html("");
		$('[name="strGestModifyEmployeeNumber"]')[0].value="";
	}

});

$('#clearUpdateId').click(function(e){
	
	empregistration.setFormFieldsAfterUpdate();
	clearEssentialDivs();
	
	//$("#EmployeeRegistration").attr('action',"/HISPis/pis/pr/transactions/EmployeeRegistration.action");
	//document.forms[0].action = "saveVerificationDoc.action";
	//$('#EmployeeRegistration').submit();
	//e.preventDefault();
}); 

//Submit form if valid for Addition
$('#submitId').click(function(e){
	//futdate();
	
	// Before Proceeding for Save- Remove aa extra spaces from following fields
	$('[name="strEmployeeFullName"]')[0].value=$('[name="strEmployeeFullName"]')[0].value.trim().replace( /\s\s+/g, ' ' );
//	$('[name="strEmployeeFullRegionalLangName"]')[0].value=$('[name="strEmployeeFullRegionalLangName"]')[0].value.trim().replace( /\s\s+/g, ' ' );
	$('[name="strEmployeeShortName"]')[0].value=$('[name="strEmployeeShortName"]')[0].value.trim().replace( /\s\s+/g, ' ' );
//	$('[name="strEmployeeShortRegionalLangName"]')[0].value=$('[name="strEmployeeShortRegionalLangName"]')[0].value.trim().replace( /\s\s+/g, ' ' );
	
	$('.validatebox-text').removeClass('validatebox-invalid');
	if($('#EmployeeRegistration').form('validate')){
		//alert("Validation True");
		//empregistration.saveEmployeeDtl();
		clearEssentialDivs();
		 
		 if($('[name="strPANNumberAvailableFlag"]')[0].checked)
			 if(!validatePanCardNumber())
				 return false;
		 
		 if(!validateBirthDate())
			 return false;
		 
		 	 
		 createEmployeeDetailsPreview();
		
	}else{
		
		clearEssentialDivs();
		//alert("Validation false");
		//$('#NewRegistration').form('validate').resetForm();
		//$("#NewRegistration").data('validator').resetForm();
		//$('.validation-summary-errors').addClass('validation-summary-valid');
        
		return false;
	}
		//$('#NewRegistration').form('submit');
	e.preventDefault();
	
});

//Submit form if valid for Updation
$('#updateId').click(function(e){
	//futdate();
	$('.validatebox-text').removeClass('validatebox-invalid');
	if($('#EmployeeRegistration').form('validate')){
		//alert("Validation True");
		
		if(checksForUpdaiton())
		{
			//alert("inside true");
			if($('[name="strPANNumberAvailableFlag"]')[0].checked)
				 if(!validatePanCardNumber())
					 return false;
			
			if(!validateBirthDate())
				 return false;
			
			empregistration.updateEmployeeDtl();
		}
		else
		{
			//alert("inside false");
			//alert("There is no change in details for updation!");
			showAlert ("1", "There is no change in details for Updation");
		}
		
	}else{
		//alert("Validation false");
		//$('#NewRegistration').form('validate').resetForm();
		//$("#NewRegistration").data('validator').resetForm();
		//$('.validation-summary-errors').addClass('validation-summary-valid');
        
		return false;
	}
		//$('#NewRegistration').form('submit');
	e.preventDefault();
});

function checksForUpdaiton()
{
	//alert($("#isExistingEmployee").is(':checked'));
	
	if($('[name="intNatureOfJobId"]')[0].value!=$('[name="updateCheckintNatureOfJobId"]')[0].value)
	{
		//alert("1");
		return true;
	}
	else if($('[name="intAppellationCode1"]')[0].value!=$('[name="updateCheckintAppellationCode1"]')[0].value)
	{
		//alert("2");
		return true;
	}
	else if($('[name="intAppellationCode2"]')[0].value!=$('[name="updateCheckintAppellationCode2"]')[0].value && $('[name="intAppellationCode2"]')[0].value!='-1')
	{
		//alert("3");
		return true;
	}
	else if($('[name="strEmployeeFullName"]')[0].value!=$('[name="updateCheckstrEmployeeFullName"]')[0].value)
	{
		//alert("4");
		return true;
	}
	/*else if($('[name="strEmployeeFullRegionalLangName"]')[0].value!=$('[name="updateCheckstrEmployeeFullRegionalLangName"]')[0].value)
	{
		//alert("5");
		return true;
	}*/
	else if($('[name="strEmployeeShortName"]')[0].value!=$('[name="updateCheckstrEmployeeShortName"]')[0].value)
	{
		//alert("6");
		return true;
	}
	/*else if($('[name="strEmployeeShortRegionalLangName"]')[0].value!=$('[name="updateCheckstrEmployeeShortRegionalLangName"]')[0].value)
	{
		//alert("7");
		return true;
	}*/
	/*else if($('[name="intSuffixCode"]')[0].value!=$('[name="updateCheckintSuffixCode"]')[0].value && $('[name="intSuffixCode"]')[0].value!='-1')
	{
		//alert("8");
		return true;
	}*/
	else if($('[name="strGenderCode"]')[0].value!=$('[name="updateCheckstrGenderCode"]')[0].value)
	{
		//alert("9");
		return true;
	}
	else if($('[name="intNationalityId"]')[0].value!=$('[name="updateCheckintNationalityId"]')[0].value)
	{
		//alert("10");
		return true;
	}
	else if($('[name="intDepartmentCode"]')[0].value!=$('[name="updateCheckintDepartmentCode"]')[0].value)
	{
		//alert("11");
		return true;
	}
	else if($('[name="intDesignationCode"]')[0].value!=$('[name="updateCheckintDesignationCode"]')[0].value)
	{
		//alert("12");
		return true;
	}
	else if($('[name="strEmployeeFinalStatus"]')[0].value!=$('[name="updateCheckstrEmployeeFinalStatus"]')[0].value)
	{
		//alert("13");
		return true;
	}
	else if($('[name="strEmployeeFinalStatus"]')[0].value!=$('[name="updateCheckstrEmployeeFinalStatus"]')[0].value)
	{
		//alert("13");
		return true;
	}
	else if($('[name="strEmployeeFinalStatus"]')[0].value!=$('[name="updateCheckstrEmployeeFinalStatus"]')[0].value)
	{
		//alert("13");
		return true;
	}
	
	if($("#isExistingEmployee").is(':checked'))
	{
		
	}
	else
	{
		if($('[name="strLastEmploymentType"]')[0].value!=$('[name="updateCheckstrLastEmploymentType"]')[0].value)
		{
			//alert("14");
			return true;
		}
		else if($('[name="strOldEmployeeNumber"]')[0].value!=$('[name="updateCheckstrOldEmployeeNumber"]')[0].value)
		{
			//alert("13");
			return true;
		}
	}
	
	if($('[name="intMobileNumber"]')[0].value!=$('[name="updateCheckintMobileNumber"]')[0].value)
	{
		//alert("14");
		return true;
	}
	else if($('[name="strPersonalEmailId"]')[0].value!=$('[name="updateCheckstrPersonalEmailId"]')[0].value)
	{
		//alert("15");
		return true;
	}
	else if($('[name="strPANNumber"]')[0].value!=$('[name="updateCheckstrPANNumber"]')[0].value)
	{
		//alert("16");
		return true;
	}
	
	return false;
}

//Submit form if valid for Deletion
$('#deleteId').click(function(e){
	//futdate();
	empregistration.deleteEmployeeDtl();
	//$('#NewRegistration').form('submit');
	e.preventDefault();
});

$("#copyFullNameInShortNameFlagId").click(function(){
	if(this.checked==false)
	{
		//$('[name="strEmployeeShortName"]')[0].value="";
		//$('[name="strEmployeeShortRegionalLangName"]')[0].value="";
		prepareShortName();
		
	}
	else
	{
		$('[name="strEmployeeShortName"]')[0].value=$('[name="strEmployeeFullName"]')[0].value;
		//multilingualConversion(document.getElementById('strEmployeeShortName'),document.getElementById('strEmployeeShortRegionalLangName'));
	}
});

function prepareShortName()
{
	//alert("inside prepareShortName");
	if($("#copyFullNameInShortNameFlagId").is(':checked'))
	{
		$('[name="strEmployeeFullName"]')[0].value=$('[name="strEmployeeFullName"]')[0].value.replace( /\s\s+/g, ' ' );
		$('[name="strEmployeeShortName"]')[0].value=$('[name="strEmployeeFullName"]')[0].value;
		//multilingualConversion(document.getElementById('strEmployeeShortName'),document.getElementById('strEmployeeShortRegionalLangName'));
	}
	else
	{
		var preparedShortName="";
		$('[name="strEmployeeFullName"]')[0].value=$('[name="strEmployeeFullName"]')[0].value.replace( /\s\s+/g, ' ' );
		var res = $('[name="strEmployeeFullName"]')[0].value.trim().split(" ");
		//alert(res.length);
		for(var i=0;i<(res.length-1);i++)
		{
			preparedShortName+=res[i].substring(0,1)+" "; 
		}
		//alert("preparedShortName="+preparedShortName);
		$('[name="strEmployeeShortName"]')[0].value=preparedShortName+res[(res.length-1)];
		//multilingualConversion(document.getElementById('strEmployeeShortName'),document.getElementById('strEmployeeShortRegionalLangName'));
	}
}

function validateOldEmployeeNumber()
{
	$('[name="strOldEmployeeNumber"]')[0].value=$('[name="strOldEmployeeNumber"]')[0].value.trim().replace(' ','');
}

//Added by Ashwini Mishra on 19-06-2015
function clearEssentialDivs()
{
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");	
	$("#divPrintId1").html("");		
	$("#divPrintId2").html("");		
}


//Modified by Ashwini Mishra on 22-0-2015 
$("#employeeUpdateFlagId").click(function(){
	var employeeUpdateFlag = this.value;
	if(this.checked==false){
		empregistration.setFormFieldsAfterSave();
		clearEssentialDivs();
		
		$("#saveFooterId").show();  // Unchecked
		$("#updateFooterId").hide();
		
		//if(("#isIdRequired").val()=="")
			//$("#imgCatCardId").show();
		return;
	}
	else
	{
		clearEssentialDivs();
		
		$("#updateFooterId").show();  // checked
		$("#saveFooterId").hide();
	}
	
	//var patPrimarCatId= $("#patPrimaryCatCodeId").val();
	var action 	= "/HISRegistration/pis/masters/openEmpPopupEmployeeRegistration.action?employeeUpdateFlag="+employeeUpdateFlag;
	
	openURLInPopup(action,'800','300');
	var handler="";
	handler = function() {
		clearEssentialDivs();		
		cancelPopup();
	};
	$( ".modalCloseImg" ).bind( "click", handler);	
	//openURLInPopupWithCallbackFn(action,'800','300','cancelPopup');
});

//Added by Ashwini Mishra on 22-06-2015
function cancelPopup(){
	document.getElementsByName("strEmployeeUpdateFlag")[0].checked=false;
	setSaveOrUpdateFooterVisibility();
	//closeModal();
}


function isExistingEmployeeCheck(calFrom)
{
	
	if($("#isExistingEmployee").is(':checked'))
	{
		//alert("checked");
		$("#divOldEmpMandatory").show();  // checked
		$("#divOldEmpNoMandatory").hide();
		
		$("#divLastEmpTypeNoMandatory").show();  // checked
		$("#divLastEmpTypeMandatory").hide();
		
		//$("#strOldEmployeeNumber").numberbox({required: false});
		$('[name="strOldEmployeeNumber"]').validatebox({required: true, noSpace: true, validType: ['numeric','minLength[5]','maxLength[5]']});
		//$('[name="strOldEmployeeNumber"]').attr("disabled", false);
		$('[name="strOldEmployeeNumber"]').attr("readonly", false);
		
		if(calFrom!="enableDisableFormFields")
			$('[name="strLastEmploymentType"]')[0].value='-1';
			
		$('[name="strLastEmploymentType"]').attr('disabled','disabled');
		
		$('[name="strLastEmploymentType"]').validatebox({required: false, validType: ['selectCombo[-1]'] });
	}
	else
	{
		//alert("Unchecked");
		$('[name="strLastEmploymentType"]').removeAttr('disabled');		
		$('[name="strLastEmploymentType"]').validatebox({required: true, validType: ['selectCombo[-1]'] });
		
		//alert($('[name="strLastEmploymentType"]')[0].value);
		if($('[name="strLastEmploymentType"]')[0].value=='1')
		{
			$('[name="strOldEmployeeNumber"]').validatebox({required: false, noSpace: true, validType: ['numeric','minLength[5]']});
			//$('[name="strOldEmployeeNumber"]').attr("disabled", true);
			if(calFrom!="enableDisableFormFields")
				$('[name="strOldEmployeeNumber"]').val('');
			$('[name="strOldEmployeeNumber"]').attr("readonly", true);
			
			$("#divOldEmpNoMandatory").show();  // Unchecked
			$("#divOldEmpMandatory").hide();
			
			$("#divLastEmpTypeNoMandatory").hide();  // checked
			$("#divLastEmpTypeMandatory").show();
		}
		else if($('[name="strLastEmploymentType"]')[0].value=='2' || $('[name="strLastEmploymentType"]')[0].value=='3')
		{
			$('[name="strOldEmployeeNumber"]').validatebox({required: true, noSpace: true, validType: ['numeric','minLength[12]','maxLength[12]']});
			//$('[name="strOldEmployeeNumber"]').attr("disabled", true);
			//if(calFrom!="enableDisableFormFields")
				//$('[name="strOldEmployeeNumber"]').val('');
			$('[name="strOldEmployeeNumber"]').attr("readonly", false);
			
			$("#divOldEmpNoMandatory").hide();  // Unchecked
			$("#divOldEmpMandatory").show();
			
			$("#divLastEmpTypeNoMandatory").hide();  // checked
			$("#divLastEmpTypeMandatory").show();
			
		}
		else
		{
			if(calFrom!="enableDisableFormFields")
				$('[name="strOldEmployeeNumber"]').val('');
			$('[name="strOldEmployeeNumber"]').validatebox({required: false, noSpace: true, validType: ['numeric','minLength[5]']});
			$('[name="strOldEmployeeNumber"]').attr("readonly", true);
			
			$("#divOldEmpNoMandatory").show();  // Unchecked
			$("#divOldEmpMandatory").hide();
			
			$("#divLastEmpTypeNoMandatory").hide();  // checked
			$("#divLastEmpTypeMandatory").show();
		}
		//$("#strOldEmployeeNumber").numberbox({required: true});
		
	}
	
}

function isPANNumberAvailableCheck()
{
	if($('[name="strPANNumberAvailableFlag"]')[0].checked)
	{
		$('[name="strPANNumber"]')[0].value="";
		$('[name="strPANNumber"]').removeAttr('readonly');
		$('[name="strPANNumber"]').validatebox({required: true, validType: ['alphaNumeric','minLength[10]']});
		
		$("#divPANNumberMandatory").show();  // Unchecked
		$("#divPANNumberNoMandatory").hide();
	}
	else
	{
		$('[name="strPANNumber"]')[0].value="NA";
		$('[name="strPANNumber"]').attr('readonly','readonly');
		$('[name="strPANNumber"]').validatebox({required: false, validType: ['alphaNumeric','minLength[2]']});
		
		$("#divPANNumberMandatory").hide();  // Unchecked
		$("#divPANNumberNoMandatory").show();
	}
}

$("#strPANNumberAvailableFlagId").click(function(){
	
	clearEssentialDivs();	
	isPANNumberAvailableCheck();	
	
});


function validatePanCardNumber() 
{
	/*
	 * Indian PAN is as follows: AAAAA9999A:
	 * Where First five characters are letters, next 4 numerals, last character letter
	 * One rule there the fourth character is choosen from a list Alphabates as bellows.
	 * 
	 * C - Company
	 * P - Person
	 * H - HUF(Hindu Undivided Family)
	 * F - Firm
	 * A - Association of Persons (AOP)
	 * T - AOP (Trust)
	 * B - Body of Individuals (BOI)
	 * L - Local Authority
	 * J - Artificial Juridical Person
	 * G - Govt
	 */
	
	var value = $('[name="strPANNumber"]')[0].value;
	var regex1 = /^[A-Z]{3}(p|P|c|C|h|H|f|F|a|A|t|T|b|B|l|L|j|J|g|G)[A-Z]{1}\d{4}[A-Z]{1}$/;
    if (!regex1.test(value) || value.length != 10) 
    {
       // alert("Please enter valid PAN Card Number!\n\nIndian PAN is as follows: AAAAA9999A\n\nWhere first five characters are letters, next 4 are numerals and last character is letter.\n\nOne rule there - the fourth character is choosen from a list alphabates as below - \n\nC - Company \nP - Person \nH - HUF(Hindu Undivided Family) \nF - Firm \nA - Association of Persons (AOP) \nT - AOP (Trust) \nB - Body of Individuals (BOI) \nL - Local Authority \nJ - Artificial Juridical Person \nG - Government");
    	showAlert ("1", "Please enter valid PAN Card Number!<BR><BR>Indian PAN is as follows: AAAAA9999A<BR><BR>Where first five characters are letters, next 4 are numerals and last character is letter.<BR><BR>One rule there - the fourth character is choosen from a list alphabates as below - <BR><BR>C - Company <BR>P - Person <BR>H - HUF(Hindu Undivided Family) <BR>F - Firm <BR>A - Association of Persons (AOP) <BR>T - AOP (Trust) <BR>B - Body of Individuals (BOI) <BR>L - Local Authority <BR>J - Artificial Juridical Person <BR>G - Government");
    	return false;
    }
    return true;
}

// PAN Number Field Value should be in Upper case
$('#strPANNumber').keyup(function() {
    $(this).val($(this).val().toUpperCase());
});

function setSaveOrUpdateFooterVisibility()
{
	if($("#employeeUpdateFlagId").is(':checked'))
	{
		//alert("checked");
		$("#updateFooterId").show();  // checked
		$("#saveFooterId").hide();
	}
	else
	{
		//alert("Unchecked");
		$("#saveFooterId").show();  // Unchecked
		$("#updateFooterId").hide();
	}
}


function createEmployeeDetailsPreview()
{
	
	// Variable Declaration
	var prev_IsExistingEmployee="";
	var prev_LastEmploymentType="";
	var prev_NatureOfJob="";
	var prev_Appellation1="";
	var prev_Appellation2="";
	var prev_EmployeeFullName="";
	var prev_EmployeeFullNameInRegLan="";
	var prev_EmployeeShortName="";
	var prev_EmployeeShortNameInRegLan="";
	var prev_Suffix="";
	var prev_DateOfBirth="";
	var prev_Gender="";
	var prev_Nationality="";
	var prev_Department="";
	var prev_Designation="";
	var prev_OldEmployeNo="";
	var prev_EmployeeFinalStatus="";
	var prev_MobileNumber="";
	var prev_EmailId="";
	var prev_PANNumber="";
	
	
	// Putting Values in Variables
	if($("#isExistingEmployee").is(':checked'))
	{
		prev_IsExistingEmployee="Yes";
		prev_LastEmploymentType="NA";
	}
	else
	{
		prev_IsExistingEmployee="No";
		prev_LastEmploymentType=$('[name="strLastEmploymentType"]').find('option:selected').text();
	}
	
	
	if($('[name="intNatureOfJobId"]').find('option:selected').val()!='-1')
	{
		prev_NatureOfJob=$('[name="intNatureOfJobId"]').find('option:selected').text();
	}
	else
	{
		prev_NatureOfJob='NA';
	}
	
	prev_Appellation1=$('[name="intAppellationCode1"]').find('option:selected').text();
	
	if($('[name="intAppellationCode2"]').find('option:selected').val()!='-1')
	{
		prev_Appellation2=$('[name="intAppellationCode2"]').find('option:selected').text();
	}
	else
	{
		prev_Appellation2='NA';
	}
	
	prev_EmployeeFullName=$('[name="strEmployeeFullName"]')[0].value;
	//prev_EmployeeFullNameInRegLan=$('[name="strEmployeeFullRegionalLangName"]')[0].value;
	prev_EmployeeShortName=$('[name="strEmployeeShortName"]')[0].value;
	//prev_EmployeeShortNameInRegLan=$('[name="strEmployeeShortRegionalLangName"]')[0].value;
	
	if($('[name="intSuffixCode"]').find('option:selected').val()!='-1')
	{
		prev_Suffix=$('[name="intSuffixCode"]').find('option:selected').text();
	}
	else
	{
		prev_Suffix='NA';
	}
	
	prev_DateOfBirth=$('[name="dtEmployeeDOB"]')[0].value;
	
	prev_Gender=$('[name="strGenderCode"]').find('option:selected').text();
	
	prev_Nationality=$('[name="intNationalityId"]').find('option:selected').text();
	
	prev_Department=$('[name="intDepartmentCode"]').find('option:selected').text();
	
	prev_Designation=$('[name="intDesignationCode"]').find('option:selected').text();
	
	if($('[name="strOldEmployeeNumber"]')[0].value!='')
	{
		prev_OldEmployeNo=$('[name="strOldEmployeeNumber"]')[0].value;
	}
	else
	{
		prev_OldEmployeNo="NA";
	}
	
	prev_EmployeeFinalStatus=$('[name="strEmployeeFinalStatus"]').find('option:selected').text();
	
	if($('[name="intMobileNumber"]')[0].value!='')
	{
		prev_MobileNumber=$('[name="intMobileNumber"]')[0].value;
	}
	else
	{
		prev_MobileNumber="NA";
	}
	
	if($('[name="strPersonalEmailId"]')[0].value!='')
	{
		prev_EmailId=$('[name="strPersonalEmailId"]')[0].value;
	}
	else
	{
		prev_EmailId="NA";
	}
	
	if($('[name="strPANNumber"]')[0].value!='')
	{
		prev_PANNumber=$('[name="strPANNumber"]')[0].value;
	}
	else
	{
		prev_PANNumber="NA";
	}
	
	//By Rajkumar on Oct'18 for 27121
	var fullNameRow="";
	var shortNameRow="";
	if(prev_EmployeeFullNameInRegLan!=null && prev_EmployeeFullNameInRegLan!=""){
		
		fullNameRow="<div class='div-table-col width24 alignLeft'>"+prev_EmployeeFullName+" / "+prev_EmployeeFullNameInRegLan+"</div>";
		shortNameRow="<div class='div-table-col width24 alignLeft'>"+prev_EmployeeShortName+" / "+prev_EmployeeShortNameInRegLan+"</div>"

	}

	else{
		fullNameRow="<div class='div-table-col width24 alignLeft'>"+prev_EmployeeFullName+"</div>";
		shortNameRow="<div class='div-table-col width24 alignLeft'>"+prev_EmployeeShortName+"</div>"
	}

	// Creating Division
	var outsideCircleOpen = "<br><br><div class='div-table-simple rounded width70' style='background-color: white;'><br>";
	
	var outsideCircleClose = "</div><br><br>";
	
	var empHeaderRow =  "<div class='div-table-row' id='rptPrintImageDivId'>" +
							"<div class='div-table-col label' style='width: 100%; position:absolute; right:18px; top:14px;'>" +
							"<span onclick='setupForConfirmPreviewDetails();'><img src='/HIS/hisglobal/images/nonclinical/nc_right.png' width='20' height='20' style='vertical-align: middle;cursor: pointer;' title='Confirm' /> Confirm</span>" +
							"&nbsp;&nbsp;&nbsp;"+
							"<span onclick='setupForEditPreviewDetails();'><img src='/HIS/hisglobal/images/nonclinical/nc_edit.png' width='20' height='20' style='vertical-align: middle;cursor: pointer;' title='Edit' /> Edit</span>" +
							"</div>"+
						"</div>"+ 
						"<div class='div-table-row listHeader width100'>"+
							"<div class='div-table-col width100 alignLeft'>&nbsp;&nbsp;<B>Please verify the details.</B></div>"+
						 "</div>"+
						 "<div class='div-table-button'>"+
						 	"<div class='div-table-row footerBar'>"+
						 		"<div class='div-table-col'> </div>"+
						 	"</div>"+
							
						 "</div>";
		
	var empDtlRow =	"<div class='div-table-row width100'>"+
						"<div class='div-table-col width20 alignRight'>Is Existing Employee ?</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'><b><i>"+prev_IsExistingEmployee+"</i></b></div>"+
						"<div class='div-table-col width24 alignRight'>Last Employment Type</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_LastEmploymentType+"</div>"+
					"</div>"+
					"<div class='div-table-row width100'>"+
						"<div class='div-table-col width20 alignRight'>Nature of Job</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_NatureOfJob+"</div>"+
						"<div class='div-table-col width24 alignRight'></div>"+
						"<div class='div-table-col width4 alignCenter'></div>"+
						"<div class='div-table-col width24 alignLeft'></div>"+
						
						
						
						
						
					"</div>"+
					"<div class='div-table-row width100'>"+
						"<div class='div-table-col width20 alignRight'>Appellation 1</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_Appellation1+"</div>"+
						"<div class='div-table-col width24 alignRight'>Appellation 2</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_Appellation2+"</div>"+
					"</div>"+
					"<div class='div-table-row width100'>"+	
						"<div class='div-table-col width20 alignRight'>Employee Full Name</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						fullNameRow+
						"<div class='div-table-col width24 alignRight'>Employee Short Name</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						shortNameRow+
						"</div>"+
					/*"<div class='div-table-row width100'>"+	
						"<div class='div-table-col width20 alignRight'>Employee Short Name</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_EmployeeShortName+" / "+prev_EmployeeShortNameInRegLan+"</div>"+
						"<div class='div-table-col width24 alignRight'>Suffix</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_Suffix+"</div>"+
					"</div>"+*/
					"<div class='div-table-row width100'>"+	
						"<div class='div-table-col width20 alignRight'>Date of Birth</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'><b><i>"+prev_DateOfBirth+"</i></b></div>"+
						"<div class='div-table-col width24 alignRight'>Gender</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_Gender+"</div>"+
					"</div>"+
					"<div class='div-table-row width100'>"+	
						"<div class='div-table-col width20 alignRight'>Nationality</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_Nationality+"</div>"+
						"<div class='div-table-col width24 alignRight'>Department</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_Department+"</div>"+
					"</div>"+
					"<div class='div-table-row width100'>"+	
						"<div class='div-table-col width20 alignRight'>Designation</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_Designation+"</div>"+
						"<div class='div-table-col width24 alignRight'>Old Employment Reference No.</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'><b><i>"+prev_OldEmployeNo+"</i></b></div>"+
					"</div>"+
					"<div class='div-table-row width100'>"+	
						"<div class='div-table-col width20 alignRight'>Employee Final Status</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_EmployeeFinalStatus+"</div>"+
						"<div class='div-table-col width24 alignRight'>Mobile No.</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_MobileNumber+"</div>"+
					"</div>"+
					"<div class='div-table-row width100'>"+	
						"<div class='div-table-col width20 alignRight'>Email Id</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_EmailId+"</div>"+
						"<div class='div-table-col width24 alignRight'>PAN Card No.</div>"+
						"<div class='div-table-col width4 alignCenter'>:</div>"+
						"<div class='div-table-col width24 alignLeft'>"+prev_PANNumber+"</div>"+
					"</div>";
	
	var empFooterRow =	"<div class='div-table-row width100'>"+	
							"<div class='div-table-col width100 alignRight'>&nbsp;</div>"+
						"</div>";
	
	
	$("#divEmpDtlPreviewId").html(outsideCircleOpen+empHeaderRow+empDtlRow+empFooterRow+outsideCircleClose);
	//$("#divEmpDtlPreviewId").html(outsideCircleOpen+empDtlRow+outsideCircleClose);
	
	$("#divEmpDtlPreviewId").show();  // checked
	$("#nonprintableDiv1").hide();
	$("#content").hide();
	$("#tabs").hide();
	$("#tabs1").hide();
	$("#divEmpDtlPreviewId").fadeIn(1500); // Show first tab content
}

function initialPage()
{
	//empregistration.setFormFieldsAfterSave();
	
	$("#divEmpDtlPreviewId").hide();  // checked
	//$("#divEmpDtlAfterSaveForPrintId").hide();
	$("#divPrintId").hide();
	$("#nonprintableDiv1").show();
	
	$("#content").show();
	$("#tabs").show();
	$("#tabs1").show();

	$('#submitId').show();

	
	$("#nonprintableDiv3").hide();
	$("#nonprintableDiv2").hide();
}

function printEmployeeRegistrationSlip(){	window.print(); }

function setupForEditPreviewDetails()
{
	$("#divEmpDtlPreviewId").hide();  // checked
	$("#nonprintableDiv1").show();
	$("#content").show();
	$("#tabs").show();
	$("#tabs1").show();
	$("#nonprintableDiv1").fadeIn(1500); // Show first tab content
}

function setupForConfirmPreviewDetails()
{
	//var didConfirm = confirm("Please confirm higlighted fields once again i.e \n\n1. Is Existing Employee ?\n\n2. Date of Birth\n\n3. Old Employment Reference Number.\n\nOnce you confirmed, System generted Employee Number can not be changed.\n\n");
	//if (didConfirm == true) 
	showConfirmDialog("1", "Please Confirm Higlighted Fields Once Again i.e <BR><BR>1. Is Existing Employee ?<BR><BR>2. Date of Birth<BR><BR>3. Old Employment Reference Number.<BR><BR>Once You Confirmed, System Generted Emp. No. can not be changed.<BR><BR>", "", function()
	{
		empregistration.saveEmployeeDtl();
	});
	//return confirm('Are you sure you want to delete this item?');
}

//Modified by Ashwini Mishra on 24-11-2015
function showEmployeeRegistrationPage()
{
	$("#nonprintableDiv5").hide();  // checked
	$("#nonprintableDiv6").show();
	$("#nonprintableDiv6").fadeIn(1500);
	
	$("#regSlipGuestId").hide();
	
	//$('[name="strGestModifyEmployeeNumber"]').validatebox({required: false, noSpace: true, validType: ['numeric','minLength[12]']});
	
	$("#guestModifyEmpDOBId").datepicker({changeMonth: true, changeYear: true, maxDate: '-d', dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
	$("#guestModifyEmpDOBId").validatebox({required: false, validType: ['date[\'dtGestModifyEmployeeDoB\',\'dd-mmm-yyyy\']']});

	$('[name="flagGuestOperationAddOrModify"]')[0].value="Add";
	
}

function showEmployeeRegistrationModifyPage()
{
	$("#nonprintableDiv5").hide();  // checked
	$("#nonprintableDiv7").show();
	$("#nonprintableDiv7").fadeIn(1500);
	
	$("#regSlipGuestId").show();
	
	//$('[name="strGestModifyEmployeeNumber"]').validatebox({required: true, noSpace: true, validType: ['numeric','minLength[12]']});
	
	applyEmpNoCheck("strGestModifyEmployeeNumber", gEmpNoLen, gEmpNoType,'');
	
	$("#guestModifyEmpDOBId").datepicker({changeMonth: true, changeYear: true, maxDate: '-d', dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
	$("#guestModifyEmpDOBId").validatebox({required: true, validType: ['date[\'dtGestModifyEmployeeDoB\',\'dd-mmm-yyyy\']']});
	
	$('[name="flagGuestOperationAddOrModify"]')[0].value="Modify";
	
}

function mainMenu()
{
	goToDefaultPage('1');
	e.preventDefault();
}

//Submit form if valid for Updation
$('#goGuestModifyId').click(function(e){
	removeAllValidations();
	applyEmpNoCheck("strGestModifyEmployeeNumber", gEmpNoLen, gEmpNoType,'');
	$("#guestModifyEmpDOBId").validatebox({required: true, validType: ['date[\'dtGestModifyEmployeeDoB\',\'dd-mmm-yyyy\']']});
	
	
	if($('#EmployeeRegistration').form('validate'))
	{
		funcForClickingGo();
	}
			
});


function funcForClickingGo()
{
	var guestModifyEmpNo = $('[name="strGestModifyEmployeeNumber"]')[0].value;
	var guestModifyDoB = $('[name="dtGestModifyEmployeeDoB"]')[0].value;
	
	var action 	= "/HISRegistration/pis/masters/validateGuestLoginDtlEmployeeRegistration.action?"+
	"&guestModifyEmpNo="+guestModifyEmpNo+
	"&guestModifyDoB="+guestModifyDoB;
	
	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data)
	{
		arrPatGlobalJsonObj=data;
		if(guestModifyEmpNo!="" && guestModifyDoB!="")
		createGuestModifyData(data);	
		else
			showAlert ("1", "Please Enter Both Criterias..!");
		//alert("Please enter both criterias..!");
	
	},error: function(errorMsg,textstatus,errorthrown) 
	{
		alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	}
	});
}

function getEmpDetailsAfterUpdationForGuest()
{
	var guestModifyEmpNo = $('[name="strGestModifyEmployeeNumber"]')[0].value;
	var guestModifyDoB = $('[name="dtGestModifyEmployeeDoB"]')[0].value;
	
	var action 	= "/HISRegistration/pis/masters/validateGuestLoginDtlEmployeeRegistration.action?"+
	"&guestModifyEmpNo="+guestModifyEmpNo+
	"&guestModifyDoB="+guestModifyDoB;
	
	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data)
	{
		arrPatGlobalJsonObj=data;
		if(guestModifyEmpNo!="" && guestModifyDoB!="")
		createGuestModifyData(data);	
		else
			showAlert ("1", "Please Enter Both Criterias..!");
		//alert("Please enter both criterias..!");
	
	},error: function(errorMsg,textstatus,errorthrown) 
	{
		alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	}
	});

}

//Added by Ashwini Mishra on 09-03-2016
function enableDisableFormFields(mode)
{
	$('[name="dtEmployeeDOB"]').attr('readonly','readonly');
	$("#empDOBId").datepicker("destroy");
	
	var panNo = document.getElementsByName("strPANNumber")[0].value;
	var vLastEmploymentType = document.getElementsByName("strLastEmploymentType")[0].value;
	
	if(!(vLastEmploymentType=="" || vLastEmploymentType=="-1"))
	{
		if(vLastEmploymentType=="1")
		{
			document.getElementsByName("strIsExistingEmployee")[0].checked = false;			
		}
		else
		{
			document.getElementsByName("strIsExistingEmployee")[0].checked = false;			
		}
	}
	else
	{
		document.getElementsByName("strIsExistingEmployee")[0].checked = true;		
	}
	
	
	if(panNo=='NA' || panNo=='')
	{
		document.getElementsByName("strPANNumberAvailableFlag")[0].checked = false;
		$('[name="strPANNumber"]').attr('readonly','readonly');
		$('[name="strPANNumber"]').validatebox({required: false, validType: ['alphaNumeric','minLength[2]']});
		$("#divPANNumberNoMandatory").show();  // Unchecked
		$("#divPANNumberMandatory").hide();
	}
	else
	{
		document.getElementsByName("strPANNumberAvailableFlag")[0].checked = true;
		$('[name="strPANNumber"]').removeAttr('readonly');
		$('[name="strPANNumber"]').validatebox({required: true, validType: ['alphaNumeric','minLength[10]']});
		$("#divPANNumberMandatory").show();  // Unchecked
		$("#divPANNumberNoMandatory").hide();
	}
	
	document.getElementsByName("strIsExistingEmployee")[0].disabled = true;
	//document.getElementsByName("strOldEmployeeNumber")[0].readonly = true;

	isExistingEmployeeCheck("enableDisableFormFields");
}

//Modified by Ashwini Mishra on 09-03-2016
function createGuestModifyData(arrStrEmpJsonObj)
{
	
	//alert("createGuestModifyData");
	var validateStatus ="Y";
	var arrEmpJsonObj = arrStrEmpJsonObj;
	if(arrEmpJsonObj!="")
	{
		for (i in arrEmpJsonObj)
		{
			validateStatus = arrEmpJsonObj[i]["validateStatus"];
			//alert("validateStatus = "+validateStatus);
			
			if(validateStatus == 'N')
			{				
				
				document.getElementsByName("strEmployeeNumber")[0].value=arrEmpJsonObj[i]["empNo"];
				document.getElementsByName("intNatureOfJobId")[0].value=arrEmpJsonObj[i]["natureOfJob"];
				document.getElementsByName("intAppellationCode1")[0].value=arrEmpJsonObj[i]["appellation1"];
				document.getElementsByName("intAppellationCode2")[0].value=arrEmpJsonObj[i]["appellation2"];
				document.getElementsByName("strEmployeeFullName")[0].value=arrEmpJsonObj[i]["empName"];
				document.getElementsByName("strEmployeeFullRegionalLangName")[0].value=arrEmpJsonObj[i]["empNameInRegLang"];
				document.getElementsByName("strEmployeeShortName")[0].value=arrEmpJsonObj[i]["empShortName"];
				document.getElementsByName("strEmployeeShortRegionalLangName")[0].value=arrEmpJsonObj[i]["empShortNameInRegLang"];
				document.getElementsByName("intSuffixCode")[0].value=arrEmpJsonObj[i]["suffix"];
				document.getElementsByName("dtEmployeeDOB")[0].value=arrEmpJsonObj[i]["empDoB"];
				document.getElementsByName("strGenderCode")[0].value=arrEmpJsonObj[i]["gender"];
				document.getElementsByName("intNationalityId")[0].value=arrEmpJsonObj[i]["nationality"];
				document.getElementsByName("intDepartmentCode")[0].value=arrEmpJsonObj[i]["department"];
				document.getElementsByName("intDesignationCode")[0].value=arrEmpJsonObj[i]["designation"];
				document.getElementsByName("strOldEmployeeNumber")[0].value=arrEmpJsonObj[i]["oldEmpNo"];
				document.getElementsByName("strEmployeeFinalStatus")[0].value=arrEmpJsonObj[i]["empFinalStatus"];
				document.getElementsByName("strLastEmploymentType")[0].value=arrEmpJsonObj[i]["lastEmploymentType"];
				document.getElementsByName("intMobileNumber")[0].value=arrEmpJsonObj[i]["mobileNo"];
				document.getElementsByName("strPersonalEmailId")[0].value=arrEmpJsonObj[i]["emailId"];
				document.getElementsByName("strPANNumber")[0].value=arrEmpJsonObj[i]["panNo"];
				
				// Setting Fields for Update Check
				document.getElementsByName("updateCheckintNatureOfJobId")[0].value=arrEmpJsonObj[i]["natureOfJob"];
				document.getElementsByName("updateCheckintAppellationCode1")[0].value=arrEmpJsonObj[i]["appellation1"];
				document.getElementsByName("updateCheckintAppellationCode2")[0].value=arrEmpJsonObj[i]["appellation2"];
				document.getElementsByName("updateCheckstrEmployeeFullName")[0].value=arrEmpJsonObj[i]["empName"];
				document.getElementsByName("updateCheckstrEmployeeFullRegionalLangName")[0].value=arrEmpJsonObj[i]["empNameInRegLang"];
				document.getElementsByName("updateCheckstrEmployeeShortName")[0].value=arrEmpJsonObj[i]["empShortName"];
				document.getElementsByName("updateCheckstrEmployeeShortRegionalLangName")[0].value=arrEmpJsonObj[i]["empShortNameInRegLang"];
				document.getElementsByName("updateCheckintSuffixCode")[0].value=arrEmpJsonObj[i]["suffix"];
				document.getElementsByName("updateCheckstrGenderCode")[0].value=arrEmpJsonObj[i]["gender"];
				document.getElementsByName("updateCheckintNationalityId")[0].value=arrEmpJsonObj[i]["nationality"];
				document.getElementsByName("updateCheckintDepartmentCode")[0].value=arrEmpJsonObj[i]["department"];
				document.getElementsByName("updateCheckintDesignationCode")[0].value=arrEmpJsonObj[i]["designation"];
				document.getElementsByName("updateCheckstrOldEmployeeNumber")[0].value=arrEmpJsonObj[i]["oldEmpNo"];
				document.getElementsByName("updateCheckstrEmployeeFinalStatus")[0].value=arrEmpJsonObj[i]["empFinalStatus"];
				document.getElementsByName("updateCheckstrLastEmploymentType")[0].value=arrEmpJsonObj[i]["lastEmploymentType"];
				document.getElementsByName("updateCheckintMobileNumber")[0].value=arrEmpJsonObj[i]["mobileNo"];
				document.getElementsByName("updateCheckstrPersonalEmailId")[0].value=arrEmpJsonObj[i]["emailId"];
				document.getElementsByName("updateCheckstrPANNumber")[0].value=arrEmpJsonObj[i]["panNo"];
				
				// This is for Old Record Compatilbility - if PAN No is blank then it will set NA
				if(arrEmpJsonObj[i]["panNo"]=='')
				{
					document.getElementsByName("strPANNumber")[0].value='NA';
					document.getElementsByName("updateCheckstrPANNumber")[0].value='NA';
				}
				
				enableDisableFormFields("updateGuest");
			}
			
		}
		
		if(validateStatus == 'N')
		{
			$("#nonprintableDiv7").hide();  // checked
			$("#nonprintableDiv6").show();
			$("#nonprintableDiv6").fadeIn(1500);
			$("#updateFooterId").show();  // checked
			$("#saveFooterId").hide();
		}
		else if(validateStatus == 'Y')
		{
			$("#guestLoginErrorMsgId").html("&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_message.png' width='25' height='25' style='vertical-align:sub;' title='Warning' /><font color='#013157'> Registration details have been validated. Please contact to concern authority for updating details !</font>");
		}
		else
		{
			$("#guestLoginErrorMsgId").html("&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_warning.png' width='25' height='25' style='vertical-align:sub;' title='Warning' /> Registration details have been rejected. Please contact to concern authority !");
		}
		
		
	}
	else
	{
		$("#guestLoginErrorMsgId").html("&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_warning.png' width='25' height='25' style='vertical-align:sub;' title='Warning' /> Wrong credentials entered !");
	}
	
}

//Modified by Ashwini Mishra on 24-11-2015 
$('#regSlipGuestId').click(function(e){
	
	var empNo = $('[name="strEmployeeNumber"]')[0].value;
	//openEmployeeDetailsPopup('View','Pending',empNo);
	
	openEmployeeDetailsPopup('RegSlip','Pending',empNo);
	
	/*
	  var windowWidth=window.innerWidth-100;
		var windowHeight=window.innerHeight-100;
		
		var blankHeight, blankWidth;
		if(!windowWidth)
			windowWidth =( $(window).width() * 0.8);
		if(!windowHeight)
			windowHeight = ($(window).height() * 0.8);
		
		//blankWidth = ($(window).width()-windowWidth);
		//blankHeight =($(window).height() - windowHeight)  ;
		blankWidth = (window.innerWidth-windowWidth);
		blankHeight =(window.innerHeight - windowHeight)  ;
		var windowLeft = Math.round((blankWidth / 2)-18)+ "px";
		var windowTop = Math.round((blankHeight / 2)-18 ) + "px";
		//windowHeight = windowHeight + "px";
		//windowWidth = windowWidth + "px";
						
		
		
		//alert("Record Details for = "+recordId);
		$("#divPrintId1").html("");
		var htmlstring=createRegistrationSlip('RegSlip','Pending',empNo);	
		//alert(htmlstring);
		$("#divPrintId1").html(htmlstring);
		
		//$("#divPrintId1").children().find('.label, .control').css('font-size','12px');
		$("#reportForm").children().find('.label, .control').css('font-size','12px');
		$("#divHospitalName").css('font-size','14px');
		$("#divHospitalAdd1").css('font-size','12px');
		$("#divHospitalAdd2").css('font-size','12px');
		$("#divHospitalCity").css('font-size','12px');
		$("#divheadingId").css('font-size','12px');
		$("#reportForm").children().css('color','black');
		$("#reportForm").children().children().css('color','black');
		$("#divheadingId").show();
		
	//$('#divPrintId1').modal();
	
		$( "#divPrintId1" ).dialog({
		    height: windowHeight,
		    width: windowWidth,
		    id: "dialogBox",
		    title: "EMPLOYEE REGISTRATION SLIP",
		    responsive: true,		   
		    modal: true,
		    open: function (event, ui) {
		    
		    },
		    close: function(event, ui)
	        {
		    	$('#divPrintId1').html("");
	        }
		});
		
		windowHeight = windowHeight + "px";
		windowWidth = windowWidth + "px";
		
		
		//$('#simplemodal-container').width(windowWidth).height(windowHeight);
		$('#simplemodal-container').width("90%").height("85%");
		//$('#simplemodal-container').css({'left':windowLeft,'top':windowTop});
		$('#simplemodal-container').css({'left':"3%",'top':"4%"});
		$("#simplemodal-container").resizable();
	    //$("#simplemodal-container").draggable();
		*/
});


/*
function createRegistrationSlip(reportOpenLocation, empValidationStatus, empNumber)
{
	return getEmployeeDetails(reportOpenLocation, empValidationStatus, empNumber);
}
*/

function validateBirthDate()
{
	var birthdate = $('[name="dtEmployeeDOB"]')[0].value;
	var day = parseInt(birthdate.substring(0,2))-1;
	var month = monthNumericValue(birthdate.substring(3,6).toUpperCase());
    var year = birthdate.substring(7,11);
    //alert(day);
    //alert(month);
    //alert(year);
    var birthDate = new Date();
    birthDate.setFullYear(year, month, day);
	//alert(mydate);
    
	if ((maxBirthDate - birthDate) < 0) // maxBirthDate defined globally on top of the page
	{
       // alert("Sorry, only persons over the age of " + age + " may register !");
		showAlert ("1", "Sorry, only persons over the age of " + age + " may register");
        return false;
    }
    return true;
}

function monthNumericValue(month)
{
	switch(month)
	{
	   case "JAN" :
	   	return 00;
	   	
	   case "FEB" :
	   	return 01;
	   	
	   case "MAR" :
	   	return 02;
	   	
	   case "APR" :
	   	return 03;
	   	
	   case "MAY" :
	   	return 04;
	   	
	   case "JUN" :
	   	return 05;
	   
	   case "JUL" :
	   	return 06;
	   	
	   case "AUG" :
	   	return 07;
	   	
	   case "SEP" :
	   	return 08;
	    
	   case "OCT" :
	   	return 09;
	   
	   case "NOV" :
	   	return 10;
	   
	   case "DEC" :
	   	return 11;
	
	   default :
   			//alert("You have entered an invalid month");
   }
}


function removeAllValidations()
{
	
	$('[name="strLastEmploymentType"]').validatebox({validType: [] });
	$('[name="intNatureOfJobId"]').validatebox({validType: [] });
	$('[name="intAppellationCode1"]').validatebox({validType: [] });
	$('[name="strGenderCode"]').validatebox({validType: [] });
	$('[name="intDepartmentCode"]').validatebox({validType: [] });
	$('[name="intDesignationCode"]').validatebox({validType: [] });
	//$('[name="strEmployeeFinalStatus"]').validatebox({validType: [] });
	$('[name="strEmployeeFullName"]').validatebox({required: false, validType: ''});
	$('[name="strEmployeeFullRegionalLangName"]').validatebox({required: false});
	$('[name="strEmployeeShortName"]').validatebox({required: false, validType: ''});
	$('[name="strEmployeeShortRegionalLangName"]').validatebox({required: false});
	$('[name="dtEmployeeDOB"]').validatebox({required: false });
	$('[name="intMobileNumber"]').validatebox({required: false, noSpace: false,validType: []});
	$('[name="strPersonalEmailId"]').validatebox({required: false, validType: []});
	$("#empDOBId").validatebox({required: false, validType: []});
	$('[name="strPANNumber"]').validatebox({required: false, validType: []});
	$('[name="strOldEmployeeNumber"]').validatebox({required: false, validType: []});
	
	$('[name="strGestModifyEmployeeNumber"]').validatebox({required: false, validType: []});
	$("#guestModifyEmpDOBId").validatebox({required: false, validType: []});
	
}
