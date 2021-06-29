
<%@page import="org.apache.struts2.views.jsp.TagUtils"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">



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

<%-- <script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script> --%>
<script>
/* function goBack()
{
    alert("id::");
    window.frameElement.src=window.frameElement.src;
} */
</script>
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

<body onload="report2.onLoadTile();" >
<s:form action="notifiableDiseaseStaticReport">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width60 ">
						<s:text name="Notifiable Disease Static "/>&nbsp;<s:text name="Report"/>
				</div>
				<div class="div-table-col title width40 ">				
				<s:radio name="strChoice" list="#{'T':'Today','D':'DateWise','M':'MonthWise','Y':'YearWise'}" value="%{strChoice}" 
  																		onchange="DiagStatsReport.showTimeTile();"></s:radio>		  
				</div>
			</div>
			
			<div class="div-table-row ">
<!-- 			 <div class="div-table-col label"> -->
<%-- 			 <s:text name="Today"></s:text>			  --%>
<!-- 			 </div> -->
<!-- 			 <div class="div-table-col control"> -->
<!-- 			 <input type="radio" name="strChoice" value="0"> -->
<!-- 			 </div> -->
<!-- 			 <div class="div-table-col label"> -->
<%-- 			 <s:text name="DateWise"></s:text>			  --%>
<!-- 			 </div> -->
<!-- 			 <div class="div-table-col control"> -->
<!-- 			 <input type="radio" name="strChoice" value="1"> -->
<!-- 			 </div> -->
<!-- 			 </div> -->
			
