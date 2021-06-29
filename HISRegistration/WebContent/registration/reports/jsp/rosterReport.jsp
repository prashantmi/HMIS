<%@page import="vo.registration.ShiftVO"%>
<%@page import="org.apache.struts2.views.jsp.TagUtils"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: Raj Kumar
 	 Date			: 07-Aug-2018 		-->

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

<body onload="report.onLoadTile();" >
<s:form action="rosterReport">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width60 ">
						<s:text name="Department wise Roster"/>&nbsp;<s:text name="Report"/>
				</div>
				<div class="div-table-col title width40 ">				
				<s:radio name="strChoice" list="#{'M':'MonthWise'}" value="%{strChoice}" 
  																		onchange="rosterReportObj.showTimeTile();"></s:radio>		  
				</div>
			</div>
<script type="text/javascript">
$(function(){
	$("#fromMonth").on('change',function(){

		if($('[name="fromMonthYear"]')[0].value==$('[name="toMonthYear"]')[0].value){
			$('[name="fromMonth"]').validatebox({required: true,validType: ['monthlesserThan["#toMonth","From Month","To Month"]']});
			$('[name="toMonth"]').validatebox({required: true,validType:['monthgreaterThan["#fromMonth","To Month","From Month"]']});  
		}
		else{
			$('[name="fromMonth"]').validatebox({required: false,validType:null});
			$('[name="toMonth"]').validatebox({required: false,validType:null});  
		}
		});
	$("#toMonth").on('change',function(){
			if($('[name="fromMonthYear"]')[0].value==$('[name="toMonthYear"]')[0].value){
			$('[name="fromMonth"]').validatebox({required: true,validType: ['monthlesserThan["#toMonth","From Month","To Month"]']});
			$('[name="toMonth"]').validatebox({required: true,validType:['monthgreaterThan["#fromMonth","To Month","From Month"]']});  
		}
			else{
				$('[name="fromMonth"]').validatebox({required: false,validType:null});
				$('[name="toMonth"]').validatebox({required: false,validType:null});  
			}
		});
$("#fromMonthYear").on('change',function(){
	if($('[name="fromMonthYear"]')[0].value==$('[name="toMonthYear"]')[0].value){
		$('[name="fromMonth"]').validatebox({required: true,validType: ['monthlesserThan["#toMonth","From Month","To Month"]']});
		$('[name="toMonth"]').validatebox({required: true,validType:['monthgreaterThan["#fromMonth","To Month","From Month"]']});
	}
	else{
		$('[name="fromMonth"]').validatebox({required: false,validType:null});
		$('[name="toMonth"]').validatebox({required: false,validType:null});  
	}
});
});

