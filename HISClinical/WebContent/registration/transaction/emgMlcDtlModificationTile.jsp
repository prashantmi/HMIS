<%try{ %>
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,java.lang.*,registration.*,hisglobal.vo.*" %>
<%@ page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/tab.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script><!--

function validatePercentage(obj){
var flag=true;

if(obj.value > 100){
	alert("Burn Percentage Cannot be Greater than 100 Percent");
	document.getElementsByName("burnPercentage")[0].focus();
	
	flag=false;
	}
	
	
	return flag;
}

function callThisOnload()
{
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
 	broughtByPolice();
 	broughtBy(document.getElementsByName("isBroughtBy")[0]);
	enableRelation(document.getElementsByName("isRelative")[0])
	enableBroughtBy();
	DutyOfficerDetail();
	fitUnfit();
	
	 
	
}

function enableRelation(obj)
{	
	if(obj.value=="1")
	{
		document.getElementsByName("broughtByRelationCode")[0].disabled=false;
		document.getElementById("policeBroughtById").style.display="none";
	}
	else
	{
		if(obj.value=="2")
		{
			document.getElementsByName("broughtByRelationCode")[0].disabled=true;
			document.getElementById("policeBroughtById").style.display="block";
		}
		else
		{
			document.getElementsByName("broughtByRelationCode")[0].disabled=true;
			document.getElementById("policeBroughtById").style.display="none";
		}
	}	
}

function broughtBy(obj)
{
	if(obj.checked==true)
		document.getElementById("broughtById").style.display="";
	else
		document.getElementById("broughtById").style.display="none";
}

function submitForSelectedMlcNo(){
	submitForm("GETPATDTLFORMLC");
}

	function submitTile(mode){
		//alert("In submitTitle:  "+mode);
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
}
function showSearchType(elem)
{
// alert("change crno "+document.getElementsByName("patCrNo")[0].value);
if(elem.value=="-1"){
alert("Please Select MLC No.");
}
else if(elem.value=="0"){
document.getElementById("divMlcNo").style.display="none";
document.getElementById("divcrno").style.display="block";
/*document.getElementsByName("crNo1")[0].value="";
document.getElementsByName("crNo2")[0].value="";
document.getElementsByName("crNo3")[0].value=""; */
document.getElementsByName("crNo1")[0].focus(); 
}
else if(elem.value=="1"){
document.getElementById("divcrno").style.display="none";
document.getElementById("divMlcNo").style.display="block";
	document.getElementsByName("mlcNo")[0].value="";
	document.getElementsByName("mlcNo")[0].focus();
}
}

function showdivEmployee()
{
//alert("show emp");
document.getElementById("divCmoName").style.display="none";
document.getElementById("divLabelcmoName").style.display="";
//document.getElementById("divemployeeLabelcmoCode").style.display="";
document.getElementById("divemployeeCmoCode").style.display="";

}


function showdivnonemployee(){
document.getElementById("divCmoName").style.display="";
document.getElementById("divLabelcmoName").style.display="";
//document.getElementById("divemployeeLabelcmoCode").style.display="none";
document.getElementById("divemployeeCmoCode").style.display="none";
}



function validateCRMLCNo(size){
var valid=true;
if(document.getElementsByName("retrievalType")[0].value=="0"){
if(validateCRNoCHECK(size))
valid=true;
else
valid=false;  
}
else if(document.getElementsByName("retrievalType")[0].value=="1"){
if(validateMLCNO())
valid=true;
else
valid=false;  
}

return valid;
}

function validateMLCNO(){
//alert("mlc");
var valid=true;
if(document.getElementsByName("mlcNo")[0].value==""){
//alert('<bean:message key="validationMLC"/>');
document.getElementsByName("mlcNo")[0].focus();
valid=false;
}
else
{
}

return valid;
}

function doHomeWork(){
//alert("home word");
document.getElementsByName("retrievalType").disabled=false;
}

function populate(e){ 


document.getElementsByName('crNoToRetrieve')[0].value=e;

submitForm("DGNDETAIL"); 

} 


function validateMLC()
{ 
	 	
var valid=true;

    if(
    	validatePercentage(document.getElementsByName("burnPercentage")[0]) &&
    	validatePoliceVerification() && validateFitnessDate() && validateBroughtBy())
    	valid=true;
    else
    	valid=false;	
   	
document.getElementsByName("mlcType")[0].value=document.getElementsByName("isBroughtByPolice")[0].value;
return valid;

} 

function DutyOfficerDetail()
{
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		document.getElementById('dutyOfficerDetailId').style.display="block";
	}
	else
	{
		document.getElementById('dutyOfficerDetailId').style.display="none";
	}
}

function enableBroughtBy()
{
	if(document.getElementsByName("isBroughtByPolice")[0].checked)
	{
		document.getElementsByName("isBroughtByPolice")[0].value="1";
		document.getElementsByName("isBroughtBy")[0].checked=true;
		document.getElementsByName("isRelative")[0].value="2"
		document.getElementsByName("isRelative")[0].disabled=true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0]);
	}
	else
	{
		document.getElementsByName("isBroughtByPolice")[0].value="0";
		document.getElementsByName("isBroughtBy")[0].checked=false;
		//document.getElementsByName("isRelative")[0].value="-1"
		document.getElementsByName("isRelative")[0].disabled=false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0]);
	}
}

function broughtByPolice()
{
	if(document.getElementsByName("broughtByPoliceValue")[0].value=="1")
	{
		document.getElementsByName("isBroughtByPolice")[0].checked=true;
		document.getElementsByName("isBroughtByPolice")[0].disabled=true;
		document.getElementsByName("isBroughtBy")[0].checked=true;
		//document.getElementsByName("isBroughtBy")[0].disabled=true;
	}
	
}

