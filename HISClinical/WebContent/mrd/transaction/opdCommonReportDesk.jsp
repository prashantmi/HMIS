<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

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

<his:javascript src="/opd/opdJs/opd.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function closePage()
{
	
	self.close();
}

function clearPage()
{
	// alert("document "+document.getElementById("opdReportDeskFrame").src)
	document.getElementById("opdReportDeskFrame").src="/HISClinical/opd/report.cnt";
	
}

</script>




<his:TitleTag name="OPD Report Desk">
	<his:insertDateTag  /> 
	
</his:TitleTag>
<table width="110%" cellspacing="0" cellpadding="0" style="background:#BBBBBB;position: relative" height="90%">
	<tr style="margin:0%;" height="100%">
		<td width="18%" nowrap="nowrap">
			<iframe src="/HISClinical/opd/report/leftMenuDesk.cnt" 
			 align="center" width="100%" height="100%">
		    </iframe>
		</td>
		<td  width="70%" nowrap="nowrap">
			<iframe id="opdReportDeskFrame" src="/HISClinical/opd/report.cnt"
				 align="center" width="100%" height="100%">
		    </iframe>
		</td>
	</tr>
	
</table>

<his:ButtonToolBarTag>
         
         <div align="center">		 
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13) clearPage();" onclick =  "clearPage();" tabindex="1"/>
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13)closePage();" onclick =  "closePage();" tabindex="1"/>	        
         </div>
		 	 
			 
	        
         
</his:ButtonToolBarTag>