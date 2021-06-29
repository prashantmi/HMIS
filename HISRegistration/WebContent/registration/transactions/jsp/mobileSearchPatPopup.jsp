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
	document.getElementById("searchNameId").style.display = "none";
	 document.getElementById("searchReferenceId").style.display = "none";
	var searchId="0";
	var searchValue="";
	ajaxCallForPopup(searchId,searchValue);
	
}

function ajaxCallForPopup(obj1,obj2)
{
	if(arrPatGlobalJsonObj=="")
	{
	var action 	= "/HISRegistration/registration/transactions/getPatDtlOnPatMobileNewRegistration.action?"+
	"searchId="+obj1+"&searchValue="+obj2;
	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data){
		arrPatGlobalJsonObj=data;
		createPatientEmpRow(data);	
			
	},error: function(errorMsg,textstatus,errorthrown) {
        alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
        
    }
	});
	}
else
	{
	
	 var arrPatJsonObj=[];
	 var isRecordFound=false;
	if(obj1=="0")
	{
	createPatientEmpRow(arrPatGlobalJsonObj);	
	}
	else
		{
		 for ( var i = 0; i < arrPatGlobalJsonObj.length; i++) {
		 var firstName=(arrPatGlobalJsonObj[i].patFirstName).toUpperCase();
		 if ((obj1=="1" && arrPatGlobalJsonObj[i].patAddMobileNo == obj2) ||(obj1=="2" && (firstName.indexOf(obj2.toUpperCase())> -1)) ||(obj1=="3" && arrPatGlobalJsonObj[i].patCrNo == obj2)) 
		 {	
		      isRecordFound=true;
			  arrPatJsonObj[i]=arrPatGlobalJsonObj[i];
			  createPatientEmpRow(arrPatJsonObj);
		 } 
		
	 } 
	 
	if(isRecordFound==false)
	{
	createPatientEmpRow("");
	}
	}
	}
}

function go(){
	
	var searchId=$('[name="searchId"]')[0].value;
	
	if(searchId==1)
		var searchValue=$('[name="searchMobile"]')[0].value;
	else if(searchId==2)
		var searchValue=$('[name="searchName"]')[0].value;
	else
		var searchValue=$('[name="searchReference"]')[0].value;
	
	if(searchValue=="")
		{
		searchId=0;
		}
	ajaxCallForPopup(searchId,searchValue);
	
	
}
 $('.simplemodal-close').click(function(){
	//alert("hello");
}); 
 
 
 function changeSearchField(obj)
 {
	 var searchId=obj.value;
	 document.getElementById("searchMobileId").value ="";
	 document.getElementById("searchNameId").value = "";
	 document.getElementById("searchReferenceId").value="";
	
	 if(searchId=="1")
	 	{
		 document.getElementById("searchMobileId").style.display = "";
		 document.getElementById("searchNameId").style.display = "none";
		 document.getElementById("searchReferenceId").style.display = "none";
		 }
	 else if(searchId=="2")
		 {
		 
		 document.getElementById("searchNameId").style.display = "";
		 document.getElementById("searchReferenceId").style.display = "none";
		 document.getElementById("searchMobileId").style.display = "none";
		
		 }
	 else if(searchId=="3")
		 {
		 document.getElementById("searchReferenceId").style.display = "";
		 document.getElementById("searchMobileId").style.display = "none";
		 document.getElementById("searchNameId").style.display = "none";
		 }
 }
 
function cancelPopup(){
	var alreadyRegisteredFlag = $('[name="alreadyRegisteredFlag"]')[0].value;
	if(parseInt(alreadyRegisteredFlag)> 0){
		if(parent.document.getElementsByName("hiddenPatIdNo")[0].value == "")
			parent.document.getElementsByName("alreadyRegisteredFlag")[0].checked=false;
	}
	parent.closeModal();
}

function callOpenerFetchPatDtlBasedOnPatCatCardNo(patIndexId){

	$("#patAgeId",parent.document).val($('[name="patAge"]')[patIndexId.value].value);
	parent.document.getElementsByName("hiddenPatIdNo")[0].value=$('[name="patIdNo"]')[patIndexId.value].value;
	parent.document.getElementsByName("patAgeUnit")[0].value=$('[name="patAgeUnit"]')[patIndexId.value].value;
	window.parent.opdregistration.fetchPatDtlBasedOnPatCatCardNo(arrPatGlobalJsonObj[patIndexId.value]);
	//closeModal();
	parent.document.getElementsByName("searchUsingMobile")[0].checked=false;
	parent.closeModal();
}



