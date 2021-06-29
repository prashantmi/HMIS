<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="com.sun.org.apache.xalan.internal.utils.XMLSecurityPropertyManager.Property"%>
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
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>

</head>
<body onload="pageOnLoad()">
<center>

<div class="wrapper rounded">

<s:form action="LgnFtr">
<%String s1="",s2="";
String strPreviousDate="",s4="";

%>
<s:if test="%{varStatus == 'INITIAL'}">
	<div class="div-table-listing" >
		<div class="div-table-row width100" >
			<div class="div-table-col title width100 " >
				User Log Details
			</div>
		</div>
		<div class="div-table-col title width100 " >
			<div class="div-table-col title width10 " >
				S.No.
			</div>
			<div class="div-table-col title width10 " align="left">
					Login Status
				</div>
				<div class="div-table-col title width20 " align="center">
					Login Date&nbsp;
					& Login Time
				</div>
				
				<div class="div-table-col title width20 " align="center">
					Logout Date&nbsp;
					& Logout Time
				</div>
				<div class="div-table-col title width10 " align="center">
					IP Address&nbsp;
				</div>
				<div class="div-table-col title width20 " align="right">
				    Counter Name
				</div>
				
		</div>
	
		<s:iterator value="#session[@hislogin.config.HISLoginConfig@KEY_LOGIN_LOG]" status="varItStatus">
			<div class="div-table-row width100 " >
				<div class="div-table-col width10 ">
				${varItStatus.index+1}</div>
				<div class="div-table-col width10 ">
					&nbsp<s:property value="varLoginStatus"/>
				</div>
				<div class="div-table-col width10 " align="right">
					<%
					s2 = (String)request.getAttribute("varUserLoginDate");
					if(!(s1.equals(s2)))
					{%>
						<s:property value="varUserLoginDate"/>
				     <%}
				    else{%>-<%}
					s1=s2;%>
				 <!--   <%=s1%>-->
				 </div>&nbsp;
				<div class="div-table-col width10 " align="left">
				&nbsp;&nbsp;
			    <s:property value="varUserLoginTime"/>
				</div>
				<div class="div-table-col width10 "  align="right">
			       <s:if test="%{(varUserLogoutDate!=null && varUserLogoutDate!='') && #strPreviousDateForJsp!=varUserLogoutDate}">
						<s:property value="varUserLogoutDate"/>
						<s:set name="strPreviousDateForJsp" value="varUserLogoutDate"></s:set>
				</s:if>
				<s:elseif test="%{varUserLogoutDate==null || varUserLogoutDate==''}"></s:elseif>
				<s:else>
					-
				</s:else>
			         &nbsp;
				</div>
				<div class="div-table-col width10 " align="left">
					&nbsp;
					<s:property value="varUserLogoutTime"/>
				</div>
				<div class="div-table-col width10" align="center">
					&nbsp;<s:property value="varIPAddress"/>
				</div>
				<div class="div-table-col width20" align="right">
					&nbsp;<s:property value="varCounterName"/>
				</div>
			</div>
			</s:iterator>
	</div>
	
	<div class="div-table-button">
		<div class="div-table-row footerBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row" align="center">
			<a href="#" class="button" tabindex="1" id="idAll"><span class="next">All Logs</span></a>
			<a href="#" class="button" tabindex="1" id="idCancel"><span class="cancel">Cancel</span></a>
		</div>
		
	</div>
</s:if>

<s:elseif  test="%{varStatus == 'USER_LOG_DETAILS'}">
<div class="div-table-listing" >
		<div class="div-table-row " >
			<div class="div-table-col title width100 " >
				All User Logs
			</div>
		</div>
		<div class="div-table-row width100 " >
				<div class="div-table-col width25 label" align="right">
				From Date</div>
				<div class="div-table-col width20 control" align="left">
				<input type="text" name="frDate" id="frDateId" value='<s:property value="frDate"/>'>
				</div>
				<div class="div-table-col width20 label" align="right">
					To Date
				</div>
				<div class="div-table-col width20 control" align="left">
				<input type="text" name="toDate" id="toDateId" value='<s:property value="toDate"/>'>&nbsp;
				</div>
				<div class="div-table-col width15 control" align="left">
				<img src="/HIS/hisglobal/images/avai/GO.png" id="imgGoId">
				</div>
			</div>
			
 		<div class="div-table-col title width100 " >
			<div class="div-table-col title width10 " >
				S.No.
			</div>
			<div class="div-table-col title width10 " align="left">
					Login Status
				</div>
				<div class="div-table-col title width20 " align="center">
					Login Date&nbsp;
					& Login Time
				</div>
				
				<div class="div-table-col title width20 " align="center">
					Logout Date&nbsp;
					& Logout Time
				</div>
				<div class="div-table-col title width10 " align="center">
					IP Address&nbsp;
				</div>
				<div class="div-table-col title width20 " align="right">
				    Counter Name
				</div>
				
		</div>
		<s:set name="strPreviousDateForJsp" value=""></s:set>
		<s:iterator value="#session[@hislogin.config.HISLoginConfig@KEY_LOGIN_LOG]" status="varItStatus" >
			<div class="div-table-row width100" >
			 <div class="div-table-col width10 ">
				${varItStatus.index+1}</div>
				<div class="div-table-col width10 ">
					&nbsp;<s:property value="varLoginStatus"/>
				</div>
				<div class="div-table-col width10 "align="right">
					&nbsp;<%
					s2 = (String)request.getAttribute("varUserLoginDate");
					if(!(s1.equals(s2)))
					{%>
						<s:property value="varUserLoginDate"/>
				     <%}
				    else{%>-<%}
					s1=s2;%>
				 <!--   <%=s1%>-->
			</div>&nbsp;
				<div class="div-table-col width10 "align="left">
					&nbsp;&nbsp;<s:property value="varUserLoginTime"/>
				</div>
				<div class="div-table-col width10 " align="right">
				
				<%-- '<s:property value="varUserLogoutDate!=null"/>,<s:property value="varUserLogoutDate==''"/> --%>
				<s:if test="%{(varUserLogoutDate!=null && varUserLogoutDate!='') && #strPreviousDateForJsp!=varUserLogoutDate}">
						<s:property value="varUserLogoutDate"/>
						<s:set name="strPreviousDateForJsp" value="varUserLogoutDate"></s:set>
				</s:if>
				<s:elseif test="%{varUserLogoutDate==null || varUserLogoutDate==''}"></s:elseif>
				<s:else>
					-
				</s:else>&nbsp;
				</div>
				<div class="div-table-col width10 " align="left">
					&nbsp;&nbsp;<s:property value="varUserLogoutTime"/>
				</div>
				<div class="div-table-col width10" align="center">
					&nbsp;<s:property value="varIPAddress"/>
				</div>
				<div class="div-table-col width20"  align="right">
					&nbsp;<s:property value="varCounterName"/>
				</div>
			</div>

		</s:iterator>
	<div class="div-table-button">
		<div class="div-table-row footerBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row" align="center">
		     <a href="#" class="button" tabindex="1" id="idBack"><span class="Back">Back</span></a>
			<a href="#" class="button" tabindex="1" id="idCancel"><span class="cancel">Cancel</span></a>
		</div>
	</div>
