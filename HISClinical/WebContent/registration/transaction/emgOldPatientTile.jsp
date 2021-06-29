<%try{ %>
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/popup.js" />
<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

//Submit Tile by setting hmode

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

function  validateOldDepartment()
{
var len;
	var isValid = true;
	//int count=0;
	count=0;
	//alert("before assignment");
	len=document.getElementsByName("departmentsToVisitStamp").length;
	//alert("before for");
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("departmentsToVisitStamp")[i].checked){
				count++;
				}
				}
	
	if(count==0){
		isValid = false;
		alert("Please Select Unit");
				}
	else
	isValid = true;

//alert(isValid);
return isValid;
}



function renewalConfirmation()
{
	var valid=false;
	valid=confirm("DO you want to Renew the Registration ?");
	return valid;
}

function renewalValidation()
{
 	var len;
	var isValid = true;
	
	count=0;
	
	len=document.getElementsByName("departmentsToRenew").length;
	
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("departmentsToRenew")[i].checked){
				count++;
				}
				}
	
	if(count==0){
		isValid = false;
		alert("Select a  department");
				}
	else
	isValid = true;


return isValid;
}

function submitTile(mode){	
	//alert("In submitTitle:  "+mode);
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
}

function showdivision(nam1,nam2,rad)
{
nam1.style.display="block";
nam2.style.display="none";
rad.checked=false;
}

window.onload=function(){
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}

}
</script>

<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>
<%String varStatus = "";
			boolean varIsNewStatus = false;
			System.out.println("sys date in jsp"+session.getAttribute(Config.SYSDATEOBJECT));
			String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			%>

<%String divdisplay = "\"\"";
			String divnddisplay = "\"\"";
			String divisrefdisplay = "\"\"";
			String divRefGnctdHospitalCode = "\"\"";
			String divRefHospname = "\"\"";
			String strdivage = "\"\"";
			String strdivdob = "\"\"";
			String RefGnctdHosNameradio = "";
			String RefGnctdHoscoderadio = "";
			String renewalStatus="";
			%>
<his:statusNew>
	<%varStatus = "New";
				varIsNewStatus = true;%>
</his:statusNew>

<his:TitleTag name="Registered Patient Visit">
	<b><font size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> 
		</font></b>


</his:TitleTag>


<his:InputCrNoTag name="EmgPatientVisitStampFB"></his:InputCrNoTag>

<bean:define id="crNo" name="EmgPatientVisitStampFB" property="patCrNo"
	type="java.lang.String" />
