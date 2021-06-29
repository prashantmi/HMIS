<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--<%@ taglib uri="/WEB-INF/csrfguard.tld" prefix="csrf" %>--%> 
<%@page %>

<html>
<head>
<!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"  type="text/css" href="/HISPis/hisglobal/css/buttons.css" >
<link rel="stylesheet"  type="text/css" href="/HISPis/hisglobal/css/tab.css" >
<link rel="stylesheet" type="text/css" href="/HISPis/hisglobal/css/layout.css" >
<link href="../../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../../hisglobal/css/basic.css">

<link rel="stylesheet" type="text/css" href="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/jquery-ui-themes-1.8.23/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/css/ui.jqgrid.css" />
   <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>



    
<script language="JavaScript" type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>

<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/js/jquery-ui-custom.min.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/js/jquery.layout.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/src/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/src/jquery.jqGrid.js"></script>
	
<script type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>

<script type='text/javascript' src='../../../hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script type="text/javascript" src="../../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="./../../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type='text/javascript' src='../../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>

<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhime.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhindic.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/pramukhime-common.js'></script>
<script type='text/javascript' src='/HISPis/hisglobal/multilingualSupport/js/multilingualSupport.js'></script>

<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/js/jquery-ui-custom.min.js"></script>

<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/plugins/ui.multiselect.js"></script>
<link rel="stylesheet" href="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/plugins/ui.multiselect.css">
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/src/grid.jqueryui.js"></script>	
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/js/jquery.layout.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/src/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.jqGrid-4.6.0/src/jquery.jqGrid.js"></script>
		
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

<link href="/HISPis/hisglobal/css/global.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/HISPis/hisglobal/js/pisGlobal.js"></script>

<script language="Javascript" src="/HISPis/hisglobal/js/security.js"></script>

<jsp:include page="/hr/common/jsp/globalFields.jsp" />



<style type="text/css">

#divPrintId2{display:none ;}		
 @media print { 
		#nonprintableDiv1 {display: none;}
		#nonprintableDiv2 {display: none;}
		#nonprintableDiv3 {display: none;}
		#loading-image {display: none;}
		#rptPrintImageDivId {display: none;}
		#simplemodal-container{display: none;}
		#simplemodal-overlay{display: none;}
		#divPrintId2{display: block;}
		.ui-dialog{display: none !important;}
		.ui-widget-overlay{display: none !important;}
	}
	

</style>

<title>Employee Registration Validate</title>
<script>
	
	$(window).on("load.loading1", function(){
		//alert("onload");
		empregvalidate.fetchDefaultValues("1");
		
		showPendingList();
		showValidatedList();
		showRejectedList();
		getValidatorDetails();

		$("#divErrorMsgId").html("");
		$("#divNormalMsgId").html("");
		$("#divPrintId").html("");

		


		funcTab1("#tab1");
		$('[name="strCurrentTab"]')[0].value='tab1';
		
		$('#tabs1 a').click(function(e) {
	   	        //Setting Current Tab Value
	   	        $('[name="strCurrentTab"]')[0].value=$(this).attr('name');
		        if($(this).attr('name')=='tab1')
		        {
		        	$('#divValidationDetailsHeadingId').show();
		        }
		        else
		        {
		        	$('#divValidationDetailsHeadingId').hide();
		        }
	     
	   });
	}); 
	


		

		
		
	
	 
	    
</script>

</head>

<body>
<center>
<s:actionerror/>

 <div id="one" style="position: absolute;width: 100%"> 
 <div class="wrapper rounded" id="nonprintableDiv1">
<s:form action="EmployeeRegistration">

<div>
	<div class="div-table" >
		<div class="div-table-row ">
			<div class="div-table-col title" style="width: 100%; text-align: left;" >
				Employee Registration Validation
			</div>
		</div>
	</div>
</div>

<div style="padding: 5px; width: 99%; " class="wrapper rounded">


		
<ul id="tabs1" >
    <li><a href="#" name="tab1" title='Pending Record(s)'><img src='/HIS/hisglobal/images/nonclinical/PendingEmployee.png' width='30' height='28' style='vertical-align: middle;cursor:pointer' />&nbsp;&nbsp;<s:text name="global.validation.tab.pending"></s:text></a></li>
    <li><a href="#" name="tab2" title='Validated Record(s)'><img src='/HIS/hisglobal/images/nonclinical/ValidatedEmployee.png' width='30' height='28' style='vertical-align: middle;cursor:pointer' />&nbsp;&nbsp;<s:text name="global.validation.tab.validated"></s:text></a></li>
    <li><a href="#" name="tab3" title='Rejected Record(s)'><img src='/HIS/hisglobal/images/nonclinical/RejectedEmployee.png' width='30' height='28' style='vertical-align: middle;cursor:pointer' />&nbsp;&nbsp;<s:text name="global.validation.tab.rejected"></s:text></a></li>
    <!--  <li><a href="#" name="tab4"><img src='/HISPis/hisglobal/images/JqGridHeadTree.png' width='20' height='20' style='vertical-align: text-bottom;cursor:pointer' title='Heads Tree'/>&nbsp;&nbsp;Heads Tree</a></li>
	-->
