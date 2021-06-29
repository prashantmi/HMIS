<%@page import="loinc.vo.LoincVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 02-Jan-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="./../hisglobal/css/buttons.css" rel="stylesheet"	type="text/css"/>
<link href="./../hisglobal/css/layout.css" rel="stylesheet"	type="text/css"/>
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->

<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>

<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" src="./../loinc/masters/js/Loinc.js"></script>
</head>
<body>
<s:form action="Loinc">
<h4><font color="#FF0000"><s:property value="%{locModel.StrWarning}"/></font></h4>
<%-- <h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4> --%>
<h4><s:property value="message"/></h4>
<div class="wrapper rounded" >
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Loinc"/>&nbsp;<s:text name="Detail"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
		<s:if test="flagAddMod=='MODIFY'" >
					<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.testname"/>&nbsp;</div>
				<div class="div-table-col column  width45"  ><s:select key="strLoincTestName" value="%{locModel.strTestNameCode}" headerKey="-1" headerValue="Select Value" 
				 			list="%{#session.testName}" listKey="value" listValue="label" 	 name="locModel.strTestNameCode" cssStyle="width:197px" onchange="ajaxTestCode(this);" disabled="true"></s:select>
				 </div>
				  <s:hidden name="strTestNameCode"  key="strTestNameCode" value="%{locModel.strTestNameCode}"></s:hidden>
				 <input name="strTestNameValue"  type="hidden">
			</div>		
		</s:if>
		<s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.testname"/>&nbsp;</div>
				<div class="div-table-col column  width45"  ><s:select key="strLoincTestName" value="%{locModel.strTestNameCode}" headerKey="-1" headerValue="Select Value" 
				 			list="%{#session.testName}" listKey="value" listValue="label" 	 name="locModel.strTestNameCode" cssStyle="width:197px" onchange="ajaxTestCode(this);" required="true"></s:select>
				 </div>
				 <!--  <input name="strTestNameCode"  type="hidden" > -->
				 <s:hidden name="strTestNameCode"  key="strTestNameCode" value="%{locModel.strTestNameCode}"></s:hidden>
				 <input name="strTestNameValue"  type="hidden">
			</div>		
		</s:else>
		
		<s:if test="flagAddMod=='MODIFY'" >
		
		<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>Test Para</div>
				<div class="div-table-col column  width45"  ><s:select key="strTestParaNameCode" value="%{locModel.strTestParaNameCode}" headerKey="-1" headerValue="Select Value" 
				 			list="%{#session.testParaName}" listKey="value" listValue="label" 	 name="locModel.strTestParaNameCode" cssStyle="width:197px" onchange="getScale();" disabled="true"></s:select>
				 </div>
				  <!-- <input name="strTestParaNameCode"  type="hidden" > -->
				   <s:hidden name="strTestParaNameCode"  key="strTestParaNameCode" value="%{locModel.strTestParaNameCode}"></s:hidden>
				<!--  <input name="strTestParaNameValue"  type="hidden"> -->
				 <s:hidden name="strTestParaNameValue"  key="strTestParaNameValue" value="%{locModel.strTestParaNameValue}"></s:hidden>
			</div>
			 <div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>Sample</div>
				<div class="div-table-col column  width45"  ><s:select key="strTestSampleCode" value="%{locModel.strTestSampleCode}" headerKey="-1" headerValue="Select Value" 
				 			list="%{#session.testSample}" listKey="value" listValue="label"  name="locModel.strTestSampleCode" cssStyle="width:197px" onchange="getProperty();" disabled="true"></s:select>
				 </div>
				  <!-- <input name="strTestSampleCode"  type="hidden"> -->
				  <s:hidden name="strTestSampleCode"  key="strTestSampleCode" value="%{locModel.strTestSampleCode}"></s:hidden>
				  <input name="strTestSampleValue"  type="hidden"> 
				 <%-- <s:hidden name="strTestSampleValue"  key="strTestSampleValue" value="%{locModel.strTestSampleValue}"></s:hidden> --%>
			</div>	 
		</s:if>	
		<s:elseif test="flagAddMod!='ADD'" >
		
		<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>Test Para</div>
				<div class="div-table-col column  width45"  ><s:select key="strTestParaNameCode" value="%{locModel.strTestParaNameCode}" headerKey="-1" headerValue="Select Value" 
				 			list="%{#session.testParaName}" listKey="value" listValue="label" 	 name="locModel.strTestParaNameCode" cssStyle="width:197px" onchange="getScale();"></s:select>
				 </div>
				   <input name="strTestParaNameCode"  type="hidden" > 
				<%--  <s:hidden name="strTestParaNameCode"  key="strTestParaNameCode" value="%{locModel.strTestParaNameCode}"></s:hidden> --%>
				  <input name="strTestParaNameValue"  type="hidden"> 
				<%-- <s:hidden name="strTestParaNameValue"  key="strTestParaNameValue" value="%{locModel.strTestParaNameValue}"></s:hidden> --%>
				
			</div>
			 <div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>Sample</div>
				<div class="div-table-col column  width45"  ><s:select key="strTestSampleCode" value="%{locModel.strTestSampleCode}" headerKey="-1" headerValue="Select Value" 
				 			list="%{#session.testSample}" listKey="value" listValue="label"  name="locModel.strTestSampleCode" cssStyle="width:197px" onchange="getProperty();"></s:select>
				 </div>
				  <input name="strTestSampleCode"  type="hidden"> 
				<%-- <s:hidden name="strTestSampleCode"  key="strTestSampleCode" value="%{locModel.strTestSampleCode}"></s:hidden> --%>
				 <input name="strTestSampleValue"  type="hidden"> 
				<%--  <s:hidden name="strTestSampleValue"  key="strTestSampleValue" value="%{locModel.strTestSampleValue}"></s:hidden> --%>
			</div>	 
		</s:elseif>	
			<s:else >
			<div class="div-table-row">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>Test Para</div>
				<div id="divTestParaComboId" class="div-table-col control" style="width: 16%;" >
					<select name="patAddTestParaCode"  tabindex="1"  id="patAddTestParaCodeId" class="select77prcnt" onchange="getScale();">
						<option value="-1">Select Value</option>
					</select>
					<input name="defaultpatAddTestParaCode" type="hidden">
					<!-- <input name="strTestParaNameCode" type="hidden"> -->
					 <s:hidden name="strTestParaNameCode"  key="strTestParaNameCode" value="%{locModel.strTestParaNameCode}"></s:hidden>
					 <input name="strTestParaNameValue" type="hidden"> 
					<%-- <s:hidden name="strTestParaNameValue"  key="strTestParaNameValue" value="%{locModel.strTestParaNameValue}"></s:hidden> --%>
				</div>
			 	<div id="divDistrictTextId" class="div-table-col control" style="width: 16%; display: none;">
					<input name="patAddTestPara" id="patAddTestParaId"  tabindex="1"  maxlength="15" type="text" class="input75prcnt">
				</div>
			</div> 
			<div class="div-table-row">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>Sample</div>
				<div id="divSampleComboId" class="div-table-col control" style="width: 16%;">
					<select name="patAddSampleCode"  tabindex="1"  id="patAddSampleCodeId" class="select77prcnt" onchange="getProperty();">
						<option value="-1">Select Value</option>
					</select>
					<input name="defaultpatAddSampleCode" type="hidden">
					<!-- <input name="strTestSampleCode" type="hidden"> -->
					<s:hidden name="strTestSampleCode"  key="strTestSampleCode" value="%{locModel.strTestSampleCode}"></s:hidden>
					 <input name="strTestSampleValue" type="hidden"> 
					 <%-- <s:hidden name="strTestSampleValue"  key="strTestSampleValue" value="%{locModel.strTestSampleValue}"></s:hidden> --%>
				</div>
				 <div id="divSampleTextId" class="div-table-col control" style="width: 16%; display: none;">
					<input name="patAddSample" id="patAddSampleId"  tabindex="1"  maxlength="15" type="text" class="input75prcnt">
				</div>
				</div> 
			</s:else >
					
		
				
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.scale"/>&nbsp;</div>
				<div class="div-table-col column  width45"><s:textfield key="strScale" value="%{locModel.strScale}"
															name="locModel.strScale" maxlength="100" size="30" readonly="true"> </s:textfield>
																	
				</div>
			</div>
			<s:if test="flagAddMod=='MODIFY'" >
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.property"/>&nbsp;</div>
				<div class="div-table-col column  width45" ><s:select key="strProperty" value="%{locModel.strUOMCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.UOMList}" listKey="value" listValue="label" name="locModel.strUOMCode" cssStyle="width:197px" disabled="true" ></s:select>
				 </div>
			</div>
			</s:if>	
			<s:else >
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.property"/>&nbsp;</div>
				<div class="div-table-col column  width45" ><s:select key="strProperty" value="%{locModel.strUOMCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.UOMList}" listKey="value" listValue="label" name="locModel.strUOMCode" cssStyle="width:197px" ></s:select>
				 </div>
			</div>
			</s:else>	
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.timeofmeasurement"/>&nbsp;</div>
				<div class="div-table-col column  width30"><s:textfield key="strTimeofMeasurement" 
															name="locModel.strTimeofMeasurement" maxlength="100" size="30" readonly="true" > </s:textfield>
																	
				</div>
		
					<div class="div-table-col column  width20"><a href="#" class="button" id="searchID" ><span class="search">Search</span></a>
				</div>
				
			</div>
	
			
			
		 	<s:if test="flagAddMod!='ADD'" > 
			<div class="div-table-row " id="strLoincDiv" >
			<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Loinc Code" />&nbsp;</div>
			<div class="div-table-col column  width10"><s:textfield id="strLoinc" name="strLoinc" readonly="true" ></s:textfield></div>
				</div>
				</s:if> 
		    <!-- <h1>Patient Registration</h1> -->
			<div class="div-table">
			<div class="div-table-row">

    			<div class="div-table-col footerBar"></div>
  				  <div class="div-table-col emptyBar"></div>

			</div>
			<div class="div-table-row title">
					<div class="div-table-col width60"> Loinc Search</div>
					<div class="div-table-col width20">
							<s:radio id="isLoincName" name="locModel.isLoincName" value="%{locModel.isLoincName}" list="#{'1':'Loinc Name','2':'Loinc Code'}" onchange="showLoincSearchTile();"></s:radio>
					</div>
					<div class="div-table-col column  width15"><s:textfield key="strLoincSearchParameter" 
															name="locModel.strLoincSearchParameter" maxlength="100" size="30"  > </s:textfield>
																	
					</div>
					<div class="div-table-col control width5">
						<img class="button" src='./../hisglobal/images/GO.png' tabindex="1" style=cursor:pointer onclick="searchLoinc()" onkeypress="if(event.keyCode==13) searchLoinc()"/>
					</div>
			</div>
		



			<div class="div-table-col footerBar"></div>
			<div class="div-table-col emptyBar"></div>
			 
			<%int i=0; %>
				<s:iterator value="#session.lstLoincVo">
				<%i++;  %>
				</s:iterator>	
				<%System.out.println(i); %>
			<%if(i>=1){ %>
			<div class="div-table-listing rounded">
			
			<div class="div-table-row ">
								<div class="div-table-col  labelLeft width5 ">
								<b>Select</b>
								</div>
								<div class="div-table-col  labelLeft width10 ">
										<b>Loinc Code</b>
								</div>
								<div class="div-table-col  labelLeft width20 ">
										<b>Component</b>
								</div>
								<div class="div-table-col  labelLeft width10 ">
										<b>System</b>
								</div>
								<div class="div-table-col  labelLeft width10 ">
										<b>Time Aspect</b>
								</div>
								<div class="div-table-col  labelLeft width10 ">
										<b>Property</b>
								</div>
								<div class="div-table-col  labelLeft width10 ">
										<b>Scale</b>
								</div>
								<div class="div-table-col  labelLeft width20 ">
										<b>Method</b>
								</div>
						</div>
				<s:iterator value="#session.lstLoincVo" >
										<div class="div-table-row ">
										
								<div class="div-table-col  control width7 ">
									  
									<input align="left" type="radio"	name="strLoincCode"  value='<s:property value="strLoincCode"/>' tabindex="1"  />
								  
								</div>
								<div class="div-table-col control width7 ">
										<s:property value="strLoincCode"/>
								</div>
								<div class="div-table-col  control width20 ">
										<s:property value="strComponentName"/>
								</div>
								<div class="div-table-col control width10 ">
										<s:property value="strSystem"/>
								</div>
								<div class="div-table-col  control width10 ">
										<s:property value="strTimeofMeasurement"/>
								</div>
								<div class="div-table-col  control width10 ">
										<s:property value="strProperty"/>
								</div>
								<div class="div-table-col  control width10 ">
										<s:property value="strScale"/>
								</div>
								<div class="div-table-col  control width20 ">
										<s:property value="strMethod"/>
								</div>
						</div>
				</s:iterator>
				
				</div>
				<%} %>
			
						
