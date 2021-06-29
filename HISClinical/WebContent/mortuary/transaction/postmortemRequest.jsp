<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script>
//window.history.go(1);

//window.history.foward()

window.onload=function()
{
	showPoliceVerification();
	DutyOfficerDetail();
	//if(document.getElementsByName("isMlc")[0].value==<%=MortuaryConfig.IS_MLC_NO%>)
		showDetail();
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getDeceasedDetail(obj)
{
	document.getElementsByName("patCrNo")[0].value=obj.value.split("@")[1];
	document.getElementsByName("deceasedNo")[0].value=obj.value.split("@")[0];
	submitForm('DECEASEDDTL')
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

function newDutyOfficerDetail()
{
	if(document.getElementsByName("newDutyOfficeFlag")[1].checked)
	{
		document.getElementById('newDutyOfficerDetailId').style.display="block";
	}
	else
	{
		document.getElementById('newDutyOfficerDetailId').style.display="none";
	}
}

function showPoliceVerification()
{
	if(document.getElementsByName("existPoliceVerFlag")[0].value==<%=MortuaryConfig.POLICE_VERIFICATION_EXIST_YES%>)
	{
		if(document.getElementsByName("policeVerExistNew")[1].checked)
		{
			document.getElementById("divNewPoliceCaseId").style.display="block";
			document.getElementById("divExistingPoliceCaseId").style.display="none";
			document.getElementsByName("newDutyOfficeFlag")[1].checked=true
			newDutyOfficerDetail();
		}
		if(document.getElementsByName("policeVerExistNew")[0].checked)
		{
			document.getElementById("divNewPoliceCaseId").style.display="none";
			document.getElementById("divExistingPoliceCaseId").style.display="block";
		}	
	}
	else
	{
			document.getElementById("divNewPoliceCaseId").style.display="block";
			document.getElementById("divExistingPoliceCaseId").style.display="none";
			document.getElementsByName("newDutyOfficeFlag")[1].checked=true
			newDutyOfficerDetail();
	}	
}

function validateSave()
{
	var valid=true;
	if(validateOpinion() && validatePoliceVer())
		valid=true;
	else
		valid=false;
	
	return valid;
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function validateOpinion()
{
	var valid;
	if(validateOpinionAdd() && validateRelativeAdd())
		valid=true;
	else
		valid=false;
	
	return valid;		
}

function validatePoliceVer()
{
	var valid=true;
	if(document.getElementsByName("isMlc")[0].value==<%= MortuaryConfig.IS_MLC_YES%>)
	{
		if(document.getElementsByName("existPoliceVerFlag")[0].value==<%=MortuaryConfig.POLICE_VERIFICATION_EXIST_YES%>)
		{
			if(document.getElementsByName("policeVerExistNew")[1].checked)
			{
				valid=validateNewPoliceVerification();
			}
			else
			{
				valid=validateExistingPoliceVerification();
			}	
		}
		else
			valid=validateNewPoliceVerification();
	}
	else
	{
		if(document.getElementsByName("postmortemType")[0].checked)
		{
			if(document.getElementsByName("existPoliceVerFlag")[0].value==<%=MortuaryConfig.POLICE_VERIFICATION_EXIST_YES%>)
			{
				if(document.getElementsByName("policeVerExistNew")[1].checked)
				{
					valid=validateNewPoliceVerification();
				}
				else
				{
					valid=validateExistingPoliceVerification();
				}	
			}
			else
				valid=validateNewPoliceVerification();
		}
		else
		{
			valid=validateRequestedByRelative();
		}
	}
	return valid;	
}

function validateRequestedByRelative()
{
	var valid=false;
	if(isEmpty(document.forms[0].requestRelativeName,"Relative Name")
		&& comboValidation(document.forms[0].requestRelativeCode,"Relationship")
		&& isEmpty(document.forms[0].requestRelativeAddress,"Relative Address")
		&& isEmpty(document.forms[0].requestRelativeContactNo,"Relative Contact No")
	)	
	{
		valid=true;
	}
	else
	{
		valid=false;
	}

	return valid;
}


function validateExistingPoliceVerification()
{
	var valid=false;
	if(isEmpty(document.forms[0].existingIncicenceDate,"Incidence Date")
		&& isEmpty(document.forms[0].existingIncicenceTimeHr,"Incidence Hour")
		&& isEmpty(document.forms[0].existingIncicenceTimeMin,"Incidence Minute")
		&& isEmpty(document.forms[0].existingDeathPlace,"Death Place")
		&& validateExistingIncidenceDate()
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}

	return valid;
}

function validateExistingIncidenceDate()
{
	var valid=true;
	var incidenceDate=document.getElementsByName("existingIncicenceDate")[0].value;
	var deathDate=document.getElementsByName("existingDeathDate")[0].value;
	var dHour=document.getElementsByName("existingDeathTimeHr")[0].value;
	var dMin=document.getElementsByName("existingDeathTimeMin")[0].value;
	
	
	days=parseInt(noOfDays(deathDate,incidenceDate));
	if(days==0)
	{
		if(parseInt(trimNum(document.getElementsByName("existingIncicenceTimeHr")[0].value)) > dHour)
		{
			alert("Incidence Date  Cannot be Greater Than "+deathDate+" "+dHour+":"+dMin);
			document.getElementsByName("existingIncicenceTimeHr")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("existingIncicenceTimeHr")[0].value)) >= dHour && parseInt(trimNum(document.getElementsByName("newIncicenceTimeMin")[0].value)) > dMin)
		{
			alert("Incidence Date  Cannot be Greater Than "+deathDate+" "+dHour+":"+dMin);
			document.getElementsByName("existingIncicenceTimeMin")[0].focus();
			valid=false;
		}
	}
	else
	{
		if(days<0)
		{
			alert("Incidence Date Cannot be Greater Than Death Date");
			document.getElementsByName("existingIncicenceDate")[0].focus();
			valid=false;
		}
		else
			valid=true;
	}
	
	return valid;
}

function validateNewPoliceVerification()
{
	var valid=false;
	if(isEmpty(document.forms[0].newCaseNo,"Case No")
		&& isEmpty(document.forms[0].newPoliceStation,"Police Station")
		&& isEmpty(document.forms[0].newDocketNo,"Docket No")
		&& isEmpty(document.forms[0].newOfficerIncharge,"Investigating Officer Name")
		&& isEmpty(document.forms[0].newIoDesignation,"Investigating Officer Designation")
		&& isEmpty(document.forms[0].newIoBatchNo,"Investigating Officer Badge No.")
		&& validateNewDutyOfficer()
		&& isEmpty(document.forms[0].newCaseRemarks,"Case Remarks")
		&& isEmpty(document.forms[0].newDeathDate,"Date of Death")
		&& isEmpty(document.forms[0].newDeathTimeHr,"Death Hour")
		&& isEmpty(document.forms[0].newDeathTimeMin,"Death Minute")
		&& isEmpty(document.forms[0].newIncicenceDate,"Incidence Date")
		&& isEmpty(document.forms[0].newIncicenceTimeHr,"Incidence Hour")
		&& isEmpty(document.forms[0].newIncicenceTimeMin,"Incidence Minute")
		&& isEmpty(document.forms[0].newDeathPlace,"Death Place")
		&& validateNewDeathDate()
		&& validateNewIncidenceDate()
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}

	return valid;
}

function validateNewDeathDate()
{
	var valid=true;
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var incidenceDate=document.getElementsByName("newIncicenceDate")[0].value;
	var deathDate=document.getElementsByName("newDeathDate")[0].value;
	var hour=document.getElementsByName("hiddenTimeHr")[0].value;
	var min=document.getElementsByName("hiddenTimeMin")[0].value;
	
	days=parseInt(noOfDays(sysDate,deathDate));
	if(days==0)
	{
		if(parseInt(trimNum(document.getElementsByName("newDeathTimeHr")[0].value)) > hour)
		{
			alert("Death Date  Cannot be Greater Than "+sysDate+" "+hour+":"+min);
			document.getElementsByName("newDeathTimeHr")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("newDeathTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("newDeathTimeMin")[0].value)) > min)
		{
			alert("Death Date  Cannot be Greater Than "+sysDate+" "+hour+":"+min);
			document.getElementsByName("newDeathTimeMin")[0].focus();
			valid=false;
		}
	}
	else
	{
		if(days<0)
		{
			alert("Death Date Cannot Be Greater Than Current Date");
			document.getElementsByName("newDeathDate")[0].focus();
			valid=false;
		}
		else
			valid=true;
	}
	
	return valid
}


function validateNewIncidenceDate()
{
	var valid=true;
	var incidenceDate=document.getElementsByName("newIncicenceDate")[0].value;
	var deathDate=document.getElementsByName("newDeathDate")[0].value;
	var dHour=document.getElementsByName("newDeathTimeHr")[0].value;
	var dMin=document.getElementsByName("newDeathTimeMin")[0].value;
	
	
	days=parseInt(noOfDays(deathDate,incidenceDate));
	if(days==0)
	{
		if(parseInt(trimNum(document.getElementsByName("newIncicenceTimeHr")[0].value)) > dHour)
		{
			alert("Incidence Date  Cannot be Greater Than "+deathDate+" "+dHour+":"+dMin);
			document.getElementsByName("newIncicenceTimeHr")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("newIncicenceTimeHr")[0].value)) >= dHour && parseInt(trimNum(document.getElementsByName("newIncicenceTimeMin")[0].value)) > dMin)
		{
			alert("Incidence Date  Cannot be Greater Than "+deathDate+" "+dHour+":"+dMin);
			document.getElementsByName("newIncicenceTimeMin")[0].focus();
			valid=false;
		}
	}
	else
	{
		if(days<0)
		{
			alert("Incidence Date Cannot be Greater Than Death Date");
			document.getElementsByName("newIncicenceDate")[0].focus();
			valid=false;
		}
		else
			valid=true;
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

function validateNewDutyOfficer()
{
	var valid=false;
	if(document.getElementsByName("newDutyOfficeFlag")[1].checked)
	{
		if(isEmpty(document.forms[0].newDutyOffName,"Duty Officer Name")
			&& isEmpty(document.forms[0].newDutyOffDesignation,"Duty Officer Designation")
			&& isEmpty(document.forms[0].newDutyOffBatchNo,"Duty Officer Badge No.")
		)
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

function clearForm()
{
	document.getElementsByName("requestedOpinion")[0].value="-1";
	document.getElementsByName("requestedItem")[0].value="-1";
	document.getElementsByName("opinionRemark")[0].value="";
	document.getElementsByName("deceasedItemRemarks")[0].value="";
	document.getElementsByName("newCaseNo")[0].value="";
	document.getElementsByName("newPoliceStation")[0].value="";
	document.getElementsByName("newDocketNo")[0].value="";
	document.getElementsByName("newOfficerIncharge")[0].value="";
	document.getElementsByName("newIoDesignation")[0].value="";
	document.getElementsByName("newIoBatchNo")[0].value="";
	document.getElementsByName("newDutyOffName")[0].value="";
	document.getElementsByName("newDutyOffDesignation")[0].value="";
	document.getElementsByName("newDutyOffBatchNo")[0].value="";
	document.getElementsByName("newCaseRemarks")[0].value="";
	
	document.getElementsByName("newDeathDate")[0].value="";
	document.getElementsByName("newDeathTimeHr")[0].value="";
	document.getElementsByName("newDeathTimeMin")[0].value="";
	document.getElementsByName("newIncicenceDate")[0].value="";
	document.getElementsByName("newIncicenceTimeHr")[0].value="";
	document.getElementsByName("newIncicenceTimeMin")[0].value="";
	document.getElementsByName("newDeathPlace")[0].value="";
	
	document.getElementsByName("existingIncicenceDate")[0].value="";
	document.getElementsByName("existingIncicenceTimeHr")[0].value="";
	document.getElementsByName("existingIncicenceTimeMin")[0].value="";
	document.getElementsByName("existingDeathPlace")[0].value="";
	
	document.getElementsByName("identifyRelativeName")[0].value="";
	document.getElementsByName("identifyRelativeAddress")[0].value="";
	document.getElementsByName("identifyRelativeCode")[0].value="-1";
	document.getElementsByName("identifyRelativeContactNo")[0].value="";
	
	/*for(var i=0;i<document.getElementsByName("chkOpinion").length;i++ )
	{
		document.getElementsByName("chkOpinion")[i].checked=false;
	}
	for(var i=0;i<document.getElementsByName("chkDeceasedItem").length;i++ )
	{
		document.getElementsByName("chkDeceasedItem")[i].checked=false;
	}*/
	for(var i=0;i<document.getElementsByName("chkRelative").length;i++ )
	{
		document.getElementsByName("chkRelative")[i].checked=false;
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

function showDetail()
{
	if(document.getElementsByName("isMlc")[0].value==<%=MortuaryConfig.IS_MLC_NO%>)
	{
		if(document.getElementsByName("postmortemType")[0].checked)
		{
			document.getElementById("divForensic").style.display="block";
			document.getElementById("divClinical").style.display="none";
		}
		else
		{
			document.getElementById("divForensic").style.display="none";
			document.getElementById("divClinical").style.display="block";
		}
	}
	else
	{
		document.getElementById("divForensic").style.display="block";
		document.getElementById("divClinical").style.display="none";
	}	
}

function validateOpinionAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].requestedOpinion,"Opinion For"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function deleteOpinionRow(obj1,obj2)
{
	document.getElementsByName("hiddenOpinionCode")[0].value=obj1;
	document.getElementsByName("hiddenOpinionName")[0].value=obj2;
	submitForm('DELETEOPINION')
}

function validateItemAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].requestedItem,"Item Name"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function deleteItemRow(obj1,obj2)
{
	document.getElementsByName("hiddenItemCode")[0].value=obj1;
	document.getElementsByName("hiddenItemName")[0].value=obj2;
	submitForm('DELETEITEM')
}

function deleteRelativeRow(obj)
{
	document.getElementsByName("relativeIndex")[0].value=obj;
	submitForm('DELETERELATIVE');
}

function validateRelativeAdd()
{
	if(document.getElementsByName("isMlc")[0].value==<%=MortuaryConfig.IS_MLC_YES%>)
	{
		var valid=true;
		var rName=document.getElementsByName("identifyRelativeName")[0].value;
		var rAdd=document.getElementsByName("identifyRelativeAddress")[0].value;
		var rCode=document.getElementsByName("identifyRelativeCode")[0].value;
		var rNo=document.getElementsByName("identifyRelativeContactNo")[0].value;
		
		if(rName!="" || rAdd!="" || rCode!="-1" || rNo!="")
		{
			if(isEmpty(document.forms[0].identifyRelativeName,"Relative Name")
				&& comboValidation(document.forms[0].identifyRelativeCode,"Relationship")
				&& isEmpty(document.forms[0].identifyRelativeAddress,"Relative Address")
				&& isEmpty(document.forms[0].identifyRelativeContactNo,"Relative Contact No")
			)	
			{
				valid=true;
			}
			else
			{
				valid=false;
			}
		}
		else
			valid=true;
	}
	else
	{
		if(document.getElementsByName("postmortemType")[0].checked)
		{
			var valid=true;
			var rName=document.getElementsByName("identifyRelativeName")[0].value;
			var rAdd=document.getElementsByName("identifyRelativeAddress")[0].value;
			var rCode=document.getElementsByName("identifyRelativeCode")[0].value;
			var rNo=document.getElementsByName("identifyRelativeContactNo")[0].value;
			
			if(rName!="" || rAdd!="" || rCode!="-1" || rNo!="")
			{
				if(isEmpty(document.forms[0].identifyRelativeName,"Relative Name")
					&& comboValidation(document.forms[0].identifyRelativeCode,"Relationship")
					&& isEmpty(document.forms[0].identifyRelativeAddress,"Relative Address")
					&& isEmpty(document.forms[0].identifyRelativeContactNo,"Relative Contact No")
				)	
				{
					valid=true;
				}
				else
				{
					valid=false;
				}
			}
			else
				valid=true;
		}
		else
			valid=true;
	}
	return valid;	
}

function validateRelativeRowAdd()
{
	if(isEmpty(document.forms[0].identifyRelativeName,"Relative Name")
		&& comboValidation(document.forms[0].identifyRelativeCode,"Relationship")
		&& isEmpty(document.forms[0].identifyRelativeAddress,"Relative Address")
		&& isEmpty(document.forms[0].identifyRelativeContactNo,"Relative Contact No")
	)	
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function viewConsent()
{
	var tempId=document.getElementsByName("templateId")[0].value;
	var path='/HISClinical/mortuary/postmortemRequest.cnt?hmode=VIEWCONSENT&templateId='+tempId;
	openPrintPopup(path,300,700);
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
}
</script>

<body>
	<html:form action="/postmortemRequest">
		<his:TransactionContainer>
		
			<his:TitleTag name="Deceased Postmortem Request">
			</his:TitleTag>
			<his:statusList>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceasedNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceased"/>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceased"/>
											<bean:message key="type"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deathdate"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="receiveDate"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
						<logic:iterate id="arrDeceasedListVO" name="<%=MortuaryConfig.DECEASED_LIST_FOR_POSTMORTEM_REQUEST %>" type="hisglobal.vo.DeceasedDetailVO" indexId="idx">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:radio name="PostmortemRequestFB" property="selectedDeceased" value="<%=arrDeceasedListVO.getDeceasedNo()+'@'+ arrDeceasedListVO.getPatCrNo()%>" onclick="getDeceasedDetail(this)" tabindex="1" ></html:radio>
									
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedNo() %>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPatFirstName()==null?"":arrDeceasedListVO.getPatFirstName() %>
											<%=arrDeceasedListVO.getPatMiddleName()==null?"":arrDeceasedListVO.getPatMiddleName() %>
											<%=arrDeceasedListVO.getPatLastName()==null?"":arrDeceasedListVO.getPatLastName() %>
										</font>	
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedType() %>
										</font>	
									</div>
								</td>
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeathDate() %>
										</font>	
									</div>
								</td>
								
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getReceivedDateTime() %>
										</font>	
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			</his:statusList>
			
			
			<his:statusTransactionInProcess>
				<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
				
				 <logic:equal name="PostmortemRequestFB" property="isMlc" value="<%=MortuaryConfig.IS_MLC_NO %>" >
				 	<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="postmortemType"/>
										</font>
									</div> 
								</td>
								<td width="75%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="forensic"/>
										</font>
										<html:radio name="PostmortemRequestFB" property="postmortemType" value="<%=MortuaryConfig.POSTMORTEM_TYPE_FORENSIC %>" onclick="showDetail()" tabindex="1" ></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="clinical"/>
										</font>
										<html:radio name="PostmortemRequestFB" property="postmortemType" value="<%=MortuaryConfig.POSTMORTEM_TYPE_CLINICAL %>" onclick="showDetail()" tabindex="1"></html:radio>
									</div>
								</td>	
							</tr>
						</table>
					</his:ContentTag>	
				</logic:equal>
				<logic:equal name="PostmortemRequestFB" property="isMlc" value="<%=MortuaryConfig.IS_MLC_YES %>" >
					<html:hidden name="PostmortemRequestFB" property="postmortemType" value="<%=MortuaryConfig.POSTMORTEM_TYPE_FORENSIC %>"/>
				</logic:equal>
				
				<his:SubTitleTag name="Opinion For">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="30%" class="tdfonthead" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
										    <font color="#FF0000">*</font>
											<bean:message key="opinion"/>
											<bean:message key="for"/>
										</B>
									</font>	
								</div>
							</td>
							<td width="65%" class="tdfonthead" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="opinion"/>
											<bean:message key="remarks"/>
										</B>
									</font>	
								</div>
							</td>
							<td width="5%" class="tdfonthead" >
								<div align="center">
									
								</div>
							</td>
						</tr>
						<tr>
							<td width="30%" class="tdfont" >
								<div align="center">
									<html:select name="PostmortemRequestFB"  property="requestedOpinion" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_OPINION%>">
										<html:options collection="<%= MortuaryConfig.ESSENTIAL_ALL_OPINION%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="65%" class="tdfont" >
								<div align="center">
									<html:textarea name="PostmortemRequestFB" property="opinionRemark" rows="1" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea> 
								</div>
							</td>
							<td width="5%" class="tdfont" >
								<div align="center">
									<img class="button" id="addButton" style="cursor:pointer" tabindex="1" src='<his:path src="/hisglobal/images/plus.gif"/>' onkeypress="if(event.keyCode==13)if(validateOpinionAdd()) submitForm('ADDOPINION') ;" onclick="if(validateOpinionAdd()) submitForm('ADDOPINION')" tabindex="1" >
								</div>
							</td>
						</tr>
					</table>	
				</his:ContentTag>
				<%if(session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO)!=null){ %>
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<logic:iterate id="opinionReqVO" name="<%=MortuaryConfig.ARR_OPINION_REQUEST_VO %>" type="hisglobal.vo.PostmortemOpinionReqDtlVO">
							<tr>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=opinionReqVO.getOpinionName() %>
									</div>
								</td>
								<td class="tdfont" width="65%" >
									<div align="center">
										<%=opinionReqVO.getRemarks() %>
									</div>
								</td>
								<td class="tdfont" width="5%" >
									<div align="center">
										<img class="button" style="cursor:pointer" tabindex="1" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteOpinionRow('<%=opinionReqVO.getOpinionCode() %>','<%=opinionReqVO.getOpinionName() %>') ;" onclick=" deleteOpinionRow('<%=opinionReqVO.getOpinionCode() %>','<%=opinionReqVO.getOpinionName() %>')" tabindex="1" >
										<html:hidden name="PostmortemRequestFB" property="hiddenOpinionCode"/>
										<html:hidden name="PostmortemRequestFB" property="hiddenOpinionName"/>
									</div>
								</td>
							</tr>	
						</logic:iterate>
					</table>
				</his:ContentTag>	
				<%} %>
			
			<div id="divForensic">	
				<his:SubTitleTag name="Police Verification Detail">
					<logic:equal name="PostmortemRequestFB" property="existPoliceVerFlag" value="<%=MortuaryConfig.POLICE_VERIFICATION_EXIST_YES %>">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" >
									<div align="right">
										<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="exist"/>
											</b>	
										</font>
										<html:radio name="PostmortemRequestFB" property="policeVerExistNew" value="<%=MortuaryConfig.POLICE_VERIFICATION_EXISTING%>" onclick="showPoliceVerification()" tabindex="1" ></html:radio>
										<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="new"/>
											</b>
										</font>	
										<html:radio name="PostmortemRequestFB" property="policeVerExistNew" value="<%=MortuaryConfig.POLICE_VERIFICATION_NEW%>" onclick="showPoliceVerification()" tabindex="1"  ></html:radio>
									</div>
								</td>
							</tr>
						</table>	
					</logic:equal>
					
				</his:SubTitleTag>
				
				<div id="divExistingPoliceCaseId" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policecaseno" />
											</font>
										</div>	
									</td>
					
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="PostmortemRequestFB" tabindex="1" maxlength="50" property="caseNo"  readonly="true"  />
									</td>
					
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policestation" />
											</font>
										</div>	
									</td>
					
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="PostmortemRequestFB" tabindex="1" property="policeStation" maxlength="100" readonly="true"   />
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="docketNo" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="PostmortemRequestFB" property="docketNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="name" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="PostmortemRequestFB" property="officerIncharge" maxlength="60" readonly="true" ></html:text>
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="designation" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="PostmortemRequestFB" property="ioDesignation" maxlength="50" readonly="true"></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="batchno" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="PostmortemRequestFB" property="ioBatchNo" maxlength="50" readonly="true"></html:text>
									</td>
								</tr>
								
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="dutyOfficer" />
												<bean:message key="detail" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
										<html:radio name="PostmortemRequestFB" property="dutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_IO %>" disabled="true"></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="io" />
										</font>		
										<html:radio name="PostmortemRequestFB" property="dutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_OTHER %>" disabled="true"></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="other" />
										</font>
									</td>
								</tr>
							</table>
								
							<div id="dutyOfficerDetailId">
								<table width="100%" cellspacing="1" cellpadding="0">
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="name" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="PostmortemRequestFB" property="dutyOffName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" readonly="true"></html:text>
										</td>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="designation" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="PostmortemRequestFB" property="dutyOffDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="batchno" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
											<html:text name="PostmortemRequestFB" property="dutyOffBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true"></html:text>
										</td>
									
									</tr>
								</table>
							</div>
							
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td colspan="4">
										<table width="100%" cellpadding="0" cellspacing="1">
											<tr>
												<td nowrap="nowrap" class="tdfonthead" width="30%">
													<div align="right">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<font color="#FF0000">*</font>
															<bean:message key="caseremarks" />
														</font>
													</div>	
												</td>
								
												<td colspan="2" nowrap="nowrap" width="70%" class="tdfont">
													<html:textarea name="PostmortemRequestFB" tabindex="1" property="caseRemarks" rows="1" cols="90" 
														onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" readonly="true" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="incidenceDate"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<his:date name="existingIncicenceDate" dateFormate="%d-%b-%Y" />
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="incidenceTime"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="PostmortemRequestFB" tabindex="1" property="existingIncicenceTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
												<b>
													<bean:message key="colon"/>
												</b>
											<html:text name="PostmortemRequestFB" tabindex="1" property="existingIncicenceTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('existingIncicenceTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
											<bean:message key="timeFormat"/>	
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="deathPlace"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="PostmortemRequestFB" property="existingDeathPlace" maxlength="100" tabindex="1"></html:text>
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
					
					<div id="divNewPoliceCaseId" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policecaseno" />
											</font>
										</div>	
									</td>
					
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="PostmortemRequestFB"  maxlength="50" property="newCaseNo" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" />
									</td>
					
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policestation" />
											</font>
										</div>	
									</td>
					
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="PostmortemRequestFB"  property="newPoliceStation" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" />
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="docketNo" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="PostmortemRequestFB" property="newDocketNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="name" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="PostmortemRequestFB" property="newOfficerIncharge" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="designation" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="PostmortemRequestFB" property="newIoDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="batchno" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="PostmortemRequestFB" property="newIoBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
								</tr>
								
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="dutyOfficer" />
												<bean:message key="detail" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
										<html:radio name="PostmortemRequestFB" property="newDutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_IO %>" onclick="newDutyOfficerDetail()" tabindex="1" ></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="io" />
										</font>		
										<html:radio name="PostmortemRequestFB" property="newDutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_OTHER %>" onclick="newDutyOfficerDetail()" tabindex="1" ></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="other" />
										</font>
									</td>
								</tr>
							</table>
								
							<div id="newDutyOfficerDetailId">
								<table width="100%" cellspacing="1" cellpadding="0">
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="name" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="PostmortemRequestFB" property="newDutyOffName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
										</td>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="designation" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="PostmortemRequestFB" property="newDutyOffDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
										</td>
									</tr>
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="batchno" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
											<html:text name="PostmortemRequestFB" property="newDutyOffBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
										</td>
									
									</tr>
								</table>
							</div>
							
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td colspan="4">
										<table width="100%" cellpadding="0" cellspacing="1">
											<tr>
												<td nowrap="nowrap" class="tdfonthead" width="30%">
													<div align="right">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<font color="#FF0000">*</font>
															<bean:message key="caseremarks" />
														</font>
													</div>	
												</td>
								
												<td colspan="2" nowrap="nowrap" width="70%" class="tdfont">
													<html:textarea name="PostmortemRequestFB" tabindex="1" property="newCaseRemarks" rows="1" cols="90" 
														onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="deathdate"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<his:date name="newDeathDate" dateFormate="%d-%b-%Y" />
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="deathtime"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="PostmortemRequestFB" tabindex="1" property="newDeathTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
												<b>
													<bean:message key="colon"/>
												</b>
											<html:text name="PostmortemRequestFB" tabindex="1" property="newDeathTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('newDeathTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
											<bean:message key="timeFormat"/>	
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="incidenceDate"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<his:date name="newIncicenceDate" dateFormate="%d-%b-%Y" />
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="incidenceTime"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="PostmortemRequestFB" tabindex="1" property="newIncicenceTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
												<b>
													<bean:message key="colon"/>
												</b>
											<html:text name="PostmortemRequestFB" tabindex="1" property="newIncicenceTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('newIncicenceTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
											<bean:message key="timeFormat"/>	
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="deathPlace"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="PostmortemRequestFB" property="newDeathPlace" maxlength="100" tabindex="1" ></html:text>
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
					<his:SubTitleTag name="Relative Who Identifying The Body">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="name"/>
											</b>
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="realtionship"/>
											</b>
										</font>
									</div>
								</td>
								<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="address"/>
											</b>
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="contactNo"/>
											</b>
										</font>
									</div>
								</td>
								<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="select"/>
											</b>
										</font>
									</div>
								</td>
							</tr>
							<logic:present name="<%=MortuaryConfig.ARR_DECEASED_EXISTING_RELATIVE_VO %>">
								<logic:iterate name="<%=MortuaryConfig.ARR_DECEASED_EXISTING_RELATIVE_VO %>" id="relativeVO" type="hisglobal.vo.DeceasedRelativeDtlVO" indexId="index">
									<tr>
										<td width="25%" class="tdfont">
											<div align="center">
												<%=relativeVO.getRelativeName() %>
											</div>	
										</td>
										<td width="15%" class="tdfont">
											<div align="center">
												<%=relativeVO.getRelativeCodeName() %>
											</div>	
										</td>
										<td width="40%" class="tdfont">
											<div align="center">
												<%=relativeVO.getRelativeAddress() %>
											</div>	
										</td>
										<td width="15%" class="tdfont">
											<div align="center">
												<%=relativeVO.getRelativeContactNo()==null?"-":relativeVO.getRelativeContactNo() %>
											</div>	
										</td>
										<td width="5%" class="tdfont">
											<div align="center">
												<html:checkbox name="PostmortemRequestFB" property="chkRelative" value="<%=index.toString() %>" tabindex="1"></html:checkbox>
											</div>	
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
					</his:ContentTag>
						<his:ContentTag>	
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfont">
										<div align="center">
											<html:text name="PostmortemRequestFB" property="identifyRelativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
										</div>	
									</td>
									<td width="15%" class="tdfont">
										<div align="center">
											<html:select name="PostmortemRequestFB" property="identifyRelativeCode" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
													<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
										</div>	
									</td>
									<td width="40%" class="tdfont">
										<div align="center">
											<html:text name="PostmortemRequestFB" property="identifyRelativeAddress" maxlength="100" size="50" tabindex="1"></html:text>
										</div>	
									</td>
									<td width="15%" class="tdfont">
										<div align="center">
											<html:text name="PostmortemRequestFB" property="identifyRelativeContactNo" maxlength="10" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
										</div>	
									</td>
									<td width="5%" class="tdfont">
										<div align="center">
											<img class="button" id="addButton" style="cursor:pointer" tabindex="1" src='<his:path src="/hisglobal/images/plus.gif"/>' onkeypress="if(event.keyCode==13)if(validateRelativeRowAdd()) submitForm('ADDRELATIVE') ;" onclick="if(validateRelativeRowAdd()) submitForm('ADDRELATIVE')" tabindex="1" >
										</div>	
									</td>
								</tr>
							</table>	
						</his:ContentTag>
						<%if(session.getAttribute(MortuaryConfig.ARR_DECEASED_ADDED_RELATIVE_VO)!=null){ %>
						<his:ContentTag>
							<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
								<logic:iterate id="addedRelativeVO" name="<%=MortuaryConfig.ARR_DECEASED_ADDED_RELATIVE_VO %>" type="hisglobal.vo.DeceasedRelativeDtlVO" indexId="idx">
									<tr>
										<td class="tdfont" width="25%" >
											<div align="center">
												<%=addedRelativeVO.getRelativeName() %>
											</div>
										</td>
										<td class="tdfont" width="15%" >
											<div align="center">
												<%=addedRelativeVO.getRelativeCodeName() %>
											</div>
										</td>
										<td class="tdfont" width="40%" >
											<div align="center">
												<%=addedRelativeVO.getRelativeAddress() %>
											</div>
										</td>
										<td class="tdfont" width="15%" >
											<div align="center">
												<%=addedRelativeVO.getRelativeContactNo() %>
											</div>
										</td>
										<td class="tdfont" width="5%" >
											<div align="center">
												<img class="button" style="cursor:pointer" tabindex="1" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRelativeRow('<%=idx.toString() %>') ;" onclick=" deleteRelativeRow('<%=idx.toString() %>')" tabindex="1" >
												<html:hidden name="PostmortemRequestFB" property="relativeIndex"/>
											</div>
										</td>
									</tr>	
								</logic:iterate>
							</table>
						</his:ContentTag>	
						<%} %>
				</div>	
				
				<div id="divClinical">
					<his:SubTitleTag name="Postmortem Requested By">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="relativename"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PostmortemRequestFB" property="requestRelativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="realtionship"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="PostmortemRequestFB" property="requestRelativeCode" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
												<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
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
											<bean:message key="relativeaddress"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:textarea name="PostmortemRequestFB" property="requestRelativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="relativeContactNo"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PostmortemRequestFB" property="requestRelativeContactNo" maxlength="10" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
									</div>
								</td>
							</tr>
							<logic:equal name="PostmortemRequestFB" property="isConsentRequired" value="<%=MortuaryConfig.YES %>">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="isConsentTaken"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:checkbox name="PostmortemRequestFB" property="isConsentTaken" tabindex="1"></html:checkbox>
										</div>
									</td>
									<td width="25%" class="tdfont" colspan="2">
										<div align="center">
											<a style="cursor:pointer" tabindex="1" onclick="viewConsent()"  >
												View Consent
											</a>
										</div>
									</td>
								</tr>
							</logic:equal>
						</table>
					</his:ContentTag>
				</div>
				
				
				<his:SubTitleTag name="Deceased Item To Be Preserved">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="30%" class="tdfonthead" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="itemName"/>
										</B>
									</font>	
								</div>
							</td>
							<td width="65%" class="tdfonthead" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="item"/>
											<bean:message key="remarks"/>
										</B>
									</font>	
								</div>
							</td>
							<td width="5%" class="tdfonthead" >
								<div align="center">
									
								</div>
							</td>
						</tr>
						<tr>
							<td width="30%" class="tdfont" >
								<div align="center">
									<html:select name="PostmortemRequestFB"  property="requestedItem" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_DECEASED_ITEM%>">
										<html:options collection="<%= MortuaryConfig.ESSENTIAL_ALL_DECEASED_ITEM%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="65%" class="tdfont" >
								<div align="center">
									<html:textarea name="PostmortemRequestFB" property="deceasedItemRemarks" rows="1" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea> 
								</div>
							</td>
							<td width="5%" class="tdfont" >
								<div align="center">
									<img class="button" id="addButton" style="cursor:pointer" tabindex="1" src='<his:path src="/hisglobal/images/plus.gif"/>' onkeypress="if(event.keyCode==13)if(validateItemAdd()) submitForm('ADDITEM') ;" onclick="if(validateItemAdd()) submitForm('ADDITEM')" tabindex="1" >
								</div>
							</td>
						</tr>
					</table>	
				</his:ContentTag>
				<%if(session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO)!=null){ %>
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<logic:iterate id="itemReqVO" name="<%=MortuaryConfig.ARR_ITEM_REQUEST_VO %>" type="hisglobal.vo.PostmortemItemReqDtlVO">
							<tr>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=itemReqVO.getItemName() %>
									</div>
								</td>
								<td class="tdfont" width="65%" >
									<div align="center">
										<%=itemReqVO.getRemarks() %>
									</div>
								</td>
								<td class="tdfont" width="5%" >
									<div align="center">
										<img class="button" style="cursor:pointer" tabindex="1" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteItemRow('<%=itemReqVO.getItemCode() %>','<%=itemReqVO.getItemName() %>') ;" onclick=" deleteItemRow('<%=itemReqVO.getItemCode() %>','<%=itemReqVO.getItemName() %>')" tabindex="1" >
										<html:hidden name="PostmortemRequestFB" property="hiddenItemCode"/>
										<html:hidden name="PostmortemRequestFB" property="hiddenItemName"/>
									</div>
								</td>
							</tr>	
						</logic:iterate>
					</table>
				</his:ContentTag>	
				<%} %>
				
			</his:statusTransactionInProcess>	
			<bean:define name="PostmortemRequestFB" property="sysDate" id="sysDate" type="java.lang.String" />
	<%
		if(sysDate==null||sysDate.equalsIgnoreCase(""))
		{
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
	%>
	<html:hidden name="PostmortemRequestFB" property="sysDate" value="<%=sysDate %>" />
			
			<his:ButtonToolBarTag>
					<his:statusList>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
					</his:statusList>
						
					<his:statusUnsuccessfull>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('FINALCANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					</his:statusUnsuccessfull>
					
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
					</his:statusTransactionInProcess>
			
				</his:ButtonToolBarTag>
		
		</his:TransactionContainer>
		
		<html:hidden name="PostmortemRequestFB" property="hmode"/>
		<html:hidden name="PostmortemRequestFB" property="patCrNo" />
		<html:hidden name="PostmortemRequestFB" property="deceasedNo" />
		<html:hidden name="PostmortemRequestFB" property="existPoliceVerFlag" />
		<html:hidden name="PostmortemRequestFB" property="policeVerExistNew" />
		<html:hidden name="PostmortemRequestFB" property="deathDateTime" />
		<html:hidden name="PostmortemRequestFB" property="hiddenTimeHr"/>
		<html:hidden name="PostmortemRequestFB" property="hiddenTimeMin"/>
		<html:hidden name="PostmortemRequestFB" property="existingDeathDate"/>
		<html:hidden name="PostmortemRequestFB" property="existingDeathTimeHr"/>
		<html:hidden name="PostmortemRequestFB" property="existingDeathTimeMin"/>
		<html:hidden name="PostmortemRequestFB" property="isMlc"/>
		<html:hidden name="PostmortemRequestFB" property="isConsentRequired"/>
		<html:hidden name="PostmortemRequestFB" property="templateId"/>
		
		
	</html:form>
	<his:status/>

</body>


</html>