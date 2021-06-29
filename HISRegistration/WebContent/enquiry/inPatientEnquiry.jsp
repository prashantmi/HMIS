<%try{ %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.config.*"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%-- <his:javascript src="/registration/js/registration.js" /> --%>
<%-- <his:javascript src="/registration/js/calendar.js" /> --%>
<%-- <his:javascript src="/registration/js/validationCommon.js" /> --%>
<%-- <his:javascript src="/registration/js/validationCalls.js" /> --%>
<%-- <his:javascript src="/registration/js/commonFunctions.js" /> --%>
<%-- <his:javascript src="/registration/js/registration.js" /> --%>
<%-- <his:javascript src="/registration/js/popup.js" /> --%>
<%-- <his:javascript src="/registration/js/dateFunctions.js" /> --%>

<script type="text/javascript" src="/HIS/hisglobal/js/avai/calendar.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/validationCommon.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/validationCalls.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/popup.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js" /></script>
<script type="text/javascript" src="/HISRegistration/registration/transactions/js/regValidation.js" /></script> 


<his:javascript src="/opd/opdJs/opdAjax.js"/>
<!-- <his:javascript src="/opd/opdJs/opd.js"/> -->
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />


<link rel="stylesheet" href="/HIS/hisglobal/css/avai/Color.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/avai/hisStyle.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/avai/hisStyleExt.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/avai/calendar-blue2.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/avai/master.css">


<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script>
var deptHtml="";
var globalDeptHtml="";
var flagDeptHtmlSet="0";

function getOrderBy(mode,order){
	document.forms[0].hmode.value=mode;
	document.forms[0].order.value=order;
	document.forms[0].submit();
}
function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.getElementsByName("hgnum_deptunitcode")[0].disabled=false;
	document.getElementsByName("hgnum_ward_code")[0].disabled=false;
	document.forms[0].submit();
}

