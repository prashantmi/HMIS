<!-- 
  Developer : Aadil
  Date		: May-2014 
-->

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css"> 
<link href="/HIS/hisglobal/customToolTip/css/writeOnToolTip.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css"> -->

<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="../../hisglobal/css/jquery-ui.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>





<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>

<!--By Surabhi for SNOMED CT word base  -->
<!-- <link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/searchtool.css"> -->
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/searchtoolnew.css">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css"> -->
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>

<script type="text/javascript">

function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    var elmt=id.split('_');
    var elmtid=elmt[1];
    clear(elmtid);
    document.getElementById("txt-snomed-ct-search_"+elmtid).value = "";
   
});

function clear(id)
{
	//var elmtid=id.split('_');
	var trgt=id;
	var targetPrefferedTerm="";  
	 var targetConceptId="";
	 //document.getElementById(id).value="";
	 if(trgt=="1")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTimmediateCause1")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdimmediateCause1")[0];   //concept Id
		 document.getElementsByName("immediateCause1")[0].value="";
		
		}
	 if(trgt=="2")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTimmediateCause2")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdimmediateCause2")[0];   //concept Id
		 document.getElementsByName("immediateCause2")[0].value="";
		
		}
	 if(trgt=="3")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTimmediateCause3")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdimmediateCause3")[0];   //concept Id
		 document.getElementsByName("immediateCause3")[0].value="";
		
		}
	 if(trgt=="4")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTOtherCause")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdOtherCause")[0];   //concept Id
		 document.getElementsByName("otherCause")[0].value="";
		
		}
	 
		    targetPrefferedTerm.value = "";
		    targetConceptId.value = "";
	 
}

window.onload = function()
{	
	load1('1','DISORDER');
	load1('2','DISORDER');
	load1('3','DISORDER');
	load1('4','DISORDER');
	
}

function load1(elmtId,semantictag)
{
	if(elmtId==null || elmtId==undefined)
	{
		elmtId="1"; semantictag="DISORDER";
	}
	if(elmtId=="1")
	{
	 id="immediateCause1";
	}
	if(elmtId=="2")
	{
	 id="immediateCause2";
	}
	if(elmtId=="3")
	{
	 id="immediateCause3";
	}
	if(elmtId=="4")
	{
	 id="otherCause";
	}
	semantictag="";
	setTarget(id);
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	var callbck_index =function(ret_OUT){setValue(ret_OUT);};
	//alert(callbck_index);
	//alert($('[name="isSnomedServiceOn"]').val());
	if($('[name="isSnomedServiceOn"]').val()==<%=RegistrationConfig.SNOMED_SERVICE_ON%>){
		selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	}else{
		selectSNOMEDCTmulti('', '','','','null', '', '');
	}
			
	 //Added by Surabhi on 1st Feb'17 for free text if snomed field is disabled  
	
	if(document.getElementsByName("immediateCause1")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_1").value;
	document.getElementsByName("immediateCause1")[0].value=s;
	}
	
	if(document.getElementsByName("immediateCause2")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_2").value;
	document.getElementsByName("immediateCause2")[0].value=s;
	}
	
	if(document.getElementsByName("immediateCause3")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_3").value;
	document.getElementsByName("immediateCause3")[0].value=s;
	}
	
	if(document.getElementsByName("otherCause")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_4").value;
	document.getElementsByName("otherCause")[0].value=s;
	}
	//end
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	/* $("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	$("#conceptdiv_3").hide();
	$("#norecorddiv_3").hide();
	$("#conceptdiv_4").hide();
	$("#norecorddiv_4").hide(); */
	 
}  

function setTarget(id)
{
	if(id=="immediateCause1")
	{
		document.getElementsByName("targetId")[0].value="immediateCause1";
	}
	if(id=="immediateCause2")
	{
		document.getElementsByName("targetId")[0].value="immediateCause2";
	}
	if(id=="immediateCause3")
	{
		document.getElementsByName("targetId")[0].value="immediateCause3";
	}
	if(id=="otherCause")
	{
		document.getElementsByName("targetId")[0].value="otherCause";
	}
	
}

