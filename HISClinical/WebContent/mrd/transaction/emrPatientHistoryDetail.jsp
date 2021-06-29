<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

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

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 

<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<script type="text/javascript">

function closeForm()
{
	self.close();
}
function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}


</script>


<html:form action="/emrDesk">

<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
<his:TitleTag name="History">
</his:TitleTag>


	<logic:present name="<%=MrdConfig.PAT_HISTORY_DETAILS_ARRAY%>">
	<logic:notEmpty name="<%=MrdConfig.PAT_HISTORY_DETAILS_ARRAY %>">
	<his:ContentTag>
		<jsp:include page="/hisglobal/utility/generictemplate/chartTile.cnt" flush="true" />
	</his:ContentTag>
	</logic:notEmpty>
	</logic:present>
	
	<logic:notPresent name="<%=MrdConfig.PAT_HISTORY_DETAILS_ARRAY%>" >
	<logic:empty name="<%=MrdConfig.PAT_HISTORY_DETAILS_ARRAY %>">
		<his:ContentTag>
			<tr>
				<td class="tdfont" width="100%" nowrap>
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="noHistoryFound" />
				</font>
				</div>
				</td>
			</tr>
					
		</his:ContentTag>
		</logic:empty>
	</logic:notPresent>
<%-- 
<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor: pointer;" onclick="showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor: pointer;" onclick="showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	<div id="divLegends" style="display:block;">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Date Fromat:     
					</font>
				</td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					  
		
					</font>
								</div>
				</td>				
			</tr>
			
		</table>
	</his:ContentTag>
	</div>
--%>




</html:form>

</html>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 