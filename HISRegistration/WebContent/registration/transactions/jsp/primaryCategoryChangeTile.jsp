<%@page import="registration.config.RegistrationConfig"%>
<%@page import="vo.registration.UnitConsultantVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 04-Mar-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet"	type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">
<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
 <script language="JavaScript" type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>

<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
 <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script type="text/javascript">

function setCompany() {
	 //alert($("#clientCodeId option:selected").html());
	$('[name="clientName"]')[0].value = $("#clientCodeId option:selected")
			.html();
}
function setRelation() {
	
	//alert($("#relationWithStaffId option:selected").val());
	 //alert($("#relationWithStaffId option:selected").html());
	/* var tempStaffName = $('[name="staffCardName"]')[0].value;
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
	} */
	if($("#relationWithStaffId option:selected").val()==3)//father
		$('[name="staffCardName"]')[0].value= $('[name="patGuardianName"]')[0].value; 
	if($("#relationWithStaffId option:selected").val()==4)//mother
		$('[name="staffCardName"]')[0].value=$('[name="patMotherName"]')[0].value;
	if($("#relationWithStaffId option:selected").val()==2)//spouse
		$('[name="staffCardName"]')[0].value=$('[name="patHusbandName"]')[0].value;
}

function setPatNameSelf() {
	// alert($('[name="relationNameWithStaff"]')[0].value);
	/* if ($('[name="relationNameWithStaff"]')[0].value == "Self") {
		$('[name="patFirstName"]')[0].value = $('[name="staffCardName"]')[0].value;
		multilingualConversion($('[name="patFirstName"]')[0], document
				.getElementById('patFirstNameInMultiLangId'));
	}
	if ($('[name="relationNameWithStaff"]')[0].value == "Father") {
		$('[name="patGuardianName"]')[0].value = $('[name="staffCardName"]')[0].value;
	}
	if ($('[name="relationNameWithStaff"]')[0].value == "Mother") {
		$('[name="patMotherName"]')[0].value = $('[name="staffCardName"]')[0].value;
	} */

}
function submitForm(mode)
{
	var isValid=true;
	if(mode=='SAVE'){
	
		if(document.getElementById("divCatGroupBeneficiaryId").style.display=="")
		{
			isValid=$('#divCatGroupBeneficiaryId').form('validate');
			if(!isValid)
				return false;
		}
		if(document.getElementById("divCatGroupArogyaShreeBeneficiaryId").style.display=="")
		{
			isValid=$('#divCatGroupArogyaShreeBeneficiaryId').form('validate');
			if(!isValid)
				return false;
		}
		if(document.getElementById("IdTile1").style.display==""){
			if($('#IdTile1').form('validate')){	
			document.forms[0].action=mode+"PrimaryCatChange.action";
			document.forms[0].submit();
			}
		}
		else{
			
			document.forms[0].action=mode+"PrimaryCatChange.action";
			document.forms[0].submit();
		}
	
	}
	else{
		
		document.forms[0].action=mode+"PrimaryCatChange.action";
		document.forms[0].submit();
	}
}

function showCatChangeTile(obj){
	//alert(obj.value);
	var tile=obj.value;
	if(tile!=1){
		document.getElementById("changeCatTile").style.display="";
		//document.getElementsByName("verificationDocument")[0].value="-1";
		checkCategory();
	}
	else{
		document.getElementById("changeCatTile").style.display="none";
		document.getElementById("verificationDocTile").style.display="";
		clearTile();
		//resetTile();
		submitForm('RENEWVERDOC');
		//renewVerDoc();
	}

}
function catChangeLogPopup(obj)
{
	//alert("123");
	var patcatchangelogFlag = this.value;
	
	var action = "/HISRegistration/registration/transactions/openPatPopupPrimaryCatChange.action?patcatchangelogFlag="
			+ patcatchangelogFlag;
	openURLInPopup(action, '600', '200');
	}

function resetTile()
{
	document.getElementsByName("patPrimaryCatCode")[0].value="-1";
	document.getElementsByName("remarks")[0].value="";
	document.getElementsByName("agsNo")[0].value="";
	document.getElementsByName("agsCounterNo")[0].value="";
	document.getElementsByName("agsCreditLimit")[0].value="";
	document.getElementsByName("agsDistrictCode")[0].value="-1";
	//document.getElementsByName("cardNo")[0].value="";
	//document.getElementsByName("verificationDocument")[0].value="-1";
	if(typeof(document.getElementsByName("remarks1")[0])!="undefined")
		document.getElementsByName("remarks1")[0].value="";
	if(typeof(document.getElementsByName("remarks2")[0])!="undefined")
		document.getElementsByName("remarks2")[0].value="";	
	if(typeof(document.getElementsByName("patIdNo1")[0])!="undefined")
		document.getElementsByName("patIdNo1")[0].value="";
	if(typeof(document.getElementsByName("patIdNo2")[0])!="undefined")
		document.getElementsByName("patIdNo2")[0].value="";
}

