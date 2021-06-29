<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<logic:equal name="PatientDeathDetailFB" property="isDirectCall" value="DIRECT">

<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
	
	<his:javascript src="/registration/js/registration.js" />
	<his:javascript src="/registration/js/validationCalls.js" />
	<his:javascript src="/registration/js/validationCommon.js" />
	<his:javascript src="/registration/js/commonFunctions.js" />
	<his:javascript src="/hisglobal/js/calendar.js"/>
	<his:javascript src="/registration/js/popup.js"/>

</logic:equal>


<script type="text/javascript">

window.onload = function() 
{
	if(document.getElementsByName("printFlag")[0].value==<%=RegistrationConfig.PRINT_FLAG_YES%>)
	{
		openDeathPrintPopup();
	
	if(document.getElementsByName("deskType")[0].value==<%=DynamicDeskConfig.DESK_TYPE_CMO_DESK%>)	
		submitPage('EMODESK');
	}	
	enableDisableIsDelivery();
}

function openDeathPrintPopup()
{
	//alert("popup");
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var noOfCopies=document.getElementsByName("noOfCopies")[0].value;
	var isInpatient=document.getElementsByName("isInpatient")[0].value;
	var path='/HISClinical/registration/patientDeathDetail.cnt?hmode=PRINTCERT&patCrNo='+patCrNo+'&noOfCopies='+noOfCopies+'&isInpatient='+isInpatient;
	openPrintPopup(path,700,800);
	document.getElementsByName("printFlag")[0].value=<%=RegistrationConfig.PRINT_FLAG_NO%>;
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
}
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function showHandoverDetail()
{
	if(document.getElementsByName("bodyHandoverTo")[0].value=="1" || document.getElementsByName("bodyHandoverTo")[0].value=="-1")
	{
		document.getElementById("divPolice").style.display="none";
		document.getElementById("divRelative").style.display="none";
	}
	if(document.getElementsByName("bodyHandoverTo")[0].value=="2")
	{
		document.getElementById("divPolice").style.display="block";
		document.getElementById("divRelative").style.display="none";
	}
	if(document.getElementsByName("bodyHandoverTo")[0].value=="3")
	{
		document.getElementById("divPolice").style.display="none";
		document.getElementById("divRelative").style.display="block";
	}
}

function showHandoverToDiv(obj)
{
	if(obj.checked==true)
		document.getElementById("divHandoverDetail").style.display="block";
	else
		document.getElementById("divHandoverDetail").style.display="none";
}

function showInjuryDiv()
{
	var InjuryCodeWithAccFlag=document.getElementsByName("deathMannerCode")[0].value;
	var accFlag=InjuryCodeWithAccFlag.split("@")[1];
	
	if(accFlag=="1")
	{
		document.getElementById("divInjuryDetailId").style.display="block";
		document.getElementsByName("isDeathAccidental")[0].value="1";
	}	
	else	
	{
		document.getElementById("divInjuryDetailId").style.display="none";
		document.getElementsByName("isDeathAccidental")[0].value="0";
	}	
}

