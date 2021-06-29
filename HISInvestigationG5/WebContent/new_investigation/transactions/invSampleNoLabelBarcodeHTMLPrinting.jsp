
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.transactions.controller.fb.SampleCollectionFB"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 

<his:javascript src="/hisglobal/js/barcode_code39.js" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script>
function DrawCode39Barcode(data, checkDigit)
		{
	
			//alert("inside barcode_code39.js");
			return DrawHTMLBarcode_Code39(data,checkDigit,"yes",
								"in", 0,1.5,0.2,2,
								"bottom","left", "",
								"black","white"); 
		}
function setBarCode()
{
	printitWithoutDialog();
	//alert(get_object("barCodeGenSize").value)
	for(var z=0;z<get_object("barCodeGenSize").value;z++)
		{
		//alert("i value"+z);
	//alert("Set BarCode Size"+document.getElementsByName("barCodeGenSize")[0].value);divBarCodeControlAll
	get_object(z+"divBarCodeControl").innerHTML=DrawCode39Barcode(get_object(z+"divBarCodeControl").innerHTML, '0');
	//alert("i value sec"+z);
	get_object(z+"divBarCodeControlAll").innerHTML=DrawCode39Barcode(get_object(z+"divBarCodeControlAll").innerHTML, '0');
		}
	//window.print();
	printitWithoutDialog();
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
}//printit
</script>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 
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
<%=((String)session.getAttribute("sampleNoLabelBarCodeString")) %>
</body>
</html>