<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="ipd.IpdConfigUtil"%>
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
<%-- <%-- <script type="text/javascript" src="../../hisglobal/js/barcode_code39.js"></script> --%>
<%-- <script type="text/javascript" src="../../hisglobal/js/jquery-barcode.js"></script> --%>

<script language="JavaScript" src="../../ipd/js/patientAdmission.js"></script>
<script language="Javascript" src="../../hisglobal/js/barcode_code39.js"></script>

<%
IpdConfigUtil util = new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());

int noofSlip=Integer.parseInt(util.getStrNoOfBarcodeSlip());  
 %>

<script type="text/javascript">


function DrawCode39Barcode(data, checkDigit)
{
	//alert("inside barcode_code39.js");
	return DrawHTMLBarcode_Code39(data,checkDigit,"no",
					"in", 0,2,0.3,4,//0,1,0.2,2,//these parameters were changed by Mukund on 23.01.2017 for making barcode large enough to be scannable using barcode scanner
					"bottom","left", "",
					"black","white"); 
	//return DrawHTMLBarcode_Code39(data,checkDigit,"no","in", 0,2,0.2,3,"bottom","left", "","black","white"); 
}	


	function setBarCode()
	{
		<% if (noofSlip>0) { %>
		
		for (var i=1; i<=<%=noofSlip%>;i++)
		{	
		get_object("divBarcodeSlipControlA_"+i).innerHTML=DrawCode39Barcode(get_object("divBarcodeSlipControlA_"+i).innerHTML, 0);
		}
		
		//window.print();
		//var t=setTimeout("printSlip1()",2000);
		printitWithoutDialog();
		<% } %>
	}

	
	function setBarCode1()
	{
		// $('#divBarcodeSlipControl')[0].innerHTML;
		for (var i=1; i<=4;i++)
		{	
			var crNo =	get_object("divBarcodeSlipControlA_"+i).innerHTML;   // $('#divBarcodeSlipControl')[0].innerHTML;
			var crNoo =	get_object("divBarcodeSlipControlB_"+i).innerHTML;
	        alert(crNo);
			$("#divBarcodeSlipControlA_"+i).barcode(
				crNo, // Value barcode (dependent on the type of barcode)
				"code128" // type (string)
				); 
			/*$("#divBarcodeSlipControlB_"+i).barcode(
					crNoo, // Value barcode (dependent on the type of barcode)
					"code128" // type (string)
					); */
		}
		printitWithoutDialog();
		//window.close();    
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
<body onload="setBarCode()" onfocus="window.close()" marginheight="0" marginwidth="0">




<div id="barcodeDialog">

<table cellspacing="0px" cellpadding="0" >
<tr>
<td class="SLIPCONTROL" width="100%"><%=((String)session.getAttribute("sampleNoLabelBarCodeString")) %></td>
</tr>

</table>

</div>






</body>
</html>