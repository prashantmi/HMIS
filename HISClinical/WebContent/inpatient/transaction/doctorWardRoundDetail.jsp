<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript"><!--


function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function submitForm21(mode)
{
    
//   alert("submitform...."+mode);
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
     document.getElementsByName("hmode")[0].value=mode;
    // doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
     //alert("hmode "+document.getElementsByName("hmode")[0].value);  
    
	 document.forms[0].submit();
	 
}
function submitRoundType(event)
{
		var onCallRequired=<%=InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_REQUIRED %>;
		// alert("oncall"+onCallRequired)
		
		if(document.getElementsByName("roundBy")[0].value=="-1")
		{
			alert("Please Select Doctor Name");
			document.getElementsByName("roundType")[0].value="-1";
		}
		else
		{
			if(onCallRequired==<%=InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_REQUIRED_YES%>)	
			{
				if(document.getElementsByName("roundType")[0].value==<%=InpatientConfig.HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ONCALL %>)
				{
						//alert("inside if")
					var patCrNo=document.getElementsByName("patCrNo")[0].value;
			        var wardCode=document.getElementsByName("wardCode")[0].value;   
			        var roundBy=document.getElementsByName("roundBy")[0].value;       
			               
					var path='/HISClinical/inpatient/doctorWardRoundDetail.cnt?hmode=ROUNDTYPE&patCrNo='+patCrNo+'&wardCode='+wardCode+'&roundBy='+roundBy;
					// alert("path"+path)
			          child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+300+',width='+600+',left=10,top=10');  
						child.moveTo(300,300);
						child.focus(); 
						if(!child.opener)
						child.opener = self;
						return child
				}
			}
		}	
}

function validateForm(mode)
{
	var valid=false;
	
 if( comboValidation(document.getElementsByName("roundBy")[0],"Round By")
 				&& isEmpty(document.getElementsByName("roundDate")[0],"Round Date")
	            && datecheck()
	            && isEmpty(document.getElementsByName("roundInHr")[0],"Round In Time")
	            && isEmpty(document.getElementsByName("roundInMin")[0],"Round In Time")
	            && validateRoundInTime()
	            && validateNextRoundDate()
	            && checkNextRoundDate() 
	            && validateBothDates()
	            && comboValidation(document.getElementsByName("roundType")[0],"Round Type")
								  			   
			   )
			{
			valid=true;
			
			}	
			else
			{
			valid=false;
			}
			if(valid==true)
			{
			submitForm(mode);
			}
}	

function validateBothDates()
{

	var roundDate=document.getElementsByName("roundDate")[0].value;
	var nextRoundDate=document.getElementsByName("nextRoundDate")[0].value;
	var roundInHr=document.getElementsByName("roundInHr")[0].value;
	var roundInMin=document.getElementsByName("roundInMin")[0].value;
	var nextRoundInHr=document.getElementsByName("nextRoundInHr")[0].value;
	var nextRoundInMin=document.getElementsByName("nextRoundInMin")[0].value;
	
	if(nextRoundDate!="")
	{
		if(roundDate==nextRoundDate)
		{
			if(nextRoundInHr<roundInHr)
			{
			alert("Next Round Time should be Greater Than Round In Time");
      		 document.forms[0].nextRoundInHr.focus(); 
     		 return false;
			}
			if(nextRoundInHr==roundInHr)
			{
				if(nextRoundInMin<roundInMin)
				{
				alert("Next Round Time should be Greater Than Round In Time");
	      		 document.forms[0].nextRoundInMin.focus(); 
	     		 return false;
				}
			}
		}
	}
	
	return true;

}
	
