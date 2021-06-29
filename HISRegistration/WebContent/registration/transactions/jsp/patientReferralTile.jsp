<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
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

<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script type="text/javascript"
	src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script> 
<script type="text/javascript">
$(window).on("load.loading1", function() {
		/* var data=document.getElementsByName("printHtml")[0].value;
		var elem = document.getElementById("divPrintId");
		elem.innerHTML= data; */
		
		//alert(document.getElementsByName("afterGo")[0].value);
/* if(document.getElementsByName("afterGo")[0].value!='0')
{  
	document.getElementById("divAfterGo").style.display = "";
	document.getElementById("divAfterGoId").style.display ="";
	//patReferral.fetchDefaultValues(); */



departmentList=(document.getElementsByName("departmentCode")[0]).innerHTML;
departmentUnitList=(document.getElementsByName("departmentUnitCode")[0]).innerHTML;
getFilteredDeptUnit(document.getElementsByName("selectedEpisode")[0]); 

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
		document.forms[0].action = mode + "PatientReferral.action";
		document.forms[0].submit();

	}
	function submitCancelAction(cnt) {
		document.forms[0].action = "cancel" + cnt + ".action";
		document.forms[0].submit();
	}
	
	
	function populate(selectedarray)
	{
		var strHtml ="";
		var elem = document.getElementById("hiddenDivVerification");
		for(i=0; i<selectedarray.length; i++)
		{
			var arrayOfDocsData=selectedarray[i].split("|");
	  	  
			strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
			strHtml+=arrayOfDocsData[1]+"&nbsp; &nbsp;";
		}
		elem.innerHTML= ":: &nbsp;" + strHtml;
		
	}  
	
	

</script>
<!-- returnHTML=returnHTML.split('@')[1]; -->

<script>
/*
 * Function Created For: Check Gender Bound Depatments
  By				   : Raj Kumar
  On				   : 24/03/2018
 */

$(function(){
	var ageBoundRange = 125;	

	if('select[name="departmentCode"]')
	{
	
	$('select[name="departmentCode"]').on('change', function(){
	if($(this).val().split('#')[6]!= "-1")
		{ 
		if($('#patGenderCodeId').val()!=$(this).val().split('#')[6])
			{
			var Gender="";
			if($('#patGenderCodeId').val()=='M')
				Gender="Male";
			else if($('#patGenderCodeId').val()=='F')
				Gender="Female";
			else 
				Gender="Transgender";
				
			alert(Gender+"s are not allowed in this department"); 
			$('#PatientReferral_departmentCode option:first').prop('selected', true);
			
			
			}
			
		}
			});
	
	 	}
	
	/*  if('select[name="departmentUnitCode"]')
		{

		$('select[name="departmentUnitCode"]').on('change', function(){
		alert($(this).val().split('#')[6]);
		
		});
		}  */
		$('#PatientReferral_departmentCode').on('blur', function() {
			

			ageBoundRange= $("#PatientReferral_departmentCode").val().split('#')[5];
			var pAge= $('#patAgeId').val();
			if($(ageBoundRange).val== undefined )
				ageBoundRange=0;
			console.log("ageBoundRange--"+ageBoundRange+ "pat age"+ pAge);
			if(parseInt(pAge) >= parseInt(ageBoundRange))
				{ 
				alert("Patient Age should not be more than "+ageBoundRange+" Years");
				$('#PatientReferral_departmentCode option:first').prop('selected', true);
				}
				
			/*var maxAgeRange = calculateMaxRangeValue(ageBoundRange, $("#patAgeUnitId").val());
			var ageRangeValidType = 'range[1,' + maxAgeRange + ']';
			$("#PatientReferral_departmentCode").validatebox({
				required : true,
				validType : [ 'numeric', ageRangeValidType ,'startZero']
			
						}); */
 
		});
		
});

</script>
<title>Refer Patient</title>
<script type='text/javascript' > 
	 window.hasHold = false;
</script>	
<s:head />


</head>

<body>
	<center>
		<s:form action="PatientReferral">
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded ">

				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
						<s:text name="refer"/>&nbsp;<s:text name="global.patient"/></div>
						
					</div>
				</div>

	<his:InputCrNoTag />
		
	<s:hidden name="goFlag" value="%{goFlag}" />
	<s:hidden name="AfterGo" value="%{goFlag}" />
	<s:hidden name="isDesk" value="%{isDesk}" />	
	<s:set name="goFlagForJsp" value="goFlag"></s:set>
	<s:hidden name="showAppointmentDateInsidePopup" value="1"/>
	<div id="divAfterGo"> 
	
	<s:if test="%{#goFlagForJsp!=0}">			
	<s:hidden id="printHtmlId" name="printHtml" value="%{errorMessage}"/>
					<%-- <s:action name="patientDetail" executeResult="true"></s:action> --%>
					<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
					<%-- <s:set name="voPatDtl" value="#session[@registration.config.RegistrationConfig@PATIENT_VO]"></s:set>
					<s:if test="%{#voPatDtl== null || #voPatRefer.length==0}">
						<s:set name="goFlagForJsp" value="0"></s:set>
					</s:if> --%>
	</s:if>
	<s:if test="%{#goFlagForJsp!=0}">			
								
				
				<div class="div-table"  id="divEpisodeId">
					<div class="div-table-row title">
						<div class="div-table-col"><s:text name="global.visit"/>&nbsp;<s:text name="detail"/></div>
					</div>
					
					<div class="div-table-row">
						<div class="div-table-col" style="width: 8%;">
								<b><s:text name="select" /></b>
						</div>
						
						<div class="div-table-col" style="width: 17%;">
						<b>	
							<s:text name="department" />
						</b>
						</DIV>
						<div class="div-table-col" style="width: 25%;">
						<b>	
							<s:text name="unit" />
						</b>
						</div>
							
					</div>
				     <s:set name="counter" value="0"/>
				     <s:set name="checkedValue" value=""></s:set>
				
					<s:iterator value="#session.arrEpisodeVOReferral" status="key" >
						<s:if test="%{#counter==0}">	
						 <s:set name="checkedValue" value="checked='checked'"></s:set>
						 <s:set name="counter" value="%{#counter+1}"/>
						</s:if>	
						<s:else>
						 <s:set name="checkedValue" value=""></s:set>
						</s:else>			
					
					
					<div class="div-table-row">
						<div class="div-table-col control" style="width: 8%;">
						
						
						
								 <input type="radio" name="selectedEpisode" tabindex="1" <s:property value="checkedValue" />  title='<s:property value="#key.index"/>' value='<s:property value="episodeCode"/>' onclick='getFilteredDeptUnit(this);'/>
						</div>
						
						<div class="div-table-col control" style="width: 17%;">
							<s:property value="department"/>
						</DIV>
						<div class="div-table-col control" style="width: 25%;">
							<s:property value="departmentUnit"/>
						</div>
						
						<input name="selectedDepartmentUnitType" id="selectedDepartmentUnitTypeId" type="hidden" value='<s:property value="departmentUnitType"/>'>
					    <input name="selectedDepartmentCode" id="selectedDepartmentCodeId" type="hidden" value='<s:property value="departmentCode"/>'>
					    <input name="selectedDepartmentUnitCode" id="selectedDepartmentUnitCodeId" type="hidden" value='<s:property value="departmentUnitCode"/>'>
					
					</div>
	 				</s:iterator>
				
				</div>
				
				<div class="div-table"  id="divReferTypeId">
				<div class="div-table-row title">
						<div class="div-table-col"><s:text name="select" />&nbsp;<s:text name="refer" />&nbsp;<s:text name="global.type" /></div>
				</div>
				<div class="div-table-row">
				         <div class="div-table-col control" style="width: 8%;">
								 <input type="radio" name="isRefferInOut" tabindex="1" value="I" onclick="showInternal(this)" />
								<s:text name="internal" />
						</div>
						<div class="div-table-col control" style="width: 8%;">
								 <input type="radio" name="isRefferInOut" tabindex="1" value="E" onclick="showExternal(this)" />
   								<s:text name="external" />
						</div>
						
				</div>
				</div>
				
				<div class="div-table"  id="divInternalReferId" style="display: none;">
				<div class="div-table-row title">
						<div class="div-table-col" style="width: 10%;"><s:text name="referto" /></div>
						<div class="div-table-col control" style="width: 90%;">
						<input type="radio" name="choice" tabindex="1" value="0" onclick="selectDept(this)" checked="checked" />
								<s:text name="department" />
						<input type="radio" name="choice" tabindex="1" value="1" onclick="selectDept(this)" />
						        <s:text name="specialClinic" />
						
						</div>
						
						
				</div>
				<div class="div-table-row">
				
				<div class="div-table-col" style="width: 50%;" id="divReferDeptId">
				  <div class="div-table" style="width: 100%;">
				        <div class="div-table-row" >
				       
					        <div class="div-table-col" >
							<b>	
								<s:text name="department" />
							</b>
							</DIV>
							<div class="div-table-col control">
																
							<s:set name="optionRefInternalDeptLocalVar" value="#session.optionRefInternalDept"></s:set>
							<s:if test="%{#optionRefInternalDeptLocalVar!=null && #optionRefInternalDeptLocalVar.size>0}">
								<s:select name="departmentCode" cssStyle="width:145px" list="#optionRefInternalDeptLocalVar"  listKey="value" listValue="label" headerKey="-1" headerValue="Select Value"/> 
							</s:if>
							<s:else>
								<select name="departmentCode" >
									<option value="-1">Select Value</option>
								</select>
							</s:else>
							</div>
				        </div>
				  </div>
			   </div>
				       
				 <div class="div-table-col" style="width: 50%; display: none;" id="divReferDeptUnitId">
				 <div class="div-table" style="width: 100%;">
				<div class="div-table-row" >
				
						<div class="div-table-col" >
						<b>	
							<s:text name="specialClinic" />
						</b>
						</DIV>
						<div class="div-table-col control">
							<s:set name="optionRefSplUnitsLocalVar" value="#session.optionRefSplUnits"></s:set>
							<s:if test="%{#optionRefSplUnitsLocalVar!=null && #optionRefSplUnitsLocalVar.size>0}">
								<s:select name="departmentUnitCode" cssStyle="width:145px" list="#optionRefSplUnitsLocalVar"  listKey="value" listValue="label"  headerKey="-1" headerValue="Select Value" /> 
							</s:if>
							<s:else>
								<select name="departmentUnitCode" >
									<option value="-1">Select Value</option>
								</select>
							</s:else>	
													
						</div>
						<div class="div-table-col control" id="aptTagId"><!-- Check yet to Delete -->
						</div>
						<div id="divNoSlot" class="div-table-col control" style="display:none;">
						<font color="red"><s:text name="no" />&nbsp;<s:text name="slot" />&nbsp;<s:text name="available" /></font>
						</div>
				</div>
				</div>
				</div>
				
				<div class="div-table-row">
				       <div class="div-table-col">
						<b>	
							<s:text name="reason" />
						</b>
						</DIV>
						<div class="div-table-col control">
							<textarea name="remarks" rows="1" cols="15"></textarea>
						</div>
				</div>
				
			</div>
			</div>
				
			<div id="divExternalReferId" class="div-table" style="display: none;">
			<div class="div-table-row title">
						<div class="div-table-col" style="width: 10%;"><s:text name="referto" /></div>
						<div id="divReferredInstitute" class="div-table-col label" style="width: 50%; display: none;">
					    <input type="radio" name="referringInstType" value="G" onclick="showdivhoscode(this)" checked="checked" /><s:text name="referInternal" /><s:text name="slash" /><s:text name="associatedInst" /> &nbsp;
			    	    <input type="radio" name="referringInstType" value="O" onclick="showdivhoscode(this)" /><s:text name="other" /> &nbsp;
				        <input name="isAssociated" id="isAssociated" type="hidden">
				       </div>
						
						
				</div>
			<div class="div-table-row" >
				
				<div id="divRefHosCode" class="div-table-col" style="width: 50%;">
					<div class="div-table" style="width: 100%;">
						<div class="div-table-row" >
							<div class="div-table-col label" style="width: 50%;"> <font color="red">*</font>
							<label for="Institute Name"><s:text name="institute" />&nbsp;<s:text name="global.name" /></label>	
							</div>
							<div class="div-table-col control" style="width: 50%;">
							
							
							<s:set name="optionRefHospitalLocalVar" value="#session.optionRefHospital"></s:set>
							<s:if test="%{#optionRefHospitalLocalVar!=null && #optionRefHospitalLocalVar.size>0}">
								<s:select name="patRefGnctdHospitalCode" id="patRefGnctdHospitalCodeId" cssStyle="width:145px" 
									list="#optionRefHospitalLocalVar"  listKey="value" listValue="label" 
									headerKey="-1" headerValue="Select Value" /> 
							</s:if>
							<s:else>
								<select name="patRefGnctdHospitalCode" >
									<option value="-1">Select Value</option>
								</select>
							</s:else>
							
							
								
							</div>
						</div>
					</div>
				</div>
				
				<div id="divRefHosname" class="div-table-col" style="width: 50%; display: none;" >
					<div class="div-table" style="width: 100%;">
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%;"> 
							<font color="red">*</font><label for="Institute Name"><s:text name="institute" />&nbsp;<s:text name="global.name" /></label>	
							</div>
							<div class="div-table-col control" style="width: 50%;">
								<input name="patRefHospitalName" maxlength="50" type="text" size="20">
							</div>
						</div>
					</div>
				</div>
				
				<div class="div-table-row">
				       <div class="div-table-col label">
						<b>	
							<s:text name="reason" />
						</b>
						</DIV>
						<div class="div-table-col control">
							<textarea name="externalReferRemarks" rows="1" cols="15"></textarea>
						</div>
				</div>
				
			  </div>
		  </div>
		  
		  <div class="div-table" id="divReferred" style="display: none;">
						
			<div class="div-table-row">
			
				<div id="divRefDeptforAssociatedInstitutes" class="div-table-col label" style="width: 50%;">
						<div class="div-table" style="width: 100%;">
						<div class="div-table-row" >
							<div class="div-table-col label" style="width: 50%;"> 
							<label for="Institute Name"><s:text name="referringInstitution" />&nbsp;<s:text name="global.department" /></label>	
							</div>
							<div class="div-table-col control" style="width: 50%;">
							<select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDeptUnit" style="width :145px">
								<option value="-1">Select Value</option>
								<option value="0">Other</option>
							</select>
							
															
							</div>
						</div>
					</div>
				</div>
				
				<div id="divRefDeptforOtherInstitutes" class="div-table-col" style="width: 50%; display: none;" >
					<div class="div-table" style="width: 100%;">
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%;"> 
							<label for="Institute Name"><s:text name="referringInstitution" />&nbsp;<s:text name="global.department" /></label>	
							</div>
							<div class="div-table-col control" style="width: 50%;">
								<input name="patRefGnctdHospitalDept" maxlength="50" type="text" size="20">
							</div>
						</div>
					</div>
				</div>
				
				<div id="divRefHospitalDeptOtherId" class="div-table-row" style="display: none;">
				       <div class="div-table-col">
						<s:text name="other"/>&nbsp;<s:text name="referred"/>&nbsp;<s:text name="global.department"/>
						</div>
						<div class="div-table-col control">
							<input name="patRefHospitalDeptOther" maxlength="20" type="text" size="20">
						</div>
				</div>
				
				
				
			</div>
			
		</div>
				
		</s:if>	
				
 		
		<div id="aptTag">
		</div>
			
<%-- 		<appt:AppointmentTag paraId2="0" paraId1="10114" tagId="2" aptId="1" crno="1011500000851" tagView="2"></appt:AppointmentTag> --%>

		 
<%-- </s:if> --%><!-- For transaction in process -->
				
				
				<%-- <div class="div-table-button">
				<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center">
				  <s:if test="afterGo!=0">
					
					
						<div class="div-table-col">
							<a href="#" class="button" id="submitId"><span
								class="save">Save</span></a>
						</div>
						<div class="div-table-col">
							<a href="#" class="button" id="clearId"><span
								class="clear">Clear</span></a>
						</div>
						
					
					</s:if>
						
						<div class="div-table-col">
							<a href="#" class="button" onclick="submitCancelAction('PatientReferral');"><span
								class="cancel">Cancel</span></a>
						</div>
				</div>
				</div> --%>
				
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
							<a href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
						</s:if>
						<s:else>
						<a href="#" class="button" id="initialClearId" ><span class="clear"><s:text name="clear"/></span></a>
						</s:else>
						
						<s:if test="%{isDesk!=1}">
						<a href="#" class="button" id="cancelId" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
						</s:if>
						<s:else>
						<a href="#" class="button" id="cancelId" onclick="closeDeskTab();"><span class="cancel"><s:text name="cancel"/></span></a>
						</s:else>
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
			<div class="div-table-col alignLeft fontNormalMessage">
			<s:property value="normalMessage" />
			</div>
	</div>
	</div>

<script type="text/javascript" src="./../../appointment/transactions/js/appointment.js" /></script>	
<script type="text/javascript" src="./../../registration/transactions/js/patientReferral.js" /></script>
	
</center>
</body>
</html>