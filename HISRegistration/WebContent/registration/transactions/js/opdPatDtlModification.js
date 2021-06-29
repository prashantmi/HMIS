
var docValidType = 'alphaNumSpecialChar';
var ageBoundRange='125';
var patCountryCodeTemp='gfhdgh';
//Start:Sheeldarshi
//Reason:Bug 10166 - System should be able to validate senior citizen category	
/*function showDivAgeDob(){
	//alert("Inside the showDivAgeDobNew patAge"+$('[name="defaultPatAge"]').val()+" Age Unit "+$('[name="defaultPatAgeUnit"]').val()+" DOB "+$('[name="defaultPatDOB"]').val())

	if(document.getElementsByName("isActualDob")[0].checked){
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
		var ageRangeValidType='range[1,'+maxAgeRange+']';
		//
		$('[name="patAge"]').validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
		$("#patAgeUnitId").validatebox({required: true, validType: ['selectCombo[0]']});
		$("#patDOBId").validatebox({required: false,validType:null});
		
		$('[name="patAge"]').val($('[name="defaultPatAge"]').val());
		$('[name="patAgeUnit"]').val($('[name="defaultPatAgeUnit"]').val());
		$("#patDOBId").val("");

	}else{
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="";

		$("#patAgeId").validatebox({required: false,validType:null});
		$("#patAgeUnitId").validatebox({required: false,validType:null});
		$("#patAgeId").val("");
		$(function() {
			$("#patDOBId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
			var _birthDate=$.datepicker.parseDate("dd-mm-yy", $('[name="defaultPatDOB"]').val());
			_birthDate=_birthDate.toLocaleFormat('%d-%b-%Y');
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_birthDate,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_birthDate);
			
		});
		//alert(ageBoundRange);
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
			validType : [ 'numeric', ageRangeValidType,'startZero' ]
		});
		$("#patAgeUnitId").validatebox({
			required : true,
			validType : [ 'selectCombo[-1]' ]
		});
		$("#patDOBId").validatebox({
			required : false,
			validType :null 
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
		$("#patAgeId").val("");
		$(function() {
			if($("#patDOBId").val()!=""){
				var _date=$.datepicker.parseDate("dd-M-yy", $("#patDOBId").val());
				/*_date=_date.toLocaleFormat('%d-%b-%Y');*/
				_date=moment(_date).format('DD-MMM-YYYY');
				$('#patDOBId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:_date,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(_date);
			}
			else{
				var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
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
/*Modify Date			: 5thDec'14
Reason	(CR/PRS)		: Secondary UHID check incorporation
Modify By				: Sheeldarshi */
function showpopuponload()
{var isDuplicate=document.getElementsByName("isDuplicatePatient")[0].value;

	
	 if (isDuplicate == "1")
		 patDtlModification.showDuplicatePatientPopup();	
}
//End:Sheeldarshi

function showDivAgeDobNew(){
	//
	if(document.getElementsByName("isActualDob")[0].checked){
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		$('[name="patAge"]').numberbox({required: true,	validType: ['minLength[1]']});
		$('[name="patDOB"]').validatebox({required: false});

	}else{
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="";
		$('[name="patAge"]').numberbox({required: false});
		$(function() {
			/*$( '[name="patDOB"]').datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
			var _birthDate=$.datepicker.parseDate("dd-mm-yy", $('[name="defaultPatDOB"]').val());
			//_birthDate=_birthDate.toLocaleFormat('%d-%b-%Y');
			_birthDate=moment(_birthDate).format('DD-MMM-YYYY');
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_birthDate,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_birthDate);

		});
		$('[name="patDOB"]').validatebox({required: true});
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
			var _birthDate=$.datepicker.parseDate("dd-mm-yy", $('[name="defaultPatDOB"]').val());
			_birthDate=moment(_birthDate).format('DD-MMM-YYYY');
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_birthDate,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_birthDate);

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
	if(docType!="-1" && docType!=""){
		$("#divCardNoId").show();

	}else{
		$('[name="patCardNo"]').validatebox({required: false});
		$("#divCardNoId").hide();
	}
	for(var i=0; i<veriyDocJSONArray.length; i++){
		if(veriyDocJSONArray[i].patDocType==docType){
			$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);
			docValidType= patDtlModification.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);
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
	//
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
	//
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//
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
	//
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

var patDtlModification={fetchDefaultValues:function (){

	var action 			= "/HISRegistration/registration/transactions/populateformvaluesPatientDetailMod.action";


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
				patDtlModification.processVerifyDocNode(elementName,elementNode);
			}
			else if(elementName=='defaults')
			{

			}
			else if (elementName == 'renewalConfig') {
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
				/*
				 * else{ $("#divAlreadyRegisteredId").hide(); }
				 */
			}
			else
			{
				patDtlModification.processGeneralNode(elementName,elementNode);
			}

		}
		//ageSelection(eval(rootNode.getElementsByTagName("defaults")[0].attributes[].name));
		patDtlModification.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
		//By Mukund to show the patient current address tile on 28.09.2016
		if(document.getElementsByName('patFBAddTypeCode')[0].value=='1')
			patDtlModification.setModAddType(document.getElementsByName('patFBAddTypeCode')[0],'1');
		//end : Mukund
		},error: function(errorMsg,textstatus,errorthrown) {
			alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);

		}
	});


},setAddType:function(obj,mode){
	//alert(obj)
	//alert("setAddType mode"+mode);
	var action 			= "";
	if(mode=='1')
		action 			= "/HISRegistration/registration/transactions/ADDADDRESSPatientDetailMod.action";
	if(mode=='2'){
		action 			= "/HISRegistration/registration/transactions/ADDADDRESSPatientDetailMod.action";
		document.getElementById("sameCurAddId").style.display="";
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
			if(elementName=='defaults')
			{

			}
			else
			{
				patDtlModification.processGeneralNode(elementName,elementNode);
			}
		}
		//alert(document.getElementsByName("patFBAddTypeCode").length);
for(var i=0;i<document.getElementsByName("patFBAddTypeCode").length;i++){
	document.getElementsByName("patFBAddTypeCode")[i].checked=false;
}
		patDtlModification.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
		
		document.getElementById("AddModdivIf").style.display = "";
		//$("#AddModdivIf").slideToggle();

		},error: function(errorMsg,textstatus,errorthrown) {
			alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);

		}
	});
	var addModify = document.getElementsByName("addModify")[0].value; 
	//alert("addModify"+addModify);

	if(document.getElementById("addTypeCombo")&& !document.getElementsByName("patSameAsCurrentAddCheck")[0].checked)
	{
		document.getElementById("addTypeCombo").style.display="";
		document.getElementById("addTypeText").style.display="none";				
	}
	if(addModify==isAddressModified && !document.getElementsByName("patSameAsCurrentAddCheck")[0].checked)
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

},setModAddType:function(obj,mode){

	//alert(obj.value);
	//alert("setModAddType mode"+mode);
	var action="";
	if(mode=='1')
		action 			= "/HISRegistration/registration/transactions/MODIFYADDRESSPatientDetailMod.action?fbAddTypeCode="+obj.value;
	else if(mode=='2')
		action 			= "/HISRegistration/registration/transactions/SAMEASCURRENTADDRESSMODIFYPatientDetailMod.action?fbAddTypeCode=1&fbAddressLabel="+document.getElementsByName('patAddTypeLable')[0].value;
	else if(mode=='3')
		action 			= "/HISRegistration/registration/transactions/SAMEASCURRENTADDRESSADDPatientDetailMod.action";

	
	//alert(action);
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
				patDtlModification.processGeneralNode(elementName,elementNode);
			}
		}
		
		
		var elementNode1=rootNode.getElementsByTagName("defaults")[0];
		
		for(var i=0;i<elementNode1.attributes.length;i++ )
		{

			if(document.getElementsByName((elementNode1.attributes[i].name.substring(7)))[0]!=undefined){
				eval("var "+elementNode1.attributes[i].name+"='"+elementNode1.attributes[i].value+"'");
				document.getElementsByName(elementNode1.attributes[i].name.substring(7))[0].value=eval(elementNode1.attributes[i].name);
				
				if(elementNode1.attributes[i].name=="defaultpatAddCountryCode"){
					document.getElementsByName("defaultpatAddCountryCode")[0].value=eval(elementNode1.attributes[i].name);
					patCountryCodeTemp=eval(elementNode1.attributes[i].name);
				}
			}
		}
		
		var addModify = document.getElementsByName("addModify")[0].value; 
			if(document.getElementById("addTypeCombo") && !document.getElementsByName("patSameAsCurrentAddCheck")[0].checked)
			{
				document.getElementById("addTypeCombo").style.display="none";
				document.getElementById("addTypeText").style.display="";
				$('[name="patAddTypeCode"]').validatebox({
					validType: null
				});
			}
			if(addModify==isAddressModified && !document.getElementsByName("patSameAsCurrentAddCheck")[0].checked)
			{
				document.getElementsByName("patAddTypeCode")[0].value=document.getElementsByName("patFBAddTypeCode")[0].value;
			}
			//showState();

			$('[name="patAddCountryCode"]').validatebox({
				validType: ['selectCombo[0]']
			});
			
			var objCountry  =document.getElementsByName("patAddCountryCode")[0];
			var countryCode = objCountry.options[objCountry.selectedIndex].value;
			var flagStateNDIstComboReqd=true;

			
			if(patCountryCodeTemp!=registrationDeskDefaultCountryCode)
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
		
		////////////////////////////////////////////////////////////////////////////////
			
			
			//////////////////////////////////////////////////////
		
		document.getElementById("AddModdivIf").style.display = "";
		//$("#AddModdivIf").slideToggle();

		},error: function(errorMsg,textstatus,errorthrown) {
			alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);

		}
	});
	
	
	//patDtlModification.onchange_patAddCountryCodeDefault();
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
	var optionText="";
	if(elementName=="patIsUrban")
		 optionText="<option value='-1'>Select Value</option>";
	else
		 optionText="<option value='0'>Select Value</option>";

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
			if (!(elementNode.attributes[i].name == "defaultpatAddPIN"))
				document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].value = eval(elementNode.attributes[i].name);
			
			if(elementNode.attributes[i].name=="defaultpatAddCountryCode"){
				document.getElementsByName("defaultpatAddCountryCode")[0].value=eval(elementNode.attributes[i].name);
				patCountryCodeTemp=eval(elementNode.attributes[i].name);
			//	alert(patCountryCodeTemp)
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
	$("#patAddCountryCodeId").bind("change",patDtlModification.onchange_patAddCountryCode);


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

			patDtlModification.processGeneralNode(elementName,elementNode);
		}

		if(flagStateNDIstComboReqd)
		{
			$('[name="patAddState"]').validatebox({required: false,	validType: 'alphaWithSpace' });
			$('[name="patAddDistrict"]').validatebox({required: false,	validType: 'alphaWithSpace' });
			$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[0]']});
			$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[0]']});
			$("#patAddStateCodeId").bind("change",patDtlModification.onchange_patAddStateCode);
		}
		else
		{
			$('[name="patAddState"]').validatebox({required: true,	validType: 'alphaWithSpace' });
			$('[name="patAddDistrict"]').validatebox({required: true,	validType: 'alphaWithSpace' });
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


	//
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
		var returnDocument=data;
		var rootNode=returnDocument.getElementsByTagName("root")[0];
		for(var i=0;i<rootNode.childNodes.length;i++ )
		{
			var elementNode=rootNode.childNodes[i];
			var elementName=elementNode.nodeName;

			patDtlModification.processGeneralNode(elementName,elementNode);
		}

		},error: function(errorMsg,textstatus,errorthrown) {
			alert('onchange_patAddCountryCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);

		}});
}, 
onchange_patAddCountryCodeDefault:function()
{
	
	var flagStateNDIstComboReqd=true;

	//alert(patCountryCodeTemp)
//	
	document.getElementsByName("patAddCountry")[0].value=objCountry.options[objCountry.selectedIndex].text;
	if(obj.value!=registrationDeskDefaultCountryCode){
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
		$('[name="patAddState"]').validatebox({required: false,	validType: 'alphaNumSpecialChar' });
		$('[name="patAddDistrict"]').validatebox({required: false,	validType: 'alphaNumSpecialChar' });
		$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[0]']});
		$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[0]']});
	}
	else
	{
		$('[name="patAddState"]').validatebox({required: true,	validType: 'alphaWithSpace' });
		$('[name="patAddDistrict"]').validatebox({required: true,	validType: 'alphaWithSpace' });
		$('[name="patAddStateCode"]').validatebox(null);
		$('[name="patAddDistrictCode"]').validatebox(null);
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

			docValidType= patDtlModification.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);

			var isize = veriyDocJSONArray[i].patDocIdSize;
			if(isize!="")
			{
		var docValidTypeNLength = 'equalLength[' + isize + ']';
		$('[name="patCardNo"]').validatebox({
			required : true,
			validType : [ docValidType, docValidTypeNLength ]
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

getDocValidtype:function(idValidTypeCode){
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
setSelectedAddress:function(obj){
	//alert(document.getElementsByName("patSameAsCurrentAddCheck")[0].checked);
	
	
	if(document.getElementsByName("patFBAddTypeCode")[0].checked)
		document.getElementById("sameCurAddId").style.display="none";
	else
		document.getElementById("sameCurAddId").style.display="";
	
	//if(!$('#AddModdivIf').css('display') == 'none')
	//	$("#AddModdivIf").slideToggle();
		
	if(document.getElementsByName("patSameAsCurrentAddCheck")[0].checked)
		patDtlModification.setModAddType(obj,'2');
	else
		patDtlModification.setModAddType(obj,'1');
	
},
setSameCurrentAddress:function(obj){
	//alert(document.getElementsByName("patSameAsCurrentAddCheck")[0].checked);
	//alert(document.getElementsByName("patFBAddTypeCode").length);
	var lenAddType=document.getElementsByName("patFBAddTypeCode").length;
	var isMod='0',tempObj="";
	for (i=0;i<lenAddType;i++){
		if(document.getElementsByName("patSameAsCurrentAddCheck")[0].checked){	
			if(document.getElementsByName("patFBAddTypeCode")[i].checked){
				isMod='1';
			}else
				document.getElementsByName("patFBAddTypeCode")[i].disabled=true;
		}
		else{
			//document.getElementsByName("patFBAddTypeCode")[i].disabled=false;
			if(document.getElementsByName("patFBAddTypeCode")[i].checked)
				tempObj=document.getElementsByName("patFBAddTypeCode")[i];
			document.getElementsByName("patFBAddTypeCode")[i].disabled=false;
		}
	}
	
	//alert(isMod);
	
	//if(document.getElementById("AddModdivIf").style.display=="block")
	//if(!$('#AddModdivIf').css('display') == 'none')
	//	$("#AddModdivIf").slideToggle();
	
    if(isMod=='1'){
    	//alert("In Mod 1");
    	if(document.getElementsByName("patSameAsCurrentAddCheck")[0].checked)
    		patDtlModification.setModAddType(obj,'2');
    	else{
    		patDtlModification.setAddType(obj,'1');
    	}
    }
	else{
		//alert("In Mod 2");
		if(document.getElementsByName("patSameAsCurrentAddCheck")[0].checked)
    		patDtlModification.setModAddType(obj,'3');
		else{
    		patDtlModification.setModAddType(tempObj,'1');
		}
	}
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
		showDivAgeDob();
		$("#PatientDetailModificationAction").attr('action',"/HISRegistration/registration/transactions/saveAsNewPatientPatientDetailMod.action");
		var namevalidate=true;
		if($('[name="patFirstName"]')[0].value=='Unknown'){
			
			namevalidate=false;
			$('#validateNameError').show();
		}
		else{
			namevalidate=true;		
			
			$('#validateNameError').hide();
		}
	
		//if($('#PatientDetailModificationAction').form('validate')&&namevalidate==true)
			
		$('#PatientDetailModificationAction').submit();
		$('#submitId').hide();
		

	},
	  showDuplicatePatientPopup : function() {

			var windowWidth = $(window).width() - 50;
			var windowHeight = 285;

			var page="/HISRegistration/registration/transactions/jsp/opdNewRegDuplicatePopup.jsp";

			var $dialog = $('<div></div>').html(
					'<iframe style="border: 0px; " src="' + page
							+ '" width="100%" height="100%"></iframe>').dialog({
				autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
				title : "Patient Detail Exists",	show: { effect: "clip"},resizable: false,
				position: ['top', 100],dialogClass: 'no-close custbtncolor',
				buttons:{
					"Continue": function() {

					 	var selecteCRToRevist = $('[name="selectedCRToVisit"]').val();
					 	if(selecteCRToRevist==undefined || selecteCRToRevist==null || selecteCRToRevist=='')
						{
							alert("Please select a record!");
						}
						else
						{
							var matCri = selecteCRToRevist.split("&&")[1];
							if(matCri=='A1' || matCri=='B2')
							{
								patDtlModification.continueWithExisting();
								$(this).dialog("close"); 
								
							}	
							else if(matCri=='C3' || matCri=='D4')
							{
								$(this).dialog("close"); 
								patDtlModification.saveAsNewPatientDtl();
							}
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
		 continueWithExisting: function(){
				var tempCrStorage = $('[name="selectedCRToVisit"]').val();
				var selecteCRToRevist = tempCrStorage.split("&&")[0];
				$('[name="selectedCRToVisit"]').val(selecteCRToRevist);
				var CrNoObj = document.getElementsByName('selectedCRToVisit')[0];
				if(validateCRNoCHECK_forOdisha('15', CrNoObj))
				{
					var url="/HISRegistration/registration/transactions/PatientVisit.action?patCrNo="+selecteCRToRevist+"&isDuplicateRegistered=1&selectedDuplicatePatCrNo="+selecteCRToRevist;//+"&slNoHashUhidForDupCont="+slNoHashUhidForDupCont;
					var menu = 'Patient_Revisit';
					parent.ajaxStartMenu();
					menu=menu.toString().replace(/_/g,' ').replace(/XXY/g,'/').replace(/XXX/g,'&');
					//url = Base64.decode(url).toString();
					parent.callMenu(url,menu);
					parent.ajaxCompleteMenu();
				}
			}
//End:Sheeldarshi
};// end of patDtlModification Object Method


function setMotherValidRule(){
	var flagYes=false;
	//
	var allowSpouse = true;
	var fatherName = $('[name="patGuardianName"]')[0].value;
	if($('[name="isActualDob"]')[0].checked && $('[name="patAgeUnit"]')[0].value!="-1"){
		/*if($("#patAgeId").val() < calculateMaxRangeValue(12,$("#patAgeUnitId").val())){
			flagYes=true;
		}*/
		if($("#patAgeId").val() < calculateMaxRangeValue(12,$("#patAgeUnitId").val())&&(fatherName == "" || fatherName == null)){
			flagYes=true;
		}
		else
			{
			   flagYes=false;
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
			flagYes=true;
		}
		var temp =ctDtYr-18;
		ctDt.setFullYear(temp, ctDt.getMonth());
		if(ctDt<=d1)
		{
			allowSpouse = false;
		}
		
	}
	if(flagYes)
		{

		$('[name="patMotherName"]').validatebox({required: true,	validType: 'alphaWithSpace'});
	}else{
		$('[name="patMotherName"]').validatebox({required: false,	validType: 'alphaWithSpace'});
	}
	if(allowSpouse)
		$('[name="patHusbandName"]').removeAttr("disabled"); 
	else{
		$('[name="patHusbandName"]').val("");
		$('[name="patHusbandName"]').attr("disabled", "disabled");
	}
}

$("#patDocTypeId").change(function(){
	patDtlModification.onchange_patDocType();
	/*var docTypeObj = $('[name="patDocType"]')[0];
	var docType= docTypeObj.options[docTypeObj.selectedIndex].value;
	for(var i=0; i<veriyDocJSONArray.length; i++){
		if(veriyDocJSONArray[i].patDocType==docType){
			$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);

			docValidType= patDtlModification.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);

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


$("#patAddStateCodeId").bind("change",patDtlModification.onchange_patAddStateCode);

/*$('[name="patFirstName"]').validatebox({
	required: true,	
	validType: ['alphaWithSpace']
});*/
$('[name="patFirstName"]').validatebox({
	required: true,	
	validType: ['alphaWithSpaceSlash']
});
$('[name="patLastName"]').validatebox({
	validType: 'alphaWithSpace'
});
$('[name="patMiddleName"]').validatebox({
	validType: 'alphaWithSpace'
});

$("#patFirstNameInMultiLangId").validatebox({
	validType: 'maxLengthFixed[33,\'patFirstNameInMultiLang\']'
});
$("#patMiddleNameInMultiLangId").validatebox({
	validType: 'maxLengthFixed[33,\'patMiddleNameInMultiLang\']'
});
$("#patLastNameInMultiLangId").validatebox({
	validType: 'maxLengthFixed[33,\'patLastNameInMultiLang\']'
});

$('[name="patGenderCode"]').validatebox({
	validType: ['selectCombo[0]']
});
$('[name="patGuardianName"]').validatebox({
	required: true,
	validType: 'alphaWithSpace'
});
$('[name="patHusbandName"]').validatebox({
	required: true,
	validType: 'alphaWithSpace'
});

$('[name="patMotherName"]').validatebox({
	required: true,
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
	validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
});
$('[name="patEmgCntNo"]').validatebox({
	validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
});

$('[name="requestBy"]').validatebox({
	validType: ['selectCombo[-1]']
});
$('[name="selectedVerifyDocs"]').validatebox({
	validType: ['minLength[1]']
});
$('[name="arrSelectedVerifyDocs"]').validatebox({
	required: true
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

$(document).ready(function(){
	  
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
		  /*Modify Date					: 4thDec'14
	  		Reason	(CR/PRS)			: Bug Id 7672
	  		Modify By                 	:  Sheeldarshi 
			validType: 'alpha'*/
			validType: 'alphaWithSpace'
			
		});

		$('[name="requestByAddress"]').validatebox({
			required: true,
			
			//validType: 'alpha'
			validType: 'alphaNumSpecialChar'
			//End:Sheeldarshi
		});


	}

});

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
//	alert("closeFunction");
	popupmodal.close=eval(func);
	//$.modal.destroy();
}




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
	//multilingualConversion(this,document.getElementById('patFirstNameInMultiLangId'));
	//multilingualConversion(this,document.getElementById('patMiddleNameInMultiLangId'));
	//multilingualConversion(this,document.getElementById('patLastNameInMultiLangId'));
	//alert($('[name="patFirstNameInMultiLang"]')[0].value);
	
	/*This loop will trim all leading and trailing spaces from input type fileds*/
	var allInputs = $(":input"); 
	allInputs.each(function() {
	        $(this).val($.trim($(this).val()));
	    });
	//End
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
	
	var patmarital = $('[name="patMaritalStatusCode"]')[0];
	if(patmarital.value!="-1"){
	var text = patmarital.options[patmarital.selectedIndex].innerHTML;
	$('[name="patMaritalStatus"]').val(text);//alert($('[name="patMaritalStatus"]')[0].value);
	}
	
	var patreligion = $('[name="patReligionCode"]')[0];
	if(patreligion.value!="-1"){
	var text = patreligion.options[patreligion.selectedIndex].innerHTML;
	$('[name="patReligion"]').val(text);//alert($('[name="patReligion"]')[0].value);
	}
	
	var patgender = $('[name="patGenderCode"]')[0];
	if(patgender.value!="-1"){
	var text = patgender.options[patgender.selectedIndex].innerHTML;
	$('[name="patGender"]').val(text);//alert($('[name="patGender"]')[0].value);
	}
	
	
	//var patcountry = $('[name="patAddCountryCode"]')[0];
	//var text = patcountry.options[patcountry.selectedIndex].innerHTML;
	//$('[name="patAddCountry"]').val(text);
	//alert($('[name="patGender"]')[0].value);

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

	fatherorSpouseerror();
	fatherorSpouse();
	setMotherValidRule();
	if($('[name="arrSelectedVerifyDocs"]')[0].value==''){
		$('#validateDocError').show();
	}
	else
		$('#validateDocError').hide();

	checkAffidavitNecessary();
	//alert("Inside Save");
	//
	$("#PatientDetailModificationAction").attr('action',"/HISRegistration/registration/transactions/SAVEPatientDetailMod.action");
	
	//document.forms[0].action = "saveVerificationDoc.action";

	if($('#PatientDetailModificationAction').form('validate'))
		
	/*Modify Date			: 24thNov'14
	Reason	(CR/PRS)		: Secondary UHID check incorporation
	Modify By				: Sheeldarshi */
		
	{
		
	sortandEncodebase64($("#PatientDetailModificationAction"));	
	$('#PatientDetailModificationAction').submit();
	
	$('#submitId').hide();
	}
else
	{
	return false;
	}
	//End:Sheeldarshi	
	//e.preventDefault();
}); 

function checkAffidavitNecessary()
{
	var needAffidavit=false,hasAffidavit=false,i;
	
	if($('[name="patIsDead"]')[0].value=="1" || $('[name="mlcNo"]')[0].value!="")
	{
		needAffidavit=true;
		for(i=0;i<$('[name="arrSelectedVerifyDocs"]').length;i++){
			var idx=$('[name="arrSelectedVerifyDocs"]')[i].value;
			//alert(idx.substring(0, idx.indexOf('#')))
			if(doc_Affidavit==idx.substring(0, idx.indexOf('#')))
				hasAffidavit=true;			
		}
	}
	
	if(needAffidavit&&!hasAffidavit){
		//$('#validateAffDocError').show();
		$('[name="isAffidavitDocumentAdded"]')[0].value="";
		$('[name="isAffidavitDocumentAdded"]').validatebox({
			required: true
		});
	}
	else{
		//$('#validateAffDocError').hide();
		$('[name="isAffidavitDocumentAdded"]')[0].value="1";
		$('[name="isAffidavitDocumentAdded"]').validatebox({
			required: false
		});
	}
}

function openVerDocPopup(event)
{
	var urlAction="/HISRegistration/registration/transactions/verificationDoc.action";
	var arrSelectedDocs="";
	for(i=0;i<$('[name="arrSelectedVerifyDocs"]').length;i++){
		var idx=$('[name="arrSelectedVerifyDocs"]')[i].value;
		arrSelectedDocs+=idx.substring(0, idx.indexOf('#'))+"@";
	}
	
	//alert($('[name="arrSelectedVerifyDocs"]').length);
	if($('[name="arrSelectedVerifyDocs"]').length>=1){
		urlAction+="?fbArrSelectedVerifyDocs="+arrSelectedDocs;
	
		if($('[name="patIsDead"]')[0].value=="1" || $('[name="mlcNo"]')[0].value!="")
			urlAction+="&fbIsAffdivate=1";	
		else
			urlAction+="&fbIsAffdivate=0";	
	}
	if(urlAction.indexOf("?")>0)
		urlAction+="&callerName=PatientDetailModificationAction";
	else
		urlAction+="?callerName=PatientDetailModificationAction";
	
	openPopup(urlAction,event,300,600);
	
}

function show(e)
{
	var processID = document.getElementsByName("processID")[0].value;
	var crNo = document.getElementsByName("crNo")[0].value;
	var filname = document.getElementsByName("filname")[0].value;
	//changed by manisha gangwar date: 02.04.2018 for fecthing from postgres
	//var url = "../../registration/transactions/EnlargedImage.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname; 
	var url = "../../registration/transactions/EnlargedImage.action";
	if($('[name="arrSelectedVerifyDocs"]')[0].value==''){
		alert("Please select verification documents first");
		return false;
	}
	
	//openURLInPopup(url,400,400,0,0);

	//openURLInPopup(url,600,400,0,0);
	openPopup(url,e,300,600);
}



function showDoc(e)
{
	var processID = document.getElementsByName("processID")[0].value;
	var processId1 = "<%=FileTransferConfig.PROCESS_ID_PATIENT_SCANNED_DOCUMENTS%>";
	//alert(processID);
	var url = "../../registration/transactions/EnlargedImage.action?strProcessId="+processID;
	if($('[name="arrSelectedVerifyDocs"]')[0].value==''){
		alert("Please select verification documents first");
		return false;
	}
	
	//openURLInPopup(url,400,400,0,0);

	//openURLInPopup(url,600,400,0,0);
	openPopup(url,e,300,600);
}


/*$("#patDocTypeId").change(function(){
	patDtlModification.onchange_patDocType();
});*/
////////////////////////jquery Validation   /////////////////

