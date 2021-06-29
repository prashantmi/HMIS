var enterCount = 0;
var delay = ( function() {
    var timer = 0;
    return function(callback, ms) {
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    };
})();


$(document).ready(function(){
	$(document).ajaxStart(function(){
		//$('.overlay1').show();
		if(parent.frameElement !=null && parent.frameElement!=undefined 
				//&& parent.frameElement.id=="frmMain"
				){
			parent.parent.ajaxStart();
		}else{
			parent.ajaxStart();
		}
	});
	$(document).ajaxComplete(function(){
		//$('.overlay1').hide();
		if(parent.frameElement !=null && parent.frameElement!=undefined 
				//&& parent.frameElement.id=="frmMain"
				){
			parent.parent.ajaxComplete();
		}else{
			parent.ajaxComplete();
		}
	});
	
	if($("#flagHasActionErrorId").val()=='false' )
	{
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
		
		var onEditCallback = function(remaining){
			$(this).siblings('.charsRemaining').text("Characters remaining: " + remaining);
			
			if(remaining > 0){
				$(this).css('background-color', 'white');
			}
		};
		
		var onLimitCallback = function(){
			$(this).css('background-color', 'red');
		};
		
		$('textarea[maxlength]').limitMaxlength({
			onEdit: onEditCallback,
			onLimit: onLimitCallback,
		});
		/**/
		checkForAmbulatory(document.getElementsByName("isAmbulatory")[0]);
		$('[name="isAmbulatory"]').prop('checked', true);
		checkForAmbulatory(document.getElementsByName("isAmbulatory")[0]);
		$('[name="isTraumaPatient"]')[0].value = "0";
		/**/
	}
	 $("#submitId").focus( function() {
	        $("#submitId").css("color","#000");
	    });
	 $("#submitId").blur( function() {
	        $("#submitId").css("color","");
	    });
	 $("#clearId").focus( function() {
		   $("#clearId").css("color","#000");
	    });
	 $("#clearId").blur( function() {
	        $("#clearId").css("color","");
	    });
	 $("#cancelId").focus( function() {
		   $("#cancelId").css("color","#000");
	    });
	 $("#cancelId").blur( function() {
	        $("#cancelId").css("color","");
	    });
});

var departmentJSONArray=[];
var categoryJSONArray=[];
var veriyDocJSONArray=[];
var defaultValJSONArray=[];
var paymentModeJSONArray =[];
var ageBoundRange=125;
var docValidType = 'alphaNumSpecialChar';
var catIdValidType='alphaNumSpecialChar';
var ageValidType = "selectComboNotSpecifiedVal['-1',1,'patGenderCode','-1','Kindly Select Department First']";
var returnDocument, isDuplicatePatientPopup = "";
var isSearchable=true;
var isBarcodeSlipPrint_Yes = 1;
var isBarcodeSlipPrint_No = 0;