function showState(){ 
	var contryCode=document.getElementsByName("gnum_country_code")[0].value;
	
// alert(<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>)
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	 //alert("chcking conuntry code "+contryCode+"  "+defaltcontryCode);
	var stateCode= document.getElementsByName("gnum_state_code")[0];
	if(contryCode==defaltcontryCode)
	{
		// alert("default country")
		document.getElementById("stateMandotary").style.display="";
		document.getElementById("divpatAddStateCodeCombo").style.display="";
		document.getElementById("divpatAddStateCodeText").style.display="none";
		document.getElementById("stateNotMandotary").style.display="none";
		document.getElementById("districtCombo").style.display="";
		document.getElementById("districtTextBox").style.display="none";
		if(stateCode.options[stateCode.selectedIndex].value=="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>")
		{
		document.getElementById("divpatAddCityLocCode").style.display=""; 
	  	document.getElementById("divpatAddCityLocation").style.display="none"; 
	  	}
	// 	document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		
	}
	else{
	// alert("not default country")
		document.getElementById("stateMandotary").style.display="none";
		document.getElementById("divpatAddStateCodeCombo").style.display="none";
		document.getElementById("divpatAddStateCodeText").style.display="";
		document.getElementById("stateNotMandotary").style.display="";
		document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  	document.getElementById("divpatAddCityLocation").style.display="";
	  	document.getElementById("districtCombo").style.display="none";
		document.getElementById("districtTextBox").style.display="";
		 
	  	document.getElementsByName('hrgstr_city_location')[0].value=""
	  	document.getElementsByName('hrgnum_is_urban')[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE%>";
	  	document.getElementsByName('hrgnum_pincode')[0].value=""
	  	document.getElementsByName('gnum_city_loc_code')[0].value="-1"
	  	document.getElementsByName('num_dist_id')[0].value="-1"
	  	
	}
	//  showLocation();
	
}

function showLocation(){

  var elmt= document.getElementsByName("gnum_state_code")[0];
  var locationRequired=0;
  var locationRequiredYes=1;
  
  if(locationRequired==locationRequiredYes)
  { 
  
	  if(elmt.options[elmt.selectedIndex].value!=<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>)
	  	  {  
	  	   
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display="";
		   document.getElementsByName("hrgstr_city")[0].value=""; 
		  }
	  else{
		 //alert("inside else hide divpatAddCityLocation") 
	  	  document.getElementById("divpatAddCityLocCode").style.display=""; 
		  document.getElementById("divpatAddCityLocation").style.display="none";   
		   document.getElementsByName("hrgstr_city")[0].value="";
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	}
	else{
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display="";   
		  document.getElementsByName("hrgstr_city")[0].value="";   
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	
}

function callThisOnload(){	
	
	document.getElementsByName("fromDate")[0].value=document.getElementsByName("fromDateHidden")[0].value;
	document.getElementsByName("toDate")[0].value=document.getElementsByName("toDateHidden")[0].value;
	setOnLoadDtl();	
	showState();
	showLocation();
	//sendData();
}

function validatePatientEnquiry()
{
	var valid;
	if(document.forms[0].hrgstr_fname.value!="" ||
	document.forms[0].hrgstr_mname.value!="" ||
	document.forms[0].hrgstr_lname.value!="" ||
	document.forms[0].gnum_gender_code.value!="-1" ||
	document.forms[0].hrgstr_father_name.value!="" ||
	document.forms[0].hrgstr_spousename.value!="" ||
	document.forms[0].gnum_dept_code.value!="-1" ||
	document.forms[0].fromDate.value!="" ||
	document.forms[0].toDate.value!="" ||
	document.forms[0].hrgnum_puk.value!="" ||
	document.forms[0].gnum_country_code.value!="-1" ||
	document.forms[0].gnum_state_code.value!="-1" ||
	document.forms[0].hrgstr_state_name.value!="" ||
	document.forms[0].gnum_city_loc_code.value!="-1" ||
	document.forms[0].hrgstr_city_location.value!="" ||
	document.forms[0].hrgstr_house_no.value!="" ||
	document.forms[0].hrgstr_street_no.value!="" ||
	document.forms[0].hrgstr_district.value!="" ||
	document.forms[0].hrgstr_city.value!="" ||
	document.forms[0].hrgnum_pincode.value!="" ||
	document.forms[0].num_dist_id.value!="" ||
	document.forms[0].hrgstr_contact_no.value!="" )
	{
		//valid=true;
		if(validateDate())
		{
			//console.log("crNoSearchCriteria :"+crNoSearchCriteria());
			//console.log("dateRangeSearchCriteria :"+dateRangeSearchCriteria());
			//console.log("patNameSearchCriteria :"+patNameSearchCriteria());
			if(crNoSearchCriteria() || dateRangeSearchCriteria() || patNameSearchCriteria()){
				if(patNameSearchCriteria()){
					if(document.getElementsByName("gnum_gender_code")[0].value=="-1"){
						alert("Kindly Select Gender with Patient Name");
						valid=false;
						return false;
					}
					else
					{
						var fname=document.getElementsByName("hrgstr_fname")[0].value;
						var mname=document.getElementsByName("hrgstr_mname")[0].value;
						var lname=document.getElementsByName("hrgstr_lname")[0].value;
						if(fname.length>=3||mname.length>=3||lname.length>=3){
							valid=true;
							submitForm('SEARCH')
						}
						else
						{
							alert("Kindly Enter Atleast 3 characters in Patient Name");
							return false;
						}	
						
					}
				}
				valid=true;
				submitForm('SEARCH')
			}else{
				alert("Please fill atleast One Search Criteria From\n"+
						"\t1. Cr No\n"+
						"\t2. Either FromDate or ToDate or both\n"+
						"\t3. Patient Name");
				valid=false;
				
			}
			
		}
	}
	else
	{
		valid=false
		alert("Please Enter Value In Atleast One Field")
	}
	
	return valid
}


function openPopUpView(e,path)
{
	var isCurrentHospitalSearch="0";
	if(document.getElementsByName("isCurrentHospitalSearch")[1].checked)
		isCurrentHospitalSearch="1";
		
	path= path+"&isCurrentHospitalSearch="+isCurrentHospitalSearch;
	openPopup(path,e,300,600);
}


var queryString;

function sendDataForStateChange(obj)
{	
	var stateCode=obj.value;
	queryString='patAddStateCode='+stateCode;
	//alert("queryString = "+queryString)
	if(stateCode!="" && stateCode!='-1')
    {
 	var url="/AHIMS/DistrictListByState";
 	}
 	httpRequestForStateChange("POST",url,true)
 	
}

function httpRequestForStateChange(reqType,url,asynch)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqForStateChange(reqType,url,asynch);
	}
	else
	 if (window.ActiveXObject)
	 {
 		request=new ActiveXObject("Msxml2.XMLHTTP");
		 if (! request)
		 {
		 request=new ActiveXObject("Microsoft.XMLHTTP");
		 }
		 if(request){
 		initReqForStateChange(reqType,url,asynch);
		 }
		else 
		{
		 alert("Your browser does not permit the use of all of this application's features!");
		 }
	 }
	 else
	 {
		 alert("Your browser does not permit the use of all of this application's features!");
	}

}

function initReqForStateChange(reqType,url,isAsynch)
{
 request.onreadystatechange=handleResponseForStateChange;
 request.open(reqType,url,isAsynch);
 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
 request.send(queryString);

}

function handleResponseForStateChange()
{
 if(request.readyState == 4)
 {
	 if(request.status == 200)
	 {
	 
	 var responseString=request.responseText
	 document.getElementById("districtCombo").innerHTML=" <select name='hrgstr_district' tabindex='1' style='font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none;  height:20px;width: 115px;'><option value='-1'>Select Value</option>"+responseString+"</select>"
	 document.forms[0].num_dist_id.value="-1"
	 document.forms[0].hrgnum_pincode.value=""
	 document.forms[0].hrgstr_city_location.value=""
	 document.forms[0].hrgnum_is_urban.value="0"
			
	 showLocation()
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 }
 }
}

var queryString;

function sendDataForCityLocation(obj)
{	
	var locationCode=obj.value;
	queryString='patAddCityLocCode='+locationCode;
	if(locationCode!="" && locationCode!='-1')
    {
 	var url="/AHIMS/DetailByCityLocation";
 	}
 	httpRequestForCityLocation("POST",url,true)
 	
}


function httpRequestForCityLocation(reqType,url,asynch)
{
	if(window.XMLHttpRequest)
	{
	 request = new XMLHttpRequest();
	 //alert("request"+request)
	 
 	 initReqForCityLocation(reqType,url,asynch);
	}
	else
	 if (window.ActiveXObject)
	 {
 		request=new ActiveXObject("Msxml2.XMLHTTP");
		 if (! request)
		 {
		 request=new ActiveXObject("Microsoft.XMLHTTP");
		 }
		 if(request){
 		initReqForCityLocation(reqType,url,asynch);
		 }
		else 
		{
		 alert("Your browser does not permit the use of all of this application's features!");
		 }
	 }
	 else
	 {
		 alert("Your browser does not permit the use of all of this application's features!");
	}

}

function initReqForCityLocation(reqType,url,isAsynch)
{
//alert("url"+url)
//alert("reqType"+reqType)
//alert("isAsynch"+isAsynch)
//alert("queryString"+queryString)
 request.onreadystatechange=handleResponseForCityLocation;
 request.open(reqType,url,isAsynch);
 request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
 request.send(queryString);

}

function handleResponseForCityLocation()
{
 if(request.readyState == 4)
 {
	 if(request.status == 200)
	 {
	 
	 var responseString=request.responseText
	 var responseArray=responseString.split('^')
	// alert("responseString"+responseString)
	 	document.forms[0].num_dist_id.value=responseArray[0]
	 	//alert(responseArray[0])
	 	//alert(document.forms[0].hrgstr_district.value)
	 	document.forms[0].hrgnum_pincode.value=responseArray[1]
		document.forms[0].hrgstr_city.value=responseArray[2]
		document.forms[0].hrgnum_is_urban.value=responseArray[3]
		
		//////Setting value in hidden field for employee////////
	
	 }
	 else
	 {
	 alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	 }
 }
}
function validateDepartmentSubmit(hmode)
{
	submitForm(hmode);	
}
function onIsCurrentHospitalSearchChange(objIsCurrHosp){
	var objDeptCode = document.getElementsByName("gnum_dept_code")[0];
	if(document.getElementsByName("isCurrentHospitalSearch")[0].checked){
	//if(objIsCurrHosp.value=="1"){
		objDeptCode.innerHTML=globalDeptHtml;
		document.getElementsByName("hgnum_deptunitcode")[0].innerHTML="<option value='-1'>Select Value</option>";
		document.getElementsByName("hgnum_deptunitcode")[0].disabled=true;
		document.getElementsByName("hgnum_ward_code")[0].value="-1";
		document.getElementsByName("hgnum_ward_code")[0].disabled=true;
		objDeptCode.onchange = "";
	}else{
		objDeptCode.innerHTML=deptHtml;
		objDeptCode.value="-1";
		document.getElementsByName("hgnum_deptunitcode")[0].disabled=false;
		document.getElementsByName("hgnum_ward_code")[0].disabled=false;
		objDeptCode.onchange = "if(this.value!='-1') validateDepartmentSubmit('GETUNIT')";
	}
}
function setOnLoadDtl(){
	//if(flagDeptHtmlSet!="1"){
		deptHtml=document.getElementsByName("gnum_dept_code")[0].innerHTML;
		globalDeptHtml=document.getElementsByName("globalDeptCode")[0].innerHTML;
		flagDeptHtmlSet="1";
	//}
	
}
function crNoSearchCriteria(){
	console.log("inside crNoSearchCriteria()");
	if(document.getElementsByName("hrgnum_puk")[0].value.trim()=="")
		return false;
	else
		return true;
}
function dateRangeSearchCriteria(){
	console.log("inside dateRangeSearchCriteria()");
	var fromDate=document.getElementsByName("fromDate")[0].value;
	var toDate=document.getElementsByName("toDate")[0].value;
	if(fromDate=="" && toDate=="")
		return false;
	else
		return true;
}
function patNameSearchCriteria(){
	console.log("inside patNameSearchCriteria()");

	var fname=document.getElementsByName("hrgstr_fname")[0].value;
	var mname=document.getElementsByName("hrgstr_mname")[0].value;
	var lname=document.getElementsByName("hrgstr_lname")[0].value;
	
	if(fname.trim()=="" && mname.trim()=="" && lname.trim()=="" )
		return false;
	else 
		return true;
}
function validateDate(){
	var fromDate=document.getElementsByName("fromDate")[0];
	var toDate=document.getElementsByName("toDate")[0];
	var sysdate=document.getElementsByName("sysdate")[0];
	if(fromDate.value!="" && toDate.value!=""){
		if(!compareDate(fromDate,toDate,2)){
			alert("From Date cannot be greater than To Date")
			return false;
		}
	}	
	if(!compareDate(fromDate,sysdate,2)){
			alert("From Date cannot be greater than Current Date")
			return false;
	}
	if(!compareDate(toDate,sysdate,2)){
			alert("To Date cannot be greater than Current Date")
			return false;
	}
	return true;
	
}


</script>

<body>
<logic:equal name="inPatientEnquiryFB" property="isDirectCall" value="DIRECT">
	<form action="<his:path src='/enquiry/inPatientEnquiry.cnt'/>"  method="post">
</logic:equal>
<%@page import="registration.*,enquiry.*"%>


<his:TitleTag>
		<his:name>
			<bean:message key="inPatientEnquiry" />
		</his:name>
</his:TitleTag>

<his:SubTitleTag name="In Patient Detail">
<table width="100%">
		<tr>
        	<td>	
            	<div align="right">  
            		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          		   <html:radio name="inPatientEnquiryFB" property="isCurrentHospitalSearch" value="0" onclick="onIsCurrentHospitalSearchChange(this)"></html:radio><b>All</b>&nbsp;
          		   <html:radio name="inPatientEnquiryFB" property="isCurrentHospitalSearch" value="1" onclick="onIsCurrentHospitalSearchChange(this)"></html:radio><b>Current Hospital</b>
          		   </font>
                </div>
             </td>       
		</tr>
	</table>
</his:SubTitleTag>

<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="16%"  class="tdfonthead">
				<div align="right">	   <font color="red">*</font>            
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fname"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="inPatientEnquiryFB"  maxlength="30" size="20" property="hrgstr_fname"  tabindex="1" styleClass ="textbox" onkeypress="return validateAlphabetsOnly(event)" />
	  			</div>
	  		</td>
	  	
	  		<td width="16%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="mname"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="inPatientEnquiryFB"  maxlength="30" size="20" property="hrgstr_mname"  styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" />
	  			</div>
	  		</td>
	  		
	  		<td width="16%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="lname"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="inPatientEnquiryFB"  maxlength="30" size="20" property="hrgstr_lname"  styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" />
	  			</div>
	  		</td>
	  		  
	  	</tr>

		<tr>
			<td width="16%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fatherName"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="inPatientEnquiryFB"  maxlength="60" size="20" property="hrgstr_father_name" styleClass="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" ></html:text>
	  			</div>
	  		</td>
	  		<td width="16%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="husbandName"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
				<html:text name="inPatientEnquiryFB"  maxlength="60" size="20" property="hrgstr_spousename" styleClass="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" ></html:text>
	  			</div>
	  		</td>
	  		<td width="16%"  class="tdfonthead">
				<div align="right">	  <font color="red">*</font>             
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="gender"/></font></div>
	  		</td>
	  		<td width="16%" class="tdfont">
	  			<div align="left">
	  			<html:select name="inPatientEnquiryFB" tabindex="1" property="gnum_gender_code" styleClass="regcbo" >
					<html:option value="-1">Select Value</html:option>
  					<html:options  collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>" property="value" labelProperty="label" />
	  			</html:select>
	  			</div>
	  		</td>
	  		
	  		
		</tr>
		<tr>
			<td width="16%" class="tdfonthead">	  
             	 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                 <bean:message key="department"/></font>
             </td>       
 			 <td width="16%" class="tdfont" >
	 	       <html:select name="inPatientEnquiryFB" tabindex="1" property="gnum_dept_code" styleClass="regcbo" onchange="if(this.value!='-1') validateDepartmentSubmit('GETUNIT')">
                 <html:option value="-1">Select Value</html:option>
	  		   	<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>" property = "value" labelProperty = "label"/>
               </html:select>	
             </td>
             <td width="16%" class="tdfonthead">	  
             	 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                 <bean:message key="unit"/></font>
             </td>       
 			 <td width="16%" class="tdfont" >
	 	       <html:select name="inPatientEnquiryFB" tabindex="1" property="gnum_dept_code" styleClass="regcbo" >
                 <html:option value="-1">Select Value</html:option>
	  		   	<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT %>" property = "value" labelProperty = "label"/>
               </html:select>	
             </td>
             <td width="16%" class="tdfonthead">	  
             	 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                 <bean:message key="ward"/></font>
             </td>       
 			 <td width="16%" class="tdfont" >
	 	       <html:select name="inPatientEnquiryFB" tabindex="1" property="gnum_dept_code" styleClass="regcbo" >
                 <html:option value="-1">Select Value</html:option>
	  		   	<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_WARD_CODE %>" property = "value" labelProperty = "label"/>
               </html:select>	
             </td>
		</tr>
		<tr>
			
			 <td width="16%" class="tdfonthead" >	  
                 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                   Adm From Date
                   </font>
             </td> 
             <td width="16%" class="tdfont" >
	    		<his:date name='fromDate' dateFormate="%d-%b-%Y" />
			 </td>
             <td width="16%" class="tdfonthead" >	  
               <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
               Adm To Date
             </td> 
             <td width="16%" class="tdfont" >
	    		<his:date name='toDate' dateFormate="%d-%b-%Y" />
			</td>
			<td width="16%" class="tdFontHead" >
			<div >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			Admission Within</font>
			</div>
			</td>
	  		<td width="16%" class="tdfont">
		<div >
		<html:select name="inPatientEnquiryFB" property="admissionWithin" styleClass="regcbo" tabindex="1">
			<html:option value="-1">Select Value</html:option>
			<html:option value="7">Last One Week</html:option>
			<html:option value="15">Last 15 Days</html:option>
			<html:option value="30">Last One Month</html:option>
			
		</html:select>
		</div>
		</td>
       	</tr>
		<tr>
		 <td width="16%" class="tdfonthead" >	  
       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                <bean:message key="crNo"/></font>
             </td> 
             <td  width="16%" class="tdfont">
            <div align="left">
				<html:text name="inPatientEnquiryFB"  maxlength="13" size="20" property="hrgnum_puk" styleClass="textbox" tabindex="1" onkeypress="return validateNumeric(event)"  ></html:text>
	  			</div>
	  		</td>
	  		<td width="16%" class="tdFontHead" >
			<div >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="ageRange"/></font>
			</div>
			</td>
	  		<td width="16%" class="tdfont">
		<div >
		<html:select name="inPatientEnquiryFB" property="hrgstr_age" styleClass="regcbo" tabindex="1">
			<html:option value="-1">Select Value</html:option>
			<html:option value="0-1">0-1</html:option>
			<html:option value="1-5">1-5</html:option>
			<html:option value="5-15">5-15</html:option>
			<html:option value="15-25">15-25</html:option>
			<html:option value="25-35">25-35</html:option>
			<html:option value="35-45">35-45</html:option>
			<html:option value="45-55">45-55</html:option>
			<html:option value="55-65">55-65</html:option>
			<html:option value="65-75">65-75</html:option>
			<html:option value="75-85">75-85</html:option>
			<html:option value="85-100">>85</html:option>
			
			
			
		</html:select>
		</div>
		</td>
		<td width="16%" class="tdfonthead" >	  
       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                Admission No
             </td> 
             <td  width="16%" class="tdfont">
            <div align="left">
            	<input type="text" name="admissionNo"   maxlength="13" size="20" tabindex="1" onkeypress="return validateNumeric(event)" />
				
	  			</div>
	  		</td>
		
		</tr>
	</table>
</his:ContentTag>
   <his:SubTitleTagBroad name="Address Detail">
		   <table width="100%">
		   <tr>
             <td>
             	<div align="right">	  
          		   <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                  	<b><bean:message key="country"/></font></b>
                 </div> 	
             </td>       
 			 <td>
 			 	<div align="left">
		 	        <html:select name="inPatientEnquiryFB" tabindex="1" property="gnum_country_code"  styleClass="regcbo" onchange="if(this.value!='-1') showState()">
                 		<html:option value="">Select Value</html:option>
	  		   			<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY %>" property = "value" labelProperty = "label"/>
                    </html:select>
	            </div>        	
             </td>
           	<td>
             	<div id="stateMandotary" style="" align="right">
             		  <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<b><bean:message key="state"/></b>
             		  </font>
             	 </div>
              <div id="stateNotMandotary" style="" align="right">
             		  <font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<b><bean:message key="state"/></b>
             		  </font>
              </div>
             </td>
             <td>     
           		 <div id="divpatAddStateCodeCombo" style="" align="left">                 
                   <html:select name="inPatientEnquiryFB" tabindex="1" property="gnum_state_code" styleClass="regcbo" onchange="if(this.value!='-1') sendDataForStateChange(this)" >
                     <html:option value="-1">Select Value</html:option>
                     <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE %>" property = "value" labelProperty = "label"/>
                   </html:select>    
                   </div> 
                   <div id="divpatAddStateCodeText" style="" align="left">
                   <html:text name="inPatientEnquiryFB" tabindex="1" property="hrgstr_state_name" styleClass="regcbo" >
                   </html:text>
                   </div>                 
             </td>
             </tr>
             </table>
      </his:SubTitleTagBroad>    
             
 <his:ContentTag>
 	<table width="100%"  cellspacing="1" cellpadding="0">         
 	<tr>
					<td class="tdfonthead" width="14%"><bean:message key="hno" /></td>
					<td class="tdfont" width="17%"><html:text
						name="inPatientEnquiryFB"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						property="hrgstr_house_no" tabindex="2" maxlength="15"
						styleClass="textbox" /></td>
					<td class="tdfonthead" width="18%"><bean:message key="street" /></td>
					<td class="tdfont" width="17%"><html:text
						name="inPatientEnquiryFB" property="hrgstr_street_no"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						tabindex="2" maxlength="30" styleClass="textbox" /></td>
					<td class="tdfonthead" width="17%"><bean:message key="location" />
					</td>
					<td class="tdfont" width="17%">
					
					<div id="divpatAddCityLocation"><html:text
						name="inPatientEnquiryFB" tabindex="2" maxlength="50"
						onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
						styleClass="textbox" property="hrgstr_city_location" /></div>
					</td>
				</tr>           
 				<tr style="display: none">   
                  
 
                    <td  class="tdfonthead" width="14%" >
                    <div align="right">
                    	 <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	                     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	                     <bean:message key="address"/>
	                     </font> 
                    </div>                    
                    </td>        
                                
                <td  class="tdfont" width="17%" colspan="3">
                <html:textarea  name="inPatientEnquiryFB" onkeypress="return CheckMaxLength(event,this,100,1);" 
                		property="hrgstr_house_no" tabindex="2" cols="55" rows="2"/>
                		<html:hidden  name="inPatientEnquiryFB" property="hrgstr_street_no" />
                </td>
               
               <%--  
	            <td  class="tdfonthead" width="18%">                    
	            <div align="right">
	            
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="street"/>  
	            </font>
	            </div>                    
	            </td>
	           
	                    
	            <td class="tdfont" width="17%">
	            <html:text  name="inPatientEnquiryFB" property="patAddStreet" onkeypress="return validateAlphaNumericWithDotsOnly(event);" tabindex="2" maxlength="30" styleClass ="textbox"/> 
	            </td>
                  
 			--%>
                  
                 <td  class="tdfonthead" width="17%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="tehsil"/>
	            </font>
	            </div>
             </td>
 
      		 <td  class="tdfont" width="17%">
       
          
                <div id="divpatAddCityLocCode" >                
	            
	            </div>	                    	                    	                    	                    	                    	                    	                    
	      
	            <div id="divpatAddCityLocation" >	                     
				<html:text  name="inPatientEnquiryFB" tabindex="2"  maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event,this);" styleClass="textbox" property="hrgstr_city_location"/>
				</div>
				
			</td>									         
                
                
                
                </tr>
                 
                <tr>
                <td class="tdfonthead" width="14%">
                <div align="right">
                <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="district"/> 
	            </font>
	            </div>
                </td>                     
                
                <td class="tdfont" width="17%">
                <div id="districtCombo">
                <html:select name="inPatientEnquiryFB" tabindex="2" styleClass="regcbo" property="num_dist_id">
				<html:option value="-1">Select Value</html:option>
				<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>" property = "value" labelProperty = "label"/>
	            </html:select>
                </div>
                <div id="districtTextBox" style="display:none">
                
                </div>					
                </td>
                  
                <td  class="tdfonthead" width="18%" ><div align="right">
                 <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="city"/>
	            <bean:message key="slash"/>
	            <bean:message key="village"/>
	            </font>
	            </div>
	            </td>
	            
	            <td class="tdfont" width="17%">
	            <html:text name="inPatientEnquiryFB" tabindex="1" property="hrgstr_city" onchange="isAlpha(this,'City')" onkeypress="return validateAlphabetsWithDotsOnly(event,this);" maxlength="30" styleClass="textbox"/> 
                </td>
                
                <td class="tdfonthead" width="14%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="pin"/>
	            </font> 
	            </div>
                </td>       
                    
                <td  class="tdfont" width="17%">
                <input type="text" id="pintext"  name="hrgnum_pincode" tabindex="2"
                 size="17"  maxlength ="6"  onkeypress="return validateNumeric(event)"  /> 
                  </td>
                                                    
                    
              </tr>
                    
              <tr style="display: none;">
                      
                    <td  class="tdfonthead"  width="17%">
                    <div align="right">
                    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                    <bean:message key="contactNo"/>
                    </font>
                    </div>
                    </td>
             		
                    <td  class="tdfont" width="17%">   
 		            <div >
         		    
                    </div>
                    </td>
                    
             <td width="18%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="areaCategory" /> </font></div>
				</td>

				<td width="18%" class="tdfont"></td> 
             <td class="tdfonthead" width="18%"></td>
             <td class="tdfont" width="18%"></td>    
                  </tr>		
                </table>
   
 </his:ContentTag>
 <his:ButtonToolBarTag>
 		<img class="button" style="cursor:pointer" src="/HIS/hisglobal/images/buttons/btn-search.png" tabindex="1" alt="Search" title="Search" onclick="validatePatientEnquiry();"  onkeypress="if(event.keyCode==13) validatePatientEnquiry();">
 		<logic:equal name="inPatientEnquiryFB" property="isDirectCall" value="DIRECT">
 		<img class="button" src="/HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
 		</logic:equal>
 		<logic:notEqual name="inPatientEnquiryFB" property="isDirectCall" value="DIRECT">
 		<img class="button" src="/HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13)submitDesk('NEW');" tabindex="1" onclick ="submitDesk('NEW');">
 		</logic:notEqual>
	  <his:statusTransactionInProcess>
	  	<logic:equal name="inPatientEnquiryFB" property="isDirectCall" value="DIRECT">
	  	<img class="button" src="/HIS/hisglobal/images/buttons/btn-clr.png" tabindex="1"  style=cursor:pointer onclick ="submitForm('unspecified')" onkeypress="if(event.keyCode==13) submitForm('unspecified');">
	  	</logic:equal>
	  	<logic:equal name="inPatientEnquiryFB" property="isDirectCall" value="DESK">
	  	<img class="button" src="/HIS/hisglobal/images/buttons/btn-clr.png" tabindex="1"  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	  	</logic:equal>
       </his:statusTransactionInProcess>       
 </his:ButtonToolBarTag>
 
 <his:statusTransactionInProcess>
 <strong>Search Result</strong>
 <his:ContentTag>
 <%String address=""; %>
 	<table width="100%"  cellspacing="1" cellpadding="0" >                    
		 <tr>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	           	<bean:message key="crNo"/></b>
	            </font>
	             <a style="">
					<img src="/AHIMS/hisglobal/images/arrow_up.gif" style="cursor: pointer;" alt="Ascending order" onclick="getOrderBy('ORDERBYCRNO','0')" tabindex="1" border="0" height="10" width="10">
				</a>
				<a style="">
						<img src="/AHIMS/hisglobal/images/arrow_down.gif" style="cursor: pointer;" alt="Desccending order" onclick="getOrderBy('ORDERBYCRNO','1')" tabindex="1" border="0" height="10" width="10">
				</a>
	            </div>
             </td>
             <td  class="tdfonthead" width="16%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="patientName"/></b>
	            </font>
	             <a style="">
					<img src="/AHIMS/hisglobal/images/arrow_up.gif" style="cursor: pointer;" alt="Ascending order" onclick="getOrderBy('ORDERBYNAME','0')" tabindex="1" border="0" height="10" width="10">
				</a>
				<a style="">
						<img src="/AHIMS/hisglobal/images/arrow_down.gif" style="cursor: pointer;" alt="Desccending order" onclick="getOrderBy('ORDERBYNAME','1')" tabindex="1" border="0" height="10" width="10">
				</a>
	            </div>
             </td>
