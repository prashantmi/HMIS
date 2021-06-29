<%@page import="hisglobal.hisconfig.Config"%>
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
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="../../hisglobal/css/jquery-ui.css">

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<title>Merging of CRNO</title>
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
</head>
<script>

	var crNoSize=<%=Config.CR_NO_FORMAT_SIZE %>
	var DEFAULT_VALUE_CR_NO_FORMAT=<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>;
	var hosCode=<%=(String)session.getAttribute("HOSPITAL_CODE")%>;
	
</script>
<body>
<s:form action="OfflineMergeCRNO">

			<div class="wrapper rounded" id="nonprintableDiv1">
			
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100"><s:text name="mergeCRNO"/></div>
					</div>
				</div>

				<his:InputCrNoTag />				
				<s:hidden name="afterGo" id="afterGoId" value="%{afterGo}" />
				<s:set name="afterGoForJsp" value="afterGo"></s:set>
				<s:set name="errorMessageForJsp" value="errorMessage"></s:set>
				<s:set name="normalMessageForJsp" value="normalMessage"></s:set>
				
				<!-- After Go -->
				<s:if test="%{#afterGoForJsp!=0}">
					<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>	
					
					
				<div id="divMergedId" >
							<s:set name="voArrMergedCRVo" value="#session[@registration.config.RegistrationConfig@ARR_MERGED_PATIENT]"></s:set>
							<s:if test="%{#afterGoForJsp!=0 && #voArrMergedCRVo!= null}">
								<div class="div-table">
									<div class="div-table-row ">
										<div class="div-table-col title width100"><s:text name="Merged Patient CR Number"/></div>
									</div>
								</div>
								<div class="div-table-listing rounded" >
									<div class="div-table-row listHeader" >
										<div class="div-table-col alignCenter" style="width: 20%"><s:text name="crNum"/></div>
										<div class="div-table-col alignCenter" style="width: 20%"><s:text name="name"/> </div>
										<div class="div-table-col alignCenter" style="width: 15%"><s:text name="age"/>&nbsp;<s:text name="slash"/>&nbsp;<s:text name="gender"/></div>
										<div class="div-table-col alignCenter" style="width: 10%"><s:text name="fatherName"/></div>
										<div class="div-table-col alignCenter" style="width: 20%"><s:text name="address"/></div>
										<div class="div-table-col alignCenter" style="width: 10%"><s:text name="reason"/></div>										
										<div class="div-table-col alignCenter" style="width: 5%"></div>								
										
									</div>
									<s:iterator id="voArrMergeCRId"  value="voArrMergedCRVo" status="statusArrMergedCR">
										<div class="div-table-row listData">
											<div class="div-table-col alignCenter" style="width: 20%;">
												<s:property value="patCrNo"/>
												<s:hidden name="hiddenMergedCRNo" value="<s:property value='patCrNo'/>"/>
												<s:hidden name="mergedCRNoIndex" value="#statusArrMergedCR.index"/>
											</div>
											<div class="div-table-col alignCenter" style="width: 20%">
												<s:property value="patFirstName"/>&nbsp;<s:property value="patMiddleName"/>&nbsp;<s:property value="patLastName"/>
											</div>											
											<div class="div-table-col alignCenter" style="width: 15%">
												<s:property value="patAge"/>&nbsp;<s:property value="patAgeUnit"/><s:text name="slash"/>&nbsp;<s:property value="patGender"/>
											</div>
											<div class="div-table-col alignCenter" style="width: 10%">
												<s:property value="patGuardianName"/>
											</div>
											<div class="div-table-col alignCenter" style="width: 20%">
												<s:property value="patAddHNo"/>&nbsp;<s:property value="patAddCityLoc"/>&nbsp;<s:property value="patAddCity"/>&nbsp;<s:property value="patAddDistrict"/>
											</div>
											<div class="div-table-col alignCenter" style="width: 10%">
												<input type="text" name="reason" maxlength="50" class="input75prcnt" onkeypress="return validateAlphaNumericWithDotsOnly(event)">
											</div>
											<div class="div-table-col alignCenter" style="width: 5%">
												<img id="imgMinusRevokeId" class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' 
												onclick="revokeCRNO(<s:property value="patCrNo"/>,<s:property value="%{#statusArrMergedCR.index}"/>)"/>
											</div>
										</div> 									
									</s:iterator>
								</div>
							</s:if>
				</div>				
				
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100"><s:text name="CR No To Be Merged"/></div>
					</div>
				</div>
				
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col label width25"><s:text name="crNum"/></div>
						<div class="div-table-col control width13"><s:textfield name="patNotUsedCrNo" id="patNotUsedCrNoId"></s:textfield> </div>
						<div class="div-table-col control width20"><img id='imgGoCrNoTobeMergedId' src='../../hisglobal/images/GO.png' style='cursor:pointer' onkeydown="setPrevValue(this, event);" onkeypress="if(event.keyCode!=13) return validateNumericOnly(this,event); else return submitForm(GO);" /></div>
						<div class="div-table-col label width40"><img hidden="true" id='imgSearchCrNoTobeMergedId' src='../../hisglobal/images/search_icon1.gif' style='cursor:pointer' onclick="openPopupWithEventHeightWidth('../../registration/transactions/PatientSearch.action',event,300,900)" /></div>
						
					</div>
				</div>
				
				<div id="divTobeMergedId" >
							<s:set name="voArrToBeMergedCRVo" value="#session[@registration.config.RegistrationConfig@ARR_TO_BE_MERGED_CR_NUMBER_VO]"></s:set>
							<s:if test="%{#afterGoForJsp!=0 && #voArrToBeMergedCRVo!= null}">
								<div class="div-table-listing rounded" >
									<div class="div-table-row listHeader" >
										<div class="div-table-col alignCenter" style="width: 20%"><s:text name="crNum"/></div>
										<div class="div-table-col alignCenter" style="width: 20%"><s:text name="name"/> </div>
										<div class="div-table-col alignCenter" style="width: 10%"><s:text name="age"/>&nbsp;<s:text name="slash"/>&nbsp;<s:text name="gender"/></div>
										<div class="div-table-col alignCenter" style="width: 20%"><s:text name="fatherName"/></div>
										<div class="div-table-col alignCenter" style="width: 20%"><s:text name="address"/></div>
										<div class="div-table-col alignCenter" style="width: 10%"></div>								
										
									</div>
									<s:iterator id="voArrToMergeCRId"  value="voArrToBeMergedCRVo" status="statusArrTobeMergedCR">
										<div class="div-table-row listData">
											<div class="div-table-col alignCenter" style="width: 20%;">
												<s:property value="patCrNo"/>
												<s:hidden name="hiddenNotUsedCRNo" value="<s:property value='patCrNo'/>"/>
											</div>
											<div class="div-table-col alignCenter" style="width: 20%">
												<s:property value="patFirstName"/>&nbsp;<s:property value="patMiddleName"/>&nbsp;<s:property value="patLastName"/>
											</div>											
											<div class="div-table-col alignCenter" style="width: 10%">
												<s:property value="patAge"/>&nbsp;<s:property value="patAgeUnit"/><s:text name="slash"/>&nbsp;<s:property value="patGender"/>
											</div>
											<div class="div-table-col alignCenter" style="width: 20%">
												<s:property value="patGuardianName"/>
											</div>
											<div class="div-table-col alignCenter" style="width: 20%">
												<s:property value="patAddHNo"/>&nbsp;<s:property value="patAddCityLoc"/>&nbsp;<s:property value="patAddCity"/>&nbsp;<s:property value="patAddDistrict"/>
												</div>
											<div class="div-table-col alignCenter" style="width: 10%">
												<img id="imgMinusDeleteId" class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' onclick="deleteRow(<s:property value="patCrNo"/>)"/>
											</div>
										</div> 									
									</s:iterator>
								</div>
							</s:if>
				</div>
				
				
				</s:if>
				
				<div class="div-table-button" id="nonprintableDiv2">
					<div class="div-table-row footerBar">
							<div class="div-table-col" > </div>
					</div>
					<div class="div-table-row emptyBar">
						<div class="div-table-col"> </div>
					</div>
					<div class="div-table-row" align="center">
						<s:if test="%{#afterGoForJsp!=0}">
								<a href="#" class="button" id="submitId"><span class="save">Save</span></a>
						</s:if>
						<a href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
						<a href="#" class="button" id="cancelId" onclick="initPage();"><span class="cancel"><s:text name="cancel"/></span></a>
					</div>
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
					<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%">
						<s:property value="%{#normalMessageForJsp}"/>
					</div>
				</div>
			</div>
				
			
			<s:hidden name="deleteCrNo"></s:hidden>
			<s:hidden name="revokedCrNo"></s:hidden>
			<s:hidden name="concatedCrNo"></s:hidden>
			<cmbPers:cmbPers></cmbPers:cmbPers>
		<s:token/>	
</s:form>
<his:status />

<script type="text/javascript"
			src="./../../registration/transactions/js/opdMergeCRNO.js" /></script>
</body>
</html>

				