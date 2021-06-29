<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>


<%@ page import ="java.util.*,registration.*, hisglobal.presentation.WebUTIL" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function checkReferDepartment(obj)
{
	if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
		
	}
	else
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
		document.getElementsByName('patRefHospitalDeptOther')[0].value=""
	}
}
var fromDeptCode="";
 var refIndex="";
 
function renewalConfirmation()
{
	var valid=false;
	valid=confirm("DO you want to Renew the Registration ?");
	return valid;
}

function getBillAmountForGrouping(fromDept,index)
{	
	 //  alert("refferringOPDEpisode "+document.getElementsByName("refferringOPDEpisode")[0].value)
	//alert(document.getElementsByName("isRefferInOut")[0].checked)
	//alert("isRefferInOut "+document.getElementsByName("isRefferInOut")[0].value)
	var referInOut=document.getElementsByName("isRefferInOut")[0].checked;
	var toDeptCode=document.getElementsByName("departmentUnitCode")[0].value;
	
	if(fromDept!=="")
	{
	// document.getElementsByName("selectedFromDept")[0].value=fromDept;
	fromDeptCode=fromDept;
	refIndex=index;
	}
	else{}
	var isrefer=document.getElementsByName("isReferred")[0].checked;
	// alert("toDeptCode "+toDeptCode+" isrefer "+isrefer+" fromDeptCode "+fromDeptCode+" referInOut "+referInOut )
	if( ((toDeptCode!="") && (toDeptCode!="-1") ) && (isrefer==true) && (fromDeptCode!="") && (referInOut==true) )
	{
		//alert("inside If ")	
		document.getElementsByName("selectedFromUnit")[0].value=fromDeptCode;
		document.getElementsByName("referingRowIndex")[0].value=refIndex;
		submitForm("GETREGFEE"); 
	//	alert("selectedFromDept"+document.getElementsByName("selectedFromDept")[0].value)
		
	}
	else
	{
	//	alert("inside Else patBillAmountWithoutGrouping "+document.getElementsByName("patBillAmountWithoutGrouping")[0].value)	
		document.getElementsByName("patAmountCollected")[0].value=document.getElementsByName("patBillAmountWithoutGrouping")[0].value;
		
	}
	
	if(document.getElementsByName("departmentUnitCode")[0].value!="-1"){
		document.getElementsByName("departmentCode")[0].value=document.getElementsByName("departmentUnitCode")[0].value.substring(0,3)
	}
	
}



function getRefer()
{  //  alert("index "+index)
// ("sdsdsdsdsdsdsds")
	var isRef=document.getElementsByName("isReferred")[0];
	
	// alert("sdsdsdsdsdsdsds")
	 // alert("isRef.checked "+document.getElementsByName("isReferred")[0].checked)
	var isRefInternalExternal=document.getElementsByName("referInternalExternal")[0].value;
	var refValue=document.getElementsByName("checkref")[0].value;
	var index=document.getElementsByName("referingRowIndex")[0].value;
	if((refValue=="1") || (isRef.checked==true) ){
	// 	alert("zxcxcxcx")
	// 	document.getElementById("addImage").style.display="none";
		document.getElementsByName("isReferred")[0].checked=true;
		document.getElementById("checkReferral").style.display="block";
		document.getElementById("isRefferDiv").style.display="block";
		
		if(isRefInternalExternal=="I")
		{	
			
			document.getElementsByName("isRefferInOut")[0].checked=true;
		 	showInternalForGrouping();
		//  	alert(document.form[0].getElementsByName("isRefferInOut").value)
		 	if(index!="")
		 	{
		 // 		alert("index "+index)
		 		document.getElementsByName("refferringOPDEpisode")[parseInt(index)].checked=true;
		 		fromDeptCode=document.getElementsByName("selectedFromUnit")[0].value;
		 		refIndex=document.getElementsByName("referingRowIndex")[0].value;
		 	}
 		}
 	}         
} 


function checkIsReferred(e){
//if(document.getElementsByName("isPatReferByList")[0].value=)

if (e.checked==true){
	// alert("check");
	//document.getElementById("common").style.display="none";
	document.getElementsByName("isReferred")[0].value="1";
	document.getElementById("checkReferral").style.display="block";
	document.getElementById("isRefferDiv").style.display="block";
	// document.getElementById("addImage").style.display="none";
	getBillAmountForGrouping('','');
// 	alert("isref in reffered "+document.getElementsByName("isReferred")[0].value)
}
else{	
	if(refIndex!="")
	{
	document.getElementsByName("refferringOPDEpisode")[parseInt(refIndex)].checked=false;
	}
// 	alert("isref inelse of reffered "+document.getElementsByName("isReferred")[0].value)
	//document.getElementById("common").style.display="block";
	document.getElementById("checkReferral").style.display="none";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	document.getElementById("internalRefer").style.display="none";
	document.getElementById("isRefferDiv").style.display="none";
	document.getElementsByName("referringInstType")[0].checked=false;
	document.getElementsByName("referringInstType")[1].checked=false;
	document.getElementsByName("isRefferInOut")[0].checked=false;
	document.getElementsByName("isRefferInOut")[1].checked=false;
	// document.getElementById("addImage").style.display="";
	document.getElementsByName("isReferred")[0].value="0";
		fromDeptCode="";
 	refIndex="";	
 	
	getBillAmountForGrouping('','');
	}
}



