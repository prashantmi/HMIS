<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
window.onload = function()
{
	if(callThisOnload)
		callThisOnload();
}
</script>

<his:TitleTag>
		<his:name>
			<bean:message key="noMailFound" />
		</his:name>
	</his:TitleTag>
	

 <his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">
 	<tr>
 		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<INPUT TYPE="button" align="MIDDLE" value="Back" onClick="submitToDesk('NEW','NEW')"/> 
				</font>
				</div>
	  		</td>
	  	</tr>
	  </table>
	</his:ContentTag>
<html:hidden name="ConsultationInboxFB" property="hmode"/>
<html:hidden name="ConsultationInboxFB" property="mailPatCrNo"/>