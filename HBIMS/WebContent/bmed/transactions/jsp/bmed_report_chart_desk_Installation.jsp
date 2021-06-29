<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="../../bmed/css/bmed_dynamic_chart.css" type="text/css">

<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/transactions/js/bmed_HemDesk_trans.js" />

<script language="javascript"  src="../../bmed/reports/js/jquery.js"></script>
<script language="javascript"  src="../../bmed/reports/js/jquery.flot.js"></script>
<script language="javascript" src="../../bmed/reports/js/jquery.flot.axislabels.js"></script>
<script language="javascript" src="../../bmed/reports/js/jquery.flot.pie.js"></script>
<script language="javascript" src="../../bmed/reports/js/jquery.flot.orderBars.js"></script>
<script language="javascript" src="../../bmed/js/jquery.flot.barnumbers.js"></script>


<script language="JavaScript"src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<!-- <script language="Javascript"src="../../bmed/js/complaintHierarchyMst.js"></script> -->
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../..//bmed/transactions/js/bmed_Dynamic_warrenty_chart_graph.js"></script>

<title>Insert title here</title>

</head>
<body>

<html:form action="/transactions/DynamicReportChartDeskCNT"
	name="stEquipInstallDeskName"
	type="bmed.transactions.controller.fb.DynamicReportChartDeskFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="DynamicReportChartDeskFB" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="DynamicReportChartDeskFB" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="DynamicReportChartDeskFB" property="strMsgString" /></div>


		
	<input type="hidden" id="Equipment" value="${DynamicReportChartDeskFB.strEquipment}">
	<input type="hidden" id="Bar1" value=${DynamicReportChartDeskFB.strUnExpire}>
	<input type="hidden" id="Bar2" value=${DynamicReportChartDeskFB.strExpire}>
	<input type="hidden" id="Bar1_Label" value="${DynamicReportChartDeskFB.strBar1Label}">
	<input type="hidden" id="Bar2_Label" value="${DynamicReportChartDeskFB.strBar2Label}">
	
	<p align="right" class="font"><script type="text/javascript">var d = new Date();document.write(d.getUTCDate() + "/" +  + d.getUTCMonth() + "/" +
														d.getUTCFullYear() + " " + d.getUTCHours() + ":" + d.getUTCMinutes() + 
														":" + d.getSeconds());</script></p>			
				
	<div id="barHeader" class="headerCSS">
		<h3>${DynamicReportChartDeskFB.strReportHeader}</h3>
		<h5>${DynamicReportChartDeskFB.strReportAddress}</h5>
		<h5>${DynamicReportChartDeskFB.strReportContact}</h5>
	</div>
	<div id="hospitalDiv" class="demo-container" style="height:100px;">
	<p class="left">${DynamicReportChartDeskFB.strPageHeading}</p>
		<table  style="height:75px;width:100%;margin-top: -18px;">
			<tr>
			 <td class="font1" style="width:14%">Hospital :</td>
			 <td style="width:38%">${DynamicReportChartDeskFB.strStoreName}</td>
			 <td class="font1" style="width:12%">Lab :</td>
			 <td style="width:38%">${DynamicReportChartDeskFB.strLabName}</td>
			</tr>
			<tr>
			 <td class="font1" style="width:12%">Location :</td>
			 <td style="width:38%">${DynamicReportChartDeskFB.strLocation}</td>
			 <td class="font1" style="width:12%">Contact No :</td>
			 <td style="width:38%">${DynamicReportChartDeskFB.strContactNo}</td>
			</tr>
		</table>
	</div>
	<div id="barContent" style="margin-top: -50px;">
	<div style="width:100%;height=30px;">
		<img id="pieChart" class="pieChartImage" src="../../hisglobal/images/pie_chart.png" alt="" title="Click to see Pie Chart"/>
		<img id="barChart" class="barChartImage" src="../../hisglobal/images/barChart.jpg" alt="" title="Click to see Bar Chart"/>
	</div>

		<p id="yAxixLabel" class="rotate font">${DynamicReportChartDeskFB.strYAxisLabel}</p>
		<div id="barDiv" class="demo-container" >
			<div id="barPlaceHolder" style="height:450px;width:700px;margin-top: 30px;"></div>
			<p class="font">${DynamicReportChartDeskFB.strNote}</p>			
		</div>		
		<div id="pieDiv" class="demo-container" style="display:none">
			<div id="piePlaceHolder"  style="height:450px;width:300px;margin-top: 30px;margin-left: -459px;"></div>
			<div id="piePlaceHolder1"  style="height:450px;width:300px;margin-right: -443px;margin-top: -452px;"></div>
			<p id="xAxixLbl" class="font" style="width: 110px;margin-left: 500px;margin-top: -35px;margin-bottom: 35px;">${DynamicReportChartDeskFB.strBar2Label}</p>
			<p id="xAxixLbl1" class="font" style="margin-left: -445px;margin-top: -50px;">${DynamicReportChartDeskFB.strBar1Label}</p>
			<p class="font">${DynamicReportChartDeskFB.strNote}</p>			
		</div>		

		
	</div>
			
	
	<html:hidden property="hmode" styleId="hmode"/>
	</center>
		</html:form>



</body>
</html>

	