function clearTile()
{
	document.getElementsByName("patPrimaryCatCode")[0].value="-1";
	document.getElementsByName("remarks")[0].value="";
	document.getElementsByName("agsNo")[0].value="";
	document.getElementsByName("agsCounterNo")[0].value="";
	document.getElementsByName("agsCreditLimit")[0].value="";
	document.getElementsByName("agsDistrictCode")[0].value="-1";
	//document.getElementsByName("cardNo")[0].value="";
	document.getElementsByName("verificationDocument")[0].value="-1";
	if(typeof(document.getElementsByName("remarks1")[0])!="undefined")
	document.getElementsByName("remarks1")[0].value="";
	if(typeof(document.getElementsByName("remarks2")[0])!="undefined")
	document.getElementsByName("remarks2")[0].value="";	
}

function showVerDoc()
   {
	//alert($('[name="verificationDocument"]').value);
	var catCode=document.getElementsByName("patPrimaryCatCode")[0].value.split("#")[0];
	var idRequired=document.getElementsByName("patPrimaryCatCode")[0].value.split("#")[2];
	
	var patAge=document.getElementsByName("patAge")[0].value;
	var confCatCode=document.getElementsByName("regConfigCatCode")[0].value;
	var confAgeLimit=document.getElementsByName("regConfigCatAgeLimit")[0].value;
	var catGroup=document.getElementsByName("patPrimaryCatCode")[0].value.split("#")[8];
	if(catCode!="-1")
	{
		//alert("patAge "+patAge+" confCatCode "+confCatCode+" confAgeLimit "+confAgeLimit);
		if(catCode==confCatCode && patAge<confAgeLimit){
			alert("Invalid Category Selection ! ");
			document.getElementsByName("patPrimaryCatCode")[0].value="-1";
		}
		else
			{
			if (catGroup == PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE) {
				showHideCatGroupBeneficiaryTile("hide");
				document.getElementById("verificationDocTile").style.display="none";

			} else if (catGroup == PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) {
				showHideCatGroupBeneficiaryTile("showAGS");
				document.getElementById("verificationDocTile").style.display="none";

			} else {
				showHideCatGroupBeneficiaryTile("hide");
				submitForm('VERDOC');
			}
					
			}
		//verDoc();
	}
	else
		checkCategory();
	document.getElementsByName("patIdNo1")[0].value="";
	document.getElementsByName("patIdNo2")[0].value="";
	document.getElementsByName("patIdNo")[0].value="";

	//document.getElementsByName("cardNo")[0].value="";
	document.getElementsByName("verificationDocument")[0].value="-1";	
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
		
		var today=new Date().toLocaleFormat('%d-%b-%Y');
		var _todayPlus30Days=new Date();
		_todayPlus30Days.setDate(_todayPlus30Days.getDate() + 30);
		_todayPlus30Days=_todayPlus30Days.toLocaleFormat('%d-%b-%Y');
		/* $('#creditLetterDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		$("#creditLetterDateId").change(function(){
		 // var date2 = $('#creditLetterDateId').datepicker('getDate');
		//	date2.setDate(date2.getDate()+30); 
		//	$("#cardvalidityDateId").datepicker({ dateFormat: "dd-M-yy" }).datepicker("setDate", new Date(date2));
			var _dateStr=$(this).val().replace(/-/g,' ');
			var _date = new Date(_dateStr);
			_date.setDate(_date.getDate() + 30);
			_date=_date.toLocaleFormat('%d-%b-%Y');
			$('#cardvalidityDateId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:_date,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(_date);
			
		}); */
		/*$("#cardvalidityDateId").datepicker({
			dateFormat : 'dd-M-yy'
		}).datepicker("setDate", "30");*/
		/* $('#cardvalidityDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:_todayPlus30Days,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(_todayPlus30Days); */
		$("#creditBillFlagId").val("1");
		/* $("#creditLetterRefNoId").validatebox({
			required : true,
			validType : 'alphaNumSpecialChar'
		}); */
		$('[name="clientCode"]').validatebox({
			validType: ['selectCombo[-1]']
		});
		$('[name="clientCode"]').validatebox({
			validType: ['selectCombo[-1]']
		});
		/* $("#creditLetterDateId")
				.validatebox(
						{
							validType : ['dtltetctdt[\'It should not be grater than Current Date.\']','dtNotGtGvnNoDay[\''+today+'\',\'30\',\'Letter Date must be within 30 days\']']
						}); */
		/* $("#cardvalidityDateId")
				.validatebox(
						{
							validType : 'dtgtetctdt[\'It should not be lesser than Current Date.\']'
						}); */

		$("#staffCardNoId").validatebox({
			required : true,
			/* validType : ['alphaNumeric','startZero'] */
			validType : ['alphaNumeric','NotAllZero']
		});
		$("#staffCardNameId").validatebox({
			required : false,
			validType : ['alphaWithSpace']
		});

		$("#agsCounterNoId").val("");
		$("#agsNoId").val("");

		//$('[name="clientCode"]')[0].value = clientCode;
		//$("#clientNameLabel").html(clientName);
		//$('[name="clientName"]')[0].value = clientName;
		$('[name="agsDistrictCode"]')[0].value = "";

	} else if (mode == "showAGS") {
		$("#creditBillFlagId").val("0");
		//$("#creditLetterRefNoId").val("");
		/* $("#creditLetterDateId").val(""); */
		/* $("#creditLetterRefNoId").validatebox({
			required : false
		}); */
		/* $("#creditLetterDateId").validatebox({
			validType : null
		}); */
		/* $("#cardvalidityDateId").validatebox({
			validType : null
		}); */
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
			//validType : 'numeric'//by mukund on recommendations of harjyot sir
			validType : 'alphaNumeric'
		});
		$("#agsNoId").validatebox({
			required : true,
			/* validType : ['startZero','alphaNumeric'] */
			 validType : ['alphaNumeric','NotAllZero']
		});

		$("#clientCodeId").val("");
		$("#staffCardNoId").val("");
		$("#staffCardNameId").val("");
		$("#relationWithStaffId").val("-1");
		/* $("#cardvalidityDateId").val(""); */
		$("#clientNameLabel").html("");
		$('[name="clientName"]')[0].value = "";
		
		
		//$('[name="agsDistrictCode"]')[0].value = $('[name="defaultpatAddDistrictCode"]')[0].value;

	} else {

		$("#creditBillFlagId").val("0");
		/* $("#creditLetterRefNoId").val(""); */
		//$("#creditLetterDateId").val("");
		/* $("#creditLetterRefNoId").validatebox({
			required : false
		});
		$("#creditLetterDateId").validatebox({
			validType : null
		}); */
	/* 	$("#cardvalidityDateId").validatebox({
			validType : null
		}); */
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
		/* $("#cardvalidityDateId").val(""); */
		$("#clientNameLabel").html("");
		$('[name="clientName"]')[0].value = "";
		$('[name="agsDistrictCode"]')[0].value = "";

	}
}
function showInit(){
	
	//alert(document.getElementsByName("isIdRequired")[0].value);
	//alert(#isIdRequired.value)
	document.getElementsByName("remarks")[0].value="";
	//document.getElementsByName("cardNo")[0].value="";
	//document.getElementsByName("verificationDocument")[0].value="-1";
	
	if(typeof(document.getElementsByName("isRenewal")[0])!="undefined")
	{
		if(document.getElementsByName("isRenewal")[0].checked)
		{
			//alert("Inside Renewal");
			document.getElementById("changeCatTile").style.display="none";
			document.getElementById("verificationDocTile").style.display="";
			if(document.getElementsByName("isIdRequired")[0].value=="2")
				document.getElementById("IdTile2").style.display="";
			if(document.getElementsByName("isIdRequired")[0].value=="1")
				document.getElementById("IdTile1").style.display="";
			
		}
		else
			document.getElementById("changeCatTile").style.display="";
	}
	else
		document.getElementById("changeCatTile").style.display="";
	
	checkCategory();
	
}