function showInternalForGrouping(){
	 // alert("showInternal"+document.getElementsByName("isRefferInOut")[0].value);
	
	document.getElementById("internalRefer").style.display="block";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";	
	document.getElementById("commonReferExternal").style.display="none";
	document.getElementById("commonReferExternal_associated").style.display="none";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	
}

function showInternal1(e){
	 // alert("showInternal"+document.getElementsByName("isRefferInOut")[0].value);
	
	document.getElementById("internalRefer").style.display="block";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";	
	document.getElementById("commonReferExternal").style.display="none";
	document.getElementById("commonReferExternal_associated").style.display="none";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	getBillAmountForGrouping('','');
}
function showExternal(e){
	
	//document.getElementById("common").style.display="none";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";
	document.getElementById("internalRefer").style.display="none";
	//document.getElementById("commonReferExternal").style.display="block";
	//document.getElementById("commonReferExternal_associated").style.display="none";
	//document.getElementById("commonReferExternal_other").style.display="none";
	//document.getElementById("associated").style.display="none";

	document.getElementById("externalRefer").style.display="block";
	//document.getElementById("internalRefer").style.display="none";
	document.getElementsByName("referringInstType")[0].checked=true;
	showAssociated(document.getElementsByName("referringInstType")[0]);
	getBillAmountForGrouping('','');
}

function showAssociated(e){

	//document.getElementById("common").style.display="block";
	document.getElementById("commonRefer").style.display="block";
	document.getElementById("commonReferExternal").style.display="block";
	document.getElementById("commonReferExternal_associated").style.display="block";		
	document.getElementById("associated").style.display="block";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("internalRefer").style.display="none";

}

function showOthers(e){

	//document.getElementById("common").style.display="block";
	document.getElementById("commonRefer").style.display="block";
	document.getElementById("commonReferExternal").style.display="block";
	document.getElementById("commonReferExternal_other").style.display="block";
	document.getElementById("commonReferExternal_associated").style.display="none";		
	document.getElementById("associated").style.display="none";
	document.getElementById("internalRefer").style.display="none";
}

function removeField(hiddenRemove){
// 	alert("remove Field"); 
	document.forms[0].removeField.value=hiddenRemove;
// 	alert("feiled value==="+document.forms[0].removeField.value);
	return true;
}

function validateSpecialClinic1()
{
	var len;
	var isValid=true;
	
	count=0;
	if(document.getElementsByName("isPatReferByList")[0].value==<%=RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE%>)
	{
		isValid=true;
	}
	else
	{
	val=document.getElementsByName("departmentUnitCode")[0].value;
// 	alert(val);
	//alert("before for");
				if(val=="-1"){
				alert("Please Select Unit");
				isValid=false;
				return false;
			}
		
		document.getElementsByName("isReferred").disabled="true";
	}
	if(validateAgeForSpecialClinicToVisit('<%=RegistrationConfig.MAX_AGE_TO_REGISTER_IN_CHILD_DEPT%>',
			'<%=RegistrationConfig.CHILD_DEPT_CODE%>',document.getElementsByName("departmentUnitCode")[0])
			<his:referMandatory property="departmentCode" isUnitBased="1" /> 
			<his:referMandatory property="departmentUnitCode"/> ){
    		isValid=true;
   	}
   	else{
   		return false;
   	}
return isValid; 
}


function validateToSave(){
// 	alert("validatetosave");
	var isValid; 
	
	
	if(validateSpecialClinic1()){
// 	alert("inside valdate to save  + true ");
	
	isValid=true;
	}
	else{
	isValid=false;
// 	alert("inside valdate to save + false");
	}
// alert("validatetosave"+isValid);

return isValid;
}
function check(){
//alert("hh"+document.getElementsByName("checkAddStatus")[0].value);
if(document.getElementsByName("checkAddStatus")[0].value=="check")
 		{
 		// alert("call on load");
 			document.getElementsByName("isReferred")[0].disabled ="true";
 		}
}

