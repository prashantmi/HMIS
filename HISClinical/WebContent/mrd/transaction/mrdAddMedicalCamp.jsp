<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>

<%@page import="mrd.transaction.controller.fb.MrdMedicalCampFB"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date;"%>
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
<his:javascript src="/mrd/js/medicalCamp.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
var selectedMedicalCamp=0;
function getSelectedCamp(obj)
{
	selectedMedicalCamp=0;
	selectedMedicalCamp= obj.value;
}

function addCampDetail()
{
	//alert("jai mata di");
	document.getElementsByName("hmode")[0].value="ADDCAMPDETAIL"
	document.forms[0].submit();
}

function validateSave(){
	//alert(document.getElementsByName("strArrEmployeeId").length);

	if(validateForm()){
		document.getElementsByName("hmode")[0].value="SAVE"
		document.forms[0].submit()
	}
	else{	
		return false;
	}	
}


function validateForm(){	
		campClosedCheck = document.getElementsByName("strIsCampClosed");
		if(campClosedCheck[0].checked==true)
		{
			document.getElementsByName("strIsCampClosed")[0].value = 0;
			var campEndDate = document.getElementsByName("strCampEndDateTime")[0].value;
			if(campEndDate == null || campEndDate =="")
			{
				alert("Please Enter end Date If Camp is Closed !");
				document.getElementsByName("strCampEndDateTime")[0].focus();
				return false;
			}
			if(document.getElementsByName("campEndHr")[0].value==null||document.getElementsByName("campEndHr")[0].value=="")
				{
					alert("Please Select Camp End Hr ");
					document.getElementsByName("campEndHr")[0].focus();
					return false;	
				}
			
			if(document.getElementsByName("campEndMin")[0].value==null||document.getElementsByName("campEndMin")[0].value=="")
				{
					alert("Please Select Camp End Min ");
					document.getElementsByName("campEndMin")[0].focus();
					return false;	
				}
			
			if(!endDatecheck())
				{
				return false;
				}		
				
			var totalPatAtnd = document.getElementsByName("strTotalNoofPatientAttended")[0].value;
			if(totalPatAtnd == null || totalPatAtnd =="")
			{
				alert("Please Enter Toatal No of Patients Attended Camp !");
				document.getElementsByName("strTotalNoofPatientAttended")[0].focus();
				return false;
			}
		}
		
		if(document.getElementsByName("strCampName")[0].value==null||document.getElementsByName("strCampName")[0].value=="")
			{
				alert("Please Enter Camp Name")				
				document.getElementsByName("strCampName")[0].focus();
				return false;	
			}
		
		if(document.getElementsByName("strCampRequisitionDate")[0].value==null||document.getElementsByName("strCampRequisitionDate")[0].value=="")
			{
				alert("Please Select Camp Requisition Date")				
				document.getElementsByName("strCampRequisitionDate")[0].focus();
				return false;	
			}
			
		if(!datecheck())
		{
			return false;
		}			
		
		if(document.getElementsByName("strCampStartDateTime")[0].value==null||document.getElementsByName("strCampStartDateTime")[0].value=="")
			{
				alert("Please Select Camp Start Date Time ")
				document.getElementsByName("strCampStartDateTime")[0].focus();
				return false;	
			}
		if(!startDatecheck())
		{
			return false;
		}
		
		if(document.getElementsByName("campStartHr")[0].value==null||document.getElementsByName("campStartHr")[0].value=="")
			{
				alert("Please Select Camp Start Hr ");
				document.getElementsByName("campStartHr")[0].focus();
				return false;	
			}
		
		if(document.getElementsByName("campStartMin")[0].value==null||document.getElementsByName("campStartMin")[0].value=="")
			{
				alert("Please Select Camp Start Min ");
				document.getElementsByName("campStartMin")[0].focus();
				return false;	
			}	
		if((document.getElementsByName("strCampEndDateTime")[0].value != "") || (document.getElementsByName("campEndHr")[0].value!="") || (document.getElementsByName("campEndMin")[0].value!=""))
		{	
			var campEndDate = document.getElementsByName("strCampEndDateTime")[0].value;
			if(campEndDate == null || campEndDate =="")
			{
				alert("Please Enter end Date If Camp is Closed !");
				document.getElementsByName("strCampEndDateTime")[0].focus();
				return false;
			}
			if(document.getElementsByName("campEndHr")[0].value==null||document.getElementsByName("campEndHr")[0].value=="")
				{
					alert("Please Select Camp End Hr ");
					document.getElementsByName("campEndHr")[0].focus();
					return false;	
				}
			
			if(document.getElementsByName("campEndMin")[0].value==null||document.getElementsByName("campEndMin")[0].value=="")
				{
					alert("Please Select Camp End Min ");
					document.getElementsByName("campEndMin")[0].focus();
					return false;	
				}	
			if(!endDatecheck())
				{
				return false;
				}
		}
		
		if(document.getElementsByName("strCampVenue")[0].value==null||document.getElementsByName("strCampVenue")[0].value=="")
			{
				alert("Please Select Camp Venue ");
				document.getElementsByName("strCampVenue")[0].focus();
				return false;	
			}
				
		if((document.getElementsByName("strEmployeeName")[0].value) != "-1" ||(document.getElementsByName("strRole")[0].value != ""))
			{
				alert("Please press add button to add Employee")
				document.getElementsByName("strArrEmployeeId")[0].focus();
				return false;	
			}		
		if(((document.getElementsByName("strTotalMalePatientAttended")[0].value != "")
		||(document.getElementsByName("strTotalMalePatientAttended")[0].value == "0") 
		|| (document.getElementsByName("strTotalFemalePatientAttended")[0].value != "")		
		|| (document.getElementsByName("strTotalFemalePatientAttended")[0].value == "0")
		|| (document.getElementsByName("strTotalMaleChildPatientAttended")[0].value != "")
		|| (document.getElementsByName("strTotalMaleChildPatientAttended")[0].value == "0")
		|| (document.getElementsByName("strTotalFemaleChildPatientAttended")[0].value != "")		
		|| (document.getElementsByName("strTotalFemaleChildPatientAttended")[0].value == "0"))
		&& (document.getElementsByName("strTotalNoofPatientAttended")[0].value != ""))
		{
			var noOfMale = document.getElementsByName("strTotalMalePatientAttended")[0].value;
			var noOfFemale = document.getElementsByName("strTotalFemalePatientAttended")[0].value;
			var noOfMaleChild = document.getElementsByName("strTotalMaleChildPatientAttended")[0].value;
			var noOfFemaleChild = document.getElementsByName("strTotalFemaleChildPatientAttended")[0].value;
			var total = document.getElementsByName("strTotalNoofPatientAttended")[0].value;
			var sum = parseInt(noOfMale) + parseInt(noOfFemale) + parseInt(noOfMaleChild) + parseInt(noOfFemaleChild);
			if(parseInt(total) != parseInt(sum))
				{
					alert("Please Fill correct Entry for Total No of Patient Attended ");
					return false;	
			}
		}		
		return true;
}

