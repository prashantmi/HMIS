<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 31-Dec-2013 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>

<link href="./../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->

<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>

function getIsRefer()
{
	showIsRefer();	
	document.forms[0].action="addUnit.action";
	document.forms[0].submit();
}

function showIsRefer()
{
	var unitType=document.getElementsByName("unitModel.strIsGeneral")[0].value;
	//alert(unitType);
	if(unitType==2)
	{
		document.getElementById("unitModel.strIsRefer").style.display="";
		document.getElementById("unitModel.strIsAppointment").style.display="";
	}
	else
	{
		document.getElementById("unitModel.strIsRefer").style.display="none";
		document.getElementsByName("unitModel.strIsRefer")[0].value="0";
		document.getElementById("unitModel.strIsAppointment").style.display="none";
		document.getElementsByName("unitModel.strIsAppointment")[0].value="0";
	}	
}

function getIsReferModify()
{
	var unitType=document.getElementsByName("unitModel.strIsGeneral")[0].value;
	//alert(unitType);
	if(unitType==2)
	{
		document.getElementById("unitModel.strIsRefer").style.display="";
		document.getElementById("unitModel.strIsAppointment").style.display="";

	}
	else
	{
		document.getElementById("unitModel.strIsRefer").style.display="none";
		document.getElementsByName("unitModel.strIsRefer")[0].value="0";
		document.getElementById("unitModel.strIsAppointment").style.display="none";
		document.getElementsByName("unitModel.strIsAppointment")[0].value="0";
	}
	
	document.forms[0].action="modifyUnit.action";
	document.forms[0].submit();
}

function getExpiry()
{
	if(typeof(document.getElementsByName("unitModel.strIsExpiry")[0])!="undefined")
	{		
		var unitType=document.getElementsByName("unitModel.strIsExpiry")[0].value;
		if(unitType==2)
		{
			$("#expdays").val('');		
			$('[name="unitModel.strExpiryDay"]').validatebox({required: false, validType: "numericwithoutzero"});
			$("#strExpiryDaysId").hide();
			$('[name="unitModel.strExpiryMonth"]').validatebox({required: true, validType: "month"});
			$("#strExpiryMonthId").show();
		}
		else if(unitType==1)
		{
			$("#expmnth").val('');		
			$('[name="unitModel.strExpiryMonth"]').validatebox({required: false, validType: "month"});
			$("#strExpiryMonthId").hide();			
			$('[name="unitModel.strExpiryDay"]').validatebox({required: true, validType: "numericwithoutzero"});
			$("#strExpiryDaysId").show();			
		}
		else 
		{
			$("#expmnth").val('');		
			$('[name="unitModel.strExpiryMonth"]').validatebox({required: false, validType: "month"});
			$("#strExpiryMonthId").hide();
			$("#expdays").val('');		
			$('[name="unitModel.strExpiryDay"]').validatebox({required: false, validType: "numericwithoutzero"});
			$("#strExpiryDaysId").hide();
		}
	}
			
}



function showHead(val)
{
	if(val.value==1)
	{
		$('[name="unitModel.strEmpCode"]').validatebox({required: true,validType: ['selectCombo[-1]']});
		$("#strEmpCodeId").show();
		
	}
	else
	{
		$('[name="unitModel.strEmpCode"]')[0].value="-1";
		$('[name="unitModel.strEmpCode"]').validatebox({required: false,validType: null});
		$("#strEmpCodeId").hide();
	}
}

function showInit()
{
	showHead(document.getElementsByName("strIsUnit")[0]);
	showIsRefer();
	getExpiry();
}



function populateUnitName(emp)
{
	if(emp.value!='-1')
		document.getElementsByName("unitModel.strUnitName")[0].value=emp.options[emp.selectedIndex].text;
	else
		document.getElementsByName("unitModel.strUnitName")[0].value="";
}


