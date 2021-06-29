<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js'></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

<style type="text/css">
.border .div-table-col{
border: 1px solid black;
}
</style>

<title>Patient Registration</title>
<script type='text/javascript'>


var arrPatGlobalJsonObj=[];
var alreadyRegisteredFlag1 = "0";
function initializePopUp(){
	//$('[name="alreadyRegisteredFlag"]')[0].value="2";
	//alert($('[name="alreadyRegisteredFlag"]')[0].value);
	if($('[name="alreadyRegisteredFlag"]')[0].value=="0"){
		$("#divAlreadyRegisteredId").hide();
		 // edited by sandip naik on 28 April,2017
		
        // end by sandip naik on 28 April,2017
	}else if($('[name="alreadyRegisteredFlag"]')[0].value=="1"){
		//$("#divAlreadyRegisteredId").show();
		$("#divAlreadyRegisteredId").hide();		
		$('[name="strAreadyRegisteredFlag"]')[0].checked=true;
		$('[name="strAreadyRegisteredFlag"]')[0].disabled=false;
		//$('[name="strAreadyRegisteredFlag"]').attr("disabled", false);
	    // edited by sandip naik on 28 April,2017
		
         // end by sandip naik on 28 April,2017	
		}
	else if ($('[name="alreadyRegisteredFlag"]')[0].value=="2"){
		$('[name="strAreadyRegisteredFlag"]')[1].checked=true;
		$('[name="strAreadyRegisteredFlag"]')[1].disabled=false;
		$("#divAlreadyRegisteredId").show()	;
		 // edited by sandip naik on 28 April,2017
		
         // end by sandip naik on 28 April,2017	
	    }
	else if ($('[name="alreadyRegisteredFlag"]')[0].value=="3"){
		$('[name="strAreadyRegisteredFlag"]')[1].checked=true;
		$('[name="strAreadyRegisteredFlag"]')[0].disabled=false;
		$('[name="strAreadyRegisteredFlag"]')[1].disabled=false;
	} else if ($('[name="alreadyRegisteredFlag"]')[0].value=="4"){
		$('[name="strAreadyRegisteredFlag"]')[1].checked=true;
		$('[name="strAreadyRegisteredFlag"]')[0].disabled=false;
		$('[name="strAreadyRegisteredFlag"]')[1].disabled=false;
		$('[name="strAreadyRegisteredFlag"]')[2].disabled=false;
	}
	else {
		//$("#divAlreadyRegisteredId").show();
		$("#divAlreadyRegisteredId").hide();		
		$('[name="strAreadyRegisteredFlag"]')[0].checked=true;
		$('[name="strAreadyRegisteredFlag"]')[0].disabled=false;
		//$('[name="strAreadyRegisteredFlag"]').attr("disabled", false);
	    // edited by sandip naik on 28 April,2017
		
         // end by sandip naik on 28 April,2017	
		}
	
	}
function go(){

	/* if(!allLetter( $('[name="searchCatName"]')[0].value))
		{
			return false;
		}
	else
		{
			return true;
		} */
	var alreadyRegisteredFlag = "0";
	var patPrimaryCatCode = $('[name="patPrimaryCatCode"]')[0].value;
	var searchCatName = $('[name="searchCatName"]')[0].value;
	var searchCatId = $('[name="searchCatId"]')[0].value;
	if($('[name="strAreadyRegisteredFlag"]')[0].checked)
		alreadyRegisteredFlag=$('[name="strAreadyRegisteredFlag"]')[0].value;
	else if($('[name="strAreadyRegisteredFlag"]')[1].checked)
		alreadyRegisteredFlag=$('[name="strAreadyRegisteredFlag"]')[1].value;
	
	alreadyRegisteredFlag1 = alreadyRegisteredFlag;
	var action 	= "/HISRegistration/registration/transactions/getPatDtlOnPatCatIdNewRegistration.action?"+
					"patPrimaryCatCode="+patPrimaryCatCode+"&searchCatName="+searchCatName+
					"&searchCatId="+searchCatId+"&alreadyRegisteredFlag="+alreadyRegisteredFlag;

	//alert("action"+action);
	
	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data){
		//alert("response"+data);
		arrPatGlobalJsonObj=data;
		if($('[name="searchCatName"]')[0].value!="" ||$('[name="searchCatId"]')[0].value!="")
			createPatientEmpRow(data);	
		else
			alert("Please Enter Any One Criteria..!");
			
	},error: function(errorMsg,textstatus,errorthrown) {
       // alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		// alert("ERROR!");
        
    }
	});
}
 $('.simplemodal-close').click(function(){
	//alert("hello");
}); 
 
 
function cancelPopup(){
	var alreadyRegisteredFlag = $('[name="alreadyRegisteredFlag"]')[0].value;
	if(parseInt(alreadyRegisteredFlag)> 0){
		if(parent.document.getElementsByName("hiddenPatIdNo")[0].value == "")
			parent.document.getElementsByName("alreadyRegisteredFlag")[0].checked=false;
	}
	parent.closeModal();
}