function fitUnfit()
{
	if(document.getElementsByName("fitnessStatus")[0].checked)
	{
		document.getElementsByName("fitnessStatus")[0].disabled=true;
		document.getElementsByName("fitnessStatus")[1].disabled=true;
	}	
}

function showFitDateTime()
{
	if(document.getElementsByName("fitnessStatus")[1].checked)
	{
		document.getElementById("fitnessDateTimeDiv").style.display="none"
		document.getElementsByName("changedToFit")[0].value='0'
	}	
	else
	{
		document.getElementById("fitnessDateTimeDiv").style.display="block"
		document.getElementsByName("changedToFit")[0].value='1'
	}
}

function onblurTimeHourCheck(_hObj)
{
	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);
	// Setting Hour
	if(hh<10)	_hObj.value="0"+hh;
	else			_hObj.value=hh;
}


function onblurTimeMinCheck(_mObj)
{
	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);
	// Setting Minutes
	if(mm<10)	_mObj.value="0"+mm;
	else			_mObj.value=mm;
}

function onkeyTimeHour(_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+hh);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38) // on Arrow press Down-40 Up-38
	{
		if(k==40)		hh=(hh+1)%24;
		else if(k==38)	hh=(24+hh-1)%24;

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;
	}
	if(k!=0)	return true;
	else		return false;
}

function onkeyTimeMin(_mObj,_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+mm);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38)	// on Arrow press Down-40 Up-38
	{
		if(k==40)
		{
			if(mm==59)	hh=(hh+1)%24;
			mm=(mm+1)%60;
		}
		else if(k==38)
		{
			if(mm==0)	hh=(24+hh-1)%24;
			mm=(60+mm-1)%60;
		}

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;

		// Setting Minutes
		if(mm<10) _mObj.value="0"+mm;
		else		_mObj.value=mm;
	}
	if(k!=0)	return true;
	else		return false;
}

function addPatientCondition(event,id)
{
	var path='/HISClinical/opd/casuality/casualmlcDtl.cnt?hmode=ADDPATCONDITION&processId='+id;
	openPopup(path,event,300,600);
}


function validatePoliceVerification()
{
var valid=true
var verificationRequired=<%=Config.MLC_DETAIL_POLICE_VERIFICATION_REQUIRED %>
var verificationRequiredYes=<%=Config.MLC_DETAIL_POLICE_VERIFICATION_REQUIRED_YES %>
if(verificationRequired==verificationRequiredYes)
{
		if(document.getElementsByName('isBroughtByPolice')[0].checked==true)
			{
		
				if(validateObjects(document.forms[0].caseNo,"Case Number") &&
				validateObjects(document.forms[0].policeStation,"Police Station") &&
				validateObjects(document.forms[0].docketNo,"Docket No") &&
				validateObjects(document.forms[0].officerIncharge,"Investigating Ofiicer Name") &&
				validateObjects(document.forms[0].ioDesignation,"Investigating Ofiicer Designation") &&
				validateObjects(document.forms[0].ioBatchNo,"Investigating Ofiicer Badge No") &&
				validateDutyDetail()&& 
				validateObjects(document.forms[0].caseRemarks,"Case Remark"))
				{
					valid= true;
				}	
				else
				{
					valid= false;
				}
			}
}	

	return valid
		
}

function validateDutyDetail()
{
	valid=true;
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		if(validateObjects(document.forms[0].dutyOffName,"Duty Officer Name") &&
			validateObjects(document.forms[0].dutyOffDesignation,"Duty Officer Designation") &&
			validateObjects(document.forms[0].dutyOffBatchNo,"Duty Officer Badge No"))
			{
				valid=true;
			}
			else
			{
				valid=false;
			}
	}
	else
	{
		valid=true;
	}
	return valid;
}

