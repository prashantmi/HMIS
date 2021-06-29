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

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
</script>
<his:TransactionContainer>
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
</his:TransactionContainer>
<html:hidden name="ConsultationInboxFB" property="hmode"/>
<html:hidden name="ConsultationInboxFB" property="patCrNo"/>