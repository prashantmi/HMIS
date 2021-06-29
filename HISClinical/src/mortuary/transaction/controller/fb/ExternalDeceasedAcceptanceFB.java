package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ExternalDeceasedAcceptanceFB extends ActionForm
{
	private String hmode;
	private String deceasedFrom;
	private String deceasedNo;
	private String patCrNo;
	private String entryMode;
	private String receivedDateTime;
	private String receivedBy;
	private String deathDate="";
	private String bodyStatus;
	private String unidentifiedBody;
	private String isBroughtDead;
	private String isMlcCase;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGenderCode;
	private String patDOB="";
	private String isActualDob;
	private String patMotherName;
	private String patHusbandName;
	private String patGuardianName;
	private String patMaritalStatusCode;
	private String add1;
	private String add2;
	private String contactNo;
	private String diagnosis;
	private String deathHistory;
	private String isPregnant;
	private String obstetricHistory;
	private String patientIdMark1;
	private String patientIdMark2;
	private String extHospitalName;
	private String extDeptName;
	private String extPatCrNo;
	private String extPatAdmNo;
	private String extUnitName;
	private String extWardName;
	private String extBedNo;
	private String doctorName;
	private String extHospitalContactNo;
	private String mlcNo;
	private String patAgeUnit;
	private String patAge;
	private String deathTimeHr;
	private String deathTimeMin;
	private String deathDateTime;
	private String hiddenTimeMin;
	private String hiddenTimeHr;
	private String sysDate="";
	private String isClaimed;
	private String policeStnAccmponiedBy;
	private String policeContactNo;
	private String remarks;
	private String placeType;
	private String patID;
	private String plcAddress;
	private String plcContact;
	
	
/////New Police Verification
	private String caseNo;
	private String policeStation;
	private String docketNo;
	private String officerIncharge;
	private String ioDesignation;
	private String ioBatchNo;
	private String dutyOfficeFlag;
	private String dutyOffName;
	private String dutyOffDesignation;
	private String dutyOffBatchNo;
	private String caseRemarks;
	
	private String relativeAddress;
	private String relativeContactNo;
	private String relativeCode;
	private String relativeName;
	
	private String storageFlag;
	private String bodyPutBy;
	private String storageReason;
	private String chamberId;
	private String chamberRackId;
	private String storageUpto;
	private String unknownChkValue;
	private String mlcChkValue;
	private String broughtLocation;
	
	private String isAssociated;
	private String assoHospitalCode;
	
	private String brtOffName;
	private String brtOffDesignation;
	private String brtOffBatchNo;
	
	private String isAccompained;
	private String accRelativeAddress;
	private String accRelativeContactNo;
	private String accRelativeCode;
	private String accRelativeName;
	
	
	public String getIsAccompained() {
		return isAccompained;
	}
	public void setIsAccompained(String isAccompained) {
		this.isAccompained = isAccompained;
	}
	public String getAssoHospitalCode() {
		return assoHospitalCode;
	}
	public void setAssoHospitalCode(String assoHospitalCode) {
		this.assoHospitalCode = assoHospitalCode;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getDocketNo() {
		return docketNo;
	}
	public void setDocketNo(String docketNo) {
		this.docketNo = docketNo;
	}
	public String getOfficerIncharge() {
		return officerIncharge;
	}
	public void setOfficerIncharge(String officerIncharge) {
		this.officerIncharge = officerIncharge;
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
	public String getCaseRemarks() {
		return caseRemarks;
	}
	public void setCaseRemarks(String caseRemarks) {
		this.caseRemarks = caseRemarks;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDeceasedFrom() {
		return deceasedFrom;
	}
	public void setDeceasedFrom(String deceasedFrom) {
		this.deceasedFrom = deceasedFrom;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}
	public String getReceivedDateTime() {
		return receivedDateTime;
	}
	public void setReceivedDateTime(String receivedDateTime) {
		this.receivedDateTime = receivedDateTime;
	}
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getBodyStatus() {
		return bodyStatus;
	}
	public void setBodyStatus(String bodyStatus) {
		this.bodyStatus = bodyStatus;
	}
	public String getUnidentifiedBody() {
		return unidentifiedBody;
	}
	public void setUnidentifiedBody(String unidentifiedBody) {
		this.unidentifiedBody = unidentifiedBody;
	}
	public String getIsBroughtDead() {
		return isBroughtDead;
	}
	public void setIsBroughtDead(String isBroughtDead) {
		this.isBroughtDead = isBroughtDead;
	}
	public String getIsMlcCase() {
		return isMlcCase;
	}
	public void setIsMlcCase(String isMlcCase) {
		this.isMlcCase = isMlcCase;
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
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getIsActualDob() {
		return isActualDob;
	}
	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}
	public String getPatMotherName() {
		return patMotherName;
	}
	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatMaritalStatusCode() {
		return patMaritalStatusCode;
	}
	public void setPatMaritalStatusCode(String patMaritalStatusCode) {
		this.patMaritalStatusCode = patMaritalStatusCode;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDeathHistory() {
		return deathHistory;
	}
	public void setDeathHistory(String deathHistory) {
		this.deathHistory = deathHistory;
	}
	public String getIsPregnant() {
		return isPregnant;
	}
	public void setIsPregnant(String isPregnant) {
		this.isPregnant = isPregnant;
	}
	public String getObstetricHistory() {
		return obstetricHistory;
	}
	public void setObstetricHistory(String obstetricHistory) {
		this.obstetricHistory = obstetricHistory;
	}
	public String getExtHospitalName() {
		return extHospitalName;
	}
	public void setExtHospitalName(String extHospitalName) {
		this.extHospitalName = extHospitalName;
	}
	public String getExtDeptName() {
		return extDeptName;
	}
	public void setExtDeptName(String extDeptName) {
		this.extDeptName = extDeptName;
	}
	public String getExtPatCrNo() {
		return extPatCrNo;
	}
	public void setExtPatCrNo(String extPatCrNo) {
		this.extPatCrNo = extPatCrNo;
	}
	public String getExtPatAdmNo() {
		return extPatAdmNo;
	}
	public void setExtPatAdmNo(String extPatAdmNo) {
		this.extPatAdmNo = extPatAdmNo;
	}
	public String getExtUnitName() {
		return extUnitName;
	}
	public void setExtUnitName(String extUnitName) {
		this.extUnitName = extUnitName;
	}
	public String getExtWardName() {
		return extWardName;
	}
	public void setExtWardName(String extWardName) {
		this.extWardName = extWardName;
	}
	public String getExtBedNo() {
		return extBedNo;
	}
	public void setExtBedNo(String extBedNo) {
		this.extBedNo = extBedNo;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getExtHospitalContactNo() {
		return extHospitalContactNo;
	}
	public void setExtHospitalContactNo(String extHospitalContactNo) {
		this.extHospitalContactNo = extHospitalContactNo;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getDeathTimeHr() {
		return deathTimeHr;
	}
	public void setDeathTimeHr(String deathTimeHr) {
		this.deathTimeHr = deathTimeHr;
	}
	public String getDeathTimeMin() {
		return deathTimeMin;
	}
	public void setDeathTimeMin(String deathTimeMin) {
		this.deathTimeMin = deathTimeMin;
	}
	public String getDeathDateTime() {
		return deathDateTime;
	}
	public void setDeathDateTime(String deathDateTime) {
		this.deathDateTime = deathDateTime;
	}
	public String getRelativeAddress() {
		return relativeAddress;
	}
	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}
	public String getRelativeContactNo() {
		return relativeContactNo;
	}
	public void setRelativeContactNo(String relativeContactNo) {
		this.relativeContactNo = relativeContactNo;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getStorageFlag() {
		return storageFlag;
	}
	public void setStorageFlag(String storageFlag) {
		this.storageFlag = storageFlag;
	}
	public String getBodyPutBy() {
		return bodyPutBy;
	}
	public void setBodyPutBy(String bodyPutBy) {
		this.bodyPutBy = bodyPutBy;
	}
	public String getStorageReason() {
		return storageReason;
	}
	public void setStorageReason(String storageReason) {
		this.storageReason = storageReason;
	}
	public String getChamberId() {
		return chamberId;
	}
	public void setChamberId(String chamberId) {
		this.chamberId = chamberId;
	}
	public String getChamberRackId() {
		return chamberRackId;
	}
	public void setChamberRackId(String chamberRackId) {
		this.chamberRackId = chamberRackId;
	}
	public String getStorageUpto() {
		return storageUpto;
	}
	public void setStorageUpto(String storageUpto) {
		this.storageUpto = storageUpto;
	}
	public String getPatientIdMark1() {
		return patientIdMark1;
	}
	public void setPatientIdMark1(String patientIdMark1) {
		this.patientIdMark1 = patientIdMark1;
	}
	public String getPatientIdMark2() {
		return patientIdMark2;
	}
	public void setPatientIdMark2(String patientIdMark2) {
		this.patientIdMark2 = patientIdMark2;
	}
	public String getUnknownChkValue() {
		return unknownChkValue;
	}
	public void setUnknownChkValue(String unknownChkValue) {
		this.unknownChkValue = unknownChkValue;
	}
	public String getMlcChkValue() {
		return mlcChkValue;
	}
	public void setMlcChkValue(String mlcChkValue) {
		this.mlcChkValue = mlcChkValue;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setUnidentifiedBody("");
		this.setIsMlcCase("");
		this.setPatFirstName("");
		this.setPatMiddleName("");
		this.setPatLastName("");
		this.setIsActualDob("");
		this.setPatAge("");
		this.setPatDOB("");
		this.setPatGenderCode("");
		this.setPatGuardianName("");
		this.setPatMotherName("");
		this.setPatMaritalStatusCode("");
		this.setPatHusbandName("");
		this.setDeathDate("");
		this.setDeathTimeHr("");
		this.setDeathTimeMin("");
		this.setPatientIdMark1("");
		this.setPatientIdMark2("");
		this.setAdd1("");
		this.setAdd2("");
		this.setContactNo("");
		this.setMlcNo("");
		
		this.setExtHospitalName("");
		this.setExtPatCrNo("");
		this.setExtPatAdmNo("");
		this.setExtDeptName("");
		this.setExtUnitName("");
		this.setExtWardName("");
		this.setExtBedNo("");
		this.setDoctorName("");
		this.setExtHospitalContactNo("");
		
		this.setCaseNo("");
		this.setPoliceStation("");
		this.setDocketNo("");
		this.setOfficerIncharge("");
		this.setIoDesignation("");
		this.setIoBatchNo("");
		this.setDutyOffName("");
		this.setDutyOffDesignation("");
		this.setDutyOffBatchNo("");
		this.setCaseRemarks("");
		
		this.setRelativeName("");
		this.setRelativeCode("");
		this.setRelativeAddress("");
		this.setRelativeContactNo("");
		
		this.setChamberId("");
		this.setChamberRackId("");
		this.setBodyPutBy("");
		this.setStorageUpto("");
		this.setStorageReason("");
	}
	public String getBroughtLocation() {
		return broughtLocation;
	}
	public void setBroughtLocation(String broughtLocation) {
		this.broughtLocation = broughtLocation;
	}
	public String getHiddenTimeHr() {
		return hiddenTimeHr;
	}
	public void setHiddenTimeHr(String hiddenTimeHr) {
		this.hiddenTimeHr = hiddenTimeHr;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getHiddenTimeMin() {
		return hiddenTimeMin;
	}
	public void setHiddenTimeMin(String hiddenTimeMin) {
		this.hiddenTimeMin = hiddenTimeMin;
	}
	public String getIsClaimed() {
		return isClaimed;
	}
	public void setIsClaimed(String isClaimed) {
		this.isClaimed = isClaimed;
	}
	public String getIsAssociated() {
		return isAssociated;
	}
	public void setIsAssociated(String isAssociated) {
		this.isAssociated = isAssociated;
	}
	public String getBrtOffName() {
		return brtOffName;
	}
	public void setBrtOffName(String brtOffName) {
		this.brtOffName = brtOffName;
	}
	public String getBrtOffDesignation() {
		return brtOffDesignation;
	}
	public void setBrtOffDesignation(String brtOffDesignation) {
		this.brtOffDesignation = brtOffDesignation;
	}
	public String getBrtOffBatchNo() {
		return brtOffBatchNo;
	}
	public void setBrtOffBatchNo(String brtOffBatchNo) {
		this.brtOffBatchNo = brtOffBatchNo;
	}
	public String getAccRelativeAddress() {
		return accRelativeAddress;
	}
	public void setAccRelativeAddress(String accRelativeAddress) {
		this.accRelativeAddress = accRelativeAddress;
	}
	public String getAccRelativeContactNo() {
		return accRelativeContactNo;
	}
	public void setAccRelativeContactNo(String accRelativeContactNo) {
		this.accRelativeContactNo = accRelativeContactNo;
	}
	public String getAccRelativeCode() {
		return accRelativeCode;
	}
	public void setAccRelativeCode(String accRelativeCode) {
		this.accRelativeCode = accRelativeCode;
	}
	public String getAccRelativeName() {
		return accRelativeName;
	}
	public void setAccRelativeName(String accRelativeName) {
		this.accRelativeName = accRelativeName;
	}
	public String getPoliceStnAccmponiedBy() {
		return policeStnAccmponiedBy;
	}
	public void setPoliceStnAccmponiedBy(String policeStnAccmponiedBy) {
		this.policeStnAccmponiedBy = policeStnAccmponiedBy;
	}
	public String getPoliceContactNo() {
		return policeContactNo;
	}
	public void setPoliceContactNo(String policeContactNo) {
		this.policeContactNo = policeContactNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPlaceType() {
		return placeType;
	}
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	public String getPatID() {
		return patID;
	}
	public void setPatID(String patID) {
		this.patID = patID;
	}
	public String getPlcAddress() {
		return plcAddress;
	}
	public void setPlcAddress(String plcAddress) {
		this.plcAddress = plcAddress;
	}
	public String getPlcContact() {
		return plcContact;
	}
	public void setPlcContact(String plcContact) {
		this.plcContact = plcContact;
	}
}
