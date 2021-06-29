var focusedElement;
var enterCount = 0;
var delay = ( function() {
    var timer = 0;
    return function(callback, ms) {
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    };
})();
$(document)
		.ready(
				function() {

					$(document)
							.ajaxStart(
									function() {
												
										if (parent.frameElement != null
												&& parent.frameElement != undefined
												//&& parent.frameElement.id == "frmMain"
													) {
											parent.parent.ajaxStart();
										} else if(parent !=null && parent != undefined){
											parent.ajaxStart();
										}
										
										
									});
					$(document)
							.ajaxComplete(
									function() {
										
										if (parent.frameElement != null
												&& parent.frameElement != undefined
												//&& parent.frameElement.id == "frmMain"
													) {
											parent.parent.ajaxComplete();
										} else if(parent !=null && parent != undefined) {
											parent.ajaxComplete();
										}
									});

					if ($("#flagHasActionErrorId").val() == 'false') {
						$('[name="departmentCode"]').validatebox({
							validType : [ 'selectCombo[-1]' ]
						});
						$('[name="departmentUnitCode"]')[0].focus();

						opdregistration.fetchDefaultValues();

						$("#patAddStateCodeId").bind("change",
								opdregistration.onchange_patAddStateCode);

						// Validation]
						$('[name="patFirstName"]').validatebox({
							required : true,
							validType : 'alphaWithSpaceSlash'
						});
						
						$("#patPrimaryCatCodeId").validatebox({
							validType : [ 'selectCombo[-1]' ]
						});
						/*$('[name="patFirstName"]').validatebox({
							required : true,
							validType : 'alphaWithSpace'
						});*/
						$('[name="patMiddleName"]').validatebox({
							validType : 'alphaWithSpace'
						});
						$('[name="patLastName"]').validatebox({
							validType : 'alphaWithSpace'
						});
						
						
						$("#patFirstNameInMultiLangId")
								.validatebox(
										{
											validType : 'maxLengthFixed[33,\'patFirstNameInMultiLang\']'
										});
						$("#patMiddleNameInMultiLangId")
								.validatebox(
										{
											validType : 'maxLengthFixed[33,\'patMiddleNameInMultiLang\']'
										});
						$("#patLastNameInMultiLangId")
								.validatebox(
										{
											validType : 'maxLengthFixed[36,\'patLastNameInMultiLang\']'
										});

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

						$('[name="patBirthPlace"]').validatebox({
							validType : 'alphaWithSpace'
						});
						$('[name="patAddHNo"]').validatebox({
							validType : 'alphaNumSpecialChar'
						});

						$('[name="patAddStreet"]').validatebox({
							validType : 'alphaNumSpecialChar'
						});
						$('[name="patAddCityLoc"]').validatebox({
							validType: 'alphaNumericWithSpaces'
						});
						$('[name="patAddCity"]').validatebox({
							validType : 'alphaNumSpecialChar'
						});

						$('[name="patAddPIN"]').validatebox({
							//required : true,
							validType : [ 'numeric', 'equalLength[6]']
						});
						/*$('[name="patAddPIN"]').validatebox({
							required : true,
							validType : [ 'equalLength[6]' ]
						});*/
						
						$('[name="patAddCountryCode"]').validatebox({
							validType : [ 'selectCombo[-1]' ]
						});
						$('[name="patAddEmailId"]').validatebox({
							validType : 'email'
						});
						$('[name="patAddPhoneNo"]').validatebox({
							validType : 'telephone'
						});
						$('[name="patAddMobileNo"]').validatebox({
							required : true,
							validType : ['numeric', 'DisableAllZero', 'mobileNostartwithseven']
						});
						$('[name="patEmgCntNo"]').validatebox({
							
							validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
						});
						$('[name="patMonthlyIncome"]').validatebox({
							validType : 'numericNew'
						});

						$('[name="patNationalId"]').validatebox({
							validType : [ 'numeric','equalLength[12]', 'validAadhaarNo']
						});		
						
						//By Mukund on 15.05.2017
						$('[name="creditLimit"]').validatebox({
							validType : [ 'numeric','DisableAllZero']
						});	
						
						$('[name="agsCreditLimit"]').validatebox({
							validType : [ 'numeric','DisableAllZero']
						});	
						//End 15.05.2017
					}
					
					$(document).scroll(function(e){

				        if ($(".ui-widget-overlay")) //the dialog has popped up in modal view
				        {
				            //get the current popup position of the dialog box
				            var pos = $(".ui-dialog").position();

				            //adjust the dialog box so that it scrolls as you scroll the page
				            if(pos!=undefined){
				            $(".ui-dialog").css({
				                position: 'fixed',
				                top: pos.y
				            });
				            }
				        }
				    });
				});

var departmentJSONArray = [];
var categoryJSONArray = [];
var veriyDocTempJSONArray = [];
var veriyDocJSONArray = [];
var defaultValJSONArray = [];
var paymentModeJSONArray = [];
var ageBoundRange = 125;
var docValidType = 'alphaNumSpecialChar';
var catIdValidType = 'alphaNumSpecialChar';
var ageValidType = "selectComboNotSpecifiedVal['-1',1,'patGenderCode','-1','Kindly Select Department First']";
var clientCode, clientName = "";
var temprtnData, isDuplicatePatientPopup = "";
var isSearchable=true;
var isBarcodeSlipPrint_Yes = 1;
var isBarcodeSlipPrint_No = 0;

