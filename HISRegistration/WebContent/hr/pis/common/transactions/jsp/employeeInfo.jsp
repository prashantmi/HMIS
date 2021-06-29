<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--<%@ taglib uri="/WEB-INF/csrfguard.tld" prefix="csrf" %>--%> 
<%@page %>
<html>
<head>

<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<link rel="stylesheet"  type="text/css" href="/HISPis/hisglobal/css/tab.css" >
		<link href="/HISPis/hisglobal/css/general.css" rel="stylesheet" type="text/css">
		<link href="/HISPis/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
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
		
		<script type="text/javascript" src="/HISPis/hisglobal/js/pisGlobal.js"></script>
		
		<link rel="stylesheet" type="text/css" media="screen" href="/HISPis/hisglobal/css/jquery.ui.autocomplete.css"/>
		<script type="text/javascript" src="/HISPis/hisglobal/masterutil/js/jquery/jquery.ui.autocomplete.js"></script>
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


 <%@ page import="hr.pis.config.PisConfig"%>

<style type="text/css">

#divPrintId2{display:none ;}		
 @media print { 
		#nonprintableDiv1 {display: none;}
		#nonprintableDiv2 {display: none;}
		#nonprintableDiv3 {display: none;}
		#rptPrintImageDivId {display: none;}
		#simplemodal-container{display: none;}
		#simplemodal-overlay{display: none;}
		#divPrintId2{display: block;}
}


.ui-jqgrid tr.jqgrow td {
			white-space: normal !important;
			height:auto;
			vertical-align:text-top;
			padding-top:1px;
			padding-bottom:1px;
			padding-left:8px;
			}
		
			.ui-jqgrid .ui-jqgrid-htable th div {
			height:auto;
			overflow:hidden;
			padding-right:4px;
			padding-top:5px;
			padding-bottom:5px;
			position:relative;
			vertical-align:text-top;
			white-space:normal !important;
			font-size: 12px;
			}

/* Added By Ashwini Mishra for correcting location of multiselect cancel button*/
.ui-button-icon-only .ui-icon, .ui-button-text-icon-primary .ui-icon, .ui-button-text-icon-secondary .ui-icon, .ui-button-text-icons .ui-icon, .ui-button-icons-only .ui-icon {
			    position: absolute;
			    top: 0%;
			    margin-top: -8px;
			}
			
			.ui-button-icon-only .ui-icon {
			    left: 0%;
			    margin-left: -8px;
			}

</style>

<title>Employee List</title>
<script>


			
$(document).ready(function(){			
				if($('[name="strPageFlag"]')[0].value=='list'){
						showEmpList('1');					
				}
				
			}); 
			
			
			
		
			
		</script>
		

	

</head>

<body>
<center>
			<s:actionerror/>
			
	 <div id="one" style="position: absolute;width: 100%">
     <div class="wrapper rounded" id="nonprintableDiv1">
			<s:form action="EmpList">
			
				<s:if test="strPageFlag=='list'">
								
				<div id="listPageDivId">
					<div id="divEmpListId" style="margin-top: 2px; margin-left:5px; margin-right:5px; ">
						<table id="list1" ><tr><td/></tr></table>
						<div id="pager1"></div>
					</div>	
				</div>		
			</s:if>	
			
			
				
				<s:hidden name="strPageFlag" value="%{strPageFlag}"></s:hidden>
				<s:hidden name="intListType" value="%{intListType}"></s:hidden>
				<s:hidden name="intRoleMgtTypeId" value="%{intRoleMgtTypeId}"></s:hidden>
				<s:hidden name="intCallingSeq" value="%{intCallingSeq}"></s:hidden>
				<s:hidden name="intSalaryTypeId" value="%{intSalaryTypeId}"></s:hidden>
				<s:hidden name="intNojId" value="%{intNojId}"></s:hidden>
				<s:hidden name="intCadreId" value="%{intCadreId}"></s:hidden>
				<s:hidden name="intEmpOffId" value="%{intEmpOffId}"></s:hidden>
				<s:hidden name="intEstbId" value="%{intEstbId}"></s:hidden>
				<s:hidden name="intDeptCode" value="%{intDeptCode}"></s:hidden>
				<s:hidden name="intSerGrpId" value="%{intSerGrpId}"></s:hidden>
				<s:hidden name="intDesigCode" value="%{intDesigCode}"></s:hidden>
				<s:hidden name="intStatusId" value="%{intStatusId}"></s:hidden>
				<s:hidden name="intFinalStatusId" value="%{intFinalStatusId}"></s:hidden>
				<s:hidden name="strCondition" value="%{strCondition}"></s:hidden>
				<s:hidden name="strAdditionalData" value="%{strAdditionalData}"></s:hidden>
				
				
			</s:form>
			
			
	
				<div class="div-table" id="nonprintableDiv2">
		
					<div class="div-table-row">
						<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%"></div>
					</div>
					
					<div class="div-table-row">
						<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%"></div>
					</div>
				
				</div>
	</div>
			
	<div class='wrapper rounded' id="divPrintId1" style="display: none;text-align: center;" align="center"></div>	
	<div class='wrapper rounded' id="divPrintId2" style="text-align: center;" align="center"></div>
				
	
			</div>
			
			<script type="text/javascript" src="/HISPis/hr/pis/common/transactions/js/employeeInfo.js" /></script>
		
		</center>
		
	<div id='loading-image' class="popUpDiv loading" style="display: none;"></div>


</body>
</html>