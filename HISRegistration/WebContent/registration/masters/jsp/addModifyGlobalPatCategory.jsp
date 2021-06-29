<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 07-Jan-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
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
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/>
 -->
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>


function getVerificationDoc(verificationDoc)
{
	var res = verificationDoc.value.split("#");
	
	$('[name="patCategoryModel.strIdSize"]')[0].value = res[1];
	$('[name="patCategoryModel.strIdValidationType"]')[0].value = res[2];
	$('[name="patCategoryModel.strIdSize"]')[1].value = res[1];
	$('[name="patCategoryModel.strIdValidationType"]')[1].value = res[2];
}
function getIdDetails(idRequire)
{
	
	if(idRequire.value=="1")
	{
		 $('[name="patCategoryModel.strIdVerificationDoc"]').validatebox({required: true,	validType: ['selectCombo[-1]']
		 });
		
		 $("#divIdVerificationDoc").show();
		 $("#divIdSize").show();
		 $("#divIdValidationType").show();	
	}
	else if(idRequire.value=="2")
	{
		$('[name="patCategoryModel.strIdVerificationDoc"]').validatebox({required: true,	validType: ['selectCombo[-1]']
		});
		
		$("#divIdVerificationDoc").show();
		 $("#divIdSize").show();
		 $("#divIdValidationType").show();
	}
	else
	{
		 $("#idsize").val('');
		 $('[name="patCategoryModel.strIdValidationType"]')[0].value="-1";
		 $('[name="patCategoryModel.strIdVerificationDoc"]')[0].value="-1";
		 $('[name="patCategoryModel.strIdVerificationDoc"]').validatebox({required: false, validType: null});
		 $("#divIdSize").hide();
		 $("#divIdValidationType").hide();	
		 $("#divIdVerificationDoc").hide();
	}	
}
function getExpiryDetails(isExpiry)
{
	if(isExpiry.value=="1")
	{		
		$("#expdays").val('');		
		$('[name="patCategoryModel.strExpiryDays"]').validatebox({required: false, validType: "numericwithoutzero"});
		$("#strExpiryDaysId").hide();
		$('[name="patCategoryModel.strExpiryMonth"]').validatebox({required: true, validType: "month"});
		$("#strExpiryMonthId").show();
	}
	else if(isExpiry.value=="2")
	{
		$("#expmnth").val('');		
		$('[name="patCategoryModel.strExpiryMonth"]').validatebox({required: false, validType: "month"});
		$("#strExpiryMonthId").hide();
		$('[name="patCategoryModel.strExpiryDays"]').validatebox({required: true, validType: "numericwithoutzero"});
		$("#strExpiryDaysId").show();
	}
	else
	{
		$("#expmnth").val('');		
		$('[name="patCategoryModel.strExpiryMonth"]').validatebox({required: false, validType: "month"});
		$("#strExpiryMonthId").hide();
		$("#expdays").val('');		
		$('[name="patCategoryModel.strExpiryDays"]').validatebox({required: false, validType: "numericwithoutzero"});
		$("#strExpiryDaysId").hide();
	}
}

