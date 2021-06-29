
var docValidType = 'alphaNumSpecialChar';
var ageBoundRange=125;
//Start:Sheeldarshi
//Reason:Bug 10166 - System should be able to validate senior citizen category	
/*function showDivAgeDob(){
	if(document.getElementsByName("isActualDob")[0].checked){
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		$('[name="patAge"]').numberbox({required: true,	validType: ['minLength[1]']});
		var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
		var ageRangeValidType='range[1,'+maxAgeRange+']';
		//alert("ageRangeValidType :"+ageRangeValidType);
		$('[name="patAge"]').validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
		$("#patAgeUnitId").validatebox({required: true, validType: ['selectCombo[0]']});
		$("#patDOBId").validatebox({required: false,validType:null});

	}else{
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="";

		$("#patAgeId").validatebox({required: false,validType:null});
		$("#patAgeUnitId").validatebox({required: false,validType:null});
		$(function() {
			$("#patDOBId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
			var today=new Date().toLocaleFormat('%d-%b-%Y');
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(today);

		});
		var dobValidType='dtltetDMY['+ageBoundRange+',\'y\']';
		$("#patDOBId").validatebox({required: true, validType: ['date[\'patDOB\',\'dd-mmm-yyyy\']','dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth \']' ,dobValidType]});
	}

}*/
function showDivAgeDob() {
	var PRIMARY_CATEGORY_SENIOR_CITIZEN = $('[name="seniorCitizenCatCode"]')[0].value;
	if ($('[name="isActualDob"]')[0].checked) {
		$("#divAge").show();
		$("#divDob").hide();
		if(document.forms[0].patPrimaryCatCode.value==PRIMARY_CATEGORY_SENIOR_CITIZEN && $('[name="seniorCitizenAgeLimit"]')[0].value !="")
		{
		var seniorAgeBoundRange = $('[name="seniorCitizenAgeLimit"]')[0].value;
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
		$("#patAgeId").validatebox({
			required : true,
			validType : [ 'numeric', ageRangeValidType ]
		});
		$("#patAgeUnitId").validatebox({
			required : true,
			validType : [ 'selectCombo[-1]' ]
		});
		$("#patDOBId").validatebox({
			required : false,
			validType :null 
		});
		//$('[name="patAge"]').val($('[name="defaultPatAge"]').val());
		//$('[name="patAgeUnit"]').val($('[name="defaultPatAgeUnit"]').val());
		//$("#patDOBId").val("");

	} else {
		$("#divAge").hide();
		$("#divDob").show();
		$("#patAgeId").validatebox({
			required : false
		});
		$("#patAgeUnitId").validatebox({
			validType : null
		});
		$("#patAgeId").val("");
		$(function() {
			if($("#patDOBId").val()!=""){
				var _date=$.datepicker.parseDate("dd-M-yy", $("#patDOBId").val());
				_date=_date.toLocaleFormat('%d-%b-%Y');
				$('#patDOBId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:_date,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(_date);
			}
			else{
				var today=new Date().toLocaleFormat('%d-%b-%Y');
				$('#patDOBId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(today);
			}

		});
		if(document.forms[0].patPrimaryCatCode.value==PRIMARY_CATEGORY_SENIOR_CITIZEN && $('[name="seniorCitizenAgeLimit"]')[0].value !="")
		{
		var seniorAgeBoundRange = $('[name="seniorCitizenAgeLimit"]')[0].value;
		var dobValidType = 'dtltetDMYa[' + seniorAgeBoundRange + ',\'y\']';
		}
		else
		var dobValidType = 'dtltetDMY[' + ageBoundRange + ',\'y\']';
		//End
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
//End
/*Modify Date			: 24thNov'14
Reason	(CR/PRS)	: Secondary UHID check incorporation
Modify By				: Sheeldarshi */
function showpopuponload()
{var isDuplicate=document.getElementsByName("isDuplicatePatient")[0].value;

	
	 if (isDuplicate == "1")
		  UnknownToKnown.showDuplicatePatientPopup();	
}
//End:Sheeldarshi
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
function showDivAgeDobNew(){
	//alert("inside showDivAgeDob");
	if(document.getElementsByName("isActualDob")[0].checked){
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		
		var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
		var ageRangeValidType='range[1,'+maxAgeRange+']';
		//alert("ageRangeValidType :"+ageRangeValidType);
		$('[name="patAge"]').validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
		$("#patAgeUnitId").validatebox({required: true, validType: ['selectCombo[0]']});
		$("#patDOBId").validatebox({required: false});

	}else{
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="";

		$("#patAgeId").validatebox({required: false});
		$("#patAgeUnitId").validatebox({required: false});
		$(function() {
			/*$("#patDOBId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
			var today=new Date().toLocaleFormat('%d-%b-%Y');
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(today);
			
		});
		//alert("new"+ageBoundRange);

		var dobValidType='dtltetDMY['+ageBoundRange+',\'y\']';
		$("#patDOBId").validatebox({required: true, validType: ['date[\'patDOB\',\'dd-mmm-yyyy\']','dtltetctdt[\'Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth \']' ,dobValidType]});
	}

}


function ageSelection(isActual)
{
	if(isActual=='1')
	{
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="";
		document.getElementsByName("isActualDob")[1].checked=true;
		document.getElementsByName("patAge")[0].value="";
		document.getElementsByName("patAgeUnit")[0].selectedIndex=0;
		$(function() {
			/*$( '[name="patDOB"]').datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", document.getElementsByName("patDOB")[0].value);*/
			var _date=$.datepicker.parseDate("dd-M-yy", document.getElementsByName("patDOB")[0].value);
			_date=_date.toLocaleFormat('%d-%b-%Y');
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_date,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_date);

		});
	}
	else
	{
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		document.getElementsByName("isActualDob")[0].checked=true;
		$('[name="patAge"]').numberbox({required: true,	validType: ['minLength[1]']});

	}
} 


function getDocType(docType)
{

	if(docType!="-1" && docType !=""){
		$("#divCardNoId").show();

	}else{
		$('[name="patCardNo"]').validatebox({required: false});
		$("#divCardNoId").hide();
	}
	for(var i=0; i<veriyDocJSONArray.length; i++){
		if(veriyDocJSONArray[i].patDocType==docType){
			$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);
			docValidType= UnknownToKnown.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);
			$('[name="patCardNo"]').validatebox({required: true, validType: docValidType});
			break;
		}else{
			$('[name="patCardNo"]').validatebox({required: false});
		}
	}
}

//////////////alphabets without initial space//////////////
function validateAlphabetsOnly(e,obj)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	//alert(keychar)
	//alert("indexof="+('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ').indexOf(keychar))
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
			(key==9) || (key==13) || (key==27))
		return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true;
	// alphas and space

	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
		return true;
	else
		return false;
}

function validateNumericOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	var pattern=/\./;
	if( charCode==0 ||
			(!pattern.test(obj.value) && charCode==46) ||
			(obj.value.length==0 && charCode==45) || 
			(charCode>=48 && charCode<= 57) )
		return true;
	else
		return false;
}

function validateAlphaNumericWithSpecialCharacterOnly(e)
{
	var key;
	var keychar;

	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
			(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
		return true;


	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-").indexOf(keychar) > -1))
		return true;

	else if ((("0123456789").indexOf(keychar) > -1))
		return true;



	else
		return false;
}

///////////////////////getting index of char on keypress////////////////////////////////////////////////

function getCursorIdex(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate();
		r.moveEnd('character', o.value.length);
		if (r.text == '') return o.value.length;
		return o.value.lastIndexOf(r.text);
	} else return o.selectionStart;
}

var veriyDocJSONArray=[];
var returnDocument, isDuplicatePatientPopup = "";
var UnknownToKnown={fetchDefaultValues:function (){
	
	

	var action 			= "/HISRegistration/registration/transactions/populateformvaluesUnknownToKnown.action";


	$.ajax({url: action,type:"POST",async:false,dataType:"xml" ,success:function(data)
		{
		var returnDocument=data;
		var rootNode=returnDocument.getElementsByTagName("root")[0];
		for(var i=0;i<rootNode.childNodes.length;i++ )
		{

			var elementNode=rootNode.childNodes[i];
			var elementName=elementNode.nodeName;

			if(elementName=='patDocType')
			{
				UnknownToKnown.processVerifyDocNode(elementName,elementNode);
			}
			else if(elementName=='defaults')
			{
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
			}
			else
			{
				UnknownToKnown.processGeneralNode(elementName,elementNode);
			}

		}
		//ageSelection(eval(rootNode.getElementsByTagName("defaults")[0].attributes[].name));
		UnknownToKnown.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
		var objCountry  =document.getElementsByName("patAddCountryCode")[0];
		var countryCode = objCountry.options[objCountry.selectedIndex].value;
		var flagStateNDIstComboReqd=true;
		if(countryCode!=registrationDeskDefaultCountryCode)
		{
			$("#divStateComboId").hide();
			$("#divStateTextId").show();
			$("#divDistrictComboId").hide();
			$("#divDistrictTextId").show();
			flagStateNDIstComboReqd=false;
		}
		else
		{
			$("#divStateTextId").hide();
			$("#divStateComboId").show();
			$("#divDistrictTextId").hide();
			$("#divDistrictComboId").show();
			
		}
		if(flagStateNDIstComboReqd)
		{
			$("#patAddStateId").validatebox({required: false,	validType: 'alphaNumSpecialChar' });
			$("#patAddDistrictId").validatebox({required: false,	validType: 'alphaNumSpecialChar' });
			$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[0]']});
			$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[0]']});
			//$("#patAddStateCodeId").bind("change",opdregistration.onchange_patAddStateCode);
		}
		else
		{
			$("#patAddStateId").validatebox({required: true,	validType: 'alphaNumSpecialChar' });
			$("#patAddDistrictId").validatebox({required: true,	validType: 'alphaNumSpecialChar' });
			
			$('[name="patAddStateCode"]').validatebox({validType: null});
			$('[name="patAddDistrictCode"]').validatebox({validType: null});
		}
		},error: function(errorMsg,textstatus,errorthrown) {
			alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);

		}
	});

},setAddType:function(obj){
	var action 			= "/HISRegistration/registration/transactions/ADDADDRESSEmgPatientDetailMod.action";
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
		var returnDocument=data;
		var rootNode=returnDocument.getElementsByTagName("root")[0];
		for(var i=0;i<rootNode.childNodes.length;i++ )
		{
			var elementNode=rootNode.childNodes[i];
			var elementName=elementNode.nodeName;
			if(elementName=='defaults')
			{

			}
			else
			{
				UnknownToKnown.processGeneralNode(elementName,elementNode);
			}
		}

		UnknownToKnown.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
		document.getElementById("AddModdivIf").style.display = "";

		},error: function(errorMsg,textstatus,errorthrown) {
			alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);

		}
	});
	var addModify = document.getElementsByName("addModify")[0].value; 

	if(document.getElementById("addTypeCombo"))
	{
		document.getElementById("addTypeCombo").style.display="";
		document.getElementById("addTypeText").style.display="none";				
	}
	if(addModify==isAddressModified)
	{
		document.getElementsByName("patAddTypeCode")[0].value=document.getElementsByName("patFBAddTypeCode")[0].value;
	}
