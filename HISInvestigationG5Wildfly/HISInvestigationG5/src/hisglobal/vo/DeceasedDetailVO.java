package hisglobal.vo;

public class DeceasedDetailVO extends ValueObject
{
	private String deceasedNo;
	private String patCrNo;
	private String entryMode;
	private String receivedDateTime;
	private String receivedBy;
	private String deathDate;
	private String bodyStatus;
	private String bodyStatusName;
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
	private String patIdMark1;
	private String patIdMark2;
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
	private String episodeCode;
	private String episodeVisitNo;
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
	private String length;
	private String weight;
	private String bodyLooks;
	private String relativeName;
	private String relativeAddress;
	private String relativeContactNo;
	private String relativeCode;
	private String isDecomposition;
	private String storageUpto;
	private String chamberRackId;
	private String color;
	private String receivedByName;
	private String patGender;
	private String mlcNo;
	private String deceasedType;
	
	/////Image
	private byte[] imageFile;
	private String imageFileName;
	
	private String postmortemId;
	private String requestDateTime;
	private String postmortemStatus;
	private String caseNo;
	private String patAge;
	private String isClaimed;
	private String performDateTime;

	private String postmortemReq;
	///PostMortem Details
	private String postmortemType;
	private String postmortemIdLabel;
	
	private String patAgeUnit;
	private String srNo;
	
	
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getIsClaimed() {
		return isClaimed;
	}
	public void setIsClaimed(String isClaimed) {
		this.isClaimed = isClaimed;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public String getPostmortemStatus() {
		return postmortemStatus;
	}
	public void setPostmortemStatus(String postmortemStatus) {
		this.postmortemStatus = postmortemStatus;
	}
	public String getChamberRackId() {
		return chamberRackId;
	}
	public void setChamberRackId(String chamberRackId) {
		this.chamberRackId = chamberRackId;
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
	public String getIsDecomposition() {
		return isDecomposition;
	}
	public void setIsDecomposition(String isDecomposition) {
		this.isDecomposition = isDecomposition;
	}
	public String getStorageUpto() {
		return storageUpto;
	}
	public void setStorageUpto(String storageUpto) {
		this.storageUpto = storageUpto;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
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
	public String getBodyStatusName() {
		return bodyStatusName;
	}
	public void setBodyStatusName(String bodyStatusName) {
		this.bodyStatusName = bodyStatusName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getReceivedByName() {
		return receivedByName;
	}
	public void setReceivedByName(String receivedByName) {
		this.receivedByName = receivedByName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPerformDateTime() {
		return performDateTime;
	}
	public void setPerformDateTime(String performDateTime) {
		this.performDateTime = performDateTime;
	}
	public String getPostmortemReq() {
		return postmortemReq;
	}
	public void setPostmortemReq(String postmortemReq) {
		this.postmortemReq = postmortemReq;
	}
	public String getPostmortemType() {
		return postmortemType;
	}
	public void setPostmortemType(String postmortemType) {
		this.postmortemType = postmortemType;
	}
	public String getPostmortemIdLabel() {
		return postmortemIdLabel;
	}
	public void setPostmortemIdLabel(String postmortemIdLabel) {
		this.postmortemIdLabel = postmortemIdLabel;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getDeceasedType() {
		return deceasedType;
	}
	public void setDeceasedType(String deceasedType) {
		this.deceasedType = deceasedType;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
}