function callThisOnload(){
if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
if((document.getElementsByName('captureMandatoryField')[0].value=="true"))
		{
			enableBlanket();
		}
	showUnit();
// 	check();
// alert("isrefererd  "+document.getElementsByName("isReferred")[0])
if(document.getElementsByName("isReferred")[0]!=null)
{
	// alert("isReferred ")
	getRefer();
}

	if(document.getElementsByName("errorMessage")[0].value!=""){
		alert(document.getElementsByName("errorMessage")[0].value)
		document.getElementsByName("errorMessage")[0].value=""
	}

  }

function showRefer(){
	// alert("show refer"+document.getElementsByName("checkAddStatus")[0].value);
	document.getElementsByName("checkAddStatus")[0].value="check";
	// alert("show refer"+document.getElementsByName("checkAddStatus")[0].value);
	return true;
	}

function ValidateDepartmentNewDeptVisit() {
        var isValid = true;
 //       alert("pat refer by list>>>>"+document.getElementsByName("isPatReferByList")[0].value);
        if(document.getElementsByName("isPatReferByList")[0].value==<%=RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE%>)
    	{
    		isValid=true;     
//			alert("inside IF") 
		}
		else
		{
        
// alert('inside ValidateDepartmentNewDeptVisit....');
if (document.getElementsByName("isReferred")[0].checked==true)
{	
	document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_TRUE%>;
	if (document.getElementsByName("isRefferInOut")[1].checked==true)
	{	
		document.getElementsByName("isRefferInOut")[1].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>;
		if(document.getElementsByName("patRefDoctor")[0].value=="")
			{
			alert("Enter the Doctor Name");
			document.getElementsByName("patRefDoctor")[0].focus();
			return false;
			}
		if (document.getElementsByName("referringInstType")[1].checked==true)
		{	
			document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_FALSE%>;
			if(document.getElementsByName("patRefHospitalName")[0].value=="")
			{
			alert("Enter the Hospital Name");
			document.getElementsByName("patRefHospitalName")[0].focus();
			return false;
			}
		}	
		else if (document.getElementsByName("referringInstType")[0].checked==true)
			{
			document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_TRUE%>;
		// 	alert('document.getElementsByName("patRefGnctdHospitalCode")[0].options['+document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex+'].value   ='+document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value)
			if(document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value=="-1")
			{
			alert("Select the Hospital Name");
			document.getElementsByName("patRefGnctdHospitalCode")[0].focus();
			return false;
			}
			}
			else
			{
			alert("select referringInstType");
			return false;
			}
	}
	else if (document.getElementsByName("isRefferInOut")[0].checked==true)
		{
		
		var flag=false;
			document.getElementsByName("isRefferInOut")[0].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL%>;
		// 	alert('internal referral');
			var index=0;
			var refferringOPDEpisodeLength=document.getElementsByName("refferringOPDEpisode").length;
	// 	alert('internal referral gfgfgf '+refferringOPDEpisodeLength);
		
			while(index<refferringOPDEpisodeLength)
			{
				if(document.getElementsByName("refferringOPDEpisode")[index].checked==true)
				{
				flag=true;
				break;
				}
				index=index+1;
			}
				if(flag==true)
				{
					return true;
				}
				else
				{
					alert("Please Select Internal Reffered Department");
					return false;
				}
		
		}
		else
		{
			alert("select reffering type");
			return false;
		}
		
		
	}
	else
		document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_FALSE%>;
	} 	  
//   alert(isValid);               
  return isValid;
           
}
function populate(e){ 
document.getElementsByName('crNoToRetrieve')[0].value=e;
submitForm("DGNDETAIL"); 

} 

function showDetail(obj)
{
	var patCrNoWithIndex=obj.value;
	
	document.getElementsByName("isPatReferByList")[0].value="1";
	document.getElementsByName("onlineReferedIndex")[0].value=patCrNoWithIndex.substring(0,patCrNoWithIndex.indexOf('$'));
	document.getElementsByName("patCrNo")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('$')+1);
	submitForm("GETPATDTL"); 
}
function showUnit()
{
	if(document.getElementsByName("isPatReferByList")[0].value=="1")
	{
//		alert("isPatReferByList<>"+document.getElementsByName("isPatReferByList")[0].value);
//		alert("true")
//alert("controlUnit "+document.getElementById("controlUnit"))
		if(document.getElementById("controlUnit")){
			document.getElementById("controlUnit").style.display="";
		}
		if(document.getElementById("collUnit")){
			document.getElementById("collUnit").style.display="none";
		}	
	// 	document.getElementById("addImage").style.display="none";
	}
	else
	{
	// alert("controlUnit "+document.getElementById("controlUnit"))
//		alert("false")
		if(document.getElementById("controlUnit")){
	 		document.getElementById("controlUnit").style.display="none";
	 	}
	 	if(document.getElementById("collUnit")){
	 		document.getElementById("collUnit").style.display="";
	 	}	
	 // 	document.getElementById("addImage").style.display="";
	}
}

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

