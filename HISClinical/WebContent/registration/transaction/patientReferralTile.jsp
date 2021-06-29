<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="java.util.List"%>

<%@ page import="registration.*,registration.controller.fb.PatientReferralFB"%>
<%@page import="opd.*"%>

<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css"> 
@media print { 
		#noprint 
		{
		 display: none; 
		}
		#divReferalLetter
		{
			display: block;
		}
		#paRefMsg
		{
			display: none;
		}
		
		
		
}
 td.TdBorder{
 		 border:2px solid black;
		}
		#divFooter 
		{
            width:779px; 
            margin-top:20px;
        }
 
 </style>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/opd/opdJs/opd.js"/>


<script type="text/javascript">


function handleResponse(){
 
 if(request.readyState == 4){
 if(request.status == 200){
 // alert("xcxcxcxcxcxc")
 
 
 var reply=request.responseText
 
 

	 document.getElementById('divDepartmentForHospital').innerHTML=reply
	 
	

 	
 } else {
 alert("A problem occurred with communicating between "+
 "the XMLHttpRequest object and the server program.");
 }
 }//end outer if
}
///////////////////////////////////////Autometic Profile Generator/////////////////////////////////START
function generateReport(e)
{	
	var selectedProfileType = document.getElementsByName("profileType")[0];
	submitProfileType(selectedProfileType);
	var autoProfileCalledFrom= 2 //patientProfile =0, patientRefered=1, externalRefered=2
	var hmode="VIEWAUTOMATICGENRATEDPROFILE";
	var profileType=document.getElementsByName("profileType")[0].value;
	var profileGenerationMode=document.getElementsByName("profileGenerationMode")[0].value;
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var deskId="1";
	for(var i=0;i<document.getElementsByName("selectedEpisode").length;i++)
		if(document.getElementsByName("selectedEpisode")[i].checked)
		{
			var episodeCode = document.getElementsByName("selectedEpisode")[i].value;
			break;
		}
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var url = "/HISClinical/opd/opdPatientProfile.cnt?hmode="+hmode+"&patCrNo="+patCrNo+"&autoProfileCalledFrom="+autoProfileCalledFrom+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&profileGenerationMode="+profileGenerationMode+"&deskId="+deskId+"&profileType="+profileType;
	openDependentPopup(url,e,700,700,true);
}
function submitProfileType(obj)
{
	var profileType=0;
	var profileGenerationMode=0;
	if(obj.value!="-1"){
	profileType = obj.value.split("#")[0];
	//alert("profile type"+profileType);
	profileGenerationMode=obj.value.split("#")[1];
	var e  = document.getElementsByName("profileType")[0];
	e.value =profileType;
	var e1  = document.getElementsByName("profileGenerationMode")[0];
	e1.value =profileGenerationMode;
	}
	else{
		document.getElementsByName("profileHeader")[0].value="";
	}
}
///////////////////////////////////////Autometic Profile Generator/////////////////////////////////CLOSED

function setRefHosp()
{
var refHos= document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].options.selectedIndex].text;
 document.getElementsByName("referralExtHospitalName")[0].value=refHos;
}


function initReq(reqType,url,isAsynch){
// alert("inside initReq()")

 /* Specify the function that will handle the HTTP response */

 request.onreadystatechange=handleResponse;
//alert("url in initreq "+url);
 request.open(reqType,url,isAsynch);

 /* set the Content-Type header for a POST request */

 request.setRequestHeader("Content-Type",

 "application/x-www-form-urlencoded; charset=UTF-8");
//alert("query String"+queryString)
 request.send(url);

}