var opdregistration = {

	fetchDefaultValues : function() {
		var action = "/HISRegistration/registration/transactions/populateformvaluesNewUnitWiseRegistration.action";

		$
				.ajax({
					url : action,
					type : "POST",
					data :{ isUnitWiseRegistration : 1},
					async : true,
					dataType : "xml",
					success : function(data) {
						var returnDocument = data;
						var rootNode = returnDocument
								.getElementsByTagName("root")[0];
						var localLanguage = rootNode
								.getAttribute(LOCAL_LANGUAGE);
						for ( var i = 0; i < rootNode.childNodes.length; i++) {
							var elementNode = rootNode.childNodes[i];
							var elementName = elementNode.nodeName;
							if (elementName == 'msgs') {
								if (elementNode.getAttribute('msg') != "") {
									$("#divNormalMsgId").html("");
									$("#divPrintId").html("");
									$("#divErrorMsgId").html(
											elementNode.getAttribute('msg'));
									break;
								}
							}else if(elementName=='departmentUnitCode')	{
								opdregistration.processDepartmentNode(
										elementName,elementNode);
							}else if (elementName == 'departmentCode') {
								opdregistration.processDepartmentNode(
										elementName, elementNode);
							} else if (elementName == 'patPrimaryCatCode') {
								opdregistration.processCategoryNode(
										elementName, elementNode);
							} else if (elementName == 'patDocType') {
								opdregistration.processVerifyDocNode(
										elementName, elementNode);
							} else if (elementName == 'renewalConfig') {								
								if (elementNode
										.getAttribute('alreadyRegistered') != ""
										&& elementNode
												.getAttribute('alreadyRegistered') != "0") {
									$("#divAlreadyRegisteredId").show("blind");
									$('[name="alreadyRegisteredFromRenewalConfig"]')[0].value = elementNode
											.getAttribute('alreadyRegistered');
									$('[name="alreadyRegisteredFlag"]')[0].value = elementNode
											.getAttribute('alreadyRegistered');
								}
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
								if (elementNode
										.getAttribute('mobileSearch') != ""
										&& elementNode
												.getAttribute('mobileSearch') != "0") {
									
									$("#divMobileSearchId").show("blind");
									$('[name="mobileSearch"]')[0].value = elementNode
											 .getAttribute('mobileSearch');
								}
								//By Mukund on 16.03.18 for BarcodeSlip print configuration
								if (elementNode
										.getAttribute('barcodeSlipPrintFlag') != null
										&& elementNode
												.getAttribute('barcodeSlipPrintFlag') != "") {
									
									//alert("elementNode.getAttribute('barcodeSlipPrintFlag'): "+elementNode.getAttribute('barcodeSlipPrintFlag'));
									$('[name="barcodeSlipPrintFlag"]')[0].value = elementNode
											 .getAttribute('barcodeSlipPrintFlag');
								}
								/*
								 * else{ $("#divAlreadyRegisteredId").hide(); }
								 */
								if (elementNode.getAttribute('isSnomedServiceOn') != null
										&& elementNode.getAttribute('isSnomedServiceOn') != "") {
									$('[name="isSnomedServiceOn"]')[0].value = elementNode.getAttribute('isSnomedServiceOn');
								}
							} else if (elementName == 'defaults') {

							} else if(elementName == 'paymentModeCode'){
								opdregistration.processPaymentModeNode(elementName,
										elementNode);
							}else {
								opdregistration.processGeneralNode(elementName,
										elementNode);
							}
						}

						opdregistration.setdefaultVariables(rootNode
								.getElementsByTagName("defaults")[0]);
						$('[name="patAgeUnit"]')[0].value = "Yr";
						$('[name="patGenderCode"]').validatebox({
							validType : ageValidType
						});
						$('[name="patMotherName"]').validatebox({
							required : false,
							validType : 'alphaWithSpace'
						});
						$('[name="patAddStateCode"]').validatebox({
							validType : [ 'selectCombo[-1]' ]
						});
						$('[name="patAddDistrictCode"]').validatebox({
							validType : [ 'selectCombo[-1]' ]
						});
						// alert("LOCAL_LANGUAGE : "+localLanguage);
						// localLanguage="telugu";
						initMultilingual(localLanguage);
						//opdregistration.setCategoryCmboSearchable();			
											

					},
					error : function(errorMsg, textstatus, errorthrown) {
						alert('fetchDefaultValues ' + errorMsg
								+ " textstatus::" + textstatus
								+ " errorthrown::" + errorthrown);

					}

				});
		showDivAgeDob();			

	},
	setCategoryCmboSearchable: function(){
		//alert("isSearchable--"+isSearchable);
		if(isSearchable){
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
		}
	},
	processDepartmentNode : function(elementName, elementNode) {
		var optionText = "<option value='-1'>Select Value</option>";
		// for single department unit 
		var selOption="-1";
		for ( var i = 0; i < elementNode.childNodes.length; i++) {
			var optionNode = elementNode.childNodes[i];
			 //alert("JSONStr"+optionNode.getAttribute("value"));
			var departmentJSONObject = jQuery.parseJSON(optionNode
					.getAttribute("value"));
			departmentJSONArray[departmentJSONArray.length] = departmentJSONObject;
			optionText += "<option value='"
					+ departmentJSONObject.deptUnitCode + "'>"
					+ (departmentJSONObject.departmentName) + "</option>";
			//for single department unit
			if(i==0) selOption=departmentJSONObject.deptUnitCode;

		}
		// alert("processDepartmentNode::optionText :"+optionText);
		if (document.getElementsByName(elementName).length != 0)
			document.getElementsByName(elementName)[0].innerHTML = optionText;
		//for single department unit
		if(elementNode.childNodes.length==1){
			document.getElementsByName(elementName)[0].value=selOption;
			$('[name="'+elementName+'"]').trigger('change');
		}
	},
	processCategoryNode : function(elementName, elementNode) {
		var optionText = "<option value='-1'>Select Value</option>";
		for ( var i = 0; i < elementNode.childNodes.length; i++) {
			var optionNode = elementNode.childNodes[i];
			// alert("JSONStr"+optionNode.getAttribute("value"));
			var categoryJSONObject = jQuery.parseJSON(optionNode
					.getAttribute("value"));
			categoryJSONArray[categoryJSONArray.length] = categoryJSONObject;
			optionText += "<option value='"
					+ categoryJSONObject.patPrimaryCatCode + "'>"
					+ (categoryJSONObject.patPrimaryCatName) + "</option>";
		}
		
		/*$('[name="'+elementName+'"]').parent()[0].innerHTML='<select name="patPrimaryCatCode" id="patPrimaryCatCodeId" tabindex="1" class="select90prcnt" onchange="opdregistration.onchange_patPrimaryCategory()">'+
		+'<option value="-1">Select Value</option>'
		+'</select>';*/
		var parId = $('[name="'+elementName+'"]').closest('div').attr('id');
		if(parId==undefined)
			parId = "_divpatPrimaryCatCodeId";
		document.getElementById(''+parId+'').innerHTML = '<select name="patPrimaryCatCode" id="patPrimaryCatCodeId" tabindex="1" class="select90prcnt" onchange="opdregistration.onchange_patPrimaryCategory()">'+
		+'<option value="-1">Select Value</option>'
		+'</select>';
		
				
		if (document.getElementsByName(elementName).length != 0){
			document.getElementsByName(elementName)[0].innerHTML = optionText;
			opdregistration.setCategoryCmboSearchable();	
		}
		
		if(document.getElementsByName(elementName).length>=0)  // added by manisha for setting default patcat 'general'
		{  $('[name="'+elementName+'"]')[0].value = "11";
		opdregistration.onchange_patPrimaryCategory();
		}
		
		$("#patPrimaryCatCodeId").validatebox({
			validType : [ 'selectCombo[-1]' ]
		});
		
	},
	processVerifyDocNode : function(elementName, elementNode) {
		var optionText = "<option value='-1'>Select Value</option>";
		for ( var i = 0; i < elementNode.childNodes.length; i++) {
			var optionNode = elementNode.childNodes[i];
			// alert("JSONStr"+optionNode.getAttribute("value"));
			var veriyDocJSONObject = jQuery.parseJSON(optionNode
					.getAttribute("value"));
			veriyDocJSONArray[veriyDocJSONArray.length] = veriyDocJSONObject;
			optionText += "<option value='" + veriyDocJSONObject.patDocType
					+ "'>" + (veriyDocJSONObject.patDocTypeName) + "</option>";

		}
		if (document.getElementsByName(elementName).length != 0)
			document.getElementsByName(elementName)[0].innerHTML = optionText;

	},
	processGeneralNode : function(elementName, elementNode) {
		var optionText = "<option value='-1'>Select Value</option>";

		if (elementNode != null) {
			for ( var i = 0; i < elementNode.childNodes.length; i++) {
				var optionNode = elementNode.childNodes[i];
				optionText += "<option value='"
						+ (optionNode.getAttribute("value")) + "'>"
						+ (optionNode.getAttribute("label")) + "</option>";

			}
		}
		if (elementName == "patRefGnctdHospitalDept") {
			optionText += "<option value='0'>Other</option>";
		}

		if (document.getElementsByName(elementName).length != 0)
			document.getElementsByName(elementName)[0].innerHTML = optionText;

	},
	setDepartmentDependents : function(deptObj) {
		var deptCode = deptObj.options[deptObj.selectedIndex].value;
		if (deptCode == "-1") {
			$('[name="defaultpatGenderCode"]')[0].value = "0";
			ageValidType = "selectComboNotSpecifiedVal['-1',1,'patGenderCode','-1','Kindly Select Department First']";
			return;
		}
		for ( var i = 0; i < departmentJSONArray.length; i++) {
			if (departmentJSONArray[i].deptUnitCode == deptCode) {

				$('[name="patGenderCode"]')[0].value = departmentJSONArray[i].defaultGenderCode;
				$('[name="defaultpatGenderCode"]')[0].value = departmentJSONArray[i].defaultGenderCode;
				$('[name="departmentCode"]')[0].value = departmentJSONArray[i].departmentCode;

				/***/
				if($('#userBoundDefaultGenderId').val()!=departmentJSONArray[i].defaultGenderCode){
					var defaultGenderName2 = $(
							"#patGenderCodeId option[value='"
									+ $('#userBoundDefaultGenderId').val()
									+ "']").text();
					//alert("User is bounded to "+defaultGenderName2+" gender");
				}	
				/***/
				ageBoundRange = departmentJSONArray[i].ageBound;
				if (departmentJSONArray[i].defaultGenderCode != "-1") {
					var defaultGenderName = $(
							"#patGenderCodeId option[value='"
									+ departmentJSONArray[i].defaultGenderCode
									+ "']").text();

					ageValidType = "selectComboNotSpecifiedVal['"
							+ departmentJSONArray[i].defaultGenderCode
							+ "',1,'patGenderCode','-1','Only "
							+ defaultGenderName
							+ " is allowed in this Department']";

					// $("#patGenderCodeId").removeClass('validatebox-text');
					$("#patGenderCodeId").removeClass('validatebox-invalid');
					$('[name="patGenderCode"]').validatebox({
						validType : ageValidType
					});

				} else {
					ageValidType = null;
					$('[name="patGenderCode"]').validatebox({
						validType : ageValidType
					});
					$('[name="patGenderCode"]').validatebox({
						validType : [ 'selectCombo[-1]' ]
					});
				}
				if (typeof ageBoundRange == undefined || ageBoundRange == "")
					ageBoundRange = 125;

				if ($('[name="isActualDob"]')[0].checked) {
					var maxAgeRange = calculateMaxRangeValue(ageBoundRange, $(
							"#patAgeUnitId").val());
					var ageRangeValidType = 'range[1,' + maxAgeRange + ']';
					$("#patAgeId").validatebox({
						required : true,
						validType : [ 'numeric', ageRangeValidType,'startZero' ]
					});
					/*$("#patDOBId").validatebox({
						validType : null
					});*/
					$("#patDOBId_Dup").validatebox({
						validType : null
					});
				} else {
					$("#patAgeId").validatebox({
						validType : null
					});
					$("#patAgeId").validatebox({
						required : false
					});
					var dobValidType = 'dtltetDMY[' + ageBoundRange + ',\'y\']';
					/*$("#patDOBId").validatebox(
							{
								required : true,
								validType : [
										'date[\'patDOB\',\'dd-mmm-yyyy\']',
										dobValidType]
							});*/
				}
				break;
			}
		}
	},
	setdefaultVariables : function(elementNode) {
		for ( var i = 0; i < elementNode.attributes.length; i++) {
			eval("var " + elementNode.attributes[i].name + "='"
					+ elementNode.attributes[i].value + "'");
			//alert( elementNode.attributes[i].name +"-"+elementNode.attributes[i].value);
			if (!(elementNode.attributes[i].name == "defaultpatAddPIN"))
				document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].value = eval(elementNode.attributes[i].name);
			if (elementNode.attributes[i].name == "defaultpatAddCountryCode") {
				document.getElementsByName("defaultpatAddCountryCode")[0].value = eval(elementNode.attributes[i].name);
			} else if (elementNode.attributes[i].name == "defaultpatAddStateCode") {
				document.getElementsByName("defaultpatAddStateCode")[0].value = eval(elementNode.attributes[i].name);
			} else if (elementNode.attributes[i].name == "defaultpatAddDistrictCode") {
				document.getElementsByName("defaultpatAddDistrictCode")[0].value = eval(elementNode.attributes[i].name);
			} /*else if (elementNode.attributes[i].name == "defaultpatAddPIN") {
				document.getElementsByName("patAddPIN")[0].value = eval(elementNode.attributes[i].name);
			}*/else if (elementNode.attributes[i].name == "defaultuserBoundDefaultGender"){
				if(eval(elementNode.attributes[i].name)=='0')
					document.getElementsByName("patGenderCode")[0].value = '-1';
				else
					document.getElementsByName("patGenderCode")[0].value = eval(elementNode.attributes[i].name);
				//alert(eval(elementNode.attributes[i].name));
			}
		}
		$("#patAddCountryCodeId").bind("change",
				opdregistration.onchange_patAddCountryCode);

	},
	onchange_patAddCountryCode : function() {
		var objCountry = document.getElementsByName("patAddCountryCode")[0];
		var countryCode = objCountry.options[objCountry.selectedIndex].value;
		var action = "/HISRegistration/registration/transactions/getStateNewUnitWiseRegistration.action?countryCode="
				+ countryCode;
		var flagStateNDIstComboReqd = true;

		document.getElementsByName("patAddCountry")[0].value = objCountry.options[objCountry.selectedIndex].text;
		if (countryCode != "-1"
				&& countryCode != document
						.getElementsByName("defaultpatAddCountryCode")[0].value) {
			$('[name="patAddStateCode"]')[0].value = "-1";
			$("#divStateComboId").hide();
			$("#divStateTextId").show();
			$('[name="patAddState"]')[0].value = "";
			$("#divDistrictComboId").val("-1");
			$('[name="patAddDistrictCode"]')[0].value = "-1";
			$("#divDistrictComboId").hide();
			$("#divDistrictTextId").show();
			$('[name="patAddDistrict"]')[0].value = "";
			flagStateNDIstComboReqd = false;
			//$('[name="defaultpatAddPIN"]')[0].value=$('[name="patAddPIN"]')[0].value;
			$('[name="patAddPIN"]')[0].value = "";
		} else {
			$("#divStateTextId").hide();
			$("#divStateComboId").show();
			$("#patAddStateCodeId").html(
					"<option value='-1'>Select Value</option>");
			$("#divDistrictTextId").hide();
			$("#divDistrictComboId").show();
			$("patAddDistrictCodeId").html(
					"<option value='-1'>Select Value</option>");
			//$('[name="patAddPIN"]')[0].value=$('[name="defaultpatAddPIN"]')[0].value;
		}

		// alert(action);
		$
				.ajax({
					url : action,
					type : "POST",
					async : true,
					dataType : "xml",
					success : function(data) {
						var returnDocument = data;
						var rootNode = returnDocument
								.getElementsByTagName("root")[0];
						for ( var i = 0; i < rootNode.childNodes.length; i++) {
							var elementNode = rootNode.childNodes[i];
							var elementName = elementNode.nodeName;

							opdregistration.processGeneralNode(elementName,
									elementNode);
							if ($('[name="patAddCountryCode"]')[0].value == $('[name="defaultpatAddCountryCode"]')[0].value)
								$('[name="patAddStateCode"]')[0].value = $('[name="defaultpatAddStateCode"]')[0].value
						}

						if (flagStateNDIstComboReqd) {
							$("#patAddStateId").validatebox({
								required : false,
								validType : 'alphaNumSpecialChar'
							});
							$("#patAddDistrictId").validatebox({
								required : false,
								validType : 'alphaNumSpecialChar'
							});
							$('[name="patAddStateCode"]').validatebox({
								validType : [ 'selectCombo[-1]' ]
							});
							$('[name="patAddDistrictCode"]').validatebox({
								validType : [ 'selectCombo[-1]' ]
							});
							opdregistration.onchange_patAddStateCode();
						} else {
							$("#patAddStateId").validatebox({
								required : true,
								validType : 'alphaNumSpecialChar'
							});
							$("#patAddDistrictId").validatebox({
								required : true,
								validType : 'alphaNumSpecialChar'
							});
							$('[name="patAddStateCode"]').validatebox({
								validType : null
							});
							$('[name="patAddDistrictCode"]').validatebox({
								validType : [ null ]
							});
						}

					},
					error : function(errorMsg, textstatus, errorthrown) {
						alert('onchange_patAddCountryCode ' + errorMsg
								+ " textstatus::" + textstatus
								+ " errorthrown::" + errorthrown);

					}
				});
	},
	onchange_patAddStateCode : function() {
		var countryCode = document.getElementsByName("patAddCountryCode")[0].value;
		var objState = document.getElementsByName("patAddStateCode")[0];
		var stateCode = objState.options[objState.selectedIndex].value;
		var action = "/HISRegistration/registration/transactions/getDistrictNewUnitWiseRegistration.action?countryCode="
				+ countryCode + "&stateCode=" + stateCode;

		// alert(action);
		$
				.ajax({
					url : action,
					type : "POST",
					async : false,
					dataType : "xml",
					success : function(data) {
						var returnDocument = data;
						var rootNode = returnDocument
								.getElementsByTagName("root")[0];
						for ( var i = 0; i < rootNode.childNodes.length; i++) {
							var elementNode = rootNode.childNodes[i];
							var elementName = elementNode.nodeName;

							opdregistration.processGeneralNode(elementName,
									elementNode);
							if ($('[name="patAddStateCode"]')[0].value == $('[name="defaultpatAddStateCode"]')[0].value)
								$('[name="patAddDistrictCode"]')[0].value = $('[name="defaultpatAddDistrictCode"]')[0].value;
						}

					},
					error : function(errorMsg, textstatus, errorthrown) {
						alert('onchange_patAddCountryCode ' + errorMsg
								+ " textstatus::" + textstatus
								+ " errorthrown::" + errorthrown);

					}
				});
	},
	onchange_patRefGnctdHospitalDept : function() {
		var refHospCode = document.getElementsByName("patRefGnctdHospitalCode")[0].value;
		var flafRefHospOrInst = refHospCode.split("#")[1];
		refHospCode = refHospCode.split("#")[0];
		var action = "/HISRegistration/registration/transactions/getRefDeptNewUnitWiseRegistration.action?refHospCode="
				+ refHospCode + "&flafRefHospOrInst=" + flafRefHospOrInst;

		// alert(action);
		$.ajax({
			url : action,
			type : "POST",
			async : true,
			dataType : "xml",
			success : function(data) {
				var returnDocument = data;
				var rootNode = returnDocument.getElementsByTagName("root")[0];
				for ( var i = 0; i < rootNode.childNodes.length; i++) {
					var elementNode = rootNode.childNodes[i];
					var elementName = elementNode.nodeName;

					opdregistration
							.processGeneralNode(elementName, elementNode);
				}

			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('onchange_patRefGnctdHospitalDept ' + errorMsg
						+ " textstatus::" + textstatus + " errorthrown::"
						+ errorthrown);

			}
		});
	},
	onchange_patPrimaryCategory : function() {

		var PrimaryCatObj = $('[name="patPrimaryCatCode"]')[0];
		var PrimaryCatCode = PrimaryCatObj.options[PrimaryCatObj.selectedIndex].value;
		var PrimaryCatName = PrimaryCatObj.options[PrimaryCatObj.selectedIndex].text;

		if (PrimaryCatCode == "-1") {
			$("#divCatCardId").hide();
			$("#divImgCatCardId").hide();
			$("#divSpareCatId").show();
			$("#divFeeLabel").hide();
			$("#divFeeVal").hide(); $("#divFeeVal2").hide();$("#divFeeVal3").hide();
			$('[name="paymentModeCode"]').val("1");
			showHideCatGroupBeneficiaryTile("hide");
			
			$("#patPrimaryCatCodeId").validatebox({
				validType : [ 'selectCombo[-1]' ]
			});
		}
		else{
			$("#divFeeLabel").show();
			$("#divFeeVal").show(); $("#divFeeVal2").show();
			/*var pymntMdeCde = "paymentModeCode";
			var paymentModeLength = $('[name="paymentModeCode"]').children('option').length;
			for(var w=0; w<paymentModeLength; w++){
				var paymentModeObj = $('[name="paymentModeCode"]')[0];
				var paymentModeName = paymentModeObj.options[w].text;
				if(PrimaryCatName.toUpperCase() === paymentModeName.toUpperCase()){
					$('[name='+pymntMdeCde+'] option:contains(' + paymentModeName + ')').attr('selected', 'selected');
					opdregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
					break;
				}else{
					//$('[name="paymentModeCode"]').val("1");
					$('[name="paymentModeCode"]').val(paymentModeObj.options[0].value);
					opdregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);

				}
			}*/
			opdregistration.processPaymentModeNode_onCatChange(PrimaryCatCode);
		}

		for ( var i = 0; i < categoryJSONArray.length; i++) {
			if (categoryJSONArray[i].patPrimaryCatCode == PrimaryCatCode) {
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
				// alert("categoryJSONArray[i].charges"+categoryJSONArray[i].charges);

				if (categoryJSONArray[i].patCatGroup == PATIENT_REG_CATEGORY_GROUP_PAID) {

					$("#patPrimaryCatCodeId").focus();
					var catWiseChargeValidType = 'validatechargeforcat['
							+ categoryJSONArray[i].patCatGroup + ','
							+ categoryJSONArray[i].charges + ']';

					$("#patPrimaryCatCodeId").validatebox({
						validType : [ catWiseChargeValidType ]
					});
				} else {

					$("#patPrimaryCatCodeId").focus();
					$("#patPrimaryCatCodeId").validatebox({
						validType : null
					});

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

				$('[name="isIdRequired"]')[0].value = categoryJSONArray[i].idReqFlag;
				$('[name="patPrimaryCat"]')[0].value = categoryJSONArray[i].patPrimaryCatName;
				$('[name="patCatShortName"]')[0].value = categoryJSONArray[i].patCatShortName;
				$('[name="patPrimaryCatGrp"]')[0].value = categoryJSONArray[i].patCatGroup;

				$('[name="patCatDocCode"]')[0].value = categoryJSONArray[i].patCatDocCode;
				$('[name="patCatDocIsAlternateId"]')[0].value = categoryJSONArray[i].patCatDocIsAlternateId;

				clientCode = categoryJSONArray[i].clientCode;
				clientName = categoryJSONArray[i].clientName;
				
				//alert("Cat short Name"+$('[name="patCatShortName"]')[0].value+"Json Value"+categoryJSONArray[i].patCatShortName);

				// alert("patCatDocCode - "+categoryJSONArray[i].patCatDocCode);
				// alert("patCatDocIsAlternateId -
				// "+categoryJSONArray[i].patCatDocIsAlternateId);
				// alert("patCatGroup - "+categoryJSONArray[i].patCatGroup);
				// alert("charges - "+categoryJSONArray[i].charges);

				console.log("patCatGroup :" + categoryJSONArray[i].patCatGroup);

				// PATIENT_REG_CATEGORY_GROUP_BENEFICIARY=0;
				if (categoryJSONArray[i].patCatGroup == PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE) {
					showHideCatGroupBeneficiaryTile("show");

				} else if (categoryJSONArray[i].patCatGroup == PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) {
					showHideCatGroupBeneficiaryTile("showAGS");

				} else {
					showHideCatGroupBeneficiaryTile("hide");
				}

				catIdValidType = opdregistration
						.getDocValidtype(categoryJSONArray[i].idValidationType);
				// alert("categoryJSONArray[i].idReqFlag
				// :"+categoryJSONArray[i].idReqFlag);
				if (categoryJSONArray[i].idReqFlag == "0") {
					$('[name="patIdNo"]')[0].value = "";
					$("#shownPatIdNoId").validatebox({
						required : false,
						validType : null
					});
					$("#divCatCardId").hide("blind");
					$("#divImgCatCardId").hide("blind");
					$("#divSpareCatId").show("blind");
					if ($('[name="alreadyRegisteredFromRenewalConfig"]')[0].value != ""
							&& $('[name="alreadyRegisteredFromRenewalConfig"]')[0].value != "0") {
						$("#divAlreadyRegisteredId").show();
					}
					if ($('[name="aadhaarSearchableFromRenewalConfig"]')[0].value != ""
							&& $('[name="aadhaarSearchableFromRenewalConfig"]')[0].value != "0") {
						$("#divaadhaarRegisteredId").show();
					}
					if ($('[name="mobileSearch"]')[0].value != ""
						&& $('[name="mobileSearch"]')[0].value != "0") {
					$("#divMobileSearchId").show();
					}
					if ($("#isMandataryReadOnlyTrueId").val() == "1")
						setMandatoryReadOnlyTrueFalse(false);
				} else if (categoryJSONArray[i].idReqFlag == "1") {
					$("#divCatCardId").show("blind");
					$("#divCatCardNoLabelId")
							.html(
									getCatCardNameBasedOnCatGroup(categoryJSONArray[i].patCatGroup));
					$("#shownPatIdNoId").attr('maxlength',
							categoryJSONArray[i].idSize);
					$("#shownPatIdNoId").focus();
					$("#divImgCatCardId").hide("blind");
					//$("#divSpareCatId").hide("blind");
					if ($('[name="alreadyRegisteredFromRenewalConfig"]')[0].value != ""
							&& $('[name="alreadyRegisteredFromRenewalConfig"]')[0].value != "0") {
						$("#divAlreadyRegisteredId").show("blind");
					}
					if ($('[name="aadhaarSearchableFromRenewalConfig"]')[0].value != ""
							&& $('[name="aadhaarSearchableFromRenewalConfig"]')[0].value != "0") {
						$("#divaadhaarRegisteredId").show("blind");
					}
					if ($('[name="mobileSearch"]')[0].value != ""
						&& $('[name="mobileSearch"]')[0].value != "0") {
					$("#divMobileSearchId").show("blind");
					}
					var docValidTypeNLength = 'equalLength['
							+ categoryJSONArray[i].idSize + ']';
					$("#shownPatIdNoId").validatebox({
						required : true,
						validType : [ catIdValidType, docValidTypeNLength,'NotAllZeroWithNumber' ]
					});

					if ($("#isMandataryReadOnlyTrueId").val() == "1")
						setMandatoryReadOnlyTrueFalse(false);
				} else if (categoryJSONArray[i].idReqFlag == "2") {
					$("#shownPatIdNoId").validatebox({
						required : false,
						validType : null
					});
					$("#divCatCardId").hide("blind");
					//$("#divSpareCatId").hide("blind");
					$("#divImgCatCardId").show("blind");
					$("#imgCatCardId").focus();
					$("#imgCatCardId").hide("blind");
					// alert($("#hiddenCatOrRegstrdPopupFlagId").val());
					if ($("#hiddenCatOrRegstrdPopupFlagId").val() != "A") {
						$("#imgCatCardId").show("blind");
						$("#divAlreadyRegisteredId").hide("blind");
						$('[name="isMandataryReadOnlyTrue"]')[0].value = "1";
						setMandatoryReadOnlyTrueFalse(true);
					}
				}
				if ((categoryJSONArray[i].patCatDocIsAlternateId != "0")) {
					// alert("1");
					// if((categoryJSONArray[i].patCatDocIsAlternateId=="1")&&((categoryJSONArray[i].idReqFlag=="1")||(categoryJSONArray[i].idReqFlag=="2")))
					{
						var action = "/HISRegistration/registration/transactions/getVerDocExceptCatDocNewUnitWiseRegistration.action?strDocCode="
								+ categoryJSONArray[i].patCatDocCode;
						$
								.ajax({
									url : action,
									type : "POST",
									async : true,
									dataType : "xml",
									success : function(data) {
										var returnDocument = data;
										var rootNode = returnDocument
												.getElementsByTagName("root")[0];
										for ( var i = 0; i < rootNode.childNodes.length; i++) {
											var elementNode = rootNode.childNodes[i];
											var elementName = elementNode.nodeName;

											opdregistration
													.processVerifyDocNode(
															elementName,
															elementNode);
										}

									},
									error : function(errorMsg, textstatus,
											errorthrown) {
										alert('onchange_patPrimaryCategory '
												+ errorMsg + " textstatus::"
												+ textstatus + " errorthrown::"
												+ errorthrown);

									}
								});
					}
				} else {
					// alert("2");
					var action = "/HISRegistration/registration/transactions/getVerDocExceptCatDocNewUnitWiseRegistration.action?strDocCode="
							+ "0";
					$
							.ajax({
								url : action,
								type : "POST",
								async : true,
								dataType : "xml",
								success : function(data) {
									var returnDocument = data;
									var rootNode = returnDocument
											.getElementsByTagName("root")[0];
									for ( var i = 0; i < rootNode.childNodes.length; i++) {
										var elementNode = rootNode.childNodes[i];
										var elementName = elementNode.nodeName;

										opdregistration.processVerifyDocNode(
												elementName, elementNode);
									}

								},
								error : function(errorMsg, textstatus,
										errorthrown) {
									alert('onchange_patPrimaryCategory '
											+ errorMsg + " textstatus::"
											+ textstatus + " errorthrown::"
											+ errorthrown);

								}
							});
				}

				break;
			}
		}

	},
	onchange_patDocType : function() {
		var docTypeObj = $('[name="patDocType"]')[0];
		var docType = docTypeObj.options[docTypeObj.selectedIndex].value;
		if (docType != "-1") {
			$("#divCardNoId").show("blind");
			$('[name="patCardNo"]').focus();
		} else {
			$('[name="patCardNo"]').validatebox({
				required : false
			});
			$("#divCardNoId").hide();
		}

		for ( var i = 0; i < veriyDocJSONArray.length; i++) {
			if (veriyDocJSONArray[i].patDocType == docType) {
				$('[name="patCardNo"]').attr('maxlength',
						veriyDocJSONArray[i].patDocIdSize);
				// alert("patDocIdSize :"+ veriyDocJSONArray[i].patDocIdSize);
				docValidType = opdregistration
						.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);

				var isize = veriyDocJSONArray[i].patDocIdSize;
				var docValidTypeNLength = 'equalLength[' + isize + ']';
				$('[name="patCardNo"]').validatebox({
					required : true,
					validType : [ docValidType, docValidTypeNLength,'NotAllZero' ]
				});
				break;
			} else {
				$('[name="patCardNo"]').validatebox({
					required : false
				});
			}
		}
	},
	getDocValidtype : function(idValidTypeCode) {
		var varDocValidType = 'alphaNumSpecialChar';
		if (idValidTypeCode == "0") {
			varDocValidType = 'alphaNumSpecialChar';
		} else if (idValidTypeCode == "1") {
			varDocValidType = 'numeric';
		} else if (idValidTypeCode == "2") {
			varDocValidType = 'alphaNumeric';
		} else if (idValidTypeCode == "3") {
			varDocValidType = 'alpha';
		}
		return varDocValidType;
	},
	populatePatientDtlFromJsonVOObj : function(elementNode) {
		//alert("inside populatePatientDtl");

		var patientJSONObject = jQuery.parseJSON(elementNode
				.getAttribute("value"));
		for ( var patientKey in patientJSONObject) {
			if (patientJSONObject.hasOwnProperty(patientKey)) {
				var patientVal = patientJSONObject[patientKey];
				// alert("patientKey :"+patientKey+", patientVal :"+patientVal);
				if (document.getElementsByName(patientKey)
						&& document.getElementsByName(patientKey)[0] != undefined
						&& patientKey != "patAddress") {
					eval("var " + patientKey + "='" + patientVal + "'");
					document.getElementsByName(patientKey)[0].value = eval(patientKey);
					
				}

			}
		}

	},
	/*populatePatientDtl : function(patientJSONObject) {
	//	alert("inside populatePatientDtl");

		for ( var patientKey in patientJSONObject) {
			if (patientJSONObject.hasOwnProperty(patientKey)) {
				var patientVal = patientJSONObject[patientKey];
				// alert("patientKey :"+patientKey+", patientVal :"+patientVal);
				if(patientKey == "patAgeWithUnit")
				{
					//alert("patientKey :"+patientKey+"\npatientVal :"+patientVal);
					patientVal = patientVal.split(" ")[0];//Added by Vasu on 30.May.2018
					patientKey = "patAge";
				}
				if (document.getElementsByName(patientKey)
						&& document.getElementsByName(patientKey)[0] != undefined) {
					By Warish on 09.09.17
					if(patientVal == null)//assuming that no patient comes with the name null
					{
						patientVal = ""; 
					}	
					if (patientKey == "patOccupation"||patientKey == "patCasteCode"||patientKey == "patDocType"||patientKey == "patMaritalStatusCode"|| patientKey == "patReligionCode"||patientKey== "patPrimaryCatCode")
					{
						if(patientVal=="0"||patientVal==""||patientVal==null){
							//alert("Warish");
							patientVal=-1;
						}
					}
					if(patientKey == "patAge")
					{
						//alert("patientKey :"+patientKey+"\npatientVal :"+patientVal);
						var unit = patientVal.split("-")[1];
						var patientVal = patientVal.split("-")[0];
						//document.getElementsByName("patAge")[0].value = age;
						//document.getElementsByName("patAgeUnit")[0].value = unit;
						$('#patAgeId').val(patientVal);
						$('#patAgeUnitId').val(unit);
					}
					if(patientKey == "departmentUnitCode")
					{
						$('[name="departmentUnitCode"]').val(patientVal);
						var deptObj = document.getElementsByName("departmentUnitCode")[0];
						if(deptObj.selectedIndex != '-1')
						{
							opdregistration.setDepartmentDependents(deptObj);
						}
						else
						{
							alert("Unit Is Not On Duty!\nPlease Select Any Other Unit.");
							patientVal = -1;
						}
					}
					End on 09.09.17
					eval("var " + patientKey + "='" + patientVal + "'");
					document.getElementsByName(patientKey)[0].value = eval(patientKey);
					//alert(patientKey);
					if(patientKey=="memSlNo")
							{
						//alert("in");
						alert(document.getElementsByName("memSlNo")[0].value);
							}
					if (patientKey== "patPrimaryCatCode")
					{
						if(patientVal!=""){
						document.getElementsByName("strEmployeePatCatGroup")[0].value=document.getElementsByName("patPrimaryCatGrp")[0].value;
						opdregistration.onchange_patPrimaryCategory();}
						}
					if (patientKey== "patAddStateCode")
					{
						if(patientVal!=""){
						opdregistration.onchange_patAddStateCode();
						}
						
						}
					
					
				}	
					// document.getElementsByName(patientKey)[0].disabled=true;
				}

			}
		
		// setMandatoryRefdReadOnlyTrueFalse(false);
		setMandatoryReadOnlyTrueFalse(false);
		
	},*/
	callOtherThread: function(patientKey, patientVal){
		//alert(patientVal);
		setTimeout(function(){
			$('[name="patAddDistrictCode"]').val(patientVal);
			var distObj = document.getElementsByName("patAddDistrictCode")[0];
			//alert(distObj);			
			//alert(distObj.selectedIndex +"\n"+(distObj.selectedIndex != undefined)  );
			if(!(distObj.selectedIndex != undefined && distObj.selectedIndex != '-1')){
				patientVal = -1;
			}
			//alert(patientVal);
			$('[name="patAddDistrictCode"]').val(patientVal);
		},400);
	},
	populatePatientDtl : function(patientJSONObject) {
		 //alert("inside populatePatientDtl");

		for ( var patientKey in patientJSONObject) {
			if (patientJSONObject.hasOwnProperty(patientKey)) {
				var patientVal = patientJSONObject[patientKey];
				//alert("patientKey :"+patientKey+", patientVal :"+patientVal);
				if(patientKey == "patAgeWithUnit")
				{
					//alert("patientKey :"+patientKey+"\npatientVal :"+patientVal);
					patientVal = patientVal.split(" ")[0];
					patientKey = "patAge";
				}
				if (document.getElementsByName(patientKey)
						&& document.getElementsByName(patientKey)[0] != undefined) {
					/**By Mukund on 15.09.17*/
					if (patientKey == "patOccupation"||patientKey == "patCasteCode"||patientKey == "patDocType"||patientKey == "patMaritalStatusCode"|| patientKey == "patReligionCode"||patientKey== "patPrimaryCatCode")
					{
						if(patientVal=="0"||patientVal==""||patientVal==null){
							patientVal=-1;
						}
					}
					if(patientKey == "patAge")
					{
						if(patientVal.indexOf('-')!= -1)
						{
							var unit = patientVal.split("-")[1];
							var patientVal = patientVal.split("-")[0];
							//document.getElementsByName("patAge")[0].value = age;
							//document.getElementsByName("patAgeUnit")[0].value = unit;
							$('#patAgeUnitId').val(unit);
							$('#patAgeId').val(patientVal);
						}
					}
					if(patientKey == "departmentUnitCode")
					{
						$('[name="departmentUnitCode"]').val(patientVal);
						//var deptObj = document.getElementsByName("departmentUnitCode")[0];
						
						//Changed by Garima on 5th July for Mobile Provisionla Registration Integration Issue Temporaray Solution
						$('[name="departmentCode"]').val(patientVal.substring(0,3));
						var deptObj = document.getElementsByName("departmentCode")[0];
								//alert(deptObj);			
						//alert($('[name="departmentCode"]').val());
						//alert(deptObj.selectedIndex +"\n"+(deptObj.selectedIndex != undefined)  );
						if(deptObj.selectedIndex != undefined && deptObj.selectedIndex != '-1')
						{
							opdregistration.setDepartmentDependents(deptObj);
						}
						else
						{
							//alert("Unit Is Not On Duty!\nPlease Select Any Other Unit.");
							patientVal = -1;
						}
					}
					if(patientKey == "departmentCode")
					{
						$('[name="departmentCode"]').val(patientVal);
						var deptObj = document.getElementsByName("departmentCode")[0];
						//alert($('[name="departmentCode"]').val());
						//alert(deptObj.selectedIndex +"\n"+(deptObj.selectedIndex != undefined)  );
						if(deptObj.selectedIndex != undefined && deptObj.selectedIndex != '-1'){
							opdregistration.setDepartmentDependents(deptObj);
						}else{
							patientVal = -1;
							$('[name="departmentCode"]').val(patientVal);
						}
					}
					/*End on 15.09.17*/
					eval("var " + patientKey + "='" + patientVal + "'");
					document.getElementsByName(patientKey)[0].value = eval(patientKey);
					
					if (patientKey == "patGenderCode" || patientKey == "patAgeUnit")
						document.getElementsByName(patientKey)[0].value="-1";
					else
						document.getElementsByName(patientKey)[0].value="";
					
					if(eval(patientKey)!=null && eval(patientKey)!="" && eval(patientKey)!="-N.A-")
						document.getElementsByName(patientKey)[0].value = eval(patientKey);
					
					if (patientKey== "patPrimaryCatCode")
					{
						if(patientVal!=""){
							document.getElementsByName("strEmployeePatCatGroup")[0].value=document.getElementsByName("patPrimaryCatGrp")[0].value;
						if(eval(patientKey) !=$('[name="patOldCatCode"]')[0].value)
						{						
						opdregistration.onchange_patPrimaryCategory();
						$("#divImgCatCardId").hide("blind");
						}
						}
					}
					
					if (patientKey== "patAddStateCode")//
					{
						if(patientVal!=""){
							opdregistration.onchange_patAddStateCode();
						}
						
					}
					if(patientKey == "patAddDistrictCode")
					{
						
						opdregistration.callOtherThread(patientKey, patientVal);
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
							if(gender =="M"){
								$('[name="patGuardianName"]')[0].value=relatedName;
							}else if(gender =="F")
								$('[name="patMotherName"]')[0].value=relatedName;
						}
						else if(relationCode==2){
							$('[name="patHusbandName"]')[0].value =relatedName;
						}else if(relationCode==6){
							if(gender =="M"){
								$('[name="patGuardianName"]')[0].value=relatedName;
							}else if(gender =="F")
								$('[name="patMotherName"]')[0].value=relatedName;
						}
					}
				}	
				}
			}
		
		setMandatoryReadOnlyTrueFalse(false);
		
	},
	
