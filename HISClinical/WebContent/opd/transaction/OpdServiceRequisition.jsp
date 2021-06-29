<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>

<his:javascript src="/appointment/js/appointment.js" />

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

<%@page import="servicearea.ServiceAreaConfig"%>
<%@page import="opd.master.controller.fb.TemplateParameterMasterFB"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>
<script type="text/javascript">

function sendData(obj)
{	
	var url;	
		if(obj.value=="-1")
		 return;	
 	 	url='/HISClinical/opd/OpdServiceRequisition.cnt?';
 	 	if(obj.name=="deptCode")
 	 	{
 	 		url+='hmode=GETCOMBOOPTIONS&deptCode=' + obj.value;
 	 		url+='&divId=OPTIONS_SERVICE_AREA_CODE'; 	 		 		
 	 	}
 	 	if(obj.name=="serviceAreaCode")
 	 	{ 	 		
 	 		url+='hmode=GETCOMBOOPTIONS&serviceAreaCode='+ obj.value;
 	 		url+='&divId=OPTIONS_SERVICE_CODE';
 	 	} 
 	 	if(obj.name=='serviceCode')
 	 	{
 	 		document.forms[0].hmode.value="GETTEMPLATEDTL"; 	 		
 	 		document.forms[0].submit();
 	 	} 	 	
 	 	else
 	 	{
 	 		//alert(url);	 	 		 			 	
			httpRequest("GET",url,true);	 	
}		}
	
	
function handleResponse(){
 
 if(request.readyState == 4){
 if(request.status == 200){
 		//alert("inside handle response")

  //alert(request.responseText);	
 		
	 var id;	 
	 //alert("form value= "+form);
	 var str=request.responseText;	 	 
	 //alert("response=====" + str);	 
	 if(str!=null || str!=""){
	  {
	  	var strResult=new Array();
	 	strResult.length=2;
	 	strResult=str.split("^",2);	 	
	 	id=document.getElementById(strResult[0]);
	 	//alert("id= "+id);
	 	id.innerHTML=strResult[1];
	  } 	
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
function validateIt()
{
 if(document.forms[0].serviceDate.value=="")
 {
 	alert("Please enter date for the service!");
 	document.forms[0].serviceDate.focus();
 	return false; 	
 }
 if(sysAfter(document.forms[0].serviceDate,'Date is smaller then current date')==false)
 {
 	document.forms[0].serviceDate.focus();
    return false;
 }
 if(document.forms[0].deptCode.value=="-1")
 {
 	alert("Please enter department!");
 	document.forms[0].deptCode.focus();
 	return false; 	
 }
 if(document.forms[0].serviceAreaCode.value=="-1")
 {
 	alert("Please enter service area!");
 	document.forms[0].serviceAreaCode.focus();
 	return false; 	
 }
 if(document.forms[0].serviceCode.value=="-1")
 {
 	alert("Please enter service!");
 	document.forms[0].serviceCode.focus();
 	return false; 	
 }
 return true;
}


</script>
</head>
<body>

<table width="100%" border="0">
	<tr>
		<td>
		 <his:TitleTag
			name="Order For Service">
			<b><font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="date" /> <bean:message key="and" /> <bean:message key="time" />
			<bean:write name="<%=Config.SYSDATE%>" /> </font></b>
		</his:TitleTag> <jsp:include page="/patientDetail.cnt" flush="true" />
		<!----------------raised order for service----------------------------------------------- -->
		<logic:notEmpty name="<%=OpdConfig.SERVICE_ALL_SERVICE_REQ_DTL_BY_CRNO%>">
		
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="3"><his:TitleTag name="Raised Service Details"></his:TitleTag></td>
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
									<td class='tdfonthead' width="15%" height="10%">
										<div align="center"><font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message	key="serviceReqNo" /></b></font></div>
									</td>
									<td class='tdfonthead' width="15%" height="10%">
										<div align="center"><font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message	key="serv_date" /></b></font></div>
									</td>							
									<td class='tdfonthead' width="20%" height="10%">
										<div align="center"><font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message	key="service" /></b></font></div>
									</td>
									<td class='tdfonthead' width="20%" height="10%">
										<div align="center"><font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message	key="serviceArea" /></b></font></div>
									</td>								
									<td class='tdfonthead' width="10%" height="10%">
										<div align="center"><font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message	key="Sitting" /></b></font></div>
									</td>
									<td class='tdfonthead' width="20%" height="10%">
										<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
										<bean:message key="reqStatus" /></b></font></div>
									</td>
								</tr>
							<logic:iterate id="list" name="<%=OpdConfig.SERVICE_ALL_SERVICE_REQ_DTL_BY_CRNO%>">		
							<tr>
								<td class='tdfont' width="10%" height="10%" ><bean:write name='list' property='serviceReqNo'/></td>
								<td class='tdfont' width="10%" height="10%" ><bean:write name='list' property='serviceDate'/></td>
								<td class='tdfont' width="10%" height="10%" ><bean:write name='list' property='serviceName'/></td>
								<td class='tdfont' width="10%" height="10%" ><bean:write name='list' property='serviceAreaName'/></td>
								<td class='tdfont' width="10%" height="10%" ><bean:write name='list' property='noOfSitting'/></td>
								<td class='tdfont' width="10%" height="10%" >
								<logic:equal name='list' property='req_status' value="<%=OpdConfig.SERVICE_STATUS_NOT_VISITED %>">
									<bean:message key="reqStatusNotVisited" />
								</logic:equal>
								<logic:equal name='list' property='req_status' value="<%=OpdConfig.SERVICE_STATUS_VISITED%>">
									<bean:message key="reqStatusVisited" />
								</logic:equal>
								<logic:equal name='list' property='req_status' value="<%=OpdConfig.SERVICE_STATUS_SCHEDULE_PASSED%>">
									<bean:message key="reqStatusSchedulePassed" />
								</logic:equal>
								</td>
							</tr>
							</logic:iterate>							
						</table>
						</td>
					</tr>	
			</table>
		</table>
		</logic:notEmpty>
		
		<!--  -------------------end of raised order for service------------------------------------------ -->
		<!----------------New service Requisition----------------------------------------------- -->
			<%String serviceDate = (String) session.getAttribute(OpdConfig.SERVICE_REQ_DATE);
			System.out.println("serviceDate----" +serviceDate);
			%>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="3"><his:TitleTag name="New Service Details"></his:TitleTag></td>
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
									key="serv_date" /></font></div>
								</td>
								<td class='tdfont' width="25%">
								<his:date name="serviceDate" dateFormate="%d-%b-%Y" value="<%=serviceDate%>" ></his:date>
								<td class='tdfonthead' width="25%" height="10%">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="department" /></font></div>
								</td>
								<td class='tdfont' width="25%">
								<html:select name="OpdServiceRequisitionFB" style="width:150px;"  property="deptCode" onchange="sendData(this)">
								 <html:option value="-1">&lt;-Select Value-&gt;</html:option>
								 <logic:present name="<%=ServiceAreaConfig.OPTION_DEPARTMENT%>" >								 
								 <html:options collection="<%=ServiceAreaConfig.OPTION_DEPARTMENT%>" property="value" labelProperty="label" />
								 </logic:present> 
								</html:select>							
								</td>
							</tr>
							
							<tr>														
								<td class='tdfonthead' width="25%" height="10%">
								<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="serviceArea" /></font></div>
								</td>
								<td class='tdfont' width="25%">
									<div id="OPTIONS_SERVICE_AREA_CODE">									
									<html:select name="OpdServiceRequisitionFB" style="width:150px;"  property="serviceAreaCode" onchange="sendData(this)">
									 	<html:option value="-1">&lt;-Select Value-&gt;</html:option>	
									 	<logic:notEmpty name="<%=ServiceAreaConfig.OPTION_SERVICEAREA %>">								 	
									 	<html:options collection="<%=ServiceAreaConfig.OPTION_SERVICEAREA %>" property="value" labelProperty="label" />
									 	</logic:notEmpty>									 								 										 										 	 
									</html:select>
									</div>
								<td class='tdfonthead' width="25%" height="10%">
								<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="service" /></font></div>
								</td>
								<td class='tdfont' width="25%">
								 <div id="OPTIONS_SERVICE_CODE">								 	
									<html:select name="OpdServiceRequisitionFB"  style="width:150px;" property="serviceCode">									
									 <html:option value="-1">&lt;-Select Value-&gt;</html:option>
									 <logic:notEmpty name="<%=ServiceAreaConfig.OPTION_SERVICE%>">								 	
									 	<html:options collection="<%=ServiceAreaConfig.OPTION_SERVICE%>" property="value" labelProperty="label" />
									 	</logic:notEmpty>			
									</html:select>					
								</div>		
								</td>																
							</tr>						
											
							
							<tr>
								<td class='tdfonthead' width="25%" height="10%">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="NoOfSitting" /></font></div>
								</td>
								<td class='tdfont' width="25%">
									<html:text name="OpdServiceRequisitionFB" property="noOfSitting" maxlength="2" onkeypress="return (NumOnly(event))"  /></td>
								<td class='tdfonthead' width="25%" height="10%"></td>
								<td class='tdfont' width="25%"></td>
							</tr>
							<tr>
								<td class='tdfonthead' height='10%'>
									<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="Apt_remarks"/></font></div>			
					 			</td>					
								<td  colspan='3' class='tdfont'><textarea style='width:400px' name="remark" ></textarea>  </td>
							</tr>
						</table>
						</td>
					</tr>	
			</table>
		</table>
		</td>
		</tr>
	
	<div style="background: url(/HISClinical/images/rnd-trans-9090b0-RB.jpg) no-repeat top right;">
			<img src="/HISClinical/images/rnd-trans-9090b0-LB.jpg" alt=''	style='border: none; display: block !important;' /></div>	
</table>
<!----------------End of New service Requisition----------------------------------------------- -->
		<!-- Button toolbar Started -->
		<table cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td width="5"><img	src="/HISClinical/images/rnd-trans-ffffff-bdr.gif"></td>
					<td
						style="background: transparent url(/HISClinical/images/border-top.gif) repeat-x scroll center top; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td width="5"><img	src="/HISClinical/images/rnd-trans-ffffff-bdr-RT.gif"></td>
				</tr>
				<tr>
					<td
						style="background: transparent url(/HISClinical/images/border-left.gif) repeat-y scroll left center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td style="background-color: rgb(255, 255, 255);" width="100%">
					<div align="center"><!-- Button toolbar Finished -->


					<div align="center">		
						<img class='button'	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer"	 tabindex='2' onclick="submitFormOnValidate(validateIt(),'SAVESERVICEREQUISITION');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'GIVENAPPOINTMENT');")>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'	style="cursor: pointer"  tabindex="1" onclick="submitToDesk('NEW','NEW')"	 onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">					
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
		<logic:notEmpty name="OpdServiceRequisitionFB" property="msg">
			<div align="center"><font color="red" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
				name="OpdServiceRequisitionFB" property="msg" /> </font></div>
		</logic:notEmpty> <logic:notEmpty name="OpdServiceRequisitionFB" property="serviceReqNo">
			<div align="center"><font color="red" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> serviceReqNo- <bean:write
				name="OpdServiceRequisitionFB" property="serviceReqNo" /></font></div>
		</logic:notEmpty>
		</td>
		</tr>
		</table>		
		<%
		TemplateParameterMasterFB fb=(TemplateParameterMasterFB)WebUTIL.getSession(request).getAttribute(ServiceAreaConfig.SERVICE_TEMPLATE_BEAN);
		%>
		<his:OpdTemplateTag name="<%=fb%>" ></his:OpdTemplateTag>
		
		
		
		<html:hidden name="OpdServiceRequisitionFB" property="hmode"/>
		<html:hidden name="OpdServiceRequisitionFB" property="patCrNo"/>
		<html:hidden name="OpdServiceRequisitionFB" property="divId"/>
</body>
</html>
 