function httpRequest(reqType,url,asynch){

 //Mozilla-based browsers
//alert("inside httpRequest")
 if(window.XMLHttpRequest){

 request = new XMLHttpRequest();
 //alert("url in http"+url);
 initReq(reqType,url,asynch);

 } else if (window.ActiveXObject){
//alert("For Internet Explorer")
 request=new ActiveXObject("Msxml2.XMLHTTP");
	//alert("request object:Mscml2"+request)
 if (! request){

 request=new ActiveXObject("Microsoft.XMLHTTP");
//alert("request object:Microsoft"+request)
 }

 if(request){
//	alert("request branched here")
 initReq(reqType,url,asynch);


 /* Unlikely to branch here, as IE uses will be able to use either 

 one of the constructors*/

 } else {

 alert("Your browser does not permit the use of all "+

 "of this application's features!");}

 } else {

 alert("Your browser does not permit the use of all "+

 "of this application's features!");}

}

function sendData(){
	
	var selectedHospitalCode=document.getElementsByName("patRefGnctdHospitalCode")[0].value;
   
 
 	if(selectedHospitalCode!="" && selectedHospitalCode!='-1'){
	 	var url='/HISClinical/registration/patientReferral.cnt?hmode=GETDEPTBYHOSPITAL&patRefGnctdHospitalCode='+selectedHospitalCode;
	 	//alert(url)
	 	httpRequest("POST",url,true)
	}
  
}


var departmentList="";
var departmentUnitList="";

window.onload=function(){

//alert("inside onload");
//alert("inside onload print value is "+document.getElementsByName('print')[0].value);
if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
		
	}
	
	
	//departmentList=(document.getElementsByName("departmentCode")[0]).innerHTML;
	departmentUnitList=(document.getElementsByName("departmentUnitCode")[0]).innerHTML;
//	alert("departmentList "+departmentList)
//	alert("departmentUnitList "+departmentUnitList)
	getFilteredDeptUnit(document.getElementsByName("selectedEpisode")[0])
	
	
	
	
	
}




function validateIt(){
	
	if (document.getElementsByName("isRefferInOut")[1].checked==true)
	{
		document.getElementsByName("isRefferInOut")[1].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL%>;
	
		if (document.getElementsByName("referringInstType")[1].checked==true)
		{	
			document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_FALSE%>;
		
			if(document.getElementsByName("patRefHospitalName")[0].value=="")
			{
				alert("Please Enter Institute Name");
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
				alert("Please Select Institute Name");
				document.getElementsByName("patRefGnctdHospitalCode")[0].focus();
				return false;
			}
		}
		else
		{
			alert("Please Select Referring Institute Type");
			return false;
		}
	}
	else if (document.getElementsByName("isRefferInOut")[0].checked==true)
	{
		
		document.getElementsByName("isRefferInOut")[0].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL%>;
		document.getElementsByName("remarks")[0].value=document.getElementsByName("remarks")[1].value;
		if(document.getElementsByName("choice")[0].checked==true)
		{
			if(document.getElementsByName("departmentCode")[0].value=="-1")
			{
				alert("Please Select the Department");
				return false;
			}
		}
			
		else if(document.getElementsByName("choice")[1].checked==true)
		{
			if(document.getElementsByName("departmentUnitCode")[0].value=="-1")
			{
				alert("Please Select Special Clinic");
				return false;
			}
		}
		
	}
	else
	{
			alert("Select Referring Type");
			return false;
	}
	
	if(document.getElementsByName("departmentUnitCode")[0].value=="-1")
	{
		document.getElementsByName("departmentUnitCode")[0].value="";
	}
	if(document.getElementsByName("departmentCode")[0].value=="-1")
	{
		document.getElementsByName("departmentCode")[0].value="";
	}
	
	setRefHosp();
	return true;
	
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
	document.getElementById("commonReferExternal_associated").style.display="none";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("commonReferExternal_DepartmentName").style.display="none";
	
	document.getElementById("externalRefer").style.display="none";
	document.getElementsByName("choice")[0].checked=true;
	document.getElementById("deptUnitTableId").style.display="";
	document.getElementById("departmentField").style.display="";
	document.getElementById("depatrmentLable").style.display="";
	document.getElementById("specialUnitField").style.display="none";
	document.getElementById("specialUnitLable").style.display="none";
}