//	showState();
	$("#divStateTextId").hide();
	$('[name="patAddCountryCode"]').validatebox({
		validType: ['selectCombo[0]']
	});
	$('[name="patAddTypeCode"]').validatebox({
		validType: ['selectCombo[0]']
	});

},setModAddType:function(obj){

	var action 			= "/HISRegistration/registration/transactions/MODIFYADDRESSPatientDetailMod.action?fbAddTypeCode="+obj.value;
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
		var returnDocument=data;

		var rootNode=returnDocument.getElementsByTagName("root")[0];
		for(var i=0;i<rootNode.childNodes.length;i++ )
		{
			var elementNode=rootNode.childNodes[i];
			var elementName=elementNode.nodeName;

		}

		UnknownToKnown.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
		document.getElementById("AddModdivIf").style.display = "";

		},error: function(errorMsg,textstatus,errorthrown) {
			alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);

		}
	});
	var addModify = document.getElementsByName("addModify")[0].value; 

	if(document.getElementById("addTypeCombo"))
	{
		document.getElementById("addTypeCombo").style.display="none";
		document.getElementById("addTypeText").style.display="";
	}
	if(addModify==isAddressModified)
	{
		document.getElementsByName("patAddTypeCode")[0].value=document.getElementsByName("patFBAddTypeCode")[0].value;
	}
	//showState();

	$('[name="patAddCountryCode"]').validatebox({
		validType: ['selectCombo[0]']
	});

	UnknownToKnown.onchange_patAddCountryCodeDefault();
},processVerifyDocNode:function(elementName,elementNode)
{

	var optionText="<option value='-1'>Select Value</option>";
	for(var i=0;i<elementNode.childNodes.length;i++ )
	{
		var optionNode=elementNode.childNodes[i];
		var veriyDocJSONObject=jQuery.parseJSON(optionNode.getAttribute("value"));
		veriyDocJSONArray[veriyDocJSONArray.length]=veriyDocJSONObject;
		optionText+="<option value='"+veriyDocJSONObject.patDocType+"'>"+(veriyDocJSONObject.patDocTypeName)+"</option>";

	}
	if(document.getElementsByName(elementName).length!=0)
		document.getElementsByName(elementName)[0].innerHTML=optionText;





},processGeneralNode:function(elementName,elementNode)
{
	var optionText="<option value='0'>Select Value</option>";

	if(elementNode!=null){
		for(var i=0;i<elementNode.childNodes.length;i++ )
		{
			var optionNode=elementNode.childNodes[i];
			optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";

		}
	}

	if(document.getElementsByName(elementName).length!=0)
		document.getElementsByName(elementName)[0].innerHTML=optionText;

},setdefaultVariables:function(elementNode)
{
	for(var i=0;i<elementNode.attributes.length;i++ )
	{

		if(document.getElementsByName((elementNode.attributes[i].name.substring(7)))[0]!=undefined){
			eval("var "+elementNode.attributes[i].name+"='"+elementNode.attributes[i].value+"'");
			//	alert("substr :"+elementNode.attributes[i].name.substring(7));
			if (!(elementNode.attributes[i].name == "defaultpatAddPIN"))
				document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].value = eval(elementNode.attributes[i].name);
			if(elementNode.attributes[i].name=="defaultpatAddCountryCode"){
				document.getElementsByName("defaultpatAddCountryCode")[0].value=eval(elementNode.attributes[i].name);
			}
			if((document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].name)=='isActualDob'){
				ageSelection((eval(elementNode.attributes[i].name)));
			}
			if((document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].name)=='patDocType'){
				getDocType((eval(elementNode.attributes[i].name)));
			}
			
			//var temp="'onchange_"+elementNode.attributes[i].name.substring(7)+"(this)'";
		}
	}
	$("#patAddCountryCodeId").bind("change",UnknownToKnown.onchange_patAddCountryCode);


},
onchange_patAddCountryCode:function()
{
	var objCountry  =document.getElementsByName("patAddCountryCode")[0];
	var countryCode = objCountry.options[objCountry.selectedIndex].value;
	var action 		= "/HISRegistration/registration/transactions/getStatePatientDetailMod.action?countryCode="+countryCode;
	var flagStateNDIstComboReqd=true;

	document.getElementsByName("patAddCountry")[0].value=objCountry.options[objCountry.selectedIndex].text;

	if(countryCode!=registrationDeskDefaultCountryCode){
		$("#divStateComboId").hide();
		$("#divStateTextId").show();
		$('[name="patAddState"]')[0].value="";
		$("#divDistrictComboId").hide();
		$("#divDistrictTextId").show();
		$('[name="patAddDistrict"]')[0].value="";
		flagStateNDIstComboReqd=false;
	}else{
		$("#divStateTextId").hide();
		$("#divStateComboId").show();
		$("#patAddStateCodeId").html("<option value='0'>Select Value</option>");
		//document.getElementsByName("patAddStateCode")[0].innerHTML="<option value='0'>Select Value</option>";
		$("#divDistrictTextId").hide();
		$("#divDistrictComboId").show();
		$("patAddDistrictCodeId").html("<option value='0'>Select Value</option>");
		//document.getElementsByName("patAddDistrictCode")[0].innerHTML="<option value='0'>Select Value</option>";
	}


	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
		var returnDocument=data;
		var rootNode=returnDocument.getElementsByTagName("root")[0];
		for(var i=0;i<rootNode.childNodes.length;i++ )
		{
			var elementNode=rootNode.childNodes[i];
			var elementName=elementNode.nodeName;

			UnknownToKnown.processGeneralNode(elementName,elementNode);
		}

		if(flagStateNDIstComboReqd)
		{
			$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[0]']});
			$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[0]']});
			$("#patAddStateCodeId").bind("change",UnknownToKnown.onchange_patAddStateCode);
		}
		else
		{
			$('[name="patAddState"]').validatebox({required: true,	validType: 'alphaNumSpecialChar' });
			$('[name="patAddDistrict"]').validatebox({required: true,	validType: 'alphaNumSpecialChar' });
			$('[name="patAddStateCode"]').validatebox(null);
			$('[name="patAddDistrictCode"]').validatebox(null);
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
	var action 			= "/HISRegistration/registration/transactions/getDistrictPatientDetailMod.action?countryCode="+countryCode+"&stateCode="+stateCode;


	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
		var returnDocument=data;
		var rootNode=returnDocument.getElementsByTagName("root")[0];
		for(var i=0;i<rootNode.childNodes.length;i++ )
		{
			var elementNode=rootNode.childNodes[i];
			var elementName=elementNode.nodeName;

			UnknownToKnown.processGeneralNode(elementName,elementNode);
		}

		},error: function(errorMsg,textstatus,errorthrown) {
			alert('onchange_patAddCountryCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);

		}});
}, 
onchange_patAddCountryCodeDefault:function()
{
	var objCountry  =document.getElementsByName("patAddCountryCode")[0];
	var countryCode = objCountry.options[objCountry.selectedIndex].value;
	var flagStateNDIstComboReqd=true;

	document.getElementsByName("patAddCountry")[0].value=objCountry.options[objCountry.selectedIndex].text;
	if(countryCode!=registrationDeskDefaultCountryCode){
		$("#divStateComboId").hide();
		$("#divStateTextId").show();
		$("#divDistrictComboId").hide();
		$("#divDistrictTextId").show();
		flagStateNDIstComboReqd=false;
	}else{
		$("#divStateTextId").hide();
		$("#divStateComboId").show();
		$("#divDistrictTextId").hide();
		$("#divDistrictComboId").show();
	}
	if(flagStateNDIstComboReqd)
	{
		$('[name="patAddState"]').validatebox({required: false,	validType: 'alphaWithSpace' });
		$('[name="patAddDistrict"]').validatebox({required: false,	validType: 'alphaWithSpace' });
		$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[0]']});
		$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[0]']});
	}
	else
	{
		$('[name="patAddState"]').validatebox({required: true,	validType: 'alphaWithSpace' });
		$('[name="patAddDistrict"]').validatebox({required: true,	validType: 'alphaWithSpace' });
		$('[name="patAddStateCode"]').validatebox({validType: null});
		$('[name="patAddDistrictCode"]').validatebox({validType: null});
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
	//alert(veriyDocJSONArray.length	);
	for(var i=0; i<veriyDocJSONArray.length; i++){
		if(veriyDocJSONArray[i].patDocType==docType){
			$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);

			docValidType= UnknownToKnown.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);

			var isize = veriyDocJSONArray[i].patDocIdSize;
			var docValidTypeNLength='equalLength['+isize+']';
			
			$('[name="patCardNo"]').validatebox({required: true, validType: [docValidType,docValidTypeNLength]});
			
			break;
		}else{
			$('[name="patCardNo"]').validatebox({required: false});
		}
	}
},

