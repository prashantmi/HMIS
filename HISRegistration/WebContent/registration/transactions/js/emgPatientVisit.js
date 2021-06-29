/**
 * Developer : Aadil
 */

$(document).ready(function()
{
	/*$.tools.dateinput.localize("en", {
		  months: 'January,February,March,April,May,June,July,August,September,October,November,December',
		  shortMonths:  'Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec',
		  days:         'Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday',
		  shortDays:    'Su,Mo,Tu,We,Th,Fr,Sa'
		  });*/
	  
	/*$(":date").dateinput({
		format: 'dd-mmm-yyyy',
		max: 0.5,                    	// max selectable day (100 days onwards)
		speed: 'fast',               	// calendar reveal speed
		firstDay: 0                  	// which day starts a week. 0 = sunday, 1 = monday etc..
	});*/
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
			emgPatientVisitJsObj.showHideNewAndOldVisit();
		
		if($('[name="newDepartmentVisit"]')[0].checked)
			emgPatientVisitJsObj.checkCostOfPaidCatIsDefined();
		
		//console.log("sysdateId :"+$("#sysdateId").val());
		$('.letterDate').val($("#sysdateId").val());
		
		$("#clientNameLabel").html($('[name="clientName"]')[0].value);

	}
	
		
	if($("#printId").val()=="1"){
		$("#divPrintId").html($("#hiddenPrintId").val());
			if(get_object("divBarCodeControl")!=null){
				get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
			}
			if(get_object("divBarCodeControlForBill")!=null)
				{
				get_object("divBarCodeControlForBill").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlForBill").innerHTML, 0);
				}
		if (!($('#divPrintId').is(':empty'))){			
			window.print();
			manageBarcodePrintProcess();
	}else{
			if($("#divOldDeptVisitId1").is(":visible")==true)
				$("#divNormalMsgId").html("");
			else
				$("#divNormalMsgId").html("Patient Visit Saved Successfully");
		}
			
	}else if($("#printId").val()=="2"){
		$("#divNormalMsgId").html("Patient Visit Saved Successfully");
		manageBarcodePrintProcess();
	}
	
	if($('[name="isDuplicateRegistered"]').val()=='1'){
		$('[name="isDuplicateRegistered"]').val('0');
		emgPatientVisitJsObj.showDuplicateDetail();
	}
	//By Default New Dept Visit Stamp to be Unchecked, Added by Singaravelan on 20-Jul-2015
	if( $('[name="isPatReferByList"]')[0].value!="1")
	{
		$('[name="newDepartmentVisit"]')[0].checked=false;
		emgPatientVisitJsObj.showHideNewAndOldVisit();
	}
	//Added by Vasu on 07.May.18
	broughtBy(document.getElementsByName("isBroughtBy")[0]);
	enableRelation(document.getElementsByName("isRelative")[0]);
	
	document.getElementsByName("isAmbulatory")[0].value = "1";
	document.getElementsByName("isMLC")[0].value = "0";
	//End Vasu

});	

var fromDeptCode="";
var refIndex="";
var countDeptVisit=0;
var catDefCharge=$('[name="patActualAmount"]')[0].value;
 
