<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Created By 	: Mukund Vinayak
 	 Dated			: 21-Sep-2016 -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<title>Appointment Configuration Master</title>
<link href="/HISRegistration/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HISRegistration/hisglobal/css/layout.css" rel="stylesheet"	type="text/css">

<link href="/HISRegistration/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HISRegistration/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HISRegistration/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HISRegistration/hisglobal/css/basic.css">

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script language="Javascript" src="/HISRegistration/hisglobal/masterutil/js/validation.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="/HISRegistration/appointment/masters/js/AppointmentConfiguration.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>
function initializePopUp()
{
	//alert('test')
	if($('[name="patcatchangelogFlag"]')[0].value=="0")
	{
		$("#divpatchangelogId").hide();
	}
	else if ($('[name="patcatchangelogFlag"]')[0].value=="1")
	{
		$("#divpatchangelogId").show();
	}
}
function cancelPopupBut()
{		
		//alert("1");
	parent.closeModal();
}	
</script>
</head>

<body onload="initializePopUp();">
<div class="wrapper rounded">
	<s:form action="PrimaryCatChange" >

		<div class="div-table">
			<div id="divTitleId" class="div-table-col title width100 ">
				<div class="div-table-col  width100 "><s:text name="Audit"/>&nbsp;<s:text name="log"/></div>
			</div>	
		</div>
						
		<div class="div-table-listing rounded width100 height100">
			<div class="div-table-row listHeader">	
				<div class='div-table-col' style='width: 40%;'>Column Names</div>
				<div class='div-table-col' style='width: 20%;'>Previous Value</div>
				<div class='div-table-col' style='width: 20%;'>Current Value</div>
			</div>
			
			<%int i=0; %>
			
			<c:forEach items="${multiRowMap}" var="map"> <%-- ${map.value} --%>
				<c:forEach items="${map.value}" var="count">
					<b>Modified at: ${count.key.split('#')[0]} by ${count.key.split('#')[1]}</b>
					<c:forEach items="${count.value.split('@')}" var="insideMap">
					<div class="div-table-row listData multirow" id="TR_SHIFT_NEW_<%=i%>">
						<div class='div-table-col' style='width: 40%;'>
						  	  ${insideMap.split('#')[0]}
						</div>
						<div class='div-table-col' style='width: 20%;'>
							${insideMap.split('#')[1]}
						</div>
						<div class='div-table-col' style='width: 20%;'>
							${insideMap.split('#')[2]}
						</div>
					</div>
					</c:forEach>
				</c:forEach>
			</c:forEach>
			
			
		</div>
			 
		<div class="div-table-button">
			<div class="div-table-row footerBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" id="cancelPopupId" onclick="cancelPopupBut();"><span class="cancel"><s:text name="cancel"/></span></a> 
			</div>
		</div>
		
	<cmbPers:cmbPers></cmbPers:cmbPers>
		
	</s:form>
</div>
	
	<h3><s:property value="message" /></h3>
	
</body>
</html>