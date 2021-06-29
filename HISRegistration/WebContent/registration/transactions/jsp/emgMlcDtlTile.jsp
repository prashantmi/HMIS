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
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link href="/HIS/hisglobal/customToolTip/css/writeOnToolTip.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">
<link href='/HIS/hisglobal/css/qtip/jquery.qtip.min.css' rel='stylesheet' />

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
 <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script> 
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/basic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script> 
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.searchabledropdown-1.0.8.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-migrate-1.0.0.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>

<script src='/HIS/hisglobal/js/qtip/jquery.qtip.min.js'></script>
<script src='/HIS/hisglobal/js/qtip/writeOnToolQTip.js'></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.bpopup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.bpopup.min.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<title>MLC Detail</title>
<script type="text/javascript">
	// 		Modification Log							
	//		Modify Date				:17thDec'14 
	//		Reason	(CR/PRS)		:Hide the MLC details if no episode available
	//		Modify By				:Sheeldarshi  
$(window).on("load.loading1", function() 
		{
	var objVisitRadio= $('[name="visitRadio"]');
	var flagVisitRadioSelected=false;
	var varDisabledCount=0;
	if(typeof objVisitRadio !=undefined && objVisitRadio.length > 0){
		for(var i=0; i<objVisitRadio.length; i++){
				if(objVisitRadio[i].disabled)
				{
				varDisabledCount++;
				}
		}
				if(varDisabledCount==objVisitRadio.length)
				{
				document.getElementById("divIncludeJsp").style.display = 'none';
				document.getElementById("submitId").style.display = 'none';
				}


	}
});
	//End:Sheeldarshi
</script>
</head>

