var enterCount = 0;
$(document).ready(function(){
	$(document).ajaxStart(function(){
		//$('.overlay1').show();
		if(parent.frameElement !=null && parent.frameElement!=undefined && parent.frameElement.id=="frmMain"){
			parent.parent.ajaxStart();
		}else{
			parent.ajaxStart();
		}
	});
	$(document).ajaxComplete(function(){
		//$('.overlay1').hide();
		if(parent.frameElement !=null && parent.frameElement!=undefined && parent.frameElement.id=="frmMain"){
			parent.parent.ajaxComplete();
		}else{
			parent.ajaxComplete();
		}
	});

	if($("#flagHasActionErrorId").val()=='false' )
	{
		splregistration.fetchDefaultValues();
		$('[name="departmentUnitCode"]')[0].focus();
		
		
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
	}
});

var departmentJSONArray=[];
var categoryJSONArray=[];
var veriyDocJSONArray=[];
var defaultValJSONArray=[];
var ageBoundRange=125;
var docValidType = 'alphaNumSpecialChar';
var catIdValidType='alphaNumSpecialChar';
var ageValidType = "selectComboNotSpecifiedVal['-1',1,'patGenderCode','-1','Kindly Select Department First']";
var isBarcodeSlipPrint_Yes = 1;
var isBarcodeSlipPrint_No = 0;

