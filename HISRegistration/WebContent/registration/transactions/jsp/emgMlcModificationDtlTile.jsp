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
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<title>MLC Modification Detail</title>

</head>

<body>
	<center>
		<s:form action="EmgMlcModificationDtl">
			<%-- <s:set name="theme" value="simple" scope="page" /> --%>
			<s:hidden name="afterGo" id="afterGoId" value="%{afterGo}" />
			<s:hidden name="errorMessage" value="%{errorMessage}"/>
			<s:hidden name="entryDate" id="entryDateId" value="%{entryDate}"/>
			<input type="hidden" name="episodeCode" id="episodeCodeId"> 
			<input type="hidden" name="episodeVisitNo" id="episodeVisitNoId">
			<input type="hidden" name="serialNo" id="serialNoId">
			<input type="hidden" name="actionName" value="EmgMlcModificationDtl" id="actionNameId">
			
			<input type="hidden" name="nMlcNoIndex" id="nMlcNoIndexId">
			
			<input type="hidden" name="oldMlcTimeHr" id="oldMlcTimeHrId">
			<input type="hidden" name="oldMlcTimeMin" id="oldMlcTimeMinId">
			
			<s:set name="afetrGoForJsp" value="afterGo"></s:set>
			<s:set name="errorMessageForJsp" value="errorMessage"></s:set>
			<div class="wrapper rounded">

				<div class="div-table">
					<div class="div-table-row">
						<div class="div-table-col title width100"><s:text name="mlc"/>&nbsp;<s:text name="modification"/>&nbsp;<s:text name="global.detail"/></div>
					</div>
				</div>
				<his:InputCrNoTag />
				
				<s:if test="%{#afetrGoForJsp!=0}">
					<%-- <s:action name="patientDetail" executeResult="true"></s:action> --%>
					<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
					
					<div class="div-table" id="divMlcDtlHeaderId">
						<div class="div-table-row">
							<div class="div-table-col title width100"><s:text name="mlc"/>&nbsp;<s:text name="global.detail"/> </div>
						</div>
					</div>
					<s:set name="voListOfMlc" value="#session[@registration.config.RegistrationConfig@EMGREGISTRATIONDESK_MLCVO_LIST]"></s:set>
					<s:if test="%{#voListOfMlc!= null && #voListOfMlc.size != 0}">
							<div class="div-table-listing rounded" id="divMlcDtlId">
								<div class="div-table-row listHeader">
									<div class="div-table-col alignCenter width10"><s:text name="select"/></div>
									<div class="div-table-col alignCenter width25"><s:text name="mlc"/>&nbsp;<s:text name="number"/> </div>
									<div class="div-table-col alignCenter width30"><s:text name="mlc"/>&nbsp;<s:text name="date"/> </div>
									<div class="div-table-col alignCenter width35"><s:text name="global.department"/><s:text name="slash"/><s:text name="unit"/></div>
								</div>
								<s:iterator id="voListOfMlcId"  value="voListOfMlc" status="statusMlcVO">
									<div class="div-table-row listData">
										<div class="div-table-col alignCenter width10">
											<input type="radio" name="mlcRadio" value='<s:property value="%{#statusMlcVO.index+'@'+mlcNo}"/>' onclick="emgMlcModificationDtlJsObj.populateFormFields(this);"/>
										</div>
										<div class="div-table-col alignCenter width25"><s:property value="patMlcNo"/></div>
										<div class="div-table-col alignCenter width30"><s:property value="mlcDate"/></div>
										<div class="div-table-col alignCenter width35"><s:property value="departmentUnit"/></div>
										
										<input type="hidden" name="epiCode" value='<s:property value="episodeCode"/>'> 
										<input type="hidden" name="epiVisitNo" value='<s:property value="episodeVisitNo"/>'>
										<%-- <s:hidden name="episodeDate" value="%{episodeDate}"></s:hidden>
										<s:hidden name="epiDate" value="%{episodeDate.split(' ')[0]}"></s:hidden>
										<s:hidden name="epiTimeHr" value="%{episodeDate.split(' ')[1].split(':')[0]}"></s:hidden>
										<s:hidden name="epiTimeMin" value="%{episodeDate.split(' ')[1].split(':')[1]}"></s:hidden> --%>
										
									</div>
								</s:iterator>
							</div>
					</s:if>
					<s:else>
						<div class="div-table" id="divNoMlcDtlId">
							<div class="div-table-row">
								<div class="div-table-col width100 alignCenter colorRed">No Record Found</div>
							</div>
						</div>
					</s:else>
					<s:if test="%{#errorMessageForJsp==null || #errorMessageForJsp==''}">
						<div class="div-table" id="divRestMlcDtlId" style="display: none;">
							<div class="div-table-row">
								<div class="div-table-col width100">
									<jsp:include page="/registration/transactions/jsp/emgFillMlcDtl.jsp"></jsp:include>
									<jsp:include page="/registration/transactions/jsp/emgMlcTypeSpecifications.jsp"></jsp:include>
									<jsp:include page="/registration/transactions/jsp/emgMlcHandoverToDtl.jsp"></jsp:include>
									<jsp:include page="/registration/transactions/jsp/emgMlcBroughtByDtl.jsp"></jsp:include>
								</div>
							</div>
						</div>
					</s:if>
				</s:if>
			</div>
			
			<div id="divWaitId" style="display:none;width:69px;height:89px;position:absolute;top:12%;left:50%;padding:2px;">
				<img src="/HIS/hisglobal/images/ajax-loader.gif" width="64" height="64" /><br>Loading..
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
					<%-- <a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a> --%>
					<%--By warish/monika for 27525--%>
					<a href="#" class="button" id="cancelId"  onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
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
			<cmbPers:cmbPers></cmbPers:cmbPers>
			<s:token/>
		</s:form>

		<script type="text/javascript" src="./../../registration/transactions/js/emgMlcModificationDtl.js" /></script>
		
	</center>
</body>
</html>