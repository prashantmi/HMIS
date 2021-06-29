<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--<%@ taglib uri="/WEB-INF/csrfguard.tld" prefix="csrf" %>--%> 
<%@page %>
<html>
<head>
<!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"  type="text/css" href="/HIS/hisglobal/css/buttons.css" >
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/layout.css" >
<link rel="stylesheet" href="/HIS/hisglobal/css/basic.css">

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.easyui.js"></script>
<script type='text/javascript' src='/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/js/basic.js"></script>

<script type="text/javascript" src="/HISRegistration/hisglobal/js/validation.js"></script>

<link href="/HISRegistration/hisglobal/css/global.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/HISRegistration/hr/common/js/pisGlobal.js"></script>
<%-- <jsp:include page="/hr/common/jsp/globalFields.jsp" /> --%>

 <%@ page import="hr.pis.config.PisConfig"%>


<style type="text/css">


</style>

<title>Employee Registration</title>
<script type='text/javascript'>


var arrPatGlobalJsonObj=[];

function initializePopUp(){
	//$('[name="alreadyRegisteredFlag"]')[0].value="2";
	/*if($('[name="strEmployeeUpdateFlag"]')[0].value=="0"){
		$("#divAlreadyRegisteredId").hide();
	}else if($('[name="divAlreadyRegisteredId"]')[0].value=="1"){
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
	}*/
}


function go(){
	//var patPrimaryCatCode = $('[name="patPrimaryCatCode"]')[0].value;
	var searchEmpName = $('[name="searchEmpName"]')[0].value;
	var searchEmpNo = $('[name="searchEmpNo"]')[0].value;
	//var alreadyRegisteredFlag = "0";
	/*if($('[name="strAreadyRegisteredFlag"]')[0].checked)
		alreadyRegisteredFlag=$('[name="strAreadyRegisteredFlag"]')[0].value;
	else if($('[name="strAreadyRegisteredFlag"]')[1].checked)
		alreadyRegisteredFlag=$('[name="strAreadyRegisteredFlag"]')[1].value;
	*/	
	var action 	= "/HISRegistration/pis/masters/getEmpDtlEmployeeRegistration.action?"+
					"&searchEmpName="+searchEmpName+
					"&searchEmpNo="+searchEmpNo;
	
	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data){
		//alert(data);
		arrPatGlobalJsonObj=data;
		if($('[name="searchEmpName"]')[0].value!="" ||$('[name="searchEmpNo"]')[0].value!="")
			createEmployeeEmpRow(data);	
		else
			showAlert ("1", "Please Enter Any One Criteria..!");
			//alert("Please Enter Any One Criteria..!");
			
	},error: function(errorMsg,textstatus,errorthrown) {
        alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
        
    }
	});
}
 $('.simplemodal-close').click(function(){
	//alert("hello");
}); 
 
//Modified by Ashwini Mishra on 09-03-2016
function cancelPopupFooter(){
	/*var alreadyRegisteredFlag = $('[name="strEmployeeUpdateFlag"]')[0].value;
	if(parseInt(alreadyRegisteredFlag)> 0){
		if(parent.document.getElementsByName("hiddenPatIdNo")[0].value == "")
			parent.document.getElementsByName("alreadyRegisteredFlag")[0].checked=false;
	}*/
	parent.document.getElementsByName("strEmployeeUpdateFlag")[0].checked=false;
	parent.setSaveOrUpdateFooterVisibility();
	parent.closeModal();
}

