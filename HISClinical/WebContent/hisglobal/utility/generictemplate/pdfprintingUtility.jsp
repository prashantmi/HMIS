<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/utility/generictemplate/css/generic_chart.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html>
<script type="text/javascript">
function setOnLoadEvent()
{
	if(document.getElementsByName('errorMode')[0].value=="TRY")
	{
		if(opener.document.getElementById('pdfPrintingHTMLData'))
		{		
			document.getElementsByName('htmlCode')[0].value=opener.document.getElementById('pdfPrintingHTMLData').innerHTML;
           var htmlData = document.getElementsByName('htmlCode')[0].value;
			//Base64 Encryption-----------by Vasu on 28.Feb.18
            var enc = window.btoa(htmlData);
            //alert(enc);
         //  removeAllEvenetsForSecurity(document.getElementsByName('htmlCode')[0].value);
            document.getElementsByName('errorMode')[0].value="OK";
			document.getElementsByName('htmlCode')[0].value = enc;
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
		var htmlData = document.getElementsByName('htmlCode')[0].value;
		//Base64 Encryption-----------by Vasu on 28.Feb.18
	    var enc = window.btoa(htmlData);
		document.getElementsByName('htmlCode')[0].value = enc;
		document.forms[0].action = "/HISClinical/hisglobal/ConvertHTMLToPDF";
		document.forms[0].submit();
	}
}

function removeAllEvenetsForSecurity(htmlCode)
{
		var el = document.createElement( 'html' );
		el.innerHTML = "<html><head></head><body>"+htmlCode+"</body></html>";
		
		var all = el.getElementsByTagName("input");
		for (var i=0, max=all.length; i < max; i++) 
		{
		     //alert(all[i].getAttribute('onclick'));
			 all[i].removeAttribute("onclick");
			 all[i].removeAttribute("onchange");			     
			 all[i].removeAttribute("onkeypress");
			 all[i].removeAttribute("onblur");
			 //all[i].removeAttribute("onload");
			 all[i].removeAttribute("onmouseover");
			 all[i].removeAttribute("onmousedown");
			 all[i].removeAttribute("ondblclick");
			 all[i].removeAttribute("onfocus");
			 all[i].removeAttribute("onkeydown");
			 all[i].removeAttribute("onkeyup");
			 all[i].removeAttribute("onmouseout");
			 all[i].removeAttribute("onmouseup");
			 all[i].removeAttribute("onmousemover");
			 all[i].removeAttribute("onselect");
		}
	
		var alltxtArea = el.getElementsByTagName("textarea");
		for (var i=0, max=alltxtArea.length; i < max; i++) 
		{
		     alltxtArea[i].removeAttribute("onclick");
			 alltxtArea[i].removeAttribute("onkeypress");
		}
		var allSelect = el.getElementsByTagName("select");
		for (var i=0, max=allSelect.length; i < max; i++) 
		{
		     allSelect[i].removeAttribute("onclick");
			 allSelect[i].removeAttribute("onchange");
			 allSelect[i].removeAttribute("onselect");
		}		
		var htmlVal = el.innerHTML;;
   		htmlVal = htmlVal.replace(/(<head>|<\/head>)/g, "");
   		htmlVal = htmlVal.replace(/(<body>|<\/body>)/g, "");
		document.getElementsByName('htmlCode')[0].value=htmlVal;
		alert(document.getElementsByName('htmlCode')[0].value);

}

function onChangeSubmit()
{ 
	var htmlData = document.getElementsByName('htmlCode')[0].value;
	//Base64 Encryption-----------by Vasu on 28.Feb.18
    var enc = window.btoa(htmlData);
	document.getElementsByName('htmlCode')[0].value = enc;
	document.forms[0].submit();
}

function pdfPrint(e)
{
	var url="/HISClinical/hisglobal/ConvertHTMLToPDF";
	openPopup(createFHashAjaxQuery(url),e,400,600);
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
							<%-- <logic:notEqual name="PDFPrintingUtilityFB" property="modePrint" value="ONLYHTML">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:radio name="PDFPrintingUtilityFB" property="modePrint" value="HTML" onchange="onChangeSubmit()"></html:radio>
								&nbsp;HTML
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="PDFPrintingUtilityFB" property="modePrint" value="PDF" onchange="onChangeSubmit()"></html:radio>
								&nbsp;PDF
							</font>
							</logic:notEqual> --%>
							<logic:equal name="PDFPrintingUtilityFB" property="modePrint" value="ONLYHTML">
								<html:hidden name="PDFPrintingUtilityFB" property="modePrint" />
							</logic:equal>
							
							<img src='<his:path src="/hisglobal/images/btn-pnt.png"/>' onKeyPress="if(window.event.keyCode==13) printPage();" onClick='printPage();'>
							&nbsp;&nbsp;
							<img src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' onKeyPress="if(window.event.keyCode==13) window.close();" onClick='window.close();'>
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