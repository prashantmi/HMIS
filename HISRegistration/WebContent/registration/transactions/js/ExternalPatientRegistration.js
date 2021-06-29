var focusedElement;
$(document)
		.ready(
				function() {

					$(document)
							.ajaxStart(
									function() {
										// console.log("inside
										// opdNewPatientRegistration.js -->>
										// ajaxStart()");
										// focusedElement =
										// document.activeElement;
										// var $focused = $(':focus');
										// focusedElement.blur();
										// $('.overlay1').show();
										
										/*alert(parent);
										if (parent.frameElement != null
												&& parent.frameElement != undefined
												&& parent.frameElement.id == "frmMain") {
											parent.parent.ajaxStart();
										} else {
											parent.ajaxStart();
										}*/
									});
					$(document)
							.ajaxComplete(
									function() {
										// console.log("inside
										// opdNewPatientRegistration.js -->>
										// ajaxComplete()");

										// $('.overlay1').hide();
										// focusedElement.focus();
										if (parent.frameElement != null
												&& parent.frameElement != undefined
												&& parent.frameElement.id == "frmMain") {
											parent.parent.ajaxComplete();
										} else {
											parent.ajaxComplete();
										}
									});

					if ($("#flagHasActionErrorId").val() == 'false') {
						/*$('[name="departmentCode"]').validatebox({
							validType : [ 'selectCombo[-1]' ]
						});
						$('[name="departmentCode"]')[0].focus();*/

						opdregistration.fetchDefaultValues();

						$("#patAddStateCodeId").bind("change",
								opdregistration.onchange_patAddStateCode);

						// Validation]

						$("#patPrimaryCatCodeId").validatebox({
							validType : [ 'selectCombo[-1]' ]
						});
						$('[name="patFirstName"]').validatebox({
							required : true,
							validType : 'alphaWithSpace'
						});
						$('[name="patMiddleName"]').validatebox({
							validType : 'alphaWithSpace'
						});
						$('[name="patLastName"]').validatebox({
							validType : 'alphaWithSpace'
						});
						$('[name="patGenderCode"]').validatebox({
							validType : [ 'selectCombo[-1]' ]					
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

						$('[name="patAddCity"]').validatebox({
							validType : 'alphaNumSpecialChar'
						});

						$('[name="patAddPIN"]').validatebox({
							//required : true,
							validType : [ 'numeric', 'equalLength[6]']
						});
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
							validType : [ 'numeric', 'equalLength[10]']
						});

						$('[name="patMonthlyIncome"]').validatebox({
							validType : 'numericNew'
						});

						$('[name="patNationalId"]').validatebox({
							validType : [ 'numeric','equalLength[12]']
						});
					}
					
					$(document).scroll(function(e){

				        if ($(".ui-widget-overlay")) //the dialog has popped up in modal view
				        {
				            //get the current popup position of the dialog box
				            var pos = $(".ui-dialog").position();

				            //adjust the dialog box so that it scrolls as you scroll the page
				            $(".ui-dialog").css({
				                position: 'fixed',
				                top: pos.y
				            });
				        }
				    });
				});

var departmentJSONArray = [];
var categoryJSONArray = [];
var veriyDocTempJSONArray = [];
var veriyDocJSONArray = [];
var defaultValJSONArray = [];
var ageBoundRange = 125;
var docValidType = 'alphaNumSpecialChar';
var catIdValidType = 'alphaNumSpecialChar';
//var ageValidType = "selectComboNotSpecifiedVal['-1',1,'patGenderCode','-1','Kindly Select Department First']";
var clientCode, clientName = "";
var temprtnData, isDuplicatePatientPopup = "";