</script>
			<div class="div-table-row ">
			</div>
			<div class="div-table-row " id="dateRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/>&nbsp;<s:text name="date"/></div>	
				<div class="div-table-col control" id="divFromDate" style="width: 25%;display: none;">
					       <input id="patFromDateId" tabindex="1" type="text" readonly="readonly" name="fromDate" class="input25prcnt" onblur="report.checkFromDateValid();">&nbsp;
				</div>	
				<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/>&nbsp;<s:text name="date"/></div>	
				<div class="div-table-col control" id="divToDate" style="width: 25%;display: none;">
					       <input id="patToDateId" tabindex="1" type="text" readonly="readonly" name="toDate" class="input25prcnt" onblur="report.checkToDateValid();">&nbsp;
				</div>	
			</div>
			 
			<div class="div-table-row " id="timeRow" name="timeRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/></div>
				<div class="div-table-col control" style="width: 25%;">				
				<s:textfield name="fromHour" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="rosterReportObj.showTimeTile();"></s:textfield>            
	   				<b>:</b>
	   			<s:textfield name="fromMin" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="rosterReportObj.showTimeTile();"></s:textfield>(HH:MM 24 Hrs)	            
				</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/></div>
    	 		<div class="div-table-col control" style="width: 25%;">	
    	 		<s:textfield name="toHour" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="rosterReportObj.showTimeTile();"></s:textfield>            
	   				<b>:</b>
	   			<s:textfield name="toMin" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="rosterReportObj.showTimeTile();"></s:textfield>(HH:MM 24 Hrs)	
	   			</div>
			</div> 
			 
			<div class="div-table-row " id="monthRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/>&nbsp;<s:text name="month"/></div>
				<div class="div-table-col control" style="width: 25%;">				
					<s:select cssStyle="width: 25%" key="fromMonth" value="%{fromMonth}" 
				 				list="#{'Jan':'Jan','Feb':'Feb','Mar':'Mar','Apr':'Apr','May':'May','Jun':'Jun','Jul':'Jul','Aug':'Aug','Sep':'Sep','Oct':'Oct','Nov':'Nov','Dec':'Dec'}" id="fromMonth" name="fromMonth"> </s:select>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="fromMonthYear" value="%{fromMonthYear}" id="fromMonthYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="fromMonthYear" onchange="unitwiseOpdStatsReport.showTimeTile();"> </s:select>	
	   			</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/>&nbsp;<s:text name="month"/></div>
    	 		<div class="div-table-col control" style="width: 25%;">	
					<s:select cssStyle="width: 25%" key="toMonth" value="%{toMonth}" 
				 				list="#{'Jan':'Jan','Feb':'Feb','Mar':'Mar','Apr':'Apr','May':'May','Jun':'Jun','Jul':'Jul','Aug':'Aug','Sep':'Sep','Oct':'Oct','Nov':'Nov','Dec':'Dec'}" id="toMonth" name="toMonth"> </s:select>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="toMonthYear" value="%{toMonthYear}" id="toMonthYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="toMonthYear" onchange="rosterReportObj.showTimeTile();"> </s:select>	
	   			</div>
			</div>
			
			<div class="div-table-row " id="yearRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/></div>
				<div class="div-table-col control" style="width: 25%;">		
				<span style="font-size: 10pt" id="divCalenderYearFrom">1-JAN</span>		
					<s:text name="1-JAN"></s:text>&nbsp;		
					<s:select cssStyle="width: 25%" key="fromYear" value="%{fromYear}" id="fromYear"
				 				list="#{'2010':'2010','2011':'2011','2012':'2012','2013':'2013','2014':'2014','2015':'2015','2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020','2021':'2021'}" name="fromYear" onchange="rosterReportObj.showTimeTile();"> </s:select>	
	   			</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/></div>
    	 		<div class="div-table-col control" style="width: 25%;">	
    	 		<span id="divCalenderYearTo" style="font-size: 10pt">31-DEC</span>
					<s:text name="31-DEC"></s:text>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="toYear" value="%{toYear}" id="toYear"
				 				list="#{'2010':'2010','2011':'2011','2012':'2012','2013':'2013','2014':'2014','2015':'2015','2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020','2021':'2021'}" name="toYear" onchange="rosterReportObj.showTimeTile();"> </s:select>	
	   			</div>
			</div> 
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/>&nbsp;</div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strDeptCode" value="%{strDeptCode}" headerKey="0" headerValue="All" 
				 				list="%{#session.departmentList}"  listKey="value" listValue="label" name="strDeptCode" onchange="rosterReportObj.onchange_strDeptCode();"> </s:select>			
				</div>
				<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unit"/>&nbsp;</div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strUnitCode" value="%{strUnitCode}" headerKey="0" headerValue="All" 
				 				list="%{#session.unitList}"  listKey="value" listValue="label" name="strUnitCode"> </s:select>			
				</div>
				
			</div>	
			
			<div class="div-table-row ">
				<%-- <div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/></div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strPatCategoryCode" value="%{strPatCategoryCode}" headerKey="0" headerValue="All" 
				 				list="%{#session.patCategoryList}"  listKey="value" listValue="label" name="strPatCategoryCode"> </s:select>			
				</div> --%>
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unitType"/>&nbsp;</div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strServiceType" value="%{strServiceType}" headerKey="0" headerValue="All" 
				 				list="#{'1':'General','2':'Special','3':'Emergency'}" name="strServiceType"> </s:select>			
				</div>
				
			</div>	
			 <div class="div-table-row ">
				<%-- <div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Report"/>&nbsp;<s:text name="Mode"/></div> --%>
				<%-- <div id="mode1" class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strMode" value="%{strMode}"  
				 				list="#{'1':'Consolidated'}" name="strMode1"></s:select>
				 </div> --%>
				 
				<%--  <div id="mode2" class="div-table-col" style="width: 25%;display:none"><s:select cssStyle="width: 75%" key="strMode" value="%{strMode}"  
				 				list="#{'1':'Consolidated','2':'Detailed'}" name="strMode2"></s:select>
				 </div> --%>
			</div>  
