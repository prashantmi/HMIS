package hisglobal.vo;

public class EpisodeDiagnosisVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String serialNo;
	private String patAdmNo;
	private String episodeVisitNo;
	private String diagnosticTypeCode;
	private String diagnosticCode; // HRGNUM_DIAGNOSTIC_CODE---?????
	private String seatId;
	private String entryDate;
	private String isValid;
	private String episodeDate;
	private String remarks;
	private String dignosisName;
	private String diagnosticTypeName;
	private String diagnosisCodeType;
	private String maxVisitNo;
	private String isRepeat;
	private String departmentUnitCode;
	private String diagnosisSite;
	private String diagnosisCodeLabel;
	private String diagnosisSiteLabel;

	private String episodeIsOpen;

	private String hospitalCode;
	private String hospitalName;
	
	public String getEpisodeIsOpen() {
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen) {
		this.episodeIsOpen = episodeIsOpen;
	}

	private String diagnosisCheckFlag;


	public String getMaxVisitNo()
	{
		return maxVisitNo;
	}

	public void setMaxVisitNo(String maxVisitNo)
	{
		this.maxVisitNo = maxVisitNo;
	}

	public String getDiagnosisCodeType()
	{
		return diagnosisCodeType;
	}

	public void setDiagnosisCodeType(String diagnosisCodeType)
	{
		this.diagnosisCodeType = diagnosisCodeType;
	}

	public String getDiagnosticTypeName()
	{
		return diagnosticTypeName;
	}

	public void setDiagnosticTypeName(String diagnosticTypeName)
	{
		this.diagnosticTypeName = diagnosticTypeName;
	}

	public String getDignosisName()
	{
		return dignosisName;
	}

	public void setDignosisName(String dignosisName)
	{
		this.dignosisName = dignosisName;
	}

	public String getEpisodeDate()
	{
		return episodeDate;
	}

	public void setEpisodeDate(String episodeDate)
	{
		this.episodeDate = episodeDate;
	}

	public String getDiagnosticCode()
	{
		return diagnosticCode;
	}

	public void setDiagnosticCode(String diagnosticCode)
	{
		this.diagnosticCode = diagnosticCode;
	}

	public String getDiagnosticTypeCode()
	{
		return diagnosticTypeCode;
	}

	public void setDiagnosticTypeCode(String diagnosticTypeCode)
	{
		this.diagnosticTypeCode = diagnosticTypeCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
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

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getDiagnosisCheckFlag() {
		return diagnosisCheckFlag;
	}

	public void setDiagnosisCheckFlag(String diagnosisCheckFlag) {
		this.diagnosisCheckFlag = diagnosisCheckFlag;
	}

	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getDiagnosisSite() {
		return diagnosisSite;
	}

	public void setDiagnosisSite(String diagnosisSite) {
		this.diagnosisSite = diagnosisSite;
	}

	public String getDiagnosisCodeLabel() {
		return diagnosisCodeLabel;
	}

	public void setDiagnosisCodeLabel(String diagnosisCodeLabel) {
		this.diagnosisCodeLabel = diagnosisCodeLabel;
	}

	public String getDiagnosisSiteLabel() {
		return diagnosisSiteLabel;
	}

	public void setDiagnosisSiteLabel(String diagnosisSiteLabel) {
		this.diagnosisSiteLabel = diagnosisSiteLabel;
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