function validateRoundInTime()
{
	var sysHr=document.getElementsByName("sysHr")[0].value;
	var sysMin=document.getElementsByName("sysMin")[0].value;
	var roundInHr=document.getElementsByName("roundInHr")[0].value;
	var roundInMin=document.getElementsByName("roundInMin")[0].value;
	// alert("hr"+sysHr)
	// alert("time"+sysMin)
	
	var aArray=(document.getElementsByName("roundDate")[0].value).split("-");
      var amonth=aArray[1];
      var aday=aArray[0];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      
      var bArray=(document.getElementsByName("sysDate")[0].value).split("-");
      var bmonth=bArray[1];
      var bday=bArray[0];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
   // alert("adate"+adate)
   // alert("bdate"+bdate)
    var d=adate-bdate;
    if(d==0)
      {
 //     	alert("inside date");
      	if(roundInHr>sysHr)
      	{
      		alert("Round In Time should be Less Than System Time")
      		 document.forms[0].roundInHr.focus(); 
     		 return false;
      	}
      	if(roundInHr==sysHr)
      	{
	      	if(roundInMin>sysMin)
	      	{
	      		alert("Round In Time should be Less Than System Time")
	      		 document.forms[0].roundInMin.focus(); 
	     		 return false;
	      	}
   		}
      }
 return true;
	
}

function validateNextRoundDate()
{
	// alert("d"+document.getElementsByName("nextRoundDate")[0].value)
	if((document.getElementsByName("nextRoundDate")[0].value=="") && (document.getElementsByName("nextRoundInHr")[0].value=="") && (document.getElementsByName("nextRoundInMin")[0].value==""))
	{
		return true;
	}
	else
	{
		if(document.getElementsByName("nextRoundDate")[0].value=="")
		{
			alert("Please Select Next Round Date");
			document.forms[0].nextRoundDate.focus(); 
			return false;
		}
		
		else if(document.getElementsByName("nextRoundInHr")[0].value=="")
		{
			alert("Please Select Next Round In Time");
			document.forms[0].nextRoundInHr.focus(); 
			return false;
		}
		
		else if(document.getElementsByName("nextRoundInMin")[0].value=="")
		{
			alert("Please Select Next Round In Time");
			document.forms[0].nextRoundInMin.focus(); 
			return false;
		}
		else
		{
			return true;
		}
	}
}
	
function datecheck()
{
		
	  var aArray=(document.getElementsByName("roundDate")[0].value).split("-");
      var amonth=aArray[1];
      var aday=aArray[0];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      
      var bArray=(document.getElementsByName("sysDate")[0].value).split("-");
      var bmonth=bArray[1];
      var bday=bArray[0];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
      if(adate>bdate)
      {
     alert("Round Date Cannot Be Greater Than System Date");
     document.forms[0].roundDate.focus(); 
      return false;
      }
 return true;
}	

function checkDate()
{
		
	  var aArray=(document.getElementsByName("nextRoundDate")[0].value).split("-");
      var amonth=aArray[1];
      var aday=aArray[0];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      
      var bArray=(document.getElementsByName("sysDate")[0].value).split("-");
      var bmonth=bArray[1];
      var bday=bArray[0];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
      if(adate<bdate)
      {
      alert("Next Round Date should Be Greater Than System Date");
      document.forms[0].nextRoundDate.focus(); 
      return false;
      }
 return true;
}	

function checkNextRoundDate()
{
		var nextRoundDate=document.getElementsByName("nextRoundDate")[0].value;
		
		if(nextRoundDate!="")
		{
				
				return checkDate();
			
		}
		else
		{
			return true;
		}

} 	
	
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

--></script>
<form name="frmdocward">
<his:TransactionContainer>
<his:TitleTag>
	<his:name>
		<bean:message key="docWardRoundDtl" />
	</his:name>
