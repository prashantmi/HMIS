<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: Aadil Wasi
 	 Date			: Dec 2013 -->

<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


<%
	String str = "";
	String str1 = (String) request.getParameter("no_of_combo");
	String cmb[] = (String[]) request.getAttribute("combo");

	if (cmb != null) {
		for (int i = 0; i < cmb.length; i++) {
			str += "<input type='hidden' name='cmb' value=" + cmb[i]
					+ ">";
		}
	}

	//js 
	String strJs = (String) request.getAttribute("js");
	if (strJs == null)
		strJs = "";
	String temp = "";
	if (request.getAttribute("masterName") != null)
		temp = (String) request.getAttribute("masterName");
	else
		temp = "Master";
	String masterName = temp.split("#")[0];

	String cnt = "/masters/"
			+ (String) request.getSession().getAttribute("cnt_page");
%>

<TITLE><%=masterName%> List Page</TITLE>
<script>
	//The following variables used for view poup functionality
	var child = null;
	var popIndex = 0;
	var gblCntrlObj = null;
	//

	window.history.forward(1);
	//window.document.onkeydown = _suppressKeyPress;

	function _suppressKeyPress() {
		var keys = new Array();
		keys[0] = 'Ctrl';
		keys[1] = 'Backspace';

		saveCode = "";
		if (window.event.keyCode == 116) {
			saveCode = window.event.keyCode;
			window.event.keyCode = 505;
		}
		if (window.event.keyCode == 505) {
			alert('Ctrl has been disabled');
			return false;
		}
		if (window.event && window.event.keyCode == 8
				&& !window.document.activeElement.isTextEdit) {
			alert("window.event.keyCode" + window.event.keyCode);
			saveCode = window.event.keyCode;
			window.event.keyCode = 506;
		}
		if (window.event && window.event.keyCode == 506) {
			alert('Backspace has been disabled');
			return false;
		}
	}
</script>
<script language="JavaScript" type="text/javascript"
	src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" type="text/javascript" 
	src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>	
<script language="JavaScript" src='<%=strJs%>'></script>

<script>
	InitDragDrop();
</script>

<style type="text/css">


</style>

</head>
<%
	String ch = (String) request.getSession()
			.getAttribute("characters");
%>
<link href="../hisglobal/masterutil/css/master.css" rel="stylesheet"
	type="text/css">
<link href="../hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">
<link href="../hisglobal/css/layout.css" rel="stylesheet"
	type="text/css">



<body
	onload="fetchRecords(null,null,null,'<%=request.getSession().getAttribute("cnt_page")%>');"
	onFocus="checkPopUp_master();" onUnload="closePopUp_master();"
	class='bodycolor'>
	<s:form name="resourceForm" acceptcharset="UTF-8" enctype="multipart/form-data" method="GET">
<div class=' wrapper rounded'>
		 <div class="div-table" ALIGN="center">
			<div class="div-table-row">
				<div class="div-table-col label width100" >
							<DIV ID="message" ALIGN="center">
							</DIV>
				</div>
			</div>
		</div>	
		<div class="div-table" ALIGN="center" >			
			<DIV ID="start">
			</div>
		</div> 

 	  <DIV ID="searchid" ALIGN="right"></DIV>
 
		<div id='footer' style='display: none'></div>
		<div class="div-table-button" >
		<div class="div-table-row"><div class="div-table-col footerBar"></div></div>
		<div class="div-table-row"><div class="div-table-col emptyBar"></div></div>
		<div class="div-table-row" align='center'>
					<%
						if (request.getAttribute("BUTTONS") != null)
								out.println(request.getAttribute("BUTTONS"));
					%>
				</div>
		</div>
 

</div>

		<s:hidden name="totalpage"></s:hidden>


		<s:hidden name="record_per_page"></s:hidden>
		<s:hidden name="counter" value='0'></s:hidden>
		<s:hidden name="no_of_combo" value=''></s:hidden>
		<s:hidden name="actual_page_block"></s:hidden>
		<s:hidden name="nextBlock"></s:hidden>
		<s:hidden name="prevBlock">
		</s:hidden>
		<s:hidden name="divisionId">
		</s:hidden>
		<s:hidden name="divid"></s:hidden>
		<s:hidden name="hmode"></s:hidden>
		<s:hidden name="flag" value='1'></s:hidden>
		<s:hidden name="comboValue"></s:hidden>
		<s:hidden name='orderByName' id='orderById'></s:hidden>
		<input type='hidden' name='cnt_page'
			value='<%=request.getSession().getAttribute("cnt_page")%>'>
		<input type='hidden' name='module_id'
			value='<%=request.getSession().getAttribute("module_id")%>'>


		<%=str%>
		<script>
			var allImageObjectForDragTarget = document
					.getElementsByTagName("img");
			for ( var nTmpI = 0; nTmpI < allImageObjectForDragTarget.length; nTmpI++) {
				var strImageName = allImageObjectForDragTarget[nTmpI].src
						.split("/")[allImageObjectForDragTarget[nTmpI].src
						.split("/").length - 1];
				if (strImageName == "Modify.gif"
						|| strImageName == "Delete.gif"
						|| strImageName == "View.gif")
					addDropTarget(allImageObjectForDragTarget[nTmpI]);
			}
		</script>

	</s:form>
</body>

		 <div class="div-table" ALIGN="center">
			<div class="div-table-row">
				<div class="div-table-col control width100" >
					<h4><s:property value="message" /></h4>
</div>
</div>
</div>

</html>