function clearForm(){	
	document.getElementsByName("strCampName")[0].value=""
	document.getElementsByName("strCampRequisitionDate")[0].value=""
	document.getElementsByName("strCampStartDateTime")[0].value=""
	document.getElementsByName("strCampEndDateTime")[0].value=""
	
	document.getElementsByName("campStartHr")[0].value=""
	document.getElementsByName("campStartMin")[0].value=""
	document.getElementsByName("campEndHr")[0].value=""
	document.getElementsByName("campEndMin")[0].value=""
	
	document.getElementsByName("strCampVenue")[0].value=""
	document.getElementsByName("strCampDescription")[0].value=""
	document.getElementsByName("strRole")[0].value=""
	document.getElementsByName("strDutyRemarks")[0].value=""
	
	document.getElementsByName("strTotalNoofPatientAttended")[0].value=""
	document.getElementsByName("strTotalMalePatientAttended")[0].value=""
	document.getElementsByName("strTotalFemalePatientAttended")[0].value=""
	document.getElementsByName("strTotalMaleChildPatientAttended")[0].value=""
	document.getElementsByName("strTotalFemaleChildPatientAttended")[0].value=""	
	document.getElementsByName("strArrEmployeeId").length=0
}	

function validateExpectedReturnDate(){
	var sysdate=document.getElementsByName("sysdate")[0].value
	var sysT = sysdate.split(" ");
	alert(s)
	var expectedReturnDate=document.getElementsByName("expectedReturnDate")[0].value
	var maxReturnDate=document.getElementsByName("maxReturnDate")[0].value
	if(!isEmpty(document.getElementsByName("expectedReturnDate")[0],"Return Date")){
		return false;
	}
	//alert("expectedReturnDate "+expectedReturnDate)
	
	if(!datecheck(sysdate,expectedReturnDate)){
		alert("Return Date should be greater than "+ sysdate)
		return false
	}
	
	if(!datecheck(expectedReturnDate,maxReturnDate)){
		alert("Return Date should be less than "+ maxReturnDate)
		return false
	}
	
	
	return true;
}


