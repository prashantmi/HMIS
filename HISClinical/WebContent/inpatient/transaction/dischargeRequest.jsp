<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>




<script type="text/javascript">

function validateAdd()
{
	var valid=true;
	if(document.getElementsByName("patStatus")[0].value==<%=InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED%> )
	{
		if(comboValidation(document.forms[0].dischargeStatus,"Discharge Status")
			&& validateNextVisitDate()
			&& validateRemarks()
			
		)
		{
			//alert("in pat if");
			valid=true;
		}
		else
		{
			//alert("in pat ifel");
			valid=false;
			
		}
		
	}
	else
	{
		if(validateRemarks())
			{
			  valid=true;
			}
		else
			{
			  valid = false;
			}
		if(document.getElementsByName("revokeRemarks")[0].value=="")
		{
			alert("Please Enter The Revoke Remarks");
			document.getElementsByName("revokeRemarks")[0].focus();
			valid=false;
		}
	}	
	return valid;
}

function validateNextVisitDate()
{
	//added by swati on date:20-06-2019
	var nextVisitDate = document.getElementsByName("nextVisitDate")[0].value;
//	alert(nextVisitDate);
	
	var selected = convertStrToDate(nextVisitDate, 'dd-Mon-yyyy');
	var currDate = document.getElementsByName("sysDate")[0].value;
	//alert(currDate);
	
	var current = convertStrToDate(currDate, 'dd-Mon-yyyy');
	
	if(document.getElementsByName("nextVisitDateFlag")[0].checked)
	{

		if(document.getElementsByName("nextVisitDays")[0].value=="")
		{		
			if(document.getElementsByName("dischargeStatus")[0].value!=8)
				{
				//if(document.getElementsByName("dischargeStatus")[0].value!=8){
				document.getElementsByName("nextVisitDateFlag")[0].value='-2';
				//}
				return true;
				}
		
			else
				{			
				
                           if(document.getElementsByName("dischargeStatus")[0].value='8'){
		                    	alert("Please Enter The no of Days");
		                     	document.getElementsByName("nextVisitDays")[0].focus();
			                    return false;
				          }
				}
		}
		else {

			return true;
		}
	}
	else
	{

		//added by swati on date:20-06-2019
			 if(selected<=current)
				{
				alert("Next visit Date can not greater than or equals to Current Date");
				return false;
				}
			
		return validateNextDate();
	}
}

function validateRemarks()
{
	 var currdiscRemarksLength = document.getElementsByName("dischargeRemarks")[0].value.length;
	 var currRevokeRemarksLength = document.getElementsByName("revokeRemarks")[0].value.length;
	 //alert(currdiscRemarksLength);
	 if(currdiscRemarksLength>500 || currRevokeRemarksLength>500)
		{
		  alert("maximum 500 characters are allowed in Remarks...");
		 return false;
		}
	 else
		 {
		   return true;
		 }
}
 function revokeRemarks(){

	 document.getElementById("revoketd1").style.display="";
	 document.getElementById("revoketd2").style.display="";
	 document.getElementById("revoketd3").style.display="";
	// document.getElementById("revoketd4").style.display="";
	 document.getElementById("show").style.display="";
}



function validateNextDate()
{
	return true;
}

function showNextVisitDateOrDays()
{
	if(document.getElementsByName("nextVisitDateFlag")[0].checked)
	{
		document.getElementById("dayLabel").style.display="block";
		document.getElementById("dayControl").style.display="block";
		document.getElementById("dateLabel").style.display="none";
		document.getElementById("dateControl").style.display="none";
	}
	else
	{	
		document.getElementById("dayLabel").style.display="none";
		document.getElementById("dayControl").style.display="none";
		document.getElementById("dateLabel").style.display="block";
		document.getElementById("dateControl").style.display="block";
	}
}

window.onload = function()
{
	showNextVisitDateOrDays();
}

function funct()