fetchPatDtlBasedOnPatCatCardNo : function(patientJSONObject) {
		// alert("inside fetchPatDtlBasedOnPatCatCardNo");
	$('[name="patGuardianName"]')[0].value ="";
	$('[name="patMotherName"]')[0].value ="";
	$('[name="patHusbandName"]')[0].value ="";
	opdregistration.populatePatientDtl(patientJSONObject);
		/*
		 * if($('[name="patDOB"]')[0].value!="")
		 * $('[name="isActualDob"]')[1].checked=true;
		 */

	},
	savePatientDtl : function() {

		if(!validateDivDob()) return;

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
		
		if ($('[name="searchUsingMobile"]')[0].checked) 
		{
			$('[name="searchUsingMobile"]')[0].value="1";
		}
		else
			$('[name="searchUsingMobile"]')[0].value="0";
		
		// return;
		// return false;
		// $('[name="patPrimaryCat"]')[0].value= $("#patPrimaryCatCodeId
		// option:selected").text();
		// For Submission
		isSearchable=false;
		sortandEncodebase64($("#NewUnitWiseRegistration"));//By Tanayjit on 21.05.18 for STH(Para Pollution)
		var action = "/HISRegistration/registration/transactions/saveNewUnitWiseRegistration.action";
		$('#submitId').hide();
		$.ajax({
			url : action,
			type : "POST",
			async : true,
			dataType : "xml",
			data : $("#NewUnitWiseRegistration").serialize(),
			success : function(data) {
				// alert("data :"+data);
				
				//$("#patPrimaryCatCodeId").removeClass("searchable");		
				//$('#submitId').show("blind");
				$('#submitId').show();
				var returnDocument = data;
				window.setTimeout(function (){
				temprtnData = data ;
				var rootNode = returnDocument.getElementsByTagName("root")[0];
				// alert("inside savePatientDtl 1");
				var elementNode = rootNode.childNodes[0];
				var elementName = elementNode.nodeName;

				if (elementName == 'savedMsgDtl') {
					isDuplicatePatientPopup = elementNode
							.getAttribute("isDuplicatePatientPopup");
					opdregistration.initializeAfterSave(elementName,
							elementNode);
				}
				}, 400);

			},
			error : function(errorMsg, textstatus, errorthrown) {
				$('#submitId').show("blind");
				//alert('savePatientDtl ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);

			}
		});
		
	},
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

		sortandEncodebase64($("#NewUnitWiseRegistration"));//By Tanayjit on 21.05.18 for STH(Para Pollution)
		var action = "/HISRegistration/registration/transactions/saveAsNewPatientNewUnitWiseRegistration.action";
		$('#submitId').hide();
		$.ajax({
			url : action,
			type : "POST",
			async : true,
			dataType : "xml",
			data : $("#NewUnitWiseRegistration").serialize(),
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
					opdregistration.initializeAfterSave(elementName,
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
	initializeAfterSave : function(saveMsgElementName, saveMsgElementNode) {
		$('#fatherorSpouseError').hide();
		var isSavedSuccussful = saveMsgElementNode
				.getAttribute("isSavedSuccussful");
		var msg = saveMsgElementNode.getAttribute("savedMessage");
		var isFormFieldTobeReset = saveMsgElementNode
				.getAttribute("isFormFieldTobeReset");
		var isPrintFlag = saveMsgElementNode.getAttribute("isPrintFlag");
		var printDivContent = saveMsgElementNode
				.getAttribute("printDivContent");
		//By Mukund on 25.11.2016
		var tokenToSet = saveMsgElementNode.getAttribute("tkn");// = saveMsgElementNode.getAttribute("tkn");
		$('[name="token"]').val(tokenToSet);
		// alert("isSavedSuccussful :"+isSavedSuccussful);
		if (isSavedSuccussful == "1") {
			$("#divNormalMsgId").html(msg);
			$("#divErrorMsgId").html("");
			if (isPrintFlag == "1") {
				$("#divPrintId").html(printDivContent);
				get_object("divBarCodeControl").innerHTML = DrawCode39Barcode(
						get_object("divBarCodeControl").innerHTML, 0);
				if(get_object("divBarCodeControlForBill")!=null){
				get_object("divBarCodeControlForBill").innerHTML = DrawCode39Barcode(
						get_object("divBarCodeControlForBill").innerHTML, 0);
				}
				// Added For Opd Card and Bill Print on the Same Time
				var oldPage = $('body').html();
				$('body').html(
						"<html><head><title></title></head><body><p>"
								+ get_object("divPrintId").innerHTML
								+ "</p></body>");
				window.print();
				$('body').html(oldPage);
				
				
			} else {
				$("#divPrintId").html("");
			}
			if($('[name="barcodeSlipPrintFlag"]')[0].value == isBarcodeSlipPrint_Yes)
				manageBarcodePrintProcess();
			
			if (isFormFieldTobeReset == "1") {
				// if(confirm(msg+"\nDo you Want To Reset The Form"))
				opdregistration.setFormFieldsAfterSave();
				enterCount = 0;
			}
		} else {
			if (isDuplicatePatientPopup == "1")
				opdregistration.showDuplicatePatientPopup(temprtnData);

			$("#divErrorMsgId").html(msg);
			$("#divNormalMsgId").html("");
			$("#divPrintId").html("");
			// if(confirm(msg+"\nDo you Want To Reset The Form"))
			if (isFormFieldTobeReset == "1") {
				opdregistration.setFormFieldsAfterSave();
				enterCount = 0;
			}

		}
		$("#divAadharConsentId").hide();
	},
	setFormFieldsAfterSave : function() {
		$('#fatherorSpouseError').hide();
		$('.validatebox-text').removeClass('validatebox-invalid');
		// $('.validatebox-text').removeClass('validatebox-invalid');

		$('[name="patPrimaryCat"]')[0].value = "";
		//$('[name="departmentCode"]')[0].value = "-1";
		$('[name="departmentCode"]')[0].value = opdregistration.fetchDefaultValues();
		$('[name="patIdNo"]')[0].value = "";
		$('[name="hiddenPatIdNo"]')[0].value = "";
		$('[name="alreadyRegisteredFlag"]')[0].checked = false;
		$("#hiddenCatOrRegstrdPopupFlagId").val("");
		// $("#divCatCardId").hide();

		$('[name="patCatShortName"]')[0].value = "";
		document.getElementById("amount").innerHTML  = "";
		$('[name="patAmountCollected"]')[0].value ="";
		$('[name="paymentModeCode"]').val("1");//default value for cash
		opdregistration.setPaymentModeRefId(document.getElementsByName("paymentModeCode")[0]);
	
		$('[name="patPrimaryCatCode"]')[0].value ="11";  //"-1"; // changed by manisha for setting default patcat 'general'
		var priCatObj = $('[name="patPrimaryCatCode"]')[0];
		if(priCatObj.selectedIndex == undefined || priCatObj.selectedIndex == null || priCatObj.selectedIndex == "-1")
			$('[name="patPrimaryCatCode"]')[0].value = "-1";
		
		opdregistration.onchange_patPrimaryCategory();
		
		$('[name="patFirstName"]')[0].value = "";
		$('[name="patMiddleName"]')[0].value = "";
		$('[name="patLastName"]')[0].value = "";

		$('[name="patFirstNameInMultiLang"]')[0].value = "";
		$('[name="patMiddleNameInMultiLang"]')[0].value = "";
		$('[name="patMiddleNameInMultiLang"]')[0].value = "";

		// $('.resetTextBox').val("");
		document.getElementById("divNewDOBHidden").innerHTML  = "";
		$('[name="isActualDob"]')[0].checked = true;// if (age provided)
													// isActualDob=0 else
													// isActualDob=1
		showDivAgeDob();
		$('[name="patAge"]')[0].value = "";
		$('[name="patAgeUnit"]')[0].value = "Yr";
		//$('[name="patDOB"]')[0].value = "";
		//$('[name="patDOB_Dup"]')[0].value = "";
		$('[name="patGender"]')[0].value = "";
		$('[name="patGenderCode"]')[0].value = "-1";

		$('[name="patMaritalStatusCode"]')[0].value = "-1";
		$('[name="patGuardianName"]')[0].value = "";
		$('[name="patHusbandName"]')[0].value = "";

		$('[name="patMotherName"]')[0].value = "";
		$('[name="patCaste"]')[0].value = "";
		$('[name="patCasteCode"]')[0].value = "-1";
		$('[name="patReligionCode"]')[0].value = "-1";
		$('[name="patBirthPlace"]')[0].value = "";
		$('[name="patOccupation"]')[0].value = "-1";
		$('[name="patMonthlyIncome"]')[0].value = "";
		

		$('[name="patNationalId"]')[0].value = "";

		$('[name="patIsUrban"]')[0].value = "";
		$('[name="patDocType"]')[0].value = "-1";
		opdregistration.onchange_patDocType();

		$('[name="patCardNo"]')[0].value = "";
		$('[name="patNationalId"]')[0].value = "";
		$('[name="patVisitReason"]')[0].value = "";

		// Address Details corresponding to AddressVO
		$('[name="patAddCountry"]')[0].value = "";
		$('[name="patAddCountryCode"]')[0].value = $('[name="defaultpatAddCountryCode"]')[0].value;
		$('[name="patAddState"]')[0].value = "";
		$('[name="patAddStateCode"]')[0].value = $('[name="defaultpatAddStateCode"]')[0].value;
		opdregistration.onchange_patAddCountryCode();
		$('[name="patAddDistrict"]')[0].value = "";
		// alert("defaultpatAddDistrictCode :"+
		// $('[name="defaultpatAddDistrictCode"]')[0].value);
		// $('[name="patAddDistrictCode"]')[0].value=$('[name="defaultpatAddDistrictCode"]')[0].value;
		$('[name="patAddHNo"]')[0].value = "";
		$('[name="patAddCityLoc"]')[0].value = "";
		$('[name="patAddStreet"]')[0].value = "";
		$('[name="patAddCity"]')[0].value = "";

		$('[name="patAddPIN"]')[0].value = "";
		$('[name="patAddPhoneNo"]')[0].value = "";
		$('[name="patAddMobileNo"]')[0].value = "";
		$('[name="patAddLandMarks"]')[0].value = "";
		$('[name="patAddEmailId"]')[0].value = "";
		$('[name="patAddPhoneNo"]')[0].value = "";
		$('[name="patAddPhoneOwner"]')[0].value = "-1";
		$('[name="patEmgCntNo"]')[0].value = "";
		$('[name="patIsUrban"]')[0].value = "-1";
		$('[name="prevCrNo"]')[0].value = "";
		$('[name="patIdNo"]')[0].value = "";

		$('[name="patPrimaryCatGrp"]')[0].value = "";
		$('[name="patDocTypeName"]')[0].value = "";

		// Referred Inst Dtl
		if ($('[name="isReferred"]')[0].checked) {
			$('[name="isReferred"]')[0].checked = false;
			Isreferred($('[name="isReferred"]')[0]);
		}
		setMandatoryReadOnlyTrueFalse(false);
		$('[name="txt-snomed-ct-search_1"]').removeClass('x onX').val('').change();//for clearing snomed fields
	},
	showDuplicatePatientPopup : function(rtnData) {
//alert("insidde showDuplicatePatientPopup");
		var windowWidth = $(window).width() - 50;
		var windowHeight = 350;

		var page="/HISRegistration/registration/transactions/jsp/opdNewRegDuplicatePopup.jsp";

		var $dialog = $('<div></div>').html(
				'<iframe style="border: 0px; " src="' + page
						+ '" width="100%" height="100%"></iframe>').dialog({
			autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
			title : "Patient Detail Exists",	show: { effect: "clip"},resizable: true,
			position: ['top', 100],dialogClass: 'no-close custbtncolor',
			buttons:{
				"Save As New Patient": function() {
					var selecteCRToRevisit = $('[name="radioForDuplicate_parent"]').val();
					//alert("Not Disabled2: "+selecteCRToRevist);
					
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
							opdregistration.saveAsNewPatientDtl();
						}
			},
			"Continue With Existing": function() {
				var selecteCRToRevisit = $('[name="selectedCRToVisit"]').val();
				//alert("Not Disabled2: "+selecteCRToRevist);
				if(selecteCRToRevisit==undefined || selecteCRToRevisit==null || selecteCRToRevisit=='')
				{
					alert("Please select a record!");
				}
				else
				{
					opdregistration.continueWithExisting(rtnData);
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
			var url="/HISRegistration/registration/transactions/PatientVisit.action?patCrNo="+selecteCRToRevist+"&isDuplicateRegistered=1&selectedDuplicatePatCrNo="+selecteCRToRevist;//+"&slNoHashUhidForDupCont="+slNoHashUhidForDupCont;
			var menu = 'Patient_Revisit';
			parent.ajaxStartMenu();
			menu=menu.toString().replace(/_/g,' ').replace(/XXY/g,'/').replace(/XXX/g,'&');
			//url = Base64.decode(url).toString();
			parent.callMenu(url,menu);
			parent.ajaxCompleteMenu();
		}
	},
	setPaymentModeRefId: function(obj){
		//alert("setPaymentModeRefId: "+obj.value);
		var paymentModeCode=obj.value.split("#")[0];
		if(paymentModeCode!="" &&  paymentModeCode!="-1" && paymentModeCode!=1 && paymentModeCode!=13 && paymentModeCode!=10){//1 for cash, 10 for cm relief fund, 13 for jan arogya
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
			$("#paymentModeCodeIds").val('1');//hardcoded wrt "general" patient category
			opdregistration.setPaymentModeRefId($('[name="paymentModeCode"]')[0]);
			$("#paymentModeCodeIds").validatebox({
				validType : [ 'selectCombo[-1]' ]
			});
			opdregistration.onchange_patPrimaryCategory();
		}
		
		/*if(elementNode.childNodes.length==1)
		{
			$("#deptId option:eq(1)").attr('selected','selected');
			opdregistration.setDepartmentDependents(document.getElementsByName("departmentCode")[0]);
		}
		else
		{
			$("#deptId").validatebox({
				validType : [ 'selectCombo[-1]' ]
			});
		}
			*/
		
	},
	processPaymentModeNode_onCatChange : function(primCatCode) {
		var optionText = "";
		var defaultPaymentMode = "-1";
		
		if (primCatCode != null) {
			optionText = "<option value='-1'>Select Value</option>";
			/*if(paymentModeJSONArray.length === 0){
				defaultPaymentMode = -1;
			}else{*/
			
				for ( var i = 0; i < paymentModeJSONArray.length; i++) {
					
					/*paymentModeJSONArray[i].recieptCategoryCode
					paymentModeJSONArray[i].refundCategoryCode
					paymentModeJSONArray[i].paymentModeCode
					paymentModeJSONArray[i].paymentCodeName*/
					var mappedCatCode = paymentModeJSONArray[i].recieptCategoryCode;
					if(paymentModeJSONArray[i].refundCategoryCode != 0){
						mappedCatCode = paymentModeJSONArray[i].refundCategoryCode;
					}
					var arrPrimCatWidDfltPymntMd = [];
					//arrPrimCatWidDfltPymntMd = mappedCatCode;
					//11@1^12@11 -- 4 -- 11@1^12@11
					//console.log(mappedCatCode+" -- "+mappedCatCode.indexOf('^')+" -- "+mappedCatCode.split('^').length);
					//if (mappedCatCode.indexOf('^')!=-1){
						arrPrimCatWidDfltPymntMd = mappedCatCode.split('^');
					//}
					//console.log("primCatCode: "+primCatCode +" -- mappedCatCode: "+ mappedCatCode+" : "+mappedCatCode.includes(primCatCode));
					//console.log("arrPrimCatWidDfltPymnt: "+arrPrimCatWidDfltPymntMd+"\n len: "+arrPrimCatWidDfltPymntMd.length+" -- "+primCatCode);
					for(var j=0; j<arrPrimCatWidDfltPymntMd.length; j++){
						//console.log(arrPrimCatWidDfltPymntMd[j].split('@')[0]+" -- "+primCatCode);
						if(arrPrimCatWidDfltPymntMd[j].split('@')[0] === primCatCode){
							defaultPaymentMode = arrPrimCatWidDfltPymntMd[j].split('@')[1];
							optionText += "<option value='"
								+ paymentModeJSONArray[i].paymentModeCode + "'>"
								+ (paymentModeJSONArray[i].paymentCodeName) + "</option>";
						}
					}
	/*				if(mappedCatCode.includes(primCatCode)){
						optionText += "<option value='"
							+ paymentModeJSONArray[i].paymentModeCode + "'>"
							+ (paymentModeJSONArray[i].paymentCodeName) + "</option>";
					}*/

				}
			//}
			
			if (document.getElementsByName("paymentModeCode").length != 0)
				document.getElementsByName("paymentModeCode")[0].innerHTML = optionText;
			//alert(paymentModeJSONArray.length+"\n"+defaultPaymentMode);
			$("#paymentModeCodeIds").val(defaultPaymentMode);
			opdregistration.setPaymentModeRefId($('[name="paymentModeCode"]')[0]);
		}
				
		/*if(elementNode.childNodes.length==1)
		{
			$("#deptId option:eq(1)").attr('selected','selected');
			opdregistration.setDepartmentDependents(document.getElementsByName("departmentCode")[0]);
		}
		else
		{
			$("#deptId").validatebox({
				validType : [ 'selectCombo[-1]' ]
			});
		}
			*/
	
	}
};

$("#patDocTypeId").change(function() {
	opdregistration.onchange_patDocType();
});

$("#patPrimaryCatCodeId").change(function() {
	opdregistration.onchange_patPrimaryCategory();
});

function closeModelFunction(popupmodal, func) {
	// alert("closeFunction");
	popupmodal.close = eval(func);
	// $.modal.destroy();
}

$("#imgCatCardId")
		.click(
				function() {
					var patPrimarCatId = $("#patPrimaryCatCodeId").val();
					var action = "/HISRegistration/registration/transactions/openPatPopupNewUnitWiseRegistration.action?patPrimarCatId="
							+ patPrimarCatId + "&alreadyRegisteredFlag=0";
					openURLInPopupWithoutClose(action, '900', '400');
					// openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');

				});

$("#alreadyRegisteredFlagId")
		.click(
				function() {
					var alreadyRegisteredFlag = this.value;
					if (this.checked == false) {
						opdregistration.setFormFieldsAfterSave();
						$("#hiddenCatOrRegstrdPopupFlagId").val("");
						$("#divNormalMsgId").html("");
						$("#divErrorMsgId").html("");
						$("#divPrintId").html("");
						if (("#isIdRequired").val() == "")
							$("#imgCatCardId").show("blind");
						return;
					}
					var patPrimarCatId = $("#patPrimaryCatCodeId").val();
					var action = "/HISRegistration/registration/transactions/openAlreadyRegPatPopupNewUnitWiseRegistration.action?patPrimarCatId="
							+ patPrimarCatId
							+ "&alreadyRegisteredFlag="
							+ alreadyRegisteredFlag;
					openURLInPopupWithoutClose(action, '900', '400');
					// openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');
				});

$("#searchUsingMobileId")
.click(
		function() {
			var alreadyRegisteredFlag = this.value;
			if (this.checked == false) {
				opdregistration.setFormFieldsAfterSave();
				$("#hiddenCatOrRegstrdPopupFlagId").val("");
				$("#divNormalMsgId").html("");
				$("#divErrorMsgId").html("");
				$("#divPrintId").html("");
				if (("#isIdRequired").val() == "")
					$("#imgCatCardId").show("blind");
				return;
			}
			var patPrimarCatId = $("#patPrimaryCatCodeId").val();
			var action = "/HISRegistration/registration/transactions/openMobileSearchPatPopupNewUnitWiseRegistration.action?patPrimarCatId="
					+ patPrimarCatId
					+ "&alreadyRegisteredFlag="
					+ alreadyRegisteredFlag;
			
			openURLInPopupWithoutClose(action, '600', '200');
			
			// openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');
});
// Submit form if valid
$('#submitId').click(function(e) {
	// alert("inside submitClick");
	/*This loop will trim all leading and trailing spaces from input type fileds*/
	enterCount++;
	if(enterCount!=1)
		return false;
	var allInputs = $(":input"); 
	allInputs.each(function() {
	        $(this).val($.trim($(this).val()));
	    });
	//End
	setMotherValidRule();
	fatherorSpouseerror();
	fatherorSpouse();
	
	//To Validate Either Father/Spouse/Mother Name	
	//alert("Father --"+$('[name="patGuardianName"]').val()+"--Spouse --"+$('[name="patHusbandName"]').val()+"--Mother--"+$('[name="patMotherName"]').val());
	
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
	if ($('#NewUnitWiseRegistration').form('validate')) {
		$('#submitId').hide();
		opdregistration.savePatientDtl();
		$('#submitId').show("blind");
	} else {
		// alert("Validation false");
		enterCount=0;
		return false;
	}
	e.preventDefault();
});

// Clear Form Data
$('#clearId').click(function(e) {
	$('[name="departmentCode"]')[0].focus();
	opdregistration.setFormFieldsAfterSave();
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");
});
$('[name="patRefGnctdHospitalCode"]').change(function() {
	// opdregistration.onchange_patRefGnctdHospitalDept();
});

$("#patAgeUnitId").change(
		function() {
			var maxAgeRange = calculateMaxRangeValue(ageBoundRange, $(
					"#patAgeUnitId").val());
			var ageRangeValidType = 'range[1,' + maxAgeRange + ']';
			$("#patAgeId").validatebox({
				required : true,
				validType : [ 'numeric', ageRangeValidType,'startZero' ]
			});
		});

/*$("#patDOBId").change(
		function() {
			setMotherValidRule();
		});*/
$("#patDOBId_Dup").change(
		function() {
			setMotherValidRule();
		});

/*
 * $("#nonprintableDiv1").click(function(){ //alert("nonprintableDiv1 clicked
 * :"+$("divPrintId").html()); //$("#divNormalMsgId").html("");
 * $("#divErrorMsgId").html(""); $("#divPrintId").html(""); });
 */

// To Open the Dialog box
$("#divCatGroupCreditBeneficiaryId").dialog({
	autoOpen : false,
	show : {
		effect : "blind",
		duration : 1000
	},
	hide : {
		effect : "explode",
		duration : 1000
	},
	buttons : {
		Cancel : function() {
			dialog.dialog("close");
		}
	}
});

function trimData(val) {
	// alert((typeof val).toUpperCase());
	if (val && val != null && val != ""
			&& (typeof val).toUpperCase() == 'STRING') {
		while (val.substr(0, 1) == ' ')
			val = val.substr(1);
		while (val.substr(val.length - 1, 1) == ' ')
			val = val.substr(0, val.length - 1);
	}
	return val;
}

function getCatCardNameBasedOnCatGroup(patCatGroupId) {
	var cardName = "Id";
	// if(patCatGroupId=="1"){
	// cardName="Employee Id";
	// }else if(patCatGroupId=="4"){
	// cardName="BPL Card No";
	// }
	return cardName;
}

function calculateMaxRangeValue(age, ageUnit) {
	var maxAgeRange = 125;

	if (ageUnit == "D") {
		maxAgeRange = (age * 365);
	} else if (ageUnit == "Wk") {
		maxAgeRange = age * 365 / 7;
	} else if (ageUnit == "Mth") {
		maxAgeRange = age * 12;
	} else if (ageUnit == "Yr") {
		if (age < maxAgeRange)
			maxAgeRange = age;
	}
	return maxAgeRange;
}

function setMotherValidRule() {
	var flagYes = false;
	var allowSpouse = true;
	// alert("inside setMotherValidRule()");
	if ($('[name="isActualDob"]')[0].checked
			&& $('[name="patAgeUnit"]')[0].value != "-1") {
		if ($("#patAgeId").val() < calculateMaxRangeValue(12,
				$("#patAgeUnitId").val())) {
			flagYes = true;
		}
		if ($("#patAgeId").val() < calculateMaxRangeValue(18,
				$("#patAgeUnitId").val())) {
			allowSpouse = false;
		}
	} else if ($('[name="patDOB_Dup"]')[0].value != "" && $('[name="patDOB_Dup"]')[0].value != "dd-mm-yyyy")//	&& isDate($('[name="patDOB"]')[0], "dd-mmm-yyyy")) 
		{
		var d1 = $.datepicker.parseDate("dd-mm-yy", $("#patDOBId_Dup").val());
		var ctDt = new Date();
		var yr = d1.getFullYear();
		var ctDtYr = ctDt.getFullYear();		
	
		if ((ctDtYr - 12) <= yr) {
			flagYes = true;
		}		
		/*if ((ctDtYr - 18) <= yr) {
			allowSpouse = false;
		}
*/
		var temp =ctDtYr-18;
		ctDt.setFullYear(temp, ctDt.getMonth());
		if(ctDt<=d1)
		{
			allowSpouse = false;
		}
	}
	// alert("flagYes :"+flagYes);
	if (flagYes) {
		$('[name="patMotherName"]').validatebox({
			required : true,
			validType : 'alphaWithSpace'
		});
	} else {
		$('[name="patMotherName"]').validatebox({
			required : false,
			validType : 'alphaWithSpace'
		});
	}
	
	//alert("allowSpouse"+allowSpouse);
	if(allowSpouse){
		$('[name="patHusbandName"]').removeAttr("disabled");
		
	}
	else{
		$('[name="patHusbandName"]').validatebox({
			required : false,
			validType : 'alphaWithSpace'
		});
		$('[name="patHusbandName"]').val("");
		$('[name="patHusbandName"]').attr("disabled", "disabled");
	}
	
}
function showDivAgeDob() {
	if ($('[name="isActualDob"]')[0].checked) {
		$("#divAge").show();
		$("#divDob").hide();
		var maxAgeRange = calculateMaxRangeValue(ageBoundRange, $(
				"#patAgeUnitId").val());
		var ageRangeValidType = 'range[1,' + maxAgeRange + ']';
		// alert("ageRangeValidType :"+ageRangeValidType);
		$("#patAgeId").validatebox({
			required : true,
			validType : [ 'numeric', ageRangeValidType,'startZero' ]
		});
		$("#patAgeUnitId").validatebox({
			required : true,
			validType : [ 'selectCombo[-1]' ]
		});
		$("#patDOBId").validatebox({
			required : false
		});
		$("#patDOBId_Dup").validatebox({
			required : false
		});

	} else {
		$("#divAge").hide();
		$("#divDob").show();
		$("#patAgeId").validatebox({
			required : false
		});
		$("#patAgeUnitId").validatebox({
			validType : null
		});
		$(function() {
			
			document.getElementById("divNewDOBHidden").innerHTML  ="";
			if($("#patDOBId_Dup").val()!=""){
				
				DateValidator.setup("divNewDOB", "patDOB_Dup", $("#patDOBId").val(), "dd-Mon-yyyy", "input45prcnt", "patDOBId_Dup", "1", "divNewDOBHidden");

				/*var _date=$.datepicker.parseDate("dd-M-yy", $("#patDOBId").val());
				var wrappedDate = moment(_date);
				_date=wrappedDate.format('DD-MMM-YYYY');*/
				/*$("#patDOBId").datepicker("destroy");
				$("#patDOBId").datepicker({dateFormat: 'dd-M-yy',
											onSelect: function(d,i){
										          if(d !== i.lastVal){
										              $(this).change();
										          }
										     }}).datepicker("setDate", new Date(_date));*/
				
				/*$('#patDOBId').DatePicker({
					format: 'd-M-Y',
					default_position :'below',
					start_date:_date,
					onSelect: function(d,i){
				          if(d !== i.lastVal){
				              $(this).change();
				          }}
				}).val(_date);*/
				
				
			}
			else{
				
				DateValidator.setup("divNewDOB", "patDOB_Dup", "", "dd-Mon-yyyy", "input45prcnt", "patDOBId_Dup", "1", "divNewDOBHidden");

				/*$("#patDOBId").datepicker("destroy");
				$("#patDOBId").datepicker({dateFormat: 'dd-M-yy',
											changeMonth: true,    stepMonths: 12, showAnim:"slideDown",
											onSelect: function(d,i){
										          if(d !== i.lastVal){
										              $(this).change();
										          }
										     }}).datepicker("setDate", new Date());	*/


				//var today=new Date().toLocaleFormat('%d-%b-%Y');//Thu Apr 26 2018 14:51:21 GMT+0530
				var today = moment().format('DD-MMM-YYYY');
				/*$('#patDOBId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(today);*/
				/*var today = moment().format('YYYY-MM-DD');
				$('#ptaDOBId').val(today);*/
				
				}

		});
		// $("#patDOBId").validatebox({required: true, validType:
		// 'dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date.
		// Please Select Valid Date Of Birth \']'});

		var dobValidType = 'dtltetDMY[' + ageBoundRange + ',\'y\']';
		/*$("#patDOBId")
				.validatebox(
						{
							required : true,
							validType : [
									'date[\'patDOB\',\'dd-mmm-yyyy\']',
									'dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth \']',
									dobValidType ]
						});*/
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

function setMandatoryRefdInitial() {
	if ($('[name="referringInstType"]')[0].checked) {
		('[name="patRefGnctdHospitalCode"]')[0].value = "-1";
		$("#patRefGnctdHospitalDeptId").html(
				"<option value='-1'>Select Value</option>");
	} else {
		$('[name="patRefHospitalName"]')[0].value = "";
	}
	$('[name="patRefDoctor"]')[0].value = "";
}
function setMandatoryInitial() {
	$('[name="patFirstName"]')[0].value = "";
	if ($('[name="isActualDob"]')[0].checked) {
		$("#patAgeId").val("");
		$("#patAgeUnitId").val("-1");
	} else {
		$("#patDOBId").val("");
		$("#patDOBId_Dup").val("");
	}
	/*
	 * $('[name="patAddCountryCode"]')[0].value="-1";
	 * $("#patAddStateCodeId").html("<option value='-1'>Select Value</option>");
	 * $("#patAddDistrictCodeId").html("<option value='-1'>Select Value</option>");
	 */

}

function setMandatoryRefdReadOnlyTrueFalse(flagTrueFalse) {
	if (flagTrueFalse)
		setMandatoryInitial();

	if ($('[name="referringInstType"]')[0].checked) {
		$('[name="patRefGnctdHospitalCode"]').attr("disabled", flagTrueFalse);
	} else {
		$('[name="patRefHospitalName"]').prop('readOnly', flagTrueFalse);
	}
	$('[name="patRefDoctor"]').prop('readOnly', flagTrueFalse);
}
function setMandatoryReadOnlyTrueFalse(flagTrueFalse) {
	if (flagTrueFalse)
		setMandatoryInitial();

	$('[name="patFirstName"]').prop('readOnly', flagTrueFalse);
	if ($('[name="isActualDob"]')[0].checked) {
		$("#patAgeId").attr("disabled", flagTrueFalse);
		$("#patAgeUnitId").attr("disabled", flagTrueFalse);
	} else {
		//$("#patDOBId").prop('readOnly', flagTrueFalse);
	}
	/*
	 * $("#patAddCountryCodeId").attr("disabled", flagTrueFalse);
	 * $("#patAddStateCodeId").attr("disabled", flagTrueFalse);
	 * $("#patAddDistrictCodeId").attr("disabled", flagTrueFalse);
	 */

	/*
	 * if($('[name="isReferred"]')[0].checked){ if(flagTrueFalse)
	 * setMandatoryRefdInitial();
	 * setMandatoryRefdReadOnlyTrueFalse(flagTrueFalse); }
	 */

}

function ageSelection() {
	// alert("isActualDob.checked :"+
	// document.getElementsByName("isActualDob")[0].checked);
	if (document.getElementsByName("isActualDob")[0].checked) {
		document.getElementById("divAge").style.display = "";
		document.getElementById("divDob").style.display = "none";
	} else {
		document.getElementsByName("patAge")[0].value = "";
		document.getElementsByName("patAgeUnit")[0].selectedIndex = 0;
		document.getElementById("divAge").style.display = "none";
		document.getElementById("divDob").style.display = "";
	}
}

function Isreferred(elem) {
	if (elem.checked) {
		elem.value = "1";
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
			//validType : 'alphaNumeric'
			validType : 'alphaNumericWithSpaces'
		});

		if ($('[name="referringInstType"]')[0].checked) {
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
			$("#divRefHosname").show();
			$("#divRefHosCode").hide();
			$("#divReferredInstitute").show();

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
		elem.value = "0";
		$('[name="patRefGnctdHospitalCode"]').validatebox({
			validType : null
		});
		$('[name="patRefHospitalName"]').validatebox({
			required : false
		});
		$('[name="patRefDoctor"]').validatebox({
			required : false
		});

		$("#divRefDtlId").hide();
		$("#divRefHosname").hide();
		$("#divRefHosCode").hide();
		$("#divReferredInstitute").hide();
		$("#divReferred").hide();
	}
}

function showdivhoscode() {
	document.getElementById("divRefHosCode").style.display = "";
	document.getElementById("divRefHosname").style.display = "none";
	document.getElementsByName("patRefHospitalName")[0].value = "";
	document.getElementsByName("patRefDoctor")[0].value = "";
	Isreferred(document.getElementsByName("isReferred")[0]);
}

function fatherSouseRequired(temp) {
	if (temp == 1) {
		$('[name="patGuardianName"]').validatebox({
			required : true,
		});
	} else {

	}
}

$("#patRefGnctdHospitalDeptId").change(function() {
	if ($("#patRefGnctdHospitalDeptId").val() == "0") {
		$("#divRefHospitalDeptOtherId").show();
		$('[name="patRefHospitalDeptOther"]').validatebox({
			required : true,
			validType : 'alphaWithSpace'
		});
	} else {
		$("#divRefHospitalDeptOtherId").hide();
		$('[name="patRefHospitalDeptOther"]').validatebox({
			required : false,
			validType : 'alphaWithSpace'
		});
	}
});

/*
 * $(document).ajaxStart(function() { //alert('ajax start'); // show loader on
 * start $("#loadingmessage").css("display","block"); }).ajaxSuccess(function() {
 * //alert('ajax end'); // hide loader on success
 * $("#loadingmessage").css("display","none"); });
 */

jQuery.fn.limitMaxlength = function(options) {

	var settings = jQuery.extend({
		attribute : "maxlength",
		onLimit : function() {
		},
		onEdit : function() {
		}
	}, options);

	// Event handler to limit the textarea
	var onEdit = function() {
		var textarea = jQuery(this);
		var maxlength = parseInt(textarea.attr(settings.attribute));

		if (textarea.val().length > maxlength) {
			textarea.val(textarea.val().substr(0, maxlength));

			// Call the onlimit handler within the scope of the textarea
			jQuery.proxy(settings.onLimit, this)();
		}

		// Call the onEdit handler within the scope of the textarea
		jQuery.proxy(settings.onEdit, this)(maxlength - textarea.val().length);
	};

	this.each(onEdit);

	return this.keyup(onEdit).keydown(onEdit).focus(onEdit);

};

$(document).ready(
		function() {

			var onEditCallback = function(remaining) {
				$(this).siblings('.charsRemaining').text(
						"Characters remaining: " + remaining);

				if (remaining > 0) {
					$(this).css('background-color', 'white');
				}
			};

			var onLimitCallback = function() {
				$(this).css('background-color', 'red');
			};

			$('textarea[maxlength]').limitMaxlength({
				onEdit : onEditCallback,
				onLimit : onLimitCallback,
			});
		});

// validType: 'validateAdvAmount[\'patMonthlyIncome\',8,2,\'Kindly Enter Amount
// in 99999999.99 format\']'

/**/

/*
 * $('[name="patDOB"]').validatebox({ required: true, validType:
 * 'dtltet[\'anotherPatDOB\',\'PatDOB should be less than or equalt to
 * AnotherPatDOB\']' //validType:
 * 'md[\''+$('[name="anotherPatDOB"]')[0].value+'\']' });
 */
function fatherorSpouseerror() {
	var fatherName = $('[name="patGuardianName"]')[0].value;
	var spouseName = $('[name="patHusbandName"]')[0].value;

	if ((fatherName == "" || fatherName == null)
			&& (spouseName == "" || spouseName == null))
		$('#fatherorSpouseError').show();
	else
		$('#fatherorSpouseError').hide();
}

function fatherorSpouse() {
	var fatherName = $('[name="patGuardianName"]')[0].value;
	var spouseName = $('[name="patHusbandName"]')[0].value;
	var maritalStatusCode = $('[name="patMaritalStatusCode"]')[0].value;
	if (maritalStatusCode == "2" || maritalStatusCode == "3") {
		if (spouseName != "" || spouseName != null)
			$('[name="patGuardianName"]').validatebox({
				required : false,
				validType : null
			});

	}
}

function showHideCatGroupBeneficiaryTile(mode) {

	var windowWidth = $(window).width() - 100;
	var windowHeight = $(window).height() - 300;
	//var today = $.datepicker.formatDate('dd-M-yy', new Date());

	// alert($('[name="isIdRequired"]')[0].value);

	if (mode == "show") {

		$("#agsCounterNoId").validatebox({
			required : false,
			validType : null
		});
		$("#agsNoId").validatebox({
			required : false,
			validType : null
		});

		$("#divCatGroupArogyaShreeBeneficiaryId").hide("blind");
		$("#divCatGroupBeneficiaryId").show("blind");
		
		

		/*
		 * $("#divCatGroupBeneficiaryId").dialog({ height: windowHeight, width:
		 * windowWidth, modal: false, resizable: false, show: { effect: "clip"},
		 * buttons: { "Ok!": function() { alert("Inside Ok");
		 * $(this).dialog("close");
		 * $('#creditLetterRefNoId').val($('#creditLetterRefNoId').val()); },
		 * "Cancel": function() { $(this).dialog("close"); } } });
		 */

		/*$("#creditLetterDateId").datepicker({
			dateFormat : 'dd-M-yy',
			onSelect: function(d,i){
		          if(d !== i.lastVal){
		              $(this).change();
		          }
		     }
		}).datepicker("setDate", new Date());*/
		//var today=new Date().toLocaleFormat('%d-%b-%Y');
		var today = moment().format('DD-MMM-YYYY');
		//var _todayPlus30Days=new Date();
		//_todayPlus30Days.setDate(_todayPlus30Days.getDate() + 30);
		var _todayPlus30Days = moment().add(30, 'days').format('DD-MMM-YYYY');//moment(today, 'DD-MMM-YYYY').add(30, 'days');//_todayPlus30Days.toLocaleFormat('%d-%b-%Y');
		$('#creditLetterDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		$("#creditLetterDateId").change(function(){
		    /*var date2 = $('#creditLetterDateId').datepicker('getDate');
			date2.setDate(date2.getDate()+30); 
			$("#cardvalidityDateId").datepicker({ dateFormat: "dd-M-yy" }).datepicker("setDate", new Date(date2));	*/
			var _dateStr=$(this).val().replace(/-/g,' ');
			//alert(_dateStr);
			var _date = new Date(_dateStr);
			_date.setDate(_date.getDate() + 30);
			//alert(_date);
			var wrappedDate = moment(_date);
			_date=wrappedDate.format('DD-MMM-YYYY');
			$('#cardvalidityDateId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_date,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_date);
			
		});
		/*$("#cardvalidityDateId").datepicker({
			dateFormat : 'dd-M-yy'
		}).datepicker("setDate", "30");*/
		$('#cardvalidityDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:_todayPlus30Days,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(_todayPlus30Days);
		$("#creditBillFlagId").val("1");
		$("#creditLetterRefNoId").validatebox({
			required : true,
			/*validType : ['alphaNumSpecialChar','startZero']*/
			validType : ['alphaNumSpecialChar','NotAllZeroWithSpclChar','consecutiveSpecialCharOnly']
		});
		$('[name="clientCode"]').validatebox({
			validType: ['selectCombo[-1]']
		});
		
		$("#creditLetterDateId")
				.validatebox(
						{
							validType : ['dtltetctdt[\'It should not be grater than Current Date.\']','dtNotGtGvnNoDay[\''+today+'\',\'30\',\'Letter Date must be within 30 days\']']
						});
		$("#cardvalidityDateId")
				.validatebox(
						{
							validType : 'dtgtetctdt[\'It should not be lesser than Current Date.\']'
						});

		$("#staffCardNoId").validatebox({
			required : false,
			/*validType : 'alphaNumeric'*/
			validType : ['alphaNumeric','NotAllZero']
		});
		$("#staffCardNameId").validatebox({
			required : false,
			validType : 'alphaWithSpace'
		});

		$("#agsCounterNoId").val("");
		$("#agsNoId").val("");

		$('[name="clientCode"]')[0].value = clientCode;
		$("#clientNameLabel").html(clientName);
		$('[name="clientName"]')[0].value = clientName;
		$('[name="agsDistrictCode"]')[0].value = "";

	} else if (mode == "showAGS") {
		$("#creditBillFlagId").val("0");
		$("#creditLetterRefNoId").val("");
		$("#creditLetterDateId").val("");
		$("#creditLetterRefNoId").validatebox({
			required : false
		});
		$("#creditLetterDateId").validatebox({
			validType : null
		});
		$("#cardvalidityDateId").validatebox({
			validType : null
		});
		$("#staffCardNoId").validatebox({
			required : false,
			validType : null
		});
		$("#staffCardNameId").validatebox({
			validType : null
		});

		$("#divCatGroupBeneficiaryId").hide("blind");

		$("#divCatGroupArogyaShreeBeneficiaryId").show("blind");
		$("#agsCounterNoId").validatebox({
			required : false,
			validType : 'alphaNumeric'
		});
		$("#agsNoId").validatebox({
			required : true,
			validType : ['alphaNumeric','NotAllZero']
		});

		$("#clientCodeId").val("");
		$("#staffCardNoId").val("");
		$("#staffCardNameId").val("");
		$("#relationWithStaffId").val("-1");
		$("#cardvalidityDateId").val("");
		$("#clientNameLabel").html("");
		$('[name="clientName"]')[0].value = "";
		$('[name="agsDistrictCode"]')[0].value = $('[name="defaultpatAddDistrictCode"]')[0].value;

	} else {

		$("#creditBillFlagId").val("0");
		$("#creditLetterRefNoId").val("");
		$("#creditLetterDateId").val("");
		$("#creditLetterRefNoId").validatebox({
			required : false
		});
		$("#creditLetterDateId").validatebox({
			validType : null
		});
		$("#cardvalidityDateId").validatebox({
			validType : null
		});
		$("#staffCardNoId").validatebox({
			required : false,
			validType : null
		});
		$("#staffCardNameId").validatebox({
			validType : null
		});

		$("#agsCounterNoId").validatebox({
			required : false,
			validType : null
		});
		$("#agsNoId").validatebox({
			required : false,
			validType : null
		});

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
		$('[name="clientName"]')[0].value = "";
		$('[name="agsDistrictCode"]')[0].value = "";

	}
}

function setCompany() {
	// alert($("#clientCodeId option:selected").html());
	$('[name="clientName"]')[0].value = $("#clientCodeId option:selected")
			.html();
}

function setRelation() {
	// alert($("#relationWithStaffId option:selected").html());
	var tempStaffName = $('[name="staffCardName"]')[0].value;
	$('[name="relationNameWithStaff"]')[0].value = $(
			"#relationWithStaffId option:selected").html();
	$('[name="patFirstName"]')[0].value = "";
	$('[name="patFirstNameInMultiLang"]')[0].value = "";
	$('[name="patGuardianName"]')[0].value = "";
	$('[name="patMotherName"]')[0].value = "";

	if ($('[name="relationNameWithStaff"]')[0].value == "Self"
			|| $('[name="relationNameWithStaff"]')[0].value == "Father"
			|| $('[name="relationNameWithStaff"]')[0].value == "Mother") {
		$('[name="staffCardName"]')[0].value = tempStaffName;
		setPatNameSelf();
	}
}

function setPatNameSelf() {
	// alert($('[name="relationNameWithStaff"]')[0].value);
	if ($('[name="relationNameWithStaff"]')[0].value == "Self") {
		$('[name="patFirstName"]')[0].value = $('[name="staffCardName"]')[0].value;
		multilingualConversion($('[name="patFirstName"]')[0], document
				.getElementById('patFirstNameInMultiLangId'));
	}
	if ($('[name="relationNameWithStaff"]')[0].value == "Father") {
		$('[name="patGuardianName"]')[0].value = $('[name="staffCardName"]')[0].value;
	}
	if ($('[name="relationNameWithStaff"]')[0].value == "Mother") {
		$('[name="patMotherName"]')[0].value = $('[name="staffCardName"]')[0].value;
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

//Added by Vasu on 23.06.2018 for patient image upload 
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

function show(e)
{
	var processID = document.getElementsByName("processID")[0].value;
	var crNo = document.getElementsByName("crNo")[0].value;
	var filname = document.getElementsByName("filname")[0].value;
	//var url = "../../registration/transactions/viewImageFromMongoDBEnlargedImage.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname;
	var url = "../../registration/transactions/EnlargedImage.action"; 
	openURLInPopup(url,400,400,0,0);
}