function checkCategory(){
	var catCodeWithIDReq=document.getElementsByName("patPrimaryCatCode")[0].value;
	//alert("catCodeWithIDReq"+catCodeWithIDReq);
	//alert(catCodeWithIDReq.split("#")[0]);
	//alert(document.getElementsByName("oldhosPrimaryCatCode")[0].value);
	//alert(document.getElementsByName("patPrimaryCatCode")[0].options.length);
	if(catCodeWithIDReq.split("#")[0]==-1 && y!=-1)
	{
	var x =document.getElementsByName("patPrimaryCatCode")[0];
	var i;
	var y =document.getElementsByName("oldhosPrimaryCatCode")[0].value;
	for (i = 0; i < x.length; i++) {
	    if(x.options[i].value.split("#")[0]==y)
	    	{
	    	x.selectedIndex=i;
	   		 showVerDoc();
	    	}
	    	
	}
	
	}
	if(document.getElementById("changeCatTile").style.display=="")
	{
		if(catCodeWithIDReq!="-1")
		{
			var x=catCodeWithIDReq.split("#");
			var categoryCode=x[0];
			var idRequired=x[2];
			//alert("idRequired"+idRequired);
			if(categoryCode=="-1")
			{
				document.getElementById("IdTile1").style.display="none";	
				document.getElementById("IdTile2").style.display="none";	
				document.getElementById("verificationDocTile").style.display="none";
			}
			else
			{	
				document.getElementById("verificationDocTile").style.display="";
				if(idRequired==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES %>){
					//document.getElementById("verificationDocTile").style.display="";
					document.getElementById("IdTile1").style.display="";
					document.getElementById("IdTile2").style.display="none";
					patCatChange.changeCardDtl();
				}
				else if(idRequired==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_EMP%>){
					//document.getElementById("verificationDocTile").style.display="none";
					document.getElementById("IdTile1").style.display="none";
					document.getElementById("IdTile2").style.display="";
				}
				else{
					//document.getElementById("verificationDocTile").style.display="none";
					document.getElementById("DocTile").style.display="none";
					document.getElementById("IdTile2").style.display="none";
					document.getElementById("IdTile1").style.display="none";
				}
			}	
		}
		else{
			document.getElementById("verificationDocTile").style.display="none";
			document.getElementById("IdTile1").style.display="none";
			document.getElementById("IdTile2").style.display="none";
		}
	}
	else{
		document.getElementById("verificationDocTile").style.display="";
		if(document.getElementsByName("isIdRequired")[0].value=="0")
			document.getElementById("DocTile").style.display="none";
		patCatChange.changeCardDtl();
	}
}

