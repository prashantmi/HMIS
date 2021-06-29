<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="inpatient.InpatientConfig"%>
<html>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>  
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />   
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/templateValidations.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">


function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value='SAVETRANSFUSIONDTL';
	document.forms[0].submit();
}

function clearForm()
{
   document.getElementsByName('requisitionNo')[0].value=""; 
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
 

function showReactionDetail(obj)
{
	if(document.getElementsByName("bagTransfuseDateArray")[obj.value].value=="")
	{
		alert("First Enter The Blood Transfusion Detail");
		document.getElementsByName("selectedBagArray")[obj.value].checked=false;
	}
	else
	{
		document.getElementsByName("reactionStartHr")[0].value="";
		document.getElementsByName("reactionStartMin")[0].value="";
		document.getElementsByName("reactionEndHr")[0].value="";
		document.getElementsByName("reactionEndMin")[0].value="";
		document.getElementsByName("reactionSummary")[0].value="";
		document.getElementsByName("controlSummary")[0].value="";
		document.getElementsByName("bloodComponentTransfusedAmount")[0].value="";
		
		document.getElementsByName("isFormFilled")[0].value="";
		document.getElementsByName("isFormFilled")[1].value="";
		document.getElementsByName("isTransfusionSet")[0].value="";
		document.getElementsByName("isTransfusionSet")[1].value="";
		document.getElementsByName("isPostTransfusionSample")[0].value="";
		document.getElementsByName("isPostTransfusionSample")[1].value="";
		document.getElementsByName("reactionFormSendTimeHr")[0].value="";
		document.getElementsByName("reactionFormSendTimeMin")[0].value="";
//		document.getElementsByName("issueDate")[0].value=document.getElementsByName("bagIssueDateArray")[obj.value].value;
		document.getElementsByName("bagTransfuseDate")[0].value=document.getElementsByName("bagTransfuseDateArray")[obj.value].value;
		
		
		document.getElementById("divReactionDetailID").style.display="";
	}
}

function validateForm(mode)
{	
	var  reactionStartDateLimit=<%=InpatientConfig.BLOOD_TRANSFUSION_REACTION_START_DATE_LIMIT%>
	var len=document.getElementsByName("selectedBagArray").length;
	var selectFlag=false;
	var popUpBagNo=document.getElementsByName('popupBloodBagNo')[0].value;
	document.getElementsByName('selBagNSeqNo')[0].value=popUpBagNo;
	
	
	var popupCount=document.getElementsByName('popupCount')[0].value;
	document.getElementsByName('countChk')[0].value=popupCount;
	
	///////////////
	if(document.getElementsByName('reactionStartDate')[0].value=="")
	{
		alert("Please Enter Reaction Start Date");
		document.getElementsByName('reactionStartDate')[0].focus();
		return false;
	}
	
	
	if(document.getElementsByName('reactionStartHr')[0].value=="")
	{
		alert("Please Enter Reaction Start Hours ");
		document.getElementsByName('reactionStartHr')[0].focus();
		return false;
	}
	if(document.getElementsByName('reactionStartMin')[0].value=="")
	{
		alert("Please Enter Reaction Start Min");
		document.getElementsByName('reactionStartMin')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionEndHr')[0].value=="")
	{
		alert("Please Enter Reaction End Hours ");
		document.getElementsByName('reactionEndHr')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionEndMin')[0].value=="")
	{
		alert("Please Enter Reaction End Min");
		document.getElementsByName('reactionEndMin')[0].focus();
		return false;
	}
	
	if(!validateDateNTime(popupCount))
	{
		return false;
	}
	/////////
	
			
	if(!document.getElementsByName("isFormFilled")[0].checked && !document.getElementsByName("isFormFilled")[1].checked)
	{
		alert("Please Select The Reaction Form(Duly Filled)");
		document.getElementsByName("isFormFilled")[0].focus();
		return false;
	}	
	if(!document.getElementsByName("isTransfusionSet")[0].checked && !document.getElementsByName("isTransfusionSet")[1].checked)
	{
		alert("Please Select Blood Bag/Bags Along With Transfusion Set");
		document.getElementsByName("isTransfusionSet")[0].focus();
		return false;
	}		
	if(!document.getElementsByName("isPostTransfusionSample")[0].checked && !document.getElementsByName("isPostTransfusionSample")[1].checked)
	{
		alert("Please Select Post Transfusion Sample");
		document.getElementsByName("isPostTransfusionSample")[0].focus();
		return false;
	}
	if(document.getElementsByName("bloodComponentTransfusedAmount")[0].value=="" || document.getElementsByName("bloodComponentTransfusedAmount")[0].value=="0" || document.getElementsByName("bloodComponentTransfusedAmount")[0].value=="00" || document.getElementsByName("bloodComponentTransfusedAmount")[0].value=="000")
	{
		alert("Please Enter The Amount of Blood/Blood Component Transfused");
		document.getElementsByName("bloodComponentTransfusedAmount")[0].focus();
		document.getElementsByName("bloodComponentTransfusedAmount")[0].value="";
		return false;
	}	
	if(document.getElementsByName('reactionFormSendDate')[0].value=="")
	{
		alert("Please Enter Reaction Form Send Date");
		document.getElementsByName('reactionFormSendDate')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionFormSendTimeHr')[0].value=="")
	{
		alert("Please Enter Reaction Form Send Hours ");
		document.getElementsByName('reactionFormSendTimeHr')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionFormSendTimeHr')[0].value>23)
	{
		alert("Reaction Form Send Hours Can Not Be Greater Than 23");
		document.getElementsByName('reactionFormSendTimeHr')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionFormSendTimeMin')[0].value=="")
	{
		alert("Please Enter Reaction Form Send Min");
		document.getElementsByName('reactionFormSendTimeMin')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionFormSendTimeMin')[0].value>59)
	{
		alert("Reaction Form Send Min Can Not Be Greater Than 59");
		document.getElementsByName('reactionFormSendTimeMin')[0].focus();
		return false;
	}
	
	//if(!validateReactionFormSendDate())
	//{
	//	return false;
	//}
	//alert("OKKKK");			
	submitForm(mode);
	self.close();
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

function validateStartDate()
{
	var valid=false;
	var startDate=document.getElementsByName("reactionStartDate")[0].value;
	var startHr=document.getElementsByName("reactionStartHr")[0].value;
	var startMin=document.getElementsByName("reactionStartMin")[0].value;
	var issueDate=document.getElementsByName("popupTransfusionDate")[0].value;
	var issueHr=document.getElementsByName("popupStartHr")[0].value;
	var issueMin=document.getElementsByName("popupStartMin")[0].value;
	
	var days=noOfDays(startDate,issueDate)
	
	if(days<0)
	{
		alert("Start Date and Time Cannot be Less Than Transfused Date and Time");
		document.getElementsByName("reactionStartDate")[0].focus();
		valid=false;
	}
	else
	{
		if(days==0)
		{
			if(parseInt(startHr)<parseInt(issueHr))
			{
				alert("Start Date and Time Cannot be Less Than Transfused Date and Time");
				document.getElementsByName("reactionStartHr")[0].focus();
				valid=false;
			}
			else
			{
				if(parseInt(startHr)==parseInt(issueHr) && parseInt(startMin)<parseInt(issueMin))
				{
					alert("Start Date and Time Cannot be Less Than Transfused Date and Time");
					document.getElementsByName("reactionStartMin")[0].focus();
					valid=false;
				}
				else
					valid=true;
			}
		}
		else
			valid=true;
	}
	return valid;
}

function validateEndDate(i)
{
	var valid=false;
	var startDate=document.getElementsByName("reactionStartDate")[0].value;
	var startHr=document.getElementsByName("reactionStartHr")[0].value;
	var startMin=document.getElementsByName("reactionStartMin")[0].value;
//	var endDate=document.getElementsByName("reactionEndDate")[0].value;
	var endDate=document.getElementsByName("reactionStartDate")[0].value;
	var endHr=document.getElementsByName("reactionEndHr")[0].value;
	var endMin=document.getElementsByName("reactionEndMin")[0].value;
	
	// var days=noOfDays(endDate,startDate)
	//alert(days);
	
			if(parseInt(endHr)<parseInt(startHr))
			{
				alert("End Time Cannot be Less Than Start Time");
				document.getElementsByName("reactionEndHr")[0].focus();
				valid=false;
			}
			else
			{
				if(parseInt(endHr)==parseInt(startHr) && parseInt(endMin)<parseInt(startMin))
				{
					alert("End Time Cannot be Less Than Start Time");
					document.getElementsByName("reactionEndMin")[0].focus();
					valid=false;
				}
				else
					valid=true;
			}
		
	return valid;
}

function validatesysdate()
{
	var valid=false;
	var sysdate=document.getElementsByName("sysdate")[0].value.split(" ")[0];
	var sysHr=document.getElementsByName("sysdate")[0].value.split(" ")[1].split(":")[0];
	var sysMin=document.getElementsByName("sysdate")[0].value.split(" ")[1].split(":")[1];
	//var endDate=document.getElementsByName("reactionEndDate")[0].value;
	var endDate=document.getElementsByName("reactionStartDate")[0].value;
	var endHr=document.getElementsByName("reactionEndHr")[0].value;
	var endMin=document.getElementsByName("reactionEndMin")[0].value;
	
	// var days=noOfDays(sysdate,endDate)
//	alert(days);
	
	
			if(parseInt(sysHr)<parseInt(endHr))
			{
				alert("End Time Cannot be Greater Than Current Time");
				document.getElementsByName("reactionEndHr")[0].focus();
				valid=false;
			}
			else
			{
				if(parseInt(sysHr)==parseInt(endHr) && parseInt(sysMin)<parseInt(endMin))
				{
					alert("End Time Cannot be Greater Than Current Time");
					document.getElementsByName("reactionEndMin")[0].focus();
					valid=false;
				}
				else
					valid=true;
			}
	
	return valid;
}

function validateDateNTime(popupCount)
{
//	alert("ROW"+i);
	var valid=false;
	if(validateStartDate(popupCount))
	{
		if(validateEndDate(popupCount))
		{
			if(validatesysdate())
			{
				valid=true;
			}
			else
				valid=false;	
		}
		else
			valid=false;
	}
	else
		valid=false;
	
//	alert("valid>>>>>>"+valid)
	return valid;
}	

function validateReactionFormSendDate()
{
	var valid=false;
	
	var sysdate=document.getElementsByName("sysdate")[0].value.split(" ")[0];
	var sysHr=document.getElementsByName("sysdate")[0].value.split(" ")[1].split(":")[0];
	var sysMin=document.getElementsByName("sysdate")[0].value.split(" ")[1].split(":")[1];
	
	// var endDate=document.getElementsByName("reactionEndDate")[0].value;
	var endHr=document.getElementsByName("reactionEndHr")[0].value;
	var endMin=document.getElementsByName("reactionEndMin")[0].value;
	
	var sendDate=document.getElementsByName("reactionFormSendDate")[0].value;
	var sendHr=document.getElementsByName("reactionFormSendTimeHr")[0].value;
	var sendMin=document.getElementsByName("reactionFormSendTimeMin")[0].value;
	
	var days=noOfDays(sendDate,endDate);
//	alert(days);
	if(days<0)
	{
		alert("Reaction Form Send Date and Time Cannot be Less Than Reaction End Date and Time");
		document.getElementsByName("reactionFormSendDate")[0].focus();
		valid=false;
	}
	else
	{
		if(days==0)
		{
			if(parseInt(sendHr)<parseInt(endHr))
			{
				alert("Reaction Form Send Date and Time Cannot be Less Than Reaction End Date and Time");
				document.getElementsByName("reactionFormSendTimeHr")[0].focus();
				valid=false;
			}
			else
			{
				if(parseInt(sendHr)==parseInt(endHr) && parseInt(sendMin)<parseInt(endMin))
				{
					alert("Reaction Form Send Date and Time Cannot be Less Than Reaction End Date and Time");
					document.getElementsByName("reactionFormSendTimeMin")[0].focus();
					valid=false;
				}
				else
					valid=true;
			}
		}
		else
		{
			var day=noOfDays(sysdate,sendDate);
			if(day<0)
			{
				alert("Reaction Form Send Date and Time Cannot be Greater Than Current Date and Time");
				document.getElementsByName("reactionFormSendDate")[0].focus();
				valid=false;
			}
			else
			{
				if(day==0)
				{
					if(parseInt(sysHr)<parseInt(sendHr))
					{
						alert("Reaction Form Send Date and Time Cannot be Greater Than Current Date and Time");
						document.getElementsByName("reactionFormSendTimeHr")[0].focus();
						valid=false;
					}
					else
					{
						if(parseInt(sysHr)==parseInt(sendHr) && parseInt(sysMin)<parseInt(sendMin))
						{
							alert("Reaction Form Send Date and Time Cannot be Greater Than Current Date and Time");
							document.getElementsByName("reactionFormSendTimeMin")[0].focus();
							valid=false;
						}
						else
							valid=true;
					}
				}
				else
				{
					valid =true;
				}
			}	
		}
	}
//	alert(">>>"+valid);
	return valid;
}
function closeForm()
{
	self.close();
}
</script>

<body>
<html:form action="/nurBloodTransfusion">

<his:TransactionContainer>
<his:TitleTag name="Transfusion Reaction Detail">	
</his:TitleTag>

			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td class="tdfonthead" width="50%">
							<div align="right" >
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 						<bean:message key="reactionStartDate" />
				 				</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="reactionStartDate"  dateFormate="%d-%b-%Y" ></his:date>
							</div>
						</td>	
						<td class="tdfonthead" width="50%"></td>
						<td class="tdfonthead" width="25%"></td>
					</tr>
					<tr>
						<td class="tdfonthead" width="50%">
							<div align="right" >
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 		 				<bean:message key="reactionStartTime" />
				 				</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<span id='divStarthrcontrol' align="left">	            
				   					<html:text name="BloodTransfusionFB" tabindex="1" property="reactionStartHr"  maxlength="2" size="3"  onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divStartMinControl' align="left">         
									<html:text name="BloodTransfusionFB" tabindex="1" property="reactionStartMin"   maxlength="2" size="3"   onkeypress="return onkeyTimeMin(this,document.getElementsByName('reactionStartHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>				
										<bean:message key="timeFormat"/>
		    					</span>
							</font>
						</td>
						<td class="tdfonthead" width="25%">
							<div align="right" >
						 		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 						<bean:message key="reactionEndTime" />
				 			 	 	</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<span id='divEndhrcontrol' align="left">	            
				   					<html:text name="BloodTransfusionFB" tabindex="1" property="reactionEndHr"  maxlength="2" size="3"  onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"  />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divEndMinControl' align="left">         
									<html:text name="BloodTransfusionFB" tabindex="1" property="reactionEndMin"   maxlength="2" size="3"  onkeypress="return onkeyTimeMin(this,document.getElementsByName('reactionEndHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>				
										<bean:message key="timeFormat"/>
		    					</span>
							 </font>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" >
							<div align="right" >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 					<bean:message key="reactionSummary" />
				 				</font>
				 	 		</div>
						</td>
						<td  class="tdfont" colspan="3">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<html:textarea name="BloodTransfusionFB" tabindex="1" rows="3" cols="100" property="reactionSummary" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))">
								</html:textarea>
							</font>
						</td>
						
					</tr>		 
					<tr>
						<td class="tdfonthead" >
							<div align="right" >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 					<bean:message key="controllSummary" />
				 				</font>
				 	 		</div>
						</td>
						<td  class="tdfont" colspan="3">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<html:textarea name="BloodTransfusionFB" tabindex="1" rows="3" cols="100" property="controlSummary" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							 </font>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" width="25%">
							<div align="right" >
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 		 				<bean:message key="reactionFormDulyFilled" />
				 				</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:radio name="BloodTransfusionFB" property="isFormFilled" tabindex="1" value="<%=InpatientConfig.YES %>"></html:radio>
								<bean:message key="yes"/>
								<html:radio name="BloodTransfusionFB" property="isFormFilled" tabindex="1" value="<%=InpatientConfig.NO %>"></html:radio>
								<bean:message key="no"/>
							</div>
						</td>
						<td class="tdfonthead" width="25%">
							<div align="right" >
						 		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 						<bean:message key="bldBagAlongWithTransfusionSet" />
				 			 	 	</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<html:radio name="BloodTransfusionFB" property="isTransfusionSet" tabindex="1" value="<%=InpatientConfig.YES %>"></html:radio>
							<bean:message key="yes"/>
							<html:radio name="BloodTransfusionFB" property="isTransfusionSet" tabindex="1" value="<%=InpatientConfig.NO %>"></html:radio>
							<bean:message key="no"/>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" width="25%">
							<div align="right" >
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 		 				<bean:message key="postTransfusionSample" />
				 				</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<html:radio name="BloodTransfusionFB" property="isPostTransfusionSample" tabindex="1" value="<%=InpatientConfig.YES %>"></html:radio>
							<bean:message key="yes"/>
							<html:radio name="BloodTransfusionFB" property="isPostTransfusionSample" tabindex="1" value="<%=InpatientConfig.NO %>"></html:radio>
							<bean:message key="no"/>
						</td>
						<td class="tdfonthead" width="25%">
							<div align="right" >
						 		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 						<bean:message key="amountOfBloodComponentTransfused" />
				 			 	 	</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<html:text name="BloodTransfusionFB" property="bloodComponentTransfusedAmount" tabindex="1" maxlength="3" size="6" onkeypress="return validateNumeric(event)"></html:text>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" width="25%">
							<div align="right" >
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 		 				<bean:message key="reactionFormSend" />
				 		 				<bean:message key="date" />
				 				</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="reactionFormSendDate" dateFormate="%d-%b-%Y" ></his:date>
							</div>
						</td>
						<td class="tdfonthead" width="25%">
							<div align="right" >
						 		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 						<bean:message key="reactionFormSend" />
				 						<bean:message key="time" />
				 			 	 	</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<span id='divStarthrcontrol' align="left">	            
				   					<html:text name="BloodTransfusionFB" tabindex="1" property="reactionFormSendTimeHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divStartMinControl' align="left">         
									<html:text name="BloodTransfusionFB" tabindex="1" property="reactionFormSendTimeMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
										<bean:message key="timeFormat"/>
		    					</span>
							</font>
						</td>
					</tr>
				</table>
			
			<logic:present name="<%=InpatientConfig.TEMPLATE_ID_LIST_FOR_TRANSFUSION_REACTION%>">
			<logic:iterate id="templateId" indexId="idx" name="<%=InpatientConfig.TEMPLATE_ID_LIST_FOR_TRANSFUSION_REACTION%>" type="java.lang.String">
				<his:GenericTemplateTag templateId="<%=templateId %>"></his:GenericTemplateTag>
			</logic:iterate>
			</logic:present>		
						
			</his:ContentTag>
 	
	<his:ButtonToolBarTag>
		
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
	</his:ButtonToolBarTag>
	
<his:status/>	
<logic:present name="<%=InpatientConfig.BLOOD_BAG_LIST_BY_REQUISTION_NO %>">
<logic:empty name="<%=InpatientConfig.BLOOD_BAG_LIST_BY_REQUISTION_NO %>">
<logic:notEqual value="0" name="BloodTransfusionFB" property="isReqNoExist">
<font color="#ff0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b>Either this patient is not external  or No bag available for this requisition number.</b>
		</font>	
</logic:notEqual>		
</logic:empty>
</logic:present>
</his:TransactionContainer>   	
		<html:hidden name="BloodTransfusionFB" property="hmode" />
		<html:hidden name="BloodTransfusionFB" property="selBagNSeqNo" />
		<html:hidden name="BloodTransfusionFB" property="popupBloodBagNo" />
		<html:hidden name="BloodTransfusionFB" property="countChk" />
		<html:hidden name="BloodTransfusionFB" property="popupCount" />
		<html:hidden name="BloodTransfusionFB" property="requisitionNo" />
		<html:hidden name="BloodTransfusionFB" property="popupTransfusionDate" />
		<html:hidden name="BloodTransfusionFB" property="popupStartHr" />
		<html:hidden name="BloodTransfusionFB" property="popupStartMin" />
		<html:hidden name="BloodTransfusionFB" property="sysdate" />
</html:form>
</body>
</html>