<%if (!crNo.trim().equals("")) {

				%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />

<his:statusRecordFound>
<%renewalStatus="renew";%>
		<his:SubTitleTag name="Renewal of Registration">
		<his:name>
			<bean:message key="renewalOfregistration" />
		</his:name>
		</his:SubTitleTag>
		
		
		<his:ContentTag>
		<%if(Config.RENEWAL_TYPE.equals("1") || Config.RENEWAL_TYPE.equals("2")){ %>
			<table width="100%" colspacing="1" colpadding="0">
				<tr>
					<td width="20%"  class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="renewRegistration"/></b>
					</font>
					</div>
	  				</td>      
	  				<td width="20%"  class="tdfont">
					<input type="checkbox" tabindex="1" name="renewal" onclick="if (renewalConfirmation()) submitForm('RENEWAL')"/>
					</td>
					<td width="20%"  class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="amount"/></b>
					</font>
					</div>
	  				</td>  
	  				<td width="25%" class="tdfont">
					<div align="left"><html:text name="EmgPatientVisitStampFB"
					property="amount" maxlength="3" size="4" readonly="true"  /></div>
					</td>
				</tr>
			</table>
			
			
			<%}else if(Config.RENEWAL_TYPE.equals("3") || Config.RENEWAL_TYPE.equals("4") || Config.RENEWAL_TYPE.equals("5")){ %> 
			<table width="100%" colspacing="1" colpadding="0">
			<bean:define id="RENEWALEPISODEVO" name="<%=RegistrationConfig.RENEWAL_REQUIRED_EPISODE_ARRAY%>" type="hisglobal.vo.EpisodeVO[]" />
			
		<%String amt=(String)session.getAttribute("amount"); %>
				<tr>
					<td width="5%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="renewRegistration"/></b></font></td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="department"/></b></font></td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="unit"/></b></font></td>
					
						
				</tr>			
				<logic:iterate id="episode" name="<%=RegistrationConfig.RENEWAL_REQUIRED_EPISODE_ARRAY%>" indexId="i">
				<tr>
					<td class="tdfonthead">
					<div align="center">
						<input type="checkbox" tabindex="1" name="departmentsToRenew"  value='<bean:write name="episode" property="episodeCode"/>' />
					</div>
					</td>
					<td class="tdfont">
					<div>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="episode" property="department"/>
					</font>
					</div>
					</td>
					<td class="tdfont">
					<div>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="episode" property="departmentUnit"/>
					</font>
					</div>
					</td>
				</tr>
				</logic:iterate>
				
				<tr>
				<td colspan="3" >
				<table width="100%">
					<tr>
					<td width="50%" class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="amount"/></b>
					</font>
					</div>
	  				</td>  
	  				<td width="50%" class="tdfont">
					<div align="left"><html:text name="EmgPatientVisitStampFB"
					property="amount" maxlength="3" size="4" readonly="true"  /></div>
					</td>
					</tr>
					</table>
				</td>
				</tr>
				
			</table>
			<%} %>
		</his:ContentTag>
	</his:statusRecordFound>



<his:statusTransactionInProcess>



	<his:SubTitleTag name="Visit Details">
		<his:name>
			<bean:message key="visitDetails" />
		</his:name>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<%varStatus = "InProcess";%>
					<td width="15%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="mlc" /></font></div>
				</td>
				<td width="15%"><html:checkbox name="EmgPatientVisitStampFB"
					property="isMLC" value="<%=RegistrationConfig.IS_MLC_TRUE%>" tabindex="1"/></td>
			</tr>
		</table>
	</his:SubTitleTag>


	<his:ContentTag>
		<logic:notEmpty
			name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>">
			<%System.out.println("hello...");

							%>
			<bean:define id="EPISODEVO"
				name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>"
				type="hisglobal.vo.EpisodeVO[]" />
			<%if (EPISODEVO.length != 0) {
								System.out
										.println("length in jsp......................"
												+ EPISODEVO.length);

								%>
			<%varStatus = "InProcess";%>
			
			
			<table width="100%" colspacing="1" colpadding="0"
				style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
					<tr>
							<td width="5%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message
								key="status" /></b></font></td>
							<td width="20%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message
								key="department" /></b></font></td>
							<td width="20%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message
								key="unit" /></b></font></td>
							<%-- <td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="room"/></b></font></td>--%>
							
							<%-- <td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="onRequest"/></b></font></td>  --%>
						</tr>
						<logic:iterate id="episode"
							name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>"
							indexId="i">
							
							<bean:define name="episode" property="episodeCode"
								id="episodeCode" />
						<bean:define name="episode" id="renewalType" property="renewalType"/>
						<%String renewType=(String)renewalType;
						int x=Integer.parseInt(renewType);%>
							<tr>
								<td width="5%" class="tdfont" nowrap><%String hh = (String) episodeCode;
									%> <html:hidden name="EmgPatientVisitStampFB"
									property="hiddenEpisode" value="<%=hh%>" /> 
									
									<logic:equal name="episode" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE%>">
									 	<div align="center">
												<img src='<his:path src="/hisglobal/images/stop.png"/>' title="Unit not on duty">
											</div>
									 </logic:equal>
									
									 <%if ((Config.CLIENT).equals(Config.CLIENT_GNCTD)){ %>
									 <logic:equal name="episode" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_TRUE%>">
										<logic:equal	name="episode" property="isConfirmed" 	value="1">
											<div align="center"><img
										src='<his:path src="/hisglobal/images/icn-lock.png"/>' 
										title="Already Visited"></div>
										</logic:equal>
										<logic:equal name="episode" property="isConfirmed" 	value="0">
										
											<logic:equal name="episode" property="renewalRequired"
												value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
												
												<div align="center"><html:radio name="EmgPatientVisitStampFB"
													property="departmentsToVisitStamp" tabindex="1"
													value="<%=hh %>" /> <%-- <input type="checkbox" tabindex="1" name="departmentsToVisitStamp" value='<bean:write name="episode" property="episodeCode"/>'/>--%>
												</div>
											</logic:equal>
	
											<logic:equal name="episode" property="renewalRequired"
												value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
												
												<div align="center"><html:radio name="EmgPatientVisitStampFB"
													property="departmentsToVisitStamp" 
													tabindex="1" value="<%=hh %>" onclick="showValue(this)" /></div>
											</logic:equal>
										</logic:equal>
									</logic:equal>
									<%}else { %>
									<logic:equal name="episode" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_TRUE%>">
										<logic:equal name="episode" property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_TRUE %>">
											<div align="center">Patient In Triage</div>
										</logic:equal>
										 <logic:equal name="episode" property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_FALSE %>">
										 		<logic:equal	name="episode" property="isConfirmed" 	value="1">
											<div align="center"><img
										src='<his:path src="/hisglobal/images/icn-lock.png"/>' 
										title="Already Visited"></div>
										</logic:equal>
										<logic:equal name="episode" property="isConfirmed" 	value="0">
										
											<logic:equal name="episode" property="renewalRequired"
												value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
												
												<div align="center"><html:radio name="EmgPatientVisitStampFB"
													property="departmentsToVisitStamp" tabindex="1"
													value="<%=hh %>" /> <%-- <input type="checkbox" tabindex="1" name="departmentsToVisitStamp" value='<bean:write name="episode" property="episodeCode"/>'/>--%>
												</div>
											</logic:equal>
	
											<logic:equal name="episode" property="renewalRequired"
												value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
												
												<div align="center"><html:radio name="EmgPatientVisitStampFB"
													property="departmentsToVisitStamp" 
													tabindex="1" value="<%=hh %>" onclick="showValue(this)" /></div>
											</logic:equal>
										</logic:equal>
	
										 </logic:equal>
									</logic:equal>
									<%} %>
							</td>
								
									
							
								<td width="20%" class="tdfont">
								<div align="center">
								<% if(x==3 || x==4 || x==5){%>
								<logic:equal name="episode"
									property="renewalRequired"
									value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="episode" property="department" /> </font>
								</logic:equal>
								 <logic:equal name="episode"
									property="renewalRequired"
									value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="episode" property="department" /> </font>
								</logic:equal>
								<%}else { %>
								<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="episode" property="department" /> </font>
								<%} %>
								</div>
								</td>


								<td width=20% " class="tdfont">
								<div align="center">
								<% if(x==3 || x==4 || x==5) {%>
								<logic:equal name="episode"
									property="renewalRequired"
									value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
									
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="episode" property="departmentUnit" /> </font>
								</logic:equal> 
								<logic:equal name="episode"
									property="renewalRequired"
									value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="episode" property="departmentUnit" /> </font>
								</logic:equal>
										<%}else{ %>
										<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="episode" property="departmentUnit" /> </font>
										<%} %>
								</div>
								</td>

								 
							</tr>
						</logic:iterate>
						<html:hidden name="EmgPatientVisitStampFB" property="hcode" />
					

			</table>
			<%}

						%>
		</logic:notEmpty>
		<logic:empty
			name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>">
			<%	varStatus = "";
				%>
			
		</logic:empty>
	</his:ContentTag>
		<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' onclick="showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' onclick="showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	<div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<input type="radio">
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Visit stamp allowed
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<img src='<his:path src="/hisglobal/images/icn-lock.png"/>'>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Already Visited But Not Confirmed
					</div>
					</font>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<img src='<his:path src="/hisglobal/images/stop.png"/>'>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Unit Not Working
					</div>
					</font>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Red
					</div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Registration expired, renewal required for this department
					</div>
					</font>
				</td>				
			</tr>
			 <%if ((Config.CLIENT).equals(Config.CLIENT_PGIMER)){ %>
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					In Triage
					</div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Patient Is In Triage
					</div>
					</font>
				</td>				
			</tr>
			<%} %>
	
		</table>
	</his:ContentTag>
	</div>
</his:statusTransactionInProcess>
<%}
%>
<his:ButtonToolBarTag>

	<%if(renewalStatus.equals("renew")){ %>
<div align="center">
				        	    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick = "if(renewalValidation())submitForm('RENEWAL');" onkeypress="if (event.keyCode==13) submitForm'RENEWAL');" >
					         		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          			            	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          			          	</div>
<%}else if (varStatus.equals("InProcess")) {%>

	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
		style=cursor:pointer
		onkeypress="if(event.keyCode==13
          submitFormOnValidate(validateOldDepartment(),'SAVE');"
		tabindex="1"
		onclick="submitFormOnValidate(validateOldDepartment(),'SAVE');" />
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		tabindex="1" style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
		style=cursor:pointer tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');">
	<%} else {

				%>

	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		tabindex="1" style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
		style=cursor:pointer tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');">

	<%}

			%>
</his:ButtonToolBarTag>
<html:hidden name="EmgPatientVisitStampFB" property="hmode" value="unspecified"/>
<his:status />
<%}catch(Exception e)
{
e.printStackTrace();	
}%>