</script>
</head>
<body onload="showInit();">
<s:form action="Unit">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.unit"/>&nbsp;<s:text name="global.detail"/>						
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.department"/></div>
				<div class="div-table-col column  width45"><s:property value="unitModel.strDeptName"/>
								<s:hidden key="strDeptName" name="unitModel.strDeptName" value="%{unitModel.strDeptName}"></s:hidden>
								<s:hidden key="strDeptCode" name="unitModel.strDeptCode" value="%{unitModel.strDeptCode}"></s:hidden>
				</div>
			</div>
			<div id="unitModel.strIsUnit" class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.isunit"/></div>
				<div class="div-table-col column  width45"><s:radio key="strIsUnit" id="unitModel.strIsUnit" name="unitModel.strIsUnit" value="%{unitModel.strIsUnit}"
															list="#{'0':'Department Unit','1':'Doctor Unit'}" onchange="showHead(this);"> </s:radio>
															<s:hidden key="strIsUnit" name="strIsUnit" value="%{unitModel.strIsUnit}"></s:hidden>
				</div>
			</div>
			<div id="strEmpCodeId" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.head"/></div> 
				<div class="div-table-col column  width45"><s:select key="strEmpCode" value="%{unitModel.strEmpCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.hodList}" listKey="value" listValue="label" name="unitModel.strEmpCode" onchange="populateUnitName(this);" cssStyle="width:198px"> </s:select>			
				</div>
			</div>			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unit"/>&nbsp;<s:text name="global.name"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strUnitName" name="unitModel.strUnitName" value="%{unitModel.strUnitName}" maxlength="50" size="30" > </s:textfield>
				<s:hidden key="strOldUnitName" name="unitModel.strOldUnitName" value="%{unitModel.strUnitName}"></s:hidden>
				</div>
			</div>
			<s:if test="flagAddMod=='ADD'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unit"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsGeneral" value="%{unitModel.strIsGeneral}" headerKey="-1" headerValue="Select Value" 
				 				 list="#{'1':'General','2':'Special','3':'Causality','0':'Other'}" onchange="getIsRefer()" name="unitModel.strIsGeneral" cssStyle="width:197px"></s:select>
				 </div>
			</div>
			</s:if>
			<s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unit"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsGeneral" value="%{unitModel.strIsGeneral}" headerKey="-1" headerValue="Select Value" 
				 				 list="#{'1':'General','2':'Special','3':'Causality','0':'Other'}" onchange="getIsReferModify()" name="unitModel.strIsGeneral" disabled="true" cssStyle="width:197px"></s:select>
				 				<s:hidden key="strIsGeneral" name="unitModel.strIsGeneral" value="%{unitModel.strIsGeneral}"></s:hidden>
				 </div>
			</div>
			</s:else>
			<div id="unitModel.strIsRefer" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><s:text name="unit.isrefer"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsRefer" value="%{unitModel.strIsRefer}"
				 				 list="#{'1':'Compulsary','0':'Not Compulsary'}" name="unitModel.strIsRefer" cssStyle="width:198px"> </s:select>
				 </div>
			</div>
			<div id="unitModel.strIsAppointment" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><s:text name="global.is"/>&nbsp;<s:text name="Apt_appointment"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsAppointment" value="%{unitModel.strIsAppointment}"
				 				 list="#{'0':'No','1':'Yes'}" name="unitModel.strIsAppointment" > </s:select>
				 </div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.diagnosis"/></div> 
				<div class="div-table-col column  width45"><s:select key="strDiagnosisType" value="%{unitModel.strDiagnosisType}" headerKey="-1" headerValue="Select Value" 
				 				list="#{'0':'ICD Only','1':'Hospital Code Only','2':'ICD & Hospital(Default)','3':'ICD(Default) & Hospital','4':'ICD-O only','5':'ICD-O(Default) & ICD','6':'ICD(Default) & ICD-O'}" 
				 				name="unitModel.strDiagnosisType" cssStyle="width:197px"> </s:select>			
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.location"/></div>
				<div class="div-table-col column  width45"><s:select key="strUnitLocCode" value="%{unitModel.strUnitLocCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.locationList}" listKey="value" listValue="label" name="unitModel.strUnitLocCode" cssStyle="width:197px"> </s:select>
				</div>
			</div>
			
			<!--mobile field add by warish 20-aug-2017---  -->
			<div class="div-table-row ">
				<div class="div-table-col label  width50">&nbsp;<s:text name="MobileNo."/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMobileNo" name="unitModel.strMobileNo" value="%{unitModel.strMobileNo}" maxlength="10" cssStyle="width:195px" onkeypress="return validateNumericOnly(this,event);"> </s:textfield>
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.epiclsedays"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strEpiDefCloseDays" name="unitModel.strEpiDefCloseDays" maxlength="3" cssStyle="width:195px"> </s:textfield>&nbsp;days
				</div>
			</div>
			
			
			
			
			<!-- Below Fields Added To Enhance  Functinality of Maximum Walkins and Lower Age Limit On 25/09/2018 -->
			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.lowerAge"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strLowerAgeLimit" name="unitModel.strLowerAgeLimit" maxlength="3" cssStyle="width:195px"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.MaxWalkinReg"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMaxWalkinReg" name="unitModel.strMaxWalkinReg" maxlength="3" cssStyle="width:195px"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.MaxWalkFolloUp"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMaxWalkinFolloUp" name="unitModel.strMaxWalkinFolloUp" maxlength="3" cssStyle="width:195px"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.MaxWalkPortReg"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMaxWalkinPortReg" name="unitModel.strMaxWalkinPortReg" maxlength="3" cssStyle="width:195px"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.MaxWalkPortFollowUP"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMaxWalkinPortFollowUP" name="unitModel.strMaxWalkinPortFollowUP" maxlength="3" cssStyle="width:195px"> </s:textfield>
				</div>
			</div>
			
			
			
			
			
			
			
			
			<s:if test="flagRenewal=='YES'">			
			<div class="div-table-row " style="display: none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.expiry"/></div> 
				<div class="div-table-col column  width45"><s:select key="strIsExpiry" value="%{unitModel.strIsExpiry}" 
				 				list="#{'0':'Not Required','1':'Day Based','2':'Month Based'}" 
				 				name="unitModel.strIsExpiry" onchange="getExpiry();"> </s:select>			
				</div>
			</div>
			</s:if>
			<s:else>
			<div class="div-table-row " style="display: none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.expiry"/></div> 
				<div class="div-table-col column  width45"><s:select key="strIsExpiry" value="%{unitModel.strIsExpiry}" 
				 				list="#{'0':'Not Required','1':'Month Based','2':'Day Based'}" 
				 				name="unitModel.strIsExpiry" onchange="getExpiry();" disabled="true" cssStyle="width:197px"> </s:select>
				 				<s:hidden key="strIsExpiry" name="unitModel.strIsExpiry" value="%{unitModel.strIsExpiry}"></s:hidden>
				</div>
			</div>
			</s:else>
			<div id="strExpiryMonthId" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.renmnth"/>&nbsp;<s:text name="unit.renmnth"/></div>	
				<div class="div-table-col column  width45"><s:textfield id="expmnth" key="strExpiryMonth" name="unitModel.strExpiryMonth" maxlength="6" > </s:textfield>
				</div>
			</div>
			<div id="strExpiryDaysId" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="unit.renday"/></div>	
				<div class="div-table-col column  width45"><s:textfield id="expdays" key="strExpiryDay" maxlength="3" name="unitModel.strExpiryDay" > </s:textfield>
				</div>
			</div>
			<div id="unitModel.strCardPrintSetup" class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.card"/>&nbsp;<s:text name="global.print"/>&nbsp;<s:text name="global.type"/></div>	
				<div class="div-table-col column  width45"><s:radio key="strCardPrintSetup" name="unitModel.strCardPrintSetup" list="#{'1':'Card','2':'Label'}" value="unitModel.strCardPrintSetup"> </s:radio>
				</div>
			</div>
			<s:if test="flagAddMod=='MODIFY'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.remarks"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strRemarks" name="unitModel.strRemarks" value="%{unitModel.strRemarks}" maxlength="50" size="30" > </s:textfield>
				</div>
			</div>
			</s:if>
			
			
			