</ul>

  
<div style="position:absolute; right:12px; top:10px; color: #025B85; font: bold 13px 'Lucida sans', Arial, Helvetica;" > 
	<div id="divValidationDetailsHeadingId" style="float: left;">
	<span onclick='toggleValidationDetails();' style="cursor: pointer;" title='Validator Details'><img src='/HIS/hisglobal/images/nonclinical/validator.png'  width='25' height='25' style='vertical-align: middle;' />
	&nbsp;<s:text name="global.validation.tab.validatorDtls"></s:text></span>
	&nbsp;
	</div>
	<div style="float: left;">
	<span onclick='viewListReport();' style="cursor: pointer;" title='Report'><img src='/HIS/hisglobal/images/nonclinical/nc_report.png' width='22' height='22'  style='vertical-align: middle;' title='Report' />
	&nbsp;<s:text name="button.report"></s:text></span>
	&nbsp;
	<span onclick="goToDefaultPage('1');" style="cursor: pointer;" title='Cancel'><img src='/HIS/hisglobal/images/nonclinical/nc_cancel.png' width='18' height='18' style='vertical-align: middle;' title='Cancel' />
	&nbsp;<s:text name="button.cancel"></s:text></span>
	</div>
</div>
			
<div id="content" > 

   <div id="tab1">
		
		
			
		<div id="divValidationDetailsId" style="margin-top: 1px; margin-bottom: 4px; display:none; " class="wrapper rounded">
			
			<div class="div-table" >
				<div class="div-table-row emptyBar">
					<div class="div-table-col"></div>
				</div>
			
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 25%">
						<font color="red">*</font><s:text name="global.validation.validateBy"></s:text><BR><BR>
						<font color="red">*</font><s:text name="global.validation.validateDt"></s:text>
					</div>
					<div class="div-table-col width control" style="width: 20%">
						<input id="strValidatorName" type="text" name="strValidatorName" size="40" maxlength="99" readonly="readonly">
						<BR><BR>
						<input id="validateDateId" type="text" name="dtValidateDate" size="12">
						
					</div>
					<div class="div-table-col label" style="width: 20%">
						<s:text name="global.validation.validatorRemarks"></s:text>
					</div>
					<div class="div-table-col width control" style="width: 35%">
						<textarea  maxlength="99" name="strValidatorRemarks" rows="3" cols="40"></textarea>
					</div>
					
				</div>
				
				<div class="div-table-row emptyBar">
					<div class="div-table-col"></div>
				</div>
				
				
			</div>
		</div>
		
		<div id="divPendingEmployeeListId" style="margin-top: 1px;" class="wrapper rounded">
			<table id="list1" ><tr><td/></tr></table>
			<div id="pager1"></div>
		</div>	
    </div>
    
	<div id="tab2">
		<div id="divValidatedEmployeeListId" style="margin-top: 1px;" class="wrapper rounded">
			<table id="list2" ><tr><td/></tr></table>
			<div id="pager2"></div>
		</div>				    
    </div>
     
    <div id="tab3">
		<div id="divRejectedEmployeeListId" style="margin-top: 1px;" class="wrapper rounded">
			<table id="list3" ><tr><td/></tr></table>
			<div id="pager3"></div>
		</div>
    </div>
    <!--  
    <div id="tab4">
		<div id="divRejectedEmployeeListId" style="margin-top: 1px;" class="wrapper rounded">
			<table id="list3" ><tr><td/></tr></table>
			<div id="pager3"></div>
		</div>
    </div>
-->
		
</div>


						


    



</div>

<div>
	<div class="div-table" >
		<div class="div-table-row ">
			<div class="div-table-col title" style="width: 100%; text-align: left;" >
				&nbsp;
			</div>
		</div>
	</div>
</div>

<div id="divEmpDtlPreviewId" align='center'></div>
<div id="divPrintId" align='center'></div>


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

<input type="hidden" name="strEmployeeNumber" />
<input type="hidden" name="strValidateStatus" />
<input type="hidden" name="strCurrentTab" />
<input type="hidden" name="strValidateId" />

<s:token />
<%--<input type="hidden" name="<csrf:tokenname/>" value="<csrf:tokenvalue/>"/>--%> 	
</s:form>


		<div id="divReportHeading1Id"  class="div-table-row" align="center" style="text-decoration: underline; display: none;">
			<s:property value="getText('emp.reg.report.pending.heading1')"/>
		</div>
		<div id="divReportHeading2Id"  class="div-table-row" align="center" style="text-decoration: underline; display: none;">
			<s:property value="getText('emp.reg.report.validated.heading2')"/>
		</div>
		<div id="divReportHeading3Id" class="div-table-row" align="center" style="text-decoration: underline; display: none;">
			<s:property value="getText('emp.reg.report.rejected.heading3')"/>
		</div>
		<div id="divReportHeading4Id" class="div-table-row" align="center" style="text-decoration: underline; display: none;">
			<s:property value="getText('emp.reg.report.slip.heading4')"/>
		</div>
		
</div>

	<div class='wrapper rounded' id="divPrintId1" style="display: none;text-align: center;" align="center"></div>
	<div class='wrapper rounded' id="divPrintId2" style="text-align: center;" align="center"></div>
	
	
	
</div>
<script type="text/javascript" src="/HISPis/hr/pis/empReg/transactions/js/empRegistrationValidate.js" /></script>


</center>

<div id='loading-image' class="popUpDiv loading" style="display: none;" >
</div>





</body>
</html>