function showInit()
{
	//alert(document.getElementsByName("patCategoryModel.strIsIdReq")[0].value);
	//alert(document.getElementsByName("patCategoryModel.strIsExpiry")[0].value);
	getIdDetails(document.getElementsByName("patCategoryModel.strIsIdReq")[0]);
	getExpiryDetails(document.getElementsByName("patCategoryModel.strIsExpiry")[0]);
	getCategoryType(document.getElementsByName("patCategoryModel.strPatCategoryType")[0]);
}
function getCategoryType(catType)
{
	if(catType.value=="0" || catType.value=="2")
	{
		$("#divIdIsIdReq").show();
		$('[name="patCategoryModel.strIsIdReq"]').validatebox({required: true, validType: ['selectCombo[-1]']});
	}
	else
	{
		$('[name="patCategoryModel.strIsIdReq"]')[0].value="-1";
		$('[name="patCategoryModel.strIsIdReq"]').validatebox({required: false, validType: null});
		$("#divIdIsIdReq").hide();
	}
}
</script>
</head>
<body onload="showInit();">
<s:form action="GlobalPatCategory">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Global"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.detail"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<s:if test="flagAddMod=='ADD'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width45"><s:select key="strPatCategoryType" value="%{patCategoryModel.strPatCategoryType}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'Primary','1':'Treatment','2':'Other'}" name="patCategoryModel.strPatCategoryType" onchange="getCategoryType(this);" cssStyle="width:198px" ></s:select>
				 </div>
			</div>	
			</s:if>
			<s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width45"><s:select key="strPatCategoryType" value="%{patCategoryModel.strPatCategoryType}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'Primary','1':'Treatment','2':'Other'}" name="patCategoryModel.strPatCategoryType" disabled="true" onchange="getCategoryType(this);"  cssStyle="width:197px"></s:select>
				 				 <s:hidden key="strPatCategoryType" name="patCategoryModel.strPatCategoryType" value="%{patCategoryModel.strPatCategoryType}"></s:hidden>
				 </div>
			</div>	
			</s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col column  width45"><s:textfield key="strPatCategoryName" value="%{patCategoryModel.strPatCategoryName}" 
															name="patCategoryModel.strPatCategoryName" maxlength="30" size="30"> </s:textfield>
				<s:hidden key="strOldPatCategoryName" name="patCategoryModel.strOldPatCategoryName" value="%{patCategoryModel.strPatCategoryName}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.short"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col column  width45"><s:textfield key="strPatCategoryShort" value="%{patCategoryModel.strPatCategoryShort}" 
															name="patCategoryModel.strPatCategoryShort" maxlength="3" size="16" cssStyle="width:196px" > </s:textfield>
				</div>
			</div>
			<s:if test="flagAddMod=='ADD'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.group"/></div>
				<div class="div-table-col column  width45"><s:select key="strPatCategoryGroup" value="%{patCategoryModel.strPatCategoryGroup}" 
				 				 list=" #{'0':'Payment Category','1':'Staff','2':'Free','3':'Beneficery Credit with Reference','4':'Beneficery Credit without Reference'}" name="patCategoryModel.strPatCategoryGroup" cssStyle="width:197px"></s:select>
				 </div>
			</div>
			</s:if>
			<s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.group"/></div>
				<div class="div-table-col column  width45"><s:select key="strPatCategoryGroup" value="%{patCategoryModel.strPatCategoryGroup}" 
				 				 list=" #{'0':'Payment Category','1':'Staff','2':'Free','3':'Beneficery/Credit Only Used in NIMS'}" name="patCategoryModel.strPatCategoryGroup" disabled="true" cssStyle="width:197px" ></s:select>
				 				 <s:hidden key="strPatCategoryGroup" name="patCategoryModel.strPatCategoryGroup" value="%{patCategoryModel.strPatCategoryGroup}"></s:hidden>
				</div>
			</div>
			</s:else>
			<s:if test="flagAddMod=='ADD'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.economy"/></div>
				<div class="div-table-col column  width45"><s:select key="strEconomicStatus" value="%{patCategoryModel.strEconomicStatus}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'Below Poverty Line','2':'Above Poverty Line','3':'Others'}" name="patCategoryModel.strEconomicStatus" cssStyle="width:197px"></s:select>
				 </div>
			</div>	
			</s:if>
			<s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.economy"/></div>
				<div class="div-table-col column  width45"><s:select key="strEconomicStatus" value="%{patCategoryModel.strEconomicStatus}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'Below Poverty Line','2':'Above Poverty Line','3':'Others'}" name="patCategoryModel.strEconomicStatus" disabled="true" cssStyle="width:197px"></s:select>
				 				<s:hidden key="strEconomicStatus" name="patCategoryModel.strEconomicStatus" value="%{patCategoryModel.strEconomicStatus}" ></s:hidden>
				 </div>
			</div>	
			</s:else>		
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.priority"/></div>
				<div class="div-table-col column  width45"><s:select key="strCatPriority" value="%{patCategoryModel.strCatPriority}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'Yes','0':'No'}" name="patCategoryModel.strCatPriority"  cssStyle="width:197px"></s:select>
				 </div>
			</div>	
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.ispaid"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsPaid" value="%{patCategoryModel.strIsPaid}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'No','1':'Yes','2':'Credit Based'}" name="patCategoryModel.strIsPaid" cssStyle="width:197px"></s:select>
				 </div>
			</div>	
			<div id="divIdIsIdReq" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.idrequire"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsIdReq" value="%{patCategoryModel.strIsIdReq}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'No','1':'Yes','2':'Mapped With External Data'}" name="patCategoryModel.strIsIdReq" onchange="getIdDetails(this);" cssStyle="width:197px"></s:select>
				 </div>
			</div>	
			<div id="divIdVerificationDoc" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="verificationDoc.verificationDoc"/></div>
				<div class="div-table-col column  width45"><s:select key="strIdVerificationDoc" value="%{patCategoryModel.strIdVerificationDoc}" headerKey="-1" headerValue="Select Value"
								list="%{#session.globalVerificationDocList}" listKey="value" listValue="label" name="patCategoryModel.strIdVerificationDoc" onchange="getVerificationDoc(this);" cssStyle="width:198px"> </s:select>
				</div>
			</div>
			<div id="divIdSize" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="verificationDoc.idsize"/></div>
				<div class="div-table-col column  width45"><s:textfield id="idsize" key="strIdSize" value="%{patCategoryModel.strIdSize}" 
															name="patCategoryModel.strIdSize" maxlength="2" disabled="true" cssStyle="width:197px" > </s:textfield>
								<s:hidden key="strIdSize" name="patCategoryModel.strIdSize" value="%{patCategoryModel.strIdSize}"></s:hidden>
				</div>
			</div>
			<div id="divIdValidationType" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="verificationDoc.validationtype"/></div>
				<div class="div-table-col column  width45"><s:select key="strIdValidationType" value="%{patCategoryModel.strIdValidationType}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'Any Data','1':'Numeric','2':'Alphanumeric','3':'Characters'}" name="patCategoryModel.strIdValidationType" disabled="true" cssStyle="width:198px" ></s:select>
				 				<s:hidden key="strIdValidationType" name="patCategoryModel.strIdValidationType" value="%{patCategoryModel.strIdValidationType}"></s:hidden>
				 </div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.isexpiry"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsExpiry" value="%{patCategoryModel.strIsExpiry}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'Not Required','1':'Month Based','2':'Day Based'}" name="patCategoryModel.strIsExpiry" onchange="getExpiryDetails(this);" cssStyle="width:197px"></s:select>
				 </div>
			</div>	
			<div id="strExpiryMonthId" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.expmnth"/></div>	
				<div class="div-table-col column  width45"><s:textfield id="expmnth" key="strExpiryMonth" name="patCategoryModel.strExpiryMonth" maxlength="6" cssStyle="width:197px"> </s:textfield>
				</div>
			</div>
			<div id="strExpiryDaysId" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.expdays"/></div>	
				<div class="div-table-col column  width45"><s:textfield id="expdays" key="strExpiryDays" maxlength="3" name="patCategoryModel.strExpiryDays" cssStyle="width:197px" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.benefit"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strBenefit" name="patCategoryModel.strBenefit" maxlength="200" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.criteria"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strCriteria" name="patCategoryModel.strCriteria" maxlength="100" size="30" > </s:textfield>
				</div>
			</div>
			<s:if test="flagAddMod=='MODIFY'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.remarks"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strRemarks" name="patCategoryModel.strRemarks" maxlength="50" size="30" cssStyle="width:197px"> </s:textfield>
				</div>
			</div>
			</s:if>
						