var opdregistration = {

	fetchDefaultValues : function() {
		var action = "/HISRegistration/registration/transactions/populateformvaluesExternalPatientRegistration.action";

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
							} /*else if (elementName == 'departmentCode') {
								opdregistration.processDepartmentNode(
										elementName, elementNode);
							}*/ else if (elementName == 'patPrimaryCatCode') {
								opdregistration.processCategoryNode(
										elementName, elementNode);
							} else if (elementName == 'patDocType') {
								opdregistration.processVerifyDocNode(
										elementName, elementNode);
							} /*else if (elementName == 'renewalConfig') {
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

								
								 * else{ $("#divAlreadyRegisteredId").hide(); }
								 
							}*/ else if (elementName == 'defaults') {

							} else {
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

					},
					error : function(errorMsg, textstatus, errorthrown) {
						alert('fetchDefaultValues ' + errorMsg
								+ " textstatus::" + textstatus
								+ " errorthrown::" + errorthrown);

					}

				});
		showDivAgeDob();

	},
	/*processDepartmentNode : function(elementName, elementNode) {
		var optionText = "<option value='-1'>Select Value</option>";
		for ( var i = 0; i < elementNode.childNodes.length; i++) {
			var optionNode = elementNode.childNodes[i];
			// alert("JSONStr"+optionNode.getAttribute("value"));
			var departmentJSONObject = jQuery.parseJSON(optionNode
					.getAttribute("value"));
			departmentJSONArray[departmentJSONArray.length] = departmentJSONObject;
			optionText += "<option value='"
					+ departmentJSONObject.departmentCode + "'>"
					+ (departmentJSONObject.departmentName) + "</option>";

		}
		// alert("processDepartmentNode::optionText :"+optionText);
		if (document.getElementsByName(elementName).length != 0)
			document.getElementsByName(elementName)[0].innerHTML = optionText;
	},*/
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
		if (document.getElementsByName(elementName).length != 0)
			document.getElementsByName(elementName)[0].innerHTML = optionText;
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
	/*setDepartmentDependents : function(deptObj) {
		var deptCode = deptObj.options[deptObj.selectedIndex].value;
		if (deptCode == "-1") {
			$('[name="defaultpatGenderCode"]')[0].value = "0";
			ageValidType = "selectComboNotSpecifiedVal['-1',1,'patGenderCode','-1','Kindly Select Department First']";
			return;
		}
		for ( var i = 0; i < departmentJSONArray.length; i++) {
			if (departmentJSONArray[i].departmentCode == deptCode) {

				$('[name="patGenderCode"]')[0].value = departmentJSONArray[i].defaultGenderCode;
				$('[name="defaultpatGenderCode"]')[0].value = departmentJSONArray[i].defaultGenderCode;
				$('[name="departmentUnitCode"]')[0].value = departmentJSONArray[i].deptUnitCode;

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
						validType : [ 'numeric', ageRangeValidType ]
					});
					$("#patDOBId").validatebox({
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
					$("#patDOBId").validatebox(
							{
								required : true,
								validType : [
										'date[\'patDOB\',\'dd-mmm-yyyy\']',
										dobValidType ]
							});
				}
				break;
			}
		}
	},*/
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
			}*/
		}
		$("#patAddCountryCodeId").bind("change",
				opdregistration.onchange_patAddCountryCode);

	},
	onchange_patAddCountryCode : function() {
		var objCountry = document.getElementsByName("patAddCountryCode")[0];
		var countryCode = objCountry.options[objCountry.selectedIndex].value;
		var action = "/HISRegistration/registration/transactions/getStateExternalPatientRegistration.action?countryCode="
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
		} else {
			$("#divStateTextId").hide();
			$("#divStateComboId").show();
			$("#patAddStateCodeId").html(
					"<option value='-1'>Select Value</option>");
			$("#divDistrictTextId").hide();
			$("#divDistrictComboId").show();
			$("patAddDistrictCodeId").html(
					"<option value='-1'>Select Value</option>");
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
		var action = "/HISRegistration/registration/transactions/getDistrictExternalPatientRegistration.action?countryCode="
				+ countryCode + "&stateCode=" + stateCode;

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
		var action = "/HISRegistration/registration/transactions/getRefDeptExternalPatientRegistration.action?refHospCode="
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
	/*onchange_patPrimaryCategory : function() {

		var PrimaryCatObj = $('[name="patPrimaryCatCode"]')[0];
		var PrimaryCatCode = PrimaryCatObj.options[PrimaryCatObj.selectedIndex].value;
		if (PrimaryCatCode == "-1") {
			$("#divCatCardId").hide();
			$("#divImgCatCardId").hide();
			$("#divSpareCatId").show();

			showHideCatGroupBeneficiaryTile("hide");
		}

		for ( var i = 0; i < categoryJSONArray.length; i++) {
			if (categoryJSONArray[i].patPrimaryCatCode == PrimaryCatCode) {

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
						$('[name="patActualAmount"]')[0].value = 0;
					} else {
						$('[name="patAmountCollected"]')[0].value = categoryJSONArray[i].charges;
						$('[name="patActualAmount"]')[0].value = categoryJSONArray[i].charges;
					}
				} else {

					if (categoryJSONArray[i].charges == "-1") {
						$('[name="patAmountCollected"]')[0].value = 0;
						$('[name="patActualAmount"]')[0].value = 0;
					} else {
						$('[name="patAmountCollected"]')[0].value = 0;
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
					$("#divSpareCatId").hide("blind");
					if ($('[name="alreadyRegisteredFromRenewalConfig"]')[0].value != ""
							&& $('[name="alreadyRegisteredFromRenewalConfig"]')[0].value != "0") {
						$("#divAlreadyRegisteredId").show("blind");
					}
					var docValidTypeNLength = 'equalLength['
							+ categoryJSONArray[i].idSize + ']';
					$("#shownPatIdNoId").validatebox({
						required : true,
						validType : [ catIdValidType, docValidTypeNLength ]
					});

					if ($("#isMandataryReadOnlyTrueId").val() == "1")
						setMandatoryReadOnlyTrueFalse(false);
				} else if (categoryJSONArray[i].idReqFlag == "2") {
					$("#shownPatIdNoId").validatebox({
						required : false,
						validType : null
					});
					$("#divCatCardId").hide("blind");
					$("#divSpareCatId").hide("blind");
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
						var action = "/HISRegistration/registration/transactions/getVerDocExceptCatDocNewRegistration.action?strDocCode="
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
					var action = "/HISRegistration/registration/transactions/getVerDocExceptCatDocNewRegistration.action?strDocCode="
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

	},*/
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
					validType : [ docValidType, docValidTypeNLength ]
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
		// alert("inside populatePatientDtl");

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
	populatePatientDtl : function(patientJSONObject) {
		// alert("inside populatePatientDtl");

		for ( var patientKey in patientJSONObject) {
			if (patientJSONObject.hasOwnProperty(patientKey)) {
				var patientVal = patientJSONObject[patientKey];
				// alert("patientKey :"+patientKey+", patientVal :"+patientVal);
				if (document.getElementsByName(patientKey)
						&& document.getElementsByName(patientKey)[0] != undefined) {
					eval("var " + patientKey + "='" + patientVal + "'");
					document.getElementsByName(patientKey)[0].value = eval(patientKey);
					/*if (patientKey == "patPrimaryCatCode")
						opdregistration.onchange_patPrimaryCategory();*/
					// document.getElementsByName(patientKey)[0].disabled=true;
				}

			}
		}
		// setMandatoryRefdReadOnlyTrueFalse(false);
		setMandatoryReadOnlyTrueFalse(false);

	},
	fetchPatDtlBasedOnPatCatCardNo : function(patientJSONObject) {
		// alert("inside fetchPatDtlBasedOnPatCatCardNo");
		opdregistration.populatePatientDtl(patientJSONObject);
		/*
		 * if($('[name="patDOB"]')[0].value!="")
		 * $('[name="isActualDob"]')[1].checked=true;
		 */

	},
	savePatientDtl : function() {

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
		//alert($('[name="patFirstName"]')[0].value);
		// return;
		// return false;
		 $('[name="patPrimaryCat"]')[0].value= $(
				 "#patPrimaryCatCodeId option:selected").text();
		// For Submission
		 sortandEncodebase64($("#ExternalPatientRegistration"));
		var action = "/HISRegistration/registration/transactions/saveExternalPatientRegistration.action";
		$('#submitId').hide();
		$.ajax({
			url : action,
			type : "POST",
			async : true,
			dataType : "xml",
			data : $("#ExternalPatientRegistration").serialize(),
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

		
		var action = "/HISRegistration/registration/transactions/saveAsNewPatientExternalPatientRegistration.action";
		//$('#submitId').hide();
		$.ajax({
			url : action,
			type : "POST",
			async : true,
			dataType : "xml",
			data : $("#ExternalPatientRegistration").serialize(),
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
				
			//	$('#submitId').show("blind");

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

		// alert("isSavedSuccussful :"+isSavedSuccussful);
		if (isSavedSuccussful == "1") {
			$("#divNormalMsgId").html(msg);
			$("#divErrorMsgId").html("");
			if (isPrintFlag == "1") {
				$("#divPrintId").html(printDivContent);
				get_object("divBarCodeControl").innerHTML = DrawCode39Barcode(
						get_object("divBarCodeControl").innerHTML, 0);

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

			if (isFormFieldTobeReset == "1") {
				// if(confirm(msg+"\nDo you Want To Reset The Form"))
				opdregistration.setFormFieldsAfterSave();
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
			}

		}

	},
	setFormFieldsAfterSave : function() {
		$('#fatherorSpouseError').hide();
		$('.validatebox-text').removeClass('validatebox-invalid');
		// $('.validatebox-text').removeClass('validatebox-invalid');

		$('[name="patPrimaryCat"]')[0].value = "";
	//	$('[name="departmentCode"]')[0].value = "-1";
		$('[name="patIdNo"]')[0].value = "";
		$('[name="hiddenPatIdNo"]')[0].value = "";
		//$('[name="alreadyRegisteredFlag"]')[0].checked = false;
		$("#hiddenCatOrRegstrdPopupFlagId").val("");
		// $("#divCatCardId").hide();

		$('[name="patCatShortName"]')[0].value = "";
		$('[name="patPrimaryCatCode"]')[0].value = "-1";
	//	opdregistration.onchange_patPrimaryCategory();
		$('[name="patFirstName"]')[0].value = "";
		$('[name="patMiddleName"]')[0].value = "";
		$('[name="patLastName"]')[0].value = "";

		$('[name="patFirstNameInMultiLang"]')[0].value = "";
		$('[name="patMiddleNameInMultiLang"]')[0].value = "";
		$('[name="patMiddleNameInMultiLang"]')[0].value = "";

		// $('.resetTextBox').val("");

		$('[name="isActualDob"]')[0].checked = true;// if (age provided)
													// isActualDob=0 else
													// isActualDob=1
		showDivAgeDob();
		$('[name="patAge"]')[0].value = "";
		$('[name="patAgeUnit"]')[0].value = "Yr";
		$('[name="patDOB"]')[0].value = "";
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
		$('[name="patAmountCollected"]')[0].value = "";

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
		$('[name="patAddMobileNo"]')[0].value = "";
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
	},
	showDuplicatePatientPopup : function(rtnData) {

		var windowWidth = $(window).width() - 100;
		var windowHeight = 185;

		var page="/HISRegistration/registration/transactions/jsp/opdNewRegDuplicatePopup.jsp";

		var $dialog = $('<div></div>').html(
				'<iframe style="border: 0px; " src="' + page
						+ '" width="100%" height="100%"></iframe>').dialog({
			autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
			title : "Patient Detail Exists",	show: { effect: "clip"},resizable: false,
			position: ['top', 100],dialogClass: 'no-close custbtncolor',
			buttons:{
				"Save As New Patient": function() {
					  $(this).dialog("close"); 
					  opdregistration.saveAsNewPatientDtl();
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
	}
};

$("#patDocTypeId").change(function() {
	opdregistration.onchange_patDocType();
});

/*$("#patPrimaryCatCodeId").change(function() {
	opdregistration.onchange_patPrimaryCategory();
});*/

function closeModelFunction(popupmodal, func) {
	// alert("closeFunction");
	popupmodal.close = eval(func);
	// $.modal.destroy();
}

/*$("#imgCatCardId")
		.click(
				function() {
					var patPrimarCatId = $("#patPrimaryCatCodeId").val();
					var action = "/HISRegistration/registration/transactions/openPatPopupNewRegistration.action?patPrimarCatId="
							+ patPrimarCatId + "&alreadyRegisteredFlag=0";
					openURLInPopupWithoutClose(action, '600', '200');
					// openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');

				});*/

/*$("#alreadyRegisteredFlagId")
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
					var action = "/HISRegistration/registration/transactions/openPatPopupNewRegistration.action?patPrimarCatId="
							+ patPrimarCatId
							+ "&alreadyRegisteredFlag="
							+ alreadyRegisteredFlag;
					openURLInPopupWithoutClose(action, '600', '200');
					// openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');
				});*/

// Submit form if valid
$('#submitId').click(function(e) {
	// alert("inside submitClick");
	setMotherValidRule();
	//fatherorSpouseerror();
	fatherorSpouse();
	if ($('#ExternalPatientRegistration').form('validate')) {
		$('#submitId').hide();
		opdregistration.savePatientDtl();
		$('#submitId').show("blind");
	} else {
		// alert("Validation false");
		return false;
	}
	e.preventDefault();
});

// Clear Form Data
$('#clearId').click(function(e) {
//	$('[name="departmentCode"]')[0].focus();
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
				validType : [ 'numeric', ageRangeValidType ]
			});
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
	// alert("inside setMotherValidRule()");
	if ($('[name="isActualDob"]')[0].checked
			&& $('[name="patAgeUnit"]')[0].value != "-1") {
		if ($("#patAgeId").val() < calculateMaxRangeValue(12,
				$("#patAgeUnitId").val())) {
			flagYes = true;
		}
	} else if ($('[name="patDOB"]')[0].value != ""
			&& isDate($('[name="patDOB"]')[0], "dd-mmm-yyyy")) {
		var d1 = $.datepicker.parseDate("dd-M-yy", $("#patDOBId").val());
		var ctDt = new Date();
		var yr = d1.getFullYear();
		var ctDtYr = ctDt.getFullYear();
		if ((ctDtYr - 12) <= yr) {
			flagYes = true;
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
			validType : [ 'numeric', ageRangeValidType ]
		});
		$("#patAgeUnitId").validatebox({
			required : true,
			validType : [ 'selectCombo[-1]' ]
		});
		$("#patDOBId").validatebox({
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
			/*$("#patDOBId").datepicker({
				dateFormat : 'dd-M-yy'
			}).datepicker("setDate", new Date());*/
			var today=new Date().toLocaleFormat('%d-%b-%Y');
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(today);

		});
		// $("#patDOBId").validatebox({required: true, validType:
		// 'dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date.
		// Please Select Valid Date Of Birth \']'});

		var dobValidType = 'dtltetDMY[' + ageBoundRange + ',\'y\']';
		$("#patDOBId")
				.validatebox(
						{
							required : true,
							validType : [
									'date[\'patDOB\',\'dd-mmm-yyyy\']',
									'dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth \']',
									dobValidType ]
						});
	}

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
		$("#patDOBId").prop('readOnly', flagTrueFalse);
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
			validType : 'numeric'
		});
		$('[name="patRefDoctor"]').validatebox({
			required : true,
			validType : 'alpha'
		});
		$('[name="patRefGnctdHospitalDeptUnit"]').validatebox({
			validType : 'alphaNumeric'
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
				validType : 'alphaWithSpace'
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
			dateFormat : 'dd-M-yy'
		}).datepicker("setDate", new Date());
		$("#cardvalidityDateId").datepicker({
			dateFormat : 'dd-M-yy'
		}).datepicker("setDate", new Date());*/
		var today=new Date().toLocaleFormat('%d-%b-%Y');
		$('#creditLetterDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		$('#cardvalidityDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		
		$("#creditBillFlagId").val("1");
		$("#creditLetterRefNoId").validatebox({
			required : true,
			validType : 'alphaNumSpecialChar'
		});
		$("#creditLetterDateId")
				.validatebox(
						{
							validType : 'dtltetctdt[\'It should not be grater than Current Date.\']'
						});
		$("#cardvalidityDateId")
				.validatebox(
						{
							validType : 'dtgtetctdt[\'It should not be lesser than Current Date.\']'
						});

		$("#staffCardNoId").validatebox({
			required : true,
			validType : 'alphaNumeric'
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
			validType : 'numeric'
		});
		$("#agsNoId").validatebox({
			required : true,
			validType : 'alphaNumeric'
		});

		$("#clientCodeId").val("-1");
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
		$("#clientCodeId").val("-1");
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