function enableBlanket()
{
	document.getElementById("blanket").style.display="block";
	document.getElementById("mandaatoryFields").style.display="block";
	
}
function disableBlanket()
{
	document.getElementById("blanket").style.display="none";
	document.getElementById("mandaatoryFields").style.display="none";
	document.getElementsByName('captureMandatoryField')[0].value="false"
}

</script> 

<his:css src="/hisglobal/css/tab.css"/>

<%
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:TitleTag name="Special Clinic New Department Visit">
		
	
	   
	 <b><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
       
		    </font></b>    
		
</his:TitleTag>

<%boolean flagIsStatusNew = false;
			String varStatus = "";
			boolean varIsNewStatus = false;
			String renewalStatus = "";%>
<his:statusNew>
	<%flagIsStatusNew = true;
				varIsNewStatus = true;
				
				varStatus = "New";%>
				
</his:statusNew>
<his:InputCrNoTag name="NewSpecialClinickVisitFB"></his:InputCrNoTag>
<bean:define id="crNo" name="NewSpecialClinickVisitFB" property="patCrNo"
	type="java.lang.String" />


<%if (!crNo.trim().equals("")) {%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />

<%} %>


<his:statusRecordFound>
	<%renewalStatus = "renew";%>
	<his:SubTitleTag name="Renewal of Registration">
		<his:name>
			<bean:message key="renewalOfregistration" />
		</his:name>
	</his:SubTitleTag>

	<his:ContentTag>

		<table width="100%" colspacing="1" colpadding="0">
			<tr>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><font
					color="#FF0000">*</font> <bean:message key="renewRegistration" /></b>
				</font></div>
				</td>
				<td width="20%" class="tdfont"><input type="checkbox" tabindex="1"
					name="renewal"
					onclick="if (renewalConfirmation()) submitForm('RENEWAL')" /></td>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><font
					color="#FF0000">*</font> <bean:message key="amount" /></b> </font>
				</div>
				</td>
				<td width="25%" class="tdfont">
				<div align="left"><html:text name="NewSpecialClinickVisitFB" property="amount"
					maxlength="3" size="4" readonly="true" tabindex="1" /></div>
				</td>
			</tr>
		</table>

	</his:ContentTag>
</his:statusRecordFound>



<his:statusList>
	<logic:notEmpty	name="<%=RegistrationConfig.ARR_SC_EPISODE_REFER_PAT_VO%>">
		<his:SubTitleTag name="Referred Patient List">
		
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="4%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;
						</font>
					</td>
					<td width="16%" align="center" class="tdfonthead"style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="crNo" />
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="name" />
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="referFromDept" />
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="referFromDeptUnit" />
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="referToDeptUnit" />
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="referDate" />
								</font>
							</b>
						</div>
					</td>
					
				</tr>
				<logic:iterate id="ARR_SC_EPISODE_REFER_PAT_VO" type="hisglobal.vo.EpisodeRefDtlVO"
				 				indexId="index" name="<%=RegistrationConfig.ARR_SC_EPISODE_REFER_PAT_VO %>">
				 	<tr>
				 		<td width="4%" class="tdfont" >
				 			<div align="center">
				 				<font size="2">
				 				<logic:equal name="ARR_SC_EPISODE_REFER_PAT_VO" property="deptUnitIsClosed" value="<%=RegistrationConfig.DEPT_UNIT_IS_CLOSED_FALSE %>">
					 				<logic:equal name="ARR_SC_EPISODE_REFER_PAT_VO" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_TRUE%>">
					 				<html:radio name="NewSpecialClinickVisitFB" property="selectedReffereCrNo" value='<%=index.toString()+"$"+ARR_SC_EPISODE_REFER_PAT_VO.getPatCrNo() %>' onclick="showDetail(this)" tabindex="1">
					 				</html:radio>
					 				</logic:equal>
					 				<logic:equal name="ARR_SC_EPISODE_REFER_PAT_VO" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE%>">
					 				<img src='<his:path src="/hisglobal/images/stop.png"/>' title="Unit not on duty">
					 				</logic:equal>
					 			</logic:equal>
					 			<logic:equal name="ARR_SC_EPISODE_REFER_PAT_VO" property="deptUnitIsClosed" value="<%=RegistrationConfig.DEPT_UNIT_IS_CLOSED_TRUE %>">
					 			
					 			</logic:equal>
					 			</font>
				 			</div>
				 		</td>
				 		<td width="16%" class="tdfont">
							<div align="center">
								<font size="2">
									 <bean:write name="ARR_SC_EPISODE_REFER_PAT_VO" property="patCrNo" />
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2">
									 <bean:write name="ARR_SC_EPISODE_REFER_PAT_VO" property="patName" />
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2">
									 <bean:write name="ARR_SC_EPISODE_REFER_PAT_VO" property="fromDepartment" />
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2">
									 <bean:write name="ARR_SC_EPISODE_REFER_PAT_VO" property="fromDepartmentUnit" />
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2">
									 <bean:write name="ARR_SC_EPISODE_REFER_PAT_VO" property="toDepartmentUnit" />
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2">
									 <bean:write name="ARR_SC_EPISODE_REFER_PAT_VO" property="reffDateTime" />
								</font>
							</div>
						</td>
						
				 	</tr>				
				
				</logic:iterate>
			</table>
		</his:ContentTag>
	</logic:notEmpty>
	<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Show 
				</font>
				<img class="button" src='<his:path src="/hisglobal/images/arrow_down.gif"/>' onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends(); " tabindex="1">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Hide
				</font>
				<img class="button" src='<his:path src="/hisglobal/images/arrow_up.gif"/>' onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone(); " tabindex="1">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	<div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="5%">
					<input type="radio">
				</td>
				<td width="95%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Visit stamp allowed
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="5%">
					<img src='<his:path src="/hisglobal/images/stop.png"/>'>
				</td>
				<td width="95%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Unit not working today
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td class="tdfont" width="5%">
				</td>
				<td width="95%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Unit Closed
					</div>
					</font>
				</td>				
			</tr>
		</table>
	</his:ContentTag>
	</div>
	
	
