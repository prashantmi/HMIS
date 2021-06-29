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

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="registration.controller.fb.HandoverToDeadBodyFB"%>
<%@page import="hisglobal.vo.PatientDeathDetailVO"%>
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
	
	<his:javascript src="/registration/js/registration.js" />
	<his:javascript src="/registration/js/validationCalls.js" />
	<his:javascript src="/registration/js/validationCommon.js" />
	<his:javascript src="/registration/js/commonFunctions.js" />
	<his:javascript src="/hisglobal/js/calendar.js"/>
	<his:javascript src="/registration/js/popup.js"/>

<script type="text/javascript">
       
       
 function submitPatientCrNo(crno,mode)
 {
   document.getElementsByName('patCrNo')[0].value=crno.value;
          submitPage(mode);
 }
       
       
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
       
       
function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}       
       
       
function showHandoverDetail()
 {
	if(document.getElementsByName("bodyHandoverTo")[0].value=="1" || document.getElementsByName("bodyHandoverTo")[0].value=="-1")
	{
		document.getElementById("divPolice").style.display="none";
		document.getElementById("divRelative").style.display="none";
		
		document.getElementsByName('officerName')[0].value="";
		document.getElementsByName('officerDesignation')[0].value="";
		document.getElementsByName('officerBadgeNo')[0].value="";
		document.getElementsByName('relativeName')[0].value="";
		document.getElementsByName('relativeCode')[0].value="-1";
		document.getElementsByName('relativeAddress')[0].value="";
		
	}
	if(document.getElementsByName("bodyHandoverTo")[0].value=="2")
	{
		document.getElementById("divPolice").style.display="block";
		document.getElementById("divRelative").style.display="none";
		
		document.getElementsByName('relativeName')[0].value="";
		document.getElementsByName('relativeCode')[0].value="-1";
		document.getElementsByName('relativeAddress')[0].value="";
	}
	if(document.getElementsByName("bodyHandoverTo")[0].value=="3")
	{
		document.getElementById("divPolice").style.display="none";
		document.getElementById("divRelative").style.display="block";
		
		document.getElementsByName('officerName')[0].value="";
		document.getElementsByName('officerDesignation')[0].value="";
		document.getElementsByName('officerBadgeNo')[0].value="";
	}
 }
       
 function openDeathPrintPopup()
   {

	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var noOfCopies=document.getElementsByName("noOfCopies")[0].value;
	var isInpatient=document.getElementsByName("isInpatient")[0].value;
	var path='/HISClinical/registration/handoverToDeadBody.cnt?hmode=PRINTCERT&patCrNo='+patCrNo+'&noOfCopies='+noOfCopies+'&isInpatient='+isInpatient;
	//alert("patCrNo:-"+patCrNo+"noOfCopies:-"+noOfCopies+"isInpatient:-"+isInpatient+"path:-"+path);
	openPrintPopup(path,700,800);
	document.getElementsByName("printFlag")[0].value=<%=RegistrationConfig.PRINT_FLAG_NO%>;
  }
  
  