</his:TitleTag>


		
		
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			
			<tr>
				<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						   <font color="#FF0000">*</font>
							<bean:message key="unit"/>
						</font>		
					</div>
				</td>
				<td width="60%"  class="tdfont" colspan="2">
					<div align="left">
						<html:select name="DoctorWardRoundDetail" property="unitCode" tabindex="1"  onkeypress="if(event.keyCOde==13)submitForm21('FETCHROUNDBY');" onchange="submitForm21('FETCHROUNDBY');" styleClass="registrationCmb">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.DEPT_UNIT_LIST%>">
							<html:option value="0">All</html:option>
							<html:options  collection="<%=InpatientConfig.DEPT_UNIT_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
			</tr>
			    <tr>
				<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						   <font color="#FF0000">*</font>
							<bean:message key="roundBy"/>
						</font>		
					</div>
				</td>
				<td width="60%"  class="tdfont" colspan="2">
					<div align="left">
						<html:select name="DoctorWardRoundDetail" property="roundBy" tabindex="1">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ESSENTIAL_EMPLOYEE_LIST_UNIT_WISE%>">
							<html:options  collection="<%=InpatientConfig.ESSENTIAL_EMPLOYEE_LIST_UNIT_WISE%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
			</tr>
			<tr>
			<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							    <font color="#FF0000">*</font>
								<bean:message key="roundDate"/>
						</font>		
					</div>
				</td>
				
				<td width="60%" class="tdfont" nowrap="nowrap">
					<div align="left"><his:date name="roundDate" dateFormate="%d-%b-%Y"></his:date></div>
				</td>
				
				
			</tr>
			<tr>
					<td class="tdfonthead" width="40%">
							<div align="right" >
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
				 		 			 <bean:message key="roundInTime" />
				 		 			 </font>
				 			</div>
						</td>
						<td width="60%" class="tdfont">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<span id='divStarthrcontrol' align="left">	            
				   					<html:text name="DoctorWardRoundDetail" tabindex="1" property="roundInHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divStartMinControl' align="left">         
									<html:text name="DoctorWardRoundDetail" tabindex="1" property="roundInMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
										<bean:message key="timeFormat"/>
		    					</span>
							</font>
						</td>
			</tr>
			<tr>
			<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							    
								<bean:message key="nextRoundDate"/>
						</font>		
					</div>
				</td>
				
				<td width="60%" class="tdfont" nowrap="nowrap">
					<div align="left"><his:date name="nextRoundDate" dateFormate="%d-%b-%Y"></his:date></div>
				</td>
				
				
			</tr>
			<tr>
					<td class="tdfonthead" width="40%">
							<div align="right" >
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="nextRoundTime" />
				 		 			 </font>
				 			</div>
						</td>
						<td width="60%" class="tdfont">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<span id='divStarthrcontrol' align="left">	            
				   					<html:text name="DoctorWardRoundDetail" tabindex="1" property="nextRoundInHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divStartMinControl' align="left">         
									<html:text name="DoctorWardRoundDetail" tabindex="1" property="nextRoundInMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
										<bean:message key="timeFormat"/>
		    					</span>
							</font>
						</td>
			</tr>
			<tr>
					<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						   <font color="#FF0000">*</font>
							<bean:message key="roundType"/>
						</font>		
					</div>
				</td>
				<td width="60%"  class="tdfont" colspan="2">
					<div align="left">
						<html:select name="DoctorWardRoundDetail" property="roundType" tabindex="1" onchange="submitRoundType(event)">
							<html:option value="-1">Select Value</html:option>
							<html:option   value="<%=InpatientConfig.HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ROUTINE %>"><%=InpatientConfig.HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ROUTINE_LABEL%></html:option>
							<html:option   value="<%=InpatientConfig.HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ONCALL %>"><%=InpatientConfig.HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ONCALL_LABEL%></html:option>
						</html:select>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>

	
	
	<his:ButtonToolBarTag>
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:ButtonToolBarTag>
	
	</his:TransactionContainer>
	
<html:hidden name="DoctorWardRoundDetail" property="hmode"/>
<html:hidden name="DoctorWardRoundDetail" property="patCrNo"/>
<html:hidden name="DoctorWardRoundDetail" property="wardCode"/>
<html:hidden name="DoctorWardRoundDetail" property="sysDate"/>
<html:hidden name="DoctorWardRoundDetail" property="sysHr"/>
<html:hidden name="DoctorWardRoundDetail" property="sysMin"/>
</form>