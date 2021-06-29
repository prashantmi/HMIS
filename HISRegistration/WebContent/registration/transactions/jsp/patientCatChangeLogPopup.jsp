<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page %>
<%@ page import="registration.config.RegistrationConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<style type="text/css">
.border .div-table-col{
border: 1px solid black;
}
</style>

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>


<script type="text/javascript">

function initializePopUp(){
	//alert('test')
	
	if($('[name="patcatchangelogFlag"]')[0].value=="0"){
		$("#divpatchangelogId").hide();
	    }
	else if ($('[name="patcatchangelogFlag"]')[0].value=="1"){
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
<center>

<s:form action="PrimaryCatChange">
    
    <div class="wrapper rounded">
	    
		<div class="div-table">
			  <div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
						<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="change"/>&nbsp;<s:text name="log"/>  
				</div>
			  </div>
				

				<s:if test="%{#session.arrEpisodeVOpatCatChangeLog.length>0}">

		        <div class="div-table-listing rounded width100 height100">
				<div class="div-table-row listHeader">
					<div class="div-table-col width14" align="center">
					<s:text name="crNO" />
					</div>
					<div class="div-table-col width31" align="center">
					<s:text name="previous" />&nbsp;<s:text name="category" />			
					</div>
					<div class="div-table-col width30" align="center">
					<s:text name="new" />&nbsp;<s:text name="category" />						
					</div>
					<div class="div-table-col width17" align="center">
					<s:text name="date" />					
					</div>
					<div class="div-table-col width21" align="center">
					<s:text name="user" />&nbsp;<s:text name="name" />					
					</div>
				</div>				
			
				<s:iterator status="status" value="%{#session.arrEpisodeVOpatCatChangeLog}">
				<div class="div-table-row listData">
					<div class="div-table-col width20" align="left">
						<s:property value="%{patCrNo}"/>
					</div>
					
					
					<div class="div-table-col width20" align="center">
					
						<s:property value="%{patPrevPrimaryCat}"/>
					</div>
					
					
					<div class="div-table-col width20" align="center">
						<s:property value="%{patNewPrimaryCat}"/>
					</div>
					<div class="div-table-col width22" align="center">
						<s:property value="%{entryDate}"/>
					</div>
					<div class="div-table-col width18" align="center">
						<s:property value="%{username}"/>
					</div>
				</div>
				
				</s:iterator>
				
				</div>
				</s:if>
				<s:else>
					<div class="div-table-listing rounded width100 height100">
					<div  class="div-table-row " align="center">
					<div  class="div-table-col width100" align="center">
					
					<img id='imgNoDataId'   src="../../hisglobal/images/NoData.png" /> 
			    
			    	</div>
			    	</div>
					</div>
				</s:else>

				
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
		</div>
	
</s:form>
</center>
</body>
</html>