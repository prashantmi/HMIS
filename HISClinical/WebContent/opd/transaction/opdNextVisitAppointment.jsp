<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>

<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/calendar.js" />
<his:javascript src="/opd/opdJs/opdAjax.js" />
<his:javascript src="/opd/opdJs/opd.js" />


<his:javascript src="/hisglobal/js/DateValidation.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>



<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>All slot Information</title>
<script type="text/javascript">

 function validateIt()
 { 
	
 	if(document.forms[0].aptDate.value=="")	
 	{
 		alert("Please select the date!"); 	
 		document.forms[0].aptDate.focus(); 		
 	} 	 	
   	if(document.forms[0].slotCode)   	 	   	
  	{	 	
  		if(document.forms[0].slotCode.value=="")
	       	alert("Slot field not found or is empty!");  		
  		else
  			return true;  			    				
 	} 	
 	return false;	
 }
 
 
 function getSlotInfo(rdoObj,slotCode,slotStartTime,slotEndTime,aptActCode)
{
	document.forms[0].slotCode.value=slotCode;
	document.forms[0].selSlotTime.value=slotStartTime + "-" + slotEndTime;
	document.forms[0].aptActCode.value=aptActCode;	
}

function sendData(objDate)
{	
	var url;
	if(objDate.value=="")
 	 {
 	 	alert("Please enter date!");
 	 	return;
 	 }	
 	 if(sysAfter(objDate,"Cannot Give Appointment!\n Date is smaller then current date"))
 	 {
 	 	if(document.forms[0].slotCode)
 	 		document.forms[0].slotCode.value="";
 	 	url='/HISClinical/opd/opdNextVisitAppointment.cnt';
		url +='?hmode=GETSLOTDTL&actCode=' + document.forms[0].actCode.value;
		url += '&aptDate='+document.forms[0].aptDate.value ;	
		url +='&para1='+ document.forms[0].para1.value;
		url +=' &para2='+ document.forms[0].para2.value;	
		httpRequest("GET",url,true);
 	 }
 	 else
 	 {
 	 	var id=document.getElementById("DIV_ID_INSERT_ROWS");
 	 		id.innerHTML=""; 	 		
 	 }	
}
	
	
function handleResponse(){
 
 if(request.readyState == 4){
 if(request.status == 200){
 		//alert("inside handle response")

 // alert(request.responseText);		
	 var id=document.getElementById("DIV_ID_INSERT_ROWS");
	 //alert("id= "+id);
	 //alert("form value= "+form);
	 var str=request.responseText;
	 //alert("response=====" + str);	 
	 if(str!=null || str!=""){
	   	id.innerHTML=str;
	  //}
	  //else{
	   //id.innerHTML="<font color='FF0000' face='Verdana, Arial, Helvetica, sans-serif'><BLINK> " + Details Not Found+ " </BLINK> </font>";
   	 }
	   //	 alert("check"+form.innerHTML);
	 // alert(request.responseText);	
	 } else {
	 alert("A problem occurred with communicating between "+
	 "the XMLHttpRequest object and the server program.");
	 }
	 }//end outer if
}	

</script>
</head>
<body>

