<%@page import="vo.registration.UnitConsultantVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 16-Jan-2014 		-->

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
<link href="./../hisglobal/css/layout.css" rel="stylesheet"	type="text/css">
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet"	type="text/css">
<link rel="stylesheet"	href="./../hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css"	type="text/css" /> -->

<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript"	src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" type="text/javascript"	src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript"	src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript"	src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"	src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>

function validateCheck()
{	
	var valid=true;	
	valid=insertCheckTwo();
	if(valid)
		valid=validateHOU();
	if(valid)
		valid=insertCheckOne();
	return valid;
	
}

function insertCheckOne()
{
	var valid=true;		
	//alert(document.getElementsByName("unitConsultantModel.capacity").length);
	for(var i=0; i < document.getElementsByName("unitConsultantModel.hierarchyLevel").length ; i++ )
	{
		if(document.getElementsByName("unitConsultantModel.hierarchyLevel")[i].value=="-1")
		{
			//$("#isUnitSelected").hide();
			//$("#isHeadOfUnitSelected").show();
	 		//alert("Please Select Level !");
			
			document.getElementsByName("unitConsultantModel.hierarchyLevel")[i].focus();
	 		valid=false;
	 		if(!valid)
	 			break;
		}

	}	
	return valid;	
}


	function updateCheckOne() 
	{
		var valid = true;
		for(var i=0; i < document.getElementsByName("unitConsultantModel.strHierarchyLevel").length ; i++ )
		{
			if (document.getElementsByName("unitConsultantModel.strHierarchyLevel")[i].value == "-1") 
			{		
				document.getElementsByName("unitConsultantModel.strHierarchyLevel")[i].focus();
				valid = false;
				break;
			}
		}
		return valid;
	}

	function insertCheckTwo() 
	{
		var valid = true;
		if (document.getElementsByName("unitConsultantModel.hierarchyLevel").length == 0) 
		{
			$("#isConsultantSelected").hide();
			$("#isConsultantAdded").show();			
			document.getElementsByName("unitConsultantModel.strEmpCode")[0].focus();
			valid = false;
		}
		return valid;
	}

	function headOfUnitValue() 
	{
		for ( var i = 0; i < document.getElementsByName("employeeHOU").length; i++) 
		{
			if (document.getElementsByName("employeeHOU")[i].checked)
				document.getElementsByName("unitConsultantModel.employeeHOU")[i].value = "true";
			else
				document.getElementsByName("unitConsultantModel.employeeHOU")[i].value = "false";
		}
	}

	
	function validateHOU()
	{
		var valid = false;
		var len = document.getElementsByName('employeeHOU').length;
		if (len == 0)
		{
			valid = true;
		}
		for ( var i = 0; i < len; i++) 
		{
						
			if (document.getElementsByName('employeeHOU')[i].checked || document.getElementsByName('employeeHOU')[i].disabled) 
			{
				valid = true;
				break;
			}
		}

		if (!valid) 
		{
			var emphou = $('input[name=employeeHOU]:checked').val();
			if(typeof emphou ==='undefined')
			{			    
			    $('input[name=employeeHOU]')[0].focus();
			    $("#isConsultantSelected").hide();
			    $("#isHOUSelected").show();  
			    return false;
			}    
			valid = false;
		}
		return valid;

	}

	function saveUnitConsultantAction() 
	{  		
		headOfUnitValue();
		if (validateCheck()) 
		{			
			document.forms[0].action = "saveUnitConsultantMst.action";
            document.forms[0].submit();            
		}
		
	}

	function updateUnitConsultant() 
	{
		for ( var i = 0; i < document.getElementsByName("strIsHeadOfUnit").length; i++)
		{
			if (document.getElementsByName("strIsHeadOfUnit")[i].checked)
				document.getElementsByName("unitConsultantModel.strIsHeadOfUnit")[i].value = "1";
			else
				document.getElementsByName("unitConsultantModel.strIsHeadOfUnit")[i].value = "";

		}
		if (updateCheckOne()) 
		{
			document.forms[0].action = "updateUnitConsultantMst.action";
			document.forms[0].submit();
		}
	}

	function cancelUnitConsultant() 
	{
		document.forms[0].action = "cancelUnitConsultantMst.action";
		document.forms[0].submit();
	}

	function getConsultantDetails() 
	{
		retValue = true;
		if (document.getElementsByName("unitConsultantModel.strEmpCode")[0].value == "-1")
		{
			$("#isConsultantAdded").hide();
			$("#isHOUSelected").hide();
			$("#isConsultantSelected").show();
			document.getElementsByName("unitConsultantModel.strEmpCode")[0].focus();
			retValue = false;
		}
		var empNo = document.getElementsByName("unitConsultantModel.strEmpCode")[0];
		var label = "";
		for ( var i = 0; i < empNo.length; i++) 
		{
			if (empNo[i].selected) 
			{
				label = empNo[i].text;
			}
		}
		document.getElementsByName("unitConsultantModel.strEmpName")[0].value = label;

		if (retValue)
		{
			document.forms[0].action = "putUnitConsultantMst.action";
			document.forms[0].submit();
		}

	}

	
	function showInit()
	{	
		for(var i=0; i < document.getElementsByName("unitConsultantModel.hierarchyLevel").length ; i++ )
		{
			if(document.getElementsByName("unitConsultantModel.hierarchyLevel")[i].value == -1)
			{
				$('[name="unitConsultantModel.hierarchyLevel"]').validatebox({
					required : true,
					validType : ['selectCombo[-1]']
				});
			}
			
		}
		for(var i=0; i < document.getElementsByName("unitConsultantModel.strHierarchyLevel").length ; i++ )
		{
			if(document.getElementsByName("flagAddMod")[i].value=="MODIFY")
			{
				$('[name="unitConsultantModel.strHierarchyLevel"]').validatebox({
					required: true,
					validType: ['selectCombo[-1]']
				});
			}		
		}	
	}
	function removeConsultants(index) 
	{
		var elmt = document.getElementsByName('unitConsultantModel.removeConsultant')[0];
		elmt.value = index;
		document.forms[0].action = "removeUnitConsultantMst.action";
		document.forms[0].submit();
	}