<!-- 				<div class="div-table-col control" style="width: 50%;">				 -->
<%-- 				<s:radio name="strChoice" list="#{'0':'Today','1':'DateWise','2':'MonthWise','3':'YearWise'}" value="%{strChoice}"  --%>
<%--  																		onchange="report.showTimeTile();"></s:radio></div>			   --%>
<!-- 				</div> -->
<!-- 			<div class="div-table-button"><div class="div-table-col emptyBar"></div> -->
<!-- 			</div> -->
			</div>
			<div class="div-table-row " id="dateRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/>&nbsp;<s:text name="date"/> </div>	
				<div class="div-table-col control" id="divFromDate" style="width: 25%;display: none;">
					       <input id="patFromDateId" tabindex="1" type="text" readonly="readonly" name="fromDate" class="input25prcnt" onblur="report2.checkFromDateValid();">&nbsp;
				</div>	
				<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/>&nbsp;<s:text name="date"/> </div>	
				<div class="div-table-col control" id="divToDate" style="width: 25%;display: none;">
					       <input id="patToDateId" tabindex="1" type="text" readonly="readonly" name="toDate" class="input25prcnt" onblur="report2.checkToDateValid();">&nbsp;
				</div>	
			</div>
			
			<div class="div-table-row " id="timeRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/></div>
				<div class="div-table-col control" style="width: 25%;">				
				<s:textfield name="fromHour" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="DiagStatsReport.showTimeTile();"></s:textfield>            
	   				<b>:</b>
	   			<s:textfield name="fromMin" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="DiagStatsReport.showTimeTile();"></s:textfield>(HH:MM 24 Hrs)	            
				</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/></div>
    	 		<div class="div-table-col control" style="width: 25%;">	
    	 		<s:textfield name="toHour" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="DiagStatsReport.showTimeTile();"></s:textfield>            
	   				<b>:</b>
	   			<s:textfield name="toMin" cssStyle="width:5%;min-width: 30px;" maxlength="2" onchange="DiagStatsReport.showTimeTile();"></s:textfield>(HH:MM 24 Hrs)	
	   			</div>
			</div>
			
			<div class="div-table-row " id="monthRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/>&nbsp;<s:text name="month"/></div>
				<div class="div-table-col control" style="width: 25%;">				
					<s:select cssStyle="width: 25%" key="fromMonth" value="%{fromMonth}" 
				 				list="#{'Jan':'Jan','Feb':'Feb','Mar':'Mar','Apr':'Apr','May':'May','Jun':'Jun','Jul':'Jul','Aug':'Aug','Sep':'Sep','Oct':'Oct','Nov':'Nov','Dec':'Dec'}" id="fromMonth" name="fromMonth"> </s:select>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="fromMonthYear" value="%{fromMonthYear}" id="fromMonthYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="fromMonthYear" onchange="DiagStatsReport.showTimeTile();"> </s:select>	
	   			</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/>&nbsp;<s:text name="month"/> </div>
    	 		<div class="div-table-col control" style="width: 25%;">	
					<s:select cssStyle="width: 25%" key="toMonth" value="%{toMonth}" 
				 				list="#{'Jan':'Jan','Feb':'Feb','Mar':'Mar','Apr':'Apr','May':'May','Jun':'Jun','Jul':'Jul','Aug':'Aug','Sep':'Sep','Oct':'Oct','Nov':'Nov','Dec':'Dec'}" id="toMonth" name="toMonth"> </s:select>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="toMonthYear" value="%{toMonthYear}" id="toMonthYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="toMonthYear" onchange="DiagStatsReport.showTimeTile();"> </s:select>	
	   			</div>
			</div>
			
			<div class="div-table-row " id="yearRow" style="display: none">
				<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="from"/></div>
				<div class="div-table-col control" style="width: 25%;">				
					<s:text name="1-JAN"></s:text>&nbsp;		
					<s:select cssStyle="width: 25%" key="fromYear" value="%{fromYear}" id="fromYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="fromYear" onchange="DiagStatsReport.showTimeTile();"> </s:select>	
	   			</div>		
				
			
    	 		<div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="to"/></div>
    	 		<div class="div-table-col control" style="width: 25%;">	
					<s:text name="31-DEC"></s:text>&nbsp;		
	   				<s:select cssStyle="width: 25%" key="toYear" value="%{toYear}" id="toYear"
				 				list="#{'2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027','2028':'2028','2029':'2029'}" name="toYear" onchange="DiagStatsReport.showTimeTile();"> </s:select>	
	   			</div>
			</div>
			
			<%-- <div >	Order By:	<br>
				<s:radio name="strOrder" list="#{'D':'Disease Name','I':'ICD Code'}" value="%{strOrder}" 
  																		onchange="DiagStatsReport.showReport"></s:radio>		  
				</div> --%>
				
					
			<!-- <div class="div-table-row "> -->
				<%-- <div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Department"/>&nbsp;</div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strDeptCode" value="%{strDeptCode}" headerKey="0" headerValue="All" 
				 				list="%{#session.departmentList}"  listKey="value" listValue="label" name="strDeptCode"> </s:select>			
				</div> --%>
				<%--<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Requisition Status"/>&nbsp;</div>
				 <div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strServiceType" value="%{strServiceType}" headerKey="0" headerValue="All" 
				 				list="#{'1':'Requisition Raised','2':'Completed','5':'In process'}" name="strServiceType"> </s:select>			
				</div> --%>
				<%-- <div class="div-table-col label" style="width: 20%;"><font color="#FF0000">*</font>&nbsp;<s:text name="Service Area"/>
</div>
				<div class="div-table-col" style="width: 25%;"><s:select cssStyle="width: 75%" key="strPatCategoryCode" value="%{strPatCategoryCode}" headerKey="0" headerValue="All" 
				 				list="%{#session.patCategoryList}"  listKey="value" listValue="label" name="strPatCategoryCode"> </s:select>			
				</div>
				
			</div>	
			
			<div class="div-table-row " style="width: 25%;display:none">
				
				<div class="div-table-col label" style="width: 20%;display:none"><font color="#FF0000">*</font>&nbsp;<s:text name="Report"/>&nbsp;<s:text name="Mode"/></div>
				<div id="mode1" class="div-table-col" style="width: 25%;display:none"><s:select cssStyle="width: 75%" key="strMode" value="%{strMode}"  
				 				list="#{'1':'Consolidated'}" name="strMode1"></s:select>
				 </div>
				 <div id="mode2" class="div-table-col" style="width: 25%;display:none"><s:select cssStyle="width: 75%" key="strMode" value="%{strMode}"  
				 				list="#{'1':'Consolidated','2':'Detailed'}" name="strMode2"></s:select>
				 </div>
			</div>	 --%>
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
				<a href="#" class="button" onclick="DiagStatsReport.showReport();"><span class="generate"><s:text name="generate"/></span></a>
				<a href="#" class="button" onclick="DiagStatsReport.clearTile();"><span class="clear"><s:text name="clear"/></span></a>
				<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>				
			</div>
			</div>
