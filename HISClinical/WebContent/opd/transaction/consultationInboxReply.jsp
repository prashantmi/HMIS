<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitToReply(hmode){
	submitForm(hmode);
}

function submitToRetreiveMail(hmode)
{
	//alert("mail id"+mailId);
	//alert("mode "+document.getElementsByName("mode")[0].value)
	//document.getElementsByName("mailRequestId")[0].value=mailId;
	document.getElementsByName("hmode")[0].value=hmode;
	//alert("hmode== "+document.getElementsByName("hmode")[0].value);
	// document.forms[0].submitForm();
	//alert("form action "+document.forms[0].action)
	
	submitToDesk(document.getElementsByName("mode")[0].value,document.getElementsByName("hmode")[0].value);
}

function getMail()
{
	document.getElementsByName("patCrNo")[0].value=document.getElementsByName("selectedPatCrNo")[0].value;
	return true;
}
function validateIt()
{
	var flag=false;
	flag=validateTestAreaLenth('1000');
	return flag;
}
</script>
<his:TitleTag>
		<his:name>
			<bean:message key="replyMail" />
		</his:name>
	</his:TitleTag>

<his:statusTransactionInProcess>

<%--<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>--%>
<jsp:include page="/opd/transaction/opd_consultationinbox_patdtl_tile.jsp"></jsp:include>
 	
<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
 		<tr>
 			<td width="10%"  class="tdfonthead">
				<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="to"/></b>
					</font>
				</div>
	  		</td>
	  		<td width="90%"  class="tdfont">
				<div align="left">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:write name="ConsultationInboxFB" property="toDoctor"/> </b>
					</font>
				</div>
	  		</td>
		</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="10%"  class="tdfonthead">
				<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="subject"/></b>
					</font>
				</div>
	  		</td>
	  		<td width="90%"  class="tdfont">
				<div align="left">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<html:text name="ConsultationInboxFB" property="subject" size="50" 
							maxlength="50" onkeypress="return notSpecChar(this,event)" /> 
					</font>
				</div>
	  		</td>
		</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="20%"  class="tdfont">
				<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<html:textarea name="ConsultationInboxFB" property="content" tabindex="1" cols="120" rows="15" 
							onkeypress="return (validateTextArea(event,this,'1000') && notSpecChar(this,event))" >
						</html:textarea>
					</font>
				</div>
	  		</td>
		</tr>
	</table>
</his:ContentTag>
</his:statusTransactionInProcess>
<his:ButtonToolBarTag>
		  <img class='button' src='<his:path src="/hisglobal/images/GoNew.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(validateIt(),'SEND');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SEND');")>
		 <img class='button' src='<his:path src="/hisglobal/images/inbox.bmp"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate( getMail(),'NEW');" onkeypress="if(event.keyCode==13)submitFormOnValidate( getMail(),'NEW');")>
		  <%--<img class="button" src='<his:path src="/hisglobal/images/reply.bmp"/>'  style=cursor:pointer tabindex="1" onclick ="submitToReply('REPLY')" onkeypress="if(event.keyCode==13) submitToReply('REPLY')"> --%>
          <img class="button" src='<his:path src="/hisglobal/images/forward.bmp"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('FORWARD')" onkeypress="if(event.keyCode==13) submitForm('FORWARD');">
           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToRetreiveMail('GETMAIL')" onkeypress="if(event.keyCode==13) submitToRetreiveMail('GETMAIL')">
</his:ButtonToolBarTag>

<html:hidden name="ConsultationInboxFB" property="hmode"/>
<html:hidden name="ConsultationInboxFB" property="mailPatCrNo"/>
<html:hidden name="ConsultationInboxFB" property="mailRequestId"/>
<html:hidden name="ConsultationInboxFB" property="toDoctorCode"/>
<html:hidden name="ConsultationInboxFB" property="toDoctorSeatId"/>
<html:hidden name="ConsultationInboxFB" property="selectedPatCrNo"/>
<html:hidden name="ConsultationInboxFB" property="fromDoctorCode"/>
<html:hidden name="ConsultationInboxFB" property="fromDoctor"/>
<html:hidden name="ConsultationInboxFB" property="fromDoctorSeatId"/>
<html:hidden name="ConsultationInboxFB" property="toDoctor"/>
