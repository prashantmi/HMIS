<%@page import="vo.registration.ShiftVO"%>
<%@page import="org.apache.struts2.views.jsp.TagUtils"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
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
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
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

<body onload="report.showDivDate();" >
<s:form action="MctsPatientlistingReport">
<div class="wrapper rounded">
<div class="div-table">
<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Mcts Patient Listing Report"/>
				</div>
				</div>
<div class="div-table-row " id="dateRow" >
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;From Date</div>	
				<div class="div-table-col control" id="divFromDate" style="width: 25%;">
					       <input id="patFromDateId" tabindex="1" type="text" readonly="readonly" name="fromDate" class="input25prcnt" onblur="MctspatListReport.checkFromDateValid();">&nbsp;
				</div>	
				<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;To Date</div>	
				<div class="div-table-col control" id="divToDate" style="width: 25%;">
					       <input id="patToDateId" tabindex="1" type="text" readonly="readonly" name="toDate" class="input25prcnt" onblur="MctspatListReport.checkToDateValid();">&nbsp;
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
				<a href="#" class="button" onclick="MctspatListReport.showReport();"><span class="generate">Generate</span></a>
				<a href="#" class="button" onclick="MctspatListReport.clearTile();"><span class="clear">Clear</span></a>
				<a href="#" class="button" onclick="submitCancel();"><span class="cancel">Cancel</span></a>
			</div>
			</div>
            </div>

</div>

<s:hidden name="title" value="Mcts Patient listing Report"></s:hidden>
<script type="text/javascript" src="./../../registration/reports/js/report.js" /></script>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token/>
</s:form>

<script type="text/javascript">
$('[name="graphOrText"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="reportType"]').validatebox({required: true,validType:  ['selectCombo[-1]']});

var MctspatListReport={
		clearTile:function(){
	  document.forms[0].action="cancelmctsPatientlist.action";
	  document.forms[0].submit();
  },
  showReport:function(){
	  if($('#MctsPatientlistingReport').form('validate')){
		  document.forms[0].action="showReportmctsPatientlist.action";
	  	  document.forms[0].submit();
	  }
	  else{
		  return true;
	  }
  },
  checkFromDateValid:function(){
	  $("#patFromDateId").validatebox({required: true, validType: ['date[\'fromDate\',\'dd-mmm-yyyy\']','dtltetlastdt[\'From Date must be lesser than Today\']','dtltet[\'toDate\',\'FromDate should be less than or equal to ToDate\']','dtNotGtGvnDay[\'toDate\',\'7\',\'From & To Date Difference must be within 7 days\']']});
	  
  },
  checkToDateValid:function(){
	  $("#patToDateId").validatebox({required: true, validType: ['date[\'toDate\',\'dd-mmm-yyyy\']','dtltetlastdt[\'To Date must be lesser than Today\']','dtgtet[\'fromDate\',\'ToDate should be greater than or equal to FromDate\']','dtNotGtGvnDay[\'fromDate\',\'7\',\'From & To Date Difference must be within 7 days\']']});

  }
  
  	
};
</script>
</body>
</html>