function validatePrimaryCategoryChange(){
	//alert("validatePrimaryCategoryChange");
	var retVal=false;
	var PrimaryCatObj=document.getElementsByName("patPrimaryCatCode")[0]
	var PrimaryCatName = PrimaryCatObj.options[PrimaryCatObj.selectedIndex].text;
	if(typeof(document.getElementsByName("isRenewal")[0])!="undefined")
	{
		//alert("1");
		if(document.getElementsByName("isRenewal")[0].checked)
		{
			//alert("2");
			if(typeof(document.getElementsByName("remarks1")[0])!="undefined")
				document.getElementsByName("remarks")[0].value=document.getElementsByName("remarks1")[0].value;
			//alert(document.getElementsByName("isIdRequired")[0].value);
				if(PrimaryCatName.toLowerCase().indexOf("wap") > -1)
					document.getElementsByName("isIdRequired")[0].value="0";
			if(document.getElementsByName("isIdRequired")[0].value!=0)
			{
				//alert("Inside Renewal");
				retVal=validateDocSelection();
				if(document.getElementsByName("isIdRequired")[0].value==1){
					if(retVal)retVal=validateId1Selection();
					//alert("PatId1"+document.getElementsByName("patIdNo1")[0].value);
					document.getElementsByName("patIdNo")[0].value=document.getElementsByName("patIdNo1")[0].value;
				}
				else if(document.getElementsByName("isIdRequired")[0].value==2){
					if(retVal)retVal=validateId2Selection();
					//alert("PatId2"+document.getElementsByName("patIdNo2")[0].value);
					document.getElementsByName("patIdNo")[0].value=document.getElementsByName("patIdNo2")[0].value;
				}
				else
					document.getElementsByName("patIdNo")[0].value="";
				if(retVal)retVal=validateRemark();
				//alert(document.getElementsByName("patIdNo")[0].value);
				//alert("retVal"+retVal);
				//retVal=false;
			}	
			else{
				//retVal=validateDocSelection()&&validateRemark();
				//alert("3");
				retVal=validateRemark();
				document.getElementsByName("patIdNo")[0].value="";
			}

		}
		else
		{
			//alert("Inside Change Pat Cat");
			//alert("4");
			var catCodeWithIDReq=document.getElementsByName("patPrimaryCatCode")[0].value;
			if(catCodeWithIDReq!="-1")
			{
				var x=catCodeWithIDReq.split("#");
				var categoryCode=x[0];
				var idRequired=x[2];
				document.getElementsByName("remarks")[0].value=document.getElementsByName("remarks2")[0].value;
				retVal=validateCatSelection();	
				if(retVal){
				if(document.getElementById("verificationDocTile").style.display=="")
					retVal=validateDocSelection();
				//alert(idRequired);
				if(idRequired=="-1")
					idRequired=0;
				if(PrimaryCatName.toLowerCase().indexOf("wap") > -1)
					idRequired=0;
				if(idRequired!=<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_NO %>)
				{
					if(idRequired!=<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES %>){
						if(retVal)retVal=validateId2Selection();
						//alert("PatId2"+document.getElementsByName("patIdNo2")[0].value);
						document.getElementsByName("patIdNo")[0].value=document.getElementsByName("patIdNo2")[0].value;
					}
					else{
						if(retVal)retVal=validateId1Selection();
						//alert("PatId1"+document.getElementsByName("patIdNo1")[0].value);
						document.getElementsByName("patIdNo")[0].value=document.getElementsByName("patIdNo1")[0].value;
					}
				}
				else{
					//alert("5");
					document.getElementsByName("patIdNo")[0].value="";				
					//alert(document.getElementsByName("patIdNo")[0].value);
					//alert("retVal"+retVal);
					retVal=validateCatSelection();
				}
			}
			}
			else
				retVal=validateCatSelection();
		
		}
	}
	else
		retVal=validateCatSelection();
	
	return retVal;
	
}

function validateCatSelection(){
	var retVal=false;
	if(document.getElementsByName("patPrimaryCatCode")[0].value=="-1"){
		document.getElementsByName("patPrimaryCatCode")[0].focus();
	}
	else retVal=true;
	return retVal;
}

function validateDocSelection(){
	var retVal=false;
	if(document.getElementsByName("verificationDocument")[0].value=="-1"){	
		document.getElementsByName("verificationDocument")[0].focus();
	}
	//else if(document.getElementsByName("cardNo")[0].value==""){
	//	document.getElementsByName("cardNo")[0].focus();
	//}
	
	else retVal=true;
	return retVal;
}

function validateRemark(){
	var retVal=false;
	if(document.getElementsByName("remarks1")[0].value==""){	
		document.getElementsByName("remarks1")[0].focus();
	}
	else retVal=true;
	return retVal;	
}

