<%@page import="vo.registration.ShiftVO"%>
<%@page import="org.apache.struts2.views.jsp.TagUtils"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="registration.config.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>

</head>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<body >
<s:form action="ConsolidatedCollectionReport">
<%String sysdate=(String)WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>
<div class="wrapper rounded">
<div class="div-table">
            <div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Consolidated Collection"/>
				</div>
				</div>
				
				<div class="div-table-row " id="dateRow" >
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/>&nbsp;<s:text name="date"/></div>	
				<div class="div-table-col control" id="divFromDate" style="width: 25%">
					       <input id="patFromDateId" tabindex="1" type="text" readonly="readonly" name="fromDate" class="input25prcnt" onblur="consolidatedCollectionReport.checkFromDateValid();" value="<%=sysdate %>" />&nbsp;
				</div>	
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/>&nbsp;<s:text name="date"/></div>	
				<div class="div-table-col control" id="divToDate" style="width: 25%;">
					       <input id="patToDateId" tabindex="1" type="text" readonly="readonly" name="toDate" class="input25prcnt" onblur="consolidatedCollectionReport.checkToDateValid();" value="<%=sysdate %>" />&nbsp; 
				</div>	
			</div>
				<div class="div-table-row ">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/></div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strPatCategoryCode" value="%{strPatCategoryCode}" headerKey="00" headerValue="All" 
				 				list="%{#session.patCategoryList}"  listKey="value" listValue="label" name="strPatCategoryCode"> </s:select>			
				</div>
				  <div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="User"/></div>  
				 <div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strUserCode" value="%{strUserCode}" headerKey="00" headerValue="All" 
				 				list="%{#session.UserList}"  listKey="value" listValue="label" name="strUserCode"> </s:select>			
				</div>
				</div>
				<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Report Generation Option"/>
				</div>
			</div>
			<div class="div-table-row ">
			<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Acrobat Reader or HTML"/></div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="reportType" value="%{reportType}" headerKey="-1" headerValue="Select Value" 
				 				list="#{'1':'Acrobat Reader','2':'HTML','3':'Excel'}" name="reportType"> </s:select>			
				</div>
				
			</div>	
			<div class="div-table-button">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" onclick="consolidatedCollectionReport.showReport1();"><span class="generate"><s:text name="generate"/></span></a>
				<a href="#" class="button" onclick="consolidatedCollectionReport.clearTile();"><span class="clear"><s:text name="clear"/></span></a>
				<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
			</div>
			</div>
</div>

</div>
<s:hidden name="title" value="Consolidated Collection Report"></s:hidden>

<input type="hidden" name="sysdate" value="<%=sysdate %>"/>
</s:form>
<script type="text/javascript" src="./../../registration/reports/js/report.js" /></script>
<script type="text/javascript">
$('[name="strPatCategoryCode"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="graphOrText"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="reportType"]').validatebox({required: true,validType:  ['selectCombo[-1]']});


 window.onload = function (e) {
	// $('[name="strChoice"]')[0].checked=true;
	  report.showDivDate();
	var objPatCat = document.getElementsByName("strPatCategoryCode")[0];
	objPatCat.value="00";
	var objUsr = document.getElementsByName("strUserCode")[0];
	objUsr.value="00";
	var objUsr = document.getElementsByName("sysDate")[0];
	
	 } 

var consolidatedCollectionReport={
clearTile:function(){
			  document.forms[0].action="cancelconsolidatedCollectionReport.action";
			  document.forms[0].submit();
		  },
		  showReport1:function(){
			  if($('#ConsolidatedCollectionReport').form('validate')){
				  document.forms[0].action="showReport1consolidatedCollectionReport.action";
			  	  document.forms[0].submit();
			  }
			  else{
				  return true;
			  }
		  },
		  checkFromDateValid:function(){
			  $("#patFromDateId").validatebox({required: true, validType: ['date[\'fromDate\',\'dd-mmm-yyyy\']','dtltetlastdt[\'From Date must be lesser than Today\']','dtltet[\'toDate\',\'FromDate should be less than or equal to ToDate\']','dtNotGtGvnDay[\'toDate\',\'31\',\'From & To Date Difference must be within 30 days\']']});
			  
		  },
		  checkToDateValid:function(){
			  $("#patToDateId").validatebox({required: true, validType: ['date[\'toDate\',\'dd-mmm-yyyy\']','dtltetlastdt[\'To Date must be lesser than Today\']','dtgtet[\'fromDate\',\'ToDate should be greater than or equal to FromDate\']','dtNotGtGvnDay[\'fromDate\',\'31\',\'From & To Date Difference must be within 30 days\']']});

		  }
		  
		  	
};
</script>
</body>
</html>