</script>
</head>
<body onload="showInit();">
<s:form action="UnitConsultantMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.unit"/>&nbsp;<s:text name="global.consultant"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label  width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/>&nbsp;:</div>
				<div class="div-table-col control  width25"><s:property value="%{unitConsultantModel.strDeptName}"></s:property>
				<s:hidden key="strDeptName" name="unitConsultantModel.strDeptName" value="%{unitConsultantModel.strDeptName}"></s:hidden>
				<s:hidden key="strDeptCode" name="unitConsultantModel.strDeptCode" value="%{unitConsultantModel.strDeptCode}"></s:hidden>
				</div>
				<div class="div-table-col label  width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unit"/>&nbsp;:</div>
				<div class="div-table-col control  width25"><s:property value="%{unitConsultantModel.strUnitName}"></s:property>
				<s:hidden key="strUnitName" name="unitConsultantModel.strUnitName" value="%{unitConsultantModel.strUnitName}"></s:hidden>
				<s:hidden key="strUnitCode" name="unitConsultantModel.strUnitCode" value="%{unitConsultantModel.strUnitCode}"></s:hidden>
				</div>
			</div>	
</div>
<%-- <s:if test="%{#session.existingRooms!=null && #session.existingRooms.size>0}"> --%>
<s:if test="flagAddMod=='ADD'">
<s:if test="%{#session.existing!=null}">
<div class="div-table" style="">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.existing"/>&nbsp;<s:text name="global.consultant"/>
				</div>
			</div>
			<div class="div-table-listing rounded">					
			<div class="div-table-row listHeader ">
				<div class="div-table-col width50" align="center"><s:text name="global.employee"/>&nbsp;<s:text name="global.name"/>
				</div>
				<div class="div-table-col width50" align="center"><s:text name="global.is"/>&nbsp;<s:text name="unit.head"/>
				</div>				 
			</div>	
			<s:iterator status="status" value="unitConsultantModel">
			<s:iterator status="i" value="employeeIdExisting">
			<div class="div-table-row listData ">
				<div class="div-table-col width50" align="center"><s:property value="%{unitConsultantModel.employeeNameExisting[#i.index]}"></s:property>
				</div>
				<div class="div-table-col column  width50" align="center">
				<s:if test='%{employeeHOU[#i.index] == "1"}'><s:set name="headAvailable" value="1"></s:set>
				Yes</s:if><s:else>---</s:else>
				</div>				 
			</div>	
			</s:iterator>
			</s:iterator>
			</div>
