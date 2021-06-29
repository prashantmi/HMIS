var docValidType = 'alphaNumSpecialChar';
var ageBoundRange=125;
//Start:Sheeldarshi
//Reason:Bug 10166 - System should be able to validate senior citizen category	
/*function showDivAgeDob(){
	
	if(document.getElementsByName("isActualDob")[0].checked){
		//alert("dsfsdfs");
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		var maxAgeRange=calculateMaxRangeValue(ageBoundRange,$("#patAgeUnitId").val());
		var ageRangeValidType='range[1,'+maxAgeRange+']';
		//alert("ageRangeValidType :"+ageRangeValidType);
		$('[name="patAge"]').validatebox({required: true,	validType: ['numeric',ageRangeValidType]});
		$("#patAgeUnitId").validatebox({required: true, validType: ['selectCombo[0]']});
		$("#patDOBId").validatebox({required: false , validType:null});
		
		$('[name="patAge"]').val($('[name="defaultPatAge"]').val());
		$('[name="patAgeUnit"]').val($('[name="defaultPatAgeUnit"]').val());
		$("#patDOBId").val("");

	}else{
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="";

		$("#patAgeId").validatebox({required: false, validType:null});
		$("#patAgeUnitId").validatebox({required: false, validType:null});
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

function showDivAge() {
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
function showDivAgeDobNew(){
	//alert("inside showDivAgeDob");
	if(document.getElementsByName("isActualDob")[0].checked){
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		$('[name="patAge"]').numberbox({required: true,	validType: ['numeric','startZero']});
		$('[name="patDOB"]').validatebox({required: false});
		
	}else{
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="";
		$('[name="patAge"]').numberbox({required: false});
		$(function() {
			/*$( '[name="patDOB"]').datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
			var _birthDate=$.datepicker.parseDate("dd-mm-yy", $('[name="defaultPatDOB"]').val());
			var wrappedDt = moment(_birthDate).format('DD-MMM-YYYY');
			//_birthDate=_birthDate.toLocaleFormat('%d-%b-%Y');
			//alert(wrappedDt);
			_birthDate = wrappedDt;
			$('#patDOBId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_birthDate,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_birthDate);
			
		});
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
			
			var _birthDate=$.datepicker.parseDate("dd-mm-yy", $('[name="defaultPatDOB"]').val());
			var wrappedDt = moment(_birthDate).format('DD-MMM-YYYY');
			//alert(wrappedDt);
			_birthDate=wrappedDt ;//_birthDate.toLocaleFormat('%d-%b-%Y');
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
			docValidType= patDtlModificationDesk.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);
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

var patDtlModificationDesk={fetchDefaultValues:function (){
		
		var action 			= "/HISRegistration/registration/transactions/populateformvaluesPatientDetailModDesk.action";
		
	
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
							patDtlModificationDesk.processVerifyDocNode(elementName,elementNode);
						}
						else if(elementName=='defaults')
						{
							
						}
						//Start:Sheeldarshi
						//Reason:Bug 10166 - System should be able to validate senior citizen category
						else if (elementName == 'renewalConfig') {							
								if (elementNode
										.getAttribute('seniorCitizenAgeLimit') != ""
										) {
									$('[name="seniorCitizenAgeLimit"]')[0].value = elementNode
											 .getAttribute('seniorCitizenAgeLimit');
									$('[name="seniorCitizenCatCode"]')[0].value = elementNode
									 .getAttribute('seniorCitizenCatCode');
								}
						}
								//End
						else
						{
							patDtlModificationDesk.processGeneralNode(elementName,elementNode);
						}
					   
					}
					//ageSelection(eval(rootNode.getElementsByTagName("defaults")[0].attributes[].name));
					patDtlModificationDesk.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
					var objCountry  =document.getElementsByName("patAddCountryCode")[0];
					var countryCode = objCountry.options[objCountry.selectedIndex].value;
					var flagStateNDIstComboReqd=true;

					//showDivAgeDob();
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
	var action 			= "/HISRegistration/registration/transactions/ADDADDRESSPatientDetailMod.action";
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
							patDtlModificationDesk.processGeneralNode(elementName,elementNode);
						}
				}
				
				patDtlModificationDesk.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
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
				
				patDtlModificationDesk.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
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

	patDtlModificationDesk.onchange_patAddCountryCodeDefault();
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
	
	if(elementName=="patIsUrban")
		 var optionText="<option value='-1'>Select Value</option>";
	else
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
			document.getElementsByName(elementNode.attributes[i].name
					.substring(7))[0].value = eval(elementNode.attributes[i].name);
		
		if(elementNode.attributes[i].name=="defaultpatAddCountryCode"){
			document.getElementsByName("defaultpatAddCountryCode")[0].value=eval(elementNode.attributes[i].name);
		}
		else if(elementNode.attributes[i].name=="defaultpatAddStateCode"){
			document.getElementsByName("defaultpatAddStateCode")[0].value=eval(elementNode.attributes[i].name);
			//alert(document.getElementsByName("defaultpatAddStateCode")[0].value);
		}else if(elementNode.attributes[i].name=="defaultpatAddDistrictCode"){
			document.getElementsByName("defaultpatAddDistrictCode")[0].value=eval(elementNode.attributes[i].name);
			//alert(document.getElementsByName("defaultpatAddDistrictCode")[0].value);
		}
		if((document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].name)=='isActualDob'){
			ageSelection((eval(elementNode.attributes[i].name)));
		}
		if((document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].name)=='patDocType'){
			getDocType((eval(elementNode.attributes[i].name)));
		}
		
		//var temp="'onchange_"+elementNode.attributes[i].name.substring(7)+"(this)'";

		
//		if((document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].name)=='patGenderCode'){
//			alert((eval(elementNode.attributes[i].name)));
//		}
//		if((document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].name)=='patDocType'){
//			getDocType((eval(elementNode.attributes[i].name)));
//		}
//		if((document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].name)=='patDocType'){
//			getDocType((eval(elementNode.attributes[i].name)));
//		}
		//var temp="'onchange_"+elementNode.attributes[i].name.substring(7)+"(this)'";
		}
	}
	$("#patAddCountryCodeId").bind("change",patDtlModificationDesk.onchange_patAddCountryCode);
	
	
},
onchange_patAddCountryCode:function()
{
	var objCountry  =document.getElementsByName("patAddCountryCode")[0];
	var countryCode = objCountry.options[objCountry.selectedIndex].value;
	var action 		= "/HISRegistration/registration/transactions/getStatePatientDetailMod.action?countryCode="+countryCode;
	var flagStateNDIstComboReqd=true;

	document.getElementsByName("patAddCountry")[0].value=objCountry.options[objCountry.selectedIndex].text;
//	alert(registrationDeskDefaultCountryCode)
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
					
					patDtlModificationDesk.processGeneralNode(elementName,elementNode);
				}
				
				if(flagStateNDIstComboReqd)
				{

					$('[name="patAddState"]').validatebox({required: false,	validType: 'alphaNumSpecialChar' });
					$('[name="patAddDistrict"]').validatebox({required: false,	validType: 'alphaNumSpecialChar' });
					$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[0]']});
					$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[0]']});
					$("#patAddStateCodeId").bind("change",patDtlModificationDesk.onchange_patAddStateCode);
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
					
					patDtlModificationDesk.processGeneralNode(elementName,elementNode);
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
					$('[name="patAddState"]').validatebox({required: false,	validType: 'alphaNumSpecialChar' });
					$('[name="patAddDistrict"]').validatebox({required: false,	validType: 'alphaNumSpecialChar' });
					$('[name="patAddStateCode"]').validatebox({validType: ['selectCombo[0]']});
					$('[name="patAddDistrictCode"]').validatebox({validType: ['selectCombo[0]']});
				}
				else
				{
					$('[name="patAddState"]').validatebox({required: true,	validType: 'alphaNumSpecialChar' });
					$('[name="patAddDistrict"]').validatebox({required: true,	validType: 'alphaNumSpecialChar' });
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
//	alert(veriyDocJSONArray.length	);
	for(var i=0; i<veriyDocJSONArray.length; i++){
		if(veriyDocJSONArray[i].patDocType==docType){
			$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);

			docValidType= patDtlModificationDesk.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);

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
}};// end of patDtlModification Object Method


function setMotherValidRule(){
	var flagYes=false;
	var fatherName = $('[name="patGuardianName"]')[0].value;
	//alert("inside setMotherValidRule()");
	var allowSpouse = true;
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

$('[name="patDocType"]').change(function(){
	patDtlModificationDesk.onchange_patDocType();
});

$.datepicker.setDefaults( 
	    {showOn: 'both',
	    	defaultDate: new Date(),
	    	buttonImageOnly: true, 
	    	buttonText : "Select Date",
	    	buttonImage: "../../hisglobal/images/calendar-icon.gif"
	    	}); 


$("#patAddStateCodeId").bind("change",patDtlModificationDesk.onchange_patAddStateCode);
$('[name="patAddCountryCode"]').validatebox({
	validType: ['selectCombo[0]']
});
$('[name="patFirstName"]').validatebox({
	required : true,
	validType : 'alphaWithSpaceSlash'
});
/*$('[name="patFirstName"]').validatebox({
	required: true,	
	validType: ['alphaWithSpace']
});*/
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
			validType: 'alpha'
		});

		$('[name="requestByAddress"]').validatebox({
			required: true,
			validType: 'alpha'
		});

		
		}
	
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