</script>
<body>
<%String sysdate=(String)WebUTIL.getSysdate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy");  %>
<%int nRow=1; %>
<his:TransactionContainer>
<html:form action="/medicalCamp">
	<his:TitleTag name="Medical Camp">
	</his:TitleTag>
	<%MrdMedicalCampFB fb=(MrdMedicalCampFB)pageContext.findAttribute("mrdMedicalCampFB"); %>
	<his:ContentTag>
		<his:SubTitleTag name="Camp Requisition Detail">
		</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campName" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div><html:textarea name="mrdMedicalCampFB" 
								property="strCampName" tabindex="1" onkeypress="return validateAlphaNumericOnly(event , this) && CheckMaxLength(event,this,100)">
							</html:textarea></div>
							</td>
	
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campReqDate" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>
							<his:date name="strCampRequisitionDate" dateFormate="%d-%b-%Y" value="<%=fb.getStrCampRequisitionDate()%>"/>
							</div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campStartDate" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>
							<his:date name="strCampStartDateTime" dateFormate="%d-%b-%Y" value="<%=fb.getStrCampStartDateTime()%>"/>															
							</div>
							</td>
														
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campEndDate" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>
							<his:date name="strCampEndDateTime" dateFormate="%d-%b-%Y" value="<%=fb.getStrCampEndDateTime()%>"/>						
							</div>
							</td>													
						</tr>
						
						
						
						<tr>
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campStartTime" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>							
								<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
									<span id='divStarthrcontrol' align="left">	            
						   			<html:text name="mrdMedicalCampFB" tabindex="1" property="campStartHr"  maxlength="2" size="3"  onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" />	
						  			<bean:message key="colon"/>
						  			</span>
						 			<span id='divStartMinControl' align="left">         
									<html:text name="mrdMedicalCampFB" tabindex="1" property="campStartMin"   maxlength="2" size="3"  onkeypress="return onkeyTimeMin(this,document.getElementsByName('campStartHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>				
									<bean:message key="timeFormat"/>
				    				</span>
								</font>							
							</div>
							</td>
														
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campEndTime" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>							
								<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
									<span id='divEndhrcontrol' align="left">	            
						   			<html:text name="mrdMedicalCampFB" tabindex="1" property="campEndHr"  maxlength="2" size="3"  onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" />					  			
						  		    <bean:message key="colon"/>				  		    
									</span>
						 			<span id='divEndMinControl' align="left">         
									<html:text name="mrdMedicalCampFB" tabindex="1" property="campEndMin"   maxlength="2" size="3"  onkeypress="return onkeyTimeMin(this,document.getElementsByName('campEndHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>				
									<bean:message key="timeFormat"/>
				    				</span>
								</font>							
							</div>
							</td>
													
						</tr>
						
						<tr>
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campVenue" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div><html:textarea name="mrdMedicalCampFB"
								property="strCampVenue" tabindex="1" onkeypress="return validateAlphaNumericOnly(event , this) && CheckMaxLength(event,this,100)">
							</html:textarea></div>
							</td>
							
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campDescription" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div><html:textarea name="mrdMedicalCampFB"
								property="strCampDescription" tabindex="1" onkeypress="return validateAlphaNumericOnly(event , this) && CheckMaxLength(event,this,500)">
							</html:textarea></div>
							</td>						
						</tr>
			</table>
		<his:SubTitleTag name="Camp Team Member Detail">
		</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="tableParamCampId">
				<tr>
					<td width="30%" class="tdfonthead"><div align="center"><font color="red" size="2">*</font><b><bean:message key="employeename" /></b></div></td>
                  	<td width="30%" class="tdfonthead"><div align="center"><font color="red" size="2">*</font><b><bean:message key="role" /></b></div></td>
                  	<td width="30%" class="tdfonthead"><div align="center"><b><bean:message key="dutyRemarks" /></b></div></td>
                  	<td width="10%" class="tdfonthead"></td>
                </tr>  
				<tr>			
					<td width="30%" class="tdfont" >	
						<div align="center">
							<html:select name="mrdMedicalCampFB" property="strEmployeeName" tabindex="1" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.MRD_MEDICAL_CAMP_VO_LIST%>">
										<html:options collection="<%=MrdConfig.MRD_MEDICAL_CAMP_VO_LIST %>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
						</div>													
					</td>
					<td class="tdfont" width="30%">
						<div align="center"><html:text name="mrdMedicalCampFB" maxlength="30" 
							property="strRole" tabindex="1" onkeypress="return validateAlphaNumericOnly(event , this)  && CheckMaxLength(event,this,30)">
						</html:text></div>
					</td>
					<td class="tdfont" width="30%">
						<div align="center"><html:text name="mrdMedicalCampFB" maxlength="50"
							property="strDutyRemarks" tabindex="1" onkeypress="return validateAlphaNumericOnly(event , this) && CheckMaxLength(event,this,50)">
						</html:text>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' tabindex='1' alt="Add Trolley Management Detail" title="Add Waste Detail" onkeypress="if(event.keyCode==13) if(validateAddRow())AddRowToTable();" onclick="if(validateAddRow())AddRowToTable();">
						</div>
					</td>
				</tr>
			</table>
		<his:SubTitleTag name="Camp Closure Detail">
		</his:SubTitleTag>
		
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="isCampClosed" /> </font></div>
					</td>		
					<td width="50%" class="tdfont" >	
					<his:checkbox name="mrdMedicalCampFB" value="<% fb.getStrIsCampClosed(); %>" property="strIsCampClosed"></his:checkbox>													
					</td>
				</tr>
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfPatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalNoofPatientAttended" tabindex="1" onkeypress="return validateNumeric(event)" >
					</html:text></div>
					</td>
				</tr>
				<tr>					
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfMalePatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalMalePatientAttended" tabindex="1" onkeypress="return validateNumeric(event)">
					</html:text></div>
					</td>
				</tr>
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfFemalePatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalFemalePatientAttended" tabindex="1" onkeypress="return validateNumeric(event)">
					</html:text></div>
					</td>
				</tr>
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfMaleChildPatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalMaleChildPatientAttended" tabindex="1" onkeypress="return validateNumeric(event)">
					</html:text></div>
					</td>
				</tr>
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfFemaleChildPatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%"> 
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalFemaleChildPatientAttended" tabindex="1" onkeypress="return validateNumeric(event)">
					</html:text></div>
					</td>
				</tr>
				
			</table>
	</his:ContentTag>
	
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" validateSave()" onkeypress="if(event.keyCode==13)validateSave()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">			
		</his:ButtonToolBarTag>

	<input type="hidden" name="numberOfRow" value="<%=(nRow-1)%>" />
	<html:hidden name="mrdMedicalCampFB" property="hmode"/>
	<html:hidden name="mrdMedicalCampFB" property="strIsCampClosed"/>
	<input type="hidden" name="sysdate" value="<%=sysdate %>">	
</html:form>
<his:status/>
</his:TransactionContainer>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
