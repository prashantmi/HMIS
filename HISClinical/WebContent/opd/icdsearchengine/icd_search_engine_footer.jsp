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

<%@page import="opd.icdsearchengine.action.ICDSearchEngineAJAXServlet"%>
<%@page import="opd.icdsearchengine.ICDSearchEngineConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ICD Search Engine</title>

<his:css src="/opd/icdsearchengine/css/icdSearchEngine.css"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<script type="text/javascript" src="/HISClinical/opd/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/opd/icdsearchengine/js/validations.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngineformbean.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngineAjax.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngine.js"/>


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
	<%
		if(ICDSearchEngineConfig.ICD_ENGINE_AUTO_COMPLETE_INDEX_TERM_SHOW.equals(ICDSearchEngineConfig.YES))
		{
	%>
		getMainIndexTermList('<%=ICDSearchEngineAJAXServlet.MODE_AUTO_INDEX_TERM_LIST%>');
	<%
		}
	%>
}
</script>

</head>

<body>

<html:form action="/icdsearchengine/icdEngineFooter">

<table id="tblFooter" width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td class="label">
			<div align="left">
				<label class="colorBlock_0">&nbsp;&nbsp;</label> Diseases
			</div>
		</td>
		<td class="label">
			<div align="left">
				<label class="colorBlock_1">&nbsp;&nbsp;</label> Includes
			</div>
		</td>
		<td class="label">
			<div align="left">
				<label class="colorBlock_3">&nbsp;&nbsp;</label> Synonyms
			</div>
		</td>
	</tr>
</table>

<!-- <table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td class="tdfonthead">
			<div align="left">
				<a>
					Conventions
				</a>
			</div>
		</td>
		<td width="50%" class="tdfonthead">
			<div align="right">
				<a>
					Guidelines
				</a>
			</div>
		</td>
	</tr>
</table>
 -->
<div id="divIndexTermLst" style="display: none;">
<select name="cboMainIndexTermList"></select>
</div>
</html:form>

</body>

</html>