var emgregistration={
		
	fetchDefaultValues:function ()
	{
		var action 	= "/HISRegistration/registration/transactions/populateformvaluesEmgRegistration.action";
		
		$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
			{
					var returnDocument=data;
					var rootNode=returnDocument.getElementsByTagName("root")[0];
					var LOCAL_LANGUAGE = rootNode.getAttribute('localLanguage');
					for(var i=0;i<rootNode.childNodes.length;i++ )
					{
						var elementNode=rootNode.childNodes[i];
						var elementName=elementNode.nodeName;
						if(elementName=='msgs')
						{
							if(elementNode.getAttribute('msg')!=""){
								$("#divNormalMsgId").html("");
								$("#divPrintId").html("");
								$("#divErrorMsgId").html(elementNode.getAttribute('msg'));
								break;
							}
						}
						else if(elementName=='departmentUnitCode')
						{
							emgregistration.processDepartmentNode(elementName,elementNode);
						}
						else if(elementName=='patPrimaryCatCode')
						{
							emgregistration.processCategoryNode(elementName,elementNode);
						}
						else if(elementName=='patDocType')
						{
							emgregistration.processVerifyDocNode(elementName,elementNode);
						}
						else if(elementName=='renewalConfig')
						{//alert(elementNode.getAttribute('aadhaarSearchable'));
							if (elementNode
									.getAttribute('aadhaarSearchable') != ""
									&& elementNode
											.getAttribute('aadhaarSearchable') != "0") {
								$("#divaadhaarRegisteredId").show("blind");
								$('[name="aadhaarSearchableFromRenewalConfig"]')[0].value = elementNode
										 .getAttribute('aadhaarSearchable');
							}
							//Start:Sheeldarshi
							//Reason:Bug 10166 - System should be able to validate senior citizen category
							if (elementNode
									.getAttribute('seniorCitizenAgeLimit') != ""
									) {
								$('[name="seniorCitizenAgeLimit"]')[0].value = elementNode
										 .getAttribute('seniorCitizenAgeLimit');
								$('[name="seniorCitizenCatCode"]')[0].value = elementNode
								 .getAttribute('seniorCitizenCatCode');
							}
							//End
							//By Mukund on 16.03.18 for BarcodeSlip print configuration
							if (elementNode
									.getAttribute('barcodeSlipPrintFlag') != null
									&& elementNode
											.getAttribute('barcodeSlipPrintFlag') != "") {
								
								//alert("elementNode.getAttribute('barcodeSlipPrintFlag'): "+elementNode.getAttribute('barcodeSlipPrintFlag'));
								$('[name="barcodeSlipPrintFlag"]')[0].value = elementNode
										 .getAttribute('barcodeSlipPrintFlag');
							}
							if (elementNode.getAttribute('isSnomedServiceOn') != null && elementNode.getAttribute('isSnomedServiceOn') != "") {
								$('[name="isSnomedServiceOn"]')[0].value = elementNode.getAttribute('isSnomedServiceOn');
							}
						}
						else if(elementName=='defaults')
						{
							
						}else if(elementName == 'paymentModeCode'){
							emgregistration.processPaymentModeNode(elementName,
									elementNode);
						}
						else
						{
							emgregistration.processGeneralNode(elementName,elementNode);
						}				
						
					}
					
					emgregistration.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
					$('[name="patAgeUnit"]')[0].value="Yr";
					$('[name="patGenderCode"]').validatebox({validType: ageValidType});
					$('[name="patMotherName"]').validatebox({required: false, validType: 'alphaWithSpace'});
					$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[-1]']});
					$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[-1]']});
					
					
					initMultilingual(LOCAL_LANGUAGE);
					
			},error: function(errorMsg,textstatus,errorthrown) {
	            alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	            
	        }
			
		});
		showDivAgeDob();
		
  },
  processDepartmentNode:function(elementName,elementNode)
  {
	var optionText="<option value='-1'>Select Value</option>";
	// for single department unit 
	var selOption="-1";
	for(var i=0;i<elementNode.childNodes.length;i++ )
	{
		var optionNode=elementNode.childNodes[i];
		//alert("JSONStr"+optionNode.getAttribute("value"));
		var departmentJSONObject=jQuery.parseJSON(optionNode.getAttribute("value"));
		departmentJSONArray[departmentJSONArray.length]=departmentJSONObject;
		optionText+="<option value='"+departmentJSONObject.deptUnitCode+"'>"+(departmentJSONObject.departmentName)+"</option>";
		//for single department unit
		if(i==0) selOption=departmentJSONObject.deptUnitCode;
	}
	//alert("processDepartmentNode::optionText :"+optionText);
	if(document.getElementsByName(elementName).length!=0)
		document.getElementsByName(elementName)[0].innerHTML=optionText;
	//for single department unit
	if(elementNode.childNodes.length==1){
		document.getElementsByName(elementName)[0].value=selOption;
		$('[name="'+elementName+'"]').trigger('change');
	}
  },
  setCategoryCmboSearchable: function(){
		//alert("isSearchable--"+isSearchable);
		//if(isSearchable){
			$("#patPrimaryCatCodeId").searchable({
					maxListSize: 100,						// if list size are less than maxListSize, show them all
					maxMultiMatch: 50,						// how many matching entries should be displayed
					exactMatch: false,						// Exact matching on search
					wildcards: true,						// Support for wildcard characters (*, ?)
					ignoreCase: true,						// Ignore case sensitivity
					latency: 200,							// how many millis to wait until starting search
					warnMultiMatch: 'top {0} matches ...',	// string to append to a list of entries cut short by maxMultiMatch 
					warnNoMatch: 'no matches ...',			// string to show in the list when no entries match
					zIndex: 'auto'							// zIndex for elements generated by this plugin
			   	
			});
		//}
	},
	
	setOrganizationCmboSearchable: function(){
		//alert("isOrganizationSearchable--"+isSearchable);
		if(isSearchable){
			$("#clientCodeId").searchable({
					maxListSize: 100,						// if list size are less than maxListSize, show them all
					maxMultiMatch: 50,						// how many matching entries should be displayed
					exactMatch: false,						// Exact matching on search
					wildcards: true,						// Support for wildcard characters (*, ?)
					ignoreCase: true,						// Ignore case sensitivity
					latency: 200,							// how many millis to wait until starting search
					warnMultiMatch: 'top {0} matches ...',	// string to append to a list of entries cut short by maxMultiMatch 
					warnNoMatch: 'no matches ...',			// string to show in the list when no entries match
					zIndex: 'auto',							// zIndex for elements generated by this plugin
					
			});
		}
		//$("#clientCodeId").removeAttr("disabled");
	},
  processCategoryNode:function(elementName,elementNode)
  {
	var optionText="<option value='-1'>Select Value</option>";
	var selOption="-1";
	for(var i=0;i<elementNode.childNodes.length;i++ )
	{
		var optionNode=elementNode.childNodes[i];
		//alert("JSONStr"+optionNode.getAttribute("value"));
		var categoryJSONObject=jQuery.parseJSON(optionNode.getAttribute("value"));
		categoryJSONArray[categoryJSONArray.length]=categoryJSONObject;
		optionText+="<option value='"+categoryJSONObject.patPrimaryCatCode+"'>"+(categoryJSONObject.patPrimaryCatName)+"</option>";
		// For single category
		if(i==0) selOption=categoryJSONObject.patPrimaryCatCode;
	}
	/*$('[name="'+elementName+'"]').parent()[0].innerHTML='<select name="patPrimaryCatCode" id="patPrimaryCatCodeId" tabindex="1" class="select90prcnt" onchange="emgregistration.onchange_patPrimaryCategory()">'+
	+'<option value="-1">Select Value</option>'
	+'</select>';*/
	
	var parId = $('[name="'+elementName+'"]').closest('div').attr('id');
	if(parId==undefined)
		parId = "_divpatPrimaryCatCodeId";
	document.getElementById(''+parId+'').innerHTML = '<select name="patPrimaryCatCode" id="patPrimaryCatCodeId" tabindex="1" class="select90prcnt" onchange="emgregistration.onchange_patPrimaryCategory()">'+
	+'<option value="-1">Select Value</option>'
	+'</select>';
	
			
	if (document.getElementsByName(elementName).length != 0){
		document.getElementsByName(elementName)[0].innerHTML = optionText;
		emgregistration.setCategoryCmboSearchable();
		emgregistration.setOrganizationCmboSearchable();
	}
	
	// For single category
	if(elementNode.childNodes.length==1){
		document.getElementsByName(elementName)[0].value=selOption;
		$('[name="'+elementName+'"]').trigger('change');
	}
	$("#patPrimaryCatCodeId").validatebox({
		validType : [ 'selectCombo[-1]' ]
	});
	
	 if(document.getElementsByName(elementName).length>=0)  // added by manisha for setting default patcat 'general'
	 {	  $('[name="'+elementName+'"]')[0].value = "11";
	 var priCatObj = $('[name="patPrimaryCatCode"]')[0];
	 if(priCatObj.selectedIndex == undefined || priCatObj.selectedIndex == null || priCatObj.selectedIndex == "-1")
		 $('[name="'+elementName+'"]')[0].value = "-1";

		emgregistration.onchange_patPrimaryCategory();
	 }
	 
  },
  processVerifyDocNode:function(elementName,elementNode)
  {
	var optionText="<option value='-1'>Select Value</option>";
	for(var i=0;i<elementNode.childNodes.length;i++ )
	{
		var optionNode=elementNode.childNodes[i];
		//alert("JSONStr"+optionNode.getAttribute("value"));
		var veriyDocJSONObject=jQuery.parseJSON(optionNode.getAttribute("value"));
		veriyDocJSONArray[veriyDocJSONArray.length]=veriyDocJSONObject;
		optionText+="<option value='"+veriyDocJSONObject.patDocType+"'>"+(veriyDocJSONObject.patDocTypeName)+"</option>";
		
	}
	if(document.getElementsByName(elementName).length!=0)
		document.getElementsByName(elementName)[0].innerHTML=optionText;
	
  },
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
	if(elementName=="patRefGnctdHospitalDept"){
		optionText+="<option value='0'>Other</option>";
	}
	
	if(document.getElementsByName(elementName).length!=0)
			document.getElementsByName(elementName)[0].innerHTML=optionText;
	
  },
  setDepartmentDependents:function(deptObj)
  {
	  
	var deptCode= deptObj.options[deptObj.selectedIndex].value;
	if(deptCode=="-1"){
		$('[name="defaultpatGenderCode"]')[0].value ="0";
		ageValidType="selectComboNotSpecifiedVal['-1',1,'patGenderCode','-1','Kindly Select Department First']";
		
		return;
	}
	for(var i=0; i<departmentJSONArray.length; i++){
		if(departmentJSONArray[i].deptUnitCode==deptCode){
			
			$('[name="patGenderCode"]')[0].value=departmentJSONArray[i].defaultGenderCode;
			$('[name="defaultpatGenderCode"]')[0].value=departmentJSONArray[i].defaultGenderCode;
			$('[name="departmentCode"]')[0].value=departmentJSONArray[i].departmentCode;

			$('[name="defaultpatGenderCode"]')[0].value=departmentJSONArray[i].defaultGenderCode;
			
			/***/
			if($('#userBoundDefaultGenderId').val()!=departmentJSONArray[i].defaultGenderCode){
				var defaultGenderName2 = $(
						"#patGenderCodeId option[value='"
								+ $('#userBoundDefaultGenderId').val()
								+ "']").text();
				//alert("User is bounded to "+defaultGenderName2+" gender");
			}	
			/***/
			
			//document.getElementsByName("ageRange")[0].value=departmentJSONArray[i].ageBound;
			ageBoundRange=departmentJSONArray[i].ageBound;
			if(departmentJSONArray[i].defaultGenderCode != "-1")
			{
			var defaultGenderName = $("#patGenderCodeId option[value='" + departmentJSONArray[i].defaultGenderCode + "']").text();
			ageValidType="selectComboNotSpecifiedVal['"+departmentJSONArray[i].defaultGenderCode+"',1,'patGenderCode','-1','Only "+defaultGenderName+ " is allowed in this Department']";
			
			//$("#patGenderCodeId").removeClass('validatebox-text');
			$("#patGenderCodeId").removeClass('validatebox-invalid');
			$('[name="patGenderCode"]').validatebox({validType: ageValidType});

			}
		else
			{
			ageValidType=null;
			$('[name="patGenderCode"]').validatebox({validType:ageValidType});
			$('[name="patGenderCode"]').validatebox({
				validType: ['selectCombo[-1]']
			});
			}
			if(typeof ageBoundRange == undefined || ageBoundRange=="")
				ageBoundRange=125;
			//Start:Sheeldarshi
			//Reason:Bug 10166 - System should be able to validate senior citizen category	
			/*if($('[name="isActualDob"]')[0].checked){
				var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
				var ageRangeValidType='range[1,'+maxAgeRange+']';
				$("#patAgeId").validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
			}else{
				$("#patAgeId").validatebox({required: false});
				var dobValidType='dtltetDMY['+ageBoundRange+',\'y\']';
				$("#patDOBId").validatebox({required: true, validType: ['date[\'patDOB\',\'dd-mmm-yyyy\']', dobValidType]});
			}*/
			showDivAgeDob();
			//End
			break;
		}
	}
  },
  setdefaultVariables:function(elementNode)
  {
	for(var i=0;i<elementNode.attributes.length;i++ )
	{
		eval("var "+elementNode.attributes[i].name+"='"+elementNode.attributes[i].value+"'");
		if (!(elementNode.attributes[i].name == "defaultpatAddPIN"))
			document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].value = eval(elementNode.attributes[i].name);
		if(elementNode.attributes[i].name=="defaultpatAddCountryCode"){
			document.getElementsByName("defaultpatAddCountryCode")[0].value=eval(elementNode.attributes[i].name);
		}else if(elementNode.attributes[i].name=="defaultpatAddStateCode"){
			document.getElementsByName("defaultpatAddStateCode")[0].value=eval(elementNode.attributes[i].name);
		}else if(elementNode.attributes[i].name=="defaultpatAddDistrictCode"){
			document.getElementsByName("defaultpatAddDistrictCode")[0].value=eval(elementNode.attributes[i].name);
		}/*else if (elementNode.attributes[i].name == "defaultpatAddPIN") {
			document.getElementsByName("patAddPIN")[0].value = eval(elementNode.attributes[i].name);
		}*/else if (elementNode.attributes[i].name == "defaultuserBoundDefaultGender") {
			if(eval(elementNode.attributes[i].name)=='0')
				document.getElementsByName("patGenderCode")[0].value = '-1';
			else
				document.getElementsByName("patGenderCode")[0].value = eval(elementNode.attributes[i].name);
			//alert(eval(elementNode.attributes[i].name));
		}
	}
	$("#patAddCountryCodeId").bind("change",emgregistration.onchange_patAddCountryCode);
	
	
  },
  onchange_patAddCountryCode:function()
  {
	var objCountry  =document.getElementsByName("patAddCountryCode")[0];
	var countryCode = objCountry.options[objCountry.selectedIndex].value;
	var action 		= "/HISRegistration/registration/transactions/getStateEmgRegistration.action?countryCode="+countryCode;
	var flagStateNDIstComboReqd=true;

	document.getElementsByName("patAddCountry")[0].value=objCountry.options[objCountry.selectedIndex].text;
	
	if(countryCode!="-1" && countryCode!=document.getElementsByName("defaultpatAddCountryCode")[0].value)
	{
		$('[name="patAddStateCode"]')[0].value="-1";
		$("#divStateComboId").hide();
		$("#divStateTextId").show();
		$('[name="patAddState"]')[0].value="";
		$('[name="patAddDistrictCode"]')[0].value="-1";
		$("#divDistrictComboId").hide();
		$("#divDistrictTextId").show();
		$('[name="patAddDistrict"]')[0].value="";
		flagStateNDIstComboReqd=false;
		//$('[name="defaultpatAddPIN"]')[0].value=$('[name="patAddPIN"]')[0].value;
		$('[name="patAddPIN"]')[0].value = "";
	}
	else
	{
		$("#divStateTextId").hide();
		$("#divStateComboId").show();
		$("#patAddStateCodeId").html("<option value='-1'>Select Value</option>");
		$("#divDistrictTextId").hide();
		$("#divDistrictComboId").show();
		$("patAddDistrictCodeId").html("<option value='-1'>Select Value</option>");
		//$('[name="patAddPIN"]')[0].value=$('[name="defaultpatAddPIN"]')[0].value;
	}
	
	
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					emgregistration.processGeneralNode(elementName,elementNode);
					if($('[name="patAddCountryCode"]')[0].value==$('[name="defaultpatAddCountryCode"]')[0].value)
						$('[name="patAddStateCode"]')[0].value=$('[name="defaultpatAddStateCode"]')[0].value
				}
				
				if(flagStateNDIstComboReqd)
				{
					$("#patAddStateId").validatebox({required: false,	validType: 'alphaNumSpecialChar' });
					$("#patAddDistrictId").validatebox({required: false,	validType: 'alphaNumSpecialChar' });
					$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[-1]']});
					$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[-1]']});
					//$("#patAddStateCodeId").bind("change",emgregistration.onchange_patAddStateCode);
					emgregistration.onchange_patAddStateCode();
				}
				else
				{
					$("#patAddStateId").validatebox({required: true,	validType: 'alphaNumSpecialChar' });
					$("#patAddDistrictId").validatebox({required: true,	validType: 'alphaNumSpecialChar' });
					$('[name="patAddStateCode"]').validatebox({validType: null});
					$('[name="patAddDistrictCode"]').validatebox({validType: [null]});
				}
					
		},error: function(errorMsg,textstatus,errorthrown) {
            alert('onchange_patAddCountryCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
            
        }});
  },
  onchange_patAddStateCode:function()
  {
	var countryCode= document.getElementsByName("patAddCountryCode")[0].value;
	var objState =document.getElementsByName("patAddStateCode")[0];
	var stateCode = objState.options[objState.selectedIndex].value;
	var action 			= "/HISRegistration/registration/transactions/getDistrictEmgRegistration.action?countryCode="+countryCode+"&stateCode="+stateCode;
	
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					emgregistration.processGeneralNode(elementName,elementNode);
					if($('[name="patAddStateCode"]')[0].value==$('[name="defaultpatAddStateCode"]')[0].value)
						$('[name="patAddDistrictCode"]')[0].value=$('[name="defaultpatAddDistrictCode"]')[0].value;
				}
					
		},error: function(errorMsg,textstatus,errorthrown) {
            alert('onchange_patAddCountryCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
            
        }});
  }, 
  onchange_patRefGnctdHospitalDept:function()
  {
	var refHospCode= document.getElementsByName("patRefGnctdHospitalCode")[0].value;
	var flafRefHospOrInst = refHospCode.split("#")[1];
	refHospCode	=	refHospCode.split("#")[0];
	var action 	= "/HISRegistration/registration/transactions/getRefDeptEmgRegistration.action?refHospCode="+refHospCode+"&flafRefHospOrInst="+flafRefHospOrInst;
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					emgregistration.processGeneralNode(elementName,elementNode);
				}
					
		},error: function(errorMsg,textstatus,errorthrown) {
            alert('onchange_patRefGnctdHospitalDept '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
            
        }});
  }, 
  onchange_patPrimaryCategory:function()
  {

		var PrimaryCatObj = $('[name="patPrimaryCatCode"]')[0];
		var PrimaryCatName = PrimaryCatObj.options[PrimaryCatObj.selectedIndex].text;
		var PrimaryCatCode= PrimaryCatObj.options[PrimaryCatObj.selectedIndex].value;
		if(PrimaryCatCode=="-1")
		{
			$("#divCatCardId").hide();
			$("#divImgCatCardId").hide();
			$("#divSpareCatId").show();
			
			$("#divFeeVal").hide();$("#divFeeVal2").hide();$("#divFeeVal3").hide();
			$('[name="paymentModeCode"]').val("1");
			$("#divFeeLabel").hide();
			
			showHideCatGroupBeneficiaryTile("hide");
			$("#patPrimaryCatCodeId").validatebox({validType : [ 'selectCombo[-1]' ]});
			
		}
		else
		{
			$("#divFeeVal").show();$("#divFeeVal2").show();
			$("#divFeeLabel").show();

			/*var pymntMdeCde = "paymentModeCode";
			var paymentModeLength = $('[name="paymentModeCode"]').children('option').length;
			for(var w=0; w<paymentModeLength; w++){
				var paymentModeObj = $('[name="paymentModeCode"]')[0];
				var paymentModeName = paymentModeObj.options[w].text;
				if(PrimaryCatName.toUpperCase() === paymentModeName.toUpperCase()){
					$('[name='+pymntMdeCde+'] option:contains(' + paymentModeName + ')').attr('selected', 'selected');
					emgregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
					break;
				}else{
					
					//$('[name="paymentModeCode"]').val("1");
					//emgregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
					$('[name="paymentModeCode"]').val(paymentModeObj.options[0].value);
					emgregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);

				}
			}*/  emgregistration.processPaymentModeNode_onCatChange(PrimaryCatCode);

		}
		
		for(var i=0; i<categoryJSONArray.length; i++)
		{
			if(categoryJSONArray[i].patPrimaryCatCode==PrimaryCatCode)
			{
				/*  ## 		Modification Log							
		 		##		Modify Date				:10thMar'15 
		 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
		 		##		Modify By				:Sheeldarshi 
				 */
				
				if(categoryJSONArray[i].patCatIsApprovalReq=="1")
				{
					document.getElementById('divRMO').style.display = "";
					$('[name="patRMOEmployee"]').validatebox({
						required:true,
						validType : [ 'selectCombo[-1]' ]
					});
				}
				else{
					document.getElementById('divRMO').style.display = "none";
					$('[name="patRMOEmployee"]').validatebox({
						required:false,
						validType : null
					});
				}
				//End
				//Start:Sheeldarshi
				//Reason:Bug 10166 - System should be able to validate senior citizen category		
				showDivAgeDob();
				//End
					if(categoryJSONArray[i].patCatGroup==PATIENT_REG_CATEGORY_GROUP_PAID)
					{			    
						$("#patPrimaryCatCodeId").focus();
						var catWiseChargeValidType='validatechargeforcat['+categoryJSONArray[i].patCatGroup+','+categoryJSONArray[i].charges+']';
						$("#patPrimaryCatCodeId").validatebox({validType: [catWiseChargeValidType]});
					}
					else
					{
					
						$("#patPrimaryCatCodeId").focus();
						$("#patPrimaryCatCodeId").validatebox({validType:null});
					
					}
				
								
					if(categoryJSONArray[i].patCatGroup!=PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE &&
							categoryJSONArray[i].patCatGroup!=PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE){
						if(categoryJSONArray[i].charges=="-1"){
							 $('[name="patAmountCollected"]')[0].value=0;
							 $('[name="patActualAmount"]')[0].value=0;
							 document.getElementById("amount").innerHTML  = 0;
						}
						else
							{
								$('[name="patAmountCollected"]')[0].value= categoryJSONArray[i].charges;
								$('[name="patActualAmount"]')[0].value= categoryJSONArray[i].charges;
								document.getElementById("amount").innerHTML  = categoryJSONArray[i].charges;
							}
					}
					else{
						
						if(categoryJSONArray[i].charges=="-1"){
							 $('[name="patAmountCollected"]')[0].value=0;
							 $('[name="patActualAmount"]')[0].value=0;		
							 document.getElementById("amount").innerHTML  = 0;
						}
						else
							{
							 $('[name="patAmountCollected"]')[0].value=0;  
							$('[name="patActualAmount"]')[0].value= categoryJSONArray[i].charges;
							document.getElementById("amount").innerHTML  = 0;
							}
						
					}			
				
					
				
				$('[name="isIdRequired"]')[0].value=categoryJSONArray[i].idReqFlag;
				$('[name="patPrimaryCat"]')[0].value= categoryJSONArray[i].patPrimaryCatName;
				$('[name="patCatShortName"]')[0].value= categoryJSONArray[i].patCatShortName;
				$('[name="patPrimaryCatGrp"]')[0].value= categoryJSONArray[i].patCatGroup;
				
				$('[name="patCatDocCode"]')[0].value= categoryJSONArray[i].patCatDocCode;
				$('[name="patCatDocIsAlternateId"]')[0].value= categoryJSONArray[i].patCatDocIsAlternateId;
	
				clientCode = categoryJSONArray[i].clientCode;
				clientName = categoryJSONArray[i].clientName;
				
				console.log("patCatGroup :"+categoryJSONArray[i].patCatGroup);			
				
				//PATIENT_REG_CATEGORY_GROUP_BENEFICIARY=0;
				if(categoryJSONArray[i].patCatGroup==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE){
					showHideCatGroupBeneficiaryTile("show");
				}
				else if(categoryJSONArray[i].patCatGroup==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE){
					showHideCatGroupBeneficiaryTile("showAGS");
				}
				else{
					showHideCatGroupBeneficiaryTile("hide");
				}
				
				
				catIdValidType= emgregistration.getDocValidtype(categoryJSONArray[i].idValidationType);
				//alert("categoryJSONArray[i].idReqFlag :"+categoryJSONArray[i].idReqFlag);
				if (categoryJSONArray[i].idReqFlag == "-1")
					categoryJSONArray[i].idReqFlag="0";
				if(categoryJSONArray[i].idReqFlag=="0")
				{
					$('[name="patIdNo"]')[0].value="";
					$("#shownPatIdNoId").validatebox({required: false, validType: null});
					$("#divCatCardId").hide();
					$("#divImgCatCardId").hide();
					$("#divSpareCatId").show();
					$("#divAlreadyRegisteredId").show();
					if($("#isMandataryReadOnlyTrueId").val()=="1")
						setMandatoryReadOnlyTrueFalse(false);
				}
				else if(categoryJSONArray[i].idReqFlag=="1")
				{
					$("#divCatCardId").show();
					$("#divCatCardNoLabelId").html(getCatCardNameBasedOnCatGroup(categoryJSONArray[i].patCatGroup));
					$("#shownPatIdNoId").attr('maxlength', categoryJSONArray[i].idSize);
					$("#divImgCatCardId").hide();
					$("#divSpareCatId").hide();
					$("#divAlreadyRegisteredId").show();
					$("#shownPatIdNoId").validatebox({required: true, validType: [catIdValidType,'NotAllZeroWithNumber']});
					
					if($("#isMandataryReadOnlyTrueId").val()=="1")
						setMandatoryReadOnlyTrueFalse(false);
				}
				else if(categoryJSONArray[i].idReqFlag=="2")
				{
						$("#shownPatIdNoId").validatebox({required: false, validType:null});
						$("#divCatCardId").hide();
						$("#divSpareCatId").hide();
						$("#divImgCatCardId").show();
						$("#imgCatCardId").hide();
						
						if($("#hiddenCatOrRegstrdPopupFlagId").val()!="A"){
							$("#imgCatCardId").show();
							$("#divAlreadyRegisteredId").hide();
							$('[name="isMandataryReadOnlyTrue"]')[0].value="1";
							setMandatoryReadOnlyTrueFalse(true);
						}
				}
				
				break;
			}
		}
  }, 
  onchange_patDocType:function()
  {
	  	var docTypeObj = $('[name="patDocType"]')[0];
		var docType= docTypeObj.options[docTypeObj.selectedIndex].value;
		if(docType!="-1"){
			$("#divCardNoId").show();
		}else{
			$('[name="patCardNo"]').validatebox({required: false});
			$('[name="patCardNo"]').val("");
			$("#divCardNoId").hide();
		}
		for(var i=0; i<veriyDocJSONArray.length; i++){
			if(veriyDocJSONArray[i].patDocType==docType){
				$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);
				//alert("patDocIdSize :"+ veriyDocJSONArray[i].patDocIdSize);
				docValidType= emgregistration.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);
				
				var isize = veriyDocJSONArray[i].patDocIdSize;
				
				if(isize!="")
				{
			var docValidTypeNLength = 'equalLength[' + isize + ']';
			$('[name="patCardNo"]').validatebox({
				required : true,
				validType : [ docValidType, docValidTypeNLength,'NotAllZero' ]
			});
				}
			else
				{
				$('[name="patCardNo"]').validatebox({
					required : true,
					validType : [ docValidType]
				});
				}
				
				break;
			}else{
				$('[name="patCardNo"]').validatebox({required: false});
			}
		}
  },
  getDocValidtype:function(idValidTypeCode)
  {
	  var varDocValidType='';
	if(idValidTypeCode=="0"){
		varDocValidType = 'alphaNumSpecialChar';
	}else if(idValidTypeCode=="1"){
		varDocValidType = 'numeric';
	}else if(idValidTypeCode=="2"){
		varDocValidType = 'alphaNumeric';
	}else if(idValidTypeCode=="3"){
		varDocValidType = 'alpha';
	}
	return varDocValidType;
  },
  populatePatientDtlFromJsonVOObj:function(elementNode)
  {
	//alert("inside populatePatientDtl");
	
	var patientJSONObject=jQuery.parseJSON(elementNode.getAttribute("value"));
	for (var patientKey in patientJSONObject) {
		 if (patientJSONObject.hasOwnProperty(patientKey)) {
		    var patientVal = patientJSONObject[patientKey];
		    //alert("patientKey :"+patientKey+", patientVal :"+patientVal);
		    if(document.getElementsByName(patientKey) && document.getElementsByName(patientKey)[0]!=undefined && patientKey != "patAddress"){
		    	eval("var "+patientKey+"='"+patientVal+"'");
		    	document.getElementsByName(patientKey)[0].value=eval(patientKey);
		    }
		    
		 }
	 }
	
  },
  populatePatientDtl:function(patientJSONObject)
  {
	//alert("inside populatePatientDtl");
	
	for (var patientKey in patientJSONObject) {
		 if (patientJSONObject.hasOwnProperty(patientKey)) {
		    var patientVal = patientJSONObject[patientKey];
		    //alert("patientKey :"+patientKey+", patientVal :"+patientVal);
		    if(document.getElementsByName(patientKey)  && document.getElementsByName(patientKey)[0]!=undefined){
		    	eval("var "+patientKey+"='"+patientVal+"'");
		    	/*Added by Mukund on 23.07.2016*/
		    	if (patientKey == "patGenderCode" || patientKey == "patAgeUnit")
					document.getElementsByName(patientKey)[0].value="-1";
				else
				document.getElementsByName(patientKey)[0].value="";
				
				if(eval(patientKey)!=null && eval(patientKey)!="" && eval(patientKey)!="-N.A-")
		    	 document.getElementsByName(patientKey)[0].value=eval(patientKey);
				/*End: Mukund*/
		    	if(patientKey=="patPrimaryCatCode")
		    	{
		    		if(patientVal!=""){
						document.getElementsByName("strEmployeePatCatGroup")[0].value=document.getElementsByName("patPrimaryCatGrp")[0].value;
						if(eval(patientKey) !=$('[name="patOldCatCode"]')[0].value)
						{						
							emgregistration.onchange_patPrimaryCategory();
						$("#divImgCatCardId").hide("blind");
						}
						
		    		}
		    	}
		    	if (patientKey== "patAddStateCode")
				{
					if(patientVal!=""){
					emgregistration.onchange_patAddStateCode();
					}
					
				}
				
				if (patientKey== "patRelatedEmpName")
				{
					document.getElementsByName("patMemRelationCode")[0].value=eval(patientKey);
				}
				if (patientKey== "patRelatedEmpGender")
				{
					
				var relationCode= eval(patMemRelationCode);
				var gender = eval(patientKey);
				var relatedName=document.getElementsByName("patMemRelationCode")[0].value;
				
				if(relationCode==5)
				{	
					if(gender =="M")
					{
						$('[name="patGuardianName"]')[0].value=relatedName;
					}
					else if(gender =="F")
						$('[name="patMotherName"]')[0].value=relatedName;
				}
				else if(relationCode==2)
				{
					$('[name="patHusbandName"]')[0].value =relatedName;
				}
				else if(relationCode==6)
				{
					if(gender =="M")
					{
						$('[name="patGuardianName"]')[0].value=relatedName;
					}
					else if(gender =="F")
						$('[name="patMotherName"]')[0].value=relatedName;
				}
					}
		    	//document.getElementsByName(patientKey)[0].disabled=true;
		    }
		    
		 }
	 }
	//setMandatoryRefdReadOnlyTrueFalse(false);
	setMandatoryReadOnlyTrueFalse(false);
	
  },
  
  setPatientDistrictDtlFromWhiteCardService : function(patientJSONObject) {
	  
	  delay(function(){
		    // do stuff
				if(patientJSONObject.hasOwnProperty("patAddDistrictCode"))
				document.getElementsByName("patAddDistrictCode")[0].value =patientJSONObject["patAddDistrictCode"];
				if(patientJSONObject.hasOwnProperty("patAddPIN"))
				$('[name="patAddPIN"]')[0].value =patientJSONObject["patAddPIN"];
		},400 ); 
	},
  fetchPatDtlBasedOnPatCatCardNo:function(patientJSONObject)
  {
	//alert("inside fetchPatDtlBasedOnPatCatCardNo");
	emgregistration.populatePatientDtl(patientJSONObject);
	emgregistration.setPatientDistrictDtlFromWhiteCardService(patientJSONObject);
	/*if($('[name="patDOB"]')[0].value!="")
		$('[name="isActualDob"]')[1].checked=true;*/
	
  },
  fetchPatDtlBasedOnPatCatWhiteCardNo : function(patientJSONObject) {
		
	  emgregistration.populatePatientDtl(patientJSONObject);
		//opdregistration.onchange_patAddStateCode();
	  emgregistration.setPatientDistrictDtlFromWhiteCardService(patientJSONObject);
		

	},
  savePatientDtl:function()
  {
	  if(!validateDivDob()) return false;
	  
	  if( $('[name="patAddCountryCode"]')[0].value == $('[name="defaultpatAddCountryCode"]')[0].value){
		  $('[name="patAddState"]')[0].value 	= $("#patAddStateCodeId option:selected").text();
		  $('[name="patAddDistrict"]')[0].value = $("#patAddDistrictCodeId option:selected").text();
	  }
	  $('[name="patAddCountry"]')[0].value 		= $("#patAddCountryCodeId option:selected").text();
	  $('[name="patGender"]')[0].value 		= $("#patGenderCodeId option:selected").text();
	  
	  if($("#patCasteCodeId").val()!="-1")
		  $('[name="patCaste"]')[0].value 		= $("#patCasteCodeId option:selected").text();
	  if($("#patDocTypeId").val()!="-1")
		  $('[name="patDocTypeName"]')[0].value = $("#patDocTypeId option:selected").text();

	  if($("#patMaritalStatusCodeId").val()!="-1")
		  $('[name="patMaritalStatus"]')[0].value 		= $("#patMaritalStatusCodeId option:selected").text();
	  
	  if($("#patReligionCodeId").val()!="-1")
		  $('[name="patReligion"]')[0].value 		= $("#patReligionCodeId option:selected").text();
		if(document.getElementById('divRMO').style.display == "")
		{
			$('[name="patRMOEmployee"]').validatebox({
			validType : [ 'selectCombo[-1]' ]
		});
		}
		else
			{
			$('[name="patRMOEmployee"]').validatebox({
				validType : null
			});
			}
	  //return false;
	  //$('[name="patPrimaryCat"]')[0].value= $("#patPrimaryCatCodeId option:selected").text();
	  	//For Submission
		sortandEncodebase64($("#EmgRegistration"));
	  	var action 	= "/HISRegistration/registration/transactions/saveEmgRegistration.action";
	  	$('#submitId').hide();
		$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,data:$("#EmgRegistration").serialize(), success:function(data){
			var returnDocument=data;
			var rootNode=returnDocument.getElementsByTagName("root")[0];
			
			var elementNode=rootNode.childNodes[0];
			var elementName=elementNode.nodeName;
			if(elementName=='savedMsgDtl')
			{
				//Start:Sheeldarshi:17thOct'14:Duplicacy check
				isDuplicatePatientPopup = elementNode
				.getAttribute("isDuplicatePatientPopup");
				//End:Sheeldarshi:17thOct'14:Duplicacy check
				emgregistration.initializeAfterSave(elementName,elementNode);
			}
			$('#submitId').show();
			
		},error: function(errorMsg,textstatus,errorthrown) {
			$('#submitId').show();
		    alert('savePatientDtl '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		    
		}});
  },
  //Start:Sheeldarshi:17thOct'14:Duplicacy check
  saveAsNewPatientDtl : function() {

		if ($('[name="patAddCountryCode"]')[0].value == $('[name="defaultpatAddCountryCode"]')[0].value) {
			$('[name="patAddState"]')[0].value = $(
					"#patAddStateCodeId option:selected").text();
			$('[name="patAddDistrict"]')[0].value = $(
					"#patAddDistrictCodeId option:selected").text();
		}
		$('[name="patAddCountry"]')[0].value = $(
				"#patAddCountryCodeId option:selected").text();
		$('[name="patGender"]')[0].value = $("#patGenderCodeId option:selected")
				.text();
		if ($("#patMaritalStatusCodeId").val() != "-1")
			$('[name="patMaritalStatus"]')[0].value = $(
					"#patMaritalStatusCodeId option:selected").text();

		if ($("#patReligionCodeId").val() != "-1")
			$('[name="patReligion"]')[0].value = $(
					"#patReligionCodeId option:selected").text();

		if ($("#patCasteCodeId").val() != "-1")
			$('[name="patCaste"]')[0].value = $(
					"#patCasteCodeId option:selected").text();
		if ($("#patDocTypeId").val() != "-1")
			$('[name="patDocTypeName"]')[0].value = $(
					"#patDocTypeId option:selected").text();

		sortandEncodebase64($("#EmgRegistration"));
		var action = "/HISRegistration/registration/transactions/saveAsNewPatientEmgRegistration.action";
		$('#submitId').hide();
		$.ajax({
			url : action,
			type : "POST",
			async : true,
			dataType : "xml",
			data : $("#EmgRegistration").serialize(),
			success : function(data) {
				// alert("data :"+data);
				var returnDocument = data;
				temprtnData = data;
				var rootNode = returnDocument.getElementsByTagName("root")[0];
				// alert("inside savePatientDtl 1");
				var elementNode = rootNode.childNodes[0];
				var elementName = elementNode.nodeName;

				if (elementName == 'savedMsgDtl') {
					isDuplicatePatientPopup = elementNode
							.getAttribute("isDuplicatePatientPopup");
					emgregistration.initializeAfterSave(elementName,
							elementNode);
				}
				$('#submitId').show("blind");

			},
			error : function(errorMsg, textstatus, errorthrown) {
				$('#submitId').show("blind");
				alert('savePatientDtl ' + errorMsg + " textstatus::"
						+ textstatus + " errorthrown::" + errorthrown);

			}
		});

	},
  //End:Sheeldarshi:17thOct'14:Duplicacy check
  initializeAfterSave:function(saveMsgElementName,saveMsgElementNode)
  {
	  $('#fatherorSpouseError').hide();
	  var isSavedSuccussful	   = saveMsgElementNode.getAttribute("isSavedSuccussful");
	  var msg				   = saveMsgElementNode.getAttribute("savedMessage");
	  var isFormFieldTobeReset = saveMsgElementNode.getAttribute("isFormFieldTobeReset");
	  var isPrintFlag	   	   = saveMsgElementNode.getAttribute("isPrintFlag");
	  var printDivContent	   = saveMsgElementNode.getAttribute("printDivContent");
	  var tokenToSet		   = saveMsgElementNode.getAttribute("tkn");//By Mukund on 30.11.2016
	  //FOCUS DEPARTMENT UNIT CODE AFTER SAVE FOR TAB INDEX FUNCTIONALITY
	  $('[name="departmentUnitCode"]')[0].focus();
	  
	  if(isSavedSuccussful=="1")
	  {
		  $("#divNormalMsgId").html(msg);
		  $("#divErrorMsgId").html("");
		  if(isPrintFlag=="1"){
			  $("#divPrintId").html(printDivContent);
			  {
			  get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
			  if(get_object("divBarCodeControlForBill")!=null)
			  get_object("divBarCodeControlForBill").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlForBill").innerHTML, 0);
			  }
			  //Added For Opd Card and Bill Print on the Same Time
			  var oldPage = $('body').html();			
			  $('body').html( 
		          "<html><head><title></title></head><body><p>"+
		          get_object("divPrintId").innerHTML+ "</p></body>");
			  window.print();
			  $('body').html(oldPage);
			//alert($('[name="barcodeSlipPrintFlag"]')[0].value +"\n"+ isBarcodeSlipPrint_Yes)
				
		  }else{
			  $("#divPrintId").html("");
		  }
		  
		  /*if(isFormFieldTobeReset=="1"){
			  //if(confirm(msg+"\nDo you Want To Reset The Form"))
				  splregistration.setFormFieldsAfterSave();
		  }*/
		  if($('[name="barcodeSlipPrintFlag"]')[0].value == isBarcodeSlipPrint_Yes)
				manageBarcodePrintProcess();
		  
		  if(isFormFieldTobeReset=="1"){
			  //if(confirm(msg+"\nDo you Want To Reset The Form"))
				  emgregistration.setFormFieldsAfterSave();
				  enterCount = 0;
		  }
		  document.getElementsByName('token')[0].value = tokenToSet;//By Mukund On 30.11.2016
	  }
	  else{
		  if (isDuplicatePatientPopup == "1")
			  emgregistration.showDuplicatePatientPopup(returnDocument);
		  $("#divErrorMsgId").html(msg);
		  $("#divNormalMsgId").html("");
		  $("#divPrintId").html("");
		  //if(confirm(msg+"\nDo you Want To Reset The Form"))
			 // emgregistration.setFormFieldsAfterSave();
		  if (isFormFieldTobeReset == "1") {
			  EmgRegistration.setFormFieldsAfterSave();
			  enterCount = 0;
			}
		  document.getElementsByName('token')[0].value = tokenToSet;//By Mukund On 30.11.2016
	  }
	  $("#divAadharConsentId").hide();
  },
  setFormFieldsAfterSave:function()
  {
	  $('#fatherorSpouseError').hide();
	  $('.validatebox-text').removeClass('validatebox-invalid');
	  //$('.validatebox-text').removeClass('validatebox-invalid');
	  $("#hiddenCatOrRegstrdPopupFlagId").val("");
	  $('[name="patPrimaryCat"]')[0].value="";
	 // $('[name="departmentUnitCode"]')[0].value="-1";
	  $('[name="departmentUnitCode"]')[0].value=emgregistration.fetchDefaultValues();
	  $('[name="patIdNo"]')[0].value="";
	  $('[name="hiddenPatIdNo"]')[0].value="";
	  
	  $('[name="patCatShortName"]')[0].value="";
	  $('[name="patPrimaryCatCode"]')[0].value="11";  //"-1"; // changed by manisha for setting default patcat 'general'
	  
	  $('[name="paymentModeCode"]').val("1");//default value for cash
	  emgregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
	
		var priCatObj = $('[name="patPrimaryCatCode"]')[0];
		if(priCatObj.selectedIndex == undefined || priCatObj.selectedIndex == null || priCatObj.selectedIndex == "-1")
			$('[name="patPrimaryCatCode"]')[0].value = "-1";  
		  
	  emgregistration.onchange_patPrimaryCategory();
	  
	  $('[name="patFirstName"]')[0].value="";
	  $('[name="patMiddleName"]')[0].value="";
	  $('[name="patLastName"]')[0].value="";
	  
	  $('[name="patFirstNameInMultiLang"]')[0].value="";
	  $('[name="patMiddleNameInMultiLang"]')[0].value="";
	  $('[name="patMiddleNameInMultiLang"]')[0].value="";
	  document.getElementById("divNewDOBHidden").innerHTML  = "";
	  $('[name="isActualDob"]')[0].checked=true;//if (age provided) isActualDob=0 else isActualDob=1
	  showDivAgeDob();
	  $('[name="patAge"]')[0].value="";
	  $('[name="patAgeUnit"]')[0].value="Yr";
	  //$('[name="patDOB"]')[0].value="";
	  //$('[name="patDOB_Dup"]')[0].value = "";
	  $('[name="patGender"]')[0].value="";
	  $('[name="patGenderCode"]')[0].value="-1";
	  
	  $('[name="patMaritalStatusCode"]')[0].value="-1";
	  $('[name="patGuardianName"]')[0].value="";
	  $('[name="patHusbandName"]')[0].value="";
	  
	  $('[name="patMotherName"]')[0].value="";
	  $('[name="patCaste"]')[0].value="";
	  $('[name="patCasteCode"]')[0].value="-1";
	  $('[name="patReligionCode"]')[0].value="-1";
	  $('[name="patBirthPlace"]')[0].value="";
	  $('[name="patOccupation"]')[0].value="-1";
	  $('[name="patMonthlyIncome"]')[0].value="";
	  $('[name="patAmountCollected"]')[0].value="";
	  
	  $('[name="patNationalId"]')[0].value="";	
	  
	  $('[name="patIsUrban"]')[0].value="";
	  $('[name="patDocType"]')[0].value="-1";
	  emgregistration.onchange_patDocType();
	   
	  $('[name="patCardNo"]')[0].value="";
	  $('[name="patNationalId"]')[0].value="";
	  $('[name="patVisitReason"]')[0].value="";
	  
	  

	  //Address Details corresponding to AddressVO
	  $('[name="patAddCountry"]')[0].value="";
	  $('[name="patAddCountryCode"]')[0].value= $('[name="defaultpatAddCountryCode"]')[0].value;
	  $('[name="patAddState"]')[0].value="";
	  $('[name="patAddStateCode"]')[0].value= $('[name="defaultpatAddStateCode"]')[0].value;
	  emgregistration.onchange_patAddCountryCode();
	  $('[name="patAddDistrict"]')[0].value="";
	  //alert("defaultpatAddDistrictCode :"+ $('[name="defaultpatAddDistrictCode"]')[0].value);
	  //$('[name="patAddDistrictCode"]')[0].value=$('[name="defaultpatAddDistrictCode"]')[0].value;
	  $('[name="patAddHNo"]')[0].value="";
	  $('[name="patAddCityLoc"]')[0].value="";
	  $('[name="patAddStreet"]')[0].value="";
	  $('[name="patAddCity"]')[0].value="";
	  
	  $('[name="patAddPIN"]')[0].value="";
	  $('[name="patAddPhoneNo"]')[0].value="";
	  $('[name="patAddMobileNo"]')[0].value="";
	  $('[name="patAddLandMarks"]')[0].value="";
	  $('[name="patAddEmailId"]')[0].value="";
	  $('[name="patAddPhoneNo"]')[0].value="";
	  $('[name="patAddPhoneOwner"]')[0].value="-1";
	  $('[name="patEmgCntNo"]')[0].value="";
	  $('[name="patIsUrban"]')[0].value="-1";
	 	  
      $('[name="patIdNo"]')[0].value="";
	  
	  $('[name="patPrimaryCatGrp"]')[0].value="";
	  $('[name="patDocTypeName"]')[0].value="";
	  
	  $('[name="patIdMark1"]')[0].value="";
	  $('[name="patIdMark2"]')[0].value="";
	  $('[name="isRelative"]')[0].value="-1";
	  $('[name="broughtByRelationCode"]')[0].value="-1";
	  $('[name="broughtByName"]')[0].value="";

	  $('[name="broughtByLocation"]')[0].value="";
	  $('[name="constableDesig"]')[0].value="";
	  $('[name="constableBadgeNo"]')[0].value="";
	  $('[name="broughtByPhone"]')[0].value="";
	  $('[name="broughtByAddress"]')[0].value="";
	  $('[name="policeStation"]')[0].value="";
	  
	  /*Start : Surabhi
	 * Reason : bug resolved for resetting the form fields after save
	 * date : 7th oct 2016 */
	  $('[name="patMiddleName"]')[0].disabled = false;
		$('[name="patLastName"]')[0].disabled = false;
		$('[name="patMaritalStatusCode"]')[0].disabled = false;
		$('[name="patGuardianName"]')[0].disabled = false;
		$('[name="patHusbandName"]')[0].disabled = false;
		$('[name="patMotherName"]')[0].disabled = false;
		$('[name="patCasteCode"]')[0].disabled = false;
		$('[name="patReligionCode"]')[0].disabled = false;
		$('[name="patBirthPlace"]')[0].disabled = false;
		$('[name="patOccupation"]')[0].disabled = false;
		$('[name="patMonthlyIncome"]')[0].disabled = false;
		$('[name="patAmountCollected"]')[0].disabled = false;
		$('[name="patNationalId"]')[0].disabled = false;
		$('[name="patIsUrban"]')[0].disabled = false;
		$('[name="patDocType"]')[0].disabled = false;
		$('[name="patCardNo"]')[0].disabled = false;
		$('[name="patNationalId"]')[0].disabled = false;
		$('[name="patVisitReason"]')[0].disabled = false;
		//Address Details corresponding to AddressVO
		$('[name="patAddCountry"]')[0].disabled = false;
		$('[name="patAddCountryCode"]')[0].disabled = false;
		$('[name="patAddDistrictCode"]')[0].disabled = false;

		$('[name="patAddState"]')[0].disabled = false;
		$('[name="patAddStateCode"]')[0].disabled = false;
		$('[name="patAddDistrict"]')[0].disabled = false;
		$('[name="patAddHNo"]')[0].disabled = false;
		$('[name="patAddCityLoc"]')[0].disabled = false;
		$('[name="patAddStreet"]')[0].disabled = false;
		$('[name="patAddCity"]')[0].disabled = false;
		$('[name="patAddPIN"]')[0].disabled = false;
		$('[name="patAddPhoneNo"]')[0].disabled = false;
		$('[name="patAddMobileNo"]')[0].disabled = false;
		$('[name="patAddLandMarks"]')[0].disabled = false;
		$('[name="patAddEmailId"]')[0].disabled = false;
		$('[name="patAddPhoneNo"]')[0].disabled = false;
		$('[name="patAddPhoneOwner"]')[0].disabled = false;
		$('[name="patAddMobileNo"]')[0].disabled = false;
		$('[name="patIsUrban"]')[0].disabled = false;
		$('[name="patAddPhoneOwner"]')[0].disabled = false;
		//Referred Inst Dtl
		if ($('[name="isReferred"]')[0].checked) {
			$('[name="isReferred"]')[0].checked = false;
			Isreferred($('[name="isReferred"]')[0]);
		}
		
		// end
	  
	/*  if($('[name="isReferred"]')[0].checked){
		  $('[name="isReferred"]')[0].checked=false;
		  Isreferred($('[name="isReferred"]')[0]);
	  }*/
	  
	  $('[name="isReferred"]')[0].checked = false;
		Isreferred($('[name="isReferred"]')[0]);
		
	  if($('[name="isUnknown"]')[0].checked){
		  $('[name="isUnknown"]')[0].checked=false;
		 // alert('hi')
		  checkForUnknownOnLoad($('[name="isUnknown"]')[0]);
	  }
	 //after save brought by dead chek box remove
	  if($('[name="isBroughtDead"]')[0].checked){
		  $('[name="isBroughtDead"]')[0].checked=false;
		//  alert('hi')
		  onBroughtDead($('[name="isBroughtDead"]')[0]);
	  }
	  
	  if($('[name="isMLC"]')[0].checked){
		  $('[name="isMLC"]')[0].checked=false;
	  }
	  
	  //Brpought by Dtl
	  if($('[name="isBroughtBy"]')[0].checked){
		  $('[name="isBroughtBy"]')[0].checked=false;
		  broughtBy($('[name="isBroughtBy"]')[0]);
	  }
	  setMandatoryReadOnlyTrueFalse(false);
	  $('[name="txt-snomed-ct-search_1"]').removeClass('x onX').val('').change();//for clearing snomed fields
  }  
 
