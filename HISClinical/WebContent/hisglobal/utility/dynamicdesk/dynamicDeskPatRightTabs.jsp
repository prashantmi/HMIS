<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

function onMenuLoad11()
{
	alert("hi");
	 var ht=Math.round($(parent.window).innerHeight()) + "px"; //140px- 134px || 146px  ////changed on 29-09-2015 from 146px to 176px due to TELANGANA Project Menu's Issue
    //alert('slimscroll on right menu:- '+ht);
    $('#divDeskMenuPat').slimscroll({
  
  //added by Akash Singh for changing rail color dated on 04-03-2015
    	color: '#0ff',
    	opacity: 1,
      
      railDraggable: true,
      height: ht
    });
    var ht1=Math.round($(parent.window).innerHeight()-400) + "px";
    $('#divDeskLeftMenuPat').slimscroll({
      	
	     //added by Akash Singh for changing rail color dated on 04-03-2015
	      	color: '#0ff',
	      	opacity: 1,
	       
	        railDraggable: true,
	        height: ht1
	      }); 
} 



</script>
</head>

<body style="margin: 0px;" onload="onMenuLoad11()">
<div id="divDeskRightMenuPat" style="margin-top: 1px; width: 100%;" align="center">
	<div id="divDeskMenuPatRight" class="div-table width100" style="margin: 0px;">
	</div>
</div>


</body>
</html>