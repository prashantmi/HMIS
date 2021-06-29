<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@page import="hissso.config.HISSSOServerConfig"%>
<%@page import="hislogin.config.HISLoginConfig"%>

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

<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/easyui.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>
<script>
var menuJSONObj, allMenus;
$(document).ready(function(){
	initialiseMenuSearch();	
	
	 $("#menuSearchResult").autocomplete({
		 source: allMenus,
		 minLength: 0
		 });
	 $("#menuSearchResult").focus();
	 $("#idMenuSearch").click(function(){
		var menu=$("#menuSearchResult").val();
		var url;
		var index=allMenus.indexOf(menu);
		if(index== -1)
			alert('This Menu Does not Exists! ');
		else{
			url= menuJSONObj.menuURLs[index];
			parent.callMenu(url,menu);
			parent.closeModal();
		}
	 });
	 $("#menuSearchResult").keypress(function(e) {
		    if(e.which == 13) {
		    $("#idMenuSearch").click();
		    }
		});
});

function initialiseMenuSearch(){
	try{
		
		menuJSONObj= JSON.parse($("#menuJSON",window.parent.document).val());
		allMenus= menuJSONObj.menuNames;
		
		} catch(err){
			alert('error occured in initializing menu search ' + err.message);
		}
}


</script>
<!-- Style to limit max size of menu search combo -->
<style>
.ui-autocomplete {
max-height: 100px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
z-index: 2000;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
* html .ui-autocomplete {
height: 100px;
}
</style>
</head>
<body>
<center>


<div class="wrapper rounded">

  
	<div class="div-table" >
		<div class="div-table-row " >
			<div class="div-table-col title width100 " >
					Search Menu
			</div>
		</div>
		<div class="div-table-row " >
			<div class="div-table-col width20 " >
					Search For :
			</div>
			<div class="div-table-col  width80 " >
					<input id="menuSearchResult">
			</div>
		</div>
		
	</div>
	<div class="div-table-button">
		<div class="div-table-row footerBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row" align="center">
			<a href="#" class="smallButton" tabindex="1" id="idMenuSearch"><span> Go </span></a>
		</div>
	</div>

<div id="divElementErrorsId" class="alertMessage"><s:actionerror/></div>




</div>
</center>
</body>
</html>