function createPatientEmpRow(arrStrPatJsonObj)
{
	var empHeaderRow =	"<div class='div-table-row title' style='background:'blue''>"+
							"<div class='div-table-col width10 alignCenter'>Select</div>"+
							"<div class='div-table-col width20 alignCenter'>Name</div>"+
							//"<div class='div-table-col width20 alignCenter'>Id</div>"+
							"<div class='div-table-col width20 alignCenter'>Gender</div>"+
							"<div class='div-table-col width10 alignCenter'>Age</div>"+
							"<div class='div-table-col width20 alignCenter'>Reference No</div>"+
							"<div class='div-table-col width20 alignCenter'>Cr No.</div>"+
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
		 if(arrPatJsonObj[i]["patRegistered"]=="1" ){
			disabled="disabled='disabled'";
		}
		if(parseInt($('[name="alreadyRegisteredFlag"]')[0].value)> 0){
			varId=arrPatJsonObj[i]["prevCrNo"];
		}else{
			varId=arrPatJsonObj[i]["patIdNo"];
		}
		
			var objPatAge = arrPatJsonObj[i]["patAgeWithUnit"].split(" ");
			var patAge	  =	objPatAge[0];
			var patAgeUnit= objPatAge[1];
			//alert("patCrNo :"+ arrPatJsonObj[i]["patCrNo"]);
			empDtlRow +=	"<div class='div-table-row listData'>"+
							"<div class='div-table-col width10 alignCenter'>"+
								"<input type='radio' name='patIndex' value='"+i+"' onclick='callOpenerFetchPatDtlBasedOnPatCatCardNo(this);'"+disabled+" />"+
								"<input type='hidden' name='patIdNo' value='"+arrPatJsonObj[i]["patIdNo"]+"' />"+
								"<input type='hidden' name='patAge' value='"+patAge+"' />"+
								"<input type='hidden' name='patAgeUnit' value='"+patAgeUnit+"' />"+
								"<input type='hidden' name='patRefNo' value='"+arrPatJsonObj[i]["patRefNo"]+"' />"+
							"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patFirstName"]+" "+arrPatJsonObj[i]["patMiddleName"]+" "+arrPatJsonObj[i]["patLastName"]+ "</div>"+
							//"<div class='div-table-col width20 alignCenter'>"+varId+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patGenderCode"]+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patAgeWithUnit"]+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patRefNo"]+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patCrNo"]+"</div>"+
						"</div>";
	}
	}
	else{
		empDtlRow +=	"<div class='div-table-row listData'><b>No Record Found<b></div>";
	}
	$("#divCatDetilId").html(empHeaderRow+empDtlRow);
	
}


function validateNumeric(e)
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
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}
function validateAlphabetsOnly(e)
{
	//alphabets only
		
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
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;
	
	if((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
				return true;
	else
	   return false;
}
</script>

</head>

<body onload="initializePopUp();">
<center>

<s:form action="mobileSearch">
    <%-- <s:set name="theme" value="simple" scope="page" /> --%>
    <div class="wrapper rounded">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
						Patient Search
				</div>
			</div>
			
			<div class="div-table-row separatorBar ">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar ">
					<div class="div-table-col"> </div>
			</div>
			
			<table style="width: 100%">
			<tr>
			<td style="width: 10%;padding-top:10px;">
			<s:text name="search"/>&nbsp;<s:text name="by"/>: &nbsp;
			</td>
			<td style="width: 35%">
				<select name="searchId" tabindex="1" class="select100prcnt" onchange="changeSearchField(this)">
						<option value="1">Mobile No.</option>
						<option value="2">First Name</option>
						<option value="3">Reference No</option>
				</select>
					
			</td>	
				<td style="width:55%;">
				
			
						<input type="text" name="searchMobile" id="searchMobileId"  tabindex="1" maxlength="10" onkeypress="return validateNumeric(event)" >
						
						<input type="text" name="searchName" id="searchNameId" tabindex="2"  onkeypress="return validateAlphabetsOnly(event)">
				
						<input type="text" name="searchReference" id="searchReferenceId" tabindex="2" maxlength="10" onkeypress="return validateNumeric(event)">
				
						<input type="button" name="Go" id="goId" tabindex="1"  value="Go" onclick="go();">
				
				</td>
				</tr>
			</table>
			</div>
	
			<div id="divCatDetilId" class="div-table-listing rounded"></div>
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
		
	<!-- </div> -->
	<s:hidden name="patPrimaryCatCode" value="%{patPrimaryCatCode}" />
	<s:hidden name="alreadyRegisteredFlag" value="%{alreadyRegisteredFlag}" />
	<cmbPers:cmbPers></cmbPers:cmbPers>
	
</s:form>
<script type="text/javascript" src="./../../registration/transactions/js/opdNewPatientRegistration.js" /></script>
</center>
</body>
</html>