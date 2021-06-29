<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<his:css src="/hisglobal/css/tab.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<title>Print</title>
<SCRIPT LANGUAGE="JavaScript"> 
window.onload=function(){
	document.forms[0].htmlCode.value=opener.document.forms[0].htmlCode.value
	document.forms[0].copies.value=opener.document.forms[0].copies.value
	document.forms[0].isCopiesEditable.value=opener.document.forms[0].isCopiesEditable.value
	document.forms[0].action="/HISInvestigationG5/hisglobal/utility/print/readPdfServlet?mode=1"
	document.forms[0].method="post"
	document.forms[0].submit();
}
</script>
<html>
<form>
<input type="hidden" name="htmlCode">
<input type="hidden" name="copies">
<input type="hidden" name="isCopiesEditable">
</form>
</html>

