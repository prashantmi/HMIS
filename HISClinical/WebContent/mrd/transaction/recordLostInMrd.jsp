<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">

function openSearchPopup(e)
{
	var path="/HISClinical/mrd/recordLostInMrd.cnt?hmode=POPUP";
	openPopup(createFHashAjaxQuery(path),e,300,700);
}

function validateSave()
{
	if( isEmpty(document.forms[0].recordDesc,"Record Id")
		&& comboValidation(document.forms[0].reportedBy,"Reported By")
		&& isEmpty(document.forms[0].lastSeenDate,"Last Seen Date")
		&& isEmpty(document.forms[0].lastSeenTimeHr,"Last Seen Time Hr")
		&& isEmpty(document.forms[0].lastSeenTimeMin,"Last Seen Time Min")
		&& validateLastSeenDate()
		&& isEmpty(document.forms[0].lostArea,"Lost Area") )
	{
	  var lastseenDate=document.getElementsByName("lastSeenDate")[0].value;
	  var recordCreationDate=document.getElementsByName("entryDate")[0].value;
	  
		if(!checkLastSeenDate(lastseenDate,recordCreationDate))
		{
				alert("Last Seen Date should Not be Less than Record Creation Date ( "+recordCreationDate+") ");
				return false;
		}
			
			else
			{
				submitForm('SAVE');
			}
	}

}
function checkLastSeenDate(lastSeenDate,recordCreationDate)
{
	// alert("Last seen Date before conversion = "+lastSeenDate);
     //alert("recordCreation Date before conversion  = "+recordCreationDate);
	
    var lastSeen_date=convertStrToDate(lastSeenDate, "dd-Mon-yyyy");
      
    var b=recordCreationDate.toString(); 
    var recordCreation_dateArr=b.split(" "); // date and time are splitted here
    var recordCreation_date=recordCreation_dateArr[0];
    var recordCreation_time=recordCreation_dateArr[1];
    var timeArr=recordCreation_time.split(":"); // hours and mins are splitted here
    var recordCreation_hr= timeArr[0];
    var recordCreation_min=timeArr[1];
    //alert("record Creation hour"+recordCreation_hr);
   // alert("record Creation Min"+recordCreation_min);
    
    
   
    var recordCreation_date=convertStrToDate(recordCreationDate, "dd-Mon-yyyy");
    
 
    // alert("Last seen Date after conversion = "+lastSeen_date);
    // alert("recordCreation Date after conversion  = "+recordCreation_date);
    if(lastSeen_date<=recordCreation_date) 
    {
    	if(lastSeen_date.toLocaleString()==recordCreation_date.toLocaleString())
    	{
    		var lastSeenTime_hr=document.getElementsByName("lastSeenTimeHr")[0].value;
    		var lastSeenTime_min=document.getElementsByName("lastSeenTimeMin")[0].value;
    		if(lastSeenTime_hr<=recordCreation_hr)
    		{
    			if(lastSeenTime_hr==recordCreation_hr)
    			{
    				if(lastSeenTime_min<recordCreation_min)  
    				   return false;
    			}
    			else 
    			    return false;
    		}
    	   // else
    	      // return false;
        }
       else 
          return false;
    }
  return true;
}