function showExternal(e){
	
	
	document.getElementById("commonRefer").style.display="none";


	document.getElementById("internalRefer").style.display="none";

	document.getElementById("externalRefer").style.display="block";
	document.getElementsByName("referringInstType")[0].checked=true;
	showAssociated(e);
}

function showAssociated(e){

	
	document.getElementById("commonRefer").style.display="block";
	
	document.getElementById("commonReferExternal_associated").style.display="block";		
	document.getElementById("divDepartmentForHospital").style.display="block";	
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("commonReferExternal_DepartmentName").style.display="none";
	document.getElementById("internalRefer").style.display="none";

}

function showOthers(e){


	document.getElementById("commonRefer").style.display="block";
	
	document.getElementById("commonReferExternal_other").style.display="block";
	document.getElementById("commonReferExternal_DepartmentName").style.display="block";
	
	document.getElementById("divDepartmentForHospital").style.display="none";	
	document.getElementById("commonReferExternal_associated").style.display="none";		
	document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true;
	document.getElementsByName('patRefHospitalDeptOther')[0].value="";
	document.getElementById("internalRefer").style.display="none";
}

function checkReferDepartment(obj)
{
	//alert(document.getElementsByName('patRefGnctdHospitalDeptUnit')[0].value);
	if(document.getElementsByName('patRefGnctdHospitalDeptUnit')[0].value=='0')
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false;
		document.getElementsByName('patRefHospitalDeptOther')[0].focus();
		
	}
	else
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true;
		document.getElementsByName('patRefHospitalDeptOther')[0].value="";
	}
}

function selectDept(obj){
	if(obj.value=="0"){
		document.getElementById("deptUnitTableId").style.display="";
		document.getElementById("departmentField").style.display="";
		document.getElementById("depatrmentLable").style.display="";
		document.getElementById("specialUnitField").style.display="none";
		document.getElementById("specialUnitLable").style.display="none";
	}
	else{
		if(obj.value=="1"){
			document.getElementById("deptUnitTableId").style.display="";
			document.getElementById("departmentField").style.display="none";
			document.getElementById("depatrmentLable").style.display="none";
			document.getElementById("specialUnitField").style.display="";
			document.getElementById("specialUnitLable").style.display="";
		}
	}
}

function getFilteredDeptUnit(obj)
{
	var unitTypeGeneral="<%=RegistrationConfig.UNIT_TYPE_GENERAL%>";
	var unitTypeSpecial="<%=RegistrationConfig.UNIT_TYPE_SPECIALITY%>";
	var index=obj.title;
	var indexInt=parseInt(index);
	var selectedUnitType=document.getElementsByName("selectedDepartmentUnitType")[indexInt].value;
	//var selectedDepartmentCode=document.getElementsByName("selectedDepartmentCode")[indexInt].value;
	var selectedDepartmentUnitCode=document.getElementsByName("selectedDepartmentUnitCode")[indexInt].value;
	//document.getElementsByName("departmentCode")[0].innerHTML=departmentList;
	document.getElementsByName("departmentUnitCode")[0].innerHTML=departmentUnitList;
	//var departmentCombo=document.getElementsByName("departmentCode")[0];
	var departmentUnitCombo=document.getElementsByName("departmentUnitCode")[0];
	// alert("selectedDepartmentCode "+selectedDepartmentCode)
	
	
	
		var options=departmentUnitCombo.options;
		// alert("options "+options)
		var i=0;
		for(;i<options.length;i++)
		{
			// alert(options[i].value)
			if(options[i].value==selectedDepartmentUnitCode)
			{
				departmentUnitCombo.remove(i);
				break;
			}
		}
	
}


function printPage() 
{
var frameElement = parent.document.getElementById("frmMain"); 
//alert("frameElement :"+frameElement);
var win = frameElement.contentWindow ;
win.print();
}

function callThisOnload(){	
	//alert("onload");
	//alert(document.getElementsByName('print')[0].value)	
	if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}
	}

</script>