var splregistration={
		
	fetchDefaultValues:function ()
	{
		$('[name="departmentUnitCode"]')[0].focus();
		var action 	= "/HISRegistration/registration/transactions/populateformvaluesSplRegistration.action";
		
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
							splregistration.processDepartmentNode(elementName,elementNode);
						}
						else if(elementName=='patPrimaryCatCode')
						{
							splregistration.processCategoryNode(elementName,elementNode);
						}
						else if(elementName=='patDocType')
						{
							splregistration.processVerifyDocNode(elementName,elementNode);
						}
						else if(elementName=='renewalConfig')
						{
							if(elementNode.getAttribute('alreadyRegistered')!="" && elementNode.getAttribute('alreadyRegistered')!="0"){
								$("#divAlreadyRegisteredId").show();
								$('[name="alreadyRegisteredFromRenewalConfig"]')[0].value=elementNode.getAttribute('alreadyRegistered');
								$('[name="alreadyRegisteredFlag"]')[0].value=elementNode.getAttribute('alreadyRegistered');
							}
							//By Mukund on 16.03.18 for BarcodeSlip print configuration
							//alert("elementNode.getAttribute('barcodeSlipPrintFlag'): "+elementNode.getAttribute('barcodeSlipPrintFlag'));
							if (elementNode.getAttribute('barcodeSlipPrintFlag') != null && elementNode.getAttribute('barcodeSlipPrintFlag') != "") {
								$('[name="barcodeSlipPrintFlag"]')[0].value = elementNode.getAttribute('barcodeSlipPrintFlag');
							}
							/*else{
								$("#divAlreadyRegisteredId").hide();
							}*/
							if (elementNode.getAttribute('isSnomedServiceOn') != null && elementNode.getAttribute('isSnomedServiceOn') != "") {
								$('[name="isSnomedServiceOn"]')[0].value = elementNode.getAttribute('isSnomedServiceOn');
							}
						}
						else if(elementName=='defaults'){
							
						}else if(elementName == 'paymentModeCode'){
							splregistration.processPaymentModeNode(elementName, elementNode);
						}else{
							splregistration.processGeneralNode(elementName,elementNode);
						}
					}
					
					splregistration.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
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
	for(var i=0;i<elementNode.childNodes.length;i++ )
	{
		var optionNode=elementNode.childNodes[i];
		//alert("JSONStr"+optionNode.getAttribute("value"));
		var departmentJSONObject=jQuery.parseJSON(optionNode.getAttribute("value"));
		departmentJSONArray[departmentJSONArray.length]=departmentJSONObject;
		optionText+="<option value='"+departmentJSONObject.deptUnitCode+"'>"+(departmentJSONObject.departmentName)+"</option>";
		
	}
	
	if(document.getElementsByName(elementName).length!=0)
		document.getElementsByName(elementName)[0].innerHTML=optionText;
  },
  processCategoryNode:function(elementName,elementNode)
  {
	var optionText="<option value='-1'>Select Value</option>";
	for(var i=0;i<elementNode.childNodes.length;i++ )
	{
		var optionNode=elementNode.childNodes[i];
		//alert("JSONStr"+optionNode.getAttribute("value"));
		var categoryJSONObject=jQuery.parseJSON(optionNode.getAttribute("value"));
		categoryJSONArray[categoryJSONArray.length]=categoryJSONObject;
		optionText+="<option value='"+categoryJSONObject.patPrimaryCatCode+"'>"+(categoryJSONObject.patPrimaryCatName)+"</option>";
		
/*		var parId = $('[name="'+elementName+'"]').closest('div').attr('id');
		alert(parId);
		document.getElementById(''+parId+'').innerHTML = '<select name="patPrimaryCatCode" id="patPrimaryCatCodeId" tabindex="1" class="select90prcnt" onchange="opdregistration.onchange_patPrimaryCategory()">'+
		+'<option value="-1">Select Value</option>'
		+'</select>';*/
	}
	if(document.getElementsByName(elementName).length!=0)
		document.getElementsByName(elementName)[0].innerHTML=optionText;
	
	if(document.getElementsByName(elementName).length>=0)  // added by manisha for setting default patcat 'general'
	{  $('[name="'+elementName+'"]')[0].value = "11";
	var priCatObj = $('[name="patPrimaryCatCode"]')[0];
	if(priCatObj.selectedIndex == undefined || priCatObj.selectedIndex == null || priCatObj.selectedIndex == "-1")
		$('[name="'+elementName+'"]')[0].value = "-1";
	   splregistration.onchange_patPrimaryCategory();
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
		$("#isReferCompusoryId").hide();
		$('[name="isReferred"]')[0].checked=false;
	    Isreferred($('[name="isReferred"]')[0]);
		return;
	}
	for(var i=0; i<departmentJSONArray.length; i++){
		if(departmentJSONArray[i].deptUnitCode==deptCode){
			$('[name="isReferCompulsory"]')[0].value=departmentJSONArray[i].isReferCompulsory;
			$("#isReferCompusoryId").hide();
			if($('[name="isReferCompulsory"]')[0].value=='1'){
				$('[name="isReferred"]')[0].checked=true;
				  Isreferred($('[name="isReferred"]')[0]);
			}
			else{
				$('[name="isReferred"]')[0].checked=false;
				  Isreferred($('[name="isReferred"]')[0]);
			}
			$('[name="patGenderCode"]')[0].value=departmentJSONArray[i].defaultGenderCode;
			$('[name="defaultpatGenderCode"]')[0].value=departmentJSONArray[i].defaultGenderCode;
			$('[name="departmentCode"]')[0].value=departmentJSONArray[i].departmentCode;
			$('[name="defaultpatGenderCode"]')[0].value=departmentJSONArray[i].defaultGenderCode;
			$('[name="departmentUnitCode"]')[0].value=departmentJSONArray[i].deptUnitCode;
			
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
			
			if($('[name="isActualDob"]')[0].checked){
				var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
				var ageRangeValidType='range[1,'+maxAgeRange+']';
				$("#patAgeId").validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
				$("#patDOBId").validatebox({required: false, validType: ['date[\'patDOB\',\'dd-mmm-yyyy\']', dobValidType]});

			}else{
				$("#patAgeId").validatebox({required: false, validType:null});
				var dobValidType='dtltetDMY['+ageBoundRange+',\'y\']';
				$("#patDOBId").validatebox({required: true, validType: ['date[\'patDOB\',\'dd-mmm-yyyy\']', dobValidType]});
				$("#patAgeId").validatebox({required: false, validType: ['numeric', ageRangeValidType]});
			}
			 showDivAgeDob();
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
		}
	}
	$("#patAddCountryCodeId").bind("change",splregistration.onchange_patAddCountryCode);
	
	
  },
  onchange_patAddCountryCode:function()
  {
	var objCountry  =document.getElementsByName("patAddCountryCode")[0];
	var countryCode = objCountry.options[objCountry.selectedIndex].value;
	var action 		= "/HISRegistration/registration/transactions/getStateSplRegistration.action?countryCode="+countryCode;
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
	}
	else
	{
		$("#divStateTextId").hide();
		$("#divStateComboId").show();
		$("#patAddStateCodeId").html("<option value='-1'>Select Value</option>");
		$("#divDistrictTextId").hide();
		$("#divDistrictComboId").show();
		$("patAddDistrictCodeId").html("<option value='-1'>Select Value</option>");
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
					
					splregistration.processGeneralNode(elementName,elementNode);
					if($('[name="patAddCountryCode"]')[0].value==$('[name="defaultpatAddCountryCode"]')[0].value)
						$('[name="patAddStateCode"]')[0].value=$('[name="defaultpatAddStateCode"]')[0].value
				}
				
				if(flagStateNDIstComboReqd)
				{
					$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[-1]']});
					$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[-1]']});
					$("#patAddStateId").validatebox({required: false,	validType: 'alphaNumSpecialChar' });
					$("#patAddDistrictId").validatebox({required: false,	validType: 'alphaNumSpecialChar' });
					//$("#patAddStateCodeId").bind("change",splregistration.onchange_patAddStateCode);
					splregistration.onchange_patAddStateCode();
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
	var action 			= "/HISRegistration/registration/transactions/getDistrictSplRegistration.action?countryCode="+countryCode+"&stateCode="+stateCode;
	
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					splregistration.processGeneralNode(elementName,elementNode);
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
	var action 	= "/HISRegistration/registration/transactions/getRefDeptSplRegistration.action?refHospCode="+refHospCode+"&flafRefHospOrInst="+flafRefHospOrInst;
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					splregistration.processGeneralNode(elementName,elementNode);
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
		var PRIMARY_CATEGORY_SENIOR_CITIZEN = $('[name="seniorCitizenCatCode"]')[0].value;
		
		//$('[name="paymentModeCode"]').val("0");
		
		if(PrimaryCatCode=="-1"){
			$("#divCatCardId").hide();
			$("#divImgCatCardId").hide();
			$("#divSpareCatId").show();
			showHideCatGroupBeneficiaryTile("hide");
			$('#divFeeVal3').hide();$('#divFeeVal2').hide();
			$('[name="paymentModeCode"]').val("1");
			$("#patPrimaryCatCodeId").validatebox({
				validType : [ 'selectCombo[-1]' ]
			});
		}else{
			$('#divFeeVal2').show();
			var pymntMdeCde = "paymentModeCode";
			var paymentModeLength = $('[name="paymentModeCode"]').children('option').length;
			// Added By Raj Kumar for Auto Select Pay Mode
			for(var w=0; w<paymentModeLength; w++){
				var paymentModeObj = $('[name="paymentModeCode"]')[0];
				var paymentModeName = paymentModeObj.options[w].text;
				if(PrimaryCatName.toUpperCase() === paymentModeName.toUpperCase()){
					$('[name='+pymntMdeCde+'] option:contains('+paymentModeName+')').prop('selected', true);
					splregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
					break;
				}else{
					//$('[name="paymentModeCode"]').val("1");
					//splregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
					$('[name="paymentModeCode"]').val(paymentModeObj.options[0].value);
					splregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);

				}
			}
			// ended 	
		}
		
		for(var i=0; i<categoryJSONArray.length; i++)
		{
			if(categoryJSONArray[i].patPrimaryCatCode==PrimaryCatCode)
			{
				
				if (categoryJSONArray[i].patCatGroup == PATIENT_REG_CATEGORY_GROUP_PAID) {
					$("#patPrimaryCatCodeId").focus();
					var catWiseChargeValidType = 'validatechargeforcat['+ categoryJSONArray[i].patCatGroup + ','+ categoryJSONArray[i].charges + ']';
					$("#patPrimaryCatCodeId").validatebox({validType : [catWiseChargeValidType]});
				} else {
					$("#patPrimaryCatCodeId").focus();
					$("#patPrimaryCatCodeId").validatebox({validType : null});
				}

				if (categoryJSONArray[i].patCatGroup != PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE
						&& categoryJSONArray[i].patCatGroup != PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) {
					if (categoryJSONArray[i].charges == "-1") {
						$('[name="patAmountCollected"]')[0].value = 0;
						document.getElementById("amount").innerHTML = 0;
						$('[name="patActualAmount"]')[0].value = 0;
					} else {
						$('[name="patAmountCollected"]')[0].value = categoryJSONArray[i].charges;
						document.getElementById("amount").innerHTML  = categoryJSONArray[i].charges;
						$('[name="patActualAmount"]')[0].value = categoryJSONArray[i].charges;
					}
				} else {

					if (categoryJSONArray[i].charges == "-1") {
						$('[name="patAmountCollected"]')[0].value = 0;
						document.getElementById("amount").innerHTML  = 0;
						$('[name="patActualAmount"]')[0].value = 0;
					} else {
						$('[name="patAmountCollected"]')[0].value = 0;
						document.getElementById("amount").innerHTML  = 0;
						$('[name="patActualAmount"]')[0].value = categoryJSONArray[i].charges;
					}

				}
				
				//$('[name="patAmountCollected"]')[0].value= categoryJSONArray[i].charges;
				$('[name="isIdRequired"]')[0].value=categoryJSONArray[i].idReqFlag;
				$('[name="patPrimaryCat"]')[0].value= categoryJSONArray[i].patPrimaryCatName;
				$('[name="patCatShortName"]')[0].value= categoryJSONArray[i].patCatShortName;
				$('[name="patPrimaryCatGrp"]')[0].value= categoryJSONArray[i].patCatGroup;
				
				if(categoryJSONArray[i].patCatGroup==PATIENT_REG_CATEGORY_GROUP_BENEFICIARY){
					showHideCatGroupBeneficiaryTile("show");
				}
				else{
					showHideCatGroupBeneficiaryTile("hide");
				}
				
				catIdValidType= splregistration.getDocValidtype(categoryJSONArray[i].idValidationType);
				//alert("categoryJSONArray[i].idReqFlag :"+categoryJSONArray[i].idReqFlag);
				if(categoryJSONArray[i].idReqFlag=="0")
				{
					$('[name="patIdNo"]')[0].value="";
					$("#shownPatIdNoId").validatebox({required: false, validType: null});
					$("#divCatCardId").hide();
					$("#divImgCatCardId").hide();
					$("#divSpareCatId").show();
					//$("#divAlreadyRegisteredId").show();
					if($('[name="alreadyRegisteredFromRenewalConfig"]')[0].value!="" && $('[name="alreadyRegisteredFromRenewalConfig"]')[0].value!="0"){
						$("#divAlreadyRegisteredId").show();
					}
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
					//$("#divAlreadyRegisteredId").show();
					if($('[name="alreadyRegisteredFromRenewalConfig"]')[0].value!="" && $('[name="alreadyRegisteredFromRenewalConfig"]')[0].value!="0"){
						$("#divAlreadyRegisteredId").show();
					}
					$("#shownPatIdNoId").validatebox({required: true, validType: catIdValidType});
					
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
			$("#divCardNoId").hide();
		}
		for(var i=0; i<veriyDocJSONArray.length; i++){
			if(veriyDocJSONArray[i].patDocType==docType){
				$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);
				
				//alert("patDocIdSize :"+ veriyDocJSONArray[i].patDocIdSize);
				docValidType=  splregistration.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);
				
				var isize = veriyDocJSONArray[i].patDocIdSize;
				var docValidTypeNLength='equalLength['+isize+']';
				$('[name="patCardNo"]').validatebox({required: true, validType: [docValidType,docValidTypeNLength]});
				break;
				
			}else{
				$('[name="patCardNo"]').validatebox({required: false});
			}
		}
  },
  getDocValidtype:function(idValidTypeCode)
  {
	var varDocValidType='alphaNumSpecialChar';
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
		    	document.getElementsByName(patientKey)[0].value=eval(patientKey);
		    	if(patientKey=="patPrimaryCatCode")
		    		splregistration.onchange_patPrimaryCategory();
		    	//document.getElementsByName(patientKey)[0].disabled=true;
		    }
		    
		 }
	 }
	//setMandatoryRefdReadOnlyTrueFalse(false);
	setMandatoryReadOnlyTrueFalse(false);
	
  },
  fetchPatDtlBasedOnPatCatCardNo:function(patientJSONObject)
  {
	//alert("inside fetchPatDtlBasedOnPatCatCardNo");
	splregistration.populatePatientDtl(patientJSONObject);
	/*if($('[name="patDOB"]')[0].value!="")
		$('[name="isActualDob"]')[1].checked=true;*/
	
  },
  savePatientDtl:function()
  {
	  if(!validateDivDob()) return;
	  
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

	  //return false;
	  //$('[name="patPrimaryCat"]')[0].value= $("#patPrimaryCatCodeId option:selected").text();
	  	//For Submission
	  
	  	var action 	= "/HISRegistration/registration/transactions/saveSplRegistration.action";
	  	$('#submitId').hide();
		$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,data:$("#SplRegistration").serialize(), success:function(data){
			var returnDocument=data;
			var rootNode=returnDocument.getElementsByTagName("root")[0];
			
			var elementNode=rootNode.childNodes[0];
			var elementName=elementNode.nodeName;
			if(elementName=='savedMsgDtl')
			{
				splregistration.initializeAfterSave(elementName,elementNode);
			}
			$('#submitId').show();
			
		},error: function(errorMsg,textstatus,errorthrown) {
			$('#submitId').show();
		    alert('savePatientDtl '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		    
		}});
  },
  initializeAfterSave:function(saveMsgElementName,saveMsgElementNode)
  {
	  $('#fatherorSpouseError').hide();
	  var isSavedSuccussful	   = saveMsgElementNode.getAttribute("isSavedSuccussful");
	  var msg				   = saveMsgElementNode.getAttribute("savedMessage");
	  var isFormFieldTobeReset = saveMsgElementNode.getAttribute("isFormFieldTobeReset");
	  var isPrintFlag	   	   = saveMsgElementNode.getAttribute("isPrintFlag");
	  var printDivContent	   = saveMsgElementNode.getAttribute("printDivContent");
	  var tokenTosetAfterSave  = saveMsgElementNode.getAttribute("tkn");
	  if(isSavedSuccussful=="1")
	  {
		  $("#divNormalMsgId").html(msg);
		  $("#divErrorMsgId").html("");
		  if(isPrintFlag=="1"){
			  $("#divPrintId").html(printDivContent);
			  get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
			  
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
		  document.getElementsByName('token')[0].value = tokenTosetAfterSave;
		  if($('[name="barcodeSlipPrintFlag"]')[0].value == isBarcodeSlipPrint_Yes)
			  	manageBarcodePrintProcess();
		  if(isFormFieldTobeReset=="1"){
			  //if(confirm(msg+"\nDo you Want To Reset The Form"))
				  splregistration.setFormFieldsAfterSave();
				  enterCount = 0;
		  }
	  
	  }
	  else{
		  $("#divErrorMsgId").html(msg);
		  $("#divNormalMsgId").html("");
		  $("#divPrintId").html("");
		  //if(confirm(msg+"\nDo you Want To Reset The Form"))
		  if(isFormFieldTobeReset=="1"){
				  splregistration.setFormFieldsAfterSave();
				  enterCount = 0;
		  }
	  }
	  
  },
  setFormFieldsAfterSave:function()
  {
	  $('#fatherorSpouseError').hide();
	  $('.validatebox-text').removeClass('validatebox-invalid');
	  //$('.validatebox-text').removeClass('validatebox-invalid');
	  
	  $('[name="patPrimaryCat"]')[0].value="";
	  $('[name="departmentUnitCode"]')[0].value="-1";
	  $('[name="patIdNo"]')[0].value="";
	  $('[name="hiddenPatIdNo"]')[0].value="";
	  $('[name="alreadyRegisteredFlag"]')[0].checked=false;
	  $("#hiddenCatOrRegstrdPopupFlagId").val("");
	  //$("#divCatCardId").hide();
	  $('[name="patCatShortName"]')[0].value="";
	  $('[name="patAmountCollected"]')[0].value="";
	  $('[name="paymentModeCode"]').val("1");//default value for cash
	  splregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);

	  $('[name="patPrimaryCatCode"]')[0].value= "11"; //"-1"; // changed by manisha for setting default patcat 'general'
	var priCatObj = $('[name="patPrimaryCatCode"]')[0];
	if(priCatObj.selectedIndex == undefined || priCatObj.selectedIndex == null || priCatObj.selectedIndex == "-1")
		$('[name="patPrimaryCatCode"]')[0].value = "-1";
			  
	  splregistration.onchange_patPrimaryCategory();
	  $('[name="patFirstName"]')[0].value="";
	  $('[name="patMiddleName"]')[0].value="";
	  $('[name="patLastName"]')[0].value="";
	  
	 // $('[name="patFirstNameInMultiLang"]')[0].value="";
	 // $('[name="patMiddleNameInMultiLang"]')[0].value="";
	 // $('[name="patMiddleNameInMultiLang"]')[0].value="";
	  
	  $('[name="isActualDob"]')[0].checked=true;//if (age provided) isActualDob=0 else isActualDob=1
	  showDivAgeDob();
	  $('[name="patAge"]')[0].value="";
	  $('[name="patAgeUnit"]')[0].value="Yr";
	  $('[name="patDOB"]')[0].value="";
	  $('[name="patDOB_Dup"]')[0].value = "";

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
	  
	  $('[name="patNationalId"]')[0].value="";	
	  
	  $('[name="patIsUrban"]')[0].value="";
	  $('[name="patDocType"]')[0].value="-1";
	  splregistration.onchange_patDocType();
	   
	  $('[name="patCardNo"]')[0].value="";
	  $('[name="patNationalId"]')[0].value="";
	  $('[name="patVisitReason"]')[0].value="";
	  
	  //alert($('[name="isReferred"]')[0].checked)
	  if($('[name="isReferred"]')[0].checked){
		  $('[name="isReferred"]')[0].checked=false;
		  $('[name="isReferCompulsory"]')[0].value="";
		  Isreferred($('[name="isReferred"]')[0]);
	  }

	  //Address Details corresponding to AddressVO
	  $('[name="patAddCountry"]')[0].value="";
	  $('[name="patAddCountryCode"]')[0].value= $('[name="defaultpatAddCountryCode"]')[0].value;
	  $('[name="patAddState"]')[0].value="";
	  $('[name="patAddStateCode"]')[0].value= $('[name="defaultpatAddStateCode"]')[0].value;
	  splregistration.onchange_patAddCountryCode();
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
	  $('[name="prevCrNo"]')[0].value="";
	  $('[name="patIdNo"]')[0].value="";
	  
	  $('[name="patPrimaryCatGrp"]')[0].value="";
	  $('[name="patDocTypeName"]')[0].value="";
	  
	  //Referred Inst Dtl
	 
	  setMandatoryReadOnlyTrueFalse(false);
	  $('[name="txt-snomed-ct-search_1"]').removeClass('x onX').val('').change();//for clearing the snomed fields
  },
	setPaymentModeRefId: function(obj){
		var paymentModeCode=obj.value.split("#")[0];
		if(paymentModeCode!="" && paymentModeCode!=1 && paymentModeCode!=13 && paymentModeCode!=10){//1 for cash, 10 for cm relief fund, 13 for jan arogya
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

$("#patDocTypeId").change(function(){
	splregistration.onchange_patDocType();
});

$("#patPrimaryCatCodeId").change(function(){
	splregistration.onchange_patPrimaryCategory();
});

function onCLickDateFn(){
	$("#patDOBId").focus();
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

$("#imgCatCardId").click(function(){
	var patPrimarCatId= $("#patPrimaryCatCodeId").val();
	var action 	= "/HISRegistration/registration/transactions/openPatPopupSplRegistration.action?patPrimarCatId="+patPrimarCatId+"&alreadyRegisteredFlag=0";
	openURLInPopupWithoutClose(action,'600','200');
	//openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');
	
});

$("#alreadyRegisteredFlagId").click(function(){
	var alreadyRegisteredFlag = this.value;
	if(this.checked==false){
		splregistration.setFormFieldsAfterSave();
		$("#hiddenCatOrRegstrdPopupFlagId").val("");
		$("#divNormalMsgId").html("");
		$("#divErrorMsgId").html("");
		$("#divPrintId").html("");
		if(("#isIdRequired").val()=="")
			$("#imgCatCardId").show();
		return;
	}
	var patPrimarCatId= $("#patPrimaryCatCodeId").val();
	var action 	= "/HISRegistration/registration/transactions/openPatPopupSplRegistration.action?patPrimarCatId="+patPrimarCatId+"&alreadyRegisteredFlag="+alreadyRegisteredFlag;
	openURLInPopupWithoutClose(action,'600','200');
	//openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');
});

//Submit form if valid
$('#submitId').click(function(e){
	//alert("inside submitClick");
	 enterCount++;
		if(enterCount!=1)
			return false;
		
		var allInputs = $(":input"); 
		allInputs.each(function() {
		        $(this).val($.trim($(this).val()));
		    });
		
	setMotherValidRule();
	fatherorSpouseerror();
	fatherorSpouse();
	
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
		$('[name="patVisitReason"]').validatebox({
			required : false,
			validType : 'WithoutZeroSymbolandSpace'
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
		$('[name="patVisitReason"]').validatebox({
			required : false,
			validType : 'WithoutZeroSymbolandSpace'
		});
		
	}
		
	if($('#SplRegistration').form('validate')){
		$('#submitId').hide();
		//alert("Validation True");
		//alert("after hide");
		splregistration.savePatientDtl();
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
	splregistration.setFormFieldsAfterSave();
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");
});
$('[name="patRefGnctdHospitalCode"]').change(function(){
	//splregistration.onchange_patRefGnctdHospitalDept();
});

$("#patAgeUnitId").change(function(){
	
	if($('[name="isActualDob"]')[0].checked){
	var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
	var ageRangeValidType='range[1,'+maxAgeRange+']';
	$("#patAgeId").validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
	}
});
$("#patDOBId_Dup").change(
		function() {
			setMotherValidRule();
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
		maxAgeRange=age*365/7;
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
	//alert("inside setMotherValidRule()");
	
	if($('[name="isActualDob"]')[0].checked && $('[name="patAgeUnit"]')[0].value!="-1"){
		if($("#patAgeId").val() < calculateMaxRangeValue(12,$("#patAgeUnitId").val())){
			flagYes=true;
		}
	}/*else if($('[name="patDOB"]')[0].value!="" && isDate($('[name="patDOB"]')[0],"dd-mmm-yyyy")){
		var d1 = $.datepicker.parseDate("dd-M-yy", $("#patDOBId").val());
		var ctDt= new Date();
		var yr = d1.getFullYear();
		var ctDtYr = ctDt.getFullYear();
		if((ctDtYr-12) <= yr){
			flagYes=true;
		}
	}*/
	else if($('[name="patDOB_Dup"]')[0].value!= "" && $('[name="patDOB_Dup"]').val()!="dd-mm-yyyy")// && isDate($('[name="patDOB"]')[0], "dd-mmm-yyyy"))
	{
		var d1 = $.datepicker.parseDate("dd-mm-yy", $("#patDOBId_Dup").val());
		var ctDt = new Date();
		var yr = d1.getFullYear();
		var ctDtYr = ctDt.getFullYear();		
		//alert(d1);
		
		if ((ctDtYr - 12) <= yr) {
			flagYes = true;
		}
		var temp =ctDtYr-18;
		ctDt.setFullYear(temp, ctDt.getMonth());
		
	}
	//alert("flagYes :"+flagYes);
	if(flagYes){
		$('[name="patMotherName"]').validatebox({required: true,	validType: 'alphaWithSpace'});
	}else{
		$('[name="patMotherName"]').validatebox({required: false,	validType: 'alphaWithSpace'});
	}
}
function showDivAgeDob(){
	if($('[name="isActualDob"]')[0].checked){
		$("#divAge").show();
		$("#divDob").hide();
		var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
		var ageRangeValidType='range[1,'+maxAgeRange+']';
		//alert("ageRangeValidType :"+ageRangeValidType);
		$("#patAgeId").validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
		$("#patAgeUnitId").validatebox({required: true, validType: ['selectCombo[-1]']});
		$("#patDOBId").validatebox({required: false,validType:null});
		$("#patDOBId_Dup").val("");
		$("#patDOBId").val("");
	}else{
		//alert("hi");
		$("#divAge").hide();
		$("#divDob").show();
		$("#patAgeId").val("");
		$("#patAgeId").validatebox({required:false, validType:null});
		$("#patAgeUnitId").validatebox({required:false, validType:null});
		$("#patDOBId_Dup").validatebox({required:false, validType :null});
		$(function() {
			document.getElementById("divNewDOBHidden").innerHTML  ="";
			DateValidator.setup("divNewDOB", "patDOB_Dup", $("#patDOBId").val(), "dd-Mon-yyyy", "input45prcnt", "patDOBId_Dup", "1", "divNewDOBHidden");
			/*$("#patDOBId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
			/*var today=new Date().toLocaleFormat('%d-%b-%Y');
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(today);*/
			
		});
		//var dobValidType='dtltetDMY['+ageBoundRange+',\'y\']';
		//$("#patDOBId").validatebox({required: true, validType: ['date[\'patDOB\',\'dd-mmm-yyyy\']','dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth \']' ,dobValidType]});
	}
	
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
	if($('[name="isActualDob"]')[0].checked){
		$("#patAgeId").val("");
		$("#patAgeUnitId").val("Yr");
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
		$("#patDOBId").prop('readOnly', flagTrueFalse);
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
	
	var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
	$('#patDOBId').DatePicker({
		format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
	}).val(today);
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

function Isreferred(elem)
 {
	
	if($('[name="isReferCompulsory"]')[0].value==1){
		if(!elem.checked)
	 	{
			$("#isReferCompusoryId").show();	
	 	}
		elem.checked="true";
	}
	else{
		$("#isReferCompusoryId").hide();
	}
 	if(elem.checked)
 	{
 		elem.value="1";
 		$("#divRefDtlId").show();
 		$("#divRefHospitalDeptOtherId").hide();
 		$('[name="patRefGnctdHospitalCode"]')[0].value="-1";
 		$('[name="patRefHospitalName"]')[0].value="";
 		$('[name="patRefHospitalDeptOther"]')[0].value="";
		
 		$('[name="patRefGnctdHospitalCrno"]').validatebox({validType: 'numeric'});
 		$('[name="patRefDoctor"]').validatebox({required: true, validType: 'alpha'});
 		$('[name="patRefGnctdHospitalDeptUnit"]').validatebox({validType: 'alphaNumeric'});
 		
 		if($('[name="referringInstType"]')[0].checked){
			$("#divRefHosname").hide();
 			$("#divRefHosCode").show();
 			$("#divReferredInstitute").show();
 			$("#divReferred").show();
 			$('[name="patRefDoctor"]')[0].value="";
 			$('[name="patRefGnctdHospitalDept"]')[0].value="-1";
 			$('[name="patRefGnctdHospitalDeptUnit"]')[0].value="";
 			$('[name="patRefGnctdHospitalCrno"]')[0].value="";
 			
 			$('[name="patRefGnctdHospitalCode"]').validatebox({validType: ['selectCombo[-1]']});
 			
 		}else{
 			$("#divRefHosname").show();
 			$("#divRefHosCode").hide();
 			$("#divReferredInstitute").show();
 			
 			
 			$('[name="patRefDoctor"]')[0].value="";
 			$("#divReferred").show();
 			$('[name="patRefGnctdHospitalDept"]')[0].value="-1";
 			$('[name="patRefGnctdHospitalDeptUnit"]')[0].value="";
 			$('[name="patRefGnctdHospitalCrno"]')[0].value="";
 			
 			$('[name="patRefGnctdHospitalCode"]').validatebox({validType: null});
 			$('[name="patRefHospitalName"]').validatebox({required : true, validType: 'alphaWithSpace'});
 		}
 		/*if($('[name="isIdRequired"]')[0].value=="2"){
			setMandatoryRefdInitial();
			setMandatoryRefdReadOnlyTrueFalse(true);
		}else{
			setMandatoryRefdReadOnlyTrueFalse(false);
		}*/
 	}
 	else
 	{
 		elem.value="0";
 		$('[name="patRefGnctdHospitalCode"]').validatebox({validType: null});
 		$('[name="patRefHospitalName"]').validatebox({required : false});
 		$('[name="patRefDoctor"]').validatebox({required: false});
 		
 		$("#divRefDtlId").hide();
 		$("#divRefHosname").hide();
 		$("#divRefHosCode").hide();
 		$("#divReferredInstitute").hide();
 		$("#divReferred").hide();
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

$(document).ajaxStart(function() {
	//alert('ajax start');
    // show loader on start
    $("#loadingmessage").css("display","block");
}).ajaxSuccess(function() {
	//alert('ajax end');
    // hide loader on success
    $("#loadingmessage").css("display","none");
});

$("#patAddStateCodeId").bind("change",splregistration.onchange_patAddStateCode);

// Validation]
$('[name="departmentUnitCode"]').validatebox({
	validType: ['selectCombo[-1]']
});
$("#patPrimaryCatCodeId").validatebox({
	validType: ['selectCombo[-1]']
});
$('[name="patFirstName"]').validatebox({
	required : true,
	validType: 'alphaWithSpace'
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
	validType: 'alphaWithSpace'
});


$('[name="patBirthPlace"]').validatebox({
	validType: 'alphaWithSpace'
});
$('[name="patAddHNo"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddStreet"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddCity"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddPIN"]').validatebox({
	validType : [ 'numeric', 'equalLength[6]']
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
	validType: 'telephone'
});
$('[name="patAddMobileNo"]').validatebox({
	required : true,
	validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
});
	
$('[name="patEmgCntNo"]').validatebox({
	validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
});


$('[name="patMonthlyIncome"]').validatebox({
	validType: 'numericNew'
});
$('[name="patNationalId"]').validatebox({
	validType : [ 'numeric','equalLength[12]']
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
function fatherorSpouseerror(){
	var fatherName = $('[name="patGuardianName"]')[0].value;
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
function showHideCatGroupBeneficiaryTile(mode){
	if(mode=="show"){
		$("#divCatGroupBeneficiaryId").show();
		/*$("#creditLetterDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
		var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
		$('#creditLetterDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		$("#creditBillFlagId").val("1");
		
		$("#creditLetterRefNoId").validatebox({required : true, validType: 'alphaNumSpecialChar'});
		$("#creditLetterDateId").validatebox({validType: 'dtltetctdt[\'It should not be grater than Current Date.\']'});
	}else{
		$("#creditBillFlagId").val("0");
		$("#creditLetterRefNoId").val("");
		$("#creditLetterDateId").val("");
		$("#creditLetterRefNoId").validatebox({required : false});
		$("#creditLetterDateId").validatebox({validType : null});
		$("#divCatGroupBeneficiaryId").hide();
	}
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
		
		
		/*$("#patDOBId_Dup")
				.validatebox(
						{
							required : true,
							validType : [
									'date[\'patDOB_Dup\',\'dd-mm-yyyy\']',
									//'dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth \',\'dd-mm-yyyy\' ]',
									dobValidType ]
						});*/
		setMotherValidRule();

	}
	return true;
}