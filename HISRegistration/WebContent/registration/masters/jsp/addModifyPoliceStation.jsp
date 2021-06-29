<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 07-May-2014 		-->

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
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->

<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script> 
<script>

</script>
</head>
<body>
<s:form action="PoliceStationMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Police"/>&nbsp;<s:text name="Station"/>&nbsp;<s:text name="global.master"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="PS"/>&nbsp;<s:text name="Full Name"/></div>
				<div class="div-table-col control width45"><s:textfield key="strPSFullName" value="%{policeStationModel.strPSFullName}" 
															name="policeStationModel.strPSFullName" maxlength="100" size="30" > </s:textfield>
				<s:hidden key="strOldPSFullName" name="policeStationModel.strOldPSFullName" value="%{policeStationModel.strPSFullName}"></s:hidden>												
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="PS"/>&nbsp;<s:text name="Short Name"/></div>
				<div class="div-table-col control width45"><s:textfield key="strPSShortName" value="%{policeStationModel.strPSShortName}" 
															name="policeStationModel.strPSShortName" maxlength="50" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Address"/>&nbsp;<s:text name="Line1"/></div>
				<div class="div-table-col control width45"><s:textfield key="strAddressLine1" value="%{policeStationModel.strAddressLine1}" 
															name="policeStationModel.strAddressLine1" maxlength="60" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Sub"/>&nbsp;<s:text name="Locality1"/></div>
				<div class="div-table-col control width45"><s:textfield key="strSubLocality1" value="%{policeStationModel.strSubLocality1}" 
															name="policeStationModel.strSubLocality1" maxlength="60" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Sub"/>&nbsp;<s:text name="Locality2"/></div>
				<div class="div-table-col control width45"><s:textfield key="strSubLocality2" value="%{policeStationModel.strSubLocality2}" 
															name="policeStationModel.strSubLocality2" maxlength="60" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="City"/></div>
				<div class="div-table-col control width45"><s:textfield key="strCity" value="%{policeStationModel.strCity}" 
															name="policeStationModel.strCity" maxlength="60" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="State"/></div>
				<div class="div-table-col control width45"><s:select key="strStateCode" cssStyle="width:200px" name="policeStationModel.strStateCode" value="%{policeStationModel.strStateCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.stateList}" listKey="value" listValue="label" onchange="policeStationMst.onchange_strStateCode();"></s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="district"/></div>
				<div class="div-table-col control width45"><s:select key="strDistrictCode" cssStyle="width:200px" name="policeStationModel.strDistrictCode" value="%{policeStationModel.strDistrictCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.districtList}" listKey="value" listValue="label" ></s:select>
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Pincode"/></div>
				<div class="div-table-col control width45"><s:textfield key="strPinCode" value="%{policeStationModel.strPinCode}" 
															name="policeStationModel.strPinCode" maxlength="6" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="Phone No."/></div>
				<div class="div-table-col control width45"><s:textfield key="strPhoneNo" value="%{policeStationModel.strPhoneNo}" 
															name="policeStationModel.strPhoneNo" maxlength="16" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="Email Id"/></div>
				<div class="div-table-col control width45"><s:textfield key="strEmailId" value="%{policeStationModel.strEmailId}" 
															name="policeStationModel.strEmailId" maxlength="100" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Incharge Name"/></div>
				<div class="div-table-col control width45"><s:textfield key="strPSInchargeName" value="%{policeStationModel.strPSInchargeName}" 
															name="policeStationModel.strPSInchargeName" maxlength="99" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="Incharge Designation"/></div>
				<div class="div-table-col control width45"><s:textfield key="strPSConstableDesignation" value="%{policeStationModel.strPSConstableDesignation}" 
															name="policeStationModel.strPSConstableDesignation" maxlength="50" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="Incharge Badge No."/></div>
				<div class="div-table-col control width45"><s:textfield key="strPSConstableBadgeNo" value="%{policeStationModel.strPSConstableBadgeNo}" 
															name="policeStationModel.strPSConstableBadgeNo" maxlength="50" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="Mobile No."/></div>
				<div class="div-table-col control width45"><s:textfield key="strMobileNo" value="%{policeStationModel.strMobileNo}" 
															name="policeStationModel.strMobileNo" maxlength="16" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Is Default"/></div>
				<div class="div-table-col control width45"><s:select key="strIsDefault" cssStyle="width:200px" name="policeStationModel.strIsDefault" value="%{policeStationModel.strIsDefault}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'Yes','0':'No'}" ></s:select>
				</div>
			</div>
						
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
	<s:hidden key="strPSCode" name="policeStationModel.strPSCode" value="%{policeStationModel.strPSCode}"></s:hidden>
	<s:hidden key="strDistrict" name="policeStationModel.strDistrict" value="%{policeStationModel.strDistrict}"></s:hidden>
	<s:hidden key="strDraftDistrictCode" name="policeStationModel.strDraftDistrictCode" value="%{policeStationModel.strDistrictCode}"></s:hidden>
	<s:hidden key="strDraftStateCode" name="policeStationModel.strDraftStateCode" value="%{policeStationModel.strStateCode}"></s:hidden>
	
	<cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token></s:token>

