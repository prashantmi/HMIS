<%@page import="enquiry.enquiryConfig"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<%@page import="enquiry.vo.*,java.util.*" %>
<%@page import="registration.*,enquiry.*"%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<script>

function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}
function getConsultantByEmpNo(empNo,department,unit){
	document.forms[0].hmode.value='CONSULTANTDTL';
	document.forms[0].empNo.value=empNo;
	document.forms[0].department.value=department;
	document.forms[0].departmentUnit.value=unit;
	document.forms[0].submit();
}

</script>

<body>
<his:TransactionContainer>
<html:form action="/otConsultantEnquiry">

	<his:TitleTag name="OT Consultant Enquiry">
	</his:TitleTag>
<his:statusList>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="70%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<b> <bean:message key="con" /> <bean:message key="name" />
					</b>
					</font></div>
				</td>
				<td width="20%" class="tdfont">
				<div align="left"><html:text name="otConsultantEnquiryFB"
					maxlength="30" size="30" styleClass="textbox"
					property="consultantName" tabindex="1"
					onkeypress="if(event.keyCode==13) submitPage('SEARCHBYNAME');" /></div>
				</td>
				<td width="10%" class="tdfont">
				<div align="left"><img class="button" src='<his:path src="/hisglobal/images/Search.png"/>' tabindex="1" style="cursor: pointer"
				onkeypress="if(event.keyCode==13) submitPage('SEARCHBYNAME');" tabindex="1" onclick="submitPage('SEARCHBYNAME');"></div>
				</td>
			</tr>
		</table>
	</his:ContentTag>

	
	<his:SubTitleTag name="All Consultants"> 
	</his:SubTitleTag>
	 <his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="name" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="15%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="designation" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
						key="department" /> </b></font></div>
					</td>

					<td class="tdfonthead" width="15%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="unit" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="20%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="days" /></b> </font></div>
					</td>
					
				</tr>
				<logic:iterate id="consultantVo" indexId="idx" name="<%=enquiryConfig.OT_CONSULTANT_LIST_VIEW %>"
					type="enquiry.vo.HospitalConsultantEnquiryVO">
					<tr>
						<td class="tdfont">
							<div align="center">
								<bean:write name="consultantVo" property="consultantName"/>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<bean:write name="consultantVo" property="designation"/>
							</div>
						</td>
						<td class="tdfont">
							
							<div align="center">
								<bean:write name="consultantVo" property="department"/>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
							  <bean:write name="consultantVo" property="departmentUnit"/>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
							  <bean:write name="consultantVo" property="day"/>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
	</his:statusList>
	<his:statusTransactionInProcess>
	<his:SubTitleTag name="Consultant Detail">
	</his:SubTitleTag>
	 <his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="50%">
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="con" /><bean:message key="name" /></b> </font>
				</td>
				<td class="tdfont" width="50%">
					<b><bean:write name="otConsultantEnquiryFB" property="consultantName" /></b> 
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="50%">
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="department" /></b> </font>
				</td>
				<td class="tdfont" width="50%">
					<b><bean:write name="otConsultantEnquiryFB" property="department" /></b>
				</td>
			</tr>	
			<tr>	
				<td class="tdfonthead" width="50%">
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="unit" /></b> </font>
				</td>
				<td class="tdfont" width="50%">
					<b><bean:write name="otConsultantEnquiryFB" property="departmentUnit" /></b>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead"  width="50%">
				<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="day" /></b> </font>
				</td>
				<td class="tdfont" width="50%">
					<b><bean:write name="otConsultantEnquiryFB" property="day" /></b>
				</td>
			</tr>
		</table>
	</his:ContentTag>			
	</his:statusTransactionInProcess>
		
	<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"onclick="submitPage('CANCEL');">
		</his:ButtonToolBarTag>
		
		<html:hidden name="otConsultantEnquiryFB" property="hmode" />
		<html:hidden name="otConsultantEnquiryFB" property="departmentUnit" />
		<html:hidden name="otConsultantEnquiryFB" property="empNo" />
		<html:hidden name="otConsultantEnquiryFB" property="department" />
		<html:hidden name="otConsultantEnquiryFB" property="day" />
		
	</html:form>
<his:status />
</his:TransactionContainer>
</body>
</html>