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
<%@page import="mrd.transaction.controller.fb.RecordFoundInMrdFB"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.vo.MrdRecordLostFoundDtlVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
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
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">

window.onload=function()
{
	showArchivalDetail();
	showArchival();
	showRackInfo();
}

function submitFoundDtl(obj)
{
	var obj1=obj.value.split("#");
	var obj2=obj1[0];
	var obj3=obj1[1];
	//alert("Lost Time Obj="+obj3);
	document.getElementsByName("lostDateTime")[0].value=obj3;
	document.getElementsByName("selectedMrdRecordId")[0].value=obj2.split("@")[0];
	document.getElementsByName("recordType")[0].value=obj2.split("@")[1];
	document.getElementsByName("mrdCode")[0].value=obj2.split("@")[2];
	//document.getElementsByName("selectedMrdRecordId")[0].value=obj.value.split("@")[0];
	//document.getElementsByName("recordType")[0].value=obj.value.split("@")[1];
	//document.getElementsByName("mrdCode")[0].value=obj.value.split("@")[2];
	submitForm21('GETFOUND');
}
function validateSave()
{
	if(validateFoundDetail() && validateStorage())
		submitForm('SAVE');
}

function validateStorage()
{
	var valid=false;
	if(document.getElementsByName("recAcceptArchivedFlag")[0].checked)
	{
		if(document.getElementsByName("changeArchivedFlag")[0].checked)
		{
			if(document.getElementsByName("isPrevLocationExist")[0].value==<%=MrdConfig.YES%>)
			{
				if(comboValidation(document.forms[0].putBy,"Put By"))
					valid=true;
				else
					valid=false;
			}
			else
			{
				alert("Previously Record Was Not Archived ");
				valid=false;
				document.getElementsByName("changeArchivedFlag")[1].checked=true;
				showArchival();
			}			
		}
		else
		{
			if(comboValidation(document.forms[0].putBy,"Put By")
				&& comboValidation(document.forms[0].rackId,"Rack")
				&& comboValidation(document.forms[0].shelfId,"Shelf")
				&& validateChangedLoc()
			)
				valid=true;
			else
				valid=false;	
		}
	}
	else
	{
		valid=true;
	}
	
	return valid;
//	return false;	
	
}

function validateChangedLoc()
{
	var valid=true;
	var prevLoc=document.getElementsByName("prevLocationCode")[0].value;
	var newLoc=document.getElementsByName("rackId")[0].value+document.getElementsByName("shelfId")[0].value;
	if(prevLoc==newLoc)
	{
		alert("Changed Location Is Same As Previous Location.\n"+"Please Select Different Location");
		document.getElementsByName("shelfId")[0].focus();
		var valid=false;
	}
	else
	{
		valid=true;
	}
	return valid;
}