</div>

</div>
<s:hidden name="title" value="Notifiable Disease Static Report"></s:hidden>
<%-- <s:hidden name="mode"></s:hidden> --%>
<s:hidden name="strOrder"></s:hidden>
<%-- <s:hidden name="serviceAreaName"></s:hidden> --%>
<%-- <s:hidden name="serviceAreaName"></s:hidden> --%>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<script type="text/javascript" src="./../../registration/reports/js/report2.js" /></script>

<script type="text/javascript">
/* $('[name="strDeptCode"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="strUnitCode"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
$('[name="graphOrText"]').validatebox({required: true,validType:  ['selectCombo[-1]']}); */
$('[name="reportType"]').validatebox({required: true,validType:  ['selectCombo[-1]']});
window.onload = function (e) {
	
	 $('[name="strChoice"]')[0].checked=true;
	 
	  report2.showTimeTile();
	// var objDept  =document.getElementsByName("strDeptCode")[0];
	//objDept.value="0";
	//var objServiceType=document.getElementsByName("strServiceType")[0];
	//objServiceType.value="0";
	/* var objPatCat=document.getElementsByName("strPatCategoryCode")[0]; */
	//objPatCat.value="0"; 
	
	}

var DiagStatsReport={
		/* onchange_strDeptCode:function()
		  {
			var objDept  =document.getElementsByName("strDeptCode")[0];
			var deptCode = objDept.options[objDept.selectedIndex].value;
			var action 		= "/HISRegistration/registration/reports/getUnitdeptwiseOPDStatReport.action?deptCode="+deptCode;
						
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
		  }, */
		  clearTile:function(){
			  document.forms[0].action="cancelnotifiableDiseaseStaticReport.action";
			  document.forms[0].submit();
		  },
		  showReport:function(){
			  /* if($('[name="strOrder"]')[0].checked)
				  $('[name="strOrder"]').val( '1');
			  else
				  $('[name="strOrder"]').val('2'); */

			  if($('#notifiableDiseaseStaticReport').form('validate')){
				  document.forms[0].action="showReportnotifiableDiseaseStaticReport.action";
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
				report2.showDivDate();
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
						var ctdt = new Date().getFullYear();
						if(ctdt==$('[name="fromMonthYear"]')[0].value){
							$('[name="fromMonth"]').validatebox({required: true,validType:['monthlesserThan[\"#toMonth",\"From Month",\"To Month"]']});
							$('[name="toMonth"]').validatebox({required: true,validType:['monthgreaterThan[\"#fromMonth",\"To Month",\"From Month"]','selectedMonthLsCMonth["To Month"]']});
						}else{
							$('[name="fromMonth"]').validatebox({required: true,validType:['monthlesserThan[\"#toMonth",\"From Month",\"To Month"]']});
							$('[name="toMonth"]').validatebox({required: true,validType:['monthgreaterThan[\"#fromMonth",\"To Month",\"From Month"]']});
						}
						
					}else{
						$('[name="fromMonth"]').validatebox({required: false,validType:null});
						$('[name="toMonth"]').validatebox({required: false,validType:['selectedMonthLsCMonth["To Month"]']});
					}//var ctdt = new Date().getFullYear();  // var ctdt = new Date().getMonth();
						
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