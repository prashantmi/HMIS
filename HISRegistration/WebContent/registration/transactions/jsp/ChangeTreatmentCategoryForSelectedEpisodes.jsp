<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet"	type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" type="text/css" href="../../hisglobal/css/dateinput.css"/>
<link href="../../hisglobal/css/dateinput_small_skin1.css" rel="stylesheet" type="text/css">


<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" type="text/css" href="dateinput.css"/>
<link href="/HIS/hisglobal/customToolTip/css/writeOnToolTip.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/dateinput_small_skin1.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">



<script language="JavaScript" type="text/javascript"
	src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script> <!--Added by Vasu  dated on 13.April.2018-->

<%-- <script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>--%>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<%-- <script type="text/javascript" src="./../../hisglobal/js/jquery/datePickerDefaultSetting.js"></script> --%>
<script type="text/javascript"	src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/validationCommon.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>


<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>


<link rel="stylesheet" href="/HIS/hisglobal/js/jquery/multiselect/css/bootstrap.min.css"type="text/css" />
<link rel="stylesheet" href="/HIS/hisglobal/js/jquery/multiselect/css/bootstrap-multiselect.css"type="text/css" />
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/multiselect/js/bootstrap.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/multiselect/js/bootstrap-multiselect.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
  
<script type="text/javascript">
$(window).on("load.loading1", function() {
/* departmentList=(document.getElementsByName("departmentCode")[0]).innerHTML;
departmentUnitList=(document.getElementsByName("departmentUnitCode")[0]).innerHTML;
getFilteredDeptUnit(document.getElementsByName("selectedEpisode")[0]); */

});


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
				window.parent.$('#tabframe').tabs('close',index);			
			}
		}
	}
	function submitForm(mode) {
		document.forms[0].action = mode + "ChangeTreatmentCategory.action";
		document.forms[0].submit();

	}
	function submitCancelAction(cnt) {
		document.forms[0].action = "cancel" + cnt + ".action";
		document.forms[0].submit();
	}
	
	
	function getNewCategoryCode(obj)
	{
		//alert(obj.value);
		document.getElementsByName("hiddenNewSecCatCode")[0].value=obj.value;
	}
	
	
	function getExpiryDays(obj){
		
		 var categoryCode=obj.value;
		//var elementArraylength=document.getElementsByName('selectEpisode').length;
		var ExpiryDays;
		var index;
		var clientCode;
		
		
		for(j=0;j<document.getElementsByName("newSecCatCode").length;j++)
		{
			
		if(document.getElementsByName("newSecCatCode")[j]==obj)
			{
			index=j;
			
			}
		}
		
		//var idDivDateControl="divDateControl"+index;
		
		var secCatCodeAndExpiryDayObj=document.getElementsByName("secCatCodeAndExpiryDay")[0].value;
		
		var arrayObj=secCatCodeAndExpiryDayObj.split(':');
		
		if((categoryCode!='-1') && (categoryCode!='0') )
		{
		var i=0
			while(i<arrayObj.length)
			{
				if(arrayObj[i].substring(0,arrayObj[i].indexOf('#'))==categoryCode)
				{
					ExpiryDays=arrayObj[i].substring(arrayObj[i].indexOf('#')+1,arrayObj[i].indexOf('^'));
					clientCode=arrayObj[i].substring(arrayObj[i].indexOf('^')+1,arrayObj[i].length);
					if(ExpiryDays=="0")
						ExpiryDays="";
					if(clientCode=="")
						clientCode="0";
					break;
				}
				i++
			}
		
			document.getElementsByName('arrValidUpto')[index].value = ExpiryDays;
			document.getElementsByName('hiddenNewSecCatCode')[index].value = document.getElementsByName('newSecCatCode')[index].value;
			document.getElementsByName('catClientCode')[index].value=clientCode;
		}
		else if((categoryCode=='-1') || (categoryCode=='0'))
		{
				document.getElementsByName('arrValidUpto')[index].value ="";
				document.getElementsByName('catClientCode')[index].value="0";
			
		} 
		

	}
	//Added by Vasu 
	function addRow(obj1,obj2,obj3,deptUnitCode,deptCode)
	{
        var episodeCode = obj1;
        var department = obj2;
        var deptUnit = obj3;
        var deptcode = deptCode;
        var deptUnitCode = deptUnitCode;
        var selectedCatCode = document.getElementsByName('newSecCatCode')[0].value;
        var t = document.getElementById('newSecCatCodeId');
        var selectedCategoryValue = t.options[t.selectedIndex].text;

        if(selectedCategoryValue == "Select Value")
            {
             alert("Please Select Category");
             return false;
            }

        var selected = $("#lstServices option:selected");
        var message = "";
        var applicableService = "";
        var applicableServiceCode = "";
        selected.each(function () {
            message += $(this).text() + " " + $(this).val() + "\n";
            applicableService += $(this).text() + ",";
            applicableServiceCode += $(this).val() + "#";
        });
        if(applicableService == null || applicableService == "")
        {
          alert("please Select atleast one Applicable Service");
          return false;
        }
        applicableService = applicableService.substring(0, applicableService.length - 1);
       // applicableServiceCode = applicableServiceCode.substring(0, applicableServiceCode.length - 1);
       applicableServiceCode = "#"+applicableServiceCode;
        //alert(applicableService);
        //alert(applicableServiceCode);
        //alert(message);
        
        var reason = document.getElementsByName('arrRemarks')[0].value;
        var validUpto = document.getElementsByName('arrValidUpto')[0].value;
        var secCatList = '<%=session.getAttribute("optionSecondaryCategory")%>';
		var letterReference = $('[name="letterReferenceNo"]').val();
		var letterDate = $('[name="letterDate"]').val();
		var creditLimit = $('[name="creditLimit"]').val();
          var row = document.createElement('tr'); 
          row.class = "div-table-row";
	     // var col1 = document.createElement('td'); 
	      var col2 = document.createElement('td'); 
	      var col3 = document.createElement('td');
	      var col4 = document.createElement('td');
	      var col5 = document.createElement('td');
	      var col6 = document.createElement('td');
	      var col7 = document.createElement('td'); 
	      var col8 = document.createElement('td');
	      var col9 = document.createElement('td');
	      var col10=document.createElement('td');
	      var col11=document.createElement('td');
	      
	      //row.appendChild(col1);
	      //row.appendChild(col2);
	      //row.appendChild(col3);
	      row.appendChild(col4);
	      row.appendChild(col5);
	      row.appendChild(col6); 
	      row.appendChild(col7);
	      row.appendChild(col8);
	      row.appendChild(col9);
	      row.appendChild(col10);
	      row.appendChild(col11);
	      
	      
	      /* col2.innerHTML = "<input name='selectedDeptCode' value='"+deptCode+"' type='hidden'>"+
	      "<input name='selectedEpisodeForTreatmentCategory' value='"+episodeCode+"' type='hidden'>"+
		  "<div align=''>"+"<input name='department' value='"+department+"' type='hidden'></input>"+department+"</div>";
          col2.class = "div-table-col control";
          col2.style.width = "9%";
	      col2.colspan="1"; */
	      
	      /* col3.innerHTML ="<input name='selectedDeptUnitCode' value='"+deptUnitCode+"' type='hidden'>"+
		  "<div align=''>"+"<input name='departmentUnit' value='"+deptUnit+"' type='hidden'></input>"+deptUnit+"</div>";
          col3.class = "div-table-col control";
          col3.style.width = "10%";
	      col3.colspan = "1"; */

	      col4.innerHTML ="<input name='selectedEpisodeForTreatmentCategory' value='"+episodeCode+"' type='hidden'>"+
		      "<input name='selectedCategoryName' value='"+selectedCategoryValue+"' type='hidden'>"+
		      "<div align=''>"+"<input name='secCatCode' value='"+selectedCatCode+"' type='hidden'></input>"+selectedCategoryValue+"</div>";
	      col4.class = "div-table-col control";
	      col4.style.width = "15%";
	      col4.colspan = "1"; 

	      col5.innerHTML = "<input name='applicableServicesName' value='"+applicableService+"' type='hidden'>"+
		      "<div align=''>"+"<input name='applicableServiceCode' value='"+applicableServiceCode+"' type='hidden'></input>"+applicableService+"</div>";
	      col5.class = "div-table-col control";
	      col5.style.width = "20%";
	      col5.colspan = "1";
	      
	      col6.innerHTML = "<div align=''>"+"<input name='validUpto' value='"+validUpto+"' type='hidden'></input>"+validUpto+"</div>";
	      col6.class = "div-table-col control";
	      col6.style.width = "20%";
	      col6.colspan = "1";

	      col7.innerHTML="<div align=''>"+"<input name='letterReferenceNo'value='"+letterReference+"' type='hidden'></input>"+letterReference+"</div>";
	      col7.class = "div-table-col control";
	      col7.style.width = "10%";
	      col7.colspan = "1";

	      col8.innerHTML="<div align=''>"+"<input name='letterDate' value='"+letterDate+"' type='hidden'></input>"+letterDate+"</div>";
	      col8.class = "div-table-col control";
	      col8.style.width = "10%";
	      col8.colspan = "1";

	      col9.innerHTML="<div align=''>"+"<input name='creditLimit' value='"+creditLimit+"' type='hidden'></input>"+creditLimit+"</div>";
	      col9.class = "div-table-col control";
	      col9.style.width = "10%";
	      col9.colspan = "1";

	      col10.innerHTML = "<div align=''>"+"<input name='remarksArr' value='"+reason+"' type='hidden'></input>"+reason+"</div>"; 
	      col10.class = "div-table-col control";
	      col10.style.width = "10%";
	      col10.colspan = "1";
	      
	      col11.innerHTML="<div align=''><img src='/HIS/hisglobal/images/avai/minus.gif' onClick='deleteSecCatRow(this)'></div>";
	      col11.className='div-table-col control';
	      col11.style.width = "2%";
	      col11.colspan="1";
	       
	      var div = document.getElementById('divSelectedCategory');
		div.style.display = "block";
	      var table = document.getElementById("demo"); 
	      table.appendChild(row); 
		}


    function deleteSecCatRow(n)
	 {
		var tableObj=document.getElementById('demo');
		tableObj.deleteRow(n);	
	 } 

    $(function () {
        $('#lstServices').multiselect({
            includeSelectAllOption: true
        });
    });     

    function myFunction() {
    	 var today = new Date(); var dd = today.getDate(); 
    	 var mm = today.getMonth()+1; //January is 0! 
    	 var yyyy = today.getFullYear();
    	  if(dd<10){
        	  dd='0'+dd
        	  }
    	  if(mm<10){
        	  mm='0'+mm
        	  }
    	 var today = dd+'-'+mm+'-'+yyyy;
    	 document.getElementById('letterDateId').value= today;//Date().format('dd-Mon-YYYY');
    	}