function validateId1Selection(){
	var retVal=false;
	if(document.getElementsByName("patIdNo1")[0].value==""){	
		document.getElementsByName("patIdNo1")[0].focus();
	}
	else retVal=true;
	return retVal;	
}

function validateId2Selection(){
	var retVal=false;
	if(document.getElementsByName("patIdNo2")[0].value==""){	
		document.getElementsByName("patIdNo2")[0].focus();
	}
	else retVal=true;
	return retVal;	
}

function getDocValidtype(idValidTypeCode)
{
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
}

function renewVerDoc()
 {
	var action= '/HISRegistration/registration/transactions/RENEWVERDOCPrimaryCatChange.action';
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{}});
 }

function verDoc()
{
	var action= '/HISRegistration/registration/transactions/VERDOCPrimaryCatChange.action';
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{}});
}

function openCatPopup(){
	var patPrimaryCatCode=document.getElementsByName("patPrimaryCatCode")[0].value.split("#")[0];
	var action 	= "/HISRegistration/registration/transactions/POPUPPrimaryCatChange.action?patPrimaryCatCode="+patPrimaryCatCode;
	//openURLInPopupWithoutClose(action,'600','200');
	openURLInPopupWithoutClose(action,'900','400');
}
/*By Mukund On 20.09.2016 for Audit log  */
function auditLogPopup(obj)
{
	//alert("inside auditlogPopup");
	var patauditlogFlag = this.value;
	
	var action = "/HISRegistration/registration/transactions/openAuditPopupPrimaryCatChange.action";
			
	openURLInPopup(action, '800', '300');
}//end:Mukund

var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>";
var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE %>";


</script>

</head>

<body onload="showInit();">
	<center>
		<s:form action="PrimaryCatChange">
		<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded ">

				<div class="div-table">
					<div class="div-table-row title">
						
					<div class="div-table-col " style="width: 69%" ><s:text name="primaryCatChange.title"></s:text> </div>						
					<s:if test="isDetailAvailable==1">
					<div id="divpatchangelogId" class="div-table-col control" style="width:3%;" >
					<img id="patcatchangelogFlagId" name="patcatchangelogFlag"  style="cursor: pointer;" align="left" src="../../hisglobal/images/log_icon.png" onclick="catChangeLogPopup(this)"/>
				     </div>
				     <div class="div-table-col " style="width: 15%" >
					<font color="yellow"><s:text name="see"/>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="change"/>&nbsp;<s:text name="log"/></font>
					</div>
				     
					</s:if>
					
					<!-- </div>
					
					
				</div>
				By Mukund for Audit Log 
				<div class="div-table">
					<div class="div-table-row title"> -->
				<%-- 	<s:if test="isDetailAvailable==1">
					<div id="divpatAuditlogId" class="div-table-col control" style="width:3%;" >
					<img id="patAuditlogFlagId" name="patAuditlogFlag"  style="cursor: pointer;" align="left" src="../../hisglobal/images/log_icon.png" onclick="auditLogPopup(this)"/>
				     </div>
				     <div class="div-table-col " style="width: 10%" >
					<font color="yellow"><s:text name="see"/>&nbsp;<s:text name="Audit"/>&nbsp;<s:text name="log"/></font>
					</div>
				     
					</s:if> --%>
					
					</div>
				</div>
				<!--End Mukund  -->
	<s:if test="isDetailAvailable==0">
	<his:InputCrNoTag />
	</s:if>
	<div id="divAfterGo" > 
	<s:if test="isDetailAvailable==1">
	
	<div class="div-table">
		<div class="div-table-row ">
			<div class="div-table-col label width25 "><s:text name="crNO"/></div>	
			<div class="div-table-col control width25 "><s:property value="patCrNo"/></div>
			<s:hidden name="patCrNo" value="%{patCrNo}"></s:hidden>
		</div>
	</div>
	
	<%-- <s:action name="patientDetail" executeResult="true"></s:action> --%>
	<his:PatientTileTag crNo="${patCrNo }"></his:PatientTileTag>
	<div class="div-table">
	<div class="div-table-row ">
				<div class="div-table-col  label width24 "><s:text name="validUpto"/>
				</div>
				<div class="div-table-col  control width25 ">
				<s:if test="isCatExpired==1">
				<font color="#FF0000"><s:if test="patPrimaryCatValid!=null"><s:property value="patPrimaryCatValid" />
				<s:hidden name="patPrimaryCatValid" value="%{patPrimaryCatValid}"></s:hidden></s:if>
				<s:else><s:if test="unavailValidUpto!=null"><s:property value="unavailValidUpto" />
				<s:hidden name="unavailValidUpto" value="%{unavailValidUpto}"></s:hidden></s:if><s:else>
				NA</s:else></s:else></font></s:if>
				<s:else><s:if test="patPrimaryCatValid!=null"><s:property value="patPrimaryCatValid" />
				<s:hidden name="patPrimaryCatValid" value="%{patPrimaryCatValid}"></s:hidden>
				</s:if><s:else><s:if test="unavailValidUpto!=null"><s:property value="unavailValidUpto" />
				<s:hidden name="unavailValidUpto" value="%{unavailValidUpto}"></s:hidden></s:if><s:else>
				NA</s:else></s:else>
				</s:else>
				</div>				
				<s:hidden name="isCatExpired" value="%{isCatExpired}"></s:hidden>
				<s:hidden name="isIdRequired" value="%{isIdRequired}"></s:hidden>		
				<s:hidden name="prevPatIdNo" value="%{patIdNo}"></s:hidden>			
				<s:hidden name="patIdNo" value="%{patIdNo}"></s:hidden>	
				<s:hidden name="prevVerificationDocument" value="%{prevVerificationDocument}"></s:hidden>	
										
			</div>
				
	</div>
	
	<div class="div-table">
		<div class="div-table-row ">
		<s:if test="patPrimaryCatValid!=null">
			<div class="div-table-col title width80 ">
