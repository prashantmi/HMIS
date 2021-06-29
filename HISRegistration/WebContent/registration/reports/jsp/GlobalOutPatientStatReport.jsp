<%@page import="vo.registration.ShiftVO"%>
<%@page import="org.apache.struts2.views.jsp.TagUtils"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
<s:form action="outpatstatsReport">
<div class="wrapper rounded">
<div class="div-table">
<div class="div-table-row ">
				<div class="div-table-col title width60 ">
						<s:text name="outpatstat"/>&nbsp;<s:text name="Report"/>
				</div>
				<div class="div-table-col title width40 ">				
				<s:radio name="strChoice" list="#{'T':'Today','D':'DateWise','M':'MonthWise','Y':'YearWise'}" value="%{strChoice}" 
  																		onchange="outpatstatsReportJS.showTimeTile();"></s:radio>		  
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
	

             <div class="div-table-row " id="dateRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/>&nbsp;<s:text name="date"/> </div>	
				<div class="div-table-col control" id="divFromDate" style="width: 25%;display: none;">
					       <input id="patFromDateId" tabindex="1" type="text" readonly="readonly" name="fromDate" class="input25prcnt" onblur="report.checkFromDateValid();">&nbsp;

				</div>	
				<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/>&nbsp;<s:text name="date"/> </div>	
				<div class="div-table-col control" id="divToDate" style="width: 25%;display: none;">
					       <input id="patToDateId" tabindex="1" type="text" readonly="readonly" name="toDate" class="input25prcnt" onblur="report.checkToDateValid();">&nbsp;

				</div>	
			</div>
			
			
			<div class="div-table-row " id="timeRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/></div>
				<div class="div-table-col control" style="width: 25%;">				
				<s:textfield name="fromHour" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="outpatstatsReportJS.showTimeTile();"></s:textfield>            
	   			 <b>:</b>
	   			<s:textfield name="fromMin" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="outpatstatsReportJS.showTimeTile();"></s:textfield>(HH:MM 24 Hrs)	            
				</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/></div>
    	 		<div class="div-table-col control" style="width: 25%;">	
    	 		<s:textfield name="toHour" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="outpatstatsReportJS.showTimeTile();"></s:textfield>            
	   				<b>:</b>
	   			<s:textfield name="toMin" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="outpatstatsReportJS.showTimeTile();"></s:textfield>(HH:MM 24 Hrs)	
	   			</div>
			</div>
			
			<div class="div-table-row " id="monthRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/>&nbsp;<s:text name="month"/></div>
				<div class="div-table-col control" style="width: 25%;">				
					<s:select cssStyle="width: 25%" key="fromMonth" value="%{fromMonth}" 
				 				list="#{'Jan':'Jan','Feb':'Feb','Mar':'Mar','Apr':'Apr','May':'May','Jun':'Jun','Jul':'Jul','Aug':'Aug','Sep':'Sep','Oct':'Oct','Nov':'Nov','Dec':'Dec'}" id="fromMonth" name="fromMonth"> </s:select>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="fromMonthYear" value="%{fromMonthYear}" id="fromMonthYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="fromMonthYear" onchange="outpatstatsReportJS.showTimeTile();"> </s:select>	
	   			</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/>&nbsp;<s:text name="month"/> </div>
    	 		<div class="div-table-col control" style="width: 25%;">	
					<s:select cssStyle="width: 25%" key="toMonth" value="%{toMonth}" 
				 				list="#{'Jan':'Jan','Feb':'Feb','Mar':'Mar','Apr':'Apr','May':'May','Jun':'Jun','Jul':'Jul','Aug':'Aug','Sep':'Sep','Oct':'Oct','Nov':'Nov','Dec':'Dec'}" id="toMonth" name="toMonth"> </s:select>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="toMonthYear" value="%{toMonthYear}" id="toMonthYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="toMonthYear" onchange="outpatstatsReportJS.showTimeTile();"> </s:select>	
	   			</div>
			</div>
			
			
			<div class="div-table-row " id="yearRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/></div>
				<div class="div-table-col control" style="width: 25%;">				
					<%-- <s:text name="1-JAN"></s:text> --%>
									<span style="font-size: 10pt" id="divCalenderYearFrom">1-JAN</span>&nbsp;		
					<s:select cssStyle="width: 25%" key="fromYear" value="%{fromYear}" id="fromYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="fromYear" onchange="outpatstatsReportJS.showTimeTile();"> </s:select>	
	   			</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/></div>
    	 		<div class="div-table-col control" style="width: 25%;">	
					<%-- <s:text name="31-DEC"></s:text> --%>
					<span id="divCalenderYearTo" style="font-size: 10pt">31-DEC</span>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="toYear" value="%{toYear}" id="toYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="toYear" onchange="outpatstatsReportJS.showTimeTile();"> </s:select>	
	   			</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/>&nbsp;</div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strDeptCode" value="%{strDeptCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.departmentList}"  listKey="value" listValue="label" name="strDeptCode"  onchange="outpatstatsReportJS.onchange_strDeptCode();"> </s:select>			
				</div>
				<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unit"/>&nbsp;</div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strUnitCode" value="%{strUnitCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.unitList}"  listKey="value" listValue="label" name="strUnitCode"> </s:select>			
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
				
			<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="global.hospital"/>&nbsp;<s:text name="list"/></div>	
			<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="hospitalCode" value="%{hospitalCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.HospitalList}"  listKey="value" listValue="label" name="hospitalCode"> </s:select>			
				</div> 
			</div>

        <div class="div-table-button">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" onclick="outpatstatsReportJS.showReport();"><span class="generate"><s:text name="generate"/></span></a>
				<a href="#" class="button" onclick="outpatstatsReportJS.clearTile();"><span class="clear"><s:text name="clear"/></span></a>
				<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>				
			</div>
			</div>
			</div>
			</div>