</his:statusList>


<his:statusTransactionInProcess>
<input type='hidden' name='crNoToRetrieve'/>
<his:SubTitleTag name="Special Cilinic Visit Stamp">
	<his:name>
		<bean:message key="lastVisitdate"/>
	</his:name>
</his:SubTitleTag>

	<bean:define name="<%=Config.CLIENT_NAME %>" id="client"></bean:define>
	<bean:define id="confFlagId" name="<%=Config.FILE_NO_GENERATION_FLAG_NAME %>" ></bean:define>
	<his:ContentTag>

					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<%int i = 0;%>
						<tr>
						
						<td width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b>  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="specialClinic" /> </font> </b></div></td>
					
						<logic:equal name="confFlagId" value="<%=Config.FILENO_GENRATION_TYPE_MANNUAL%>">
							<td  width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
								 			
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="fileNo" /> </font> </b></div></td>
							
						</logic:equal>
							
							
							<td  width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="amountCollected" /> </font> </b></div></td>
								
			<%--				<td width="10%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
								<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> ADD
								 </font> </b></div></td>
								--%>
						</tr>
						</table>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
						  <td width="30%" class="tdfont">
						  	<div id="collUnit" style="display: none;" align="center">
	       						<html:select name="NewSpecialClinickVisitFB" property="departmentUnitCode" onchange="getBillAmountForGrouping('','')"  tabindex="1" >
	       							<html:option value="-1">Select Value</html:option>
		   							<html:options collection = "unitsColl" property  = "value" labelProperty = "label"/>
		   						</html:select>
		   					</div>
		   					<div id="controlUnit" style="display: none;" align="center">
		   						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			   						<bean:write name="NewSpecialClinickVisitFB" property="toDepartmentUnit"/>
			   					</font>	
		   					</div>
		   						<html:hidden name="NewSpecialClinickVisitFB" property="toDepartmentUnitCode"/>
		   				</td> 	
		   				<logic:equal name="confFlagId" value="<%=Config.FILE_NO_GENRATION_MANNUAL_TRUE %>">
		   				<td  width="30%" class="tdfont" >
						<div align="center">
						<html:text name="NewSpecialClinickVisitFB"  readonly="false" styleClass="textbox"  maxlength ="30" property="fileNo"></html:text>
						</div>
						
						</td> 
					</logic:equal>
					<td width="30%" class="tdfont">
						<div align="center">
						<html:text name="NewSpecialClinickVisitFB"  readonly="true" styleClass="textbox"  maxlength ="30" property="patAmountCollected"></html:text>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%--			<img class="button" id="addImage"
						src='<his:path src="/hisglobal/images/icn-add.png"/>' style="cursor:pointer display: none;"
						onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSpecialClinic1(),'ADD');" 
						tabindex="1"  onclick="submitFormOnValidate(validateSpecialClinic1() && showRefer(),'ADD');"> --%>
						<html:hidden name="NewSpecialClinickVisitFB" property="checkAddStatus" />		
						</div>
						</td> 
	<%--					<td width="10%" class="tdfont">
			<div align="center" id="im">
						<img class="button" id="addImage"
		src='<his:path src="/hisglobal/images/icn-add.png"/>' style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSpecialClinic1(),'ADD');" 
		tabindex="1"  onclick="submitFormOnValidate(validateSpecialClinic1() && showRefer(),'ADD');">
		<html:hidden name="NewSpecialClinickVisitFB" property="checkAddStatus" />
		</div>
						</td> --%>
						</tr>
						</table>
						
						
						
		</his:ContentTag>
		
		
					<%Boolean flag=(Boolean)session.getAttribute("flag"); 
						if(flag.compareTo(new Boolean("true"))==0){					
						%>
			
			
			
			
	
		<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				
				<% Map outermap=(Map)session.getAttribute("map");
					Map innerMap=(Map)outermap.get("map0");
					System.out.println("innermap======"+innerMap);
				%>
				
				
				<logic:iterate id="outerMap" 
							name="map" indexId="j" type="java.util.Map.Entry"  >
				 <%String ss= outerMap.getClass().getName();
				 System.out.println("vfgffgfg"+ss);
				// System.out.println("vfgffgfg"+outerMap.getValue());
				 %>							
				 
			  
							
			<bean:define  name="outerMap" property="value"  id="innermap" type="java.util.Map"/> 
		<%--	<logic:iterate id="innMap" name="innermap" indexId="k">				
				
		<bean:define name="innMap" property="value" id="textValue" type="java.lang.String"/>
				
				<td width="30%" class="tdfont">
						<div align="center">
						<html:text name="innMap"  readonly="true" styleClass="textbox"  maxlength ="30" property="" value="<%=textValue%>"></html:text>
								
						</div>
						</td> 
				
				</logic:iterate>
				--%>
				
					<tr> 
             	 <td width="60%" class="tdfonthead" ><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
    	<%=(String)innermap.get("deptName")%>
		</div></td>
		<logic:equal name="confFlagId" value="<%=Config.FILE_NO_GENRATION_MANNUAL_TRUE%>">
		     <td width="30%"  class="tdfonthead">
			 <div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		        <bean:message key="fileNo"/>
		        </font>
		     </div>
	       </td>
	
		<td class="tdfont">
		<html:text  name="NewSpecialClinickVisitFB" property="fileNo" readonly="true" tabindex="1"  maxlength="30" value='<%=(String)innermap.get("fileNo")%>'/>
        </td>    
        </logic:equal>
        	
	   <td width="10%" class="tdfont"> <img class="button"
		src='<his:path src="/hisglobal/images/icn-min.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSpecialClinic(),'ADD');"
		tabindex="1" onclick="submitFormOnValidate(removeField('<bean:write name="outerMap" property="key"/>'),'REMOVE')"></td>
		   </tr>
				</logic:iterate>
				</table>
				</his:ContentTag>
					<html:hidden name="NewSpecialClinickVisitFB" property="removeField" value=""/>
				
				<%} %> 
		
		
