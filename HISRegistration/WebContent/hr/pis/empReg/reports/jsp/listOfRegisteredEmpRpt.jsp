<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--<%@ taglib uri="/WEB-INF/csrfguard.tld" prefix="csrf" %>--%> 
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->

<link rel="stylesheet" href="/HIS/hisglobal/css/buttons.css" type="text/css" >
<link rel="stylesheet" href="/HIS/hisglobal/css/tab.css" type="text/css" >
<link rel="stylesheet" href="/HIS/hisglobal/css/layout.css" type="text/css">

<link rel="stylesheet" href="/HISRegistration/hisglobal/css/general.css" type="text/css">

<link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/basic.css">
				
<link rel="stylesheet" type="text/css" href="/HISRegistration/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/jquery-ui-themes-1.8.23/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/HISRegistration/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/css/ui.jqgrid.css" />
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

    
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.validate.js'></script>
<script type='text/javascript' src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/js/basic.js"></script>
<script type='text/javascript' src='/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.simplemodal.js'></script>
		

<script type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>
<%-- <script type="text/javascript" src="/HISPis/hisglobal/js/date.js"></script> --%>
<script language="JavaScript" src="/HIS/hisglobal/utility/generictemplate/js/date_validator.js"></script>

		
<script type='text/javascript' src='/HIS/hisglobal/multilingualSupport/js/pramukhime.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/multilingualSupport/js/pramukhindic.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/multilingualSupport/js/pramukhime-common.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/multilingualSupport/js/multilingualSupport.js'></script>
	
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/js/jquery-ui-custom.min.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/plugins/ui.multiselect.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/src/grid.jqueryui.js"></script>	
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/js/jquery.layout.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/src/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/pis/jquery/jquery.jqGrid-4.6.0/src/jquery.jqGrid.js"></script>
		
<link rel="stylesheet" type="text/css" media="screen" href="/HIS/hisglobal/css/jquery.ui.autocomplete.css"/>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery.ui.autocomplete.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/css/css/font-awesome.min.css">

<link href="/HISRegistration/hisglobal/css/global.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/HISRegistration/hisglobal/js/pisGlobal.js"></script>
<script type='text/javascript' src='/HISRegistration/hisglobal/js/validation.js'></script>

<%-- <jsp:include page="/hr/common/jsp/globalFields.jsp" /> --%>

<!--  theme changer start-->
<link rel="stylesheet" type="text/css" title="blue" href="/HIS/hisglobal/css/buttons.css" />
<link rel="stylesheet" type="text/css" title="blue" href="/HIS/hisglobal/css/layout.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HIS/hisglobal/css/buttons-green.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HIS/hisglobal/css/layout-green.css" />
<link rel="stylesheet" type="text/css" title="normal" href="/HIS/hisglobal/css/normalfont.css" />
<link rel="alternate stylesheet" type="text/css" title="large" href="/HIS/hisglobal/css/largefont.css" />
<link rel="alternate stylesheet" type="text/css" title="small" href="/HIS/hisglobal/css/smallfont.css" />
<script type="text/javascript" src="/HIS/hisglobal/js/themechanger.js"></script>
<!--  theme changer end-->

<script language="JavaScript" src="/HISRegistration/hisglobal/js/reportGlobal.js"></script>

<%@ page import="hr.pis.config.PisConfig"%>

<style type="text/css">

@media print { 
	#nonprintableDiv{display: none;}
	#nonprintableDiv2 {display: none;}
	#nonprintableDiv3 {display: none;}	
	.ui-dialog{display: none !important;}
	.ui-widget-overlay{display: none !important;}
}

</style>



<title><s:text name="registration.rpt.title"/></title>
<script>

var empNoLen = <%=PisConfig.EMP_NO_LENGTH%>;
var empNoType = <%=PisConfig.EMP_NO_TYPE%>;

$(window).on("load.loading1", function(){
	
	_func.fetchDefaultValues();
	
	$('[name="strIsHeaderReq"]')[0].checked=true;
	$('[name="strIsFooterReq"]')[0].checked=true;
	$('[name="strIsLogoReq"]')[0].checked=true;

	onChngHeaderReq(1);
	
	/*
	if($('[name="strPageFlag"]')[0].value=='empList')
	{
		//alert("1");
			showEmpList();
			
	}*/
	
	
	/*$("#divEmpWise").hide();
	$("#divOrderWise").hide();
	onChngRptGenType();*/
	
}); 