{
	//alert(document.getElementsByName("dischargeStatus")[0].value);		
	
	if(document.getElementsByName("dischargeStatus")[0].value=='2')
		{
	
		document.getElementsByName("nextVisitDateFlag")[0].disabled = true;
		document.getElementsByName("nextVisitDateFlag")[1].disabled = true;
		document.getElementById("dayLabel").style.display="none";
		document.getElementById("dayControl").style.display="none";
		document.getElementById("dateLabel").style.display="none";
		document.getElementById("dateControl").style.display="none"; 
		document.getElementsByName("dischargeStatus")[0].value
		}
	else
		{
		document.getElementsByName("nextVisitDateFlag")[0].disabled = false;
		document.getElementsByName("nextVisitDateFlag")[1].disabled = false;
		}
	if(document.getElementsByName("dischargeStatus")[0].value !='8'){

		document.getElementById("visitId").style.display='none';
		document.getElementById("dayId").style.display='none';

		}
	else{
		document.getElementById("visitId").style.display='';
		document.getElementById("dayId").style.display='';

		}


}
</script>
	<his:TitleTag>
		<his:name>
			<bean:message key="discharge" />
			<bean:message key="request" />
		</his:name>
	</his:TitleTag>
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
<his:statusTransactionInProcess>		
	<his:SubTitleTag name="Discharge Request">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<logic:equal name="DischargeRequestFB" property="patStatus" value="<%=InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED %>">
				<bean:define name="DischargeRequestFB" property="sysDate" id="sysDate" type="java.lang.String"/>
				<% if(sysDate==null||sysDate.equalsIgnoreCase(""))
				   {
					sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				   }	 
				%>
				<html:hidden name="DischargeRequestFB" property="sysDate" value="<%=sysDate%>"/>	
				
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="discharge" />
									<bean:message key="status"/>
								</b>
							</font>		
						</div>
					</td>
					<td width="75%"  class="tdfont">
						<div align="left">
							<html:select name="DischargeRequestFB" property="dischargeStatus" tabindex="1" onchange="funct();">
								<html:option value="-1">Select value</html:option>
								<logic:present name="<%=InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST %>">
									<html:options collection="<%=InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST %>"  property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
				</tr>
				<tr id="visitId">
					<td width="25%"  class="tdfonthead">
						<div align="right" id="nextvisit">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="nextVisitDate" />
								</b>
							</font>		
						</div>
					</td>
					<td width="75%"  class="tdfont">
						<div align="left" id="nextvisitdate">
							<html:radio name="DischargeRequestFB" property="nextVisitDateFlag" value="<%=InpatientConfig.NEXT_VISIT_SELECTION_DAYS %>" onclick="showNextVisitDateOrDays()"></html:radio>Days
							<html:radio name="DischargeRequestFB" property="nextVisitDateFlag" value="<%=InpatientConfig.NEXT_VISIT_SELECTION_DATE %>" onclick="showNextVisitDateOrDays()"></html:radio>Date
						</div>
					</td>
				</tr>
				<tr id="dayId">
					<td width="25%"  class="tdfonthead">
						<div align="right" id="dayLabel" style="display: none;">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
									<b>
										<bean:message key="days" />
									</b>
							</font>		
						</div>
						<div align="right" id="dateLabel" style="display: none;">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
									<b>
										<bean:message key="date" />
									</b>
							</font>		
						</div>
					</td>
					<td width="75%"  class="tdfont">
						<div align="left" id="dayControl" style="display: none;">
							<html:text name="DischargeRequestFB" property="nextVisitDays" maxlength="2" size="5" onkeypress="return validateNumeric(event)"></html:text>
						</div>
						<div align="left" id="dateControl" style="display: none;">
							<his:date name="nextVisitDate" dateFormate="%d-%b-%Y" value="<%=sysDate %>"></his:date>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="discharge" />
									<bean:message key="remarks"/>
								</b>
							</font>		
						</div>
					</td>
					<td width="75%"  class="tdfont">
						<div align="left">
							<html:textarea name="DischargeRequestFB" property="dischargeRemarks"rows="3" cols="50" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))"></html:textarea>
						</div>                                                                                                                                               
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="DischargeRequestFB" property="patStatus" value="<%=InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL %>">
				
					
					<!-- added by swati sagar on date:11-06-2019 -->
					
					<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					       <div><b><bean:message key="discharge" /></b>
					        	<b><bean:message key="status" />	</b>
						   </div>
					</font>
					</div>
					</td>
					
				       	<td width="25%"  class="tdfont">
					              <div align="left">
					    			 <b><bean:write name="DischargeRequestFB" property="dischargeStatusValue"  /></b>
										</div>
										
										
					     </td>
