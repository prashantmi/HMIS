<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.CertificateAcceptFB"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>

<script type="text/javascript">

window.onload=function()
{
	showAcceptLostDiv();
	showRackInfo();
	
	if(document.getElementsByName("recordType")[0].value==<%=MrdConfig.RECORD_TYPE_OPD_FILE%>)
		document.getElementsByName("acceptLostFlag")[1].disabled="true";
		
	if(document.getElementsByName("acceptLostFlag")[0].checked)
		checkedSelectedValue();
		
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

function showAcceptLostDiv()
{
	if(document.getElementsByName("acceptLostFlag")[0].checked)
	{
		document.getElementById("divAcceptId").style.display="block";
		document.getElementById("divLostId").style.display="none";
	}	
	else
	{
		document.getElementById("divAcceptId").style.display="none";
		document.getElementById("divLostId").style.display="block";
	}
}

function showRackShelf()
{
	showRackInfo();
	var rackId=document.getElementsByName("rackId")[0].value;
	if(rackId!="-1")
		submitForm21('GETSHELF');
}

function showRackInfo()
{
	if(document.getElementsByName("rackId")[0].value=="-1")
		document.getElementById("divRackInfoId").style.display="none";
	else
		document.getElementById("divRackInfoId").style.display="block";
}

function showRack()
{
	var mrdCode=document.getElementsByName("mrdCode")[0].value;
	if(mrdCode!="-1")
		submitForm21('GETRACK');
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
				if(document.getElementsByName("acceptLostFlag")[0].checked)
				{
					if(document.getElementsByName("verifiedEnclosure")[i].value==<%=MrdConfig.NO%>)
					{
						alert("Please Verified The Enclosure of "+document.getElementsByName("hiddenRecordDesc")[i].value);
						return false;
					}
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
		if(document.getElementsByName("acceptLostFlag")[0].checked)
		{
			if(document.getElementsByName("rackId")[0].value=="-1")
				submitForm21('ACCEPT');
			else
				validateArchival();
		}
		else
		{
			validateLost();
		}
	}
	
}

function validateLost()
{
	if(comboValidation(document.forms[0].reportedBy,"Reported By")
		&& isEmpty(document.forms[0].lastSeenDate,"Last Seen Date")
		&& isEmpty(document.forms[0].lastSeenTimeHr,"Last Seen Time Hr")
		&& isEmpty(document.forms[0].lastSeenTimeMin,"Last Seen Time Min")
		&& validateLastSeenDate()
		&& isEmpty(document.forms[0].lostArea,"Lost Area")
	)
	{
	if(!checkLastSeenDate()) 
	{
		alert("Last Seen Date Should be Greater Than Record Dispatch Date");
		document.getElementsByName("lastSeenDate")[0].focus();
		valid=false;
	}
	else
	submitForm21('LOST');
	}
}

function validateArchival()
{
	var rackId=document.getElementsByName("rackId")[0].value;
	var shelfId=document.getElementsByName("shelfId")[0].value;
	var putBy=document.getElementsByName("putBy")[0].value;
	
	if(shelfId=="-1")
	{
		alert("Please Select Shelf To Archive The Record")
		document.getElementsByName("shelfId")[0].focus();
		return false;
	}
	else if(putBy=="-1")
	{
		alert("Please Select Put By")
		document.getElementsByName("putBy")[0].focus();
		return false;
	}

	submitForm21('ARCHIVAL');	
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

function validateLastSeenDate()
{
	var valid=true;
	var dispatchDate=document.getElementsByName("dispatchDate")[0].value;
	//alert("Dispatch Date = "+dispatchDate);
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
		{
			valid=true;
		}
	}
	
	return valid;
}
function checkLastSeenDate()
{
	
	var dispatchDate=document.getElementsByName("dispatchDate")[0].value;
	//alert("Dispatch Date = "+dispatchDate);
	var lastSeenDate=document.getElementsByName("lastSeenDate")[0].value;
    var lastSeen_date=convertStrToDate(lastSeenDate, "dd-Mon-yyyy");
      
    var b=dispatchDate.toString(); 
    var dispatchDate_dateArr=b.split(" "); // date and time are splitted here
    var dispatchDate_date=dispatchDate_dateArr[0];
    var dispatchDate_time=dispatchDate_dateArr[1];
    var timeArr=dispatchDate_time.split(":"); // hours and mins are splitted here
    var dispatchDate_hr= timeArr[0];
    var dispatchDate_min=timeArr[1];
    
   
    var dispatchDate_date=convertStrToDate(dispatchDate, "dd-Mon-yyyy");
    
    if(lastSeen_date<=dispatchDate_date) 
    {
    	if(lastSeen_date.toLocaleString()==dispatchDate_date.toLocaleString())
    	{
    		var lastSeenTime_hr=document.getElementsByName("lastSeenTimeHr")[0].value;
    		var lastSeenTime_min=document.getElementsByName("lastSeenTimeMin")[0].value;
    		if(lastSeenTime_hr<=dispatchDate_hr)
    		{
    			if(lastSeenTime_hr==dispatchDate_hr)
    			{
    				if(lastSeenTime_min<dispatchDate_min)  
    				   return false;
    			}
    			else 
    			    return false;
    		}
        }
       else 
          return false;
    }
  return true;
}



function clearForm()
{
	if(document.getElementsByName("acceptLostFlag")[0].checked)
	{
		document.getElementsByName("rackId")[0].value="-1";
		document.getElementsByName("shelfId")[0].value="-1";
		document.getElementsByName("putBy")[0].value="-1";
		showRackInfo();
	}
	else
	{
		document.getElementsByName("reportedBy")[0].value="-1";
		document.getElementsByName("lastSeenDate")[0].value="";
		document.getElementsByName("lastSeenTimeHr")[0].value="";
		document.getElementsByName("lastSeenTimeMin")[0].value="";
		document.getElementsByName("lostArea")[0].value="";
		document.getElementsByName("lostType")[0].checked=true;
		document.getElementsByName("lostRemarks")[0].value="";
	}
}

function validateOnSearchDate()
{
	var schDate=document.getElementsByName("searchDate")[0].value;
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var days=noOfDays(schDate,sysDate);
	if(days<0)
	{
		alert("Search Date Cannot Be Greater Than System Date");
	}	
	else
		submitForm('NEW')
}

</script>

<body>
		<% 
			   String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			%>
	<his:TransactionContainer>
		<%String recordTypeName=(String)(((CertificateAcceptFB)pageContext.findAttribute("CertificateAcceptFB")).getRecordTypeName()); 
					recordTypeName=recordTypeName+" Acceptance And Archival In MRD";
		%>
		<his:SubTitleTag name="">
			<div align="left">
				<%=recordTypeName %>
			</div>
		</his:SubTitleTag>
		<his:statusTransactionInProcess>
		<logic:notEqual name="CertificateAcceptFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
			<%String width="10%"; %>		
			<logic:equal name="CertificateAcceptFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET %>">
				<%width="10%"; %>
			</logic:equal>
			<logic:equal name="CertificateAcceptFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET %>">
				<%width="10%"; %>
			</logic:equal>
				<his:ContentTag>
					<table style="width:90vw" border="0"  cellspacing="1" cellpadding="0">
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
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="crNo"/>
								</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="admNo"/>
								</b>	
								</font>
							</div>
						</td>
						<td width="8%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="patientName"/>
								</b>	
								</font>
							</div>
						</td>
						
						<td width="7%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="dischargeDate"/>
								</b>	
								</font>
							</div>
						</td>
							
							<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="from"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="7%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="senderName"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="7%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="broughtbyname"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="<%=width%>" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="dipatchDate"/>
										</b>	
									</font>
								</div>
							</td>
							<logic:equal name="CertificateAcceptFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET %>">
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
							<logic:equal name="CertificateAcceptFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET %>">
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
						<logic:iterate id="arrDisptchRecordVO" name="<%=MrdConfig.ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO %>" indexId="idx" type="hisglobal.vo.RecordDispatchDtlVO">
							<tr>
								<td width="5%" class="tdfontheadExam">
								<%String tempStr="showEnclosureButton('"+idx.toString()+"')"; %>
									<div align="center">
										<html:checkbox name="CertificateAcceptFB" property="selectedRecord" value="<%=arrDisptchRecordVO.getDispatchId() %>" onclick="<%=tempStr %>"></html:checkbox>
									</div>
								</td>
								<td width="<%=width%>" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDisptchRecordVO.getRecordDesc() %>
										</font>
									</div>
									<html:hidden name="CertificateAcceptFB" property="hiddenRecordDesc" value="<%=arrDisptchRecordVO.getRecordDesc() %>"/>
									<html:hidden name="CertificateAcceptFB" property="hiddenRecordId" value="<%=arrDisptchRecordVO.getRecordId() %>"/>
								</td>
								
								
								<td  width="<%=width%>" class="tdfontheadExam">
				  	 <div align="center">
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    	<%=arrDisptchRecordVO.getPatCrNo() %>	 
				   				  		
				   		</font>
				   	 </div>   
					</td>
					<td  width="<%=width%>" class="tdfontheadExam">
				  	 <div align="center">
				  	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				   <%=arrDisptchRecordVO.getPatAdmNo() %>
				     </font>
				    </div>   
					</td>
					<td  width="8%" class="tdfontheadExam">
				  	 <div align="center">
				  	 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    	<%=arrDisptchRecordVO.getPatName() %>
				    	
				    	</font>
				     </div>   
					</td>
					
					<td  width="<%=width%>" class="tdfontheadExam" >
				  	 <div align="center">
				  	 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    	<%=arrDisptchRecordVO.getDischargeDate() %>
				    	
				    	</font>
				     </div>   
					</td>
								
															
								<td width="21%" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDisptchRecordVO.getDepartmentName()%>
											<%=arrDisptchRecordVO.getDepartmentUnitName()==null?"":"/"+arrDisptchRecordVO.getDepartmentUnitName()%>
											<%=arrDisptchRecordVO.getWardName()==null?"":"/"+arrDisptchRecordVO.getWardName()%>
										</font>
									</div>
								</td>
								<td width="7%" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDisptchRecordVO.getSenderName() %>
										</font>
									</div>
								</td>
								<td width="7%" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDisptchRecordVO.getRecipentName() %>
										</font>
									</div>
								</td>
								<td width="<%=width%>" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDisptchRecordVO.getRecipientDate() %>
											<input type="hidden" name="dispatchDate" value="<%=arrDisptchRecordVO.getRecipientDate()%>" />
										</font>
									</div>
								</td>
								<logic:equal name="CertificateAcceptFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET %>">
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
									<html:hidden name="CertificateAcceptFB" property="verifiedEnclosure" value="<%=MrdConfig.NO %>"/>
								</logic:equal>
								<logic:equal name="CertificateAcceptFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_MLC_CASESHEET %>">
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
									<html:hidden name="CertificateAcceptFB" property="verifiedEnclosure" value="<%=MrdConfig.NO %>"/>
								</logic:equal>
							</tr> 
						</logic:iterate>
					</table>
				</his:ContentTag>
			</logic:notEqual>
		</his:statusTransactionInProcess>
			
			<logic:equal name="CertificateAcceptFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
				<bean:define name="CertificateAcceptFB" property="searchDate" id="schDate" type="java.lang.String"/>
				<% 
				if(schDate==null||schDate.equalsIgnoreCase(""))
				{   
					schDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				}	
				%>
				<his:ContentTag>
					
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="center">
									<his:date name="searchDate" dateFormate="%d-%b-%Y" value="<%=schDate%>"></his:date>
								</div>
							</td>
							<td width="75%" class="tdfonthead">
								<div align="left">
									<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateOnSearchDate()" onkeypress="if(event.keyCode==13) validateOnSearchDate()">
								</div>
							</td>
						</tr>
					</table>
					
				<his:statusTransactionInProcess>	
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="fileNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="from"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="dipatchDate"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
						<logic:iterate id="arrDisptchRecordVO" name="<%=MrdConfig.ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO %>" indexId="idx" type="hisglobal.vo.RecordDispatchDtlVO">
							<tr>
								<td width="5%" class="tdfontheadExam">
									<div align="center">
										<html:checkbox name="CertificateAcceptFB" property="selectedRecord" value="<%=arrDisptchRecordVO.getDispatchId() %>" ></html:checkbox>
									</div>
								</td>
								<td width="30%" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDisptchRecordVO.getRecordDesc() %>
										</font>
									</div>
								</td>
								<td width="30%" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDisptchRecordVO.getDepartmentName()%>
											<%=arrDisptchRecordVO.getDepartmentUnitName()==null?"":"/"+arrDisptchRecordVO.getDepartmentUnitName()%>
										</font>
									</div>
								</td>
								<td width="30%" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDisptchRecordVO.getSenderDate() %>
										</font>
									</div>
								</td>
							</tr>
						</logic:iterate>	
					</table>
				</his:statusTransactionInProcess>	
					
				</his:ContentTag>			
			</logic:equal>
		<his:statusTransactionInProcess>	
			<his:SubTitleTag name="">
				<div align="left">
					<html:radio name="CertificateAcceptFB" property="acceptLostFlag" value="<%=MrdConfig.CERTIFICATE_ACCEPT_IN_MRD %>" onclick="showAcceptLostDiv()"></html:radio>
					<bean:message key="accept"/>
					<html:radio name="CertificateAcceptFB" property="acceptLostFlag" value="<%=MrdConfig.CERTIFICATE_LOST_BEFORE_MRD %>" onclick="showAcceptLostDiv()"></html:radio>
					<bean:message key="lost"/>
				</div>
			</his:SubTitleTag>
			
			<div id="divAcceptId">
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="rack"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="CertificateAcceptFB" property="rackId" onchange="showRackShelf()">
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
										<bean:message key="shelf"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="CertificateAcceptFB" property="shelfId">
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
										<bean:message key="putBy"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<%UserVO userVO=ControllerUTIL.getUserVO(request);%>
								 <%=userVO.getUsrName() %>
								<html:hidden name="CertificateAcceptFB" property="putBy" value="<%=userVO.getUserEmpID()%>"/>
							
								<%-- 
									<html:select name="CertificateAcceptFB" property="putBy" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" >
											<html:options collection="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select> --%>
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
										&nbsp;<bean:write name="CertificateAcceptFB" property="rackInfo" />
									</div>
								</td>
							</tr>
						</table>		
					</div>	
				</his:ContentTag>
			</div>
			
			<div id="divLostId">
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
									<html:select name="CertificateAcceptFB" property="reportedBy" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" >
											<html:options collection="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" property = "value" labelProperty = "label"/>
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
										<html:radio name="CertificateAcceptFB" property="lostType" value="<%=MrdConfig.RECORD_LOST_TYPE_COMPLETE %>" disabled="true"></html:radio>
										<bean:message key="complete"/>	
										<html:radio name="CertificateAcceptFB" property="lostType" value="<%=MrdConfig.RECORD_LOST_TYPE_PARTIAL %>" disabled="true"></html:radio>
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
									<html:text name="CertificateAcceptFB" tabindex="1" property="lastSeenTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
									<b>
										<bean:message key="colon"/>
									</b>
									<html:text name="CertificateAcceptFB" tabindex="1" property="lastSeenTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('lastSeenTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
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
									<html:text name="CertificateAcceptFB" property="lostArea" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
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
									<html:textarea name="CertificateAcceptFB" property="lostRemarks" rows="1" cols="96" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" tabindex="1"></html:textarea>
								</div>
							</td>
						</tr>	
					</table>
				</his:ContentTag>		
			</div>
			
			
		</his:statusTransactionInProcess>
		
		<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm21('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</his:statusTransactionInProcess>
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm21('CANCEL')">
			</his:statusUnsuccessfull>	
		</his:ButtonToolBarTag>
		
		<html:hidden name="CertificateAcceptFB" property="hmode" value="unspecified"/>
		<html:hidden name="CertificateAcceptFB" property="recordType"/>
		<html:hidden name="CertificateAcceptFB" property="recordTypeName"/>
		<html:hidden name="CertificateAcceptFB" property="mrdCode"/>
		<html:hidden name="CertificateAcceptFB" property="tempSelectedChk"/>
		<html:hidden name="CertificateAcceptFB" property="dispatchId"/>
		<html:hidden name="CertificateAcceptFB" property="hiddenTimeHr" />
		<html:hidden name="CertificateAcceptFB" property="hiddenTimeMin" />
		<html:hidden name="CertificateAcceptFB" property="rackInfo"/>
		<html:hidden name="CertificateAcceptFB" property="recordId"/>
		<html:hidden name="CertificateAcceptFB" property="tempVerifiedEnclosure" />
		<html:hidden name="CertificateAcceptFB" property="sysDate" value="<%=sysDate%>"/>
					
	</his:TransactionContainer>


</body>

</html>	