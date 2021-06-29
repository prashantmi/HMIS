
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function submitPage(mode)
{
	elmt=document.getElementsByName("mode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}
function showDesk()
{
	submitPage('DESK');
}

function showRecordType()
{
	submitPage('RECORDTYPE');
}
</script>
<body>
	<html:form action="/opdFileTracking">
	<his:TransactionContainer>
		<his:TitleTag name="OPD File Tracking">
		</his:TitleTag>
		
		<his:ContentTag>
			<logic:equal name="OPDFileTrackingFB" value="<%=MrdConfig.NO %>" property="isMrdListOne">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead" >
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="mrd"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="OPDFileTrackingFB" property="mrdCode" onchange="if (this.value!=-1) showDesk()">
									<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_ALL_OPD_FILE_MRD_USER_BASED%>">
											<html:options collection="<%=MrdConfig.LIST_ALL_OPD_FILE_MRD_USER_BASED%>" property="value" labelProperty="label" />
										</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>	
			</logic:equal>
			
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
		</his:ButtonToolBarTag>
		
		<html:hidden name="OPDFileTrackingFB" property="mode" />
		<html:hidden name="OPDFileTrackingFB" property="isMrdListOne" />
		<html:hidden name="OPDFileTrackingFB" property="mrdCode" />
		
	</his:TransactionContainer>
	
	</html:form>
	<his:status/>
</body>		
</html>