function setValue(selectedSNOMEDTerm)
{	
	//alert("set value= "+selectedSNOMEDTerm.term);
	var target=document.getElementsByName("targetId")[0].value;
		
	var targetPrefferedTerm="";
	var targetConceptId="";
	
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	{
		
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
	
		if(target == "immediateCause1") // Visit Reason
		{
			//alert("inside patVisitReason of setValue()");
			 
			 targetPrefferedTerm=document.getElementsByName("snomdPTimmediateCause1")[0];  //preffered term
			 targetConceptId=document.getElementsByName("snomdCIdimmediateCause1")[0];   //concept Id
		
			 if(document.getElementsByName("immediateCause1")[0].value=="") document.getElementsByName("immediateCause1")[0].value = str;
			else document.getElementsByName("immediateCause1")[0].value = document.getElementsByName("immediateCause1")[0].value + ", " + str;
			
		}
		
		if(target == "immediateCause2") // Visit Reason
		{
			//alert("inside patVisitReason of setValue()");
			 
			 targetPrefferedTerm=document.getElementsByName("snomdPTimmediateCause2")[0];  //preffered term
			 targetConceptId=document.getElementsByName("snomdCIdimmediateCause2")[0];   //concept Id
		
			 if(document.getElementsByName("immediateCause2")[0].value=="") document.getElementsByName("immediateCause2")[0].value = str;
			else document.getElementsByName("immediateCause2")[0].value = document.getElementsByName("immediateCause2")[0].value + ", " + str;
			
		}
		
		if(target == "immediateCause3") // Visit Reason
		{
			//alert("inside patVisitReason of setValue()");
			 
			 targetPrefferedTerm=document.getElementsByName("snomdPTimmediateCause3")[0];  //preffered term
			 targetConceptId=document.getElementsByName("snomdCIdimmediateCause3")[0];   //concept Id
		
			 if(document.getElementsByName("immediateCause3")[0].value=="") document.getElementsByName("immediateCause3")[0].value = str;
			else document.getElementsByName("immediateCause3")[0].value = document.getElementsByName("immediateCause3")[0].value + ", " + str;
			
		}
		
		if(target == "otherCause") // Visit Reason
		{
			//alert("inside patVisitReason of setValue()");
			 
			 targetPrefferedTerm=document.getElementsByName("snomdPTOtherCause")[0];  //preffered term
			 targetConceptId=document.getElementsByName("snomdCIdOtherCause")[0];   //concept Id
		
			 if(document.getElementsByName("otherCause")[0].value=="") document.getElementsByName("otherCause")[0].value = str;
			else document.getElementsByName("otherCause")[0].value = document.getElementsByName("otherCause")[0].value + ", " + str;
			
		}
	 
		if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
		else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
		
		if(targetConceptId.value=="")  targetConceptId.value = str1;
		else targetConceptId.value = targetConceptId.value + "#" + str1;
		
	}
}

					 //Added by Surabhi on 1st Feb'17 for free text if snomed field is disabled  

function freeText()
{
	if(document.getElementsByName("immediateCause1")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_1").value;
	document.getElementsByName("immediateCause1")[0].value=s;
	}
	
	if(document.getElementsByName("immediateCause2")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_2").value;
	document.getElementsByName("immediateCause2")[0].value=s;
	}
	
	if(document.getElementsByName("immediateCause3")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_3").value;
	document.getElementsByName("immediateCause3")[0].value=s;
	}
	
	if(document.getElementsByName("otherCause")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_4").value;
	document.getElementsByName("otherCause")[0].value=s;
	}
}
//end
</script>
 <!--End: Surabhi  -->
 

<title>Patient Death Detail</title>

</head>