</div>

<div class="div-table-button">
<div class="div-table-row">
		<div class="div-table-col footerBar"></div>
		<div class="div-table-col emptyBar"></div>
</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
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
	<s:hidden key="strPatCategoryCode" name="patCategoryModel.strPatCategoryCode" value="%{patCategoryModel.strPatCategoryCode}"></s:hidden>
	<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{patCategoryModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message" /></div>

<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<%-- <h4><font color="#FF0000"><s:property value="%{patCategoryModel.StrWarning}"/></font></h4>--%>
<%-- <h4><font color="#FF0000"><s:property value="%{patCategoryModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript" >
$('[name="patCategoryModel.strPatCategoryType"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="patCategoryModel.strPatCategoryName"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});
$('[name="patCategoryModel.strPatCategoryShort"]').validatebox({required: true,	validType: 'alpha'});

$('[name="patCategoryModel.strEconomicStatus"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="patCategoryModel.strCatPriority"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="patCategoryModel.strIsPaid"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});

/* $('[name="patCategoryModel.strIsIdReq"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
}); */

$('[name="patCategoryModel.strIsExpiry"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});

$('#submitId').click(function(e){
	$("#GlobalPatCategory").attr('action',"/HISRegistration/registration/saveGlobalPatCategory.action");
	if($('#GlobalPatCategory').form('validate'))
		{
		sortandEncodebase64($("#GlobalPatCategory"));
		$('#GlobalPatCategory').submit();
		
		}
			});
$('#cancelId').click(function(e){	
	$("#GlobalPatCategory").attr('action',"/HISRegistration/registration/cancelGlobalPatCategory.action");	
		$('#GlobalPatCategory').submit();			
});
$('#clearId').click(function(e){	
	
	$('[name="patCategoryModel.strPatCategoryType"]').val('-1');	
	$('[name="patCategoryModel.strPatCategoryName"]').val('');
	$('[name="patCategoryModel.strPatCategoryShort"]').val('');
	$('[name="patCategoryModel.strPatCategoryGroup"]').val('0');
	$('[name="patCategoryModel.strEconomicStatus"]').val('-1');
	$('[name="patCategoryModel.strCatPriority"]').val('-1');
	$('[name="patCategoryModel.strIsPaid"]').val('-1');
	$('[name="patCategoryModel.strIsIdReq"]').val('-1');
	getIdDetails('-1');
	$('[name="patCategoryModel.strIdSize"]').val('');
	$('[name="patCategoryModel.strIdValidationType"]').val('-1');
	$('[name="patCategoryModel.strIsExpiry"]').val('-1');
	getExpiryDetails('-1');
	$('[name="patCategoryModel.strExpiryMonth"]').val('');
	$('[name="patCategoryModel.strExpiryDays"]').val('');
	$('[name="patCategoryModel.strBenefit"]').val('');
	$('[name="patCategoryModel.strCriteria"]').val('');
});
$('#modifyId').click(function(e){	
	$("#GlobalPatCategory").attr('action',"/HISRegistration/registration/updateGlobalPatCategory.action");
	//alert("1");	
	if($('#GlobalPatCategory').form('validate'))
	{	
		sortandEncodebase64($("#GlobalPatCategory"));
		$('#GlobalPatCategory').submit();			//alert("11");
	}
});
$('#reloadId').click(function(e){
	location.reload("true");
});
</script>
</body>
</html>