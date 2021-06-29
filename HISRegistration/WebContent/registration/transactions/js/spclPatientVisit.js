/**
 * Developer : Aadil
 */

$(document).ready(function()
{
	$.tools.dateinput.localize("en", {
		  months: 'January,February,March,April,May,June,July,August,September,October,November,December',
		  shortMonths:  'Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec',
		  days:         'Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday',
		  shortDays:    'Su,Mo,Tu,We,Th,Fr,Sa'
		  });
	  
	$(":date").dateinput({
		format: 'dd-mmm-yyyy',
		max: 0.5,                    	// max selectable day (100 days onwards)
		speed: 'fast',               	// calendar reveal speed
		firstDay: 0                  	// which day starts a week. 0 = sunday, 1 = monday etc..
	});
	
	if($("#afterGoId").val()=="1"){
		var deptCode = $('[name="departmentCode"]');
		var flagHide=false;
		if(typeof deptCode != undefined && deptCode.length >0 && deptCode[0].options.length==1){
			$('[name="newDepartmentVisit"]')[0].checked=false;
			flagHide=true;
		}
		if($("#divOldDeptVisitId3").is(":visible")==true){
			$('[name="oldDepartmentVisit"]')[0].checked=false;
			flagHide=true;
		}
		if(flagHide)
			spclPatientVisitJsObj.showHideNewAndOldVisit($('[name="newDepartmentVisit"]')[0]);
		
		$('.letterDate').val($("#sysdateId").val());

	}
	if($("#printId").val()=="1"){
		$("#divPrintId").html($("#hiddenPrintId").val());
			if(get_object("divBarCodeControl")!=null)
				get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
		if (!($('#divPrintId').is(':empty')))			
			window.print();
		else
		{
			if($("#divOldDeptVisitId1").is(":visible")==true)
				$("#divNormalMsgId").html("");
			else
				$("#divNormalMsgId").html("Patient Visit Saved Successfully");
			
		}
			
	} 
});
	
var fromDeptCode="";
var refIndex="";
var countDeptVisit=0;
 