<body>
	<s:form action="PatientDeathDetail">
	
		<s:hidden name="afterGo" id="afterGoId" value="%{afterGo}" />
		<s:hidden name="strErrorMessage" value="%{strErrorMessage}"/>
		<s:hidden name="strNormalMsg" value="%{strNormalMsg}"/>
		<s:hidden name="hiddenTimeHr" value="%{hiddenTimeHr}" id="hiddenTimeHrId" ></s:hidden>
		<s:hidden name="hiddenTimeMin" value="%{hiddenTimeMin}" id="hiddenTimeMinId" ></s:hidden>
		<s:hidden name="flagHasActionError" id="flagHasActionErrorId" value="%{hasActionErrors()}"/>
		<s:hidden name="isDesk" value="%{isDesk}" />	
		
		<input type="hidden" name="sysdate" id="sysdateId">
		<input type="hidden" name="episodeCode" >
		<input type="hidden" name="episodeVisitNo" >
		<input type="hidden" name="departmentCode" >
		<input type="hidden" name="departmentUnitCode" >
		<input type="hidden" name="wardCode" >
		<input type="hidden" name="roomCode" >
		
		<s:set name="afetrGoForJsp" value="afterGo"></s:set>
		<s:set name="errorMessageForJsp" value="strErrorMessage"></s:set>
		<s:set name="normalMessageForJsp" value="strNormalMsg"></s:set>
		<s:set name="hiddenTimeHrForJsp" value="hiddenTimeHr"></s:set>
		<s:set name="hiddenTimeMinForJsp" value="hiddenTimeMin"></s:set>
		
		
		<div class="wrapper rounded">

			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col title width100"><s:text name="global.patient"/>&nbsp;<s:text name="death"/>&nbsp;<s:text name="detail"/></div>
				</div>
			</div>
			<his:InputCrNoTag />
			<s:if test="%{#afetrGoForJsp!=0}">
				<%-- <s:action name="patientDetail" executeResult="true"></s:action> --%>
				<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
				
				<div class="div-table" >
					<div class="div-table-row">
						<div class="div-table-col title width100 "><s:text name="open"/>&nbsp;<s:text name="episodedtl"/></div>
					</div>
				</div>
				<div class="div-table" id="divOpenEpisodeDtlId">
					<s:set name="voArrOpenEpisode" value="#session[@registration.config.RegistrationConfig@ARR_OPEN_EPISODE_VO_FOR_PAT_DEATH_DTL]"></s:set>
					<s:if test="%{#voArrOpenEpisode!= null && #voArrOpenEpisode.length>0}">
						<div class="div-table-listing rounded">
							<div class="div-table-row listHeader">
								<div class="div-table-col alignCenter width10"><s:text name="select"/></div>
								<div class="div-table-col alignCenter width50"><s:text name="unit"/>&nbsp;<s:text name="name"/> </div>
								<div class="div-table-col alignCenter width40"><s:text name="global.visit"/>&nbsp;<s:text name="date"/></div>
								
							</div>
							<s:iterator id="voArrOpenEpisodeId"  value="voArrOpenEpisode" status="statusArrOpenEpisodeVO">
								<div class="div-table-row listData">
									<div class="div-table-col alignCenter width10">
										<s:set name="index" value="%{#statusArrOpenEpisodeVO.index+''}"></s:set>
										<input type="radio" name="chks" id='chksId<s:property value="index"/>' value='<s:property value="index"/>' onkeypress="patDeathDtlJsObj.onclickEpisode(this,'<s:property value="index"/>')" onclick="patDeathDtlJsObj.onclickEpisode(this,'<s:property value="index"/>')"/>
										<input type="hidden" name="selEpisodeCode" value='<s:property value="episodeCode"/>'>
										<input type="hidden" name="selEpisodeVisitNo" value='<s:property value="episodeVisitNo"/>'>
										<input type="hidden" name="selVisitDate" value='<s:property value="entryDate"/>'>
										<input type="hidden" name="selDeptCode" value='<s:property value="departmentCode"/>'>
										<input type="hidden" name="selDeptUnitCode" value='<s:property value="departmentUnitCode"/>'>
										<input type="hidden" name="selWardCode" value='<s:property value="wardCode"/>'>
										<input type="hidden" name="selRoomCode" value='<s:property value="roomCode"/>'>
										<input type="hidden" name="selMlcFlag" value='<s:property value="mlcFlag"/>'>
										<input type="hidden" name="selMlcNo" value='<s:property value="mlcNo"/>'>
									</div>
									<div class="div-table-col alignCenter width50">
										<s:property value="departmentUnit"/>
									</div>
									<div class="div-table-col alignCenter width40">
										<s:property value="entryDate"/>
									</div>
									
								</div>
							</s:iterator>
						</div>
					</s:if>
					<s:else>
						<div class="div-table-col width100 alignCenter">No Record Found</div>
					</s:else>
				</div>
				<%-- <s:if test="!hasActionErrors()"> --%>
				<div class="div-table" >
					<div class="div-table-row">
						<div class="div-table-col title width100 "><s:text name="death"/>&nbsp;<s:text name="notification"/>&nbsp;<s:text name="detail"/>  </div>
					</div>
				</div>
				
				<div class="div-table" >
					<div class="div-table-row">
						<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="date"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="death"/></div>
						<div class="div-table-col width25 Control">
							<input type="text" class="input35prcnt" tabindex="1" name="deathDate" id="deathDateId" readonly="readonly">
						</div>
						<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="time"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="death"/></div>
						<div class="div-table-col width25 Control">
							<input type="text" class="input10prcnt" name="deathTimeHr" tabindex="1" id="deathTimeHrId" value='<s:property value="%{#hiddenTimeHrForJsp}"/>' maxlength="2" onkeyup="moveToRightBox(this, event,'deathTimeMin')"> :
							<input type="text" class="input10prcnt" name="deathTimeMin" tabindex="1" id="deathTimeMinId" value='<s:property value="%{#hiddenTimeMinForJsp}"/>' maxlength="2" > (HH:MM 24 Hrs)
						</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width25 label"><s:text name="manner"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="death"/></div>
						<div class="div-table-col width25 Control">
							<s:set name="optionsDeathMannerList" value="#session[@registration.config.RegistrationConfig@ESSENTIAL_DEATH_MANNER_LIST]"></s:set>
							<s:if test="%{#optionsDeathMannerList!= null}">
								<s:select name="deathMannerCode" id="deathMannerCodeId" 
									cssClass="select45prcnt" list="optionsDeathMannerList"  
									 listKey="value" listValue="label" 
								   headerKey="-1"  headerValue="Select Value" />
							</s:if>
							
						</div>
					</div>
					
					<div class="div-table-row female" style="display: none;">
						<div class="div-table-col width50">
							<div class="div-table" >
								<div class="div-table-row">
									<div class="div-table-col width50 label"><s:text name="global.is"/>&nbsp;<s:text name="pregnant"/></div>
									<div class="div-table-col width50 Control">
										<s:text name="yes"/><input type="radio" name="isPregnant" onclick="patDeathDtlJsObj.showHidePregnantDtl();" onkeypress="patDeathDtlJsObj.showHidePregnantDtl();" value='<s:property value="@registration.config.RegistrationConfig@IS_PREGNATNT_YES"/>'>
										<s:text name="no"/><input type="radio" name="isPregnant" checked="checked" onclick="patDeathDtlJsObj.showHidePregnantDtl();" onkeypress="patDeathDtlJsObj.showHidePregnantDtl();" value='<s:property value="@registration.config.RegistrationConfig@IS_PREGNATNT_NO"/>'>
									</div>
								</div>
								<div class="div-table-row pregnant" style="display: none;">
									<div class="div-table-col width50 label"><s:text name="global.is"/>&nbsp;<s:text name="delivery"/>  </div>
									<div class="div-table-col width50 Control">
										<s:text name="yes"/><input type="radio" name="isDelivery" value='<s:property value="@registration.config.RegistrationConfig@IS_DELIVERY_YES"/>'>
										<s:text name="no"/><input type="radio" name="isDelivery" checked="checked" value='<s:property value="@registration.config.RegistrationConfig@IS_DELIVERY_NO"/>'>
									</div>
								</div>
							</div>
						</div>
						<div class="div-table-col width50">
							<div class="div-table pregnant" style="display: none;">
								<div class="div-table-row">
									<div class="div-table-col width50 label"><s:text name="pregnancy"/>&nbsp;<s:text name="global.remarks"/></div>
									<div class="div-table-col width50 Control">
										<textarea rows="2" name="pregnancyRemarks"  id="pregnancyRemarksId" class="textarea60prcnt"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="div-table-row">
						<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="verification"/>&nbsp;<s:text name="date"/> </div>
						<div class="div-table-col width25 Control">
							<input type="text" class="input35prcnt" name="verificationDate" tabindex="1" id="verificationDateId" readonly="readonly">
						</div>
						<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="verification"/>&nbsp;<s:text name="time"/> </div>
						<div class="div-table-col width25 Control">
							<input type="text" class="input10prcnt" name="verificationTimeHr" tabindex="1" id="verificationTimeHrId" value='<s:property value="%{#hiddenTimeHrForJsp}"/>' maxlength="2" onkeyup="moveToRightBox(this, event,'verificationTimeMin')"> :
							<input type="text" class="input10prcnt" name="verificationTimeMin" tabindex="1" id=verificationTimeMinId" value='<s:property value="%{#hiddenTimeMinForJsp}"/>' maxlength="2" > (HH:MM 24 Hrs)
						</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width25 label"><s:text name="con"/></div>
						<div class="div-table-col width25 Control">
							<s:select name="attConsultantId" id="attConsultantId" 
										cssClass="select45prcnt" list="%{#session.consultantList}"  
										 listKey="value" listValue="label" 
									   headerKey="-1"  headerValue="Select Value" />
						</div>
						<div class="div-table-col width25 label"><s:text name="con"/>&nbsp;<s:text name="global.remarks"/> </div>
						<div class="div-table-col width25 Control">
							<textarea rows="2" name="consultantRemarks"  id="consultantRemarksId" class="textarea60prcnt"></textarea>
						</div>
					</div>
				</div>
				
				<div class="div-table" >
					<div class="div-table-row">
						<div class="div-table-col title width100 "><s:text name="cause"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="death"/></div>
					</div>
				</div>
				<div class="div-table" >
					<div class="div-table-row">
						<div class="div-table-col width100 subHeader"><s:text name="part1"/></div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width20 label"><font color="#FF0000">*</font><s:text name="immediate"/>&nbsp;<s:text name="cause"/></div>
						<div class="div-table-col width5 alignCenter fontBold">A.</div>
						<div class="div-table-col width75 control">
							<div align="left" id="dialog-form_1" >
							<input type="hidden" name="snomdPTimmediateCause1" />
				        	<input type="hidden" name="snomdCIdimmediateCause1" />
							<input type="hidden" name="immediateCause1"/>
								
								<div id="snomed-ct-search">
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<!-- <textarea name="immediateCause1" id="immediateCause1Id" class="textarea66prcnt" rows="2"></textarea> -->
								<textarea name="txt-snomed-ct-search_1" rows="2" id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input tooltipClass" value="" onfocus="load1('1','DISORDER');" style="width:65%;height:75%" autocomplete="off"></textarea>
								</div>
								<div id="norecorddiv_1">
								<label style="display: none;" id="reccnt">No. of records : </label>
								<span style="display: inline;" id="reccount" ></span>
								<label style="display: none;" id="nosuggestion">No suggestions found</label>
								<label style="display: none;" id="norec">No results found</label>
		                    	<label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
	                 			</div>         
                   				<div class="concept" id="conceptdiv_1"></div>
                    		</div>
						</div>
					</div>
					
					<div class="div-table-row">
						<div class="div-table-col width20 label"><s:text name="antecedent"/>&nbsp;<s:text name="cause"/> </div>
						<div class="div-table-col width5 alignCenter fontBold">B.</div>
						<div class="div-table-col width75 control">
							
							<div align="left" id="dialog-form_2" >
							<input type="hidden" name="snomdPTimmediateCause2" />
				        	<input type="hidden" name="snomdCIdimmediateCause2" />
							<input type="hidden" name="immediateCause2"/>
										
								<div id="snomed-ct-search">
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<!-- <textarea name="immediateCause2" id="immediateCause2Id" class="textarea66prcnt" rows="2"></textarea>-->
								<textarea name="txt-snomed-ct-search_2" rows="2" id="txt-snomed-ct-search_2" class="clearable ui-autocomplete-input tooltipClass" value="" onfocus="load1('2','DISORDER');" style="width:65%;height:75%" autocomplete="off"></textarea>
								</div>
								<div id="norecorddiv_2">
								<label style="display: none;" id="reccnt">No. of records : </label>
								<span style="display: inline;" id="reccount" ></span>
								<label style="display: none;" id="nosuggestion">No suggestions found</label>
								<label style="display: none;" id="norec">No results found</label>
		                    	<label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
	                 			</div>         
                   				<div class="concept" id="conceptdiv_2"></div>
                    		</div>
						</div>
					</div>
					
					<div class="div-table-row">
						<div class="div-table-col width20 label"><s:text name="underlying"/>&nbsp;<s:text name="cause"/> </div>
						<div class="div-table-col width5 alignCenter fontBold">C.</div>
						<div class="div-table-col width75 control">
							<div align="left" id="dialog-form_3" >
								<input type="hidden" name="snomdPTimmediateCause3" />
					        	<input type="hidden" name="snomdCIdimmediateCause3" />
								<input type="hidden" name="immediateCause3"/>
								<div id="snomed-ct-search">
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<!-- <textarea name="immediateCause3" id="immediateCause3Id" class="textarea66prcnt" rows="2"></textarea> -->
								<textarea name="txt-snomed-ct-search_3" rows="2" id="txt-snomed-ct-search_3" class="clearable ui-autocomplete-input tooltipClass" value="" onfocus="load1('3','DISORDER');" style="width:65%;height:75%" autocomplete="off"></textarea>
								</div>
								<div id="norecorddiv_3">
								<label style="display: none;" id="reccnt">No. of records : </label>
								<span style="display: inline;" id="reccount" ></span>
								<label style="display: none;" id="nosuggestion">No suggestions found</label>
								<label style="display: none;" id="norec">No results found</label>
		                    	<label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
	                 			</div>         
	                  			<div class="concept" id="conceptdiv_3"></div>
							</div>
						</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width100 subHeader"><s:text name="part2"/></div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width25 label"><s:text name="other"/>&nbsp;<s:text name="significant"/>&nbsp;<s:text name="cause"/></div>
						<div class="div-table-col width75 control">
							<!-- <textarea name="otherCause" id="otherCauseId" class="textarea66prcnt" rows="2"></textarea> -->
							<div align="left" id="dialog-form_4" >
							<input type="hidden" name="snomdPTOtherCause" />
				        	<input type="hidden" name="snomdCIdOtherCause" />
							<input type="hidden" name="otherCause"/>
										
								<div id="snomed-ct-search">
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<!-- <textarea name="immediateCause1" id="immediateCause1Id" class="textarea66prcnt" rows="2"></textarea> -->
								<textarea name="txt-snomed-ct-search_4" rows="2" id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input tooltipClass" value="" onfocus="load1('4','DISORDER');" style="width:65%;height:75%" autocomplete="off"></textarea>
								</div>
								<div id="norecorddiv_4">
								<label style="display: none;" id="reccnt">No. of records : </label>
								<span style="display: inline;" id="reccount" ></span>
								<label style="display: none;" id="nosuggestion">No suggestions found</label>
								<label style="display: none;" id="norec">No results found</label>
		                    	<label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
	                 			</div>         
                   				<div class="concept" id="conceptdiv_4"></div>
                    		</div>
						</div>
					</div>
				</div>
				<div class="div-table" >
					<div class="div-table-row">
						<div class="div-table-col width100" id="divInjuryDtlId" style="display: none;">
							
							<div class="div-table" id="divInjuryDtlId">
								<div class="div-table-row">
									<div class="div-table-col title width100"><s:text name="injury"/>&nbsp;<s:text name="detail"/> </div>
								</div>
								<div class="div-table-row">
									<div class="div-table-col width25 label"><s:text name="nature"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="injury"/></div>
									<div class="div-table-col width25 Control">
										<s:select 	name="injuryNatureCode" id="injuryNatureCodeId"
												cssClass="select61prcnt" list="#session[@registration.config.RegistrationConfig@ESSENTIAL_INJURY_NATURE_LIST]"  
												 listKey="value" listValue="label" 
											   headerKey="-1"  headerValue="Select Value"/>
									</div>
									<div class="div-table-col width25 label"><s:text name="mlc"/>&nbsp;<s:text name="type"/> </div>
									<div class="div-table-col width25 Control">
										<s:select 	name="mlcTypeCode" id="mlcTypeCodeId"
												cssClass="select61prcnt" list="#session[@registration.config.RegistrationConfig@ESSENTIAL_INJURY_TYPE_LIST]"  
												 listKey="value" listValue="label" 
											   headerKey="-1"  headerValue="Select Value"/>
									</div>
								</div>
								<div class="div-table-row">
									<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="injury"/>&nbsp;<s:text name="global.remarks"/></div>
									<div class="div-table-col width25 Control">
										<textarea name="injuryRemarks" id="injuryRemarksId" class="textarea60prcnt"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
				
				<div class="div-table" id="divHandoverDtlHeaderId">
					<div class="div-table-row title">
						<div class="div-table-col width5">
							<%-- <s:checkbox name="isHandoverTo" tabindex="1" value="1"  onclick="patDeathDtlJsObj.showHandoverToDiv(this)" /> --%>
							<input type="checkbox" name="isHandoverTo" tabindex="1" id="isHandoverToId" value="0" onclick="patDeathDtlJsObj.showHandoverToDiv(this);">
						</div>
						<div class="div-table-col width95"><s:text name="handover"/>&nbsp;<s:text name="to"/>&nbsp;<s:text name="detail"/> </div>
					</div>
				</div>
				<div class="div-table" >
					<div class="div-table-row">
						<div class="div-table-col width100" id="divHandoverDtlDataId" style="display: none;">
							<div class="div-table">
								<div class="div-table-row">
									<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="handover"/>&nbsp;<s:text name="date"/></div>
									<div class="div-table-col width25 Control">
										<!-- <input type="text" class="input35prcnt" name="handoverDate" tabindex="1" id="handoverDateId" readonly="readonly"> -->
										<input id="handoverDateId" tabindex="1" type="text" name="handoverDate" class="input35prcnt">&nbsp;
									</div>
									<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="handover"/>&nbsp;<s:text name="time"/> </div>
									<div class="div-table-col width25 Control">
										<input type="text" class="input10prcnt" name="handoverTimeHr" tabindex="1" id="handoverTimeHrId" value='<s:property value="%{#hiddenTimeHrForJsp}"/>' maxlength="2" onkeyup="moveToRightBox(this, event,'handoverTimeMin')"> :
										<input type="text" class="input10prcnt" name="handoverTimeMin" tabindex="1" id="handoverTimeMinId" value='<s:property value="%{#hiddenTimeMinForJsp}"/>' maxlength="2" > (HH:MM 24 Hrs)
									</div>
								</div>
								<div class="div-table-row">
									<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="handover"/>&nbsp;<s:text name="to"/> </div>
									<div class="div-table-col width25 Control">
										<select name="bodyHandoverTo" id="bodyHandoverToId" tabindex="1" class="select61prcnt" onchange="patDeathDtlJsObj.showHandoverDetail()">
											<option value="-1">Select Value</option>
											<option value='<s:property value="@registration.config.RegistrationConfig@DEAD_BODY_HANDOVER_TO_MORTUARY"/>'>Mortuary</option>
											<option value='<s:property value="@registration.config.RegistrationConfig@DEAD_BODY_HANDOVER_TO_POLICE"/>'>Police</option>
											<option value='<s:property value="@registration.config.RegistrationConfig@DEAD_BODY_HANDOVER_TO_RELATIVES"/>'>Relatives</option>
											<%-- <s:if test="%{isMlc==@registration.config.RegistrationConfig@IS_MLC_TRUE}">
												<option value='<s:property value="@registration.config.RegistrationConfig@DEAD_BODY_HANDOVER_TO_RELATIVES"/>'>Relatives</option>
											</s:if> --%>
										</select>
										
									</div>
								</div>
								<div class="div-table-row">
									<div class="div-table-col width100">
										<div class="div-table" id="divPoliceId" style="display: none;">
											<div class="div-table-row">
												<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="officer"/>&nbsp;<s:text name="global.name"/></div>
												<div class="div-table-col width25 Control">
													<input type="text" class="input60prcnt" tabindex="1" name="officerName" id="officerNameId">
												</div>
												<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="officer"/>&nbsp;<s:text name="designation"/>  </div>
												<div class="div-table-col width25 Control">
													<input type="text" class="input60prcnt" name="officerDesignation" tabindex="1" id="officerDesignationId">
												</div>
											</div>
											<div class="div-table-row">
												<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="officer"/>&nbsp;<s:text name="batchno"/></div>
												<div class="div-table-col width25 Control">
													<input type="text" class="input60prcnt" name="officerBadgeNo" tabindex="1" id="officerBadgeNoId">
												</div>
											</div>
										</div>
										
										<div class="div-table" id="divRelativeId" style="display: none;">
											<div class="div-table-row">
												<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="relative"/>&nbsp;<s:text name="global.name"/> </div>
												<div class="div-table-col width25 Control">
													<input type="text" class="input60prcnt" name="relativeName" tabindex="1" id="relativeNameId">
												</div>
												<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="relationship"/></div>
												<div class="div-table-col width25 Control">
													<s:set name="optionsRelativeList" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_RELATION_DTL]"></s:set>
													<s:select 	name="relativeCode" tabindex="1" id="relativeCodeId"
															cssClass="select61prcnt" list="optionsRelativeList"  
															 listKey="value" listValue="label" 
														   headerKey="-1"  headerValue="Select Value"/>
												</div>
											</div>
											<div class="div-table-row">
												<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="address"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="relative"/></div>
												<div class="div-table-col width25 Control">
													<textarea name="relativeAddress" tabindex="1" id="relativeAddressId" class="textarea60prcnt"></textarea>
												</div>
											</div>
										</div>
									</div>
									</div>
								
							    <div class="div-table-row">
									<div class="div-table-col width25 label">
										<font color="#FF0000">*</font><s:text name="Is"/>&nbsp;<s:text name="global.print"/>&nbsp;<s:text name="Certificate"/>
									</div>
									<div class="div-table-col width25 control">
										<input type="checkbox" checked="checked" name="printFlag" id="printFlagId"/>
									</div>
									
									<div class="div-table-col width25 label">
										<font color="#FF0000">*</font><s:text name="No. of"/>&nbsp;<s:text name="Copies"/>
									</div>
									<div class="div-table-col width25 control">
										<input type="number" class="input10prcnt" name="noOfCopies" id="noOfCopiesId" min="1" max="9" step="1" value="3"/>
									</div>
								</div>
								<div class="div-table-row">
									<div class="div-table-col width25 label">
										<font color="#FF0000">*</font><s:text name="Is"/>&nbsp;<s:text name="Reciept"/>&nbsp;<s:text name="Taken"/>
									</div>
									<div class="div-table-col width25 control">
										<input type="checkbox" name="isReceiptTaken" id="isReceiptTakenId"/>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
				<%-- </s:if> --%>
			</s:if>
			
			<div class="div-table-button">
				<div class="div-table-row footerBar">
						<div class="div-table-col" > </div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center">
					<s:if test="%{#afetrGoForJsp!=0}">
						<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
					</s:if>
					<a href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
					<s:if test="%{isDesk!=1}">
						<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
					</s:if>
					<s:else>
						<a href="#" class="button" onclick="closeDeskTab();"><span class="cancel"><s:text name="cancel"/></span></a>
					</s:else>
				</div>
			</div>
			
		</div>
		<div class="div-table-simple">
			<div class="div-table-row">
				<div id='divMsgId' class="div-table-col alignLeft fontError width100">
					<s:if test="hasActionErrors()">
					      <s:actionerror/>
					</s:if>
					<s:else>
						<s:property value="%{#normalMessageForJsp}"/>
					</s:else>
				</div>
			</div>
		</div>
		
		<s:hidden name="normalBodyHandover" value="%{normalBodyHandover}" id="normalBodyHandoverId"></s:hidden>
		<s:hidden name="mlcBodyHandover" value="%{mlcBodyHandover}" id="mlcBodyHandoverId"></s:hidden>
		<s:hidden name="isBodyHandoverToMortuary" value="%{isBodyHandoverToMortuary}" id="isBodyHandoverToMortuaryId"></s:hidden>
		
		<s:hidden name="hiddenOnSetDate" value="%{hiddenOnSetDate}" id="hiddenOnSetDateId"></s:hidden>
		<s:hidden name="hiddenRecentVisitDate" value="%{hiddenRecentVisitDate}" id="hiddenRecentVisitDateId"></s:hidden>
		<s:hidden name="isMlc" value="%{isMlc}" id="isMlcId"></s:hidden>	
		<s:hidden name="isDeathAccidental" value="%{isDeathAccidental}" id="isDeathAccidentalId"></s:hidden>	
		<s:hidden name="printFlag" value="%{printFlag}" id="printFlagId"></s:hidden>
		<s:hidden name="noOfCopies" value="%{noOfCopies}" id="noOfCopiesId"></s:hidden>
		<s:hidden name="patMlcNo" value="%{patMlcNo}" id="patMlcNoId"></s:hidden>
		<s:hidden name="isInpatient" value="%{isInpatient}" id="isInpatient"></s:hidden>
		<s:hidden name="otherHospitalFlag" value="%{otherHospitalFlag}" />
		<input type="hidden" name="targetId" />
		<input type="hidden" name="isSnomedServiceOn" value='<s:property value="isSnomedServiceOn" />'/>
					
		<%-- <s:hidden name="%{patGender}"></s:hidden>
		<s:hidden name="%{patAge}"></s:hidden>
		<s:hidden name="isClient"/>
		<s:hidden name="isDirectCall" />
		<s:hidden name="deskType"/> --%>
		<cmbPers:cmbPers></cmbPers:cmbPers>
		<s:token/>
	</s:form>
	<script type="text/javascript" src="./../../registration/transactions/js/patientDeathDtl.js" /></script>
	

</body>
</html>