,
//Start:Sheeldarshi:17thOct'14:Duplicacy check
showDuplicatePatientPopup : function(rtnData) {

	var windowWidth = $(window).width() - 50;
	var windowHeight = 350;
	parent.ajaxComplete();
	var page="/HISRegistration/registration/transactions/jsp/opdNewRegDuplicatePopup.jsp";

	var $dialog = $('<div></div>').html(
			'<iframe style="border: 0px; " src="' + page
					+ '" width="100%" height="100%"></iframe>').dialog({
		autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
		title : "Patient Detail Exists",	show: { effect: "clip"},resizable: false,
		position: ['top', 100],dialogClass: 'no-close custbtncolor',
		buttons:{
			"Save As New Patient": function() {
			 	var selecteCRToRevisit = $('[name="radioForDuplicate_parent"]').val();
			 	var matCri = selecteCRToRevisit.split("&&")[1];
				if(matCri=='A1' || matCri=='B2')
				{
					//alert("Feature not available as record for Aadhaar/Other Id present");
					alert("Aadhaar/OtherId already registered");
					return false;
				}	
				else
				{
					$(this).dialog("close"); 
					//return false;
					emgregistration.saveAsNewPatientDtl();
				}
		},
		"Continue With Existing": function() {
			var selecteCRToRevisit = $('[name="selectedCRToVisit"]').val();
		 	if(selecteCRToRevisit==undefined || selecteCRToRevisit==null || selecteCRToRevisit=='')
			{
				alert("Please select a record!");
			}
			else
			{
				emgregistration.continueWithExisting(rtnData);
				$(this).dialog("close");
			}
		},
			"Cancel": function() {
				  $(this).dialog("close"); }
		},
		open: function() {
			 	$('.ui-widget-overlay').addClass('custoverlay');
			 	$('.ui-dialog-buttonpane').find('button:contains("Cancel")').addClass('custbtncolor');
			 	$('.ui-dialog-buttonpane').find('button:contains("Save As New Patient")').addClass('custbtncolor');
		},
		close: function() {
			$(".ui-widget-overlay").removeClass('custoverlay');
	    }   
	});
	$dialog.dialog('open');
 },
 continueWithExisting: function(rtnData){
		var tempCrStorage = $('[name="selectedCRToVisit"]').val();
		var selecteCRToRevist = tempCrStorage.split("&&")[0];//$('[name="selectedCRToVisit"]').val();
		//var slNoHashUhidForDupCont_temp = rtnData.getElementsByTagName("root")[0].childNodes[0].getAttribute("strErrMsg");
		//var slNoHashUhidForDupCont = slNoHashUhidForDupCont_temp.split("@")[1];
		//alert(slNoHashUhidForDupCont);
		$('[name="selectedCRToVisit"]').val(selecteCRToRevist);
		//return false;
		var CrNoObj = document.getElementsByName('selectedCRToVisit')[0];
		if(validateCRNoCHECK_forOdisha('15', CrNoObj))
		{
			//var url='/HISRegistration/registration/transactions/GETPATDTLPatientVisit.action?patCrNo='+selecteCRToRevist+'&modeCase=1&isPatReferByList=0';
			var url="/HISRegistration/registration/transactions/EmgPatientVisit.action?patCrNo="+selecteCRToRevist+"&isDuplicateRegistered=1&selectedDuplicatePatCrNo="+selecteCRToRevist;//+"&slNoHashUhidForDupCont="+slNoHashUhidForDupCont;
			var menu = 'Emergency_Patient_Revisit';
			parent.ajaxStartMenu();
			menu=menu.toString().replace(/_/g,' ').replace(/XXY/g,'/').replace(/XXX/g,'&');
			//url = Base64.decode(url).toString();
			parent.callMenu(url,menu);
			parent.ajaxCompleteMenu();
		}
	},
	setPaymentModeRefId: function(obj){
		var paymentModeCode=obj.value.split("#")[0];
		if(paymentModeCode!=""  && paymentModeCode!=-1 && paymentModeCode!=1 && paymentModeCode!=13 && paymentModeCode!=10){//1 for cash, 10 for cm relief fund, 13 for jan arogya
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
			optionText = "<option value='-1'>Select Value</option>";
			for ( var i = 0; i < elementNode.childNodes.length; i++) {
				var optionNode = elementNode.childNodes[i];
				var paymentModeJSONObject = jQuery.parseJSON(optionNode.getAttribute("value"));
				paymentModeJSONArray[paymentModeJSONArray.length] = paymentModeJSONObject;
				optionText += "<option value='"
						+ paymentModeJSONObject.paymentModeCode + "'>"
						+ (paymentModeJSONObject.paymentCodeName) + "</option>";

			}
			if (document.getElementsByName(elementName).length != 0)
				document.getElementsByName(elementName)[0].innerHTML = optionText;
			$("#paymentModeCodeId").val('1');//hardcoded wrt "general" patient category
			emgregistration.setPaymentModeRefId($('[name="paymentModeCode"]')[0]);
			$("#paymentModeCodeId").validatebox({
				validType : [ 'selectCombo[-1]' ]
			});
			emgregistration.onchange_patPrimaryCategory();
		}			
	},
	processPaymentModeNode_onCatChange : function(primCatCode) {
		var optionText = "";
		var defaultPaymentMode = "-1";
		
		if (primCatCode != null) {
			optionText = "<option value='-1'>Select Value</option>";
			for ( var i = 0; i < paymentModeJSONArray.length; i++) {
					
				var mappedCatCode = paymentModeJSONArray[i].recieptCategoryCode;
				if(paymentModeJSONArray[i].refundCategoryCode != 0){
					mappedCatCode = paymentModeJSONArray[i].refundCategoryCode;
				}
				var arrPrimCatWidDfltPymntMd = [];
				arrPrimCatWidDfltPymntMd = mappedCatCode.split('^');
				for(var j=0; j<arrPrimCatWidDfltPymntMd.length; j++){
					if(arrPrimCatWidDfltPymntMd[j].split('@')[0] === primCatCode){
						defaultPaymentMode = arrPrimCatWidDfltPymntMd[j].split('@')[1];
						optionText += "<option value='"
							+ paymentModeJSONArray[i].paymentModeCode + "'>"
							+ (paymentModeJSONArray[i].paymentCodeName) + "</option>";
					}
				}
			}
			
			if (document.getElementsByName("paymentModeCode").length != 0)
				document.getElementsByName("paymentModeCode")[0].innerHTML = optionText;
			$("#paymentModeCodeId").val(defaultPaymentMode);
			emgregistration.setPaymentModeRefId($('[name="paymentModeCode"]')[0]);
		}
	
	}

};
//End:Sheeldarshi:17thOct'14:Duplicacy check
$("#patDocTypeId").change(function(){
	emgregistration.onchange_patDocType();
});