function callOpenerFetchPatDtlBasedOnPatCatCardNo(patIndexId){
	var alreadyRegisteredFlag = $('[name="alreadyRegisteredFlag"]')[0].value;
	if(parseInt(alreadyRegisteredFlag)> 0){
		$("#hiddenCatOrRegstrdPopupFlagId",parent.document).val("A");
	}else{
		$("#hiddenCatOrRegstrdPopupFlagId",parent.document).val("C");
	}
	$("#patAgeId",parent.document).val($('[name="patAge"]')[patIndexId.value].value);
	parent.document.getElementsByName("hiddenPatIdNo")[0].value=$('[name="patIdNo"]')[patIndexId.value].value;
	parent.document.getElementsByName("patAgeUnit")[0].value=$('[name="patAgeUnit"]')[patIndexId.value].value;
	
	parent.document.getElementsByName("strAreadyRegisteredFlag")[0].value=alreadyRegisteredFlag1;
	window.parent.opdregistration.fetchPatDtlBasedOnPatCatCardNo(arrPatGlobalJsonObj[patIndexId.value]);
	//closeModal();
	parent.closeModal();
}



function createPatientEmpRow(arrStrPatJsonObj)
{
     //alert("JSON Data"+arrStrPatJsonObj);
	var empHeaderRow =	"<div class='div-table-row listHeader'>"+
							"<div class='div-table-col width10 alignCenter'>Select</div>"+
							"<div class='div-table-col width20 alignCenter'>Name</div>"+
							"<div class='div-table-col width20 alignCenter'>Temp Reg No.</div>"+
							"<div class='div-table-col width10 alignCenter'>Gender</div>"+
							"<div class='div-table-col width10 alignCenter'>Age</div>"+
							"<div class='div-table-col width10 alignCenter'>Mobile</div>"+
							"<div class='div-table-col width20 alignCenter'>Cr No</div>"+
						"</div>";
	var empDtlRow="";
	//var arrPatJsonObj = eval('(' + arrStrPatJsonObj + ')');
	var arrPatJsonObj = arrStrPatJsonObj;
	var varId="";
	if(arrPatJsonObj!="")
	{
	for (i in arrPatJsonObj)
	{
		var disabled="";
		if(arrPatJsonObj[i]["patCrNo"]!="" && arrPatJsonObj[i]["patCrNo"]!="0" && arrPatJsonObj[i]["patCrNo"]!="-" && arrPatJsonObj[i]["patCrNo"]!="--" && arrPatJsonObj[i]["patCrNo"]!="---"){
			disabled="disabled='disabled'";
		}
		if(parseInt($('[name="alreadyRegisteredFlag"]')[0].value)> 0){
			varId=arrPatJsonObj[i]["prevCrNo"];
		}else{
			varId=arrPatJsonObj[i]["patIdNo"];
		}
		
			var objPatAge = arrPatJsonObj[i]["patAgeWithUnit"].split(" ");
			var patAge	  =	objPatAge[0];
			//alert(patAge);
			var patAgeUnit= objPatAge[1];
			//alert("patCrNo :"+ arrPatJsonObj[i]["patCrNo"]);
			empDtlRow +=	"<div class='div-table-row listData'  name='orsPatientList' id='"+arrPatJsonObj[i]["patFirstName"]+"&"+arrPatJsonObj[i]["patMiddleName"]+"&"+arrPatJsonObj[i]["patLastName"]+"@"+arrPatJsonObj[i]["patAddMobileNo"]+"'>"+
							"<div class='div-table-col width10 alignCenter'>"+
								"<input type='radio' name='patIndex' value='"+i+"' onclick='callOpenerFetchPatDtlBasedOnPatCatCardNo(this);'"+disabled+" />"+
								"<input type='hidden' name='patIdNo' value='"+arrPatJsonObj[i]["patIdNo"]+"' />"+
								"<input type='hidden' name='patAge' value='"+patAge+"' />"+
								"<input type='hidden' name='patAgeUnit' value='"+patAgeUnit+"' />"+
							"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patFirstName"]+" "+arrPatJsonObj[i]["patMiddleName"]+" "+arrPatJsonObj[i]["patLastName"]+ "</div>"+
							"<div class='div-table-col width20 alignCenter'>"+varId+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patGenderCode"]+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patAgeWithUnit"]+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patAddMobileNo"]+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patCrNo"]+"</div>"+
						"</div>";
	}
	}
	else{
		empDtlRow +=	"<div class='div-table-row listData'><b>No Record Found<b></div>";
	}
	$("#divCatDetilId").html(empHeaderRow+empDtlRow);
	
}