function noOfDays(a,b)
{
	var valid=true;
	var days=0;
	
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
    
    days=((bdate-adate)/86400000);

    return days;
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function validateLastSeenDate()
{
	var valid=true;
	var lastSeenDate=document.getElementsByName("lastSeenDate")[0].value;
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var ntHour=document.getElementsByName("hiddenTimeHr")[0].value;
	var ntMin=document.getElementsByName("hiddenTimeMin")[0].value;
	
	var days=noOfDays(lastSeenDate,sysDate);
	
	if(days<0)
	{
		alert("Last Seen Date Cannot be Greater Than  "+sysDate);
		document.getElementsByName("lastSeenDate")[0].focus();
		valid=false;
	}
	else
	{
		if(days==0)
		{
			if(parseInt(trimNum(document.getElementsByName("lastSeenTimeHr")[0].value)) > hour)
			{
				alert("Last Seen Date Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("lastSeenTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("lastSeenTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("lastSeenTimeMin")[0].value)) > min)
			{
				alert("Last Seen Date Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("lastSeenTimeMin")[0].focus();
				valid=false;
			}
		}
		else
			valid=true;
	}
	
	return valid;
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
	document.getElementsByName("recordDesc")[0].value="";
	document.getElementsByName("recordId")[0].value="";
	document.getElementsByName("mrdRecordId")[0].value="";
	
	document.getElementsByName("reportedBy")[0].value="-1";
	document.getElementsByName("lastSeenDate")[0].value="";
	document.getElementsByName("lastSeenTimeHr")[0].value="";
	document.getElementsByName("lastSeenTimeMin")[0].value="";
	document.getElementsByName("lostArea")[0].value="";
	document.getElementsByName("lostRemarks")[0].value="";
}

</script>
	<body>
		<html:form action="/recordLostInMrd">
			<his:TransactionContainer>
			<his:TitleTag name="Record Lost In MRD">
			</his:TitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="recordId"/>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont">
							<div align="left">
								<html:text name="RecordLostInMrdFB" property="recordDesc" readonly="true"></html:text>
								<html:hidden name="RecordLostInMrdFB" property="recordId"/>
								<html:hidden name="RecordLostInMrdFB" property="mrdRecordId"/>
								<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openSearchPopup(event)">
							</div>
						</td>
					</tr>
				</table>	
			</his:ContentTag>
			<his:SubTitleTag name="Record Lost Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="reportedBy"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="RecordLostInMrdFB" property="reportedBy" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_LOST_RECORD_REPORTED_BY_EMP %>" >
										<html:options collection="<%=MrdConfig.LIST_LOST_RECORD_REPORTED_BY_EMP %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="lostType"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="RecordLostInMrdFB" property="lostType" value="<%=MrdConfig.RECORD_LOST_TYPE_COMPLETE %>" disabled="true"></html:radio>
									<bean:message key="complete"/>	
									<html:radio name="RecordLostInMrdFB" property="lostType" value="<%=MrdConfig.RECORD_LOST_TYPE_PARTIAL %>" disabled="true"></html:radio>
									<bean:message key="partial"/>	
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="lastSeenDate"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="lastSeenDate" dateFormate="%d-%b-%Y" ></his:date>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="lastSeenTime"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="RecordLostInMrdFB" tabindex="1" property="lastSeenTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
								<b>
									<bean:message key="colon"/>
								</b>
								<html:text name="RecordLostInMrdFB" tabindex="1" property="lastSeenTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('lastSeenTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
								<bean:message key="timeFormat"/>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="lostArea"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="RecordLostInMrdFB" property="lostArea" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
						</td>
						<td width="25%" class="tdfont">
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="lost"/>
									<bean:message key="remarks"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont" colspan="3">
							<div align="left">
								<html:textarea name="RecordLostInMrdFB" property="lostRemarks" rows="1" cols="96" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" tabindex="1"></html:textarea>
							</div>
						</td>
					</tr>	
				</table>
			</his:ContentTag>
			<% 
			   String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			%>
			<html:hidden name="RecordLostInMrdFB" property="sysDate" value="<%=sysDate%>"/>
			
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc();" onkeypress="if(event.keyCode==13) cancelFunc()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
			</his:ButtonToolBarTag>
			
			</his:TransactionContainer>
			
			
			<html:hidden name="RecordLostInMrdFB" property="hmode"/>
			<html:hidden name="RecordLostInMrdFB" property="hiddenTimeHr" />
			<html:hidden name="RecordLostInMrdFB" property="hiddenTimeMin" />
			<html:hidden name="RecordLostInMrdFB" property="entryDate" />
			
		</html:form>
		
		<his:status/>
	</body>
</html>	