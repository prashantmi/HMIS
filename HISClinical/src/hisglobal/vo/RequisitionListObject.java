package hisglobal.vo;

import hisglobal.vo.ValueObject;
import investigation.vo.Sample;
import investigation.usefulmethods.Entry;

import java.util.List;

public class RequisitionListObject extends ValueObject{

	private String labName;
	private String labCode;
	private String testName;
	private String requisitionNo;
	private String requisitionDate;
	private String requisitionType;
	private String patCRNo;
	private String patDOB;
	private String patAge;
	private String patName;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String hospitalCode;
	private String hospitalName;
	private String patEpisodeCode;
	private String patVisitDate;
	private String patVisitNo;
	private String sampleID;
	private List<Sample> sampleList;
	private String requisitionDNo;
	private String patAdmissionNo;
	private String status; 
	private String sessionNo;
	private String testType;
	private String proposedtestdate;
	private String transferredToLab;
	private List<Entry> transferredLabList;
	private String isSelected="0";
	private String patStatusCode;
	private String sampleNo;
	private String testCode;
	private String filmNo;
	private String sampleName;
	private String testDay;
	private String patGender;
	
	
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getTestDay() {
		return testDay;
	}
	public void setTestDay(String testDay) {
		this.testDay = testDay;
	}
	public String getFilmNo() {
		return filmNo;
	}
	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
	private String patdeptunit;

	
	public String getPatdeptunit() {
		return patdeptunit;
	}
	public void setPatdeptunit(String patdeptunit) {
		this.patdeptunit = patdeptunit;
	}
	public String getPatStatusCode() {
		return patStatusCode;
	}
	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
	}
	public List<Entry> getTransferredLabList() {
		return transferredLabList;
	}
	public void setTransferredLabList(List<Entry> transferredLabList) {
		this.transferredLabList = transferredLabList;
	}
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	public String getTransferredToLab() {
		return transferredToLab;
	}
	public void setTransferredToLab(String transferredToLab) {
		this.transferredToLab = transferredToLab;
	}
	public String getProposedtestdate() {
		return proposedtestdate;
	}
	public void setProposedtestdate(String proposedtestdate) {
		this.proposedtestdate = proposedtestdate;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getSampleID() {
		return sampleID;
	}
	public void setSampleID(String sampleID) {
		this.sampleID = sampleID;
	}
	public RequisitionListObject()
	{
		
	}
	public String getHospitalCode() {
		return this.hospitalCode;
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
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}
	
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	/* Constructor for Patient With CR NO*/
	public RequisitionListObject(String requisitionNo, String requisitionDate,String patFirstName,String patMiddleName,String patLastName,String patCRNo,String patAge,String patEpisodeVisitNo,String patEpisodeNo,String patEpisodeVisitDate
	 ,String hospitalName)
	{
				this.requisitionNo=requisitionNo;
				this.requisitionDate=requisitionDate;
				this.patCRNo=patCRNo;
				this.patAge=patAge;
				this.patFirstName=patFirstName;
				this.patEpisodeCode=patEpisodeNo;
				this.patVisitDate=patEpisodeVisitDate;
				this.patVisitNo=patEpisodeVisitNo;
				this.patMiddleName=patMiddleName;
				this.patLastName=patLastName;
				this.patName=this.patFirstName+this.patMiddleName+this.patLastName;
				
	}
	/*Constructor for OutSide Patient*/
	public RequisitionListObject(String requisitionNo, String requisitionDate,String patDOB,String patFirstName,String patMiddleName,String patLastName,String hospitalCode,String hospitalName)
	{
				this.requisitionNo=requisitionNo;
				this.requisitionDate=requisitionDate;
				this.patAge=patDOB;
				this.patFirstName=patFirstName;
				this.patMiddleName=patMiddleName;
				this.patLastName=patLastName;
				this.hospitalName=hospitalName;
				this.hospitalCode=hospitalCode;
				this.patName=this.patFirstName+this.patMiddleName+this.patLastName;
				
	}
	/*Constructor for OutSide Sample 6 arguments*/
	public RequisitionListObject(String requisitionNo, String requisitionDate,String sampleID,String hospitalName,String hospitalCode,String labName )
	{
				this.requisitionNo=requisitionNo;
				this.requisitionDate=requisitionDate;
				this.hospitalCode=hospitalCode;
				this.hospitalName=hospitalName;
				this.sampleID=sampleID;
				this.labName=labName;
	}
	/*Constructor with 5 arguments*/
	public RequisitionListObject(String requisitionNo, String requisitionDate,String sampleID,String hospitalName,String hospitalCode)
	{
				this.requisitionNo=requisitionNo;
				this.requisitionDate=requisitionDate;
				this.hospitalCode=hospitalCode;
				this.hospitalName=hospitalName;
				this.sampleID=sampleID;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatEpisodeCode() {
		return patEpisodeCode;
	}
	public void setPatEpisodeCode(String patEpisodeCode) {
		this.patEpisodeCode = patEpisodeCode;
	}
	public String getPatVisitDate() {
		return patVisitDate;
	}
	public void setPatVisitDate(String patVisitDate) {
		this.patVisitDate = patVisitDate;
	}
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}
	public List<Sample> getSampleList() {
		return sampleList;
	}
	public void setSampleList(List<Sample> sampleList) {
		this.sampleList = sampleList;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getRequisitionType() {
		return requisitionType;
	}
	public void setRequisitionType(String requisitionType) {
		this.requisitionType = requisitionType;
	}
	public String getPatAdmissionNo() {
		return patAdmissionNo;
	}
	public void setPatAdmissionNo(String patAdmissionNo) {
		this.patAdmissionNo = patAdmissionNo;
	}
	public String getSessionNo() {
		return sessionNo;
	}
	public void setSessionNo(String sessionNo) {
		this.sessionNo = sessionNo;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	
}