<logic:notEqual name="NewSpecialClinickVisitFB" property="isPatReferByList" value="1">				
<his:SubTitleTag name="Reffer Detail">
 <table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		<td width="95%" >
			<div align="right">
			<b><bean:message key="isreferred"/></b>
			</div>
		</td>
		<td width="5%">
			<div align="left" >
			<html:checkbox name="NewSpecialClinickVisitFB" property="isReferred" tabindex="1" onclick="checkIsReferred(this)" onkeypress="if(event.keyCode==13) checkIsReferred(this);"/>
			</div>
		</td>
	</tr>
</table>		
</his:SubTitleTag>
<div id="isRefferDiv" style="display:none">			 
<his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		<td width="40%">
		<div id="checkReferral" style="display:none">
			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="referInternal" />
			</font>
			<html:radio name="NewSpecialClinickVisitFB" property="isRefferInOut" tabindex="1" value="I" onclick="showInternal1(this)" />
		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="referExternal" />
			</font>
			<html:radio name="NewSpecialClinickVisitFB" property="isRefferInOut" tabindex="1" value="E" onclick="showExternal(this)" />
		</div>
		</td>
		<td width="40%">
			<div id="externalRefer" style="display:none">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="gnctd" />
				</font>
				<html:radio name="NewSpecialClinickVisitFB" property="referringInstType" tabindex="1" value="G" onclick="showAssociated(this)" />
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="other" />
				</font>
				<html:radio name="NewSpecialClinickVisitFB" property="referringInstType" value="O" tabindex="1" onclick="showOthers(this)" />
				<html:hidden name="NewSpecialClinickVisitFB" property="isAssociated" />
				</div>
		</td>
		</tr>
	</table>
	</his:ContentTag>
	</div>
	<bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>
		<div id="commonRefer" style="display:none"> 
	<his:ContentTag>
	

		<table width="100%" cellspacing="1" cellpadding="1">
			 
			    <tr>
			    <td width="25%" class="tdfonthead" >
			    <div align="right">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referredBy" /></font></div>
				</td>
				<td width="25%" class="tdfont">
				<html:text name="NewSpecialClinickVisitFB" styleClass="textbox" maxlength="100" property="patRefDoctor" onblur="isAlpha(this,'Referred By Doctor')" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
				</td>
				<td width="25%"  class="tdfonthead">
				<div id="commonReferInternal" style="display:none">&nbsp; &nbsp; &nbsp; &nbsp;</div>
				
				<div id="commonReferExternal" style="display:none">
				<div align="right"><font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="hospital"/> <bean:message key="name"/></div>
				</div>
				</td>
				
				<td width="25%"  class="tdfont">
				<div id="commonReferInternal_1" style="display:none">&nbsp; &nbsp; &nbsp; &nbsp;</div>
				
				<div id="commonReferExternal_associated" style="display:none">
				<html:select name="NewSpecialClinickVisitFB" tabindex="1" property="patRefGnctdHospitalCode" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>" property = "value" labelProperty = "label"/>
			    </html:select>
			    </div>
			    
			    <div id="commonReferExternal_other" style="display:none">
				<html:text  name="NewSpecialClinickVisitFB" tabindex="1" property="patRefHospitalName" onkeypress="return validateAlphabetsOnly(event,this)" maxlength="100" styleClass="textbox" size="20"/>
				</div>
				</td>
				</tr>
			
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referFromDept" /> </font></div>
					</td>
					<td width="25%" class="tdfont">
						<html:select name="NewSpecialClinickVisitFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
						<html:option value="">Select Value</html:option>
						<html:option value="0">Other</html:option>
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL%>" property="value" labelProperty="label" /> 
						</html:select> 
					</td>
					<td width="25%" class="tdfonthead" >
    			 	<div  align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="other" />
					<bean:message key="department" /> </font>
					</div>
					
					</td>
					<td width="25%" class="tdfont">
					<html:text name="NewSpecialClinickVisitFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true" onkeypress="return validateAlphabetsOnly(event,this)"/>
					</td>
			</tr>
			
			
		</table>
		
		
		
		<div id="associated" style="display:none">		
		<table width="100%" cellspacing="1" cellpadding="1">
			 <tr>
				<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referring" /> <bean:message key="crNo" />
				</font></div>
				</td>
				<td width="25%" %  class="tdfont">
				<html:text name="NewSpecialClinickVisitFB" tabindex="1" property="patRefGnctdHospitalCrno" maxlength="12" tabindex="1" onkeydown="setPrevValue(this, event);" onkeyup="moveToRight(this, event);" onkeypress="return validateNumeric(event)" styleClass="textbox" />
				</td>
			    <td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referring" /> <bean:message key="unit" />
				</font></div>
				</td>
				<td width="25%"  class="tdfont">
				<html:text name="NewSpecialClinickVisitFB" property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15" styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)" />
				</td>
			 </tr>
		</table>
		</div> 
 
 </his:ContentTag>
 </div>