<body>
	<center>
		<s:form action="EmgMlcDtl">
			<%-- <s:set name="theme" value="simple" scope="page" /> --%>
			<s:hidden name="afterGo" id="afterGoId" value="%{afterGo}" />
			<s:hidden name="errorMessage" value="%{errorMessage}"/>
			<s:hidden name="flagMlcOnlineOffline" value="%{flagMlcOnlineOffline}"/>
			<s:hidden name="entryDate" id="entryDateId" value="%{entryDate}"/>
			<s:hidden name="isTodayOfflineVisit" value="%{isTodayOfflineVisit}"/>
			<s:hidden name="isDesk" value="%{isDesk}" />	
			<input type="hidden" name="actionName" id="actionNameId" value="EmgMlcDtl">
			
			<input type="hidden" name="nVisitNoIndex" id="nVisitNoIndexId">
			<s:set name="afetrGoForJsp" value="afterGo"></s:set>
			<s:set name="errorMessageForJsp" value="errorMessage"></s:set>
			<s:set name="flagMlcOnlineOfflineForJsp" value="flagMlcOnlineOffline"></s:set>
			<div class="wrapper rounded">

				<div class="div-table">
					<div class="div-table-row">
						<div class="div-table-col title width100"><s:text name="mlc"/>&nbsp;<s:text name="global.detail"/>
							<s:if test="%{#flagMlcOnlineOfflineForJsp==@registration.config.RegistrationConfig@MLC_PROCESS_ONLINE}">
								<s:text name="online"/>
							</s:if>
							<s:elseif test="%{#flagMlcOnlineOfflineForJsp==@registration.config.RegistrationConfig@MLC_PROCESS_OFFLINE}">
								<s:text name="offline"/>
							</s:elseif>
						</div>
					</div>
				</div>

				<s:if test="%{#flagMlcOnlineOfflineForJsp!=null && #flagMlcOnlineOfflineForJsp!=''}">
					<his:InputCrNoTag />
					<s:if test="%{#afetrGoForJsp==0 && #flagMlcOnlineOfflineForJsp!=@registration.config.RegistrationConfig@MLC_PROCESS_ONLINE}">
						<div class="div-table">
							<div class="div-table-row ">
								<div class="div-table-col width25 label"><s:text name="date"/></div>
								<div class="div-table-col width25 control">
									<input type="text" class="input30prcnt" name="visitDate" id="visitDateId"  readonly="readonly">&nbsp;
								</div>
							</div>
						</div>
					</s:if>
				</s:if>
				
				<s:if test="%{#afetrGoForJsp!=0}">
					<%-- <s:action name="patientDetail" executeResult="true"></s:action> --%>
					<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
					<div class="div-table" id="divMlcEpisodeTitleId">
						<div class="div-table-row title">
							<s:if test="%{#flagMlcOnlineOfflineForJsp!=null && #flagMlcOnlineOfflineForJsp==@registration.config.RegistrationConfig@MLC_PROCESS_ONLINE}">
								<div class="div-table-col width100"><s:text name="mlc"/>&nbsp;<s:text name="episodedtl"/></div>
							</s:if>
							<s:else>
								<div class="div-table-col width15"><s:text name="mlc"/>&nbsp;<s:text name="episodedtl"/></div>
								<div class="div-table-col width85">
									<input type="checkbox" name="selectMlcEpisode" onclick="emgOfflineMlcDtlJsObj.onClickSelectMlcEpisode(this);">
								</div>
							</s:else>
						</div>
					</div>
					
					<s:set name="voArrEpisode" value="#session[@registration.config.RegistrationConfig@EMGREGISTRATIONDESK_MLC_EPISODEVO_ARR]"></s:set>
					<s:if test="%{#voArrEpisode!= null && #voArrEpisode.length != 0}">
						<s:if test="%{#flagMlcOnlineOfflineForJsp!=null && #flagMlcOnlineOfflineForJsp==@registration.config.RegistrationConfig@MLC_PROCESS_ONLINE}">
							 <div class="div-table-listing rounded" > 
								<div class="div-table-row listHeader">
									<div class="div-table-col alignCenter width5"><s:text name="select"/></div>
									<div class="div-table-col alignCenter width35"><s:text name="visitingDeptUnit"/> </div>
									<div class="div-table-col alignCenter width10"><s:text name="global.visit"/>&nbsp;<s:text name="number"/> </div>
									<div class="div-table-col alignCenter width20"><s:text name="global.visit"/>&nbsp;<s:text name="date"/><s:text name="slash"/><s:text name="time"/></div>
									<div class="div-table-col alignCenter width15"><s:text name="mlc"/>&nbsp;<s:text name="status"/> </div>
									<div class="div-table-col alignCenter width15"><s:text name="mlc"/>&nbsp;<s:text name="number"/>&nbsp;<s:text name="ifalreadyalloted"/></div>
								</div>
								<s:iterator id="voArrEpisodeId"  value="voArrEpisode" status="statusArrEpisodeVO">
									<div class="div-table-row listData">
										<s:set name="mlcStatusForJsp" value="'-'"></s:set>
										<s:set name="disabledForJsp" value="''"></s:set>
										<s:set name="disabledClassForJsp" value="'classSwitchDisabled'"></s:set>
										<s:if test="mlcFlag==2">
											<s:set name="mlcStatusForJsp" value="'Confirmed'"></s:set>
											<s:set name="disabledForJsp" value="disabled='disabled'"></s:set>
										</s:if>
										<s:elseif test="mlcFlag==1">
											<s:set name="mlcStatusForJsp" value="'Provisional'"></s:set>
										</s:elseif>
										
										<div class="div-table-col alignCenter width5">
											<s:if test="#statusArrEpisodeVO.count==1">
												<input type="radio" name="visitRadio" class='<s:property value="#disabledClassForJsp"/>' <s:property value="#disabledForJsp"/> value='<s:property value="%{#statusArrEpisodeVO.index}"/>' tabindex="1" checked="checked" onclick="emgMlcDtlJsObj.setNVisitNoIndex(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);"/>
											</s:if>
											<s:else>
												<input type="radio" name="visitRadio" class='<s:property value="#disabledClassForJsp"/>' <s:property value="#disabledForJsp"/> value='<s:property value="%{#statusArrEpisodeVO.index}"/>' tabindex="1"  onclick="emgMlcDtlJsObj.setNVisitNoIndex(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);"/>
											</s:else>
										</div>
										<div class="div-table-col alignCenter width35"><s:property value="department"/>/<s:property value="departmentUnit"/></div>
										<div class="div-table-col alignCenter width10"><s:property value="episodeVisitNo"/> </div>
										<div class="div-table-col alignCenter width20"><s:property value="episodeDate"/></div>
										<div class="div-table-col alignCenter width15"><s:property value="#mlcStatusForJsp"/></div>
										<div class="div-table-col alignCenter width15"><s:property value="mlcNo"/></div>
										<s:hidden name="episodeDate" value="%{episodeDate}"></s:hidden>
										<s:hidden name="epiDate" value="%{episodeDate.split(' ')[0]}"></s:hidden>
										<s:hidden name="epiTimeHr" value="%{episodeDate.split(' ')[1].split(':')[0]}"></s:hidden>
										<s:hidden name="epiTimeMin" value="%{episodeDate.split(' ')[1].split(':')[1]}"></s:hidden>
									</div>
								</s:iterator>
							 </div> 
						</s:if>
						<s:else>
							<ul class="">
							<s:set name="unitIndexForJsp" value="0"></s:set>
							<s:set name="deptUnitCodeForJsp" value=""></s:set>
							<s:iterator id="voArrEpisodeId"  value="voArrEpisode" status="statusArrEpisodeVO">
								<s:if test="%{#deptUnitCodeForJsp != departmentUnitCode}">
									<s:if test="%{#unitIndexForJsp!=0}">
										</div>
									</div>
									</s:if>
									<li class="list-plus" id='<s:property value="%{#unitIndexForJsp}"/>' onclick="emgOfflineMlcDtlJsObj.showHideMlcEpisodeDtl(this,'<s:property value="%{#unitIndexForJsp}"/>');">
										<s:property value="department"/>/<s:property value="departmentUnit"/>
									</li>
									<div class="" id='divMlcEpisodeDtlId<s:property value="%{#unitIndexForJsp}"/>' style="display: none;">
										<div class="div-table-listing rounded" >
								</s:if>
										<s:set name="mlcStatusForJsp" value="'-'"></s:set>
										<s:set name="disabledForJsp" value="''"></s:set>
										<s:set name="disabledClassForJsp" value="'classSwitchDisabled'"></s:set>
										<s:if test="mlcFlag==2">
											<s:set name="mlcStatusForJsp" value="'Confirmed'"></s:set>
											<s:set name="disabledForJsp" value="disabled='disabled'"></s:set>
										</s:if>
										<s:elseif test="mlcFlag==1">
											<s:set name="mlcStatusForJsp" value="'Provisional'"></s:set>
										</s:elseif>
												
										<s:if test="%{#deptUnitCodeForJsp != departmentUnitCode}">
												<div class="div-table-row listHeader">
													<div class="div-table-col alignCenter width5"></div>
													<div class="div-table-col alignCenter width5"><s:text name="select"/></div>
													<div class="div-table-col alignCenter width35"><s:text name="visitingDeptUnit"/></div>
													<div class="div-table-col alignCenter width7"><s:text name="global.visit"/>&nbsp;<s:text name="number"/></div>
													<div class="div-table-col alignCenter width20"><s:text name="global.visit"/>&nbsp;<s:text name="date"/><s:text name="slash"/><s:text name="time"/></div>
													<div class="div-table-col alignCenter width13"><s:text name="mlc"/>&nbsp;<s:text name="status"/> </div>
													<div class="div-table-col alignCenter width15"><s:text name="mlc"/>&nbsp;<s:text name="number"/>&nbsp;<s:text name="ifalreadyalloted"/></div>
												</div>
												<div class="div-table-row listData">
													<div class="div-table-col alignCenter width5"></div>
													<div class="div-table-col alignCenter width5">
														<input type="radio" name="visitRadio" tabindex="1" value='<s:property value="%{#statusArrEpisodeVO.index}"/>'
															class='classVisitRadio<s:property value="%{#unitIndexForJsp+' ' #disabledClassForJsp}"/>' <s:property value="#disabledForJsp"/>
															onclick='emgOfflineMlcDtlJsObj.setMlcEpisodeChkBox(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);'/>
													</div>
													<div class="div-table-col alignCenter width35"><s:property value="department"/>/<s:property value="departmentUnit"/></div>
													<div class="div-table-col alignCenter width7"><s:property value="episodeVisitNo"/> </div>
													<div class="div-table-col alignCenter width20"><s:property value="episodeDate"/></div>
													<div class="div-table-col alignCenter width13"><s:property value="#mlcStatusForJsp"/></div>
													<div class="div-table-col alignCenter width15"><s:property value="mlcNo"/></div>
													<s:hidden name="episodeDate" value="%{episodeDate}"></s:hidden>
													<s:hidden name="epiDate" value="%{episodeDate.split(' ')[0]}"></s:hidden>
													<s:hidden name="epiTimeHr" value="%{episodeDate.split(' ')[1].split(':')[0]}"></s:hidden>
													<s:hidden name="epiTimeMin" value="%{episodeDate.split(' ')[1].split(':')[1]}"></s:hidden>
												</div>
										</s:if>
										<s:else>
											<div class="div-table-row listData">
												<div class="div-table-col alignCenter width5"></div>
												<div class="div-table-col alignCenter width5">
													<input type="radio" name="abcRadio" tabindex="1"  value='<s:property value="%{#statusArrEpisodeVO.index}"/>'
														class='classVisitRadio<s:property value="%{#unitIndexForJsp+' ' #disabledClassForJsp}"/>' <s:property value="#disabledForJsp"/>
														onclick='emgOfflineMlcDtlJsObj.setMlcEpisodeChkBox(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);'/>
												</div>
												<div class="div-table-col alignCenter width35"><s:property value="department"/>/<s:property value="departmentUnit"/></div>
												<div class="div-table-col alignCenter width7"><s:property value="episodeVisitNo"/> </div>
												<div class="div-table-col alignCenter width20"><s:property value="episodeDate"/></div>
												<div class="div-table-col alignCenter width13"><s:property value="#mlcStatusForJsp"/></div>
												<div class="div-table-col alignCenter width15"><s:property value="mlcNo"/></div>
												<s:hidden name="episodeDate" value="%{episodeDate}"></s:hidden>
												<s:hidden name="epiDate" value="%{episodeDate.split(' ')[0]}"></s:hidden>
												<s:hidden name="epiTimeHr" value="%{episodeDate.split(' ')[1].split(':')[0]}"></s:hidden>
												<s:hidden name="epiTimeMin" value="%{episodeDate.split(' ')[1].split(':')[1]}"></s:hidden>
											</div>
										</s:else>
										<s:if test="%{#deptUnitCodeForJsp != departmentUnitCode}">
											<s:set name="unitIndexForJsp" value="%{#unitIndexForJsp + 1}"></s:set>
										</s:if>
										<s:set name="deptUnitCodeForJsp" value="departmentUnitCode"></s:set>
									
							</s:iterator>
										</div>
									</div>
						</s:else>
					</s:if>
					<s:else>
					 <!-- Added by Vasu on 04.May.18 -->
					     <s:if test="%{#flagMlcOnlineOfflineForJsp!=null && #flagMlcOnlineOfflineForJsp==@registration.config.RegistrationConfig@MLC_PROCESS_ONLINE}">
							 <div class="div-table-listing rounded" > 
								<div class="div-table-row listHeader">
									<div class="div-table-col alignCenter width5"><s:text name="select"/></div>
									<div class="div-table-col alignCenter width35"><s:text name="visitingDeptUnit"/> </div>
									<div class="div-table-col alignCenter width10"><s:text name="global.visit"/>&nbsp;<s:text name="number"/> </div>
									<div class="div-table-col alignCenter width20"><s:text name="global.visit"/>&nbsp;<s:text name="date"/><s:text name="slash"/><s:text name="time"/></div>
									<div class="div-table-col alignCenter width15"><s:text name="mlc"/>&nbsp;<s:text name="status"/> </div>
									<div class="div-table-col alignCenter width15"><s:text name="mlc"/>&nbsp;<s:text name="number"/>&nbsp;<s:text name="ifalreadyalloted"/></div>
								</div>
								<s:iterator id="voArrEpisodeId"  value="voArrEpisode" status="statusArrEpisodeVO">
									<div class="div-table-row listData">
										<s:set name="mlcStatusForJsp" value="'-'"></s:set>
										<s:set name="disabledForJsp" value="''"></s:set>
										<s:set name="disabledClassForJsp" value="'classSwitchDisabled'"></s:set>
										<s:if test="mlcFlag==2">
											<s:set name="mlcStatusForJsp" value="'Confirmed'"></s:set>
											<s:set name="disabledForJsp" value="disabled='disabled'"></s:set>
										</s:if>
										<s:elseif test="mlcFlag==1">
											<s:set name="mlcStatusForJsp" value="'Provisional'"></s:set>
										</s:elseif>
										
										<div class="div-table-col alignCenter width5">
											<s:if test="#statusArrEpisodeVO.count==1">
												<input type="radio" name="visitRadio" class='<s:property value="#disabledClassForJsp"/>' <s:property value="#disabledForJsp"/> value='<s:property value="%{#statusArrEpisodeVO.index}"/>' tabindex="1" checked="checked" onclick="emgMlcDtlJsObj.setNVisitNoIndex(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);"/>
											</s:if>
											<s:else>
												<input type="radio" name="visitRadio" class='<s:property value="#disabledClassForJsp"/>' <s:property value="#disabledForJsp"/> value='<s:property value="%{#statusArrEpisodeVO.index}"/>' tabindex="1"  onclick="emgMlcDtlJsObj.setNVisitNoIndex(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);"/>
											</s:else>
										</div>
										<div class="div-table-col alignCenter width35"><s:property value="department"/>/<s:property value="departmentUnit"/></div>
										<div class="div-table-col alignCenter width10"><s:property value="episodeVisitNo"/> </div>
										<div class="div-table-col alignCenter width20"><s:property value="episodeDate"/></div>
										<div class="div-table-col alignCenter width15"><s:property value="#mlcStatusForJsp"/></div>
										<div class="div-table-col alignCenter width15"><s:property value="mlcNo"/></div>
										<s:hidden name="episodeDate" value="%{episodeDate}"></s:hidden>
										<s:hidden name="epiDate" value="%{episodeDate.split(' ')[0]}"></s:hidden>
										<s:hidden name="epiTimeHr" value="%{episodeDate.split(' ')[1].split(':')[0]}"></s:hidden>
										<s:hidden name="epiTimeMin" value="%{episodeDate.split(' ')[1].split(':')[1]}"></s:hidden>
									</div>
								</s:iterator>
							 </div> 
						</s:if>
						
						
						<%-- <s:else> --%>
							<ul class="">
							<s:set name="unitIndexForJsp" value="0"></s:set>
							<s:set name="deptUnitCodeForJsp" value=""></s:set>
							<s:iterator id="voArrEpisodeId"  value="voArrEpisode" status="statusArrEpisodeVO">
								<s:if test="%{#deptUnitCodeForJsp != departmentUnitCode}">
									<s:if test="%{#unitIndexForJsp!=0}">
										</div>
									</div>
									</s:if>
									<li class="list-plus" id='<s:property value="%{#unitIndexForJsp}"/>' onclick="emgOfflineMlcDtlJsObj.showHideMlcEpisodeDtl(this,'<s:property value="%{#unitIndexForJsp}"/>');">
										<s:property value="department"/>/<s:property value="departmentUnit"/>
									</li>
									<div class="" id='divMlcEpisodeDtlId<s:property value="%{#unitIndexForJsp}"/>' style="display: none;">
										<div class="div-table-listing rounded">
								</s:if>
										<s:set name="mlcStatusForJsp" value="'-'"></s:set>
										<s:set name="disabledForJsp" value="''"></s:set>
										<s:set name="disabledClassForJsp" value="'classSwitchDisabled'"></s:set>
										<s:if test="mlcFlag==2">
											<s:set name="mlcStatusForJsp" value="'Confirmed'"></s:set>
											<s:set name="disabledForJsp" value="disabled='disabled'"></s:set>
										</s:if>
										<s:elseif test="mlcFlag==1">
											<s:set name="mlcStatusForJsp" value="'Provisional'"></s:set>
										</s:elseif>
												
										<s:if test="%{#deptUnitCodeForJsp != departmentUnitCode}">
												<div class="div-table-row listHeader">
													<div class="div-table-col alignCenter width5"></div>
													<div class="div-table-col alignCenter width5"><s:text name="select"/></div>
													<div class="div-table-col alignCenter width35"><s:text name="visitingDeptUnit"/></div>
													<div class="div-table-col alignCenter width7"><s:text name="global.visit"/>&nbsp;<s:text name="number"/></div>
													<div class="div-table-col alignCenter width20"><s:text name="global.visit"/>&nbsp;<s:text name="date"/><s:text name="slash"/><s:text name="time"/></div>
													<div class="div-table-col alignCenter width13"><s:text name="mlc"/>&nbsp;<s:text name="status"/> </div>
													<div class="div-table-col alignCenter width15"><s:text name="mlc"/>&nbsp;<s:text name="number"/>&nbsp;<s:text name="ifalreadyalloted"/></div>
												</div>
												<div class="div-table-row listData">
													<div class="div-table-col alignCenter width5"></div>
													<div class="div-table-col alignCenter width5">
														<input type="radio" name="visitRadio" tabindex="1" value='<s:property value="%{#statusArrEpisodeVO.index}"/>'
															class='classVisitRadio<s:property value="%{#unitIndexForJsp+' ' #disabledClassForJsp}"/>' <s:property value="#disabledForJsp"/>
															onclick='emgOfflineMlcDtlJsObj.setMlcEpisodeChkBox(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);'/>
													</div>
													<div class="div-table-col alignCenter width35"><s:property value="department"/>/<s:property value="departmentUnit"/></div>
													<div class="div-table-col alignCenter width7"><s:property value="episodeVisitNo"/> </div>
													<div class="div-table-col alignCenter width20"><s:property value="episodeDate"/></div>
													<div class="div-table-col alignCenter width13"><s:property value="#mlcStatusForJsp"/></div>
													<div class="div-table-col alignCenter width15"><s:property value="mlcNo"/></div>
													<s:hidden name="episodeDate" value="%{episodeDate}"></s:hidden>
													<s:hidden name="epiDate" value="%{episodeDate.split(' ')[0]}"></s:hidden>
													<s:hidden name="epiTimeHr" value="%{episodeDate.split(' ')[1].split(':')[0]}"></s:hidden>
													<s:hidden name="epiTimeMin" value="%{episodeDate.split(' ')[1].split(':')[1]}"></s:hidden>
												</div>
										</s:if>
										<s:else>
											<div class="div-table-row listData">
												<div class="div-table-col alignCenter width5"></div>
												<div class="div-table-col alignCenter width5">
													<input type="radio" name="abcRadio" tabindex="1"  value='<s:property value="%{#statusArrEpisodeVO.index}"/>'
														class='classVisitRadio<s:property value="%{#unitIndexForJsp+' ' #disabledClassForJsp}"/>' <s:property value="#disabledForJsp"/>
														onclick='emgOfflineMlcDtlJsObj.setMlcEpisodeChkBox(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);'/>
												</div>
												<div class="div-table-col alignCenter width35"><s:property value="department"/>/<s:property value="departmentUnit"/></div>
												<div class="div-table-col alignCenter width7"><s:property value="episodeVisitNo"/> </div>
												<div class="div-table-col alignCenter width20"><s:property value="episodeDate"/></div>
												<div class="div-table-col alignCenter width13"><s:property value="#mlcStatusForJsp"/></div>
												<div class="div-table-col alignCenter width15"><s:property value="mlcNo"/></div>
												<s:hidden name="episodeDate" value="%{episodeDate}"></s:hidden>
												<s:hidden name="epiDate" value="%{episodeDate.split(' ')[0]}"></s:hidden>
												<s:hidden name="epiTimeHr" value="%{episodeDate.split(' ')[1].split(':')[0]}"></s:hidden>
												<s:hidden name="epiTimeMin" value="%{episodeDate.split(' ')[1].split(':')[1]}"></s:hidden>
											</div>
										</s:else>
										<s:if test="%{#deptUnitCodeForJsp != departmentUnitCode}">
											<s:set name="unitIndexForJsp" value="%{#unitIndexForJsp + 1}"></s:set>
										</s:if>
										<s:set name="deptUnitCodeForJsp" value="departmentUnitCode"></s:set>
									
							</s:iterator>
										</div>
									</div>
						<%-- </s:else> --%>
						<!-- <div class="div-table" id="divNoMlcEpisodeFoundId">
							<div class="div-table-row">
								<div class="div-table-col width100 alignCenter colorRed">No Record Found</div>
							</div>
						</div> -->
					</s:else>
					<%-- <s:if test="%{#errorMessageForJsp==null || #errorMessageForJsp==''}"> --%>  <!-- Commented by Vasu on 03.May.2018 -->
					<div id="divIncludeJsp">
						<jsp:include page="/registration/transactions/jsp/emgFillMlcDtl.jsp"></jsp:include>
						<jsp:include page="/registration/transactions/jsp/emgMlcTypeSpecifications.jsp"></jsp:include>
						<jsp:include page="/registration/transactions/jsp/emgMlcHandoverToDtl.jsp"></jsp:include>
						<jsp:include page="/registration/transactions/jsp/emgMlcBroughtByDtl.jsp"></jsp:include>
					</div>
					<%-- </s:if> --%>
				</s:if>
			</div>
			
		
			
			<div class="div-table-button" >
				<div class="div-table-row footerBar">
						<div class="div-table-col" > </div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center">
					<s:if test="%{#afetrGoForJsp!=0 && (#errorMessageForJsp==null || #errorMessageForJsp=='')}">
						<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
					</s:if>
					<s:if test="%{#flagMlcOnlineOfflineForJsp!=null && #flagMlcOnlineOfflineForJsp!=''}">
						<a href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
					</s:if>
					
					<s:if test="%{isDesk!=1}">
					<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
					</s:if>
					<s:else>
					<a href="#" class="button" id="cancelTabId" onclick="closeDeskTab();"><span class="cancel"><s:text name="cancel"/></span></a>
					</s:else>
				</div>
			</div>
			
			<div class="div-table-simple" >
				<div class="div-table-row">
					<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%">
						<s:property value="%{#errorMessageForJsp}"/>
					</div>
				</div>
				<div class="div-table-row">
					<div id='divNormalMsgId' class="div-table-col alignLeft fontError" style="width: 100%">
						<s:property value="strWarningMsg"/>
					</div>
				</div>
			</div>
			
			<div class="div-table" >
				<div class="div-table-row">
					<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%">
						<s:property value="strNormalMsg"/>
					</div>
				</div>
				
			</div>
			
			<div class="div-table" id="divPrintId" style="font-weight: normal;color: black;"></div>

			<s:hidden name="hospitalName" value="%{hospitalName}" id="hospitalNameId"></s:hidden>
			<s:hidden name="address1" value="%{address1}" id="address1Id"></s:hidden>
			<s:hidden name="address2" value="%{address2}" id="address2Id"></s:hidden>
			<s:hidden name="city" value="%{city}" id="cityId"></s:hidden>
			<s:hidden name="districtName" value="%{districtName}" id="districtNameId"></s:hidden>
			<s:hidden name="stateName" value="%{stateName}" id="stateNameId"></s:hidden>
			<s:hidden id="hiddenPrintId" name="printHtml" value="%{printDivContent}" />
			<s:hidden name="printFlag" value="%{printFlag}" id="printFlagId"></s:hidden>
			<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token/>
		</s:form>

		<script type="text/javascript"
			src="./../../registration/transactions/js/emgMlcDtl.js" /></script>
	</center>
</body>
</html>