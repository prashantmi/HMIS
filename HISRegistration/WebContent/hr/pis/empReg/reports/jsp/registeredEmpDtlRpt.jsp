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
<link rel="stylesheet" href="/HISPis/hisglobal/css/buttons.css" type="text/css" >
<link rel="stylesheet" href="/HISPis/hisglobal/css/tab.css" type="text/css" >
<link rel="stylesheet" href="/HISPis/hisglobal/css/layout.css" type="text/css">
<link rel="stylesheet" href="/HISPis/hisglobal/css/general.css" type="text/css">
<link rel="stylesheet" href="/HISPis/hisglobal/css/jquery-ui.css" type="text/css">
<link rel="stylesheet" href="/HISPis/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HISPis/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HISPis/hisglobal/css/basic.css">
				
<link rel="stylesheet" type="text/css" href="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/jquery-ui-themes-1.8.23/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/css/ui.jqgrid.css" />
  <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="Javascript" src="/HISPis/hisglobal/js/security.js"></script>


    
<script language="JavaScript" type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.validate.js"></script>
		
<script type='text/javascript' src='/HISPis/hisglobal/masterutil/js/jquery/basic.js'></script>
<script type="text/javascript" src="/HISPis/hisglobal/js/dateFunctions.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/js/date.js"></script>
<script language="JavaScript" src="/HISPis/hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type='text/javascript' src='/HISPis/hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
		
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhime.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhindic.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhime-common.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/multilingualSupport.js'></script>
	
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/plugins/ui.multiselect.js"></script>
<link rel="stylesheet" href="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/plugins/ui.multiselect.css">
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/src/grid.jqueryui.js"></script>	
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/js/jquery.layout.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/src/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/src/jquery.jqGrid.js"></script>
		
<link rel="stylesheet" type="text/css" media="screen" href="/HISPis/hisglobal/css/jquery.ui.autocomplete.css"/>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.ui.autocomplete.js"></script>
<link rel="stylesheet" href="/HISPis/hisglobal/font-awesome-4.2.0/css/font-awesome.min.css">

<link href="/HISPis/hisglobal/css/global.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/HISPis/hisglobal/js/pisGlobal.js"></script>
<script type='text/javascript' src='/HISPis/hisglobal/js/validation.js'></script>
<jsp:include page="/hr/common/jsp/globalFields.jsp" />

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

<script language="JavaScript" src="/HISPis/hisglobal/js/reportGlobal.js"></script>

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


<title><s:text name="registered.emp.dtl.report.name"/></title>
<script>

var empNoLen = <%=PisConfig.EMP_NO_LENGTH%>;
var empNoType = <%=PisConfig.EMP_NO_TYPE%>;


$(window).on("load.loading1", function(){
	
	fieldValidate();
	
}); 

$(document).ready(function() {
	setReportOpenAsOption(1);
	$('#nonprintableDiv').bPopup({modalClose: false});
	
	if($("#intRptMode").val()=="2"){afterClickEmpGo();}

});

/*$(function() {
	
	 $("#strEmpNo").autocomplete({ 
		 width: 1000,
		 delay:500,
		 minLength: 0,
		 scroll: true,
		 highlight: false,  
		 source:   function(request, response) {
	            $.ajax({
	            	url:"/HISPis/pis/common/transactions/findValidEmpWithRmEmployeeInfo.action",
	 	            dataType: "json",
	 	            data: {searchStr: request.term},
	 	            success: function( data, textStatus, jqXHR) {response(data);},
	 	            error: function(jqXHR, textStatus, errorThrown){//console.log(textStatus);}
	 	            
	            });
		 },
		 select: function( event, ui ) {
			 //ui.item.label="";
			 ui.item.value=(ui.item.value).substring(0,12);		 
		 },
		
		 focus: function( event, ui ) {
			 //ui.item.label="";
			 ui.item.value=(ui.item.value).substring(0,12);						
		 },
		 messages: {
		        noResults: '',
		        results: function() {}
		    }

			
	    });
	 
	
});*/


	
</script>
</head>

<body>
<center>
<s:actionerror/>

