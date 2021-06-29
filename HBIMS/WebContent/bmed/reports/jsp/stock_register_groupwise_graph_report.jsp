<%@page import="hisglobal.hisconfig.Config"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!-- 
/**
 * @author Anshuman Singh
 * Date of Creation : 19-June-2014
 * Date of Modification :  
 * Version : 
 * Module  : HEMMS Product
 */
 -->
<html>
<head>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript"  src="../../bmed/reports/js/jquery.js"></script>
<script language="javascript"  src="../../bmed/reports/js/jquery.flot.js"></script>
<script language="javascript" src="../../bmed/reports/js/flot-valuelabels.js"></script>
<script language="javascript" src="../../bmed/reports/js/jquery.flot.axislabels.js"></script>
<script language="javascript" src="../../bmed/reports/js/jquery.flot.orderBars.js"></script>
<script language="javascript" src="../../bmed/reports/js/jquery.flot.pie.js"></script>

<script language="javascript" src="../../bmed/reports/js/jquery.jqplot.min.js"></script>
<script language="javascript" src="../../bmed/reports/js/jqplot.barRenderer.min.js"></script>
<script language="javascript" src="../../bmed/reports/js/jqplot.categoryAxisRenderer.min.js"></script>
<script language="javascript" src="../../bmed/reports/js/jqplot.pointLabels.min.js"></script>

<link href="../../hisglobal/css/control.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/emms.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery.jqplot.min.css" rel="stylesheet" type="text/css"  />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/reports/js/stock_register_group_wise_chart_report.js" />
<his:javascript	src="/bmed/reports/js/stock_register_graph_report.js" />

<style>
#barPlaceHolder div.jqplot-axis jqplot div.tickLabel 
{    
    width: 135px;
 /*   transform: rotate(-10deg);   */ 
  /*  -moz-transform:rotate(-10deg);*/ /* Firefox */s  
    /*rotation-point:50% 50%;*/ /* CSS3 */
    /*rotation:270deg;*/ /* CSS3 */
}
.valueLabels {
        font-size:70%;
        color:black;
}
#barPlaceHolder div.valueLabelLight {
        opacity:0.5;
        background-color: white;
        border:none;
        position:absolute;
}
#barPlaceHolder div.valueLabel {
        position:absolute;
        border:none;
}
.axisLabel{
    font-family: Times New Roman;
    font-size: 10pt;
    display:none;
}
.rotateY{
    position: relative;
    left: -455px;
    top: 200px;
    transform: rotate(-90deg);
}
.rotateX{
    position: relative;
    top: 440px;
}
#barPlaceHolder div.jqplot-yaxis div.jqplot-yaxis-tick
{
	white-space:normal;
	text-align: center;
	width: 130px;
}
</style>

</head>
<body marginheight="0" marginwidth="0" class="">