var spclPatientVisitJsObj={

	// Not in use right now. Because Ref Dept is coming now on SuperUserHospCode once
  onchange_patRefGnctdHospitalDept:function()
  {
	var refHospCode= document.getElementsByName("patRefGnctdHospitalCode")[0].value;
	var flafRefHospOrInst = refHospCode.split("#")[1];
	refHospCode	=	refHospCode.split("#")[0];
	var action 	= "/HISRegistration/registration/transactions/getRefDeptPatientVisit.action?refHospCode="+refHospCode+"&flafRefHospOrInst="+flafRefHospOrInst;
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					spclPatientVisitJsObj.processGeneralNode(elementName,elementNode);
				}
					
		},error: function(errorMsg,textstatus,errorthrown) {
            alert('onchange_patRefGnctdHospitalDept '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
            
        }});
  },
  processGeneralNode:function(elementName,elementNode)
  {
	var optionText="<option value='-1'>Select Value</option>";
	//alert("elementName :"+elementName);
	if(elementNode!=null){
		for(var i=0;i<elementNode.childNodes.length;i++ )
		{
			var optionNode=elementNode.childNodes[i];
			//alert("value :"+optionNode.getAttribute("value"))
			optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
			
		}
	}
	if(elementName=="patRefGnctdHospitalDept"){
		optionText+="<option value='0'>Other</option>";
	}
	//alert("optionText :"+optionText);
	if(document.getElementsByName(elementName).length!=0)
			document.getElementsByName(elementName)[0].innerHTML=optionText;
	
  }, 
  showDetail:function(obj)
  {
	  var patCrNoWithIndex= obj.value;
	  //alert("spclPatientVisitJsObj.showDetail() -->> patCrNoWithIndex :"+patCrNoWithIndex);
	  $('[name="isPatReferByList"]')[0].value	= "1";
	  $('[name="onlineReferedIndex"]')[0].value = patCrNoWithIndex.substring(0,patCrNoWithIndex.indexOf('#'));
	  $('[name="indexID"]')[0].value			= patCrNoWithIndex.substring(0,patCrNoWithIndex.indexOf('#'));
	  $('[name="selectedReferal"]')[0].value	= patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('#')+1,patCrNoWithIndex.indexOf('$'));
	  $('[name="patCrNo"]')[0].value			= patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('$')+1);
	  
	  submitForm("GETPATDTL"); 
  },
  checkuncheckNewDepartment:function(Obj)
  {
	  if(Obj.checked){
		  $('[name="newDepartmentVisit"]')[0].value='On';
		  $('[name="oldDepartmentVisit"]')[0].value='';
	  }
	  submitForm('NEWDEPARTMENTVISITDTL');
  },
  checkuncheckOldDepartment:function(Obj)
  {
	  if(Obj.checked){
		  $('[name="oldDepartmentVisit"]')[0].value='On';
		  $('[name="newDepartmentVisit"]')[0].value='';
	  }else{
		  $('[name="oldDepartmentVisit"]')[0].value='';
	  }
	  submitForm('OLDDEPARTMENTVISITDTL');
  }, 
  showInternalExternal:function(objIntrnlExtrnl)
  {
	  //alert("inside showInternalExternal() -->> objIntrnlExtrnl :"+objIntrnlExtrnl.value);
	  if(objIntrnlExtrnl.value=="I"){
		  $("#divInternalReferId").show();
		  $("#divExternalReferalId").hide();
		  spclPatientVisitJsObj.setExternalReferred(objIntrnlExtrnl);
	  }else{
		  //objIntrnlExtrnl.checked=false;
		  $("#divExternalReferalId").show();
		  $("#divInternalReferId").hide();
		  spclPatientVisitJsObj.setExternalReferred(objIntrnlExtrnl);
	  }
	   
  },
  setExternalReferred : function (objIntrnlExtrnl)
  {
	//alert("inside setExternalReferred() -->> objIntrnlExtrnl :"+objIntrnlExtrnl.value);
  	if(objIntrnlExtrnl.value=="O")
  	{
  		$("#divRefDtlId").show();
  		$("#divRefHospitalDeptOtherId").hide();
  		$('[name="patRefGnctdHospitalCode"]')[0].value="-1";
  		$('[name="patRefHospitalName"]')[0].value="";
  		$('[name="patRefHospitalDeptOther"]')[0].value="";
 		
  		/*$('[name="patRefGnctdHospitalCrno"]').validatebox({validType: 'numeric'});
  		$('[name="patRefDoctor"]').validatebox({required: true, validType: 'alpha'});
  		$('[name="patRefGnctdHospitalDeptUnit"]').validatebox({validType: 'alphaNumeric'});*/
  		
  		if($('[name="referringInstType"]')[0].checked){
 			$("#divRefHosname").hide();
  			$("#divRefHosCode").show();
  			$("#divReferredInstitute").show();
  			$("#divReferred").show();
  			$('[name="patRefDoctor"]')[0].value="";
  			$('[name="patRefGnctdHospitalDept"]')[0].value="-1";
  			$('[name="patRefGnctdHospitalDeptUnit"]')[0].value="";
  			$('[name="patRefGnctdHospitalCrno"]')[0].value="";
  			
  			//$('[name="patRefGnctdHospitalCode"]').validatebox({validType: ['selectCombo[-1]']});
  			
  		}else{
  			$("#divRefHosname").show();
  			$("#divRefHosCode").hide();
  			$("#divReferredInstitute").show();
  			
  			
  			$('[name="patRefDoctor"]')[0].value="";
  			$("#divReferred").show();
  			$('[name="patRefGnctdHospitalDept"]')[0].value="-1";
  			$('[name="patRefGnctdHospitalDeptUnit"]')[0].value="";
  			$('[name="patRefGnctdHospitalCrno"]')[0].value="";
  			
  			//$('[name="patRefGnctdHospitalCode"]').validatebox({validType: null});
  			//$('[name="patRefHospitalName"]').validatebox({required : true, validType: 'alphaWithSpace'});
  		}
  		
  	}
  	else
  	{
  		//$('[name="patRefHospitalName"]').validatebox({required : false});
  		//$('[name="patRefDoctor"]').validatebox({required: false});
  		
  		$("#divRefDtlId").hide();
  		$("#divRefHosname").hide();
  		$("#divRefHosCode").hide();
  		$("#divReferredInstitute").hide();
  		$("#divReferred").hide();
  	}
  },
  checkIsReferred : function(obj)
  {
	  //alert("inside checkIsReferred()");
	  if(!spclPatientVisitJsObj.checkForRefer()){
		  return false;
	  }
	  if(obj.checked){
		  obj.value="1";
		  $("#divRefDtlChkboxId").show();
		  $('[name="isRefferInOut"]')[0].checked=true;
		  spclPatientVisitJsObj.showInternalExternal($('[name="isRefferInOut"]')[0]);
	  }else{
		  obj.value="0";
		  $("#divRefDtlChkboxId").hide();
		  
		  /*if(refIndex!="")
		  		$('[name="refferringOPDEpisode"]')[parseInt(refIndex)].checked=false;*/
	  }
		  
  },
  showdivhoscode : function(obj)
  {
	  //alert("inside showdivhoscode()");
  	
	  $('[name="patRefHospitalName"]')[0].value="";
	  $('[name="patRefDoctor"]')[0].value="";
	  if(obj.value=="G"){
		  $("#divRefHosname").hide();
		  $("#divRefHosCode").show();
	  }else{
		  $("#divRefHosCode").hide();
		  $("#divRefHosname").show();
	  }
	  //setExternalReferred($('[name="isRefferInOut"]')[1]);
  },
  validateDepartmentNewDeptVisit : function(field)
  {
  	//alert('ValidateDepartmentNewDeptVisit');
  	var isValid = true;
  	if($('[name="isPatReferByList"]')[0].value==IS_PAT_REFER_BY_LIST_TRUE){
  		if(PATIENT_REG_CATEGORY_GROUP_BENEFICIARY == $("#patPrimaryCatGrpCodeId").val() && 
  				spclPatientVisitJsObj.validateCatBeneficiary("new")==false){
  			isValid= false;
	    }else{
	    	isValid=true;     
	    }
  	}
  	else
  	{
  		var depCode=$('[name="departmentCode"]')[0].value;
	  	if( (depCode=="") || (depCode=="-1") )
	  	{
	  		alert("Please Select Department");
	  		isValid=false;
	  	}
	  	else
	  	{
	      	
	      	isValid=true;
	      	
	      	var objIsRef = $('[name="isReferred"]');
			if(typeof objIsRef != undefined && objIsRef.length>0 && objIsRef[0].checked==false)
	  		{
	  			//$('[name="isReferred"]')[0].value = IS_REFERRED_FALSE;
	
	  			value2=$('[name="departmentCode"]')[0].value;
	        	//alert(value2);
	        		if(field)
	        			value=field.value;
	        		else
	        			value="";
	        
	           	if ((value.length == 0)||(value=='-1'))
	              { 
	                 	if(value2=='-1')
	                 	{
	              		alert("Department is required");
	                  	isValid = false;
	                  	$('[name="departmentCode"]')[0].focus();
	                	}
	              }
	  		}
			if(isValid && PATIENT_REG_CATEGORY_GROUP_BENEFICIARY == $("#patPrimaryCatGrpCodeId").val() && 
	  				spclPatientVisitJsObj.validateCatBeneficiary("new")==false){
		  		isValid=false;
		    }
			if(isValid){
		  		var depCodeArray = depCode.split('#');
			  	
			  	var defaultGenderCode=depCodeArray[6];
			  	var defaultGenderName=depCodeArray[8];
			  	
			  	if(typeof depCodeArray[5]!=undefined && depCodeArray[5]!="")
			  		ageBoundRange = depCodeArray[5];
			  	
			  	var maxAgeRange	= calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
			  	departmentName	= $("#departmentCodeId option:selected").text();
			  	patGender		= $("#patGenderId").val();
			  	
			  	//alert("maxAgeRange :"+maxAgeRange);
			  	if($("#patAgeId").val() > maxAgeRange){
			  		alert("Only Patient having age upto \""+maxAgeRange+" "+ $("#patAgeUnitId").val()+"\" is Allowed in the Department,"+
			  				"\n\""+departmentName+
			  				"\",\nKindly Select Another Department");
			  		
			  		$("#departmentCodeId").val("-1");
			  		$("#departmentCodeId").focus();
			  		return false;
			  	}
			  	
			  	if(typeof defaultGenderCode!= undefined && defaultGenderCode!="" && defaultGenderCode!="-1"){
			  		if($("#patGenderCodeId").val()!= defaultGenderCode){
			  			alert("Only Patient having gender \""+defaultGenderName+"\" is Allowed in the Department,"+
			  					"\n\""+departmentName+
			  					"\",\nKindly Select Another Department");
			  			$("#departmentCodeId").val("-1");
				  		$("#departmentCodeId").focus();
				  		return false;
			  		}
			  	}
		  	}
	  	}
	  	
	  	
	  	
  	}
  	
  	return isValid;
  	
  	
  },
  renewalValidation : function()
  {
   	var len;
  	var isValid = true;
  	//alert("In renewalValidation");
  	count=0;
  	
  	len=$('[name="departmentsToVisitStamp"]').length;
  	
  	for(var i=0;i<len;i++){
  		if($('[name="departmentsToVisitStamp"]')[i].checked){
  			count++;
		}
	}
  	
  	if(count==0){
  		isValid = false;
  		alert("Select a  department");
  	}else{
  		isValid = true;
  		alert('Please Collect Rs '+  $('[name="amount"]')[0].value);
  	}

  return isValid;
  },
  checkForRefer : function()
  {
	  //alert("inside checkForRefer() -->> countDeptVisit :"+countDeptVisit);
	  var flagTrue=false;
	  var isRef=$('[name="isReferred"]')[0];
	  var deptCode = $('[name="departmentCode"]');
	  if( (countDeptVisit>1) && (isRef.checked)){
		  isRef.checked=false;
		  alert("Please Select Only One Department For Visit Stamp With Refer ");
		  return false;
	  }else if (countDeptVisit>0){
		  if($('[name="newDepartmentVisit"]')[0].checked){
			  isRef.checked=false;
			  alert("Please Do Either New Depart Visit or Old Depart Visit For Visit Stamp With Refer ");
			  return false;
		  }
	  }
	  flagTrue=true;
	  return flagTrue;
  	
  },
  checkIsReferredToDelete : function(objIsRef)
  {
	  //alert("Inside checkIsReferredToDelete()");
	  if(!checkForRefer()){
		  return false;
	  }
	  
	  if (e.checked==true)
	  {
	  	$("#checkReferral").show();
		$("#isRefferDiv").show();
	  }
	  else
	  {	
	  	if(refIndex!="")
	  		$('[name="refferringOPDEpisode"]')[parseInt(refIndex)].checked=false;
	  	
	  	// alert("sdsdsdsdkkko")
	  	
	  	
	  	$('[name="referringInstType"]')[0].checked=false;
	  	$('[name="referringInstType"]')[1].checked=false;
	  	
	  	$('[name="isRefferInOut"]')[0].checked=false;
	  	$('[name="isRefferInOut"]')[1].checked=false;
	  	
	  	$("#divCheckReferralId").hide();
	  	$("#divExternalReferalId").hide();
	  	$("#associated").hide();
	  	$("#divInternalReferId").hide();
	  	//$("#isRefferDiv").hide();
	  	
	  	// alert(refIndex);
	  	fromDeptCode="";
	   	refIndex="";	
	   	
	  }
  },
  calculateGrandtotal : function(objDeptToVisitStamp,index)
  {
  	//alert("In calculateGrandtotal() -->> index :"+index);
	var hiddenEpisodePatAmount= parseFloat($('[name="hiddenEpisodePatAmount"]')[index].value);  
	var grandTotal=parseFloat($('[name="grandTotal"]')[0].value);
	//var departmentsToVisitStamp=$('[name="departmentsToVisitStamp"]');
	
	if(objDeptToVisitStamp.checked){
		countDeptVisit=countDeptVisit+1;
	}else{
		countDeptVisit=countDeptVisit-1;
	}
	
	if($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2"){
		if($('[name="splRenewalRequired"]')[0].value=="1"){
			if(objDeptToVisitStamp.checked){
				$('[name="grandTotal"]')[0].value=$('[name="patAmountHospitalWise"]')[0].value;
			}else{
				if(countDeptVisit==0)
					$('[name="grandTotal"]')[0].value="0.00";
			}
		}
	}else if($('[name="strRenewalType"]')[0].value=="3" || $('[name="strRenewalType"]')[0].value=="4"){
		if(objDeptToVisitStamp.checked){
			$('[name="grandTotal"]')[0].value=grandTotal+hiddenEpisodePatAmount;
		}else{
			$('[name="grandTotal"]')[0].value=grandTotal-hiddenEpisodePatAmount;
		}
	}
	//alert("grand Total :"+$('[name="grandTotal"]')[0].value);
	
	/*if(!spclPatientVisitJsObj.checkForRefer()){
		  alert("hey in calculateGrandtotal");
		  $('[name="departmentsToVisitStamp"]')[index].checked=false;
		  countDeptVisit=countDeptVisit-1;
		  return false;
	  }*/
  	
  },
  validateDepartmentOldDeptVisit : function(callerChecker) 
  {
	    var isValid = true;
	    
		//alert("document.getElementsByName('isRefferInOut')[0].checked "+document.getElementsByName("isRefferInOut")[0].checked)
		//alert('inside validateDepartmentOldDeptVisit....');
		if($('[name="onRequestVisit"]')[0].value!=1)
		{
			if($('[name="departmentsToVisitStamp"]').length!=0)
			{
				var isVisited=false;
				for(var visitIndex=0; visitIndex< $('[name="departmentsToVisitStamp"]').length; visitIndex++)
				{
					if($('[name="departmentsToVisitStamp"]')[visitIndex].checked)
					{
						isVisited=true;
					}
				
				}
				if(isVisited==false)
				{
					alert("Please Select Department for old visit");
					return false;
				}
			}
			else
			{
				alert(" No department to visit");
				return false;
			}			
		}
		if(isValid && PATIENT_REG_CATEGORY_GROUP_BENEFICIARY == $("#patPrimaryCatGrpCodeId").val() && 
	    		opdPatientVisitJsObj.validateCatBeneficiary("old")==false)
	    {
			isValid= false;
	    }
		return isValid;
  },
  validateNewAndOldSave: function()
  {
	  var newDepartmentVisitFlag = $('[name="newDepartmentVisit"]')[0];
	  var oldDepartmentVisitFlag = $('[name="oldDepartmentVisit"]')[0];
	  var patAmountCollected	 = $('[name="patAmountCollected"]')[0].value;
	  var grandTotal=0.00;
	  var msg="Please Collect Amount For,\n";
	  var flagSave = true;
	  
	  if(newDepartmentVisitFlag.checked){
		  flagSave=spclPatientVisitJsObj.validateDepartmentNewDeptVisit($('[name="departmentsToVisitStamp"]')[0]);
		  
		  if(flagSave){
			  var deptComboArray = $('[name="departmentCode"]')[0].value.split('#');
			  var defaultIsReferredFlag=deptComboArray[7];
			  	
			  var departmentName	= $("#departmentCodeId option:selected").text();
			  if(typeof defaultIsReferredFlag!=undefined && defaultIsReferredFlag=="1" && $('[name="isReferred"]')[0].checked==false){
				  alert("IsReferred Checkbox must be Selected for this Department, "+
			  				"\n\""+departmentName+
			  				"\",\nKindly Select Another Department");
				  flagSave=false;
			  }
		  }
	  }
	  
	  if(flagSave && oldDepartmentVisitFlag.checked){
		  flagSave=spclPatientVisitJsObj.validateDepartmentOldDeptVisit();
	  }
	  
	  if(flagSave && spclPatientVisitJsObj.validateIsReferred())
	  {
		  if(typeof $('[name="grandTotal"]')!=undefined && $('[name="grandTotal"]').length>0 ){
			  grandTotal=$('[name="grandTotal"]')[0].value;
		  }
		  if(newDepartmentVisitFlag.checked && oldDepartmentVisitFlag.checked){
			  if(($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2")
					  && $('[name="splRenewalRequired"]')[0].value=="1"){
				  
					  msg=msg+"\nRenewal Amount    = "+patAmountCollected+
					  		  "\n--------------------------------------"+
					  		  "\nTotal Amount		     =  "+patAmountCollected;
					  		  
			  }else{
				  msg=msg+"\nNew Dept Visit Amount = "+patAmountCollected+
				  		  "\nRenewal Amount 	      =  "+grandTotal+
				  		  "\n-------------------------------------"+
				  		  "\nTotal Amount		      = "+(parseFloat(patAmountCollected)+parseFloat(grandTotal));
			  }
					  
		  }else if(newDepartmentVisitFlag.checked){
			  if(($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2")
					  && $('[name="splRenewalRequired"]')[0].value=="1"){
				  
					  msg=msg+"\nRenewal Amount    = "+patAmountCollected+
					  		  "\n--------------------------------------"+
					  		  "\nTotal Amount		     =  "+patAmountCollected;
					  		  
			  }else{
				  msg=msg+"\nNew Dept Visit Amount = "+patAmountCollected+
				  "\n--------------------------------------"+
				  "\nTotal Amount		      = "+patAmountCollected;
			  }
		  }else{
			  msg=msg+"\nRenewal Amount  = "+grandTotal+
			  		  "\n-------------------------------"+
			  		  "\nTotal Amount	     = "+parseFloat(grandTotal);
		  }
		  msg+="\n\n\tDo You Wish To Continue...";
		  if(confirm(msg))
			  submitForm('SAVE');
	  }
	  else
		  return false;
  },
  showHideNewAndOldVisit : function(obj){
	  //alert("inside showHideNewAndOldVisit() :"+obj);
	  var newDepartmentVisitFlag = $('[name="newDepartmentVisit"]')[0];
	  var oldDepartmentVisitFlag = $('[name="oldDepartmentVisit"]')[0];
	  
	  if(newDepartmentVisitFlag.checked){
		  $("#divNewDeptVisitId").show();
	  }else{
		  $('[name="departmentCode"]')[0].value="-1";
		  $("#divNewDeptVisitId").hide();
	  }
	  var objdepartmentsToVisitStamp =$('[name="departmentsToVisitStamp"]');
	  if(oldDepartmentVisitFlag.checked){
		  $("#divOldDeptVisitId1").show();
		  $("#divOldDeptVisitId2").show();
		  $("#divOldDeptVisitId3").show();
		  
	  }else{
		  if(typeof objdepartmentsToVisitStamp!= undefined && objdepartmentsToVisitStamp.length > 0){
			  for(var i=0; i<objdepartmentsToVisitStamp.length; i++)
				  objdepartmentsToVisitStamp[i].checked=false;
			  $('[name="grandTotal"]')[0].value="0.00";
			  countDeptVisit=0;
		  }
		  $("#divOldDeptVisitId1").hide();
		  $("#divOldDeptVisitId2").hide();
		  $("#divOldDeptVisitId3").hide();
	  }
	  if(!newDepartmentVisitFlag.checked || !oldDepartmentVisitFlag.checked){
		  $('[name="isReferred"]')[0].checked=false;
		  $("#submitBothId").hide();
		  spclPatientVisitJsObj.checkIsReferred($('[name="isReferred"]')[0]);
	  }
	  if(!newDepartmentVisitFlag.checked && !oldDepartmentVisitFlag.checked)
		  $("#submitBothId").hide();
	  else
		  $("#submitBothId").show();
  },
  validateIsReferred : function(){
	  var objIsRef = $('[name="isReferred"]');
		if(typeof objIsRef != undefined && objIsRef.length>0 && objIsRef[0].checked){
			
			//$('[name="isReferred"]')[0].value = IS_REFERRED_TRUE;
			if ($('[name="isRefferInOut"]')[1].checked)
			{	
				$('[name="isRefferInOut"]')[1].value = IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL;
				if($('[name="patRefDoctor"]')[0].value=="")
				{
					alert("Enter Doctor Name");
					$('[name="patRefDoctor"]')[0].focus();
					return false;
				}
				if ($('[name="referringInstType"]')[1].checked)
				{	
					$('[name="isAssociated"]')[0].value = IS_ASSOCIATED_FALSE;
					if($('[name="patRefHospitalName"]')[0].value=="")
					{
						alert("Enter Referring Institute Name");
						$('[name="patRefHospitalName"]')[0].focus();
						return false;
					}
				}	
				else if ($('[name="referringInstType"]')[0].checked)
				{
					$('[name="isAssociated"]')[0].value=IS_ASSOCIATED_TRUE;
				 // alert('$('[name="patRefGnctdHospitalCode"]')[0].options['+$('[name="patRefGnctdHospitalCode"]')[0].selectedIndex+'].value   ='+$('[name="patRefGnctdHospitalCode"]')[0].options[$('[name="patRefGnctdHospitalCode"]')[0].selectedIndex].value)
					if($('[name="patRefGnctdHospitalCode"]')[0].options[$('[name="patRefGnctdHospitalCode"]')[0].selectedIndex].value=="-1")
					{
						alert("Select Referring Institute Name");
						$('[name="patRefGnctdHospitalCode"]')[0].focus();
						return false;
					}
				}
				else
				{
					alert("Select Referring Institute Name");
					return false;
				}
			}
			else if ($('[name="isRefferInOut"]')[0].checked)
		  	{
		  		
		  		$('[name="isRefferInOut"]')[0].value=IS_REFERRED_IN_OUT_ACCEPT_INTERNAL;
		  		var index=0;
		  		var refferringOPDEpisodeLength=$('[name="refferringOPDEpisode"]').length;
		  	 	//alert('internal referral length '+refferringOPDEpisodeLength);
		  		var flag=false;
	  			while(index<refferringOPDEpisodeLength)
	  			{
	  				if($('[name="refferringOPDEpisode"]')[index].checked){
		  				flag=true;
		  				break;
	  				}
	  				index=index+1;
	  			}
	  			//alert("flag :"+ flag);
				if(flag==true)
				{
					return true;
				}
				else
				{
					alert("Please Select Internal Referred Department");
					return false;
				}
		  		
		  	}
			else
			{
				alert("Select Referring Institute Name");
				return false;
			}
	  }
	  return true;
  },
  validateOnRequest : function (e){
		var valid=false;
		$('[name="onRequestVisit"]')[0].value=1;
		if(countDeptVisit==0)
		{
			$('[name="hcode"]')[0].value=e;
			// alert('hiddenEpisode....'+document.getElementsByName('hiddenEpisode')[0].value);
			$('[name="hcode"]')[0].value=e;
			
			// alert('hcode....'+document.getElementsByName('hcode')[0].value);
			if($('[name="newDepartmentVisit"]')[0].checked){
				alert("Only Old Department Visit is allowed for Forceful Visit");
				return false;
			}
			if(spclPatientVisitJsObj.validateIsReferred())
				valid=true;
		}
		else
		{	
			alert("Please deselect other Old Department Visit First");
			return false;
		}
		return valid;
	},
	validateCatBeneficiary : function(mode){
		if(mode=="new"){
			if($('[name="creditBillFlag"]')[0].value=="1" && 
					$('[name="creditLetterRefNo"]')[0].value==""){
				alert("Please Enter Letter Ref No.");
				$('[name="creditLetterRefNo"]')[0].focus();
				return false;
				
			}
		}else if(mode=="old"){
			var departmentsToVisitStamp = $('[name="departmentsToVisitStamp"]');
			for(var i=0; i< departmentsToVisitStamp.length; i++){
				if(departmentsToVisitStamp[i].checked && $('[name="arrCreditBillFlag"]')[i].value=="1"){
					if($('[name="arrCreditLetterRefNo"]')[i].value==""){
						alert("Please Enter Letter Ref No.");
						$('[name="arrCreditLetterRefNo"]')[i].focus();
						return false;
						
					}
				}
			}
		}
		return true;
	}
 
};

/*$("#patRefGnctdHospitalCodeId").change(function(){
	spclPatientVisitJsObj.onchange_patRefGnctdHospitalDept();
});*/
$("#patRefGnctdHospitalDeptId").change(function(){
	if($("#patRefGnctdHospitalDeptId").val()=="0"){
		$("#divRefHospitalDeptOtherId").show();
		//$('[name="patRefHospitalDeptOther"]').validatebox({required : true, validType: 'alphaWithSpace'});
	}
	else{
		$("#divRefHospitalDeptOtherId").hide();
		//$('[name="patRefHospitalDeptOther"]').validatebox({required : false, validType: 'alphaWithSpace'});
	}
});
$("#submitNewId").click(function(){
	if(spclPatientVisitJsObj.checkForRefer($('[name="isReferred"]')))
		submitFormOnValidate(spclPatientVisitJsObj.validateDepartmentNewDeptVisit($('[name="departmentsToVisitStamp"]')[0]),'SAVE');
});

$("#submitOldId").click(function(){
	//alert("inside submitOldId");
	if(spclPatientVisitJsObj.checkForRefer($('[name="isReferred"]')))
		submitFormOnValidate(spclPatientVisitJsObj.validateDepartmentOldDeptVisit(),'SAVE');
});

$("#submitBothId").click(function(){
	//alert("inside submitBothId");
	var objIsRef = $('[name="isReferred"]');
	if(typeof objIsRef != undefined && objIsRef.length>0 && objIsRef[0].checked){
		if(spclPatientVisitJsObj.checkForRefer($('[name="isReferred"]')))
			spclPatientVisitJsObj.validateNewAndOldSave();
		else{
			$("#divRefDtlChkboxId").hide();
			$('[name="isRefferInOut"]')[0].checked=true;
			spclPatientVisitJsObj.showInternalExternal($('[name="isRefferInOut"]')[0]);
			$("#divRefDtlChkboxId").hide();
		}
	}else{
		spclPatientVisitJsObj.validateNewAndOldSave();
	}
});

$("#cancelId").click(function(){
	//alert("inside cancelId");
	if($("#afterGoId").val()=="1")
		submitForm('CANCEL');
	else
		callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');
});

$("#clearId").click(function(){
	//alert("inside clearId");
	submitForm('CLEAR');
});


$("#divLegendsId").click(function(){
	$("#divLegendsDtlId").toggle();
});


function submitForm(mode) {
	document.forms[0].action = mode + "SpclPatientVisit.action";
	//alert("action :"+document.forms[0].action);
	
	if(mode=="SAVE"){
		$('[name="oldDepartmentVisit"]').attr("disabled", false);
	  	$('[name="newDepartmentVisit"]').attr("disabled", false);
	}
	document.forms[0].submit();
}

function calculateMaxRangeValue(age,ageUnit){
	var maxAgeRange=125;
	
	if(ageUnit=="D"){
		maxAgeRange=(age*365);
	}else if(ageUnit=="Wk"){
		maxAgeRange=age*365/7;
	}else if(ageUnit=="Mth"){
		maxAgeRange=age*12;
	}else if(ageUnit=="Yr"){
		if(age<maxAgeRange)
			maxAgeRange=age;
	}
	return maxAgeRange;
}

function submitFormonRadio(mode,obj) {
	document.forms[0].action = mode + "SpclPatientVisit.action?crno="+obj.value;
	document.forms[0].submit();

}
function populate(selectedarray)
{
	var strHtml ="";
	var elem = document.getElementById("hiddenDivVerification");
	for(i=0; i<selectedarray.length; i++)
	{
		var arrayOfDocsData=selectedarray[i].split("|");
  	  
		strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
		strHtml+=arrayOfDocsData[1]+"&nbsp; &nbsp;";
	}
	elem.innerHTML= ":: &nbsp;" + strHtml;
	
}  

