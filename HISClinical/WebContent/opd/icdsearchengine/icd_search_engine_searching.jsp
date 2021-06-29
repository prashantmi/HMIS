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
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.icdsearchengine.action.ICDSearchEngineAJAXServlet"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ICD Search Engine</title>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/opd/icdsearchengine/css/icdSearchEngine.css"/>
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>

<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<script type="text/javascript" src="/HISClinical/opd/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/opd/icdsearchengine/js/validations.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngineformbean.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngineAjax.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngine.js"/>
<his:javascript src="/opd/icdsearchengine/js/support.js"/>


<script>

window.onload = function()
{
	// Setting Header
	var objEngine = ICDSearchEngine.getICDSearchEngine(document);
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

<html:form action="/icdsearchengine/icdEngineSearching">

<%	
	String fun = "return(validateAlphaNumericOnly(this,event) && onEnterApply(this,event,'GOForSearch','"+ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME+"'));";
	String fun1 = "return(validateAlphaNumOnly(this,event) && onEnterApply(this,event,'GOForSearch','"+ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE+"'));";
	String fun2 = "return(validatePositiveIntegerOnly(this,event) && onEnterApply(this,event,'GOForSearch','"+ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE+"'));";
	String fun3 = "return(validateAlphaNumericOnly(this,event) && onEnterApply(this,event,'GOForSearch','"+ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_NOT_KNOWN+"'));";
%>

<logic:equal name="ICDSearchEngineFB" property="strCurOption" value="<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_VOLUME_1 %>">
<his:ContentTag>
	<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="25%" class="srchHead">
				<input type="radio" name="setSearch" checked="checked" onclick="changeSearchOpt(this,<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME%>,<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME%>)"/> Disease Name 
				<input type="radio" name="setSearch" onclick="changeSearchOpt(this,<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE%>,<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME%>)"/> ICD Code
			</td> 
			<td width="25%" class="srchVal">
				<div id="divSrchName"> 
					<html:text name="ICDSearchEngineFB" property="strIcdDiseaseName" tabindex="1" maxlength="100" styleClass="textboxDisName" 
						onkeypress="<%=fun%>"/>
				</div>
				<div id="divSrchId" style="display: none;">
					<html:text name="ICDSearchEngineFB" property="strIcdCodeMain" tabindex="1" maxlength="3" size="4" styleClass="textboxNonSize" 
						onkeypress="<%=fun1%>"/>
					.
					<html:text name="ICDSearchEngineFB" property="strIcdCodeSub" tabindex="1" maxlength="1" size="2" styleClass="textboxNonSize" 
						onkeypress="<%=fun2%>"/>
				</div>
			</td>
			<td width="25%" class="srchHead">
				<div id="divSrchNameImg" align="left">
					<img id="imgSearchDiseaseName" name="imgSearchDiseaseName" src='<his:path src="/hisglobal/images/GO.png"/>' alt="GO" title="Search" tabindex="1" 
						onclick="GOForSearch('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME%>');" onkeypress="if(event.keyCode==13) GOForSearch('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME%>');" >
				</div>
				<div id="divSrchIdImg" align="left" style="display: none">
					<img id="imgSearchIcdCode" name="imgSearchIcdCode" src='<his:path src="/hisglobal/images/GO.png"/>' alt="GO" title="Search" tabindex="1" 
						onclick="GOForSearch('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE%>');" onkeypress="if(event.keyCode==13) GOForSearch('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE%>');" >
				</div>
			</td> 
			<td width="50%" class="srchVal">
				<div align="right">
					<img id="imgSearchAdvanced" name="imgSearchAdvanced" title="More" src='<his:path src="/hisglobal/images/arrdouble-up.png"/>' tabindex="1" 
						onclick="viewSearchAdvanced();" onkeypress="if(event.keyCode==13) viewSearchAdvanced();" >
				</div>
			</td>
		</tr>
	</table>

<div id="divAdvancedSearch" style="display: block;">
<div id="divOptName">
	<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<% if(ICDSearchEngineConfig.ICD_ENGINE_MAIN_DIESEASE_TYPE_SHOW.equals(ICDSearchEngineConfig.NO)) { %>
			<td width="25%" class="srchHead">
				Disease Type
			</td>
			<td width="25%" class="srchVal">
				<html:hidden name="ICDSearchEngineFB" property="strMainDiseaseTypeCode" value="-1"/>
				<html:select name="ICDSearchEngineFB" property="strDiseaseTypeCode" tabindex="1" styleClass="comboNormal">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_DISEASE_TYPES%>">
						<html:options collection="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_DISEASE_TYPES%>" property="value" labelProperty="label"/>
					</logic:present>														
				</html:select>
			</td>
			<%	} else { %>
			<td width="25%" class="srchHead">
				Disease Type Main<input type="radio" name="radDisMainType" value="<%=ICDSearchEngineConfig.YES%>" checked="checked" tabindex="1" onchange="toggleDiseaseType('<%=ICDSearchEngineConfig.YES%>')"/>
				&nbsp;&nbsp;Sub<input type="radio" name="radDisMainType" value="<%=ICDSearchEngineConfig.NO%>" tabindex="1" onchange="toggleDiseaseType('<%=ICDSearchEngineConfig.YES%>')"/>
			</td>
			<td width="25%" class="srchVal">
				<div id="divDisTypeMain" style="display: block;">
					<html:select name="ICDSearchEngineFB" property="strMainDiseaseTypeCode" tabindex="1" styleClass="comboNormal">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_MAIN_DISEASE_TYPES%>">
							<html:options collection="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_MAIN_DISEASE_TYPES%>" property="value" labelProperty="label"/>
						</logic:present>														
					</html:select>
				</div>
				<div id="divDisTypeSub" style="display: none;">
					<html:select name="ICDSearchEngineFB" property="strDiseaseTypeCode" tabindex="1" styleClass="comboNormal">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_DISEASE_TYPES%>">
							<html:options collection="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_DISEASE_TYPES%>" property="value" labelProperty="label"/>
						</logic:present>														
					</html:select>
				</div>
			</td>
			<%	} %>
			<td width="25%" class="srchHead">
				Chronic Disease
			</td>
			<td width="25%" class="srchVal">
				<html:select name="ICDSearchEngineFB" property="strChronicDiseaseCode" tabindex="1" styleClass="comboNormal">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_CHRONIC_DISEASES%>">
						<html:options collection="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_CHRONIC_DISEASES%>" property="value" labelProperty="label"/>
					</logic:present>														
				</html:select>
			</td>
		</tr>
	</table>
</div>
	<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="25%" class="srchHead">
				Record Type
			</td>
			<td width="25%" class="srchVal">
				<html:select name="ICDSearchEngineFB" property="strRecordType" tabindex="1" styleClass="comboNormal">
					<html:option value="<%=ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_ALL%>">All</html:option>
					<html:option value="<%=ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_DISEASE%>"><%=ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_DISEASE)]%> Only</html:option>
					<html:option value="<%=ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_INCLUDE%>"><%=ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_INCLUDE)]%> Only</html:option>
					<html:option value="<%=ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_SYNONYM%>"><%=ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_SYNONYM)]%> Only</html:option>
				</html:select>
			</td>
			<td width="25%" class="srchHead">
				Order By
			</td>
			<td width="25%" class="srchVal">
				<html:select name="ICDSearchEngineFB" property="strOrderBy" tabindex="1" styleClass="comboNormal">
					<html:option value="<%=ICDSearchEngineConfig.ORDER_BY_DISEASE_NAME%>"><%=ICDSearchEngineConfig.ORDER_BY_ARR[Integer.parseInt(ICDSearchEngineConfig.ORDER_BY_DISEASE_NAME)]%></html:option>
					<html:option value="<%=ICDSearchEngineConfig.ORDER_BY_ICD_CODE%>"><%=ICDSearchEngineConfig.ORDER_BY_ARR[Integer.parseInt(ICDSearchEngineConfig.ORDER_BY_ICD_CODE)]%></html:option>
				</html:select>
			</td>
		</tr>
	</table>
