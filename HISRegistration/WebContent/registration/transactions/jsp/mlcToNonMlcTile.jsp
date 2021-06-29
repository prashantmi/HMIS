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

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<%-- <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.tools.min.js"></script> --%>
<%-- <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script> --%>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/MlctoNonMlc.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<%-- <script type="text/javascript" src="./../../registration/transactions/js/datagrid/datagrid-detailview.js"></script> --%>
	

<title>MLC to Non MLC</title>

</head>
<%-- <style type="text/css">

	.tempClass{
	}
</style> --%>
<%-- <script type="text/javascript">

</script> --%>
<body>
	<s:form action="MlcToNonMlc">
		<input type="hidden" name="abc" value="1" />
		<s:hidden name="afterGo" id="afterGoId" value="%{afterGo}" />
		<s:hidden name="strErrorMessage" value="%{strErrorMessage}"/>
		<s:hidden name="strNormalMsg" value="%{strNormalMsg}"/>
		
		<s:set name="afetrGoForJsp" value="afterGo"></s:set>
		<s:set name="errorMessageForJsp" value="strErrorMessage"></s:set>
		<s:set name="normalMessageForJsp" value="strNormalMsg"></s:set>
		
		
		<div class="wrapper rounded " id="nonprintableDiv1">

			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col title width100"><s:text name="mlctononmlc"/>&nbsp;<s:text name="conversion"/></div>
				</div>
			</div>

			<his:InputCrNoTag />
			<s:if test="%{#afetrGoForJsp!=0}">
<%-- 				<s:action name="patientDetail" executeResult="true"></s:action>
 --%>				<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
				<div class="div-table" id="divProvMlcEpisodeTitleId">
					<div class="div-table-row">
						<div class="div-table-col title width100 "><s:text name="conversion"/>&nbsp;<s:text name="of"/></div>&nbsp;<s:text name="episode"/>&nbsp;<s:text name="from"/>&nbsp;<s:text name="mlctononmlc"/>&nbsp;<s:text name="case"/></div></div>
					</div>
				</div>
				<div class="div-table" id="divProvMlcEpisodeDtlId">
					<s:set name="voProvMlcEpisode" value="#session[@registration.config.RegistrationConfig@ARR_OPEN_PROVISIONAL_MLC_EPISODE_VO]"></s:set>
					<s:if test="%{#afetrGoForJsp!=0 && #voProvMlcEpisode!= null && #voProvMlcEpisode.length>0}">
						<div class="div-table-listing rounded">
							<div class="div-table-row listHeader">
								<div class="div-table-col alignCenter width5"><s:text name="select"/></div>
								<div class="div-table-col alignCenter width45"><s:text name="global.unit"/>&nbsp;<s:text name="global.name"/> </div>
								<div class="div-table-col alignCenter width40"><s:text name="global.remarks"/><font color="red">*</font> </div>
								
							</div>
							<s:iterator id="voProvMlcEpisodeId"  value="voProvMlcEpisode" status="statusProvMlcEpisodeVO">
								<div class="div-table-row listData">
									<div class="div-table-col alignCenter width5">
										<s:set name="index" value="%{#statusProvMlcEpisodeVO.index+''}"></s:set>
										<input type="radio" name="chks" id="chksId<s:property value="index"/>" value='<s:property value="index"/>' onkeypress="selectDeselect(this,'<s:property value="index"/>')" onclick="selectDeselect(this,'<s:property value="index"/>')"/>
										<input type="hidden" name="selEpisodeCode" value='<s:property value="episodeCode"/>'>
										<input type="hidden" name="selEpisodeVisitNo" value='<s:property value="episodeVisitNo"/>'>
										<input type="hidden" name="selMlcNo" value='<s:property value="mlcNo"/>'>
									</div>
									<div class="div-table-col alignCenter width45">
										<s:property value="departmentUnit"/>
									</div>
									<div class="div-table-col alignCenter width40">
										<input type="text" class="input50prcnt tooltipClass tempClass" name="selRemarks" id='selRemarksId<s:property value="index"/>'  maxlength="150" disabled="disabled">
									</div>
									
								</div>
							</s:iterator>
						</div>
					</s:if>
					<s:else>
						<div class="div-table-col width100 alignCenter">No Provisional MLC Episode Found</div>
					</s:else>
				</div>
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
					<a href="#" class="button" id="cancelId" onclick="window.parent.closeTab('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
				</div>
			</div>
			
		</div>
			<div class="div-table-simple">
				<div class="div-table-row">
					<div id='divMsgId' class="div-table-col alignLeft fontError width100">
						<s:if test="%{#errorMessageForJsp!=null && #errorMessageForJsp!=''}">
							<s:property value="%{#errorMessageForJsp}"/>
						</s:if>
						<s:else>
							<s:property value="%{#normalMessageForJsp}"/>
						</s:else>
					</div>
				</div>
			</div>
			<cmbPers:cmbPers></cmbPers:cmbPers>
			<s:token/>
	</s:form>
	
	<!-- <table id="tt"></table> -->
	

</body>
</html>