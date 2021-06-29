<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/registration/js/popup.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function validateParichitRegistration(mode)
{
	var visitDate=document.getElementsByName('visitDate')[0]
	var sysDate=document.getElementsByName('sysDate')[0]
	var visitHr=document.getElementsByName('visitHr')[0]
	var visitMin=document.getElementsByName('visitMin')[0]
	var employeeId=document.getElementsByName('employeeId')[0]
	var episodeActionCode=document.getElementsByName('episodeActionCode')[0]
	
	var vaild=false
	if(compareDateCall(visitDate,sysDate,2,'Visit Date','Current Date') &&
		validateVisitHr(visitHr) &&
		validateVisitMin(visitMin) &&
		comboValidation(employeeId,'Consultant') &&
		comboValidation(episodeActionCode,'Episode Action')
	)
	{
		valid=true
	}
	return valid
}

function validateVisitHr(visitHr){	

      valVisitHr=visitHr.value;
 //     alert('valFromHr'+valFromHr);
	  if(valVisitHr==null||valVisitHr==""){	  	       
	      alert("Please Enter value in Hr Field");	  	      
	      document.getElementsByName('visitHr')[0].focus();
	      return false;     
	  }
	  if(valVisitHr>23){
	    alert("Hr Value canont be greater than 23");
	    document.getElementsByName('visitHr')[0].focus();
	    return false;     
	  }
	  return true;	  
	} 
	
	function validateVisitMin(visitMin){	 
      valVisitMin=visitMin.value;
	  if(valVisitMin==null||valVisitMin==""){	  	       
	      alert("Please Enter value in Min Field");	  	      
	      document.getElementsByName('visitMin')[0].focus();
	      return false;     
	  }
	  if(valVisitMin>59){
	    alert("From Min Value canont be greater than 59");
	    document.getElementsByName('visitMin')[0].focus();
	    return false;     
	  }
	  return true;	  
	}

function compareDateCall(d1,d2,mode,l1,l2) 
{
	var valid=true;
	if(isEmpty(d1,l1) && isEmpty(d2,l2))
	{
 		if(compareDate(d1,d2, mode))
			valid = true;
		else
		{
			alert(l1+" can't be greater than "+l2);
			valid = false;
		}
	}
	else
		valid=false;
	return valid;
}

function moveToRightBox(elem, evt){
		val=elem.value;
		maxLength =elem.getAttribute('maxlength');
		i=0;
		if(val.length==maxLength){
			for(i=0; i<prevValue.length;i++)
				if(prevValue.charAt(i)!=val.charAt(i))
					break;
		}
		if(i==maxLength-1){
			if(elem.name == 'visitHr'){
				document.getElementsByName("visitMin")[0].focus();
			
			}
		}	
	}
	
	function setPreviosValue(elem, evt){
			prevValue = elem.value;
	}
</script>


<%@page import="java.util.*,registration.*,hisglobal.vo.*, registration.controller.fb.FileNoChangeFB.*, registration.controller.util.FileNoChangeUTIL"%>
<% String 	sysDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); 
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:TitleTag>
	<his:name>
			<bean:message key="parichiPatientRegistration" />
	</his:name>
	<b> <font  size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="date" />
	<bean:message key="and" /> <bean:message key="time" />
	 <%=systemDate %>
		 </font> </b>
</his:TitleTag>
<logic:equal name="parichitRegistrationFB" property="calledFrom" value="DIRECTLY">
<his:InputCrNoTag name="parichitRegistrationFB">
</his:InputCrNoTag>
</logic:equal>

<his:statusTransactionInProcess>
	<tiles:insert page="/patientDetail.cnt" flush="true" />
	
	<his:SubTitleTag name="Parichit Detail">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#EBEBEB">
	
			<tr>
			
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="visitDate" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<his:date name="visitDate" dateFormate="%d-%b-%Y" ></his:date>
					 </font></td>
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="visitTime" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					
					<span id='divfromhrcontrol' align="left">	            
		   			<html:text name="parichitRegistrationFB" tabindex="1" property="visitHr"  maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)" />	
		  			 <b>	<bean:message key="colon"/></b>
					</span>
		 			<span id='divfromMinControl' align="left">         
					<html:text name="parichitRegistrationFB" tabindex="1" property="visitMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>
    				</span>
					 </font></td>
				
				</tr>
				
	<tr>
				
	
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="con" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:select name="parichitRegistrationFB" tabindex="1" property="employeeId" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>">
  					<html:options collection="<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>" property="value" labelProperty="label" />
  					</logic:present>
	 			  </html:select>
					 </font></td>				
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="episodeAction" /> </font></div>
				</td>

				
				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:select name="parichitRegistrationFB" tabindex="1" property="episodeActionCode" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_EPISODE_ACTION %>">
  					<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_EPISODE_ACTION%>" property="value" labelProperty="label" />
  					</logic:present>
	 			  </html:select>
					 </font></td>
	</tr>
	
	 <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  
	  <b>
	  <bean:message key="remarks"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	      	<html:textarea name="parichitRegistrationFB"    property="remarks"  />  
	     
	  </div>
      
       </td>
  </tr>
		
	</table>
	</his:ContentTag>
	
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>

	<logic:equal name="parichitRegistrationFB" property="calledFrom" value="DIRECTLY">
	<his:statusTransactionInProcess>
	<img class="button"	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) validateYellowSlipEntry('SAVE');"
	tabindex="1"
	onclick="if(validateParichitRegistration()) submitForm('SAVE')">
	</his:statusTransactionInProcess> 
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style=cursor:pointer 	onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
	onclick="submitPage('CANCEL');"> <img class="button"
	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
	tabindex="1" onclick="submitForm('NEW')"
	onkeypress="if(event.keyCode==13) submitForm('INITIAL');">
	</logic:equal>
	<logic:equal name="parichitRegistrationFB" property="calledFrom" value="OPDDESK">
	<his:statusTransactionInProcess>
	<img class="button"	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) validateYellowSlipEntry('SAVE');"
	tabindex="1"
	onclick="validateParichitRegistration('SAVE')">
	</his:statusTransactionInProcess>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style=cursor:pointer 	onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
	onclick="submitPage('CANCEL');"> <img class="button"
	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
	tabindex="1" onclick="submitForm('NEW')"
	onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</logic:equal>
</his:ButtonToolBarTag>


<his:status/>
<html:hidden name="parichitRegistrationFB" property="hmode"/>
<html:hidden name="parichitRegistrationFB" property="calledFrom"/>
<html:hidden name="parichitRegistrationFB" property="sysDate" value="<%=sysDate %>"/>