<% 
			String divisrefdisplay="\"\"";
		 String externalReferdisplay="none";
		 String commonReferdisplay="none";
         String associateddisplay="none";
         String internalReferdisplay="none";
         String referringInstTypedisplay="none";
         String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
		%>
<body>
<div id="noprint">	
	<his:TitleTag>
		<his:name>
			<bean:message key="opdReferPatient" />
		</his:name>
		<b> <font  size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> 
		</font> </b>
	</his:TitleTag>
	
	<his:InputCrNoTag name="patReferralFB" >
</his:InputCrNoTag>
	
<his:statusTransactionInProcess>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />

<logic:present name="<%=RegistrationConfig.ARRAY_OPEN_EPISODE_VISITED_TODAY %>">

<his:SubTitleTag name="Visit Details">
</his:SubTitleTag>

<his:ContentTag>
	
		
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>

							<td width="8%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
								<div align="center">	
									<b>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="select"/>
										
										</font>
									</b>
								</div>	
							</td>
						    
						    <td width="23%" align="center" Class = "tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">    
								    <b>
									    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										    <bean:message key="department"/>
									    </font>
								    </b>
								</div>    
						    </td>
						    
						    <td width="23%" align="center" Class = "tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">    
								    <b>
									    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										    <bean:message key="unit"/>
									    </font>
							    	</b>
							    </div>	
						    </td>
						    
						    
						</tr>
			
			<%int i=0; 
			String checkedValue="";%>
			<logic:iterate id="openEpisodeVO" name="<%=RegistrationConfig.ARRAY_OPEN_EPISODE_VISITED_TODAY %>" type="hisglobal.vo.EpisodeVO" indexId="index" >
			<%if(i==0)
				{
				checkedValue="checked='checked'";
				i++;
				}
				else{
					checkedValue="";
				}%>
				
								<tr>
								    <td width="8%" class="tdfont" >
									    <div align="center">
										    <input type="radio" name="selectedEpisode" tabindex="1" value='<bean:write name="openEpisodeVO" property="episodeCode" />' <%=checkedValue%> title="<%=String.valueOf(index.intValue())%>" onclick="getFilteredDeptUnit(this);" />
						    			</div>
						    		</td>
						    
						    		<td width="23%" Class = "tdfont">
									    <div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="openEpisodeVO" property="department"/>
											</font>
										</div>
									</td>
							
									<td width="23%" Class = "tdfont">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="openEpisodeVO" property="departmentUnit"/>
											</font>
										</div>
									</td>
									<html:hidden  name="patReferralFB" property="selectedDepartmentUnitType" value="<%=openEpisodeVO.getDepartmentUnitType() %>" />
									<html:hidden  name="patReferralFB" property="selectedDepartmentCode" value="<%=openEpisodeVO.getDepartmentCode() %>" /> 
									<html:hidden  name="patReferralFB" property="selectedDepartmentUnitCode" value="<%=openEpisodeVO.getDepartmentUnitCode() %>" />
							   
							
								</tr>
						
								
			
			</logic:iterate>
				</table>
		
	