</script>
<s:head />

</head>

<body onload = "myFunction()">
	<center>
		<s:form action="ChangeTreatmentCategory">
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded ">
		
	<s:hidden name="goFlag" value="%{goFlag}" />
	<s:hidden name="AfterGo" value="%{goFlag}" />
	<s:set name="goFlagForJsp" value="goFlag"></s:set>
	<div id="divAfterGo"> 
	
	<s:if test="%{#goFlagForJsp!=0}">			
	<s:hidden id="printHtmlId" name="printHtml" value="%{errorMessage}"/>
					<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
	</s:if>
	<s:if test="%{#goFlagForJsp!=0}">			
								
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
						<s:text name="Selected Episode Details"/></div>
					</div>
				</div>
				<div class = "div-table">
				   <div class="div-table-row">
						<div class="div-table-col" style="width: 20%;">
								<b><s:text name="Visit Type" /></b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						<b>	
							<s:text name="department" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						<b>	
							<s:text name="unit" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						<b>	
							<s:text name="Admission Number" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						<b>	
							<s:text name="Ward" />
						</b>
						</div>
							
				   </div>
				   <div class="div-table-row">
				     <div class="div-table-col control" style="width: 20%;">
						   <s:if test="%{#session.ipdFlag == 0}">
						     <s:text name = "OPD"/>
						   </s:if>
						   <s:else>
						      <s:text name = "IPD"/>
						   </s:else> 
						</div>
						<div class="div-table-col control" style="width: 20%;">
						   <s:if test="%{#session.ipdFlag == 0}">
						    <s:property value="%{#session.arrOpenEpisodeVO.department}"/>
						   </s:if>
						   <s:else>
						     <s:property value="%{#session.admittedPatientVO.department}"/>
						   </s:else> 
						</div>
						<div class="div-table-col control" style="width: 20%;">
						   <s:if test="%{#session.ipdFlag == 0}">
						   <s:property value="%{#session.arrOpenEpisodeVO.departmentUnit}"/>
						   </s:if>
						   <s:else>
						       <s:property value="%{#session.admittedPatientVO.departmentUnit}"/>
						   </s:else> 
						</div>
						<div class="div-table-col control" style="width: 20%;">
						   <s:if test="%{#session.ipdFlag == 0}">
						   <s:text name = "-"/>
						   </s:if>
						   <s:else>
						      <s:property value="%{#session.admittedPatientVO.admissionNo}"/>
						   </s:else> 
						</div>
						<div class="div-table-col control" style="width: 20%;">
						   <s:if test="%{#session.ipdFlag == 0}">
						   <s:text name = "-"/>
						   </s:if>
						   <s:else>
						      <s:property value="%{#session.admittedPatientVO.ward}"/>
						   </s:else> 
						</div>	
				   </div>
				</div>
				<!-- Checkpoint by Vasu -->
				<div class="div-table"  id="divEpisodeId">
			  <div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
						<s:text name="Change Treatment Category"/>&nbsp;</div>
					</div>
				</div>
				 <s:if test="%{#session.ipdFlag == 0}"> 
						<div class="div-table-row">
						<%-- <div class="div-table-col" style="width: 9%;">
						<b>	
							<s:text name="department" />
						</b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="unit" />
						</b>
						</div> --%>
						<div class="div-table-col" style="width: 15%;">
						   <font color="#FF0000" size="2">*</font>
						<b>	
							<s:text name="Treatment Category" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						   <font color="#FF0000" size="2">*</font>
						<b>	
							<s:text name="Applicable Services" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						  <font color="#FF0000" size="2">*</font>
						<b>	
							<s:text name="Valid Upto (No. of Days)" />
						</b>
						</div>
						<!--Added by Vasu on 13.April.2017  -->
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="Letter Reference No" />
						</b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="Letter Date" />
						</b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="Credit Limit" />
						</b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<!-- <font color="#FF0000" size="2">*</font> -->
						<b>	
							<s:text name="Remarks" />
						</b>
						</div>
							
					</div>
				     <s:set name="counter" value="0"/>
				     <s:set name="checkedValue" value=""></s:set>
				
					<s:iterator id="COLL_OPEN_EPISODE_VO" value="#session.arrOpenEpisodeVO" status="key" >
						<s:if test="%{#counter==0}">	
						 <s:set name="checkedValue" value="checked=''"></s:set>
						 <s:set name="counter" value="%{#counter+1}"/>
						</s:if>	
						<s:else>
						 <s:set name="checkedValue" value=""></s:set>
						</s:else>			
				
						<s:set name="episodeCode"  value='<s:property value="%{#COLL_OPEN_EPISODE_VO.episodeCode}"/>' ></s:set>
						<s:set name="newSecCatCode"  value='<s:property value="%{#COLL_OPEN_EPISODE_VO.patSecondaryCatCode}"/>'></s:set>
						<s:set name="episodeVisitNo" value='<s:property value="%{#COLL_OPEN_EPISODE_VO.episodeVisitNo}"/>'></s:set>
						 <input type="hidden" name="selectEpisodeVisitNo" value='<s:property value="%{#COLL_OPEN_EPISODE_VO.episodeVisitNo}"/>'/>
						  <input type="hidden" name="hiddenNewSecCatCode" value='<s:property value="%{#COLL_OPEN_EPISODE_VO.patSecondaryCatCode}"/>'/>
						   <input type="hidden" name="hiddenValidUptoDate" value='<s:property value="%{#COLL_OPEN_EPISODE_VO.ValidUpto}"/>'/>
						  <%-- 	<input type="hidden" name="visitNo"value='<s:property value="%{#COLL_OPEN_EPISODE_VO.episodeVisitNo}"/> --%>
						  	<input type = "hidden" name="IsIpdFlag" value ="0" >											
					 <div class="div-table-row"> 
						<%-- <div class="div-table-col control" style="width: 9%;">
							<s:property value="department"/>
						</div>
						<div class="div-table-col control" style="width: 10%;">
							<s:property value="departmentUnit"/>
						</div>  --%>
						<div class="div-table-col control" style="width: 15%;">
						  <%-- <s:property  value="%{#COLL_OPEN_EPISODE_VO.patSecondaryCatCode}"  /> --%>
					     <s:select id="newSecCatCodeId" name="newSecCatCode" value="%{#COLL_OPEN_EPISODE_VO.patSecondaryCatCode}" headerKey="-1" list="%{#session.optionSecondaryCategory}" headerValue="Select Value"  listKey="value" listValue="label"  cssStyle="width:190px" disabled="false" onchange="getNewCategoryCode(this)">
						 </s:select>
						</div>
						<div class="div-table-col control" style="width: 20%;">
					     <s:select id="lstServices" name="applicableServicesName2" class="4colactive" multiple="true"  headerKey="-1" list="%{#session.optionApplicableServices}"  listKey="value" listValue="label"  cssStyle="width:190px" disabled="false" >
						 </s:select>
						</div>
						<div class="div-table-col control" style="width: 20%;">
						<%-- <s:textfield id="arrValidUptoId_%{#key.index}" name="arrValidUpto" tabindex="1" key="arrValidUpto"  value="%{#COLL_OPEN_EPISODE_VO.ValidUpto}" maxlength="3" disabled="false" onkeypress="return validateNumeric(event)" >
						</s:textfield>&nbsp;<s:text name="Days"/> --%>
						<s:textfield id="arrValidUptoId_%{#key.index}" name="arrValidUpto" tabindex="1" key="arrValidUpto"  value="" maxlength="3" disabled="false" onkeypress="return validateNumeric(event)" >
						</s:textfield>&nbsp;<s:text name="Days"/>	
						</div>	
						<!--  -->						
						 <div class="div-table-col control" style="width: 10%;">
						 	<s:textfield  name="letterReferenceNo" tabindex="1" maxlength="50"  size="12" onkeypress="return validateAlphaNumericWithSpecialCharactersOnly(event)"/>
						 </div>
						 <div class="div-table-col control" style="width: 10%;">
						 	 <s:textfield  name="letterDate" id="letterDateId" tabindex="1" maxlength="150"  size="12" /> 
						 	<!-- <input name="letterDate" id="" type="date"/> -->
						 </div>
						 <div class="div-table-col control" style="width: 10%;">
						 	<s:textfield  name="creditLimit" tabindex="1" maxlength="5"  size="12" onkeypress="return validateNumeric(event)" />
						 </div>
						 <div class="div-table-col control" style="width: 10%;">
						
						<s:textfield  name="arrRemarks" tabindex="1" maxlength="150"  size="16"  onkeypress="return CheckMaxLength(event,this,150,3)" disabled="false">
						</s:textfield>
						</div>
						<!--  -->
						<input name="selectedDepartmentUnitType" id="selectedDepartmentUnitTypeId" type="hidden" value='<s:property value="departmentUnitType"/>'>
					    <input name="selectedDepartmentCode" id="selectedDepartmentCodeId" type="hidden" value='<s:property value="departmentCode"/>'>
					    <input name="selectedDepartmentUnitCode" id="selectedDepartmentUnitCodeId" type="hidden" value='<s:property value="departmentUnitCode"/>'>
					     <input name="catClientCode" id="catClientCode_%{#key.index}" type="hidden" value='<s:property value="catClientCode"/>'>
					     <div class="div-table-col control" style="width: 2%;">
							<img src = "/HISRegistration/hisglobal/images/plus.png" onclick = "addRow('<s:property value="episodeCode"/>','<s:property value="department"/>','<s:property value="departmentUnit"/>','<s:property value="departmentUnitCode"/>','<s:property value="departmentCode"/>');" >
						</div>
					  </div>
	 				</s:iterator>
				 </s:if> 
				 <s:else>
			       <div class="div-table-row">
						<div class="div-table-col" style="width: 15%;">
						  <font color="#FF0000" size="2">*</font>
						<b>	
							<s:text name="Treatment Category" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						   <font color="#FF0000" size="2">*</font>
						<b>	
							<s:text name="Applicable Services" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						   <font color="#FF0000" size="2">*</font>
						<b>	
							<s:text name="Valid Upto (No. of Days)" />
						</b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="Letter Reference No" />
						</b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="Letter Date" />
						</b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="Credit Limit" />
						</b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<!-- <font color="#FF0000" size="2">*</font> -->
						<b>	
							<s:text name="Remarks" />
						</b>
						</div>
							
					</div>
					<div class = "div-table row">
					  <div class="div-table-col control" style="width: 15%;">
						  <%--  <s:property  value="%{#session.admittedPatientVO.patSecondaryCatCode}"  /> --%>
					     <s:select id="newSecCatCodeId" name="newSecCatCode" value="%{#session.admittedPatientVO.patSecondaryCatCode}" headerKey="-1" list="%{#session.optionSecondaryCategory}" headerValue="Select Value"  listKey="value" listValue="label"  cssStyle="width:197px" disabled="false" onchange="getNewCategoryCode(this)">
						 </s:select>
						</div>
						<div class="div-table-col control" style="width: 20%;">
					     <s:select id="lstServices" name="applicableServicesName2" class="4colactive" multiple="true"  headerKey="-1" list="%{#session.optionApplicableServices}"  listKey="value" listValue="label"  cssStyle="width:190px" disabled="false" size="12">
						 </s:select>
						</div>
						<div class="div-table-col control" style="width: 20%;">
						<s:textfield id="" name="arrValidUpto" tabindex="1" key="arrValidUpto"  value="" maxlength="3" disabled="false" onkeypress="return validateNumeric(event)" >
						</s:textfield>&nbsp;<s:text name="Days"/>	
						</div>
						<div class="div-table-col control" style="width: 10%;">
						 	<s:textfield  name="letterReferenceNo" tabindex="1" maxlength="50"  size="12"  onkeypress="return validateAlphaNumericWithSpecialCharactersOnly(event)"/>
						 </div>
						 <div class="div-table-col control" style="width: 10%;">
						 	<s:textfield  name="letterDate" id="letterDateId" tabindex="1" maxlength="150"  size="12" />
						 </div>
						 <div class="div-table-col control" style="width: 10%;">
						 	<s:textfield  name="creditLimit" tabindex="1" maxlength="5"  size="12" onkeypress="return validateNumeric(event)" />
						 </div>
						 <div class="div-table-col control" style="width: 10%;">
						
						<s:textfield  name="arrRemarks" tabindex="1" maxlength="150"  size="16"  onkeypress="return CheckMaxLength(event,this,150,3)" disabled="false">
						</s:textfield>
						</div>	
						<input type = "hidden" name="IsIpdFlag" value ="1" >
					      <input type = "hidden" name ="selectEpisodeVisitNo" value = '<s:property value="%{#session.admittedPatientVO.episodeVisitNo}"/>'/>
					        <input type = "hidden" name ="AdmissionNo" value = '<s:property value="%{#session.admittedPatientVO.admissionNo}"/>'/>
					        <%-- <input type = "hidden" name ="selectedEpisodeForTreatmentCategory" value = '<s:property value="%{#session.admittedPatientVO.episodeCode}"/>'/> --%>
						<div class="div-table-col control" style="width: 2%;">
							<img src = "/HISRegistration/hisglobal/images/plus.png" onclick = "addRow('<s:property value="%{#session.admittedPatientVO.episodeCode}"/>','<s:property value="department"/>','<s:property value="departmentUnit"/>','<s:property value="departmentUnitCode"/>','<s:property value="departmentCode"/>');" >
						</div>
					</div>
					
					
				<%-- <div class="div-table-row">
						<div class="div-table-col" style="width: 8%;">
								<b><s:text name="select" /></b>
						</div>
						<div class="div-table-col" style="width: 8%;">
								<b><s:text name="Revoke" /></b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="Admission No" />
						</b>
						</DIV>
						<div class="div-table-col" style="width: 10%;">
						<b>	
							<s:text name="Ward Name" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						<b>	
							<s:text name="Treatment Category" />
						</b>
						</div>
						<div class="div-table-col" style="width: 20%;">
						<font color="#FF0000" size="2">*</font>
						<b>	
							<s:text name="Remarks" />
						</b>
						</div>
						</div>
						 <input type="hidden" name="hiddenNewSecCatCode" /> 
						<s:set id="ADMITTED_EPISODE_VO" value="#session.admittedPatientVO" name="ADMITTED_EPISODE_VO" ></s:set>
						<s:set name="admissionno" value="%{#session.admittedPatientVO.admissionNo}" ></s:set> --%>
						<%-- <div class="div-table-row">
						<div class="div-table-col control" style="width: 8%;">
						<s:property  value="%{#session.admittedPatientVO.patSecondaryCatCode}"  />
							<s:if test="%{#session.admittedPatientVO.patSecondaryCatCode== '-1' || #session.admittedPatientVO.patSecondaryCatCode==null || #session.admittedPatientVO.patSecondaryCatCode == #session.admittedPatientVO.patPrimaryCatCode}">
								 <input type="checkbox" name="selectEpisode" tabindex="1"  value='<s:property value="episodeCode"/>'  onclick='enableIPDEpisode(this);'/>
							</s:if>
							<s:else>
							 <input type="checkbox" name="selectEpisode" tabindex="1"  value='<s:property value="episodeCode"/>' disabled="disabled" onclick='enableIPDEpisode(this);'/>
							</s:else>
						</div>
						<div class="div-table-col control" style="width: 8%;">
						
						
						<s:if test="%{#session.admittedPatientVO.patSecondaryCatCode== '-1'  || #session.admittedPatientVO.patSecondaryCatCode==null || #session.admittedPatientVO.patSecondaryCatCode == #session.admittedPatientVO.patPrimaryCatCode}">
						 <input type="checkbox" name="revokeChk" tabindex="1" <s:property value="revokeChk" />  title='<s:property value="#key.index"/>' value='<s:property value="episodeCode"/>' onclick='enableIPDRevokeEpisode(this);'  disabled="disabled"/>													
						</s:if>
						<s:else>
						<input type="checkbox" name="revokeChk" tabindex="1"  value='<s:property value="episodeCode"/>' onclick='enableIPDRevokeEpisode(this);'/>
						</s:else>
														 
						</div>
						<div class="div-table-col control" style="width: 10%;">
							<s:property value="%{#session.admittedPatientVO.admissionNo}"/>
						</DIV>
						<div class="div-table-col control" style="width: 10%;">
							<s:property value="%{#session.admittedPatientVO.ward}"/>
						</div>
						<div class="div-table-col control" style="width: 20%;">
						   <s:property  value="%{#session.admittedPatientVO.patSecondaryCatCode}"  />
					     <s:select id="newSecCatCodeId" name="newSecCatCode" value="%{#session.admittedPatientVO.patSecondaryCatCode}" headerKey="-1" list="%{#session.optionSecondaryCategory}" headerValue="Select Value"  listKey="value" listValue="label"  cssStyle="width:197px" disabled="true" onchange="getNewCategoryCode(this)">
						 </s:select>
						</div>
						<div class="div-table-col control" style="width: 20%;">
						
						<s:textfield  name="remarks" tabindex="1" maxlength="150"  size="20"  onkeypress="return CheckMaxLength(event,this,150,3)" disabled="true">
						</s:textfield>
						</div>
									
						<input name="selectedDepartmentUnitType" id="selectedDepartmentUnitTypeId" type="hidden" value='<s:property value="departmentUnitType"/>'>
					    <input name="selectedDepartmentCode" id="selectedDepartmentCodeId" type="hidden" value='<s:property value="departmentCode"/>'>
					    <input name="selectedDepartmentUnitCode" id="selectedDepartmentUnitCodeId" type="hidden" value='<s:property value="departmentUnitCode"/>'>
					  </div> --%>
				</s:else> 
				        <%-- <s:iterator id="COLL_OPEN_EPISODE_VO_CAT" value="#session.previousTreatmentCategories" status="key" >
				         <div class="div-table-row">
						<div class="div-table-col control" style="width: 9%;">
							<s:property value="applicableServicesCode"/>
						</div>
						<div class="div-table-col control" style="width: 10%;">
							 <s:property value="letterReferenceNo"/>
						</div>
						</div>
				      </s:iterator>   --%>
				      <s:iterator id="COLL_PREVIOUS_EPISODE_VO" value="#session.getSelectedTreatmentCategoriesForParticularEpisode" status="key" >
				        <div class = "div-table row">
				           <div class="div-table-col control" style="width: 15%;">
				             <div align="center">
				             <s:property value = "%{#COLL_PREVIOUS_EPISODE_VO.treatmentCategory}"/>
				             </div>
				           </div>
				           <div class="div-table-col control" style="width: 20%;">
				            <div align="center">
				             <s:property value = "%{#COLL_PREVIOUS_EPISODE_VO.applicableServices}"/>
				             </div>
				           </div>
				           <div class="div-table-col control" style="width: 20%;">
				             <div align="center">
				             <s:property value = "%{#COLL_PREVIOUS_EPISODE_VO.validUpto}"/>
				             </div>
				           </div>
				           <div class="div-table-col control" style="width: 10%;">
				            <div align="center">
				             <s:property value = "%{#COLL_PREVIOUS_EPISODE_VO.creditLetterRefNo}"/>
				             </div>
				           </div>
				           <div class="div-table-col control" style="width: 10%;">
				            <div align="center">
				             <s:property value = "%{#COLL_PREVIOUS_EPISODE_VO.creditLetterDate}"/>
				             </div>
				           </div>
				           <div class="div-table-col control" style="width: 10%;">
				             <div align="center">
				             <s:property value = "%{#COLL_PREVIOUS_EPISODE_VO.creditLimit}"/>
				             </div>
				           </div>
				           <div class="div-table-col control" style="width: 10%;">
				             <div align="center">
				             <s:property value = "%{#COLL_PREVIOUS_EPISODE_VO.remarks}"/>
				             </div>
				           </div>
				        </div>
				      </s:iterator>
				 </div> 		<!-- Checkpoint by Vasu -->
		</s:if>	
                <div id = "divSelectedCategory" style="display:none">
                  <p><Font color="red"><s:text name="Selected Treatment Categories Are:"/></Font></p>
                   <br>
                   <div class = "div-table" id = "demo">
                    
                   </div>
                </div>
				<div class="div-table-button">
					<div class="div-table-row footerBar">
							<div class="div-table-col"> </div>
					</div>
					<div class="div-table-row emptyBar">
						<div class="div-table-col"> </div>
					</div>
					<div class="div-table-row" align="center">
						
						<s:if test="%{#goFlagForJsp!=0}">
							<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
							<a href="#" class="button" id="clearId" onclick ="submitForm('NEW')"><span class="clear"><s:text name="clear"/></span></a>
						</s:if>
						<s:else>
						<a href="#" class="button" id="initialClearId" ><span class="clear"><s:text name="clear"/></span></a>
						</s:else>
						
						
						<a href="#" class="button" id="cancelId" onclick ="submitForm('NEW')"><span class="cancel"><s:text name="cancel"/></span></a>
					</div>
					
				</div>
				
				</div>
	
	
<div class="div-table" id="divPrintId" style="font-weight: normal;color: black;"></div>

</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token/>
</s:form>
<div class="div-table">
	<div class="div-table-row ">
			<div class="div-table-col alignLeft fontError">
			<s:property value="errorMessage" />
			</div>
	</div>
	</div>
	
	<div class="div-table">
	<div class="div-table-row ">
	
<s:hidden  name="hmode"  value="%{hmode%}"></s:hidden>
<s:hidden  name="secCatCodeAndExpiryDay" value="%{secCatCodeAndExpiryDay}"></s:hidden>
<s:hidden  name="isInvalidCatCode" value="%{isInvalidCatCode}"></s:hidden>
			<div class="div-table-col alignLeft fontNormalMessage">
			<s:property value="normalMessage" />
			</div>
	</div>
	</div>
<script type="text/javascript" src="./../../registration/transactions/js/ChangeTreatmentCategory.js" /></script>	
</center>
</body>
</html>