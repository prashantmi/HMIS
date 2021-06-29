<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.PoliceVerificationDtlVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<script type="text/javascript">
function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function closeForm(event)
{
	opener.document.getElementsByName("policeVerificationFlag")[0].checked=true;
	opener.showPoliceVerification(event)
	self.close();
}

function isFormClose()
{
	var formclose=true;
  	<%     
		Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
    	if(objStatus.contains(Status.NEW)){
    	%>    	
    		formclose=false;    	   	
    	<%
    }    
    %>
    
    if(formclose)
    {
    	if(!window.opener.closed)
    	{
    		self.close();
    	}
    }
}

function validateOk()
{
	submitPage('EXISTPOLICEVER');
}
</script>

<html:form action="/deceasedAcceptance">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
		<body onload="isFormClose()">
			<his:TitleTag name="Police Verification Detail">
			</his:TitleTag>
			<logic:present name="<%=MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL %>">
			<bean:define id="policeVerVO" name="<%=MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL %>" type="hisglobal.vo.PoliceVerificationDtlVO"></bean:define>
			<his:ContentTag>
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%" nowrap="nowrap" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="policecaseno" />
							</font>
						</td>
						<td width="20%" nowrap="nowrap" class="tdfont">
							&nbsp;<bean:write name="policeVerVO" property="caseNo"/>
						</td>
						<td width="30%" nowrap="nowrap" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="policestation" />
							</font>
						</td>
						<td width="20%" nowrap="nowrap" class="tdfont">
							&nbsp;<bean:write name="policeVerVO" property="policeStation"/>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" nowrap="nowrap" width="30%">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="docketNo" />
							</font>
						</td>
						<td class="tdfont" nowrap="nowrap" width="20%">
							&nbsp;<bean:write name="policeVerVO" property="docketNo"/>
						</td>
						<td class="tdfonthead" nowrap="nowrap" width="30%">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="officerincharge" />
								<bean:message key="name" />
							</font>
						</td>
						<td class="tdfont" nowrap="nowrap" width="20%">
							&nbsp;<bean:write name="policeVerVO" property="officerIncharge"/>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" nowrap="nowrap" width="30%">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="officerincharge" />
								<bean:message key="designation" />
							</font>
						</td>
						<td class="tdfont" nowrap="nowrap" width="20%">
							&nbsp;<bean:write name="policeVerVO" property="ioDesignation"/>
						</td>
						<td class="tdfonthead" nowrap="nowrap" width="30%">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="officerincharge" />
								<bean:message key="batchno" />
							</font>
						</td>
						<td class="tdfont" nowrap="nowrap" width="20%">
							&nbsp;<bean:write name="policeVerVO" property="ioBatchNo"/>
						</td>
					</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="dutyOfficer" />
									<bean:message key="name" />
								</font>
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%">
								&nbsp;<bean:write name="policeVerVO" property="dutyOffName"/>
							</td>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="dutyOfficer" />
									<bean:message key="designation" />
								</font>
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%">
								&nbsp;<bean:write name="policeVerVO" property="dutyOffDesignation"/>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="dutyOfficer" />
									<bean:message key="batchno" />
								</font>
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
								&nbsp;<bean:write name="policeVerVO" property="dutyOffBatchNo"/>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="caseremarks" />
								</font>
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
								&nbsp;<bean:write name="policeVerVO" property="caseRemarks"/>
							</td>
						</tr>
				</table>
			</his:ContentTag>
			</logic:present>
			<his:ButtonToolBarTag>
				<logic:present name="<%=MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL %>">	
					<img class="button" src='<his:path src="/hisglobal/images/ok.gif"/>'  style=cursor:pointer tabindex="1" onclick ="validateOk()" onkeypress="if(event.keyCode==13) validateOk() ">
				</logic:present>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm(event)" onkeypress="if(event.keyCode==13) closeForm(event)">
	        </his:ButtonToolBarTag>
        
        <html:hidden name="DeceasedAcceptanceFB" property="hmode"/>
        
		</body>
		<his:status/>
</html:form>			