<!-- 					     <td width="50%"  class="tdfonthead"></td> -->
					     	<td width="50%"  class="tdfont">
					     	
					                	<div align="right">
                                               <a  onclick ="revokeRemarks()" onkeypress="revokeRemarks()">Want To Revoke?</a></div>
						                </div>
					     	</td>
					     	
					
					
					</tr>
					
					
					
					
					<tr>
					
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<div>
									<bean:message key="nextVisitDate" />
								</div>
									
								</b>
							</font>		
						</div>
						
					</td>
					<td width="25%"  class="tdfont">
					<div align="left">
					
									 <b><bean:write name="DischargeRequestFB" property="nextVisitDate"  /></b>
								</div>
					</td>
					
					<td width="50%"  class="tdfont">
					</td>
					
					
<!-- 					------------ -->
					
				</tr>
				
				
				
				
				
				<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="discharge" />
									<bean:message key="remarks"/>
								</b>
							</font>		
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<div align="left">
						
<%-- 							<html:textarea name="DischargeRequestFB" property="dischargeRemarksDisabled" rows="3" cols="25" disabled="true"></html:textarea> --%>
							<bean:write name="DischargeRequestFB" property="dischargeRemarksDisabled"  />
         
					</td>
					
					<td width="25%"  class="tdfont">
					</td>
					</tr>
				<tr id="revoketd">
					<td width="25%"  id="revoketd1" class="tdfonthead" style="display:none">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="revoke" />
									<bean:message key="remarks"/>
								</b>
							</font>		
						</div>
					</td>
					<td width="25%" id="revoketd2"   class="tdfont"style="display:none">
						<div align="left">
							<html:textarea name="DischargeRequestFB" property="revokeRemarks" rows="3" cols="50" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))"></html:textarea>
						</div>
					</td>
					<td width="50%" id="revoketd3"   class="tdfont" style="display:none">
					</td>
						
					
					
				</tr>
			</logic:equal>
		</table>
	</his:ContentTag>	
</his:statusTransactionInProcess>	
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
		<logic:equal name="DischargeRequestFB" property="patStatus" value="<%=InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL %>">
		<img class='button' id="show"   src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer;display:none"  tabindex='2' onclick =  "submitFormOnValidate(validateAdd(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateAdd(),'SAVE');")>		
		</logic:equal>
		<logic:equal name="DischargeRequestFB" property="patStatus" value="<%=InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED %>">
				<img class='button'    src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(validateAdd(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateAdd(),'SAVE');")>		
		</logic:equal>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:statusTransactionInProcess>
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
	</his:ButtonToolBarTag>
	
	
<html:hidden name="DischargeRequestFB" property="hmode"/>
<html:hidden name="DischargeRequestFB" property="patCrNo"/>
<html:hidden name="DischargeRequestFB" property="patStatus"/>
<html:hidden name="DischargeRequestFB" property="sysDate"/>			
<html:hidden name="DischargeRequestFB" property="dischargeRemarks"/>			
<html:hidden name="DischargeRequestFB" property="revokeRemarks"/>	

