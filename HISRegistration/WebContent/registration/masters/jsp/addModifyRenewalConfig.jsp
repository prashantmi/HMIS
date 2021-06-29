<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: Deepika Gaba
 	 Date			: Dec 2013 
 	 Modified By 	: Aadil on Jun 2014
 -->

<%@ taglib uri="/struts-tags" prefix="s"%>
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
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" src="./../registration/masters/js/addModifyRenewalConfig.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
</head>


<body onload="populateFormOnLoad()">
	<s:form action="RenewalConfig">


		<div class="wrapper rounded">
			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col title width100 "><s:text name="renewalconfig"/>&nbsp;<s:text name="global.master"/>
					</div>
				</div>
				<div class="div-table-row ">
					<div class='div-table-col' style='width: 5%'>
						<INPUT TYPE='CHECKBOX' NAME='chkCommonCode'
							onclick='showHidedivHospitalWiseCommon();'>
					</div>
					<div class="div-table-col width87 "><s:text name="global.hospital"/>&nbsp;<s:text name="wise"/>&nbsp;<s:text name="common"></s:text> </div>
					<div class="div-table-col width3">
						<img src="/HIS/hisglobal/images/avai/plus.gif" style="cursor: pointer;" onclick="createMultiRow1('none')" />
					</div>
				</div>
			</div>
			<s:set name="optionsCatList" value="#session[@registration.config.RegistrationConfig@REG_MST_OPTION_PRIMARY_CATEGORY]"></s:set>
			<div class="div-table hospWiseCommon" id="divHospWiseCommonId" >
				<s:set name="lstVoHospWiseCommonRenewalConfig" value="#session[@registration.config.RegistrationConfig@REG_MST_LIST_OF_HOPWISE_COMMON_RENEWAL_CONFIG_VO]"></s:set>
				<input type="hidden" name="sizeOfHospWiseCommonRenewalConfig" value='<s:property value="%{#lstVoHospWiseCommonRenewalConfig.size}"/>'>
				<s:iterator id="voHospWiseCommonRenewalConfig"  value="lstVoHospWiseCommonRenewalConfig" status="statusHospWiseCommonRenewalConfigVO">
					<s:hidden name="strRenewalGroup" value="%{strRenewalGroup}"></s:hidden>
					<input type="hidden" name="IndexId" value='<s:property value="%{#statusHospWiseCommonRenewalConfigVO.index}"/>'>
					<div class="div-table-row" id='divHospWiseCommonRowId<s:property value="%{#statusHospWiseCommonRenewalConfigVO.index}"/>'>
						<div class="div-table-col width10 label"><s:text name="global.category"/></div>
						<div class="div-table-col width10 control">
							<s:select name="strRenewalPatCat" id="%{'strRenewalPatCat' + #statusHospWiseCommonRenewalConfigVO.index}" value="strRenewalPatCat"
								cssClass="select90prcnt" list="optionsCatList"  
								 listKey="value" listValue="label" 
							   headerKey="-1"  headerValue="Select Value" 
							   onchange="%{'validateCategory(this,0,' + #statusHospWiseCommonRenewalConfigVO.index+');'}" />
						</div>
						<s:hidden name="strRenewalType" value="%{strRenewalType}"></s:hidden>
						<%--<div class="div-table-col width10 label">Renewal Type</div>
						 <div class="div-table-col width15 control">
							<s:select cssClass="select90prcnt"
								list="#{'0':'No Renewal','2':'Hospital-Wise For Particular Group', '3':'Department Unit-Wise Common To All', '4':'Department Unit-Wise Unit Specific From Unit Master'}"
								name="strRenewalType" value="strRenewalType"
								onchange="%{'renewalTypeDayOrMonth(this,' + #statusHospWiseCommonRenewalConfigVO.index+');'}"  />
						</div> --%>
						<div class="div-table-col width75 label" >
							<div class="div-table" id="divHospWiseCommonDayOrMonthWiseId${statusHospWiseCommonRenewalConfigVO.index}" >
								<div class="div-table-row">
									<div class="div-table-col width40">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width60 label" ><s:text name="day"/><s:text name="slash"/><s:text name="month"/>&nbsp;<s:text name="wise"/></div>
												<div class="div-table-col width40 control" >
													<s:select name="strRenewalMode" id="%{'strRenewalModeId' + #statusHospWiseCommonRenewalConfigVO.index}"
														list="#{'1':'Day-Wise','2':'Month-Wise'}" cssClass="select100prcnt"
														onchange="%{'showdivDayWise(this,' + #statusHospWiseCommonRenewalConfigVO.index+');'}"
														value="strRenewalMode" />
												</div>
											</div>
										</div>
									</div>
									<div class="div-table-col width60" id="divHospWiseCommonDayWiseId${statusHospWiseCommonRenewalConfigVO.index}" style="display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width65 label" ><s:text name="noofdays"/>  </div>
												<div class="div-table-col width35 control" >
													<input type="text" name="strNoOfDays" class="input70prcnt" value='<s:property value="strNoOfDays"/>' maxlength="3" onkeypress="return validateNumericOnly(this,event);">
												</div>
											</div>
										</div>
									</div>
									<div class="div-table-col width60" id="divHospWiseCommonMonthWiseId${statusHospWiseCommonRenewalConfigVO.index}" style="display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width50" ><s:text name="global.is"/>&nbsp;<s:text name="multiple"/>&nbsp;<s:text name="month"/> </div>
												<div class="div-table-col width50" >
													<s:select cssClass="select90prcnt" id="%{'strIsMultipleMonth'+ #statusHospWiseCommonRenewalConfigVO.index}"
															headerValue="Is Multiple Month"
															list="#{'':'Select Value','0':'No', '1':'Bi-Annually', '2':'Tri-Annually'}"
															name="strIsMultipleMonth" value="strIsMultipleMonth"
															onchange="%{'showDvMultipleMonth(this,' + #statusHospWiseCommonRenewalConfigVO.index+');'}" />
												</div>
											</div>
											<s:set name="ismultiplemonthgen" value="strIsMultipleMonth"></s:set>
											<s:set name="strDay1ForJsp" value="''"></s:set>
											<s:set name="strMonth1ForJsp" value="''"></s:set>
											<s:set name="strDay2ForJsp" value="''"></s:set>
											<s:set name="strMonth2ForJsp" value="''"></s:set>
											<s:set name="strDay3ForJsp" value="''"></s:set>
											<s:set name="strMonth3ForJsp" value="''"></s:set>
											
											<s:if test="%{#ismultiplemonthgen == 0 || #ismultiplemonthgen==1 || #ismultiplemonthgen==2}">
												<s:set name="strDay1ForJsp" value="strMonth1.split('-')[0]"></s:set>
												<s:set name="strMonth1ForJsp" value="strMonth1.split('-')[1]"></s:set>
											</s:if>
											<s:if test="%{#ismultiplemonthgen==1 || #ismultiplemonthgen==2}">
												<s:set name="strDay2ForJsp" value="strMonth2.split('-')[0]"></s:set>
												<s:set name="strMonth2ForJsp" value="strMonth2.split('-')[1]"></s:set>
											</s:if>
											<s:if test="%{#ismultiplemonthgen==2}">
												<s:set name="strDay3ForJsp" value="strMonth3.split('-')[0]"></s:set>
												<s:set name="strMonth3ForJsp" value="strMonth3.split('-')[1]"></s:set>
											</s:if>
											
											
											<div class="div-table-row" id="divMultipleNo${statusHospWiseCommonRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay1" cssClass="input90prcnt" value="%{#strDay1ForJsp}" maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth1" value="%{#strMonth1ForJsp}" />
												</div>
											</div>
											<div class="div-table-row" id="divMultipleBi${statusHospWiseCommonRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay2" cssClass="input90prcnt" value="%{#strDay2ForJsp}" maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth2" value="%{#strMonth2ForJsp}" />
												</div>
											</div>
											<div class="div-table-row" id="divMultipleTri${statusHospWiseCommonRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay3" cssClass="input90prcnt" value="%{#strDay3ForJsp}"  maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth3" value="%{#strMonth3ForJsp}" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
						
						
						<div class="div-table-col width5">
							<img src='/HIS/hisglobal/images/avai/minus.gif' style='cursor: pointer;' onclick='DeleteRows("divHospWiseCommonRowId${statusHospWiseCommonRenewalConfigVO.index}")'>
						</div>
					</div>
				</s:iterator>
			</div>
			
			<!--  ------------------------------    General   ----------------------------------- -->
			<div class="div-table notHospWiseCommon" id="divNotHospWiseCommonGenId" >
				<div class="div-table-row title ">
					<div class="div-table-col  width95"><s:text name="general"/>&nbsp;<s:text name="renewal"/></div>
					<div class="div-table-col width5">
						<img src="/HIS/hisglobal/images/avai/plus.gif" style="cursor: pointer;" onclick="createMultiRow1('Gen')" />
					</div>
				</div>
				<s:set name="lstVoGenRenewalConfig" value="#session[@registration.config.RegistrationConfig@REG_MST_LIST_OF_GEN_RENEWAL_CONFIG_VO]"></s:set>
				<input type="hidden" name="sizeOfNotHospWiseCommonRenewalConfigGen" value='<s:property value="%{#lstVoGenRenewalConfig.size}"/>'>
				<s:iterator id="voGenRenewalConfig"  value="lstVoGenRenewalConfig" status="statusGenRenewalConfigVO">
					<div class="div-table-row" id='divNotHospWiseCommonGenRowId<s:property value="%{#statusGenRenewalConfigVO.index}"/>'>
						<s:hidden name="strRenewalGroupGen" value="%{strRenewalGroup}"></s:hidden>
						<input type="hidden" name="GenIndexId" value='<s:property value="%{#statusGenRenewalConfigVO.index}"/>'>
						<div class="div-table-col width10 label"><s:text name="global.category"/></div>
						<div class="div-table-col width10 control">
							
							<s:set name="optionsCatList" value="#session[@registration.config.RegistrationConfig@REG_MST_OPTION_PRIMARY_CATEGORY]"></s:set>
							<s:select name="strRenewalPatCatGen" id="%{'strRenewalPatCatGen' + #statusGenRenewalConfigVO.index}" value="strRenewalPatCat"
								cssClass="select90prcnt" list="optionsCatList"  
								 listKey="value" listValue="label" 
							   headerKey="-1"  headerValue="Select Value"
							   onchange="%{'validateCategory(this,1,' + #statusGenRenewalConfigVO.index+');'}" />
						</div>
						<div class="div-table-col width10 label"><s:text name="renewal"/>&nbsp;<s:text name="global.type"/></div>
						<div class="div-table-col width15 control">
							<s:select cssClass="select90prcnt"
								list="#{'0':'No Renewal','2':'Hospital-Wise For Particular Group', '3':'Department Unit-Wise Common To All', '4':'Department Unit-Wise Unit Specific From Unit Master'}"
								name="strRenewalTypeGen" value="strRenewalType"
								onchange="%{'renewalTypeDayOrMonthGen(this,' + #statusGenRenewalConfigVO.index+');'}"  />
						</div>
						<div class="div-table-col width50 label" >
							<div class="div-table" id="divNotHospWiseCommonGenDayOrMonthWiseId${statusGenRenewalConfigVO.index}" >
								<div class="div-table-row">
									<div class="div-table-col width40">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width60 label" ><s:text name="day"/><s:text name="slash"/><s:text name="month"/>&nbsp;<s:text name="wise"/></div>
												<div class="div-table-col width40 control" >
													<s:select name="strRenewalModeGen" id="%{'strRenewalModeGenId' + #statusGenRenewalConfigVO.index}"
														list="#{'1':'Day-Wise','2':'Month-Wise'}" cssClass="select100prcnt"
														onchange="%{'showdivDayWiseGen(this,' + #statusGenRenewalConfigVO.index+');'}"
														value="strRenewalMode" />
												</div>
											</div>
										</div>
										
									</div>
									<div class="div-table-col width60" id="divNotHospWiseCommonGenDayWiseId${statusGenRenewalConfigVO.index}" style="display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width65 label" ><s:text name="noofdays"/></div>
												<div class="div-table-col width35 control" >
													<input type="text" name="strNoOfDaysGen" class="input70prcnt" value='<s:property value="strNoOfDays"/>' maxlength="3" onkeypress="return validateNumericOnly(this,event);">
												</div>
											</div>
										</div>
									</div>
									<div class="div-table-col width60" id="divNotHospWiseCommonGenMonthWiseId${statusGenRenewalConfigVO.index}" style="display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width50" ><s:text name="global.is"/>&nbsp;<s:text name="multiple"/>&nbsp;<s:text name="month"/></div>
												<div class="div-table-col width50" >
													<s:select cssClass="select90prcnt" id="%{'strIsMultipleMonthGen'+ #statusGenRenewalConfigVO.index}"
															headerValue="Is Multiple Month"
															list="#{'':'Select Value','0':'No', '1':'Bi-Annually', '2':'Tri-Annually'}"
															name="strIsMultipleMonthGen" value="strIsMultipleMonth"
															onchange="%{'showDvMultipleMonthGen(this,' + #statusGenRenewalConfigVO.index+');'}" />
												</div>
											</div>
											<s:set name="ismultiplemonthgen" value="strIsMultipleMonth"></s:set>
											<s:set name="strDay1GenForJsp" value="''"></s:set>
											<s:set name="strMonth1GenForJsp" value="''"></s:set>
											<s:set name="strDay2GenForJsp" value="''"></s:set>
											<s:set name="strMonth2GenForJsp" value="''"></s:set>
											<s:set name="strDay3GenForJsp" value="''"></s:set>
											<s:set name="strMonth3GenForJsp" value="''"></s:set>
											
											<s:if test="%{#ismultiplemonthgen == 0 || #ismultiplemonthgen==1 || #ismultiplemonthgen==2}">
												<s:set name="strDay1GenForJsp" value="strMonth1.split('-')[0]"></s:set>
												<s:set name="strMonth1GenForJsp" value="strMonth1.split('-')[1]"></s:set>
											</s:if>
											<s:if test="%{#ismultiplemonthgen==1 || #ismultiplemonthgen==2}">
												<s:set name="strDay2GenForJsp" value="strMonth2.split('-')[0]"></s:set>
												<s:set name="strMonth2GenForJsp" value="strMonth2.split('-')[1]"></s:set>
											</s:if>
											<s:if test="%{#ismultiplemonthgen==2}">
												<s:set name="strDay3GenForJsp" value="strMonth3.split('-')[0]"></s:set>
												<s:set name="strMonth3GenForJsp" value="strMonth3.split('-')[1]"></s:set>
											</s:if>
											
											
											<div class="div-table-row" id="divMultipleNoGen${statusGenRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay1Gen" cssClass="input90prcnt" value="%{#strDay1GenForJsp}" maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth1Gen" value="%{#strMonth1GenForJsp}" />
												</div>
											</div>
											<div class="div-table-row" id="divMultipleBiGen${statusGenRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay2Gen" cssClass="input90prcnt" value="%{#strDay2GenForJsp}" maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth2Gen" value="%{#strMonth2GenForJsp}" />
												</div>
											</div>
											<div class="div-table-row" id="divMultipleTriGen${statusGenRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay3Gen" cssClass="input90prcnt" value="%{#strDay3GenForJsp}"  maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth3Gen" value="%{#strMonth3GenForJsp}" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
						
						
						<div class="div-table-col width5">
							<img src='/HIS/hisglobal/images/avai/minus.gif' style='cursor: pointer;' onclick='DeleteRows("divNotHospWiseCommonGenRowId${statusGenRenewalConfigVO.index}")'>
						</div>
					</div>
				</s:iterator>
			</div>

			<!--  ------------------------------    Emergency   ----------------------------------- -->
			<div class="div-table notHospWiseCommon" id="divNotHospWiseCommonEmgId" >
				<div class="div-table-row title ">
					<div class="div-table-col  width95"><s:text name="emergency"/>&nbsp;<s:text name="renewal"/></div>
					<div class="div-table-col width5">
						<img src="/HIS/hisglobal/images/avai/plus.gif" style="cursor: pointer;" onclick="createMultiRow1('Emg')" />
					</div>
				</div>
				<s:set name="lstVoEmgRenewalConfig" value="#session[@registration.config.RegistrationConfig@REG_MST_LIST_OF_EMG_RENEWAL_CONFIG_VO]"></s:set>
				<input type="hidden" name="sizeOfNotHospWiseCommonRenewalConfigEmg" value='<s:property value="%{#lstVoEmgRenewalConfig.size}"/>'>
				<s:iterator id="voEmgRenewalConfig"  value="lstVoEmgRenewalConfig" status="statusEmgRenewalConfigVO">
					<div class="div-table-row" id='divNotHospWiseCommonEmgRowId<s:property value="%{#statusEmgRenewalConfigVO.index}"/>'>
						<s:hidden name="strRenewalGroupEmg" value="%{strRenewalGroup}"></s:hidden>
						<input type="hidden" name="EmgIndexId" value='<s:property value="%{#statusEmgRenewalConfigVO.index}"/>'>
						<div class="div-table-col width10 label"><s:text name="global.category"/></div>
						<div class="div-table-col width10 control">
							
							<s:set name="optionsCatList" value="#session[@registration.config.RegistrationConfig@REG_MST_OPTION_PRIMARY_CATEGORY]"></s:set>
							<s:select name="strRenewalPatCatEmg" id="%{'strRenewalPatCatEmg' + #statusEmgRenewalConfigVO.index}" value="strRenewalPatCat"
								cssClass="select90prcnt" list="optionsCatList"  
								 listKey="value" listValue="label" 
							   headerKey="-1"  headerValue="Select Value" 
							   onchange="%{'validateCategory(this,2,' + #statusEmgRenewalConfigVO.index+');'}" />
						</div>
						<div class="div-table-col width10 label"><s:text name="renewal"/>&nbsp;<s:text name="global.type"/></div>
						<div class="div-table-col width15 control">
							<s:select cssClass="select90prcnt"
								list="#{'0':'No Renewal','2':'Hospital-Wise For Particular Group', '3':'Department Unit-Wise Common To All', '4':'Department Unit-Wise Unit Specific From Unit Master'}"
								name="strRenewalTypeEmg" value="strRenewalType"
								onchange="%{'renewalTypeDayOrMonthEmg(this,' + #statusEmgRenewalConfigVO.index+');'}"  />
						</div>
						<div class="div-table-col width50 label" >
							<div class="div-table" id="divNotHospWiseCommonEmgDayOrMonthWiseId${statusEmgRenewalConfigVO.index}" style="display: none;">
								<div class="div-table-row">
									<div class="div-table-col width40">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width60 label" ><s:text name="day"/><s:text name="slash"/><s:text name="month"/>&nbsp;<s:text name="wise"/></div>
												<div class="div-table-col width40 control" >
													<s:select name="strRenewalModeEmg" id="%{'strRenewalModeEmgId' + #statusEmgRenewalConfigVO.index}"
														list="#{'1':'Day-Wise','2':'Month-Wise'}" cssClass="select100prcnt"
														onchange="%{'showdivDayWiseEmg(this,' + #statusEmgRenewalConfigVO.index+');'}"
														value="strRenewalMode" />
												</div>
											</div>
										</div>
									</div>
									<div class="div-table-col width60" id="divNotHospWiseCommonEmgDayWiseId${statusEmgRenewalConfigVO.index}" style="display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width65 label" ><s:text name="noofdays"/>  </div>
												<div class="div-table-col width35 control" >
													<input type="text" name="strNoOfDaysEmg" class="input70prcnt" value='<s:property value="strNoOfDays"/>' maxlength="3" onkeypress="return validateNumericOnly(this,event);">
												</div>
											</div>
										</div>
									</div>
									<div class="div-table-col width60" id="divNotHospWiseCommonEmgMonthWiseId${statusEmgRenewalConfigVO.index}" style="display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width50" ><s:text name="global.is"/>&nbsp;<s:text name="multiple"/>&nbsp;<s:text name="month"/></div>
												<div class="div-table-col width50" >
													<s:select cssClass="select90prcnt" id="%{'strIsMultipleMonthEmg'+ #statusEmgRenewalConfigVO.index}"
															headerValue="Is Multiple Month"
															list="#{'':'Select Value','0':'No', '1':'Bi-Annually', '2':'Tri-Annually'}"
															name="strIsMultipleMonthEmg" value="strIsMultipleMonth"
															onchange="%{'showDvMultipleMonthEmg(this,' + #statusEmgRenewalConfigVO.index+');'}" />
												</div>
											</div>
											<s:set name="ismultiplemonthgen" value="strIsMultipleMonth"></s:set>
											<s:set name="strDay1EmgForJsp" value="''"></s:set>
											<s:set name="strMonth1EmgForJsp" value="''"></s:set>
											<s:set name="strDay2EmgForJsp" value="''"></s:set>
											<s:set name="strMonth2EmgForJsp" value="''"></s:set>
											<s:set name="strDay3EmgForJsp" value="''"></s:set>
											<s:set name="strMonth3EmgForJsp" value="''"></s:set>
											
											<s:if test="%{#ismultiplemonthgen == 0 || #ismultiplemonthgen==1 || #ismultiplemonthgen==2}">
												<s:set name="strDay1EmgForJsp" value="strMonth1.split('-')[0]"></s:set>
												<s:set name="strMonth1EmgForJsp" value="strMonth1.split('-')[1]"></s:set>
											</s:if>
											<s:if test="%{#ismultiplemonthgen==1 || #ismultiplemonthgen==2}">
												<s:set name="strDay2EmgForJsp" value="strMonth2.split('-')[0]"></s:set>
												<s:set name="strMonth2EmgForJsp" value="strMonth2.split('-')[1]"></s:set>
											</s:if>
											<s:if test="%{#ismultiplemonthgen==2}">
												<s:set name="strDay3EmgForJsp" value="strMonth3.split('-')[0]"></s:set>
												<s:set name="strMonth3EmgForJsp" value="strMonth3.split('-')[1]"></s:set>
											</s:if>
											
											
											<div class="div-table-row" id="divMultipleNoEmg${statusEmgRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay1Emg" cssClass="input90prcnt" value="%{#strDay1EmgForJsp}" maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth1Emg" value="%{#strMonth1EmgForJsp}" />
												</div>
											</div>
											<div class="div-table-row" id="divMultipleBiEmg${statusEmgRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay2Emg" cssClass="input90prcnt" value="%{#strDay2EmgForJsp}" maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth2Emg" value="%{#strMonth2EmgForJsp}" />
												</div>
											</div>
											<div class="div-table-row" id="divMultipleTriEmg${statusEmgRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay3Emg" cssClass="input90prcnt" value="%{#strDay3EmgForJsp}"  maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth3Emg" value="%{#strMonth3EmgForJsp}" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
						
						
						<div class="div-table-col width5">
							<img src='/HIS/hisglobal/images/avai/minus.gif' style='cursor: pointer;' onclick='DeleteRows("divNotHospWiseCommonEmgRowId${statusEmgRenewalConfigVO.index}")'>
						</div>
					</div>
				</s:iterator>
			</div>
				
			<!--  ------------------------------    Special   ----------------------------------- -->
			<div class="div-table notHospWiseCommon" id="divNotHospWiseCommonSplId" >
				<div class="div-table-row title ">
					<div class="div-table-col  width95"><s:text name="special"/>&nbsp;<s:text name="renewal"/> </div>
					<div class="div-table-col width5">
						<img src="/HIS/hisglobal/images/avai/plus.gif" style="cursor: pointer;" onclick="createMultiRow1('Spl')" />
					</div>
				</div>
				<s:set name="lstVoSplRenewalConfig" value="#session[@registration.config.RegistrationConfig@REG_MST_LIST_OF_SPL_RENEWAL_CONFIG_VO]"></s:set>
				<input type="hidden" name="sizeOfNotHospWiseCommonRenewalConfigSpl" value='<s:property value="%{#lstVoSplRenewalConfig.size}"/>'>
				<s:iterator id="voSplRenewalConfig"  value="lstVoSplRenewalConfig" status="statusSplRenewalConfigVO">
					<div class="div-table-row" id='divNotHospWiseCommonSplRowId<s:property value="%{#statusSplRenewalConfigVO.index}"/>'>
						<s:hidden name="strRenewalGroupSpl" value="%{strRenewalGroup}"></s:hidden>
						<input type="hidden" name="SplIndexId" value='<s:property value="%{#statusSplRenewalConfigVO.index}"/>'>
						<div class="div-table-col width10 label"><s:text name="global.category"/></div>
						<div class="div-table-col width10 control">
							
							<s:select name="strRenewalPatCatSpl" id="%{'strRenewalPatCatSpl' + #statusSplRenewalConfigVO.index}" value="strRenewalPatCat"
								cssClass="select90prcnt" list="optionsCatList"  
								 listKey="value" listValue="label" 
							   headerKey="-1"  headerValue="Select Value" 
							   onchange="%{'validateCategory(this,3,' + #statusSplRenewalConfigVO.index+');'}" />
						</div>
						<div class="div-table-col width10 label"><s:text name="renewal"/>&nbsp;<s:text name="global.type"/> </div>
						<div class="div-table-col width15 control">
							<s:select cssClass="select90prcnt"
								list="#{'0':'No Renewal','2':'Hospital-Wise For Particular Group', '3':'Department Unit-Wise Common To All', '4':'Department Unit-Wise Unit Specific From Unit Master'}"
								name="strRenewalTypeSpl" value="strRenewalType"
								onchange="%{'renewalTypeDayOrMonthSpl(this,' + #statusSplRenewalConfigVO.index+');'}"  />
						</div>
						<div class="div-table-col width50 label" >
							<div class="div-table" id="divNotHospWiseCommonSplDayOrMonthWiseId${statusSplRenewalConfigVO.index}" style="display: none;">
								<div class="div-table-row">
									<div class="div-table-col width40">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width60 label" ><s:text name="day"/><s:text name="slash"/><s:text name="month"/>&nbsp;<s:text name="wise"/></div>
												<div class="div-table-col width40 control" >
													<s:select name="strRenewalModeSpl" id="%{'strRenewalModeSplId' + #statusSplRenewalConfigVO.index}"
														list="#{'1':'Day-Wise','2':'Month-Wise'}" cssClass="select100prcnt"
														onchange="%{'showdivDayWiseSpl(this,' + #statusSplRenewalConfigVO.index+');'}"
														value="strRenewalMode" />
												</div>
											</div>
										</div>
									</div>
									<div class="div-table-col width60" id="divNotHospWiseCommonSplDayWiseId${statusSplRenewalConfigVO.index}" style="display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width65 label" ><s:text name="noofdays"/>  </div>
												<div class="div-table-col width35 control" >
													<input type="text" name="strNoOfDaysSpl" class="input70prcnt" value='<s:property value="strNoOfDays"/>' maxlength="3" onkeypress="return validateNumericOnly(this,event);">
												</div>
											</div>
										</div>
									</div>
									<div class="div-table-col width60" id="divNotHospWiseCommonSplMonthWiseId${statusSplRenewalConfigVO.index}" style="display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col width50" ><s:text name="global.is"/>&nbsp;<s:text name="multiple"/>&nbsp;<s:text name="month"/></div>
												<div class="div-table-col width50" >
													<s:select cssClass="select90prcnt" id="%{'strIsMultipleMonthSpl'+ #statusSplRenewalConfigVO.index}"
															headerValue="Is Multiple Month"
															list="#{'':'Select Value','0':'No', '1':'Bi-Annually', '2':'Tri-Annually'}"
															name="strIsMultipleMonthSpl" value="strIsMultipleMonth"
															onchange="%{'showDvMultipleMonthSpl(this,' + #statusSplRenewalConfigVO.index+');'}" />
												</div>
											</div>
											<s:set name="ismultiplemonthgen" value="strIsMultipleMonth"></s:set>
											<s:set name="strDay1SplForJsp" value="''"></s:set>
											<s:set name="strMonth1SplForJsp" value="''"></s:set>
											<s:set name="strDay2SplForJsp" value="''"></s:set>
											<s:set name="strMonth2SplForJsp" value="''"></s:set>
											<s:set name="strDay3SplForJsp" value="''"></s:set>
											<s:set name="strMonth3SplForJsp" value="''"></s:set>
											
											<s:if test="%{#ismultiplemonthgen == 0 || #ismultiplemonthgen==1 || #ismultiplemonthgen==2}">
												<s:set name="strDay1SplForJsp" value="strMonth1.split('-')[0]"></s:set>
												<s:set name="strMonth1SplForJsp" value="strMonth1.split('-')[1]"></s:set>
											</s:if>
											<s:if test="%{#ismultiplemonthgen==1 || #ismultiplemonthgen==2}">
												<s:set name="strDay2SplForJsp" value="strMonth2.split('-')[0]"></s:set>
												<s:set name="strMonth2SplForJsp" value="strMonth2.split('-')[1]"></s:set>
											</s:if>
											<s:if test="%{#ismultiplemonthgen==2}">
												<s:set name="strDay3SplForJsp" value="strMonth3.split('-')[0]"></s:set>
												<s:set name="strMonth3SplForJsp" value="strMonth3.split('-')[1]"></s:set>
											</s:if>
											
											
											<div class="div-table-row" id="divMultipleNoSpl${statusSplRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay1Spl" cssClass="input90prcnt" value="%{#strDay1SplForJsp}" maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth1Spl" value="%{#strMonth1SplForJsp}" />
												</div>
											</div>
											<div class="div-table-row" id="divMultipleBiSpl${statusSplRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay2Spl" cssClass="input90prcnt" value="%{#strDay2SplForJsp}" maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth2Spl" value="%{#strMonth2SplForJsp}" />
												</div>
											</div>
											<div class="div-table-row" id="divMultipleTriSpl${statusSplRenewalConfigVO.index}" style="display: none;">
												<div class="div-table-col width35 label" ><s:text name="dd"/></div>
												<div class="div-table-col width15 control" >
													<s:textfield name="strDay3Spl" cssClass="input90prcnt" value="%{#strDay3SplForJsp}"  maxlength="2" onkeypress="return validateNumericOnly(this,event);"></s:textfield>
												</div>
												<div class="div-table-col width15 label" ><s:text name="mm"/></div>
												<div class="div-table-col width35 control" >
													<s:select cssClass="select100prcnt"
														list="#{'':'Select Value','Jan':'Jan', 'Feb':'Feb', 'Mar':'Mar', 'Apr':'Apr','May':'May', 'Jun':'Jun', 'Jul':'Jul', 'Aug':'Aug', 'Sep':'Sep', 'Oct':'Oct', 'Nov':'Nov', 'Dec':'Dec'}"
														name="strMonth3Spl" value="%{#strMonth3SplForJsp}" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
						
						
						<div class="div-table-col width5">
							<img src='/HIS/hisglobal/images/avai/minus.gif' style='cursor: pointer;' onclick='DeleteRows("divNotHospWiseCommonSplRowId${statusSplRenewalConfigVO.index}")'>
						</div>
					</div>
				</s:iterator>
				
			</div>
			
			<div class="div-table-button">
			<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
				</div>
				
				<div class="div-table-row" align='center'>
					<a href="#" class="button"
						onclick="return submitSaveAction('RenewalConfig');"><span
						class="save"><s:text name="save"/></span></a> 
						<%-- <a href="#" class="button"
						onclick="return submitCancelAction('RenewalConfig');"><span
						class="cancel"><s:text name="cancel"/></span></a> --%><!--By Mukund on 05.12.2016  -->
						<a href="#" class="button" onclick="window.parent.closeTab('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel">Cancel</span></a>
				</div>
			</div>

			<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
			<s:hidden name="strRenewalTypeCommonToAll" value="%{strRenewalTypeCommonToAll}"></s:hidden>
			<s:hidden name="temp"></s:hidden>
			
			<div id="hiddenDivPatCatOptionsId" style="display: none;">
				<s:select	name="strPatCatOptions" id="strPatCatOptionsId" 
							list="optionsCatList" listKey="value" listValue="label" 
					   headerKey="-1"  headerValue="Select Value" />
			</div>
		</div>

<cmbPers:cmbPers></cmbPers:cmbPers>	
<s:token></s:token>
	</s:form>
	<%System.out.println("dsfsdfsf"+request.getParameter("message")); %>
		<s:property value="message" />
	<h3>
	</h3>
</body>
</html>