</his:ContentTag>

	<his:SubTitleTag name="Select Refer Type">
			
		</his:SubTitleTag>
		
		
 <his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">
 <tr>
		<tr>
		
		
		<td >
		<div id="checkReferral" style="display:<%=divisrefdisplay%>">
		
			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="referInternal" />
			</font>
			<html:radio name="patReferralFB" property="isRefferInOut" tabindex="1" value="I" onclick="showInternal(this)" />
		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="referExternal" />
			</font>
			<html:radio name="patReferralFB" property="isRefferInOut" tabindex="1" value="E" onclick="showExternal(this)" />
		
		</div>
		</td>
		
		
		
		<td >
			<div id="externalRefer" style="display:<%=externalReferdisplay%>">
			
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="gnctd" />
				</font>
				<html:radio name="patReferralFB" property="referringInstType" tabindex="1" value="G" onclick="showAssociated(this)" />
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="other" />
				</font>
				<html:radio name="patReferralFB" property="referringInstType" value="O" tabindex="1" onclick="showOthers(this)" />
				<html:hidden name="patReferralFB" property="isAssociated" />
				
				</div>
		</td>
		</tr>
	</table>
	</his:ContentTag>
	
	
		<div id="commonRefer" style="display:<%=commonReferdisplay%>"> 
	<his:ContentTag>
		<bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>

		<table width="100%" cellspacing="1" cellpadding="1">
		<tr>
				<td width="20%"  class="tdfonthead">
				<div align="right"> 
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="institute"/> <bean:message key="name"/></font>
				</div>
				</td>
				<td width="15%"  class="tdfont">
				<div id="commonReferExternal_associated" style="display:none">
				<html:select name="patReferralFB" tabindex="1" property="patRefGnctdHospitalCode" styleClass="regcbo" onchange="if(this.value!='-1'&& this.value='-1')sendData();">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>">
				<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>" property = "value" labelProperty = "label"/>
				</logic:present>
			    </html:select>
			    </div>
			    
			    <div id="commonReferExternal_other" style="display:none">
				<html:text  name="patReferralFB" tabindex="1" property="patRefHospitalName" onkeypress="return validateAlphabetsOnly(event,this)" maxlength="100" styleClass="textbox" size="15"/>
				</div>
				</td>
				 <td  width="15%" class="tdfonthead" >
	 			<div align="right">
	 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    <bean:message key="reason"/>
			    </font>
			    </div>
			    </td>
			   <td width="20%" class="tdfont" > 
			   <div align="left">
				 <input type="textarea" name="remarks"  onkeypress="return (validateTextArea(event,this,'50') && validateAlphaNumericOnly(event))" style="height: 30px;" size="20" maxlength="50" tabindex="1"/>
			   </div>
			   </td>
			   
				<td width="30%" class="tdfonthead">
					<div align="center">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="referProfileType" />
						</font>
					</div>
				</td>
				
			</tr>	
			<tr>
				<td width="20%" class="tdfonthead" nowrap>
				<div align="right"> 
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referToDept" />
				</font>
				</div>
				</td>
				
				<td width="15%" class="tdfont">
						
						<div id="divDepartmentForHospital">
						<html:select name="patReferralFB" property="patRefGnctdHospitalDeptUnit" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
						<html:option value="">Select Value</html:option>
						<html:option value="0">Other</html:option>
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>" property="value" labelProperty="label" />
						</html:select> 
						</div>
						<div id="commonReferExternal_DepartmentName" style="display:none">
						<html:text name="patReferralFB" property="patRefGnctdHospitalDept" tabindex="1" size="20" maxlength="15"></html:text>
						</div>
				</td>
				<td width="15%" class="tdfonthead" >
   			 	<div  align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="other" />
				<bean:message key="department" /> </font>
				</div>
				<td width="20%" class="tdfont" >
   			 	<html:text name="patReferralFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true" onkeypress="return validateAlphaNumericOnly(event,this)"/>
   			 				
				</td>
				
				<td width="30%" class="tdfont" >	
						<div align="center">
							<input type="hidden" name="genProfileType"/>
							<%
							List lst=(List)session.getAttribute(OpdConfig.PROFILE_TYPE_LIST);
							int size=lst.size();
							boolean flag=false;
							if(size==1 || size==0) flag=true;
							%>
							<html:select name="patReferralFB" property="profileType" onchange="submitProfileType(this)" styleClass="regcbo">
							<% if(!flag){%>
							<html:option value="-1">Select Value</html:option>
							<%} %>
							<logic:present name="<%=OpdConfig.PROFILE_TYPE_LIST%>">
							<html:options collection="<%=OpdConfig.PROFILE_TYPE_LIST %>" property="value" labelProperty="label"  />
							</logic:present>
							</html:select>									
							<img class="button" id="generateReportButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex='1' alt="Add Refer" title="Add Refer" onkeypress="if(event.keyCode==13) generateReport(event) ;" onclick="generateReport(event)">
				</td>
				
			</tr>
			
		</table>
 </his:ContentTag>
 </div>

