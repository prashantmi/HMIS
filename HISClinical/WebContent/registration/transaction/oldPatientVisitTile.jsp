<%try{ %>
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
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
		}
}
var countDeptVisit=0;
function checkForRefer(obj)
{
//alert("size "+obj.checked)
var isRef=document.getElementsByName("isReferred")[0];
	if(obj.checked==true)
	{
		countDeptVisit=countDeptVisit+1;
	}
	else
	{
		countDeptVisit=countDeptVisit-1;
	}
	if( (countDeptVisit>1) && (isRef.checked==true))
	{
		
		isRef.checked=false;
		alert("Please Select Only One Department For Visit Stamp With Refer ")
		 checkIsReferred(isRef);
	}
	
}

function getEpisodeRefDtl(mode,check){
// alert("check value of rfere"+document.getElementsByName("isReferred")[0].value);
if(check.checked==true){
	//	alert("mode "+mode);
		document.getElementsByName("isReferred")[0].value="1";
		submitForm(mode);	
	}
	else{
		//alert("in else");
		document.getElementsByName("isReferred")[0].value="0";
		submitForm(mode);
	}	
}
function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

//Submit Tile by setting hmode
function submitTile(mode){
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	//alert(elem.value); 
	document.forms[0].submit();
}

function checkIsReferred(e){
if (e.checked==true){
	
	//document.getElementById("common").style.display="none";
	document.getElementById("checkReferral").style.display="block";
	}
else{	
	//document.getElementById("common").style.display="block";
	document.getElementById("checkReferral").style.display="none";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	document.getElementById("internalRefer").style.display="none";
	document.getElementsByName("referringInstType")[0].checked=false;
	document.getElementsByName("referringInstType")[1].checked=false;
	document.getElementsByName("isRefferInOut")[0].checked=false;
	document.getElementsByName("isRefferInOut")[1].checked=false;
	}	
}

function showInternal(e){
	
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
	
	 showAssociated("");
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




function  validateOldDepartment()
{
	
	var len;
	var isValid = true;
	//int count=0;
	count=0;
	//alert("before assignment");
	len=document.getElementsByName("departmentsToVisitStamp").length;
	//alert("before for");
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("departmentsToVisitStamp")[i].checked){
				count++;
				}
				}
	
	if(count==0){
		isValid = false;
		alert("Please Select Department");
				}
	else
	isValid = true;

 //alert(isValid);
return isValid;
}


function callThisOnload(){
	
	//alert("onload")
	focusCrNo();
	
	if(document.getElementsByName("isReferred")[0]){
		var refDtl=document.getElementsByName("isReferred")[0];
		var refDtlInOut=document.getElementsByName("isRefferInOut")[0];
		if(refDtl.checked==true){
			if(refDtlInOut.checked==true)
			{
				showInternal('');
			}
		}
	}
	var valid;
	
	if(document.getElementsByName('saveSuccessful')[0].value=='true')
	{
		/*valid=confirm('Do Not Print Card?');
		if(!valid)
		{
		submitForm("PRINT");
		}
		else	
		{
		<%//session.removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);%>
		}
		*/
		showMessage('Do You want to Print Card?')
		enableBlanket();
	}
	
}


function populate(e){ 
document.getElementsByName('crNoToRetrieve')[0].value=e;
submitForm("DGNDETAIL"); 
} 