$(document).ready(function() {
	//setReportOpenAsOption(1);
	
	if($('[name="strPageFlag"]')[0].value=='rptPage')
	{	
		setReportOpenAsOption(1);
		$('#nonprintableDiv').bPopup({modalClose: false}); 		
	}	

	
	

});
	
</script>
</head>

<body>
<center>
<s:actionerror/>

<div id="one" style="width: 100%">
 <div class="wrapper rounded" id="nonprintableDiv" style="width:75%; margin-top: 40px;">  
<s:form action="ListOfRegisteredEmpRpt">
	
<s:if test="strPageFlag=='rptPage'">
 <!-- <div class="wrapper rounded" id="nonprintableDiv" style="width:75%; margin-top: 40px;">  -->
	<div class="div-table-row emptyBar">
			<div class="div-table-col"></div>
		</div>
		
		<div class="div-table-row emptyBar">
			<div class="div-table-col"></div>
		</div>

	<div class="div-table">
		<div class="div-table-row " align="left" id="reportFooterId">
			<div class="div-table-col control" style="width: 40%">
				<h3 style="margin: 0px;"><b><s:text name="registration.rpt.title"/></b></h3>
			</div>
			<div class="div-table-col control" style="width: 60%; text-align: right;">
			<b>	
			<span id="configId"  style="cursor: pointer;" title='Options'><img src='/HIS/hisglobal/images/nonclinical/nc_report_options.png' width='18' height='18' style='vertical-align: middle;' title='Options' />&nbsp;<s:text name="global.footer.options"></s:text></span>
			&nbsp;&nbsp;
			<span id="reportId"  style="cursor: pointer;" title='Report'><img src='/HIS/hisglobal/images/nonclinical/nc_report.png' width='18' height='18' style='vertical-align: middle;' title='Report' />&nbsp;<s:text name="global.footer.report"></s:text></span>
			&nbsp;&nbsp;
			<span id="clearId"  style="cursor: pointer;" title='Clear'><img src='/HIS/hisglobal/images/nonclinical/nc_clear.png' width='18' height='18' style='vertical-align: middle;' title='Clear' />&nbsp;<s:text name="global.footer.clear"></s:text></span>
			&nbsp;&nbsp;
			<span id="cancelId"  style="cursor: pointer;" title='Cancel'><img src='/HIS/hisglobal/images/nonclinical/nc_cancel.png' width='18' height='18' style='vertical-align: middle;' title='Cancel' />&nbsp;<s:text name="global.footer.cancel"></s:text></span>
			</b>
			</div>
		</div>
	</div>
	
		
	<div id="divConfigId" class="div-table" style="text-align: right;display: ;">		
		<div class="div-table-button" style="margin: 0px;">
			<div class="div-table-row footerBar" > 
				<div class="div-table-col"> </div>
			</div>
		</div>
		<div class="div-table-row subtitle" style="text-align: right; ">
			<div class="div-table-col" style="width: 100%; text-align: right;">		
				
				<span id="reportTypeId" title="Report Type" >
				<span id="imgHtmlIconId" style="display:none; cursor: pointer;"><img src='/HIS/hisglobal/images/nonclinical/nc_html_icon.png' width='18'  height='18' style='vertical-align: middle;' /></span>
				<span id="imgPdfIconId" style="display:none; cursor: pointer;"><img src='/HIS/hisglobal/images/nonclinical/nc_pdf_icon.png' width='18' height='18' style='vertical-align: middle;' /></span>
				<span id="imgExcelIconId" style="display:none; cursor: pointer;"><img src='/HIS/hisglobal/images/nonclinical/nc_excel_icon.png' width='18' height='18' style='vertical-align: middle;' /></span>
				
				<select name="strReportType" id="strReportType" style="width:120px;" onchange="setReportOpenAsOption(1);" >
					<option value="1">HTML</option>
					<option value="2">PDF</option>
					<!-- <option value="3">EXCEL</option> -->
				</select>
				
				</span>
				&nbsp;&nbsp;
				
				<span style="cursor: pointer;" title="Label(s)" >				
				<s:text name="global.labelsLanguage" ></s:text>
				<select name="strLabelLang" id="strLabelLang" style="width:120px;">
					<option id="langEng" value="1">English</option>
					<option id="langTelgu" value="2">Telugu</option>
					<option id="langBoth" value="3">Both</option>
				</select>
				</span>				
				&nbsp;&nbsp;
				
				<span style="cursor: pointer;" title="Report Header Required" >
				<s:text name="global.header"></s:text>
				<input name="strIsHeaderReq" tabindex="2" id="strIsHeaderReq" type="checkbox" onchange="onChngHeaderReq(1)">
				</span>
				&nbsp;&nbsp;
				<span style="cursor: pointer;" title="Report Footer Required" >
				<s:text name="global.footer"></s:text>
				<input name="strIsFooterReq" tabindex="2" id="strIsFooterReq" type="checkbox" onchange="onChngFooterReq(1)">
				</span>
				&nbsp;&nbsp;	
				<span style="cursor: pointer;" title="Logo Required" >
				<s:text name="global.logo"></s:text>
				<input name="strIsLogoReq" tabindex="2" id="strIsLogoReq" type="checkbox" disabled="disabled" onchange="onChngLogoReq(1)">
				</span>
				&nbsp;&nbsp;	
				<div style="display: none;">	 
 				<span style="cursor: pointer;" title="Watermark Required" > 
 				<s:text name="global.watermark"></s:text> 
 				<input name="strIsWatermarkReq" tabindex="2" id="strIsWatermarkReq" type="checkbox" onchange="onChngWatermarkReq(1)"> 
 				</span> 
				</div>
				
			</div>
		</div>
	</div>
	
	
	<div class="div-table-button" style="margin: 0px;">
		<div class="div-table-row footerBar" > 
			<div class="div-table-col"> </div>
		</div>
	</div>
	
	<div class="div-table"  >
		 
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		
		<div class="div-table-row" style="display:none; width: 100%;">
			<div class="div-table-col label" style="width: 25%">
				<font color="red">*</font><s:text name="registration.rpt.gen.type"></s:text>
			</div>
			<div class="div-table-col width control" style="width: 75%">
				<span  >
				<s:text name="registration.rpt.general"></s:text>
				<input name="strRptGenType"  id="strRptGenType" type="radio" value="1" onchange="onChngRptGenType();">
				</span>
				&nbsp;&nbsp;	
				<span>
				<s:text name="registration.rpt.emp.wise"></s:text>
				<input name="strRptGenType"  id="strRptGenType" type="radio" value="2" onchange="onChngRptGenType();">111
				</span>
			</div>
		</div>
		
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		
		
		
	<div id="divEmpWise"  style="display: none;">
	
	
	<div class="div-table"  >
	
	
	<div id="enterEmpNoDiv" class="div-table-row " style="width: 100%">		
			<div class="div-table-col label" style="width: 25%" >
			 	<font color="red">*</font><s:text name="global.emp.number"></s:text>
			  </div>
											
			<div class="div-table-col width control" style="width: 25%" >
				<input id="strEmpNo" name="strEmpNo" maxlength='<%=PisConfig.EMP_NO_LENGTH %>' size='<%=PisConfig.EMP_NO_SIZE %>' type="text" onkeypress='Javascript: if (event.keyCode==13) funcForClickingGo(); else <%="return validateData(event,"+PisConfig.EMP_NO_TYPE_CODE+")" %> ' >   
				<img id="goButtonId" src="/HIS/hisglobal/images/nonclinical/go.png" alt="go"  width="20" height="20" style="cursor: pointer;" title="Go" />	
				<img onclick="searchEmpNo();" src="/HIS/hisglobal/images/nonclinical/search.gif" alt="view" width="20" height="20" style="cursor: pointer;" title="View">


			</div>
										
									
 										
 			<div class="div-table-col label" style="width: 25%" >
			
			  </div>
			<div class="div-table-col width control" style="width: 25%" >			
			</div>										
			
	</div>
	
	<div id="showEmpDetailsDiv" class="div-table-row " style="width: 100%; display: none;">
		
		
			<div class="div-table-col label" style="width: 25%" >
			 	<font color="red">*</font><s:text name="global.emp.number"></s:text>
			  </div>
											
			<div class="div-table-col width control" style="width: 25%" >
				<div id="empNoVal"></div>
			</div>
										
									
 										
 			<div class="div-table-col label" style="width: 25%" >
				<font color="red">*</font><s:text name="global.emp.name"></s:text>
			  </div>
			<div class="div-table-col width control" style="width: 25%" >
			<div id="empNameVal"></div>
			<input id="strEmpName" name="strEmpName" type="hidden">
			</div>
											
		</div>							
		
	</div>