$("#patPrimaryCatCodeId").change(function(){
	emgregistration.onchange_patPrimaryCategory();
});

function onCLickDateFn(){
	$("#patDOBId_Dup").focus();//$("#patDOBId").focus();
}

$.datepicker.setDefaults( 
	    {showOn: 'both',
	    	defaultDate: new Date(),
	    	buttonImageOnly: true, 
	    	buttonText : "Select Date",
	    	buttonImage: "../../hisglobal/images/calendar-icon.gif",
	    		onSelect: function (dateText, inst) {
		    		onCLickDateFn();
		    		}
	    	}); 

function closeModelFunction(popupmodal,func){
	//alert("closeFunction");
	popupmodal.close=eval(func);
	//$.modal.destroy();
}

/*$("#imgCatCardId").click(function(){
	var patPrimarCatId= $("#patPrimaryCatCodeId").val();
	var action 	= "/HISRegistration/registration/transactions/openPatPopupEmgRegistration.action?patPrimarCatId="+patPrimarCatId+"&alreadyRegisteredFlag=0";
	openURLInPopupWithoutClose(action,'900','400');
	//openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');
	
});
*/
$("#imgCatCardId")
.click(
		function() {
			var patPrimarCatId = $("#patPrimaryCatCodeId").val();
			var PrimaryCatObj = $('[name="patPrimaryCatCode"]')[0];
			var PrimaryCatName = PrimaryCatObj.options[PrimaryCatObj.selectedIndex].text;
			PrimaryCatName=PrimaryCatName.toLowerCase();
			if(PrimaryCatName.indexOf("wap") > -1)
			{
				openWhiteCardPopup()
			}	
			else
				{
			var action 	= "/HISRegistration/registration/transactions/openPatPopupEmgRegistration.action?patPrimarCatId="+patPrimarCatId+"&alreadyRegisteredFlag=0";
			openURLInPopupWithoutClose(action, '900', '400');
				}
		});

$("#alreadyRegisteredFlagId").click(function(){
	var alreadyRegisteredFlag = this.value;
	if(this.checked==false){
		emgregistration.setFormFieldsAfterSave();
		$("#hiddenCatOrRegstrdPopupFlagId").val("");
		$("#divNormalMsgId").html("");
		$("#divErrorMsgId").html("");
		$("#divPrintId").html("");
		if(("#isIdRequired").val()=="")
			$("#imgCatCardId").show();
		return;
	}
	var patPrimarCatId= $("#patPrimaryCatCodeId").val();
	var action 	= "/HISRegistration/registration/transactions/openPatPopupEmgRegistration.action?patPrimarCatId="+patPrimarCatId+"&alreadyRegisteredFlag="+alreadyRegisteredFlag;
	openURLInPopupWithoutClose(action,'600','200');
	//openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');
});

//Submit form if valid
$('#submitId').click(function(e){
	//alert("inside submitClick");
	/*This loop will trim all leading and trailing spaces from input type fileds*/
	 enterCount++;
		if(enterCount!=1)
			return false;
	var allInputs = $(":input"); 
	allInputs.each(function() {
	        $(this).val($.trim($(this).val()));
	    });
	//End
	if((!$('[name="isUnknown"]')[0].checked)){
		setMotherValidRule();
	
		fatherorSpouseerror();
		fatherorSpouse();
	}
	if($('[name="isBroughtBy"]')[0].checked){
	
		if($('[name="isUnknown"]')[0].checked){
		
			$("#isRelativeKnownId").removeClass('validatebox-invalid');
		}else{
		
			$("#isRelativeUnKnownId").removeClass('validatebox-invalid');
		}
	}else{
	
		$("#isRelativeKnownId").removeClass('validatebox-invalid');
		$("#isRelativeUnKnownId").removeClass('validatebox-invalid');
	}
	//$('[name="isUnknown"]')[0].checked;
	
	if($('[name="patGuardianName"]').val() != "" ||$('[name="patHusbandName"]').val() != "" ||$('[name="patMotherName"]').val() != "")
	{
		$('[name="patGuardianName"]').validatebox({
			required : false,
			validType : 'alphaWithSpace'
		});
	
		$('[name="patHusbandName"]').validatebox({
			required : false,
			validType : 'alphaWithSpace'
		});
		$('[name="patMotherName"]').validatebox({
			required : false,
			validType : 'alphaWithSpace'
		});
		
	}
	else
	{
		$('[name="patGuardianName"]').validatebox({
			required : true,
			validType : 'alphaWithSpace'
		});
	
		$('[name="patHusbandName"]').validatebox({
			required : true,
			validType : 'alphaWithSpace'
		});
		$('[name="patMotherName"]').validatebox({
			required : true,
			validType : 'alphaWithSpace'
		});
		
	}	
	//alert("1429: "+document.getElementsByName("isAmbulatory")[0].value );

	//alert("1431 "+$('[name="isAmbulatory"]').val());
	
	if($('#EmgRegistration').form('validate')){
		$('#submitId').hide();
		//alert("Validation True");
		//alert("after hide");
		var isAmbulatoryCheck = document.getElementsByName("isAmbulatory")[0].value;
		document.getElementsByName("isAmbulatoryCheckUncheck")[0].value = isAmbulatoryCheck;
		
		var isTraumaCheck = document.getElementsByName("isTraumaPatient")[0].value;
		document.getElementsByName("isTrauma")[0].value = isTraumaCheck;
		//alert(isTraumaCheck);
		//alert(document.getElementsByName("isAmbulatoryCheckUncheck")[0].value);
		emgregistration.savePatientDtl();
		$('#divClient').empty();
		$('#divClient').html("<select name='clientCode'  tabindex='1' id='clientCodeId' onchange='setCompany();'  style='height: 19px;width:160px;'><option value='-1'>Select Value</option></select>");
		$('#submitId').show();
			}else{
		//alert("Validation false");
		enterCount=0;
		return false;
	}
	e.preventDefault();	
});

