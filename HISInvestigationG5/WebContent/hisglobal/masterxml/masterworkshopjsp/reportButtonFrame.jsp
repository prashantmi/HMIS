<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>



<%@page import="hisglobal.presentation.WebUTIL"%>
<html>
	<head>
		<title>Welcome to HIS</title>
		<his:css src="/hisglobal/css/masterXml.css" />
		<his:css src="/hisglobal/css/Color.css" />

		<script Language=javascript>
function printPage()
{

	
	var frameElement = parent.document.getElementById("reportPrintFrame");
	var win = frameElement.contentWindow;


	win.print();


}

function submitpage(mode)
{
	
	//parent.document.getElementById('reportPrintFrame').contentDocument.getElementsByName('hmode').value=mode;
    //document.mstHandlerListForm.hmode.value=mode;
    document.getElementsByName('hmode')[0].value=mode;
    document.forms[0].submit();
 
}
		</script>
	</head>
	
	<body>	

<html:form action="/masterWorkshop/mstHandlerListAction" method="post" target="_parent">

	<table cellpadding="0" cellspacing="1" width="100%" >
		<thead style='DISPLAY: TABLE-HEADER-GROUP' >
			<tr>
				<td>
					<div align="right" id="noPrint">
						<a>
							<img src='<his:path src="/hisglobal/images/btn-pnt.png"/>' onKeyPress="if(event.keyCode==13)printPage();" onClick='printPage();' tabindex="1">
						</a>
						<a>
							<img src='<his:path src="/hisglobal/images/btn-ccl.png"/>' onKeyPress="if(event.keyCode==13)submitpage('CANCEL');" onClick='submitpage("CANCEL");' tabindex="1">
						</a>
					</div>
				</td>
			</tr>
	</table>	
<input type="hidden" name="hmode" value="LIST">
</html:form>
	</body>
	

</html>