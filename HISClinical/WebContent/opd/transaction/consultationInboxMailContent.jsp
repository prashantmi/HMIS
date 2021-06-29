<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/opd/opdJs/opd.js"/>

<script type="text/javascript">

function displayProfileFile()
{
	var profileId=document.getElementsByName("profileId")[0].value;
	if(profileId!="-1")
	{
		var displayFileServletURL = "/HISClinical/DisplayFileServlet?" + "<%=ServletsUtilityConfig.FILE_PATH_WINDOWS%>" + "=" + "<%=Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS%>" 
								+ "&" + "<%=ServletsUtilityConfig.FILE_PATH_LINUX%>" + "=" + "<%=Config.PATIENT_PROFILE_STORAGE_PATH_LINUX%>"
								+ "&" + "<%=ServletsUtilityConfig.FILE_NAME%>" + "=" +profileId+"<%=Config.PATIENT_PROFILE_FILE_STORAGE_EXT%>";
		displayFileServletURL = "/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPRINTPROFILE&profileId=" +profileId;
								
		// alert("profileId "+displayFileServletURL);
		window.open(createFHashAjaxQuery(displayFileServletURL),'DisplayFile','status=yes,scrollbars=yes,height='+screen.availHeight+',width='+screen.availWidth+ ',left=10,top=10,,dependent=yes,resizable=yes');
	}
}

function submitToReply(hmode){
	submitForm21(hmode);
}
function submitForm21(mode)
{
    
  //  alert("submitform...."+mode);
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
     document.getElementsByName("hmode")[0].value=mode;
    // doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
     //alert("hmode "+document.getElementsByName("hmode")[0].value);  
    
	 document.forms[0].submit();
	 
}


function submitToRetreiveMail(hmode,mailId)
{
	//alert("mail id"+mailId);
	//alert("mode "+document.getElementsByName("mode")[0].value)
	document.getElementsByName("mailRequestId")[0].value=mailId;
	submitForm(hmode);
	//document.getElementsByName("hmode")[0].value=hmode;
	//alert("hmode== "+document.getElementsByName("hmode")[0].value);
	//document.forms[0].submitForm();
	//alert("form action "+document.forms[0].action)
	
	//submitToDesk(document.getElementsByName("mode")[0].value,document.getElementsByName("hmode")[0].value);
}


callThisOnload()
{
 //alert("asasas "+document.getElementsByName("patCrNo").value);
	// alert("yasjhjkshdkjhskdhskdh=== "+document.getElementsByName("selectedPatCrNo").value)
	// alert("ackStatus "+document.getElementsByName("toDoctor")[0].value)
}
function getMail()
{
	document.getElementsByName("mailPatCrNo")[0].value=document.getElementsByName("selectedPatCrNo")[0].value;
	
	return true;
}
</script>
<his:TitleTag>
		<his:name>
			<bean:message key="mail" />
		</his:name>
	</his:TitleTag>

<his:statusTransactionInProcess>
	<%//String crNo=(String)request.getAttribute("patCrNo");------
  	//String url="/HISClinical/registration/patientDetail.cnt?patCrNo="+crNo;-------
	%>
	<%--<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>--%>

<jsp:include page="/opd/transaction/opd_consultationinbox_patdtl_tile.jsp"></jsp:include>