//  function validateAlphabetsOnly(e,obj)
/* 

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
		return true
	// alphas and space
	
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
	else
		{alert("Enter alphabets and space only.");
	   return false;}
}  */
 function getCursorIdex(obj) {
		if (obj.createTextRange) {
			var r = document.selection.createRange().duplicate()
			r.moveEnd('character', obj.value.length)
			if (r.text == '') return obj.value.length
			return obj.value.lastIndexOf(r.text)
		} else return obj.selectionStart
	}

function allLetter(inputtxt)  
{  
	$("#searchCatName").on("input", function(){
		  var regexp = /[^a-zA-Z ]/g;
		  if($(this).val().match(regexp)){
		    $(this).val( $(this).val().replace(regexp,'') );
		  }
		});
}  

function getORSListId(e){
	//var shoe = $('[name="strAreadyRegisteredFlag"]')[2].checked; 
	//alert(shoe+"\n"+$('[name="strAreadyRegisteredFlag"]')[1].checked+"\n"+e.checked);
	if(e.value=='3'){
		showHideDivForORS();
		goForORS(e);
	}else{
		showHideDivForORS();
	}
		
}

$(document).on('click', '[name="strAreadyRegisteredFlag"]', function(e){
	
	//alert($('[name="strAreadyRegisteredFlag"]:checked').val());
	var e = $('[name="strAreadyRegisteredFlag"]:checked');
	//alert(e.value+"\n"+e.val());
	if(e.val()=='3'){
		showHideDivForORS();
		goForORS(e);
	}else{
		showHideDivForORS();
	}
		
});
function showHideDivForORS(){
	//alert($('[name="strAreadyRegisteredFlag"]')[2].checked);
	if($('[name="strAreadyRegisteredFlag"]')[2].checked){
		$('#divPatientListNonORS').hide();
		$('#divORSPatientList').show();
		$('#divCatDetilId').show();
	}else{
		$('#divORSPatientList').hide();
		$('#divPatientListNonORS').show();
		$('#divCatDetilId').show();
		//alert("here");
	}
	//alert("there");
}
function goForORS(e){

	var alreadyRegisteredFlag = e.val();
	var patPrimaryCatCode = '0';//$('[name="patPrimaryCatCode"]')[0].value;
	var searchCatName = '0';//$('[name="searchCatName"]')[0].value;
	var searchCatId = '0';//$('[name="searchCatId"]')[0].value;
	
	var action 	= "/HISRegistration/registration/transactions/getPatDtlOnPatCatIdNewRegistration.action?"+
					"patPrimaryCatCode="+patPrimaryCatCode+"&searchCatName="+searchCatName+
					"&searchCatId="+searchCatId+"&alreadyRegisteredFlag="+alreadyRegisteredFlag;
	
	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data){
		//alert(data)
		arrPatGlobalJsonObj=data;
		createPatientEmpRow(data);
					
	},error: function(errorMsg,textstatus,errorthrown) {
        alert('goForORS '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	}
	});
}

