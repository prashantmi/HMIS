<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.CertificateLostFoundFB"%>
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
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<script type="text/javascript">

window.onload=function()
{
	checkedSelectedValue();
	showRackInfo();
	
	if(document.getElementsByName("recordType")[0].value==<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET%> || document.getElementsByName("recordType")[0].value==<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET%>)	
	{
		for(var i=0; i<document.getElementsByName("selectedRecord").length;i++)
		{
			if(document.getElementsByName("selectedRecord")[i].checked)
				showEnclosureButton(i);
		}
		setVerifiedEnclosureVlue();
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


function cancelForm(mode)
{
	document.getElementsByName("deskmode")[0].value=mode;
	document.forms[0].submit();
}


function checkedSelectedValue()
{
	var str=document.getElementsByName("tempSelectedChk")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('selectedRecord');
	for(var i=0;i<arr.length;i++)
	{
		for(var j=0;j<chks.length;j++)
		{
			if(chks[j].value==arr[i])
			{
				chks[j].checked=true;
			}
		}
	}
}

function setVerifiedEnclosureVlue()
{
	var str=document.getElementsByName("tempVerifiedEnclosure")[0].value;
	var arr=str.split("@");
	var verEnc=document.getElementsByName("verifiedEnclosure");
	for(var i=0;i<arr.length;i++)
	{
		verEnc[i].value=arr[i];
	}
}

function showRackShelf()
{
	showRackInfo();
	var rackId=document.getElementsByName("rackId")[0].value;
	if(rackId!="-1")
		submitForm('GETSHELF');
}

function showRackInfo()
{
	if(document.getElementsByName("rackId")[0].value=="-1")
		document.getElementById("divRackInfoId").style.display="none";
	else
		document.getElementById("divRackInfoId").style.display="block";
}

function validateSave()
{
	var count=0;
	for(var i=0;i<document.getElementsByName("selectedRecord").length;i++)
	{
		if(document.getElementsByName("selectedRecord")[i].checked)
		{
			count++;
			if(document.getElementsByName("recordType")[0].value==<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET%> || document.getElementsByName("recordType")[0].value==<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET%>)
			{
				if(document.getElementsByName("verifiedEnclosure")[i].value==<%=MrdConfig.NO%>)
				{
					alert("Please Verified The Enclosure of "+document.getElementsByName("hiddenRecordDesc")[i].value);
					return false;
				}
			}
		}
	}
	
	if(count==0)
	{
		alert("Please Select At Least One Record")
		return false;
	}
	else
	{
		validateFoundDetail();
	}
	
	
}

function validateFoundDetail()
{
	if(isEmpty(document.forms[0].foundBy,"Found By")
		&& isEmpty(document.forms[0].foundDate,"Found Date")
		&& isEmpty(document.forms[0].foundTimeHr,"Found Time Hr")
		&& isEmpty(document.forms[0].foundTimeMin,"Found Time Min")
		&& validateFoundDate()
		&& checkFoundDate()
		&& comboValidation(document.forms[0].rackId,"Rack Id")
		&& comboValidation(document.forms[0].shelfId,"Shelf Id")
		&& comboValidation(document.forms[0].putBy,"Put By")
	)
	submitForm('SAVE');
}

function showEnclosureButton(obj)
{
	var divId="divEnclosureId"+obj;
	if(document.getElementsByName("selectedRecord")[obj].checked)
		document.getElementById(divId).style.display="block";
	else	
		document.getElementById(divId).style.display="none";
}

function getEnclosureDtl(index)
{
	var dispatchId=document.getElementsByName("selectedRecord")[index].value;
	var recordType=document.getElementsByName("recordType")[0].value;
	var recordId=document.getElementsByName("hiddenRecordId")[index].value;
	var path="/HISClinical/mrd/certificateAcceptInMrd.cnt?hmode=GETENCLOSURE&dispatchId="+dispatchId+"&index="+index+"&recordType="+recordType+"&recordId="+recordId;
//	var popup=window.open(path,"Enclosures",'height=100,width=700,left=200,top=250');
	openPrintPopup(path,300,700);
}

function openPrintPopup(url, height, width)
{
	child = window.open(createFHashAjaxQuery(url),'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
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

function validateFoundDate()
{
	var valid=true;
	var foundDate=document.getElementsByName("foundDate")[0].value;
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var ntHour=document.getElementsByName("hiddenTimeHr")[0].value;
	var ntMin=document.getElementsByName("hiddenTimeMin")[0].value;
	
	var days=noOfDays(foundDate,sysDate);
	
	if(days<0)
	{
		alert("Found Date Cannot be Greater Than  "+sysDate);
		document.getElementsByName("foundDate")[0].focus();
		valid=false;
	}
	else
	{
		if(days==0)
		{
			if(parseInt(trimNum(document.getElementsByName("foundTimeHr")[0].value)) > hour)
			{
				alert("Found Date Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("foundTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("foundTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("foundTimeMin")[0].value)) > min)
			{
				alert("Found Date Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				document.getElementsByName("foundTimeMin")[0].focus();
				valid=false;
			}
		}
		else
			valid=true;
	}
	
	return valid;
}
function checkFoundDate()
{
	var chk=document.getElementsByName("selectedRecord");
	var objLostDate = null;
	var maxLostDate = null;
	for(var i=0;i<chk.length;i++)
	{
	   if(chk[i].checked)
	   {	   
			var lostDate=document.getElementsByName("lostDate")[i].value;
			var objTemp = convertStrToDate(lostDate, "dd-Mon-yyyy hh:mm");
			if(objLostDate==null || objLostDate<objTemp)
			{
				objLostDate = objTemp;
				maxLostDate = lostDate;
			}
		}
	}
	if(objLostDate!=null)
	{
		//alert("Lost Date = "+objLostDate);
		var foundDate = document.getElementsByName("foundDate")[0].value;
	    var cur_hr=document.getElementsByName("foundTimeHr")[0].value;
	    var cur_min=document.getElementsByName("foundTimeMin")[0].value;
		foundDate += " " + cur_hr + ":" + cur_min;
		
	    if(parseInt(cur_hr)>=24)
	    {
	    	if(parseInt(cur_hr)==24)
	    	{
	    		document.getElementsByName("foundTimeHr")[0].value=0;
	    	}
	    	else
	    	{
	    		alert("Hours cannot be greater than 23");
	    		document.getElementsByName("foundTimeHr")[0].focus();
	    		return false;
	    	}
	    }
	    if(parseInt(cur_min)>=60)
	    {
	    	if(parseInt(cur_min)==60)
	    	{
	    		document.getElementsByName("foundTimeMin")[0].value=0;
	    	}
	    	else
	    	{
	    		alert("Minutes cannot be greater than 59");
	    		document.getElementsByName("foundTimeMin")[0].focus();
	    		return false;
	    	}
	    }

		var objFoundDate = convertStrToDate(foundDate, "dd-Mon-yyyy hh:mm");
		   
		   
		    /*var b=lostDate.toString(); 
		    var lst_dateArr=b.split(" "); // date and time are splitted here
		    var lst_date=lst_dateArr[0];
		    var lst_time=lst_dateArr[1];
		    var timeArr=lst_time.split(":"); // hours and mins are splitted here
		    var lst_hr= timeArr[0];
		    var lst_min=timeArr[1];
		    if(objFoundDate<=objLostDate)
		    {
		    	if(found_date.toLocaleString()==lost_date.toLocaleString())
		    	{
		    		if(cur_hr<=lst_hr)
		    		{
		    			if(cur_hr==lst_hr)
		    			{
		    				if(cur_min<lst_min)
		    				{
		    					alert("Found Date should be Greater than Lost Date ( "+lostDate+" )");
		    					return false;
		    				}
		       			}
		    			else 
		    			{
		    				alert("Found Date should be Greater than Lost Date ( "+lostDate+" )");
		    				return false;
		    			}
		    		}
		    	}
		    	else
		    	{
		    		alert("Found Date should be Greater than Lost Date ( "+lostDate+" )");
		    		return false;
		    	}
		    }*/
		    
		if(objFoundDate<=objLostDate)
		{
	    	alert("Found Date should be Greater than Lost Date ( "+maxLostDate+" )");
	    	return false;
	    }
	    return true;
	}
}

function clearForm()
{
	document.getElementsByName("foundBy")[0].value="";
	document.getElementsByName("foundDate")[0].value="";
	document.getElementsByName("foundTimeHr")[0].value="";
	document.getElementsByName("foundTimeMin")[0].value="";
	document.getElementsByName("foundRemarks")[0].value="";
	document.getElementsByName("rackId")[0].value="-1";
	document.getElementsByName("shelfId")[0].value="-1";
	document.getElementsByName("putBy")[0].value="-1";
	
	showRackInfo();
}
</script>

<body>
	<his:TransactionContainer>
		<%String recordTypeName=(String)(((CertificateLostFoundFB)pageContext.findAttribute("CertificateLostFoundFB")).getRecordTypeName()); 
					recordTypeName=recordTypeName+" Found And Archival In MRD";
		%>
		<his:SubTitleTag name="">
			<div align="left">
				<%=recordTypeName %>
			</div>
		</his:SubTitleTag>
		
		<his:statusTransactionInProcess>
			<%String width="20%";%>	
			<logic:equal name="CertificateLostFoundFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET %>">
				<%width="15%"; %>
			</logic:equal>
			<logic:equal name="CertificateLostFoundFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET %>">
				<%width="15%"; %>
			</logic:equal>
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
						<td width="<%=width%>" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="recordId"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="reportedBy"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="<%=width%>" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="lostArea"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="lostType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="<%=width%>" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="lostDate"/>
									</b>	
								</font>
							</div>
						</td>
						<logic:equal name="CertificateLostFoundFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET %>">
							<td width="<%=width%>" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="enclosure"/>
										</b>	
									</font>
								</div>
							</td>
						</logic:equal>
						<logic:equal name="CertificateLostFoundFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET %>">
							<td width="<%=width%>" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="enclosure"/>
										</b>	
									</font>
								</div>
							</td>
						</logic:equal>
					</tr>
					<logic:iterate id="arrLostRecordVO" name="<%=MrdConfig.ARR_LOST_RECORD_LIST_TO_BE_FOUND_BY_RECORD_TYPE_VO %>" indexId="idx" type="hisglobal.vo.RecordLostFoundDtlVO">
						<tr>
							<td width="5%" class="tdfontheadExam">
							<%String tempStr="showEnclosureButton('"+idx.toString()+"')";
								//count++;
							   %>
								<div align="center">
									<html:checkbox name="CertificateLostFoundFB" property="selectedRecord" value="<%=arrLostRecordVO.getDispatchId() %>" onclick="<%=tempStr %>"></html:checkbox>
								</div>
							</td>
							<td width="<%=width%>" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrLostRecordVO.getRecordDesc() %>
									</font>
								</div>
								<html:hidden name="CertificateLostFoundFB" property="hiddenRecordDesc" value="<%=arrLostRecordVO.getRecordDesc() %>"/>
								<html:hidden name="CertificateLostFoundFB" property="hiddenRecordId" value="<%=arrLostRecordVO.getRecordId() %>"/>
							</td>
							<td width="20%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrLostRecordVO.getReportedBy() %>
									</font>
								</div>
							</td>
							<td width="<%=width%>" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrLostRecordVO.getLostArea() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrLostRecordVO.getLostType() %>
									</font>
								</div>
							</td>
							<td width="<%=width%>" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrLostRecordVO.getLostDateTime() %>
										<input type="hidden" name="lostDate" value="<%=arrLostRecordVO.getLostDateTime()%>"/>
									</font>
								</div>
							</td>
							<logic:equal name="CertificateLostFoundFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET %>">
								<% String j=idx.toString();
								String div="divEnclosureId"+j;%>
								<td width="<%=width%>" class="tdfontheadExam">
									<div align="center" id="<%=div %>" style="display: none;">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%String str="getEnclosureDtl('"+idx.toString()+"')"; %>
											<html:button value="Enclosure"  property="mybutton" onclick="<%=str %>"/>
										</font>
									</div>
								</td>
								<html:hidden name="CertificateLostFoundFB" property="verifiedEnclosure" value="<%=MrdConfig.NO %>"/>
							</logic:equal>
							<logic:equal name="CertificateLostFoundFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET %>">
								<% String k=idx.toString();
								String div1="divEnclosureId"+k;%>
								<td width="<%=width%>" class="tdfontheadExam">
									<div align="center" id="<%=div1 %>" style="display: none;">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%String str1="getEnclosureDtl('"+idx.toString()+"')"; %>
											<html:button value="Enclosure"  property="mybutton" onclick="<%=str1 %>"/>
										</font>
									</div>
								</td>
								<html:hidden name="CertificateLostFoundFB" property="verifiedEnclosure" value="<%=MrdConfig.NO %>"/>
							</logic:equal>
						</tr> 
					</logic:iterate>
				</table>
			</his:ContentTag>
			
			<his:SubTitleTag name="Record Found Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="foundBy"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="CertificateLostFoundFB" property="foundBy" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(this,event)" tabindex="1"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td>
					</tr>
					<tr>
						 <bean:define name="CertificateLostFoundFB" property="foundDate" id="foundDate" type="java.lang.String"/>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="foundDate"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="foundDate" dateFormate="%d-%b-%Y" value="<%=foundDate %>"></his:date>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="found"/>
									<bean:message key="time"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="CertificateLostFoundFB" tabindex="1" property="foundTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
								<b>
									<bean:message key="colon"/>
								</b>
								<html:text name="CertificateLostFoundFB" tabindex="1" property="foundTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('foundTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
								<bean:message key="timeFormat"/>
							</div>
						</td>
					</tr>
					<tr>	
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="found"/>
									<bean:message key="remarks"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont" colspan="3">
							<div align="left">
								<html:textarea name="CertificateLostFoundFB" property="foundRemarks" rows="1" cols="90" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" tabindex="1"></html:textarea>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			
			<his:SubTitleTag name="Record Archival Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="rack"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="CertificateLostFoundFB" property="rackId" onchange="showRackShelf()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_OF_RACK_BASED_ON_MRD %>" >
										<html:options collection="<%=MrdConfig.LIST_OF_RACK_BASED_ON_MRD %>" property = "rackId" labelProperty = "rackName"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="shelf"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="CertificateLostFoundFB" property="shelfId">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_OF_SHELF_BASED_ON_RACK %>" >
										<html:options collection="<%=MrdConfig.LIST_OF_SHELF_BASED_ON_RACK %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="putBy"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="CertificateLostFoundFB" property="putBy" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" >
										<html:options collection="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
						</td>
						<td width="25%" class="tdfont">
						</td>
					</tr>
				</table>
				<div id="divRackInfoId" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="rack"/>
										<bean:message key="information"/>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfont">
								<div align="left">
									&nbsp;<bean:write name="CertificateLostFoundFB" property="rackInfo" />
								</div>
							</td>
						</tr>
					</table>		
				</div>
			</his:ContentTag>
			<% 
			   String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			%>
			<html:hidden name="CertificateLostFoundFB" property="sysDate" value="<%=sysDate%>"/>	
		</his:statusTransactionInProcess>
		
		<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</his:statusTransactionInProcess>
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
			</his:statusUnsuccessfull>	
		</his:ButtonToolBarTag>
		
	</his:TransactionContainer>
	
		<html:hidden name="CertificateLostFoundFB" property="hmode" value="unspecified"/>
		<html:hidden name="CertificateLostFoundFB" property="recordType" />
		<html:hidden name="CertificateLostFoundFB" property="recordTypeName"/>
		<html:hidden name="CertificateLostFoundFB" property="mrdCode" />
		<html:hidden name="CertificateLostFoundFB" property="tempSelectedChk" />
		<html:hidden name="CertificateLostFoundFB" property="tempVerifiedEnclosure" />
		<html:hidden name="CertificateLostFoundFB" property="hiddenTimeHr" />
		<html:hidden name="CertificateLostFoundFB" property="hiddenTimeMin" />
		<html:hidden name="CertificateLostFoundFB" property="recordId"/>
		
</body>
</html>		