var emgPatientVisitJsObj={
		
  onchange_patRefGnctdHospitalDept:function()
  {
	var refHospCode= document.getElementsByName("patRefGnctdHospitalCode")[0].value;
	var flafRefHospOrInst = refHospCode.split("#")[1];
	refHospCode	=	refHospCode.split("#")[0];
	var action 	= "/HISRegistration/registration/transactions/getRefDeptEmgPatientVisit.action?refHospCode="+refHospCode+"&flafRefHospOrInst="+flafRefHospOrInst;
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					emgPatientVisitJsObj.processGeneralNode(elementName,elementNode);
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
		  emgPatientVisitJsObj.setExternalReferred(objIntrnlExtrnl);
	  }else{
		  //objIntrnlExtrnl.checked=false;
		  $("#divExternalReferalId").show();
		  $("#divInternalReferId").hide();
		  emgPatientVisitJsObj.setExternalReferred(objIntrnlExtrnl);
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
  		$('[name="patRefHospitalName"]').validatebox({required: true,  validType : 'allowAlphaSpaceBracketDot' });
  		$('[name="patRefHospitalDeptOther"]')[0].value="";
  		$('[name="patRefDoctor"]').validatebox({required: true,  validType : 'allowAlphaSpaceBracketDot' });
		$('[name="patRefGnctdHospitalDeptUnit"]').validatebox({validType: 'alphaNumericWithSpaces'});
		$('[name="patRefGnctdHospitalCrno"]').validatebox({validType : ['numericwithoutspace','equalLengthForCrno["12|15"]','DisableAllZero']});
 		
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
  			$('[name="patRefHospitalName"]').validatebox({required : true, validType: 'allowAlphaSpaceBracketDot'});
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
	  if(!emgPatientVisitJsObj.checkForRefer()){
		  return false;
	  }
	  if(obj.checked){
		  $("#divRefDtlChkboxId").show();
		  $('[name="isRefferInOut"]')[0].checked=true;
		  emgPatientVisitJsObj.showInternalExternal($('[name="isRefferInOut"]')[0]);
	  }else{
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
	  //console.log("creditLetterDateId :"+ $("#creditLetterDateId").val());
  	var isValid = true;
  	
	var depCode=$('[name="departmentCode"]')[0].value;
	var patPrCatGroup=$('[name="patPrimaryCatGrpCode"]')[0].value;

  	if( (depCode=="") || (depCode=="-1") )
  	{
  		alert("Please Select Department");
  		isValid=false;
  	}
  	else
  	{
      	
      	isValid=true;
      	
  		//alert('inside ValidateDepartmentNewDeptVisit....');
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
  				emgPatientVisitJsObj.validateCatBeneficiary("new")==false){
	  		isValid=false;
	    }
		if(isValid){
	  		var depCodeArray = depCode.split('#');
	  	  	var defaultGenderCode=depCodeArray[6];
	  	  	var defaultGenderName=depCodeArray[7];
	  	  	
	  	    var ageBoundRange="";
	  	  	if(typeof depCodeArray[5]!=undefined && depCodeArray[5]!="")
	  	  		ageBoundRange = depCodeArray[5];
	  	  	//alert("ageBoundRange :"+ageBoundRange);
	  	  	departmentName	= $("#departmentCodeId option:selected").text();
	  	  	patGender		= $("#patGenderId").val();
	  	  	
	  	  	if(ageBoundRange!=undefined && ageBoundRange!=null && ageBoundRange!=""){
	  	  		var maxAgeRange	= calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
	  	  		//alert("maxAgeRange :"+maxAgeRange);
	  	  		if($("#patAgeId").val() > maxAgeRange){
	  	  			alert("Only Patient having age upto \""+maxAgeRange+" "+ $("#patAgeUnitId").val()+"\" is Allowed in the Department,"+
	  	  					"\n\""+departmentName+
	  	  			"\",\nKindly Select Another Department");
	  	  			
	  	  			$("#departmentCodeId").val("-1");
	  	  			$("#departmentCodeId").focus();
	  	  			return false;
	  	  		}
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
	  	  	
	  	  if(patPrCatGroup==0 && catDefCharge==-1){
				alert("It is paid category and charges are not defined in Charge Master.");
				return false;
			}
	  	}
  	}
  	
  	
  	
  	
  	//alert("hey");
  	/*if($('[name="oldDepartmentVisit"]')[0].checked==true && $('[name="newDepartmentVisit"]')[0].checked==true)
  	{
  		if(isValid)
  			isValid=emgPatientVisitJsObj.validateDepartmentOldDeptVisit();
  	}	
  	
  	if(($('[name="oldDepartmentVisit"]')[0].checked==true && $('[name="newDepartmentVisit"]')[0].checked==true)
  			||($('[name="oldDepartmentVisit"]')[0].checked==true && $('[name="newDepartmentVisit"]')[0].checked==false))
  	{
  		return isValid;
  	}
  	else
  	{
  		if(isValid)
  		{
  			if($('[name="patAmountCollected"]')[0].value!="0" && $('[name="patAmountCollected"]')[0].value!="0.00")			
  				alert('Please Collect Rs '+  $('[name="patAmountCollected"]')[0].value);
  			return isValid;
  		}
  	}*/
  	
  	return isValid;
  	
  },
  renewalValidation : function()
  {
   	var len;
  	var isValid = true;
  	alert("In renewalValidation");
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
	  alert("Inside checkIsReferredToDelete()");
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
		var hiddenEpisodePatAmount= parseFloat($('[name="hiddenEpisodePatAmount"]')[index].value);  
		var grandTotal=parseFloat($('[name="grandTotal"]')[0].value);
		
		if(objDeptToVisitStamp.checked){
			countDeptVisit=countDeptVisit+1;
		}else{
			countDeptVisit=countDeptVisit-1;
		}
		
		if($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2"){
			if($('[name="emgRenewalRequired"]')[0].value=="1"){
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
  				emgPatientVisitJsObj.validateCatBeneficiary("old")==false){
	  		isValid=false;
	    }
		$('.readOnlyClass').attr("disabled",false);
		
		if( $('[name="patRenewalActualAmount"]')[0].value=='-1' &&
				$('[name="patPrimaryCatGrpCode"]')[0].value== PATIENT_REG_CATEGORY_GROUP_PAID)
		{
			alert("It is paid category and charges are not defined in Charge Master.");
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
	  var finalTotal=0;
	  var msg="Please Collect Amount For,\n";
	  var flagSave = true;
	  
	  if(newDepartmentVisitFlag.checked){
		  flagSave=emgPatientVisitJsObj.validateDepartmentNewDeptVisit($('[name="departmentsToVisitStamp"]')[0]);
	  }
	  
	  if(flagSave && oldDepartmentVisitFlag.checked){
		  flagSave=emgPatientVisitJsObj.validateDepartmentOldDeptVisit();
	  }
	  
	  if(flagSave && emgPatientVisitJsObj.validateIsReferred()){
		  
		  if(typeof $('[name="grandTotal"]')!=undefined && $('[name="grandTotal"]').length>0 ){
			  grandTotal=$('[name="grandTotal"]')[0].value;
		  }
		  
		  if(newDepartmentVisitFlag.checked && oldDepartmentVisitFlag.checked){
			  if(($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2")
					  && $('[name="emgRenewalRequired"]')[0].value=="1"){
				  
				      finalTotal=patAmountCollected;
					  msg=msg+"\nRenewal Amount    = "+patAmountCollected+
					  		  "\n--------------------------------------"+
					  		  "\nTotal Amount		     =  "+finalTotal;
					  		  
			  }else{
				  finalTotal=parseFloat(patAmountCollected)+parseFloat(grandTotal);
				  msg=msg+"\nNew Dept Visit Amount = "+patAmountCollected+
				  		  "\nRenewal Amount 	      =  "+grandTotal+
				  		  "\n-------------------------------------"+
				  		  "\nTotal Amount		      = "+finalTotal;
			  }
					  
		  }else if(newDepartmentVisitFlag.checked){
			  finalTotal=patAmountCollected;
			  if(($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2")
					  && $('[name="emgRenewalRequired"]')[0].value=="1"){
				  
					  msg=msg+"\nRenewal Amount    = "+patAmountCollected+
					  		  "\n--------------------------------------"+
					  		  "\nTotal Amount		     =  "+finalTotal;
					  		  
			  }else{
				  msg=msg+"\nNew Dept Visit Amount = "+patAmountCollected+
				  "\n--------------------------------------"+
				  "\nTotal Amount		      = "+finalTotal;
			  }
		  }else{
			  finalTotal= parseFloat(grandTotal);
			  msg=msg+"\nRenewal Amount  = "+grandTotal+
			  		  "\n-------------------------------"+
			  		  "\nTotal Amount	     = "+finalTotal;
		  }
		  if(finalTotal > 0){
			//Added by Vasu on 18.May.18 for isAmbulatory and isMLC flags
				var ambulatory = document.getElementsByName("isAmbulatory")[0].value;
				document.getElementsByName("isAmbulatoryCheckUncheck")[0].value = ambulatory;
				var mlc = document.getElementsByName("isMLC")[0].value;
				document.getElementsByName("isMLCFlag")[0].value = mlc;
				//alert(document.getElementsByName("isAmbulatoryCheckUncheck")[0].value);
				//alert(document.getElementsByName("isMLCFlag")[0].value);
				//End Vasu 
		  msg+="\n\n\tDo You Wish To Continue...";
		  if(confirm(msg)){
			  if($('#EmgPatientVisit').form('validate'))
               {
			  sortandEncodebase64($("#EmgPatientVisit"));
			  $("#submitBothId").hide();
			  emgPatientVisitJsObj.checkAllEpisodeMlcFlag();
			  submitForm('SAVE');
               }
			  
		  }else{
			  $('.readOnlyClass').attr("disabled",true);
		  }
		  }
		  else{
			  if($('#EmgPatientVisit').form('validate'))
              {
			  
			  $("#submitBothId").hide();
              //}
			  //alert("hide:623");
			 // $("#submitBothId").hide();
			  emgPatientVisitJsObj.checkAllEpisodeMlcFlag();
			//Added by Vasu on 18.May.18 for isAmbulatory and isMLC flags
				var ambulatory = document.getElementsByName("isAmbulatory")[0].value;
				document.getElementsByName("isAmbulatoryCheckUncheck")[0].value = ambulatory;
				var mlc = document.getElementsByName("isMLC")[0].value;
				document.getElementsByName("isMLCFlag")[0].value = mlc;
				//alert(document.getElementsByName("isAmbulatoryCheckUncheck")[0].value);
				//alert(document.getElementsByName("isMLCFlag")[0].value);
				//End Vasu
			sortandEncodebase64($("#EmgPatientVisit")); 
			  submitForm('SAVE');
              }
		  }
	  }
	  else
		  return false;
  },
  alertCostOfPaidCatIsNotDefined : function(patPrCatGroup,catDefCharge){
	  var rtnvalue=true;
	  if(patPrCatGroup==0 && catDefCharge==-1){
			alert("It is paid category and charges are not defined in Charge Master.");
			rtnvalue= false;
		}
	  return rtnvalue;

  },
  checkCostOfPaidCatIsDefined : function(){
	  //alert("Inside Cost Check");
	  var rtnvalue=true;
	  var patPrCatGroup=$('[name="patPrimaryCatGrpCode"]')[0].value;
	  var catActualCharge=$('[name="patActualAmount"]')[0].value;
	  //catDefCharge=$('[name="patActualAmount"]')[0].value;
	 
	  if(patPrCatGroup!=PATIENT_REG_CATEGORY_GROUP_BENEFICIARY &&
			  patPrCatGroup!=PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE){
			if(catActualCharge=="-1"){
				 $('[name="patAmountCollected"]')[0].value="0.00";
				 $('[name="patActualAmount"]')[0].value="0.00";		
			}
			else{
					$('[name="patAmountCollected"]')[0].value= catActualCharge;
					$('[name="patActualAmount"]')[0].value= catActualCharge;
			}
		}
		else{
			
			if(catActualCharge=="-1"){
				 $('[name="patAmountCollected"]')[0].value="0.00";
				 $('[name="patActualAmount"]')[0].value="0.00";		
			}
			else{
				 $('[name="patAmountCollected"]')[0].value="0.00";  
				 $('[name="patActualAmount"]')[0].value= catActualCharge;
			}			
		}
	  
	  
		if(patPrCatGroup==PATIENT_REG_CATEGORY_GROUP_PAID)
		{    
			$("#patAmountCollectedId").focus();
			var catWiseChargeValidType='validatechargeforcat['+patPrCatGroup+','+catDefCharge+']';
			//alert(catWiseChargeValidType);		
	     	//$("#patAmountCollectedId").validatebox({validType: [catWiseChargeValidType]});
			rtnvalue=emgPatientVisitJsObj.alertCostOfPaidCatIsNotDefined(patPrCatGroup,catDefCharge);     	
		}
		else{		
			$("#patAmountCollectedId").focus();
			//$("#patAmountCollectedId").validatebox({validType:null});		
		}
	  
	  return rtnvalue;
  },
  showHideNewAndOldVisit : function(){
	  //alert("inside showHideNewAndOldVisit()");
	  var newDepartmentVisitFlag = $('[name="newDepartmentVisit"]')[0];
	  var oldDepartmentVisitFlag = $('[name="oldDepartmentVisit"]')[0];
	  
	  var obj = document.getElementsByName("creditLetterDate")[0];
 		if(typeof obj!=="undefined" && obj.value!=""){
 			var _sysDate=new Date();
 			var _chkDate=convertStrToDate(obj.value,"dd-Mon-yyyy");
 			
 			var timeDiff = Math.abs(_sysDate.getTime() - _chkDate.getTime());
 			var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));  

 			var _date = new Date();
 			_date.setDate((_sysDate.getDate() - diffDays) + 1);
 			//alert("1. "+_date);
 			today = moment(_date).format('DD-MMM-YYYY');
 			//today = today.toLocaleFormat('%d-%b-%Y');
 			_date.setDate(_date.getDate() + 29);
 			//alert("2. "+_date);
 			_todayPlus30Days = _date;
 			_todayPlus30Days = moment(_todayPlus30Days).format('DD-MMM-YYYY');
 	
 			//alert("1. today: "+today+"\ntodayplus30: "+_todayPlus30Days);

 		}else{
 			var today= moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
 			var _todayPlus30Days= moment().add(30, 'days').format('DD-MMM-YYYY');//moment(today, 'DD-MMM-YYYY').add(30, 'days');
 			/*new Date();
			_todayPlus30Days.setDate(_todayPlus30Days.getDate() + 30);
			_todayPlus30Days=_todayPlus30Days.toLocaleFormat('%d-%b-%Y');*/
 		}
	 //alert($('[name="creditLetterDate"]').val()+"\n"+$('[name="cardvalidityDate"]').val());
	  if(newDepartmentVisitFlag.checked){
		  $("#divNewDeptVisitId").show();
		  $("#divCatGroupBeneficiaryId").show();
		  $("#divCatGroupArogyaShreeBeneficiaryId").show();
		 // var today=new Date().toLocaleFormat('%d-%b-%Y');
		  $('#creditLetterDateId').DatePicker({
				format: 'd-M-Y',default_position :'below',show_icon :false, start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		  }).val(today);
		  $('#cardvalidityDateId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_todayPlus30Days,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_todayPlus30Days);
		  
		  //By mukund for auto-select payment mode name based on patient category
	   	  	var PrimaryCatName = $('[name="patPrimaryCategoryName"]').val();
			var pymntMdeCde = "paymentModeCode";
			var paymentModeLength = $('[name="paymentModeCode"]').children('option').length;
			for(var w=0; w<paymentModeLength; w++){
				var paymentModeObj = $('[name="paymentModeCode"]')[0];
				var paymentModeName = paymentModeObj.options[w].text;
				var paymentModeCodeVal = paymentModeObj.options[w].value;
				if(paymentModeCodeVal.includes("#1")){
					$('[name='+pymntMdeCde+']').val(paymentModeCodeVal);
					emgPatientVisitJsObj.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
					break;
				}
				/*if(PrimaryCatName.toUpperCase() === paymentModeName.toUpperCase()){
					$('[name='+pymntMdeCde+'] option:contains(' + paymentModeName + ')').attr('selected', 'selected');
					emgPatientVisitJsObj.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
					break;
				}*/else{
					$('[name="paymentModeCode"]').val(paymentModeObj.options[0].value);
					emgPatientVisitJsObj.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);

				}
			}
		$('[name="paymentModeCode"]').validatebox({
			  validType : [ 'selectCombo[-1]' ]
		  });
	   	 //End on Oct'18 for auto-select payment mode 
	  }else{
		  $('[name="departmentCode"]')[0].value="-1";
		  $("#divNewDeptVisitId").hide();
		  $("#divCatGroupBeneficiaryId").hide();
		  $("#divCatGroupArogyaShreeBeneficiaryId").hide();
	  }
	  if(oldDepartmentVisitFlag.checked){
		  $("#divOldDeptVisitId1").show();
		  $("#divOldDeptVisitId2").show();
		  $("#divOldDeptVisitId3").show();
		  var isVisited=false;
		  
		  if($('[name="departmentsToVisitStamp"]').length!=0)
			{
				
				for(var visitIndex=0; visitIndex< $('[name="departmentsToVisitStamp"]').length; visitIndex++)
				{
					if($('[name="renewalRequired"]')[visitIndex].value=="1")
					{
						isVisited=true;
					}
				
				}
			}
		  
		  if($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2")
			  {
		  if($('[name="opdRenewalRequired"]')[0].value!=0 && $('[name="patPrimaryCatGrpCode"]')[0].value==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)
			  $("#divCatGroupBeneficiaryId").show();
		  if($('[name="opdRenewalRequired"]')[0].value!=0 && $('[name="patPrimaryCatGrpCode"]')[0].value==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)
			  $("#divCatGroupArogyaShreeBeneficiaryId").show();
			  }
		  if($('[name="strRenewalType"]')[0].value=="3" || $('[name="strRenewalType"]')[0].value=="4")
			  {
			
			  if(isVisited && $('[name="patPrimaryCatGrpCode"]')[0].value==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)
				  {
				  $("#divCatGroupBeneficiaryId").show();
				  $('#creditLetterDateId').DatePicker({
						format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				  }).val(today);
		 		  
		 		  alert(_todayPlus30Days);
		 		$('#cardvalidityDateId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:_todayPlus30Days,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(_todayPlus30Days);
				  
				  }
			  if(isVisited && $('[name="patPrimaryCatGrpCode"]')[0].value==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)
				  $("#divCatGroupArogyaShreeBeneficiaryId").show();
			  }
	  }else{
		  var objdepartmentsToVisitStamp =$('[name="departmentsToVisitStamp"]');
		  if(typeof objdepartmentsToVisitStamp!= undefined && objdepartmentsToVisitStamp.length > 0){
			  for(var i=0; i<objdepartmentsToVisitStamp.length; i++)
				  objdepartmentsToVisitStamp[i].checked=false;
			  $('[name="grandTotal"]')[0].value="0.00";
		  }
		  $("#divOldDeptVisitId1").hide();
		  $("#divOldDeptVisitId2").hide();
		  $("#divOldDeptVisitId3").hide();
	  }
	  if(!newDepartmentVisitFlag.checked || !oldDepartmentVisitFlag.checked){
		  $('[name="isReferred"]')[0].checked=false;
		  $("#submitBothId").hide();
		  emgPatientVisitJsObj.checkIsReferred($('[name="isReferred"]')[0]);
	  }
	  if(!newDepartmentVisitFlag.checked && !oldDepartmentVisitFlag.checked)
		  $("#submitBothId").hide();
	  else
		  $("#submitBothId").show();
	  
	  if($("#divCatGroupArogyaShreeBeneficiaryId").show())
	  {
	   $("#agsCounterNoId").validatebox({
			required : false,
			validType : 'numeric'
		});
		$("#agsNoId").validatebox({
			required : true,
			validType : 'alphaNumeric'
		});
	  }
  },
  validateIsReferred : function(){
	  var objIsRef = $('[name="isReferred"]');
		if(typeof objIsRef != undefined && objIsRef.length>0 && objIsRef[0].checked){
			//alert($('[name="isReferred"]')[0].value);
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
					/*alert($('[name="isAssociated"]')[0].value);
					$('[name="isAssociated"]')[0].value = IS_ASSOCIATED_FALSE;*/
					if($('[name="patRefHospitalName"]')[0].value=="")
					{
						alert("Enter Referring Institute Name");
						$('[name="patRefHospitalName"]')[0].focus();
						return false;
					}
				}	
				else if ($('[name="referringInstType"]')[0].checked)
				{
					//start : Surabhi
					//$('[name="isAssociated"]')[0].value=IS_ASSOCIATED_TRUE;
					//end
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
	  }else{
		  $('[name="isReferred"]')[0].value = IS_REFERRED_FALSE;
	  }
	  return true;
  },
  checkUncheck : function(objCheckBox){
	  if(objCheckBox.checked)
		  objCheckBox.value="1";
	  else
		  objCheckBox.value="0";
  },
  checkAllEpisodeMlcFlag : function(){
	  var objEpisodeMlcFlag=$('[name="arrMlcFlag"]');
	  for(var i=0; i<objEpisodeMlcFlag.length; i++)
		  objEpisodeMlcFlag[i].checked=true;
  },
  unCheckAllEpisodeMlcFlag : function(){
	  var objEpisodeMlcFlag=$('[name="arrMlcFlag"]');
	  for(var i=0; i<objEpisodeMlcFlag.length; i++)
		  objEpisodeMlcFlag[i].checked=false;
  },
  validateCatBeneficiary : function(mode){

	  /*For sync with Patient Revisit on 28.12.2018*/
	  if($('[name="creditLetterRefNo"]').prop('disabled'))
	  		$('[name="creditLetterRefNo"]').prop('disabled', false);
		if($('[name="letterType"]').prop('disabled'))
		  		$('[name="letterType"]').prop('disabled', false);
		 if($('[name="creditLetterDate"]').prop('disabled'))
				$('[name="creditLetterDate"]').prop('disabled', false);
		  if($('[name="clientCode"]').prop('disabled'))
				$('[name="clientCode"]').prop('disabled', false);
		  if($('[name="staffCardNo"]').prop('disabled'))
				$('[name="staffCardNo"]').prop('disabled', false);
		  if($('[name="staffCardName"]').prop('disabled'))
				$('[name="staffCardName"]').prop('disabled', false);
		  if($('[name="cardvalidityDate"]').prop('disabled'))
				$('[name="cardvalidityDate"]').prop('disabled', false);
		  if($('[name="creditLimit"]').prop('disabled'))
				$('[name="creditLimit"]').prop('disabled', false);
		  if($('[name="relationWithStaff"]').prop('disabled'))
				$('[name="relationWithStaff"]').prop('disabled', false);
		  
		  //alert("card validity date: "+$('[name="cardvalidityDate"]').val());
		  
		  /*End: 28.12.2018*/
		if(mode=="new"){
			//console.log("validateCatBeneficiary() --> mode : new");
			/*if($('[name="creditBillFlag"]')[0].value=="1" && document.getElementsByName("staffCardNo")[0].value=="")
			{
				alert("Please Enter Staff No.");
				$('[name="staffCardNo"]')[0].focus();
				return false;
			}	*/
			if($('[name="creditBillFlag"]')[0].value=="1" && 
					$('[name="creditLetterRefNo"]')[0].value==""){
				alert("Please Enter Letter Ref No.");
				$('[name="creditLetterRefNo"]')[0].focus();
				return false;
				
			}
			if($('[name="creditBillFlag"]')[0].value=="1" && document.getElementsByName("creditLetterDate")[0].value=="")
			{
				alert("Please Enter Letter date");
				$('[name="creditLetterDate"]')[0].focus();
				return false;
			}
			if($('[name="creditBillFlag"]')[0].value=="1" && document.getElementsByName("letterType")[0].value=="-1")
			{
				alert("Please Select Letter Type");
				$('[name="letterType"]')[0].focus();
				return false;
			}
			
		}else if(mode=="old"){
			//console.log("validateCatBeneficiary() --> mode : old");
			/*if($('[name="creditBillFlag"]')[0].value=="1" && document.getElementsByName("staffCardNo")[0].value=="")
			{
				alert("Please Enter Staff No.");
				$('[name="staffCardNo"]')[0].focus();
				return false;
			}*/
			var departmentsToVisitStamp = $('[name="departmentsToVisitStamp"]');
			for(var i=0; i< departmentsToVisitStamp.length; i++){
				if(departmentsToVisitStamp[i].checked && $('[name="renewalRequired"]')[i].value=="1"){
					if($('[name="creditLetterRefNo"]')[i].value==""){
						alert("Please Enter Letter Ref No.");
						$('[name="creditLetterRefNo"]')[i].focus();
						return false;
						
					}
					if(document.getElementsByName("creditLetterDate")[0].value=="")
					{
						alert("Please Enter Letter date");
						$('[name="creditLetterDate"]')[i].focus();
						return false;
					}
					if(document.getElementsByName("letterType")[0].value=="-1")
					{
						alert("Please Select Letter Type");
						$('[name="letterType"]')[0].focus();
						return false;
					}
				}
			}
			
		}
		return true;
	}, 
	showDuplicateDetail : function(){
		  var patCrNoWithIndex= $('[name="selectedDuplicatePatCrNo"]').val();//obj.value;
		  //alert("showDuplicateDetail() -->> patCrNoWithIndex :"+ $('[name="selectedDuplicatePatCrNo"]').val());
		  $('[name="patCrNo"]').val(patCrNoWithIndex);
		  submitForm("GETPATDTL"); 
	  }
 
,
//Added by warish for: cash combo
setPaymentModeRefId : function(obj){
	var paymentModeCode=obj.value.split("#")[0];
	if(paymentModeCode!="" && paymentModeCode!=-1  && paymentModeCode!=1 && paymentModeCode!=13 && paymentModeCode!=10){//1 for cash, 10 for cm relief fund, 13 for jan arogya
		$('#divFeeVal3').show();
		$('[name="paymentModeRefId"]').validatebox({
			required : true,
			validType : ['alphaNumSpecialChar','NotAllZeroWithSpclChar','consecutiveSpecialCharOnly']
		});
	}else{
		$('[name="paymentModeRefId"]').val("");
		$('[name="paymentModeRefId"]').validatebox({
			required : false,
			validType : null
		});
		$('#divFeeVal3').hide();
	}
		
},
processPaymentModeNode : function(elementName, elementNode) {
	var optionText = "";

	if (elementNode != null) {
		for ( var i = 0; i < elementNode.childNodes.length; i++) {
			var optionNode = elementNode.childNodes[i];
			optionText += "<option value='"
					+ (optionNode.getAttribute("value")) + "'>"
					+ (optionNode.getAttribute("label")) + "</option>";

		}
	}
	if (document.getElementsByName(elementName).length != 0)
		document.getElementsByName(elementName)[0].innerHTML = optionText;
}

};

$("#patRefGnctdHospitalDeptId").change(function(){
	if($("#patRefGnctdHospitalDeptId").val()=="0"){
		$("#divRefHospitalDeptOtherId").show();
		$('[name="patRefHospitalDeptOther"]').validatebox({required : true, validType: 'alphaWithSpace'});
	}
	else{
		$("#divRefHospitalDeptOtherId").hide();
		$('[name="patRefHospitalDeptOther"]').validatebox({required : false, validType: null});
	}
});

/*$("#patRefGnctdHospitalCodeId").change(function(){
	emgPatientVisitJsObj.onchange_patRefGnctdHospitalDept();
});*/

$("#submitNewId").click(function(){
	if(emgPatientVisitJsObj.checkForRefer($('[name="isReferred"]')))
		submitFormOnValidate(emgPatientVisitJsObj.validateDepartmentNewDeptVisit($('[name="departmentsToVisitStamp"]')[0]),'SAVE');
});

$("#submitOldId").click(function(){
	//alert("inside submitOldId");
	if(emgPatientVisitJsObj.checkForRefer($('[name="isReferred"]')))
		submitFormOnValidate(emgPatientVisitJsObj.validateDepartmentOldDeptVisit(),'SAVE');
});

$("#submitBothId").click(function(){
	//alert("inside submitBothId");
	var objIsRef = $('[name="isReferred"]');
	if(typeof objIsRef != undefined && objIsRef.length>0 && objIsRef[0].checked){
		if(emgPatientVisitJsObj.checkForRefer($('[name="isReferred"]')))
			emgPatientVisitJsObj.validateNewAndOldSave();
		else{
			$("#divRefDtlChkboxId").hide();
			$('[name="isRefferInOut"]')[0].checked=true;
			emgPatientVisitJsObj.showInternalExternal($('[name="isRefferInOut"]')[0]);
			$("#divRefDtlChkboxId").hide();
		}
	}else{
		emgPatientVisitJsObj.validateNewAndOldSave();
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
	
	if($('#EmgPatientVisit').form('validate'))
	{
		sortandEncodebase64($("#EmgPatientVisit"));
		document.forms[0].action = mode + "EmgPatientVisit.action";
		//alert("action :"+document.forms[0].action);
		
		if(mode=="SAVE"){
			$('[name="oldDepartmentVisit"]').attr("disabled", false);
		  	$('[name="newDepartmentVisit"]').attr("disabled", false);
		}
		document.forms[0].submit();
	}
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

/*Start: Surabhi
	 * reason: setting the organisation name*/
function setCompany() {
	
	//alert($("#clientCodeId option:selected").html());
	$('[name="clientName"]')[0].value = $("#clientCodeId option:selected").html();
	//alert("value :"+$('[name="clientName"]')[0].value);
}
//End
function submitFormonRadio(mode,obj) {
	document.forms[0].action = mode + "EmgPatientVisit.action?crno="+obj.value;
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
function dateNotInRange(obj)
{
	if(obj.value!=""){
	var _sysDate=new Date();
	var _chkDate=convertStrToDate(obj.value,"dd-Mon-yyyy");
	
	var timeDiff = Math.abs(_sysDate.getTime() - _chkDate.getTime());
	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
	
	
	if(_chkDate>_sysDate){
		alert("Refrence Letter Date Should not be Greater than Sysdate");
		obj.value="";
		return true;
	}		
	else if(diffDays>30){
		
		alert("Refrence Letter Date must be within 30 Days");
		obj.value="";
		return true;
	}
	else
		return false;
}
return false;
}

//Added by Vasu on 07.May.18
function broughtBy(obj) {
	/*if ($('[name="isUnknown"]')[0].checked == true) {
			//|| $('[name="isBroughtDead"]')[0].checked) {
		if (!obj.checked) {
			$("#isUnknownId").show();
			//$("#isRelativeKnownId").focus();
		}
		obj.checked = "true";
		$('[name="patIdMark1"]').validatebox({
			required : true//,
			//validType : 'alphaNumeric'
		});
		$('[name="patIdMark2"]').validatebox({
			required : true//,
			//validType : 'alphaNumeric'
		});
	//	$("#isRelativeKnownId").focus();
	
	$('[name="isRelative"]')[0].focus();
	} else {
		$("#isUnknownId").hide();
		
		$('[name="patIdMark1"]').validatebox({
			required : false
		});
		$('[name="patIdMark2"]').validatebox({
			required : false
		});
	}*/

	if (obj.checked == true) {
		$('[name="isRelative"]')[0].focus();
		$('[name="isRelative"]').validatebox({
			validType : [ 'selectCombo[-1]' ]
		});
		$('[name="broughtByName"]').validatebox({
			required : false,
			/*  ## 		Modification Log							
			 	##		Modify Date				:12thDec'14 
			 	##		Reason	(CR/PRS)		:Bug fix 7721
			 	##		Modify By				:Sheeldarshi 

			validType : 'alpha'*/
			validType : 'alphaWithSpace'
				
		});
		$('[name="broughtByLocation"]').validatebox({
			required : false,
			//validType : 'alphaNumeric'
			validType : 'alphaNumericWithSpaces'
			
		});

		$('[name="broughtByPhone"]').validatebox({
			required : false,
			validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
		});

		$('[name="broughtByAddress"]').validatebox({
			required : false//,
			//validType : 'alphaNumeric'
			//validType : 'alphaNumericWithSpaces'
		   //End:Sheeldarshi
		});
		

		obj.value = "1";
		document.getElementById("broughtById").style.display = "";
	} else {

		$('[name="isRelative"]').validatebox({
			validType : null
		});
		$('[name="broughtByName"]').validatebox({
			required : false
		});
		$('[name="broughtByLocation"]').validatebox({
			required : false
		});
		$('[name="broughtByPhone"]').validatebox({
			required : false
		});
		$('[name="broughtByAddress"]').validatebox({
			required : false
		});
		

		obj.value = "0";
		document.getElementById("broughtById").style.display = "none";
		document.getElementsByName("isRelative")[0].value = "-1"
		document.getElementsByName("broughtByRelationCode")[0].value = "-1"
		document.getElementsByName("broughtByName")[0].value = ""
		document.getElementsByName("broughtByLocation")[0].value = ""
		document.getElementsByName("constableDesig")[0].value = ""
		document.getElementsByName("constableBadgeNo")[0].value = ""
		document.getElementsByName("broughtByPhone")[0].value = ""
		document.getElementsByName("broughtByAddress")[0].value = ""
		document.getElementsByName("isRelative")[0].value = "-1"
		enableRelation(document.getElementsByName("isRelative")[0])
	}
}

function enableRelation(obj) {

	if (obj.value == "1")///////relative
	{
		$('[name="broughtByAddress"]').validatebox({
			required : false//,
			/*  ## 		Modification Log							
		 	##		Modify Date				:12thDec'14 
		 	##		Reason	(CR/PRS)		:Bug fix 7721
		 	##		Modify By				:Sheeldarshi 
			validType : 'alphaNumeric'*/
			//validType : 'alphaNumericWithSpaces'
			
		});
		$('[name="broughtByPhone"]').validatebox({
			required : false,
			validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
		});
		/*$('[name="broughtByRelationCode"]').validatebox({
			validType : [ 'selectCombo[-1]' ]
		});*/

		$('[name="constableDesig"]').validatebox({
			required : false,
			//validType : 'alphaNumeric'
			validType : 'alphaNumericWithSpaces'
			//End:Sheeldarshi			
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : false,
			validType : 'alphaNumeric'
		});

		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' ></font>Relative Name";
		document.getElementsByName("broughtByRelationCode")[0].disabled = false;
		document.getElementById("phoneBroughtByDiv").style.display = "block";
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("policeStnDiv").style.display = "none";
		document.getElementsByName("broughtByRelationCode")[0].value = "-1";
		document.getElementsByName("broughtByName")[0].value = "";
		document.getElementsByName("broughtByLocation")[0].value = "";
		document.getElementsByName("constableDesig")[0].value = "";
		document.getElementsByName("constableBadgeNo")[0].value = "";
		document.getElementsByName("broughtByPhone")[0].value = "";
		document.getElementsByName("broughtByAddress")[0].value = "";
		document.getElementsByName("broughtByAddress")[0].value = "";
		document.getElementsByName("policeStation")[0].value = "";

	} else {
		$('[name="broughtByPhone"]').validatebox({
			required : false
		});
		$('[name="broughtByAddress"]').validatebox({
			required : false
		});//$('[name="broughtByRelationCode"]').validatebox({validType: null});
		$("#broughtByRelationCodeId").removeClass('validatebox-invalid');
		document.getElementsByName("broughtByRelationCode")[0].disabled = true;

		//document.getElementById("phoneBroughtByDiv").innerHTML="Phone No.";			

	}
	if (obj.value == "2") {/////////POlICE Detail
		$('[name="broughtByPhone"]').validatebox({
			required : false
		});
		$('[name="broughtByAddress"]').validatebox({
			required : false
		});
		//$('[name="broughtByRelationCode"]').validatebox({validType: null});

		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' ></font>Officer Name";
		document.getElementById("policeDetailDiv").style.display = "block";
		document.getElementById("policeStnDiv").style.display = "block";
		document.getElementById("phoneBroughtByDiv").style.display = "none";

		//document.getElementById("phoneBroughtByDiv").innerHTML="Phone No.";	
		$('[name="constableDesig"]').validatebox({
			required : false,
			/*  ## 		Modification Log							
		 	##		Modify Date				:12thDec'14 
		 	##		Reason	(CR/PRS)		:Bug fix 7721
		 	##		Modify By				:Sheeldarshi 

					validType : 'alphaNumeric'*/
					validType : 'alphaNumericWithSpaces'
			
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : false,
			validType : 'alphaNumeric'
		});
		document.getElementsByName("broughtByRelationCode")[0].value = "-1"
		document.getElementsByName("broughtByName")[0].value = ""
		document.getElementsByName("broughtByLocation")[0].value = ""
		document.getElementsByName("constableDesig")[0].value = ""
		document.getElementsByName("constableBadgeNo")[0].value = ""
		document.getElementsByName("broughtByPhone")[0].value = ""
		document.getElementsByName("broughtByAddress")[0].value = ""
		document.getElementsByName("policeStation")[0].value = ""
	} else if (obj.value == "3") {
		$('[name="broughtByAddress"]').validatebox({
			required : false//,
			//validType : 'alphaNumeric'
			//validType : 'alphaNumericWithSpaces'
				//End:Sheeldarshi	
		});
		$('[name="broughtByPhone"]').validatebox({
			required : false,
			validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
		});
		//$('[name="broughtByRelationCode"]').validatebox({validType: null});

		//document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' ></font>Name";
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("policeStnDiv").style.display = "none";
		//document.getElementById("phoneBroughtByDiv").innerHTML="Phone No.";
		$('[name="constableDesig"]').validatebox({
			required : false
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : false
		});

		$('[name="constableDesig"]').validatebox({
			required : false,
			/*  ## 		Modification Log							
		 	##		Modify Date				:12thDec'14 
		 	##		Reason	(CR/PRS)		:Bug fix 7721
		 	##		Modify By				:Sheeldarshi 

					validType : 'alphaNumeric'*/
					validType : 'alphaNumericWithSpaces'
			//End:Sheeldarshi
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : false,
			validType : 'alphaNumeric'
		});
		document.getElementsByName("broughtByRelationCode")[0].value = "-1"
		document.getElementsByName("broughtByName")[0].value = ""
		document.getElementsByName("broughtByLocation")[0].value = ""
		document.getElementsByName("constableDesig")[0].value = ""
		document.getElementsByName("constableBadgeNo")[0].value = ""
		document.getElementsByName("broughtByPhone")[0].value = ""
		document.getElementsByName("broughtByAddress")[0].value = ""
		document.getElementsByName("policeStation")[0].value = ""
	}
	// in order to change the label "name" of brought by to EMT name & to diplay the combo for vehicle in case of choice by 108
	if (obj.value == "3") {
		document.getElementById("phoneBroughtByDiv").style.display = "block";
		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' ></font>Name";
		//document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#FF0000' size='2' >*</font>Phone No.";
	}

	if (obj.value == "-1")///////relative
	{
		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' ></font> Name";
		document.getElementById("phoneBroughtByDiv").style.display = "block";
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("policeStnDiv").style.display = "none";
		$('[name="broughtByAddress"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="broughtByPhone"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="broughtByRelationCode"]').validatebox({
			validType : null
		});
		$('[name="isRelative"]').validatebox({
			validType : null
		});
		$('[name="constableDesig"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="broughtByLocation"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="broughtByName"]').validatebox({
			required : false,
			validType : null
		});

		document.getElementsByName("isRelative")[0].value = "-1";
		document.getElementsByName("broughtByRelationCode")[0].value = "-1";
		document.getElementsByName("broughtByName")[0].value = "";
		document.getElementsByName("broughtByLocation")[0].value = "";
		document.getElementsByName("constableDesig")[0].value = "";
		document.getElementsByName("constableBadgeNo")[0].value = "";
		document.getElementsByName("broughtByPhone")[0].value = "";
		document.getElementsByName("broughtByAddress")[0].value = "";
		document.getElementsByName("isRelative")[0].value = "-1";
		document.getElementsByName("policeStation")[0].value = "";

	}
}


function checkForAmbulatory(obj){
	
	var trueFalse =obj.checked;
	//$('[name="isBroughtBy"]')[0].checked = true;
	
	if (!trueFalse) {
		//$('[name="isAmbulatory"]')[0].value = "0";
		//$('[name="isAmbulatory"]').val("0");
		document.getElementsByName("isAmbulatory")[0].value = "0";
		//$("#isRelativeKnownId option[value=1]").remove();
		$('[name="isBroughtBy"]')[0].checked = true;
		
		/*$('[name="isMLC"]')[0].checked = false;
		$('[name="isMLC"]')[0].disabled = true;*/
		
		/*$('[name="isUnknown"]')[0].checked = false;
		$('[name="isUnknown"]')[0].disabled = true;*/
		//document.getElementById("knownRelative").style.display = "none";
		//document.getElementById("unknownRelative").style.display = "block";
		
		
		//alert($("#idmark1mandatory")+"show");
		/*$("#idmark1mandatory").show();
		$("#idmark2mandatory").show();
		
		$('[name="patIdMark1"]').attr("tabindex","1");
		$('[name="patIdMark2"]').attr("tabindex","1");
*/
		
		
		
	} else {
		document.getElementsByName("isAmbulatory")[0].value = "1";
		$('[name="isBroughtBy"]')[0].checked = false;
		//$('[name="departmentUnitCode"]')[0].focus();
		//$('[name="isMLC"]')[0].disabled = false;
		/*$('[name="isUnknown"]')[0].disabled = false;*/
		/*var abc= $("#isRelativeKnownId").html();
		$("#isRelativeKnownId").html(abc+"<option value='1'>Relative</option>");*/

		/*$("#idmark1mandatory").hide();
		$("#idmark2mandatory").hide();
		$('[name="patIdMark1"]').attr("tabindex","2");
		$('[name="patIdMark2"]').attr("tabindex","2");*/


	}

	broughtBy($('[name="isBroughtBy"]')[0]);
	//Referred Inst Dtl
	if ($('[name="isReferred"]')[0].checked) {
		$('[name="isReferred"]')[0].checked = false;
		Isreferred($('[name="isReferred"]')[0]);
	}
}


function checkMLC(obj) {
	 //alert("call checkMLC function");
	//$('[name="departmentUnitCode"]')[0].focus();
	if (obj.checked == true) {
		obj.value = "1";
		document.getElementsByName("isMLC")[0].value="1";
	} else {
		obj.value = "0";
		document.getElementsByName("isMLC")[0].value="0";
	}
	//alert(document.getElementsByName("isMLC")[0].value);
}
//By Mukund on 25.09.2017
function manageBarcodePrintProcess()
{
	saveEntryForBarcodePrinting();
}

/*function saveEntryForBarcodePrinting() {
	
	var action = "/HISRegistration/registration/transactions/SAVEFROMOUTSIDEBarcodeGeneration.action";
	//alert(action);
	$.ajax({
				url : action,
				type : "POST",
				async : true,
				dataType : "xml",
				success : function(action) {
						//alert("success!!\n"+action);
						//do nothing
				var url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
			    
				var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=180,width=500,left=10,top=10');  
				  	child.moveTo(250,250);
				 	child.focus(); 
					if(!child.opener)
				   		child.opener = self;
				},
				error : function(errorMsg, textstatus, errorthrown) {
					alert('saveEntryForBarcodePrinting ' + errorMsg
							+ " textstatus::" + textstatus
							+ " errorthrown::" + errorthrown);

				}
			});
}*/
//End on 25.09.2017
