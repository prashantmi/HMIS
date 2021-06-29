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

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page import="registration.*,hisglobal.vo.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<logic:equal name="EpisodeDeathDetailFB" property="isDirectCall" value="DIRECT">

		<his:css src="/hisglobal/css/Color.css" />
		<his:css src="/hisglobal/css/master.css" />
		<his:css src="/hisglobal/css/hisStyle.css" />
		<his:css src="/hisglobal/css/hisStyleExt.css" />
		<his:css src="/hisglobal/css/calendar-blue2.css" />
		
		<his:javascript src="/registration/js/registration.js" />
		<his:javascript src="/registration/js/calendar.js" />
		<his:javascript src="/registration/js/time.js" />
		<his:javascript src="/registration/js/validationCommon.js" />
		<his:javascript src="/registration/js/validationCalls.js" />
		<his:javascript src="/registration/js/commonFunctions.js" />
		<his:javascript src="/registration/js/dateFunctions.js" />
		<his:javascript src="/registration/js/popup.js" />
		<his:javascript src="/opd/opdJs/opd.js" />

</logic:equal>

<script>
function checkMaxLenTextArea(e,elem,maxLen)
{
	key = e.keyCode;
	var valid=true;
	if(key==8 || key==46) //backspace || del
		return true;
	if(key==13)	//enter key not allowed
		return true;
	val = elem.value;
	if(val.length+1>maxLen)
		return false;
}

function showdivhandovertorelative()
{
	<%
	//EpisodeVO epVO=(EpisodeVO)session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);
	//if(epVO.getEpisodeTypeCode().equalsIgnoreCase(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC))
	{
	%>
		//alert("MLC CASE::: Body cannot be handed over to relative");
		//document.getElementsByName("bodyHandOverTo")[1].checked=true;
	<%
	}
	//else
	{
	%>
	document.getElementById("divhandovertorelative").style.display="";
	document.getElementsByName("patrelativename")[0].value="";
	document.getElementsByName("patRelativeAddress")[0].value="";
	<%
	}
	%>
}

