<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
<his:javascript src="/registration/js/popup.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="mrd.MrdConfig"%>

<script type="text/javascript">

function hideDivision(id)
{
	alert("hide "+document.getElementById(id).style.display)
	document.getElementById(id).style.display="none";
}
</script>
<html>
   	<his:SubTitleTag name="Diagnosis">
   	</his:SubTitleTag>
   	<table cellspacing="1" width="100%">
		<logic:iterate name="<%=MrdConfig.PAT_EPISODE_DIAGNOSIS_VO_ARRAY%>" id="episodeDiagnosisVOId" type="hisglobal.vo.EpisodeDiagnosisVO">
			<tr>
				<td class="tdfont">
					<div align="center"> 
					<b>
					<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="episodeDiagnosisVOId" property="entryDate" />
					</font>
					</b>
					</div>
				</td>
				<td class="tdfont">
					<div align="center">
					<b>
					<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="episodeDiagnosisVOId" property="dignosisName" />
					</font>
					</b>
	 				</div>
				</td>
				
				<td class="tdfont">
					<div align="center">
					<b>
					<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="episodeDiagnosisVOId" property="diagnosticTypeName" />
					</font>
					</b>
					</div>
				</td>
				
			</tr>
		</logic:iterate>
   </table>
</html>