<div id="internalRefer" style="display:none">
			<!--- ReferInternal -------  Details of all open episodes-->

		<%-- <logic:empty name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>">

			<%System.out.println("empty ARR_EPISODE_REFER_VO");

							%> --%>

			
				

				<his:ContentTag>
				<logic:notEmpty
				name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>">
				<bean:define id="OPD_OPEN_EPISODES"
					name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>"
					type="hisglobal.vo.EpisodeRefDtlVO[]" />
				

					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<%int i = 0;%>
						<tr>
							<td width="5%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">&nbsp;</font></td>

							<td width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="referFromDept" /> </font> </b></div></td>



							<td width="45%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="referFromDeptUnit" /> </font> </b></div></td>



						</tr>

						<logic:iterate id="ARR_OPD_OPEN_EPISODE_VO"	name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>" indexId="index" type="hisglobal.vo.EpisodeRefDtlVO">
							<%
						 String epCode=ARR_OPD_OPEN_EPISODE_VO.getEpisodeCode();
						 String frmDep=ARR_OPD_OPEN_EPISODE_VO.getFromDepartment();
						 String frmDepUnit=ARR_OPD_OPEN_EPISODE_VO.getFromDepartmentUnit();
						 String frmDepCode=ARR_OPD_OPEN_EPISODE_VO.getFromDepartmentCode();
						 String frmDepUnitCode=ARR_OPD_OPEN_EPISODE_VO.getFromDepartmentUnitCode();
						%>
						<%-- 
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="episodeCode" id ="epCode"/>
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartment" id ="frmDep"/>
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentUnit" id ="frmDepUnit"/>
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentCode" id ="frmDepCode"/>
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentUnitCode" id ="frmDepUnitCode"/>
						--%>
						
						<%String hh=(String)epCode; %>
						<%String strFrmDep=(String)frmDep; %>
						<%String strFrmDepUnit=(String)frmDepUnit; %>
						<%String strFrmDepCode=(String)frmDepCode; %>
						<%String strFrmDepUnitCode=(String)frmDepUnitCode; %>
							<tr>
								<td width="5%" class="tdfont">
								<div align="center"><input type="radio"
									name="refferringOPDEpisode" tabindex="1"
										onclick="getBillAmountForGrouping('<%=strFrmDepUnitCode%>','<%=String.valueOf(index.intValue())%>');"
									value="<%=hh %>" /></div>
								</td>
								<html:hidden
									name="NewSpecialClinickVisitFB" property="episodeCode"
									value="<%=hh%>"/>
								
								<td width="30%" class="tdfont">
								<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
									property="fromDepartment" />
									<html:hidden
									name="NewSpecialClinickVisitFB" property="fromDepartment"
									value="<%=strFrmDepCode%>"/>
									</div>
								</td>


								<td width="45%" class="tdfont">
								<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
									property="fromDepartmentUnit" />
									<html:hidden
									name="NewSpecialClinickVisitFB" property="fromDepartment"
									value="<%=strFrmDepUnitCode%>"/>
									</div>

								</td>

							</tr>
						</logic:iterate>

					</table>
					
			</logic:notEmpty>
				</his:ContentTag>
				
			</div>	
							<%
		varStatus="InProcess";
	%>
	</logic:notEqual>
