<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="HTML"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<HTML>

<head>

<meta http-equiv="Content-Type" content="text/HTML; charset=ISO-8859-1">
<link  href="/AHIMS/hisglobal/css/Color.css" rel="stylesheet" type="text/css" >
<link href="/AHIMS/hisglobal/css/master.css" rel="stylesheet" type="text/css" >
<link href="/AHIMS/ipd/css/transaction.css" rel="stylesheet" type="text/css">

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

function submitForm(hmode){
	document.getElementsByName('hmode')[0].value=hmode
	document.forms[0].submit()
}

function hideDetailsH(){
}

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
<his:TransactionContainer>
<body onload="getBedProperty();">
<logic:equal name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src='/enquiry/hospitalWardEnquiry.cnt' />" method="post">
</logic:equal>

<his:TitleTag name="Ward Enquiry">
	</his:TitleTag>
	<his:SubTitleTag name="Ward Detail">
	</his:SubTitleTag>
	<%String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE); 
	if(deskType==null || !deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK))
	{
	%>
	
<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
		<td width="50%" class="tdfonthead">
			<div align="right">
			<b><bean:message key="ward" /></b>&nbsp;
			</div>
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><bean:write name="hospitalWardEnquiryFB" property="ward"/></b>
		</td>
	</tr>
	   <tr>
		<td width="50%" class="tdfonthead">
			<div align="right">
			<b><bean:message key="department" /></b>&nbsp;
			</div>
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><bean:write name="hospitalWardEnquiryFB" property="department"/></b>
		</td>
	</tr>
	   <tr>
		<td width="50%" class="tdfonthead">
			<div align="right">
			<b><bean:message key="unit" /></b>&nbsp;
			</div>
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><bean:write name="hospitalWardEnquiryFB" property="departmentUnit"/></b>
		</td>
	</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
	
	<!--<tr>
		<td width="33%"  class="tdfonthead">
			<div align="center">
			<b><bean:message key="building" /></b>
			</div>
		</td>
		<td width="33%"  class="tdfonthead">
		<div align="center">
			<b><bean:message key="block" /></b>
			</div>
		</td>
		<td width="33%"  class="tdfonthead">
		<div align="center">
			<b><bean:message key="floor" /></b>
			</div>
		</td>
		
	</tr>
	<tr>
		<td width="33%"  class="tdfont">
			<div align="center">
			<bean:write name="hospitalWardEnquiryFB" property="locationBuilding"/>
			</div>		
		</td>
		<td width="33%" class="tdfont">
			<div align="center">		
			<bean:write name="hospitalWardEnquiryFB" property="locationBlock"/>
			</div>		
		</td>
		<td width="33%" class="tdfont">
			<div align="center">		
			<bean:write name="hospitalWardEnquiryFB" property="locationFloor"/>
			</div>	
		</td>
	</tr>
	--></table>
	</his:ContentTag>
	<%} %>
	<his:ContentTag>
	<br>
	<bean:write name="hospitalWardEnquiryFB" property="bedProperty" filter="false" />
	
</his:ContentTag>
<div id="bedStatusDiv"></div>
<HTML:hidden name="hospitalWardEnquiryFB" property="strWinResize"/>
<HTML:hidden name="hospitalWardEnquiryFB" property="hmode"/>
<HTML:hidden name="hospitalWardEnquiryFB" property="isDirectCall"/>

<logic:equal name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
</form>
</logic:equal> 
<his:ButtonToolBarTag>
	<logic:equal name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('unspecified');" tabindex="1" onclick="submitForm('unspecified');">
	</logic:equal>
	<logic:notEqual name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
	<%
	if(deskType==null || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK))
	{%>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" tabindex="1" onclick="submitToDesk('NEW','NEW');">
	<%}else{ %>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick="submitForm('NEW');">
	<%} %>
	</logic:notEqual>
</his:ButtonToolBarTag>
</body>
</his:TransactionContainer>
</HTML>