</div>
</his:ContentTag>
</logic:equal>


<logic:equal name="ICDSearchEngineFB" property="strCurOption" value="<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_VOLUME_3 %>">

<his:ContentTag>
	<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="50%" class="srchHead">
				<html:text name="ICDSearchEngineFB" property="strIndexSearchString" tabindex="1" maxlength="100" styleClass="textboxDisName" 
					onchange = "setCorrIndexTermID(this)" onkeypress="<%=fun3%>" />
				<html:hidden name="ICDSearchEngineFB" property="strAutoCompleteString"/>
				<html:hidden name="ICDSearchEngineFB" property="strIndexCode"/>
				<script type="text/javascript">
					if(setSerachDropDownLists())
						NewDropDownSearch.setup(document.getElementsByName("strIndexSearchString")[0],elemIndexTermList,NewDropDownSearch.SEARCH_TYPE["ANY_WHERE"],false,true);
				</script>
				<input style="display: none;"/>
			</td>
			<td width="50%" class="srchVal">
				<img id="imgSearchDiseaseName" name="imgSearchDiseaseName" src='<his:path src="/hisglobal/images/GO.png"/>' alt="GO" title="Search" tabindex="1" 
					onclick="GOForSearch('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_NOT_KNOWN%>');" onkeypress="if(event.keyCode==13) GOForSearch('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_NOT_KNOWN%>');" >
			</td> 
		</tr>
	</table>
</his:ContentTag>

</logic:equal>