getDocValidtype:function(idValidTypeCode){
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
/*Modify Date			: 24thNov'14
Reason	(CR/PRS)	: Secondary UHID check incorporation
Modify By				: Sheeldarshi */


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
		
		$("#UnknownToKnown").attr('action',"/HISRegistration/registration/transactions/saveAsNewPatientUnknownToKnown.action");
		var namevalidate=true;
		if($('[name="patFirstName"]')[0].value=='Unknown'){
			namevalidate=false;
			$('#validateNameError').show();
		}
		else{
			namevalidate=true;		
			$('#validateNameError').hide();
		}
	
		if($('#UnknownToKnown').form('validate')&&namevalidate==true)
		{
			sortandEncodebase64($("#UnknownToKnown"));
			$('#UnknownToKnown').submit();
		}
		$('#submitId').hide();
		

	},
	  showDuplicatePatientPopup : function() {

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
						  UnknownToKnown.saveAsNewPatientDtl();
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
//End:Sheeldarshi
};// end of UnknownToKnown Object Method


function setMotherValidRule(){

	var flagYes=false;
	var allowSpouse = true;
	if($('[name="isActualDob"]')[0].checked && $('[name="patAgeUnit"]')[0].value!="-1"){
		if($("#patAgeId").val() < calculateMaxRangeValue(12,$("#patAgeUnitId").val())){
			flagYes=true;
		}
		if ($("#patAgeId").val() < calculateMaxRangeValue(18,
				$("#patAgeUnitId").val())) {
			allowSpouse = false;
		}
	
	}else if($('[name="patDOB"]')[0].value!="" && isDate($('[name="patDOB"]')[0],"dd-mmm-yyyy")){
		var d1 = $.datepicker.parseDate("dd-M-yy", $("#patDOBId").val());
		var ctDt= new Date();
		var yr = d1.getFullYear();
		var ctDtYr = ctDt.getFullYear();
		if((ctDtYr-12) <= yr){
		{
			flagYes=true;
		}
		
	}
		var temp =ctDtYr-18;
		ctDt.setFullYear(temp, ctDt.getMonth());
		if(ctDt<=d1)
		{
			allowSpouse = false;
		}
	//alert("flagYes :"+flagYes);
	if(flagYes){
		$('[name="patMotherName"]').validatebox({required: true,	validType: 'alphaWithSpace'});
	}else{
		$('[name="patMotherName"]').validatebox({required: false,	validType: 'alphaWithSpace'});
	}
	
}
	if(allowSpouse)
		$('[name="patHusbandName"]').removeAttr("disabled"); 
	else{
		$('[name="patHusbandName"]').val("");
		$('[name="patHusbandName"]').attr("disabled", "disabled");
	}
};