<%-- 			<s:text name="primaryCatChange.select"></s:text> --%>
			</div>
			<div class="div-table-col title width20 "><s:radio id="isRenewal" tabindex="1" name="isRenewal" value="%{isRenewal}" list="#{'1':'Renewal','2':'Change Category'}" onchange="showCatChangeTile(this);"></s:radio>
			<s:hidden name="renewalFlag" value="%{isRenewal}"></s:hidden>
			</div>	
		</s:if>
		<s:else>
			<div class="div-table-col title width100 "><s:text name="primaryCatChange.select"></s:text></div>
			<s:hidden name="isRenewal" value="2"></s:hidden>			
		</s:else>
								
		</div>
	</div>
	<div id="changeCatTile" class="div-table" style="display:none">			
		<div class="div-table-row ">
				<div class="div-table-col label  width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/></div>
				<div class="div-table-col column  width25">
				<%-- <s:select key="patPrimaryCatCode" headerKey="-1" headerValue="Select Value" 
 				 				list="%{#session.listPatCatExceptPatientcat}" listKey="value" listValue="label" name="patPrimaryCatCode" onchange="showVerDoc();"></s:select> --%> 
				
				<s:set name="listPatCatExceptPatientcatLocalVar" value="#session.listPatCatExceptPatientcat"></s:set>
							<s:if test="%{#listPatCatExceptPatientcatLocalVar!=null && #listPatCatExceptPatientcatLocalVar.size>0}">
								<s:select key="patPrimaryCatCode" headerKey="-1" headerValue="Select Value" 
 				 				list="%{#session.listPatCatExceptPatientcat}" listKey="value" listValue="label" name="patPrimaryCatCode" onchange="showVerDoc();"></s:select> 
							</s:if>
							<s:else>
								<select name="patPrimaryCatCode" >
									<option value="-1">Select Value</option>
								</select>
							</s:else>
				
				</div>
		</div>
<!-- 		<div class="div-table-row "> -->
<%-- 				<div class="div-table-col label  width25"><s:text name="global.remarks"/></div>		 --%>
<%-- 				<div class="div-table-col column  width75"><s:textarea key="remarks1" name="remarks1" value="%{remarks}" rows="2" cols="50" onkeypress="return CheckMaxLength(event,this,40,3)"></s:textarea> --%>
<!-- 				</div> -->
<!-- 		</div> -->
<!-- start sheeldarshi -->
			
			<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="0">
						