<table width="100%" border="0">
	<tr>
		<td>
		<%
					String systemDate = (String) session
					.getAttribute(Config.SYSDATE);
			String slotDate = (String) request
					.getAttribute(OpdConfig.APPOINTMENT_DATE);
			System.out.println("slotDate" + slotDate);			
		%> <his:TitleTag
			name="OPD Next Visit Appointment">
			<b><font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="date" /> <bean:message key="and" /> <bean:message key="time" />
			<bean:write name="<%=Config.SYSDATE%>" /> </font></b>
		</his:TitleTag> <jsp:include page="/patientDetail.cnt" flush="true" />
		<logic:empty name="OpdNextVisitAppointmentFB" property="aptReqNo">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="3"><his:TitleTag name="Appointment Details"></his:TitleTag></td>
			</tr>
			<tr>
				<td colspan="3" height="1" style="background-color: #ffffff;" />
			</tr>
			<tr>
				<td>
				<div style='background-color: #9090b0; color: #fff'>
				<div
					style='background: url(/HISClinical/images/rnd-trans-9090b0-RT.jpg) no-repeat top right;'>
				<img src="/HISClinical/images/rnd-trans-9090b0-LT.jpg" alt=''
					style='border: none; display: block !important;' /></div>
				<div align="center">				
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td style="background-color: #f5f3f3" width="100%">
						<table width="100%">
							<tr>
								<td class='tdfonthead' width="25%" height="10%">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
									key="Apt_dateOfApt" /></font></div>
								</td>
								<td class='tdfont' width="25%">
								<his:date name="aptDate" dateFormate="%d-%b-%Y" value="<%=slotDate%>" onchange="sendData(this);"></his:date>
								</td>
								<td class='tdfonthead' width="25%" height="10%">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
									key="Apt_activity" /></font></div>
								</td>
								<td class='tdfont' width="25%">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">
									<%=OpdConfig.ACTIVITY_NAME_OPD_CONSULTATION%> </font>
								</td>
							</tr>
							<tr>
								<td class='tdfonthead' height='10%'>
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
									key="department" /></font></div>
								</td>
								<td class='tdfont'>
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">
									<%=session
									.getAttribute(OpdConfig.DEPARTMENT_UNITNAME_APPOINTMENT)%>
								</font>
								</td>
								<td class='tdfonthead' height='10%'></td>
								<td class='tdfont'></td>

							</tr>								
							<tr>
								<td colspan="4">
									<div id="DIV_ID_INSERT_ROWS">
										<logic:notEmpty name="<%=OpdConfig.APPOINTMENT_ALL_SLOTDTL%>">
										<table width="100%">
											<tr valign="top">
												<td class='tdfonthead'></td>
												<td class='tdfonthead'>
												<div align="center"><font color="#000000" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"><bean:message
													key="Apt_slotNo" /></font></div>
												</td>
												<td class='tdfonthead'>
												<div align="center"><font color="#000000" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"><bean:message
													key="Apt_slotTime" /></font></div>
												</td>
												<td class='tdfonthead'>
												<div align="center"><font color="#000000" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"><bean:message
													key="Apt_No.Appointment_Left" /></font></div>
												</td>
												<td class='tdfonthead'>
												<div align="center"><font color="#000000" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"><bean:message
													key="Apt_No.Appointment_Given" /></font></div>
												</td>
											</tr>
											<logic:iterate id="list" name="<%=OpdConfig.APPOINTMENT_ALL_SLOTDTL%>">
												<tr valign="top">
													<td class='tdfont'>
													<div align="center"><input type="radio"
														style='cursor: pointer' name="rdoSlot"
														onclick="getSlotInfo(this,'<bean:write name='list' property='slotCode'/>','<bean:write name='list' property='slotStartTime'/>','<bean:write name='list' property='slotEndTime'/>','<bean:write name='list' property='aptActCode'/>')">
													</div>
													</td>
													<td class='tdfont'>
													<div align="center"><font color="#000000" size="2"
														face="Verdana, Arial, Helvetica, sans-serif"><bean:write
														name='list' property='slotCode' /></font></div>
													</td>
													<td class='tdfont'>
													<div align="center"><font color="#000000" size="2"
														face="Verdana, Arial, Helvetica, sans-serif"><bean:write
														name='list' property='slotStartTime' />-<bean:write
														name='list' property='slotEndTime' /></font></div>
													</td>
													<td class='tdfont'>
													<div align="center"><font color="#000000" size="2"
														face="Verdana, Arial, Helvetica, sans-serif"><bean:write
														name='list' property='aptPersonLeft' /></font></div>
													</td>
													<td class='tdfont'>
													<div align="center"><font color="#000000" size="2"
														face="Verdana, Arial, Helvetica, sans-serif"><bean:write
														name='list' property='totAptPersons' /></font></div>
													</td>
												</tr>
											</logic:iterate>
											<tr valign="top">				
												<td colspan='5'>
													<table width="100%">
														<tr valign="top">
															<td class='tdfonthead'>
															<div align="right"><font color="#000000" size="2"
																face="Verdana, Arial, Helvetica, sans-serif"><bean:message
																key="Apt_appointment" />&nbsp;<bean:message key="Apt_slot" /></font></div>
															</td>
															<td class='tdfonthead'><div align="left"><html:text
																name="OpdNextVisitAppointmentFB" styleClass="TEXTBOX1"
																readonly="true" property="slotCode" /></div></td>
															<td class='tdfonthead'>
															<div align="right"><font color="#000000" size="2"
																face="Verdana, Arial, Helvetica, sans-serif"><bean:message
																key="Apt_slotTime" /></font></div>
															</td>
															<td class='tdfonthead'><div align="left"><html:text
																name="OpdNextVisitAppointmentFB" readonly="true"
																property="selSlotTime" /></div></td>
														</tr>														
													</table>
												</td>
											</tr>	
										</table>
									</logic:notEmpty>		
									
									</div>	
								</td>
							</tr>									
							<tr>
								<td class='tdfonthead' height='10%'>
									<div align="right"><font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="remarks" /></font></div>
								</td>
								 <td colspan='3' class='tdfont'>
								 	<textarea style='width: 400px' name="remark"></textarea>
								 </td>
							</tr>															
						</table>
						</td>
					</tr>
				</table>				
				</div>
				<div
					style="background: url(/HISClinical/images/rnd-trans-9090b0-RB.jpg) no-repeat top right;">
				<img src="/HISClinical/images/rnd-trans-9090b0-LB.jpg" alt=''
					style='border: none; display: block !important;' /></div>
				</div>
				</td>
			</tr>
		</table>
		</logic:empty>
		<!-- Button toolbar Started -->
		<table cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td width="5"><img
						src="/HISClinical/images/rnd-trans-ffffff-bdr.gif"></td>
					<td
						style="background: transparent url(/HISClinical/images/border-top.gif) repeat-x scroll center top; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td width="5"><img
						src="/HISClinical/images/rnd-trans-ffffff-bdr-RT.gif"></td>
				</tr>
				<tr>
					<td
						style="background: transparent url(/HISClinical/images/border-left.gif) repeat-y scroll left center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td style="background-color: rgb(255, 255, 255);" width="100%">
					<div align="center"><!-- Button toolbar Finished -->


					<div align="center">
					<logic:empty name="OpdNextVisitAppointmentFB" property="aptReqNo" >
						<img class='button'	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer"	 tabindex='2' onclick="submitFormOnValidate(validateIt(),'GIVENAPPOINTMENT');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'GIVENAPPOINTMENT');")>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'	style="cursor: pointer"  tabindex="1" onclick="submitToDesk('NEW','NEW')"	 onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					</logic:empty>
					 <logic:notEmpty name="OpdNextVisitAppointmentFB" property="aptReqNo">
						<img class="button" src='<his:path src="/hisglobal/images/ok.png"/>'	style="cursor: pointer"  tabindex="1"   onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">	 
					 </logic:notEmpty>
					</div>
					</div>
					</td>
					<td
						style="background: transparent url(/HISClinical/hisglobal/images/border-right.gif) repeat-y scroll right center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
				</tr>
				<tr>
					<td tabindex="1" width="5"><img
						src="/HISClinical/images/rnd-trans-ffffff-bdr-LB.gif"></td>
					<td
						style="background: transparent url(/HISClinical/hisglobal/images/border-bottom.gif) repeat-x scroll center bottom; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td tabindex="1" width="5"><img
						src="/HISClinical/images/rnd-trans-ffffff-bdr-RB.gif"></td>
				</tr>
			</tbody>
		</table>
		<table bgcolor="white" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		<td>
		<logic:notEmpty name="OpdNextVisitAppointmentFB" property="msg">
			<div align="center"><font color="red" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
				name="OpdNextVisitAppointmentFB" property="msg" /> </font></div>
		</logic:notEmpty> <logic:notEmpty name="OpdNextVisitAppointmentFB" property="aptReqNo">
			<div align="center"><font color="red" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> AptReqNo- <bean:write
				name="OpdNextVisitAppointmentFB" property="aptReqNo" /> Date-<bean:write
				name="OpdNextVisitAppointmentFB" property="aptDate" /> Time-<bean:write
				name="OpdNextVisitAppointmentFB" property="selSlotTime" /> </font></div>
		</logic:notEmpty> <html:hidden name="OpdNextVisitAppointmentFB" property="actCode" />
		</td>
		</tr>
		</table>
	</td>
	</tr>
</table>
		<html:hidden name="OpdNextVisitAppointmentFB" property="para1" />
		<html:hidden name="OpdNextVisitAppointmentFB" property="para2" />
		<html:hidden name="OpdNextVisitAppointmentFB" property="hmode" />
		<html:hidden name="OpdNextVisitAppointmentFB" property="patCrNo" />
		<html:hidden name="OpdNextVisitAppointmentFB" property="level" />
		<html:hidden name="OpdNextVisitAppointmentFB"property="tempActCode" /> 
		<html:hidden name="OpdNextVisitAppointmentFB" property="tempPara1" /> 
		<html:hidden name="OpdNextVisitAppointmentFB" property="aptActCode" /> 
		<html:hidden name="OpdNextVisitAppointmentFB" property="aptReqNo" /> 
		<html:hidden name="OpdNextVisitAppointmentFB" property="currentDate"	value="<%=systemDate %>" />
</body>
</html>