<his:ContentTag>
	<table width="52%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="20%"  class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="from"/></b>
					</font>
				</div>
			</td>
			<td width="80%"  class="tdfont">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:write name="ConsultationInboxFB" property="fromDoctor"/></b>
					</font>
				</div>
			</td>
	  	<%--	<td width="20%"  class="tdfonthead"  >
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="recevied"/></b> 
				</font>
				</div>
	  		</td>--%>
	  	<%--	<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:write name="ConsultationInboxFB" property="sentDate"/> </b>
				</font>
				</div>
	  		</td> --%>
		</tr>
	</table>
	<table width="102.5%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="10%"  class="tdfonthead">
				<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="subject"/></b>
					</font>
				</div>
	  		</td>
	  		<td width="40%"  class="tdfont">
				<div align="left">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<html:text name="ConsultationInboxFB" property="subject" readonly="true" size="50"/> 
					</font>
				</div>
	  		</td>
	  		<logic:notEqual name="ConsultationInboxFB" property="mailType"  value="<%=OpdConfig.OPD_COSULTANT_ACKNOWLEDGEMENT_MAIL %>">
	  			<td width="30%"  class="tdfonthead" nowrap="nowrap">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="profileHeader"/></b>
						</font>
					</div>
	  			</td>
		  		<td width="23%"  class="tdfont" nowrap="nowrap">
					<div align="left">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<html:select name="ConsultationInboxFB" property="profileId" onchange="displayProfileFile();" tabindex="1" >
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.PROFILE_LIST_COMBO%>" >
									<html:options collection ="<%=OpdConfig.PROFILE_LIST_COMBO%>" property = "value" labelProperty = "label"/>
								</logic:present>
							</html:select>
						</font>
					</div>
	  			</td>
	  		</logic:notEqual>
		</tr>
	</table>
	<table width="81%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="35%">
			</td>
			<td width="65%"  class="tdfont"   >
				<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<html:textarea name="ConsultationInboxFB" property="content" tabindex="1" cols="120" rows="15" readonly="true"> 
						</html:textarea>
					</font>
				</div>
	  		</td>
		</tr>
	</table>
	<logic:equal name="ConsultationInboxFB" property="mailType"  value="<%=OpdConfig.OPD_COSULTANT_ACKNOWLEDGEMENT_MAIL %>">
		<table width="80%" cellspacing="1" cellpadding="0">
			<logic:notPresent name="<%=OpdConfig.ACKNOWLEDGED_MAIL_DETAIL %>">
			<tr>
				<td colspan="2" width="100%" class="tdfont">
					<div align="left">
						<bean:define id="mailId" name="ConsultationInboxFB" property="mailParentRequestId" ></bean:define>
						<a style="cursor: pointer;" onclick="submitToRetreiveMail('GETPREMAIL','<%=mailId %>')" ><b><u>Show Original Mail Details</u></b></a>
					</div>
				</td>
			</tr>
			</logic:notPresent>
			<logic:present name="<%=OpdConfig.ACKNOWLEDGED_MAIL_DETAIL %>">
			<tr>
				<td width="100%"  class="tdfonthead" colspan="2">
					<div align="left">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="originalMessage"/>
						</font>
					</div>
		  		</td>
		  	</tr>
			<bean:define id="mailDetailsId"  name="<%=OpdConfig.ACKNOWLEDGED_MAIL_DETAIL %>"  ></bean:define>
			<tr>
				<td width="10%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="to"/>:</b>
						</font>
					</div>
		  		</td>
				<td width="90%"  class="tdfont">
					<div align="left">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="mailDetailsId" property="toDoctor" />
						</font>
					</div>
	  			</td>
	  		</tr>
	  		<tr>
		  		<td width="10%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="sent"/>:</b>
						</font>
					</div>
		  		</td>
		  		<td width="90%"  class="tdfont">
					<div align="left">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="mailDetailsId" property="sentDate" />
						</font>
					</div>
	  			</td>
	  		</tr>
			<tr>
				<td width="10%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="subject"/>:</b>
						</font>
					</div>
	  			</td>
				<td width="90%"  class="tdfont" >
					<div align="left">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="mailDetailsId" property="subject" />
						</font>
					</div>
	  			</td>
			</tr>
			<tr>
				<td width="100%"  class="tdfonthead" colspan="2">
				</td>
			</tr>  
			<tr >
				<td width="100%"  class="tdfont" colspan="2"  >
					<div align="center" >	           
						<html:textarea name="mailDetailsId" property="content" tabindex="1" cols="120" rows="15" readonly="true"> 
						</html:textarea>
					</div>
				</td>
			</tr>  
			</logic:present> 
		</table>
	</logic:equal>
</his:ContentTag>
 
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
		  <img class='button' src='<his:path src="/hisglobal/images/inbox.bmp"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(getMail(),'NEW');" onkeypress="if(event.keyCode==13)submitFormOnValidate( getMail(),'NEW');")>
		  <logic:notEqual name="ConsultationInboxFB" property="mailType" value="<%=OpdConfig.OPD_COSULTANT_ACKNOWLEDGEMENT_MAIL %>">
		  <img class="button" src='<his:path src="/hisglobal/images/reply.bmp"/>'  style=cursor:pointer tabindex="1" onclick ="submitToReply('REPLY')" onkeypress="if(event.keyCode==13) submitToReply('REPLY')">
          <img class="button" src='<his:path src="/hisglobal/images/forward.bmp"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm21('FORWARD')" onkeypress="if(event.keyCode==13) submitForm('FORWARD');">
          </logic:notEqual>
         <%--  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitFormOnValidate(getMail(),'NEW')" onkeypress="if(event.keyCode==13) submitFormOnValidate(getMail(),'NEW')"> --%>
</his:ButtonToolBarTag>
<html:hidden name="ConsultationInboxFB" property="hmode"/>
<html:hidden name="ConsultationInboxFB" property="mailPatCrNo"/>
<html:hidden name="ConsultationInboxFB" property="selectedPatCrNo"/>
<html:hidden name="ConsultationInboxFB" property="mailRequestId"/>
<html:hidden name="ConsultationInboxFB" property="mailParentRequestId"/>
<html:hidden name="ConsultationInboxFB" property="toDoctorCode"/>
<html:hidden name="ConsultationInboxFB" property="toDoctorSeatId"/>
<html:hidden name="ConsultationInboxFB" property="fromDoctorCode"/>
<html:hidden name="ConsultationInboxFB" property="fromDoctor"/>
<html:hidden name="ConsultationInboxFB" property="fromDoctorSeatId"/>
<html:hidden name="ConsultationInboxFB" property="toDoctor"/>
<html:hidden name="ConsultationInboxFB" property="mailType"/>
	