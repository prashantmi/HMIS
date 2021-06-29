<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!-- 
/**
 * @author CDAC
 */
-->


<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page import="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
		
		<his:css src="/hisglobal/css/Color.css"/>
		<his:css src="/hisglobal/css/master.css"/>
		<his:css src="/hisglobal/css/hisStyle.css"/>
		<his:css src="/hisglobal/css/hisStyleExt.css"/>
		<his:css src="/hisglobal/css/calendar-blue2.css"/>
		
		<his:javascript src="/registration/js/popup.js" />
		<his:javascript src="/registration/js/calendar.js" />
		<his:javascript src="/registration/js/registration.js" />
		<his:javascript src="/registration/js/dateFunctions.js" />
		<his:javascript src="/registration/js/validationCalls.js" />
		<his:javascript src="/registration/js/commonFunctions.js" />
		<his:javascript src="/registration/js/validationCommon.js" />
		<his:javascript src="/opd/opdJs/opd.js"/>

</logic:equal>

<script>

function setPreviosValue(elem, evt){
			prevValue = elem.value;
	}  

  function moveToRightBox(elem, evt){
		val=elem.value;
		maxLength =elem.getAttribute('maxlength');
		i=0;
		if(val.length==maxLength){
			for(i=0; i<prevValue.length;i++)
				if(prevValue.charAt(i)!=val.charAt(i))
					break;
		}
		if(i==maxLength-1){
			if(elem.name == 'mlcTimeHr'){
				document.getElementsByName("mlcTimeMin")[0].focus();
			
			}	
		}
	}
		
function validatePercentage(obj){
var flag=true;

if(obj.value > 100){
	alert("Burn Percentage Cannot be Greater than 100 Percent");
	document.getElementsByName("burnPercentage")[0].focus();
	
	flag=false;
	}
	
	
	return flag;
}

