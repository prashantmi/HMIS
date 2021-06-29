<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.EpisodeRestAdviceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.PatMedicalDtlVO"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="mrd.transaction.controller.fb.MedicalCertificateFB"%>
<%@page import="hisglobal.vo.EpisodeVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="inpatient.InpatientConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}


</script>

<body>
	<html:form action="/ancTeamDetail">
	
		<his:TitleTag name="ANC Team Detail">
		</his:TitleTag>
			
		<logic:empty name="AncTeamDetailFB" property="patCrNo" >
			<his:InputCrNoTag name="AncTeamDetailFB"></his:InputCrNoTag>
		</logic:empty>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="24%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="consultant"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="24%" class="tdfont" width="25%"  >
					<div align="left" >
						<html:select name="AncTeamDetailFB" property="consultantId" tabindex="1" > 
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ALL_CONSULTANT_LIST%>">
									<html:options collection="<%=InpatientConfig.ALL_CONSULTANT_LIST%>" property="value" labelProperty="label"/>	
								</logic:present>
						</html:select>
					</div>
				</td>
				<td class="tdfonthead" width="24%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="role"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td class="tdfont" width="24%"  >
					<div align="left" >
						<html:select name="AncTeamDetailFB" property="roleId" tabindex="1" > 
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ALL_ROLE_LIST%>">
									<html:options collection="<%=InpatientConfig.ALL_ROLE_LIST%>" property="value" labelProperty="label"/>	
								</logic:present>
						</html:select>
					</div>
				</td>
				<td width="4%" class="tdfont" width="25%"  >
					<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Diagnosis" title="Add Row" onkeypress="if(event.keyCode==13) submitPage('ADDDROW');" onclick="submitPage('ADDDROW');">
				</td>
				
			</tr>
		</table>	
			
		<his:ButtonToolBarTag>
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
		</his:ButtonToolBarTag>	
			
		<html:hidden name="AncTeamDetailFB" property="hmode" />
	
	
	</html:form>
	<his:status/>
</body>


</html>