function validateFoundDetail()
{
	if(isEmpty(document.forms[0].foundBy,"Found By")
		&& isEmpty(document.forms[0].foundDate,"Found Date")
		&& isEmpty(document.forms[0].foundTimeHr,"Found Time Hr")
		&& isEmpty(document.forms[0].foundTimeMin,"Found Time Min")
		&& validateFoundDate()
		&& checkLostFoundDate()
		
	)
		valid=true;
	else	
		valid=false;	
		
	return valid;
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
function checkLostFoundDate()
{
	var valid=true;
	var foundDate=document.getElementsByName("foundDate")[0].value;
	var lostDateTime=document.getElementsByName("lostDateTime")[0].value;
	
	//alert("Lost Date Time = "+lostDateTime);
	var b=lostDateTime.toString(); 
    var lostDate_dateArr=b.split(" "); // date and time are splitted here
    var lost_date=lostDate_dateArr[0];
    var lost_time=lostDate_dateArr[1];
    var timeArr=lost_time.split(":"); // hours and mins are splitted here
    var lost_hr= timeArr[0];
    var lost_min=timeArr[1];
	
	
	var days=noOfDays(lost_date,foundDate);
	
	if(days<0)
	{
		alert("Found Date Should  be Greater Than Lost Date "+lost_date+" "+lost_hr+":"+lost_min);
		document.getElementsByName("foundDate")[0].focus();
		valid=false;
	}
	else
	{
		if(days==0)
		{
			if(parseInt(trimNum(document.getElementsByName("foundTimeHr")[0].value)) <= lost_hr)
			{
				if(parseInt(trimNum(document.getElementsByName("foundTimeHr")[0].value)) == lost_hr)
				{
					if(parseInt(trimNum(document.getElementsByName("foundTimeMin")[0].value)) < lost_min)
					{
					alert("Found Date Should be Greater than Lost Date "+lost_date+" "+lost_hr+":"+lost_min);
					document.getElementsByName("foundTimeMin")[0].focus();
					 valid=false;
					}
					else
					valid=true;
				}
				else
				{
					alert("Found Date Should be Greater than Lost Date "+lost_date+" "+lost_hr+":"+lost_min);
					document.getElementsByName("foundTimeHr")[0].focus();
					valid=false;
				}
			}
			//else if(parseInt(trimNum(document.getElementsByName("foundTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("foundTimeMin")[0].value)) > min)
			//{
				//alert("Found Date Cannot be Greater than "+sysDate+" "+ntHour+":"+ntMin);
				//document.getElementsByName("foundTimeMin")[0].focus();
				//valid=false;
			//}
		}
		else
			valid=true;
	}
	
	return valid;
}

function showArchivalDetail()
{
	if(document.getElementsByName("recAcceptArchivedFlag")[0].checked)
		document.getElementById("divArchivalDetail").style.display="block";
	else	 
		document.getElementById("divArchivalDetail").style.display="none";
}

function showArchival()
{
	if(document.getElementsByName("changeArchivedFlag")[0].checked)
	{
		document.getElementById("divChangeArchived").style.display="none";
		//document.getElementById("divRackInfoId").style.display="none";
		document.getElementById("divRackPrevLocId").style.display="block";
	}	
	else
	{	 
		document.getElementById("divChangeArchived").style.display="block";
		document.getElementById("divRackPrevLocId").style.display="none";
		showRackInfo();
	}	
}

function showRackInfo()
{
	if(document.getElementsByName("rackId")[0].value=="-1")
		document.getElementById("divRackInfoId").style.display="none";
	else
		document.getElementById("divRackInfoId").style.display="block";
}


function showRackShelf()
{
	showRackInfo();
	var rackId=document.getElementsByName("rackId")[0].value;
	if(rackId!="-1")
		submitForm('GETSHELF');
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
	document.getElementsByName("foundBy")[0].value="";
	document.getElementsByName("foundDate")[0].value="";
	document.getElementsByName("foundTimeHr")[0].value="";
	document.getElementsByName("foundTimeMin")[0].value="";
	document.getElementsByName("foundRemarks")[0].value="";
	document.getElementsByName("putBy")[0].value="-1";
	document.getElementsByName("rackId")[0].value="-1";
	document.getElementsByName("shelfId")[0].value="-1";
	showRackInfo();
	
}


function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function showEmployeepopup(e,fieldToPopulate,index)
{
	//alert (fieldToPopulate);
	//alert(index);
	//var dept = document.getElementsByName("deptname")[0].value;
	var path="/HISClinical/mrd/employeePopUp.cnt?fieldToPopulate="+fieldToPopulate+"&index="+index
	openPopup(createFHashAjaxQuery(path),e,300,600);
}

</script>
<body>
	<his:TransactionContainer>
		<html:form action="/recordFoundInMrd">
		<his:SubTitleTag name="Mrd Record Found Detail">
		</his:SubTitleTag>
		<his:statusList>
			<logic:present name="<%=MrdConfig.ARR_MRD_LOST_RECORD_LIST_VO%>">
				<%
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((RecordFoundInMrdFB)request.getAttribute("RecordFoundInMrdFB")).getCurrentPage());
					fbPage.setObjArrName(MrdConfig.ARR_MRD_LOST_RECORD_LIST_VO);
					fbPage.setAppendInTitle("Lost Record");
					fbPage.setMaxRecords(10);
					%>
					<html:hidden name="RecordFoundInMrdFB" property="currentPage"/>
					<his:PaginationTag name="fbPagination"></his:PaginationTag>
					
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="recordId"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="recordType"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="12%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="reportedBy"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="lostArea"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
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
											<bean:message key="gender/age"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
						<%
						MrdRecordLostFoundDtlVO[] mrdLostRecordVO=(MrdRecordLostFoundDtlVO[])session.getAttribute(MrdConfig.ARR_MRD_LOST_RECORD_LIST_VO);
						int startIndexEpi = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
						int endIndexEpi = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
						
						for(int i=startIndexEpi; i<=endIndexEpi; i++)
						{
							MrdRecordLostFoundDtlVO lostVO=mrdLostRecordVO[i];
						%>
						
			 			<tr>
							<td width="5%" class="tdfontheadExam">
								<div align="center">
									<html:radio name="RecordFoundInMrdFB" property="mrdRecordId" value="<%=lostVO.getMrdRecordId()+'@'+lostVO.getRecordType()+'@'+lostVO.getMrdCode()+'#'+lostVO.getLostDateTime() %>" onclick="submitFoundDtl(this)" tabindex="1"></html:radio>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<%=lostVO.getRecordDesc() %>
							   		</font>
							    </div>   
							</td>
							<td  width="13%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  			<%=lostVO.getRecordTypeLabel() %>
							    	</font>
							    </div>   
							</td>
							<td width="12%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<%=lostVO.getReportedBy() %>
							   		</font>
							    </div>   
							</td>
							<td width="15%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<%=lostVO.getLostArea() %>
							   		</font>
							    </div>   
							</td>
							<td width="15%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<%=lostVO.getPatName() %>
							   		</font>
							    </div>   
							</td>
							<td width="15%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<%=lostVO.getPatCrNo() %>
							   		</font>
							    </div>   
							</td>
							<td width="10%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<%=lostVO.getPatGender() %>/
							  	 		<%=lostVO.getPatAge() %>
							  	 		
							   		</font>
							    </div>   
								
							</td>
						</tr>
              			<% 
              			} 
						  %>
                	</table>
              			
				</his:ContentTag>
			</logic:present>	
		</his:statusList>
		<his:statusTransactionInProcess>
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
							 <% /* Added by Dheeraj to get Employee Id */ %>
								<html:hidden name="RecordFoundInMrdFB" property="empId" />
								<html:text name="RecordFoundInMrdFB" property="foundBy" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(this,event)" tabindex="1" readonly="true"></html:text>
								<img class="button" src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1" border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event,'requestById@requestByName@requestByEmpDept','<%=0 %>');" 
										onclick="showEmployeepopup(event,'empId@foundBy','<%=0 %>');" alt="Search Employee" title="Search Employee">
							</div>
						</td>
						<td width="25%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td>
					</tr>
					<tr>
						 <bean:define name="RecordFoundInMrdFB" property="foundDate" id="foundDate" type="java.lang.String"/>
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
								<html:text name="RecordFoundInMrdFB" tabindex="1" property="foundTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
								<b>
									<bean:message key="colon"/>
								</b>
								<html:text name="RecordFoundInMrdFB" tabindex="1" property="foundTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('foundTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
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
								<html:textarea name="RecordFoundInMrdFB" property="foundRemarks" rows="1" cols="90" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" tabindex="1"></html:textarea>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			<his:SubTitleTag name="">
			<div align="left">
				<html:checkbox name="RecordFoundInMrdFB" property="recAcceptArchivedFlag" onclick="showArchivalDetail()" tabindex="1"></html:checkbox>
				Record Archival Detail
			</div>
			</his:SubTitleTag>
			<div id="divArchivalDetail">
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%%" class="tdfont">
								<div align="left">
									<html:radio name="RecordFoundInMrdFB" property="changeArchivedFlag" value="<%=MrdConfig.NO %>" onclick="showArchival()" tabindex="1"></html:radio>
									<bean:message key="previousPlace"/>
									<html:radio name="RecordFoundInMrdFB" property="changeArchivedFlag" value="<%=MrdConfig.YES %>" onclick="showArchival()" tabindex="1"></html:radio>
									<bean:message key="change"/> <bean:message key="archived"/>
								</div>
							</td>
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
									<html:select name="RecordFoundInMrdFB" property="putBy" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" >
											<html:options collection="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
					</table>
					<div id="divChangeArchived">
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
										<html:select name="RecordFoundInMrdFB" property="rackId" onchange="showRackShelf()" tabindex="1">
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
										<html:select name="RecordFoundInMrdFB" property="shelfId" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.LIST_OF_SHELF_BASED_ON_RACK %>" >
												<html:options collection="<%=MrdConfig.LIST_OF_SHELF_BASED_ON_RACK %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
									</div>
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
											&nbsp;<bean:write name="RecordFoundInMrdFB" property="rackInfo" />
										</div>
									</td>
								</tr>
							</table>		
						</div>
					</div>	
					<div id="divRackPrevLocId" style="display: none;">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="record"/>
											<bean:message key="location"/>
										</font>
									</div>
								</td>
								<td width="75%" class="tdfont">
									<div align="left">
										&nbsp;<bean:write name="RecordFoundInMrdFB" property="prevLocation"/>
									</div>
								</td>
							</tr>
						</table>		
					</div>		
					
				</his:ContentTag>
			</div>	
		</his:statusTransactionInProcess>		
			<% 
			   String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			%>
			<html:hidden name="RecordFoundInMrdFB" property="sysDate" value="<%=sysDate%>"/>
		
		
		<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</his:statusTransactionInProcess>
			<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
			</his:statusList>
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</his:statusUnsuccessfull>	
		</his:ButtonToolBarTag>
		
		<html:hidden name="RecordFoundInMrdFB" property="hmode"/>
		<html:hidden name="RecordFoundInMrdFB" property="hiddenTimeHr" />
		<html:hidden name="RecordFoundInMrdFB" property="hiddenTimeMin" />
		<html:hidden name="RecordFoundInMrdFB" property="recordType" />
		<html:hidden name="RecordFoundInMrdFB" property="mrdCode" />
		<html:hidden name="RecordFoundInMrdFB" property="selectedMrdRecordId" />
		<html:hidden name="RecordFoundInMrdFB" property="prevLocation" />
		<html:hidden name="RecordFoundInMrdFB" property="prevLocationCode" />
		<html:hidden name="RecordFoundInMrdFB" property="isPrevLocationExist" />
		<html:hidden name="RecordFoundInMrdFB" property="lostDateTime" />
		</html:form>
		<his:status/>
	</his:TransactionContainer>	
</body>
</html>