<%-- 			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Order By"/></div>
				<div class="div-table-col control" style="width: 25%;"><s:radio name="strOrderBy" list="#{'1':'CR No.','2':'Name','3':'Visit Date'}" value="%{strOrderBy}"></s:radio>		 
				</div>
				<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Group By"/></div>
				<div class="div-table-col control" style="width: 25%;"><s:radio name="strGroupBy" list="#{'0':'Category','1':'Visit Type'}" value="%{strGroupBy}"></s:radio>		 
				</div>
			</div>	 --%>
			
<!-- 			<div class="div-table-button"><div class="div-table-col emptyBar"></div> -->
<!-- 			</div> -->

			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Report Generation Option"/>
				</div>
			</div>
			
			<div class="div-table-row ">
<%-- 				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Report Type"/></div> --%>
<%-- 				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="graphOrText" value="%{graphOrText}" headerKey="-1" headerValue="Select Value"  --%>
<%-- 				 				list="#{'1':'Textual'}" name="graphOrText"> </s:select>			 --%>
<!-- 				</div> -->
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Acrobat Reader or HTML"/></div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="reportType" value="%{reportType}" headerKey="-1" headerValue="Select Value" 
				 				list="#{'1':'Acrobat Reader','2':'HTML','3':'Excel'}" name="reportType"> </s:select>			
				</div>
				
			</div>	
			
			<div class="div-table-button">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" onclick="rosterReportObj.showReport();"><span class="generate"><s:text name="generate"/></span></a>
				<a href="#" class="button" onclick="rosterReportObj.clearTile();"><span class="clear"><s:text name="clear"/></span></a>
				<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>				
			</div>
			</div>
</div>

</div>
<s:hidden name="title" value="Roster Report"></s:hidden>
<s:hidden name="strMode"></s:hidden>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<%-- <script type="text/javascript" src="./../../registration/reports/js/report.js" /></script> --%>