//Clear Form Data
$('#clearId').click(function(e){
	$('[name="departmentUnitCode"]')[0].focus();
	emgregistration.setFormFieldsAfterSave();
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");
});
$('[name="patRefGnctdHospitalCode"]').change(function(){
	//emgregistration.onchange_patRefGnctdHospitalDept();
});

$("#patAgeUnitId").change(function(){
	var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
	var ageRangeValidType='range[1,'+maxAgeRange+']';
	$("#patAgeId").validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
});

/*$("#nonprintableDiv1").click(function(){
	//alert("nonprintableDiv1 clicked :"+$("divPrintId").html());
	//$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");
});*/


function trimData(val)
{
	//alert((typeof val).toUpperCase());
	if(val && val!=null && val!="" && (typeof val).toUpperCase() == 'STRING')
	{
		while(val.substr(0,1)==' ')	val=val.substr(1);
		while(val.substr(val.length-1,1)==' ')	val=val.substr(0,val.length-1);			
	}
	return val;
}

function getCatCardNameBasedOnCatGroup(patCatGroupId)
{
	var cardName="Id";
	if(patCatGroupId=="1"){
		cardName="Employee Id";
	}else if(patCatGroupId=="4"){
		cardName="BPL Card No";
	}
	return cardName;
}

function calculateMaxRangeValue(age,ageUnit){
	var maxAgeRange=125;
	
	if(ageUnit=="D"){
		maxAgeRange=(age*365);
	}else if(ageUnit=="Wk"){
		maxAgeRange=Math.floor(age*365/7);
	}else if(ageUnit=="Mth"){
		maxAgeRange=age*12;
	}else if(ageUnit=="Yr"){
		if(age<maxAgeRange)
			maxAgeRange=age;
	}
	return maxAgeRange;
}


function setMotherValidRule(){
	var flagYes=false;
	var allowSpouse = true;

	//alert("inside setMotherValidRule()");
	if($('[name="isActualDob"]')[0].checked && $('[name="patAgeUnit"]')[0].value!="-1"){
		if($("#patAgeId").val() < calculateMaxRangeValue(12,$("#patAgeUnitId").val())){
			flagYes=true;
		}
		if ($("#patAgeId").val() < calculateMaxRangeValue(18,
				$("#patAgeUnitId").val())) {
			allowSpouse = false;
		}
	}else if($("#patDOBId_Dup").val()!="" && $("#patDOBId_Dup").val()!="dd-mm-yyyy")// && isDate($('[name="patDOB"]')[0],"dd-mmm-yyyy"))
		{
		var d1 = $.datepicker.parseDate("dd-mm-yy", $("#patDOBId_Dup").val());
		var ctDt= new Date();
		var yr = d1.getFullYear();
		var ctDtYr = ctDt.getFullYear();
		if((ctDtYr-12) <= yr){
			flagYes=true;
		}
		/*if ((ctDtYr - 18) <= yr) {
			allowSpouse = false;
		}*/
		var temp =ctDtYr-18;
		ctDt.setFullYear(temp, ctDt.getMonth());
		if(ctDt<=d1)
		{
			allowSpouse = false;
		}
	}
	if(flagYes && (!$('[name="isUnknown"]')[0].checked)){
		$('[name="patMotherName"]').validatebox({required: true,	validType: 'alphaWithSpace'});
	}else{
		$('[name="patMotherName"]').validatebox({required: false,	validType: 'alphaWithSpace'});
	}
	
	//alert("allowSpouse"+allowSpouse);
	if(allowSpouse){
		if(!$('[name="isUnknown"]')[0].checked)
			$('[name="patHusbandName"]').removeAttr("disabled");
	}
	else{
		$('[name="patHusbandName"]').val("");
		$('[name="patHusbandName"]').attr("disabled", "disabled");
	}
}
function showDivAgeDob(){
	var PRIMARY_CATEGORY_SENIOR_CITIZEN = $('[name="seniorCitizenCatCode"]')[0].value;
	if($('[name="isActualDob"]')[0].checked){
		$("#divAge").show();
		$("#divDob").hide();
		//Start:Sheeldarshi
		//Reason:Bug 10166 - System should be able to validate senior citizen category	
		var seniorAgeBoundRange = $('[name="seniorCitizenAgeLimit"]')[0].value;
		if(document.forms[0].patPrimaryCatCode.value==PRIMARY_CATEGORY_SENIOR_CITIZEN && ageBoundRange<seniorAgeBoundRange)
			{			
			alert("Senior Citizen Category Not Allowed in this department");
			$('[name="patPrimaryCatCode"]')[0].value="-1";
			$('[name="patIdNo"]')[0].value = "";
			$("#shownPatIdNoId").validatebox({
				required : false,
				validType : null
			});
			emgregistration.onchange_patPrimaryCategory();
			return false;
			}
		if(document.forms[0].patPrimaryCatCode.value==PRIMARY_CATEGORY_SENIOR_CITIZEN && $('[name="seniorCitizenAgeLimit"]')[0].value !="")
		{
		var maxAgeRange = calculateMaxRangeValue(seniorAgeBoundRange, $("#patAgeUnitId").val());
		var maxAgeRange2 = calculateMaxRangeValue(ageBoundRange, $(
		"#patAgeUnitId").val());
		var ageRangeValidType = 'range[' + maxAgeRange + ',' + maxAgeRange2 + ']';
		}
		else
		{
		var maxAgeRange = calculateMaxRangeValue(ageBoundRange, $(
				"#patAgeUnitId").val());
		var ageRangeValidType = 'range[1,' + maxAgeRange + ']';
		// alert("ageRangeValidType :"+ageRangeValidType);
		}
		//End
		//alert("ageRangeValidType :"+ageRangeValidType);
		$("#patAgeId").validatebox({required: true,	validType: ['numeric',ageRangeValidType,'startZero']});
		$("#patAgeUnitId").validatebox({required: true, validType: ['selectCombo[-1]']});
		$("#patDOBId_Dup").validatebox({required: false,validType:null});
		//$("#patDOBId").validatebox({required: false,validType:null});
		
	}else{
		$("#divAge").hide();
		$("#divDob").show();
		$("#patAgeId").validatebox({required: false});
		$("#patAgeUnitId").validatebox({required: false});
		$(function() {
			document.getElementById("divNewDOBHidden").innerHTML  ="";
			if($("#patDOBId_Dup").val()!=""){

				DateValidator.setup("divNewDOB", "patDOB_Dup", $("#patDOBId").val(), "dd-Mon-yyyy", "input45prcnt", "patDOBId_Dup", "1", "divNewDOBHidden");

				/*var _date=$.datepicker.parseDate("dd-M-yy", $("#patDOBId").val());
				$("#patDOBId").datepicker({dateFormat: 'dd-M-yy',
											onSelect: function(d,i){
										          if(d !== i.lastVal){
										              $(this).change();
										          }
										     }}).datepicker("setDate", new Date(_date));*/
				/*var _date=$.datepicker.parseDate("dd-M-yy", $("#patDOBId").val());
				_date = moment(_date).format('DD-MMM-YYYY');
				//_date=_date.toLocaleFormat('%d-%b-%Y');
				$('#patDOBId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:_date,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(_date);*/
			}
			else{

				DateValidator.setup("divNewDOB", "patDOB_Dup", "", "dd-Mon-yyyy", "input45prcnt", "patDOBId_Dup", "1", "divNewDOBHidden");

				/*$("#patDOBId").datepicker({dateFormat: 'dd-M-yy',
											onSelect: function(d,i){
										          if(d !== i.lastVal){
										              $(this).change();
										          }
										     }}).datepicker("setDate", new Date());*/
				var today=  moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
				/*$('#patDOBId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(today);*/
			}
			
		});
		//Start:Sheeldarshi
		//Reason:Bug 10166 - System should be able to validate senior citizen category	
		if(document.forms[0].patPrimaryCatCode.value==PRIMARY_CATEGORY_SENIOR_CITIZEN && $('[name="seniorCitizenAgeLimit"]')[0].value !="")
		{
		var seniorAgeBoundRange = $('[name="seniorCitizenAgeLimit"]')[0].value;
		var dobValidType = 'dtltetDMYa[' + seniorAgeBoundRange + ',\'y\']';
		}
		else
		var dobValidType = 'dtltetDMY[' + ageBoundRange + ',\'y\']';
		//End
		//$("#patDOBId").validatebox({required: true, validType: ['date[\'patDOB\',\'dd-mmm-yyyy\']','dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth \']' ,dobValidType]});
	}
	
}

function validateDivDob()
{
	var PRIMARY_CATEGORY_SENIOR_CITIZEN = $('[name="seniorCitizenCatCode"]')[0].value;
	
	if ($('[name="isActualDob"]')[1].checked) // Case DOB
	{
		var DOBValActual = $("#patDOBId").val(); // in dd-Mon-yyyy
		var DOBVal = $("#patDOBId_Dup").val(); // in dd-mm-yyyy

		var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y'); // today in dd-Mon-yyyy
		
		// Date valid format
		var format = "dd-M-yy";
		var value = DOBValActual;
	 	var flg = true;
	 	//alert (value)
	 	//alert(format)
	 	var d1 = null;
	 	try
	 	{
	 		d1 = $.datepicker.parseDate(format, value);
	 	}
	 	catch(e)
	 	{
	 		flg = false;
	 	}
	 	
	 	if(!flg)
	 	{
	 		alert('This should be a date in dd-mm-yyyy format.For ex: (28-01-2014)');
	 		$("#patDOBId_Dup").focus();
	 		return false;
	 	}
	 	
	 	// Cannot be Greater Than Current Date
	 	flg = true;
		//var d1 = $.datepicker.parseDate(format, value);
		var ctdt = new Date();
		//alert("Current Date :"+ctdt);
		if(!(d1<=ctdt))
		{
	 		alert('Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth in \'dd-mm-yyyy\'');
	 		$("#patDOBId_Dup").focus();
	 		return false;
		}
	 	
		// Age Limit
		if(document.forms[0].patPrimaryCatCode.value==PRIMARY_CATEGORY_SENIOR_CITIZEN && $('[name="seniorCitizenAgeLimit"]')[0].value !="")
		{
			var seniorAgeBoundRange = $('[name="seniorCitizenAgeLimit"]')[0].value;
			//var dobValidType = 'dtltetDMYa[' + seniorAgeBoundRange + ',\'y\']';
			d1 = $.datepicker.parseDate(format, value);
			ctdt = new Date();
			var dmy = seniorAgeBoundRange;
			var dmyFlag='y';
			var month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
			if(dmyFlag=="y")
				 ctdt.setFullYear(ctdt.getFullYear()-dmy);
			else if(dmyFlag=="m")
				 ctdt.setMonth(ctdt.getMonth()-dmy);
			else if(dmyFlag=="d")
				 ctdt.setDate(ctdt.getDate()-dmy);
			 
			var msg="Date Should be less than "+ctdt.getDate()+"-"+(month[ctdt.getMonth()])+"-"+ctdt.getFullYear()+".";
			if(!(d1 <= ctdt))
			{
		 		alert(msg);
		 		$("#patDOBId_Dup").focus();
		 		return false;
			}
		}
		else
		{
			//var dobValidType = 'dtltetDMY[' + ageBoundRange + ',\'y\']';
			d1 = $.datepicker.parseDate(format, value);
			ctdt = new Date();
			ctdt.setHours(0,0,0,0);
			d1.setHours(0,0,0,0);
			var dmy = ageBoundRange;
			var dmyFlag='y';
			var month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
			
			if(dmyFlag=="y")
				ctdt.setFullYear(ctdt.getFullYear()-dmy);
			else if(dmyFlag=="m")
				ctdt.setMonth(ctdt.getMonth()-dmy);
			else if(dmyFlag=="d")
				ctdt.setDate(ctdt.getDate()-dmy);
			 
			var msg="Date Should not be less than "+ctdt.getDate()+"-"+(month[ctdt.getMonth()])+"-"+ctdt.getFullYear()+".";
			if(!(d1 >= ctdt))
			{
		 		alert(msg);
		 		$("#patDOBId_Dup").focus();
		 		return false;
			}
		}
		//setMotherValidRule();
	}
	return true;
}


function setMandatoryRefdInitial(){
	if($('[name="referringInstType"]')[0].checked){
		('[name="patRefGnctdHospitalCode"]')[0].value="-1";
		$("#patRefGnctdHospitalDeptId").html("<option value='-1'>Select Value</option>");
	}else{
		$('[name="patRefHospitalName"]')[0].value="";
	}
	$('[name="patRefDoctor"]')[0].value="";
}
function setMandatoryInitial(){
	$('[name="patFirstName"]')[0].value="";
	$('[name="patMiddleName"]')[0].value = "";
	$('[name="patLastName"]')[0].value = "";
	$('[name="patGuardianName"]')[0].value ="";
	$('[name="patMotherName"]')[0].value ="";
	$('[name="patHusbandName"]')[0].value ="";
	if($('[name="isActualDob"]')[0].checked){
		$("#patAgeId").val("");
		$("#patAgeUnitId").val("-1");
	}else{
		$("#patDOBId").val("");
		$("#patDOBId_Dup").val("");
	}
	/*$('[name="patAddCountryCode"]')[0].value="-1";
	$("#patAddStateCodeId").html("<option value='-1'>Select Value</option>");
	$("#patAddDistrictCodeId").html("<option value='-1'>Select Value</option>");*/
	
}

function setMandatoryRefdReadOnlyTrueFalse(flagTrueFalse){
	if(flagTrueFalse)
		setMandatoryInitial();
	
	if($('[name="referringInstType"]')[0].checked){
		$('[name="patRefGnctdHospitalCode"]').attr("disabled", flagTrueFalse);
	}else{
		$('[name="patRefHospitalName"]').prop('readOnly', flagTrueFalse);
	}
	$('[name="patRefDoctor"]').prop('readOnly', flagTrueFalse);
}
function setMandatoryReadOnlyTrueFalse(flagTrueFalse){
	if(flagTrueFalse)
		setMandatoryInitial();
	
	$('[name="patFirstName"]').prop('readOnly', flagTrueFalse);
	if($('[name="isActualDob"]')[0].checked){
		$("#patAgeId").attr("disabled", flagTrueFalse);
		$("#patAgeUnitId").attr("disabled", flagTrueFalse);
	}else{
		//$("#patDOBId").prop('readOnly', flagTrueFalse);
	}
	/*$("#patAddCountryCodeId").attr("disabled", flagTrueFalse);
	$("#patAddStateCodeId").attr("disabled", flagTrueFalse);
	$("#patAddDistrictCodeId").attr("disabled", flagTrueFalse);*/
	
	/*if($('[name="isReferred"]')[0].checked){
		if(flagTrueFalse)
			setMandatoryRefdInitial();
		setMandatoryRefdReadOnlyTrueFalse(flagTrueFalse);
	}*/
		
}

