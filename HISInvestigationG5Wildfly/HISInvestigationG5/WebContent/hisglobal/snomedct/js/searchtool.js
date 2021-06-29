/*******************************************************************************
 * Copyright 2014 Centre for Development of Advanced Computing(C-DAC), Pune
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
var index = 0;
var jsonData;
var currentCountDisplayed;
var displaySearch;
var h = 80;
var dialogFormHTML;
var divDialogForm;
var txtId;
var selectedConceptId;
var stateParam;
var semantictagParam;
var acceptabilityParam;
var returnlimitParam;

var enumSERVICES = {
	SEARCH : {
		type : "search",
		suggestbyacceptability_url : "http://10.226.2.97:8080/csnoserv/api/search/suggestbyacceptability",
		searchbyacceptability_url : "http://10.226.2.97:8080/csnoserv/api/search/searchbyacceptability",
	},
};

var enumACCEPTABILITY = {
	ALL : "all",
	PREFERRED : "preferred",
	PREFERRED_EXCLUDING_FSN : "preferredexcludingfsn",
	SYNONYMS : "synonyms",
	ACCEPTABLE : "acceptable"
};
var enumSTATE = {
	BOTH : "both",
	ACTIVE : "active",
	INACTIVE : "inactive"
};

var enumSEMANTICTAG = {

	ALL : "all",
	PROCEDURE : "procedure",
	DISORDER : "disorder",
	FINDING : "finding",
	OBSERVABLE_ENTITY : "observable entity",
	BODY_STRUCTURE : "body structure",
	ORGANISM : "organism",
	SUBSTANCE : "substance",
	SPECIMEN : "specimen",
	SPECIAL_CONCEPT : "special concept",
	LINKAGE_CONCEPT : "linkage concept",
	PHYSICAL_FORCE : "physical force",
	EVENT : "event",
	ENVIRONMENT : "environment",
	GEOGRAPHIC_LOCATION : "geographic location",
	SOCIAL_CONCEPT : "social concept",
	SITUATION_WITH_EXPLICIT_CONTEXT : "situation",
	STAGING_SCALE : "staging scale",
	PHYSICAL_OBJECT : "physical object",
	QUALIFIER_VALUE : "qualifier value",
	RECORD_ARTIFACT : "record artifact",
	PERSON : "person",
	LINK_ASSERTION : "link assertion",
	NAMESPACE_CONCEPT : "namespace concept",
	ATTRIBUTE : "attribute",
	ASSESSMENT_SCALE : "assessment scale",
	RACIAL_GROUP : "racial group",
	TUMOR_STAGING : "tumor staging",
	OCCUPATION : "occupation",
	MORPHOLOGIC_ABNORMALITY : "morphologic abnormality",
	CELL : "cell",
	CELL_STRUCTURE : "cell structure",
	ETHNIC_GROUP : "ethnic group",
	PRODUCT : "product",
	INACTIVE_CONCEPT : "inactive concept",
	NAVIGATIONAL_CONCEPT : "navigational concept",
	LIFE_STYLE : "life style",
	REGIME_THERAPY : "regime/therapy",
	RELIGION_PHILOSOPHY : "religion/philosophy"
};

var enumLANGREFSET={
	UK: "uk",
	US: "us",
};
var enumRELATIONSHIP={
	IS_A: "is a",
	FINDING_SITE:"finding site",
	CAUSATIVE_AGENT:"causative agent",
	HAS_INTENT:"has intent",
	PROCEDURE_SITE:"procedure site",
	SPECIMEN_SUBSTANCE:"specimen substance",
	ASSOCIATED_FINDING:"associated finding",
	ASSOCIATED_WITH:"associated with",
	ASSOCIATED_MORPHOLOGY:"associated morphology",
	HAS_ACTIVE_INGREDIENT:"has active ingredient",
	PROCEDURE_SITE_DIRECT:"procedure site direct",
	SPECIMEN_SOURCE_TOPOGRAPHY:"specimen source topography",
	HAS_FOCUS:"has focus",
	HAS_MEASURED_COMPONENT:"has measured component",
	COMPONENT:"component",
	PROCEDURE_SITE_INDIRECT:"procedure site indirect",
	USING:"using",
	DIRECT_DEVICE:"direct device",
	USING_DEVICE:"using device",
	SPECIMEN_SOURCE_MORPHOLOGY:"specimen source morphology",
	DIRECT_MORPHOLOGY:"direct morphology ",
	INDIRECT_MORPHOLOGY:"indirect morphology ",
	PART_OF:"part of ",
	DUE_TO:"due to ",
	RECIPIENT_CATEGORY:"recipient category ",
	SUBJECT_RELATIONSHIP_CONTEXT:"subject relationship context ",
	DIRECT_SUBSTANCE:"direct substance ",
	USING_SUBSTANCE:"using substance ",
	INTERPRETS:"interprets ",
	SPECIMEN_PROCEDURE:"specimen procedure ",
	HAS_DEFINITIONAL_MANIFESTATION:"has definitional manifestation ",
	ASSOCIATED_ETIOLOGIC_FINDING:"associated etiologic finding ",
	HAS_INTERPRETATION:"has interpretation ",
	AFTER:"after ",
	METHOD:"method ",
	TEMPORALLY_FOLLOWS:"temporally follows ",
	ASSOCIATED_PROCEDURE:"associated procedure ",
	ASSOCIATED_FUNCTION:"associated function ",
	PROCEDURE_DEVICE:"procedure device ",
	REVISION_STATUS:"revision status ",
	MEASUREMENT_METHOD:"measurement method ",
	OCCURRENCE:"occurrence ",
	MEASURES:"measures ",
	PROCEDURE_MORPHOLOGY:"procedure morphology ",
	FINDING_METHOD:"finding method ",
	INDIRECT_DEVICE:"indirect device ",
	ROUTE_OF_ADMINISTRATION:"route of administration ",
	USING_ACCESS_DEVICE:"using access device ",
	TEMPORAL_CONTEXT:"temporal context ",
	APPROACH:"approach ",
	SURGICAL_APPROACH:"surgical approach ",
	SEVERITY:"severity ",
	COURSE:"course ",
	CLINICAL_COURSE:"clinical course ",
	LATERALITY:"laterality ",
	HAS_DOSE_FORM:"has dose form ",
	SPECIMEN_SOURCE_IDENTITY:"specimen source identity ",
	ACCESS_INSTRUMENT:"access instrument ",
	STAGE:"stage ",
	USING_ENERGY:"using energy ",
	ONSET:"onset ",
	INSTRUMENTATION:"instrumentation ",
	PRIORITY:"priority  ",
	EXTENT:"extent ",
	SCALE_TYPE:"scale type ",
	SUBJECT_OF_INFORMATION:"subject of information ",
	FINDING_CONTEXT:"finding context ",
	HAS_SPECIMEN:"has specimen ",
	ACCESS:"access ",
	PROPERTY:"property ",
	TIME_ASPECT:"time aspect ",
	EPISODICITY:"episodicity ",
	LOCATION:"location ",
	PATHOLOGICAL_PROCESS:"pathological process ",
	COMMUNICATION_WITH_WOUND:"communication with wound ",
	PROCEDURE_CONTEXT:"procedure context ",
	FINDING_INFORMER:"finding informer"
};

$(document)
		.ready(
				function() {

					selectedConceptId = "";
					dialogFormHTML = '<div id="snomed-ct-search"><input type="text" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search" class="searchText" name="txt-snomed-ct-search" style="width:95%;color:#000000;"></input><input type="image" name="submit" src="/HISInvestigationG5/hisglobal/snomedct/images/search.png" id="srcImg" alt="Search"  title="Search" style="height: 20px; width: 20px;float:right;cursor:pointer;position:relative;top:3px;padding-left:5px;display:block;"></div>';
					dialogFormHTML += '<label id="reccnt">No. of records : </label><span id="reccount" vertical-align:top; ></span><label id="nosuggestion">No suggestions found</label><label id="norec">No results found</label><label id="msg3chars">Please enter atleast 3 characters</label>';
					dialogFormHTML += '<div id="licenseBox"></div>';
					divDialogForm = '<div id="dialog-form" style="width: 600px;">'
							+ dialogFormHTML + '</div>';

					$('body').append(divDialogForm);

					dialogData = '';
					dialogHTML = '<div id="dialog-message" title="CSNOPlugin Information"><p>'
							+ dialogData + '</p></div>';
					$('body').append(dialogHTML);

					var dialog = $("#dialog-form").dialog({

						autoOpen : false,
						height : 160,
						width : 600,
						modal : true,
						buttons : {

							"." : function(event) {

							}
						}

					}

					);

					dialog.data("uiDialog")._title = function(title) {
						title.html(this.options.title);

					};

					if (document.getElementById("conceptdiv"))
						$("#conceptdiv").remove();

					dialog
							.dialog(
									'option',
									'title',
									'<img style="width:63px;height:20%;vertical-align: text-bottom;padding-right:34em;" src="/HISInvestigationG5/hisglobal/snomedct/css/images/CSNOCtrl.png" style="position:relative;right:0px;top:0px;"/><img align="right" style="position:relative;vertical-align: text-bottom; height: 20%; width: 36px; top: 0px;" src="/HISInvestigationG5/hisglobal/snomedct/css/images/CDACLogo.png"/> ');

				});

/*
 * loadResultsList() function loads the matching records for the term searched
 * in SNOMEDCT repository. The records contains the Concept-id,Description and
 * Description-id The result will display the concept-id, description-id and
 * description of the selected concept from the result list.
 * 
 * Parameter Description: @param state(var)-Defines state of the concept. Refer
 * enumSTATE in searchtool.js to pass values for the state_IN parameter If state
 * is, ACTIVE- It will return all active Concepts with all active Descriptions.
 * INACTIVE- It will return all inactive Concepts with all active and inactive
 * Descriptions. BOTH- It will return all active and inactive Concepts with all
 * active and inactive Descriptions.
 * 
 * @param semantictag(var)-Defines semantic tag in which SNOMED CT term/text will be
 * searched. Refer enumSEMANTICTAG in searchtool.js to pass values for the semantictag_IN
 * parameter. If semantic tag is, ALL- It will search SNOMED CT term in all Semantic Tags
 * 
 * Other examples-'PROCEDURE','DISORDER','BODY_STRUCTURE'
 * 
 * @param acceptability(var)-Represents whether only FSN and Preferred Terms are to
 * be fetched or all matching terms.Refer enumACCEPTABILITY in searchtool.js to
 * pass values for the acceptability_IN parameter. If acceptability is,
 * PREFERRED- Only FSN and Preferred Terms for given term will be fetched 
 * PREFERRED_EXCLUDING_FSN- Only Preferred terms excluding FSN for given term will be fetched
 * ACCEPTABLE- Only acceptable terms shall be fetched
 * SYNONYMS- All terms excluding FSN shall be fetched 
 * ALL- It will fetch all SNOMED CT terms
 * 
 * @param returnlimit(var)-Maximum number of matching terms to be fetched. To get all
 * the hits available, pass '-1'. @param call (var)-It will contain the
 * definition of the callback function used to retrieve return value/result from
 * the selectSNOMEDCT function. The result includes Concept-id,Description-id
 * and Description of the selected concept from SNOMED CT repository. User will
 * have to define a callback function in your HTML page.
 */