$("#patDocTypeId").change(function(){
	UnknownToKnown.onchange_patDocType();
	/*var docTypeObj = $('[name="patDocType"]')[0];
	var docType= docTypeObj.options[docTypeObj.selectedIndex].value;
	for(var i=0; i<veriyDocJSONArray.length; i++){
		if(veriyDocJSONArray[i].patDocType==docType){
			$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);

			docValidType= UnknownToKnown.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);

			$('[name="patCardNo"]').validatebox({required: true, validType: docValidType});
			break;
		}else{
			$('[name="patCardNo"]').validatebox({required: false, validType: 'alphaSpecialChar'});
		}
	}*/
});

$.datepicker.setDefaults( 
		{showOn: 'both',
			defaultDate: new Date(),
			buttonImageOnly: true, 
			buttonText : "Select Date",
			buttonImage: "../../hisglobal/images/calendar-icon.gif"
		}); 

$("#patAddStateCodeId").bind("change",UnknownToKnown.onchange_patAddStateCode);
$('[name="patAddCountryCode"]').validatebox({
	validType: ['selectCombo[0]']
});
$('[name="patFirstName"]').validatebox({
	required: true,	
	validType: ['alphaWithSpace']
});
$('[name="patLastName"]').validatebox({
	validType: 'alphaWithSpace'
});
$('[name="patMiddleName"]').validatebox({
	validType: 'alphaWithSpace'
});
$('[name="patGenderCode"]').validatebox({
	validType: ['selectCombo[0]']
});
$('[name="patGuardianName"]').validatebox({
	required: true,
	validType: 'alphaWithSpace'
});
$('[name="patHusbandName"]').validatebox({
	validType: 'alphaWithSpace'
});

