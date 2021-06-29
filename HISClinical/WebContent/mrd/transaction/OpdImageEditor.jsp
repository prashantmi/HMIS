<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>


<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Shushrut Image Editor</title>

<script type="text/javascript">

function setAplletsize(h,w)
{
	var app=document.getElementsByName("imageApplet")[0];
	app.height=h;
	app.width=w;
	window.height=h;
	document.width=w;
} 

function saveImageinJS()
{
	document.getElementsByName("imageApplet")[0].saveImage();
	document.getElementById("imageApplet").saveImage();
	document.applets[0].saveImage();
	
	reportOpener("SAVED");
	closeWindow();
}

function cancelImageinJS()
{
	//alert(" Inside   cancelImageinJS() .....");
	reportOpener("CANCELED");
	closeWindow();
}

function closeWindow()
{
	window.close();
}

function reportOpener(mode)
{
	document.getElementById("status").value=mode;
	opener.CallFromImageEditor(document.getElementById("status").value);
}

function WhenClosed()
{
	if(document.getElementById("status").value=="NOTHING")
		reportOpener("CANCELED");
}

function Welcome()
{
	alert('Welcome !!!! \n Enjoy the Image Editor .....');
	
	alert('Please Wait !!!! \n Until Applet Loading is Completed .....');
	
	imageApplet.setActive();
}

</script>

</head>

<body onunload="WhenClosed()" onload="Welcome()">
	<input type="hidden" name="status" id="status" value="NOTHING" />
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr align="center">
			<td width="100%" align="center">
				<applet id="imageApplet" name="imageApplet" code="hisglobal.tools.imageeditor.applets.ImageEditorApplet" height="100" width="200"
					archive="his-image-editor-applet.jar" codebase="/HISClinical/hisglobal/tools/imageeditor/">
					
					<param name="inFileName" value="<bean:write name="OpdImageEditorFB" property="inFileName"/>" valuetype="data" />
					<param name="outFileName" value="<bean:write name="OpdImageEditorFB" property="outFileName"/>" valuetype="data" />
					<param name="inFilePath" value="<bean:write name="OpdImageEditorFB" property="inFilePath"/>" valuetype="data" />
					<param name="outFilePath" value="<bean:write name="OpdImageEditorFB" property="outFilePath"/>" valuetype="data" />
					<logic:notEqual name="OpdImageEditorFB" property="title" value="">
						<param name="title" value="<bean:write name="OpdImageEditorFB" property="title"/>" valuetype="data" />
					</logic:notEqual>
					<!-- Default Controls Description     F:L:E:R:C:T 
						F -> FreeHand Draw
						L -> Line Draw
						E -> Ecllipse Draw
						R -> Rectangle Draw
						C -> Fill Color
						T -> Text Draw -->
					<logic:notEqual name="OpdImageEditorFB" property="controlDesc" value="">
						<param name="controlDesc" value="<bean:write name="OpdImageEditorFB" property="controlDesc"/>" valuetype="data" />
					</logic:notEqual>
					<logic:notEqual name="OpdImageEditorFB" property="colorDesc" value="">
						<param name="colorDesc" value="<bean:write name="OpdImageEditorFB" property="colorDesc"/>" valuetype="data" />
					</logic:notEqual>
				</applet>
			</td>
		</tr>
		<tr align="center">
			<td width="100%" align="center">
				<input type="button" id="Save" value="OK" onclick="saveImageinJS()" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" id="Cancel" value="CANCEL" onclick="cancelImageinJS()" />
			</td>
		</tr>
	</table>
</body>

</html>