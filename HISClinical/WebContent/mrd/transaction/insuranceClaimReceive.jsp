
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="mrd.MrdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
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

function validateForm(mode)
{
	
	if(document.getElementsByName("cidNoFlag")[0].value==<%=MrdConfig.ENABLE%>)
	{
		if(document.getElementsByName("recevingDate")[0].value=="")
		{
			alert("Please select receiving date"+'\n'+"For this click on plus button");
			document.getElementsByName("recevingDate")[0].focus();
			return false;
		}
		
		if(document.getElementsByName("CIDNo")[0].value=="")
		{
			alert("Please select CID No"+'\n'+"For this click on plus button");
			document.getElementsByName("CIDNo")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("cidNoFlag")[0].value==<%=MrdConfig.DISABLE%>)
	{
		if(document.getElementsByName("recevingDate")[0].value=="")
		{
			alert("Please select receiving date");
			document.getElementsByName("recevingDate")[0].focus();
			return false;
		}
		
		var noOfDay=noOfDays(document.getElementsByName("recevingDate")[0].value,document.getElementsByName("sysDate")[0].value)
		if(noOfDay>0)
		{
			alert("Receving date can not be greater than system date");
			document.getElementsByName("recevingDate")[0].focus();
			return false;
		}
		
		if(document.getElementsByName("recevingTimeHr")[0].value=="")
		{
			alert("Please enter receiving time hour");
			document.getElementsByName("recevingTimeHr")[0].focus();
			return false;
		}
		if(document.getElementsByName("recevingTimeHr")[0].value>23)
		{
			alert("Hour can not be greater than 23");
			document.getElementsByName("recevingTimeHr")[0].focus();
			return false;
		}
		if(document.getElementsByName("recevingTimeMin")[0].value=="")
		{
			alert("Please enter receiving time minute");
			document.getElementsByName("recevingTimeMin")[0].focus();
			return false;
		}
		if(document.getElementsByName("recevingTimeMin")[0].value>59)
		{
			alert("Minute can not be greater than 59");
			document.getElementsByName("recevingTimeMin")[0].focus();
			return false;
		}
		if(noOfDay==0)
		{
			if(document.getElementsByName("recevingTimeHr")[0].value>document.getElementsByName("sysHour")[0].value)
			{
				alert("Receving time can not be greater than system time");
				document.getElementsByName("recevingTimeHr")[0].focus();
				return false;
			}
		
			if(document.getElementsByName("recevingTimeHr")[0].value==document.getElementsByName("sysHour")[0].value)
			{
				if(document.getElementsByName("recevingTimeMin")[0].value>document.getElementsByName("sysMinute")[0].value)
				{
					alert("Receving time can not be greater than system time");
					document.getElementsByName("recevingTimeMin")[0].focus();
					return false;
				}
			}
		}
	
	}
	
	if(document.getElementsByName("receivingMode")[0].checked==false && document.getElementsByName("receivingMode")[1].checked==false)
	{
		alert("Please select Received From");
		document.getElementsByName("receivingMode")[0].focus;
		return false;
	}
	
	if(document.getElementsByName("patName")[0].value=="")
	{
		alert("Please select patient name"+'\n'+"For this click on search button");
		document.getElementsByName("patName")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("companyId")[0].value=="-1")
	{
		alert("Please select company");
		document.getElementsByName("companyId")[0].focus();
		return false;
	}
	if(document.getElementsByName("policyNo")[0].value=="")
	{
		alert("Please enter policy No");
		document.getElementsByName("policyNo")[0].focus();
		return false;
	}
	
	submitPage(mode);
}

</script>
<body >
		

<his:TransactionContainer>
		<html:form action="/insuranceClaimReceive">
		<his:TitleTag name="Insurance Claim Receiving Detail">
		</his:TitleTag>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<font color="#FF0000">*</font>
		 					<bean:message key="recevingDate"/>
		 				</font>
		  			</div>
	      		</td> 
	      		<logic:equal value="<%=MrdConfig.DISABLE %>" name="InsuranceClaimReceiveFB" property="cidNoFlag">
	      		<td width="25%" class="tdfont">
					<div align="left">
						<bean:define id="receiveDate" name="InsuranceClaimReceiveFB" property="recevingDate"></bean:define>
						<his:date name="recevingDate" dateFormate="%d-%b-%Y" value="<%=receiveDate.toString() %>"></his:date>
					</div>
				</td>
				</logic:equal>
				<logic:equal value="<%=MrdConfig.ENABLE %>" name="InsuranceClaimReceiveFB" property="cidNoFlag">
				<td width="25%" class="tdfont">
					<div align="left">
						<html:text property="recevingDate" name="InsuranceClaimReceiveFB" size="15" readonly="true"></html:text>
					</div>
				</td>
				</logic:equal>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<font color="#FF0000">*</font>
		 					<bean:message key="recevingTime"/>
		 				</font>
		  			</div>
	      		</td>
	      		<logic:equal value="<%=MrdConfig.DISABLE %>" name="InsuranceClaimReceiveFB" property="cidNoFlag">
	      		<td width="25%" class="tdfont">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<span id='divStarthrcontrol' align="left">	            
				   			<html:text name="InsuranceClaimReceiveFB" tabindex="1" property="recevingTimeHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  				<b>
				  			 		<bean:message key="colon"/>
				  			 	</b>
						</span>
				 		<span id='divStartMinControl' align="left">         
							<html:text name="InsuranceClaimReceiveFB" tabindex="1" property="recevingTimeMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
								<bean:message key="timeFormat"/>
		    			</span>
					</font>
				</td> 
				</logic:equal>
				<logic:equal value="<%=MrdConfig.ENABLE %>" name="InsuranceClaimReceiveFB" property="cidNoFlag">
				<td width="25%" class="tdfont">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<span id='divStarthrcontrol' align="left">	            
				   			<html:text name="InsuranceClaimReceiveFB" tabindex="1" property="recevingTimeHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" readonly="true"/>	
				  				<b>
				  			 		<bean:message key="colon"/>
				  			 	</b>
						</span>
				 		<span id='divStartMinControl' align="left">         
							<html:text name="InsuranceClaimReceiveFB" tabindex="1" property="recevingTimeMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)" readonly="true"/>				
								<bean:message key="timeFormat"/>
		    			</span>
					</font>
				</td>
				</logic:equal>
			</tr>
			<tr>
				 <logic:equal value="<%=MrdConfig.ENABLE %>" name="InsuranceClaimReceiveFB" property="cidNoFlag">
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="CIDNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="CIDNo" value="" size="20" tabindex="1" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true"/>
				 		<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' alt="Add CID No" title="Add CID No" onkeypress="if(event.keyCode==13) openPopup('/HISClinical/mrd/insuranceClaimReceive.cnt?hmode=CIDNO',event,400,700);" onclick="openPopup('/HISClinical/mrd/insuranceClaimReceive.cnt?hmode=CIDNO',event,400,700);">
				 	</div>
				 </td>
				 </logic:equal>	
				 <logic:equal value="<%=MrdConfig.DISABLE%>" name="InsuranceClaimReceiveFB" property="cidNoFlag">
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="fileTrackingNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="CIDNo" value="" size="20" tabindex="1" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)"/>
				 	</div>
				 </td>
				 </logic:equal>	
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="receivingMode"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">Company</font><html:radio property="receivingMode" value="<%=MrdConfig.RECEIVING_MODE_COMPANY %>" name="InsuranceClaimReceiveFB" tabindex="1"></html:radio>
				 		<font color="#000000">Patient/Relative</font><html:radio property="receivingMode" value="<%=MrdConfig.RECEIVING_MODE_PATIENT_OR_RELATIVE %>" name="InsuranceClaimReceiveFB" tabindex="1"></html:radio>
				 	</div>
				 </td>
				 
			</tr>
		</table>
		</his:ContentTag>
		<his:SubTitleTag name="Patient Detail">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td>
					<div align="right">
						<img class="button"  style="cursor: pointer" alt="Search" title="Search" tabindex='1'	src='<his:path src="/hisglobal/images/btn-search.png"/>' onclick="openPopup('/HISClinical/mrd/insuranceClaimReceive.cnt?hmode=SEARCHPOPUP',event,400,700);"  onkeypress="if(event.keyCode==13) openPopup('/HISClinical/mrd/insuranceClaimReceive.cnt?hmode=SEARCHPOPUP',event,400,700);">
					</div>
				</td>
			</tr>
		</table>
		</his:SubTitleTag>
				
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="patientName"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="patName" value="" size="30" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="genderAge"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" nowrap="nowrap">
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="ageGender" value="" size="14" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
				 	</div>
				  </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="crNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="patCrNo" value="" size="14" tabindex="1"  onkeypress="return validateNumeric(event)" readonly="true"/>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="admissionNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="patAdmNo" value="" size="14" tabindex="1"  onkeypress="return validateNumeric(event)" readonly="true"/>
				 	</div>
				 </td>
				 
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap="nowrap">
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="admissionDeptUnit"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="admissionDeptUnit" value="" size="30" tabindex="1" onkeypress="return validateNumeric(event)" readonly="true"/>
				 	</div>
				 </td>
				
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="admissionDate"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="patAdmDate" value="" size="14" tabindex="1"  onkeypress="return validateNumeric(event)" readonly="true"/>
				 	</div>
				 </td>
			</tr>
			<tr>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="dischargeDate"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="dischargeDate" value="" size="14" tabindex="1"  onkeypress="return validateNumeric(event)" readonly="true"/>
				 	</div>
				 </td>
				 
				  <td class="tdfonthead" width="25%" >
				  </td>
				  <td class="tdfont" width="25%" >
				  </td>
				  
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="companyName"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:select name="InsuranceClaimReceiveFB" property="companyId" tabindex="1" disabled="false">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=MrdConfig.ALL_INSURANCE_COMPANY_LIST%>" >
							<html:options collection="<%=MrdConfig.ALL_INSURANCE_COMPANY_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="policyNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="InsuranceClaimReceiveFB" property="policyNo" value="" size="21" tabindex="1" maxlength="20" onkeypress="return validateAlphaNumOnly(this,event)" />
				 	</div>
				 </td>
			</tr>
			 <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="remarks"/>
			 			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="InsuranceClaimReceiveFB" tabindex="1" rows="2" cols="102" property="remarks" onkeypress="return (validateTextArea(event,this,'500'))">
						</html:textarea>
					</font> 
				</td>
					
		    </tr>
		   
		</table>
		</his:ContentTag>	
				
		<his:ButtonToolBarTag>
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
		</his:ButtonToolBarTag>
	
	<html:hidden name="InsuranceClaimReceiveFB" property="hmode" />
	<html:hidden name="InsuranceClaimReceiveFB" property="cidNoFlag" />
	<html:hidden name="InsuranceClaimReceiveFB" property="episodeCode" />
	<html:hidden name="InsuranceClaimReceiveFB" property="episodeVisitNo" />
	<html:hidden name="InsuranceClaimReceiveFB" property="sysDate" />
	<html:hidden name="InsuranceClaimReceiveFB" property="sysHour" />
	<html:hidden name="InsuranceClaimReceiveFB" property="sysMinute" />
	
	
</html:form>
</his:TransactionContainer>
<his:status/>

</body>
</html>