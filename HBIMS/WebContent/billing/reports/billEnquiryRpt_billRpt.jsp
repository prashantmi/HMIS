<!DOCTYPE html>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
Bill Enquiry Report
author: Debashis Sardar
dated: 05/03/2012
 -->
<%@ page import="hisglobal.utility.HisUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>


<html>
<head><meta charset=utf-8>
<title>Bill Enquiry Report</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<style type='text/css'>

</style>

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script type="text/javascript">
function printContent(){

newwin=window.open('','printwin','left=100,top=100,width=700,height=700,scrollbars=yes');
newwin.document.write('<HTML><HEAD>');
newwin.document.write((document.getElementsByTagName("head")[0]).innerHTML);
newwin.document.write('<style type="text/css"> .hidecontrol { display: none; } <\/style>\n')
newwin.document.write('<script>\n');
newwin.document.write('function chkstate(){ \n');
//newwin.document.write('if(document.readystate=="complete" || document.readystate=="undefined"){\n');
newwin.document.write('window.close();\n');
//newwin.document.write('}\n');
//newwin.document.write('else{\n');
//newwin.document.write('setTimeout("chkstate()",2000)\n');
//newwin.document.write('}\n');
newwin.document.write('}\n');
newwin.document.write('function print_win(){\n');
newwin.document.write('window.print();\n');
newwin.document.write('chkstate();\n');
newwin.document.write('}\n');

newwin.document.write('<\/script>\n');
newwin.document.write('<\/HEAD>\n');
newwin.document.write('<BODY onload="print_win()">\n');
newwin.document.write((document.getElementsByTagName("body" )[0]).innerHTML);
newwin.document.write('<\/BODY>\n');
newwin.document.write('<\/HTML>\n');
newwin.document.close();

}
function goBack()
{
    //alert("id::"+window.frameElement.src);
    window.frameElement.src=window.frameElement.src;
}
</script>
</head>
<body onLoad="showPatientDetails();">
<html:form action="reports/BillEnquiryRptCNT.cnt" method="post">
	   
    <div id='errMsg' class="errMsg"><bean:write name="billEnquiryRpt" property="strErrMsg" filter="false"/></div>
    <div class='hidecontrol' id="printAreaDivId" align="right">
		<!-- <img onclick="printContent();" src="../../hisglobal/images/print_tab.gif" style="cursor:pointer;"> 
		<img onclick="goBack();" src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;">
		 -->
		<a href="#" class="button" id="" onclick='printContent();'><span class="print">Print</span></a>
		<a href="#" class="button"	onclick="goBack();"><span class="cancel">Cancel</span></a> 
	</div>
	
	<table width='75%' align ='center' cellspacing ='1px' >
	 
	 <tr align="right" valign="middle">
		<td colspan="6" align="right">
		
		<%java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); 
		//java.util.ResourceBundle res = java.util.ResourceBundle.getBundle("hisglobal.hisconfig.hisReport");
        String hospitalCode=(String)session.getAttribute("HOSPITAL_CODE");
        HisUtil util=new HisUtil("HisGlobal","Report Page");
      //  String hospHeader=util.getHospitalHeader(hospitalCode,1,"html");
     // String hospHeader=util.getHospitalHeaderNew(hospitalCode,1,"html",request);
         
      Map logoReq=new HashMap();
      
      logoReq.put("FORMAT", "html");
      logoReq.put("REQUEST", request);
      logoReq.put("HOSPCODE", hospitalCode);
      
      String hospHeader=util.getHospitalHeaderMain(logoReq);  %>
		
			<div align="right"><font size='2'>Report Date &amp; Time : <%=  sdf.format(new java.util.Date())  %> </font></div>
		</td>
	</tr>
	<%=hospHeader %>
    <!--  <tr align="center" valign="middle">
		<td colspan="6" align="center">
			<div align="center"><font size="3"><b></b></font></div>
		</td>
	</tr>
	<tr align="center" valign="top">
		<td colspan="6">
			<div align="center"><font size="3"><b></b></font></div>
		</td>
	</tr>
	<tr align="center" valign="top">
		<td colspan="6">
			<div align="center"><font size="3"><b></b></font></div>
		</td>
	</tr>
	<tr align="center" valign="top">
		<td colspan="6">
			<div align="center"><font size="3"><b></b></font></div>
		</td>
	</tr>-->
	<br>
	<tr style=" height: 3mm;" align="left" valign="top">
		<td></td><td></td><td></td>
	</tr>
	<tr align="center" valign="top">
		<td colspan="6">
			<div align="center" style="margin-left:80px;"><font size="3"><b>Bill Wise Payment Details Report</b></font></div>
		</td>
	</tr>
	<tr style=" height: 3mm;" align="left" valign="top">
		<td></td><td></td><td></td>
	</tr>
	
	</table>
								
	<br>							
    <bean:write name="billEnquiryRpt" property="reportContent" filter="false"/>
   
   <table width='75%' align ='center' cellspacing ='1px' >
	 <tr align="center" valign="middle">
		<td colspan="6" align="center">
			<div align="center">*<bean:write name="billEnquiryRpt" property="reportFooter" filter="false"/>*</div>
		</td>
	</tr>
	</table>
</html:form>

</body>
</html>