function validateOnRequest(e){
	var valid=false;
	document.getElementsByName('onRequestVisit')[0].value=1;
// alert('inside validateOnRequest()'+e);
//document.getElementsByName("departmentsToVisitStamp")[0].value=e;
//alert('departmentsToVisitStamp...'+document.getElementsByName('departmentsToVisitStamp')[0].value);
if(countDeptVisit==0)
{
	document.getElementsByName('hiddenEpisode')[0].value=e;
	// alert('hiddenEpisode....'+document.getElementsByName('hiddenEpisode')[0].value);
	document.getElementsByName('hcode')[0].value=e;
	ValidateDepartmentNewDeptVisit();
	// alert('hcode....'+document.getElementsByName('hcode')[0].value);
	
	
	
	valid=true;
}
else
{	
		var isRef=document.getElementsByName("isReferred")[0];
		if(isRef.checked==true)
		{
		isRef.checked=false;
		alert("Please Select Only One Department For Visit Stamp With Refer")
		checkIsReferred(isRef);
		}
		else
		{
			valid=true;
		}
	
}
return valid;
}
 function ValidateDepartmentNewDeptVisit() {
         var isValid = true;
      //   alert("document.getElementsByName('isRefferInOut')[0].checked "+document.getElementsByName("isRefferInOut")[0].checked)
// alert('inside ValidateDepartmentNewDeptVisit....');

	if(document.getElementsByName("isPatReferByList")[0].value==<%=RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE%>)
	{
			return true;
			//alert("is valid true>>>>"+isValid);
	}
	else
	{
	
if (document.getElementsByName("isReferred")[0].checked==true)
{	
	// alert("referringInstType "+document.getElementsByName("referringInstType")[1].checked)
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
			alert("Please Enter the Institute Name");
			document.getElementsByName("patRefHospitalName")[0].focus();
			return false;
			}
		}	
		else if (document.getElementsByName("referringInstType")[0].checked==true)
			{
			document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_TRUE%>;
			//alert('document.getElementsByName("patRefGnctdHospitalCode")[0].options['+document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex+'].value   ='+document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value)
			if(document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value=="-1")
			{
			alert("Please Select the Institute Name");
			document.getElementsByName("patRefGnctdHospitalCode")[0].focus();
			return false;
			}
			}
			else
			{
			alert("Select Referring Institute Type");
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
			alert("Select Reffering Type");
			return false;
		}
		
		
	}
	else
		document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_FALSE%>;
		
	
	  
      
                
  return isValid;
        

       } }
       
       
function addAmount(obj,amount){


if(obj.checked==true)
{
document.getElementsByName('amount')[0].value=parseInt(document.getElementsByName('amount')[0].value)+parseInt(amount);
}
else
document.getElementsByName('amount')[0].value=parseInt(document.getElementsByName('amount')[0].value)-parseInt(amount);
}


function renewalConfirmation()
{
	var valid=false;
	valid=confirm("DO you want to Renew the Registration ?");
	return valid;
}

function renewalValidation()
{
 	var len;
	var isValid = true;
	
	count=0;
	
	len=document.getElementsByName("departmentsToRenew").length;
	
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("departmentsToRenew")[i].checked){
				count++;
				}
				}
	
	if(count==0){
		isValid = false;
		alert("Select a  department");
				}
	else
	{
	isValid = true;
	alert('Please Collect Rs '+  document.getElementsByName('amount')[0].value)
	}

return isValid;
}

function showDetail(obj)
{
	var patCrNoWithIndex=obj.value;
	document.getElementsByName("isPatReferByList")[0].value="1";
	document.getElementsByName("indexID")[0].value=patCrNoWithIndex.substring(0,patCrNoWithIndex.indexOf('$'));
	document.getElementsByName("patCrNo")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('$')+1);
	
	submitForm("GETPATDTL"); 
}
function showMessage(message){
	document.getElementById("confirmMsg").style.display="block"
	document.getElementById("alertMessage1").innerHTML=message;
	document.getElementsByName("noButton")[0].focus();
}

function closeAlert(flag){
	document.getElementById("confirmMsg").style.display="none"
	disableBlanket();
	if(flag){
		submitForm("PRINT");
	}else{
		document.getElementsByName('saveSuccessful')[0].value='false'
	}
}

function enableBlanket()
{
	document.getElementById("blanket").style.display="block";
}
function disableBlanket()
{
	document.getElementById("blanket").style.display="none";
}
</script>


<%@ page import ="java.util.*,registration.*,hisglobal.vo.*, registration.controller.util.OldPatientVisitUTIL" %>