</div>


 
<div class="div-table-button">
<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
				</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" id="submitId"><span class="save" onclick="onSaveClick();">Save</span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel">Cancel</span></a>
			<a href="#" class="button" id="clearLoincId"><span class="clear">Clear</span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" id="updateId"><span class="save" >Save</span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel">Cancel</span></a>
			<a href="#" class="button" id="clearModifyId"><span class="clear">Clear</span></a>
			</s:else>
	</div>
</div>

</div>

</div>


<s:hidden name="StrSeqNo" ></s:hidden>	
<s:hidden name="StrOldLoincCode" value="%{locModel.strOldLoincCode}"></s:hidden>	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strLocCode" name="locModel.strLocCode" value="%{locModel.strLocCode}"></s:hidden>
	
<s:token></s:token>
</s:form>


<%-- <h4><font color="#FF0000"><s:property value="%{locModel.StrWarning}"/></font></h4>
 <h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4> 
<h4><s:property value="message"/></h4> --%>
<%-- <h4><font color="#FF0000"><s:property value="%{locModel.StrWarning}"/></font></h4>
<h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4>
<h4><s:property value="message"/></h4> --%>

<s:actionerror/>


<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>

<script type="text/javascript" >

$('[name="locModel.strTestNameCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="patAddTestParaCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="patAddSampleCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="locModel.strUOMCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});

window.onload = function() {
//	$("#strLoincDiv")[0].style.display="none";
	if($("form :radio") != null){
	var len =$("form :radio").length;
	var i;
	for (i = 0; i < len; i++) {
	    if($("form :radio")[i].value == $('[name= "StrOldLoincCode"]')[0].value)
	    	{
	    	
	    	$("form :radio")[i].checked = true;
	    	$('[name= "strLoinc"]')[0].value=$("form :radio")[i].value;
	    	
	    	}
	}
	}  
	};
	
	$("input[name=strLoincCode]:radio").change(function () {
		
		$('[name= "strLoinc"]')[0].value=$('[name="strLoincCode"]:checked').val();
		
	});
$('#searchID').click(function(e){

		  $('[name="strTestNameValue"]')[0].value = $("#Loinc_locModel_strTestNameCode option:selected")[0].text;
		  $('[name="strTestNameCode"]')[0].value = $("#Loinc_locModel_strTestNameCode option:selected")[0].value;
		  $('[name="strTestParaNameCode"]')[0].value = $("#patAddTestParaCodeId option:selected")[0].value;
		//$('[name="strTestParaNameCode"]')[0].value = $('[name= "strTestParaNameCode"]')[0].value
		  $('[name="strTestParaNameValue"]')[0].value = $("#patAddTestParaCodeId option:selected")[0].text;
		  
		  $('[name="strTestSampleCode"]')[0].value = $("#patAddSampleCodeId option:selected")[0].value;
		  $('[name="strTestSampleValue"]')[0].value = $("#patAddSampleCodeId option:selected")[0].text;
		  
	$("#Loinc").attr('action',"/HISRegistration/registration/searchSuggestiveLoinc.action");
	if($('#Loinc').form('validate'))
	{
		//$("#strLoincDiv")[0].style.display="block";
			$('#Loinc').submit();
	}
});

$('#submitId').click(function(e)
		{
		$("#Loinc").attr('action',"/HISRegistration/registration/saveLoinc.action");
		if($('#Location').form('validate')==true)
			{			
				$('#Loinc').submit();
			}
});
$('#updateId').click(function(e){
	if(typeof $('[name="strLoincCode"]')[0]== 'undefined')
	{
	alert("please select a loinc code");
	return false;	
	}
	$("#Loinc").attr('action',"/HISRegistration/registration/updateLoinc.action");
	if($('#Loinc').form('validate'))
		{		
			$('#Loinc').submit();
		}
});


$('#cancelId').click(function(e){
	
	$("#Loinc").attr('action',"/HISRegistration/registration/cancelLoinc.action");
			$('#Loinc').submit();
});





$('#clearLoincId').click(function(e)

{
	/*  $('[name="locModel.strTestNameCode"]').val('-1');	
	
	$('[name="locModel.strTestParaNameCode"]').val('-1');
		
	$('[name="locModel.strTestSampleCode"]').val('-1');
	
	$('[name="locModel.strScale"]').val('');
	$('[name="locModel.strUOMCode"]').val('-1');
	$('[name="locModel.strTimeofMeasurement"]').val('');  */
	//location.reload(true);
	$("#Loinc").attr('action',"/HISRegistration/registration/addLoinc.action");
	$('#Loinc').submit();
  $('[name="locModel.strTestNameCode"]').val('-1');	
	
	$('[name="locModel.strTestParaNameCode"]').val('-1');
		
	$('[name="locModel.strTestSampleCode"]').val('-1');
	
	$('[name="locModel.strScale"]').val('');
	$('[name="locModel.strUOMCode"]').val('-1');
	$('[name="locModel.strTimeofMeasurement"]').val('');  
		
		});	

	
$('#clearModifyId').click(function(e){
	location.reload(true);
});

function searchLoinc()
{
	
	if(document.getElementsByName("locModel.strLoincSearchParameter")[0].value == null  || document.getElementsByName ("locModel.strLoincSearchParameter")[0].value == "" )
		{
		
			alert("Please Enter Search Paramaeter");
			return false;
		}
	$('[name="strTestNameCode"]')[0].value = $("#Loinc_locModel_strTestNameCode option:selected")[0].value;
	document.forms[0].action="searchParamLoinc.action";
	
	document.forms[0].submit();
}

function getLoincSuggestive()
{
	  $('[name="strTestNameValue"]')[0].value = $("#Loinc_locModel_strTestNameCode option:selected")[0].text;
	  $('[name="strTestNameCode"]')[0].value = $("#Loinc_locModel_strTestNameCode option:selected")[0].value;
	  $('[name="strTestParaNameCode"]')[0].value = $("#patAddTestParaCodeId option:selected")[0].value;
	  $('[name="strTestParaNameValue"]')[0].value = $("#patAddTestParaCodeId option:selected")[0].text;
	  
	  $('[name="strTestSampleCode"]')[0].value = $("#patAddSampleCodeId option:selected")[0].value;
	  $('[name="strTestSampleValue"]')[0].value = $("#patAddSampleCodeId option:selected")[0].text;
	  
$("#Loinc").attr('action',"/HISRegistration/registration/searchSuggestiveLoinc.action");
if($('#Loinc').form('validate'))
{
	//$("#strLoincDiv")[0].style.display="block";
		$('#Loinc').submit();
}
}

</script>
</body>
</html>