<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>



<% 
/* 
 * Module Name: Global - Common 
 * Name of Process: Under Construction 
 * Name of Developer: Sh.Ashwini Mishra
 * Date of Creation: 29-05-2014
 * Name of Last Modifier: Sh. Ashwini Mishra
 * Last Modification Date: 29-05-2014 
 * Version: 1.0
 */
%>

<!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--<%@ taglib uri="/WEB-INF/csrfguard.tld" prefix="csrf" %>--%> 
<%@page %>

<html>
<head>

<!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="../../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../../hisglobal/css/basic.css">
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="Javascript" src="/HISPis/hisglobal/js/security.js"></script>

<script language="JavaScript" type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jquery-migrate-1.1.1.js"></script>



<script type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='../../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../../hisglobal/masterutil/js/jquery/basic.js'></script>
<script type='text/javascript' src='../../../hisglobal/masterutil/js/jquery/jquery.loadJSON.js'></script>
<script type='text/javascript' src='../../../hisglobal/masterutil/js/jquery/jquery.validate.js'></script>
<script type='text/javascript' src='../../../hisglobal/js/AnchorPosition.js'></script>
<script type='text/javascript' src='../../../hisglobal/js/PopupWindow.js'></script>
<script type="text/javascript" src="../../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="../../../hisglobal/utility/generictemplate/js/date_validator.js"></script>

<!--  theme changer start-->
<link rel="stylesheet" type="text/css" title="blue" href="/HISPis/hisglobal/css/buttons.css" />
<link rel="stylesheet" type="text/css" title="blue" href="/HISPis/hisglobal/css/layout.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HISPis/hisglobal/css/buttons-green.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HISPis/hisglobal/css/layout-green.css" />
<link rel="stylesheet" type="text/css" title="normal" href="/HISPis/hisglobal/css/normalfont.css" />
<link rel="alternate stylesheet" type="text/css" title="large" href="/HISPis/hisglobal/css/largefont.css" />
<link rel="alternate stylesheet" type="text/css" title="small" href="/HISPis/hisglobal/css/smallfont.css" />
<script type="text/javascript" src="/HISPis/hisglobal/js/themechanger.js"></script>
<!--  theme changer end-->	
<script type="text/javascript" src="/HISPis/hisglobal/js/pisGlobal.js"></script>


<style type="text/css">
body
    {
        /*background:url(/HIS/hisglobal/images/nonclinical/underConstruction1.jpg) no-repeat center center fixed;*/
        background:url(/HIS/hisglobal/images/nonclinical/underConstruction.jpg) no-repeat center center fixed;
        
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        margin: 0;
        padding: 0;
    }
</style>
<script>
$(document).ready(function(){
	$('#backToDeskId').click(function(e){
		window.location="/HISConfig/eDesk/openDeskMainDeskAction.action?deskId=10001";
		});
	});
</script>
</head>
<body>

		<div class="div-table-button" style="position: fixed;top: 1%"> 
		<br>
			<center>			
				<b style="font-size: xx-large;;font-style: italic;font-weight: bold;color: red;">This page is under construction</b>
			</center>
			<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				
				<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
				</div>
					
		</div>		
		<div class="div-table-button" style="position: fixed;bottom: 1%;display: none;">
				<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
				</div>		
				<br>						
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center" style="display:none">
					<a href="#" class="button" id="backToDeskId"><span class="back"><s:property value="getText('emp.posting.button.back.to.desk')" /></span></a>
				</div>		
				<br>	
		</div>

</body>		
</html>