function searchInORSList(){
	//alert("Han chal gya");
	var name = $('#searchNameORSId').val();
	var mobNo = $('#searchMobileORSId').val();
	var searchCriteriaFlag =0; 
	if(name!=null && name!=""){
		searchCriteriaFlag = 1;
		setORSList(searchCriteriaFlag, name);
	}
	if(mobNo!=null && mobNo!="" &&  mobNo.length==10 && searchCriteriaFlag==0){
		searchCriteriaFlag = 2;	
		setORSList(searchCriteriaFlag, mobNo);
	}
	if(searchCriteriaFlag==0){
		$('[name="strAreadyRegisteredFlag"]')[2].checked=false;
		setORSList(0, 0);
		alert("Please Enter Any One Criteria..!");
		showHideDivForORS();
		return false;
	}
	
}
function setORSList(searchType, schVal){
	var allrows = document.getElementsByName("orsPatientList");

	for(i=0;i<allrows.length;i++)
		allrows[i].style.display="none";
		
	for(i=0;i<allrows.length;i++)
	{
		var str = allrows[i].id.split("@");
		/* if(searchType=="0")
		{
			var aptno=str[1];
			if(aptno==schVal)
				allrows[i].style.display="";
		} else*/
		if(searchType=="1")//first name
		{
			var name1 = schVal.toString();
			name1 = name1.toUpperCase();
			
			var nameArr=str[0].split("&");
			var fname, mname, lname, fullName;
			
			fname = nameArr[0];
			mname =	nameArr[1];
			lname =	nameArr[2];
			
			fname = fname.toString().toUpperCase();
			mname = mname.toString().toUpperCase();
			lname = lname.toString().toUpperCase();
			
			//alert(name[0]+"\n"+name[1]+"\n"+name[2]);
		
			if(name1==fname||name1==mname||name1==lname)
				allrows[i].style.display="";

			if(mname!="")
				fullName=fname+" "+mname+" "+lname;
			else
				fullName=fname+" "+lname;
						//alert(fullName+"\n"+name1);
			if(fullName==name1)
				allrows[i].style.display="";
		}
		else if(searchType=="2")//contact no
		{
			var cntno=str[1]
			if(cntno==schVal)
				allrows[i].style.display="";
		}
		
	}
	//document.getElementsByName('deptToSearchFrom')[0].value=0;//to reset the value of Department/unit back to All Units
}
</script>

</head>

<body onload="initializePopUp();">
<center>
<s:actionerror/>
<s:form action="NewRegistration">
    <%-- <s:set name="theme" value="simple" scope="page" /> --%>
    <div class="wrapper rounded">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
						Patient Search
				</div>
			</div>
			<div id="divAlreadyRegisteredId" class="div-table-row ">
				<div class="div-table-col Control width10" ></div>
				<div class="div-table-col Control width90" >
					<input type="radio" tabindex="1" name="strAreadyRegisteredFlag" value="1" disabled="disabled" />Patient Old
					<input type="radio" tabindex="1" name="strAreadyRegisteredFlag" value="2" disabled="disabled" />Portal User
					<input type="radio" tabindex="1" name="strAreadyRegisteredFlag" value="3" disabled="disabled" />ORS List <!-- onclick='getORSListId(this)' -->
					
				</div>
			</div>
			<div class="div-table-row separatorBar ">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar ">
					<div class="div-table-col"> </div>
			</div>
			
			<div class="div-table-row " id="divPatientListNonORS">
				<div class="div-table-col label width20" >
						Name
				</div>
				<div class="div-table-col Control width20" >
						<input type="text" name="searchCatName" id="searchCatName" tabindex="1" maxlength="30" size="10" onkeyup="allLetter(this)">
				</div>
				<div class="div-table-col label width20" >
						Temp Reg No.
				</div>
				<div class="div-table-col Control width20" >
						<input type="text" name="searchCatId"  tabindex="2" maxlength="18" size="10" onkeypress="if(event.keyCode==13) go();" >
				</div>
				<div class="div-table-col Control width20" >
						<input type="button" name="Go" id="goId" tabindex="1"  value="Go" onclick="go();">
				</div>
			
			</div>
			
			<div class="div-table-row "  id="divORSPatientList" style="display:none" >
				<div class="div-table-col label width18" >
						Name
				</div>
				<div class="div-table-col Control width20" >
						<input type="text" name="searchNameORS" id="searchNameORSId" tabindex="1" maxlength="30" size="10" onkeyup="allLetter(this)">
				</div>
				<div class="div-table-col Control width2" >OR</div>
				<div class="div-table-col label width20" >
						Mobile
				</div>
				<div class="div-table-col Control width20" >
						<input type="text" name="searchMobileORS"  id="searchMobileORSId" tabindex="2" maxlength="18" size="10">
				</div>
				<div class="div-table-col Control width20" >
						<input type="button" name="searchInORS" id="searchInORSId" tabindex="1" value="Search" onclick="searchInORSList()">
				</div>
			
			</div>
			
			<div id="divCatDetilId" class="div-table-listing rounded"></div>
		</div>
		<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" id="cancelPopupId" onclick="cancelPopup();"><span class="cancel">Cancel</span></a>
			</div>
			
		</div>
	</div>
	<s:hidden name="patPrimaryCatCode" value="%{patPrimaryCatCode}" />
	<s:hidden name="alreadyRegisteredFlag" value="%{alreadyRegisteredFlag}" />
	<cmbPers:cmbPers></cmbPers:cmbPers>
	
</s:form>
<script type="text/javascript" src="./../../registration/transactions/js/opdNewPatientRegistration.js" ></script>

</center>
</body>
</html>