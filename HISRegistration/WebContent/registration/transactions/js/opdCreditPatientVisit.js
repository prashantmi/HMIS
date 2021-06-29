/**
 * Developer : Aadil
 */

$(document).ready(function(){
	 /* $.tools.dateinput.localize("en", {
		  months: 'January,February,March,April,May,June,July,August,September,October,November,December',
		  shortMonths:  'Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec',
		  days:         'Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday',
		  shortDays:    'Su,Mo,Tu,We,Th,Fr,Sa'
		  });*/
	  
	/*$(":date").dateinput({
		format: 'dd, mmmm yyyy',	// the format displayed for the user
		format: 'mm/dd/yy',
		format: 'dd-mmm-yyyy',
		selectors: true,             	// whether month/year dropdowns are shown
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
			opdPatientVisitJsObj.showHideNewAndOldVisit();
		
        if($('[name="newDepartmentVisit"]')[0].checked)
        	opdPatientVisitJsObj.checkCostOfPaidCatIsDefined();
		
		$('.letterDate').val($("#sysdateId").val());
		
		$("#clientNameLabel").html($('[name="clientName"]')[0].value);
		//$("#cardvalidityDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
		//$("#cardvalidityDateId").datepicker();
		
		//$("#creditLetterDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
		//$('.letterDate').datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
		//alert($('[name="isPatReferByList"]')[0].value);
		if( $('[name="isPatReferByList"]')[0].value=="1")
		{
		document.getElementsByName("isReferred")[0].checked=true;
		opdPatientVisitJsObj.checkIsReferred(document.getElementsByName("isReferred")[0]);
		document.getElementById("tdExtRefer").style.display="none";
		if(typeof(document.getElementsByName("refferringOPDEpisode")[0]) != 'undefined')
		document.getElementsByName("refferringOPDEpisode")[0].checked=true;
		}
		else{
			opdPatientVisitJsObj.checkIsReferred($('[name="isReferred"]')[0]);
			
			//By Default New Dept Visit Stamp to be Unchecked, Added by Singaravelan on 20-Jul-2015
			{
				$('[name="newDepartmentVisit"]')[0].checked=false;
				opdPatientVisitJsObj.showHideNewAndOldVisit();
			}
		}
		
	}
	if($("#printId").val()=="1"){
		$("#divPrintId").html($("#hiddenPrintId").val());
			if(get_object("divBarCodeControl")!=null)
			{
			get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
			
			}
			if(get_object("divBarCodeControlForBill")!=null)
				{
				get_object("divBarCodeControlForBill").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlForBill").innerHTML, 0);
				}
		if (!($('#divPrintId').is(':empty')))			
			window.print();
		else{
			if($("#divOldDeptVisitId1").is(":visible")==true)
				$("#divNormalMsgId").html("");
			else
				$("#divNormalMsgId").html("Patient Visit Saved Successfully");
		}
			
	} 
	
	
	
});	

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
var fromDeptCode="";
var refIndex="";
var countDeptVisit=0;
var catDefCharge=$('[name="patActualAmount"]')[0].value;
 
var opdPatientVisitJsObj={

	// Not in use right now. Because Ref Dept is coming now on SuperUserHospCode once
  onchange_patRefGnctdHospitalDept:function()
  {
	var refHospCode= document.getElementsByName("patRefGnctdHospitalCode")[0].value;
	var flafRefHospOrInst = refHospCode.split("#")[1];
	refHospCode	=	refHospCode.split("#")[0];
	var action 	= "/HISRegistration/registration/transactions/getRefDeptPatientCreditVisit.action?refHospCode="+refHospCode+"&flafRefHospOrInst="+flafRefHospOrInst;
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					opdPatientVisitJsObj.processGeneralNode(elementName,elementNode);
				}
					
		},error: function(errorMsg,textstatus,errorthrown) {
            alert('onchange_patRefGnctdHospitalDept '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
            
        }});
  },onchange_patPrimaryCategory:function()
  {
		var PrimaryCatObj = $('[name="patPrimaryCatCode"]')[0];
		var PrimaryCatCode= PrimaryCatObj.options[PrimaryCatObj.selectedIndex].value;
		if(PrimaryCatCode=="-1"){
			$("#divCatCardId").hide();
			$("#divSpareCatId").show();
		}
		var arrPrimaryCatCode= PrimaryCatCode.split('#');
		var primaryCatGrp=arrPrimaryCatCode[9];
		var patCatCharge=arrPrimaryCatCode[2];
		//alert("PrimaryCatCode "+PrimaryCatCode);
		
		if(PrimaryCatCode!="-1"){
			
			if(primaryCatGrp== PATIENT_REG_CATEGORY_GROUP_PAID){
					$("#patPrimaryCatCodeId").focus();
					var catWiseChargeValidType = 'validatechargeforcat['+ primaryCatGrp + ','+ patCatCharge + ']';	
					//$("#patPrimaryCatCodeId").validatebox({validType : [ catWiseChargeValidType ]});
					opdPatientVisitJsObj.alertCostOfPaidCatIsNotDefined(primaryCatGrp,patCatCharge);  
			} else {	
					$("#patPrimaryCatCodeId").focus();
					//$("#patPrimaryCatCodeId").validatebox({validType : null});	
			}
			
			if (primaryCatGrp != PATIENT_REG_CATEGORY_GROUP_BENEFICIARY
					&& primaryCatGrp != PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) {
				//alert("Inside Paid"+patCatCharge);
				if (patCatCharge == "-1") {
					$('[name="patAmountCollected"]')[0].value = "0.00";
					$('[name="patActualAmount"]')[0].value = "0.00";
				} else {
					$('[name="patAmountCollected"]')[0].value = patCatCharge;
					$('[name="patActualAmount"]')[0].value = patCatCharge;
				}
			} else {
				//alert("Inside Ben"+patCatCharge);
				if (patCatCharge == "-1") {
					$('[name="patAmountCollected"]')[0].value = "0.00";
					$('[name="patActualAmount"]')[0].value = "0.00";
				} else {
					$('[name="patAmountCollected"]')[0].value = "0.00";
					$('[name="patActualAmount"]')[0].value = patCatCharge;
				}

			}		
			
		}
		else
			$('[name="patAmountCollected"]')[0].value= "0.00";
		//alert(arrPrimaryCatCode[3]);
	    if(arrPrimaryCatCode[3]=="1")
		{
			$("#divCatCardId").show();
			$("#shownPatIdNoId").attr('maxlength',arrPrimaryCatCode[4] );
			$("#shownPatIdNoId").focus();
			//$("#shownPatIdNoId").validatebox({required: true, validType: alphanumeric});
		}
		else
		{
			$('[name="patIdNo"]')[0].value="";
			//	$("#shownPatIdNoId").validatebox({required: false, validType:null});
				$("#divCatCardId").hide();
		}
	    //alert($('[name="patAmountCollected"]')[0].value);
		
},
showhidePatIdOnload:function()
{
	var PrimaryCatObj = $('[name="patPrimaryCatCode"]')[0];
	var PrimaryCatCode= PrimaryCatObj.options[PrimaryCatObj.selectedIndex].value;
	var arrPrimaryCatCode= PrimaryCatCode.split('#');
	 if(arrPrimaryCatCode[3]=="1")
		{
			$("#divCatCardId").show();
			$("#shownPatIdNoId").attr('maxlength',arrPrimaryCatCode[4] );
			$("#shownPatIdNoId").focus();
			//$("#shownPatIdNoId").validatebox({required: true, validType: alphanumeric});
		}
		else
		{
			$('[name="patIdNo"]')[0].value="";
			//	$("#shownPatIdNoId").validatebox({required: false, validType:null});
				$("#divCatCardId").hide();
		}
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
	  //alert("opdPatientVisitJsObj.showDetail() -->> patCrNoWithIndex :"+patCrNoWithIndex);
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
		  opdPatientVisitJsObj.setExternalReferred(objIntrnlExtrnl);
	  }else{
		  //objIntrnlExtrnl.checked=false;
		  $("#divExternalReferalId").show();
		  $("#divInternalReferId").hide();
		  opdPatientVisitJsObj.setExternalReferred(objIntrnlExtrnl);
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
 		
  		$('[name="patRefGnctdHospitalCrno"]').validatebox({validType : ['numericwithoutspace','equalLengthForCrno["12|15"]','DisableAllZero']});
  		
  		$('[name="patRefDoctor"]').validatebox({required: true, validType: 'allowAlphaSpaceBracketDot'});
  		$('[name="patRefGnctdHospitalDeptUnit"]').validatebox({validType: 'allowAlphaSpaceBracketDot'});
  		
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
	// alert("inside checkIsReferred()");
	  if(!opdPatientVisitJsObj.checkForRefer()){
		  return false;
	  }
	//  alert("is corss--"+document.getElementsByName("patIsCrossConsultantWithReferal")[0].value);
	  if(obj.checked){
		   obj.value="1";
		  $("#divRefDtlChkboxId").show();
		  $('[name="isRefferInOut"]')[0].checked=true;
		  opdPatientVisitJsObj.showInternalExternal($('[name="isRefferInOut"]')[0]);
		 // if( $('[name="isPatReferByList"]')[0].value=="1"){
		   var amtWithCrConsultation=document.getElementsByName("patAmountCrConsultation")[0].value;
		  // alert("amtWithCrConsultation---"+amtWithCrConsultation);
		    if(document.getElementsByName("patIsCrossConsultantWithReferal")[0].value=="1" && document.getElementsByName("isRefferInOut")[0].checked){
			 document.getElementsByName("patAmountCollected")[0].value=amtWithCrConsultation;
			}
		 // }
	  }else{
		  obj.value="0";
		  $("#divRefDtlChkboxId").hide();
		  var amtWithoutCrConsultation=document.getElementsByName("patAmountNCrConsultation")[0].value;
		// alert("amtWithoutCrConsultation--"+amtWithoutCrConsultation);
		 if(document.getElementsByName("patIsCrossConsultantWithReferal")[0].value=="1"){
				document.getElementsByName("patAmountCollected")[0].value=amtWithoutCrConsultation;
			}
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
  	var ageBoundRange=125;
  	var departmentName="";
  	var patGender="";
  	var patPrCatGroup=$('[name="patPrimaryCatGrpCode"]')[0].value;
	 
  	//console.log("creditLetterDateId :"+ $("#creditLetterDateId").val());
  	if($('[name="isPatReferByList"]')[0].value==IS_PAT_REFER_BY_LIST_TRUE){
  		if(PATIENT_REG_CATEGORY_GROUP_BENEFICIARY == $("#patPrimaryCatGrpCodeId").val() && 
	    		opdPatientVisitJsObj.validateCatBeneficiary("new")==false)
	  	{
	    	return false;
	    }
      	isValid=true;   
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
	  		
		  	
		  	if(isValid){
		  		var depCodeArray = depCode.split('#');
			  	
			  	var defaultGenderCode=depCodeArray[6];
			  	var defaultGenderName=depCodeArray[7];
			  	
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
		  	
		  	if(isValid){
		  		isValid=opdPatientVisitJsObj.alertCostOfPaidCatIsNotDefined(patPrCatGroup,catDefCharge);
		  	}
		  	
		  	if(isValid && PATIENT_REG_CATEGORY_GROUP_BENEFICIARY == $("#patPrimaryCatGrpCodeId").val() && 
		    		opdPatientVisitJsObj.validateCatBeneficiary("new")==false)
		  	{
		    	return false;
		    }
		  	
		  	if($('[name="otherHospitalFlag"]')[0].value=="1" && $('[name="otherHospitalDataFound"]')[0].value!="1"){
		  		if($("#patPrimaryCatCodeId").val()!="-1")
		  			$('[name="patPrimaryCat"]')[0].value=$("#patPrimaryCatCodeId option:selected").text();
		  	}
	  	}

	  	
	  	
  	}
  	//alert("hey");
  	/*if($('[name="oldDepartmentVisit"]')[0].checked==true && $('[name="newDepartmentVisit"]')[0].checked==true)
  	{
  		if(isValid)
  			isValid=opdPatientVisitJsObj.validateDepartmentOldDeptVisit();
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
	  //alert("1");
	  var flagTrue=false;
	  var isRef=$('[name="isReferred"]')[0];
	  var deptCode = $('[name="departmentCode"]');
	  if( (countDeptVisit>1) && (isRef.checked)){
		  isRef.checked=false;
		//  alert("2");
		  alert("Please Select Only One Department For Visit Stamp With Refer ");
		  return false;
	  }else if (countDeptVisit>0){
		  if($('[name="newDepartmentVisit"]')[0].checked){
			//  alert("3");
			  isRef.checked=false;
			  alert("Please Do Either New Depart Visit or Old Depart Visit For Visit Stamp With Refer ");
			  return false;
		  }
	  }
	 // alert("4");
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
		//$("'#arrCreditLetterRefNo"+index+"'").addClass("letterDateValidRule");
	}else{
		countDeptVisit=countDeptVisit-1;
		//$("'#arrCreditLetterRefNo"+index+"'").removeClass("letterDateValidRule");
	}
	
	if($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2"){
		if($('[name="opdRenewalRequired"]')[0].value=="1"){
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
	  //console.log("PATIENT_REG_CATEGORY_GROUP_BENEFICIARY :"+PATIENT_REG_CATEGORY_GROUP_BENEFICIARY);
	 // console.log("patPrimaryCatGrpCode :"+$("#patPrimaryCatGrpCodeId").val());
	    
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
	  var msg="Please Collect Amount For,\n";
	  var flagSave = true;
	  var finalTotal=0;
	  if(!$('#PatientVisit').form('validate'))
		  return false;
	  if(newDepartmentVisitFlag.checked){
		  flagSave=opdPatientVisitJsObj.validateDepartmentNewDeptVisit($('[name="departmentsToVisitStamp"]')[0]);
	  }
	  
	  if(flagSave && oldDepartmentVisitFlag.checked){
		  flagSave=opdPatientVisitJsObj.validateDepartmentOldDeptVisit();
	  }
	    if(flagSave && opdPatientVisitJsObj.validateIsReferred())
	  {
		  
		  if(typeof $('[name="grandTotal"]')!=undefined && $('[name="grandTotal"]').length>0 ){
			  grandTotal=$('[name="grandTotal"]')[0].value;
		  }
		  //alert("inside flagSave && opdPatientVisitJsObj.validateIsReferred()");
		  if(newDepartmentVisitFlag.checked && oldDepartmentVisitFlag.checked){
			  if(($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2")
					  && $('[name="opdRenewalRequired"]')[0].value=="1"){
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
			  //alert("strRenewalType :"+$('[name="strRenewalType"]')[0].value);
			  finalTotal=patAmountCollected;
			  if(($('[name="strRenewalType"]')[0].value=="1" || $('[name="strRenewalType"]')[0].value=="2")
					  && $('[name="opdRenewalRequired"]')[0].value=="1"){
				
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
			  msg+="\n\n\tDo You Wish To Continue...";
			  if(confirm(msg)){
				  submitForm('SAVE');
			  }
		  }else{
			  submitForm('SAVE');
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
			else
				{
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
		rtnvalue=opdPatientVisitJsObj.alertCostOfPaidCatIsNotDefined(patPrCatGroup,catDefCharge);     	
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
	  
	  if(newDepartmentVisitFlag!='undefined' && newDepartmentVisitFlag.checked){
		  $("#divNewDeptVisitId").show();
		  $("#divCatGroupBeneficiaryId").show();
		  $("#divCatGroupArogyaShreeBeneficiaryId").show();
      	opdPatientVisitJsObj.checkCostOfPaidCatIsDefined();
      	
      	var today=new Date().toLocaleFormat('%d-%b-%Y');
		  $('#creditLetterDateId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		  });

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
		  if($('[name="opdRenewalRequired"]')[0].value!=0 && $('[name="patPrimaryCatGrpCode"]')[0].value==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)
			  $("#divCatGroupBeneficiaryId").show();
		  if($('[name="opdRenewalRequired"]')[0].value!=0 && $('[name="patPrimaryCatGrpCode"]')[0].value==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)
			  $("#divCatGroupArogyaShreeBeneficiaryId").show();
		  var today=new Date().toLocaleFormat('%d-%b-%Y');
			  $('#arrCreditLetterDate').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			  });
		  
	  }else{
		  var objdepartmentsToVisitStamp =$('[name="departmentsToVisitStamp"]');
		  if(typeof objdepartmentsToVisitStamp!= undefined && objdepartmentsToVisitStamp.length > 0){
			  for(var i=0; i<objdepartmentsToVisitStamp.length; i++)
				  objdepartmentsToVisitStamp[i].checked=false;
			  $('[name="grandTotal"]')[0].value="0.00";
			  countDeptVisit=0;
		  }
		  //alert("1");
		  $("#divOldDeptVisitId1").hide();
		  $("#divOldDeptVisitId2").hide();
		  $("#divOldDeptVisitId3").hide();
		  
	  }
	 // if(!newDepartmentVisitFlag.checked || !oldDepartmentVisitFlag.checked){
		  //$('[name="isReferred"]')[0].checked=false;
		  //$("#submitBothId").hide();
		 // alert("2");
		  opdPatientVisitJsObj.checkIsReferred($('[name="isReferred"]')[0]);
		  //alert("3");
	 // }
	/*  if(!newDepartmentVisitFlag.checked && !oldDepartmentVisitFlag.checked)
		  $("#submitBothId").hide();
	  else
		  $("#submitBothId").show();*/
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
	  //alert("inside validateIsReferred");
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
					//$('[name="isAssociated"]')[0].value = IS_ASSOCIATED_FALSE;
					if($('[name="patRefHospitalName"]')[0].value=="")
					{
						alert("Enter Referring Institute Name");
						$('[name="patRefHospitalName"]')[0].focus();
						return false;
					}
				}	
				else if ($('[name="referringInstType"]')[0].checked)
				{
					//$('[name="isAssociated"]')[0].value=IS_ASSOCIATED_TRUE;
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
			if(opdPatientVisitJsObj.validateIsReferred())
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
			
			/*if($('[name="creditBillFlag"]')[0].value=="1" && $("#clientCodeId option:selected").val()=="-1")
			{
				alert("Please Select Company");
				$('[name="clientCode"]')[0].focus();
				return false;
			}*/
			if($('[name="creditBillFlag"]')[0].value=="1" && document.getElementsByName("staffCardNo")[0].value=="")
			{
				alert("Please Enter Staff No.");
				$('[name="staffCardNo"]')[0].focus();
				return false;
			}	
			
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
			
			/*if(!isDateLessThanSysDate($('[name="creditLetterDate"]')[0].value)){
				alert("Letter Date Should not greater than Current Date.");
				$('[name="creditLetterDate"]')[0].focus();
				return false;
			}*/
			
		}else if(mode=="old"){
		
			if($('[name="creditBillFlag"]')[0].value=="1" && document.getElementsByName("staffCardNo")[0].value=="")
			{
				alert("Please Enter Staff No.");
				$('[name="staffCardNo"]')[0].focus();
				return false;
			}
			var departmentsToVisitStamp = $('[name="departmentsToVisitStamp"]');
			for(var i=0; i< departmentsToVisitStamp.length; i++){
				if(departmentsToVisitStamp[i].checked && $('[name="arrCreditBillFlag"]')[i].value=="1"){
					if($('[name="arrCreditLetterRefNo"]')[i].value==""){
						alert("Please Enter Letter Ref No.");
						$('[name="arrCreditLetterRefNo"]')[i].focus();
						return false;
						
					}
					if(document.getElementsByName("arrCreditLetterDate")[i].value=="")
					{
						alert("Please Enter Letter date");
						$('[name="arrCreditLetterDate"]')[i].focus();
						return false;
					}
					/*if(!isDateLessThanSysDate($('[name="arrCreditLetterDate"]')[i].value)){
						alert("Letter Date Should not greater than Current Date.");
						$('[name="arrCreditLetterDate"]')[i].focus();
						return false;
					}*/
				}
				
			}
		}
		return true;
	}
 
};

