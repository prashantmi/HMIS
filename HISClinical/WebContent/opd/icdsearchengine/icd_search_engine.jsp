
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.icdsearchengine.ICDSearchEngineConfig"%>

<title>ICD Search Engine</title>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngineformbean.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngine.js"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

window.onload = function()
{
	callOnLoad();
}

function callOnLoad()
{
	// Setting ICD Search Engine Setup
	ICDSearchEngine.setup('<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_DEFAULT%>',document);
	var objEngine = ICDSearchEngine.getICDSearchEngine();
	objEngine.populate();
	objEngine.setFrameSizes();
}
</script>

<frameset id="frmsetICDSearchEngine" rows="52,100%,0,0,18" border="0" bordercolor="#FFFFFF" framespacing="0" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
	<frame id="frmICDEngineHeader" name="frmICDEngineHeader" src="/HISClinical/opd/icdsearchengine/icdEngineHeader.cnt" 
		scrolling="no" noresize="noresize" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

	<frame id="frmICDEngineSearching" name="frmICDEngineSearching" src="/HISClinical/opd/icdsearchengine/icdEngineSearching.cnt" 
		scrolling="no" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

	<frame id="frmICDEngineResultVol1" name="frmICDEngineResultVol1" src="/HISClinical/opd/icdsearchengine/icdEngineResultVol1.cnt" 
		scrolling="auto" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

	<frame id="frmICDEngineResultVol3" name="frmICDEngineResultVol3" src="/HISClinical/opd/icdsearchengine/icdEngineResultVol3.cnt" 
		scrolling="auto" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

	<frame id="frmICDEngineFooter" name="frmICDEngineFooter" src="/HISClinical/opd/icdsearchengine/icdEngineFooter.cnt" 
		scrolling="no" noresize="noresize" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">
</frameset>