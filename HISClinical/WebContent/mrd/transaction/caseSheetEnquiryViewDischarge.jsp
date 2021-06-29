
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 

<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

	<his:TitleTag key="patientProfile">
		
	</his:TitleTag>
	<bean:write name="CaseSheetEnquiryFB" property="profileHTML" filter="false"/>

<center>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) window.close();" onclick="window.close();" />
</center>
