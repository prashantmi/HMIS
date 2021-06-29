<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="registration.config.RegistrationConfig"%>
<%@page import="vo.registration.PatientVO"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css">

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/basic.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>

<script type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>

 <script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<%--<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script> --%>
<script>

/* $(document).ready(function(){
	//By Mukund for patient list acccording to selected department/unit
	function setPatientList(obj)
	{
		var i;
		var allrows = document.getElementsByName("duplicateSearchRecord");
		
		for(i=0;i<allrows.length;i++)
			allrows[i].style.display="none";
		
		for(i = 0; i < allrows.length; i++)
		{	var str = obj.value.toString();
			var depCode = allrows[i].id.split("@")[0];

			if(str==depCode)//this check is required to show department specific list only 
				allrows[i].style.display="";

			if(str==0)//this check is required to show all patient list when all unit is selected
				allrows[i].style.display="";

		}
		document.getElementsByName("searchId")[0].value="1";
		document.getElementsByName("searchValue")[0].value="";
	}
	//to search the patient list for desired values
	function searchValueValidation()
	{
		var schVal= document.getElementsByName("searchValue")[0].value;
		schVal=schVal.trim();
		if(schVal=="")
		{	
				alert("Please Enter Search Value");
				document.getElementsByName("searchValue")[0].focus();
				return false;
		}
		var searchType= document.getElementsByName("searchId")[0].value;
		if(searchType=="1")//Appointment no
		{
			var n = schVal.search(/[a-z,.>?:;!@#$%^&*()_]/i);
			if(n!="-1")
			{	alert("Please Enter Number Only");
				return false;}
			else
				searchPatientList(schVal, "1");
		}
		if(searchType=="2")//Name
		{
			var n = schVal.search(/[0-9,.>?:;!@#$%^&*()_]/i);
			if(n!="-1")
			{	alert("Please Enter alphabets Only");
				return false;}
			else
				searchPatientList(schVal, "2");
		}
		if(searchType=="3")//contact no
		{
			var n = schVal.search(/[a-z,.>?:;!@#$%^&*()_]/i);
			if(n!="-1")
			{	alert("Please Enter Number Only");
				return false;}
			else
				searchPatientList(schVal, "3");		
		}
		if(searchType=="4")//crno
		{
			var n = schVal.search(/[a-z,.>?:;!@#$%^&*()_]/i);
			if(n!="-1")
			{	alert("Please Enter Number Only");
				return false;}
			else
				searchPatientList(schVal, "4");		
		}
	}
	function searchPatientList(schVal, searchType)
	{
		var allrows = document.getElementsByName("allPatientList");

		for(i=0;i<allrows.length;i++)
			allrows[i].style.display="none";
			
		for(i = 0; i < allrows.length; i++)
		{
			var str = allrows[i].id.split("@");
			if(searchType=="1")//appointment no
			{
				var aptno=str[1];
				if(aptno==schVal)
					allrows[i].style.display="";
			}
			else if(searchType=="2")//first name
			{
				var name1 = schVal.toString();
				name1 = name1.toUpperCase();
				
				var nameArr=str[2].split("&");
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
			else if(searchType=="3")//contact no
			{
				var cntno=str[3]
				if(cntno==schVal)
					allrows[i].style.display="";
			}
			else if(searchType=="4")//contact no
			{
				var crno=str[4]
				if(crno==schVal)
					allrows[i].style.display="";
			}
			
		}
		document.getElementsByName('deptToSearchFrom')[0].value=0;//to reset the value of Department/unit back to All Units
	}
});	 */
$(document).ready(function(){
	var allRadioObj = document.getElementsByName('radioForDuplicate');
	for(i = 0; i<allRadioObj.length; i++)
	{
		var matCri = allRadioObj[i].value.split("&&")[1];
		if(matCri == 'A1' || matCri == 'B2')
		{
			parent.document.getElementsByName("radioForDuplicate_parent")[0].value = allRadioObj[i].value;
		}
	}	
});
function checkUncheckRadioForDupicate(obj){
	//alert(obj.value);
	var patCrNo = obj.value.split("&&")[0];
	var matCri = obj.value.split("&&")[1];
	if(matCri!="0" && matCri!="A1" && matCri!="B2")
	{
		$('[name="parentCrNo"]').val(patCrNo);
		parent.document.getElementsByName("selectedCRToVisit")[0].value=$('[name="parentCrNo"]').val();
	}
	else
	{
		parent.document.getElementsByName("selectedCRToVisit")[0].value =   obj.value;
	}	
}
</script>
</head>
<body>