function validateFitnessDate()
{
	valid=true;
	if(document.getElementsByName('changedToFit')[0].value=='1')
	{
	var days=0;
	var a=document.getElementsByName("fitDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var b=document.getElementsByName("sysDate")[0].value; 
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    
    days=((bdate-adate)/86400000);
    if(days==0)
    {
    	valid=checkFitnessTime();
    }
    else
    {
    	if(days<0)
    	{
    		alert("Fitness Date cannot be Greater than Today");
    		valid=false;
    	}
    	else
    	{
    		alert("OK");
    		valid=true;
    	}
    }
    }
    
    return valid;
	
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function checkFitnessTime()
{
	valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	
	if(document.getElementsByName("fitTimeHr")[0].value=="")
	{
		alert("Please Enter the Fitness Hour");
		document.getElementsByName("fitTimeHr")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("fitTimeHr")[0].value)) > hour)
	{
		alert("Hour Cannot be Greater than "+ hour);
		document.getElementsByName("fitTimeHr")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("fitTimeMin")[0].value=="")
	{
		alert("Please Enter the Fitness Minute");
		document.getElementsByName("fitTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("fitTimeMin")[0].value)) > parseInt("59"))
	{
		alert("Minute Cannot be Greater than 59");
		document.getElementsByName("fitTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("fitTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("fitTimeMin")[0].value)) > min)
	{
		alert("Minute Cannot be Greater than "+min);
		document.getElementsByName("fitTimeMin")[0].focus();
		valid=false;
	}
	return valid;
}

function validateBroughtBy()
{
 	if(document.forms[0].isBroughtBy.checked)
 	{
	 	if(comboValidation(document.forms[0].isRelative,"Brought By") &&
	 	isEmpty(document.getElementsByName('broughtByName')[0],'Brought By Name') &&
	 	isEmpty(document.getElementsByName('broughtByLocation')[0],'Brought From Location') && 
	 	validateBroughtByPolice())
	 	{
	 		return true
	 	}
	 	else
	 	{
	 		return false
	 	}
	 }
	 else
	 {
	 	return true
	 }
 }
 
function validateBroughtByPolice()
 {
 	valid=true
 	if(document.forms[0].isRelative.value== <%=RegistrationConfig.PATIENT_BROUGHT_BY_POLICE %>)
 	{
 		if(isEmpty(document.getElementsByName('constableDesig')[0],'Designation' ) &&
 		isEmpty(document.getElementsByName('constableBadgeNo')[0],'Batch No.'))
 		{
 			valid=true
 		}
 		else
 		{
 			valid=false
 		}
 	}
 	return valid
 } 
--></script>

<%
System.out.println("inside mlc mod tile");
%>
<%boolean statusnew=true;
String varStatus="";%>
<his:statusNew>
<%
statusnew =false;
%>
</his:statusNew>
<his:statusUnsuccessfull>
<%
System.out.println("inside mlc mod tile");
statusnew =false;
%>
</his:statusUnsuccessfull>
<%

String st= (String)WebUTIL.getSession(request).getAttribute("SYSDATE");
int ln=st.length();
String year=st.substring(+8);
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:TitleTag name="MLC Details Updation">


<b>
<font  size="2" face="Verdana, Arial, Helvetica, sans-serif">

</font>
</b>    
</his:TitleTag>
<%if(statusnew==false) {%>
	<table width = "100%"  border="0" cellpadding="0" cellspacing="1" style="border-top:1px solid #003366;"> 

	<tr>
	 <td width="25%" class="tdfonthead"><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="selectseacrhType"/></font></b>
     </td>
     
	 <td width="17%"  class ="tdfont">      		 		 		 
        <html:select name="EmgMlcDtlModificationFB" disabled="<%=statusnew%>" property="retrievalType" tabindex="1" onchange="showSearchType(this)">
           <html:option value="-1">Select Value</html:option>
           <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_CRMLCNO %>" property = "value" labelProperty = "label" />
        </html:select>        
      </td> 
      
      <logic:notEmpty name="EmgMlcDtlModificationFB" property ="retrievalType">
           <bean:define name="EmgMlcDtlModificationFB" property ="retrievalType" id="searchType" type="java.lang.String"/>
      </logic:notEmpty>    
           <%
     	          String divmlcdisp="none";
                  String divcrnodisp="";
	        %>
	        <logic:equal name="searchType" value="1">	          
	          <%
	            divmlcdisp="\'\'";
	            divcrnodisp="none";
	          %>
	          </logic:equal>
		 
		 <td width="33%" class="tdfont">	
    		<div id="divcrno" style='display:<%=divcrnodisp%>'>	    		
	    		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	                   <bean:message key="crNo"/></font>
	                   <%String size=Integer.toString(Integer.parseInt(Config.CR_NO_FORMAT_SIZE)+2); %>
			   <input type="text"  name="patCrNo"  maxlength="<%=Config.CR_NO_FORMAT_SIZE%>" size ="<%=size%>" value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateCRNoCHECK('<%=Config.CR_NO_FORMAT_SIZE%>'),'GETPATDTL');  return validateNumeric(event);" tabindex="1" > 
			   
			   	           
	       </div>        	  
           
           <div id="divMlcNo" style='display:<%=divmlcdisp%>' property="mlcNo" >
  		   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
    	   <bean:message key="mlcnumber"/></font>
	       <html:text name="EmgMlcDtlModificationFB" property="mlcNo" maxlength="10" tabindex="1" readonly="<%=statusnew %>" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateCRMLCNo('<%=Config.CR_NO_FORMAT_SIZE%>'),'GETPATDTL');"/>
           </div>                     
	       </td>
		   
		   <td width="25%" class="tdfont">               
	       <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' style=cursor:pointer onclick="submitFormOnValidate(validateCRMLCNo('<%=Config.CR_NO_FORMAT_SIZE%>'),'GETPATDTL');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateCRMLCNo('<%=Config.CR_NO_FORMAT_SIZE%>'),'GETPATDTL');" size = '7' tabindex="1"/>
		   </td>       			
		   </tr>
	
	
	</table>
	<%} %>
	<his:statusList>
	<his:ContentTag>
	<logic:equal name="EmgMlcDtlModificationFB" property="selectedListType" value="0">
		<table id="mlcNoListId"  width = "100%"  border="0" cellpadding="0" cellspacing="1" style="border-top:1px solid #003366;">
		<tr>
			<td width="20%" class="tdfonthead">	
			  <div align="right" >
  		   		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
    	   		<bean:message key="selectMlcNo"/></font></div>
    	   </td>
    	  <td width="20%"  class="tdfont">
					<div align="left">	           
					<html:select name="EmgMlcDtlModificationFB" property="selectedMlcNo" tabindex="1"  >
					<html:option value="-1">Select Value</html:option>
					<html:options collection ="<%=RegistrationConfig.MLC_NO_LIST%>" property = "value" labelProperty = "label"/>
					</html:select>
					</div>
	  				</td>  
	  	   <td width="25%" class="tdfont">               
	      	 <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' style=cursor:pointer onclick="submitFormOnValidate(true,'GETPATDTLFORMLC');" onkeypress="if(event.keyCode==13) submitFormOnValidate(true,'GETPATDTLFORMLC');" size = '7' tabindex="1"/>
		   </td>     
		</tr>
		</table>
		</logic:equal>
		<logic:equal name="EmgMlcDtlModificationFB" property="selectedListType" value="1">
		<table id="crNoListId"  width = "100%"  border="0" cellpadding="0" cellspacing="1" style="border-top:1px solid #003366;">
		<tr>
			<td width="20%" class="tdfonthead">	
			  <div align="right" >
  		   		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
    	   		<bean:message key="selectCrNo"/></font></div>
    	   </td>
    	  <td width="20%"  class="tdfont">
					<div align="left">	           
					<html:select name="EmgMlcDtlModificationFB" property="selectedPatCrNo" tabindex="1"  >
					<html:option value="-1">Select Value</html:option>
					<html:options collection ="<%=RegistrationConfig.CR_NO_LIST%>" property = "value" labelProperty = "label"/>
					</html:select>
					</div>
	  				</td>  
	  	   <td width="25%" class="tdfont">               
	      	 <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' style=cursor:pointer onclick="submitFormOnValidate(true,'GETPATDTLFORCRNO');" onkeypress="if(event.keyCode==13) submitFormOnValidate(true,'GETPATDTLFORCRNO');" size = '7' tabindex="1"/>
		   </td>     
		</tr>
		</table>
		</logic:equal>
	</his:ContentTag>
	</his:statusList>
	
	<div id='mlcdetails'>