</s:elseif>
<s:hidden name="varUserName"></s:hidden>
<s:hidden name="varPassword"></s:hidden>
<s:hidden name="varLoggedIn"></s:hidden>
<s:hidden name="varIPAddress"></s:hidden>
<s:hidden name="varStatus"></s:hidden>


<div id="divElementErrorsId" class="alertMessage"><s:actionerror/></div>
<s:token/>
</s:form>

<script type="text/javascript">
$(document).ready(function(){
	$("#frDateId").datepicker({dateFormat: 'dd-M-yy'})
	$("#toDateId").datepicker({dateFormat: 'dd-M-yy'})
	
	//$("#frDateId").validatebox({required: true, validType: ['date[\'frDate\',\'dd-mmm-yyyy\']']});
	$("#frDateId").validatebox({required: true, validType: ['date[\'frDate\',\'dd-mmm-yyyy\']','dtltet[\'toDate\',\'From Date should be less than or equal to To Date\']']});
	
	$("#toDateId").validatebox({required: true, validType: ['date[\'toDate\',\'dd-mmm-yyyy\']','dtltetctdt[\'To Date must be less than or equal to Current Date\']','dtgtet[\'frDate\',\'To Date should be greater than or equal to From Date\']']});
});



function pageOnLoad()
{
	//if(document.getElementsByName('varPassword')[0])	document.getElementsByName('varPassword')[0].focus();
}
function callMenu(url)
{
	//alert('menu called with url: '+ url);
	var targetURL = url;// + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
	var elemFrame = parent.document.getElementById("frmMain");
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = window.parent.$('#tabframe').tabs('getSelected');
			var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
			window.parent.$('#tabframe').tabs('select',index-1);	
			window.parent.$('#tabframe').tabs('close',index);	

		}
	}
}
var userLogDetails={
	submitOnAll : function()
	{
		
			//For Submission
		  	submitForm("allUserLogDetailsLgnFtr");
		
	},
	submitOnDatewise : function()
	{
		//var fromDateString = document.getElementsByName("frDate")[0].value;
		//var toDateString = document.getElementsByName("toDate")[0].value;
		//alert(fromDateString);
		//alert(toDateString);
	//	var dateAsString = document.getElementsByName("varCurrentDate")[0].value;
		//currentDate = convertStrToDate(dateAsString,'dd-MM-yyyy hh:mm');
		//fromDate = convertStrToDate(fromDateString,'dd-MM-yyyy');
		//toDate = convertStrToDate(toDateString,'dd-MM-yyyy');
		//alert(currentDate);
		//alert(fromDate);
		//alert(toDate);
		//alert(validateDateBeforeOnly(document.getElementsByName("frDate")[0],'dd-MM-yyyy',document.getElementsByName("toDate")[0],'dd-MM-yyyy'));
			//For Submission
		  	submitForm("datewiseUserLogDetailsLgnFtr");
		
	},
	submitOnBack : function()
	{
		
			//For Submission
		  	submitForm("initUserLogDetailsLgnFtr");
		
	},
}
	// On Click of All
	$('#idAll').click(function(e){
		userLogDetails.submitOnAll();
		//callMenu("/AHIMSG5/hislogin/transactions/jsp/st_user_log_details.jsp");
		e.preventDefault();
	});
$('#imgGoId').click(function(e){
	userLogDetails.submitOnDatewise();
	//callMenu("/AHIMSG5/hislogin/transactions/jsp/st_user_log_details.jsp");
	e.preventDefault();
});

//On Click of All
$('#idBack').click(function(e){
	userLogDetails.submitOnBack();
	//callMenu("/AHIMSG5/hislogin/transactions/jsp/st_user_log_details.jsp");
	e.preventDefault();
});

// On Click of Cancel
	$('#idCancel').click(function(e){
		callMenu("/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp");
		e.preventDefault();
	});
</script>

</div>
</center>
</body>
</html>