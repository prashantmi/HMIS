<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 08-Jan-2014 		-->

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

<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>

<!-- Added by Vasu on 14.May.18 for multiSelect Combo of Applicable Services -->
<link rel="stylesheet" href="/HIS/hisglobal/js/jquery/multiselect/css/bootstrap.min.css"type="text/css" />
<link rel="stylesheet" href="/HIS/hisglobal/js/jquery/multiselect/css/bootstrap-multiselect.css"type="text/css" />
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/multiselect/js/bootstrap.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/multiselect/js/bootstrap-multiselect.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<!--End Vasu -->
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
function onLoadPatCatType()
{
	if($('[name="patCategoryModel.strPatCategoryType"]').val() == 0 || $('[name="patCategoryModel.strPatCategoryType"]').val() == 2)
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


function onLoadPatCatGrp()
{
	if($('[name="patCategoryModel.strPatCategoryGroup"]').val() == 4)
	{
		$("#divIdCatGrp").show();
		$('[name="patCategoryModel.strPatClient"]').validatebox({required: false, validType: ['selectCombo[""]']});
	}
	else
	{
		$("#divIdCatGrp").hide();
	}
}
function checkForApplicableServices()
{
	var trfls = $('[name="flagAddMod"]').val();
	if(trfls == 'MODIFY'){
		//alert($('[name="patCategoryModel.strApplicableServicesCode2"]').val());
		var mltslctval  = $('[name="patCategoryModel.strApplicableServicesCode2"]').val();
		var mltslctval_LEN  = mltslctval.length;
		var mltslctval_sbstr = mltslctval.substring(1, mltslctval_LEN-1);
		//alert(mltslctval_sbstr);
		var mltslctval_ARR = mltslctval_sbstr.split('#');
		//alert(mltslctval_ARR);
		var valArr = mltslctval_ARR;
		i = 0, size = valArr.length;
		/* for(i; i < size; i++){
		  $("#lstServices").multiselect("widget").find(":checkbox[value='"+valArr[i]+"']").attr("checked","checked");
		  $("#lstServices option[value='" + valArr[i] + "']").attr("selected", 1);
		  $("#lstServices").multiselect("refresh");
		} */
		$("#lstServices").val(valArr);
		$("#lstServices").multiselect('refresh');
		
	}
}
function showInit()
{
	onLoadPatCatType();
	onLoadPatCatGrp();
	getIdDetails(document.getElementsByName("patCategoryModel.strIsIdReq")[0]);
	getExpiryDetails(document.getElementsByName("patCategoryModel.strIsExpiry")[0]);
	checkForApplicableServices();
}

function getPatCategoryName()
{
	var patCatCode = document.getElementsByName("patCategoryModel.strPatCategoryCode")[0];
	document.getElementsByName("patCategoryModel.strPatCategoryName")[0].value=patCatCode.options[patCatCode.selectedIndex].innerHTML;
	document.forms[0].action="putLocalPatCategory.action";
	document.forms[0].submit();
}

//Changes done for Client combo by Surabhi on 26th july 2016
function getPatClientName()
{
	var patCatClient = document.getElementsByName("patCategoryModel.strPatClient")[0];
	document.getElementsByName("patCategoryModel.strGlobalPatClient")[0].value=patCatClient.options[patCatClient.selectedIndex].innerHTML;
	document.forms[0].action="putLocalPatCategory.action";
	document.forms[0].submit();
}

//Added by Vasu on 14.May.2018 for Multiselect Combo of Applicable Services
$(function () {
        $('#lstServices').multiselect({
            includeSelectAllOption: true
        });
    });    

function setIsDefault(obj)
{
	//var dValue=document.getElementsByName(isDefault).value;
		document.getElementsByName("isDefault")[0].value="0";//dValue;	
	}
//End

</script>
</head>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<link href="./../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<body onload="showInit();">
<s:form action="LocalPatCategory">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Local"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.detail"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<s:if test="flagAddMod=='ADD'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.category"/></div>
				<div class="div-table-col column  width45"><s:select key="strPatCategoryCode" value="%{patCategoryModel.strPatCategoryCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.globalPatCategoryList}" listKey="value" listValue="label" name="patCategoryModel.strPatCategoryCode" onchange="getPatCategoryName();" cssStyle="width:198px"> </s:select>
				</div>
			</div>
			</s:if>
			<s:else>
			<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.global"/>&nbsp;<s:text name="global.category"/></div>
				<div class="div-table-col column  width45"><s:property value="patCategoryModel.strGlobalPatCategoryName"/>
				<s:hidden key="strGlobalPatCategoryName" name="patCategoryModel.strGlobalPatCategoryName" value="%{patCategoryModel.strGlobalPatCategoryName}" ></s:hidden>
				</div>
				<s:hidden key="strPatCategoryCode" name="patCategoryModel.strPatCategoryCode" value="%{patCategoryModel.strPatCategoryCode}"></s:hidden>
			</s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width45"><s:select key="strPatCategoryType" value="%{patCategoryModel.strPatCategoryType}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'Primary','1':'Treatment','2':'Other'}" name="patCategoryModel.strPatCategoryType" disabled="true"  cssStyle="width:198px"></s:select>
				 				 <s:hidden key="strPatCategoryType" name="patCategoryModel.strPatCategoryType" value="%{patCategoryModel.strPatCategoryType}"></s:hidden>				 				 
				 </div>
			</div>	
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col column  width45"><s:textfield key="strPatCategoryName" value="%{patCategoryModel.strPatCategoryName}" 
															name="patCategoryModel.strPatCategoryName" maxlength="30" size="30" > </s:textfield>
				<s:hidden key="strOldPatCategoryName" name="patCategoryModel.strOldPatCategoryName" value="%{patCategoryModel.strPatCategoryName}" cssStyle="width:198px"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.short"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col column  width45"><s:textfield key="strPatCategoryShort" value="%{patCategoryModel.strPatCategoryShort}" 
															name="patCategoryModel.strPatCategoryShort" maxlength="3" size="16"   cssStyle="width:196px"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.category"/>&nbsp;<s:text name="global.group"/></div>
				<div class="div-table-col column  width45"><s:select key="strPatCategoryGroup" value="%{patCategoryModel.strPatCategoryGroup}" 
				 				 list=" #{'0':'Payment Category','1':'Staff','2':'Free','3':'Beneficery Credit with Reference','4':'Beneficery Credit without Reference'}" name="patCategoryModel.strPatCategoryGroup" disabled="true"  cssStyle="width:198px"></s:select>
				 				  <s:hidden key="strPatCategoryGroup" name="patCategoryModel.strPatCategoryGroup" value="%{patCategoryModel.strPatCategoryGroup}" ></s:hidden> 
				 </div>
			</div>
			
			<!-- Changes done for Client combo by Surabhi on 26th july 2016 -->
			<div class="div-table-row " id="divIdCatGrp">
				<div class="div-table-col label  width50">&nbsp;<s:text name="Default Organization"/>&nbsp;</div>
				<div class="div-table-col column  width45"><s:select key="strPatClient" value="%{patCategoryModel.strPatClient}" headerKey=" " headerValue="Select Value" 
				 				 list="%{#session.globalClientList}" listKey="value" listValue="label" name="patCategoryModel.strPatClient" onchange="getPatClientName();" cssStyle="width:198px"> </s:select>
				 				  <%-- <s:hidden key="strPatClient" name="patCategoryModel.strPatClient" value="%{patCategoryModel.strPatClient}" ></s:hidden>  --%>
				 </div>
			</div>
			<!-- End -->
			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.economy"/></div>
				<div class="div-table-col column  width45"><s:select key="strEconomicStatus" value="%{patCategoryModel.strEconomicStatus}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'Below Poverty Line','2':'Above Poverty Line','3':'Others'}" name="patCategoryModel.strEconomicStatus" disabled="true"  cssStyle="width:198px"></s:select>
				 				 <s:hidden key="strEconomicStatus" name="patCategoryModel.strEconomicStatus" value="%{patCategoryModel.strEconomicStatus}"></s:hidden>				 				 
				 </div>
			</div>			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.priority"/></div>
				<div class="div-table-col column  width45"><s:select key="strCatPriority" value="%{patCategoryModel.strCatPriority}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'Yes','0':'No'}" name="patCategoryModel.strCatPriority" cssStyle="width:198px" ></s:select>
				 </div>
			</div>	
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.ispaid"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsPaid" value="%{patCategoryModel.strIsPaid}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'No','1':'Yes','2':'Credit Based'}" name="patCategoryModel.strIsPaid"   cssStyle="width:198px"></s:select>
				 </div>
			</div>	
			<div id="divIdIsIdReq" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.idrequire"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsIdReq" value="%{patCategoryModel.strIsIdReq}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'No','1':'Yes','2':'Mapped With External Data'}" name="patCategoryModel.strIsIdReq" onchange="getIdDetails(this);"  cssStyle="width:198px"></s:select>
				 </div>
			</div>	
			<!-- ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi  -->
			<div id="divIdIsApprovalReq" class="div-table-row " >
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.isapprovalrequired"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsApprovalRequired" value="%{patCategoryModel.strIsApprovalRequired}"  
				 				 list=" #{'0':'No','1':'Yes'}" name="patCategoryModel.strIsApprovalRequired"   cssStyle="width:198px"></s:select>
				 </div>
			<!-- End:Sheeldarshi -->
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
															name="patCategoryModel.strIdSize" maxlength="2" size="16" disabled="true" cssStyle="width:197px"> </s:textfield>
									<s:hidden key="strIdSize" name="patCategoryModel.strIdSize" value="%{patCategoryModel.strIdSize}"></s:hidden>
				</div>
			</div>
			<div id="divIdValidationType" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="verificationDoc.validationtype"/></div>
				<div class="div-table-col column  width45"><s:select key="strIdValidationType" value="%{patCategoryModel.strIdValidationType}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'Any Data','1':'Numeric','2':'Alphanumeric','3':'Characters'}" name="patCategoryModel.strIdValidationType" disabled="true" cssStyle="width:197px"></s:select>
				 		<s:hidden key="strIdValidationType" name="patCategoryModel.strIdValidationType" value="%{patCategoryModel.strIdValidationType}"></s:hidden>
				 </div>
			</div>
	
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.isexpiry"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsExpiry" value="%{patCategoryModel.strIsExpiry}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'Not Required','1':'Month Based','2':'Day Based'}" name="patCategoryModel.strIsExpiry" onchange="getExpiryDetails(this);"  cssStyle="width:198px"></s:select>
				 </div>
			</div>	
			<div id="strExpiryMonthId" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.expmnth"/></div>	
				<div class="div-table-col column  width45"><s:textfield id="expmnth" key="strExpiryMonth" name="patCategoryModel.strExpiryMonth" maxlength="6" cssStyle="width:197px" > </s:textfield>
				</div>
			</div>
			<div id="strExpiryDaysId" class="div-table-row " style="display:none">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="patCategory.expdays"/></div>	
				<div class="div-table-col column  width45"><s:textfield id="expdays" key="strExpiryDays" maxlength="3" name="patCategoryModel.strExpiryDays" cssStyle="width:197px" > </s:textfield>
				</div>
			</div>
				<!--By Mukund on 03.11.2017  -->
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="Is Category Special"/></div>
				<div class="div-table-col column  width45"><s:select key="strIsCatSpl" value="%{patCategoryModel.strIsCatSpl}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'No','1':'Yes'}"   name="patCategoryModel.strIsCatSpl" cssStyle="width:198px"></s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="Department(s) Bound"/></div>
				<div class="div-table-col column  width45"><s:select key="strBoundDeptCode" value="%{patCategoryModel.strBoundDeptCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.globalDepartmentList}"  listKey="value" listValue="label" name="patCategoryModel.strBoundDeptCode" cssStyle="width:198px"></s:select>
				</div>
			</div>
		<!--End On 03.11.2017  -->
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
			<!-- Added by Vasu on 14.May.18 -->
			<div class="div-table-row ">
			    <div class="div-table-col label width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Is Default"/></div>
			    <%-- <div class="div-table-col column  width5"><input type="radio" checked="checked" id="isDefaultYes" name="strIsDefault" value="1" onchange="setIsDefault(this);" /><s:text name="Yes"/></div>
			    <div class="div-table-col column  width5"><input type="radio" id="isDefaultNo" name="strIsDefault" value="0" onchange="setIsDefault(this);"/><s:text name="No"/> --%>
			    <div class="div-table-col column  width45"><s:select key="strIsDefault" value="%{patCategoryModel.strIsDefault}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'Yes','0':'No'}" name="patCategoryModel.strIsDefault" cssStyle="width:198px" ></s:select>
			     <%-- <s:radio label="Answer" name="isDefault" list="#{'1':'Yes','0':'No'}" value="%{patCategoryModel.strIsDefault}"  onchange="setIsDefault(this);"/> --%>
				</div>
			
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="Applicable Service"/></div>	
				<div class="div-table-col column  width45"><%-- <s:textfield key="" name="applicableService" maxlength="100" size="30" > </s:textfield> --%>
				 <s:select id="lstServices" name="applicableServicesName" class="4colactive" multiple="true" key="strApplicableServicesCode"  headerKey="-1" 
				 list="%{#session.applicableServicesList}"  listKey="value" listValue="label" cssStyle="width:190px" disabled="false" >
				  <s:hidden key="strApplicableServicesCode" name="patCategoryModel.strApplicableServicesCode" value="%{#session.applicableServicesList}"></s:hidden> 
				 
						 </s:select>
				</div>
			</div>
			<!-- End Vasu -->
			<s:if test="flagAddMod=='MODIFY'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.remarks"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strRemarks" name="patCategoryModel.strRemarks" maxlength="50" size="30" > </s:textfield>
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
    		<a href="#" class="button" id="submitId" ><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId" ><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" id="updateId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="reloadId"><span class="clear"><s:text name="clear"/></span></a>
			</s:else>
	</div>
