<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="registration.master.controller.util.RegistrationTimingMasterUTIL"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/css/calendar-blue2.css"/>

<title>Registration Timing Master</title>

<script>

window.history.forward()
	function submitTile(mode)
	{
		document.getElementsByName("hmode")[0].value=mode;  
	//	alert(">>>>>>"+mode)
	   	document.forms[0].submit();
	}
	
	function validateGo()
	{
		if(document.getElementsByName("regCatCode")[0].value=="-1")
		{
			alert("Please Select The Registration Category");
		}
		else if(document.getElementsByName("seasonCode")[0].value=="-1")
		{
			alert("Please Select The Season");
		}
		else
		submitTile("GO");
	}
	
	function setAlreadyAddedShifts()
	{
	//	alert("Inside Already Added");
		var str=document.getElementsByName('alreadyAdded')[0].value;
	//	alert(str);
		var arr=str.split("#");
		var chks=document.getElementsByName('shift');
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
	
	function validateSave()
	{
		count=0;
		var len=document.getElementsByName("shift").length
		
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("shift")[i].checked)
			{
				count++;
			}
		}
		
		if(count==0)
		{
			alert("Please Select At least One Shift");
		}
		else
		{
			submitTile("SAVE");
		}
	}

</script>

	<body onload="setAlreadyAddedShifts()">
		<html:form action="/master/registrationTimingMaster">
			<his:TransactionContainer>
			<%@ page import ="java.util.*,registration.*, hisglobal.utility.* " %>
			
				<his:TitleTag name="Registration Timing Master">
				</his:TitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="regCat"/>
	  									</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<logic:equal name="registrationTimingMasterFB" property="hmode" value="NEW">
										<html:select name="registrationTimingMasterFB" property="regCatCode" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:option value="11">Normal</html:option>
											<html:option value="12">Special</html:option>
											<html:option value="13">Causality</html:option>
										</html:select>
									</logic:equal>
									
									<logic:notEqual name="registrationTimingMasterFB" property="hmode" value="NEW">
										<logic:equal name="registrationTimingMasterFB" property="regCatCode" value="11">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  							<b>&nbsp;
					  								<bean:message key="normal"/>
						  						</b>
						  					</font>	
										</logic:equal>
										<logic:equal name="registrationTimingMasterFB" property="regCatCode" value="12">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  							<b>&nbsp;
					  								<bean:message key="special"/>
					  							</b>
					  						</font>	
										</logic:equal>
										<logic:equal name="registrationTimingMasterFB" property="regCatCode" value="13">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  							<b>&nbsp;
					  								<bean:message key="casuality"/>
					  							</b>
					  						</font>	
										
										</logic:equal>
								
									</logic:notEqual>	
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="season"/>
	  									</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<logic:equal name="registrationTimingMasterFB" property="hmode" value="NEW">	
										<html:select name="registrationTimingMasterFB" property="seasonCode" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_SEASON %>">
											<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_SEASON %>" property="value" labelProperty="label"/>
											</logic:present>
										</html:select>
									</logic:equal>	
									<logic:notEqual name="registrationTimingMasterFB" property="hmode" value="NEW">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  						<b>&nbsp;
					  							<bean:write name="registrationTimingMasterFB" property="seasonName"/>
					  							<html:hidden name="registrationTimingMasterFB" property="seasonName"/>
					  						</b>
					  					</font>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
<his:statusTransactionInProcess>				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="1">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="daysOfWeek"/>
										</b>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="shift"/>
										</b>
									</font> 
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="1" cellpadding="1">	
						<tr>
							<td width="25%"  Class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
	  							<div align="center">
					  				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;
					  				</font>
						  		</div>
		  					</td>
		  					<%int noOfShift=RegistrationTimingMasterUTIL.getNoOfShift(request);
							  System.out.println(">>>>>>"+noOfShift);
							  
								List colShift = (List)session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_SHIFT_FOR_REG);
							  	Iterator itrColShift = colShift.iterator();
							  	while(itrColShift.hasNext()){
							  		Entry shiftEntry = (Entry) itrColShift.next();
							%>
							
							<td Class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
	  							<div align="center">
					  				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%= shiftEntry.getLabel() %>
					  				</font>
						  		</div>
		  					</td>
								<%} %>
								
								<% for(int dow=1; dow<=7; dow++ ){
									%>
						</tr>
						<tr>
							<td class="tdfont">
								<div align="center">
								<%String dayRow="dOw"+dow; %>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="<%=dayRow %>"/>
									</font>
								</div>
							</td>
							
							<% 
							List colShiftCode = (List)session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_SHIFT_FOR_REG);
						  	Iterator itrColShiftCode = colShiftCode.iterator();
						  	while(itrColShiftCode.hasNext()){
						  		Entry shiftEntryCode = (Entry) itrColShiftCode.next();
							%>
							
							
								<td class="tdfont">
								<div align="center">
									<html:checkbox  name="registrationTimingMasterFB"  property="shift" value='<%=dow+"_"+shiftEntryCode.getValue() %>' tabindex="1"/>
								</div>
							</td>
							
						  	<%}} %>
						</tr>
								
										
					</table>
				</his:ContentTag>	
</his:statusTransactionInProcess>		
				<his:ButtonToolBarTag>
					<logic:equal name="registrationTimingMasterFB" property="hmode" value="NEW">
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_SHIFT_FOR_REG%>">
						<img class="button" src='<his:path src="/hisglobal/images/GoFront.png"/>' style=cursor:pointer tabindex="1" onclick="validateGo() && submitTile('GO')" onkeypress="if(event.keyCode==13)validateGo() && submitTile('GO');">
					</logic:present>	
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW');">
					</logic:equal>
						
					<logic:equal name="registrationTimingMasterFB" property="hmode" value="GO">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateSave() && submitTile('SAVE')" onkeypress="if(event.keyCode==13)validateSave() && submitTile('SAVE');">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL');">
					</logic:equal>	
				</his:ButtonToolBarTag>
				
				<html:hidden name="registrationTimingMasterFB" property="hmode"/>
				<html:hidden name="registrationTimingMasterFB" property="regCatCode"/>
				<html:hidden name="registrationTimingMasterFB" property="seasonCode"/>
				<html:hidden name="registrationTimingMasterFB" property="tempMode"/>
				<html:hidden name="registrationTimingMasterFB" property="alreadyAdded"/>
				
			</his:TransactionContainer>
		</html:form>
		<his:status/>
	</body>
</html>