<html:form  action="/reports/StockRegisterGraphReportCNT"  method="post" styleClass="">
	
			
	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="stockRegisterGraphReportFB" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="stockRegisterGraphReportFB" property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="stockRegisterGraphReportFB" property="strNormalMsg" /></div>
		
	<input type="hidden" id="strInstalPend" value="${stockRegisterGraphReportFB.strInstalPend}">
	<input type="hidden" id="strNotInUse" value="${stockRegisterGraphReportFB.strNotInUse}">
	<input type="hidden" id="strNotFuncRepairable" value="${stockRegisterGraphReportFB.strNotFuncRepairable}">
	<input type="hidden" id="strNotfuncNotRepairable" value="${stockRegisterGraphReportFB.strNotfubcNotRepairable}">
	<input type="hidden" id="strFunctional" value="${stockRegisterGraphReportFB.strFunctional}">
	<input type="hidden" id="strNonFunctional" value="${stockRegisterGraphReportFB.strNonFunctional}">
	<input type="hidden" id="strRate" value="${stockRegisterGraphReportFB.strRate}">
	<input type="hidden" id="strGroupName" value="${stockRegisterGraphReportFB.strGroupName}">
	
	<div style="height: 25px;">
		<a style="position: relative;top: 7px;left: 88%;" href="#" class="button" onClick="cancelPage('LIST');">
			<span class="back">Back</span>
		</a>
	</div>
	<img style="width:65px;height=62px;" id="HeaderLogo" class="" src="../../hisglobal/images/osmc_logo.gif" alt="OSMC Logo" />

	<div id="barHeader" class="headerCSS">
		<div style="font-family: arial; font-size: 12pt; font-weight: bold;">${stockRegisterGraphReportFB.strReportHeader}</div>
		<div style="font-family: arial; font-size: 12pt; font-weight: bold;">${stockRegisterGraphReportFB.strReportSubHeader}</div>
		<div style="font-family: arial; font-size: 10pt; font-weight: bold;">${stockRegisterGraphReportFB.strReportAddress}</div>
		<div style="font-family: arial; font-size: 10pt; font-weight: bold;">${stockRegisterGraphReportFB.strReportContact}</div>
		<div style="font-weight: bold; font-size: 16pt; font-family: Times New Roman; text-decoration: underline;">${stockRegisterGraphReportFB.strReportName}</div>
	</div>
	
	<div style="font-family: Arial; font-size: 8pt;text-align: right;">${stockRegisterGraphReportFB.strDateNTime}</div>
	<div id="hospitalDiv" class="demo-container" style="height:100px;">
	<p class="left">${stockRegisterGraphReportFB.strPageHeading}</p>
		<table  style="height:75px;width:100%;margin-top: -18px;">
			<tr>
			 <td class="font1" style="width:14%;text-align: right; font-weight: bold;">Hospital Name:</td>
			 <td style="width:38%">${stockRegisterGraphReportFB.strStoreName}</td>
			 <td class="font1" style="width:14%;text-align: right; font-weight: bold;">Lab Name:</td>
			 <td style="width:38%">${stockRegisterGraphReportFB.strLabName}</td>
			</tr>
		</table>
	</div>
	<div style="font-weight: bold; position: relative; top: -30px; text-decoration: underline; color: black; font-family: Arial;">${stockRegisterGraphReportFB.strIsFooter}</div>	
	<div style="color: #FF0000;font-family: Arial;font-weight: bold;">${stockRegisterGraphReportFB.strNote}</div>	
	<div id="barContent" style="margin-top: -50px;">
	<div style="width:100%;height=30px;display:none;">
		<img style="width:30px;height=30px; position: relative; left: 465px;" id="pieChart" class="pieChartImage" src="../../hisglobal/images/pie_chart.png" alt="" title="Click to see Pie Chart"/>
		<img style="width:30px;height=30px; position: relative; left: 465px;" id="barChart" class="barChartImage" src="../../hisglobal/images/barChart.jpg" alt="" title="Click to see Bar Chart"/>
	</div>

		<div id="yAxixLabel" class="rotateY axisLabel">${stockRegisterGraphReportFB.strYAxisLabel}</div>
		<div id="yAxixLabel" class="rotateX axisLabel">${stockRegisterGraphReportFB.strXAxisLabel}</div>
		<div id="barDiv" class="demo-container" style="height:1000px;width:1000px;margin-top: -30px;">
			<div id="barPlaceHolder" style="height:80%;width:100%;margin-top: 30px;"></div>

		</div>	
		<div id="pieDiv" class="demo-container" style="display:none">
			<div id="piePlaceHolder"  style="height:450px;width:300px;margin-top: 30px;margin-left: -459px;"></div>
			<div id="piePlaceHolder1"  style="height:450px;width:300px;margin-right: -443px;margin-top: -452px;"></div>
			<p id="xAxixLbl" class="font" style="width: 110px;margin-left: 500px;margin-top: -35px;margin-bottom: 35px;">Functional Graph</p>
			<p id="xAxixLbl1" class="font" style="margin-left: -445px;margin-top: -50px;">Not Functional Repairable Graph</p>
			<p class="font">${stockRegisterGraphReportFB.strNote}</p>	
		</div>			
		
<div id="xAxixLabelDiv" class="flot-x-axis flot-x1-axis xAxis x1Axis" style="left: 0px; bottom: 0px; display: block; position: relative; right: 0px; top: 0px;">
</div>
<div id="legendDiv" class="legend" style="position: relative; top: -800px; right: -135px;display:none;">
		<table style="border: 1px solid;position:absolute;top:13px;right:139px;;font-size:smaller;color:#545454">
			<tbody>
				<tr>
					<td class="legendColorBox">
						<div style="border:1px solid none;padding:1px">
							<div style="width:4px;height:0;border:5px solid rgb(237,194,64);overflow:hidden"></div>
						</div>
					</td>
					<td class="legendLabel">Installation Pending</td>
				</tr>	
				<tr>	
					<td class="legendColorBox">
						<div style="border:1px solid none;padding:1px">
							<div style="width:4px;height:0;border:5px solid rgb(175,216,248);overflow:hidden"></div>
						</div>
					</td>
					<td class="legendLabel">Not In Use</td>
				</tr>	
				<tr>	
					<td class="legendColorBox">
						<div style="border:1px solid none;padding:1px">
							<div style="width:4px;height:0;border:5px solid rgb(203,75,75);overflow:hidden"></div>
						</div>
					</td>
					<td class="legendLabel">Non Functional Repairable</td>
				</tr>	
				<tr>	
					<td class="legendColorBox">
						<div style="border:1px solid none;padding:1px">
							<div style="width:4px;height:0;border:5px solid rgb(77,167,77);overflow:hidden"></div>
						</div>
					</td>
					<td class="legendLabel">Non Functional Not Repairable</td>
				</tr>	
				<tr>	
					<td class="legendColorBox">
						<div style="border:1px solid none;padding:1px">
							<div style="width:4px;height:0;border:5px solid rgb(148,64,237);overflow:hidden"></div>
						</div>
					</td>
					<td class="legendLabel">Functional</td>
				</tr>	
				<tr>	
					<td class="legendColorBox">
						<div style="border:1px solid none;padding:1px">
							<div style="width:4px;height:0;border:5px solid rgb(189,155,51);overflow:hidden"></div>
						</div>
					</td>
					<td class="legendLabel">Non Functional</td>
				</tr>
			</tbody>
		</table>
</div>
    		
	</div>
			
	
	<html:hidden property="hmode" styleId="hmode"/>
	</center>
	
	<cmbPers:cmbPers />
</html:form>
</body>
</html>