function enableDisableIsDelivery()
{
	if(document.getElementsByName("isPregnant")[0].checked)
	{
		document.getElementById("isDeliveryLabel").style.display="block";
		document.getElementById("isDeliveryControl").style.display="block";
		document.getElementById("isDeliveryLabelEmpty").style.display="none";
		document.getElementById("isDeliveryControlEmpty").style.display="none";
		document.getElementById("pregnancyRemarksLabel").style.display="block";
		
	}
	else
	{
		document.getElementById("isDeliveryLabel").style.display="none";
		document.getElementById("isDeliveryControl").style.display="none";
		document.getElementById("isDeliveryLabelEmpty").style.display="block";
		document.getElementById("isDeliveryControlEmpty").style.display="block";
		document.getElementById("pregnancyRemarksLabel").style.display="none";
	
		document.getElementsByName("isDelivery")[1].checked=true;
		document.getElementsByName("pregnancyRemarks")[0].value="";
	}
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


function validate()
{
	var valid=true;
	if(isEmpty(document.forms[0].deathDate,"Date of Death")
		&& isEmpty(document.forms[0].deathTimeHr,"Death Hour")
		&& isEmpty(document.forms[0].deathTimeMin,"Death Minute")
		&& comboValidation(document.forms[0].deathMannerCode,"Manner of Death")
				&& isEmpty(document.forms[0].onSetDate,"On Set Date")
		&& isEmpty(document.forms[0].immediateCause1,"Immediate Cause of Death")
		
		&& isEmpty(document.forms[0].verificationDate,"Verification Date")
		&& isEmpty(document.forms[0].verificationTimeHr,"Verification Hour")
		&& isEmpty(document.forms[0].verificationTimeMin,"Verification Minute")
		
		&& validateInjury()
		&& validateHandoverToDetail()
		&& validateDeathDateTime()
		&& validateOnSetDate()
		&& validateVerificationDate()
		
		
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
//	return false;
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function validateInjury()
{
	var valid=true;
	if(document.getElementsByName("isDeathAccidental")[0].value=="1")
	{
		if(isEmpty(document.forms[0].injuryNatureCode,"Nature of Injury")
		&& isEmpty(document.forms[0].injuryTypeCode,"Type of Injury")
	//	&& isEmpty(document.forms[0].injuryRemarks,"Injury Remarks")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	return valid;
}

function validateDeathDateTime()
{
	var valid=true;
	
	
	/*if(document.getElementsByName("deathDate")[0].value=="")
	{
		alert("Please Enter The Date of Death")
		document.getElementsByName("deathDate")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("deathTimeHr")[0].value=="")
	{
		alert("Please Enterr The Date Hour")
		document.getElementsByName("deathTimeHr")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("deathTimeMin")[0].value=="")
	{
		alert("Please Enterr The Date Minute")
		document.getElementsByName("deathTimeMin")[0].focus();
		valid=false;
	}
	else*/
	
		valid=validateDeathDate();
	
	
	return valid;
}

function validateDeathDate()
{
	var valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var ntHour=document.getElementsByName("hiddenTimeHr")[0].value;
	var ntMin=document.getElementsByName("hiddenTimeMin")[0].value;
	
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var deathDate=document.getElementsByName("deathDate")[0].value;
	var recentVisitDate=document.getElementsByName("hiddenRecentVisitDate")[0].value;	// epiDate is the Patient Recent Visited Date
	
	day=parseInt((noOfDays(sysDate,deathDate)));
	
	if(day>0)
	{
		day1=parseInt((noOfDays(deathDate,recentVisitDate)));
		
		if(day1<0)
		{
			alert("Death Date Cannot be Less than Visited Date = "+recentVisitDate);
			document.getElementsByName("deathDate")[0].focus();
			valid=false;
		}
		else
		{
			valid=true;
		}
	}
	else
	{
		if(day==0)
		{
			if(parseInt(trimNum(document.getElementsByName("deathTimeHr")[0].value)) > hour)
			{
				alert("Death Date Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("deathTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("deathTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("deathTimeMin")[0].value)) > min)
			{
				alert("Death Date Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("deathTimeMin")[0].focus();
				valid=false;
			}
		}
		else
		{
			alert("Date of Death Cannot be Greater than Current Date");
			document.getElementsByName("deathDate")[0].focus();
			valid=false;
		}
	}
	return valid;
}

function validateVerificationDate()
{
	var valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var dHour=parseInt(trimNum(document.getElementsByName("deathTimeHr")[0].value));
	var dMin=parseInt(trimNum(document.getElementsByName("deathTimeMin")[0].value));
//	var hHour=parseInt(trimNum(document.getElementsByName("handoverTimeHr")[0].value));
//	var hMin=parseInt(trimNum(document.getElementsByName("handoverTimeMin")[0].value));
	
	
	var ntHour=document.getElementsByName("hiddenTimeHr")[0].value;
	var ntMin=document.getElementsByName("hiddenTimeMin")[0].value;
	var ntdHour=document.getElementsByName("deathTimeHr")[0].value;
	var ntdMin=document.getElementsByName("deathTimeMin")[0].value;
//	var nthHour=document.getElementsByName("handoverTimeHr")[0].value;
//	var nthMin=document.getElementsByName("handoverTimeMin")[0].value;
	
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var deathDate=document.getElementsByName("deathDate")[0].value;
	var verDate=document.getElementsByName("verificationDate")[0].value;
//	var handoverDate=document.getElementsByName("handoverDate")[0].value;
	
	day1=parseInt((noOfDays(verDate,deathDate)));
	
	if(day1>0)
	{
		if(document.getElementsByName("isBodyHandoverToMortuary")[0].value==<%=Config.BODY_HANDOVER_MORTUARY_NO %>)
		{	
			var hHour=parseInt(trimNum(document.getElementsByName("handoverTimeHr")[0].value));
			var hMin=parseInt(trimNum(document.getElementsByName("handoverTimeMin")[0].value));
			
			var nthHour=document.getElementsByName("handoverTimeHr")[0].value;
			var nthMin=document.getElementsByName("handoverTimeMin")[0].value;
			
			var handoverDate=document.getElementsByName("handoverDate")[0].value;
			
			if(document.getElementsByName("isBodyHandoverToMortuary")[0].value==<%=Config.BODY_HANDOVER_MORTUARY_NO %>
				&& document.getElementsByName("isHandoverTo")[0].checked)
			{
				day2=parseInt((noOfDays(verDate,handoverDate)));
				
				if(day2>0)
				{
					alert("Verification Date Cannot be Greater Than Handover Date");
					document.getElementsByName("verificationDate")[0].focus();
					valid=false;
				}
				else
				{
					if(day2==0)
					{
						if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) > hHour)
						{
							alert("Verification Date Cannot be Greater Than "+handoverDate+" "+nthHour+":"+nthMin);
							document.getElementsByName("verificationTimeHr")[0].focus();
							valid=false;
						}
						else if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) >= hHour && parseInt(trimNum(document.getElementsByName("verificationTimeMin")[0].value)) > hMin)
						{
							alert("Verification Date Cannot be Greater Than "+handoverDate+" "+nthHour+":"+nthMin);
							document.getElementsByName("verificationTimeMin")[0].focus();
							valid=false;
						}
					}
					else
					{
						valid=true;
					}
				}
			}
			if(document.getElementsByName("isBodyHandoverToMortuary")[0].value==<%=Config.BODY_HANDOVER_MORTUARY_NO %>
				&& !document.getElementsByName("isHandoverTo")[0].checked)
			{
				day2=parseInt((noOfDays(verDate,sysDate)));
				
				if(day2>0)
				{
					alert("Verification Date Cannot be Greater Than Current Date");
					document.getElementsByName("verificationDate")[0].focus();
					valid=false;
				}
				else
				{
					if(day2==0)
					{
						if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) > hour)
						{
							alert("Verification Date Cannot be Greater Than "+sysDate+" "+ntHour+":"+ntMin);
							document.getElementsByName("verificationTimeHr")[0].focus();
							valid=false;
						}
						else if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("verificationTimeMin")[0].value)) > min)
						{
							alert("Verification Date Cannot be Greater Than "+sysDate+" "+ntHour+":"+ntMin);
							document.getElementsByName("verificationTimeMin")[0].focus();
							valid=false;
						}
					}
					else
					{
						valid=true;
					}
				}
			}
		}
		else
		//if(document.getElementsByName("isBodyHandoverToMortuary")[0].value==<%=Config.BODY_HANDOVER_MORTUARY_YES %>)
		{
			day2=parseInt((noOfDays(verDate,sysDate)));
			
			if(day2>0)
			{
				alert("Verification Date Cannot be Greater Than Current Date");
				document.getElementsByName("verificationDate")[0].focus();
				valid=false;
			}
			else
			{
				if(day2==0)
				{
					if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) > hour)
					{
						alert("Verification Date Cannot be Greater Than "+sysDate+" "+ntHour+":"+ntMin);
						document.getElementsByName("verificationTimeHr")[0].focus();
						valid=false;
					}
					else if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("verificationTimeMin")[0].value)) > min)
					{
						alert("Verification Date Cannot be Greater Than "+sysDate+" "+ntHour+":"+ntMin);
						document.getElementsByName("verificationTimeMin")[0].focus();
						valid=false;
					}
					
				}
				else
				{
					valid=true;
				}
			}
		}		
	}
	else
	{
		if(day1==0)
		{
			if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) < dHour)
			{
				alert("Verification Date Cannot be Less Than "+deathDate+" "+ntdHour+":"+ntdMin);
				document.getElementsByName("verificationTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) <= dHour && parseInt(trimNum(document.getElementsByName("verificationTimeMin")[0].value)) < dMin)
			{
				alert("Verification Date Cannot be Less than "+deathDate+" "+ntdHour+":"+ntdMin);
				document.getElementsByName("verificationTimeMin")[0].focus();
				valid=false;
			}
			if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) > hour)
			{
				alert("Verification Date Cannot be Greater Than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("verificationTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("verificationTimeMin")[0].value)) > min)
			{
				alert("Verification Date Cannot be Greater Than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("verificationTimeMin")[0].focus();
				valid=false;
			}
		}
		else
		{
			alert("Verification Date Cannot be Less Than Death Date");
			document.getElementsByName("verificationDate")[0].focus();
			valid=false;
		}
	}
	
	
	return valid;
}

