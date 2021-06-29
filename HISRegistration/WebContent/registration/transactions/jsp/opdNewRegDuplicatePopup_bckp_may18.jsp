<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="registration.config.RegistrationConfig"%>
<%@page import="vo.registration.PatientVO"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css">

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/basic.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>

<script type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>

 <script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<%--<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script> --%>

</head>
<body>

<div id="divDuplicateCRNOs">
			<div class="div-table-listing rounded width100 height100">
				<div class="div-table-row listHeader">
					<div class="div-table-col width10" align="center">
					<s:text name="crNO" />
					</div>
					<div class="div-table-col width20" align="center">
					<s:text name="global.patient" />&nbsp;<s:text name="global.name" />	
					</div>
					<div class="div-table-col width10" align="center">
					<s:text name="gender" /><s:text name="slash" /><s:text name="age" />						
					</div>
					<div class="div-table-col width20" align="center">
					<s:text name="fathersName" />					
					</div>
					<div class="div-table-col width20" align="center">
					<s:text name="address" />					
					</div>
					<div class="div-table-col width20" align="center">
					<s:text name="dateOfReg" />						
					</div>
				</div>				
			
				<s:iterator status="status" value="%{#session.allPatientVOList}">
				<div class="div-table-row listData">
					<div class="div-table-col width10" align="center">
						<s:property value="%{patCrNo}"/>
					</div>
					<div class="div-table-col width20" align="center">
						<s:property value="%{patFirstName}"/>&nbsp;<s:property value="%{patMiddleName}"/>&nbsp;<s:property value="%{patLastName}"/>
					</div>
					<div class="div-table-col width10" align="center">
						<s:property value="%{patGender}"/><s:text name="slash" /><s:property value="%{patAge}"/>
					</div>
					<div class="div-table-col width20" align="center">
					<s:if test="%{#patGuardianName!=''}">
						<s:property value="%{patGuardianName}"/>
					</s:if>
					<s:else>NA</s:else>
					</div>
					<div class="div-table-col width20" align="center">
						<s:property value="%{patAddressLine1}"/>&nbsp;<s:property value="%{patAddCity}"/>&nbsp;<s:property value="%{patAddDistrict}"/>&nbsp;<s:property value="%{patAddState}"/>
					</div>
					<div class="div-table-col width20" align="center">
						<s:property value="%{registerDate}"/>
					</div>
				</div>
				</s:iterator>
				<%-- <%String patPrimaryCatCode=(String)request.getParameter("patPrimaryCatCode");//session.getAttribute("patPrimaryCatCode");
	  			  	%>
				<input type ="hidden" name="newSplRegFB" value="<%=patPrimaryCatCode %>" >  --%>
			</div>
			
		</div>

<%-- <script type="text/javascript" src="./../../registration/transactions/js/opdNewPatientRegistration.js" /></script> --%>
		
</body>		
</html>