window.onload = function() 
 {
	if(document.getElementsByName("printFlag")[0].value==<%=RegistrationConfig.PRINT_FLAG_YES%>)
		openDeathPrintPopup();
		
	if(document.getElementsByName('flagForPrint')[0].value=="0")	
		showHandoverDetail();
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

function certificateHanover()
{
 var valid=false;
   	if(isEmpty(document.getElementsByName('certificateHandoverTo')[0],"Certificate Handover To"))
   	  { valid=true;}else{ valid=false; }
  return valid;
}

function validateFinal()
{
 var flag=document.getElementsByName('flagForPrint')[0].value;
  //alert("falg"+flag)
  valid=false;
  if(flag=="0")
  {
     valid=certificateHanover();
     
  }
  if(flag=="1")
  {
    valid= validateHandoverToDetail();
  }
  return valid;
}

function validateHandoverToDetail()
{
     // alert("validateHandoverDate()"+validateHandoverDate())
	 // alert("validateIsHandoverTo()"+validateIsHandoverTo())
	  
	  var valid=false;;
	if(validateHandoverDate() && validateIsHandoverTo())
		  {
		  	valid=true;  }
		else
		  {	valid=false; }	 

	return valid;
}

function validateIsHandoverTo()
{
	var valid=true;
	//alert("validateIsHandoverTo");
	
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
  // alert("validateHandoverDate");
	var valid=true;
	if(isEmpty(document.getElementsByName('handoverDate')[0],"Handover Date")
		&& isEmpty(document.getElementsByName('handoverTimeHr')[0],"Handover Hour")
		&& isEmpty(document.getElementsByName('handoverTimeMin')[0],"Handover Minute")
	)	
	{
	//   alert("valid");
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

	
	var ntHour=document.getElementsByName("hiddenTimeHr")[0].value;
	var ntMin=document.getElementsByName("hiddenTimeMin")[0].value;
	
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var handoverDate=document.getElementsByName("handoverDate")[0].value;
	
	day=parseInt((noOfDays(sysDate,handoverDate)));
	
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
				alert("Handover Date  Cannot be Greater than "+sysDate+" "+ntHour+":"+tnMin);
				document.getElementsByName("handoverTimeMin")[0].focus();
				valid=false;
			}
		}
		else
		{
			alert("Handover Date Cannot be Greater than Current Date")
			valid=false;
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
 
 
 
 
 
 
</script>




<html:form action="/handoverToDeadBody">

     <his:TitleTag key="deadbodyHandover">
     </his:TitleTag>
     
	<his:statusList>
	  
	       <% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((HandoverToDeadBodyFB)request.getAttribute("HandoverToDeadBodyFB")).getCurrentPage());
				fbPage.setObjArrName(RegistrationConfig.LIST_OF_DEAD_PATIENT);
				fbPage.setAppendInTitle("Patient");
				fbPage.setMaxRecords(10);
			%>
			<html:hidden name="HandoverToDeadBodyFB" property="currentPage"/>

			<his:PaginationTag name="fbPagination"></his:PaginationTag>
	    
	    
	    <his:ContentTag>
       <table width="100%" cellpadding="0" cellspacing="1">
          <tr>
			<td width="5%" class="tdfonthead">
				<div align="center">
				 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="select" /> 
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
				 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="crNo" /> 
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
			      	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="name" /> 
					</font>
				</div>
			</td>
			<td width="14%" class="tdfonthead">
				<div align="center">
				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="deathdate" /> 
				</font>
				</div>
			</td>
			<td width="11%" class="tdfonthead">
				<div align="center">
				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="gender/age" /> 
				</font>	
				</div>
			</td>
			<td width="22%" class="tdfonthead">
			  <div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    <bean:message key="handoverstatus" />  
					</font>
				</div>
			</td>
			<td width="18%" class="tdfonthead">
			  <div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				      <bean:message key="certificateprintstatus" />  
				</font>
			  </div>
			</td>
          </tr>  
              
              
              	<%
              	   PatientDeathDetailVO[] patientVos=(PatientDeathDetailVO[])session.getAttribute(RegistrationConfig.LIST_OF_DEAD_PATIENT);
						
						int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX));
						int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX));
						
						for(int j=startIndex;j<=endIndex;j++)
						{
							PatientDeathDetailVO patientVo=patientVos[j];
				%>
					  <tr>
						<td width="5%" class="tdfont">
						 <div align="center">
						  <html:radio name="HandoverToDeadBodyFB" property="chkPatCrNo" value="<%=patientVo.getPatCrNo()%>" onclick="submitPatientCrNo(this,'GETPATDTL')" >  </html:radio>
						 </div>
						</td>
						<td width="15%" class="tdfont" nowrap="nowrap">
						<div align="center">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;
						   <%=patientVo.getPatCrNo() %>
						 </font>
						</div>
						</td>
						<td width="15%" class="tdfont" nowrap="nowrap">
						  <div align="center" >
						   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;
						     <%=patientVo.getPatName() %>
						   </font>
						  </div>
						</td>
						<td width="14%" class="tdfont" nowrap="nowrap">
						  <div align="center" >
						   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;
						    <%=patientVo.getDeathDate() %>
						    
						   </font>					              
						  </div>
						</td>
						<td width="11%" class="tdfont" nowrap="nowrap"> 
						  <div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;
						      <%=patientVo.getPatAge() %>
						     
						    </font>								              
						  </div>
						</td>
						<td width="22%" class="tdfont" nowrap="nowrap">
						   <div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;
						     <%=patientVo.getBodyHandoverTo() %>
						   
						    </font>		
							</div>
						</td>
						<td width="18%" class="tdfont" nowrap="nowrap">
						   <div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp;
						     <%=patientVo.getDeathCertificateId() %>
						    </font>		
							</div>
						</td>
					</tr>	
					<%} %>
	       
	         </table>
	         </his:ContentTag>
	         
	         	 
	</his:statusList>
	
	<his:statusTransactionInProcess>
	
	
		<bean:define name="HandoverToDeadBodyFB" property="sysDate" id="sysDate" type="java.lang.String" />
	<%
		if(sysDate==null||sysDate.equalsIgnoreCase(""))
		{
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
	%>
	
		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
		<his:SubTitleTag name="">
			<div align="left">
				Dead Body Handover Detail
			</div>
		</his:SubTitleTag>
		  <logic:equal name="HandoverToDeadBodyFB" property="flagForPrint" value="1">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b> <bean:message key="handoverdate"/> </b>	
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
								<html:text name="HandoverToDeadBodyFB" tabindex="1" property="handoverTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
								<b>
									<bean:message key="colon"/>
								</b>
								<html:text name="HandoverToDeadBodyFB" tabindex="1" property="handoverTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('handoverTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
								<bean:message key="timeFormat"/>	
							</div>
						</td>
					</tr>
					<logic:equal name="HandoverToDeadBodyFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_NO %>">
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
								<html:select name="HandoverToDeadBodyFB"  property="bodyHandoverTo" onchange="showHandoverDetail()">
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
					<logic:equal name="HandoverToDeadBodyFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_YES %>">
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
									<html:select name="HandoverToDeadBodyFB"  property="bodyHandoverTo" onchange="showHandoverDetail()">
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
								<html:text name="HandoverToDeadBodyFB" property="officerName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
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
									<html:text name="HandoverToDeadBodyFB" property="officerDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
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
									<html:text name="HandoverToDeadBodyFB" property="officerBadgeNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
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
									<html:text name="HandoverToDeadBodyFB" property="relativeName" maxlength="80" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
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
									<html:select name="HandoverToDeadBodyFB" property="relativeCode">
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
									<html:textarea name="HandoverToDeadBodyFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event))"></html:textarea>
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
								<html:checkbox name="HandoverToDeadBodyFB" property="isPrintCertificate"></html:checkbox>
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
								<logic:equal name="HandoverToDeadBodyFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_NO %>">
									<html:text name="HandoverToDeadBodyFB" property="noOfCopies" size="5" value="<%=RegistrationConfig.NO_OF_DEATH_CERTIFICATE_COPIES_NORMAL %>" readonly="true"></html:text>
								</logic:equal>	
								<logic:equal name="HandoverToDeadBodyFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_YES %>">
									<html:text name="HandoverToDeadBodyFB" property="noOfCopies" size="5" value="<%=RegistrationConfig.NO_OF_DEATH_CERTIFICATE_COPIES_MLC %>" readonly="true"></html:text>
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
	    </logic:equal>
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        <logic:equal name="HandoverToDeadBodyFB" property="flagForPrint" value="0">
	           
	           <his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> <bean:message key="handoverdate"/> </b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont" >
							<div align="left">
							   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
							     <bean:write name="HandoverToDeadBodyFB" property="handoverDate"/>
							   </font>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="handovertime"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
							 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
							    <bean:write name="HandoverToDeadBodyFB" property="handoverTimeHr" />
							  </font> 
								<b>
									<bean:message key="colon"/>
								</b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 <bean:write name="HandoverToDeadBodyFB" property="handoverTimeMin" />
								</font>
								<bean:message key="timeFormat"/>	
							</div>
						</td>
					</tr>
					<logic:equal name="HandoverToDeadBodyFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_NO %>">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="handoverto"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
								  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b>&nbsp;
								  <html:hidden name="HandoverToDeadBodyFB" property="bodyHandoverTo"/>
									<logic:equal name="HandoverToDeadBodyFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_MORTUARY %>">
										&nbsp;<b>Mortuary</b>
									</logic:equal>
									<logic:equal name="HandoverToDeadBodyFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>">
										&nbsp;<b>Police</b>
									</logic:equal>
									<logic:equal name="HandoverToDeadBodyFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES %>">
										&nbsp;<b>Relatives</b>
									</logic:equal> 
								</b></font>
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</logic:equal>
					<logic:equal name="HandoverToDeadBodyFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_YES %>">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="handoverto"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
								  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b>&nbsp;
								    <html:hidden name="HandoverToDeadBodyFB" property="bodyHandoverTo"/>
									<logic:equal name="HandoverToDeadBodyFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_MORTUARY %>">
										&nbsp;<b>Mortuary</b>
									</logic:equal>
									<logic:equal name="HandoverToDeadBodyFB" property="bodyHandoverTo" value="<%=RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE %>">
										&nbsp;<b>Police</b>
									</logic:equal>
								</b></font>
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
										<b>
											<bean:message key="officer"/>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
								  <bean:write name="HandoverToDeadBodyFB" property="officerName"/>
								</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="officer"/>
											<bean:message key="designation"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
								    <bean:write name="HandoverToDeadBodyFB" property="officerDesignation"/>
								</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="officer"/>
											<bean:message key="batchno"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
							  <div align="left">
								 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
								      <bean:write name="HandoverToDeadBodyFB" property="officerBadgeNo"/>
								 </font>
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
										<b>
											<bean:message key="relativename"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
								   <bean:write name="HandoverToDeadBodyFB" property="relativeName"/>
								</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="realtionship"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								  <html:hidden name="HandoverToDeadBodyFB" property="relativeCode"/>
								  	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>&nbsp;
								  <bean:write name="HandoverToDeadBodyFB" property="relationName"/>
								  	</b></font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="relativeaddress"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
								 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
								   <bean:write name="HandoverToDeadBodyFB" property="relativeAddress"/>
								 </font>  
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</table>
				</div>
				
				
				<his:SubTitleTag name="">
			              <div align="left">
				               Print Certificate Detail
			              </div>
		        </his:SubTitleTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
					  <td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b>
										<bean:message key="certificateHandoverTo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="HandoverToDeadBodyFB" property="certificateHandoverTo" maxlength="50" size="30" />
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
								<logic:equal name="HandoverToDeadBodyFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_NO %>">
									<html:text name="HandoverToDeadBodyFB" property="noOfCopies" size="5" value="<%=RegistrationConfig.NO_OF_DEATH_CERTIFICATE_COPIES_NORMAL %>" readonly="true"></html:text>
								</logic:equal>	
								<logic:equal name="HandoverToDeadBodyFB" property="isMlc" value="<%=RegistrationConfig.IS_MLC_YES %>">
									<html:text name="HandoverToDeadBodyFB" property="noOfCopies" size="5" value="<%=RegistrationConfig.NO_OF_DEATH_CERTIFICATE_COPIES_MLC %>" readonly="true"></html:text>
								</logic:equal>	
							</div>
						</td>
					</tr>
					<tr>
					  <td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="remarks"/>
								</b>	
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="HandoverToDeadBodyFB" property="remarks" maxlength="50" size="30" />
							</div>
						</td>
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
					</tr>
					<html:hidden name="HandoverToDeadBodyFB" property="isPrintCertificate" value="on"/>
					
				</table>	
				
			</his:ContentTag>
	     
	        </logic:equal>
		
	  <html:hidden name="HandoverToDeadBodyFB" property="sysDate" value="<%=sysDate%>"/>
	</his:statusTransactionInProcess>		
	<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex="1" onclick ="if(validateFinal())submitPage('SAVE')" onkeypress="if(event.keyCode==13) if(validateFinal())submitPage('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer  tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
	</his:statusTransactionInProcess>
	<his:statusList>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
    </his:statusList>
	</his:ButtonToolBarTag>
	
	
	
		<html:hidden name="HandoverToDeadBodyFB" property="hmode" />
		<html:hidden name="HandoverToDeadBodyFB" property="patCrNo"/>
		<html:hidden name="HandoverToDeadBodyFB" property="isMlc"/>	
		<html:hidden name="HandoverToDeadBodyFB" property="printFlag"/>
		<html:hidden name="HandoverToDeadBodyFB" property="noOfCopies"/>
	    <html:hidden name="HandoverToDeadBodyFB" property="hiddenTimeHr"/>
		<html:hidden name="HandoverToDeadBodyFB" property="hiddenTimeMin"/>
		<html:hidden name="HandoverToDeadBodyFB" property="departmentUnitCode"/>
		<html:hidden name="HandoverToDeadBodyFB" property="flagForPrint" />
	 	<html:hidden name="HandoverToDeadBodyFB" property="deskType"/>
	 	<html:hidden name="HandoverToDeadBodyFB" property="isInpatient"/>
	 	<html:hidden name="HandoverToDeadBodyFB" property="deathCertificateId"/>	
	
	
	</html:form>
<his:status/>


</html>