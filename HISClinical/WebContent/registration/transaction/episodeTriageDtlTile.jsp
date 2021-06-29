<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@ page import="java.util.*,registration.*,hisglobal.presentation.WebUTIL,hisglobal.utility.Entry"%>


<%@page import="hisglobal.hisconfig.Config"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DIRECT">
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
		<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
</logic:equal>

<script>

function validateModifyForm()
{
	if(!modValidate())
		return false;
	if(document.getElementsByName("remarks")[0].value=="")
	{
		alert("Enter Summary ...");
		document.getElementsByName("remarks")[0].focus();
		return false;
	}
	return true;
}

function modValidate()
{
	onblurTimeHourCheck(document.getElementsByName("outTimeHr")[0]);
	onblurTimeMinCheck(document.getElementsByName("outTimeMin")[0]);
	document.getElementsByName("outTime")[0].value=document.getElementsByName("outTimeHr")[0].value+":"+document.getElementsByName("outTimeMin")[0].value;
	
	if(document.getElementsByName("outDate")[0].value=="")
	{
		alert("Enter Patient Out Date ...");
		document.getElementsByName("outDate")[0].focus();
		return false;
	}
	
	var indate=document.getElementsByName("inDate")[0].value;
	var outdate=document.getElementsByName("outDate")[0].value;
	var curr=document.getElementById("currentDateStr").value;
	  if(!compareDateCall(indate,outdate,2,"Patient Entry Date","Patient Out Date"))
		  {
		 // alert("false value");
		  		return false;
		  }
	  else {
		  //alert("else loop");
		  	if(!compareDateCall(outdate,curr,2,"Patient Out Date","Current Date"))
			return false;  
	  }

			/* var objFrmDate =  convertStrToDate(indate, "dd-Mon-yyyy");
			alert(objFrmDate);
			var objToDate =  convertStrToDate(outdate, "dd-Mon-yyyy");
			alert(objToDate);

			if(objFrmDate<=objToDate)
				return false; */
	
	/* // Out Time
	 if(document.getElementsByName("outTimeHr")[0].value=="")
	{
		alert("Enter Patient Out Time Hour ...");
		document.getElementsByName("outTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("outTimeMin")[0].value=="")
	{
		alert("Enter Patient Out Time Minute ...");
		document.getElementsByName("outTimeMin")[0].focus();
		return false;
	}

	var outHr=parseInteger(document.getElementsByName("outTimeHr")[0].value);
	var outMin=parseInteger(document.getElementsByName("outTimeMin")[0].value);
	
	// Compare Out Time with In Time
	if(indate.value==outdate.value)
	{
		var inHr=parseInteger(document.getElementsByName("inTimeHr")[0].value);
		var inMin=parseInteger(document.getElementsByName("inTimeMin")[0].value);
		if(outHr<inHr)
		{
			alert("Out Time should be greater than Entry Time...");
			document.getElementsByName("outTimeHr")[0].focus();
			return false;
		}
		else if(outHr==inHr && outMin<=inMin)
		{
			alert("Out Time should be greater than Entry Time...");
			document.getElementsByName("outTimeMin")[0].focus();
			return false;
		}
	}	
 
	// Compare Out Time with Systime
	if(outdate.value==curr.value)
	{
		var curHr=parseInteger(document.getElementById("curHr").value);
		var curMin=parseInteger(document.getElementById("curMin").value);
		if(outHr>curHr)
		{
			alert("Out Time can't be greater than Current Time...");
			document.getElementsByName("outTimeHr")[0].focus();
			return false;
		}
		else if(outHr==curHr && outMin>curMin)
		{
			alert("Out Time can't be greater than Current Time...");
			document.getElementsByName("outTimeMin")[0].focus();
			return false;
		}
	} 	

	if(document.getElementsByName("outPatStatus")[0].value=="-1")
	{
		alert("Select Patient Out Status ...");
		document.getElementsByName("outPatStatus")[0].focus();
		return false;
	}
	if(document.getElementsByName("outCondition")[0].value=="")
	{
		alert("Describe Patient Out Condition ...");
		document.getElementsByName("outCondition")[0].focus();
		return false;
	} */
	return true; 
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

function validateForm(isTight)
{
	onblurTimeHourCheck(document.getElementsByName("inTimeHr")[0]);
	onblurTimeMinCheck(document.getElementsByName("inTimeMin")[0]);
	document.getElementsByName("inTime")[0].value=document.getElementsByName("inTimeHr")[0].value+":"+document.getElementsByName("inTimeMin")[0].value;

	if(document.getElementsByName("emergencyCode")[0].value=="-1")
	{
		alert("Select Emergency Type ...");
		document.getElementsByName("emergencyCode")[0].focus();
		return false;
	}

	if(document.getElementsByName("inDate")[0].value=="")
	{
		alert("Enter Patient Entry Date ...");
		document.getElementsByName("inDate")[0].focus();
		return false;
	}
	var inDate = document.getElementsByName("inDate")[0].value;
	var curr=document.getElementById("currentDateStr").value;
	var yest=document.getElementById("yesterDateStr").value;
	if(inDate!=curr && inDate!=yest)
	{
		alert("Patient Entry Date can either be Today or Day Before ...");
		document.getElementsByName("inDate")[0].focus();
		return false;
	}
	if(document.getElementsByName("inTimeHr")[0].value=="")
	{
		alert("Enter Patient Entry Time Hour ...");
		document.getElementsByName("inTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("inTimeMin")[0].value=="")
	{
		alert("Enter Patient Entry Time Minute ...");
		document.getElementsByName("inTimeMin")[0].focus();
		return false;
	}
	// Compare In Time with Sys Time
	if(inDate==curr)
	{
		var inHr=parseInteger(document.getElementsByName("inTimeHr")[0].value);
		var inMin=parseInteger(document.getElementsByName("inTimeMin")[0].value);

		var curHr=parseInteger(document.getElementById("curHr").value);
		var curMin=parseInteger(document.getElementById("curMin").value);
		if(inHr>curHr)
		{
			alert("Entry Time can't be greater than Current Time...");
			document.getElementsByName("inTimeHr")[0].focus();
			return false;
		}
		else if(inHr==curHr && inMin>curMin)
		{
			alert("Entry Time can't be greater than Current Time...");
			document.getElementsByName("inTimeMin")[0].focus();
			return false;
		}
	}

	if(document.getElementsByName("inPatStatus")[0].value=="-1")
	{
		alert("Select Patient Entry Status ...");
		document.getElementsByName("inPatStatus")[0].focus();
		return false;
	}
	if(document.getElementsByName("inCondition")[0].value=="")
	{
		alert("Describe Patient Entry Condition ...");
		document.getElementsByName("inCondition")[0].focus();
		return false;
	}
	if(isTight)
	{
		if(!validateModifyForm())
			return false;
	}
	else
	{
		// Any Of Out Entry is Done
		if( document.getElementsByName("outDate")[0].value!="" || 
			document.getElementsByName("outPatStatus")[0].value!="-1" ||
			document.getElementsByName("outCondition")[0].value!="" )
		{
			if(!validateModifyForm())
				return false;
		}
		else
		{
			document.getElementsByName("outDate")[0].value=""; 
			document.getElementsByName("outTimeHr")[0].value="";
			document.getElementsByName("outTimeMin")[0].value="";
			document.getElementsByName("outPatStatus")[0].value="";
			document.getElementsByName("outCondition")[0].value="";
		}
	}
	return true;
}

function validateAlphaOnly(obj,e)
{// Ascii Code 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, . -46, for , - 44
	var k=e.charCode;
	//alert(k);
	if( e.keyCode!=0 || k==32 || k==46 || (k>=65 && k<=90) || (k>=97 && k<=122) )
		return true;
	else
		return false;
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

function compareDateCall(d1,d2,mode,l1,l2) 
{
	var d11 = document.getElementsByName("inDate")[0].value ;
	var d22 = document.getElementsByName("outDate")[0].value ;
	
	var valid=true;
	 if(isEmpty(d1,l1) && isEmpty(d2,l2))
	{
 		if(compareDate(d1,d2, mode)){
 	 		//alert("should return false");
			valid = true;}
		else
		{
			alert(l1+" can't be greater than "+l2);
			valid = false;
		}
	}
	else
		valid=false;
		//alert("return value in compare Call"+valid);
 	return valid;
}

function getSeperator(dtStr)
{
	var seprator = "";
	var format = "-";

	if (dtStr.indexOf(format) > -1)
		seprator = "-";
	else
	{
		if (dtStr.indexOf("/") > -1)
			seprator = "/";
		else
		{
			if (dtStr.indexOf(".") > -1)
				seprator = ".";
		}
	}	//endif 
	return seprator;
}

 function compareDate(frDate,toDate)
{
	/* var objFrmDate =  convertStrToDate(frDate, "dd-Mon-yyyy");
	var objToDate =  convertStrToDate(toDate, "dd-Mon-yyyy");

	if(objFrmDate>objToDate)
		return false; */

	var seprator1 = "", seprator2 = "";
	var retValue = false;
	var dtMode = 0;
	var retValues1,retValues2;
	
	if (frDate != "" && frDate != null && toDate != "" && toDate != null)
	{
		seprator1 = getSeperator(frDate);		//function that returns seperator
		seprator2 = getSeperator(toDate);		//function that returns seperator
		
		if (seprator1 != "" && frDate.length >= 8)
		{
			retValues1 = parseDate(frDate,seprator1);
			if (seprator2 != "" && toDate.length >= 8 && retValues1.cancelKey == true)
			{
				retValues2 = parseDate(toDate,seprator2);
				if(retValues2.cancelKey == true)
				{
					if(retValues1.year == retValues2.year)
					{
						if(retValues1.month == retValues2.month)
						{
							//if(retValues1.day == retValues2.day){
							//	dtMode = 1;
							//else
							//{
								if(retValues1.day == retValues2.day)
									{
										dtMode = 2;
										//validateTime(frDate,toDate);
										retValue = true;
										//alert("verified");
										return {cancelKey :retValue,mode:dtMode};
										}
								else if (retValues1.day < retValues2.day)
								{
									dtMode = 2;
									retValue = true;
									//alert("verified");
									return {cancelKey :retValue,mode:dtMode};
									}
								
								else{
									//alert("Invalid Date");
									dtMode = 0;
									}
							}
							else
								{
									dtMode = 0;
									//alert("wrong month");
								}
						}
						else
						{
							/* if(retValues1.month > retValues2.month)
								dtMode = 2;
							else */
								dtMode = 0;	 

							//alert("wrong year");
						}
					}
					else
					{
						/* if(retValues1.year > retValues2.year)
							dtMode = 2;
						else */
						dtMode = 0;	 	
						alert("Wrong format");
					}
					
					//retValue = true;
				}
			}
		}
	//}
	//alert(retValue);
	 //return {cancelKey :retValue,mode:dtMode}; 
	return retValue;
} //end compareDate() function */

/* 
function validateTime(frmDate, toDate)
{
	var indate=frmDate;
	var outdate=toDate;
	var curr=document.getElementById("currentDateStr").value;
	
	if(document.getElementsByName("outTimeHr")[0].value=="")
	{
		alert("Enter Patient Out Time Hour ...");
		document.getElementsByName("outTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("outTimeMin")[0].value=="")
	{
		alert("Enter Patient Out Time Minute ...");
		document.getElementsByName("outTimeMin")[0].focus();
		return false;
	}

	var outHr=parseInteger(document.getElementsByName("outTimeHr")[0].value);
	var outMin=parseInteger(document.getElementsByName("outTimeMin")[0].value);
	
	// Compare Out Time with In Time
	if(indate.value==outdate.value)
	{
		var inHr=parseInteger(document.getElementsByName("inTimeHr")[0].value);
		var inMin=parseInteger(document.getElementsByName("inTimeMin")[0].value);
		if(outHr<inHr)
		{
			alert("Out Time should be greater than Entry Time...");
			document.getElementsByName("outTimeHr")[0].focus();
			return false;
		}
		else if(outHr==inHr && outMin<=inMin)
		{
			alert("Out Time should be greater than Entry Time...");
			document.getElementsByName("outTimeMin")[0].focus();
			return false;
		}
	}	

	// Compare Out Time with Systime
	if(outdate.value==curr.value)
	{
		var curHr=parseInteger(document.getElementById("curHr").value);
		var curMin=parseInteger(document.getElementById("curMin").value);
		if(outHr>curHr)
		{
			alert("Out Time can't be greater than Current Time...");
			document.getElementsByName("outTimeHr")[0].focus();
			return false;
		}
		else if(outHr==curHr && outMin>curMin)
		{
			alert("Out Time can't be greater than Current Time...");
			document.getElementsByName("outTimeMin")[0].focus();
			return false;
		}
	}
}
 */

</script>



<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DIRECT">
	<form action="/HISClinical/opd/casuality/triageDetail.cnt" method="post">
</logic:equal>

<%
	String varStatus="";
	String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>

<his:TitleTag name="Day Care Detail">
<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DIRECT">
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
<logic:equal name="EpisodeTriageDetailFB" property="hmode" value="GETCRNO">
<his:InputCrNoTag name="EpisodeTriageDetailFB"> </his:InputCrNoTag>	
</logic:equal>
</his:statusNew>

<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DESK">
	<bean:define id="crNo" name="EpisodeTriageDetailFB" property="patCrNo" type="java.lang.String" />
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
</logic:equal>


<his:statusTransactionInProcess>
	<%varStatus="InProcess";%>
	
	<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DIRECT">
		<bean:define id="name" name="EpisodeTriageDetailFB" property="patFirstName" type="java.lang.String" />
		<bean:define id="crNo" name="EpisodeTriageDetailFB" property="patCrNo" type="java.lang.String" />
		<jsp:include page="/registration/patientDetail.cnt" flush="true" />
	</logic:equal>

	<%
	Date today=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT);
	String sysDate=WebUTIL.getCustomisedSysDate(today, "dd-MMM-yyyy");
	String curHr=WebUTIL.getCustomisedSysDate(today, "HH");
	String curMin=WebUTIL.getCustomisedSysDate(today, "mm");
	
	Calendar cal=Calendar.getInstance();
	cal.setTime(today);
	cal.add(Calendar.DAY_OF_MONTH,-1);
	String yesterDate=WebUTIL.getCustomisedSysDate(cal.getTime(), "dd-MMM-yyyy");
	
	%>
	<input type="hidden" id="currentDateStr" value="<%=sysDate%>"/>
	<input type="hidden" id="yesterDateStr" value="<%=yesterDate%>"/>
	<input type="hidden" id="curHr" value="<%=curHr%>"/>
	<input type="hidden" id="curMin" value="<%=curMin%>"/>

	<table cellpadding="0" cellspacing="1" width="100%">
		<tr>
			<td colspan="2">
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="emerCase" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<html:select name="EpisodeTriageDetailFB" property="emergencyCode" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=RegistrationConfig.EMERGENCY_CASE_LIST%>">
										<html:options collection="<%=RegistrationConfig.EMERGENCY_CASE_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</logic:equal>
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<html:hidden name="EpisodeTriageDetailFB" property="emergencyCode"/>
									<bean:define  name="EpisodeTriageDetailFB" property="emergencyCode" id="emerCaseCode" type="java.lang.String"/>
									<%
										String eCode="";
										List lstEmerCase=(ArrayList)session.getAttribute(RegistrationConfig.EMERGENCY_CASE_LIST);										
										for(int xxx=0;xxx<lstEmerCase.size();xxx++)
										{
											Entry entobj=(Entry)lstEmerCase.get(xxx);
											if(entobj.getValue().equals(emerCaseCode))
											{
												eCode=entobj.getLabel();
												break;
											}
										}
									%>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><%=eCode%></b>
									</font>
								</logic:notEqual>
							</td>
							<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">							
								<td>
									<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(true,'MODIFY');" onclick="submitFormOnValidate(true,'MODIFY');">
								</td>
							</logic:equal>
						</tr>						
					</table>
				</his:ContentTag>
			</td>
		</tr>
		
		<tr>
			<td width="50%" valign="top" height="100%">
			 	<his:SubTitleTag name="Day Care Entry Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0" height="100%" style="height: inherit;">
						<tr>
							<td width="50%" class="tdfonthead" valign="middle" style="height: 25px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="date" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont" valign="middle" style="height: 25px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">								
									<bean:define id="inDateFromFB" name="EpisodeTriageDetailFB" property="inDate" type="java.lang.String" />
									<his:date name='inDate' dateFormate="%d-%b-%Y" value="<%=inDateFromFB%>" />
								</logic:equal>
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<!-- <html:text name="EpisodeTriageDetailFB" property="inDate" readonly="true"/> -->
									<html:hidden name="EpisodeTriageDetailFB" property="inDate"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><bean:write name="EpisodeTriageDetailFB" property="inDate"/></b>
									</font>									
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" valign="middle" style="height: 25px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="time" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont" valign="middle" style="height: 25px">
								<html:hidden name="EpisodeTriageDetailFB" property="inTime"/>
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<span>
										<html:text name="EpisodeTriageDetailFB" tabindex="1" property="inTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
										<b>	<bean:message key="colon"/></b>
									</span>
									<span>
										<html:text name="EpisodeTriageDetailFB" tabindex="1" property="inTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('inTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
										<bean:message key="timeFormat"/>
									</span>
								</logic:equal>
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<html:hidden name="EpisodeTriageDetailFB" property="inTimeHr"/>
									<html:hidden name="EpisodeTriageDetailFB" property="inTimeMin"/>
									<!-- <span>
										<html:text name="EpisodeTriageDetailFB" tabindex="1" property="inTimeHr" readonly="true" size="3" />
										<b>	<bean:message key="colon"/></b>
									</span>
									<span>
										<html:text name="EpisodeTriageDetailFB" tabindex="1" property="inTimeMin" readonly="true" size="3" />
										<bean:message key="timeFormat"/>
									</span>  -->
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><bean:write name="EpisodeTriageDetailFB" property="inTime"/></b>
									</font>									
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" valign="middle" style="height: 25px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="patStatus" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont" valign="middle" style="height: 25px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<html:select name="EpisodeTriageDetailFB" property="inPatStatus" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=RegistrationConfig.PATIENT_STATUS_LIST%>">
										<html:options collection="<%=RegistrationConfig.PATIENT_STATUS_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</logic:equal>
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<html:hidden name="EpisodeTriageDetailFB" property="inPatStatus"/>
									<bean:define  name="EpisodeTriageDetailFB" property="inPatStatus" id="patStatId" type="java.lang.String"/>
									<%
										String stat="";
										List lstPatStat=(ArrayList)session.getAttribute(RegistrationConfig.PATIENT_STATUS_LIST);
										for(int yyy=0;yyy<lstPatStat.size();yyy++)
										{
											Entry entobj=(Entry)lstPatStat.get(yyy);
											if(entobj.getValue().equals(patStatId))
											{
												stat=entobj.getLabel();
												break;
											}
										}
									%>
									<!-- <input type="text" value="<%=stat%>" readonly="readonly"/> -->
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><%=stat%></b>
									</font>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" valign="middle" style="height: 50px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>								
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="patientcondition" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont" valign="middle" style="height: 50px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<html:textarea name="EpisodeTriageDetailFB" property="inCondition" 
										onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))" tabindex="1"></html:textarea>
								</logic:equal>
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
									<!-- <html:textarea name="EpisodeTriageDetailFB" property="inCondition" readonly="true" /> -->
									<html:hidden name="EpisodeTriageDetailFB" property="inCondition"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><bean:write name="EpisodeTriageDetailFB" property="inCondition"/></b>
									</font>
								</logic:notEqual>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</td>
			<td width="50%" valign="top" height="100%">
			 	<his:SubTitleTag name="Day Care Out Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0" height="100%" style="height: inherit;">
						<tr>
							<td width="50%" class="tdfonthead" valign="middle" style="height: 25px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="OUT">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<logic:equal name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="date" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont" valign="middle" style="height: 25px">
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
									<bean:define id="outDateFromFB" name="EpisodeTriageDetailFB" property="outDate" type="java.lang.String" />
									<his:date name='outDate' dateFormate="%d-%b-%Y" value="<%=outDateFromFB%>" />
								</logic:notEqual>
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
									<!-- <html:text name="EpisodeTriageDetailFB" property="outDate" readonly="true"/> -->
									<html:hidden name="EpisodeTriageDetailFB" property="outDate"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><bean:write name="EpisodeTriageDetailFB" property="outDate"/></b>
									</font>									
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" valign="middle" style="height: 25px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="OUT">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<logic:equal name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="time" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont" valign="middle" style="height: 25px">
								<html:hidden name="EpisodeTriageDetailFB" property="outTime"/>
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
									<html:hidden name="EpisodeTriageDetailFB" property="outTime"/>
									<span>
										<html:text name="EpisodeTriageDetailFB" tabindex="1" property="outTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
										<b>	<bean:message key="colon"/></b>
									</span>
									<span>
										<html:text name="EpisodeTriageDetailFB" tabindex="1" property="outTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('outTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
										<bean:message key="timeFormat"/>
									</span>
								</logic:notEqual>
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
									<html:hidden name="EpisodeTriageDetailFB" property="outTimeHr"/>
									<html:hidden name="EpisodeTriageDetailFB" property="outTimeMin"/>
									<!-- <span>
										<html:text name="EpisodeTriageDetailFB" tabindex="1" property="outTimeHr" readonly="true" size="3" />
										<b>	<bean:message key="colon"/></b>
									</span>
									<span>
										<html:text name="EpisodeTriageDetailFB" tabindex="1" property="outTimeMin" readonly="true" size="3" />
										<bean:message key="timeFormat"/>
									</span>  -->
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><bean:write name="EpisodeTriageDetailFB" property="outTime"/></b>
									</font>									
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" valign="middle" style="height: 25px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="OUT">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<logic:equal name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="patStatus" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont" valign="middle" style="height: 25px">
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
									<html:select name="EpisodeTriageDetailFB" property="outPatStatus" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=RegistrationConfig.PATIENT_STATUS_LIST%>">
										<html:options collection="<%=RegistrationConfig.PATIENT_STATUS_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</logic:notEqual>
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
									<html:hidden name="EpisodeTriageDetailFB" property="outPatStatus"/>
									<bean:define  name="EpisodeTriageDetailFB" property="outPatStatus" id="patStatId1" type="java.lang.String"/>
									<%
										String stat1="";
										List lstPatStat1=(ArrayList)session.getAttribute(RegistrationConfig.PATIENT_STATUS_LIST);
										for(int yyy=0;yyy<lstPatStat1.size();yyy++)
										{
											Entry entobj=(Entry)lstPatStat1.get(yyy);
											if(entobj.getValue().equals(patStatId1))
											{
												stat1=entobj.getLabel();
												break;
											}
										}
									%>
									<!-- <input type="text" value="<%=stat1%>" readonly="readonly"/> -->
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><%=stat1%></b>
									</font>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" valign="middle" style="height: 50px">
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="OUT">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<logic:equal name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
								</logic:equal>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="patientcondition" />&nbsp;
								</font>
							</td>
							<td width="50%" class="tdfont" valign="middle" style="height: 50px">
								<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
									<html:textarea name="EpisodeTriageDetailFB" property="outCondition" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))" tabindex="1"></html:textarea>
								</logic:notEqual>
								<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
									<!-- <html:textarea name="EpisodeTriageDetailFB" property="outCondition" readonly="true" /> -->
									<html:hidden name="EpisodeTriageDetailFB" property="outCondition"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b><bean:write name="EpisodeTriageDetailFB" property="outCondition"/></b>
									</font>
								</logic:equal>
							</td>
						</tr>
					</table>
				</his:ContentTag>			
			</td>
		</tr>
	</table>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="50%" class="tdfonthead">
					<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="OUT">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					</logic:equal>
					<logic:equal name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					</logic:equal>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="summary" />&nbsp;
					</font>
				</td>

				<td width="50%" class="tdfont">
					<div align="left">
						<logic:notEqual name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
							<html:textarea name="EpisodeTriageDetailFB" property="remarks" 
								onkeypress="return (validateTextArea(event,this,'300') && validateAlphaNumericOnly(event))" tabindex="1"/>
						</logic:notEqual>
						<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="TOMODIFY">
							<!-- <html:textarea name="EpisodeTriageDetailFB" property="remarks" readonly="true" /> -->
							<html:hidden name="EpisodeTriageDetailFB" property="remarks"/>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<b><bean:write name="EpisodeTriageDetailFB" property="remarks"/></b>
							</font>
						</logic:equal>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DIRECT">
		<%	if(varStatus.equals("InProcess"))	{	%>
			<his:statusTransactionInProcess>
			<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
				<logic:notEqual name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateForm(false),'SAVE');" onclick="submitFormOnValidate(validateForm(false),'SAVE');">
				</logic:notEqual>
			</logic:equal>
			<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="OUT">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateModifyForm(),'MODIFYSAVE');" onclick="submitFormOnValidate(validateModifyForm(),'MODIFYSAVE');">
			</logic:equal>
			<logic:equal name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateForm(true),'MODIFYSAVE');" onclick="submitFormOnValidate(validateForm(true),'MODIFYSAVE');">
			</logic:equal>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" onclick="submitForm('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');" >
			</his:statusTransactionInProcess>
		<%	} else {	%>
			<his:statusNew>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" onclick="submitForm('CANCEL');">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');">
			</his:statusNew>
		<%	}	%>
	</logic:equal>
	<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DESK">
		<%	if(varStatus.equals("InProcess"))	{	%>
			<his:statusTransactionInProcess>
			<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="ALL">
				<logic:notEqual name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateForm(false),'SAVE');" onclick="submitFormOnValidate(validateForm(false),'SAVE');">
				</logic:notEqual>
			</logic:equal>
			<logic:equal name="EpisodeTriageDetailFB" property="entryMode" value="OUT">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateModifyForm(),'MODIFYSAVE');" onclick="submitFormOnValidate(validateModifyForm(),'MODIFYSAVE');">
			</logic:equal>
			<logic:equal name="EpisodeTriageDetailFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateForm(true),'MODIFYSAVE');" onclick="submitFormOnValidate(validateForm(true),'MODIFYSAVE');">
			</logic:equal>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" onclick="submitToDesk('NEW','NEW');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
			</his:statusTransactionInProcess>
		<%	} else {	%>
			<his:statusNew>			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" onclick="submitToDesk('NEW','NEW');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</his:statusNew>
		<%	}	%>
	</logic:equal>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>

<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DIRECT">
	<his:status/>
</logic:equal>


<html:hidden name="EpisodeTriageDetailFB" property="hmode"/>
<html:hidden name="EpisodeTriageDetailFB" property="seatId"/>
<html:hidden name="EpisodeTriageDetailFB" property="patCrNo"/>
<html:hidden name="EpisodeTriageDetailFB" property="entryDate"/>
<html:hidden name="EpisodeTriageDetailFB" property="entryMode"/>
<html:hidden name="EpisodeTriageDetailFB" property="episodeCode"/>
<html:hidden name="EpisodeTriageDetailFB" property="episodeVisitNo"/>
<html:hidden name="EpisodeTriageDetailFB" property="serialNo"/>
<html:hidden name="EpisodeTriageDetailFB" property="isDirectCall"/>

<logic:equal name="EpisodeTriageDetailFB" property="isDirectCall" value="DIRECT">
	</form>
</logic:equal>