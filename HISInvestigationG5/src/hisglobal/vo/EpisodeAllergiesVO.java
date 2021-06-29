package hisglobal.vo;

public class EpisodeAllergiesVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	private String allergiesCode;		//Allergy Type Code
	private String remarks;
	private String organCode;
	private String reasonCode;		//Allergy Code
	private String sensitivityCode;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String allergyTypeName;		//Allergy Type
	private String organName;
	private String sensitivityName;
	private String reasonName;			//Allergy Name 
	
	private String allergySite;
	private String allergySymtoms;
	private String advice;
	private String patAdmNo;
	
	private String allergySiteCode;
	private String allergySymtomsCode;
	private String duration;
	private String isRevoked;
	private String durationDays;
	private String allergyName;
	private String allergyTypeCode;
	private String episodeIsOpen;
	private String departmentUnitCode;
	private String hospitalCode;
	private String hospitalName;
	
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEpisodeIsOpen() {
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen) {
		this.episodeIsOpen = episodeIsOpen;
	}

	public String getAllergyTypeCode() {
		return allergyTypeCode;
	}

	public void setAllergyTypeCode(String allergyTypeCode) {
		this.allergyTypeCode = allergyTypeCode;
	}

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

	public String getAllergySite() {
		return allergySite;
	}

	public void setAllergySite(String allergySite) {
		this.allergySite = allergySite;
	}

	public String getAllergySymtoms() {
		return allergySymtoms;
	}

	public void setAllergySymtoms(String allergySymtoms) {
		this.allergySymtoms = allergySymtoms;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getAllergyTypeName()
	{
		return allergyTypeName;
	}

	public void setAllergyTypeName(String allergyTypeName)
	{
		this.allergyTypeName = allergyTypeName;
	}

	public String getOrganName()
	{
		return organName;
	}

	public void setOrganName(String organName)
	{
		this.organName = organName;
	}

	public String getSensitivityName()
	{
		return sensitivityName;
	}

	public void setSensitivityName(String sensitivityName)
	{
		this.sensitivityName = sensitivityName;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getAllergiesCode()
	{
		return allergiesCode;
	}

	public void setAllergiesCode(String allergiesCode)
	{
		this.allergiesCode = allergiesCode;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getOrganCode()
	{
		return organCode;
	}

	public void setOrganCode(String organCode)
	{
		this.organCode = organCode;
	}

	public String getReasonCode()
	{
		return reasonCode;
	}

	public void setReasonCode(String reasonCode)
	{
		this.reasonCode = reasonCode;
	}

	public String getSensitivityCode()
	{
		return sensitivityCode;
	}

	public void setSensitivityCode(String sensitivityCode)
	{
		this.sensitivityCode = sensitivityCode;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getReasonName()
	{
		return reasonName;
	}

	public void setReasonName(String reasonName)
	{
		this.reasonName = reasonName;
	}

	public String getAllergySiteCode() {
		return allergySiteCode;
	}

	public void setAllergySiteCode(String allergySiteCode) {
		this.allergySiteCode = allergySiteCode;
	}

	public String getAllergySymtomsCode() {
		return allergySymtomsCode;
	}

	public void setAllergySymtomsCode(String allergySymtomsCode) {
		this.allergySymtomsCode = allergySymtomsCode;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getIsRevoked() {
		return isRevoked;
	}

	public void setIsRevoked(String isRevoked) {
		this.isRevoked = isRevoked;
	}

	public String getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(String durationDays) {
		this.durationDays = durationDays;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

}