<logic:equal name="ICDSearchEngineFB" property="strCurOption" value="<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_VOLUME_BOTH %>">
<his:ContentTag>
	<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="50%" class="srchHead">
				<html:text name="ICDSearchEngineFB" property="strDiseaseName" tabindex="1" maxlength="100" styleClass="textboxDisName" 
					onkeypress="<%=fun3%>"/>
				<input style="display: none;"/>
			</td>
			<td width="50%" class="srchVal">
				<img id="imgSearchDiseaseName" name="imgSearchDiseaseName" src='<his:path src="/hisglobal/images/GO.png"/>' alt="GO" title="Search" tabindex="1" 
					onclick="GOForSearch('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_NOT_KNOWN%>');" onkeypress="if(event.keyCode==13) GOForSearch('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_NOT_KNOWN%>');" >
			</td> 
		</tr>
	</table>
</his:ContentTag>
</logic:equal>

<logic:equal name="ICDSearchEngineFB" property="strCurOption" value="<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_TABULAR_LIST %>">
<his:ContentTag>
	<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1" style="vertical-align: middle;">
		<tr valign="middle" style="vertical-align: middle;">
			<td width="100%" class="srchHead" valign="middle" style="vertical-align: middle;">
				<div align="center" style="vertical-align: middle;">
					<input type="radio" name="strReportOption" value="1" checked="checked" onclick="setCriteria()" style="vertical-align: middle;">Group/Chapter List
					&nbsp;&nbsp;<input type="radio" name="strReportOption" value="2" onclick="setCriteria()" style="vertical-align: middle;">Sub Group/Sub Blocks List
					&nbsp;&nbsp;<input type="radio" name="strReportOption" value="3" onclick="setCriteria()" style="vertical-align: middle;">Disease List
					&nbsp;&nbsp;<input type="radio" name="strReportOption" value="4" onclick="setCriteria()" style="vertical-align: middle;">Sub Disease List
				</div>
			</td>	
		</tr>
	</table>
	<div id="divReportsCriteria" style="display: none;">
		<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="50%" class="srchHead">
					Chapter/Group
				</td>
				<td width="50%" class="srchVal">
					<%
						String strIcdGroupCodeOnChnage = "populateSubGroups('"+ICDSearchEngineAJAXServlet.MODE_ALL_SUB_GROUP+"')";
					%>
					<html:select name="ICDSearchEngineFB" property="strIcdGroupCode" tabindex="1" styleClass="comboNormal" onchange="<%=strIcdGroupCodeOnChnage%>">
						<html:option value="0">All</html:option>
						<logic:present name="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_ICD_GROUPS%>">
							<html:options collection="<%=ICDSearchEngineConfig.ICD_ENGINE_LIST_ICD_GROUPS%>" property="value" labelProperty="label"/>
						</logic:present>														
					</html:select>
				</td>
			</tr>
		</table>
		<div id="divDiseaseRpt" style="display: none;">
			<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="50%" class="srchHead">
						Sub Group
					</td>
					<td width="50%" class="srchVal">
						<%
							String strIcdSubGroupCodeOnChnage = "populateDiseases('"+ICDSearchEngineAJAXServlet.MODE_ALL_DISEASE+"')";
						%>
						<html:select name="ICDSearchEngineFB" property="strIcdSubGroupCode" tabindex="1" styleClass="comboNormal" onchange="<%=strIcdSubGroupCodeOnChnage%>">
							<html:option value="0">All</html:option>
						</html:select>
					</td>
				</tr>
			</table>
		</div>
		<div id="divSubDiseaseRpt" style="display: none;">
			<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="50%" class="srchHead">
						Disease
					</td>
					<td width="50%" class="srchVal">
						<html:select name="ICDSearchEngineFB" property="strIcdDiseaseCode" tabindex="1" styleClass="comboNormal">
							<html:option value="0">All</html:option>
						</html:select>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="50%" class="srchHead">
				<font color="#FF0000">*</font>Acrobat Reader or HTML
			</td>
			<td width="50%" class="srchVal">
				<input type="hidden" name="graphOrText" value="<%=Config.TEXT%>">
				<select name="strReportType" tabindex="1" class="comboNormal">
        			<option value="-1">Select Value</option>
        			<option value="<%=Config.PDF%>">Acrobat Reader</option>
        			<option value="<%=Config.RTF%>">HTML</option>	    
				</select>
			</td>
		</tr>
	</table>
	<table id="tblSearching" width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="100%" class="srchHead">
				<div align="center">
				<img id="imgSearchDiseaseName" name="imgSearchDiseaseName" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' alt="Generate Report" title="Generate Report" tabindex="1" 
					onclick="generateReport();" onkeypress="if(event.keyCode==13) generateReport();" >
				</div>
			</td>	
		</tr>
	</table>
</his:ContentTag>
</logic:equal>

<jsp:include page="/opd/icdsearchengine/icd_search_engine_hidden_fields.jsp" flush="true"></jsp:include>

</html:form>
</body>
</html>