function callOpenerFetchEmpDtl(empIndexId){
	var strEmployeeUpdateFlag = $('[name="strEmployeeUpdateFlag"]')[0].value;
	
	/*if(parseInt(alreadyRegisteredFlag)> 0){
		$("#hiddenCatOrRegstrdPopupFlagId",parent.document).val("A");
	}else{
		$("#hiddenCatOrRegstrdPopupFlagId",parent.document).val("C");
	}*/
	//$("#patAgeId",parent.document).val($('[name="patAge"]')[patIndexId.value].value);
	parent.document.getElementsByName("strEmployeeNumber")[0].value=$('[name="empNo"]')[empIndexId.value].value;
	parent.document.getElementsByName("intNatureOfJobId")[0].value=$('[name="natureOfJob"]')[empIndexId.value].value;
	parent.document.getElementsByName("intAppellationCode1")[0].value=$('[name="appellation1"]')[empIndexId.value].value;
	parent.document.getElementsByName("intAppellationCode2")[0].value=$('[name="appellation2"]')[empIndexId.value].value;
	
	parent.document.getElementsByName("strEmployeeFullName")[0].value=$('[name="empName"]')[empIndexId.value].value;
	//parent.document.getElementsByName("strEmployeeFullRegionalLangName")[0].value=$('[name="empRegLangName"]')[empIndexId.value].value;
	
	parent.document.getElementsByName("strEmployeeShortName")[0].value=$('[name="empShortName"]')[empIndexId.value].value;
	//parent.document.getElementsByName("strEmployeeShortRegionalLangName")[0].value=$('[name="empShortRegLangName"]')[empIndexId.value].value;
	//parent.document.getElementsByName("intSuffixCode")[0].value=$('[name="suffix"]')[empIndexId.value].value;
	parent.document.getElementsByName("dtEmployeeDOB")[0].value=$('[name="empDOB"]')[empIndexId.value].value;
	parent.document.getElementsByName("strGenderCode")[0].value=$('[name="gender"]')[empIndexId.value].value;
	parent.document.getElementsByName("intNationalityId")[0].value=$('[name="nationality"]')[empIndexId.value].value;
	parent.document.getElementsByName("intDepartmentCode")[0].value=$('[name="department"]')[empIndexId.value].value;
	parent.document.getElementsByName("intDesignationCode")[0].value=$('[name="designation"]')[empIndexId.value].value;
	parent.document.getElementsByName("strOldEmployeeNumber")[0].value=$('[name="oldEmpNo"]')[empIndexId.value].value;
	parent.document.getElementsByName("strEmployeeFinalStatus")[0].value=$('[name="empFinalStatus"]')[empIndexId.value].value;
	parent.document.getElementsByName("strLastEmploymentType")[0].value=$('[name="lastEmploymentType"]')[empIndexId.value].value;
	parent.document.getElementsByName("intMobileNumber")[0].value=$('[name="mobileNo"]')[empIndexId.value].value;
	parent.document.getElementsByName("strPersonalEmailId")[0].value=$('[name="emailId"]')[empIndexId.value].value;
	parent.document.getElementsByName("strPANNumber")[0].value=$('[name="panNo"]')[empIndexId.value].value;
	
	// Setting Fields for Update Check
	parent.document.getElementsByName("updateCheckintNatureOfJobId")[0].value=$('[name="natureOfJob"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckintAppellationCode1")[0].value=$('[name="appellation1"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckintAppellationCode2")[0].value=$('[name="appellation2"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrEmployeeFullName")[0].value=$('[name="empName"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrEmployeeFullRegionalLangName")[0].value=$('[name="empRegLangName"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrEmployeeShortName")[0].value=$('[name="empShortName"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrEmployeeShortRegionalLangName")[0].value=$('[name="empShortRegLangName"]')[empIndexId.value].value;
	//parent.document.getElementsByName("updateCheckintSuffixCode")[0].value=$('[name="suffix"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrGenderCode")[0].value=$('[name="gender"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckintNationalityId")[0].value=$('[name="nationality"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckintDepartmentCode")[0].value=$('[name="department"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckintDesignationCode")[0].value=$('[name="designation"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrEmployeeFinalStatus")[0].value=$('[name="empFinalStatus"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrLastEmploymentType")[0].value=$('[name="lastEmploymentType"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrOldEmployeeNumber")[0].value=$('[name="oldEmpNo"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckintMobileNumber")[0].value=$('[name="mobileNo"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrPersonalEmailId")[0].value=$('[name="emailId"]')[empIndexId.value].value;
	parent.document.getElementsByName("updateCheckstrPANNumber")[0].value=$('[name="panNo"]')[empIndexId.value].value;
	//parent.document.getElementsByName("patAgeUnit")[0].value=$('[name="patAgeUnit"]')[patIndexId.value].value;
	//window.parent.opdregistration.fetchPatDtlBasedOnPatCatCardNo(arrPatGlobalJsonObj[patIndexId.value]);
	//closeModal();
	
	// This is for Old Record Compatilbility - if PAN No is blank then it will set NA
	if($('[name="panNo"]')[empIndexId.value].value=='')
	{
		parent.document.getElementsByName("strPANNumber")[0].value='NA';
		parent.document.getElementsByName("updateCheckstrPANNumber")[0].value='NA';
	}

	parent.enableDisableFormFields("update");
	parent.closeModal();
}



function createEmployeeEmpRow(arrStrEmpJsonObj)
{
	var empHeaderRow =	"<div class='div-table-row listHeader'>"+
							"<div class='div-table-col width10 alignCenter'><s:text name='emp.reg.search.emp.select'></s:text></div>"+
							"<div class='div-table-col width20 alignCenter'><s:text name='emp.reg.search.emp.number'></s:text></div>"+
							"<div class='div-table-col width25 alignCenter'><s:text name='emp.reg.search.emp.name'></s:text></div>"+
							/* "<div class='div-table-col width25 alignCenter'><s:text name='emp.reg.search.emp.name.regional.language'></s:text></div>"+ */
							"<div class='div-table-col width20 alignCenter'><s:text name='emp.reg.search.emp.dob'></s:text></div>"+
						"</div>";
	var empDtlRow="";
	//var arrPatJsonObj = eval('(' + arrStrPatJsonObj + ')');
	var arrEmpJsonObj = arrStrEmpJsonObj;
	var varId="";
	if(arrEmpJsonObj!="")
	{
	for (i in arrEmpJsonObj)
	{
		var disabled="";
		//if(arrEmpJsonObj[i]["empNo"]!="" && arrEmpJsonObj[i]["empNo"]!="0" && arrEmpJsonObj[i]["empNo"]!="-" && arrEmpJsonObj[i]["empNo"]!="--" && arrEmpJsonObj[i]["empNo"]!="---"){
			//disabled="disabled='disabled'";
		//}
		
		/*if(parseInt($('[name="alreadyRegisteredFlag"]')[0].value)> 0){
			varId=arrPatJsonObj[i]["prevCrNo"];
		}else{
			varId=arrPatJsonObj[i]["patIdNo"];
		}*/
		
			//var objPatAge = arrPatJsonObj[i]["patAgeWithUnit"].split(" ");
			//var patAge	  =	objPatAge[0];s
			//var patAgeUnit= objPatAge[1];
			//alert("patCrNo :"+ arrPatJsonObj[i]["patCrNo"]);
			empDtlRow +=	"<div class='div-table-row listData'>"+
							"<div class='div-table-col width10 alignCenter'>"+
								"<input type='radio' name='empIndex' value='"+i+"' onclick='callOpenerFetchEmpDtl(this);'"+disabled+" />"+
								"<input type='hidden' name='empNo' value='"+arrEmpJsonObj[i]["empNo"]+"' />"+
								"<input type='hidden' name='empName' value='"+arrEmpJsonObj[i]["empName"]+"' />"+
								"<input type='hidden' name='empRegLangName' value='"+arrEmpJsonObj[i]["empNameInRegLang"]+"' />"+
								"<input type='hidden' name='empDOB' value='"+arrEmpJsonObj[i]["empDoB"]+"' />"+
								"<input type='hidden' name='natureOfJob' value='"+arrEmpJsonObj[i]["natureOfJob"]+"' />"+
								"<input type='hidden' name='appellation1' value='"+arrEmpJsonObj[i]["appellation1"]+"' />"+
								"<input type='hidden' name='appellation2' value='"+arrEmpJsonObj[i]["appellation2"]+"' />"+
								"<input type='hidden' name='empShortName' value='"+arrEmpJsonObj[i]["empShortName"]+"' />"+
								"<input type='hidden' name='empShortRegLangName' value='"+arrEmpJsonObj[i]["empShortNameInRegLang"]+"' />"+
								"<input type='hidden' name='suffix' value='"+arrEmpJsonObj[i]["suffix"]+"' />"+
								"<input type='hidden' name='gender' value='"+arrEmpJsonObj[i]["gender"]+"' />"+
								"<input type='hidden' name='nationality' value='"+arrEmpJsonObj[i]["nationality"]+"' />"+
								"<input type='hidden' name='department' value='"+arrEmpJsonObj[i]["department"]+"' />"+
								"<input type='hidden' name='designation' value='"+arrEmpJsonObj[i]["designation"]+"' />"+
								"<input type='hidden' name='oldEmpNo' value='"+arrEmpJsonObj[i]["oldEmpNo"]+"' />"+
								"<input type='hidden' name='empFinalStatus' value='"+arrEmpJsonObj[i]["empFinalStatus"]+"' />"+
								"<input type='hidden' name='lastEmploymentType' value='"+arrEmpJsonObj[i]["lastEmploymentType"]+"' />"+
								"<input type='hidden' name='mobileNo' value='"+arrEmpJsonObj[i]["mobileNo"]+"' />"+
								"<input type='hidden' name='emailId' value='"+arrEmpJsonObj[i]["emailId"]+"' />"+
								"<input type='hidden' name='panNo' value='"+arrEmpJsonObj[i]["panNo"]+"' />"+
							"</div>"+
							"<div class='div-table-col width20 alignCenter'>"+arrEmpJsonObj[i]["empNo"]+ "</div>"+
							"<div class='div-table-col width25 alignCenter'>"+arrEmpJsonObj[i]["empName"]+"</div>"+
							/* "<div class='div-table-col width25 alignCenter'>"+arrEmpJsonObj[i]["empNameInRegLang"]+"</div>"+ */
							"<div class='div-table-col width20 alignCenter'>"+arrEmpJsonObj[i]["empDoB"]+"</div>"+
						"</div>";
	}
	}
	else{
		empDtlRow +=	"<div class='div-table-row listData'><center><b>No Record Found</b></center></div>";
	}
	$("#divEmpDetilId").html(empHeaderRow+empDtlRow);
	
}

function JSON_stringify(s, emit_unicode)
{
   var json = JSON.stringify(s);
   return emit_unicode ? json : json.replace(/[\u007f-\uffff]/g,
      function(c) { 
        return '\\u'+('0000'+c.charCodeAt(0).toString(16)).slice(-4);
      }
   );
}

</script>

</head>

<body onload="initializePopUp();" style="margin-left: 0px;">
<center>

<s:form action="EmployeeRegistration">
    <%-- <s:set name="theme" value="simple" scope="page" /> --%>
    <div class="wrapper rounded">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
						<s:text name="emp.reg.search.heading.1"></s:text>
				</div>
			</div>
			
			<div class="div-table-row separatorBar ">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar ">
					<div class="div-table-col"> </div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label width30" >
						<s:text name="emp.reg.search.emp.name"></s:text>
				</div>
				<div class="div-table-col Control width60" >
						<input type="text" name="searchEmpName" maxlength="99" size="35" onPaste='return false' onkeypress='<%="return validateData(event,"+4+")" %>' >
				</div>
				
				<div class="div-table-col Control width10" >
						
				</div>
			
			</div>
			<div class="div-table-row ">
					<div class="div-table-col label width30" >(<s:text name="emp.reg.search.emp.search.operator"></s:text>) </div>
					<div class="div-table-col Control width50" > </div>
					<div class="div-table-col Control width20" >
						<a href="#" class="smallButton" name="Go" id="goId" onclick="go();"><span class="go"  style="cursor: pointer" title='Go'><s:text name="emp.reg.search.emp.search.go"></s:text></span></a>
					</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label width30" >
						<s:text name="emp.reg.search.emp.number"></s:text>
				</div>
				<div class="div-table-col Control width60" >
						<input type="text" name="searchEmpNo" maxlength='<%=PisConfig.EMP_NO_LENGTH %>' size="35" onPaste='return false' onkeypress='<%="return validateData(event,"+PisConfig.EMP_NO_TYPE_CODE+")" %> '>
				</div>
				<div class="div-table-col Control width10" >
						
				</div>
			
			</div>
	
			<div id="divEmpDetilId" class="div-table-listing rounded"></div>
			
		</div>
		<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" id="cancelPopupId" onclick="cancelPopupFooter();"><span class="cancel" style="cursor: pointer" title='Cancel'><s:text name="emp.reg.footer.cancel"></s:text></span></a>
			</div>
			
		</div>
	</div>
	
	<s:hidden name="strEmployeeUpdateFlag" value="%{strEmployeeUpdateFlag}" />

<s:token />
<%--<input type="hidden" name="<csrf:tokenname/>" value="<csrf:tokenvalue/>"/>--%> 		
</s:form>
<script type="text/javascript" src="/HISRegistration/hr/pis/empReg/transactions/js/empRegistration.js" /></script>
</center>
</body>
</html>