<div id="divDuplicateCRNOs">
			<div class="div-table-listing rounded width100 height100">
				<div class="div-table-row listHeader">
					<div class="div-table-col width3" align="center">
					<s:text name="select" />
					</div>
					<div class="div-table-col width8" align="center">
					<s:text name="crNO" />
					</div>
					<div class="div-table-col width17" align="center">
					<s:text name="global.patient" />&nbsp;<s:text name="global.name" />	
					</div>
					<div class="div-table-col width8" align="center">
					<s:text name="gender" /><s:text name="slash" /><s:text name="age" />						
					</div>
					<div class="div-table-col width18" align="center">
					<s:text name="guardianName" /><%-- <s:text name="fathersName" />	 --%>				
					</div>
					<div class="div-table-col width20" align="center">
					<s:text name="address" />					
					</div>
					<div class="div-table-col width18" align="center">
					<s:text name="dateOfReg" />						
					</div>
					<div class="div-table-col width8" align="center">
					<s:text name="matchCriteria" />						
					</div>
				</div>				
				<s:set var="isA1B2Present" value='false'/>
				<s:iterator status="status" value="%{#session.allPatientVOList}">
				<div class="div-table-row listData">
					<div class="div-table-col width3" align="center">
						<s:set var="matchCriteriaJSP" value="matchCriteria"/>
						<s:if test="%{#matchCriteriaJSP=='A1' || #matchCriteriaJSP=='B2'}">
							<input type="radio" name="radioForDuplicate" value='<s:property value="%{patCrNo}"/>&&<s:property value="%{#matchCriteriaJSP}"/>'  onclick='checkUncheckRadioForDupicate(this);' />
							<s:set var="isA1B2Present" value='true'/>
						</s:if>
						<s:elseif test="%{#matchCriteriaJSP=='C3' || #matchCriteriaJSP=='D4'}">
								<s:if test="%{#isA1B2Present}">
									<input type="radio" name="radioForDuplicate" disabled value='<s:property value="%{patCrNo}"/>&&<s:property value="%{#matchCriteriaJSP}"/>' onclick='checkUncheckRadioForDupicate(this);' />
								</s:if>
								<s:else>
									<input type="radio" name="radioForDuplicate" value='<s:property value="%{patCrNo}"/>&&<s:property value="%{#matchCriteriaJSP}"/>' onclick='checkUncheckRadioForDupicate(this);' />
								</s:else>								
						</s:elseif>
						<s:else>
							<input type="radio" name="radioForDuplicate" value='0&&0' onclick='checkUncheckRadioForDupicate(this);' />
						</s:else>
					</div>
					<div class="div-table-col width8" align="center">
						<s:property value="%{patCrNo}"/>
					</div>
					<div class="div-table-col width17" align="center">
						<s:property value="%{patFirstName}"/>&nbsp;<s:property value="%{patMiddleName}"/>&nbsp;<s:property value="%{patLastName}"/>
					</div>
					<div class="div-table-col width8" align="center">
						<s:property value="%{patGender}"/><s:text name="slash" /><s:property value="%{patAge}"/>
					</div>
					<div class="div-table-col width18" align="center">
					<s:if test="%{#patGuardianName!=''}">
						<s:property value="%{patGuardianName}"/>
					</s:if>
					<s:elseif test="%{#patHusbandName!=''}">
						<s:property value="%{patHusbandName}"/>
					</s:elseif>
					<s:else>NA</s:else>
					</div>
					<div class="div-table-col width20" align="center">
						<s:property value="%{patAddressLine1}"/>&nbsp;<s:property value="%{patAddCity}"/>&nbsp;<s:property value="%{patAddDistrict}"/>&nbsp;<s:property value="%{patAddState}"/>
					</div>
					<div class="div-table-col width18" align="center">
						<s:property value="%{registerDate}"/>
					</div>
					<div class="div-table-col width8" align="center">
						<s:property value="%{matchCriteriaStr}"/>
					</div>
				</div>
				</s:iterator>
				<%-- <%String patPrimaryCatCode=(String)request.getParameter("patPrimaryCatCode");//session.getAttribute("patPrimaryCatCode");
	  			  	%>
				<input type ="hidden" name="newSplRegFB" value="<%=patPrimaryCatCode %>" >  --%>
			</div>
			
		</div>
		<input type="hidden" name='matchCriteriaJSP' />
		<input type="hidden" name='parentCrNo' />
		<input type="hidden" name='slNoHashUhidForDupCont' />
<%-- <script type="text/javascript" src="./../../registration/transactions/js/opdNewPatientRegistration.js" /></script> --%>
		
</body>		
</html>