/*$("#patRefGnctdHospitalCodeId").change(function(){
	opdPatientVisitJsObj.onchange_patRefGnctdHospitalDept();
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
	sortandEncodebase64($("#PatientCreditVisit"));
	$("#submitNewId").hide();
	if(opdPatientVisitJsObj.checkForRefer($('[name="isReferred"]')))
		submitFormOnValidate(opdPatientVisitJsObj.validateDepartmentNewDeptVisit($('[name="departmentsToVisitStamp"]')[0]),'SAVE');
});

$("#submitOldId").click(function(){
   sortandEncodebase64($("#PatientCreditVisit"));
	//alert("inside submitOldId");
	$("#submitOldId").hide();
	if(opdPatientVisitJsObj.checkForRefer($('[name="isReferred"]')))
		submitFormOnValidate(opdPatientVisitJsObj.validateDepartmentOldDeptVisit(),'SAVE');
});

$("#submitBothId").click(function(){
	//alert("inside submitBothId");
	//$("#submitBothId").hide();
	if(!($('[name="newDepartmentVisit"]').is(':checked') || $('[name="oldDepartmentVisit"]').is(':checked'))){
		alert("Please Select any one Visit !");
		return false;
	}
	
	if($("#patPrimaryCatCodeId").is(':visible')){
		if($("#patPrimaryCatCodeId").val()=="-1"){
			alert("Please Select Patient Category !");
			$("#patPrimaryCatCodeId").focus();
			return false;
		}
		if($("#shownPatIdNoId").is(':visible')){
			if($("#shownPatIdNoId").val()==""){
				alert("Please Enter the Id !");
				$("#shownPatIdNoId").focus();
				return false;
			}
		}
	}
	var objIsRef = $('[name="isReferred"]');
	if(typeof objIsRef != undefined && objIsRef.length>0 && objIsRef[0].checked){
		if(opdPatientVisitJsObj.checkForRefer($('[name="isReferred"]'))){
			if(!opdPatientVisitJsObj.validateNewAndOldSave())
				{
				$("#submitBothId").show();
				}
		}
		else{
			$("#divRefDtlChkboxId").hide();
			$('[name="isRefferInOut"]')[0].checked=true;
			opdPatientVisitJsObj.showInternalExternal($('[name="isRefferInOut"]')[0]);
			$("#divRefDtlChkboxId").hide();
			
		}
	}else{
		//opdPatientVisitJsObj.validateNewAndOldSave();
		if(!opdPatientVisitJsObj.validateNewAndOldSave())
		{
		$("#submitBothId").show();
		}
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
$("#patPrimaryCatCodeId").change(function(){
	//alert('hjkg');
	opdPatientVisitJsObj.onchange_patPrimaryCategory();
});

//$( "input[name='selectedReffereCrNo']" ).click(opdPatientVisitJsObj.showDetail(this));

function submitForm(mode) {
	document.forms[0].action = mode + "PatientCreditVisit.action";
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
	document.forms[0].action = mode + "PatientCreditVisit.action?crno="+obj.value;
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

function isDateLessThanSysDate(objDate){
	var d1 = convertStrToDate(objDate, 'dd-Mon-yyyy');
	var ctdt = new Date();
	
	//d2.setDate(d2.getDate() + 1);
	
	if(d1>ctdt){
		return false;
	}else{
		return true;
	}
}
function setRelation()
{
	$('[name="relationNameWithStaff"]')[0].value=$("#relationWithStaffId option:selected").html();
}

function setCompany() {
	
	 //alert($("#clientCodeId option:selected").html());
	$('[name="clientName"]')[0].value = $("#clientCodeId option:selected").html();
	
}
function showCatPromptDtl(){
	submitForm("RELOADPATDTL"); 
}
function enableBenDtl()
{
	if($('[name="addBenDtl"]')[0].checked==true)
		{
		document.getElementById("AddBenDtlDiv").style.display="block";
		}
	else
		{
		document.getElementById("AddBenDtlDiv").style.display="none";
		}
}