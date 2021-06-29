<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">



function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateSave()
{
	var valid=true;
	if(isEmpty(document.forms[0].startDateTimeHr,"Start Time Hour")
		&& isEmpty(document.forms[0].startDateTimeMin,"Start Time Minute")
		&& isEmpty(document.forms[0].endDateTimeHr,"End Time Hour")
		&& isEmpty(document.forms[0].endDateTimeMin,"End Time Minute")
		&& comboValidation(document.forms[0].incisionType,"Incision Type")
		&& isEmpty(document.forms[0].deathDate,"Actual Date of Death")
		&& isEmpty(document.forms[0].deathTimeHr,"Actual Death Time Hour")
		&& isEmpty(document.forms[0].deathTimeMin,"Actual Death Time Minute")
		/*&& comboValidation(document.forms[0].conductBy,"Conduct By")
		&& isEmpty(document.forms[0].complexion,"Complexion")
		&& isEmpty(document.forms[0].hairColorLength,"Hair Color Length")
		&& isEmpty(document.forms[0].eyeColor,"Eye Color")
		&& isEmpty(document.forms[0].length1,"length")
		&& isEmpty(document.forms[0].length2,"length")
		&& isEmpty(document.forms[0].weight1,"weight")
		&& isEmpty(document.forms[0].weight2,"weight")
		&& comboValidation(document.forms[0].bodyLooks,"Body Looks")
		&& isEmpty(document.forms[0].cynosis,"Cynosis")
		&& isEmpty(document.forms[0].pupilRight,"Pupils Right")
		&& isEmpty(document.forms[0].pupilLeft,"Pupils Left")
		&& isEmpty(document.forms[0].corneaRight,"Cornea Right")
		&& isEmpty(document.forms[0].corneaLeft,"Cornea Left")
		&& isEmpty(document.forms[0].lividity,"Lividity")
		&& isEmpty(document.forms[0].rigorMortis,"Rigor Mortis")
		&& isEmpty(document.forms[0].orifices,"Orifices")
		&& isEmpty(document.forms[0].mouthOdour,"Mouth Odour")
		&& isEmpty(document.forms[0].clothDetails,"Cloth Details")
		&& isEmpty(document.forms[0].remarks,"Remarks")*/
		&& validateDate()
		&& validateOpinion()
		&& validateExtraOpinion()
		&& comboValidation(document.forms[0].deathMannerCode,"Death Cause")
		&& isEmpty(document.forms[0].opinion,"Final Opinion")
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

function validateDate()
{
	var valid=true;
	var ntHour=document.getElementsByName("hiddenTimeHour")[0].value;
	var ntMin=document.getElementsByName("hiddenTimeMin")[0].value;
	
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHour")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var sHour=parseInt(trimNum(document.getElementsByName("startDateTimeHr")[0].value));
	var sMin=parseInt(trimNum(document.getElementsByName("startDateTimeMin")[0].value));
	var eHour=parseInt(trimNum(document.getElementsByName("endDateTimeHr")[0].value));
	var eMin=parseInt(trimNum(document.getElementsByName("endDateTimeMin")[0].value));
	
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var startDate=document.getElementsByName("startDate")[0].value;
	var endDate=document.getElementsByName("endDate")[0].value;
	
	day=parseInt((noOfDays(sysDate,endDate)));
	
	if(day<0)
	{
		alert("End Date Cannot Be Greater Than System Date");
		document.getElementsByName("endDate")[0].focus();
		valid=false;
	}
	else
	{
		if(day==0)
		{
			if(eHour > hour)
			{
				alert("End Date Cannot Be Greater Than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("endDateTimeHr")[0].focus();
				valid=false;
			}
			else if(eHour >= hour && eMin > min)
			{
				alert("End Date Cannot Be Greater Than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("endDateTimeMin")[0].focus();
				valid=false;
			}
			else if(true)
			{
				valid=validateStartDate();
			}
		}
		else
		{
			valid=validateStartDate();
		}
	}
	
	return valid;
}

function validateStartDate()
{
	var valid=true;
	var sHour=parseInt(trimNum(document.getElementsByName("startDateTimeHr")[0].value));
	var sMin=parseInt(trimNum(document.getElementsByName("startDateTimeMin")[0].value));
	var eHour=parseInt(trimNum(document.getElementsByName("endDateTimeHr")[0].value));
	var eMin=parseInt(trimNum(document.getElementsByName("endDateTimeMin")[0].value));
	var nteHour=document.getElementsByName("endDateTimeHr")[0].value;
	var nteMin=document.getElementsByName("endDateTimeMin")[0].value;
	
	var startDate=document.getElementsByName("startDate")[0].value;
	var endDate=document.getElementsByName("endDate")[0].value;
	
	day=parseInt((noOfDays(endDate,startDate)));
	
	if(day<0)
	{
		alert("Start Date Cannot Be Greater Than "+endDate+" "+nteHour+":"+nteMin);
		document.getElementsByName("startDate")[0].focus();
		valid=false;
	}
	else
	{
		if(day==0)
		{
			if(eHour < sHour)
			{
				alert("Start Date Cannot Be Greater Than "+endDate+" "+nteHour+":"+nteMin);
				document.getElementsByName("startDateTimeHr")[0].focus();
				valid=false;
			}
			else if(eHour <= sHour && eMin < sMin)
			{
				alert("Start Date Cannot Be Greater Than "+endDate+" "+nteHour+":"+nteMin);
				document.getElementsByName("startDateTimeMin")[0].focus();
				valid=false;
			}
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
	document.getElementsByName("startDate")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("endDate")[0].value=document.getElementsByName("sysDate")[0].value;;
	document.getElementsByName("startDateTimeHr")[0].value="";
	document.getElementsByName("startDateTimeMin")[0].value="";
	document.getElementsByName("endDateTimeHr")[0].value="";
	document.getElementsByName("endDateTimeMin")[0].value="";
	document.getElementsByName("incisionType")[0].value="-1";
	document.getElementsByName("remarks")[0].value="";
	//document.getElementsByName("conductBy")[0].value="-1";
	document.getElementsByName("complexion")[0].value="";
	document.getElementsByName("hairColorLength")[0].value="";
	document.getElementsByName("eyeColor")[0].value="";
	document.getElementsByName("length")[0].value="";
//	document.getElementsByName("length2")[0].value="";
	document.getElementsByName("weight")[0].value="";
//	document.getElementsByName("weight2")[0].value="";
	document.getElementsByName("bodyLooks")[0].value="-1";
	document.getElementsByName("clothDetails")[0].value="";
	document.getElementsByName("cynosis")[0].value="";
	document.getElementsByName("pupilRight")[0].value="";
	document.getElementsByName("pupilLeft")[0].value="";
	document.getElementsByName("corneaRight")[0].value="";
	document.getElementsByName("corneaLeft")[0].value="";
	document.getElementsByName("lividity")[0].value="";
	document.getElementsByName("rigorMortis")[0].value="";
	document.getElementsByName("orifices")[0].value="";
	document.getElementsByName("mouthOdour")[0].value="";
	document.getElementsByName("isDecomposition")[0].checked=true;
	
	//injury
	document.getElementsByName("injuryNatureCode")[0].value="-1";
	document.getElementsByName("injuryTypeCode")[0].value="-1";
	document.getElementsByName("injuryRemarks")[0].value="";
	document.getElementsByName("injuryDate")[0].value="";
	document.getElementsByName("injuryTimeHr")[0].value="";
	document.getElementsByName("injuryTimeMin")[0].value="";
	document.getElementsByName("weaponRemarks")[0].value="";
	document.getElementsByName("injuryPhoto")[0].checked=true;
	document.getElementsByName("weaponUsed")[0].checked=true;
	
	//Opinion request
	
	document.getElementsByName("deathDate")[0].value="";
	document.getElementsByName("deathTimeHr")[0].value="";
	document.getElementsByName("deathTimeMin")[0].value="";
	document.getElementsByName("deathMannerCode")[0].value="-1";
	document.getElementsByName("opinion")[0].value="";
	
	
	for(var i=0;i<document.getElementsByName("opinionRemarks").length;i++)
	{
		document.getElementsByName("opinionRemarks")[i].value="";
	}
	document.getElementsByName("extraOpinionCode")[0].value="-1";
	document.getElementsByName("extraOpinionRemarks")[0].value="";
	
}

function validateTeamAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].teamMember,"Team Member")
		&& comboValidation(document.forms[0].role,"Role Name")
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


function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenExtraOpinionCode")[0].value=obj1;
	document.getElementsByName("hiddenExtraOpinionName")[0].value=obj2;
	submitForm('DELETEROW')
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

function showWeaponRemarks()
{
	if(document.getElementsByName("weaponUsed")[0].checked)
		document.getElementById("weaponRemarksId").style.display="block";
	else
		document.getElementById("weaponRemarksId").style.display="none";
}

function validateAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].extraOpinionCode,"Opinion For")
		&& isEmpty(document.forms[0].extraOpinionRemarks,"Opinion ")
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

function hideUnhide(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}

function validateOpinion()
{
	var valid=false;
	for(var i=0;i<document.getElementsByName("opinionRemarks").length;i++)
	{
		if(document.getElementsByName("opinionRemarks")[i].value=="")
		{
			alert("Please Enter The Opinion For '"+document.getElementsByName("opinionName")[i].value+"'");
			document.getElementsByName("opinionRemarks")[i].focus();
			return false;
		}
		else
			valid=true;
	}
	
	return valid;
}

function validateExtraOpinion()
{
	var valid=false;
	if(typeof document.getElementsByName("extraOpinionCode")[0] !='undefined')
	{
		if(document.getElementsByName("extraOpinionCode")[0].value!="-1" || document.getElementsByName("extraOpinionRemarks")[0].value!="")
		{
			if(document.getElementsByName("extraOpinionCode")[0].value=="-1")
			{
				alert("Please Select The Opinion For ");
				document.getElementsByName("extraOpinionCode")[0].focus();
				valid=false;
			}
			else if(document.getElementsByName("extraOpinionRemarks")[0].value=="")
			{
				alert("Please Enter The Opinion ");
				document.getElementsByName("extraOpinionRemarks")[0].focus();
				valid=false;
			}
			else
			{
				valid=true;
			}
		}
		else
			valid=true;
	}
	else
		valid=true;
		
	return valid;	
}

//function disabledField()
 /*window.onload= function()
{	if(document.getElementsByName("hmode")[0].value=="NEW")
	{
		if(document.getElementsByName("conductBy")[0].value!="-1")
			document.getElementsByName("conductBy")[0].disabled=true;
		if(document.getElementsByName("complexion")[0].value!="")
			document.getElementsByName("complexion")[0].readOnly=true;	
		if(document.getElementsByName("hairColorLength")[0].value!="")
			document.getElementsByName("hairColorLength")[0].readOnly=true;
		if(document.getElementsByName("eyeColor")[0].value!="")
			document.getElementsByName("eyeColor")[0].readOnly=true;	
		if(document.getElementsByName("length1")[0].value!="")
		{
			document.getElementsByName("length1")[0].readOnly=true;
			document.getElementsByName("length2")[0].readOnly=true;
		}	
		if(document.getElementsByName("weight1")[0].value!="")
		{
			document.getElementsByName("weight1")[0].readOnly=true;
			document.getElementsByName("weight2")[0].readOnly=true;
		}
		if(document.getElementsByName("bodyLooks")[0].value!="-1")
			document.getElementsByName("bodyLooks")[0].disabled=true;
		if(document.getElementsByName("cynosis")[0].value!="")
			document.getElementsByName("cynosis")[0].readOnly=true;	
		if(document.getElementsByName("pupilRight")[0].value!="")
			document.getElementsByName("pupilRight")[0].readOnly=true;	
		if(document.getElementsByName("pupilLeft")[0].value!="")
			document.getElementsByName("pupilLeft")[0].readOnly=true;	
		if(document.getElementsByName("corneaRight")[0].value!="")
			document.getElementsByName("corneaRight")[0].readOnly=true;	
		if(document.getElementsByName("corneaLeft")[0].value!="")
			document.getElementsByName("corneaLeft")[0].readOnly=true;	
		if(document.getElementsByName("lividity")[0].value!="")
			document.getElementsByName("lividity")[0].readOnly=true;
		if(document.getElementsByName("rigorMortis")[0].value!="")
			document.getElementsByName("rigorMortis")[0].readOnly=true;
		if(document.getElementsByName("orifices")[0].value!="")
			document.getElementsByName("orifices")[0].readOnly=true;
		if(document.getElementsByName("mouthOdour")[0].value!="")
			document.getElementsByName("mouthOdour")[0].readOnly=true;	
		if(document.getElementsByName("isDecomposition")[0].checked || document.getElementsByName("isDecomposition")[1].checked)
	 	{
	 		document.getElementsByName("isDecomposition")[0].disabled=true;
	 		document.getElementsByName("isDecomposition")[1].disabled=true;
	 	}
		if(document.getElementsByName("clothDetails")[0].value!="")
			document.getElementsByName("clothDetails")[0].readOnly=true;	
			
		/////Injury
		if(document.getElementsByName("injuryNatureCode")[0].value!="-1")
			document.getElementsByName("injuryNatureCode")[0].disabled=true;
		if(document.getElementsByName("injuryTypeCode")[0].value!="-1")
			document.getElementsByName("injuryTypeCode")[0].disabled=true;
		if(document.getElementsByName("injuryRemarks")[0].value!="")
			document.getElementsByName("injuryRemarks")[0].readOnly=true;	
		
		if(document.getElementsByName("weaponRemarks")[0].value!="")
			document.getElementsByName("weaponRemarks")[0].readOnly=true;		
			
		if(document.getElementsByName("gaExistingFlag")[0].value==<%=MortuaryConfig.YES%>)
		{
			if(document.getElementsByName("weaponUsedValue")[0].value==<%=MortuaryConfig.YES%>)
			{
				document.getElementsByName("weaponUsed")[0].checked=true;
				document.getElementsByName("weaponUsed")[0].disabled=true;
			//	document.getElementsByName("weaponUsedValue")[0].value=<%=MortuaryConfig.YES%>;
			}	
			if(document.getElementsByName("injuryPhotoValue")[0].value==<%=MortuaryConfig.YES%>)
			{
				document.getElementsByName("injuryPhoto")[0].checked=true;	
				document.getElementsByName("injuryPhoto")[0].disabled=true;
			//	document.getElementsByName("injuryPhotoValue")[0].value=<%=MortuaryConfig.YES%>;
			}		
		}
		showWeaponRemarks();			
	}												
}*/

window.onload=function ()
{
	if(document.getElementsByName("weaponUsedValue")[0].value==<%=MortuaryConfig.YES%>)
		document.getElementsByName("weaponUsed")[0].checked=true;
	if(document.getElementsByName("injuryPhotoValue")[0].value==<%=MortuaryConfig.YES%>)
		document.getElementsByName("injuryPhoto")[0].checked=true;	
		
	showWeaponRemarks();
	
	if(document.getElementsByName("addOpinionFlag")[0].value==<%=MortuaryConfig.YES%>)
		showAddOpinion();	
}
function validateUpdate()
{
	var valid=false
	if(validateExtraOpinion()
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

function showAddOpinion()
{
	document.getElementById("divNewOpinionAdd").style.display="block";
}


function validateWeight(obj,e)
{
//	alert(">>>>"+getCursorIdex(obj));
	if(e.charCode!=0)
	{
		var count=0;
		var dot=".";
		for(var i=0;i<obj.value.length;i++)
		{
			if(obj.value.charAt(i)==dot.charAt(0))count++;
		}
		
//		alert("len"+obj.value.length)
		if(getCursorIdex(obj)==0)
			return validateNumeric(e);
			
		if(getCursorIdex(obj)==1 || getCursorIdex(obj)==2)
			return validateNumericNOneDotOnly(obj,e);
		
		if(getCursorIdex(obj)==3) //&& count==0 && e.charCode!=46)
		{
			if(count==0) 
			{
				if(e.charCode==46)
					return true;
				else	
					return validateDot(obj,e);
			}
			else
			{
				return validateNumeric(e);
			}
		//	obj.value=obj.value+".";
		//	return true;
		}	
		else
			return validateNumericNOneDotOnly(obj,e);	
		
		if(getCursorIdex(obj)==4)
		{
			if(e.charCode==46)
			{	
				
				return true;
			}	
			else	
				return validateNumeric(e);
		}
			
	}
	else
		return true;
}

function validateDot(obj,e)
{
	var len=obj.value.length;
//	alert(len);
	var str="";
	if(len<5)
	{	
		for(var i=0;i<len;i++)
		{//alert(">>>"+i)
			if(i==2)
			{
				str=str+obj.value.charAt(i);
				str=str+".";
			//	alert("if>>"+str);
			}	
			else
				str=str+obj.value.charAt(i);	
		//	alert(str);	
		}
		obj.value=str;
		return true;
	}	
	else
		return true;	
}

</script>

<his:TransactionContainer>
	<his:TitleTag name="Postmortem Entry">
	</his:TitleTag>
	
	<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
	
	<his:statusTransactionInProcess>
		<his:SubTitleTag name="Postmortem Initiation Detail">
		</his:SubTitleTag>
		<bean:define name="GeneralAppearanceEntryFB" property="sysDate" id="sysDate" type="java.lang.String"/>
		<% if(sysDate==null||sysDate.equalsIgnoreCase(""))
		   {
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }	 
						
		%>
	<html:hidden name="GeneralAppearanceEntryFB" property="sysDate" value="<%=sysDate%>"/>
	
	<%boolean readonly=false; %>
		<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
		<%readonly=true; %>
		</logic:equal>
		<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.NO %>">
			<%readonly=false; %>
		</logic:equal>
		
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="startDate"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.NO %>">
								<his:date name="startDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>"/>
							</logic:equal>
							<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
								<html:text name="GeneralAppearanceEntryFB" property="startDate" readonly="<%=readonly %>"></html:text>
							</logic:equal>	
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="startTime"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="startDateTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" readonly="<%=readonly %>"/>
							<b>
								<bean:message key="colon"/>
							</b>
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="startDateTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('startDateTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)" readonly="<%=readonly %>"/>
							<bean:message key="timeFormat"/>	
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="endDate"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.NO %>">
								<his:date name="endDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>"/>
							</logic:equal>
							<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
								<html:text name="GeneralAppearanceEntryFB" property="endDate" readonly="<%=readonly %>"></html:text>
							</logic:equal>
							
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="endTime"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="endDateTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" readonly="<%=readonly %>"/>
							<b>
								<bean:message key="colon"/>
							</b>
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="endDateTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('endDateTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)" readonly="<%=readonly %>"/>
							<bean:message key="timeFormat"/>	
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="incision"/>
								<bean:message key="type"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:select name="GeneralAppearanceEntryFB" property="incisionType" tabindex="1" disabled="<%=readonly %>">
								<html:option value="-1">Select value</html:option>
								<logic:present name="<%=MortuaryConfig.ESSENTIAL_INCISION_TYPE_LIST %>">
									<html:options collection="<%=MortuaryConfig.ESSENTIAL_INCISION_TYPE_LIST %>" property = "value" labelProperty = "label"/>
								</logic:present>
							</html:select>
							<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
								<html:hidden name="GeneralAppearanceEntryFB" property="incisionType"/>
							</logic:equal>
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
					</td>
					<td width="25%"  class="tdfont">
					</td>
					
				</tr>
			</table>
		</his:ContentTag>		
		
		<his:SubTitleTag name="External Appearance">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div align="right" >
							<img id="imgExternalAppearance" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
						</div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag>
	<div id="divExternalAppearance" style="display: none;">
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="complexion"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="complexion" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="hairColorLength"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="hairColorLength" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="eyeColor"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="eyeColor" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="mouthOdour"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="mouthOdour" maxlength="20" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="length"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="length" maxlength="3" size="6" onkeypress="return validateNumeric(event)" />
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;&nbsp;Cm</font>
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="weight"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="weight" maxlength="5" size="6" onkeypress="return  validateWeight(this,event)"  />
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;&nbsp;Kg</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="bodyLooks"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:select name="GeneralAppearanceEntryFB" property="bodyLooks" tabindex="1" >
								<html:option value="-1">Select value</html:option>
								<html:option value="<%=MortuaryConfig.DECEASED_BODY_LOOKS_FLAT%>">Flat</html:option>
								<html:option value="<%=MortuaryConfig.DECEASED_BODY_LOOKS_PLUMP%>">Plump</html:option>
								<html:option value="<%=MortuaryConfig.DECEASED_BODY_LOOKS_LEAN%>">Lean</html:option>
							</html:select>
							<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
								<html:hidden name="GeneralAppearanceEntryFB" property="bodyLooks"/>
							</logic:equal>
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="cynosis"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" property="cynosis" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="pupils"/>
								<bean:message key="right"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="pupilRight" maxlength="20" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="pupils"/>
								<bean:message key="left"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="pupilLeft" maxlength="20" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="cornea"/>
								<bean:message key="right"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="corneaRight" maxlength="20" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="cornea"/>
								<bean:message key="left"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="corneaLeft" maxlength="20" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="lividity"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="lividity" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="rigorMortis"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="rigorMortis" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="orifices"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="orifices" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="isDecomposition"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
							<html:radio name="GeneralAppearanceEntryFB" property="isDecomposition" value="<%=MortuaryConfig.IS_DECOMPOSITION_YES %>" tabindex="1" ></html:radio>YES
							<html:radio name="GeneralAppearanceEntryFB" property="isDecomposition" value="<%=MortuaryConfig.IS_DECOMPOSITION_NO %>" tabindex="1" ></html:radio>NO
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 
								<bean:message key="clothDetails"/>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont" colspan="3">
						<div align="left">
							<html:textarea name="GeneralAppearanceEntryFB" property="clothDetails" rows="1" cols="96" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
						</div>
					</td>
				</tr>
				
			</table>
		</his:ContentTag>
	</div>		
		<his:SubTitleTag name="Injury Details">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div align="right" >
							<img id="imgInjuryDetail" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
						</div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<div id="divInjuryDetail" style="display: none;">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="natureInjury"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="GeneralAppearanceEntryFB"  property="injuryNatureCode" >
									<html:option value="-1">select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_INJURY_NATURE_LIST%>">
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_INJURY_NATURE_LIST%>" property="value" labelProperty="label" />
									</logic:present>
									<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
										<html:hidden name="GeneralAppearanceEntryFB" property="injuryNatureCode"/>
									</logic:equal>
								</html:select>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="typeOfInjury"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="GeneralAppearanceEntryFB"  property="injuryTypeCode" >
									<html:option value="-1">select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_INJURY_TYPE_LIST%>">
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_INJURY_TYPE_LIST%>" property="value" labelProperty="label" />
									</logic:present>
									<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
										<html:hidden name="GeneralAppearanceEntryFB" property="injuryTypeCode"/>
									</logic:equal>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="injury"/>
									<bean:message key="date"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont" >
							<div align="left">
								<logic:empty name="GeneralAppearanceEntryFB" property="injuryDate">
									<his:date name="injuryDate" dateFormate="%d-%b-%Y"/>
								</logic:empty>
								<logic:notEmpty name="GeneralAppearanceEntryFB" property="injuryDate">
									<html:text name="GeneralAppearanceEntryFB" property="injuryDate" readonly="true"></html:text>
								</logic:notEmpty>	
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="injury"/>
									<bean:message key="time"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<logic:empty name="GeneralAppearanceEntryFB" property="injuryDate">
									<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="injuryTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" />
									<bean:message key="colon"/>
									<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="injuryTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('injuryTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)" />
									<bean:message key="timeFormat"/>
								</logic:empty>	
								<logic:notEmpty name="GeneralAppearanceEntryFB" property="injuryDate">
									<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="injuryTimeHr" maxlength="2" size="3" readonly="true"/>
									<bean:message key="colon"/>
									<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="injuryTimeMin" maxlength="2" size="3" readonly="true"/>
									<bean:message key="timeFormat"/>
								</logic:notEmpty>	
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="weaponUsed"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:checkbox name="GeneralAppearanceEntryFB" property="weaponUsed" onclick="showWeaponRemarks()" tabindex="1"></html:checkbox>
								
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="injuryPhotoTaken"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:checkbox name="GeneralAppearanceEntryFB" property="injuryPhoto" tabindex="1"></html:checkbox>
								
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
								<html:textarea name="GeneralAppearanceEntryFB" property="injuryRemarks" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
							</div>
						</td>
					</tr>
				</table>	
				<div id="weaponRemarksId" style="display: none;">
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
									<html:textarea name="GeneralAppearanceEntryFB" property="weaponRemarks" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</his:ContentTag>
		</div>	
		<%List lstTemp=(List)session.getAttribute(MortuaryConfig.TEMPLATE_ID_LIST_FOR_POSTMORTEM);
			if(lstTemp.size()>0){
		%>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td>
						<logic:iterate id="templateId" indexId="idx" name="<%=MortuaryConfig.TEMPLATE_ID_LIST_FOR_POSTMORTEM%>" type="hisglobal.utility.Entry">
							<his:GenericTemplateTag templateId="<%=templateId.getLabel() %>"></his:GenericTemplateTag>
						</logic:iterate>
					</td>
				</tr>		
			</table>	
		</his:ContentTag>
		<%} %>
			<his:SubTitleTag name="Opinion Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="actual"/>
									<bean:message key="deathdate"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont" >
							<div align="left">
								<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.NO %>">
									<his:date name="deathDate" dateFormate="%d-%b-%Y"/>
								</logic:equal>	
								<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
									<html:text name="GeneralAppearanceEntryFB" property="deathDate" readonly="<%=readonly %>"></html:text>
								</logic:equal>	
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="actual"/>
									<bean:message key="deathtime"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="deathTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" readonly="<%=readonly %>"/>
								<bean:message key="colon"/>
								<html:text name="GeneralAppearanceEntryFB" tabindex="1" property="deathTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('deathTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)" readonly="<%=readonly %>"/>
								<bean:message key="timeFormat"/>	
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.NO %>">	
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr bgcolor="#086FA6 "> 
						<td width="20%" colspan="2">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Opinion Requested For 
									</b>	
								</font>
							</div>
						</td>
						<td width="20%">
							<div align="center">
								<img class='button' src='<his:path src="/hisglobal/images/plus.gif"/>'  style=cursor:pointer onclick ="showAddOpinion()" onkeypress="if(event.keyCode==13) showAddOpinion()") tabindex="1" title="Add New Opinion">
							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="opinion"/>
										<bean:message key="for"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="65%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="opinion"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						&nbsp;
						</td>
					</tr>
					<logic:iterate id="requestedOpinionVO" name="<%=MortuaryConfig.ARR_OPINION_REQUESTED_VO %>" type="hisglobal.vo.PostmortemOpinionReqDtlVO">
					<tr>
						<td width="30%" class="tdfont">
							<div align="center">
								<%=requestedOpinionVO.getOpinionName() %>
								<html:hidden name="GeneralAppearanceEntryFB" property="opinionCode" value="<%=requestedOpinionVO.getOpinionCode() %>"/>
								<html:hidden name="GeneralAppearanceEntryFB" property="opinionName" value="<%=requestedOpinionVO.getOpinionName() %>"/>
							</div>
						</td>
						<td width="65%" class="tdfont">
							<div align="center">
								<html:textarea name="GeneralAppearanceEntryFB" property="opinionRemarks" rows="1" cols="80" tabindex="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							</div>
						</td>	
						<td width="5%" class="tdfont">
						</td>
					</tr>		
					</logic:iterate>
				</table>	
				<div id="divNewOpinionAdd" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<%List lstopinion=(List)session.getAttribute(MortuaryConfig.OPINION_LIST_NOT_REQUESTED);
							if(lstopinion.size()>0){%>
						<tr>
							<td width="30%" class="tdfont">
								<div align="center">
									<html:select name="GeneralAppearanceEntryFB"  property="extraOpinionCode" tabindex="1" >
										<html:option value="-1">select Value</html:option>
										<logic:present name="<%=MortuaryConfig.OPINION_LIST_NOT_REQUESTED%>">
											<html:options collection="<%=MortuaryConfig.OPINION_LIST_NOT_REQUESTED%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="65%" class="tdfont">
								<div align="center">
									<html:textarea name="GeneralAppearanceEntryFB" property="extraOpinionRemarks" rows="1" cols="80" tabindex="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>	
							<td width="5%" class="tdfont">
								<div align="center">
									<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style='cursor:pointer' tabindex="1" onclick ="if(validateAdd()) submitForm('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitForm('ADDROW');">
								</div>
							</td>
						</tr>
						<%} %>
					</table>	
				</div>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<%if(session.getAttribute(MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO)!=null){ %>
					<logic:iterate id="extraOpinion" name="<%=MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO %>" type="hisglobal.vo.PostmortemOpinionDetailVO">
						<tr>
							<td width="30%" class="tdfont">
								<div align="center">
									<%=extraOpinion.getOpinionName() %>
								</div>
							</td>	
							<td width="65%" class="tdfont">
								<div align="center">
									<%=extraOpinion.getOpinion() %>
								</div>
							</td>
							<td width="5%" class="tdfont">
								<div align="center">
									<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' tabindex="1" onkeypress="if(event.keyCode==13) deleteRow('<%=extraOpinion.getOpinionCode() %>','<%=extraOpinion.getOpinionName() %>') ;" onclick=" deleteRow('<%=extraOpinion.getOpinionCode() %>','<%=extraOpinion.getOpinionName() %>')" tabindex="1" >
									<html:hidden name="GeneralAppearanceEntryFB" property="hiddenExtraOpinionCode"/>
									<html:hidden name="GeneralAppearanceEntryFB" property="hiddenExtraOpinionName"/>
								</div>
							</td>
						</tr>
					</logic:iterate>	
					<%} %>
							
				</table>
			</his:ContentTag>
		</logic:equal>
		<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr bgcolor="#086FA6 "> 
						<td width="20%" colspan="2">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Opinion Requested For 
									</b>	
								</font>
							</div>
						</td>
						<td width="20%">
							<div align="center">
								<img class='button' src='<his:path src="/hisglobal/images/plus.gif"/>'  style=cursor:pointer onclick ="showAddOpinion()" onkeypress="if(event.keyCode==13) showAddOpinion()") tabindex="1" title="Add New Opinion">
							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="opinion"/>
										<bean:message key="for"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="65%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									   
										<bean:message key="opinion"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						&nbsp;
						</td>
					</tr>
					<logic:iterate id="addedOpinion" name="<%=MortuaryConfig.ARR_ADDED_OPINION_VO %>" type="hisglobal.vo.PostmortemOpinionDetailVO">
						<tr>
							<td width="30%" class="tdfont">
								<div align="center">
									<%=addedOpinion.getOpinionName() %>
									<html:hidden name="GeneralAppearanceEntryFB" property="opinionCode" value="<%=addedOpinion.getOpinionCode() %>"/>
									<html:hidden name="GeneralAppearanceEntryFB" property="opinionName" value="<%=addedOpinion.getOpinionName() %>"/>
								</div>
							</td>	
							<td width="65%" class="tdfont">
								<div align="center">
									<html:textarea name="GeneralAppearanceEntryFB" property="opinionRemarks" rows="1" cols="80" value="<%=addedOpinion.getOpinion() %>" tabindex="1"></html:textarea>
								</div>
							</td>
							<td width="5%" class="tdfont">
								<div align="center">
									
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>
				<div id="divNewOpinionAdd" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">	
					<%List lstNotAddedOpinion=(List)session.getAttribute(MortuaryConfig.OPINION_LIST_NOT_REQUESTED);
						if(lstNotAddedOpinion.size()>0){%>
						<tr>
							<td width="30%" class="tdfont">
								<div align="center">
									<html:select name="GeneralAppearanceEntryFB"  property="extraOpinionCode" tabindex="1">
										<html:option value="-1">select Value</html:option>
										<logic:present name="<%=MortuaryConfig.OPINION_LIST_NOT_REQUESTED%>">
											<html:options collection="<%=MortuaryConfig.OPINION_LIST_NOT_REQUESTED%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="65%" class="tdfont">
								<div align="center">
									<html:textarea name="GeneralAppearanceEntryFB" property="extraOpinionRemarks" rows="1" cols="80" tabindex="1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>	
							<td width="5%" class="tdfont">
								<div align="center">
									<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style='cursor:pointer' tabindex="1" onclick ="if(validateAdd()) submitForm('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitForm('ADDROW');">
								</div>
							</td>
						</tr>
						<%} %>
					</table>	
				</div>	
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<%if(session.getAttribute(MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO)!=null){ %>
					<logic:iterate id="extraOpinion" name="<%=MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO %>" type="hisglobal.vo.PostmortemOpinionDetailVO">
						<tr>
							<td width="30%" class="tdfont">
								<div align="center">
									<%=extraOpinion.getOpinionName() %>
								</div>
							</td>	
							<td width="65%" class="tdfont">
								<div align="center">
									<%=extraOpinion.getOpinion() %>
								</div>
							</td>
							<td width="5%" class="tdfont">
								<div align="center">
									<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' tabindex="1" onkeypress="if(event.keyCode==13) deleteRow('<%=extraOpinion.getOpinionCode() %>','<%=extraOpinion.getOpinionName() %>') ;" onclick=" deleteRow('<%=extraOpinion.getOpinionCode() %>','<%=extraOpinion.getOpinionName() %>')" tabindex="1" >
									<html:hidden name="GeneralAppearanceEntryFB" property="hiddenExtraOpinionCode"/>
									<html:hidden name="GeneralAppearanceEntryFB" property="hiddenExtraOpinionName"/>
								</div>
							</td>
						</tr>
					</logic:iterate>	
					<%} %>
				</table>
			</his:ContentTag>		
		</logic:equal>	
			
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr bgcolor="#086FA6 "> 
						<td width="20%" colspan="2">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Final Opinion 
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="dthCause"/>
							</font>
						</div>
					</td>
					<td width="75%"  class="tdfont">
						<div align="left">
							<html:select name="GeneralAppearanceEntryFB" property="deathMannerCode" tabindex="1" >
								<html:option value="-1">Select value</html:option>
								<logic:present name="<%=MortuaryConfig.ESSENTIAL_DEATH_MANNER_LIST %>">
									<html:options collection="<%=MortuaryConfig.ESSENTIAL_DEATH_MANNER_LIST %>" property = "value" labelProperty = "label"/>
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
									<bean:message key="final"/>
									<bean:message key="opinion"/>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont">
							<div align="left">
								<html:textarea name="GeneralAppearanceEntryFB" property="opinion" rows="2" cols="100" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
							</div>
						</td>		
					</tr>
					<tr>	
						<td width="25%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="remarks"/>
								</font>
							</div>
						</td>
						<td width="75%"  class="tdfont" >
							<div align="left">
								<html:textarea name="GeneralAppearanceEntryFB" property="remarks" rows="2" cols="100" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
							</div>
						</td>
					</tr>
					
				</table>	
			</his:ContentTag>
		
		
				
	</his:statusTransactionInProcess>
	<his:ButtonToolBarTag>
		
		<his:statusTransactionInProcess>
		<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.NO %>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
		</logic:equal>
		<logic:equal name="GeneralAppearanceEntryFB" property="gaExistingFlag" value="<%=MortuaryConfig.YES %>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateUpdate()) submitPage('UPDATE')" onkeypress="if(event.keyCode==13)if(validateUpdate())submitPage('UPDATE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</logic:equal>
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
	
	</his:ButtonToolBarTag>
	
</his:TransactionContainer>


	
	<html:hidden name="GeneralAppearanceEntryFB" property="hmode"/>
	<html:hidden name="GeneralAppearanceEntryFB" property="deceasedNo"/>
	<html:hidden name="GeneralAppearanceEntryFB" property="hiddenTimeHour"/>
	<html:hidden name="GeneralAppearanceEntryFB" property="hiddenTimeMin"/>
	<html:hidden name="GeneralAppearanceEntryFB" property="gaExistingFlag"/>
	<html:hidden name="GeneralAppearanceEntryFB" property="weaponUsedValue"/>
	<html:hidden name="GeneralAppearanceEntryFB" property="injuryPhotoValue"/>
	<html:hidden name="GeneralAppearanceEntryFB" property="srNo"/>
	<html:hidden name="GeneralAppearanceEntryFB" property="addOpinionFlag"/>
	