$('[name="patMotherName"]').validatebox({
	validType: 'alphaWithSpace'
});

$('[name="patBirthPlace"]').validatebox({
	validType: 'alphaWithSpace'
});
$('[name="patAddStateName"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddHNo"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddStreet"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddCityLoc"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddDistrict"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddCity"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddPIN"]').validatebox({
	validType : [ 'numericwithoutspace','startZero' ,'equalLength[6]']
});

$('[name="patAddLandMarks"]').validatebox({
	validType: 'alphaNumSpecialChar'
});

$('[name="patAddEmailId"]').validatebox({
	validType: 'email'
});
$('[name="patAddPhoneNo"]').validatebox({
	validType : ['telephone','minLengthNumeric[5]','DisableAllZero']	
});
$('[name="patAddMobileNo"]').validatebox({
	required: true,
	validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero']
});

$('[name="requestBy"]').validatebox({
	validType: ['selectCombo[-1]']
});
$('[name="selectedVerifyDocs"]').validatebox({
	validType: ['minLength[1]']
});
if($('[name="isDuplicatePatient"]').val()!=1){
$('[name="arrSelectedVerifyDocs"]').validatebox({
	required: true
});
}
$('[name="patMonthlyIncome"]').validatebox({
	validType: 'numericNew'
});
$('[name="patIdMark1"]').validatebox({required: true,validType: ['spaceCheck','consecutiveSpecialChar']});
	$('[name="patIdMark2"]').validatebox({required: true,validType: ['spaceCheck','consecutiveSpecialChar']});