//validType: 'validateAdvAmount[\'patMonthlyIncome\',8,2,\'Kindly Enter Amount in 99999999.99 format\']'

/**/

/*$('[name="patDOB"]').validatebox({
	required: true,	
	validType: 'dtltet[\'anotherPatDOB\',\'PatDOB should be less than or equalt to AnotherPatDOB\']'
	//validType: 'md[\''+$('[name="anotherPatDOB"]')[0].value+'\']'
});*/
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
	/*This loop will trim all leading and trailing spaces from input type fileds*/
	var allInputs = $(":input"); 
	allInputs.each(function() {
	        $(this).val($.trim($(this).val()));
	    });
	//End
	if(document.getElementsByName("isActualDob")[0].checked){
		$('[name="tempIsActualDOB"]')[0].value="0";
		$('[name="patAge"]').val($("#patAgeId").val());
	}else{
		$('[name="tempIsActualDOB"]')[0].value="1";
	}
	
	  if( $('[name="patAddCountryCode"]')[0].value == $('[name="defaultpatAddCountryCode"]')[0].value){
		  $('[name="patAddState"]')[0].value 	= $("#patAddStateCodeId option:selected").text();
		  $('[name="patAddDistrict"]')[0].value = $("#patAddDistrictCodeId option:selected").text();
		  $('[name="patGender"]')[0].value 		= $("#patGenderCodeId option:selected").text();
		  $('[name="patCaste"]')[0].value 		= $("#patCasteCodeId option:selected").text();
		  
	  }
	//By Mukund on 10.04.2017 for saving patient details using eithe father's, mother's or spouse's name
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
	  
	  fatherorSpouseerror();
		fatherorSpouse();
		setMotherValidRule();
	//futdate();
	//alert("action :"+$('[name="action"]')[0].value);
	$("#PatientDetailModificationActionDesk").attr('action',"/HISRegistration/registration/transactions/SAVEPatientDetailModDesk.action");
	//document.forms[0].action = "saveVerificationDoc.action";
//	alert($('#PatientDetailModificationActionDesk').form('validate'));
	if($('#PatientDetailModificationActionDesk').form('validate'))
	{
		    sortandEncodebase64($("#PatientDetailModificationActionDesk"));
			$('#PatientDetailModificationActionDesk').submit();
			
	}
	//e.preventDefault();
}); 
////////////////////////   jquery Validation   /////////////////