<div id="internalRefer" style="display:<%=internalReferdisplay%>">
<his:ContentTag>
<his:SubTitleTag name="Refer To">
<bean:message key="department"/>
<html:radio  name="patReferralFB" property="choice" value="0" tabindex="1" onclick="selectDept(this)" ></html:radio>
 <bean:message key="specialClinic"/>
<html:radio  name="patReferralFB" property="choice" value="1" tabindex="1" onclick="selectDept(this)" ></html:radio>
</his:SubTitleTag>
	<table id="deptUnitTableId" width="100%" cellspacing="1" cellpadding="1" style="display: none;">
		<tr>
			
		    <td id="depatrmentLable"  width="20%" class="tdfonthead" >
 			<div align="right">
 			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    <bean:message key="department"/>
		    </font>
		    </div>
		    </td>
		    
		   <td id="departmentField" width="20%" class="tdfont" > 
	       <div  align="left">
		   <html:select name="patReferralFB"  property="departmentCode" tabindex="1" styleClass="regCbo"  > 
		   <html:option value="-1">Select Value</html:option>
		   <logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>">
		   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>" property = "value" labelProperty = "label"/>
		   </logic:present>
		   </html:select>
		   </div>
		   </td>
		 
		    <td id="specialUnitLable" width="20%" class="tdfonthead">
		    <div align="right">
 			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    <bean:message key="specialClinic"/>
		    </font>
		    </div>
            </td>
        
          
		   <td id="specialUnitField" width="20%" class="tdfont" > 
		   <div align="left">
		   <html:select name="patReferralFB"  property="departmentUnitCode" tabindex="1" styleClass="regCbo"  > 
		   <html:option value="-1">Select Value</html:option>
		   <logic:present name="<%=OpdConfig.OPD_UNIT_WITH_SPECIALITY%>">
		   <html:options collection = "<%=OpdConfig.OPD_UNIT_WITH_SPECIALITY%>" property = "value" labelProperty = "label"/>
		   </logic:present>
		   </html:select>
		   </div>
		   </td>
		   </tr>	 	
		   <tr>
		   <td  width="20%" class="tdfonthead" >
 			<div align="right">
 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    <bean:message key="reason"/>
		    </font>
		    </div>
		    </td>
		   <td  width="40%" class="tdfont" > 
		   <div align="left">
			 <input type="textarea" name="remarks"  onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" style="height: 30px;" size="40" maxlength="100" tabindex="1"/>
		   </div>
		   </td>
</tr>
		</table>
	</his:ContentTag>
</div>





</logic:present>

</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<logic:present name="<%=RegistrationConfig.ARRAY_OPEN_EPISODE_VISITED_TODAY %>">
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer;"  tabindex="1" onclick =  "submitFormOnValidate(validateIt(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SAVE');")>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</logic:present>
	<logic:notPresent name="<%=RegistrationConfig.ARRAY_OPEN_EPISODE_VISITED_TODAY %>">
		 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</logic:notPresent>          
</his:ButtonToolBarTag>


<html:hidden name="patReferralFB" property="hmode" value="unspecified"/>
<html:hidden name="patReferralFB" property="episodeVisitNo"/>
<html:hidden name="patReferralFB" property="print"/>
<html:hidden name="patReferralFB" property="referralExtHospitalName"/>
<html:hidden name="patReferralFB" property="patCrNo" />
<html:hidden name="patReferralFB" property="profileType" />
<html:hidden name="patReferralFB" property="selectedEpisode" />
<html:hidden name="patReferralFB" property="profileGenerationMode" />
</div>
<logic:equal name="patReferralFB" property="print" value="1">
<div id='divReferalLetter' style="display:block;"><his:GenericTemplateTag templateId='3310001'></his:GenericTemplateTag></div>
</logic:equal>
<his:status />

</body>