<%System.out.println("inside OldpatientVisitTile.jsp"); %>
<% 
			String divisrefdisplay="\"\"";
		 String externalReferdisplay="none";
		 String commonReferdisplay="none";
         String associateddisplay="none";
         String internalReferdisplay="none";
         String referringInstTypedisplay="none";
		%>
<%
String st= (String)session.getAttribute("SYSDATE");
System.out.println("date in jsp"+st);

String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");

%>



<%
String varStatus="";
String renewalStatus="";
%>
<his:statusNew>
<%varStatus="New";%>	

</his:statusNew>


<his:TitleTag name="Old Patient Visit">
	   
	   <!--<b>
	   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	   <a onclick="openPopup('registration/searchByNamePopup.cnt',event)" style=cursor:pointer>
	   <bean:message key="search"/>
	   <bean:message key="by"/>
	   <bean:message key="name"/>
	   </a>
	   </font>
	   </b>
	   -->		
	   <b>
	   <font size="2" face="Verdana, Arial, Helvetica, sans-serif">
       
	   </font>
	   </b>    

</his:TitleTag>

<his:InputCrNoTag name="oldPatientVisitFB">
	
	</his:InputCrNoTag>
<bean:define id="crNo" name="oldPatientVisitFB" property="patCrNo" type="java.lang.String"/>
<his:statusList>
	<logic:notEmpty name="<%=RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO %>">
		<his:SubTitleTag name="Old Referred Patient List">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="4%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;
						</font>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
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
									<bean:message key="toDept" /> 
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
				
				<logic:iterate id="ARR_EPISODE_OLD_PAT_REFER_VO" name="<%=RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO %>" type="hisglobal.vo.EpisodeRefDtlVO" indexId="index">
					<tr>
						<td width="4%" class="tdfont">
							<div align="center">
								<font size="2">
									<html:radio name="oldPatientVisitFB" property="selectedRefCrNo" value='<%=index.toString()+"$"+ARR_EPISODE_OLD_PAT_REFER_VO.getPatCrNo() %>' onclick="showDetail(this)"></html:radio>
									<html:hidden name="oldPatientVisitFB" property="indexID"/>
								</font>
							</div>	
						</td>
						
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="patCrNo" /> 
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="patName" /> 
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="fromDepartment" /> 
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="fromDepartmentUnit" />
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="toDepartment" /> 
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="reffDateTime" /> 
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>	
		</his:ContentTag>
	</logic:notEmpty>

</his:statusList>