function broughtBy(obj)
{
	if(obj){
		if(obj.checked==true)
			document.getElementById("broughtById").style.display="";
		else
			document.getElementById("broughtById").style.display="none";
	}		
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

function submit4image()
{
	submitTile("REFRESHFORIMAGE");
}

function submitTile(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function callThisOnload()
{
	if(document.getElementsByName('patCrNo')[0]){
		document.getElementsByName('patCrNo')[0].focus()
	}
	if(document.getElementsByName('cmoType')[0]!=null)
	{
		document.getElementsByName('cmoType')[0].checked=true;
		showdivEmployee(document.getElementsByName('cmoType')[0]);
	}
	broughtBy(document.getElementsByName("isBroughtBy")[0]);
	enableRelation(document.getElementsByName("isRelative")[0]);
	focusCrNo();		
}

function populate(e)
{
	document.getElementsByName('crNoToRetrieve')[0].value=e;
	submitForm("DGNDETAIL");
}
function validateMlcDateTime()
{
	valid=true;
	if( validateMlcSysDate())
	{
		valid=validateMlcEpiDate();
		
	}
	else
	{
		valid=false;
	}	
	return valid;
}
function validateMLC()
{

	if( !(validateObjects(document.forms[0].patMlcNo,"MLC Number") && validateMlcDate(document.forms[0].mlcDate,"MLC Date")) )		
		return false;
	
	if(!validateMlcDateTime())
	{
		return false;
	}
	
	/*if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Enter Patient MLC Time Hour ...");
		document.getElementsByName("mlcTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Enter Patient MLC Time Minute ...");
		document.getElementsByName("mlcTimeMin")[0].focus();
		return false;
	}*/
	onblurTimeHourCheck(document.getElementsByName("mlcTimeHr")[0]);
	onblurTimeMinCheck(document.getElementsByName("mlcTimeMin")[0]);
	document.getElementsByName("mlcTime")[0].value=document.getElementsByName("mlcTimeHr")[0].value+":"+document.getElementsByName("mlcTimeMin")[0].value;
	
	if(!(validatePercentage(document.getElementsByName("burnPercentage")[0]) &&
		validatePoliceVerification() &&  validateBroughtBy()) )		
		return false;
		
		
		
	return true;
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

function parseInteger(val)
{
	if(val.length<1 || !(/^[0-9]*$/.test(val)))
		return 0;
	do
	{
		if(val.length==1)	return parseInt(val);
		if(/^0/.test(val))	val=val.substring(1);
		else				return parseInt(val);
	} while(true);
}

//onkeypress="return onkeyTimeHour(this,event)"
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

function onblurTimeHourCheck(_hObj)
{
	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);
	// Setting Hour
	if(hh<10)	_hObj.value="0"+hh;
	else			_hObj.value=hh;
}

//onkeypress="return onkeyTimeMin(this,document.getElementsByName('hour')[0],event)"
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

function onblurTimeMinCheck(_mObj)
{
	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);
	// Setting Minutes
	if(mm<10)	_mObj.value="0"+mm;
	else			_mObj.value=mm;
}

function addPatientCondition(event,id)
{
	var path='/HISClinical/opd/casuality/casualmlcDtl.cnt?hmode=ADDPATCONDITION&processId='+id;
	openPopup(path,event,300,600);
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

function handOverDetail()
{
	if(document.getElementsByName("handoverToFlag")[2].checked)
	{
		document.getElementById('handOvereDetailId').style.display="block";
	}
	else
	{
		document.getElementById('handOvereDetailId').style.display="none";
	}
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

function validateHandOverToDetail()
{
	valid=true;
	if(document.getElementsByName("handoverToFlag")[2].checked)
	{
		if(validateObjects(document.forms[0].handoverToName,"Handover To Name") &&
			validateObjects(document.forms[0].handoverToDesignation,"Handover To Designation") &&
			validateObjects(document.forms[0].handoverToBatchNo,"Handover To Badge No"))
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

function enableBroughtBy()
{
	if(document.getElementsByName("isBroughtByPolice")[0].checked)
	{
		document.getElementsByName("isBroughtBy")[0].checked=true;
		document.getElementsByName("isRelative")[0].value="2"
		document.getElementsByName("isRelative")[0].disabled=true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0]);
	}
	else
	{
		document.getElementsByName("isBroughtBy")[0].checked=false;
		document.getElementsByName("isRelative")[0].value="-1"
		document.getElementsByName("isRelative")[0].disabled=false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0]);
	}
}

function validateMlcSysDate()
{
	valid=true;
	var days=0;
	var a=document.getElementsByName("mlcDate")[0].value;
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
    
    var c=document.getElementsByName("epiDate")[0].value; 
    var cArray=c.split("-");
     
    var cday=cArray[0];
    var cmonth=cArray[1];
    var cyear=cArray[2];
    var cdate=new Date(cmonth +" "+ cday+" "+cyear);
      
    
    days=((bdate-adate)/86400000);
    
    if(days==0)
    {
    	valid=checkMlcSysTime();
    }
    else
    {
    	if(days<0)
    	{
    		alert("MLC Date cannot be Greater than Today");
    		valid=false;
    	}
    	else
    	{
    		valid=true;
    	}
    }
    
    
    
    return valid;
}

function validateMlcEpiDate()
{
	valid=true;
	var day=0;
	var a=document.getElementsByName("mlcDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
     var c=document.getElementsByName("epiDate")[0].value; 
    var cArray=c.split("-");
     
    var cday=cArray[0];
    var cmonth=cArray[1];
    var cyear=cArray[2];
    var cdate=new Date(cmonth +" "+ cday+" "+cyear);
    
     day=(adate-cdate)/86400000;
    if(day==0)
    {
    	valid=checkMlcEpiTime();
    }
    else
    {
    	if(day<0)
    	{
    		alert("MLC Date cannot be Less than Patient Visit Date");
    		valid=false;
    	}
    	else
    	{
    		valid=true;
    	}
    }
    
    return valid;
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function checkMlcSysTime()
{
	valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var mlcDate=document.getElementsByName("mlcDate")[0].value;
	var epiDate=document.getElementsByName("epiDate")[0].value; 
	var sysDate=document.getElementsByName("sysDate")[0].value; 
	
	if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Please Enter the MLC Hour");
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) > hour)
	{
		alert("MLC Date Cannot be Greater than "+sysDate+" "+hour+":"+min);
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Please Enter the MLC Minute");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > parseInt("59"))
	{
		alert("Minute Cannot be Greater than 59");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > min)
	{
		alert("MLC Date Cannot be Greater than "+sysDate+" "+hour+":"+ (min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	return valid;
}

function checkMlcEpiTime()
{
	valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("epiTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("epiTimeMin")[0].value));
	var mlcDate=document.getElementsByName("mlcDate")[0].value;
	var epiDate=document.getElementsByName("epiDate")[0].value; 
	var sysDate=document.getElementsByName("sysDate")[0].value; 
	
	if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Please Enter the MLC Hour");
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) < hour)
	{
		alert("MLC Date Cannot be Less than "+epiDate+" "+hour+":"+(min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Please Enter the MLC Minute");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > parseInt("59"))
	{
		alert("Minute Cannot be Greater than 59");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) <= hour && parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) < min)
	{
		alert("MLC Date Cannot be Less than "+epiDate+" "+hour+":"+(min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeMin")[0].focus();
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
</script>

<his:TransactionContainer>
<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
	<form action="/HISClinical/opd/casuality/casualmlcDtl.cnt" method="post">
</logic:equal>

<%
	String varStatus="";
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>

<his:TitleTag name="MLC Details">
<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
	<font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
		<b>
			</b>
	</font>
</logic:equal>
</his:TitleTag>

<his:statusNew>
	<%	
		varStatus="New";
	%>
	<logic:equal name="CsultyEmgMlcDtlFB" property="hmode" value="GETCRNO">
			<his:InputCrNoTag name="CsultyEmgMlcDtlFB"> </his:InputCrNoTag>
	</logic:equal>
</his:statusNew>



<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
<his:statusInProcess>		
		<bean:define id="crNo" name="CsultyEmgMlcDtlFB" property="patCrNo" type="java.lang.String" />
		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
	</his:statusInProcess>
	</logic:equal>
<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DESK">	
	<bean:define id="crNo" name="CsultyEmgMlcDtlFB" property="patCrNo" type="java.lang.String" />
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
</logic:equal>


<his:statusTransactionInProcess>
	<%varStatus="InProcess";%>

	

	<bean:define name="CsultyEmgMlcDtlFB" property="mlcDate" id="mlcDate" type="java.lang.String" />
	
	<his:SubTitleTag name="MLC Details">
		<table width="100%">
			<tr>
				<td>	  
	            	<div width="75%" align="right">		
						<html:checkbox name="CsultyEmgMlcDtlFB" property="isBroughtByPolice" onclick="enableBroughtBy()" tabindex="1"></html:checkbox>
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
			<%varStatus="InProcess";%>
			<tr>
				<td width="15%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="mlcnumber" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<html:text name="CsultyEmgMlcDtlFB" property="patMlcNo" tabindex="1" maxlength="10" onkeypress="return validateAlphaNumericOnly(event,this)" />
				</td>
				
				<td width="35%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="isreferred" />
					</font>
				</div>
				</td>

				<%	
					boolean refer=true;
					EpisodeVO episode= (EpisodeVO)session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);
					if(episode!=null)
					{
						String referred= episode.getEpisodeReferAccept();
						if(referred!=null)
						{
							if(referred.equalsIgnoreCase(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
								refer= false;
							else
								refer= true;
						}
					}
				%>

				<td width="25%" class="tdfont">
					<html:checkbox name="CsultyEmgMlcDtlFB" property="isTransfer" value="<%=RegistrationConfig.IS_TRANSFER_TRUE%>" disabled="<%=refer%>" tabindex="1"/>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="mlr" />
						<bean:message key="bookNo" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<html:text name="CsultyEmgMlcDtlFB" property="bookNo" tabindex="1" maxlength="20" onkeypress="return validateAlphaNumericOnly(event,this)" />
				</td>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="mlr" />
						<bean:message key="pageNo" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<html:text name="CsultyEmgMlcDtlFB" property="pageNo" tabindex="1" maxlength="4" onkeypress="return validateNumeric(event)" />
				</td>
			</tr>
			
			<%
				if(mlcDate==null||mlcDate.equalsIgnoreCase(""))
				{
					mlcDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				}
			%>
			
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="mlcDate" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<his:date name='mlcDate' dateFormate="%d-%b-%Y" value="<%=mlcDate%>" />
				</td>

				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="mlcTime" />
					</font>
				</div>
				</td>

				<td width="25%" class="tdfont">
					<html:hidden name="CsultyEmgMlcDtlFB" property="mlcTime"/>
					<span>
						<html:text name="CsultyEmgMlcDtlFB" tabindex="1" property="mlcTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)"/>
						<b>	<bean:message key="colon"/></b>
					</span>
					<span>
						<html:text name="CsultyEmgMlcDtlFB" tabindex="1" property="mlcTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('mlcTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
						<bean:message key="timeFormat"/>
					</span>
				</td>
			</tr>
			
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="typeOfInjury" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<html:select name="CsultyEmgMlcDtlFB" property="injuryTypeCode" tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST%>">
						<html:options collection = "<%=RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST%>" property  = "value" labelProperty = "label"/>
						</logic:present>
					</html:select>
					
				</td>
				
				<td width="25%"  class="tdfonthead" align="right">
				<div align="right">
					
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patStatus" />
					</font>
				</div>
				</td>

				<td width="25%" class="tdfont">
					<html:select name="CsultyEmgMlcDtlFB" property="patStatusCode" tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.PATIENT_STATUS_LIST%>">
						<html:options collection = "<%=RegistrationConfig.PATIENT_STATUS_LIST%>" property  = "value" labelProperty = "label"/>
						</logic:present>
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="15%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="natureInjury" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<html:select name="CsultyEmgMlcDtlFB" property="injuryNatureCode" tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST%>">
						<html:options collection = "<%=RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST%>" property  = "value" labelProperty = "label"/>
						</logic:present>
					</html:select>
				</td>
			   
			    <td class="tdfonthead" width="35%" align="right">
			    <div align="right">
            		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fitToMakeStatement" />
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont" >
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
				        <b>
							<html:radio name="CsultyEmgMlcDtlFB" tabindex="1" property="fitnessStatus" value="<%=RegistrationConfig.PATIENT_STATUS_FIT%>"></html:radio>
							<bean:message key="fit"/>
							<html:radio name="CsultyEmgMlcDtlFB" tabindex="1" property="fitnessStatus" value="<%=RegistrationConfig.PATIENT_STATUS_UNFIT%>"></html:radio>
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
					<html:text property="typeOfWeapon" name="CsultyEmgMlcDtlFB" tabindex="1" size="40"  maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    <div align="right">
            		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="injurySize" />
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont" >
					<html:text property="injurySize" name="CsultyEmgMlcDtlFB" tabindex="1" size="40"  maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)"/>
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
					
					<html:text property="injuryDepth" name="CsultyEmgMlcDtlFB" tabindex="1" size="40" maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)"/>
					
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    <div align="right">
			   		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="injurySite" />
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont" >
					
					
					<html:select name="CsultyEmgMlcDtlFB" property="injurySide" tabindex="1" styleClass="regcbo">
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
							<html:radio name="CsultyEmgMlcDtlFB" tabindex="1" property="ageOfInjury" value="<%=RegistrationConfig.PATIENT_INJURY_WITHIN_24_HOURS%>"></html:radio>
							<bean:message key="injurywihin24hrs"/>
							<html:radio name="CsultyEmgMlcDtlFB" tabindex="1" property="ageOfInjury" value="<%=RegistrationConfig.PATIENT_INJURY_AFTER_24_HOURS%>"></html:radio>
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
					
					<html:text property="burnPercentage" name="CsultyEmgMlcDtlFB" tabindex="1" size="3" maxlength="3" onkeypress="return validateNumeric(event)" onblur="validatePercentage(this)"/>
					
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    <div align="right">
			   		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="poisonRemarks" />
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont" >
					<html:text property="poisonRemarks" name="CsultyEmgMlcDtlFB" tabindex="1" size="40" maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				</td>	
			</tr>
		</table>	
	
	<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td   class="tdfonthead" colspan="1" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patientcondition" />
					</font>
				</div>
				</td>
				<td class="tdfont" colspan="3" >
					<html:textarea name="CsultyEmgMlcDtlFB" tabindex="1" rows="1" cols="90" property="patCondition" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
					<html:button value=" Add "  property="mybutton" onclick="addPatientCondition(event,5)" style='cursor:pointer'  tabindex='1'/>
				</td>
			</tr>
			<tr>
				<td   class="tdfonthead" colspan="1" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="diagnosis" />
					</font>
				</div>
				</td>
				<td class="tdfont" colspan="3" >
					<html:textarea name="CsultyEmgMlcDtlFB" tabindex="1" rows="1" cols="90" property="diagnosis" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
					<html:button value=" Add "  property="mybutton" onclick="addPatientCondition(event,6)" style='cursor:pointer'  tabindex='1'/>
				</td>
			</tr>
			
			<tr>
				<td width="25%" class="tdfonthead" nowrap="nowrap" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="referDoctor" />
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<html:select name="CsultyEmgMlcDtlFB" property="referDoctorCode" tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<logic:notEmpty name="<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>">
						<html:options collection = "<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>" property  = "value" labelProperty = "label"/>
						</logic:notEmpty>
					</html:select>
				</td>

				<td width="25%" class="tdfonthead" align="right">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="referredRemarks" />
					</font>
					</div>
				</td>

				<td class="tdfont" width="25%">
					<html:textarea name="CsultyEmgMlcDtlFB" tabindex="1" property="referDocotorRemarks" 
						onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" 
						style="width: 190px;font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px;"  />
				</td>
			</tr>
		</table>
	</his:ContentTag>
	
	<logic:equal name="CsultyEmgMlcDtlFB" property="isPoliceVerReq" value="<%=Config.MLC_DETAIL_POLICE_VERIFICATION_REQUIRED_YES %>">
	<his:SubTitleTag name="Police Verification Details">
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" nowrap="nowrap" class="tdfonthead" align="right">
				<div align="right">
					
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="policecaseno" />
					</font>
				</div>
				</td>

				<td width="25%" nowrap="nowrap" class="tdfont">
					<html:text name="CsultyEmgMlcDtlFB" tabindex="1" maxlength="50" styleClass="textboxBig" 
					property="caseNo" onkeypress="return validateAlphaNumericOnly(event,this)" />
				</td>

				<td width="25%" nowrap="nowrap" class="tdfonthead" align="right">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="policestation" />
					</font>
					</div>
				</td>

				<td width="25%" nowrap="nowrap" class="tdfont">
					<html:text name="CsultyEmgMlcDtlFB" tabindex="1" property="policeStation" maxlength="100" 
					styleClass="textboxBig" onkeypress="return validateAlphaNumOnly(this,event)" />
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="docketNo" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="CsultyEmgMlcDtlFB" property="docketNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="officerincharge" />
						<bean:message key="name" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="CsultyEmgMlcDtlFB" property="officerIncharge" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="officerincharge" />
						<bean:message key="designation" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="CsultyEmgMlcDtlFB" property="ioDesignation" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="officerincharge" />
						<bean:message key="batchno" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="CsultyEmgMlcDtlFB" property="ioBatchNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
				</td>
			</tr>
			
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="dutyOfficer" />
						<bean:message key="detail" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%" colspan="3">
					<html:radio name="CsultyEmgMlcDtlFB" property="dutyOfficeFlag" value="<%=RegistrationConfig.DUTY_OFFICER_IS_IO %>" onclick="DutyOfficerDetail()" tabindex="1"></html:radio>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="io" />
					</font>		
					<html:radio name="CsultyEmgMlcDtlFB" property="dutyOfficeFlag" value="<%=RegistrationConfig.DUTY_OFFICER_IS_OTHER %>" onclick="DutyOfficerDetail()" tabindex="1"></html:radio>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="other" />
					</font>
				</td>
			</tr>
		</table>
			
		<div id="dutyOfficerDetailId">
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dutyOfficer" />
							<bean:message key="name" />
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%">
						<html:text name="CsultyEmgMlcDtlFB" property="dutyOffName" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</td>
					<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dutyOfficer" />
							<bean:message key="designation" />
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%">
						<html:text name="CsultyEmgMlcDtlFB" property="dutyOffDesignation" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</td>
				</tr>
				<tr>
					<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dutyOfficer" />
							<bean:message key="batchno" />
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%" colspan="3">
						<html:text name="CsultyEmgMlcDtlFB" property="dutyOffBatchNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
					</td>
				
				</tr>
			</table>
		</div>
		
		
			
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td colspan="4">
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="isbroughtdead" />
								</font>
							</div>
							</td>
			
							<td class="tdfont" nowrap="nowrap" width="75%" >
								<html:checkbox name="CsultyEmgMlcDtlFB" tabindex="1" property="isBroughtDead" value="<%=RegistrationConfig.PATIENT_BROUGHT_DEAD_TRUE%>"/>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="tdfonthead" width="25%" align="right">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="caseremarks" />
								</font>
								</div>
							</td>
			
							<td colspan="2" nowrap="nowrap" width="75%" class="tdfont">
								<html:textarea name="CsultyEmgMlcDtlFB" tabindex="1" property="caseRemarks" rows="1" cols="90" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	</logic:equal>
	
	<his:SubTitleTag name="Handover To Detail"> 
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="name" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="CsultyEmgMlcDtlFB" property="handoverOffName" maxlength="60" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="designation" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<html:text name="CsultyEmgMlcDtlFB" property="handoverOffDesg" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="batchno" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%" >
					<html:text name="CsultyEmgMlcDtlFB" property="handoverOffBadgeNo" maxlength="50" styleClass="textboxBig" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"></html:text>
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="handoverdate" />
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<his:date name="handoverDateTime" dateFormate="%d-%b-%Y" value="<%=mlcDate %>" ></his:date>
				</td>
			</tr>
		</table>
	</his:ContentTag>
				
<!-- Closed by Akash Singh, because HisContextListener is commented on web.xml, dated on 20-july-2015 -->
<%-- 	<bean:define id="confFlagId" name="<%=Config.EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE%>" ></bean:define> 
 --%>	
   <bean:define id="confFlagId" value="1"></bean:define>
 	<logic:equal name="confFlagId" value="<%=Config.EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE_TRUE%>">
		<his:SubTitleTag name="">
			<div align="left">
				<html:checkbox name="CsultyEmgMlcDtlFB" tabindex="1" value="<%=RegistrationConfig.IS_BROUGHT_BY_TRUE%>" property="isBroughtBy" onclick="broughtBy(this)" />
				Brought By Detail
			</div>
		</his:SubTitleTag>
		<div id="broughtById" style="display: none;" >
			<his:ContentTag>
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="broughtBy" />
							</font>
						</div>
						</td>
						
						<td width="25%" class="tdfont">
							<html:select name="CsultyEmgMlcDtlFB" property="isRelative" onclick="enableRelation(this)" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_RELATIVE %>">Relative</html:option>
								<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_POLICE %>">Police</html:option>
								<html:option value="<%=RegistrationConfig.PATIENT_BROUGHT_BY_OTHER %>">Others</html:option>
							</html:select>
						</td>
						
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="realtionship" />
							</font>
						</div>
						</td>
						
						<td width="25%" class="tdfont">
							<html:select name="CsultyEmgMlcDtlFB" property="broughtByRelationCode" tabindex="1" styleClass="regcbo" disabled="true">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL%>">
								<html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" property  = "value" labelProperty = "label"/>
								</logic:present>
							</html:select>
						</td>
					</tr>
					<tr>
						
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="name" />
							</font>
						</div>
						</td>
						<td width="25%" class="tdfont">
							<html:text name="CsultyEmgMlcDtlFB" tabindex="1" property="broughtByName" 
								onkeypress="return validateAlphabetsWithDotsOnly(event,this)" styleClass="textboxBig" maxlength="60"/>
						</td>
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="broughtFromLoacation"/>
							</font>
						</div>
						</td>
						<td width="25%" class="tdfont">
							<html:text name="CsultyEmgMlcDtlFB" tabindex="1" property="broughtByLocation"  maxlength="100" 
							styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>

						</td>
					</tr>
				</table>	
			<div id="policeBroughtById">
				<table width="100%" cellspacing="1" cellpadding="0">	
					<tr >
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="designation"/>
							</font>
						</div>
						</td>
						
						<td width="25%" class="tdfont">
							<html:text name="CsultyEmgMlcDtlFB" tabindex="1" property="constableDesig"  maxlength="50" 
							styleClass="textboxBig" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
						</td>
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="batchNo"/> 
							</font>
							</div>
						</td>
		
						<td width="25%" class="tdfont">
							<html:text name="CsultyEmgMlcDtlFB" tabindex="1" property="constableBadgeNo" maxlength="50" 
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
				       				<!--<td width="50%" align="right"  style="background-color:#FFEBD5;height:45;font-family: 'MS Sans Serif';	font-size:18px; color:#653232;text-align:right;">
				        				--><td width="50%" class="LABEL">
				        				<div align="right">
				        				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					          				<bean:message key="phone"/>  <bean:message key="no"/>        
					        			</font>
					        			</div>
				       				</td>
				       				<td width="50%" class="CONTROL">
				     				<!--<td width="50%"  style="background-Color:#F1ECE2;height:45;color:#990000;text-align:left;text-transform: capitalize;font-family:'Trebuchet MS';	font-size: 10px;font-weight: bold;font-variant: normal;">
				         				--><html:text name="CsultyEmgMlcDtlFB" tabindex="1" property="broughtByPhone" maxlength="30" onkeypress="return validateNumeric(event)" />
				       				</td>
				       			</tr>
				       		</table>
				       	</td>
				       	<td width="50%">
				       		<table width="100%" cellspacing="1" cellpadding="0">
				        		<tr>
				       				<td width="25%"  rowspan="2" class="tdfonthead" align="right"><!-- 18 --> 
				          				<div align="right">
				          				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				             				<bean:message key="remarks"/>
				       		 			</font>
				       		 			</div>
				       				</td>
				      				<td colspan="2" width="35%" rowspan="2" class="tdfont" valign="top" ><!-- 3 -->
				            			<html:textarea styleClass="textarea2" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" name="CsultyEmgMlcDtlFB" property="broughtByAddress" />
				        			</td>
				        		</tr>
				        	</table>
				        </td>
				    </tr>

				</table>
			</his:ContentTag>
		</div>
	</logic:equal>

	
	
	<input type="hidden" name="sysDate" value="<%=mlcDate%>" />
	
</his:statusTransactionInProcess>


<his:ButtonToolBarTag>
	<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
		<%	if(varStatus.equals("InProcess"))	{	%>
			<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateMLC(),'SAVE');" onclick="submitFormOnValidate(validateMLC(),'SAVE');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick="submitForm('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');" tabindex="1">
			</his:statusTransactionInProcess>
		<%	} else {	%>
			<his:statusNew>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick="submitForm('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');" tabindex="1">
			</his:statusNew>
		<%	}	%>
	</logic:equal>
	<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DESK">
		
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateMLC(),'SAVE');" onclick="submitFormOnValidate(validateMLC(),'SAVE');">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" tabindex="1" onclick="submitToDesk('NEW','NEW');">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1">
			</his:statusTransactionInProcess>
		
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" tabindex="1" onclick="submitToDesk('NEW','NEW');">
			</his:statusUnsuccessfull>
			
			<his:statusDone>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" tabindex="1" onclick="submitToDesk('NEW','NEW');">
			</his:statusDone>
	</logic:equal>
	
</his:ButtonToolBarTag>

<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
	<his:status/>
</logic:equal>

</his:TransactionContainer>
<html:hidden name="CsultyEmgMlcDtlFB" property="hmode"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="patCrNo"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="episodeCode"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="episodeVisitNo"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="isDirectCall"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="isPoliceVerReq"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="hiddenTimeHr"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="hiddenTimeMin"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="epiDate"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="epiTimeHr"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="epiTimeMin"/>

<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
</form>	
</logic:equal>