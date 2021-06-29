<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8" /></head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/newBornBaby.js"></script>
<script type="text/javascript">
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
</script>

<body onload="viewN();" onUnload="closePopUp();">
	<html:form action="/transactions/NewBornBabyTransCNT" method ="post" type="ipd.transactions.NewBornBabyTransFB" >
	<div class="errMsg" id="errId"><bean:write name="newBornTransBean_desk"
		property="strMsgString" /></div>
	<div class="normalMsg" id="nrmId"><bean:write name="newBornTransBean_desk" property="strMsg"/><div class="warningMsg"><bean:write name="newBornTransBean_desk" property="strWarningMsg" /></div></div>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">New Born Baby Admission</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Mother CR No.</td>
		<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId" style="display: block;">
			<crNo:crNo value="${newBornTransBean_desk.strCrNo}" js="	onkeypress='return validateData(event,5);'  onkeyup='goRetFuncNB(event);'" id="strCrNoId"></crNo:crNo>
	 		<img src="../../hisglobal/images/Go.png"
				onClick="return goFuncNB1();" align="top" style="cursor: pointer; cursor: hand" > 
			</div>
			<div id="patientCrId" style="display: none;">
				<bean:write name="newBornTransBean_desk" property="strCrNo"/>
			</div>
		</td>
		</tr>
	</table>
	<div id="id4" style="display:none;">
		
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<pDtl:patDtl crNo="${newBornTransBean_desk.strCrNo}" address="false"></pDtl:patDtl>
		</table>
		</div>
	<div id="id5" style="display: none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Mother Details</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL" colspan="1">Mother Name</td>
			<td width="25%" class="CONTROL" colspan="3">
				<bean:write name="newBornTransBean_desk" property="strMotherName" filter="false"/>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Mother Admission No.</td>
			<td width="25%" class="CONTROL">
				<bean:write name="newBornTransBean_desk" property="strMotherAdmissionNo" filter="false"/>
			</td>
			<td width="25%" class="LABEL">Department/Unit</td>
			<td width="25%" class="CONTROL"><bean:write name="newBornTransBean_desk" property="strDeptUnitName"/></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Mother Nationality</td>
			<td width="25%" class="CONTROL">
				<bean:write name="newBornTransBean_desk" property="strMotherNationality" filter="false"/>
			</td>
			<td width="25%" class="LABEL">Mother Religion</td>
			<td width="25%" class="CONTROL">
				<bean:write name="newBornTransBean_desk" property="strMotherReligion" filter="false"/>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Treatment Category</td>
			<td width="25%" class="CONTROL">
			<bean:write name="newBornTransBean_desk" property="strTreatmentCategoryName" filter="false"/>
			</td>
			<td width="25%" class="LABEL" colspan="2"></td>
		</tr>
		</table>
		</div>	
	<div id="newModificationId" style="display:none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  colspan="4" class="TITLE">
			<div id="plusAddModiId" align="left" style="display:block;">
					<img src="../../hisglobal/images/plus.gif" 
									onClick="view3();" style="cursor: pointer; cursor: hand"/>
						New Born Baby Form
					</div>
					<div id="minusAddModiId" style="display:none;" align="left">
						<img src="../../hisglobal/images/minus.gif" 
								onClick="view4();" style="cursor: pointer; cursor: hand"/>
								New Born Baby Form
					</div>
				</td>
		</tr>
	</table>
	</div>
	<div id="newAddressModiId" style="display:none ">
		<bean:write name="newBornTransBean_desk" property="strAddressModi" filter="false"/>
	</div>
		<div id="PatientOccId" style="display:none">
			<table class="TABLEWIDTH" align="center" cellspacing="1px">		
				<tr>
					<td  colspan="4" class="TITLE">
				<div id="id1" align="left" style="display:block;">
					<img src="../../hisglobal/images/plus.gif" 
									onClick="view1();" style="cursor: pointer; cursor: hand"/>
						Patient Occupation Details
					</div>
					<div id="id2" style="display:none;" align="left">
						<img src="../../hisglobal/images/minus.gif" 
								onClick="view2();" style="cursor: pointer; cursor: hand"/>
								Patient Occupation Details
					</div>
					</td>
				</tr>
			</table>
		</div>
			<div id ="PatientOccDtl" style="display:none;">
				<bean:write name="newBornTransBean_desk" property="occupationDetailValues" filter="false"/>
			</div>
			<div id="wardDivId" style="display:none">	
			<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<tr>
				<td colspan="4" class="TITLE">Ward Bed Status</td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">Department</td>
				<td width="25%" class="CONTROL">
					<select name="StrDeptNameNewBorn" onchange="getUnitComboNB();" class="comboMax">
						<bean:write property="strDeptValue" name="newBornTransBean_desk" filter="false"/>
					</select>
				</td>
				<td width="25%" class="LABEL">Unit</td>
				<td width="25%" class="CONTROL">
					<div id="unitDivId">
						<select name="strUnitNewBorn"  class="comboNormal" onchange="funUnit()">
							<bean:write property="strUnitValue" name="newBornTransBean_desk" filter="false"/>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td class="LABEL" width="25%">Bed Status</td>
				<td  class="multiControl" width="25%">
				<img src="../../hisglobal/images/Bed_.gif" onClick="bedDetailsNB();" align="middle" onmouseover="" style="cursor: pointer; cursor: hand"/>
				</td>
				<td  class="LABEL" width="25%" colspan="2"></td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">Ward</td>
				<td width="25%" class="CONTROL">
				<div id="wardNameId"><bean:write name="newBornTransBean_desk" property="strMotherWardName"/></div>
				</td>
				<td width="25%" class="LABEL">Room
				</td>
				<td width="25%" class="CONTROL">
					<div id="roomBedId">
						<bean:write name="newBornTransBean_desk" property="strRoom"/>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" colspan="1" class="LABEL">Remarks</td>
				<td width="25%" colspan="3" class="CONTROL"><textarea rows="3"
				cols="25" name="strRemarks"></textarea></td>
			</tr>
		</table>
	</div>
	<div style="display: none" id="chargesDtlId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
				<td colspan="4" class="TITLE">Admission Charges Details</td>
			</tr>
	</table>
	</div>
	<div id="admissionId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		
		<tr>
			<td  class="LABEL" width="25%">Admission Charges</td>
				<td  class="CONTROL" width="25%" ><input type="text" name="strAdmissionChargeValue" value="${newBornTransBean_desk.strAdmissionChargeValue}" class="txtFldMin"></td>
				<td  class="LABEL" width="25%"></td>
				<td  class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>
	<div id="admissionAdvanceId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  class="LABEL" width="25%">Advance Admission Charges</td>
				<td  class="CONTROL" width="25%" ><input type="text" name="strAdmissionAdvanceChargeValue" value="${newBornTransBean_desk.strAdmissionAdvanceChargeValue}" class="txtFldMin"></td>
				<td  class="LABEL" width="25%"></td>
				<td  class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>
	<div id="registrationId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  class="LABEL" width="25%">Registration Charges</td>
				<td  class="CONTROL" width="25%" ><input type="text" name="strNewBornRegistrationChargeVal" value="${newBornTransBean_desk.strNewBornRegistrationChargeVal}" class="txtFldMin"></td>
				<td  class="LABEL" width="25%"></td>
				<td  class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>
	<div id="totalID" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  class="LABEL" width="25%">Total Charges</td>
				<td  class="CONTROL" width="25%" ><input type="text" name="strTotalChargeVal" value="" class="txtFldMin"></td>
				<td  class="LABEL" width="25%"></td>
				<td  class="LABEL" width="25%"></td>
		</tr>
	</table>
	</div>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px"> 
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields		
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><img src="../../hisglobal/images/btn-sv.png"
				onClick="return validate1NewBorn();" style="cursor: pointer; cursor: hand"/>
			<img src="../../hisglobal/images/btn-clr.png"
				onClick="clearRecordNB();" style="cursor: pointer; cursor: hand"/>
			<img src="../../hisglobal/images/btn-ccl.png"
				onClick="window.close();" /style="cursor: pointer; cursor: hand"/>
			</td>
		</tr>
	</table>
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="deskmode"/>
	<input type="hidden" name="strMotherDeptCode" value="${newBornTransBean_desk.strMotherDeptCode}"/>
	<input type="hidden" name="strMotherWardCode" value="${newBornTransBean_desk.strMotherWardCode}"/>
	<input type="hidden" name="strMotherWardTypeCode" value="${newBornTransBean_desk.strMotherWardTypeCode}"/>
	<input type="hidden" name="strMotherRoomTypeCode" value="${newBornTransBean_desk.strMotherRoomTypeCode}"/>
	<input type="hidden" name="strMotherBedTypeTypeCode" value="${newBornTransBean_desk.strMotherRoomTypeCode}"/>
	<input type="hidden" name="strWardTypeCode" value="${newBornTransBean_desk.strWardTypeCode}"/>
	<input type="hidden" name="strDeptUnitCode" value="${newBornTransBean_desk.strDeptUnitCode}"/>
	<input type="hidden" name="strTreatmentCategoryCode" value="${newBornTransBean_desk.strTreatmentCategoryCode}"/>
	<input type="hidden" name="strWardCode" value="${newBornTransBean_desk.strWardCode}"/>
	<input type="hidden" name="strBedTypeCode" value="${newBornTransBean_desk.strBedTypeCode}"/>
	<input type="hidden" name="strWardName" value="${newBornTransBean_desk.strWardName}"/>
	<input type="hidden" name="strRoomTypeCode" value="${newBornTransBean_desk.strRoomTypeCode}"/>
	<input type="hidden" name="strDeptCode" value="${newBornTransBean_desk.strDeptCode}"/>
	<input type="hidden" name="strConsultantCode" value="${newBornTransBean_desk.strConsultantCode}"/>
	<input type="hidden" name="strEpisodeCode" value="${newBornTransBean_desk.strEpisodeCode}"/>
	<input type="hidden" name="strVisitNo" value="${newBornTransBean_desk.strVisitNo}"/>
	<input type="hidden" name="strMlcNo" value="${newBornTransBean_desk.strMlcNo}"/>
	<input type="hidden" name="strAdviceAdmNo" value="${newBornTransBean_desk.strAdviceAdmNo}"/>
	<input type="hidden" name="strIsUrban" value="${newBornTransBean_desk.strIsUrban}"/>
	<input type="hidden" name="strBedCode" value="${newBornTransBean_desk.strBedCode}"/>
	<input type="hidden" name="strRoomCode" value="${newBornTransBean_desk.strRoomCode}"/>
	<input type="hidden" name="strBookingDate" value="${newBornTransBean_desk.strBookingDate}"/>
	<input type="hidden" name="strFlag" value="1"/>
	<input type="hidden" name="strMsApprovalFlag" value="${newBornTransBean_desk.strMsApprovalFlag}"/>
	<input type="hidden" name="strMsApprovalStatus" value="${newBornTransBean_desk.strMsApprovalStatus}"/>
	<input type="hidden" name="strNewBorn" value="${newBornTransBean_desk.strNewBorn}"/>
	<input type="hidden" name="strMotherCrNo" value="${newBornTransBean_desk.strMotherCrNo}"/>
	<input type="hidden" name="strMotherAdmissionNo" value="${newBornTransBean_desk.strMotherAdmissionNo}"/>
	<input type="hidden" name="strMotherNationalityCode" value="${newBornTransBean_desk.strMotherNationalityCode}"/>
	<input type="hidden" name="strMotherReligionCode" value="${newBornTransBean_desk.strMotherReligionCode}"/>
	<input type="hidden" name="strMotherName" value="${newBornTransBean_desk.strMotherName}"/>
	<input type="hidden" name="strAdmissionCharge" value="${newBornTransBean_desk.strAdmissionCharge}"/>
	<input type="hidden" name="strNewBornRegistrationCharge" value="${newBornTransBean_desk.strNewBornRegistrationCharge}"/>
	<input type="hidden" name="strPatStatusCode" value="${newBornTransBean_desk.strPatStatusCode}"/>
	<input type="hidden" name="strDobTime" value="${newBornTransBean_desk.strPatStatusCode}"/>
	<input type="hidden" name="strMotherUnitCode" value="${newBornTransBean_desk.strMotherUnitCode}"/>
	<input type="hidden" name="strMotherNationalityCode" value="${newBornTransBean_desk.strMotherNationalityCode}"/>
	<input type="hidden" name="strAdmissionAdvance" value="${newBornTransBean_desk.strAdmissionAdvance}"/>
	<input type="hidden" name="strConsultantCode" value="${newBornTransBean_desk.strConsultantCode}"/>
	<input type="hidden" name="strPatCatCode" value="${newBornTransBean_desk.strPatCatCode}"/>
	<input type="hidden" name="strPatStatusCode" value="${newBornTransBean_desk.strPatStatusCode}"/>
	</html:form>
	<tag:autoIndex></tag:autoIndex>
	</body>
	</html>
	
