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

function initializePopUp(){
	//$('[name="alreadyRegisteredFlag"]')[0].value="2";
	if($('[name="alreadyRegisteredFlag"]')[0].value=="0"){
		$("#divAlreadyRegisteredId").hide();
	}else if($('[name="alreadyRegisteredFlag"]')[0].value=="1"){
		$("#divAlreadyRegisteredId").show();		
		$('[name="strAreadyRegisteredFlag"]')[0].checked=true;
		$('[name="strAreadyRegisteredFlag"]')[0].disabled=false;
		//$('[name="strAreadyRegisteredFlag"]').attr("disabled", false);
	}
	else if ($('[name="alreadyRegisteredFlag"]')[0].value=="2"){
		$('[name="strAreadyRegisteredFlag"]')[1].checked=true;
		$('[name="strAreadyRegisteredFlag"]')[1].disabled=false;
		$("#divAlreadyRegisteredId").show()	;	
	}else if ($('[name="alreadyRegisteredFlag"]')[0].value=="3"){
		$('[name="strAreadyRegisteredFlag"]')[0].checked=true;
		$('[name="strAreadyRegisteredFlag"]')[0].disabled=false;
		$('[name="strAreadyRegisteredFlag"]')[1].disabled=false;
	}
}
function go(){
	var patPrimaryCatCode = $('[name="patPrimaryCatCode"]')[0].value;
	var searchCatName = $('[name="searchCatName"]')[0].value;
	var searchCatId = $('[name="searchCatId"]')[0].value;
	var val;
	var alreadyRegisteredFlag = "0";
	if($('[name="strAreadyRegisteredFlag"]')[0].checked)
		alreadyRegisteredFlag=$('[name="strAreadyRegisteredFlag"]')[0].value;
	else if($('[name="strAreadyRegisteredFlag"]')[1].checked)
		alreadyRegisteredFlag=$('[name="strAreadyRegisteredFlag"]')[1].value;
		
	var action 	= "/HISRegistration/registration/transactions/getPatDtlOnPatCatIdNewRegistration.action?"+
					"patPrimaryCatCode="+patPrimaryCatCode+"&searchCatName="+searchCatName+
					"&searchCatId="+searchCatId+"&alreadyRegisteredFlag="+alreadyRegisteredFlag;
	
	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data){
		arrPatGlobalJsonObj=data;
		if($('[name="searchCatName"]')[0].value.trim!="" || $('[name="searchCatId"]')[0].value.trim!="")
		{
			if($('[name="searchCatName"]')[0].value.trim()!="")
				{
					val=$('[name="searchCatName"]')[0].value.trim();
					if(val.length >=3)
						{
							createPatientEmpRow(data);
						}
					else
						{
							alert("Enter atleast 3 characters in Name without space");
						}
				}
			else if($('[name="searchCatId"]')[0].value.trim!="")
				{
					createPatientEmpRow(data);
				}
		}
		else
			alert("Please Enter any one of the Filter criteria..!");
			
	},error: function(errorMsg,textstatus,errorthrown) {
        alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
        
    }
	});
}
 $('.simplemodal-close').click(function(){
	alert("hello");
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
	//parent.document.getElementsByName("hiddenPatIdNo")[0].value=$('[name="patIdNo"]')[patIndexId.value].value;
	parent.document.getElementsByName("patIdNo2")[0].value=$('[name="patIdNo"]')[patIndexId.value].value;
	//alert($('[name="memSlNo"]')[patIndexId.value].value);
	parent.document.getElementsByName("memSlNo")[0].value=$('[name="memSlNo"]')[patIndexId.value].value;
	//parent.document.getElementsByName("hiddenPatIdNo2")[0].value=$('[name="patIdNo"]')[patIndexId.value].value;
	//parent.document.getElementsByName("hiddenPatIdNo1")[0].value=$('[name="patIdNo"]')[patIndexId.value].value;
	//parent.document.getElementsByName("patAgeUnit")[0].value=$('[name="patAgeUnit"]')[patIndexId.value].value;
	//window.parent.opdregistration.fetchPatDtlBasedOnPatCatCardNo(arrPatGlobalJsonObj[patIndexId.value]);
	//closeModal();
	parent.closeModal();
}



function createPatientEmpRow(arrStrPatJsonObj)
{
	var empHeaderRow =	"<div class='div-table-row listHeader'>"+
							/* "<div class='div-table-col width10 alignCenter'>Select</div>"+
							"<div class='div-table-col width20 alignCenter'>Name</div>"+
							"<div class='div-table-col width20 alignCenter'>Id</div>"+
							"<div class='div-table-col width20 alignCenter'>Gender</div>"+
							"<div class='div-table-col width10 alignCenter'>Age</div>"+
							"<div class='div-table-col width20 alignCenter'>Cr No</div>"+ */
							"<div class='div-table-col width5 alignCenter'>Select</div>"+
							"<div class='div-table-col width12 alignCenter'>Name</div>"+
							"<div class='div-table-col width13 alignCenter'>Employee Id</div>"+
							"<div class='div-table-col width10 alignCenter'>Gender/Age</div>"+							
							"<div class='div-table-col width10 alignCenter'>Job Type</div>"+
							"<div class='div-table-col width10 alignCenter'>Designation</div>"+
							"<div class='div-table-col width10 alignCenter'>Dependent Relation</div>"+
							"<div class='div-table-col width10 alignCenter'>Category</div>"+
							"<div class='div-table-col width20 alignCenter'>CR No</div>"+
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
			var patAgeUnit= objPatAge[1];
			//alert("patCrNo :"+ arrPatJsonObj[i]["patCrNo"]);
			if(arrPatJsonObj[i]["patMemName"]=="--")
				{
			empDtlRow +=	"<b><div class='div-table-row listData'>"+
							"<div class='div-table-col width5 alignCenter' >"+
								"<input type='radio' name='patIndex' value='"+i+"' onclick='callOpenerFetchPatDtlBasedOnPatCatCardNo(this);'"+disabled+" />"+
								"<input type='hidden' name='patIdNo' value='"+arrPatJsonObj[i]["patIdNo"]+"' />"+
								"<input type='hidden' name='patAge' value='"+patAge+"' />"+
								"<input type='hidden' name='patAgeUnit' value='"+patAgeUnit+"' />"+
								"<input type='hidden' name='patJobTypeId' value='"+arrPatJsonObj[i]["patJobTypeId"]+"' />"+
								"<input type='hidden' name='patDesignationCode' value='"+arrPatJsonObj[i]["patDesignationCode"]+"' />"+
								"<input type='hidden' name='patMemRelationCode' value='"+arrPatJsonObj[i]["patMemRelationCode"]+"' />"+
								"<input type='hidden' name='patPrimaryCatCode' value='"+arrPatJsonObj[i]["patPrimaryCatCode"]+"' />"+
								//alert(arrPatJsonObj[i]["memslno"]);
								"<input type='hidden' name='memSlNo' value='"+arrPatJsonObj[i]["memslno"]+"' />"+
								"<input type='hidden' name='patRelatedEmpName' value='"+arrPatJsonObj[i]["patRelatedEmpName"]+"' />"+
								"<input type='hidden' name='patRelatedEmpGender' value='"+arrPatJsonObj[i]["patRelatedEmpGender"]+"' />"+
								"<input type='hidden' name='patMemRelationName' value='"+arrPatJsonObj[i]["patMemRelationName"]+"' />"+
								"<input type='hidden' name='patMemDeptName' value='"+arrPatJsonObj[i]["patMemDeptName"]+"' />"+
								
							"</div>"+
							"<div class='div-table-col width12 alignCenter'>"+arrPatJsonObj[i]["patFirstName"]+" "+arrPatJsonObj[i]["patMiddleName"]+" "+arrPatJsonObj[i]["patLastName"]+ "</div>"+
							"<div class='div-table-col width13 alignCenter'>"+varId+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patGenderCode"]+"/"+arrPatJsonObj[i]["patAgeWithUnit"]+"</div>"+
							"<div class='div-table-col width10 alignCenter' >"+arrPatJsonObj[i]["patJobType"]+"</div>"+
							"<div class='div-table-col width10 alignCenter'  >"+arrPatJsonObj[i]["patDesignation"]+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patMemRelationName"]+"</div>"+
							"<div class='div-table-col width10 alignCenter' >"+arrPatJsonObj[i]["patPrimaryCatName"]+"</div>"+
							"<div class='div-table-col width20 alignCenter' >"+arrPatJsonObj[i]["patCrNo"]+"</div>"+							
							
							
						"</b></div>";
				}
			else
				{
			empDtlRow +=	"<div class='div-table-row listData'>"+
							/* "<div class='div-table-col width10 alignCenter'>"+
								"<input type='radio' name='patIndex' value='"+i+"' onclick='callOpenerFetchPatDtlBasedOnPatCatCardNo(this);'"+disabled+" />"+
								"<input type='hidden' name='patIdNo' value='"+arrPatJsonObj[i]["patIdNo"]+"' />"+
								"<input type='hidden' name='patAge' value='"+patAge+"' />"+
								"<input type='hidden' name='patAgeUnit' value='"+patAgeUnit+"' />"+ */
								"<div class='div-table-col width5 alignCenter' style='overflow:auto;'>"+
								"<input type='radio' name='patIndex' value='"+i+"' onclick='callOpenerFetchPatDtlBasedOnPatCatCardNo(this);'"+disabled+" />"+
								"<input type='hidden' name='patIdNo' value='"+arrPatJsonObj[i]["patIdNo"]+"' />"+
								"<input type='hidden' name='patAge' value='"+patAge+"' />"+
								"<input type='hidden' name='patAgeUnit' value='"+patAgeUnit+"' />"+
								"<input type='hidden' name='patJobTypeId' value='"+arrPatJsonObj[i]["patJobTypeId"]+"' />"+
								"<input type='hidden' name='patDesignationCode' value='"+arrPatJsonObj[i]["patDesignationCode"]+"' />"+
								"<input type='hidden' name='patMemRelationCode' value='"+arrPatJsonObj[i]["patMemRelationCode"]+"' />"+
								"<input type='hidden' name='patPrimaryCatCode' value='"+arrPatJsonObj[i]["patPrimaryCatCode"]+"' />"+
								"<input type='hidden' name='patRelatedEmpName' value='"+arrPatJsonObj[i]["patRelatedEmpName"]+"' />"+
								"<input type='hidden' name='patRelatedEmpGender' value='"+arrPatJsonObj[i]["patRelatedEmpGender"]+"' />"+
								"<input type='hidden' name='patMemRelationName' value='"+arrPatJsonObj[i]["patMemRelationName"]+"' />"+
								"<input type='hidden' name='patMemDeptName' value='"+arrPatJsonObj[i]["patMemDeptName"]+"' />"+
							"</div>"+
							"<div class='div-table-col width12 alignCenter'>"+arrPatJsonObj[i]["patFirstName"]+" "+arrPatJsonObj[i]["patMiddleName"]+" "+arrPatJsonObj[i]["patLastName"]+ "</div>"+
							/* "<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patIdNo"]+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patGenderCode"]+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patAgeWithUnit"]+"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrPatJsonObj[i]["patCrNo"]+"</div>"+ */
							"<div class='div-table-col width13 alignCenter'>"+varId+"</div>"+
							"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patGenderCode"]+"/"+arrPatJsonObj[i]["patAgeWithUnit"]+"</div>"+
							"<div class='div-table-col width10 alignCenter' >"+arrPatJsonObj[i]["patJobType"]+"</div>"+
							"<div class='div-table-col width10 alignCenter' >"+arrPatJsonObj[i]["patDesignation"]+"</div>"+
							"<div class='div-table-col width10 alignCenter' >"+arrPatJsonObj[i]["patMemRelationName"]+"</div>"+
							"<div class='div-table-col width10 alignCenter' >"+arrPatJsonObj[i]["patPrimaryCatName"]+"</div>"+
							"<div class='div-table-col width20 alignCenter' >"+arrPatJsonObj[i]["patCrNo"]+"</div>"+
						"</div>";
				}
	}
	}
	else{
		empDtlRow +=	"<div class='div-table-row listData'>No Record Found</div>";
	}
	$("#divCatDetilId").html(empHeaderRow+empDtlRow);
	
}

