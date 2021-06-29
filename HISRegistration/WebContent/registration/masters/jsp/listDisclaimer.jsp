<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 12-Feb-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>

<link href="./../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/>

<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script>
function submitTile(obj)
{
	var val=obj.value;
	//alert(document.getElementsByName("disclaimerModel.strSelectDisclaimer")[0].value);
	document.forms[0].action=val+"DefaultDisclaimerMst.action";
	document.forms[0].submit();
	
}

</script>
</head>
<body>
 <div class="div-table"> 
 	<div class="div-table-row "> 
 		<div class="div-table-col width100 ">
 			<div align="right"><s:text name="select"/>&nbsp;<s:text name="global.disclaimer"/>
					<s:select name="disclaimerModel.strSelectDisclaimer" value="%{disclaimerModel.strSelectDisclaimer}" list="#{'DEFAULT':'Default Disclaimer','DEPT':'Department Wise Disclaimer','DEPTUNIT':'Department Unit Wise Disclaimer'}" 
 									onchange="submitTile(this)"></s:select> </div>
 		</div>
 	</div>
 </div>
</body>
<s:include value="/hisglobal/masterutil/master/lst_mstTemp_gbl.jsp"></s:include>
<cmbPers:cmbPers></cmbPers:cmbPers>
</html>