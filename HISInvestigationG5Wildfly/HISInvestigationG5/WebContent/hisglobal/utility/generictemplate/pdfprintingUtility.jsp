<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/utility/generictemplate/css/generic_chart.css"/>

<html>
<script type="text/javascript">
function setOnLoadEvent()
{
	if(document.getElementsByName('errorMode')[0].value=="TRY")
	{
		if(opener.document.getElementById('pdfPrintingHTMLData'))
		{		
			document.getElementsByName('htmlCode')[0].value=opener.document.getElementById('pdfPrintingHTMLData').innerHTML;
			document.getElementsByName('errorMode')[0].value="OK";
			document.forms[0].submit();
		}
		else
		{
			document.getElementsByName('errorMode')[0].value = "ERROR";
			document.getElementsByName('errorMessage')[0].value = "No Data Available for Printing";
			document.forms[0].submit();
		}
	}
	if('<bean:write name="PDFPrintingUtilityFB" property="modePrint"/>'=='PDF')
	{
		document.forms[0].submit();
	}
	if('<bean:write name="PDFPrintingUtilityFB" property="modePrint"/>'=='PDFGO')
	{
		document.forms[0].action = "/HISInvestigationG5/hisglobal/ConvertHTMLToPDF";
		document.forms[0].submit();
	}
}

function onChangeSubmit()
{
	document.forms[0].submit();
}

function pdfPrint(e)
{
	var url="/HISInvestigationG5/hisglobal/ConvertHTMLToPDF";
	openPopup(url,e,400,600);
}

function printPage()
{
	document.getElementById("tblModeSelect").style.display="none";
	window.print();
	document.getElementById("tblModeSelect").style.display="";
}
</script>

<body onload="setOnLoadEvent()">
	<html:form action="/printingTile">
		<html:hidden name="PDFPrintingUtilityFB" property="errorMode" />
		<html:hidden name="PDFPrintingUtilityFB" property="htmlCode" />
		<html:hidden name="PDFPrintingUtilityFB" property="waterMarkText" />
		<html:hidden name="PDFPrintingUtilityFB" property="errorMessage" />
		
		<logic:equal name="PDFPrintingUtilityFB" property="modePrint" value="PDFGO">
			<table width="100%" cellpadding="0" cellspacing="1" id="tblModeSelect">
				<tr>
					<td width="100%" class="tdfonthead">
						<div align="left">
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;Please Wait PDF Generation process is in progress...</b>
								</font>
						</div>
					</td>
				</tr>
			</table>
		</logic:equal>
		
		<logic:notEqual name="PDFPrintingUtilityFB" property="modePrint" value="PDFGO">
		<logic:notEqual name="PDFPrintingUtilityFB" property="errorMode" value="ERROR">
			<table width="100%" cellpadding="0" cellspacing="1" id="tblModeSelect">
				<tr>
					<td width="100%" class="tdfonthead">
						<div align="left">
							<logic:notEqual name="PDFPrintingUtilityFB" property="modePrint" value="ONLYHTML">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:radio name="PDFPrintingUtilityFB" property="modePrint" value="HTML" onchange="onChangeSubmit()"></html:radio>
								&nbsp;HTML
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="PDFPrintingUtilityFB" property="modePrint" value="PDF" onchange="onChangeSubmit()"></html:radio>
								&nbsp;PDF
							</font>
							</logic:notEqual>
							<logic:equal name="PDFPrintingUtilityFB" property="modePrint" value="ONLYHTML">
								<html:hidden name="PDFPrintingUtilityFB" property="modePrint" />
							</logic:equal>
							
							<img src='<his:path src="/hisglobal/images/btn-pnt.png"/>' onKeyPress="if(window.event.keyCode==13) printPage();" onClick='printPage();'>
							&nbsp;&nbsp;
							<img src='<his:path src="/hisglobal/images/btn-ccl.png"/>' onKeyPress="if(window.event.keyCode==13) window.close();" onClick='window.close();'>
						</div>
					</td>
				</tr>
			</table>
			<div id="pdfPrintingHTMLData">
				<bean:write name="PDFPrintingUtilityFB" property="htmlCode" filter="false"/>
			</div>
		</logic:notEqual>
		<logic:equal name="PDFPrintingUtilityFB" property="errorMode" value="ERROR">
			<table width="100%" cellpadding="0" cellspacing="1" id="tblModeSelect">
				<tr>
					<td width="100%" class="tdfonthead">
						<div align="center">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="PDFPrintingUtilityFB" property="errorMessage"/>
							</font>
						</div>
					</td>
				</tr>
			</table>
		</logic:equal>
		</logic:notEqual>
	</html:form>
</body>
</html>