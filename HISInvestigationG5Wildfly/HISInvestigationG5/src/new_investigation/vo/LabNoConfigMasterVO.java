package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class LabNoConfigMasterVO extends ValueObject 
{

	private String testCode;
	private String seqNo;
	private String patientType;
	private String seqDigit;
	private String labNoFormat;
	private String labCode;
	private String hospitalCode;
	private String initializationType;
	private String fromSeries;
	private String toSeries;
	private String initDate;
	private String reinitDate;
	private String partOne;
	private String partTwo;
	private String isActive;
	private String seriesFormat;
	private String sampleAcceptanceAreaCode;
	private String acceptanceAreawise;


	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getSeqDigit() {
		return seqDigit;
	}
	public void setSeqDigit(String seqDigit) {
		this.seqDigit = seqDigit;
	}
	public String getLabNoFormat() {
		return labNoFormat;
	}
	public void setLabNoFormat(String labNoFormat) {
		this.labNoFormat = labNoFormat;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getInitializationType() {
		return initializationType;
	}
	public void setInitializationType(String initializationType) {
		this.initializationType = initializationType;
	}
	
	public String getFromSeries() {
		return fromSeries;
	}
	public void setFromSeries(String fromSeries) {
		this.fromSeries = fromSeries;
	}
	public String getToSeries() {
		return toSeries;
	}
	public void setToSeries(String toSeries) {
		this.toSeries = toSeries;
	}
	public String getInitDate() {
		return initDate;
	}
	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}
	public String getReinitDate() {
		return reinitDate;
	}
	public void setReinitDate(String reinitDate) {
		this.reinitDate = reinitDate;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getPartOne() {
		return partOne;
	}
	public void setPartOne(String partOne) {
		this.partOne = partOne;
	}
	public String getPartTwo() {
		return partTwo;
	}
	public void setPartTwo(String partTwo) {
		this.partTwo = partTwo;
	}
	public String getSeriesFormat() {
		return seriesFormat;
	}
	public void setSeriesFormat(String seriesFormat) {
		this.seriesFormat = seriesFormat;
	}
	public String getSampleAcceptanceAreaCode() {
		return sampleAcceptanceAreaCode;
	}
	public void setSampleAcceptanceAreaCode(String sampleAcceptanceAreaCode) {
		this.sampleAcceptanceAreaCode = sampleAcceptanceAreaCode;
	}
	public String getAcceptanceAreawise() {
		return acceptanceAreawise;
	}
	public void setAcceptanceAreawise(String acceptanceAreawise) {
		this.acceptanceAreawise = acceptanceAreawise;
	}
	

}
