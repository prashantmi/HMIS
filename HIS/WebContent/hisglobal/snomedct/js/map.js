/*******************************************************************************
 * Copyright 2016 Centre for Development of Advanced Computing(C-DAC), Pune
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

 //enum object for gender of patient
 var enumGENDER = {
	MALE : "male",
	FEMALE : "female",
	NONE : ""
};


/*
*This function calls the mapping service with input parameters.
*On success, it checks the condition and opens dialog box for selection of mapping ICD-10 code.
*/
function mapICDCode(field_IN, snomedcttxt_IN, gender_IN, birthdate_IN, diagnosisdate_IN, secondary_IN, callback){
	
	if (gender_IN == null || gender_IN == ''
			|| gender_IN == undefined) {
		gender_IN = enumGENDER.NONE;
	} else
		gender_IN = enumGENDER[gender_IN];
	

	var servURL = '';
	servURL = "/AHIMSG5/snomedct/csnoserv/api/map/icdmap";	//change the url here
		
	servURL += "?id=" + encodeURIComponent(snomedcttxt_IN) + "&gender=" + encodeURIComponent(gender_IN) + "&birthdate=" + encodeURIComponent(birthdate_IN) + "&diagnosisdate=" + encodeURIComponent(diagnosisdate_IN) + "&secondary=" + encodeURIComponent(secondary_IN);	
	
	console.log(servURL);
		
		$
				.ajax({
					type : "GET",
					url : servURL,
					dataType : "jsonp",
					crossDomain : true,
					success : function(data, textStatus, jqXHR) {
						
						//if result object is null or no mapping group exists then return msg for it.
						if(data == null || data == "" || data.mapGroup.length == 0 || data.mapGroup == null || (data.mapGroup.length == 1 && data.mapGroup[0] == null) ){
							selectValueForMap(escape("No Map") , null, callback, field_IN);
						}
						//if result object has atleast one mapping group then display dialog box with mapAdvices and corresponding code for it.
						else if(data.mapGroup.length >= 1){							
								displayDialog(data, callback, field_IN);
						}												
						
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
				
}


/*
*variable declared used for the dialog box
*/
var dialogFormHTML;
var divDialogForm;

				function displayDialog(data, callback, field_IN) {

					dialogFormHTML = '<b>SNOMED CT to ICD-10 Map: </b><br/>';
					dialogFormHTML += '<span><img style="width:10px;height:10px;" src="/HIS/hisglobal/snomedct/css/images/color_dodger_blue.png" /><label style="font-size:12px;"> Recommended code for use</label>';
					dialogFormHTML += '<span class="pull-right" ><img style="width:10px;height:10px;" src="/HIS/hisglobal/snomedct/css/images/color_black.png" /><label  style="font-size:12px;"> Possible code for use</label></span></span>';
					dialogFormHTML += '<label id="resultTableList"></label>';
					dialogFormHTML += '<div id="licenseBox"></div>';
					divDialogForm = '<div id="dialog-form" style="width: 600px;">' + '</div>';

					$('body').append(divDialogForm);

					var dialog = $("#dialog-form").dialog({

						autoOpen : false,
						height : 160,
						width : 600,
						modal : true,
						resizable : false,
						
						buttons : {

							"." : function(event) {

							}
						}

					}

					);

					dialog.data("uiDialog")._title = function(title) {
						title.html(this.options.title);

					};

					dialog
							.dialog(
									'option',
									'title',
									'<img style="width:63px;height:20%;vertical-align: text-bottom;padding-right:34em;" src="/HIS/hisglobal/snomedct/css/images/CSNOCtrl.png" style="position:relative;right:0px;top:0px;"/><img align="right" style="position:relative;vertical-align: text-bottom; height: 20%; width: 36px; top: 0px;" src="/HIS/hisglobal/snomedct/css/images/CDACLogo.png"/> ');
									
	
					$("#dialog-form").dialog("option", "height", "320");
					$("#dialog-form").html(dialogFormHTML);
					
					$("#dialog-form").dialog("open");	


					
					var footer = '';
					footer += '<div id="footer"><div class="footer-nav">';
					footer += '<ul><li style="float: left;position: relative;margin-left: 0px;">&copy; CSNOtk v3.0</li><li style="float: right;position: absolute;right: 60px; padding: 0 10px;">CSNOCtrl&nbsp;&copy;Centre for Development of Advanced Computing</li>';
					footer += '<li style="float: right;position: absolute;right: 4px; padding: 0 10px;"><a href="#" id="license">License</a></li></ul>';
					footer += '</div></div>';
					
					jQuery('.ui-dialog button:nth-child(1)').button('disable');
					$('.ui-dialog-buttonpane').find("button").show().filter(":contains('.')").hide();
					
					if (!$("#footer").length) {
						$(".ui-dialog-buttonpane").append(footer);
					}
					
					//action when dialog box is closed either by esc key/close button or other means
					$("#dialog-form").dialog({
						close: function( event, ui ) {
							$("#dialog-form").dialog("destroy").remove();
							$("#dialog-form").remove();	
							dialogFormHTML = "";
							divDialogForm = "";	
							$(".ui-dialog").remove();
						}
					});
					
					//action when license link/button in the dialog box is clicked 
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
					
					//This loop allows to add tables for each mapGroup and subsequent rows for each advice within the group.
					for (var i = 0; i < data.mapGroup.length; i++) {						
						addGroupTable(i);						
						addRowResult(i, data, callback, field_IN);						
					}						

				}


/*
*addGroupTable function is to create a new table for each group and then subsequently add rows for mapping advices with code.
*var - define the group number among all mapGroup.
*/
function addGroupTable(i) {
	
	var space = document.createElement("br");
	var tableListId = document.getElementById("resultTableList");
	tableListId.appendChild(space);
	
	//declare a new element of table type.
	var element = document.createElement("table");
	//Assign different attributes to the element. 
	element.name = "resultTable"; 
	element.className = "bordered";
	element.id = "resultTable"+i; 
	
	var tableListId = document.getElementById("resultTableList");
	//Append the newly created table to already defined id.  
	tableListId.appendChild(element);
  
    var table = document.getElementById("resultTable"+i);
    var row = table.insertRow(table.rows.length);
	row.className = "theader expand";
	
	//code to expand and collapse alterantively on click over the row.
	row.onclick = function(){
     $(this).toggleClass('expand').nextUntil('tr.theader').slideToggle(100);
	};
    
	var j=i+1;
	//declare and add a table header to the newly created table with heading as group number.
	var headerCell = document.createElement("TH");
    headerCell.innerHTML = '<span style="text-align: right;" class="sign"></span> Group ' + j ;
    row.appendChild(headerCell);
}

/*
*addRowResult function is to add row the newly created table with data as advice and mapping ICD code.
*/
function addRowResult(i, data, callback, field_IN) {

	var snomedid = data.conceptId;
	var mappedICDCode = data.mapGroup[i].mappedICDCode;
	var mapAdvices = data.mapGroup[i].mapAdvices;
		
		for(var j=0; j<mapAdvices.length; j++){
			var table = document.getElementById("resultTable"+i);
			var row = table.insertRow(table.rows.length);
			var cell0 = row.insertCell(0);
			
			if(mappedICDCode==mapAdvices[j].target){
				advicesplit = '<div style="cursor:pointer;" onclick="selectMapTerm('+"'"+mapAdvices[j].target+"'"+','+"'"+mapAdvices[j].term+"'"+','+callback+','+"'"+field_IN+"'"+');"><div id="mappedICDCodeId" style="color:DodgerBlue; font-weight: bold;" >' + mapAdvices[j].target;	
			}
			else{				
				advicesplit = '<div style="cursor:pointer;" onclick="selectMapTerm('+"'"+mapAdvices[j].target+"'"+','+"'"+mapAdvices[j].term+"'"+','+callback+','+"'"+field_IN+"'"+');"><div id="mappedICDCodeId" style="font-weight: bold; " >' + mapAdvices[j].target;
			}		
			
			if(mapAdvices[j].term)
				advicesplit += '<label style="cursor:pointer; color:black; font-weight: normal;"> - ' + mapAdvices[j].term + '</label>';	
			else
				advicesplit += '<label style="cursor:pointer; color:black; font-weight: normal;"> - No Description Available</label>';	
			
			advicesplit += '</div>';
			advicesplit += '<div style="font-size:10px; color:grey;"><i>' + mapAdvices[j].advice + '</i></div></div>';			
			
			cell0.innerHTML = advicesplit;
			
			
		}

}

/*
*selectMapTerm function is to get the selected code among all listed advice and codes.
*And then passes the value to selectValueForMap() to set value back using callback function.
*/
function selectMapTerm(val, term, callback, field_IN)
{
	clearMapDialog();
	
	selectValueForMap(escape(val), escape(term), callback, field_IN);
}

/*
*selectValueForMap function is to return the selected code from the result list to callback function.
*/
function selectValueForMap(value, term, callback, field_IN) {
	var data = unescape(value);
	var term = unescape(term);

	/*htmlData = '<br/><table id="mapResultTable">';
	htmlData += '<tr class="conId1"><th class="conTh">ICD Code </th><td class="conDtls">' + data + '</td></tr>';
	if(term!=null && term!="undefined"){
		htmlData += '<tr class="conId1"><th class="conTh">ICD Term </th><td class="conDtls">' + term + '</td></tr>';
	}
	htmlData += '</table>';
	*/
	var returnterm_OUT = data +'$$$'+ term;;
	
	if (typeof callback === "function")
		callback(returnterm_OUT, field_IN);
}

/*
*clearMapDialog function is to close the dialog box and to reset the global variables.
*It clears/removes the appended elements from the body of the page.
*/
function clearMapDialog(){

	$("#dialog-form").dialog("close");
	$("#dialog-form").dialog("destroy").remove();
	$("#dialog-form").remove();
	
	dialogFormHTML = "";
	divDialogForm = "";
	
	$(".ui-dialog").remove();
}


/*
*infoDialog function is to display box for providing information related to CSNOMapCtrl
*/
function infoMapDialog(dialogCode) {
	var infoDialogHTML = '';
	
	//if code is mapICDCode then it provides information for including scripts and functions for enabling plugin in an application
	if (dialogCode == 'mapICDCode') {
		infoDialogHTML = infoDialogHTML
				+ '<ol><li style="margin-left: 20px">Add following javascripts in your web page : <ul><li style="margin-left: 40px">jquery.js</li><li style="margin-left: 40px">jquery-ui.custom.min.js</li><li style="margin-left: 40px">jquery.autocomplete.js</li><li style="margin-left: 40px">jquery-ui-date.js</li></ul></li>'
				+ '<li style="margin-left: 20px">Add following CSNOCtrl scripts in your web page : <ul><li style="margin-left: 40px">map.js - for mapping input SNOMED Concept ID to ICD-10 code.</li></ul></li>'
				+ '<li style="margin-left: 20px;">Call <i>mapICDCode(field_IN, snomedctid_IN, gender_IN, birthdate_IN, diagnosisdate_IN, secondary_IN, callback_icd)</i> function for mapping the input SNOMED CT concepts to ICD-10 codes.</li>'
				+ '</ol>'
				+ '<ul><li  style="margin-left: 20px;">Parameter Description</li></ul>'
				+ '<ol><li  style="margin-left: 40px;"><b>field_IN</b>-	Id of the field to which resultant mapped ICD-10 code to be set.</li>'
				+ '<li style="margin-left: 40px;"><b>snomedctid_IN</b>-	SNOMED CT Concept ID that need to be mapped to ICD-10 code.</li>'
				+ '<li style="margin-left: 40px;"><b>gender_IN</b>-	Gender of the patient. <ul>MALE or  M (case insensitive)</ul><ul>FEMALE or F (case insensitive)</ul><ul>blank -  otherwise or unknown</ul></li>'
				+ '<li style="margin-left: 40px;"><b>birthdate_IN</b>-	Date of birth of the patient. It need to in MM-DD-YYYY format to be send to API.</li>'
				+ '<li style="margin-left: 40px;"><b>diagnosisdate_IN</b>-	Date of the diagnosis i.e. date on which patient was diagnosed. It need to in MM-DD-YYYY format to be send to API. If not provided, it will be considered as the current date.</li>'
				+ '<li style="margin-left: 40px;"><b>secondary_IN</b>-	Secondary diagnosis Concept ID. It can hold multiple SNOMED CT Concept IDs assumed to be separated by a plus (+) sign.</li>'
				+ '<li style="margin-left: 40px;"><b>callback</b>-	It will contain the definition of the callback function'
				+ ' used to retrieve return value/result from the <i>mapICDCode</i> function.<br>The result includes ICD-10 code.'
				+ ' <br>Callback function has to be defined for user defined HTML page.'
				+ ' <br/><div class="code">var callback =function(ret_OUT, field_id){setValue_icd(ret_OUT, field_id);};'
				+ ' <br/>function setValue_icd(mappedTerm, field_id)<br>{<p style="color:green;text-align:justify;">'
				+ '&nbsp;/* Parameter mappedTerm contains the resultant ICD-10 code.*/'
				+ '</p>&nbsp;document.getElementById(field_id).html=mappedTerm;<br>}'
				+ '<br></div>'
				+ ' <br>User will have to define a function like <i>setValue_icd</i> in the callback function' 
				+ ' to set the return value <i>mappedTerm</i> in some HTML control of the web page.<br>For different HTML controls' 
				+ ', user will have to define different callback functions or can use by passing different field_id.</li>'
				+ '</ol>';
	}

	infoDialogHTML = infoDialogHTML
			+ '<span width style="font-size:x-small;margin-left: 10px">Note:- CSNOCtrl js and css are available with CSNOCtrl downloadable package</span>';
	$("#dialog-howto").html(infoDialogHTML);
	$("#dialog-howto").dialog({
		modal : true,
		width : 550,
		height : 330,
		close: function( event, ui ) {
			$(".ui-dialog").remove();
		}
	});
	// Adding this if else again because any option cannot be added before above
	// dialog call which initializes the dialog.
	
	if (dialogCode == 'mapICDCode') {
		$("#dialog-howto").dialog("option", "width", 550);
		$("#dialog-howto").dialog("option", "height", 300);
		$('#dialog-howto').dialog('option', 'title',
				'How To Use CSNOCtrl for mapping SNOMED CT to ICD-10 code');
	}

	$("#dialog-howto").dialog('option', 'position', 'center');

}