$('[name="patNationalId"]').validatebox({
	validType : [ 'numeric','equalLength[12]']
});
$('[name="requestBy"]').change(function(){
	var obj = document.getElementsByName("requestBy")[0];
	if(!obj)	return;
	if(obj.value=="1")
	{
		$('[name="requestRelation"]').validatebox({
			validType: ['selectCombo[0]']
		});
		$('[name="requestByName"]').validatebox({
			required: true,
			validType: 'alphaWithSpace'
		});

		$('[name="requestByAddress"]').validatebox({
			required: true,
			validType: 'alphaNumSpecialChar'
		});
		$('[name="constableDesig"]').validatebox({
			required: false,
			validType: 'alphaNumSpecialChar'
		});

		$('[name="constableBadgeNo"]').validatebox({
			required: false,
			validType: 'alphaNumSpecialChar'
		});

	}
	else if(obj.value=="2"){
		
		$('[name="requestRelation"]').validatebox({
			validType: null
		});
		$('[name="requestByName"]').validatebox({
			required: true,
			validType: 'alphaNumSpecialChar'
		});

		$('[name="requestByAddress"]').validatebox({
			required: true,
			validType: 'alphaNumSpecialChar'
		});
		$('[name="constableDesig"]').validatebox({
			required: true,
			validType: 'alphaNumSpecialChar'
		});

		$('[name="constableBadgeNo"]').validatebox({
			required: true,
			validType: 'alphaNumSpecialChar'
		});
		
	}
else if(obj.value=="0"){
		
		$('[name="requestRelation"]').validatebox({
			validType: null
		});
		$('[name="requestByName"]').validatebox({
			required: false,
			validType: null
		});

		$('[name="requestByAddress"]').validatebox({
			required: false,
			validType: null
		});
		$('[name="constableDesig"]').validatebox({
			required: false,
			validType: null
		});

		$('[name="constableBadgeNo"]').validatebox({
			required: false,
			validType: null
		});
}
});

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

function fatherorSpouseerror()
{var fatherName = $('[name="patGuardianName"]')[0].value;
var spouseName = $('[name="patHusbandName"]')[0].value;
if((fatherName == "" || fatherName == null) && (spouseName == "" || spouseName == null))
	$('#fatherorSpouseError').show();
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
//Submit form if valid


$('#submitId').click(function(e){

	if(document.getElementsByName("isActualDob")[0].checked){
		$('[name="tempIsActualDOB"]')[0].value="0";
	}
	else{
		$('[name="tempIsActualDOB"]')[0].value="1";
	}
	if( $('[name="patAddCountryCode"]')[0].value == $('[name="defaultpatAddCountryCode"]')[0].value){
		$('[name="patAddState"]')[0].value 	= $("#patAddStateCodeId option:selected").text();
		$('[name="patAddDistrict"]')[0].value = $("#patAddDistrictCodeId option:selected").text();
		$('[name="patGender"]')[0].value 		= $("#patGenderCodeId option:selected").text();
		$('[name="patCaste"]')[0].value 		= $("#patCasteCodeId option:selected").text();

	}

	
	
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
	fatherorSpouseerror();
	fatherorSpouse();
	setMotherValidRule();
	$("#UnknownToKnown").attr('action',"/HISRegistration/registration/transactions/SAVEUnknownToKnown.action");
	//document.forms[0].action = "saveVerificationDoc.action";
	var namevalidate=true;
	if($('[name="patFirstName"]')[0].value=='Unknown'){
		namevalidate=false;
		$('#validateNameError').show();
	}
	else{
		namevalidate=true;		
		$('#validateNameError').hide();
	}
	
	if($('[name="arrSelectedVerifyDocs"]')[0].value==''){
		$('#validateDocError').show();
	}
	else
		$('#validateDocError').hide();


	if($('#UnknownToKnown').form('validate')&&namevalidate==true)
		$('#UnknownToKnown').submit();
	
	if($('#UnknownToKnown').form('validate')){
		/*Modify Date			: 24thNov'14
		Reason	(CR/PRS)		: Secondary UHID check incorporation
		Modify By				: Sheeldarshi */
		
		$('#submitId').hide();
		}
	else
		{
		//End:Sheeldarshi		
		return false;
	}
	
	//e.preventDefault();
}); 

////////////////////////jquery Validation   /////////////////