function ageSelection()
{
	//alert("isActualDob.checked :"+ document.getElementsByName("isActualDob")[0].checked);
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
	}
	else
	{
		document.getElementsByName("patAge")[0].value="";
		document.getElementsByName("patAgeUnit")[0].selectedIndex=0;
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="";
	}
} 
function Isreferred(elem) {
	//alert("1");
	//alert(elem);
	if (elem.checked) {
		//alert("11");
		//elem.value = "1";
		$("#divRefDtlId").show();
		$("#divRefHospitalDeptOtherId").hide();
		$('[name="patRefGnctdHospitalCode"]')[0].value = "-1";
		$('[name="patRefHospitalName"]')[0].value = "";
		$('[name="patRefHospitalDeptOther"]')[0].value = "";

		$('[name="patRefGnctdHospitalCrno"]').validatebox({
			//validType : ['numeric','validCRNo']
			validType : ['numericwithoutspace','equalLengthForCrno["12|15"]','DisableAllZero']
		});
		$('[name="patRefDoctor"]').validatebox({
			required : true,
			//validType : 'alpha'
			validType : 'allowAlphaSpaceBracketDot'	
		});
		$('[name="patRefGnctdHospitalDeptUnit"]').validatebox({
			validType : 'alphaNumericWithSpaces'
		});

		if ($('[name="referringInstType"]')[0].checked) {
			//alert("111");
			$("#divRefHosname").hide();
			$("#divRefHosCode").show();
			$("#divReferredInstitute").show();
			$("#divReferred").show();
			$('[name="patRefDoctor"]')[0].value = "";
			$('[name="patRefGnctdHospitalDept"]')[0].value = "-1";
			$('[name="patRefGnctdHospitalDeptUnit"]')[0].value = "";
			$('[name="patRefGnctdHospitalCrno"]')[0].value = "";

			$('[name="patRefGnctdHospitalCode"]').validatebox({
				validType : [ 'selectCombo[-1]' ]
			});

		} else {
			//alert("112");
			$("#divRefHosname").show();
			$("#divRefHosCode").hide();
			$("#divReferredInstitute").show();
			
			$('[name="patRefGnctdHospitalCrno"]').validatebox({
				validType : ['numericwithoutspace','equalLengthForCrno["12|15"]','DisableAllZero']
			});

			$('[name="patRefDoctor"]')[0].value = "";
			$("#divReferred").show();
			$('[name="patRefGnctdHospitalDept"]')[0].value = "-1";
			$('[name="patRefGnctdHospitalDeptUnit"]')[0].value = "";
			$('[name="patRefGnctdHospitalCrno"]')[0].value = "";

			$('[name="patRefGnctdHospitalCode"]').validatebox({
				validType : null
			});
			$('[name="patRefHospitalName"]').validatebox({
				required : true,
				//validType : 'alphaWithSpace'
				validType : 'allowAlphaSpaceBracketDot'
			});
		}
		/*
		 * if($('[name="isIdRequired"]')[0].value=="2"){
		 * setMandatoryRefdInitial(); setMandatoryRefdReadOnlyTrueFalse(true);
		 * }else{ setMandatoryRefdReadOnlyTrueFalse(false); }
		 */
	} else {
		//alert("2");
	//	elem.value = "0";
		
		$('[name="patRefGnctdHospitalCode"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="patRefHospitalName"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="patRefDoctor"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="patRefGnctdHospitalCrno"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="patRefGnctdHospitalDeptUnit"]').validatebox({
			required : false,
			validType : null
		});
		$("#divRefDtlId").hide();
		$("#divRefHosname").hide();
		$("#divRefHosCode").hide();
		$("#divReferredInstitute").hide();
		$("#divReferred").hide();
		//alert("22");
	}
}

function showdivhoscode()
{
    document.getElementById("divRefHosCode").style.display="";  
	document.getElementById("divRefHosname").style.display="none"; 
	document.getElementsByName("patRefHospitalName")[0].value="";
	document.getElementsByName("patRefDoctor")[0].value="";
	Isreferred(document.getElementsByName("isReferred")[0]);
}


function fatherSouseRequired(temp)
{
	if(temp==1)
	{
		$('[name="patGuardianName"]').validatebox({
		required : true,
		});
	}
	else
		{
		
		}
}

$("#patRefGnctdHospitalDeptId").change(function(){
	if($("#patRefGnctdHospitalDeptId").val()=="0"){
		$("#divRefHospitalDeptOtherId").show();
		$('[name="patRefHospitalDeptOther"]').validatebox({required : true, validType: 'alphaWithSpace'});
	}
	else{
		$("#divRefHospitalDeptOtherId").hide();
		$('[name="patRefHospitalDeptOther"]').validatebox({required : false, validType: 'alphaWithSpace'});
	}
});


$("#patAddStateCodeId").bind("change",emgregistration.onchange_patAddStateCode);

// Validation]
$('[name="departmentUnitCode"]').validatebox({
	validType: ['selectCombo[-1]']
});

$("#patPrimaryCatCodeId").validatebox({
	validType: ['selectCombo[-1]']
});
/*$('[name="patFirstName"]').validatebox({
	required : true,
	validType: 'alphaWithSpace'
});*/
$('[name="patFirstName"]').validatebox({
	required : true,
	validType : 'alphaWithSpaceSlash'
});
$('[name="patMiddleName"]').validatebox({
	validType: 'alphaWithSpace'
});
$('[name="patLastName"]').validatebox({
	validType: 'alphaWithSpace'
});

$('[name="patGuardianName"]').validatebox({
	required : true,
	validType: 'alphaWithSpace'
});

/*$('[name="patGuardianName"]').validatebox({
	validType: ['fatherOrSpouseReqdd[1]']
});
$('[name="patHusbandName"]').validatebox({
	validType: ['fatherOrSpouseReqdd[1]']
});*/
$('[name="patHusbandName"]').validatebox({
	required : true,
	validType: 'alphaWithSpace'
});

$('[name="patMotherName"]').validatebox({
	required : true,
	validType: 'alphaWithSpace'
});


$('[name="patBirthPlace"]').validatebox({
	validType: 'alphaWithSpace'
});
$('[name="patAddHNo"]').validatebox({
	validType: 'alphaNumericWithSpaces'//'alphaNumSpecialChar'
});

$('[name="patAddStreet"]').validatebox({
	validType: 'alphaNumericWithSpaces'//'alphaNumSpecialChar'
});

//edited by sandip naik on 28 April,2017
$('[name="patAddCityLoc"]').validatebox({
	validType: 'alphaNumericWithSpaces'
});
//end by sandip naik on 28 April,2017

$('[name="patAddCity"]').validatebox({
	validType: 'alphaWithSpace'//'alphaNumSpecialChar'
});
$('[name="patAddLandMarks"]').validatebox({
	validType: 'alphaWithSpace'//'alphaNumSpecialChar'
});