function validate() {
    var nm = document.getElementsByName("searchCatName");
    var alpha = /^[a-zA-Z\s]+$/;  
    if (!nm.value.match(alpha)) {
        alert('Enter only alphabet and space');       
        return false;
   }
   else 
   {
    return true;
   }
}


</script>

</head>

<body onload="initializePopUp();">
<center>

<s:form action="NewRegistration">
    <%-- <s:set name="theme" value="simple" scope="page" /> --%>
    <div class="wrapper rounded">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
						<s:text name="global.patient"/>&nbsp;<s:text name="search"/> 
				</div>
			</div>
			<div id="divAlreadyRegisteredId" class="div-table-row " style="display:none">
				<div class="div-table-col Control width10" ></div>
				<div class="div-table-col Control width90" >
					<input type="radio" name="strAreadyRegisteredFlag" value="1" disabled="disabled" /><s:text name="global.patient"/>&nbsp;<s:text name="old"/>   
					<input type="radio" name="strAreadyRegisteredFlag" value="2" disabled="disabled" /><s:text name="portal"/>&nbsp;<s:text name="user"/>    
				</div>
			</div>
			<div class="div-table-row separatorBar ">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar ">
					<div class="div-table-col"> </div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label width20" >
						<s:text name="global.name"/>   
				</div>
				<div class="div-table-col Control width20" >
						<input type="text" name="searchCatName" tabindex="1" maxlength="30" size="10" onkeypress="return validate();">
				</div>
				<div class="div-table-col label width20" >
						<s:text name="global.id"/>&nbsp;<s:text name="slash"/>&nbsp;<s:text name="global.cardno"/>   
				</div>
				<div class="div-table-col Control width20" >
						<input type="text" name="searchCatId" tabindex="2" maxlength="18" size="10">
				</div>
				<div class="div-table-col Control width20" >
						<input type="button" name="Go" id="goId" tabindex="1" value="Go" onclick="go();">
				</div>
			
			</div>
	
			<div id="divCatDetilId" class="div-table-listing rounded" style="width : 100%"></div>
		</div>
		<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" id="cancelPopupId" onclick="cancelPopup();"><span class="cancel"><s:text name="cancel"/></span></a>
			</div>
			
		</div>
	</div>
	<s:hidden name="patPrimaryCatCode" value="%{patPrimaryCatCode}" />
	<s:hidden name="alreadyRegisteredFlag" value="%{alreadyRegisteredFlag}" />
	<cmbPers:cmbPers></cmbPers:cmbPers>
	
</s:form>
<script type="text/javascript" src="./../../registration/transactions/js/opdNewPatientRegistration.js" /></script>
</center>
</body>
</html>