<%if(!crNo.trim().equals("")){%>	
	<jsp:include page="/registration/patientDetail.cnt" flush="true"/>

<his:statusRecordFound>
<%renewalStatus="renew";%>
		<his:SubTitleTag name="Renewal of Registration">
		</his:SubTitleTag>
		
		
		<his:ContentTag>
		<%if(Config.RENEWAL_TYPE.equals("1") || Config.RENEWAL_TYPE.equals("2")){ %>
			<table width="100%" colspacing="1" colpadding="0">
				<tr>
					<td width="20%"  class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="renewRegistration"/></b>
					</font>
					</div>
	  				</td>      
	  				<td width="20%"  class="tdfont">
					<input type="checkbox" tabindex="1" name="renewal" onclick="if (renewalConfirmation()) submitForm('RENEWAL')"/>
					</td>
					<td width="20%"  class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="amount"/></b>
					</font>
					</div>
	  				</td>  
	  				<td width="25%" class="tdfont">
					<div align="left"><html:text name="oldPatientVisitFB"
					property="amount" maxlength="3" size="4" readonly="true"  /></div>
					</td>
				</tr>
			</table>
			
			
			<%}else if(Config.RENEWAL_TYPE.equals("3") || Config.RENEWAL_TYPE.equals("4") || Config.RENEWAL_TYPE.equals("5")){ %> 
			<table width="100%" colspacing="1" colpadding="0">
			<bean:define id="RENEWALEPISODEVO" name="<%=RegistrationConfig.RENEWAL_REQUIRED_EPISODE_ARRAY%>" type="hisglobal.vo.EpisodeVO[]" />
			
		<%String amt=(String)session.getAttribute("amount"); %>
				<tr>
					<td width="5%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="renewRegistration"/></b></font></td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="department"/></b></font></td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="unit"/></b></font></td>
					
						
				</tr>			
				<logic:iterate id="episode" name="<%=RegistrationConfig.RENEWAL_REQUIRED_EPISODE_ARRAY%>" indexId="i">
				<tr>
					<td class="tdfonthead">
					<div align="center">
						<input type="checkbox" tabindex="1" name="departmentsToRenew"  value='<bean:write name="episode" property="episodeCode"/>' onclick="addAmount(this,'<%=amt %>')" onkeypress="if(event.keyCode==13) addAmount(this,'<%=amt %>'"/>
					</div>
					</td>
					<td class="tdfont">
					<div>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="episode" property="department"/>
					</font>
					</div>
					</td>
					<td class="tdfont">
					<div>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="episode" property="departmentUnit"/>
					</font>
					</div>
					</td>
				</tr>
				</logic:iterate>
				</table>
				<table width="100%" colpadding="0">
				<tr>
					<td width="50%" class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="amount"/></b>
					</font>
					</div>
	  				</td>  
	  				<td width="50%" class="tdfont">
					<div align="left"><html:text name="oldPatientVisitFB"
					property="amount" maxlength="3" size="4" readonly="true"  /></div>
					</td>
				</tr>
			</table>
			<%} %>
		</his:ContentTag>
	</his:statusRecordFound>


	<his:statusTransactionInProcess>
	
	<his:SubTitleTag name="Visit Details">
	</his:SubTitleTag>
	
	  <bean:define id="EPISODEVO" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>" type="hisglobal.vo.EpisodeVO[]" />
	  <his:ContentTag>
  			<% if(EPISODEVO.length!=0) {System.out.println("length in jsp......................"+EPISODEVO.length); %><%varStatus="InProcess";%>
		
				
					<table  width="100%" border="0" cellspacing="1" cellpadding="0">
						
						<tr>
						<td width="5%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="status"/></b></font></td>
						<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="department"/></b></font></td>
						<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="unit/room"/></b></font></td>
						<%if ((Config.CLIENT).equals(Config.CLIENT_GNCTD)){ %>
						<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="complaintdtl"/></b></font></td>
						<%}else{ %> 
						<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="fileNo"/></b></font></td>
						<%} %>
						<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="lastVisitdate"/></b></font></td>
						<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="con"/></b></font></td>
						</tr>
						<logic:iterate id="episode" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>" indexId="i"  type="hisglobal.vo.EpisodeVO">
						
						<%
						String renewalType=(String)episode.getRenewalType();
						String unitType=(String)episode.getIsGeneral()==null ? "" : (String)episode.getIsGeneral();						
						%>
						
						<%--<bean:define name="episode" id="renewalType" property="renewalType"/>
						<bean:define name="episode" id="unitType" property="isGeneral"/>  --%>

						<%String hh=(String)renewalType;
						int x=Integer.parseInt(hh);
						String backgroundColor="" ;
						String tdFontColor="class='tdfont'";
						if(unitType.equals(RegistrationConfig.UNIT_TYPE_SPECIALITY))
						{
							backgroundColor="bgcolor='#FFC0CB'" ;
							tdFontColor="";
						}
						
						%>
							<tr <%=backgroundColor %>>
								<td <%=tdFontColor %> nowrap >
								<!--start Unit Already Visit Today -->
								<logic:equal name="episode"  property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_TRUE %>">
									<div align="center">
									<img src='<his:path src="/hisglobal/images/icn-lock.png"/>' title="Unit Already Visited Today">
									</div>
								</logic:equal>
								<!-- end unit already visited today -->
								<!--start Unit Not Visited Today and Unit Is ON Duty -->		
								<logic:equal name="episode"  property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_FALSE %>">
									<logic:equal name="episode" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_TRUE%>">
									<% if(x==3 || x==4 || x==5){%>
											<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
											<div align="center">
											<input type="checkbox" tabindex="1" name="departmentsToVisitStamp" value='<bean:write name="episode"  property="episodeCode"/>' onclick="checkForRefer(this)" />
											</div>
											</logic:equal>
											
											<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
											<div align="center">
											<input type="checkbox" tabindex="1" name="departmentsToVisitStamp"  value='<bean:write name="episode" property="episodeCode"/>'/>
											</div>
											</logic:equal>
										<%} else {%>
											<div align="center">
											<input type="checkbox" tabindex="1" name="departmentsToVisitStamp" value='<bean:write name="episode" property="episodeCode"/>' onclick="checkForRefer(this)" />
											</div>
										<%} %>
									</logic:equal>									
								</logic:equal>	
								<!-- end unit not visited today and unit is on duty  -->
								
								<!-- start unit not visited today and unit not on duty -->
								<logic:equal name="episode"  property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_FALSE %>">
									<logic:equal name="episode" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE%>">
										<!--start Unit type special -->
										<logic:equal name="episode" property="isGeneral" value="<%=RegistrationConfig.UNIT_TYPE_SPECIALITY%>">
											<div align="center">
												<img src='<his:path src="/hisglobal/images/stop.png"/>' title="Unit not on duty">
											</div>
										</logic:equal>
										<!-- end Unit type special -->
										<!-- start unit type general -->
										<logic:equal name="episode" property="isGeneral" value="<%=RegistrationConfig.UNIT_TYPE_GENERAL%>">
											<!-- start  other general unit working -->
											<logic:notEqual name="episode" property="isOtherUnitWorking" value="<%=RegistrationConfig.NO_DEPT_UNIT_WORKING_TRUE%>">
													<html:hidden name="oldPatientVisitFB" property="hiddenEpisode" />
													<div align="center">
														<img src='<his:path src="/hisglobal/images/forward.gif"/>' title="Visit stamp on request" style=cursor:pointer tabindex="1" onclick = "submitFormOnValidate(validateOnRequest('<bean:write name="episode" property="episodeCode" />'),'SAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(validateOnRequest('<bean:write name="episode" property="episodeCode" />'),'SAVE');" >
														
														</div>
											</logic:notEqual>
											<!-- end other general unit working -->
										
										
										<!-- start other general unit not working -->
										<logic:equal name="episode" property="isOtherUnitWorking" value="<%=RegistrationConfig.NO_DEPT_UNIT_WORKING_TRUE%>">
													<html:hidden name="oldPatientVisitFB" property="hiddenEpisode" />
													<div align="center">
														<img src='<his:path src="/hisglobal/images/stop.png"/>' title="No Unit Working In The Department" >
													</div>
										</logic:equal>
										<!-- end other general Unit Working -->
									</logic:equal>
									<!-- end unit type general -->
									</logic:equal>
								</logic:equal>
								<!-- end unit not visited today and unit not on duty -->
								
										
								
								
								</td>
								 
								<td <%=tdFontColor %>>
									<div align="center">
									<% if(x==3 || x==4 || x==5){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="department"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="department"/>
										</font>
									</logic:equal>
									<%}
									else{%>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="department"/>
										</font>
									<%} %>
									</div>
								</td>
								
								
								
								<td <%=tdFontColor %>>
									<div align="center">
									<% if(x==3 || x==4 || x==5){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="departmentUnit"/>/
										<bean:write name="episode" property="room"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="departmentUnit"/>/
										<bean:write name="episode" property="room"/>
										</font>
									</logic:equal>
									<%}else{ %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="departmentUnit"/>/
										<bean:write name="episode" property="room"/>
										</font>
										<%} %>
									</div>
								</td>
								
								
								
								<%if ((Config.CLIENT).equals(Config.CLIENT_GNCTD)){ %>
								<td <%=tdFontColor %>>
									<div align="center">
									<% if(x==3 || x==4 || x==5){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="complainDetail"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="complainDetail"/>
										</font>
									</logic:equal>
									<%} else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="complainDetail"/>
										</font>
									<%} %>
									</div>
								</td>
								<%}else{ %>
								<td <%=tdFontColor %>>
									<div align="center">
									<% if(x==3 || x==4 || x==5){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="fileNoView"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="fileNoView"/>
										</font>
									</logic:equal>
									<%} else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="fileNoView"/>
										</font>
									<%} %>
									</div>
								</td>
								
								<%} %>
								
								<td <%=tdFontColor %> >
									<div align="center">
									<% if(x==3 || x==4 || x==5){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="episodeDate"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="episodeDate"/>
										</font>
									</logic:equal>
									<%} else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="episodeDate"/>
										</font>
									<%} %>
									</div>
								</td>
								
								<td <%=tdFontColor %> >
									<div align="center">
									<% if(x==3 || x==4 || x==5){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="empName"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="empName"/>
										</font>
									</logic:equal>
									<%} else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="empName"/>
										</font>
									<%} %>
									</div>
								</td>
							</tr>
							
										
						</logic:iterate>
						<html:hidden name="oldPatientVisitFB" property="hcode" />						
					</table>
			
