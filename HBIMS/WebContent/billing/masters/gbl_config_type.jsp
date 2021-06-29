
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head>
<meta charset=utf-8>
<title>Global Sub Group Master Add Page</title>





<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.min.css" rel="stylesheet">

<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>

<link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css">

<script type="text/javascript">

function openDiv(id)
{
	var newId="DIV"+id;
	if(document.getElementById(newId).style.display=="none")
		document.getElementById(newId).style.display="block";
	else
		document.getElementById(newId).style.display="none";
}

</script>
<style>

</style>
</head>
<body>
<html:form name="GblConfigTypeFB" action="/masters/GblConfigType" type="billing.masters.controller.fb.GblConfigTypeFB">

   
   <div class="row rowFlex reFlex">
	<div class="col-sm-12" align="right"> 
   		<bean:write name="GblConfigTypeFB" property="strHtmlString" filter="false" />
   	</div>
   </div>
	    
	   	<input type="hidden" name="hmode">

	 	<cmbPers:cmbPers/>
       
</html:form>

</body>
</html>