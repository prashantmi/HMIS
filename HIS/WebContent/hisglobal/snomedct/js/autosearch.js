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
var enumSearchSERVICES = {
	SEARCH : {
		type : "search",		
		searchbyacceptability_url : "http://localhost:8081/csnoserv/api/search/search",
	},
};

var enumSearchACCEPTABILITY = {
	ALL : "all",
	PREFERRED : "preferred",
	PREFERRED_EXCLUDING_FSN : "preferredexcludingfsn",
	SYNONYMS : "synonyms",
	ACCEPTABLE : "acceptable"
};
var enumSearchSTATE = {
	BOTH : "both",
	ACTIVE : "active",
	INACTIVE : "inactive"
};

var enumSearchSEMANTICTAG = {

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



function autoselectSNOMEDCT(FIELD_ID,state_IN, semantictag_IN, acceptability_IN, returnlimit_IN,
		callback) {		
	if (returnlimit_IN <= -1 || returnlimit_IN == '' || returnlimit_IN == undefined
			|| returnlimit_IN == null) {
		returnlimit_IN =10;

	}	

	if (state_IN == null || state_IN == ''
			|| state_IN == undefined) {
		state_IN = enumSearchSTATE.ACTIVE;
	}else
		state_IN = enumSearchSTATE[state_IN];

	if (semantictag_IN == null || semantictag_IN == undefined
			|| semantictag_IN == '') {
		semantictag_IN = enumSearchSEMANTICTAG.ALL;
	} else
		semantictag_IN = enumSearchSEMANTICTAG[semantictag_IN];

	if (acceptability_IN == null
			|| acceptability_IN == undefined || acceptability_IN == '') {
		acceptability_IN = enumSearchACCEPTABILITY.SYNONYMS;
	} else
		acceptability_IN = enumSearchACCEPTABILITY[acceptability_IN];

	var xhrRequest = null;							
				$('#'+FIELD_ID).on('keypress, keydown', function(event) {	
					var isFirefox = typeof InstallTrigger !== 'undefined';
					if (event.which==186 ||( isFirefox && event.which==59)) {          			
            				event.preventDefault();
         				}
					if($('#'+FIELD_ID).val().trim()!="")
					{
						if(this.selectionStart<$('#'+FIELD_ID).val().length||event.which==37 || event.which==39)
						{
							return false;
						}
					}				
					if (event.which == 8||event.which == 46){
        			if($('#'+FIELD_ID).val().slice(-1)==';')
        			{
        				return false;
        			}
        			}
        			if (event.ctrlKey==true) {          			
            				event.preventDefault();
         				}
				}); 


	$("#"+FIELD_ID)
			.autocomplete(
					{
						max : 20,
						minLength : 3,
						cacheLength : 1,
						scroll : false,
						width : 200,
						highlight : false,
						autoFocus : false,
						source : function(request, response) {
							var dataValue = document.getElementById(FIELD_ID).value;								
								
							var servURL = "";
							if (dataValue == '') {
								return;
							}
							servURL = enumSERVICES.SEARCH.searchbyacceptability_url;							
							servURL += "?term="
									+ dataValue
									+ "&state=" + state_IN + "&semantictag="
									+ semantictag_IN + "&acceptability="
									+ acceptability_IN + "&returnlimit="+returnlimit_IN;
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
											var items=data;											
											if(items.length<=0)
											{
												//$('#nosuggestion').show();
											}
											response($.map(items, function (item) {
													return { value: item.term, concept: item };
													
                       							}));
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											console.log(textStatus);
										}
									});						

						},						
						select : function(event, ui) {
						if (typeof callback === "function")
						callback(ui.item.concept,FIELD_ID);							
							$("#"+FIELD_ID).blur();
							$("#"+FIELD_ID).focus();
						},
						focus: function (event, ui) {
 					    //  this.value = ui.item.label;
       						event.preventDefault(); // Prevent the default focus behavior.
						}
					}).data("autocomplete")._renderItem = function (ul, item) {
        return $("<li>")
            .data("item.autocomplete", item)
            .append("<a title='" + item.concept.conceptFsn + "'>" + item.concept.term+ "</a>")
            .appendTo(ul);
    };    
    

}

