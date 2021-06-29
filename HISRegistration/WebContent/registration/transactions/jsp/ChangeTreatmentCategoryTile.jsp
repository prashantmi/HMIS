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



<script language="JavaScript" type="text/javascript"
	src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>

<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript" src="./../../hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script type="text/javascript"	src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/validationCommon.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>


<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.jquery.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.css">


<script type="text/javascript">
$(window).on("load.loading1", function() {
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
		/* if(mode=="GETDTLFORCATCHANGE")
		{
		var val = document.getElementsByName('selectedEpisodeIndex')[0].value;
		alert(val);
		} */
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
</script>
<title>Change Treatment Category</title>
<s:head />

</head>

<body>
	<center>
		<s:form action="ChangeTreatmentCategory">
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded ">

				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
						<s:text name="Change Treatment Category"/>&nbsp;</div>
						
					</div>
				</div>

	<his:InputCrNoTag />
		
	<s:hidden name="goFlag" value="%{goFlag}" />
	<s:hidden name="AfterGo" value="%{goFlag}" />
	<s:set name="goFlagForJsp" value="goFlag"></s:set>
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
				        <s:text name = "Episode Details"/> 
						 <%-- <div class="div-table-col control" style="width: 20%;">
						<s:text name="IPD" />
						<s:if test="%{#session.ipdFlag == 1}">
						<s:radio  id="isIPDFlagId" name="isIpdFlag"  value ="1" onclick="submitForm('GETEPISODE')" list="#{'1':''}" ></s:radio>
						<s:text name="OPD" />
						<s:radio id="isOPDFlagId" name="isIpdFlag" value ="0" onclick="submitForm('GETEPISODE')" list="#{'':''}"></s:radio>
						</s:if>
						<s:else>
						<s:radio id="isIPDFlagId1" name="isIpdFlag"  value ="1" onclick="submitForm('GETEPISODE')" list="#{'1':''}" ></s:radio>
						<s:text name="OPD" />
						<s:radio id="isOPDFlagId1"  name="isIpdFlag" value ="0"  onclick="submitForm('GETEPISODE')" list="#{'0':''}"></s:radio>
						</s:else>
					
						</div>  --%>
						
				</div>
						<div class="div-table-row">
						<div class="div-table-col" style="width: 10%;">
								<b><s:text name="select" /></b>
						</div>
						<div class="div-table-col" style="width: 15%;">
								<b><s:text name="Visit Type" /></b>
						</div>
						<div class="div-table-col" style="width: 15%;">
						<b>	
							<s:text name="department" />
						</b>
						</div>
						<div class="div-table-col" style="width: 15%;">
						<b>	
							<s:text name="unit" />
						</b>
						</div>
						<div class="div-table-col" style="width: 15%;">
						<b>	
							<s:text name="Admission Number" />
						</b>
						</div>
						<div class="div-table-col" style="width: 15%;">
						<b>	
							<s:text name="Ward" />
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
						  												
					<div class="div-table-row">
						<div class="div-table-col control" style="width: 10%;">
							  <input type="radio" name="selectedEpisodeIndex" tabindex="1" <s:property value="checkedValue" />  title='<s:property value="#key.index"/>' value='<s:property value="#key.index"/>' onclick='submitForm("GETDTLFORCATCHANGE");'/> 
						</div>
						<div class="div-table-col control" style="width: 15%;">
							 <s:text name = "OPD"/> 
						</div>
						<div class="div-table-col control" style="width: 15%;">
							<s:property value="department"/>
						</div>
						<div class="div-table-col control" style="width: 15%;">
							<s:property value="departmentUnit"/>
						</div>
						<div class="div-table-col control" style="width: 15%;">
					       <s:text name = "-"/> 
						</div>
						<div class="div-table-col control" style="width: 15%;">
						   <s:text name = "-"/> 
						</div>				
						<input name="selectedDepartmentUnitType" id="selectedDepartmentUnitTypeId" type="hidden" value='<s:property value="departmentUnitType"/>'>
					    <input name="selectedDepartmentCode" id="selectedDepartmentCodeId" type="hidden" value='<s:property value="departmentCode"/>'>
					    <input name="selectedDepartmentUnitCode" id="selectedDepartmentUnitCodeId" type="hidden" value='<s:property value="departmentUnitCode"/>'>
					     <input name="catClientCode" id="catClientCode_%{#key.index}" type="hidden" value='<s:property value="catClientCode"/>'>
					  </div>
	 				</s:iterator>
				<%-- </s:if> --%>
				<%-- <s:else> --%>
						 <input type="hidden" name="hiddenNewSecCatCode" /> 
						<%-- <s:set id="ADMITTED_EPISODE_VO" value="#session.admittedPatientVO" name="ADMITTED_EPISODE_VO" ></s:set> --%>
						<%-- <s:set name="admissionno" value="%{#session.admittedPatientVO.admissionNo}" ></s:set> --%>
						<s:if test="%{#session.admittedPatientVO.admissionNo!= null}">
						<div class="div-table-row">
						<div class="div-table-col control" style="width: 10%;">
						<%-- <s:property  value="%{#session.admittedPatientVO.patSecondaryCatCode}"  /> --%>
							<s:if test="%{#session.admittedPatientVO.patSecondaryCatCode== '-1' || #session.admittedPatientVO.patSecondaryCatCode==null || #session.admittedPatientVO.patSecondaryCatCode == #session.admittedPatientVO.patPrimaryCatCode}">
								 <%-- <input type="checkbox" name="selectEpisode" tabindex="1"  value='<s:property value="episodeCode"/>'  onclick='enableIPDEpisode(this);'/> --%>
								 <input type="radio" name="selectedEpisodeIPD" tabindex="1" <s:property value="checkedValue" />  value='<s:property value="1"/>' onclick='submitForm("GETDTLFORCATCHANGEIPD");'/> 
							</s:if>
							<s:else>
							 <%-- <input type="checkbox" name="selectEpisode" tabindex="1"  value='<s:property value="episodeCode"/>' disabled="disabled" onclick='enableIPDEpisode(this);'/> --%>
							<input type="radio" name="selectedEpisodeIPD" tabindex="1" <s:property value="checkedValue" />  value='<s:property value="1"/>' onclick='submitForm("GETDTLFORCATCHANGEIPD");'/> 
							</s:else>
						</div>
						<div class="div-table-col control" style="width: 15%;">
						
						
						<%-- <s:if test="%{#session.admittedPatientVO.patSecondaryCatCode== '-1'  || #session.admittedPatientVO.patSecondaryCatCode==null || #session.admittedPatientVO.patSecondaryCatCode == #session.admittedPatientVO.patPrimaryCatCode}">
						 <input type="checkbox" name="revokeChk" tabindex="1" <s:property value="revokeChk" />  title='<s:property value="#key.index"/>' value='<s:property value="episodeCode"/>' onclick='enableIPDRevokeEpisode(this);'  disabled="disabled"/>													
						</s:if>
						<s:else>
						<input type="checkbox" name="revokeChk" tabindex="1"  value='<s:property value="episodeCode"/>' onclick='enableIPDRevokeEpisode(this);'/>
						</s:else> --%>
						
						 <s:text name = "IPD"/>
														 
						</div>
						<div class = "div-table-col control" style = "width:15%"> 
						 <s:property value="%{#session.admittedPatientVO.department}"/>
						</div>
						<div class = "div-table-col control" style = "width:15%"> 
						 <s:property value="%{#session.admittedPatientVO.departmentUnit}"/>
						</div>
						 
						<div class="div-table-col control" style="width: 15%;">
							<s:property value="%{#session.admittedPatientVO.admissionNo}"/>
						</div>
						<div class="div-table-col control" style="width: 15%;">
							<s:property value="%{#session.admittedPatientVO.ward}"/>
						</div>
						<%-- <div class="div-table-col control" style="width: 20%;">
						   <s:property  value="%{#session.admittedPatientVO.patSecondaryCatCode}"  />
					     <s:select id="newSecCatCodeId" name="newSecCatCode" value="%{#session.admittedPatientVO.patSecondaryCatCode}" headerKey="-1" list="%{#session.optionSecondaryCategory}" headerValue="Select Value"  listKey="value" listValue="label"  cssStyle="width:197px" disabled="true" onchange="getNewCategoryCode(this)">
						 </s:select>
						</div>
						<div class="div-table-col control" style="width: 20%;">
						
						<s:textfield  name="remarks" tabindex="1" maxlength="150"  size="20"  onkeypress="return CheckMaxLength(event,this,150,3)" disabled="true">
						</s:textfield>
						</div> --%>
									
						<input name="selectedDepartmentUnitType" id="selectedDepartmentUnitTypeId" type="hidden" value='<s:property value="departmentUnitType"/>'>
					    <input name="selectedDepartmentCode" id="selectedDepartmentCodeId" type="hidden" value='<s:property value="departmentCode"/>'>
					    <input name="selectedDepartmentUnitCode" id="selectedDepartmentUnitCodeId" type="hidden" value='<s:property value="departmentUnitCode"/>'>
					    <input type = "hidden" name ="episodeCode" value = '<s:property value="%{#session.admittedPatientVO.episodeCode}"/>'/>
					  </div>
					  </s:if>
				<%-- </s:else> --%>
				 </div> 		<!-- Checkpoint by Vasu -->
		</s:if>	
                <%-- <div id = "divSelectedCategory" style="display:none">
                  <p><Font color="red"><s:text name="Selected Treatment Categories Are:"/></Font></p>
                   <br>
                   <div class = "div-table" id = "demo">
                    
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
						
						<%-- <s:if test="%{#goFlagForJsp!=0}">
							<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
							<a href="#" class="button" id="clearId" onclick ="submitForm('NEW')"><span class="clear"><s:text name="clear"/></span></a>
						</s:if>
						<s:else>
						<a href="#" class="button" id="initialClearId" ><span class="clear"><s:text name="clear"/></span></a>
						</s:else> --%>
						
						
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