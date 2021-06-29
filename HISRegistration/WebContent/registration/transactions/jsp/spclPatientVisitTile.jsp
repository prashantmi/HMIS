<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html>
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
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" type="text/css" href="dateinput.css"/>
<link href="/HIS/hisglobal/css/dateinput_small_skin1.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/customToolTip/css/writeOnToolTip.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/customToolTip/js/writeOnToolTip.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<style type="text/css">

@media print { 
		#nonprintableDiv1 
		{
		 display: none; 
		}
		#nonprintableDiv2 
		{
		 display: none; 
		}
		#nonprintableDiv3 
		{
		 display: none; 
		}
		#nonprintableDiv4 
		{
		 display: none; 
		}
		
}

.border .div-table-col{
border: 1px solid black;
}
</style>
<title>Special Patient Visit</title>
<s:head />


</head>
<script>
	 
	var CHILD_DEPT_CODE						=	"123,152,153";
	var IS_REFERRED_TRUE					=	<%= RegistrationConfig.IS_REFERRED_TRUE %>
	var IS_REFERRED_FALSE					=	<%= RegistrationConfig.IS_REFERRED_FALSE %>
	var IS_ASSOCIATED_FALSE					=	<%= RegistrationConfig.IS_ASSOCIATED_FALSE %>
	var IS_ASSOCIATED_TRUE					=	<%= RegistrationConfig.IS_ASSOCIATED_TRUE %>
	var IS_PAT_REFER_BY_LIST_TRUE			=	<%= RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE %>
	var MAX_AGE_TO_REGISTER_IN_CHILD_DEPT	=	"14";
	var IS_REFERRED_IN_OUT_ACCEPT_INTERNAL	=	<%= RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL %>
	var IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL	=	<%= RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL %>
	var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>";
	
	
