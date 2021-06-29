
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.*"%>

<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<%-- 
<his:javascript src="/opd/opdJs/opdAjax.js" />
<his:javascript src="/opd/opdJs/opd.js" />
--%>
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
function submitForDepartment()
{
	var flag=false;
	if(document.getElementsByName("gnum_dept_code")[0].value!="-1")
	{
		flag=true;
	}
	return flag;
}
</script>


<html:form action="/departmentLocationEnquiry">
	<his:TitleTag>
		<his:name>
			<bean:message key="centralEnquiry" />
		</his:name>
	</his:TitleTag>
	<his:SubTitleTag name="Department Location Enquiry">
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="20%" align="left" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
					key="selectDepartment" /></b></font></div>
				</td>
				<td class="tdfont" align="left" width="20%"><html:select
					name="DepartmentLocationEnquiryFB" tabindex="1"
					property="gnum_dept_code" styleClass="regcbo"
					onchange="submitFormOnValidate(submitForDepartment(),'SEARCH')">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>"
						property="value" labelProperty="label" />
				</html:select></td>
			</tr>
		</table>
	</his:ContentTag>

	<his:statusTransactionInProcess>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<font color="#000000" size="4"
					face="Verdana, Arial, Helvetica, sans-serif"><b><strong>Search
				Result</strong></b></font>
			</table>
		</his:ContentTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="20%" align="left" class="tdfonthead">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b>
						<bean:message key="locationDesc" /></b></font></div>
					</td>
					<td class="tdfont" align="left" width="20%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:write  name="DepartmentLocationEnquiryFB" property="hgstr_description" /></b></font>
					</div>
					</td>

				</tr>
				<!--<tr>
					<td width="20%" align="left" class="tdfonthead">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message 	key="building" /></b></font></div>
					</td>
					<td class="tdfont" align="left" width="20%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:write  name="DepartmentLocationEnquiryFB" property="hgstr_building" /></b></font></div>
					</td>

				</tr>
				<tr>
					<td width="20%" align="left" class="tdfonthead">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message 	key="block" /></b></font></div>
					</td>
					<td class="tdfont" align="left" width="20%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:write 	name="DepartmentLocationEnquiryFB" property="hgstr_block" /></b></font></div>
					</td>

				</tr>
				<tr>
					<td width="20%" align="left" class="tdfonthead">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message 	key="floor" /></b></font></div>
					</td>
					<td class="tdfont" align="left" width="20%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:write name="DepartmentLocationEnquiryFB" property="hgstr_floor" /></b></font></div>
					</td>

				</tr>
				<tr>
					<td width="20%" align="left" class="tdfonthead">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message 	key="roomNo" /></b></font></div>
					</td>
					<td class="tdfont" align="left" width="20%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:write name="DepartmentLocationEnquiryFB" property="hgstr_room" /></b></font></div>
					</td>

				</tr>
												
				--><tr>
					<td width="20%" align="left" class="tdfonthead">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message key="landmark" /></b></font></div>
					</td>
					<td class="tdfont" align="left" width="20%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:write name="DepartmentLocationEnquiryFB" property="hgstr_landmark" /></b></font></div>
					</td>

				</tr>

			</table>
		</his:ContentTag>
	</his:statusTransactionInProcess>
	<html:hidden name="DepartmentLocationEnquiryFB" property="hmode" />
</html:form>
<his:ButtonToolBarTag>

	<div align="center"><img class="button"
		src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
		style="cursor: pointer"
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>'
			tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')"
			onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:statusTransactionInProcess></div>

</his:ButtonToolBarTag>
<his:status />