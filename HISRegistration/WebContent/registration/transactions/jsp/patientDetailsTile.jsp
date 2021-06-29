<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	$("#divPatDtlTileHeaderId").click(function(){
		$("#divPatDtlTileContentId").slideToggle();
	});
});

//added by manisha gangwar date:28.3.2018
function show(e)
{
	var processID = document.getElementsByName("processID")[0].value;
	var crNo = document.getElementsByName("crNo")[0].value;
	var filname = document.getElementsByName("filname")[0].value;
	//var url = "../../registration/transactions/EnlargedImage.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname; 
	//alert(url);
	var url = "../../registration/transactions/EnlargedImage.action"; 
	
	openURLInPopup(url,600,400,0,0);
}
</script>
<body>
			
		<div class="div-table" id="divPatDtlTileHeaderId" style="cursor: pointer;">
			<div class="div-table-row title">
				<div class="div-table-col  width50 "><s:text name="global.patient"/>&nbsp;<s:text name="global.details"/>
				</div>
				<div class="div-table-col  label width50 ">
				<s:text name="registration"/>&nbsp;<s:text name="date"/>
				<b>
				<s:property value="registerDate" /></b>
				</div>
			</div>
		</div>
		<div class="div-table" >
			<div class="div-table-row ">
				<div class="div-table-col width100" id="divPatDtlTileContentId">
					<div class="div-table">
						<div class="div-table-row ">
						<div class="div-table-col" style="width: 95%">
					<div class="div-table">
					<div class="div-table-row ">
							<div class="div-table-col  label " style="width: 25%"><s:text name="global.name"/>
							</div>
							<div class="div-table-col  control width25 ">
								<s:property value="patFirstName" />
								<s:property value="patMiddleName" />
								<s:property value="patLastName " />	
								
							<%-- 	<s:if test="%{(patFirstNameInMultiLang!=null && patFirstNameInMultiLang!='')
											|| (patMiddleNameInMultiLang!=null && patMiddleNameInMultiLang!='')
											|| (patLastNameInMultiLang!=null && patLastNameInMultiLang!='') }">
									/<br>
									<s:property value="patFirstNameInMultiLang" />
									<s:property value="patMiddleNameInMultiLang" />
									<s:property value="patLastNameInMultiLang" />
								</s:if>
 --%>							</div>
							
							<div class="div-table-col  label width25 "><s:text name="crNO"/>
							</div>
							<div class="div-table-col  control width25 ">
							<s:property value="patCrNo" />
							
							</div>
						</div>
						
						<div class="div-table-row ">
							<div class="div-table-col  label width25 "><s:text name="age"/><s:text name="slash"/><s:text name="gender"/>
							</div>
							<div class="div-table-col  control width25 ">
							<s:property value="patAge" />
							<s:property value="patAgeUnit" />
							<s:property value="patGender" />	
							<s:hidden name="patAge" id="patAgeId" value="%{patAge}"></s:hidden>
							<s:hidden name="patAgeUnit" id="patAgeUnitId" value="%{patAgeUnit}"></s:hidden>
							<s:hidden name="patGenderCode" id="patGenderCodeId" value="%{patGenderCode}"></s:hidden>
							<s:hidden name="patGender" id="patGenderId" value="%{patGender}"></s:hidden>
							<%-- <s:hidden name="patPrimaryCatGrpCode" id="patPrimaryCatGrpCodeId" value="%{patPrimaryCatGrpCode}"></s:hidden> --%>
							
							</div>
							
							<div class="div-table-col  label width25 "><s:text name="global.category"/>
							</div>
							<div class="div-table-col  control width25 ">
							<s:if test="isCatExpired==1">
							<font color="#FF0000"><s:property value="patPrimaryCat" />&nbsp;(Validity Over)</font>
							</s:if><s:else><s:property value="patPrimaryCat" /></s:else>
							</div>
						</div>
						
						<div class="div-table-row ">
							<div class="div-table-col  label width25 "><s:text name="fatherName"/>
							</div>
							<div class="div-table-col  control width25 ">
							<s:property value="patGuardianName" />
							</div>
							
							<div class="div-table-col  label width25 "><s:text name="husbandName"/>
							</div>
							<div class="div-table-col  control width25 ">
							<s:property value="patHusbandName" />
							</div>
						</div>
						
						<s:if test="%{(mlcDetail!=null && mlcDetail!='') || patIsDead==1}">
						<div class="div-table-row ">
							<s:if test="mlcDetail!=null && mlcDetail!=''">
								<div class="div-table-col width25 label"><s:text name="mlc"/>&nbsp;<s:text name="number"/></div>	
								<div class="div-table-col width25 control colorRed"><s:property value="mlcDetail"/></div>		
							</s:if>
							<s:else>
								<div class="div-table-col width50 label">&nbsp;</div>	
							</s:else>
							<s:if test="patIsDead==1">
								<div class="div-table-col width50 control" align="right"><font color="#FF0000"><s:text name="global.patient"/>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="dead"/></font></div>	
							</s:if>
						</div>
						</s:if>
					</div>
							</div>
							<div class="div-table-col" style="width: 5%">
							<s:if test="%{isImageUploaded == 1}">
							<img class="button" style="cursor:pointer" src='../../hisglobal/images/image-icon.PNG' alt="View Photo" title="View Photo" 
								onclick="show(event);"/>
								</s:if>
							</div>
							</div>
							</div>
						</div>
						
					</div>
				</div>
		<input type="hidden" name="processID" value="11"/>
		<input type="hidden" name="filname"  value='<s:property value="imageFileName"/>'/>
		<input type="hidden" name="crNo" value='<s:property value="patCrNo"/>'/>
		
</body>
</html>		

	
			