<!--             <td  class="tdfonthead" width="10%">-->
<!--	            <div align="center">-->
<!--	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
<!--	            <bean:message key="visitDate"/>-->
<!--	            </font>-->
<!--	            </div>-->
<!--             </td>-->
<!--             <td  class="tdfonthead" width="10%">-->
<!--	            <div align="center">-->
<!--	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
<!--	            <bean:message key="department"/>-->
<!--	            </font>-->
<!--	            </div>-->
<!--             </td>-->
             
             <td  class="tdfonthead" width="8%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	           	 <bean:message key="gender/age"/></b>
	            </font>
	            </div>
             </td>
             
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            	<bean:message key="status"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="fatherSpouseName"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="23%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="address"/></b>
	            </font>
	            </div>
             </td>
              <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            <bean:message key="hospital"/></b>
	            </font>
	            </div>
             </td>
              <td  class="tdfonthead" width="5%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
	            	<bean:message key="detail"/></b>
	            </font>
	            </div>
             </td>
            </tr>
            <logic:present name="<%=enquiryConfig.PATIENT_ENQUIRY_IN_PATIENT_VO %>">            
 			<logic:iterate id="commonEnqVO" name="<%=enquiryConfig.PATIENT_ENQUIRY_IN_PATIENT_VO %>" type="hisglobal.vo.CommonEnquiryVO">
 			<tr>
 			<td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getHrgnum_puk() %>	
	  			</div>
	  		</td>
	  		<td class="tdfont"> 
	  			<div align="center">
	  			<%=commonEnqVO.getHrgstr_fname()+" "+
	  			commonEnqVO.getHrgstr_mname()+" "+
	  			commonEnqVO.getHrgstr_lname()%>	
	  			</div>
	  		</td>
