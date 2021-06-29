<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
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
function showReportDesk(event)
{
	// alert("Report Desk")
	// alert("screen.availHeight "+screen.availHeight+" screenWidth "+screen.availWidth)
	var screenHieght=screen.availHeight-100;
	child=openDependentPopup('<his:path src='opd/gotoOpdReportDesk.cnt'/>',event,screenHieght,screen.availWidth);
	child.moveTo(0,0);
}
</script>

<his:ContentTag>
<table width="100%" cellspacing="0" cellpadding="0" height="10%">	
	<tr style="margin:0%">
		<td>
			<a href="/HISClinical/opd/transaction/opdFooter.jsp" onclick="showReportDesk(event)" >Click For Reports</a>
		</td>
	</tr>
	
</table>

</his:ContentTag>

<div id="divDiseaseNameCodeList" style="display: none; position: absolute;">
	<select name="diseaseNameCodeList" id="diseaseNameCodeList" multiple="multiple" size="4">
		<logic:iterate name="<%=DynamicDeskConfig.DYNAMIC_DESK_ICD_DISEASE_LIST%>" id="list" type="hisglobal.vo.IcdDiseaseMasterVO">
			<option value="<%=list.getDisease().trim()%>" ><%=list.getDiseaseCode().trim()%></option>
		</logic:iterate>
	</select>
</div>
<div id="divDiseaseCodeNameList" style="display: none; position: absolute;">
	<select name="diseaseCodeNameList" id="diseaseCodeNameList" multiple="multiple" size="4">
		<logic:iterate name="<%=DynamicDeskConfig.DYNAMIC_DESK_ICD_DISEASE_LIST%>" id="list" type="hisglobal.vo.IcdDiseaseMasterVO">
			<option value="<%=list.getDiseaseCode().trim()%>" ><%=list.getDisease().trim()%></option>
		</logic:iterate>
	</select>
</div>

<!-- Added by Vasu on 14.November.2018 to get Morphology and Diagnosis Site Code list for ICD-O -->
<div id="divDiagnosisSiteNameCodeList" style="display: none; position: absolute;">
	<select name="diagnosisSiteNameCodeList" id="diseaseNameCodeList" multiple="multiple" size="4">
		<logic:iterate name="<%=DynamicDeskConfig.DYNAMIC_DESK_ICDO_DIAGNOSIS_SITE_LIST%>" id="list" type="hisglobal.vo.IcdDiseaseMasterVO">
			<option value="<%=list.getDiseaseSite().trim()%>" ><%=list.getDiseaseCode().trim()%></option>
		</logic:iterate>
	</select>
</div>
<div id="divDiagnosisSiteCodeNameList" style="display: none; position: absolute;">
	<select name="dignosisSiteCodeNameList" id="diseaseCodeNameList" multiple="multiple" size="4">
		<logic:iterate name="<%=DynamicDeskConfig.DYNAMIC_DESK_ICDO_DIAGNOSIS_SITE_LIST%>" id="list" type="hisglobal.vo.IcdDiseaseMasterVO">
			<option value="<%=list.getDiseaseCode().trim()%>" ><%=list.getDiseaseSite().trim()%></option>
		</logic:iterate>
	</select>
</div>

<div id="divMorphologyNameCodeList" style="display: none; position: absolute;">
	<select name="morphologyNameCodeList" id="diseaseNameCodeList" multiple="multiple" size="4">
		<logic:iterate name="<%=DynamicDeskConfig.DYNAMIC_DESK_ICDO_MORPHOLOGY_LIST%>" id="list" type="hisglobal.vo.IcdDiseaseMasterVO">
			<option value="<%=list.getMorphTitle().trim()%>" ><%=list.getMorphCode().trim()%></option>
		</logic:iterate>
	</select>
</div>
<div id="divMorphologyCodeNameList" style="display: none; position: absolute;">
	<select name="morphologyCodeNameList" id="diseaseCodeNameList" multiple="multiple" size="4">
		<logic:iterate name="<%=DynamicDeskConfig.DYNAMIC_DESK_ICDO_MORPHOLOGY_LIST%>" id="list" type="hisglobal.vo.IcdDiseaseMasterVO">
			<option value="<%=list.getMorphCode().trim()%>" ><%=list.getMorphTitle().trim()%></option>
		</logic:iterate>
	</select>
</div>