<bean:define id="crNo" name="EmgMlcDtlModificationFB" property="patCrNo" type="java.lang.String"/>
<bean:define id="mlcNo" name="EmgMlcDtlModificationFB" property="mlcNo" type="java.lang.String"/>
<%if(!crNo.trim().equals("")||(!mlcNo.trim().equals("")) ){%>	
	
	<his:statusInProcessWithJsp>			
	  <jsp:include page="/registration/patientDetail.cnt" flush="true" />	
	  
	  <bean:define name="EmgMlcDtlModificationFB" property="fitDate" id="fitDate" type="java.lang.String" />
	  <%
		if(fitDate==null||fitDate.equalsIgnoreCase(""))
		{
			fitDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
	%>
    <his:SubTitleTag name="MLC Details">
    	<table width="100%">
			<tr>
				<td>	  
	            	<div width="75%" align="right">		
						<html:checkbox name="EmgMlcDtlModificationFB" property="isBroughtByPolice" onclick="enableBroughtBy()" tabindex="1"></html:checkbox>
						<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>
								<bean:message key="broughtByPolice" />
							</b>	 
						</font>
					</div>
	            </td>       
	 		</tr>
	    </table>
    </his:SubTitleTag>
    
    
    <his:ContentTag>
     <table width="100%" cellspacing="1" cellpadding="0">
     <%varStatus="InProcess"; %>
      <tr>
          <td width="25%" class="tdfonthead">
          	<div align="right">
	           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
               <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	         	  <bean:message key="mlcnumber"/>     
	           </font>
	         </div>  
          </td>
                        
        <td width="25%" class="tdfont">        
      	  <html:text name="EmgMlcDtlModificationFB" property="patMlcNo" styleClass="textbox" maxlength="20" tabindex="1" readonly="true"/>
        </td>
        
         <%
         boolean refer=true;
           System.out.println("getting case resolved");
           String refered= (String)request.getAttribute("refertype");         
           if(refered.equalsIgnoreCase(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
        	   refer= false;
           else
        	   refer= true;        	   
         %>        
        <td class="tdfonthead" width="20%"> 
        	<div align="right">
        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
         		<bean:message key="isreferred"/>
         	</font>
         </div>	
        </td>
         
        <td class="tdfont" width="30%">
        	<html:checkbox name="EmgMlcDtlModificationFB" tabindex="1" property="isTransfer" value="<%=RegistrationConfig.IS_TRANSFER_TRUE%>" disabled="<%=refer%>" tabindex="1"/>		
        </td>     
    </tr>
    <tr>
		<td width="25%" class="tdfonthead">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="mlr" />
				<bean:message key="bookNo" />
			</font>
			</div>
		</td>
		
		<td width="25%" class="tdfont">
			<html:text name="EmgMlcDtlModificationFB" property="bookNo" tabindex="1" maxlength="20" onkeypress="return validateAlphaNumericOnly(event,this)" />
		</td>
		<td width="25%" class="tdfonthead">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="mlr" />
				<bean:message key="pageNo" />
			</font>
			</div>
		</td>
			
		<td width="25%" class="tdfont">
			<html:text name="EmgMlcDtlModificationFB" property="pageNo" tabindex="1" maxlength="4" onkeypress="return validateNumeric(event)" />
		</td>
	</tr>   
		
    <tr> 
    	<td width="25%" class="tdfonthead">
           <div align="right">
            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		    	<bean:message key="mlcDate"/>
		    </font>
		    </div>
        </td> 
               
        <td width="25%" class="tdfont" tabindex="1">  
     	  <html:text name="EmgMlcDtlModificationFB" property="mlcDate" readonly="true"></html:text>
        </td>
        <td width="25%" class="tdfonthead">
			<div align="right">
			<font color="#FF0000">*</font>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="mlcTime" />
			</font>
			</div>
		</td>

		<td width="25%" class="tdfont">
			<html:hidden name="EmgMlcDtlModificationFB" property="mlcTime"/>
			<span>
				<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="mlcTimeHr" maxlength="2" size="3" readonly="true"/>
				<b>	<bean:message key="colon"/></b>
			</span>
			<span>
				<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="mlcTimeMin" maxlength="2" size="3" readonly="true"/>
				<bean:message key="timeFormat"/>
			</span>
		</td>                        
    </tr>
    <tr>
		<td width="25%" class="tdfonthead">
			<div align="right">
			
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="typeOfInjury" />
			</font>
			</div>
		</td>
			
		<td width="25%" class="tdfont">
			<html:select name="EmgMlcDtlModificationFB" property="injuryTypeCode" tabindex="1" styleClass="regcbo" >
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST%>">
				<html:options collection = "<%=RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST%>" property  = "value" labelProperty = "label"/>
				</logic:present>
			</html:select>
		</td>
			
		<td width="25%"  class="tdfonthead">
			<div align="right">
			
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="patStatus" />
			</font>
			</div>
		</td>

		<td width="25%" class="tdfont">
			<html:select name="EmgMlcDtlModificationFB" property="patStatusCode" tabindex="1" styleClass="regcbo" >
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=RegistrationConfig.PATIENT_STATUS_LIST%>">
				<html:options collection = "<%=RegistrationConfig.PATIENT_STATUS_LIST%>" property  = "value" labelProperty = "label"/>
				</logic:present>
			</html:select>
		</td>
	</tr>
	<tr>
		<td width="25%" class="tdfonthead">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="natureInjury" />
			</font>
			</div>
		</td>
				
		<td width="25%" class="tdfont">
			<html:select name="EmgMlcDtlModificationFB" property="injuryNatureCode" tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST%>">
				<html:options collection = "<%=RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST%>" property  = "value" labelProperty = "label"/>
				</logic:present>
			</html:select>
		</td>
			   
	    <td class="tdfonthead" width="25%">
       		<div align="right">
       		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fitToMakeStatement" />
		 	</font>
		 	</div>
		</td>
		<td width="25%" class="tdfont" >
			<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
		        <b>
					<html:radio name="EmgMlcDtlModificationFB" tabindex="1" property="fitnessStatus" value="<%=RegistrationConfig.PATIENT_STATUS_FIT%>" onclick="showFitDateTime()"></html:radio>
					<bean:message key="fit"/>
					<html:radio name="EmgMlcDtlModificationFB" tabindex="1" property="fitnessStatus" value="<%=RegistrationConfig.PATIENT_STATUS_UNFIT%>" onclick="showFitDateTime()"></html:radio>
					<bean:message key="unfit"/>
			    </b>
			</font>
		</td>	
	</tr>
	
	
	<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="typeOfWeapon" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<html:text property="typeOfWeapon" name="EmgMlcDtlModificationFB" tabindex="1" size="40"  maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    <div align="right">
            		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="injurySize" />
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont" >
					<html:text property="injurySize" name="EmgMlcDtlModificationFB" tabindex="1" size="40"  maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				</td>	
			</tr>
			
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="injuryDepth" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					
					<html:text property="injuryDepth" name="EmgMlcDtlModificationFB" tabindex="1" size="40" maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)"/>
					
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    <div align="right">
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="injurySite" />
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont" >
					
					
					<html:select name="EmgMlcDtlModificationFB" property="injurySide" tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIAL_DISEASE_SIDE_LIST%>">
						<html:options collection = "<%=RegistrationConfig.ESSENTIAL_DISEASE_SIDE_LIST%>" property  = "value" labelProperty = "label"/>
						</logic:present>
					</html:select>
					
				</td>	
			</tr>
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="injuryAge" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont" >
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
				        <b>
							<html:radio name="EmgMlcDtlModificationFB" tabindex="1" property="ageOfInjury" value="<%=RegistrationConfig.PATIENT_INJURY_WITHIN_24_HOURS%>"></html:radio>
							<bean:message key="injurywihin24hrs"/>
							<html:radio name="EmgMlcDtlModificationFB" tabindex="1" property="ageOfInjury" value="<%=RegistrationConfig.PATIENT_INJURY_AFTER_24_HOURS%>"></html:radio>
							<bean:message key="injuryafter24hrs"/>
					    </b>
					</font>
				</td>	
			   
			    <td class="tdfonthead" width="25%" align="right">
			    </td>
				<td width="25%" class="tdfont" >	
					
				</td>	
			</tr>
			
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="burnPercentage" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					
					<html:text property="burnPercentage" name="EmgMlcDtlModificationFB" tabindex="1" size="3" maxlength="3" onkeypress="return validateNumeric(event)" onblur="validatePercentage(this)"/>
					
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    <div align="right">
			        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="poisonRemarks" />
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont" >
					<html:text property="poisonRemarks" name="EmgMlcDtlModificationFB" tabindex="1" size="40" maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				</td>	
			</tr>
			
	</table>
	<div id="fitnessDateTimeDiv" style="display: none;">
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr> 
		    	<td width="25%" class="tdfonthead">
		           <div align="right">
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				    	<bean:message key="fitnessDate"/>
				    </font>
				    </div>
		        </td> 
		               
		        <td width="25%" class="tdfont" tabindex="1">  
		     	   <his:date name="fitDate" dateFormate="%d-%b-%Y" value="<%=fitDate%>"></his:date>
		        </td>
		        <td width="25%" class="tdfonthead">
					<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fitTime" />
					</font>
					</div>
				</td>
		
				<td width="25%" class="tdfont">
					<html:hidden name="EmgMlcDtlModificationFB" property="fitTime"/>
					<span>
						<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="fitTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
						<b>	<bean:message key="colon"/></b>
					</span>
					<span>
						<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="fitTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('fitTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
						<bean:message key="timeFormat"/>
					</span>
				</td>                        
		    </tr>
		</table>
	</div>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td   class="tdfonthead" colspan="1">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="patientcondition" />
			</font>
			</div>
		</td>
		<td class="tdfont" colspan="3" >
			<html:textarea name="EmgMlcDtlModificationFB" tabindex="1" rows="1" cols="90" property="patCondition" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
			<html:button value=" Add "  property="mybutton" onclick="addPatientCondition(event,5)" style='cursor:pointer'  tabindex='1'/>
		</td>
	</tr>
	<tr>
		<td   class="tdfonthead" colspan="1">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="diagnosis" />
			</font>
			</div>
		</td>
		<td class="tdfont" colspan="3" >
			<html:textarea name="EmgMlcDtlModificationFB" tabindex="1" rows="1" cols="90" property="diagnosis" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
			<html:button value=" Add "  property="mybutton" onclick="addPatientCondition(event,6)" style='cursor:pointer'  tabindex='1'/>
		</td>
	</tr>
			
	<tr>
		<td width="25%" class="tdfonthead" nowrap="nowrap">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referDoctor" />
			</font>
			</div>
		</td>
		<td width="25%" class="tdfont">
			<html:select name="EmgMlcDtlModificationFB" property="referDoctorCode" tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<logic:notEmpty name="<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>">
				<html:options collection = "<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>" property  = "value" labelProperty = "label"/>
				</logic:notEmpty>
			</html:select>
		</td>
		<td width="25%" class="tdfonthead">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referredRemarks" />
			</font>
			</div>
		</td>
		<td class="tdfont" width="25%">
			<html:textarea name="EmgMlcDtlModificationFB" tabindex="1" property="referDocotorRemarks" 
				onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" 
				style="width: 190px;font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px;"  />
		</td>
	</tr>
      
      <bean:define name="EmgMlcDtlModificationFB" property="cmoType" id="employeeType"/>
      <%      
         String divemployee="";  
         String divnonemployee="";  
         String divcmocodeabel="";
         String divcmonameabel="";
         String divcmonameabelnonemp="";
         String divnonemployeename="";
		// String divEmployeeCmoCode="";
		// String divEmployeeCmoName="";
      %>     
       <logic:equal name="employeeType" value="E">  
	       <%
	      
	          divemployee="";  
	          divnonemployee="none";  
	          divcmocodeabel="";
	          divcmonameabel=""; 
	          divcmonameabelnonemp="none";
	          divnonemployeename="none";

	        %>
        </logic:equal> 
                
        <logic:equal name="employeeType" value="N">  
        <%
        
          divemployee="none";  
          divnonemployee="none"; 
          divcmocodeabel="none";
          divcmonameabel="none";
          divcmonameabelnonemp="";
          divnonemployeename="";
          %>
       </logic:equal>
       </table>
       </his:ContentTag>
    
    <logic:equal name="EmgMlcDtlModificationFB" property="isPoliceVerReq" value="<%=Config.MLC_DETAIL_POLICE_VERIFICATION_REQUIRED_YES %>">
	    <his:SubTitleTag name="Police Verification Details">
	    	<table width="100%">
				<tr>
					<td>	  
		            	<div width="75%" align="right">		
							<html:checkbox name="EmgMlcDtlModificationFB" property="isBroughtDead" value="<%=RegistrationConfig.PATIENT_BROUGHT_DEAD_TRUE%>" tabindex="1"></html:checkbox>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b>
									<bean:message key="isbroughtdead" />
								</b>	 
							</font>
						</div>
		            </td>       
		 		</tr>
		    </table>
	    	
	    </his:SubTitleTag>
	    
	    <his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" nowrap="nowrap" class="tdfonthead">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="policecaseno" />
						</font>
						</div>
					</td>
					<td width="25%" nowrap="nowrap" class="tdfont">
						<html:text name="EmgMlcDtlModificationFB" tabindex="1" maxlength="50" styleClass="textboxBig" 
						property="caseNo" onkeypress="return validateAlphaNumericOnly(event,this)" />
					</td>
					<td width="25%" nowrap="nowrap" class="tdfonthead">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="policestation" />
						</font>
						</div>
					</td>
					<td width="25%" nowrap="nowrap" class="tdfont">
						<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="policeStation" maxlength="100" 
						styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" />
					</td>
				</tr>
				<tr>
					<td class="tdfonthead" nowrap="nowrap" width="25%">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="docketNo" />
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%">
						<html:text name="EmgMlcDtlModificationFB" property="docketNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
					</td>
					<td class="tdfonthead" nowrap="nowrap" width="25%">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="officerincharge" />
							<bean:message key="name" />
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%">
						<html:text name="EmgMlcDtlModificationFB" property="officerIncharge" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</td>
				</tr>
				<tr>
					<td class="tdfonthead" nowrap="nowrap" width="25%">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="officerincharge" />
							<bean:message key="designation" />
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%">
						<html:text name="EmgMlcDtlModificationFB" property="ioDesignation" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</td>
					<td class="tdfonthead" nowrap="nowrap" width="25%">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="officerincharge" />
							<bean:message key="batchno" />
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%">
						<html:text name="EmgMlcDtlModificationFB" property="ioBatchNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
					</td>
				</tr>
				
				<tr>
					<td class="tdfonthead" nowrap="nowrap" width="25%">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dutyOfficer" />
							<bean:message key="detail" />
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%" colspan="3">
						<html:radio name="EmgMlcDtlModificationFB" property="dutyOfficeFlag" value="<%=RegistrationConfig.DUTY_OFFICER_IS_IO %>" onclick="DutyOfficerDetail()" tabindex="1"></html:radio>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="io" />
						</font>		
						<html:radio name="EmgMlcDtlModificationFB" property="dutyOfficeFlag" value="<%=RegistrationConfig.DUTY_OFFICER_IS_OTHER %>" onclick="DutyOfficerDetail()" tabindex="1"></html:radio>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="other" />
						</font>
					</td>
				</tr>
			</table>
				
			<div id="dutyOfficerDetailId">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dutyOfficer" />
								<bean:message key="name" />
							</font>
							</div>
						</td>
						<td class="tdfont" nowrap="nowrap" width="25%">
							<html:text name="EmgMlcDtlModificationFB" property="dutyOffName" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
						</td>
						<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dutyOfficer" />
								<bean:message key="designation" />
							</font>
							</div>
						</td>
						<td class="tdfont" nowrap="nowrap" width="25%">
							<html:text name="EmgMlcDtlModificationFB" property="dutyOffDesignation" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" nowrap="nowrap" width="25%">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dutyOfficer" />
								<bean:message key="batchno" />
							</font>
							</div>
						</td>
						<td class="tdfont" nowrap="nowrap" width="25%" colspan="3">
							<html:text name="EmgMlcDtlModificationFB" property="dutyOffBatchNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
						</td>
					
					</tr>
				</table>
			</div>
				
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td colspan="4">
						<table width="100%" cellpadding="0" cellspacing="1">
							<tr>
								<td nowrap="nowrap" class="tdfonthead" width="25%">
									<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="caseremarks" />
									</font>
									</div>
								</td>
				
								<td colspan="2" nowrap="nowrap" width="75%" class="tdfont">
									<html:textarea name="EmgMlcDtlModificationFB" tabindex="1" property="caseRemarks" rows="1" cols="90" 
										onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" tabindex="1"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</his:ContentTag>
    </logic:equal>        

	<his:SubTitleTag name="MLC Handover To Detail"> 
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="name" />
					</font>
					</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="EmgMlcDtlModificationFB" property="handoverOffName" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="designation" />
					</font>
					</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="EmgMlcDtlModificationFB" property="handoverOffDesg" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="batchno" />
					</font>
					</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%" >
					<html:text name="EmgMlcDtlModificationFB" property="handoverOffBadgeNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="handoverdate" />
					</font>
					</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="EmgMlcDtlModificationFB" property="handoverDateTime" readonly="true" ></html:text>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	
	
    <bean:define id="confFlagId" name="<%=Config.EMG_BROUGHT_BY_DETAIL_MLC_FLAG%>" ></bean:define>
	<logic:equal name="confFlagId" value="<%=Config.EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE_TRUE%>">
		<his:SubTitleTag name="">
			<div align="left">
				<html:checkbox name="EmgMlcDtlModificationFB" tabindex="1" value="<%=RegistrationConfig.IS_BROUGHT_BY_TRUE%>" property="isBroughtBy" onclick="broughtBy(this)" />
				Brought By Detail
			</div>
		</his:SubTitleTag>
	

     <div id="broughtById" style="display: none;" >
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="broughtBy" />
						</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<html:select name="EmgMlcDtlModificationFB" property="isRelative" onclick="enableRelation(this)" tabindex="1">
							<html:option value="-1">Select Value</html:option>
							<html:option value="1">Relative</html:option>
							<html:option value="2">Police</html:option>
							<html:option value="0">Others</html:option>
						</html:select>
						<html:hidden name="EmgMlcDtlModificationFB" property="hiddenIsRelative" />
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="realtionship" />
						</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<html:select name="EmgMlcDtlModificationFB" property="broughtByRelationCode" tabindex="1" styleClass="regcbo" disabled="true">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL%>">
							<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" property  = "value" labelProperty = "label"/>
							</logic:present>
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="name" />
						</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="broughtByName" 
							onkeypress="return validateAlphabetsWithDotsOnly(event,this)" styleClass="textboxBig" maxlength="60" />
					</td>
					<td width="25%" class="tdfonthead" >
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="broughtFromLoacation"/>
						</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="broughtByLocation"  maxlength="100" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
					</td>	
				</tr>
			</table>	
	
			<div id="policeBroughtById">
				<table width="100%" cellspacing="1" cellpadding="0">	
					<tr >
						<td width="25%" class="tdfonthead" >
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="designation"/>
							</font>
							</div>
						</td>
						
						<td width="25%" class="tdfont">
							<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="constableDesig"  maxlength="50" 
							styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="batchNo"/> 
							</font>
							</div>
						</td>
		
						<td width="25%" class="tdfont">
							<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="constableBadgeNo" maxlength="50" 
							styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" />
						</td>
					</tr>
				</table>	
			</div>
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr> 
				    	<td width="50%" height="100%">
				       		<table width="100%" cellspacing="1" cellpadding="0">
				       			<tr>
				       			<td width="50%" class="LABEL">
				       				<!--<td width="50%"  style="background-color:#FFEBD5;height:45;font-family: 'MS Sans Serif';	font-size:18px; color:#653232;text-align:right;">
				        				--><div align="right">
				        				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					          				<bean:message key="phone"/>  <bean:message key="no"/>        
					        			</font>
					        			</div>
				       				</td>
				       				<td width="50%" class="CONTROL">
				     				<!--<td width="50%"  style="background-Color:#F1ECE2;height:45;color:#990000;text-align:left;text-transform: capitalize;font-family:'Trebuchet MS';	font-size: 10px;font-weight: bold;font-variant: normal;">
				         				--><html:text name="EmgMlcDtlModificationFB" tabindex="1" property="broughtByPhone" maxlength="30" onkeypress="return validateNumeric(event)" />
				       				</td>
				       			</tr>
				       		</table>
				       	</td>
				       	<td width="50%">
				       		<table width="100%" cellspacing="1" cellpadding="0">
				        		<tr>
				       				<td width="25%"  rowspan="2" class="tdfonthead"><!-- 18 --> 
				          				<div align="right">
				          				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				             				<bean:message key="remarks"/>
				       		 			</font>
				       		 			</div>
				       				</td>
				      				<td colspan="2" width="35%" rowspan="2" class="tdfont" valign="top" ><!-- 3 -->
				            			<html:textarea styleClass="textarea2" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" name="EmgMlcDtlModificationFB" property="broughtByAddress" />
				        			</td>
				        		</tr>
				        	</table>
				        </td>
				    </tr>
				</table>
			</his:ContentTag>
		</div>
	</logic:equal>
	
	
  <%-- <his:ContentTag>  
 
   <bean:define name="EmgMlcDtlModificationFB" property="cmoType" id="employeeType"/> 
    <%
    	String divEmployeeCmoCode="";
	 	String divEmployeeCmoName="";
    %>
       <logic:equal name="employeeType" value="E">  
	       <%
	       divEmployeeCmoName="display:none";
	       divEmployeeCmoCode="";
	        

	        %>
        </logic:equal> 
                
        <logic:equal name="employeeType" value="N">  
        <%
        divEmployeeCmoName="";
	    divEmployeeCmoCode="display:none";
        %>
       </logic:equal>--%>
       <%--
      <table width="100%"> 
      <tr>           
           <td class="tdfont" nowrap width="13%">           	       
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      <bean:message key="employee"/>
		      </font> 
		      <html:radio name="EmgMlcDtlModificationFB" property="cmoType" tabindex="1" value="E" onclick="showdivEmployee(this)"/> 
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      <bean:message key="nonemployee"/></font> 
		      <html:radio name="EmgMlcDtlModificationFB" tabindex="1" property="cmoType" value="N" onclick="showdivnonemployee(this)"/>	       
          </td>
        
        <td class="tdfont">
        <div id='divLabelcmoName' style='display:none' align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		<bean:message key="cmoName"/></font> 
        </div>
        </td>
		
      
        <td class="tdfont">
	    <div id='divCmoName'  style='<%=divEmployeeCmoName%>' align="left">	            
		<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="doctorName" styleClass="textboxBig" maxlength="30" size="20" onblur="isAlpha(this,'First Name')" onkeypress="return validateAlphabetsOnly(event)"/>
		</div>    
		<div id='divemployeeCmoCode'  style='<%=divEmployeeCmoCode%>' align="left">	            
		<html:select
					name="EmgMlcDtlModificationFB" property="cmoCode"
					tabindex="1" styleClass="regcbo" >
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>"
						property="value" labelProperty="label" />
				</html:select>
		</div>    
		</td>
		
	<td class="tdfont">--%>
		<%-- <div id='divemployeeLabelcmoCode' style='display:none' align="right">
         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
		 <bean:message key="cmoCode"/></font> 
         </div> --%>
	<%--	</td>
		
		<td class="tdfont"> --%>
	<%--	<div id='divemployeeCmoCode' style='display:none' align="left">         
    	<html:text name="EmgMlcDtlModificationFB" tabindex="1" property="cmoCode" styleClass="textboxBig" maxlength="10" size="20"/> 
    	</div>	 
		</td>--%>
	<%--
      </tr>
        <logic:notEmpty name="<%=RegistrationConfig.UPLOADED_FILE_AS_ARRAY%>">
		<tr>
		<td class="tdfont">    
		 <bean:message key="viewmlcDoc"/>	  
		 <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' style=cursor:pointer  tabindex='1'   onkeypress="if(event.keyCode==13) openPopup('<his:path src="/enlargedImage.cnt"/>',event)" onclick="openPopup('<his:path src="/enlargedImage.cnt"/>',event)">	  		    
        </td>     
      </tr> 
       </logic:notEmpty>
       
   </table>   
     </his:ContentTag>--%>
          <%
 
         	System.out.println("dt::::::::::::::::");        	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	System.out.println("dt:::::::::"+dt);        	
       // String 	mlcDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
 
%>
     <input type="hidden" name="sysDate" value="<%=fitDate%>"/> 
     <input type="hidden" name="changedToFit" value="0" />
  </his:statusInProcessWithJsp>

  <%
  }

%>
     
<his:ButtonToolBarTag>
   <%if(varStatus.equals("InProcess")){%>

          <div align="center">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13)submitFormOnValidate(validateMLC(),'SAVE');"  tabindex="1" onclick =  "submitFormOnValidate(validateMLC(),'SAVE');" >
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          
       <%} else{ %>
	
		 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">

	  </div>
	  <%} %>
</his:ButtonToolBarTag>
<his:status/>
		<html:hidden name="EmgMlcDtlModificationFB" property="patCrNo" />
	  	<html:hidden name="EmgMlcDtlModificationFB" property="hmode" value="unspecified"/>
	   	<html:hidden name="EmgMlcDtlModificationFB" property="selectedListType"/>
	   	<html:hidden name="EmgMlcDtlModificationFB" property="isPoliceVerReq"/>
   		<html:hidden name="EmgMlcDtlModificationFB" property="broughtByPoliceValue"/>
   		<html:hidden name="EmgMlcDtlModificationFB" property="hiddenTimeHr"/>
   		<html:hidden name="EmgMlcDtlModificationFB" property="hiddenTimeMin"/>
   		<html:hidden name="EmgMlcDtlModificationFB" property="mlcType"/>
   		<html:hidden name="EmgMlcDtlModificationFB" property="serialNo"/>
   		
   
 <%}catch(Exception e)
 {
	 e.printStackTrace();
 }%>  