<!-- 			<div id="divCatGroupBeneficiaryId" style="display: none;" class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-dialog-buttons ui-draggable">			 -->
			<div id="divCatGroupBeneficiaryId" style="display: none;">
			<div class="div-table">
			
			   <%--  <div class="div-table-row">
					<div class="div-table-col width25 label">
						<font color="red">*</font><s:text name="reference"/>&nbsp;<s:text name="letter"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" class="input75prcnt" maxlength="50" tabindex="1">
					</div>
					<div class="div-table-col width16 label">
						<font color="red">*</font><s:text name="letter"/>&nbsp;<s:text name="date"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditLetterDate" id="creditLetterDateId" readonly="readonly" class="input60prcnt" tabindex="1">
					</div>
				</div> --%>
			
				<div class="div-table-row">
					<div class="div-table-col width25 label"><font color="red">*</font><s:text name="company"/>
					</div>
					<div class="div-table-col width16 control"  >
						<s:select name="clientCode"  tabindex="1" id="clientCodeId"  list="%{#session.listcompany}" listKey="value" listValue="label"  headerKey="-1" headerValue="Select Value"  onchange="setCompany();">
						</s:select>
					</div>
					<div class="div-table-col width16 control"  id="clientNameLabel" style="display: none">
					</div>
					<div class="div-table-col width16 label" id="staffCardNoLabel"><font color="red">*</font><s:text name="staff"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width13 control" >
						<input type="text" name="staffCardNo" id="staffCardNoId" class="input60prcnt" maxlength="50" tabindex="1">
					</div>
					<div class="div-table-col width13 label"><s:text name="staff"/>&nbsp;<s:text name="global.name"/>
					</div>
					<div class="div-table-col width10 control" >
						<input type="text" name="staffCardName" id="staffCardNameId" class="input75prcnt" maxlength="100" onkeyup="setPatNameSelf();">
					</div>
				</div>
				
				<div class="div-table-row" >
					<div class="div-table-col width25 label"><s:text name="relation"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="global.patient"/>
					</div>
					<div class="div-table-col width16 control" >
						<s:select name="relationWithStaff"  tabindex="2" id="relationWithStaffId"  list="%{#session.listrelation}" listKey="value" listValue="label"  headerKey="-1" headerValue="Select Value"  onchange="setRelation()">
							
						</s:select>					
					</div>
					<%-- <div class="div-table-col width16 label"><s:text name="validfor"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="cardvalidityDate" id="cardvalidityDateId" readonly="readonly" class="input60prcnt">
					</div> --%>
					
				</div>
			</div>
			</div>
			
			<div id="divCatGroupArogyaShreeBeneficiaryId" style="display: none;">
			<div class="div-table">
				<div class="div-table-row" >
					<div class="div-table-col width25 label" ><s:text name="district"/>
					</div>
					<div class="div-table-col width16 control" >
						<s:select name="agsDistrictCode"  tabindex="2" id="agsDistrictCodeId" list="%{#session.listagsdistrict}" listKey="value" listValue="label"  headerKey="-1" headerValue="Select Value"  >
							
						</s:select>
					</div>
					<div class="div-table-col width16 label"><font color="red">*</font><s:text name="arogyasri"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width13 control" >
						<input type="text" name="agsNo" id="agsNoId" class="input75prcnt" maxlength="15" tabindex="1"> 
					</div>
					<!--By Mukund on 13.04.2017 after recommendations of Harjyot sir  -->
					<div class="div-table-col width13 label"><%-- <s:text name="arogyamitra"/>&nbsp; --%><s:text name="global.counter"/>&nbsp;<s:text name="number"/>/<s:text name="TG"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width10 control" >
						<input type="text" name="agsCounterNo" id="agsCounterNoId" class="input75prcnt" maxlength="15">
					</div>	
					<!--End On 13.04.2017  -->
									
				</div>
				<div class="div-table-row" >
					<div class="div-table-col width25 label"><s:text name="credit"/>&nbsp;<s:text name="Limit"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="agsCreditLimit" id="agsCreditLimitId" class="input75prcnt numberinput" maxlength="11">
					</div>
					</div>
			</div>
			</div>
			
		<!-- End Sheeldarshi -->
	</div>
	
	<div id="verificationDocTile" class="div-table" style="display:none">
<!-- 		<div class="div-table-row "> -->
<!-- 			<div class="div-table-col title width100 "> -->
<%-- 			<s:text name="global.verificationDoc"></s:text> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<%-- <s:if test="%{#session.optionVerificationDoc.size()}==0">
	
		</s:if>
		<s:else> --%>
			<div class="div-table-row " id="DocTile">			
			<div class="div-table-col label  width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.verificationDoc"/></div>		
			<div class="div-table-col column  width25" style="display: none"><s:select key="verificationDocument"  name="verificationDocument"  
  				 				list="%{#session.optionVerificationDoc}" listKey="value" listValue="label" value="%{#session.optionVerificationDoc[0].value}" onchange="patCatChange.changeCardDtl()"></s:select> 
			</div>
			<div class="div-table-col column  width15"><s:text name="%{#session.optionVerificationDoc[0].label}"></s:text>
			</div>
			<div class="div-table-col label  width25"></div>
			<div class="div-table-col column  width20"></div>
<%-- 			<div class="div-table-col label  width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.cardno"/></div>		 --%>
<%-- 			<div class="div-table-col column  width20"><s:textfield key="cardNo" name="cardNo" value="%{cardNo}" maxlength="30" onkeypress="patCatChange.changeCardDtl()"> </s:textfield> --%>
<!-- 			</div>			 -->
		</div>
		<%-- </s:else> --%>
		<div class="div-table-row " id="IdTile1" style="display:none">
			<div class="div-table-col label  width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.id"/></div>		
			<div class="div-table-col column  width25"><s:textfield name="patIdNo1" disabled="false" value="%{patIdNo}"></s:textfield></div>
		</div>
		
<%-- 		<s:property value="%{patIdNo}"/> --%>
<%-- 		<s:if test="patIdNo!=''"> --%>
		<div class="div-table-row " id="IdTile2" style="display:none">
			<div class="div-table-col label  width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.id"/></div>		
			<div class="div-table-col column  width15"><s:textfield name="patIdNo2" readonly="true" value="%{patIdNo}"></s:textfield></div>
			<div class="div-table-col column  width10"><img id="imgCatCardId" src="../../hisglobal/images/search_icon1.gif" onclick="openCatPopup();"/>			
			</div>
		</div>