<%} %>
		</his:ContentTag>
		
		
		
<%if(! (renewalStatus.equals("renew"))){ %>	
	<logic:notEqual name="oldPatientVisitFB" property="isPatReferByList" value="1" >	
	<his:SubTitleTag name="Refer Details">
	<table width="100%" cellspacing="1" cellpadding="0">
	  <tr>
		<td width="95%" nowrap="nowrap">
			<div align="right">
				<b><bean:message key="isreferred"/></b>
			</div>
		</td>
		<td width="5%">
			<div align="left">
			<html:checkbox name="oldPatientVisitFB" property="isReferred" value="1"  onclick="getEpisodeRefDtl('EPISODEDTL',this)" onkeypress="if(event.keyCode==13) getEpisodeRefDtl('EPISODEDTL',this);" tabindex="1"/>
			</div>
		</td>
		</tr>
	 </table>	
	</his:SubTitleTag>
		
		 <bean:define name="oldPatientVisitFB" property="isReferred" id="isref" type="java.lang.String"/>            
        
        <logic:equal name="isref" value="1">  
            <%
	        divisrefdisplay="";  
            externalReferdisplay="none";
            commonReferdisplay="none";
            associateddisplay="none";
            internalReferdisplay="none";
            referringInstTypedisplay="none";
            %>        
            
        </logic:equal> 
         <logic:equal name="isref" value="0">  
            <%
            divisrefdisplay="none";  
            externalReferdisplay="none";
            commonReferdisplay="none";
            associateddisplay="none";
            internalReferdisplay="none";
            referringInstTypedisplay="none";
        	
            %>        
        </logic:equal>
 <logic:equal name="isref" value="1">          
 <his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		<td width="40%">
		<div id="checkReferral" style="display:<%=divisrefdisplay%>">
			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="referInternal" />
			</font>
			<html:radio name="oldPatientVisitFB" property="isRefferInOut" tabindex="1" value="I" onclick="showInternal(this)" />
		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="referExternal" />
			</font>
			<html:radio name="oldPatientVisitFB" property="isRefferInOut" tabindex="1" value="E" onclick="showExternal(this)" />
		</div>
		</td>
		<td width="40%">
			<div id="externalRefer" style="display:<%=externalReferdisplay%>">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="gnctd" />
				</font>
				<html:radio name="oldPatientVisitFB" property="referringInstType" tabindex="1" value="G" onclick="showAssociated(this)" />
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="other" />
				</font>
				<html:radio name="oldPatientVisitFB" property="referringInstType" value="O" tabindex="1" onclick="showOthers(this)" />
				<html:hidden name="oldPatientVisitFB" property="isAssociated" />
				</div>
		</td>
		</tr>
	</table>
	</his:ContentTag>
	</logic:equal>
	<bean:define id="clientFlag" name="<%=Config.CLIENT_NAME %>"></bean:define>
	
		<div id="commonRefer" style="display:<%=commonReferdisplay%>"> 
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
				<html:text name="oldPatientVisitFB" styleClass="textbox" maxlength="100" property="patRefDoctor" onblur="isAlpha(this,'Referred By Doctor')" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
				</td>
				<td width="25%"  class="tdfonthead">
				<div id="commonReferInternal" style="display:none">&nbsp; &nbsp; &nbsp; &nbsp;</div>
				
				<div id="commonReferExternal" style="display:none">
				<div align="right"><font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="hospital"/> <bean:message key="name"/></font></div>
				</div>
				</td>
				
				<td width="25%"  class="tdfont">
				<div id="commonReferInternal_1" style="display:none">&nbsp; &nbsp; &nbsp; &nbsp;</div>
				
				<div id="commonReferExternal_associated" style="display:none">
				<html:select name="oldPatientVisitFB" tabindex="1" property="patRefGnctdHospitalCode" styleClass="regcbo">
				<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>" property = "value" labelProperty = "label"/>
			    </html:select>
			    </div>
			    
			    <div id="commonReferExternal_other" style="display:none">
				<html:text  name="oldPatientVisitFB" tabindex="1" property="patRefHospitalName" onkeypress="return validateAlphabetsOnly(event)" maxlength="100" styleClass="textbox" size="20"/>
				</div>
				</td>
			</tr>
			
			 <tr>
				<td width="25%" class="tdfonthead" >
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referFromDept" />
				</font></div>
				</td>
				<td width="25%" class="tdfont">
					<html:select name="oldPatientVisitFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
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
					<html:text name="oldPatientVisitFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true"/>
					</td>
				</tr>
			
		</table>
		
		
		
		<div id="associated" style="display:<%=associateddisplay%>">		
		<table width="100%" cellspacing="1" cellpadding="1">
			<tr>
				<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referring" /> <bean:message key="crNo" />
				</font></div>
				</td>
				<td width="25%" %  class="tdfont">
				<html:text name="oldPatientVisitFB" tabindex="1" property="patRefGnctdHospitalCrno" maxlength="12" tabindex="1" onkeydown="setPrevValue(this, event);" onkeyup="moveToRight(this, event);" onkeypress="return validateNumeric(event)" styleClass="textbox" />
				</td>
			    <td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referring" /> <bean:message key="unit" />
				</font></div>
				</td>
				<td width="25%"  class="tdfont">
				<html:text name="oldPatientVisitFB" property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15" styleClass="textbox" />
				</td>
			</tr>
			
		</table>
		</div> 
 
 </his:ContentTag>
 </div>


<div id="internalRefer" style="display:<%=internalReferdisplay%>">
			<!--- ReferInternal -------  Details of all open episodes-->

		
				<his:ContentTag>
				<logic:notEmpty
				name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>">
				<bean:define id="OPD_OPEN_EPISODES"
					name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>"
					type="hisglobal.vo.EpisodeRefDtlVO[]" />
				

					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<%int i = 0;%>
						<tr>
							<td width="5%" class="tdfonthead"><font
								color="#000000" size="2"
			 					face="Verdana, Arial, Helvetica, sans-serif"></td>

							<td width="30%" class="tdfonthead"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="referFromDept" /> </font> </b></div></td>



							<td width="45%" class="tdfonthead"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="referFromDeptUnit" /> </font> </b></div></td>



						</tr>

						<logic:iterate id="ARR_OPD_OPEN_EPISODE_VO"
							name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>">
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="episodeCode" id ="epCode"/>
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartment" id ="frmDep"/>
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentUnit" id ="frmDepUnit"/>
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentCode" id ="frmDepCode"/>
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentUnitCode" id ="frmDepUnitCode"/>
<%String hh=(String)epCode; %>
<%String strFrmDep=(String)frmDep; %>
<%String strFrmDepUnit=(String)frmDepUnit; %>
<%String strFrmDepCode=(String)frmDepCode; %>
<%String strFrmDepUnitCode=(String)frmDepUnitCode; %>
							<tr>
								<td width="5%" class="tdfont">
								<div align="center"><input type="radio"
									name="refferringOPDEpisode"
									onclick="enableDepartment('<%=i++%>');"
									value="<%=hh %>" tabindex="1"/></div>
								</td>
								<html:hidden
									name="oldPatientVisitFB" property="episodeCode"
									value="<%=hh%>"/>
								
								<td width="30%" class="tdfont">
								<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
									property="fromDepartment" />
									<html:hidden
									name="oldPatientVisitFB" property="fromDepartment"
									value="<%=strFrmDepCode%>"/>
									</div>
								</td>


								<td width="45%" class="tdfont">
								<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
									property="fromDepartmentUnit" />
									<html:hidden
									name="oldPatientVisitFB" property="fromDepartment"
									value="<%=strFrmDepUnitCode%>"/>
									</div>

								</td>

							</tr>
						</logic:iterate>

					</table>
					
			</logic:notEmpty>
				</his:ContentTag>

		

		<!--- End ReferInternal ---- Details of all open episodes-->
		</div>
		</logic:notEqual>
				
		
	<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	<div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<input type="checkbox">
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Visit stamp allowed
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<img src='<his:path src="/hisglobal/images/icn-lock.png"/>'>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Unit already visited today
					</div>
					</font>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<img src='<his:path src="/hisglobal/images/stop.png"/>'>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					No unit working in the department
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<img src='<his:path src="/hisglobal/images/forward.gif"/>'>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					On request, visit stamp to other unit of same department (only for general units)
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Red
					</div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Registration expired, renewal required for this department
					</div>
					</font>
				</td>				
			</tr>
	
		</table>
	</his:ContentTag>
	</div>
	
	<%} %>