function loadResultsList(state, semantictag, acceptability, returnlimit, call) {
	$('#reccnt').show();
	$('#reccount').show();
	$('#norec').hide();
	$('#msg3chars').hide();
	$('#nosuggestion').hide();

	if (document.getElementById("conceptdiv"))
		$("#conceptdiv").remove();

	if (displaySearch == false) {

		if (document.getElementById("conceptdiv"))
			$("#conceptdiv").remove();
		displaySearch = true;
	}

	if (displaySearch == true) {

		var dataValue = document.getElementById("txt-snomed-ct-search").value;

		if (dataValue.trim() == '') {
			$("#dialog-form").dialog("option", "height", 160);
			if (document.getElementById("conceptdiv"))
				$("#conceptdiv").remove();
			$('#reccnt').hide();
			$('#reccount').hide();
			$('#norec').show();
			$('#msg3chars').hide();
			$('#nosuggestion').hide();
			var txtBox = document.getElementById("txt-snomed-ct-search");
			txtBox.focus();
			return;
		}

		var servURL = '';

		servURL = enumSERVICES.SEARCH.searchbyacceptability_url;

		servURL += "?term=" + encodeURIComponent(dataValue) + "&state=" + state
				+ "&semantictag=" + semantictag + "&acceptability=" + acceptability
				+ "&returnlimit=" + returnlimit;

		$
				.ajax({
					type : "GET",
					url : servURL,
					dataType : "jsonp",
					crossDomain : true,
					success : function(data, textStatus, jqXHR) {

						var htmlData = '';
						jsonData = data;
						$('#reccount').text(data.length);
						if (data.length == 0) {
							$("#dialog-form").dialog("option", "height", 160);
							if (document.getElementById("conceptdiv"))
								$("#conceptdiv").remove();
							$('#reccnt').hide();
							$('#reccount').hide();
							$('#norec').show();
							$('#msg3chars').hide();
							$('#nosuggestion').hide();
							var txtBox = document
									.getElementById("txt-snomed-ct-search");
							txtBox.focus();
							return;
						}

						displaySearch = false;

						htmlData = '';
						htmlData += '<div class="concept" id="conceptdiv">';

						htmlData += '<table class="bordered">';
						htmlData += '<thead><tr><th>Description&nbsp;&nbsp;</th></tr></thead>';
						currentCountDisplayed = data.length;
						for (var i = 0; i < data.length; ++i) {
							var val = '\'' + data[i].conceptId + '###$$$'
									+ data[i].term + '###$$$' + data[i].id
									+ '\'';

							htmlData += '<tr title="' + data[i].conceptFsn
									+ '" onclick="selectValue(\'' + escape(val)
									+ '\',' + call + ');"><td>' + data[i].term
									+ '</td></tr>';
						}
						$("#dialog-form").dialog("option", "height", "500");
						htmlData += '</table>';
						htmlData += '</div>';
						if (document.getElementById("conceptdiv"))
							$("#conceptdiv").remove();
						$("#dialog-form").append(htmlData);

					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
	}

}

/*
 * selectSNOMEDCT function provides the search and suggest results for the term
 * to be searched from SNOMEDCT repository with the help of input parameters (
 * state_IN, semantictag_IN, acceptability_IN, returnlimit_IN, callback). The most matching
 * terms from the SNOMEDCT repository are populated and displayed on the page
 * for the searched term. Parameter Description: @param state_IN(var)-Defines
 * state of the concept. Refer enumSTATE in searchtool.js to pass values for the
 * state_IN parameter If state is, ACTIVE- It will return all active Concepts
 * with all active Descriptions. INACTIVE- It will return all inactive Concepts
 * with all active and inactive Descriptions. BOTH- It will return all active and
 * inactive Concepts with all active and inactive Descriptions.
 * 
 * @param semantictag_IN(var)-Defines semantic tag in which SNOMED CT term/text will be
 * searched. Refer enumSEMANTICTAG in searchtool.js to pass values for the semantictag_IN
 * parameter. If semantic tag is, ALL- It will search SNOMED CT term in all semantic tags
 * 
 * Other examples-'PROCEDURE','DISORDER','BODY_STRUCTURE'
 * 
 * @param acceptability_IN(var)-Represents whether only FSN and Preferred Terms are
 * to be fetched or all matching terms.Refer enumACCEPTABILITY in searchtool.js
 * to pass values for the acceptability_IN parameter. If acceptability is,
 * PREFERRED- Only FSN and Preferred Terms for given term will be fetched
 * PREFERRED_EXCLUDING_FSN- Only Preferred terms excluding FSN for given term will be fetched
 * ACCEPTABLE- Only acceptable terms shall be fetched
 * SYNONYMS- All terms excluding FSN shall be fetched 
 * ALL- It will fetch all SNOMED CT terms
 * 
 * @param returnlimit_IN(var)-Maximum number of matching terms to be fetched. To get
 * all the hits available, pass '-1'. @param callback (var)-It will contain the
 * definition of the callback function used to retrieve return value/result from
 * the selectSNOMEDCT function. The result includes Concept-id,Description-id
 * and Description of the selected concept from SNOMED CT repository. User will
 * have to define a callback function in your HTML page like-
 * 
 * var callback =function(selectedSNOMEDTerm){setValue(selectedSNOMEDTerm);};
 * function setValue(selectedSNOMEDTerm) { //selectedSNOMEDTerm contains
 * Concept-id,Description-id and Description. User can manipulate the string or
 * edit the function definition as per his requirement
 * //document.getElementById(HTMLcontrolid).value=selectedSNOMEDTerm; }
 * 
 * The function shall contain only 1 parameter <selectedSNOMEDTerm> that refers
 * to the result including Concept-id,Description-id and Description. User will
 * have to define a function like setValue in the callback function to set the
 * return value <selectedSNOMEDTerm> in some HTML control of the web page. For
 * different HTML controls user will have to define different callback functions
 * and write corresponding <setValue> functions for them.
 * 
 */

function selectSNOMEDCT(state_IN, semantictag_IN, acceptability_IN, returnlimit_IN,
		callback) {

	//alert("hi");
	if (returnlimit_IN <= -1 || returnlimit_IN == '' || returnlimit_IN == undefined
			|| returnlimit_IN == null) {
		returnlimit_IN = -1;

	}
	returnlimitParam = returnlimit_IN;

	if (state_IN == -1 || state_IN == null || state_IN == ''
			|| state_IN == undefined) {
		state_IN = enumSTATE.BOTH;
	} else

		state_IN = enumSTATE[state_IN];

	stateParam = state_IN;

	if (semantictag_IN == -1 || semantictag_IN == null || semantictag_IN == undefined
			|| semantictag_IN == '') {
		semantictag_IN = enumSEMANTICTAG.ALL;
	} else
		semantictag_IN = enumSEMANTICTAG[semantictag_IN];

	semantictagParam = semantictag_IN;

	if (acceptability_IN == -1 || acceptability_IN == null
			|| acceptability_IN == undefined || acceptability_IN == '') {
		acceptability_IN = enumACCEPTABILITY.ALL;
	} else
		acceptability_IN = enumACCEPTABILITY[acceptability_IN];

	acceptabilityParam = acceptability_IN;

	$('#dialog-form').dialog({
		position : 'center'
	});
	$("#dialog-form").click(
			function(e) {
				if (e.target.id == "srcImg") {
					var dataValue = document
							.getElementById("txt-snomed-ct-search").value;
					if (dataValue.length >= 3) {
						if (document.getElementById("conceptdiv"))
							$("#conceptdiv").remove();
						$('#msg3chars').hide();
						$('#nosuggestion').hide();
						loadResultsList(stateParam, semantictagParam,
								acceptabilityParam, returnlimitParam, callback);
					} else {
						if (document.getElementById("conceptdiv"))
							$("#conceptdiv").remove();
						$('#msg3chars').show();
						$('#reccnt').hide();
						$('#reccount').hide();
						$('#norec').hide();
						$('#nosuggestion').hide();
					}
				}

			});

	selectedConceptId = "";
	var footer = '';
	footer += '<div id="footer"><div class="footer-nav">';
	footer += '<ul><li style="float: left;position: relative;margin-left: 0px;">&copy; CSNOtk v2.0</li><li style="float: right;position: absolute;right: 60px; padding: 0 10px;">CSNOCtrl&nbsp;&copy;Centre for Development of Advanced Computing</li>';
	footer += '<li style="float: right;position: absolute;right: 4px; padding: 0 10px;"><a href="#" id="license">License</a></li></ul>';
	footer += '</div></div>';
	displaySearch = false;

	$("#dialog-form").dialog("option", "height", 160);
	$("#dialog-form").html(dialogFormHTML);
	jQuery('.ui-dialog button:nth-child(1)').button('disable');
	$('.ui-dialog-buttonpane').find("button").show().filter(":contains('.')")
			.hide();
	if (!$("#footer").length) {
		$(".ui-dialog-buttonpane").append(footer);
	}
	$("#reccnt").hide();
	$('#reccount').hide();
	$('#norec').hide();
	$('#msg3chars').hide();
	$('#nosuggestion').hide();

	var txtBox = document.getElementById("txt-snomed-ct-search");
	txtBox.focus();

	$("#dialog-form").dialog("open");

	$("#txt-snomed-ct-search")
			.keyup(
					function(e) {
						if (e.which == 13) {
							var dataValue = document
									.getElementById("txt-snomed-ct-search").value;

							if (dataValue == '') {
								$("#dialog-form").dialog("option", "height", 160);
								var txtBox = document
										.getElementById("txt-snomed-ct-search");
								txtBox.focus();
								return;
							}

							if (dataValue.length >= 3) {
								$("#dialog-form").dialog("option", "height", 500);
								if (document.getElementById("conceptdiv"))
									$("#conceptdiv").remove();
								$("#msg3chars").hide();
								$('#nosuggestion').hide();
								var txtBox = document
										.getElementById("txt-snomed-ct-search");
								txtBox.blur();
								loadResultsList(stateParam, semantictagParam,
										acceptabilityParam, returnlimitParam, callback);
							} else {
								if (document.getElementById("conceptdiv"))
									$("#conceptdiv").remove();
								$('#reccnt').hide();
								$('#reccount').hide();
								$('#norec').hide();
								$("#msg3chars").show();
								$('#nosuggestion').hide();
							}
						}
						else
							{
							$('#reccnt').hide();
							$('#reccount').hide();
							$('#nosuggestion').hide();
							}
					});


	var xhrRequest = null;
	$("#txt-snomed-ct-search")
			.autocomplete(
					{
						max : 20,
						minLength : 3,
						cacheLength : 1,
						scroll : false,
						width : 520,
						highlight : false,
						autoFocus : false,
						source : function(request, response) {

							var dataValue = document
									.getElementById("txt-snomed-ct-search").value;

							var servURL = "";
							if (dataValue == '') {
								$("#dialog-form").dialog("option", "height",
										160);
								if (document.getElementById("conceptdiv"))
									$("#conceptdiv").remove();
								var txtBox = document
										.getElementById("txt-snomed-ct-search");
								txtBox.focus();
								return;
							}

							if (dataValue.length >= 3) {
								if (document.getElementById("conceptdiv"))
									$("#conceptdiv").remove();
								$("#dialog-form").dialog("option", "height",
										160);
								$("#msg3chars").hide();
								$('#nosuggestion').hide();

							} else {
								if (document.getElementById("conceptdiv"))
									$("#conceptdiv").remove();
								$("#dialog-form").dialog("option", "height",
										160);
								$('#reccnt').hide();
								$('#reccount').hide();
								$('#norec').hide();
								$("#msg3chars").show();
								$('#nosuggestion').hide();
							}

							servURL = enumSERVICES.SEARCH.suggestbyacceptability_url;
							
							servURL += "?term="
									+ encodeURIComponent(request.term)
									+ "&state=" + stateParam + "&semantictag="
									+ semantictagParam + "&acceptability="
									+ acceptabilityParam + "&returnlimit=5";
							console.log(servURL);

							if (xhrRequest && xhrRequest.readystate != 4)
								xhrRequest.abort();
							xhrRequest = $
									.ajax({
										type : "GET",
										url : servURL,
										dataType : "jsonp",
										crossDomain : true,
										success : function(data, textStatus,
												jqXHR) {
											console.log(data);
											var items = data;
											if(items.length<=0)
											{
												$('#reccnt').hide();
												$('#reccount').hide();
												$('#norec').hide();
												$("#msg3chars").hide();
												$('#nosuggestion').show();
											}
											response(items);
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											console.log(textStatus);

										}
									});

						},
						select : function(event, ui) {
							var data = document
									.getElementById("txt-snomed-ct-search").value;
							if (data.length >= 3) {
								
								$("#msg3chars").hide();
								$('#nosuggestion').hide();
								document.getElementById("txt-snomed-ct-search").value = ui.item.value;
								if (document.getElementById("conceptdiv"))
									$("#conceptdiv").remove();

								loadResultsList(stateParam, semantictagParam,
										acceptabilityParam, returnlimitParam, callback);
							} else {
								if (document.getElementById("conceptdiv"))
									$("#conceptdiv").remove();
								$('#reccnt').hide();
								$('#reccount').hide();
								$('#norec').hide();
								$("#msg3chars").show();
								$('#nosuggestion').hide();
							}
						}

					});
	$('#license').click(function() {
		$('#licenseBox').load("license.html", function(content) {
			$('#licenseBox').dialog({
				autoOpen : false,
				resizable : false,
				height : "auto",
				width : 800,
				dialogClass : 'noTitle',
				title : "LICENSE INFO",
				modal : true,
				show : {
					effect : "blind",
					duration : 1000
				},
				hide : {
					effect : "explode",
					duration : 1000
				}
			});
			$('#licenseBox').dialog('open');
		});

	});

}

/*
 * selectValue() function selects the record from the list. The record returns
 * the details of the searched term from the SNOMEDCT repository. The details
 * include description id,description and concept id of the selected record. The
 * result shows concept-id,description-id and description of the term. @param
 * value (var) contains the result including concept-id, description-id and
 * description.
 * 
 * @param callback (var) - It will contain the definition of the callback
 * function used to retrieve return value/result from the selectSNOMEDCT().The
 * result includes Concept-id,Description-id and Description of the selected
 * concept.
 * 
 * returnterm_OUT (var) contains the detailed output with concept-id,
 * description-id and description.
 */


function selectValue(value, callback) {
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = "Description: " + term[1] + "\r\n" + "Concept Id: "
			+ term[0].replace("'", "") + "\r\n" + "Description id: "
			+ term[2].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#dialog-form").dialog("close");

}
function message(val) {
	$(".ui-dialog-buttonpane").removeClass('footer-nav');
	
	dialogHTML = '<p>' + val + '</p>';
	$("#dialog-message").html(dialogHTML);
	$("#dialog-message").dialog({
		modal : true,
		buttons : {
			Ok : function() {
				$("#txt-snomed-ct-search").focus();
				$("#dialog-message").dialog("close");
			}
		}
	});
}

function getHeight(count, buttonPlace) {
	if (buttonPlace == true) {
		switch (count) {
		case 1:
			return 270;
		case 2:
			return 320;
		case 3:
			return 370;
		case 4:
			return 410;
		case 5:
			return 450;
		}
	} else {
		switch (count) {
		case 1:
			return 250;
		case 2:
			return 300;
		case 3:
			return 350;
		case 4:
			return 390;
		case 5:
			return 420;
		}
	}
}
