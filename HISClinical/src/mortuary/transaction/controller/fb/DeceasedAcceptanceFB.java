package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeceasedAcceptanceFB extends ActionForm
{
	private String patCrNo;
	private String hmode;
	private String entryMode;
	private String receivedDateTime;
	private String receivedBy;
	private String deathDateTime;
	private String bodyStatus;
	private String unidentifiedBody;
	private String isBroughtDead;
	private String isMlcCase;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGenderCode;
	private String patDOB;
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
	private String idMark1;
	private String idMark2;
	private String extHospitalName;
	private String extDeptName;
	private String extPatCrNo;
	private String extPatAdmNo;
	private String extUnitName;
	private String extWardName;
	private String extBedNo;
	private String doctorName;
	private String extHospitalContactNo;
	private String issueDateTime;
	private String ipAdd;
	private String patMlcNo;
	private String episodeCode;
	private String episodeVisitNo;
//	private String patAdmNo;
	private String departmentCode;
	private String departmentUnitCode;
	private String wardCode;
	private String roomNo;
	private String theatreCode;
	private String complexion;
	private String hairColorLength;
	private String eyeColor;
	private String height;
	private String clothDetails;
	private String briefDetails;
	private String weight;
	private String bodyLooks;
	private String relativeName;
	private String relativeAddress;
	private String relativeContactNo;
	private String relativeCode;
	private String deadPatRadio;
	private String isBroughtBy;
	private String broughtByName;
	private String handoverStorageFlag;
	private String officerName;
	private String officerDesignation;
	private String officerBadgeNo;
	private String bodyHandoverTo;
	private String bodyPutBy;
	private String storageReason;
	private String chamberId;
	private String chamberRackId;
	private String storageUpto;
	private String isStorageByRelative;
	private String isClaimed;
	private String deathDuration;
	private String fromUnit;
	private String storageFlag;
	private String deathEnteredBy;
	private String policeContactNo;
	private String itemDesc;
	private String policeStnHandOver;
	
	/////Existing Police Verification
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
//	private String isBroughtDead;
	private String caseRemarks;
	private String policeVerificationFlag;
	private String policeVerificationExist;
	
	
	/////New Police Verification
	private String newCaseNo;
	private String newPoliceStation;
	private String newDocketNo;
	private String newOfficerIncharge;
	private String newIoDesignation;
	private String newIoBatchNo;
	private String newDutyOfficeFlag;
	private String newDutyOffName;
	private String newDutyOffDesignation;
	private String newDutyOffBatchNo;
	private String newCaseRemarks;
	
	/////Image
	private byte[] imageFile;
	private String imageFileName;
	private String existingImage;
	private String newImage;
	private String isDefaultImage;
	private String imageExistFlag;
	private String[] selectedExistingImage; 
	private String removeImageIndex;
	private String newAddImageExistFlag;
	private String tempChkValue;
	
	////Storage Relative
	private String storageRelativeName;
	private String storageRelativeAddress;
	private String storageRelativeContactNo;
	private String storageRelativeCode;
	
	public String getTempChkValue() {
		return tempChkValue;
	}
	public void setTempChkValue(String tempChkValue) {
		this.tempChkValue = tempChkValue;
	}
	public String getRemoveImageIndex() {
		return removeImageIndex;
	}
	public void setRemoveImageIndex(String removeImageIndex) {
		this.removeImageIndex = removeImageIndex;
	}
	public String[] getSelectedExistingImage() {
		return selectedExistingImage;
	}
	public void setSelectedExistingImage(String[] selectedExistingImage) {
		this.selectedExistingImage = selectedExistingImage;
	}
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getPoliceVerificationFlag() {
		return policeVerificationFlag;
	}
	public void setPoliceVerificationFlag(String policeVerificationFlag) {
		this.policeVerificationFlag = policeVerificationFlag;
	}
	public String getBodyHandoverTo() {
		return bodyHandoverTo;
	}
	public void setBodyHandoverTo(String bodyHandoverTo) {
		this.bodyHandoverTo = bodyHandoverTo;
	}
	public String getDeadPatRadio() {
		return deadPatRadio;
	}
	public void setDeadPatRadio(String deadPatRadio) {
		this.deadPatRadio = deadPatRadio;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getDeathDateTime() {
		return deathDateTime;
	}
	public void setDeathDateTime(String deathDateTime) {
		this.deathDateTime = deathDateTime;
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
	public String getIssueDateTime() {
		return issueDateTime;
	}
	public void setIssueDateTime(String issueDateTime) {
		this.issueDateTime = issueDateTime;
	}
	public String getIpAdd() {
		return ipAdd;
	}
	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getTheatreCode() {
		return theatreCode;
	}
	public void setTheatreCode(String theatreCode) {
		this.theatreCode = theatreCode;
	}
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}
	public String getHairColorLength() {
		return hairColorLength;
	}
	public void setHairColorLength(String hairColorLength) {
		this.hairColorLength = hairColorLength;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getClothDetails() {
		return clothDetails;
	}
	public void setClothDetails(String clothDetails) {
		this.clothDetails = clothDetails;
	}
	public String getBriefDetails() {
		return briefDetails;
	}
	public void setBriefDetails(String briefDetails) {
		this.briefDetails = briefDetails;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBodyLooks() {
		return bodyLooks;
	}
	public void setBodyLooks(String bodyLooks) {
		this.bodyLooks = bodyLooks;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
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
	public String getIsBroughtBy() {
		return isBroughtBy;
	}
	public void setIsBroughtBy(String isBroughtBy) {
		this.isBroughtBy = isBroughtBy;
	}
	public String getBroughtByName() {
		return broughtByName;
	}
	public void setBroughtByName(String broughtByName) {
		this.broughtByName = broughtByName;
	}
	public String getHandoverStorageFlag() {
		return handoverStorageFlag;
	}
	public void setHandoverStorageFlag(String handoverStorageFlag) {
		this.handoverStorageFlag = handoverStorageFlag;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getOfficerDesignation() {
		return officerDesignation;
	}
	public void setOfficerDesignation(String officerDesignation) {
		this.officerDesignation = officerDesignation;
	}
	public String getOfficerBadgeNo() {
		return officerBadgeNo;
	}
	public void setOfficerBadgeNo(String officerBadgeNo) {
		this.officerBadgeNo = officerBadgeNo;
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
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}
	public String getPoliceVerificationExist() {
		return policeVerificationExist;
	}
	public void setPoliceVerificationExist(String policeVerificationExist) {
		this.policeVerificationExist = policeVerificationExist;
	}
	public String getNewCaseNo() {
		return newCaseNo;
	}
	public void setNewCaseNo(String newCaseNo) {
		this.newCaseNo = newCaseNo;
	}
	public String getNewPoliceStation() {
		return newPoliceStation;
	}
	public void setNewPoliceStation(String newPoliceStation) {
		this.newPoliceStation = newPoliceStation;
	}
	public String getNewDocketNo() {
		return newDocketNo;
	}
	public void setNewDocketNo(String newDocketNo) {
		this.newDocketNo = newDocketNo;
	}
	public String getNewOfficerIncharge() {
		return newOfficerIncharge;
	}
	public void setNewOfficerIncharge(String newOfficerIncharge) {
		this.newOfficerIncharge = newOfficerIncharge;
	}
	public String getNewIoDesignation() {
		return newIoDesignation;
	}
	public void setNewIoDesignation(String newIoDesignation) {
		this.newIoDesignation = newIoDesignation;
	}
	public String getNewIoBatchNo() {
		return newIoBatchNo;
	}
	public void setNewIoBatchNo(String newIoBatchNo) {
		this.newIoBatchNo = newIoBatchNo;
	}
	public String getNewDutyOfficeFlag() {
		return newDutyOfficeFlag;
	}
	public void setNewDutyOfficeFlag(String newDutyOfficeFlag) {
		this.newDutyOfficeFlag = newDutyOfficeFlag;
	}
	public String getNewDutyOffName() {
		return newDutyOffName;
	}
	public void setNewDutyOffName(String newDutyOffName) {
		this.newDutyOffName = newDutyOffName;
	}
	public String getNewDutyOffDesignation() {
		return newDutyOffDesignation;
	}
	public void setNewDutyOffDesignation(String newDutyOffDesignation) {
		this.newDutyOffDesignation = newDutyOffDesignation;
	}
	public String getNewDutyOffBatchNo() {
		return newDutyOffBatchNo;
	}
	public void setNewDutyOffBatchNo(String newDutyOffBatchNo) {
		this.newDutyOffBatchNo = newDutyOffBatchNo;
	}
	public String getNewCaseRemarks() {
		return newCaseRemarks;
	}
	public void setNewCaseRemarks(String newCaseRemarks) {
		this.newCaseRemarks = newCaseRemarks;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setBroughtByName("");
		
	}
	public String getExistingImage() {
		return existingImage;
	}
	public void setExistingImage(String existingImage) {
		this.existingImage = existingImage;
	}
	public String getNewImage() {
		return newImage;
	}
	public void setNewImage(String newImage) {
		this.newImage = newImage;
	}
	public String getIsDefaultImage() {
		return isDefaultImage;
	}
	public void setIsDefaultImage(String isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}
	public String getImageExistFlag() {
		return imageExistFlag;
	}
	public void setImageExistFlag(String imageExistFlag) {
		this.imageExistFlag = imageExistFlag;
	}
	public String getNewAddImageExistFlag() {
		return newAddImageExistFlag;
	}
	public void setNewAddImageExistFlag(String newAddImageExistFlag) {
		this.newAddImageExistFlag = newAddImageExistFlag;
	}
	public String getStorageUpto() {
		return storageUpto;
	}
	public void setStorageUpto(String storageUpto) {
		this.storageUpto = storageUpto;
	}
	public String getIsStorageByRelative() {
		return isStorageByRelative;
	}
	public void setIsStorageByRelative(String isStorageByRelative) {
		this.isStorageByRelative = isStorageByRelative;
	}
	public String getStorageRelativeName() {
		return storageRelativeName;
	}
	public void setStorageRelativeName(String storageRelativeName) {
		this.storageRelativeName = storageRelativeName;
	}
	public String getStorageRelativeAddress() {
		return storageRelativeAddress;
	}
	public void setStorageRelativeAddress(String storageRelativeAddress) {
		this.storageRelativeAddress = storageRelativeAddress;
	}
	public String getStorageRelativeContactNo() {
		return storageRelativeContactNo;
	}
	public void setStorageRelativeContactNo(String storageRelativeContactNo) {
		this.storageRelativeContactNo = storageRelativeContactNo;
	}
	public String getStorageRelativeCode() {
		return storageRelativeCode;
	}
	public void setStorageRelativeCode(String storageRelativeCode) {
		this.storageRelativeCode = storageRelativeCode;
	}
	public String getIsClaimed() {
		return isClaimed;
	}
	public void setIsClaimed(String isClaimed) {
		this.isClaimed = isClaimed;
	}
	public String getIdMark1() {
		return idMark1;
	}
	public void setIdMark1(String idMark1) {
		this.idMark1 = idMark1;
	}
	public String getIdMark2() {
		return idMark2;
	}
	public void setIdMark2(String idMark2) {
		this.idMark2 = idMark2;
	}
	public String getDeathDuration() {
		return deathDuration;
	}
	public void setDeathDuration(String deathDuration) {
		this.deathDuration = deathDuration;
	}
	public String getFromUnit() {
		return fromUnit;
	}
	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
	}
	public String getStorageFlag() {
		return storageFlag;
	}
	public void setStorageFlag(String storageFlag) {
		this.storageFlag = storageFlag;
	}
	public String getDeathEnteredBy() {
		return deathEnteredBy;
	}
	public void setDeathEnteredBy(String deathEnteredBy) {
		this.deathEnteredBy = deathEnteredBy;
	}
	public String getPoliceContactNo() {
		return policeContactNo;
	}
	public void setPoliceContactNo(String policeContactNo) {
		this.policeContactNo = policeContactNo;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getPoliceStnHandOver() {
		return policeStnHandOver;
	}
	public void setPoliceStnHandOver(String policeStnHandOver) {
		this.policeStnHandOver = policeStnHandOver;
	}
	
}