</his:statusTransactionInProcess>
<%
}
%>



<his:ButtonToolBarTag>
<%if(renewalStatus.equals("renew")){ %>
<div align="center">
				        	    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick = "if(renewalValidation())submitForm('RENEWAL');" onkeypress="if (event.keyCode==13) submitForm'RENEWAL');" >
					         		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          			            	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          			          	</div>
<%}else if(varStatus.equals("InProcess")){%>


				        		<div align="center">
				        	    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick = "submitFormOnValidate(validateOldPatientVisit() && ValidateDepartmentNewDeptVisit(),'SAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(validateOldPatientVisit() && ValidateDepartmentNewDeptVisit(),'SAVE');" >
					         		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          			            	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          			          	</div>
          			                  			          

			<%}else{ %>
					         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
		            <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          			          

          			        		
			<%} %> 
			
			</his:ButtonToolBarTag>
 

		  <his:status/>
		  			
<input type="hidden" name="hmode" value="unspecified"/>
<html:hidden name="oldPatientVisitFB" property="isPatReferByList" />
<html:hidden name="oldPatientVisitFB" property="indexID" />
<html:hidden name="oldPatientVisitFB" property="onRequestVisit" />
<html:hidden name="oldPatientVisitFB" property="saveSuccessful" />

<div id="blanket" style="height: 580;width: 1024;display: none;"></div>

<div id="confirmMsg" style="display: none; height: 110px;width: 310px;position:absolute; top: 20%;left: 30%;z-index: 9100;background-image:url('../hisglobal/images/confirmYesNoImage.png'); ">
	<br>
	<br>
	<br>
	<div id="alertMessage1" align="center" style="position: absolute;left: 30%;top: 30%;"></div>
	 <div id='yesNoButton' style='position:absolute;left: 33%; top: 58%;'>
	 <input type='button' value='Yes' name="yesButton" onclick='closeAlert(true)' onkeyPress='if(event.keyCode==13) closeAlert(true)'
	  tabindex='1' id='yesButton' style='font-weight:bold;margin-right:10px;cursor:pointer;left: '>
	 <input type='button' value='No' name="noButton" tabindex='1' id='noButton'  
	 style='font-weight:bold;margin-left:10px;cursor:pointer;' onclick='closeAlert(false)' 
	 onkeyPress='if(event.keyCode==13) closeAlert(false)'>
	 </div>
</div>

<%}catch(Exception e){e.printStackTrace();} %>