<s:hidden name="title" value="Out Patient Statistics Report"></s:hidden>
<s:token/>
</s:form>

<script type="text/javascript" src="./../../registration/reports/js/report.js" /></script>	

<script type="text/javascript">
$('[name="strDeptCode"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
//$('[name="strUnitCode"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="graphOrText"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="reportType"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="hospitalCode"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
var outpatstatsReportJS={
		onchange_strDeptCode:function()
		  {
			var objDept  =document.getElementsByName("strDeptCode")[0];
			var deptCode = objDept.options[objDept.selectedIndex].value;
			var action 		= "/HISRegistration/registration/reports/getUnitoutpatstatReport.action?deptCode="+deptCode;
						
			$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
				{
						var returnDocument=data;
						var rootNode=returnDocument.getElementsByTagName("root")[0];
						for(var i=0;i<rootNode.childNodes.length;i++ )
						{
							var elementNode=rootNode.childNodes[i];
							var elementName=elementNode.nodeName;
							
							report.processGeneralNode(elementName,elementNode);
						}
													
				},error: function(errorMsg,textstatus,errorthrown) {
		            alert('onchange_strDeptCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		            
		        }});
		  },
		  clearTile:function(){
			  document.forms[0].action="canceloutpatstatReport.action";
			  document.forms[0].submit();
		  },
		  showReport:function(){
			   if($('[name="strChoice"]')[0].checked)
				  $('[name="strMode"]').val( $('[name="strMode1"]').val());
			  else
				  $('[name="strMode"]').val( $('[name="strMode2"]').val());

			  if($('#outpatstatsReport').form('validate')){
				  document.forms[0].action="showReportoutpatstatReport.action";
			  	  document.forms[0].submit();
			  }
			  else{
				  return true;
			  }
		  },
		  showTimeTile:function(){
			  if($('[name="strChoice"]')[0].checked){
				  
				$("#patFromDateId").validatebox({required: false, validType:null});
				$("#patToDateId").validatebox({required: false, validType: null});
				$('[name="fromMonthYear"]').validatebox({required: false,validType:null});
				$('[name="toMonthYear"]').validatebox({required: false,validType:null});
				$('[name="fromYear"]').validatebox({required: false,validType:null});
				$('[name="toYear"]').validatebox({required: false,validType:null});
				$('[name="fromMonth"]').validatebox({required: false,validType:null});
				$('[name="toMonth"]').validatebox({required: false,validType:null});
					
			  	$("#dateRow").hide();
			  	$("#timeRow").show();
				$("#monthRow").hide();
				$("#yearRow").hide();
				$("#mode1").show();
				$("#mode2").hide();
				
			  	$('[name="fromHour"]').validatebox({required: true,validType:['numeric','numberRangeZeroToTwentythree']});
			  	$('[name="fromMin"]').validatebox({required: true,validType:['numeric','numberRangeZeroToFiftynine']});
			  	$('[name="toHour"]').validatebox({required: true,validType:['numeric','numberRangeZeroToTwentythree']});
			  	$('[name="toMin"]').validatebox({required: true,validType:['numeric','numberRangeZeroToFiftynine']});
				

			  }
			  else if($('[name="strChoice"]')[1].checked){
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
				$('[name="strMode2"]').val("1");
				
			  }
			  else if($('[name="strChoice"]')[2].checked){
				  
				  	$('[name="fromHour"]').validatebox({required: false,validType:null});
				  	$('[name="fromMin"]').validatebox({required: false,validType:null});
				  	$('[name="toHour"]').validatebox({required: false,validType:null});
				  	$('[name="toMin"]').validatebox({required: false,validType:null});
					$("#patFromDateId").validatebox({required: false, validType:null});
					$("#patToDateId").validatebox({required: false, validType: null});
					$('[name="fromMonthYear"]').validatebox({required: true,validType:['lesserThan["#toMonthYear","From Year","To Year"]']});
					$('[name="toMonthYear"]').validatebox({required: true,validType:['greaterThan["#fromMonthYear","To Year","From Year"]','selectedYearLsCYear["To Year"]']});
					$('[name="fromYear"]').validatebox({required: false,validType:null});
					$('[name="toYear"]').validatebox({required: false,validType:null});
					
					$("#dateRow").hide();
					$("#timeRow").hide();  
					$("#monthRow").show();
					$("#yearRow").hide();
					$("#mode1").hide();
					$("#mode2").show();
					$('[name="strMode2"]').val("1");
					
					
					if($('[name="fromMonthYear"]')[0].value==$('[name="toMonthYear"]')[0].value){
						$('[name="fromMonth"]').validatebox({required: true,validType:['monthlesserThan["#toMonth","From Month","To Month"]']});
						$('[name="toMonth"]').validatebox({required: true,validType:['monthgreaterThan["#fromMonth","To Month","From Month"]','selectedMonthLsCMonth["To Month"]']});
					}
					else{
						$('[name="fromMonth"]').validatebox({required: false,validType:null});
						$('[name="toMonth"]').validatebox({required: false,validType:['selectedMonthLsCMonth["To Month"]']});
					}
						
			  }
			  else if($('[name="strChoice"]')[3].checked){
				  
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
			  }
		  } 	
};
</script>
</body>
</html>