function validateOnSetDate()
{
	var client= "<%=Config.CLIENT%>";
	var valid=true;
	if(client=="<%=Config.CLIENT_PGIMER%>")
	{
		var hiddenOnSetDate=document.getElementsByName("hiddenOnSetDate")[0].value;
		var deathDate=document.getElementsByName("deathDate")[0].value;
		var onSetDate=document.getElementsByName("onSetDate")[0].value;
		
		day1=parseInt((noOfDays(onSetDate,hiddenOnSetDate)));
		day2=parseInt((noOfDays(onSetDate,deathDate)));
		
		if(day1<0)
		{
			alert("On Set Date Cannot be Less Than "+hiddenOnSetDate);
			document.getElementsByName("onSetDate")[0].focus();
			valid=false;
		}
		else if(day2>0)
		{
			alert("On Set Date Cannot be Greater Than "+deathDate);
			document.getElementsByName("onSetDate")[0].focus();
			valid=false;
		}
	}
	
	return valid;
}

function validateHandoverToDetail()
{
	var valid=true;
	if(document.getElementsByName("deskType")[0].value!=<%=DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK%>)
	{
		if(document.getElementsByName("isBodyHandoverToMortuary")[0].value==<%=Config.BODY_HANDOVER_MORTUARY_NO %>)
		{
			if(document.getElementsByName("isHandoverTo")[0].checked)
			{
				if(validateHandoverDate() && validateIsHandoverTo())
					valid=true;
				else
					valid=false;	
			}
			else
			{
				valid=true;
			}
		}
		else
		{
			valid=true;
		}	
	}
	else
	{
		valid=true;
	}	
	return valid;
}