</div>

	<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		
		<div id="divGeneral">
		
		<div class="div-table"  >
			
				
		 <div class="div-table-row " style="width: 100%">
			
				
				<div class="div-table-col label" style="width: 25%">
							<font color="red">*</font><s:property value="getText('registration.rpt.nature.of.job')"/>
			   </div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="intNatureOfJob" id="intNatureOfJob" style="width: 110px">
							<option value="-1">Select Value</option>
					</select>
					<input name="strNatureOfJob" id="strNatureOfJob" type="hidden">
				</div>
					
			</div>
				
		
			
			<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
			</div>
					
		<div class="div-table-row " style="width: 100%">
				
			<div class="div-table-col label" style="width: 25%;">
							<s:property value="getText('registration.rpt.desig')"/>
			   </div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="intEmpDesig" id="intEmpDesig" style="width: 110px" >
							<option value="-1">Select Value</option>
					</select>
					<input name="strEmpDesig" id="strEmpDesig" type="hidden">
				</div>
				
				
				<div class="div-table-col label" style="width: 25%">
							<s:property value="getText('registration.rpt.dept')"/>
			   </div>
				<div class="div-table-col width control " style="width: 25%">
					<select name="intEmpDept" id="intEmpDept" style="width: 110px" >
							<option value="-1">Select Value</option>
					</select>
					<input name="strEmpDept" id="strEmpDept" type="hidden">
				</div>
				
			</div>
				
		
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>	
				
		<div class="div-table-row " style="width: 100%">	
				
				
				<div class="div-table-col label" style="width: 25%">
							<s:property value="getText('registration.rpt.gender')"/>
			   </div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="intGender" id="intGender" style="width: 110px" >
							<option value="-1">Select Value</option>
					</select>
					<input name="strGender" id="strGender" type="hidden">
				</div>
				
				
				<div class="div-table-col label" style="width: 25%">
							<s:property value="getText('registration.rpt.final.status')"/>
			   </div>
				<div class="div-table-col width control" style="width: 25%">
					<select name="intFinalStatus" id="intFinalStatus" style="width: 110px" >
							<option value="-1">Select Value</option>
					</select>
					<input name="strFinalStatus" id="strFinalStatus" type="hidden">
				</div>
				
			</div>
			
			
			
		
			
			
		</div>	
		
	</div>

		
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		
		<div class="div-table-row " style="width: 100%; display:none;">
		
			<div class="div-table-col label" style="width: 25%" >
				<s:text name="registration.rpt.is.period.req"></s:text>
			  </div>
			<div class="div-table-col width control" style="width: 75%" >
			<input id="strIsPeriodReq" name="strIsPeriodReq" type="checkbox">
			</div>
		
		</div>
		
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		
			<div class="div-table-row " id="divPeriod" style="width: 100%; display:none;">
		
			<div class="div-table-col label" style="width: 25%" >
				<font color="red">*</font><s:text name="global.period.from"></s:text>
			  </div>
			<div class="div-table-col width control" style="width: 25%" >
			<input id="dtPeriodFrom" name="dtPeriodFrom" type="text" maxlength="11" size="12">
			</div>
			
			<div class="div-table-col label" style="width: 25%" >
				<font color="red">*</font><s:text name="global.period.to"></s:text>
			  </div>
			<div class="div-table-col width control" style="width: 25%" >
			<input id="dtPeriodTo" name="dtPeriodTo" type="text" maxlength="11" size="12" >
			</div>
		
		</div>
		
		
			
			<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
			</div>					
		
		
	</div>
	
	<div class="div-table-button">
		<div class="div-table-row footerBar">
			<div class="div-table-col"> </div>
		</div>
	</div>
		
	<div class="div-table-simple" id="nonprintableDiv2">
		<div class="div-table-row">
			<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%"></div>
		</div>
	</div>
		
	<div class="div-table" id="nonprintableDiv3">
		<div class="div-table-row">
			<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%"></div>
		</div>
	</div>
	
	<div id="divPrintId" ></div>
<!-- </div>	  -->
</s:if>

<s:hidden id="strPageFlag" name="strPageFlag" />
<s:hidden name="strSsoTicket" id="strSsoTicket" value="%{#session.keyLoggedInUserGrantingTicketId}" ></s:hidden>

<s:token />
<%--<input type="hidden" name="<csrf:tokenname/>" value="<csrf:tokenvalue/>"/>--%> 	
</s:form>
  </div> 



</div>

<script type="text/javascript" src="/HISRegistration/hr/pis/empReg/reports/js/listOfRegisteredEmpRpt.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/bPopup.js" /></script>
</center>

<div id='loading-image' class="popUpDiv loading" style="display: none;">      
</div>

</body>
</html>