</s:form>
<s:actionerror/>
<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{policeStationModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message"/></div>


<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<%--<h4><font color="#FF0000"><s:property value="%{policeStationModel.StrWarning}"/></font></h4>--%>
<%-- <h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript">
$('[name="policeStationModel.strPSShortName"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'});
$('[name="policeStationModel.strPSFullName"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'});
$('[name="policeStationModel.strAddressLine1"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'});
$('[name="policeStationModel.strSubLocality1"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'});
$('[name="policeStationModel.strSubLocality2"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'});
$('[name="policeStationModel.strCity"]').validatebox({required: true, validType: 'alphaWithSpace'});
$('[name="policeStationModel.strPinCode"]').validatebox({required: true, validType: 'numeric'});
$('[name="policeStationModel.strPhoneNo"]').validatebox({required: false, validType: 'numeric'});
$('[name="policeStationModel.strMobileNo"]').validatebox({required: false, validType: 'numeric'});
$('[name="policeStationModel.strEmailId"]').validatebox({required: false, validType: 'email'});

$('[name="policeStationModel.strPSInchargeName"]').validatebox({required: true, validType: 'alphaWithSpace'});
$('[name="policeStationModel.strPSConstableDesignation"]').validatebox({required: false, validType: 'alphaWithSpace'});
$('[name="policeStationModel.strPSConstableBadgeNo"]').validatebox({required: false, validType: 'alphaNumericWithSpaces'});

$('[name="policeStationModel.strDistrictCode"]').validatebox({required: true, validType: ['selectCombo[-1]']});
$('[name="policeStationModel.strStateCode"]').validatebox({required: true, validType: ['selectCombo[-1]']});
$('[name="policeStationModel.strIsDefault"]').validatebox({required: true, validType: ['selectCombo[-1]']});

var policeStationMst={
		onchange_strStateCode:function()
		  {
			var objState  =document.getElementsByName("policeStationModel.strStateCode")[0];
			var stateCode = objState.options[objState.selectedIndex].value;
			var action 		= "/HISRegistration/registration/masters/getDistrictPoliceStationMst.action?stateCode="+stateCode;
						
			$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
				{
						var returnDocument=data;
						var rootNode=returnDocument.getElementsByTagName("root")[0];
						for(var i=0;i<rootNode.childNodes.length;i++ )
						{
							var elementNode=rootNode.childNodes[i];
							var elementName=elementNode.nodeName;
							
							policeStationMst.processGeneralNode(elementName,elementNode);
						}
													
				},error: function(errorMsg,textstatus,errorthrown) {
		            alert('onchange_strStateCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		            
		        }});
		  },
		  processGeneralNode:function(elementName,elementNode)
		  {
			var optionText="";
			if(elementName=="policeStationModel.strDistrictCode")
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
		  }
};

$('#submitId').click(function(e){
	var el = $('[name="policeStationModel.strDistrictCode"]')[0];
	var text = el.options[el.selectedIndex].innerHTML;
	$('[name="policeStationModel.strDistrict"]').val(text);
	$("#PoliceStationMst").attr('action',"/HISRegistration/registration/savePoliceStationMst.action");
	if($('#PoliceStationMst').form('validate'))
		{
		sortandEncodebase64($("#PoliceStationMst"));
		$('#PoliceStationMst').submit();
		
		}
});
$('#cancelId').click(function(e){	
	$("#PoliceStationMst").attr('action',"/HISRegistration/registration/cancelPoliceStationMst.action");	
		$('#PoliceStationMst').submit();			
});
$('#modifyId').click(function(e){	
	var el = $('[name="policeStationModel.strDistrictCode"]')[0];
	var text = el.options[el.selectedIndex].innerHTML;
	$('[name="policeStationModel.strDistrict"]').val(text);
	$("#PoliceStationMst").attr('action',"/HISRegistration/registration/updatePoliceStationMst.action");
	if($('#PoliceStationMst').form('validate'))
		{
		sortandEncodebase64($("#PoliceStationMst"));
		$('#PoliceStationMst').submit();
		}
});
$('#clearId').click(function(e){
	$('[name="policeStationModel.strPSShortName"]').val('');
	$('[name="policeStationModel.strPSFullName"]').val('');
	$('[name="policeStationModel.strAddressLine1"]').val('');
	$('[name="policeStationModel.strSubLocality1"]').val('');
	$('[name="policeStationModel.strSubLocality2"]').val('');
	$('[name="policeStationModel.strCity"]').val('');
	if($('[name="policeStationModel.strStateCode"]').val()==$('[name="policeStationModel.strDraftStateCode"]').val())
		$('[name="policeStationModel.strDistrictCode"]').val($('[name="policeStationModel.strDraftDistrictCode"]').val());
	else
	{
		policeStationMst.onchange_strStateCode();
		$('[name="policeStationModel.strDistrictCode"]').val('-1');
	}
		
	$('[name="policeStationModel.strStateCode"]').val($('[name="policeStationModel.strDraftStateCode"]').val());

	$('[name="policeStationModel.strPinCode"]').val('');
	$('[name="policeStationModel.strPhoneNo"]').val('');
	$('[name="policeStationModel.strMobileNo"]').val('');
	$('[name="policeStationModel.strEmailId"]').val('');
	$('[name="policeStationModel.strPSInchargeName"]').val('');
	$('[name="policeStationModel.strPSConstableDesignation"]').val('');
	$('[name="policeStationModel.strPSConstableBadgeNo"]').val('');
	$('[name="policeStationModel.strIsDefault"]').val('1');
	});
$('#reloadId').click(function(e){
	location.reload(true);
});


</script>
</body>
</html>