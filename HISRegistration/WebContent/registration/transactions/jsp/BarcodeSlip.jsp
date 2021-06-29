<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet"	type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>

<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<%-- <script type="text/javascript" src="../../hisglobal/js/barcode_code39.js"></script> --%>
<script type="text/javascript" src="../../hisglobal/js/jquery-barcode.js"></script>

<script type="text/javascript">
	function DrawCode39Barcode(data, checkDigit)
	{
				return DrawHTMLBarcode_Code39(data,checkDigit,"yes",
									"in", 0,1.5,0.2,2,
									"bottom","left", "",
									"black","white"); 
	}
	/* function setBarCode()
	{
		printitWithoutDialog();
		window.close();
	} */
	function setBarCode()
	{
		var crNo = $('#divBarcodeSlipControl')[0].innerHTML;
		$("#divBarcodeSlipControl").barcode(
				crNo, // Value barcode (dependent on the type of barcode)
				"code128" // type (string)
				); 
		printitWithoutDialog();
		window.close();    
	}
	// The code below does work  and is tested with IE 5.5 SP2.
	function printitWithoutDialog()     
	{   
			if ((navigator.appName == "Netscape"))
			{
				window.print() ;
			}
			else
			{
				var WebBrowser = '<OBJECT ID="WebBrowser1" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"> </OBJECT>';
				document.body.insertAdjacentHTML('beforeEnd', WebBrowser);
				WebBrowser1.ExecWB(6, -1);
				WebBrowser1.outerHTML = "";
			}
	}
</script>
<head>

<style media="print">
.donotprint
{
display:none;
}
.divwithborder {
	border: thin solid #FF0000;
	color:#FF0000;
}
.divwithborderblack {
	border: thin solid black;
	color:black;         
}
</style>
<style  media="screen">
.donotprint
{
display:block;
width:100%;
}
.divwithborder {
	border: thin solid #FF0000;
	color:#FF0000;
}
.divwithborderblack {
	border: thin solid black;
	color:black;
}
</style>

</head>
<body onload="setBarCode()" marginheight="0" marginwidth="0">
<div id="barcodeDialog">
	<%=((String)session.getAttribute("sampleNoLabelBarCodeString")) %>
</div>
</body>
</html>