</his:statusTransactionInProcess>

	
	<his:statusInProcessWithJsp>
		
	</his:statusInProcessWithJsp>
	<!-- ...............Code for Button Tool Bar.......... -->
	
	
<his:ButtonToolBarTag>
	<%
	hisglobal.presentation.Status objStatus= (hisglobal.presentation.Status) request.getAttribute(Config.STATUS_OBJECT);
	if(objStatus!= null){
		HashSet statuslist= objStatus.getStatusList();
       // System.out.println(statuslist.contains(objStatus.TRANSINPROCESS));	
		if (statuslist.contains(objStatus.TRANSINPROCESS)&& (!statuslist.contains(objStatus.ERROR_DA)) )
		{%>

	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer 
		onkeypress="if(event.keyCode==13) submitFormOnValidate(<%=(Boolean)session.getAttribute("flag")%> || validateToSave() && ValidateDepartmentNewDeptVisit(),'CHECKMANDATORY');"
		tabindex="1" onclick="submitFormOnValidate(<%=(Boolean)session.getAttribute("flag")%> || validateToSave() && ValidateDepartmentNewDeptVisit(),'CHECKMANDATORY');">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		tabindex="1" style="cursor:pointer;"
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%} else{ %>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"
		style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%} }%>
</his:ButtonToolBarTag>
<!-- .......End........Code for Button Tool Bar.......... -->

<html:hidden name="NewSpecialClinickVisitFB" property="isPatReferByList"/>	
<html:hidden name="NewSpecialClinickVisitFB" property="captureMandatoryField"/>

<div id="blanket" style="height: 580;width: 1024;display: none;"></div>

	<div id="mandaatoryFields" style="display: none; height: 110px;width: 310px;position:absolute; top: 20%;left: 30%;z-index: 9100;background-color: #FFEBD5;">
				<table width="100%">
					<tr>
						<td width="80%" class="tdfonthead" align="center">
							<font color="#FF0000" size="2"	face="Verdana, Arial, Helvetica, sans-serif">
							Enter Mandatory Fields	
							</font>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<img class="button" tabindex="1" src="/HISClinical/hisglobal/images/button_cancel.png" style="cursor: pointer; " onkeypress="disableBlanket()" onclick="disableBlanket()">		
							</div> 
						</td>
					</tr>
					
				</table>
				<table width="100%">
					
					<%if(request.getAttribute("HTML_CODE")!=null){%>
					<%=request.getAttribute("HTML_CODE") %>
					<%} %>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<img class="button" tabindex="1" src="/HIS/hisglobal/images/GoNew.png" style="cursor: pointer; position: absolute;" onkeypress="if(validateMandatory()) submitForm('SAVE')" onclick="if(validateMandatory()) submitForm('SAVE')">		
							</div> 
						</td>
					</tr>
					
				</table>
				<br>
				
			</div>

<his:status />

<input type="hidden" name="hmode" value="unspecified" />
<input type="hidden" name="departmentCode"/>
<html:hidden name="NewSpecialClinickVisitFB" property="patCrNo"/>	
<html:hidden name="NewSpecialClinickVisitFB" property="selectedFromUnit" />
<html:hidden name="NewSpecialClinickVisitFB" property="patBillAmountWithoutGrouping" />
<html:hidden name="NewSpecialClinickVisitFB" property="referInternalExternal" />
<html:hidden name="NewSpecialClinickVisitFB" property="referingRowIndex" />
<html:hidden name="NewSpecialClinickVisitFB" property="checkref" />
<html:hidden name="NewSpecialClinickVisitFB" property="onlineReferedIndex"/>
<html:hidden name="NewSpecialClinickVisitFB" property="patAge"/>
<html:hidden name="NewSpecialClinickVisitFB" property="errorMessage"/>