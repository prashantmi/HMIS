

var enumSERVICES = {
	SEARCH : {
		type : "search",
		suggestbyacceptability_url : "http://10.226.2.97:8080/csnoserv/api/search/suggestbyacceptability",
		searchbyacceptability_url : "http://10.226.2.97:8080/csnoserv/api/search/searchbyacceptability",
	},
};


function selectValue(value, callback) 
{
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#dialog-form").dialog("close");

}

var snomedElementId = '';
 var callbck_index =function(ret_OUT){setValue(ret_OUT);};
function selectinvSNOMEDCT(id)
{
	var semantictag="";
	var elmtId=id;
	 snomedElementId = id;
	// selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index);
	
	 selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
}

function snomedctsingle(id)
{
   alert("snomed");
   var semantictag="";
	
	selectinvSNOMEDCT(id);
//ajaxx('ACTIVE',semantictag,'SYNONYMS','10','null',id,callbck_index);
}

function setValue(selectedSNOMEDTerm)
{
	// alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	var arr=selectedSNOMEDTerm.split(",");
	var str=arr[0];
	var str1=arr[1];
	
	var snomedValue = '';
	//alert(str[0]); alert(str1);
	if(document.getElementsByName(snomedElementId)[0].value != null && 
			document.getElementsByName(snomedElementId)[0].value != "-")
		{
			snomedValue = document.getElementsByName(snomedElementId)[0].value + ", ";
		}
	document.getElementsByName(snomedElementId)[0].value= snomedValue + arr[1] + ":" + arr[0];
	//document.getElementsByName(snomedElementId +  "#" + "conceptId")[0].value=str1;
		}
	else
		{
		document.getElementsByName(snomedElementId)[0].value="-";
		//document.getElementsByName(snomedElementId+ "#" +"conceptId")[0].value="";
		}
	snomedElementId = '';
}

function clearSnomedText(snomedElementId)
{
	document.getElementsByName(snomedElementId)[0].value = "-";
}


function ajaxx(state_IN, semantictag_IN, acceptability_IN, returnlimit_IN,refsetid_IN,
		elmtid,callback)
{
	
	
	
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

	if (refsetid_IN <= -1 || refsetid_IN == '' || refsetid_IN == undefined
			|| refsetid_IN == null) {
		refsetid_IN = null;
	}
	refsetidParam=refsetid_IN;

	
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
	
	var servURL = "";
	  
	var dataValue=document.getElementById("1").value;
	  servURL = enumSERVICES.SEARCH.searchbyacceptability_url;							
								servURL += "?term="
										+ dataValue
										+ "&state=" + state_IN + "&semantictag="
										+ semantictag_IN + "&acceptability="
										+ acceptability_IN + "&returnlimit="+returnlimit_IN+"&refsetid="+refsetidParam;
								console.log("url"+servURL);
								
	alert("#tt"+elmtid);
var a="1";
	$(function() {
         /* var availableTutorials = [
             "ActionScript",
             "Boostrap",
             "C",
             "C++",
             "Ecommerce",
             "Jquery",
             "Groovy",
             "Java",
             "JavaScript",
             "Lua",
             "Perl",
             "Ruby",
             "Scala",
             "Swing",
             "XHTML"	
          ];
          $( "#"+a ).autocomplete({
             minLength:1,   
             delay:500,   
             source: availableTutorials
          });*/
		
		
		$.ajax({
		     url:servURL,
		     type:"post",
		     data:'',
		     success:function(data){
		      $( "#"+a ).autocomplete({   
		          source: data   
		        });
		     
		     },error:  function(data, status, er){
		              console.log("cc"+data+"_"+status+"_"+er);
		          },
		           
		    });
		
		
       });
}