<div id="one" style="width: 100%" >
<div class="wrapper rounded" id="nonprintableDiv" style="width:75%; margin-top: 40px;">    
<s:form action="RegisteredEmpDtlRpt">	
	<div class="div-table">
		<div class="div-table-row " align="left" id="reportFooterId">
			<div class="div-table-col control" style="width: 40%">
				<h3 style="margin: 0px;"><b><s:text name="registered.emp.dtl.report.name"/></b></h3>
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
		<div class="div-table-row subtitle" style="text-align: right;">
			<div class="div-table-col" style="width: 100%; text-align: right;">				
				<span id="reportTypeId" title="Report Type" >
				<span id="imgHtmlIconId" style="display:none; cursor: pointer;"><img src='/HIS/hisglobal/images/nonclinical/nc_html_icon.png' width='18'  height='18' style='vertical-align: middle;' /></span>
				<span id="imgPdfIconId" style="display:none; cursor: pointer;"><img src='/HIS/hisglobal/images/nonclinical/nc_pdf_icon.png' width='18' height='18' style='vertical-align: middle;' /></span>
				<span id="imgExcelIconId" style="display:none; cursor: pointer;"><img src='/HIS/hisglobal/images/nonclinical/nc_excel_icon.png' width='18' height='18' style='vertical-align: middle;' /></span>
				
				<select name="strReportType" id="strReportType" style="width:120px;" onchange="setReportOpenAsOption(1);" >
					<option value="1">HTML</option>
					<option value="2">PDF</option>
<!-- 					<option value="3">EXCEL</option> -->
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
				<div style="display: none;">
 				&nbsp;&nbsp;	 
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
	
	<div id="mainTable" class="div-table" >
		
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
		
			<s:if test="intRptMode==1">
		</s:if>
		<s:elseif test="intRptMode==2">
		
				<div class="div-table-row " style="width: 100%">
				
				<div class="div-table-col label width25" >
							<font color="red">*</font><s:text name="global.emp.number"></s:text>
			    </div>
				
				<div id="strEmpNoDivId" class="div-table-col width25 control addModDiv" >
					<s:property value="strEmpNo"/>
					<s:hidden name="strEmpNo" id="strEmpNo"  />
				</div>
				
				<div class="div-table-col width25 label" >
							<font color="red">*</font><s:text name="global.emp.name"></s:text>
			   </div>
			   
				<div id="strEmpNameDivId" class="div-table-col width25 control addModDiv" >
					<s:property value="strEmpName"/>
					<s:hidden name="strEmpName" id="strEmpName"  />
				</div>
				
				  
			   
				</div>
		</s:elseif>
		<s:elseif test="intRptMode==3">
		
		<!-- 
			<div class="div-table-row ">
				
				<div class="div-table-col label width25" >
							<font color="red">*</font><s:text name="global.emp.number"></s:text>
			    </div>
				
				<div class="div-table-col width25 control addModDiv" >
					<s:textfield name="strEmpNo" id="strEmpNo" maxLength="12" size="15" onkeyup="resetEmpName();" />
					<img id="goButtonId" src="/HIS/hisglobal/images/nonclinical/go.png" alt="go"  width="20" height="20" style="cursor: pointer;" title="Go" />	
					<img onclick="searchEmpNo();" src="/HIS/hisglobal/images/nonclinical/search.gif" alt="view" width="20" height="20" style="cursor: pointer;" title="View">
				</div>
				
				
				<div class="div-table-col width25 label" >
							<font color="red">*</font><s:text name="global.emp.name"></s:text>
			   </div>
			   
				<div id="strEmpNameDivId" class="div-table-col width25 control addModDiv" >
					<input id="strEmpName" name="strEmpName" maxlength="12"	type="text" size="15" readonly="readonly">
				</div>
				
				   
			   
			</div>
			
		 -->	
			
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
				<div style="display: none;">
					<input id="strEmpNoTemp" name="strEmpNoTemp" />
				</div>

			</div>
											
		</div>	
		
	
		<div id="showEmpDetailsDiv" class="div-table" style="display: none" >	
			
			<div class="div-table-row " style="width: 100%">
			
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
					<input id="strEmpName" name="strEmpName" type="hidden" >
				</div>
												
			</div>
			
	
			
		</div>	
	
			
			
		</s:elseif>	
			
			
		</div>	
		
			<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
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

<s:token />
<%--<input type="hidden" name="<csrf:tokenname/>" value="<csrf:tokenvalue/>"/>--%> 	
</s:form>
 </div>
  </div>   

<script type="text/javascript" src="/HISPis/hr/pis/empReg/reports/js/registeredEmpDtlRpt.js" /></script>
<script type="text/javascript" src="/HIS/hisglobal/js/bPopup.js" /></script>
<script type="text/javascript" src="/HISPis/hr/pis/common/transactions/js/employeeInfo.js" /></script>
</center>

<s:hidden name="intRptMode" id="intRptMode"  />

<div id='loading-image' class="popUpDiv loading" style="display: none;">      
</div>

</body>
</html>