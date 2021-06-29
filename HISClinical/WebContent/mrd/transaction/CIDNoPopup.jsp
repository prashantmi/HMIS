<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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

<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function fillCIDNo()
{
	var len=document.getElementsByName("CIDNoArray").length;
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("CIDNoArray")[i].checked)
		{
			opener.document.getElementsByName("CIDNo")[0].value=document.getElementsByName("CIDNoArray")[i].value;
			opener.document.getElementsByName("recevingDate")[0].value=document.getElementsByName("recevingDateArray")[i].value;
			opener.document.getElementsByName("recevingTimeHr")[0].value=document.getElementsByName("recevingTimeHoursArray")[i].value;
			opener.document.getElementsByName("recevingTimeMin")[0].value=document.getElementsByName("recevingTimeMinuteArray")[i].value;
		}
	}
		
	closeForm();
}

</script>
<body >
		

<his:TransactionContainer>
		<html:form action="/computerizedSummonDetail">
		
		<his:TitleTag name="CID No Detail">
		</his:TitleTag>
		
		<logic:present name="<%=MrdConfig.ALL_CID_NO_INFO_LIST %>">
		<logic:notEmpty name="<%=MrdConfig.ALL_CID_NO_INFO_LIST %>">
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
				<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="select"/></b>
						</font>
					</div>
	  			</td>
				<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="CIDNo"/></b>
						</font>
					</div>
	  			</td>
	  			<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="subject"/></b>
						</font>
					</div>
	  			</td>
	  			<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="recevingDate"/></b>
						</font>
					</div>
	  			</td>
	  			<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="receivingTime"/></b>
						</font>
					</div>
	  			</td>
			</tr>
			<logic:iterate id="DocReceivingFormDtlvo" name="<%=MrdConfig.ALL_CID_NO_INFO_LIST %>" indexId="idx" type="hisglobal.vo.DocReceivingFormDtlVO">
			<tr>
				<td class="tdfont" >
				 	<div align="center">
				 		<html:radio property="CIDNoArray" value="<%=DocReceivingFormDtlvo.getCIDNo() %>" onclick="fillCIDNo()"></html:radio>
				 		<html:hidden name="SummonDtlFB" property="recevingDateArray" value="<%=DocReceivingFormDtlvo.getReceivingDate() %>"/>
				 		<html:hidden name="SummonDtlFB" property="recevingTimeHoursArray" value='<%=DocReceivingFormDtlvo.getReceivingTime().split(":")[0] %>'/>
				 		<html:hidden name="SummonDtlFB" property="recevingTimeMinuteArray" value='<%=DocReceivingFormDtlvo.getReceivingTime().split(":")[1] %>'/>
				 	</div>
				 </td>
				<td class="tdfont" >
				 	<div align="center">
				 		<bean:write name="DocReceivingFormDtlvo" property="CIDNo"/>
				 	</div>
				 </td>	
				 <td class="tdfont" >
				 	<div align="center">
				 		<bean:write name="DocReceivingFormDtlvo" property="typeOfDocument"/>
				 	</div>
				 </td>	
				 <td class="tdfont" >
				 	<div align="center">
				 		<bean:write name="DocReceivingFormDtlvo" property="receivingDate"/>
				 	</div>
				 </td>	
				 <td class="tdfont" >
				 	<div align="center">
				 		<bean:write name="DocReceivingFormDtlvo" property="receivingTime"/>
				 	</div>
				 </td>	
			</tr>
			</logic:iterate>
		</table>
		</his:ContentTag>
		</logic:notEmpty>
		</logic:present>
			
		
		<his:ButtonToolBarTag>
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
	 	</his:ButtonToolBarTag>
	<logic:present name="<%=MrdConfig.ALL_CID_NO_INFO_LIST %>">
	<logic:empty name="<%=MrdConfig.ALL_CID_NO_INFO_LIST %>">
		<div align="left">	           
			<font color="#ff0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>No CID No found</b>
			</font>
		</div>				
	</logic:empty>
	</logic:present>	
	
	<html:hidden name="SummonDtlFB" property="hmode" />
	
	
	
</html:form>
</his:TransactionContainer>


</body>
</html>