package hisglobal.vo;

import hisglobal.vo.ValueObject;
import investigation.vo.Sample;

import java.util.List;

public class SlideRequestVO extends ValueObject{
	private String slideRequestNo;
	
	private String labName;
	private String testName;
	private String accessionNo;
	private String crNo;
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
	private String requestedBy;
	private String requestedDepartment;
	
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getRequestedDepartment() {
		return requestedDepartment;
	}
	public void setRequestedDepartment(String requestedDepartment) {
		this.requestedDepartment = requestedDepartment;
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
	public SlideRequestVO()
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
	public SlideRequestVO(String requisitionNo, String requisitionDate,String patFirstName,String patMiddleName,String patLastName,String patCRNo,String patAge,String patEpisodeVisitNo,String patEpisodeNo,String patEpisodeVisitDate
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
	public SlideRequestVO(String requisitionNo, String requisitionDate,String patDOB,String patFirstName,String patMiddleName,String patLastName,String hospitalCode,String hospitalName)
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
	public SlideRequestVO(String requisitionNo, String requisitionDate,String sampleID,String hospitalName,String hospitalCode)
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
	public String getSlideRequestNo() {
		return slideRequestNo;
	}
	public void setSlideRequestNo(String slideRequestNo) {
		this.slideRequestNo = slideRequestNo;
	}
	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}
	public String getCrNo() {
		return crNo;
	}
	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}

}