$('[name="patAddPIN"]').validatebox({
	//required : true,
	validType : [ 'numericwithoutspace','startZero', 'equalLength[6]']
});
/*$('[name="patVisitReason"]').validatebox({
	validType: '[maxLength[99]]'
});*/
$('[name="patAddCountryCode"]').validatebox({
	validType: ['selectCombo[-1]']
});
$('[name="patAddEmailId"]').validatebox({
	validType: 'email'
});
$('[name="patAddPhoneNo"]').validatebox({
	validType: ['telephone','minLengthNumeric[5]','DisableAllZero']
});
$('[name="patAddMobileNo"]').validatebox({
	required : true,
	validType: [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
});
$('[name="patEmgCntNo"]').validatebox({
	validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
});
$('[name="patMonthlyIncome"]').validatebox({
	validType: 'numericNew'
});


$('[name="patNationalId"]').validatebox({
	validType : [ 'numeric','equalLength[12]', 'validAadhaarNo']
});

jQuery.fn.limitMaxlength = function(options){

    var settings = jQuery.extend({
        attribute: "maxlength",
        onLimit: function(){},
        onEdit: function(){}
    }, options);

    // Event handler to limit the textarea
    var onEdit = function(){
        var textarea = jQuery(this);
        var maxlength = parseInt(textarea.attr(settings.attribute));

        if(textarea.val().length > maxlength){
            textarea.val(textarea.val().substr(0, maxlength));

            // Call the onlimit handler within the scope of the textarea
            jQuery.proxy(settings.onLimit, this)();
        }

        // Call the onEdit handler within the scope of the textarea
        jQuery.proxy(settings.onEdit, this)(maxlength - textarea.val().length);
    };

    this.each(onEdit);

    return this.keyup(onEdit)
                .keydown(onEdit)
                .focus(onEdit);
               
};



//validType: 'validateAdvAmount[\'patMonthlyIncome\',8,2,\'Kindly Enter Amount in 99999999.99 format\']'

/**/

/*$('[name="patDOB"]').validatebox({
	required: true,	
	validType: 'dtltet[\'anotherPatDOB\',\'PatDOB should be less than or equalt to AnotherPatDOB\']'
	//validType: 'md[\''+$('[name="anotherPatDOB"]')[0].value+'\']'
});*/

function fatherorSpouseerror()
{var fatherName = $('[name="patGuardianName"]')[0].value;
var spouseName = $('[name="patHusbandName"]')[0].value;

if((fatherName == "" || fatherName == null) && (spouseName == "" || spouseName == null))
	$('#fatherorSpouseError').show();
else
	$('#fatherorSpouseError').hide();
}


function fatherorSpouse()
{
	var fatherName = $('[name="patGuardianName"]')[0].value;
	var spouseName = $('[name="patHusbandName"]')[0].value;
	var maritalStatusCode = $('[name="patMaritalStatusCode"]')[0].value;
	if(maritalStatusCode == "2" || maritalStatusCode=="3")
	{
		if(spouseName != "" || spouseName != null)
			$('[name="patGuardianName"]').validatebox({required: false,validType: null});
		
	}
}

/////////////////////////////////////////////////////////////////
function callMenu(url)
{
	//alert('menu called with url: '+ url);
	var targetURL = url;// + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
	
	
	var elemFrame = parent.document.getElementById("frmMain");
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = window.parent.$('#tabframe').tabs('getSelected');
			var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
			window.parent.$('#tabframe').tabs('select',index-1);	
			window.parent.$('#tabframe').tabs('close',index);			
		}
	}
}
/*function checkMLC(obj) {
	alert("call checkMLC function");
	$('[name="departmentUnitCode"]')[0].focus();
	if (obj.checked == true) {
		obj.value = "1";
	} else {
		obj.value = "0";
	}
	alert(document.getElementsByName("isMLC")[0].value);
}*/
/*function onBroughtDead(obj) {
	//$('[name="departmentUnitCode"]')[0].focus();
	if (obj.checked == true) {
		obj.value = "1";
		document.getElementsByName("isBroughtBy")[0].checked = true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	} else {
		obj.value = "0";
		document.getElementsByName("isBroughtBy")[0].checked = false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	}
}*/
function broughtBy(obj) {
	if ($('[name="isUnknown"]')[0].checked == true) {
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
	}

	if (obj.checked == true) {
		$('[name="isRelative"]')[0].focus();
		$('[name="isRelative"]').validatebox({
			validType : [ 'selectCombo[-1]' ]
		});
		$('[name="broughtByName"]').validatebox({
			required : true,
			/*  ## 		Modification Log							
			 	##		Modify Date				:12thDec'14 
			 	##		Reason	(CR/PRS)		:Bug fix 7721
			 	##		Modify By				:Sheeldarshi 

			validType : 'alpha'*/
			validType : 'alphaWithSpace'
				
		});
		$('[name="broughtByLocation"]').validatebox({
			required : true,
			//validType : 'alphaNumeric'
			validType : 'alphaNumericWithSpaces'
			
		});

		$('[name="broughtByPhone"]').validatebox({
			required : true,
			validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
		});

		$('[name="broughtByAddress"]').validatebox({
			required : true//,
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
function onclickFocus()
{

	if($("#broughtById").is(':visible') == true)
	{
		$('[name="isRelative"]')[0].focus();
	}
	else
	{
		$('[name="departmentUnitCode"]')[0].focus();
	}			
}

function onclickFocusInstName(){
	if($("#divRefDtlId").is(':visible') == true)
	{
		$('[name="patRefGnctdHospitalCode"]')[0].focus();
	}
	else
	{
		$('[name="departmentUnitCode"]')[0].focus();
	}	
}
function onclickFocusInstNameOthr(){
	$('[name="patRefHospitalName"]')[0].focus();
}

function enableRelation(obj) {

	if (obj.value == "1")///////relative
	{
		$('[name="broughtByAddress"]').validatebox({
			required : true//,
			/*  ## 		Modification Log							
		 	##		Modify Date				:12thDec'14 
		 	##		Reason	(CR/PRS)		:Bug fix 7721
		 	##		Modify By				:Sheeldarshi 
			validType : 'alphaNumeric'*/
			//validType : 'alphaNumericWithSpaces'
			
		});
		$('[name="broughtByPhone"]').validatebox({
			required : true,
			validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
		});
		$('[name="broughtByRelationCode"]').validatebox({
			validType : [ 'selectCombo[-1]' ]
		});

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

		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' >*</font>Relative Name";
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

		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' >*</font>Officer Name";
		document.getElementById("policeDetailDiv").style.display = "block";
		document.getElementById("policeStnDiv").style.display = "block";
		document.getElementById("phoneBroughtByDiv").style.display = "none";

		//document.getElementById("phoneBroughtByDiv").innerHTML="Phone No.";	
		$('[name="constableDesig"]').validatebox({
			required : true,
			/*  ## 		Modification Log							
		 	##		Modify Date				:12thDec'14 
		 	##		Reason	(CR/PRS)		:Bug fix 7721
		 	##		Modify By				:Sheeldarshi 

					validType : 'alphaNumeric'*/
					validType : 'alphaNumericWithSpaces'
			
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : true,
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
			required : true//,
			//validType : 'alphaNumeric'
			//validType : 'alphaNumericWithSpaces'
				//End:Sheeldarshi	
		});
		$('[name="broughtByPhone"]').validatebox({
			required : true,
			validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
		});
		//$('[name="broughtByRelationCode"]').validatebox({validType: null});

		//document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font>Name";
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
		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' >*</font>Name";
		//document.getElementById("phoneBroughtByDiv").innerHTML="<font color='#FF0000' size='2' >*</font>Phone No.";
	}

	if (obj.value == "-1")///////relative
	{
		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' >*</font> Name";
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

function checkForUnknownOnLoad(obj) {

	var trueFalse =obj.checked;
	//alert(trueFalse+"trueFalse");
	$('[name="isBroughtBy"]')[0].checked = trueFalse;
	if (trueFalse) {
		$("#isRelativeKnownId option[value=1]").remove();
		//document.getElementById("knownRelative").style.display = "none";
		//document.getElementById("unknownRelative").style.display = "block";
		
		$('[name="isAmbulatory"]')[0].checked = false;
		$('[name="isAmbulatory"]')[0].disabled = true;
		
		$('[name="isUnknown"]')[0].value = "1";
		$('[name="patFirstName"]')[0].value = "Unknown";
		$("#patFirstNameId").attr('readonly', true);
		$('[name="patMiddleName"]')[0].value = "";
		$('[name="patLastName"]')[0].value = "";
		$('[name="patMaritalStatusCode"]')[0].value = "-1";
		$('[name="patMaritalStatusCode"]')[0].value = "-1";
		$('[name="patGuardianName"]')[0].value = "";
		$('[name="patHusbandName"]')[0].value = "";
		$('[name="patMotherName"]')[0].value = "";
		$('[name="patCasteCode"]')[0].value = "-1";
		$('[name="patReligionCode"]')[0].value = "-1";
		$('[name="patBirthPlace"]')[0].value = "";
		$('[name="patOccupation"]')[0].value = "-1";
		$('[name="patMonthlyIncome"]')[0].value = "";
		$('[name="patNationalId"]')[0].value = "";
		$('[name="patIsUrban"]')[0].value = "";
		$('[name="patDocType"]')[0].value = "-1";
		$('[name="patCardNo"]')[0].value = "";
		$('[name="patNationalId"]')[0].value = "";
		$('[name="patVisitReason"]')[0].value = "";
		//Address Details corresponding to AddressVO
		$('[name="patAddCountry"]')[0].value = "";
		$('[name="patAddState"]')[0].value = "";
		$('[name="patAddHNo"]')[0].value = "";
		$('[name="patAddStreet"]')[0].value = "";
		$('[name="patAddCity"]')[0].value = "";
		$('[name="patAddPIN"]')[0].value = "";
		$('[name="patAddPhoneNo"]')[0].value = "";
		$('[name="patAddMobileNo"]')[0].value = "";
		$('[name="patAddLandMarks"]')[0].value = "";
		$('[name="patAddEmailId"]')[0].value = "";
		$('[name="patAddPhoneNo"]')[0].value = "";
		$('[name="patAddPhoneOwner"]')[0].value = "";
		$('[name="patAddMobileNo"]')[0].value = "";
		$('[name="patIsUrban"]')[0].value = "-1";
		$('[name="patAddPhoneOwner"]')[0].value = "-1";
		$('[name="isMLC"]')[0].value = "1";

		$('[name="patGuardianName"]').validatebox({
			required : false
		});
		$('[name="patMotherName"]').validatebox({
			required : false
		});
		$('[name="patAddPIN"]').validatebox({
			required : false
		});
		$('[name="patAddMobileNo"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="patAddEmailId"]').validatebox({
			validType : null
		});
		
		$('[name="patEmgCntNo"]').validatebox({
			required : false,
			validType : null
		});
		
		//alert($("#idmark1mandatory")+"show");
		$("#idmark1mandatory").show();
		$("#idmark2mandatory").show();
		
		$('[name="patIdMark1"]').attr("tabindex","1");
		$('[name="patIdMark2"]').attr("tabindex","1");

		
		
		
	} else {
		//$('[name="departmentUnitCode"]')[0].focus();
		var abc= $("#isRelativeKnownId").html();
		$("#isRelativeKnownId").html(abc+"<option value='1'>Relative</option>");

		$('[name="isAmbulatory"]')[0].checked = true;
		$('[name="isAmbulatory"]')[0].disabled = false;
		
		//document.getElementById("knownRelative").style.display = "block";
		//document.getElementById("unknownRelative").style.display = "none";
		$('[name="isUnknown"]')[0].value = "0";
		$('[name="patFirstName"]')[0].value = "";
		$("#patFirstNameId").attr('readonly', false);
		$('[name="patGuardianName"]').validatebox({
			required : false
		});
		$('[name="patMotherName"]').validatebox({
			required : false
		});
		$('[name="isMLC"]')[0].value = "0";
		//alert($("#idmark1mandatory")+"ghhghg");
		$("#idmark1mandatory").hide();
		$("#idmark2mandatory").hide();
		$('[name="patIdMark1"]').attr("tabindex","2");
		$('[name="patIdMark2"]').attr("tabindex","2");


	}

	broughtBy($('[name="isBroughtBy"]')[0]);
	$('[name="isMLC"]')[0].checked = trueFalse;
	$('[name="isMLC"]')[0].disabled = trueFalse;
		
	$('[name="patFirstName"]')[0].readonly = trueFalse;

	$('[name="patMiddleName"]')[0].disabled = trueFalse;
	$('[name="patLastName"]')[0].disabled = trueFalse;
	$('[name="patMaritalStatusCode"]')[0].disabled = trueFalse;
	$('[name="patMaritalStatusCode"]')[0].disabled = trueFalse;
	$('[name="patGuardianName"]')[0].disabled = trueFalse;
	$('[name="patHusbandName"]')[0].disabled = trueFalse;
	$('[name="patMotherName"]')[0].disabled = trueFalse;
	$('[name="patCasteCode"]')[0].disabled = trueFalse;
	$('[name="patReligionCode"]')[0].disabled = trueFalse;
	$('[name="patBirthPlace"]')[0].disabled = trueFalse;
	$('[name="patOccupation"]')[0].disabled = trueFalse;
	$('[name="patMonthlyIncome"]')[0].disabled = trueFalse;
	$('[name="patAmountCollected"]')[0].disabled = trueFalse;
	$('[name="patNationalId"]')[0].disabled = trueFalse;
	$('[name="patIsUrban"]')[0].disabled = trueFalse;
	$('[name="patDocType"]')[0].disabled = trueFalse;
	$('[name="patCardNo"]')[0].disabled = trueFalse;
	$('[name="patNationalId"]')[0].disabled = trueFalse;
	$('[name="patVisitReason"]')[0].disabled = trueFalse;
	//Address Details corresponding to AddressVO
	$('[name="patAddCountry"]')[0].disabled = trueFalse;
	$('[name="patAddCountryCode"]')[0].disabled = trueFalse;
	$('[name="patAddDistrictCode"]')[0].disabled = trueFalse;

	$('[name="patAddState"]')[0].disabled = trueFalse;
	$('[name="patAddStateCode"]')[0].disabled = trueFalse;
	$('[name="patAddDistrict"]')[0].disabled = trueFalse;
	$('[name="patAddHNo"]')[0].disabled = trueFalse;
	$('[name="patAddCityLoc"]')[0].disabled = trueFalse;
	$('[name="patAddStreet"]')[0].disabled = trueFalse;
	$('[name="patAddCity"]')[0].disabled = trueFalse;
	$('[name="patAddPIN"]')[0].disabled = trueFalse;
	$('[name="patAddPhoneNo"]')[0].disabled = trueFalse;
	$('[name="patAddMobileNo"]')[0].disabled = true;
	$('[name="patAddLandMarks"]')[0].disabled = trueFalse;
	$('[name="patAddEmailId"]')[0].disabled = trueFalse;
	$('[name="patAddPhoneNo"]')[0].disabled = trueFalse;
	$('[name="patAddPhoneOwner"]')[0].disabled = trueFalse;
	$('[name="patAddMobileNo"]')[0].disabled = trueFalse;
	$('[name="patIsUrban"]')[0].disabled = trueFalse;
	$('[name="patAddPhoneOwner"]')[0].disabled = trueFalse;
	$('[name="patEmgCntNo"]')[0].disabled = true;
	//Referred Inst Dtl
	if ($('[name="isReferred"]')[0].checked) {
		$('[name="isReferred"]')[0].checked = false;
		Isreferred($('[name="isReferred"]')[0]);
	}

	//Brought by Details

	//Referred Inst Dtl
	/*if ($('[name="isReferred"]')[0].checked) {
		$('[name="isReferred"]')[0].checked = false;
		Isreferred($('[name="isReferred"]')[0]);
	}*/

}

function showHideCatGroupBeneficiaryTile(mode){
	var windowWidth = $(window).width()-100;
	var windowHeight = $(window).height()-300;	
	//var today = $.datepicker.formatDate('dd-M-yy', new Date());

	if(mode=="show"){
		
		$("#agsCounterNoId").validatebox({required : false, validType: null});
		$("#agsNoId").validatebox({required : false,validType : null});
		
		$("#divCatGroupArogyaShreeBeneficiaryId").hide("blind");		
		$("#divCatGroupBeneficiaryId").show("blind");		
		
		/*$("#creditLetterDateId").datepicker({dateFormat: 'dd-M-yy',
			onSelect: function(d,i){
		          if(d !== i.lastVal){
		              $(this).change();
		          }
		     }}).datepicker("setDate", new Date());*/
		
		var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
		var _todayPlus30Days=new Date();
		_todayPlus30Days.setDate(_todayPlus30Days.getDate() + 30);

		var wrappedDate = moment(_todayPlus30Days);
		_todayPlus30Days=wrappedDate.format('DD-MMM-YYYY');
		
		//_todayPlus30Days=_todayPlus30Days.toLocaleFormat('%d-%b-%Y');
		$('#creditLetterDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		$("#creditLetterDateId").change(function(){
		    /*var date2 = $('#creditLetterDateId').datepicker('getDate');
			date2.setDate(date2.getDate()+30); 
			$("#cardvalidityDateId").datepicker({ dateFormat: "dd-M-yy" }).datepicker("setDate", new Date(date2));*/
			var _dateStr=$(this).val().replace(/-/g,' ');
			var _date = new Date(_dateStr);
			_date.setDate(_date.getDate() + 30);
			
			var wrappedDate = moment(_date);
			_date=wrappedDate.format('DD-MMM-YYYY');
			//_date=_date.toLocaleFormat('%d-%b-%Y');
			$('#cardvalidityDateId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_date,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_date);
		});
		/*$("#cardvalidityDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", "30");*/
		$('#cardvalidityDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:_todayPlus30Days,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(_todayPlus30Days);
		$("#creditBillFlagId").val("1");
		
		
		/*$("#creditLetterRefNoId").validatebox({required : true, validType:[ 'alphaNumSpecialCharCannotStartZero']});*/
		$("#creditLetterRefNoId").validatebox({required : true, validType:[ 'alphaNumSpecialChar','NotAllZeroWithSpclChar','consecutiveSpecialCharOnly']});
		/*Added By Mukund on 23.04.2016*/
		$('[name="letterType"]').validatebox({validType: ['selectCombo[-1]']});
		
		$('[name="clientCode"]').validatebox({validType: ['selectCombo[-1]']});
		$("#creditLetterDateId").validatebox({validType:  ['dtltetctdt[\'It should not be grater than Current Date.\']','dtNotGtGvnNoDay[\''+today+'\',\'30\',\'Letter Date must be within 30 days\']']});
		$("#cardvalidityDateId").validatebox({validType: 'dtgtetctdt[\'It should not be lesser than Current Date.\']'});

//		$("#staffCardNoId").validatebox({required : true, validType:['alphaNumericCannotStartZero']});	
		$("#staffCardNoId").validatebox({required : false, validType:['alphaNumeric','NotAllZero']});	
		$("#staffCardNameId").validatebox({required : false, validType: 'alphaWithSpace'});		
		
		$("#agsCounterNoId").val("");
		$("#agsNoId").val("");
		if($('[name="clientCode"]')[0].value=="")
			$('[name="clientCode"]')[0].value="-1";
		$('[name="clientCode"]')[0].value= clientCode;
		$("#clientNameLabel").html(clientName);
		$('[name="clientName"]')[0].value=clientName;
		$('[name="agsDistrictCode"]')[0].value="";
		
	}
	else if(mode=="showAGS"){
		//$("#clientCodeId").val("");
		$("#creditBillFlagId").val("0");
		$("#creditLetterRefNoId").val("");
		$("#creditLetterDateId").val("");
		$("#creditLetterRefNoId").validatebox({required : false});
		$("#creditLetterDateId").validatebox({validType : null});
		$("#cardvalidityDateId").validatebox({validType : null});
		$("#staffCardNoId").validatebox({required : false,validType: null});		
		$("#staffCardNameId").validatebox({validType: null});	
		
		$("#divCatGroupBeneficiaryId").hide("blind");
		
		$("#divCatGroupArogyaShreeBeneficiaryId").show("blind");
		$("#agsCounterNoId").validatebox({required : false, validType: 'numeric'});
		$("#agsNoId").validatebox({required : true, validType:['alphaNumeric','NotAllZero']});
		
		$("#clientCodeId").val("");
		$("#staffCardNoId").val("");
		$("#staffCardNameId").val("");
		$("#relationWithStaffId").val("-1");
		$("#cardvalidityDateId").val("");
		$("#clientNameLabel").html("");
		$('[name="clientName"]')[0].value="";
		$('[name="agsDistrictCode"]')[0].value=$('[name="defaultpatAddDistrictCode"]')[0].value;
		/*Added By Mukund on 23.04.2016*/
		$("#letterTypeId").val("");
		
	}
	else{	
		
		$("#creditBillFlagId").val("0");
		$("#creditLetterRefNoId").val("");
		$("#creditLetterDateId").val("");
		$("#creditLetterRefNoId").validatebox({required : false});
		$("#creditLetterDateId").validatebox({validType : null});
		$("#cardvalidityDateId").validatebox({validType : null});
		$("#staffCardNoId").validatebox({required : false,validType: null});		
		$("#staffCardNameId").validatebox({validType: null});	
		
		$("#agsCounterNoId").validatebox({required : false, validType: null});
		$("#agsNoId").validatebox({required : false,validType : null});
		/*Added By Mukund on 23.04.2016*/
		$("#letterTypeId").validatebox({required : false,validType : null});
		
		$("#divCatGroupBeneficiaryId").hide("blind");
		$("#divCatGroupArogyaShreeBeneficiaryId").hide("blind");
		
		$("#agsCounterNoId").val("");
		$("#agsNoId").val("");
		$("#clientCodeId").val("");
		$("#staffCardNoId").val("");
		$("#staffCardNameId").val("");
		$("#relationWithStaffId").val("-1");
		$("#cardvalidityDateId").val("");
		$("#clientNameLabel").html("");
		$('[name="clientName"]')[0].value="";
		$('[name="agsDistrictCode"]')[0].value="";
		/*Added By Mukund on 23.04.2016*/
		$("#letterTypeId").val("");

	}
}

////////////////////////////////////////////////////////////////////////////

function upload(e)
{
	var processID = document.getElementsByName("processID")[0].value;
	//var crNo = document.getElementsByName("crNoHosCode")[0].value;
	var crNo = document.getElementsByName("crNo")[0].value;
	//var filname = document.getElementsByName("filename")[0].value;
	var filname = document.getElementsByName("filname")[0].value;
	//var url = "/HISGlobal/hisglobal/filetransfer/UploadFile.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname;
	var url = "../../registration/transactions/UploadFile.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname;
	//alert(url);
	openURLInPopup(url,600,400,0,0);
}


//added by manisha gangwar date:28.3.2018
function show(e)
{
	var processID = document.getElementsByName("processID")[0].value;
	var crNo = document.getElementsByName("crNo")[0].value;
	var filname = document.getElementsByName("filname")[0].value;
	//var url = "../../registration/transactions/viewImageFromMongoDBEnlargedImage.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname;
	var url = "../../registration/transactions/EnlargedImage.action"; 
	openURLInPopup(url,400,400,0,0);
}


function openPopup(url,eventObj, height, width)
{
if(eventObj.type=="click" || eventObj.keyCode==13 )
 {
   	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 

if(!child.opener)
   child.opener = self;
 }
 return child
}

function submitForm(actionURL)
{
	document.forms[0].action = actionURL + ".action";
	document.forms[0].submit();
}

function checkMLC(obj) {
	//alert("call checkMLC function");
	$('[name="departmentUnitCode"]')[0].focus();
	if (obj.checked == true) {
		obj.value = "1";
	} else {
		obj.value = "0";
	}
	//alert(document.getElementsByName("isMLC")[0].value);
}
function onBroughtDead(obj) {
	//$('[name="departmentUnitCode"]')[0].focus();
	if (obj.checked == true) {
		obj.value = "1";
		document.getElementsByName("isBroughtBy")[0].checked = true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	//	$("#idmark1mandatory").show();
	//	$("#idmark2mandatory").show();
		$('[name="patIdMark1"]').validatebox({
			required : false,
			validType: ['spaceCheck','consecutiveSpecialChar']
		});
		$('[name="patIdMark2"]').validatebox({
			required : false,
			validType: ['spaceCheck','consecutiveSpecialChar']
		});
	} else {
		obj.value = "0";
		document.getElementsByName("isBroughtBy")[0].checked = false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		$("#idmark1mandatory").hide();
		$("#idmark2mandatory").hide();
	}
}
function broughtBy(obj) {
	/*if ($('[name="isUnknown"]')[0].checked == true || $('[name="isAmbulatory"]')[0].checked == false) {
			//|| $('[name="isBroughtDead"]')[0].checked) {
		if (!obj.checked) {
			$("#isUnknownId").show();
			//$("#isRelativeKnownId").focus();
		}*/
		if ($('[name="isUnknown"]')[0].checked == true                //is brought by compulsory if we select checkbox of is brought dead
				|| $('[name="isBroughtDead"]')[0].checked) {
			if (!obj.checked) {
				$("#isUnknownId").show();
				//$("#isRelativeKnownId").focus();
			}
		obj.checked = "true";
		$('[name="patIdMark1"]').validatebox({
			required : true,
			validType : ['spaceCheck','consecutiveSpecialChar']
		});
		$('[name="patIdMark2"]').validatebox({
			required : true,
			validType : ['spaceCheck','consecutiveSpecialChar']
		});
	//	$("#isRelativeKnownId").focus();
	
	$('[name="isRelative"]')[0].focus();
	} else {
		$("#isUnknownId").hide();
		
		$('[name="patIdMark1"]').validatebox({
			required : false,
			validType: ['spaceCheck','consecutiveSpecialChar']
		});
		$('[name="patIdMark2"]').validatebox({
			required : false,
			validType: ['spaceCheck','consecutiveSpecialChar']
		});
	}

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
function onclickFocus()
{

	if($("#broughtById").is(':visible') == true)
	{
		$('[name="isRelative"]')[0].focus();
	}
	else
	{
		$('[name="departmentUnitCode"]')[0].focus();
	}			
}

function onclickFocusInstName(){
	if($("#divRefDtlId").is(':visible') == true)
	{
		$('[name="patRefGnctdHospitalCode"]')[0].focus();
	}
	else
	{
		$('[name="departmentUnitCode"]')[0].focus();
	}	
}
function onclickFocusInstNameOthr(){
	$('[name="patRefHospitalName"]')[0].focus();
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
		/*$('[name="broughtByRelationCode"]').validatebox({   //comment to relation combo validation not required
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
		$('[name="broughtByRelationCode"]').validatebox({
			required : false,
		});
		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' ></font>Relative Name";
		document.getElementsByName("broughtByRelationCode")[0].disabled = false;
		document.getElementById("phoneBroughtByDiv").style.display = "block";
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("policeStnDiv").style.display = "none";
		document.getElementsByName("broughtByRelationCode")[0].value = "-1"
		document.getElementsByName("broughtByName")[0].value = ""
		document.getElementsByName("broughtByLocation")[0].value = ""
		document.getElementsByName("constableDesig")[0].value = ""
		document.getElementsByName("constableBadgeNo")[0].value = ""
		document.getElementsByName("broughtByPhone")[0].value = ""
		document.getElementsByName("broughtByAddress")[0].value = ""
		document.getElementsByName("broughtByAddress")[0].value = ""
		document.getElementsByName("policeStation")[0].value = ""

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

		//document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font>Name";
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
			validType : 'alphaNumeric'
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

		document.getElementsByName("isRelative")[0].value = "-1"
		document.getElementsByName("broughtByRelationCode")[0].value = "-1"
		document.getElementsByName("broughtByName")[0].value = ""
		document.getElementsByName("broughtByLocation")[0].value = ""
		document.getElementsByName("constableDesig")[0].value = ""
		document.getElementsByName("constableBadgeNo")[0].value = ""
		document.getElementsByName("broughtByPhone")[0].value = ""
		document.getElementsByName("broughtByAddress")[0].value = ""
		document.getElementsByName("isRelative")[0].value = "-1"
		document.getElementsByName("policeStation")[0].value = ""

	}
}

/*function checkForUnknownOnLoad(obj) {

	var trueFalse =obj.checked;
	$('[name="isBroughtBy"]')[0].checked = trueFalse;
	if (trueFalse) {
		$("#isRelativeKnownId option[value=1]").remove();
		//document.getElementById("knownRelative").style.display = "none";
		//document.getElementById("unknownRelative").style.display = "block";
		$('[name="isUnknown"]')[0].value = "1";
		$('[name="patFirstName"]')[0].value = "Unknown";
		$('[name="patMiddleName"]')[0].value = "";
		$('[name="patLastName"]')[0].value = "";
		$('[name="patMaritalStatusCode"]')[0].value = "-1";
		$('[name="patMaritalStatusCode"]')[0].value = "-1";
		$('[name="patGuardianName"]')[0].value = "";
		$('[name="patHusbandName"]')[0].value = "";
		$('[name="patMotherName"]')[0].value = "";
		$('[name="patCasteCode"]')[0].value = "-1";
		$('[name="patReligionCode"]')[0].value = "-1";
		$('[name="patBirthPlace"]')[0].value = "";
		$('[name="patOccupation"]')[0].value = "-1";
		$('[name="patMonthlyIncome"]')[0].value = "";
		$('[name="patNationalId"]')[0].value = "";
		$('[name="patIsUrban"]')[0].value = "";
		$('[name="patDocType"]')[0].value = "-1";
		$('[name="patCardNo"]')[0].value = "";
		$('[name="patNationalId"]')[0].value = "";
		$('[name="patVisitReason"]')[0].value = "";
		//Address Details corresponding to AddressVO
		$('[name="patAddCountry"]')[0].value = "";
		$('[name="patAddState"]')[0].value = "";
		$('[name="patAddHNo"]')[0].value = "";
		$('[name="patAddStreet"]')[0].value = "";
		$('[name="patAddCity"]')[0].value = "";
		$('[name="patAddPIN"]')[0].value = "";
		$('[name="patAddPhoneNo"]')[0].value = "";
		$('[name="patAddMobileNo"]')[0].value = "-1";
		$('[name="patAddLandMarks"]')[0].value = "";
		$('[name="patAddEmailId"]')[0].value = "";
		$('[name="patAddPhoneNo"]')[0].value = "";
		$('[name="patAddPhoneOwner"]')[0].value = "";
		$('[name="patAddMobileNo"]')[0].value = "";
		$('[name="patIsUrban"]')[0].value = "-1";
		$('[name="patAddPhoneOwner"]')[0].value = "-1";
		$('[name="isMLC"]')[0].value = "1";

		$('[name="patGuardianName"]').validatebox({
			required : false
		});
		$('[name="patMotherName"]').validatebox({
			required : false
		});
		
	} else {
		//$('[name="departmentUnitCode"]')[0].focus();
		var abc= $("#isRelativeKnownId").html();
		$("#isRelativeKnownId").html(abc+"<option value='1'>Relative</option>");


		//document.getElementById("knownRelative").style.display = "block";
		//document.getElementById("unknownRelative").style.display = "none";
		$('[name="isUnknown"]')[0].value = "0";
		$('[name="patFirstName"]')[0].value = "";
		$('[name="patGuardianName"]').validatebox({
			required : false
		});
		$('[name="patMotherName"]').validatebox({
			required : false
		});
		$('[name="isMLC"]')[0].value = "0";

	}

	broughtBy($('[name="isBroughtBy"]')[0]);
	$('[name="isMLC"]')[0].checked = trueFalse;
	$('[name="isMLC"]')[0].disabled = trueFalse;
	$('[name="patFirstName"]')[0].readonly = trueFalse;

	$('[name="patMiddleName"]')[0].disabled = trueFalse;
	$('[name="patLastName"]')[0].disabled = trueFalse;
	$('[name="patMaritalStatusCode"]')[0].disabled = trueFalse;
	$('[name="patMaritalStatusCode"]')[0].disabled = trueFalse;
	$('[name="patGuardianName"]')[0].disabled = trueFalse;
	$('[name="patHusbandName"]')[0].disabled = trueFalse;
	$('[name="patMotherName"]')[0].disabled = trueFalse;
	$('[name="patCasteCode"]')[0].disabled = trueFalse;
	$('[name="patReligionCode"]')[0].disabled = trueFalse;
	$('[name="patBirthPlace"]')[0].disabled = trueFalse;
	$('[name="patOccupation"]')[0].disabled = trueFalse;
	$('[name="patMonthlyIncome"]')[0].disabled = trueFalse;
	$('[name="patAmountCollected"]')[0].disabled = trueFalse;
	$('[name="patNationalId"]')[0].disabled = trueFalse;
	$('[name="patIsUrban"]')[0].disabled = trueFalse;
	$('[name="patDocType"]')[0].disabled = trueFalse;
	$('[name="patCardNo"]')[0].disabled = trueFalse;
	$('[name="patNationalId"]')[0].disabled = trueFalse;
	$('[name="patVisitReason"]')[0].disabled = trueFalse;
	//Address Details corresponding to AddressVO
	$('[name="patAddCountry"]')[0].disabled = trueFalse;
	$('[name="patAddCountryCode"]')[0].disabled = trueFalse;
	$('[name="patAddDistrictCode"]')[0].disabled = trueFalse;

	$('[name="patAddState"]')[0].disabled = trueFalse;
	$('[name="patAddStateCode"]')[0].disabled = trueFalse;
	$('[name="patAddDistrict"]')[0].disabled = trueFalse;
	$('[name="patAddHNo"]')[0].disabled = trueFalse;
	$('[name="patAddCityLoc"]')[0].disabled = trueFalse;
	$('[name="patAddStreet"]')[0].disabled = trueFalse;
	$('[name="patAddCity"]')[0].disabled = trueFalse;
	$('[name="patAddPIN"]')[0].disabled = trueFalse;
	$('[name="patAddPhoneNo"]')[0].disabled = trueFalse;
	$('[name="patAddMobileNo"]')[0].disabled = true;
	$('[name="patAddLandMarks"]')[0].disabled = trueFalse;
	$('[name="patAddEmailId"]')[0].disabled = trueFalse;
	$('[name="patAddPhoneNo"]')[0].disabled = trueFalse;
	$('[name="patAddPhoneOwner"]')[0].disabled = trueFalse;
	$('[name="patAddMobileNo"]')[0].disabled = trueFalse;
	$('[name="patIsUrban"]')[0].disabled = trueFalse;
	$('[name="patAddPhoneOwner"]')[0].disabled = trueFalse;
	//Referred Inst Dtl
	if ($('[name="isReferred"]')[0].checked) {
		$('[name="isReferred"]')[0].checked = false;
		Isreferred($('[name="isReferred"]')[0]);
	}

	//Brought by Details

	//Referred Inst Dtl
	if ($('[name="isReferred"]')[0].checked) {
		$('[name="isReferred"]')[0].checked = false;
		Isreferred($('[name="isReferred"]')[0]);
	}

}*/

function setCompany()
{
	//alert($("#clientCodeId option:selected").html());
	$('[name="clientName"]')[0].value=$("#clientCodeId option:selected").html();
}

function setRelation()
{
	//alert($("#relationWithStaffId option:selected").html());
	var tempStaffName=$('[name="staffCardName"]')[0].value;
	$('[name="relationNameWithStaff"]')[0].value=$("#relationWithStaffId option:selected").html();
	$('[name="patFirstName"]')[0].value="";
	$('[name="patFirstNameInMultiLang"]')[0].value="";
	$('[name="patGuardianName"]')[0].value="";$('[name="patMotherName"]')[0].value="";
	
	if($('[name="relationNameWithStaff"]')[0].value=="Self" || $('[name="relationNameWithStaff"]')[0].value=="Father" || $('[name="relationNameWithStaff"]')[0].value=="Mother")
	{
		$('[name="staffCardName"]')[0].value=tempStaffName;
		setPatNameSelf();
	}
}

function setPatNameSelf()
{
	//alert($('[name="relationNameWithStaff"]')[0].value);
	if($('[name="relationNameWithStaff"]')[0].value=="Self")
	{
		$('[name="patFirstName"]')[0].value=$('[name="staffCardName"]')[0].value;
		multilingualConversion($('[name="patFirstName"]')[0],document.getElementById('patFirstNameInMultiLangId'));
	}
	if($('[name="relationNameWithStaff"]')[0].value=="Father")
	{
		$('[name="patGuardianName"]')[0].value=$('[name="staffCardName"]')[0].value;
	}
	if($('[name="relationNameWithStaff"]')[0].value=="Mother")
	{
		$('[name="patMotherName"]')[0].value=$('[name="staffCardName"]')[0].value;
	}
	
}
//Start:Sheeldarshi
//Reason:Bug 10060 - Spouse field should be disable as per the marital status field.
function enableDisableSpouse()
{
	var maritalVal=$("#patMaritalStatusCodeId").val();
	if (maritalVal == "1" || maritalVal == "4")	
			{
		 	$('[name="patHusbandName"]').val("");
			$('[name="patHusbandName"]').attr("disabled", "disabled");
			}
	else
			{
			$('[name="patHusbandName"]').removeAttr("disabled");
			}
}

function changeTabIndex()
{
	/*var div1 = document.getElementsByName("patAddHNo");
	//alert(div1);
	var align = div1.getAttribute("tabindex").val();

	alert(align);*/
	
	 $('[name="patMiddleName"]').attr('tabindex','0');
	 $('[name="patLastName"]').attr('tabindex','0');
	 $('[name="patAge"]').attr('tabindex','0');
	 $('[name="patAgeUnit"]').attr('tabindex','0');
	 $('[name="isActualDob"]').attr('tabindex','0');
	 $('[name="patDOBId_Dup"]').attr('tabindex','0');
	 //$('[name="patDOBId"]').attr('tabindex','0');
	 $('[name="patGenderCode"]').attr('tabindex','0');
	 $('[name="patCasteCode"]').attr('tabindex','0');
	 $('[name="patGuardianName"]').attr('tabindex','0');
	 $('[name="patHusbandName"]').attr('tabindex','0');
	 $('[name="patMotherName"]').attr('tabindex','0');
	 $('[name="patMaritalStatusCode"]').attr('tabindex','0');
	 $('[name="patReligionCode"]').attr('tabindex','0');
	 $('[name="patBirthPlace"]').attr('tabindex','0');
	 $('[name="patOccupation"]').attr('tabindex','0');
	 $('[name="patMonthlyIncome"]').attr('tabindex','0');
	 $('[name="patVisitReason"]').attr('tabindex','0');
	 $('[name="patAddCountryCode"]').attr('tabindex','0');
	 $('[name="patAddStateCode"]').attr('tabindex','0');
	 $('[name="patAddState"]').attr('tabindex','0');
	 $('[name="patAddHNo"]').attr('tabindex','0');
	 $('[name="patAddStreet"]').attr('tabindex','0');
	 $('[name="patAddCityLoc"]').attr('tabindex','0');
	 $('[name="patAddDistrictCode"]').attr('tabindex','0');
	 $('[name="patAddDistrict"]').attr('tabindex','0');
	 $('[name="patAddCity"]').attr('tabindex','0');
	 $('[name="patAddPIN"]').attr('tabindex','0');
	 $('[name="patAddLandMarks"]').attr('tabindex','0');
	 $('[name="patIsUrban"]').attr('tabindex','0');
	 $('[name="patAddEmailId"]').attr('tabindex','0');
	 $('[name="patAddPhoneNo"]').attr('tabindex','0');
	 $('[name="patAddPhoneOwner"]').attr('tabindex','0');
	 $('[name="patAddMobileNo"]').attr('tabindex','0');
	 $('[name="patFirstName"]').attr('tabindex','0');
}

function openWhiteCardPopup()
{
	var action = "/HISRegistration/registration/transactions/openWhiteCardPopupEmgRegistration.action";
openURLInPopupWithoutClose(action, '600', '200');
}

//multiplication table d
var d = [
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
[1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
[2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
[3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
[4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
[5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
[6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
[7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
[8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
];

//permutation table p
var p = [
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
[1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
[5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
[8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
[9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
[4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
[2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
[7, 0, 4, 6, 9, 1, 3, 2, 5, 8]
];

//inverse table inv
var inv = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];

//converts string or number to an array and inverts it
function invArray(array) {

if (Object.prototype.toString.call(array) === "[object Number]") {
    array = String(array);
}

if (Object.prototype.toString.call(array) === "[object String]") {
    array = array.split("").map(Number);
}

return array.reverse();

}

//generates checksum
function generate(array) {

var c = 0;
var invertedArray = invArray(array);

for (var i = 0; i < invertedArray.length; i++) {
    c = d[c][p[((i + 1) % 8)][invertedArray[i]]];
}

return inv[c];
}

//validates checksum
function validate(array) {

var c = 0;
var invertedArray = invArray(array);

for (var i = 0; i < invertedArray.length; i++) {
    c = d[c][p[(i % 8)][invertedArray[i]]];
}

//return (c === 0);
return c;
}

$('[name="patNationalId"]').bind("keyup change", function(e){ onChangeAadhaarField(e);})

function onChangeAadhaarField(e){
	var len = $('[name="patNationalId"]').val().length;
	if(len == 12)
	{
		var array = $('[name="patNationalId"]')[0].value;
		var ret = validate(array);
		if(ret != 0 )
		{
			//alert("Invalid Aadhaar No.\nPlease enter a valid Aadhaar No.");
			//$('[name="patNationalId"]').val("");
			$("#divAadharConsentId").hide();
		}
		else
		{
			//if ( $("#divAadharConsentId").is( ":hidden" ) ) {
			    $( "#divAadharConsentId" ).show( "blind" );
			  //}
			
			//alert($('[name="isAadharConsent"]')[0].value);
			$("#isAadharConsentYes").attr('checked', true);
			checkUncheckAadharConsent($('[name="isAadharConsent"]')[0]);
		}	
	     
	}
	else
	{
	/*	$('[name="isAadharConsent"]').val("");
		checkUncheckAadharConsent($('[name="isAadharConsent"]')[0]);*/
		$("#divAadharConsentId").hide();
	}
	
}

function checkUncheckAadharConsent(event)
{
	//alert(event.value)
	$('[name="isAadharConsentGiven"]').val(event.value);
}

//Added by vasu dated on 02.May.18 for ambulatory patient
function checkForAmbulatory(obj){
	
	var trueFalse =obj.checked;
	//$('[name="isBroughtBy"]')[0].checked = true;
	
	if (!trueFalse) {
		//$('[name="isAmbulatory"]')[0].value = "0";
		//$('[name="isAmbulatory"]').val("0");
		//alert("3483: "+ document.getElementsByName("isAmbulatory")[0].value );
		document.getElementsByName("isAmbulatory")[0].value = "0";
		//alert("3485: "+ document.getElementsByName("isAmbulatory")[0].value );
		//$("#isRelativeKnownId option[value=1]").remove();
		$('[name="isBroughtBy"]')[0].checked = true;
		
		/*$('[name="isMLC"]')[0].checked = false;
		$('[name="isMLC"]')[0].disabled = true;*/
		
		$('[name="isUnknown"]')[0].checked = false;
		$('[name="isUnknown"]')[0].disabled = true;
		//document.getElementById("knownRelative").style.display = "none";
		//document.getElementById("unknownRelative").style.display = "block";
		
		
		//alert($("#idmark1mandatory")+"show");
		$("#idmark1mandatory").show();
		$("#idmark2mandatory").show();
		
		$('[name="patIdMark1"]').attr("tabindex","1");
		$('[name="patIdMark2"]').attr("tabindex","1");

		//alert("checkUncheckIsAmbulatory: If");
		
		
	} else {
		document.getElementsByName("isAmbulatory")[0].value = "1";
		$('[name="isBroughtBy"]')[0].checked = false;
		//$('[name="departmentUnitCode"]')[0].focus();
		$('[name="isMLC"]')[0].disabled = false;
		$('[name="isUnknown"]')[0].disabled = false;
		/*var abc= $("#isRelativeKnownId").html();
		$("#isRelativeKnownId").html(abc+"<option value='1'>Relative</option>");*/

		$("#idmark1mandatory").hide();
		$("#idmark2mandatory").hide();
		$('[name="patIdMark1"]').attr("tabindex","2");
		$('[name="patIdMark2"]').attr("tabindex","2");
		document.getElementsByName("isAmbulatory")[0].value = "1";
		//alert("checkUncheckIsAmbulatory: else");
	}

	broughtBy($('[name="isBroughtBy"]')[0]);
	//Referred Inst Dtl
	if ($('[name="isReferred"]')[0].checked) {
		$('[name="isReferred"]')[0].checked = false;
		Isreferred($('[name="isReferred"]')[0]);
	}
}

//End Vasu
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
//End