</div>
</s:if>

<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:if test="flagAddMod=='ADD'"><s:text name="global.add"/>&nbsp;<s:text name="global.consultant"/></s:if>
						<s:else >>><s:text name="global.modify"/>&nbsp;<s:text name="global.consultant"/></s:else >
				</div>
			</div>			
			<div class="div-table-row ">
				<div class="div-table-col label width25">Select&nbsp;<s:text name="global.consultant"/><s:select key="strEmpCode" value="%{unitConsultantModel.strEmpCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.consultantList}" listKey="value" listValue="label" name="unitConsultantModel.strEmpCode"> </s:select>
				<s:hidden key="strEmpName" name="unitConsultantModel.strEmpName" value="%{unitConsultantModel.strEmpName}"></s:hidden>
				</div>
				<div class="div-table-col width25"><img src="../hisglobal/images/plus.png" onclick="getConsultantDetails();">
				 </div>
				 <div class="div-table-col width25">
				 </div>
				 <div class="div-table-col width25">
				 </div>
			</div>	
</div>

<s:if test="%{#session.newConsultants!=null && #session.newConsultants.size>0}">
<div id="unitConsultantModel.consultantdetails" class="div-table-listing rounded" style="">	
			<div class="div-table-row listHeader ">
				<div class="div-table-col width25"><s:text name="unitConsultant.empName"/>
				</div>
				<div class="div-table-col width25"><s:text name="global.is"/>&nbsp;<s:text name="unit.head"/>
				 </div>
				 <div class="div-table-col width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.level"/>
				 </div>
				 <div class="div-table-col width25"><s:text name="global.remove"/>
				 </div>
			</div>	
			
			<s:iterator status="status" value="%{#session.newConsultants}">
			<div class="div-table-row listData ">
				<div class="div-table-col width25"><s:property value="%{strEmpName}"></s:property>		
				</div>	
				<s:if test='%{#headAvailable=="1"}'>							
				<div class="div-table-col width25"><s:radio key="employeeHOU[#status.index]" name="employeeHOU" list="#{'1':''}" value="" disabled="true"></s:radio>
				<s:hidden name="unitConsultantModel.employeeHOU"></s:hidden>
				</div>
				</s:if>	
				<s:else>
				<div class="div-table-col width25"><s:radio key="employeeHOU[#status.index]" name="employeeHOU" list="#{'1':''}" value="%{employeeHOU[#status.index]}"></s:radio>
				<s:hidden name="unitConsultantModel.employeeHOU"></s:hidden>
				</div>
				</s:else>
				<div class="div-table-col width25"><s:select key="hierarchyLevel[#status.index]" name="unitConsultantModel.hierarchyLevel" value="%{unitConsultantModel.hierarchyLevel[#status.index]}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'Level 1 (Highest)','2':'Level 2','3':'Level 3','4':'Level 4','5':'Level 5','6':'Level 6','7':'Level 7','8':'Level 8','9':'Level 9 (Lowest)'}" ></s:select>
				</div>
				<div class="div-table-col width25"><img src="../hisglobal/images/Minus.png" onclick="removeConsultants('<s:property value="%{#status.index}" />')"/>
				</div>
			</div>	
			</s:iterator>			
</div>
</s:if>
</s:if>

<s:set name="unitConsultantModels" value="%{#session.existingConsultants}"></s:set>
<s:if test="flagAddMod=='MODIFY'">
<div class="div-table" style="">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.existing"/>&nbsp;<s:text name="global.consultant"/>
				</div>
			</div>
<div id="unitConsultantModel.consultantdetails" class="div-table-listing rounded" style="">	
			<div class="div-table-row listHeader">
				<div class="div-table-col width25"><s:text name="unitConsultant.empId"/>
				</div>
				<div class="div-table-col width25"><s:text name="global.is"/>&nbsp;<s:text name="unit.head"/>
				</div>
				<div class="div-table-col width25"><font color="#FF0000">*</font>&nbsp;<s:text name="global.level"/>
				</div>
			</div>

<s:iterator value="%{#session.existingConsultants}" status="i">
			<div class="div-table-row listData">
			<div class="div-table-col width25"><s:property value="%{strEmpName}"></s:property>
			<s:hidden name="unitConsultantModel.strEmpName" value="%{strEmpName}"></s:hidden>
			<s:hidden name="unitConsultantModel.strEmpCode" value="%{strEmpCode}"></s:hidden>		
			</div>
			<div class="div-table-col width25"><s:radio key="strIsHeadOfUnit" list="#{'1':''}" value="%{strIsHeadOfUnit}"></s:radio>
			<s:hidden name="unitConsultantModel.strIsHeadOfUnit"></s:hidden>
			</div>
			<div class="div-table-col width25"><s:select key="strHierarchyLevel" name="unitConsultantModel.strHierarchyLevel" value="%{strHierarchyLevel}" headerKey="-1" headerValue="Select Value"  
 				 				 list=" #{'1':'Level 1 (Highest)','2':'Level 2','3':'Level 3','4':'Level 4','5':'Level 5','6':'Level 6','7':'Level 7','8':'Level 8','9':'Level 9 (Lowest)'}" ></s:select>
			</div>		
			</div>
</s:iterator>
</div>
</div>
</s:if>


<div class="div-table-button">
<div class="div-table-row">
		<div class="div-table-col footerBar"></div>
		<div class="div-table-col emptyBar"></div>
</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" onclick="saveUnitConsultantAction();"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" onclick="submitCancelAction('<%=request.getSession().getAttribute("cnt_page") %>');"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" Id="ClearId"><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" onclick="updateUnitConsultant();"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" onclick="cancelUnitConsultant();"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" Id="UpdateClearId"><span class="clear"><s:text name="clear"/></span></a>
			
			
			</s:else>
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strDeptUnitCode" name="unitConsultantModel.strDeptUnitCode" value="%{unitConsultantModel.strDeptUnitCode}"></s:hidden>
	<s:hidden key="removeConsultant" name="unitConsultantModel.removeConsultant" value="%{unitConsultantModel.removeConsultant}"></s:hidden>
	<cmbPers:cmbPers/>
<div id="isConsultantAdded" style="display:none">
<h3>
	<font color="red">Please Add Consultants!</font>
</h3>
</div>
<div id="isConsultantSelected" style="display:none"> 
<h3>
	<font color="red">Please Select Consultants</font>
</h3>
</div>
<div id="isHOUSelected" style="display:none">
<h3>
	<font color="red">Please Select Head Of Unit.</font>
</h3>
</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<h4><font color="#FF0000"><s:property value="%{unitConsultantModel.StrWarning}"/></font></h4>
<h4><s:property value="message"/></h4>

<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<script type="text/javascript" >
$('#ClearId').click(function(e){
	$('[name="unitConsultantModel.hierarchyLevel"]').val('-1');
	$('[name="unitConsultantModel.strEmpCode"]').val('-1');
	$("#isConsultantAdded").hide();
	$("#isConsultantSelected").hide();
	  });
	  
$('#UpdateClearId').click(function(e){
	location.reload(true);
});
</script>
</body>