<%-- 		</s:if> --%>
		<s:if test="isRenewal==1">
		<div class="div-table-row ">
				<div class="div-table-col label  width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.remarks"/></div>		
				<div class="div-table-col column  width75"><s:textarea key="remarks1" name="remarks1" tabindex="1" value="%{remarks}" rows="2" cols="50" onkeypress="return CheckMaxLength(event,this,40,3)"></s:textarea>
				</div>
		</div>
		</s:if>
		<s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width25"><s:text name="global.remarks"/></div>		
				<div class="div-table-col column  width75"><s:textarea key="remarks2" name="remarks2" tabindex="1" value="%{remarks}" rows="2" cols="50" onkeypress="return CheckMaxLength(event,this,40,3)"></s:textarea>
				</div>
		</div>
		</s:else>
			
	</div>	
<!-- 	<div id="empDetailTile" class="div-table" style="display:none"> -->
<!-- 		<div class="div-table-row "> -->
<%-- 			<div class="div-table-col title width100 "><s:text name="global.id"></s:text>&nbsp;<s:text name="global.detail"/></div> --%>
<!-- 		</div> -->
<!-- 		<div class="div-table-row "> -->
<%-- 			<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.id"/></div>		 --%>
<%-- 			<div class="div-table-col column  width15"><s:textfield name="hiddenPatIdNo2" disabled="true" value="%{patIdNo}"></s:textfield> --%>
<%-- 				<s:hidden name="patIdNo"></s:hidden></div> --%>
<!-- 			<div class="div-table-col column  width35"><img id="imgCatCardId" src="../../hisglobal/images/search_icon1.gif" onclick="openCatPopup();"/>			 -->
<!-- 			</div> -->
<!-- 		</div> -->
			
<!-- 	</div>	 -->
				
	</s:if>
				
	<div class="div-table-button">
		<div class="div-table-row footerBar">
				<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row" align="center">
			
			<s:if test="isDetailAvailable==1">
			<a href="#" tabindex="1" class="button" id="submitId" ><span class="save" onclick="if(validatePrimaryCategoryChange())submitForm('SAVE');"><s:text name="save"/></span></a>
			<a href="#" tabindex="1" class="button" id="clearId" onclick="resetTile();" ><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else>
			<a href="#" tabindex="1" class="button" id="initialClearId" onclick="submitForm('CANCEL');"><span class="clear"><s:text name="clear"/></span></a>
			</s:else>		
			<s:if test="isDetailAvailable==1">			
			<a href="#" tabindex="1" class="button" id="clearId" onclick="submitForm('CANCEL');"><span class="cancel"><s:text name="cancel"/></span></a>
			</s:if>
			<s:else>
			<a href="#" tabindex="1" class="button" id="clearId" onclick="window.parent.closeTab('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
			</s:else>
		</div>
		
	</div>
	</div>			
	</div>
	<div class="div-table">
	<div class="div-table-row ">
			<div class="div-table-col alignLeft fontError">
			<s:property value="errorMessage" />
			</div>
			<div class="div-table-col alignLeft">
			<s:property value="strNormalMsg" />
			</div>
	</div>
	</div>
	<s:hidden name="patAgeUnit"></s:hidden>	
	<s:hidden id="printHtmlId" name="printHtml" value="%{errorMessage}"/>
	<s:hidden name="remarks"></s:hidden>
	<s:hidden name="regConfigCatCode"></s:hidden>
	<s:hidden name="regConfigCatAgeLimit"></s:hidden>
	<input type="hidden" name="clientName" />
	<input type="hidden" name="memSlNo" />	
	<s:hidden name="patGuardianName"></s:hidden>
	<s:hidden name="patHusbandName"></s:hidden>
	<s:hidden name="patMotherName"></s:hidden>
	<s:hidden name="oldhosPrimaryCatCode"></s:hidden>
	
<div class="div-table" id="divPrintId" style="font-weight: normal;color: black;"></div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
</center>
<script type="text/javascript">
$('[name="patPrimaryCatCode"]').validatebox({required: true,validType: ['selectCombo[-1]']}); 
$('[name="verificationDocument"]').validatebox({required: true,validType: ['selectCombo[-1]']});
/* $('[name="cardNo"]').validatebox({required: true,	validType: 'numeric'}); */
//$('[name="patIdNo1"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'}); 
$('[name="patIdNo2"]').validatebox({required: true, validType: ['alphaNumericWithSpaces','NotAllZeroWithNumber']}); 
$('[name="remarks1"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});
//patCatChange.changeCardDtl();
var docValidType = 'alphaNumSpecialChar';
var patCatChange={
changeCardDtl:function()
{
	//alert($('[name="verificationDocument"]').value);
	//alert(document.getElementsByName("verificationDocument")[0].value);
	docValidType=getDocValidtype(document.getElementsByName("verificationDocument")[0].value.split("#")[2]);
	var isize = document.getElementsByName("verificationDocument")[0].value.split("#")[1];
	var docValidTypeNLength='equalLength['+isize+']';
	//alert(docValidType);alert(docValidTypeNLength);
	//$('[name="cardNo"]').validatebox({required: true, validType: [docValidType,docValidTypeNLength]});
	$('[name="patIdNo1"]').attr('maxlength', isize);
	$('[name="patIdNo1"]').validatebox({required: true, validType: [docValidType,docValidTypeNLength,'NotAllZeroWithNumber']});
}};

</script>
</body>
</html>