</script>
<body>
	<s:form action="SpclPatientVisit">
		<%-- <s:set name="theme" value="simple" scope="page" /> --%>
		<div class="wrapper rounded" id="nonprintableDiv1">

			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col title width100"><s:text name="special"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.visit"/>  </div>
				</div>
			</div>

			<his:InputCrNoTag />
			
			
			
			<s:hidden name="afterGo" id="afterGoId" value="%{afterGo}" />
			<s:hidden name="modeCase" value="%{modeCase}"></s:hidden>
			<s:hidden name="isPatReferByList" value="%{isPatReferByList}" />
			<s:hidden name="strRenewalType" value="%{strRenewalType}" />
			<s:hidden name="splRenewalRequired" value="%{splRenewalRequired}" />
			<s:hidden name="patAmountHospitalWise" value="%{patAmountHospitalWise}" />
			<s:hidden name="patAmountDeptWise" value="%{patAmountDeptWise}" />
			<s:hidden name="patRenewalAmountDeptWise" value="%{patRenewalAmountDeptWise}" />
			
			<s:hidden name="print" id="printId" value="%{print}" />
			<s:hidden name="goWithoutDeptVisitOn" value="%{goWithoutDeptVisitOn}" />
			<s:hidden name="errorMessage" value="%{errorMessage}"/>
			<s:hidden name="patPrimaryCatGrpCode" value="%{patPrimaryCatGrpCode}" id="patPrimaryCatGrpCodeId"/>
			
			<s:set name="afetrGoForJsp" value="afterGo"></s:set>
			<s:set name="modeCaseForJsp" value="modeCase"></s:set>
			<s:set name="renewalTypeForJsp" value="strRenewalType"></s:set>
			<s:set name="spclRenewalRequiredForJsp" value="splRenewalRequired"></s:set>
			<s:set name="patAmountHospitalWiseForJsp" value="patAmountHospitalWise"></s:set>
			<s:set name="patAmountDeptWiseForJsp" value="patAmountDeptWise"></s:set>
			<s:set name="patRenewalAmountDeptWiseForJsp" value="patRenewalAmountDeptWise"></s:set>
			<s:set name="isPatReferByListForJsp" value="isPatReferByList"></s:set>
			<s:set name="printForJsp" value="print"></s:set>
			<s:set name="goWithoutDeptVisitOnForJsp" value="goWithoutDeptVisitOn"></s:set>
			<s:set name="errorMessageForJsp" value="errorMessage"></s:set>
			<s:set name="patPrimaryCatGrpCodeForJsp" value="patPrimaryCatGrpCode"></s:set>
			<s:hidden name="sysdate" value="%{sysdate}" id="sysdateId"/>
			<%-- <s:include value="/registration/transactions/jsp/patientDetailsTile.jsp"/> --%>
			
			<!-- For Old Start -->
			<s:set name="voOldPatRefer" value="#session[@registration.config.RegistrationConfig@ARR_EPISODE_OLD_SPECIAL_PAT_REFER_VO]"></s:set>
			<s:if test="%{#afetrGoForJsp==0 && #voOldPatRefer!= null && #voOldPatRefer.length>0}">
				
				<div class="div-table-listing rounded">
					<div class="div-table-row ">
						<div class="div-table-col title width100 "><s:text name="oldreferredpatlist"/></div>
					</div>
					<div class="div-table-row listHeader">
						<div class="div-table-col alignCenter" style="width: 4%;">Select</div>
						<div class="div-table-col alignCenter" style="width: 16%;">Cr No</div>
						<div class="div-table-col alignCenter" style="width: 16%;">Name</div>
						<div class="div-table-col alignCenter" style="width: 16%;">From Department</div>
						<div class="div-table-col alignCenter" style="width: 16%;">From Unit</div>
						<div class="div-table-col alignCenter" style="width: 16%;">Referred To Dept</div>
						<div class="div-table-col alignCenter" style="width: 16%;">Refer Date</div>
					</div>
					<s:iterator id="voOldPatReferId"  value="voOldPatRefer" status="statusOldRefrVO">
						<div class="div-table-row listData">
							<div class="div-table-col alignCenter" style="width: 4%;">
								<s:set name="strSelectedOldReferCrNo" value="%{#statusOldRefrVO.index + '#O$' + patCrNo}"></s:set>
								<input type="radio" name="selectedRefCrNo" value='<s:property value="strSelectedOldReferCrNo" />' onclick="spclPatientVisitJsObj.showDetail(this)" /> 
								<%-- <s:hidden name="indexID" value="%{indexID}"/> --%>
							</div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="patCrNo"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="patName"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="fromDepartment"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="fromDepartmentUnit"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="toDepartment"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="reffDateTime"/> </div>
						</div>
					
					</s:iterator>
				</div>
			</s:if>
			<!-- For Old End -->
			
			<!-- For New Start -->
			<s:set name="voPatRefer" value="#session[@registration.config.RegistrationConfig@ARR_EPISODE_REFER_SPECIAL_PAT_VO]"></s:set>
			<s:if test="%{#afetrGoForJsp==0 && #voPatRefer!= null && #voPatRefer.length>0}">
				<div class="div-table-listing rounded">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">Patient Referral Detail</div>
					</div>
					<div class="div-table-row listHeader">
						<div class="div-table-col alignCenter" style="width: 4%;">Select</div>
						<div class="div-table-col alignCenter" style="width: 16%;">Cr No</div>
						<div class="div-table-col alignCenter" style="width: 16%;">Name</div>
						<div class="div-table-col alignCenter" style="width: 16%;">From Department</div>
						<div class="div-table-col alignCenter" style="width: 16%;">From Unit</div>
						<div class="div-table-col alignCenter" style="width: 16%;">Referred To Dept</div>
						<div class="div-table-col alignCenter" style="width: 16%;">Refer Date</div>
					</div>
					<s:iterator id="voPatReferId"  value="voPatRefer" status="statusRefrVO">
						<div class="div-table-row listData">
							<div class="div-table-col alignCenter" style="width: 4%;">
								<s:if test="%{deptUnitIsClosed == @registration.config.RegistrationConfig@DEPT_UNIT_IS_CLOSED_FALSE}">
									<s:if test="%{deptUnitIsOnDuty == @registration.config.RegistrationConfig@DEPT_UNIT_IS_ON_DUTY_TRUE}">
										<s:set name="strSelectedReferCrNo" value="%{#statusRefrVO.index + '#N$' + patCrNo}"></s:set>
										<input type="radio" name="selectedReffereCrNo" value='<s:property value="%{strSelectedReferCrNo}"/>' onclick="spclPatientVisitJsObj.showDetail(this)">
									</s:if>
									<s:else>
										<!-- <input type="radio" name="selectedReffereCrNo1" value="%{#statusRefrVO.index}"> -->
										<img src="../../hisglobal/images/stop.png" title="Unit not on duty">
									</s:else>
									
								</s:if>
							</div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="patCrNo"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="patName"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="fromDepartment"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="fromDepartmentUnit"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="toDepartment"/> </div>
							<div class="div-table-col alignCenter" style="width: 16%;"><s:property value="reffDateTime"/> </div>
						</div>
					
					</s:iterator>
				</div>
			</s:if>
			
			
			<s:if test="%{#afetrGoForJsp!=0}">
				<s:action name="patientDetail" executeResult="true"></s:action>
				<%-- <s:include value="/registration/transactions/jsp/patientDetailsTile.jsp"/> --%>
			
			
				<div class="div-table" >
						<%-- 'modeCaseForJsp:<s:property value="%{#modeCaseForJsp}"/>' --%>
						<div class="div-table-col width24 label">New Department Visit</div>
						<div class="div-table-col width23 control" >
							<s:if test="%{(newDepartmentVisit=='On' || newDepartmentVisit=='on')}">
								<s:if test="#isPatReferByListForJsp == @registration.config.RegistrationConfig@IS_PAT_REFER_BY_LIST_TRUE">
									<input type="checkbox" name="newDepartmentVisit" checked="checked" disabled="disabled" />
								</s:if>
								<s:elseif test="%{#modeCaseForJsp!=2}">
									<input type="checkbox" name="newDepartmentVisit" onclick="spclPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:elseif>
								<s:else>
									<input type="checkbox" name="newDepartmentVisit" checked="checked" onclick="spclPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:else>
							</s:if>
							<s:else>
								<s:if test="#isPatReferByListForJsp == @registration.config.RegistrationConfig@IS_PAT_REFER_BY_LIST_TRUE">
									<input type="checkbox" name="newDepartmentVisit" disabled="disabled" />
								</s:if>
								<s:elseif test="%{#modeCaseForJsp!=2}">
									<input type="checkbox" name="newDepartmentVisit" onclick="spclPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:elseif>
								<s:else>
									<input type="checkbox" name="newDepartmentVisit" checked="checked" onclick="spclPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:else>
							</s:else>
						</div>
						<div class="div-table-col width24 label">Old Department Visit</div>
						<div class="div-table-col width24 control" >
							<s:if test="%{oldDepartmentVisit=='On'||oldDepartmentVisit=='on'}">
								<s:if test="%{#isPatReferByListForJsp == @registration.config.RegistrationConfig@IS_PAT_REFER_BY_LIST_TRUE}">
									<input type="checkbox" name="oldDepartmentVisit" checked="checked" disabled="disabled"  />
								</s:if>
								<s:elseif test="%{#modeCaseForJsp!=2}">
									<input type="checkbox" name="oldDepartmentVisit" onclick="spclPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:elseif>
								<s:else>
									<input type="checkbox" name="oldDepartmentVisit" checked="checked" onclick="spclPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:else>
							</s:if>
							<s:else>
								<s:if test="%{#isPatReferByListForJsp == @registration.config.RegistrationConfig@IS_PAT_REFER_BY_LIST_TRUE}">
									<input type="checkbox" name="oldDepartmentVisit" disabled="disabled"  />
								</s:if>
								<s:elseif test="%{#modeCaseForJsp!=2}">
									<input type="checkbox" name="oldDepartmentVisit" onclick="spclPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:elseif>
								<s:else>
									<input type="checkbox" name="oldDepartmentVisit" checked="checked" onclick="spclPatientVisitJsObj.showHideNewAndOldVisit(this);" />
								</s:else>
							</s:else>
						</div>
						
				</div>
				
				<s:if test="%{newDepartmentVisit=='On' || newDepartmentVisit=='on' || #goWithoutDeptVisitOnForJsp==1}">
					<div class="div-table" id="divNewDeptVisitId">
						<div class="div-table-row">
							<div class="div-table-col title width100 ">New Department Visit Stamp</div>
						</div>
						<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY}">
							<s:set name="newDeptVisitWidth" value="'width10'"></s:set>
						</s:if>
						<s:else>
							<s:set name="newDeptVisitWidth" value="'width16'"></s:set>
						</s:else>
						<div class="div-table-row">
							<div class="div-table-col label <s:property value="#newDeptVisitWidth"/>">
								<font color="red" size="2">*</font>Department
							</div>
							<div class="div-table-col control <s:property value="#newDeptVisitWidth"/>">
								<s:if test="%{#isPatReferByListForJsp != @registration.config.RegistrationConfig@IS_PAT_REFER_BY_LIST_TRUE}">
									<div class="div-table">
										<div class="div-table-row">
											<div class="div-table-col width100">
												<s:set name="deptOption" value="#session[@registration.config.RegistrationConfig@SPLREGISTRATIONDESK_OPTION_DEPARTMENT]"></s:set>
												<s:if test="%{#deptOption!= null}">
													<s:select name="departmentCode" id="departmentCodeId" cssClass="select90prcnt" list="deptOption" 
															listKey="value" listValue="label" 
															headerKey="-1" headerValue="Select Value" />
												</s:if>
												<s:else>
													<select name="departmentCode" class="select90prcnt"><option value="-1">Select Value</option> </select>
												</s:else>
											</div>
											<s:hidden name="toDepartmentCode" value="%{departmentUnitCode}"/>
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="div-table">
										<div class="div-table-row">
											<div class="div-table-col width100">
												<s:property value="toDepartment"/>
											</div>
											<s:hidden name="toDepartmentCode" value="%{toDepartmentCode}"/>
										</div>
									</div>
								</s:else>
							</div>
							
							<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY}">
								<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="1">
									<div class="div-table-col <s:property value="#newDeptVisitWidth"/> label">
										<font color="red">*</font>Reference Letter No
									</div>
									<div class="div-table-col <s:property value="#newDeptVisitWidth"/> control" >
										<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" class="input90prcnt letterNo" maxlength="50">
									</div>
									<div class="div-table-col <s:property value="#newDeptVisitWidth"/> label">
										<font color="red">*</font>Letter Date
									</div>
									<div class="div-table-col <s:property value="#newDeptVisitWidth"/> control" >
										<input type="date" name="creditLetterDate" id="creditLetterDateId" value="Today" class="input60prcnt letterDate">
									</div>
							</s:if>
							<s:else>
								<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="0">
							</s:else>
								
							<div class="div-table-col label <s:property value="#newDeptVisitWidth"/>">Visit Reason</div>
							<div class="div-table-col control <s:property value="#newDeptVisitWidth"/>">
								<input type="text" name="patVisitReason" class="tooltipClass" id="patVisitReasonId" 
											maxlength="99" size="10" title="Visit Reason">
							</div>
							<div class="div-table-col label <s:property value="#newDeptVisitWidth"/>">Amount Collected</div>
							<div class="div-table-col control <s:property value="#newDeptVisitWidth"/>">
								<s:if test="%{#renewalTypeForJsp== 1 || #renewalTypeForJsp==2}">
									<s:textfield name="patAmountCollected" value="%{patAmountHospitalWiseForJsp}" size="10" readonly="true"></s:textfield>
								</s:if>
								<s:elseif test="%{#renewalTypeForJsp== 3 || #renewalTypeForJsp==4}">
									<s:textfield name="patAmountCollected" value="%{patAmountDeptWiseForJsp}" size="10" readonly="true"></s:textfield>
								</s:elseif>
								<s:else>
									<s:textfield name="patAmountCollected" value="0.00" size="10" readonly="true"></s:textfield>
								</s:else>
							</div>
						</div>
						
					</div>
				</s:if>
				
				<s:set name="reducedWidth" value="0"></s:set>
				<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY
								&& (#renewalTypeForJsp== 3 || #renewalTypeForJsp==4)}">
					<s:set name="reducedWidth" value="4"></s:set>
				</s:if>
				
				<s:if test="%{oldDepartmentVisit=='On' || oldDepartmentVisit=='on' || #goWithoutDeptVisitOnForJsp==1}">
					<div class="div-table" id="divOldDeptVisitId1">
						<div class="div-table-row">
							<div class="div-table-col title width100 ">Old Department Visit Details</div>
						</div>
					</div>
						<s:set name="voArrEpisode" value="#session[@registration.config.RegistrationConfig@SPLREGISTRATIONDESK_EPISODEVO_ARR]"></s:set>
						<s:if test="%{#voArrEpisode!= null && #voArrEpisode.length != 0}">
							<s:set name="varStatus" value="InProcess"></s:set>
							<div class="div-table-listing rounded" id="divOldDeptVisitId2">
								<div class="div-table-row listHeader">
									<div class='div-table-col alignCenter width5' >Status</div>
									<div class='div-table-col alignCenter width<s:property value="%{23- #reducedWidth}"/>'>Department</div>
									<div class='div-table-col alignCenter width<s:property value="%{27- #reducedWidth}"/>' >Unit/Room</div>
									<div class='div-table-col alignCenter width<s:property value="%{14- #reducedWidth}"/>' >Last Visit Date</div>
									<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY
												&& (#renewalTypeForJsp== 3 || #renewalTypeForJsp==4) }">
										<div class='div-table-col alignCenter width10'><font color="red">*</font>Ref. Letter No</div>
										<div class='div-table-col alignCenter width10'><font color="red">*</font>Letter Date</div>
									</s:if>
									<div class='div-table-col alignCenter width<s:property value="%{16- #reducedWidth}"/>' >Visit Reason</div>
									<div class='div-table-col alignCenter width<s:property value="%{15- #reducedWidth}"/>' >Renewal Fee</div>
								</div>
								<s:iterator id="voPatReferId"  value="voArrEpisode" status="statusArrEpisodeVO">
									<s:if test="%{isGeneral== @registration.config.RegistrationConfig@UNIT_TYPE_SPECIALITY}">
										<s:set name="backgroundColor" value="#FFC0CB"></s:set>
									</s:if>
									<s:else>
										<s:set name="backgroundColor" value=""></s:set>
									</s:else>	
									
									<div class='div-table-row listData <s:property value="backgroundColor"/>'>
										<div class="div-table-col alignCenter width5">
											
											<s:set name="fontColor" value=""></s:set>
											<s:if test="%{visitedToday == @registration.config.RegistrationConfig@DEPT_UNIT_VISITED_TODAY_TRUE}">
												<img src="../../hisglobal/images/icn-lock.png" title="Unit Already Visited Today">
											</s:if>
											<!-- end unit already visited today -->
											<s:else>
												<!--start Unit Not Visited Today and Unit Is ON Duty -->
												<%-- renewalTypeForJsp:<s:property value="renewalTypeForJsp"/>
												deptUnitIsOnDuty:<s:property value="deptUnitIsOnDuty"/>XX --%>
												<s:if test="%{deptUnitIsOnDuty == @registration.config.RegistrationConfig@DEPT_UNIT_IS_ON_DUTY_TRUE && isGeneral== @registration.config.RegistrationConfig@UNIT_TYPE_SPECIALITY}">
													<s:if test="%{#renewalTypeForJsp== 1 || #renewalTypeForJsp==2}">
														<s:if test="%{#spclRenewalRequiredForJsp == @registration.config.RegistrationConfig@RENEWAL_REQUIRED_TRUE}">
															<s:set name="fontColor" value="'colorRed'"></s:set>
															<s:hidden name="renewalRequired" value="%{spclRenewalRequiredForJsp}"/>
															<input type="checkbox" name="departmentsToVisitStamp" onclick='spclPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);' value="<s:property value="episodeCode"/>"/>
														</s:if>
														<s:else>
															<input type="checkbox" name="departmentsToVisitStamp" value="<s:property value="episodeCode"/>" onclick="spclPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);" />
														</s:else>
													</s:if>
													<s:elseif test="%{#renewalTypeForJsp== 3 || #renewalTypeForJsp==4}">
														<s:if test="%{renewalRequired == @registration.config.RegistrationConfig@RENEWAL_REQUIRED_TRUE}">
															<s:set name="fontColor" value="'colorRed'"></s:set>
															<s:hidden name="renewalRequired" value="%{renewalRequired}"/>
															<input type="checkbox" name="departmentsToVisitStamp" onclick='spclPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);' value="<s:property value="episodeCode"/>"/>
														</s:if>
														<s:else>
															<input type="checkbox" name="departmentsToVisitStamp" value="<s:property value="episodeCode"/>" onclick="spclPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);" />
														</s:else>
													</s:elseif>
													<s:else>
														<input type="checkbox" name="departmentsToVisitStamp" value="<s:property value="episodeCode"/>" onclick="spclPatientVisitJsObj.calculateGrandtotal(this,<s:property value="%{#statusArrEpisodeVO.index}"/>);" />
														<s:hidden name="renewalRequired" value="%{renewalRequired}"/>
													</s:else>
													
												</s:if>
												<!-- end unit not visited today and unit is on duty  -->
												<s:else>
													<s:if test="%{isGeneral== @registration.config.RegistrationConfig@UNIT_TYPE_SPECIALITY}">
														<img src="../../hisglobal/images/stop.png" title="Unit not on duty">
													</s:if>
												</s:else>
												<!-- End unit not on duty -->
												
											</s:else>
											<!-- End unit not visited today -->
											
										</div>
										
										<div class='div-table-col alignCenter width<s:property value="%{23- #reducedWidth}"/> <s:property value="fontColor"/>'>
											<s:property value="department"/>
										</div>
										<div class='div-table-col alignCenter width<s:property value="%{27- #reducedWidth}"/> <s:property value="fontColor"/>'>
											<s:property value="departmentUnit"/>/<s:property value="room"/>
										</div>
										<div class='div-table-col alignCenter width<s:property value="%{14- #reducedWidth}"/> <s:property value="fontColor"/>'>
											<s:property value="episodeDate"/>.
										</div>
										<s:if test="%{#patPrimaryCatGrpCodeForJsp == @registration.config.RegistrationConfig@PATIENT_REG_CATEGORY_GROUP_BENEFICIARY
															&& (#renewalTypeForJsp== 3 || #renewalTypeForJsp==4)}">
											<s:if test="%{#fontColor=='colorRed'}">
												<input type="hidden" name="arrCreditBillFlag" value="1" id='arrCreditBillFlagId<s:property value="%{#statusArrEpisodeVO.index}"/>' >
												<div class='div-table-col alignCenter width10'>
													<input type="text" name="arrCreditLetterRefNo" id="arrCreditLetterRefNoId" class="input90prcnt letterNo" maxlength="50">
												</div>
												<div class='div-table-col alignCenter width10'>
													<input type="date" name="arrCreditLetterDate" id="arrCreditLetterDate" value="Today" class="input90prcnt letterDate">
												</div>
											</s:if>
											<s:else>
												<input type="hidden" name="arrCreditBillFlag" value="0" id='arrCreditBillFlagId<s:property value="%{#statusArrEpisodeVO.index}"/>' >
												<div class='div-table-col alignCenter width10'>
													<s:property value="creditLetterRefNo"/>-
													<input type="hidden" name="arrCreditLetterRefNo" id="arrCreditLetterRefNoId" value='<s:property value="creditLetterRefNo"/>' class="input90prcnt">
												</div>
												<div class='div-table-col alignCenter width10'>
													<s:property value="creditLetterDate"/>-
													<input type="hidden" name="arrCreditLetterDate" id="arrCreditLetterDate" value='<s:property value="creditLetterDate"/>' class="input90prcnt">
												</div>
											</s:else>
											
										</s:if>
										<s:else>
											<input type="hidden" name="arrCreditBillFlag" value="0" id='arrCreditBillFlagId<s:property value="%{#statusArrEpisodeVO.index}"/>' >
										</s:else>
										
										<div class='div-table-col alignCenter width<s:property value="%{16- #reducedWidth}"/>'>
											<input type="text" name="arrPatVisitReason" class="tooltipClass" id='arrPatVisitReasonId<s:property value="%{#statusArrEpisodeVO.index}"/>' 
														maxlength="99" size="10" title="Visit Reason">
										</div>
										<div class='div-table-col alignRight width<s:property value="%{15- #reducedWidth}"/> <s:property value="fontColor"/>'>
											<s:set name="epAmount" value="0.00"></s:set>
											<s:if test="%{#fontColor=='colorRed'}">
												<s:if test="%{#renewalTypeForJsp== 1 || #renewalTypeForJsp==2}">
													<s:property value="patAmountHospitalWiseForJsp"/>
													<s:set name="epAmount" value="patAmountHospitalWiseForJsp"></s:set>
												</s:if>
												<s:elseif test="%{#renewalTypeForJsp== 3 || #renewalTypeForJsp==4}">
													<s:property value="patRenewalAmountDeptWiseForJsp"/>
													<s:set name="epAmount" value="patRenewalAmountDeptWiseForJsp"></s:set>
												</s:elseif>
												<s:else>
													0.00
													<s:set name="epAmount" value="'0.00'"></s:set>
												</s:else>
											</s:if>
											<s:else>
												0.00
											</s:else>
											<input type="hidden" name="hiddenEpisodePatAmount" value="<s:property value="%{#epAmount}"/>">
										</div>
									</div>
									
								</s:iterator>
								
								<div class="div-table-row listdata">
									<div class="div-table-col width100 alignRight">
										RENEWAL FEE:<input type="text" name="grandTotal" value="0.00" disabled="disabled">
										<s:hidden name="hcode" value="%{hcode}" />	
									</div>
								</div>
							</div>
							
						
							
						</s:if>
						<s:else>
							<div class="div-table" id="divOldDeptVisitId3">
								<div class="div-table-row">
									<div class="div-table-col width100 alignCenter colorRed">No Record Found</div>
								</div>
							</div>
						</s:else>
					
				</s:if>
						
			</s:if>
			
			<s:if test="%{#afetrGoForJsp!=0 && #isPatReferByListForJsp != @registration.config.RegistrationConfig@IS_PAT_REFER_BY_LIST_TRUE}">
				<div class="div-table" >
					<div class="div-table-row title">
						<div class="div-table-col" style="width: 80%">
								Refer Detail
						</div>
						<div class="div-table-col label" style="width: 20%">
								Is Referred<input type="checkbox" name="isReferred" value="0" onclick="spclPatientVisitJsObj.checkIsReferred(this)">
						</div>
					</div>
				</div>
				<div id="divRefDtlChkboxId" class="div-table" style="display: none;">
					<div id="divCheckReferralId" class="div-table-row" >
						<div class="div-table-col width100">&nbsp;&nbsp;&nbsp;
							Internal Referral <input type="radio" name="isRefferInOut" value="I" onclick="spclPatientVisitJsObj.showInternalExternal(this)" />&nbsp;&nbsp;
					    	External Referral <input type="radio" name="isRefferInOut" value="O" onclick="spclPatientVisitJsObj.showInternalExternal(this)" />
						</div>
					</div>
					
					<div id="divInternalReferId" style="display:none">
						<s:set name="voArrOpenEpisode" value="#session[@registration.config.RegistrationConfig@ARR_OPD_OPEN_EPISODE_VO]"></s:set>
						<s:if test="%{#afetrGoForJsp!=0 && #voArrOpenEpisode!= null}">
							<div class="div-table-listing rounded" >
								<div class="div-table-row listHeader" >
									<div class="div-table-col alignCenter" style="width: 5%">Select</div>
									<div class="div-table-col alignCenter" style="width: 45%">From Department</div>
									<div class="div-table-col alignCenter" style="width: 50%">From Unit</div>
								</div>
								<s:iterator id="voArrOpenEpisodeId"  value="voArrOpenEpisode" status="statusArrOpenEpisodeRefr">
									<s:set name="epCode" value="episodeCode"></s:set>
									<s:set name="frmDep" value="fromDepartment"></s:set>
									<s:set name="frmDepUnit" value="fromDepartmentUnit"></s:set>
									<s:set name="frmDepCode" value="fromDepartmentCode"></s:set>
									<s:set name="frmDepUnitCode" value="fromDepartmentUnitCode"></s:set>
									
									<div class="div-table-row listData">
										<div class="div-table-col alignCenter" style="width: 4%;">
											<input type="radio" name="refferringOPDEpisode" value='<s:property value="epCode"/>'>
											<s:hidden name="episodeCode" value="%{epCode}"></s:hidden>
										</div>
										<div class="div-table-col alignCenter" style="width: 45%">
											<s:property value="frmDep"/>
											<s:hidden name="frmDepCode" value="%{frmDepCode}"></s:hidden>
										</div>
										
										<div class="div-table-col alignCenter" style="width: 50%">
											<s:property value="frmDepUnit"/>
											<s:hidden name="frmDepUnitCode" value="%{frmDepUnitCode}"></s:hidden>
										</div>
									</div>
								
								</s:iterator>
							</div>
						</s:if>
					</div>
					
					
					<div id="divExternalReferalId" class="div-table-row" style="display:none">
						<div class="div-table-col width100">
							<div id="divRefDtlId" class="div-table" >
								<div class="div-table-row separatorBar ">
									<div class="div-table-col"> </div>
								</div>
								<div class="div-table-row" >
									<div id="divReferredInstitute" class="div-table-col label" style="width: 50%;">
										Associated Institute <input type="radio" name="referringInstType" value="G" onclick="spclPatientVisitJsObj.showdivhoscode(this)" checked="checked" /> &nbsp;
								    	Other <input type="radio" name="referringInstType" value="O" onclick="spclPatientVisitJsObj.showdivhoscode(this)" /> &nbsp;
									</div>
									<div class="div-table-col" style="width: 50%;">
										<div id="divRefHosCode" class="div-table" style="width: 100%;">
											<div class="div-table-row" >
												<div class="div-table-col label" style="width: 50%;"> <font color="red">*</font>
												<label for="Institute Name">Institute Name</label>	
												</div>
												<div class="div-table-col control" style="width: 50%;">
													<s:set name="optionsRefHospital" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_REF_HOSPITAL]"></s:set>
													<s:if test="%{#optionsRefHospital!= null}">
														<s:select id="patRefGnctdHospitalCodeId" name="patRefGnctdHospitalCode" 
															cssStyle="width:145px" list="optionsRefHospital"  
															 listKey="value" listValue="label" 
														   headerKey="-1"  headerValue="Select Value" />
													</s:if>
													<s:else>
														<select id="patRefGnctdHospitalCodeId" name="patRefGnctdHospitalCode" >
															<option value="-1">Select Value</option>
														</select>
													</s:else>
												</div>
											</div>
										</div>
										<div id="divRefHosname" class="div-table" style="width: 100%;">
											<div class="div-table-row">
												<div class="div-table-col label" style="width: 50%;"> 
												<font color="red">*</font><label for="Institute Name">Institute Name</label>	
												</div>
												<div class="div-table-col control" style="width: 50%;">
													<input name="patRefHospitalName" maxlength="100" type="text" size="20">
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="div-table" id="divReferred" style="display: none;">
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 25%;">Referring Institute CR No.</div>
									<div class="div-table-col control" style="width: 25%;">
										<input type	="text" name="patRefGnctdHospitalCrno" maxlength ="18"  >
									</div>
									<div class="div-table-col label" style="width: 25%;">
										<font color="red">*</font>Doctor Name
									</div>
									<div class="div-table-col control" style="width: 25%;">
										<input type="text" 	name="patRefDoctor" maxlength="60">
									</div>
								</div>
								
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 25%;">
										Referring Institute Department.
									</div>
									<div class="div-table-col control" style="width: 25%;">
										
										<s:set name="optionsRefDeptDtl" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL]"></s:set>
										<s:if test="%{#optionsRefDeptDtl!= null}">
											<s:select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDept" 
												cssStyle="width:145px" list="optionsRefDeptDtl"  
												 listKey="value" listValue="label" 
											   headerKey="-1"  headerValue="Select Value" />
										</s:if>
										<s:else>
											<select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDept" style="width :145px">
												<option value="-1">Select Value</option>
												<option value="0">Other</option>
											</select>
										</s:else>
									</div>
									
									<div id="divRefHospitalDeptOtherId" class="div-table-col" style="width: 50%; display: none;">
										<div class="div-table">
											<div class="div-table-row">
												<div class="div-table-col label" style="width: 50%;"><font color="red">*</font>Other Refd. Dept</div>
												<div class="div-table-col control" style="width: 50%;">
													<input name="patRefHospitalDeptOther" maxlength="50" type="text" size="20">
												</div>
											</div>
											
										</div>
									</div>
									
								</div>
								<div class="div-table-row">
									<div class="div-table-col label" style="width: 25%;">
										Referring Institute Unit.
									</div>
									<div class="div-table-col control" style="width: 25%;">
										<input name="patRefGnctdHospitalDeptUnit" maxlength="15" type="text" size="20" >
									</div>
									<div class="div-table-col label" style="width: 25%;">&nbsp;</div>
									<div class="div-table-col control" style="width: 25%;">&nbsp;</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
				</s:if>
				
				<s:if test="%{#afetrGoForJsp!=0 && #voArrEpisode!= null && #voArrEpisode.length > 0}">
					<div class="div-table" id="divLegendsId" style="cursor: pointer;" title="Click To Show Legends">
						<div class="div-table-row ">
							<div class="div-table-col title width100">Legends</div>
						</div>
					</div>
				</s:if>
				
				<div class="div-table-simple" id="divLegendsDtlId" style="display: none;" >
					<div class="div-table-row">
						<div class="div-table-col width10 alignCenter"><input type="checkbox"></div>
						<div class="div-table-col width90">Visit stamp allowed</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width10 alignCenter"><img src="../../hisglobal/images/icn-lock.png" /></div>
						<div class="div-table-col width90">Unit already visited today</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width10 alignCenter"><img src="../../hisglobal/images/stop.png"/></div>
						<div class="div-table-col width90">No unit working in the department</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width10 alignCenter colorRed">Red</div>
						<div class="div-table-col width90">Registration expired, renewal required for this department</div>
					</div>
				</div>
		</div>
		
	
		
		<div class="div-table-button" id="nonprintableDiv2">
			<div class="div-table-row footerBar">
					<div class="div-table-col" > </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<s:if test="%{#afetrGoForJsp!=0}">
					<%-- <s:if test="%{(oldDepartmentVisit=='On' || oldDepartmentVisit=='on') && newDepartmentVisit==''}">
						<a href="#" class="button" id="submitOldId"><span class="save">Save</span></a>
					</s:if>
					<s:elseif test="%{(newDepartmentVisit=='On' || newDepartmentVisit=='on') && oldDepartmentVisit==''}">
						<a href="#" class="button" id="submitNewId"><span class="save">Save</span></a>
					</s:elseif > --%>
					<s:if test="%{(newDepartmentVisit=='On' || newDepartmentVisit=='on') || (oldDepartmentVisit=='On' || oldDepartmentVisit=='on')}">
						<a class="button" id="submitBothId" style="cursor: pointer;"><span class="save">Save</span></a>
					</s:if>
				</s:if>
				<a href="#" class="button" id="clearId" ><span class="clear">Clear</span></a>
				<a href="#" class="button" id="cancelId" onclick="initPage();"><span class="cancel">Cancel</span></a>
			</div>
		</div>
		<div class="div-table-simple" id="nonprintableDiv3">
			<div class="div-table-row">
				<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%">
					<s:property value="%{#errorMessageForJsp}"/>
				</div>
			</div>
		</div>
		
		<div class="div-table" id="nonprintableDiv4">
			<div class="div-table-row">
				<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%"></div>
			</div>
			
		</div>
		<div class="div-table" id="divPrintId" style="font-weight: normal;color: black;">
			<%-- <s:if test="%{#printForJsp==1}">
				<s:property value="printDivContent"/>
			</s:if> --%>
		</div>

		
		<s:hidden id="hiddenPrintId" name="printHtml" value="%{printDivContent}" />
		
		<s:hidden name="patFirstName" value="%{patFirstName}" />
		<s:hidden name="patMiddleName" value="%{patMiddleName}"/>
		<s:hidden name="patLastName" value="%{patLastName}"/>
		<s:hidden name="patGender" value="%{patGender}"/>           
		<s:hidden name="patAge" value="%{patAge}"/>
		<s:hidden name="patPrimaryCatCode" value="%{patPrimaryCatCode}"/>
		<s:hidden name="patGuardianName" value="%{patGuardianName}" />
		<%-- <s:hidden name="patAmountCollected" value="%{patAmountCollected}" /> --%>
		
		<s:hidden name="selectedFromDept" value="%{selectedFromDept}" />
		<s:hidden name="patBillAmountWithoutGrouping" value="%{patBillAmountWithoutGrouping}" />
		<s:hidden name="referInternalExternal" value="%{referInternalExternal}" />
		<s:hidden name="referingRowIndex" value="%{referingRowIndex}" />
		<s:hidden name="onlineReferedIndex" value="%{onlineReferedIndex}" />
		<s:hidden name="entryDate" value="%{entryDate}" />
		<s:hidden name="episodeVisitNo" value="%{episodeVisitNo}" />
		<s:hidden name="onRequestVisit" value="%{onRequestVisit}" />
		<s:hidden name="captureMandatoryField" value="%{captureMandatoryField}" />
		<s:hidden name="selectedCheckBox" value="%{selectedCheckBox}" />
		
		<s:hidden name="saveSuccessful" value="%{saveSuccessful}" />
		<s:hidden name="isPrintCard" value="%{isPrintCard}" />
		<s:hidden name="indexID" value="%{indexID}" />
		<s:hidden name="selectedReferal" value="%{selectedReferal}" />
		
		
    <cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token/>
	</s:form>

	<script type="text/javascript"
		src="./../../registration/transactions/js/spclPatientVisit.js" /></script>
</body>
</html>