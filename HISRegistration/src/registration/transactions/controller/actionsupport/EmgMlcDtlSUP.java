package registration.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class EmgMlcDtlSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	
	protected String afterGo;
	protected String strNormalMsg;
	protected String strWarningMsg;
	protected String errorMessage;
	
	//////////////////////////////////////
	
	protected String patCrNo;
	protected String isDirectCall;
	protected String patMlcNo;
	protected String episodeCode;
	protected String caseNo;
	protected String policeName;
	protected String policeDesignation;
	protected String policeStation;
	protected String officerIncharge;
	protected String mlcDate;
	protected String cmoCode;
	protected String isTransfer;
	protected String seatId;
	protected String entryDate;
	protected String isBroughtDead;
	protected String patCondition;
	protected String doctorName;
	protected String mlcRemark;
	protected String isValid;
	protected String serialNo;
	protected String patFirstName;
	protected String patMiddleName;
	protected String patLastName;
	protected String patDOB;
	protected String patGender;
	protected String patGenderCode;
	protected String patMaritalStatus;
	protected String patMaritalStatusCode;
	protected String patReligion;
	protected String patReligionCode;
	protected String patGuardianName;
	protected String patMomName;// in case of new born child
	protected String isReferred;
	protected String patRefGnctdHospitalCode;
	protected String patRefHospitalName;
	protected String patRefDoctor;
	protected String isUnknown;
	protected String mlcTypes;
	/*Start : Surabhi
	 * Reason : to get the patient mlc details for the certificate
	 * date : 7th oct 2016 */
	protected String printFlag;
	protected String address1;
	protected String address2;
	protected String city;
	protected String districtName;
	protected String hospitalName;
	protected String stateName;
	// end
	
	
	protected String batchNo;
	protected String broughtByName;
	protected String broughtByAddress;
	protected String broughtByPhone;
	protected String getCrNoToRetrieve;
	protected String cmoType;
	
	///////////   MultiRow Div /////////////
	protected String[] mlcTypeCode;
	protected String[] injurySide;
	protected String[] injuryNatureCode;
	protected String[] typeOfWeapon;
	protected String[] injurySize;
	protected String[] injuryDepth;
	protected String[] burnPercentage;
	protected String[] poisonRemarks;
	////////////////////////////////////////
	
	protected String isRelative;
	protected String broughtByGenderCode;
	protected String broughtByRelationCode;
	protected String broughtByLocation;
	protected String isBroughtBy;
	protected String mlcTime;
	protected String mlcTimeHr;
	protected String mlcTimeMin;
	
	protected String patMlcStatusCode;
	
	protected String referDoctorCode;
	protected String referDocotorRemarks;
	protected String mlcType;
	protected String diagnosis;
	protected String fitnessStatus;
	protected String mlcBookNo;
	protected String mlcPageNo;
	protected String isBroughtByPolice;
	protected String processId;
	protected String macroHead;
	protected String client;
	protected String docketNo;
	protected String ioDesignation;
	protected String ioBatchNo;
	protected String dutyOfficeFlag;
	protected String dutyOffName;
	protected String dutyOffDesignation;
	protected String dutyOffBatchNo;
	protected String handoverDateTime;
	protected String handoverTimeHr;
	protected String handoverTimeMin;
	protected String handoverToFlag;
	protected String handoverOffName;
	protected String handoverOffDesg;
	protected String handoverOffBadgeNo;
	protected String hiddenTimeHr;
	protected String hiddenTimeMin;
	protected String isPoliceVerReq;
	protected String constableDesig;
	protected String constableBadgeNo;
	protected String caseRemarks;
	protected String episodeVisitNo;
	protected String isDuplicateMlcNo;
	protected String systemIPAddress;// IP address of the system from which the details are entered
	protected String epiDate;
	protected String epiTimeHr;
	protected String epiTimeMin;
	
	protected String visitDate;
	protected String nVisitNoIndex;
	
	protected String finalOpinion;
	protected String provisionalOpinion;
	protected String mlcStatus;
	protected String patIdMark1;
	protected String patIdMark2;
	protected String phone;
	protected String PinCode;
	
	protected String patAge;
	
	public String getPinCode() {
		return PinCode;
	}
	public void setPinCode(String pinCode) {
		PinCode = pinCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static String flagMlcOnlineOffline;
	protected String isTodayOfflineVisit;
	protected String isDesk;
	
	public HttpServletRequest getObjRequest() {
		return objRequest;
	}
	public void setObjRequest(HttpServletRequest objRequest) {
		this.objRequest = objRequest;
	}
	public HttpServletResponse getObjResponse() {
		return objResponse;
	}
	public void setObjResponse(HttpServletResponse objResponse) {
		this.objResponse = objResponse;
	}
	public ServletContext getObjContext() {
		return objContext;
	}
	public void setObjContext(ServletContext objContext) {
		this.objContext = objContext;
	}
	public Map getMapSesion() {
		return mapSesion;
	}
	public void setMapSesion(Map mapSesion) {
		this.mapSesion = mapSesion;
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override 
	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
	}

	@Override 
	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}
	@Override 
	public void setSession(Map sessionMap)
	{
		this.mapSesion = sessionMap;
	}
	
	public String getAfterGo() {
		return afterGo;
	}
	public void setAfterGo(String afterGo) {
		this.afterGo = afterGo;
	}
	
	
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getIsDirectCall() {
		return isDirectCall;
	}
	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getPoliceName() {
		return policeName;
	}
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
	public String getPoliceDesignation() {
		return policeDesignation;
	}
	public void setPoliceDesignation(String policeDesignation) {
		this.policeDesignation = policeDesignation;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getOfficerIncharge() {
		return officerIncharge;
	}
	public void setOfficerIncharge(String officerIncharge) {
		this.officerIncharge = officerIncharge;
	}
	public String getMlcDate() {
		return mlcDate;
	}
	public void setMlcDate(String mlcDate) {
		this.mlcDate = mlcDate;
	}
	public String getCmoCode() {
		return cmoCode;
	}
	public void setCmoCode(String cmoCode) {
		this.cmoCode = cmoCode;
	}
	public String getIsTransfer() {
		return isTransfer;
	}
	public void setIsTransfer(String isTransfer) {
		this.isTransfer = isTransfer;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsBroughtDead() {
		return isBroughtDead;
	}
	public void setIsBroughtDead(String isBroughtDead) {
		this.isBroughtDead = isBroughtDead;
	}
	public String getPatCondition() {
		return patCondition;
	}
	public void setPatCondition(String patCondition) {
		this.patCondition = patCondition;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getMlcRemark() {
		return mlcRemark;
	}
	public void setMlcRemark(String mlcRemark) {
		this.mlcRemark = mlcRemark;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatMaritalStatus() {
		return patMaritalStatus;
	}
	public void setPatMaritalStatus(String patMaritalStatus) {
		this.patMaritalStatus = patMaritalStatus;
	}
	public String getPatMaritalStatusCode() {
		return patMaritalStatusCode;
	}
	public void setPatMaritalStatusCode(String patMaritalStatusCode) {
		this.patMaritalStatusCode = patMaritalStatusCode;
	}
	public String getPatReligion() {
		return patReligion;
	}
	public void setPatReligion(String patReligion) {
		this.patReligion = patReligion;
	}
	public String getPatReligionCode() {
		return patReligionCode;
	}
	public void setPatReligionCode(String patReligionCode) {
		this.patReligionCode = patReligionCode;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatMomName() {
		return patMomName;
	}
	public void setPatMomName(String patMomName) {
		this.patMomName = patMomName;
	}
	public String getIsReferred() {
		return isReferred;
	}
	public void setIsReferred(String isReferred) {
		this.isReferred = isReferred;
	}
	public String getPatRefGnctdHospitalCode() {
		return patRefGnctdHospitalCode;
	}
	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode) {
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}
	public String getPatRefHospitalName() {
		return patRefHospitalName;
	}
	public void setPatRefHospitalName(String patRefHospitalName) {
		this.patRefHospitalName = patRefHospitalName;
	}
	public String getPatRefDoctor() {
		return patRefDoctor;
	}
	public void setPatRefDoctor(String patRefDoctor) {
		this.patRefDoctor = patRefDoctor;
	}
	public String getIsUnknown() {
		return isUnknown;
	}
	public void setIsUnknown(String isUnknown) {
		this.isUnknown = isUnknown;
	}
	public String getMlcTypes() {
		return mlcTypes;
	}
	public void setMlcTypes(String mlcTypes) {
		this.mlcTypes = mlcTypes;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getBroughtByName() {
		return broughtByName;
	}
	public void setBroughtByName(String broughtByName) {
		this.broughtByName = broughtByName;
	}
	public String getBroughtByAddress() {
		return broughtByAddress;
	}
	public void setBroughtByAddress(String broughtByAddress) {
		this.broughtByAddress = broughtByAddress;
	}
	public String getBroughtByPhone() {
		return broughtByPhone;
	}
	public void setBroughtByPhone(String broughtByPhone) {
		this.broughtByPhone = broughtByPhone;
	}
	public String getGetCrNoToRetrieve() {
		return getCrNoToRetrieve;
	}
	public void setGetCrNoToRetrieve(String getCrNoToRetrieve) {
		this.getCrNoToRetrieve = getCrNoToRetrieve;
	}
	public String getCmoType() {
		return cmoType;
	}
	public void setCmoType(String cmoType) {
		this.cmoType = cmoType;
	}
	public String[] getMlcTypeCode() {
		return mlcTypeCode;
	}
	public void setMlcTypeCode(String[] mlcTypeCode) {
		this.mlcTypeCode = mlcTypeCode;
	}
	public String[] getInjurySide() {
		return injurySide;
	}
	public void setInjurySide(String[] injurySide) {
		this.injurySide = injurySide;
	}
	public String[] getInjuryNatureCode() {
		return injuryNatureCode;
	}
	public void setInjuryNatureCode(String[] injuryNatureCode) {
		this.injuryNatureCode = injuryNatureCode;
	}
	public String[] getTypeOfWeapon() {
		return typeOfWeapon;
	}
	public void setTypeOfWeapon(String[] typeOfWeapon) {
		this.typeOfWeapon = typeOfWeapon;
	}
	public String[] getInjurySize() {
		return injurySize;
	}
	public void setInjurySize(String[] injurySize) {
		this.injurySize = injurySize;
	}
	public String[] getInjuryDepth() {
		return injuryDepth;
	}
	public void setInjuryDepth(String[] injuryDepth) {
		this.injuryDepth = injuryDepth;
	}
	public String[] getBurnPercentage() {
		return burnPercentage;
	}
	public void setBurnPercentage(String[] burnPercentage) {
		this.burnPercentage = burnPercentage;
	}
	public String[] getPoisonRemarks() {
		return poisonRemarks;
	}
	public void setPoisonRemarks(String[] poisonRemarks) {
		this.poisonRemarks = poisonRemarks;
	}
	public String getIsRelative() {
		return isRelative;
	}
	public void setIsRelative(String isRelative) {
		this.isRelative = isRelative;
	}
	public String getBroughtByGenderCode() {
		return broughtByGenderCode;
	}
	public void setBroughtByGenderCode(String broughtByGenderCode) {
		this.broughtByGenderCode = broughtByGenderCode;
	}
	public String getBroughtByRelationCode() {
		return broughtByRelationCode;
	}
	public void setBroughtByRelationCode(String broughtByRelationCode) {
		this.broughtByRelationCode = broughtByRelationCode;
	}
	public String getBroughtByLocation() {
		return broughtByLocation;
	}
	public void setBroughtByLocation(String broughtByLocation) {
		this.broughtByLocation = broughtByLocation;
	}
	public String getIsBroughtBy() {
		return isBroughtBy;
	}
	public void setIsBroughtBy(String isBroughtBy) {
		this.isBroughtBy = isBroughtBy;
	}
	public String getMlcTime() {
		return mlcTime;
	}
	public void setMlcTime(String mlcTime) {
		this.mlcTime = mlcTime;
	}
	public String getMlcTimeHr() {
		return mlcTimeHr;
	}
	public void setMlcTimeHr(String mlcTimeHr) {
		this.mlcTimeHr = mlcTimeHr;
	}
	public String getMlcTimeMin() {
		return mlcTimeMin;
	}
	public void setMlcTimeMin(String mlcTimeMin) {
		this.mlcTimeMin = mlcTimeMin;
	}
	
	public String getPatMlcStatusCode() {
		return patMlcStatusCode;
	}
	public void setPatMlcStatusCode(String patMlcStatusCode) {
		this.patMlcStatusCode = patMlcStatusCode;
	}
	public String getReferDoctorCode() {
		return referDoctorCode;
	}
	public void setReferDoctorCode(String referDoctorCode) {
		this.referDoctorCode = referDoctorCode;
	}
	public String getReferDocotorRemarks() {
		return referDocotorRemarks;
	}
	public void setReferDocotorRemarks(String referDocotorRemarks) {
		this.referDocotorRemarks = referDocotorRemarks;
	}
	public String getMlcType() {
		return mlcType;
	}
	public void setMlcType(String mlcType) {
		this.mlcType = mlcType;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getFitnessStatus() {
		return fitnessStatus;
	}
	public void setFitnessStatus(String fitnessStatus) {
		this.fitnessStatus = fitnessStatus;
	}
	public String getIsBroughtByPolice() {
		return isBroughtByPolice;
	}
	public String getMlcBookNo() {
		return mlcBookNo;
	}
	public void setMlcBookNo(String mlcBookNo) {
		this.mlcBookNo = mlcBookNo;
	}
	public String getMlcPageNo() {
		return mlcPageNo;
	}
	public void setMlcPageNo(String mlcPageNo) {
		this.mlcPageNo = mlcPageNo;
	}
	public void setIsBroughtByPolice(String isBroughtByPolice) {
		this.isBroughtByPolice = isBroughtByPolice;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getMacroHead() {
		return macroHead;
	}
	public void setMacroHead(String macroHead) {
		this.macroHead = macroHead;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getDocketNo() {
		return docketNo;
	}
	public void setDocketNo(String docketNo) {
		this.docketNo = docketNo;
	}
	public String getIoDesignation() {
		return ioDesignation;
	}
	public void setIoDesignation(String ioDesignation) {
		this.ioDesignation = ioDesignation;
	}
	public String getIoBatchNo() {
		return ioBatchNo;
	}
	public void setIoBatchNo(String ioBatchNo) {
		this.ioBatchNo = ioBatchNo;
	}
	public String getDutyOfficeFlag() {
		return dutyOfficeFlag;
	}
	public void setDutyOfficeFlag(String dutyOfficeFlag) {
		this.dutyOfficeFlag = dutyOfficeFlag;
	}
	public String getDutyOffName() {
		return dutyOffName;
	}
	public void setDutyOffName(String dutyOffName) {
		this.dutyOffName = dutyOffName;
	}
	public String getDutyOffDesignation() {
		return dutyOffDesignation;
	}
	public void setDutyOffDesignation(String dutyOffDesignation) {
		this.dutyOffDesignation = dutyOffDesignation;
	}
	public String getDutyOffBatchNo() {
		return dutyOffBatchNo;
	}
	public void setDutyOffBatchNo(String dutyOffBatchNo) {
		this.dutyOffBatchNo = dutyOffBatchNo;
	}
	public String getHandoverDateTime() {
		return handoverDateTime;
	}
	public void setHandoverDateTime(String handoverDateTime) {
		this.handoverDateTime = handoverDateTime;
	}
	public String getHandoverTimeHr() {
		return handoverTimeHr;
	}
	public void setHandoverTimeHr(String handoverTimeHr) {
		this.handoverTimeHr = handoverTimeHr;
	}
	public String getHandoverTimeMin() {
		return handoverTimeMin;
	}
	public void setHandoverTimeMin(String handoverTimeMin) {
		this.handoverTimeMin = handoverTimeMin;
	}
	public String getHandoverToFlag() {
		return handoverToFlag;
	}
	public void setHandoverToFlag(String handoverToFlag) {
		this.handoverToFlag = handoverToFlag;
	}
	public String getHandoverOffName() {
		return handoverOffName;
	}
	public void setHandoverOffName(String handoverOffName) {
		this.handoverOffName = handoverOffName;
	}
	public String getHandoverOffDesg() {
		return handoverOffDesg;
	}
	public void setHandoverOffDesg(String handoverOffDesg) {
		this.handoverOffDesg = handoverOffDesg;
	}
	public String getHandoverOffBadgeNo() {
		return handoverOffBadgeNo;
	}
	public void setHandoverOffBadgeNo(String handoverOffBadgeNo) {
		this.handoverOffBadgeNo = handoverOffBadgeNo;
	}
	public String getHiddenTimeHr() {
		return hiddenTimeHr;
	}
	public void setHiddenTimeHr(String hiddenTimeHr) {
		this.hiddenTimeHr = hiddenTimeHr;
	}
	public String getHiddenTimeMin() {
		return hiddenTimeMin;
	}
	public void setHiddenTimeMin(String hiddenTimeMin) {
		this.hiddenTimeMin = hiddenTimeMin;
	}
	public String getIsPoliceVerReq() {
		return isPoliceVerReq;
	}
	public void setIsPoliceVerReq(String isPoliceVerReq) {
		this.isPoliceVerReq = isPoliceVerReq;
	}
	public String getConstableDesig() {
		return constableDesig;
	}
	public void setConstableDesig(String constableDesig) {
		this.constableDesig = constableDesig;
	}
	public String getConstableBadgeNo() {
		return constableBadgeNo;
	}
	public void setConstableBadgeNo(String constableBadgeNo) {
		this.constableBadgeNo = constableBadgeNo;
	}
	public String getCaseRemarks() {
		return caseRemarks;
	}
	public void setCaseRemarks(String caseRemarks) {
		this.caseRemarks = caseRemarks;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getIsDuplicateMlcNo() {
		return isDuplicateMlcNo;
	}
	public void setIsDuplicateMlcNo(String isDuplicateMlcNo) {
		this.isDuplicateMlcNo = isDuplicateMlcNo;
	}
	public String getSystemIPAddress() {
		return systemIPAddress;
	}
	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
	}
	public String getEpiDate() {
		return epiDate;
	}
	public void setEpiDate(String epiDate) {
		this.epiDate = epiDate;
	}
	public String getEpiTimeHr() {
		return epiTimeHr;
	}
	public void setEpiTimeHr(String epiTimeHr) {
		this.epiTimeHr = epiTimeHr;
	}
	public String getEpiTimeMin() {
		return epiTimeMin;
	}
	public void setEpiTimeMin(String epiTimeMin) {
		this.epiTimeMin = epiTimeMin;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getnVisitNoIndex() {
		return nVisitNoIndex;
	}
	public void setnVisitNoIndex(String nVisitNoIndex) {
		this.nVisitNoIndex = nVisitNoIndex;
	}
	public String getFinalOpinion() {
		return finalOpinion;
	}
	public void setFinalOpinion(String finalOpinion) {
		this.finalOpinion = finalOpinion;
	}
	public String getProvisionalOpinion() {
		return provisionalOpinion;
	}
	public void setProvisionalOpinion(String provisionalOpinion) {
		this.provisionalOpinion = provisionalOpinion;
	}
	public String getMlcStatus() {
		return mlcStatus;
	}
	public void setMlcStatus(String mlcStatus) {
		this.mlcStatus = mlcStatus;
	}
	public String getPatIdMark1() {
		return patIdMark1;
	}
	public void setPatIdMark1(String patIdMark1) {
		this.patIdMark1 = patIdMark1;
	}
	public String getPatIdMark2() {
		return patIdMark2;
	}
	public void setPatIdMark2(String patIdMark2) {
		this.patIdMark2 = patIdMark2;
	}
	public String getFlagMlcOnlineOffline() {
		return flagMlcOnlineOffline;
	}
	public void setFlagMlcOnlineOffline(String flagMlcOnlineOffline) {
		this.flagMlcOnlineOffline = flagMlcOnlineOffline;
	}
	public String getIsTodayOfflineVisit() {
		return isTodayOfflineVisit;
	}
	public void setIsTodayOfflineVisit(String isTodayOfflineVisit) {
		this.isTodayOfflineVisit = isTodayOfflineVisit;
	}
	public void reset(){
		
		patMlcNo		= "";
		mlcBookNo		= "";
		mlcPageNo		= "";
		mlcDate			= "";
		mlcTimeHr		= "";
		mlcTimeMin		= "";
		finalOpinion	= "";	
		patMlcStatusCode= "";
		fitnessStatus	= "";
		patCondition	= "";
		diagnosis		= "";
		referDoctorCode	= "";
		provisionalOpinion= "";
		referDocotorRemarks= "";
		
		//////
		mlcTypeCode 	= null;
		injurySide		= null;
		injuryNatureCode= null;
		typeOfWeapon	= null;
		injurySize		= null;
		injuryDepth		= null;
		burnPercentage	= null;
		poisonRemarks	= null;
		
		handoverOffName	= "";
		handoverOffDesg	= "";
		handoverOffBadgeNo= "";
		handoverDateTime= "";
	}
	public void clearMessageField(){
		this.setStrNormalMsg("");
		this.setErrorMessage("");
		this.setStrWarningMsg("");
		//this.setPrint("");
		//this.setSaveSuccessful("");
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public String getIsDesk() {
		return isDesk;
	}
	public void setIsDesk(String isDesk) {
		this.isDesk = isDesk;
	}	
	/*Start : Surabhi
	 * Reason : to get the patient mlc details for the certificate
	 * date : 7th oct 2016 */
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	// end
}