</div>
</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
<s:hidden name="patCategoryModel.strSeatId" value="%{patCategoryModel.strSeatId}"></s:hidden>
<s:hidden name="patCategoryModel.strApplicableServicesCode2" value="%{patCategoryModel.strApplicableServicesCode}"></s:hidden>
</s:form>
<s:token></s:token>
<s:actionerror/>


<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{patCategoryModel.StrWarning}"/></div>
<div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message" /></div>


<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>



<%-- <h4><font color="#FF0000"><s:property value="%{patCategoryModel.StrWarning}"/></font></h4>--%>
<%-- <h4><font color="#FF0000"><s:property value="%{patCategoryModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript">
$('[name="patCategoryModel.strPatCategoryCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
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
$('[name="patCategoryModel.strIsIdReq"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="patCategoryModel.strIsExpiry"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="patCategoryModel.strIsDefault"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
/* 
$('[name="patCategoryModel.strApplicableServicesCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
 */
$('#submitId').click(function(e){

	/*Added by Vasu on 14.May.2018*/
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
    
	document.getElementsByName("patCategoryModel.strApplicableServicesCode")[0].value=applicableServiceCode;   

   // var isDefault = document.getElementsByName("isDefault")[0].value;
    //alert(isDefault);
   // document.getElementsByName("patCategoryModel.strIsDefault")[0].value=isDefault;
	//alert(document.getElementsByName("isDefault")[0].value);
	//End Vasu
	$("#LocalPatCategory").attr('action',"/HISRegistration/registration/saveLocalPatCategory.action");
	if($('#LocalPatCategory').form('validate'))
		{
		sortandEncodebase64($("#LocalPatCategory"));
		$('#LocalPatCategory').submit();
		}
					
			});
			

$('#updateId').click(function(e){
	/*Added by Vasu on 14.May.2018*/
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
    
	document.getElementsByName("patCategoryModel.strApplicableServicesCode")[0].value=applicableServiceCode;   

    //var isDefault = document.getElementsByName("strIsDefault")[0].value;
    //alert(document.getElementsByName("patCategoryModel.strIsDefault")[0].value);
    //return false;
	//alert(document.getElementsByName("isDefault")[0].value);
	//End Vasu
	
	$("#LocalPatCategory").attr('action',"/HISRegistration/registration/updateLocalPatCategory.action");
	if($('#LocalPatCategory').form('validate'))
		{
		sortandEncodebase64($("#LocalPatCategory"));
		
		$('#LocalPatCategory').submit();
		
		}

	});
			
$('#cancelId').click(function(e){	
	$("#LocalPatCategory").attr('action',"/HISRegistration/registration/cancelLocalPatCategory.action");	
		$('#LocalPatCategory').submit();			
			});
$('#clearId').click(function(e){	
	$('[name="patCategoryModel.strPatCategoryCode"]').val('-1');	
	$('[name="patCategoryModel.strPatCategoryType"]').val('-1');
	$('[name="patCategoryModel.strPatCategoryName"]').val('');
	$('[name="patCategoryModel.strPatCategoryShort"]').val('');
	$('[name="patCategoryModel.strPatCategoryGroup"]').val('0');
	$('[name="patCategoryModel.strEconomicStatus"]').val('-1');
	$('[name="patCategoryModel.strCatPriority"]').val('-1');
	$('[name="patCategoryModel.strIsPaid"]').val('-1');
	$('[name="patCategoryModel.strIsIdReq"]').val('-1');
	getIdDetails('-1');
	$('[name="patCategoryModel.strIsExpiry"]').val('-1');
	$('[name="patCategoryModel.strExpiryMonth"]').val('');
	$('[name="patCategoryModel.strExpiryDays"]').val('');
	getExpiryDetails('-1');
	$('[name="patCategoryModel.strBenefit"]').val('');
	$('[name="patCategoryModel.strCriteria"]').val('');
		});
		
$('#reloadId').click(function(e){
	location.reload(true);
});
</script>
</body>
</html>