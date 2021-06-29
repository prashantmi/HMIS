<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%-- commented for IPD Nursing desk optimization

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
</div> --%>

