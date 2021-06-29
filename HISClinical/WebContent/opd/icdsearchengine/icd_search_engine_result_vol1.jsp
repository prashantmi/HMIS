<!-- 
/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.icdsearchengine.ICDSearchEngineConfig"%>
<%@page import="opd.icdsearchengine.action.ICDSearchEngineAJAXServlet"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ICD Search Engine</title>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/opd/icdsearchengine/css/icdSearchEngine.css"/>

<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js"/>
<script type="text/javascript" src="/HISClinical/opd/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/opd/icdsearchengine/js/validations.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngineformbean.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngineAjax.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngine.js"/>
<his:javascript src="/opd/icdsearchengine/js/support.js"/>

<script>

window.onload = function()
{
	// Setting Engine
	var objEngine = ICDSearchEngine.getICDSearchEngine();
	if(!objEngine)
	{
		parent.callOnLoad();
		objEngine = ICDSearchEngine.getICDSearchEngine();
	}
	if(objEngine)	objEngine.populate();
}
</script>

</head>

<body>

<html:form action="/icdsearchengine/icdEngineResultVol1">
<div id="divMainResult">
<his:statusTransactionInProcess>
<logic:notEmpty name="<%=ICDSearchEngineConfig.ICD_ENGINE_RESULT_ICD_DISEASE_LIST%>">
	<his:ContentTag>
		<table id="ResHeader" width="100%" cellpadding="0" cellspacing="1">
			<tr class="ResHeading">
				<td width="5%">
					&nbsp;
				</td>
				<td width="80%">
					<div align="center">
						Disease Name
					</div>
				</td>
				<td width="15%">
					<div align="center">
						ICD Code
					</div>
				</td>
			</tr>
		</table>
		<div id="divSubDisSample" style="display: none;">
			<table width="100%" cellpadding="0" cellspacing="1" >
				<tr class="ResRowVol1_#strRecordType">
					<td width="5%" class="label">
						<div align="center">
						</div>
					</td>
					<td width="5%" class="label">
						<div align="center">
							#Seq.
						</div>
					</td>
					<td width="80%" class="hyperlink">
						<div align="left">
							<a id="ancDtlDisease" name="ancDtlDisease" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)" 
								onclick="viewICDDiseaseDetail(event,'#strIcdCode','#strRecordType','#strSearialNo')">
								#strDiseaseName
							</a>
						</div>
					</td>
					<td width="15%" class="hyperlink">
						<div align="left">
							<a id="ancDtlDisease" name="ancDtlDisease" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)"
								onclick="viewICDDiseaseDetail(event,'#strIcdCode','#strRecordType','#strSearialNo')">
								#strIcdCode
							</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<table id="ResDetailList" width="100%" cellpadding="0" cellspacing="0"><tr><td>
			<logic:iterate id="icdResDiseaseLst" indexId="idn" name="<%=ICDSearchEngineConfig.ICD_ENGINE_RESULT_ICD_DISEASE_LIST%>" type="opd.icdsearchengine.vo.ResultDiseaseListVO">
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr class="ResRowVol1_<%=icdResDiseaseLst.getRecordType()%>">
					<td width="5%" class="label">
						<div align="center">
							<%=idn.intValue()+1%>.
						</div>
					</td>
					<td width="80%" class="hyperlink">
						<div align="left">
							<logic:notEqual name="icdResDiseaseLst" property="haveChildren" value="0">
								<img id="imgSub<%=icdResDiseaseLst.getDiseaseCode().trim()%>" name="imgSubDiseases" alt="+" title="Show Sub Diseases" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' tabindex="1" 
									onclick="toggleSubDiseases('<%=ICDSearchEngineAJAXServlet.MODE_SUB_DISEASE_LIST%>','<%=icdResDiseaseLst.getRecordType() %>','<%=icdResDiseaseLst.getDiseaseCode()%>');" 
									onkeypress="if(event.keyCode==13) toggleSubDiseases('<%=ICDSearchEngineAJAXServlet.MODE_SUB_DISEASE_LIST%>','<%=icdResDiseaseLst.getRecordType() %>','<%=icdResDiseaseLst.getDiseaseCode()%>');" >
									&nbsp;
							</logic:notEqual>
							<logic:equal name="icdResDiseaseLst" property="haveChildren" value="0">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>

							<a id="ancDtlDisease" name="ancDtlDisease" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)" 
								onclick="viewICDDiseaseDetail(event,'<%=icdResDiseaseLst.getDiseaseCode()%>','<%=icdResDiseaseLst.getRecordType()%>','<%=icdResDiseaseLst.getSlNo()%>')">
								<bean:write name="icdResDiseaseLst" property="fullDiseaseName"/>
							</a>
						</div>
					</td>
					<td width="15%" class="hyperlink">
						<div align="left">
							<a id="ancDtlDisease" name="ancDtlDisease" tabindex="1" class="anchorNormal" onmouseover="mouseOverAnchor(this)" onmouseout="mouseOutAnchor(this)"
								onclick="viewICDDiseaseDetail(event,'<%=icdResDiseaseLst.getDiseaseCode()%>','<%=icdResDiseaseLst.getRecordType()%>','<%=icdResDiseaseLst.getSlNo()%>')">
								<bean:write name="icdResDiseaseLst" property="diseaseCode"/>
							</a>
						</div>
					</td>
				</tr>
			</table>
			<logic:notEqual name="icdResDiseaseLst" property="haveChildren" value="0">
				<div id="divSub<%=icdResDiseaseLst.getDiseaseCode().trim()%>"></div>
			</logic:notEqual>
			</logic:iterate>
		</td></tr></table>
	</his:ContentTag>
</logic:notEmpty>
<logic:empty name="<%=ICDSearchEngineConfig.ICD_ENGINE_RESULT_ICD_DISEASE_LIST%>">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="100%" class="nofound">
					No Disease Found for the Serach
				</td>
			</tr>
		</table>
	</his:ContentTag>
</logic:empty>
</his:statusTransactionInProcess>

<logic:equal name="ICDSearchEngineFB" property="strSearchStart" value="false">
<table id="tblLoading" width="100%" height="100%" cellpadding="0" cellspacing="0" border="0">
	<tr><td>
		<img id="imgLoading" name="imgLoading" alt="Loading" title="Loading" src='<his:path src="/hisglobal/images/loading.gif"/>' tabindex="1">
	</td></tr>
</table>
</logic:equal>
</div>
<div id="divHiddenLoading" style="display: none;">
	<table id="tblLoading" width="100%" height="100%" cellpadding="0" cellspacing="0" border="0">
		<tr><td>
			<img id="imgLoading" name="imgLoading" alt="Loading" title="Loading" src='<his:path src="/hisglobal/images/loading.gif"/>' tabindex="1">
		</td></tr>
	</table>
</div>
<jsp:include page="/opd/icdsearchengine/icd_search_engine_hidden_fields.jsp" flush="true"></jsp:include>

</html:form>
</body>
</html>