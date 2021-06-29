<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="HTML"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>

<head>

<meta http-equiv="Content-Type" content="text/HTML; charset=ISO-8859-1">

<link href="/AHIMS/ipd/css/transaction.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	Type="text/css">

<style type='text/css'>

.bedbutton{
border          : 3px #B5B5B5 outset;
padding         : 25 20px;
color           : black;
cursor          : pointer ;
cursor          : hand;
text-align      : center;
text-decoration : none;
font            : normal 13px Verdana;
}

.bedbutton:visited, .bedbutton:hover, .bedbutton:active{
color: white;
}
</style>
<title>Bed Status</title>
<script language="Javascript" src="/AHIMS/hisglobal/js/util.js"></script>
<script language="JavaScript" src="/AHIMS/hisglobal/masterutil/js/master.js"></script>

<script language="Javascript" src="/AHIMS/hisglobal/js/toolTip.js"></script>
<script type="text/javascript">
function getAjaxResponse(res,mode){
	if(mode=='101'){
		document.getElementById("bedStatusDiv").innerHTML=res;
	}
}

function hideDetailsH(obj)
{
 document.getElementById("minusonLineIdH").style.display="none";
 document.getElementById("plusonLineIdH").style.display="block";
 document.getElementById("bedStatHelpDtl").style.display="none";
}
function showDetailsH()
{
 document.getElementById("plusonLineIdH").style.display="none";
 document.getElementById("minusonLineIdH").style.display="block";
 document.getElementById("bedStatHelpDtl").style.display="block";
}

function resize_popUp()
{
 //alert("added height->"+document.forms[0].strWinResize.value);
 var newHeight=document.forms[0].strWinResize.value;
 window.resizeTo('400',newHeight);
}

function getBedProperty(){
	var hmode = "BEDPROPERTIES"; 
	var url="/AHIMS/ipd/transactions/IpdCNT.cnt?hmode=" +hmode+"&modPopUp=<%=request.getAttribute("modPopUp")%>";
	ajaxFunction(url,"101");
}
//onUnload="window.opener.closeChild();"//x


</script>
</head>
<title>Bed Status</title>

<body onLoad="resize_popUp();getBedProperty();">
<HTML:form action="/hospitalDepartmentEnquiry" method="post">
	<bean:write name="HospitalDepartmentEnquiryFB" property="bedProperty"
		filter="false" />
<div id="allData"></div>
<div id="bedStatusDiv"></div>
<HTML:hidden name="HospitalDepartmentEnquiryFB" property="strWinResize"/>
<HTML:hidden name="HospitalDepartmentEnquiryFB" property="hmode"/>
</HTML:form>
</body>
</HTML>