<!--            <td class="tdfont">-->
<!--	  			<div align="center">-->
<!--	  			<%=commonEnqVO.getHrgdt_episode_date() %>	-->
<!--	  			</div>-->
<!--	  		</td>-->
<!--	  		 <td class="tdfont">-->
<!--	  			<div align="center">-->
<!--	  			<%=commonEnqVO.getGstr_dept_name() %>	-->
<!--	  			</div>-->
<!--	  		</td>-->
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getGstr_gender_name()+"/"+commonEnqVO.getHrgstr_age() %>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getHgnum_pat_status_code() %>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<%if(commonEnqVO.getHrgstr_fhname()!=""){ %>
	  			<%=commonEnqVO.getHrgstr_fhname()+"/"+ commonEnqVO.getHrgstr_husname()%>	
	  			<%} %>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			
	  			<% address="";
	  				if(commonEnqVO.getHrgstr_house_no()!="")
	  					address=address+commonEnqVO.getHrgstr_house_no()+",";
	  				if(commonEnqVO.getHrgstr_street_no()!="")
	  					address=address+ commonEnqVO.getHrgstr_street_no()+",";
	  				if(commonEnqVO.getHrgstr_city_location()!="")
	  					address=address+commonEnqVO.getHrgstr_city_location()+",";
	  				if(commonEnqVO.getHrgstr_state_name()!="")
	  					address=address+commonEnqVO.getHrgstr_state_name() +", \n";
	  				if(commonEnqVO.getGstr_countryname()!="")
	  					address=address+commonEnqVO.getGstr_countryname();
	  				if(commonEnqVO.getHrgnum_pincode().equals("0"))
	  				{
	  					address=address;
	  					
	  				}
	  				else
	  					address=address +","+commonEnqVO.getHrgnum_pincode();
	  			%>
	  			<%out.print(address);%>	
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=commonEnqVO.getGstr_hospital_name()%>	
	  			</div>
	  		</td>
	  		<%if(commonEnqVO.getHgnum_pat_status_code().toUpperCase().equals("ADMITTED"))
	  		{
	  			String mode="hrgnum_puk="+commonEnqVO.getHrgnum_puk()+"&hmode=POPUP";	
		  		String path="/AHIMS/enquiry/inPatientEnquiry.cnt?"+ mode;
	  		%>
	  		<td class="tdfont">
	  			<div align="center">
	  			<a style="cursor: pointer;" onclick="openPopUpView(event,'<%=path %>')">View Detail</a>	
	  			
	  			</div>
	  		</td>
	  		<% }else{%>
	  		<td class="tdfont">
	  		
	  		</td>
	  		<%} %>
             </tr>
 		
 		</logic:iterate>
 		</logic:present>
 	</table>
 </his:ContentTag>
 
 </his:statusTransactionInProcess>
 <div id="divGlobalId" style="display: none;">
		<html:select name="inPatientEnquiryFB" tabindex="1" property="globalDeptCode" styleClass="regcbo" >
		    <html:option value="-1">Select Value</html:option>
			<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_GLOBAL_DEPARTMENT%>" property = "value" labelProperty = "label"/>
		 </html:select>	
	</div>
<%String sysdate=(String)WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>

<html:hidden name="inPatientEnquiryFB" property="hmode"/>
<html:hidden name="inPatientEnquiryFB" property="order"/>
<html:hidden name="inPatientEnquiryFB" property="isDirectCall"/>
<html:hidden name="inPatientEnquiryFB" property="fromDateHidden"/>
<html:hidden name="inPatientEnquiryFB" property="toDateHidden"/>
<input type="hidden" name="sysdate" value="<%=sysdate %>"/>

<logic:equal name="inPatientEnquiryFB" property="isDirectCall" value="DIRECT">
	</form>
</logic:equal>
<his:status/>
</body>
</html>
<%}catch(Exception e)
{
System.out.println(e.getMessage());	
}%>