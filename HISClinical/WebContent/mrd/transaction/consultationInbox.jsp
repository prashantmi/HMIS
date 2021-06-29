<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>
<%@page import="opd.transaction.controller.fb.ConsultationInboxFB"%>

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
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitToRetreiveMail(hmode,mailId,crNo)
{
	//alert("mail id"+mailId);
	//alert("mode "+document.getElementsByName("mode")[0].value)
	document.getElementsByName("mailRequestId")[0].value=mailId;
	document.getElementsByName("hmode")[0].value='GETMAIL';
	document.getElementsByName("patCrNo")[0].value=crNo;
	//alert("hmode== "+document.getElementsByName("hmode")[0].value);
	// document.forms[0].submitForm();
	//alert("form action "+document.forms[0].action)
	
	submitToDesk(document.getElementsByName("mode")[0].value,document.getElementsByName("hmode")[0].value);
}

function submitToDelete(){
	
	var selectedMailIdArray=document.getElementsByName("selectedMailId");
	var i=0;
	var j=0;
	while(i<selectedMailIdArray.length){
	if(selectedMailIdArray[i].checked){
		//alert("hello");
		j=j+1;
		}
		i=i+1;
	}
	
	if(parseInt(j)<1){
		alert("Please Select Mail");
	}
	else{
		  submitForm("DELETE");
	}
	// alert("count== "+j)
	// submitForm("DELETE");
}

function callThisOnload()
{
	//alert("zdzxzxzxzx "+document.getElementsByName("selectedPatCrNo")[0].value)
}
</script>
<his:TransactionContainer>
<his:TitleTag>
		<his:name>
			<bean:message key="opdCosultationInbox" />
		</his:name>
	</his:TitleTag>
	
<his:statusTransactionInProcess>
 <his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">
 	<tr>
 		<td width="5%"  class="tdfont">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="sel"/></b>
				</font>
				</div>
	  		</td>
 		<td width="20%"  class="tdfont">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="from"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfont">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="subject"/></b>
				</font>
				</div>
	  		</td>
	  	
		  	<td id="recId" width="20%"  class="tdfont">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="recevied"/></b>
					</font>
					</div>
		  	</td>			
 	</tr>
 	<%ConsultationInboxFB fb=(ConsultationInboxFB)request.getAttribute("ConsultationInboxFB");
		//	String patCrNo=fb.getPatCrNo();
		System.out.println("pat cr np.===================================== "+fb); 
		String selectedCrNo=fb.getSelectedPatCrNo();
		%>
 		<logic:iterate id="consultationVo" name="<%=OpdConfig.CONSULTATION_INBOX_DETAIL_VO%>" type="hisglobal.vo.ConsultationDtlVO">
 			
 			<% String ack=consultationVo.getAckStatus();
 			String backColor;
 			if(ack.equals(OpdConfig.OPD_CONSULTANT_ACK_STATUS_READ)){
 				backColor="#CCCCCC";
 				}
 			else{
 				backColor="#FFFFFF";
 			}
 			%>
 			<tr  style="background-color:maroon">
 			<%String mailId=consultationVo.getMailRequestId();
 			String patCrNo=consultationVo.getPatCrNo();
 			%>
 				<td style="background-color:<%=backColor%>" width="5%"  class="tdfont">
 				<html:checkbox name="ConsultationInboxFB" property="selectedMailId" value="<%=mailId%>"></html:checkbox>
 				</td>
 				<td width="20%"  class="tdfont" style="background-color:<%=backColor%>" >
				<div align="center" >	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<%--<html:link  action="<%=path%>" onclick="submitToRetreiveMail('GETMAIL',mailid)"><b><bean:write name="consultationVo" property="fromDoctor"/></b></html:link> --%>
				<a style="cursor: pointer;" onclick="submitToRetreiveMail('GETMAIL','<%=mailId %>','<%=patCrNo %>')" ><b><bean:write name="consultationVo" property="fromDoctor"/></b></a>
				</b></font>
				</div>
	  		</td>
	  	
	  		
	  		
	  		<td width="20%"  class="tdfont" style="background-color:<%=backColor%>" >
				<div align="center"  >	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				
				<a style="cursor: pointer;" onclick="submitToRetreiveMail('GETMAIL','<%=mailId %>','<%=patCrNo %>')" ><b><bean:write name="consultationVo" property="subject"/>  </b></a>
				</font>
				</div>
	  		</td>
	  	
		  	<td width="20%"  class="tdfont" style="background-color:<%=backColor%>" >
					<div align="center"  >	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:write name="consultationVo" property="sentDate"/>  </b>
					</font>
					</div>
		  	</td>			
 			</tr>
 		</logic:iterate>
 
 </table>
</his:ContentTag>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		  <img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDelete()" onkeypress="if(event.keyCode==13) submitToDelete()">
</his:ButtonToolBarTag>
</his:TransactionContainer>
<html:hidden name="ConsultationInboxFB" property="hmode"/>
<html:hidden name="ConsultationInboxFB" property="patCrNo"/>
<html:hidden name="ConsultationInboxFB" property="mailRequestId"/>
<html:hidden name="ConsultationInboxFB" property="selectedPatCrNo"/>
