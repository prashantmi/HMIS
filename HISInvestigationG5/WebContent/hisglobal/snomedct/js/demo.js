/*******************************************************************************
 * Copyright 2014 Centre for Development of Advanced Computing(C-DAC), Pune
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
var index = 0;
var jsonData;
var currentCountDisplayed;
var displaySearch;
var h = 80;

var txtId;
var currentHierachy, currentSERVICE;
var selectedConceptId;
var conceptIdOfSearch, conceptIdOfHierarchySearch, conceptIdOfFiniteSearch;

$(document).ready(function() {
	resetControls();
});

function resetControls() {

	document.getElementById("snomedcttxt").value = "";
	document.getElementById("forState").value = "ACTIVE";
	document.getElementById("forSemanticTag").value = "ALL";
	document.getElementById("forAcceptability").value = "SYNONYMS";
	document.getElementById("txtreturnlimit").value = "-1";
	document.getElementById("conceptId").value = "";
	document.getElementById("langRefset").value="US";
	document.getElementById("cptId").value = "";
	document.getElementById('conResult').value = "";
	document.getElementById("relation").value = "IS_A";
	document.getElementById("srcTgt").value = "source";

	$("#blankConceptIdLookup").hide();
	$("#wrongConceptIdLookup").hide();
	$("#blankConceptIdRelation").hide();
	$("#wrongConceptIdRelation").hide();	
}

function lookupByConcept(conceptId,langRefset,targetSpanId) {
	var dataValue = document.getElementById(conceptId).value;
	var langrefset= enumLANGREFSET[document.getElementById(langRefset).value];
	
		$("#conceptdetails").remove();
		
		$("#blankConceptIdLookup").hide();
		$("#wrongConceptIdLookup").hide();

	if (dataValue.trim() == '') {
		$("#conceptdetails").remove();
		$("#blankConceptIdLookup").show();
		$("#wrongConceptIdLookup").hide();
		var txtBox = document.getElementById(conceptId);
		txtBox.focus();
		return;
	}

	if (!(dataValue.match(/^[0-9]+$/))) {
		$("#conceptdetails").remove();
		$("#blankConceptIdLookup").hide();
		$("#wrongConceptIdLookup").show();		
		var txtBox = document.getElementById(conceptId);
		txtBox.focus();
		return;
	}

	// Call ur webservice here
	$
			.ajax({
				type : "GET",
				url : "http://localhost:8080/csnoserv/api/lookup/concept", // replace
				// with
				// ur
				// service
				// call
				dataType : "jsonp",
				crossDomain : true,
				data : {
					"id" : dataValue,
					"langrefset" : langrefset
				},
				success : function(data, textStatus, jqXHR) {
					var htmlData = '';
					var jsonData = data;
					if (jsonData != null) {
						if (data.conceptId == 0) {
							message('Invalid Concept ID');
							return;
						}
						if (jsonData.conceptId == 'null') {
							$("#conceptdetails").remove();
							message('Please enter a valid Concept ID');
						} else {
							htmlData = '<table id="conceptdetails" style="margin-left:5px;border:1px solid #ddd; border-radius:5px; box-shadow:1px 2px 2px #ddd; padding:10px">';
							var status = '';
							if (jsonData.active == true)
								status = "ACTIVE";
							else
								status = "INACTIVE";

							htmlData += '<tr class="conId1"><th class="conTh">Concept ID</th><td class="conDtls">'
									+ jsonData.conceptId + '</td></tr>';
							htmlData += '<tr class="conId1"><th  class="conTh">Fully Specified Name</th><td class="conDtls">'
									+ jsonData.fsn + '</td></tr>';
							htmlData += '<tr class="conId1"><th class="conTh">Status</th><td class="conDtls">'
									+ status + '</td></tr>';
							htmlData += '<tr class="conId1"><th  class="conTh" style="vertical-align:top;">Synonyms</th><td  class="conDtls" rowspan="'
									+ jsonData.descriptions + '"><table>';
							for (var index = 0; index < jsonData.descriptions.length; index++) {
								htmlData += '<tr><td>'
										+ jsonData.descriptions[index].term
										+ '</td></tr>';
							}
							htmlData += '</table></td></tr></table>';
							targetSpanId = '#' + targetSpanId;
							$(targetSpanId).html(htmlData);
						}

					} else {
						$("#wrongConceptIdLookup").show();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
}

function lookupByConceptRelation(cptId, relation, srcTgt, targetSpanId) {

	$("#blankConceptIdRelation").hide();
	$("#wrongConceptIdRelation").hide();
	
	$("#relconcept").remove();

	var id = document.getElementById(cptId).value;
	if (id.trim() == '') {
		$("#relconcept").remove();	
		$("#blankConceptIdRelation").show();
		$("#wrongConceptIdRelation").hide();	
		var txtBox = document.getElementById(cptId);
		txtBox.focus();
		return;
	}
	var relationV = document.getElementById(relation).value;
	var direction = document.getElementById(srcTgt).value;	
	if (!(id.match(/^[0-9]+$/))) {
		$("#relconcept").remove();
		$("#blankConceptIdRelation").hide();
		$("#wrongConceptIdRelation").show();
		var txtBox = document.getElementById(conceptId);
		txtBox.focus();
		return;
	}
	
	$
			.ajax({
				type : "GET",
				url : "http://localhost:8080/csnoserv/api/lookup/relationship",
				// replace with your service call
				// url:"http://localhost:9090/csnoserv/api/lookup/relationship",
				dataType : "jsonp",
				crossDomain : true,
				data : {
					"id" : id,
					"relation" : enumRELATIONSHIP[relationV],
					"direction" : direction,
					
				},				
				success : function(data, textStatus, jqXHR) {					
					var htmlData = '';

					var jsonData = data;
					if (jsonData != null) {
						if (jsonData.length > 0) {
							htmlData = '<br/><br/><table id="relconcept" style="margin-left:90px;border:1px solid #ddd; border-radius:5px; box-shadow:1px 2px 2px #ddd; padding:10px">';
							htmlData += '<tr><th>' + 'Related Concept IDs'
									+ '</th></tr>';

							for (var index = 0; index < jsonData.length; index++) {
								htmlData += '<tr><td>' + jsonData[index]
										+ '</td></tr>';
							}
							htmlData += '</table></td></tr></table>';
							targetspanId = '#' + targetSpanId;
							$(targetspanId).html(htmlData);
						} else {
							$("#relconcept").remove();
							$("#wrongConceptIdRelation").show();
						}
					} else {
						$("#relconcept").remove();
						$("#wrongConceptIdRelation").show();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);													
				}

			});

}

function infoDialog(dialogCode) {
	var infoDialogHTML = '';

	if (dialogCode == 'searchCtrl') {
		infoDialogHTML = infoDialogHTML
				+ '<ol><li style="margin-left: 20px">Add following javascripts in your web page : <ul><li style="margin-left: 40px">jquery.js</li><li style="margin-left: 40px">jquery-ui.custom.min.js</li><li style="margin-left: 40px">jquery.autocomplete.js</li></ul></li>'
				+ '<li style="margin-left: 20px">Add following CSNOCtrl scripts in your web page : <ul><li style="margin-left: 40px">searchtool.js</li><li style="margin-left: 40px">searchtool.css</li></ul></li>'
				+ '<li style="margin-left: 20px;">Call <i>selectSNOMEDCT(state_IN,semantictag_IN,acceptability_IN,returnlimit_IN,callback)</i> function to view CSNOCtrl dialog box.'
				+ '<ul><li  style="margin-left: 20px;">Parameter Description</li></ul>'
				+ '<ol><li  style="margin-left: 40px;"><b>state_IN</b>-	Defines the state of concept. Refer enumSTATE in searchtool.js to pass values for the state_IN parameter. Possible values for state_IN parameter are as follows-<ul><b>ACTIVE</b> - It will return only active components.</ul><ul><b>INACTIVE</b> - It will return only inactive components.</ul><ul><b>BOTH</b> -It will return active and inactive components.</ul></li>'
				+ '<li style="margin-left: 40px;"><b>semantictag_IN</b>-	Defines semantic tag of the concept. Refer enumSEMANTICTAG in searchtool.js to pass values for the semantictag_IN parameter.Possible values for semantictag_IN parameter are as follows-<ul><b>ALL</b> - It will search for SNOMED CT terms in all semantic tags</ul><ul>Other possible values are- PROCEDURE,DISORDER,BODY_STRUCTURE,etc.</ul></li>'
				+ '<li style="margin-left: 40px;"><b>acceptability_IN</b>-	Refer enumACCEPTABILITY in searchtool.js to pass values for the acceptability_IN parameter. Possible values for acceptability_IN parameter are as follows-<ul><b>PREFERRED</b> - If acceptability_IN is PREFERRED, only FSN and Preferred Terms for a given term shall be fetched.</ul><ul><b>PREFERRED_EXCLUDING_FSN</b> - Only Preferred Terms excluding FSN for given term shall be fetched.</ul><ul><b>SYNONYMS</b> - Only synonyms for the given term shall be fetched.</ul><ul><b>ACCEPTABLE</b> - Only Acceptable Terms for given term shall be fetched. </ul><ul><b>ALL</b> - It will fetch for all SNOMED CT terms related to the search term, irrespective of their acceptability value.</ul></li>'
				+ '<li style="margin-left: 40px;"><b>returnlimit_IN</b>-	Defines the maximum number of matching terms to be fetched from the SNOMED CT repository.For displaying all results, returnlimit should be -1.</li>'
				+ '<li style="margin-left: 40px;"><b>callback</b>-	It will contain the definition of the callback function'
				+ ' used to retrieve return value/result from the <i>selectSNOMEDCT</i> function.<br>The result includes Concept Id,Description Id'
				+ ' and Description of the selected concept from SNOMED CT repository.<br>Callback function has to be defined for '
				+ ' user defined HTML page.<br/><div class="code">var callback =function(selectedSNOMEDTerm){setValue'
				+ ' (selectedSNOMEDTerm);};<br/>function setValue(selectedSNOMEDTerm)<br>{<p style="color:green;text-align:justify;">'
				+ '&nbsp;/* Parameter selectedSNOMEDTerm contains Concept Id,Description Id and Description.User can manipulate the string or edit the '
				+ 'function definition as per his requirement*/</p>&nbsp;document.getElementById(HTMLcontrolid).value=selectedSNOMEDTerm;<br>}'
				+ '<br></div>The function shall contain only 1 parameter <i>selectedSNOMEDTerm</i> that refers to the result including'
				+ ' Concept Id,Description Id and Description.<br>User will have to define a function like <i>setValue</i> in the callback function' 
				+ ' to set the return value <i>selectedSNOMEDTerm</i> in some HTML control of the web page.<br>For different HTML controls' 
				+ ', user will have to define different callback functions.</li>'
				+ '</ol>';
	}
	if (dialogCode == 'conceptLookup') {
		infoDialogHTML = infoDialogHTML

				+ '<ol><li style="margin-left: 20px">Add following javascripts in your web page : <ul><li style="margin-left: 40px">jquery.js</li><li style="margin-left: 40px">jquery-ui.custom.min.js</li><li style="margin-left: 40px">jquery.autocomplete.js</li></ul></li>'
				+ '<li style="margin-left: 20px">Add following CSNOCtrl scripts in your web page : <ul><li style="margin-left: 40px">demo.js</li><li style="margin-left: 40px">searchtool.css</li></ul></li>'
				+ '<li style="margin-left: 20px;">Call <i>lookupByConcept(conceptId,langRefset,targetSpanId)</i> function for displaying concept details.'
				+ '</ol>';
	}

	if (dialogCode == 'conceptRelationLookup') {
		infoDialogHTML = infoDialogHTML
				+ '<ol><li style="margin-left: 20px">Add following javascripts in your web page : <ul><li style="margin-left: 40px">jquery.js</li><li style="margin-left: 40px">jquery-ui.custom.min.js</li><li style="margin-left: 40px">jquery.autocomplete.js</li></ul></li>'
				+ '<li style="margin-left: 20px">Add following CSNOCtrl scripts in your web page : <ul><li style="margin-left: 40px">demo.js</li><li style="margin-left: 40px">searchtool.css</li></ul></li>'
				+ '<li style="margin-left: 20px;">Call <i>lookupByConceptRelation(conceptControlID, relationControlID, directionControlID, targetSpanConrolID)</i> function for displaying the Ids of the related SNOMED CT concepts.'
				+ '</ol>';
	}

	infoDialogHTML = infoDialogHTML
			+ '<span width style="font-size:x-small;margin-left: 10px">Note:- CSNOCtrl js and css are available with CSNOCtrl downloadable package</span>';
	$("#dialog-howto").html(infoDialogHTML);
	$("#dialog-howto").dialog({
		modal : true,
		width : 550,
		height : 330
	});
	// Adding this if else again because any option cannot be added before above
	// dialog call which initializes the dialog.
	if (dialogCode == 'searchCtrl') {
		$("#dialog-howto").dialog("option", "width", 550);
		$("#dialog-howto").dialog("option", "height", 600);
		$('#dialog-howto').dialog('option', 'title',
				'How To Use CSNOCtrl: Search');

	} else if (dialogCode == 'conceptLookup') {
		$("#dialog-howto").dialog("option", "width", 550);
		$("#dialog-howto").dialog("option", "height", 300);
		$('#dialog-howto').dialog('option', 'title',
				'Steps to Use CSNOCtrl: LookUp');
	} else if (dialogCode == 'conceptRelationLookup') {
		$("#dialog-howto").dialog("option", "width", 550);
		$("#dialog-howto").dialog("option", "height", 300);
		$('#dialog-howto').dialog('option', 'title',
				'Steps to Use CSNOCtrl: Getting Concept Relationships');
	}

	$("#dialog-howto").dialog('option', 'position', 'center');
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