<script type="text/javascript">
$('[name="strDeptCode"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="strUnitCode"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="graphOrText"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="reportType"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
document.getElementsByName("strDeptCode")[0].value="0";
//document.getElementsByName("strPatCategoryCode")[0].value="0";
document.getElementsByName("strServiceType")[0].value="0";
var rosterReportObj={
		onLoadTile:function(){
			  $('[name="strChoice"]')[0].checked=true;
			  $("#monthRow").show(); 
			  
			
		  },
		  
		onchange_strDeptCode:function()
		  {
			var objDept  =document.getElementsByName("strDeptCode")[0];
			var deptCode = objDept.options[objDept.selectedIndex].value;
			var action 		= "/HISRegistration/registration/reports/getDeptUnitrosterReport.action?deptCode="+deptCode;
			/* alert(action); */			
			$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
				{
						var returnDocument=data;
						var rootNode=returnDocument.getElementsByTagName("root")[0];
						for(var i=0;i<rootNode.childNodes.length;i++ )
						{
							var elementNode=rootNode.childNodes[i];
							var elementName=elementNode.nodeName;
							
							rosterReportObj.processGeneralNode(elementName,elementNode);
						}
													
				},error: function(errorMsg,textstatus,errorthrown) {
		            alert('onchange_strDeptCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		            
		        }});
		  },
		  clearTile:function(){
			  document.forms[0].action="cancelrosterReport.action";
			  document.forms[0].submit();
		  },
		  showReport:function(){
			  if($('[name="strChoice"]')[0].checked)
				  $('[name="strMode"]').val( $('[name="strMode1"]').val());
			  else
				  $('[name="strMode"]').val( $('[name="strMode2"]').val());
				//alert($('[name="strMode"]').val());
				//return false;
			  if($('#rosterReport').form('validate')){
				  document.forms[0].action="showReportrosterReport.action";
			  	  document.forms[0].submit();
			  }
			  else{
				  return true;
			  }
		  },
		  showTimeTile:function(){
			 
			  //$("#timeRow").hide();
			  if($('[name="strChoice"]')[0].checked){
				 // alert("show time tile called");
					
				  	/* $('[name="fromHour"]').validatebox({required: false,validType:null});
				  	$('[name="fromMin"]').validatebox({required: false,validType:null});
				  	$('[name="toHour"]').validatebox({required: false,validType:null});
				  	$('[name="toMin"]').validatebox({required: false,validType:null});
					$("#patFromDateId").validatebox({required: false, validType:null});
					$("#patToDateId").validatebox({required: false, validType: null}); */
					$('[name="fromMonthYear"]').validatebox({required: true,validType:['lesserThan["#toMonthYear","From Year","To Year"]']});
					$('[name="toMonthYear"]').validatebox({required: true,validType:['greaterThan["#fromMonthYear","To Year","From Year"]','selectedYearLsCYear["To Year"]']});
					//$('[name="fromYear"]').validatebox({required: false,validType:null});
					//$('[name="toYear"]').validatebox({required: false,validType:null});
					
					//$("#dateRow").hide();
					$("#timeRow").hide();  
					$("#monthRow").show();
					//$("#yearRow").hide();
					//$("#mode1").hide();
					//$("#mode2").show();
					//$('[name="strMode2"]').val("1");
					
					
					if($('[name="fromMonthYear"]')[0].value==$('[name="toMonthYear"]')[0].value){
						$('[name="fromMonth"]').validatebox({required: true,validType: ['monthlesserThan["#toMonth","From Month","To Month"]']});
						$('[name="toMonth"]').validatebox({required: true,validType:['monthgreaterThan["#fromMonth","To Month","From Month"]']});  
					}
					else{
						$('[name="fromMonth"]').validatebox({required: false,validType:null});
						$('[name="toMonth"]').validatebox({required: false,validType:null});
					}
				
				  
				
			  }
			  else if($('[name="strChoice"]')[1].checked){
				  /*
				  $('[name="fromHour"]').validatebox({required: false,validType:null});
			  	$('[name="fromMin"]').validatebox({required: false,validType:null});
			  	$('[name="toHour"]').validatebox({required: false,validType:null});
			  	$('[name="toMin"]').validatebox({required: false,validType:null});
				$('[name="fromMonthYear"]').validatebox({required: false,validType:null});
				$('[name="toMonthYear"]').validatebox({required: false,validType:null});
				$('[name="fromMonth"]').validatebox({required: false,validType:null});
				$('[name="toMonth"]').validatebox({required: false,validType:null});
					
				$("#dateRow").show();
				$("#timeRow").hide();  
				report.showDivDate();
				 $("#monthRow").hide();
				$("#yearRow").hide();
				$("#mode1").hide();
				$("#mode2").show(); 
				$('[name="strMode2"]').val("1"); */
							
			  }  
			  
			   else if($('[name="strChoice"]')[2].checked){
				   
					  
					/* $("#patFromDateId").validatebox({required: false, validType:null});
					$("#patToDateId").validatebox({required: false, validType: null});
					$('[name="fromMonthYear"]').validatebox({required: false,validType:null});
					$('[name="toMonthYear"]').validatebox({required: false,validType:null});
					$('[name="fromYear"]').validatebox({required: false,validType:null});
					$('[name="toYear"]').validatebox({required: false,validType:null});
					$('[name="fromMonth"]').validatebox({required: false,validType:null});
					$('[name="toMonth"]').validatebox({required: false,validType:null});
						
				  	$("#dateRow").hide();
				  	$("#timeRow").show();
					/* $("#monthRow").hide();
					$("#yearRow").hide(); 
					$("#mode1").show();
					$("#mode2").hide();
					$('[name="strMode2"]').val("1");
					
				  	$('[name="fromHour"]').validatebox({required: true,validType:['numeric','numberRangeZeroToTwentythree']});
				  	$('[name="fromMin"]').validatebox({required: true,validType:['numeric','numberRangeZeroToFiftynine']});
				  	$('[name="toHour"]').validatebox({required: true,validType:['numeric','numberRangeZeroToTwentythree']});
				  	$('[name="toMin"]').validatebox({required: true,validType:['numeric','numberRangeZeroToFiftynine']});
					 */

			  } 
			  /* else if($('[name="strChoice"]')[3].checked){
				  
				  	$('[name="fromHour"]').validatebox({required: false,validType:null});
				  	$('[name="fromMin"]').validatebox({required: false,validType:null});
				  	$('[name="toHour"]').validatebox({required: false,validType:null});
				  	$('[name="toMin"]').validatebox({required: false,validType:null});
					$("#patFromDateId").validatebox({required: false, validType:null});
					$("#patToDateId").validatebox({required: false, validType: null});
					$('[name="fromMonthYear"]').validatebox({required: false,validType:null});
					$('[name="toMonthYear"]').validatebox({required: false,validType:null});
					
					$("#dateRow").hide();
					$("#timeRow").hide();  
					$("#monthRow").hide();
					$("#yearRow").show();
					$("#mode1").hide();
					$("#mode2").show();
					$('[name="strMode2"]').val("1");
					
					$('[name="fromYear"]').validatebox({required: true,validType:['lesserThan["#toYear","From Year","To Year"]']});
					$('[name="toYear"]').validatebox({required: true,validType:['greaterThan["#fromYear","To Year","From Year"]','selectedYearLsCYear["To Year"]']});
					$('[name="fromMonth"]').validatebox({required: false,validType:null});
					$('[name="toMonth"]').validatebox({required: false,validType:['selectedMonthLsCMonth["To Month"]']});
			  } */
		
		  
		},
		processGeneralNode:function(elementName,elementNode)
		  {
			var optionText="";
				optionText="<option value='0'>All</option>";
			if(elementNode!=null){
				for(var i=0;i<elementNode.childNodes.length;i++ )
				{
					var optionNode=elementNode.childNodes[i];
					optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
					
				}
			}
			if(document.getElementsByName(elementName).length!=0)
					document.getElementsByName(elementName)[0].innerHTML=optionText;			
		  }
		
};
var report={
		processGeneralNode:function(elementName,elementNode)
		  {
			var optionText="";
			if(elementNode.childNodes.length!="0"&&elementName=="strUnitCode")
				optionText="<option value='0'>All</option>";
			else
				optionText="<option value='-1'>Select Value</option>";
			if(elementNode!=null){
				for(var i=0;i<elementNode.childNodes.length;i++ )
				{
					var optionNode=elementNode.childNodes[i];
					optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
					
				}
			}
			if(document.getElementsByName(elementName).length!=0)
					document.getElementsByName(elementName)[0].innerHTML=optionText;			
		  },
		  showTimeTile:function(){
			  if($('[name="strChoice"]')[0].checked){
			  	$("#dateRow").hide();
			  	$("#timeRow").hide();
			  	$("#monthRow").show();
			  	$('[name="fromHour"]').validatebox({required: false,validType:['numeric','numberRangeZeroToTwentythree']});
			  	$('[name="fromMin"]').validatebox({required: false,validType:['numeric','numberRangeZeroToFiftynine']});
			  	$('[name="toHour"]').validatebox({required: false,validType:['numeric','numberRangeZeroToTwentythree']});
			  	$('[name="toMin"]').validatebox({required: false,validType:['numeric','numberRangeZeroToFiftynine']});	
				$("#patFromDateId").validatebox({required: false, validType:null});
				$("#patToDateId").validatebox({required: false, validType: null});
				$('[name="fromMonthYear"]').validatebox({required: true,validType:['lesserThan["#toMonthYear","From Year","To Year"]']});
				$('[name="toMonthYear"]').validatebox({required: true,validType:['greaterThan["#fromMonthYear","To Year","From Year"]','selectedYearLsCYear["To Year"]']});
				if($('[name="fromMonthYear"]')[0].value==$('[name="toMonthYear"]')[0].value){
					 $('[name="fromMonth"]').validatebox({required: true,validType:['monthlesserThan["#toMonth","From Month","To Month"]']});
					$('[name="toMonth"]').validatebox({required: true,validType:['monthgreaterThan["#fromMonth","To Month","From Month"]']}); 
				}
				else{
					$('[name="fromMonth"]').validatebox({required: false,validType:null});
					$('[name="toMonth"]').validatebox({required: false,validType:null});  
				}
			  }
			  
		  },
		  onLoadTile:function(){
			  $('[name="strChoice"]')[0].checked=true;
			  report.showTimeTile();
		  }
		  
		  
		  
};


$(document).ready(function () {
	$('#patFromDateId, #patToDateId').DatePicker({format: 'd-M-Y',onSelect: function(dateStr) {
	   $(this).blur();
	   onCLickDateFn($(this));
	}
	});
	});

function onCLickDateFn(obj){
	obj.focus();
}

function callMenu(url)
{
	//alert('menu called with url: '+ url);
	var targetURL = url;// + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
	
	
	var elemFrame = parent.document.getElementById("frmMain");
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = window.parent.$('#tabframe').tabs('getSelected');
			var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
			window.parent.$('#tabframe').tabs('select',index-1);	
			window.parent.$('#tabframe').tabs('close',index);				
		}
	}
};
</script>
<script>

</script> 
</body>
</html>