</div>

<div class="div-table-button">
<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
				</div>
				<div class="div-table-row">
					<div class="div-table-col emptyBar"></div>
				</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" id="submitId" ><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId" ><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
<%-- 			<a href="#" class="button" onclick="submitCancelAction('<%=request.getSession().getAttribute("cnt_page") %>');"><span class="cancel">Cancel</span></a> --%>
			</s:if>
			<s:else >
			<a href="#" class="button" id="modifyId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="reloadId"><span class="clear"><s:text name="clear"/></span></a>
			</s:else>
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden name="flagRenewal" value="%{flagRenewal}"></s:hidden>
	<s:hidden key="strUnitCode" name="unitModel.strUnitCode" value="%{unitModel.strUnitCode}"></s:hidden>
	<s:hidden key="strDeptUnitCode" name="unitModel.strDeptUnitCode" value="%{unitModel.strDeptUnitCode}"></s:hidden>
	<s:hidden key="strRenewalType" name="unitModel.strRenewalType" value="%{unitModel.strRenewalType}"></s:hidden>
	<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
 <div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{unitModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message" /></div>


<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>


<%-- <h4><font color="#FF0000"><s:property value="%{unitModel.StrWarning}"/></font></h4>--%>

 

<%-- <h4><font color="#FF0000"><s:property value="%{unitModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript" >
$('[name="unitModel.strUnitName"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});
$('[name="unitModel.strIsGeneral"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']	
}); 
$('[name="unitModel.strDiagnosisType"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']	
}); 
$('[name="unitModel.strUnitLocCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']	
});
$('[name="unitModel.strEpiDefCloseDays"]').validatebox({required: true,	validType: 'numeric'});
$('#submitId').click(function(e){
	$("#Unit").attr('action',"/HISRegistration/registration/saveUnit.action");
	if($('#Unit').form('validate'))
		{
		sortandEncodebase64($("#Unit"));
		$('#Unit').submit();			
		}
			});
$('#cancelId').click(function(e){	
	$("#Unit").attr('action',"/HISRegistration/registration/cancelUnit.action");	
		$('#Unit').submit();			
			});
$('#modifyId').click(function(e){	
	$("#Unit").attr('action',"/HISRegistration/registration/updateUnit.action");	
	if($('#Unit').form('validate'))
		{
		sortandEncodebase64($("#Unit"));
		$('#Unit').submit();			
		}
});
$('#reloadId').click(function(e){
	location.reload("true");
});
$('#clearId').click(function(e){
	$("[name='unitModel.strIsUnit']").filter("[value='0']").prop("checked",true);
	showHead("-1");
	$('[name="unitModel.strUnitName"]').val('');
	$('[name="unitModel.strDiagnosisType"]').val('-1');
	$('[name="unitModel.strIsGeneral"]').val('-1');
	$('[name="unitModel.strUnitLocCode"]').val('-1');
	$('[name="unitModel.strEpiDefCloseDays"]').val('');	
	getIsRefer();
	$("[name='unitModel.strCardPrintSetup']").filter("[value='1']").prop("checked",true);
});
$('#reloadId').click(function(e){
	location.reload("true");
});

$('[name="unitModel.strMobileNo"]').validatebox({
	required :false,
	validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
});
</script>
</body>
</html>