function validateIsHandoverTo()
{
	var valid=true;
	
		if(document.getElementsByName("bodyHandoverTo")[0].value=="-1")
		{
			alert("Please Select hand Over To");
			document.getElementsByName("bodyHandoverTo")[0].focus();
			valid=false;
		}
		if(document.getElementsByName("bodyHandoverTo")[0].value=="1")
		{
			valid=true;
		}
		if(document.getElementsByName("bodyHandoverTo")[0].value=="2")
		{
			valid=validateHandoverPolice();
		}
		if(document.getElementsByName("bodyHandoverTo")[0].value=="3")
		{
			valid=validateHandoverRelative();
		}
	
	return valid;
}

function validateHandoverPolice()
{
	var valid=true;
	if(
		isEmpty(document.forms[0].officerName,"Officer Name")
		&& isEmpty(document.forms[0].officerDesignation,"Officer Designation")
		&& isEmpty(document.forms[0].officerBadgeNo,"Officer Badge No")
	)
	{
		valud=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateHandoverRelative()
{
	var valid=true;
	if(
		isEmpty(document.forms[0].relativeName,"Relative Name")
		&& comboValidation(document.forms[0].relativeCode,"Relationship")
		&& isEmpty(document.forms[0].relativeAddress,"Address Of Relative")
	)
	{
		valud=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateHandoverDate()
{
	var valid=true;
	if(isEmpty(document.forms[0].handoverDate,"Handover Date")
		&& isEmpty(document.forms[0].handoverTimeHr,"Handover Hour")
		&& isEmpty(document.forms[0].handoverTimeMin,"Handover Minute")
	)	
	{
		valid=validateHandOverDt();
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateHandOverDt()
{
	var valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var vHour=parseInt(trimNum(document.getElementsByName("verificationTimeHr")[0].value));
	var vMin=parseInt(trimNum(document.getElementsByName("verificationTimeMin")[0].value));
	
	var ntHour=document.getElementsByName("hiddenTimeHr")[0].value;
	var ntMin=document.getElementsByName("hiddenTimeMin")[0].value;
	var ntvHour=document.getElementsByName("verificationTimeHr")[0].value;
	var ntvMin=document.getElementsByName("verificationTimeMin")[0].value;
	
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var verDate=document.getElementsByName("verificationDate")[0].value;
	var handoverDate=document.getElementsByName("handoverDate")[0].value;
	
	day=parseInt((noOfDays(sysDate,handoverDate)));
	
	if(day>0)
	{
		day1=parseInt((noOfDays(handoverDate,verDate)));
		
		if(day1<0)
		{
			alert("Handover Date Cannot be Less than Verification Date");
			valid=false;
		}
		else
		{
			if(day1==0)
			{
				if(parseInt(trimNum(document.getElementsByName("handoverTimeHr")[0].value)) < vHour)
				{
					alert("Handover Date  Cannot be Less than "+verDate+" "+ntvHour+":"+ntvMin);
					document.getElementsByName("handoverTimeHr")[0].focus();
					valid=false;
				}
				else if(parseInt(trimNum(document.getElementsByName("handoverTimeHr")[0].value)) <= vHour && parseInt(trimNum(document.getElementsByName("handoverTimeMin")[0].value)) < vMin)
				{
					alert("Handover Date  Cannot be Less than "+verDate+" "+ntvHour+":"+ntvMin);
					document.getElementsByName("handoverTimeMin")[0].focus();
					valid=false;
				}
			}
			else
			{alert("dede");
				valid=true;
			}
		}
	}
	else
	{
		if(day==0)
		{
			if(parseInt(trimNum(document.getElementsByName("handoverTimeHr")[0].value)) > hour)
			{
				alert("Handover Date  Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("handoverTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("handoverTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("handoverTimeMin")[0].value)) > min)
			{
				alert("Handover Date  Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("handoverTimeMin")[0].focus();
				valid=false;
			}
			else if(parseInt(noOfDays(handoverDate,verDate))<0)
			{
				alert("Verification Date Cannot be Greater than Current Date");
				document.getElementsByName("verificationDate")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("handoverTimeHr")[0].value)) < vHour)
			{
				alert("Handover Date  Cannot be Less than "+verDate+" "+ntvHour+":"+ntvMin);
				document.getElementsByName("handoverTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("handoverTimeHr")[0].value)) <= vHour && parseInt(trimNum(document.getElementsByName("handoverTimeMin")[0].value)) < vMin)
			{
				alert("Handover Date  Cannot be Less than "+verDate+" "+ntvHour+":"+ntvMin);
				document.getElementsByName("handoverTimeMin")[0].focus();
				valid=false;
			}
		}
		else
		{
			alert("Handover Date Cannot be Greater than Current Date")
			valid=false;
		}
	}
	return valid;
}

function noOfDays(a,b)
{
	valid=true;
	var day=0;
	
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
      
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
     day=(adate-bdate)/86400000;
    return day;
}

function clearForm()
{
	var client= "<%=Config.CLIENT%>";
	
	document.getElementsByName("deathDate")[0].value="";
	document.getElementsByName("deathTimeHr")[0].value="";
	document.getElementsByName("deathTimeMin")[0].value="";
	document.getElementsByName("deathMannerCode")[0].value="";
	
	if(client=="<%=Config.CLIENT_PGIMER%>")
		document.getElementsByName("onSetDate")[0].value=document.getElementsByName("hiddenOnSetDate")[0].value;
	document.getElementsByName("immediateCause1")[0].value="";
	document.getElementsByName("immediateCause2")[0].value="";
	document.getElementsByName("immediateCause3")[0].value="";
	document.getElementsByName("otherCause")[0].value="";
	document.getElementsByName("verificationDate")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("verificationTimeHr")[0].value=document.getElementsByName("hiddenTimeHr")[0].value;
	document.getElementsByName("verificationTimeMin")[0].value=document.getElementsByName("hiddenTimeMin")[0].value;
//	document.getElementsByName("consultantRemarks")[0].value="";
	document.getElementsByName("handoverDate")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("handoverTimeHr")[0].value=document.getElementsByName("hiddenTimeHr")[0].value;
	document.getElementsByName("handoverTimeMin")[0].value=document.getElementsByName("hiddenTimeMin")[0].value;
	document.getElementsByName("isHandoverTo")[0].value="";
	document.getElementsByName("bodyHandoverTo")[0].value="-1";
	document.getElementsByName("officerName")[0].value="";
	document.getElementsByName("officerDesignation")[0].value="";
	document.getElementsByName("officerBadgeNo")[0].value="";
	document.getElementsByName("relativeName")[0].value="";
	document.getElementsByName("relativeAddress")[0].value="";
	document.getElementsByName("relativeCode")[0].value="-1";
	document.getElementsByName("injuryNatureCode")[0].value="-1";
	document.getElementsByName("injuryTypeCode")[0].value="-1";
	document.getElementsByName("injuryRemarks")[0].value="";
	showHandoverDetail();
	document.getElementsByName("isPregnant")[1].checked=true;
	document.getElementsByName("isDelivery")[1].checked=true;
	enableDisableIsDelivery();
	
}

</script>

<logic:equal name="PatientDeathDetailFB" property="isDirectCall" value="DIRECT">
	<form action="/HISClinical/registration/patientDeathDetail.cnt" method="post">
</logic:equal>

<his:TransactionContainer>
	<his:TitleTag name="Patient Death Notification Detail">
	</his:TitleTag>
	
		<bean:define name="PatientDeathDetailFB" property="sysDate" id="sysDate" type="java.lang.String" />
	<%
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	%>
	
	<his:statusNew>
		<logic:equal name="PatientDeathDetailFB" property="hmode" value="GETCRNO">
			<logic:empty name="PatientDeathDetailFB" property="patCrNo" >
				<his:InputCrNoTag name="PatientDeathDetailFB"></his:InputCrNoTag>
			</logic:empty>
		</logic:equal>		
	</his:statusNew>
	<his:statusUnsuccessfull>
		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	</his:statusUnsuccessfull>
	
	<his:statusTransactionInProcess>
		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
		
		<his:SubTitleTag name="Death Notification Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="deathdate"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<his:date name="deathDate" dateFormate="%d-%b-%Y" />
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="deathtime"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:text name="PatientDeathDetailFB" tabindex="1" property="deathTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
								<b>
									<bean:message key="colon"/>
								</b>
							<html:text name="PatientDeathDetailFB" tabindex="1" property="deathTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('deathTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
							<bean:message key="timeFormat"/>	
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="deathManner"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:select name="PatientDeathDetailFB" property="deathMannerCode" onchange="showInjuryDiv()">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=RegistrationConfig.ESSENTIAL_DEATH_MANNER_LIST%>">
									<html:options collection="<%=RegistrationConfig.ESSENTIAL_DEATH_MANNER_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="onSetDate"/>
								</b>	
							</font>
						</div>
					</td>
					<bean:define name="PatientDeathDetailFB" property="onSetDate" id="onSetDt" type="java.lang.String" />
					<td width="25%" class="tdfont">
						<div align="left">
							<his:date name="onSetDate" dateFormate="%d-%b-%Y" value="<%=onSetDt %>"/>
						</div>
					</td>
				</tr>
			</table>	
				<logic:equal name="PatientDeathDetailFB" property="patGender" value="2">
					<logic:greaterThan name="PatientDeathDetailFB" property="patAge" value="<%=Config.ANC_DETAIL_MINIMUN_AGE_FOR_ANC_ELIGIBILITY %>">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="isPregnant"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									YES<html:radio name="PatientDeathDetailFB" property="isPregnant" value="<%=RegistrationConfig.IS_PREGNATNT_YES %>" onclick="enableDisableIsDelivery()"></html:radio>
									NO<html:radio name="PatientDeathDetailFB" property="isPregnant" value="<%=RegistrationConfig.IS_PREGNATNT_NO %>" onclick="enableDisableIsDelivery()"></html:radio>
								</div>
							</td>
							<td width="25%" class="tdfonthead" >
								<div align="right" id="isDeliveryLabel">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="isDelivery"/>
										</b>	
									</font>
								</div>
								<div align="right" id="isDeliveryLabelEmpty">
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left" id="isDeliveryControl">
									YES<html:radio name="PatientDeathDetailFB" property="isDelivery" value="<%=RegistrationConfig.IS_DELIVERY_YES %>"></html:radio>
									NO<html:radio name="PatientDeathDetailFB" property="isDelivery" value="<%=RegistrationConfig.IS_DELIVERY_NO %>"></html:radio>
								</div>
								<div align="right" id="isDeliveryControlEmpty">
								</div>
							</td>
						</tr>
					</table>
					<div id="pregnancyRemarksLabel" >	
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr >
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
											<b>
												<bean:message key="pregnancyRemarks"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="75%" class="tdfont" >
									<div align="left">
										<html:text name="PatientDeathDetailFB"  property="pregnancyRemarks" size="90" maxlength="100"></html:text>
									</div>
								</td>		
							</tr>
						</table>
					</div>	
				</logic:greaterThan>
			</logic:equal>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="verification"/>
									<bean:message key="date"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" >
						<div align="left">
							<his:date name="verificationDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>"/>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="verification"/>
									<bean:message key="time"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:text name="PatientDeathDetailFB" tabindex="1" property="verificationTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
							<b>
								<bean:message key="colon"/>
							</b>
							<html:text name="PatientDeathDetailFB" tabindex="1" property="verificationTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('verificationTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
							<bean:message key="timeFormat"/>	
						</div>
					</td>
				</tr>
			</table>	
		</his:ContentTag>
		<his:SubTitleTag name="Cause of Death">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr bgcolor="#91C4D8 "> 
					<td width="20%" colspan="3">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>
									PART I
								</b>	
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="immediateCause"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									(a)
								</b>	
							</font>
						</div>
					</td>
					<td width="75%" class="tdfonthead">
						<div align="left">
							<div align="left">
								<html:textarea name="PatientDeathDetailFB" property="immediateCause1" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="anteceedentCause"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									(b)i.
								</b>	
							</font>
						</div>
					</td>
					<td width="75%" class="tdfonthead">
						<div align="left">
							<div align="left">
								<html:textarea name="PatientDeathDetailFB" property="immediateCause2" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>
									<bean:message key="underlyingCause"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									(b)ii.
								</b>	
							</font>
						</div>
					</td>
					<td width="75%" class="tdfonthead">
						<div align="left">
							<div align="left">
								<html:textarea name="PatientDeathDetailFB" property="immediateCause3" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							</div>
						</div>
					</td>
				</tr>
			</table>	
		</his:ContentTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr bgcolor="#91C4D8 "> 
					<td width="20%" colspan="3">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>
									PART II
								</b>	
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
								<b>
									<bean:message key="otherSignificantCause"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									(c)
								</b>	
							</font>
						</div>
					</td>
					<td width="75%" class="tdfonthead">
						<div align="left">
							<div align="left">
								<html:textarea name="PatientDeathDetailFB" property="otherCause" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
				
		<div id="divInjuryDetailId" style="display: none;">
			<his:SubTitleTag name="Injury Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b>
										<bean:message key="natureInjury"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="PatientDeathDetailFB"  property="injuryNatureCode">
									<html:option value="-1">select Value</html:option>
									<logic:present name="<%=RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST%>">
										<html:options collection="<%=RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b>
										<bean:message key="typeOfInjury"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="PatientDeathDetailFB"  property="injuryTypeCode">
									<html:option value="-1">select Value</html:option>
									<logic:present name="<%=RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST%>">
										<html:options collection="<%=RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="injuryRemarks"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfonthead" colspan="3">
							<div align="left">
								<html:textarea name="PatientDeathDetailFB" property="injuryRemarks" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		</div>
	<%String str="block";//"none"; %>	
	<logic:notEqual name="PatientDeathDetailFB" property="deskType" value="<%=DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK %>">
	<% str="block"; %>
	</logic:notEqual>
	<div style="display:<%=str %>">
		<logic:equal name="PatientDeathDetailFB" property="isBodyHandoverToMortuary" value="<%=Config.BODY_HANDOVER_MORTUARY_NO %>">
		<his:SubTitleTag name="">
			<div align="left">
				<html:checkbox name="PatientDeathDetailFB" tabindex="1" value="<%=RegistrationConfig.IS_HANDOVER_TRUE%>" property="isHandoverTo" onclick="showHandoverToDiv(this)" />
				Handover To Detail
			</div>
		</his:SubTitleTag>
		<div id="divHandoverDetail" style="display: none;">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b>
										<bean:message key="handoverdate"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont" >
							<div align="left">
								<his:date name="handoverDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>"/>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b>
										<bean:message key="handovertime"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="PatientDeathDetailFB" tabindex="1" property="handoverTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
								<b>
									<bean:message key="colon"/>
								</b>
								<html:text name="PatientDeathDetailFB" tabindex="1" property="handoverTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('handoverTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
								<bean:message key="timeFormat"/>	
							</div>
						</td>
					</tr>
					<logic:equal name="PatientDeathDetailFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_NO %>">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="handoverto"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="PatientDeathDetailFB"  property="bodyHandoverTo" onchange="showHandoverDetail()">
										<html:option value="-1">Select Value</html:option>
										<html:option value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_MORTUARY %>">Mortuary</html:option>
										<html:option value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>">Police</html:option>
										<html:option value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES %>">Relatives</html:option>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</logic:equal>
					<logic:equal name="PatientDeathDetailFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_YES %>">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="handoverto"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="PatientDeathDetailFB"  property="bodyHandoverTo" onchange="showHandoverDetail()">
										<html:option value="-1">Select Value</html:option>
										<html:option value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_MORTUARY %>">Mortuary</html:option>
										<html:option value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>">Police</html:option>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</logic:equal>
				</table>	
				<div id="divPolice" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="officer"/>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<html:text name="PatientDeathDetailFB" property="officerName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="officer"/>
											<bean:message key="designation"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="PatientDeathDetailFB" property="officerDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="officer"/>
											<bean:message key="batchno"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="PatientDeathDetailFB" property="officerBadgeNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</table>
				</div>
				<div id="divRelative" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="relativename"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="PatientDeathDetailFB" property="relativeName" maxlength="80" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="realtionship"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="PatientDeathDetailFB" property="relativeCode">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL%>">
											<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<b>
											<bean:message key="relativeaddress"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:textarea name="PatientDeathDetailFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</table>
				</div>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="printCertificate"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:checkbox name="PatientDeathDetailFB" property="isPrintCertificate"></html:checkbox>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="noOfCopies"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<logic:equal name="PatientDeathDetailFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_NO %>">
									<html:text name="PatientDeathDetailFB" property="noOfCopies" size="5" value="<%=RegistrationConfig.NO_OF_DEATH_CERTIFICATE_COPIES_NORMAL %>" readonly="true"></html:text>
								</logic:equal>	
								<logic:equal name="PatientDeathDetailFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_YES %>">
									<html:text name="PatientDeathDetailFB" property="noOfCopies" size="5" value="<%=RegistrationConfig.NO_OF_DEATH_CERTIFICATE_COPIES_MLC %>" readonly="true"></html:text>
								</logic:equal>	
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="isReceiptTaken"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<input type="checkbox" name="isReceiptTaken">
							</div>
						</td>
						<td width="25%" class="tdfonthead">
						</td>
						<td width="25%" class="tdfont">
						</td>
					</tr>
				</table>	
			</his:ContentTag>
		</div>
		</logic:equal>
	</div>
			<html:hidden name="PatientDeathDetailFB" property="sysDate" value="<%=sysDate%>"/>
	</his:statusTransactionInProcess>
		
	
	<his:ButtonToolBarTag>
		<logic:equal name="PatientDeathDetailFB" property="isDirectCall" value="DIRECT">
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="  submitForm('SAVE')" onkeypress="if(event.keyCode==13) submitForm('SAVE')">
			</his:statusTransactionInProcess>	
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
			<his:statusNew>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO')">
			</his:statusNew>
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
			</his:statusTransactionInProcess>
		</logic:equal>
		
		<logic:equal name="PatientDeathDetailFB" property="isDirectCall" value="DESK">
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
		
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick =" if(validate()) submitForm('SAVE')" onkeypress="if(event.keyCode==13) if(validate()) submitForm('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
		</his:statusTransactionInProcess>	
		</logic:equal>
	</his:ButtonToolBarTag>
	
	
</his:TransactionContainer>

		<html:hidden name="PatientDeathDetailFB" property="hmode" />
		<html:hidden name="PatientDeathDetailFB" property="isDirectCall" />
		<html:hidden name="PatientDeathDetailFB" property="patCrNo"/>
		<html:hidden name="PatientDeathDetailFB" property="normalBodyHandover" />
		<html:hidden name="PatientDeathDetailFB" property="mlcBodyHandover"/>
		<html:hidden name="PatientDeathDetailFB" property="isBodyHandoverToMortuary"/>
		<html:hidden name="PatientDeathDetailFB" property="isClient"/>
		<html:hidden name="PatientDeathDetailFB" property="patGender"/>
		<html:hidden name="PatientDeathDetailFB" property="patAge"/>
		<html:hidden name="PatientDeathDetailFB" property="hiddenTimeHr"/>
		<html:hidden name="PatientDeathDetailFB" property="hiddenTimeMin"/>
		<html:hidden name="PatientDeathDetailFB" property="hiddenOnSetDate"/>
		<html:hidden name="PatientDeathDetailFB" property="hiddenRecentVisitDate"/>
		<html:hidden name="PatientDeathDetailFB" property="isMlc"/>	
		<html:hidden name="PatientDeathDetailFB" property="isDeathAccidental"/>	
		<html:hidden name="PatientDeathDetailFB" property="printFlag"/>
		<html:hidden name="PatientDeathDetailFB" property="noOfCopies"/>
		<html:hidden name="PatientDeathDetailFB" property="deskType"/>
		<html:hidden name="PatientDeathDetailFB" property="patMlcNo"/>
		<html:hidden name="PatientDeathDetailFB" property="isInpatient"/>

<logic:equal name="PatientDeathDetailFB" property="isDirectCall" value="DIRECT">
	<his:status/>
	</form>
</logic:equal>

