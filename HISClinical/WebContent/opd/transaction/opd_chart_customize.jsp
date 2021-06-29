<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/opd/js/chartReporting.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
	<his:TitleTag name="Charts">
	</his:TitleTag>

	<his:statusTransactionInProcess>
		<%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
		<html:hidden name="ChartReportingFB" property="entryDate" value="<%=sysDate%>" />
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="50%" class="tdfonthead" colspan="2">
						<div align="right">
							<b><bean:message key="chart"/></b>&nbsp;
						</div>
					</td>
					<td width="50%" class="tdfont" colspan="2">
						<div align="left">
							&nbsp;<bean:write name="ChartReportingFB" property="chartHeader" />
							<html:hidden name="ChartReportingFB" property="chartId"/>
							<html:hidden name="ChartReportingFB" property="chartHeader"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="100%" class="tdfont" colspan="4">
						<div align="left">
							<b><bean:message key="selchart"/> <bean:message key="para"/></b> 
						</div>
					</td>
				</tr>
				<logic:iterate id="voChartPara" name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_PARAMETERS%>" type="hisglobal.vo.ChartParameterMappingVO">
				<tr>
					<td width="25%" class="tdfont">
						<div align="right">
							<input type="checkbox" name="chartPara" value="<%=voChartPara.getParaId()%>" checked="checked" />
						</div>
					</td>
					<td width="75%" class="tdfonthead" colspan="3">
						<div align="left">
							<b>&nbsp;<bean:write name="voChartPara" property="paraName"/></b>
						</div>
					</td>
				</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
	</his:statusTransactionInProcess>
		
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' style="cursor:pointer" onclick="submitFormOnValidate(validate(),'GETREPORT');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validate(),'GETREPORT');;" >
		</his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitFormOnValidate(true,'NEW');" onkeypress="if(event.keyCode==13) submitFormOnValidate(true,'NEW');" >
	</his:ButtonToolBarTag>

<html:hidden name="ChartReportingFB" property="hmode"/>
<html:hidden name="ChartReportingFB" property="deskType"/>
<html:hidden name="ChartReportingFB" property="deptUnitCode"/>
<html:hidden name="ChartReportingFB" property="episodeCode"/>
<html:hidden name="ChartReportingFB" property="episodeVisitNo"/>
<html:hidden name="ChartReportingFB" property="admissionNo"/>
<html:hidden name="ChartReportingFB" property="epiAdmStartDate"/>
<html:hidden name="ChartReportingFB" property="fromDate"/>
<html:hidden name="ChartReportingFB" property="toDate"/>
<html:hidden name="ChartReportingFB" property="targetPage" value="CUSTOM"/>
<html:hidden name="ChartReportingFB" property="sortType"/>
<html:hidden name="ChartReportingFB" property="sortChartId"/>
<html:hidden name="ChartReportingFB" property="oldSortType"/>
<html:hidden name="ChartReportingFB" property="graphChartId"/>