function  hideivhandovertorelative()
{
	document.getElementById("divhandovertorelative").style.display="none";
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

function compareDateCall(d1,d2,mode,l1,l2) 
{
	var valid=true;
	if(isEmpty(d1,l1) && isEmpty(d2,l2))
	{
 		if(compareDate(d1,d2, mode))
			valid = true;
		else
		{
			alert(l1+" can't be greater than "+l2);
			valid = false;
		}
	}
	else
		valid=false;
	return valid;
}

function validateFrom()
{
	var valid=false;
	
	document.getElementsByName("deathTime")[0].value=document.getElementById("deathHr").value+":"+document.getElementById("deathMin").value;
	document.getElementsByName("bodyHandOverTime")[0].value=document.getElementById("handOHr").value+":"+document.getElementById("handOMin").value;
	
	if(! validateObjects(document.forms[0].deathDate,"Death Date"))	return false;
	if( document.getElementById("deathHr").value=="" ) 
	{
		alert("Enter Death Time ...");
		document.getElementById("deathHr").focus();
		return false; 
	}
	if( document.getElementById("deathMin").value=="") 
	{
		alert("Enter Death Time ...");
		document.getElementById("deathMin").focus();
		return false; 
	}
	if(! (validateObjects(document.forms[0].deathCause,"Cause of Death") && 
		validateObjects(document.forms[0].mannerOfDeath,"Manner of death") && 
		validateObjects(document.forms[0].attendConsultantId,"Death verified by") && 
		validateObjects(document.forms[0].deathverificationDate,"Death Verification Date") &&
		validateObjects(document.forms[0].bodyHandOverDate,"Hand Over Date") ) )
		return false;
	
	if( document.getElementById("handOHr").value=="")
	{
		alert("Enter Hand Over Time ...");
		document.getElementById("handOHr").focus();
		return false;
	} 
	if(document.getElementById("handOMin").value=="" )
	{
		alert("Enter Hand Over Time ...");
		document.getElementById("handOMin").focus();
		return false;
	} 
	
	if(! (validateObjects(document.getElementsByName("bodyHandOverTo")[1],"Body Handed To") && 
		validateObjects(document.forms[0].patRelativeName,"Relative Name") && 
		validateObjects(document.forms[0].relativeCode,"Relative Type") && 
		validateObjects(document.forms[0].patRelativeAddress,"Relative Address")) )
	{
		return false;
	}
	
	var x= confirm("Records once saved canot be modified!!!");
	if(x==true)
	{
		var x=confirm("Are you sure you want to continue????");
		if(x==true)
			valid=true;
		else
			valid=false;
	}
	else
	{
		valid=false;
	}
	return valid;
}
window.onload=function ()
{
	if(document.getElementsByName('hmode')[0].value=="SAVE")
	{
		submitToDesk('NEW','NEW');
	}
}
</script>


<logic:equal name="EpisodeDeathDetailFB" property="isDirectCall" value="DIRECT">
	<form action="/HISClinical/opd/casuality/deathDetail.cnt" method="post">
</logic:equal>

<%
	String varStatus="";
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>


<his:TitleTag name="Death Detail">
<logic:equal name="EpisodeDeathDetailFB" property="isDirectCall" value="DIRECT">
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
<logic:equal name="EpisodeDeathDetailFB" property="hmode" value="GETCRNO">
<his:InputCrNoTag name="EpisodeDeathDetailFB"> </his:InputCrNoTag>	
</logic:equal>
</his:statusNew>


<logic:equal name="EpisodeDeathDetailFB" property="isDirectCall" value="DESK">
	<bean:define id="crNo" name="EpisodeDeathDetailFB" property="patCrNo" type="java.lang.String" />
	<jsp:include page="/registration/patientDetail.cnt" flush="true" />
</logic:equal>


<his:statusTransactionInProcess>
	<%varStatus="InProcess";%>

	<logic:equal name="EpisodeDeathDetailFB" property="isDirectCall" value="DIRECT">
		<bean:define id="crNo" name="EpisodeDeathDetailFB" property="patCrNo" type="java.lang.String" />
		<jsp:include page="/registration/patientDetail.cnt" flush="true" />
	</logic:equal>

	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="deathdate" />
						</font>
					</div>
				</td>
	
				<td width="25%" class="tdfont">
					<his:date name="deathDate" dateFormate="%d-%b-%Y" />
				</td>
	
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="deathtime" />
						</font>
					</div>
				</td>
	
				<td width="25%" class="tdfont">
					<html:hidden name="EpisodeDeathDetailFB" property="deathTime"/>
					<span>
						<input type="text" tabindex="1" id="deathHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
						<b>	<bean:message key="colon"/></b>
					</span>
					<span>
						<input type="text" tabindex="1" id="deathMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementById('deathHr'),event)" onblur="onblurTimeMinCheck(this)"/>
						<bean:message key="timeFormat"/>
					</span>
				</td>
			</tr>
	
			<tr>
				<td class="tdfonthead" width="25%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="deathcause" />
						</font>
					</div>
				</td>
	
				<td width="25%" class="tdfont"%>
					<html:textarea styleClass="textarea3" name="EpisodeDeathDetailFB" property="deathCause" tabindex="1" 
						onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))" />
				</td>
	
				<td class="tdfonthead" width="25%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="remarks" />
						</font>
					</div>
				</td>
				
				<td class="tdfont" width="25%">
					<html:textarea styleClass="textarea3" name="EpisodeDeathDetailFB" property="consultantRemark" tabindex="1" 
					onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))" />
				</td>
			</tr>
	
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="deathManner" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<html:select name="EpisodeDeathDetailFB" property="mannerOfDeath" tabindex="1" styleClass="regCbo">
						<html:option value="">Select Value</html:option>
						<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_DEATH_MANNER%>" property="value" labelProperty="label" />
					</html:select>
				</td>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="deathverifiedBy" />
						</font>
					</div>
				</td>
	
				<td width="25%" class="tdfont">
					<html:text name="EpisodeDeathDetailFB" property="attendConsultantId" maxlength="10" tabindex="1" 
					onkeypress="return validateAlphaNumericOnly(event)" />
				</td>
	
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="deathverificationDate" />
						</font>
					</div>
				</td>
	
				<td width="25%" class="tdfont">
					<his:date name='<%="deathverificationDate"%>' dateFormate="%d-%b-%Y" />
				</td>
			</tr>
		</table>
	</his:ContentTag>
	
	<his:SubTitleTag name="Hand Over Detail">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellpadding="1" cellspacing="1">
			<tr>
				<td width="25%" height="22" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="handoverdate" />
						</font>
					</div>
				</td>
				
				<td width="25%" class="tdfont">
					<his:date name='<%="bodyHandOverDate"%>' dateFormate="%d-%b-%Y" />
				</td>
				
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
							<bean:message key="handovertime" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<html:hidden name="EpisodeDeathDetailFB" property="bodyHandOverTime"/>
					<span>
						<input type="text" tabindex="1" id="handOHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
						<b>	<bean:message key="colon"/></b>
					</span>
					<span>
						<input type="text" tabindex="1" id="handOMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementById('handOHr'),event)" onblur="onblurTimeMinCheck(this)"/>
						<bean:message key="timeFormat"/>
					</span>
				</td>
			</tr>	
			<tr>
				<td class="tdfont" width="25%" colspan="4">
					<div id="divbodyhandoverdetail">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="bodyhanoverrelative" />
						</font>
						<html:radio name="EpisodeDeathDetailFB" property="bodyHandOverTo" value="<%=RegistrationConfig.BODY_HANDOVER_TO_RELATIVE%>" onclick="showdivhandovertorelative()" tabindex="1" />
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="mortuary" />
						</font>
						<html:radio name="EpisodeDeathDetailFB" property="bodyHandOverTo" value="<%=RegistrationConfig.BODY_HANDOVER_TO_MORTUARY%>" onclick="hideivhandovertorelative()" tabindex="1" />
					</div>
				</td>
			</tr>
		</table>
	
		<div id="divhandovertorelative" style='display: none'>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="relativename" />
							</font>
						</div>
					</td>
					
					<td width="25%" class="tdfont">
						<html:text name="EpisodeDeathDetailFB" property="patRelativeName" styleClass="textboxBig" 
						tabindex="1" maxlength="80" onkeypress="return validateAlphaOnly(event)" />
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="realtionship" />
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<html:select name="EpisodeDeathDetailFB" property="relativeCode" tabindex="1" styleClass="regCbo">
							<html:option value="">Select Value</html:option>
							<html:options collection="<%=RegistrationConfig.ESSENTIALBO_RELATIVE_CODE%>" property="value" labelProperty="label" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="relativeaddress" />
							</font>
						</div>
					</td>

					<td width="25%" class="tdfont">
						<html:textarea name="EpisodeDeathDetailFB" property="patRelativeAddress" styleClass="textArea2" 
						tabindex="1" onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event))" />
					</td>

					<td width="25%" class="tdfonthead"></td>
					<td width="25%" class="tdfont"></td>
				</tr>
			</table>
		</div>
	</his:ContentTag>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<logic:equal name="EpisodeDeathDetailFB" property="isDirectCall" value="DIRECT">
		<%	if(varStatus.equals("InProcess"))	{	%>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)	submitFormOnValidate(validateForm(),'SAVE');" onclick="submitFormOnValidate(validateFrom(),'SAVE');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" onclick="submitForm('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');" >
		<%	} else {	%>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" onclick="submitForm('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');">
		<%	}	%>
	</logic:equal>
	<logic:equal name="EpisodeDeathDetailFB" property="isDirectCall" value="DESK">
		<%	if(varStatus.equals("InProcess"))	{	%>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateForm(),'SAVE');" onclick="submitFormOnValidate(validateFrom(),'SAVE');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" onclick="submitToDesk('NEW','NEW');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
		<%	} else {	%>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" onclick="submitToDesk('NEW','NEW');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		<%	}	%>
	</logic:equal>
</his:ButtonToolBarTag>

<html:hidden name="EpisodeDeathDetailFB" property="hmode"/>
<html:hidden name="EpisodeDeathDetailFB" property="patCrNo"/>
<html:hidden name="EpisodeDeathDetailFB" property="episodeCode"/>
<html:hidden name="EpisodeDeathDetailFB" property="episodeVisitNo"/>
<html:hidden name="EpisodeDeathDetailFB" property="isDirectCall"/>

<logic:equal name="EpisodeDeathDetailFB" property="isDirectCall" value="DIRECT">
	</form>
</logic:equal>