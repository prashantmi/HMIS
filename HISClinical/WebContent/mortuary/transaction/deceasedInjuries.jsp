<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="mortuary.MortuaryConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

window.onload = function() 
{
	if(document.getElementsByName("injuryExistingFlag")[0].value==<%=MortuaryConfig.YES%>)
	{
		if(document.getElementsByName("weaponUsedValue")[0].value==<%=MortuaryConfig.YES%>)
			document.getElementsByName("weaponUsed")[0].checked=true;
		if(document.getElementsByName("injuryPhotoValue")[0].value==<%=MortuaryConfig.YES%>)
			document.getElementsByName("injuryPhoto")[0].checked=true;	
			
		document.getElementsByName("weaponUsed")[0].disabled=true;
		document.getElementsByName("injuryPhoto")[0].disabled=true;	
	}
	showWeaponRemarks();
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateSave()
{
	var valid = true;
	if(comboValidation(document.forms[0].injuryNatureCode,"Nature Of Injury")
		&& comboValidation(document.forms[0].injuryTypeCode,"Type Of Injury")
		&& isEmpty(document.forms[0].injuryDate,"Injury Date")
		&& isEmpty(document.forms[0].injuryTimeHr,"Injury Time Hour")
		&& isEmpty(document.forms[0].injuryTimeMin,"Injury Time Minute")
		&& isEmpty(document.forms[0].injuryRemarks,"Injury Remarks")
		&& validateInjuryDate()
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
		
	return false;	
}

function validateInjuryDate()
{
	var valid=false;
	var injuryDate=document.getElementsByName("injuryDate")[0].value;
	var injuryTimeHr=document.getElementsByName("injuryTimeHr")[0].value;
	var injuryTimeMin=document.getElementsByName("injuryTimeMin")[0].value;
	var deathDate=document.getElementsByName("deathDate")[0].value;
	var deathTimeHr=document.getElementsByName("deathTimeHr")[0].value;
	var deathTimeMin=document.getElementsByName("deathTimeMin")[0].value;
	
	day=parseInt((noOfDays(deathDate,injuryDate)));
	
	if(day>0)
	{
		valid=true;
	}
	else
	{
		if(day==0)
		{
			if(parseInt(deathTimeHr) < parseInt(injuryTimeHr))
			{
				alert("Injury Date Cannot Be Greater Than "+deathDate+" "+deathTimeHr+":"+deathTimeMin)
				document.getElementsByName("deathTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(deathTimeHr) <= parseInt(injuryTimeHr) && parseInt(deathTimeMin) < parseInt(injuryTimeMin))
			{
				alert("Injury Date Cannot Be Greater Than "+deathDate+" "+deathTimeHr+":"+deathTimeMin)
				document.getElementsByName("deathTimeMin")[0].focus();
				valid=false;
			}
		}
		else
		{
			alert("Injury Date Cannot Be Greater Than "+deathDate+" "+deathTimeHr+":"+deathTimeMin);
			document.getElementsByName("deathDate")[0].focus();
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

function clearForm()
{
	//document.getElementsByName("deathMannerCode")[0].value="-1";
	document.getElementsByName("injuryNatureCode")[0].value="-1";
	document.getElementsByName("injuryTypeCode")[0].value="-1";
	document.getElementsByName("injuryDate")[0].value="";
	document.getElementsByName("injuryTimeHr")[0].value="";
	document.getElementsByName("injuryTimeMin")[0].value="";
	document.getElementsByName("weaponRemarks")[0].value="";
	document.getElementsByName("injuryRemarks")[0].value="";
	document.getElementsByName("injuryPhoto")[0].checked=false;
	document.getElementsByName("weaponUsed")[0].checked=false;
}

function showWeaponRemarks()
{
	if(document.getElementsByName("weaponUsed")[0].checked)
		document.getElementById("weaponRemarksId").style.display="block";
	else
		document.getElementById("weaponRemarksId").style.display="none";
}

</script>

<his:TransactionContainer>
	<his:TitleTag name="Deceased Injury Datail">
	</his:TitleTag>
	
	<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
	
	<his:statusTransactionInProcess>
		<his:SubTitleTag name="Deceased Injury">
		</his:SubTitleTag>
		<%boolean readonly=false; %>
		<logic:equal name="DeceasedInjuriesFB" property="injuryExistingFlag" value="<%=MortuaryConfig.YES %>">
		<%readonly=true; %>
		</logic:equal>
		<logic:equal name="DeceasedInjuriesFB" property="injuryExistingFlag" value="<%=MortuaryConfig.NO %>">
			<%readonly=false; %>
		</logic:equal>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="natureInjury"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:select name="DeceasedInjuriesFB"  property="injuryNatureCode" disabled="<%=readonly %>">
								<html:option value="-1">select Value</html:option>
								<logic:present name="<%=MortuaryConfig.ESSENTIAL_INJURY_NATURE_LIST%>">
									<html:options collection="<%=MortuaryConfig.ESSENTIAL_INJURY_NATURE_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="typeOfInjury"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:select name="DeceasedInjuriesFB"  property="injuryTypeCode" disabled="<%=readonly %>">
								<html:option value="-1">select Value</html:option>
								<logic:present name="<%=MortuaryConfig.ESSENTIAL_INJURY_TYPE_LIST%>">
									<html:options collection="<%=MortuaryConfig.ESSENTIAL_INJURY_TYPE_LIST%>" property="value" labelProperty="label" />
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
								<bean:message key="injury"/>
								<bean:message key="date"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" >
						<div align="left">
							<logic:equal name="DeceasedInjuriesFB" property="injuryExistingFlag" value="<%=MortuaryConfig.YES %>">
								<html:text name="DeceasedInjuriesFB" property="injuryDate" readonly="<%=readonly %>"></html:text>
							</logic:equal>
							<logic:equal name="DeceasedInjuriesFB" property="injuryExistingFlag" value="<%=MortuaryConfig.NO %>">
								<his:date name="injuryDate" dateFormate="%d-%b-%Y"/>
							</logic:equal>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="injury"/>
								<bean:message key="time"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:text name="DeceasedInjuriesFB" tabindex="1" property="injuryTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" readonly="<%=readonly %>"/>
							<bean:message key="colon"/>
							<html:text name="DeceasedInjuriesFB" tabindex="1" property="injuryTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('injuryTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)" readonly="<%=readonly %>"/>
							<bean:message key="timeFormat"/>	
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="weaponUsed"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:checkbox name="DeceasedInjuriesFB" property="weaponUsed" onclick="showWeaponRemarks()"></html:checkbox>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="injuryPhotoTaken"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:checkbox name="DeceasedInjuriesFB" property="injuryPhoto"></html:checkbox>
						</div>
					</td>
				</tr>	
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="injuryRemarks"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" colspan="3">
						<div align="left">
							<html:textarea name="DeceasedInjuriesFB" property="injuryRemarks" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" readonly="<%=readonly %>"></html:textarea>
						</div>
					</td>
				</tr>
			</table>	
			<div id="weaponRemarksId">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right" id="weaponRemarksLabel">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="weaponRemarks"/>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" id="weaponRemarksControl">
							<div align="left" >
								<html:textarea name="DeceasedInjuriesFB" property="weaponRemarks" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" readonly="<%=readonly %>"></html:textarea>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
	</his:statusTransactionInProcess>	
	
	<his:ButtonToolBarTag>
		
		<his:statusTransactionInProcess>
			<logic:equal name="DeceasedInjuriesFB" property="injuryExistingFlag" value="<%=MortuaryConfig.NO %>">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</logic:equal>
			<logic:equal name="DeceasedInjuriesFB" property="injuryExistingFlag" value="<%=MortuaryConfig.YES %>">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
			</logic:equal>
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
	
	</his:ButtonToolBarTag>

</his:TransactionContainer>
		<html:hidden name="DeceasedInjuriesFB" property="hmode"/>
		<html:hidden name="DeceasedInjuriesFB" property="deceasedNo"/>
		<html:hidden name="DeceasedInjuriesFB" property="injuryExistingFlag"/>
		<html:hidden name="DeceasedInjuriesFB" property="weaponUsedValue"/>
		<html:hidden name="DeceasedInjuriesFB" property="injuryPhotoValue"/>
		<html:hidden name="DeceasedInjuriesFB" property="deathDate"/>
		<html:hidden name="DeceasedInjuriesFB" property="deathTimeHr"/>
		<html:hidden name="DeceasedInjuriesFB" property="deathTimeMin"/>
		