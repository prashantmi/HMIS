package hisglobal.vo;

public class PatFitnessDtlVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String sNo;
	private String empNo;
	private String patAdmNo;
	private String sufferingFrom;
	private String fitnessDate;
	private String fitnessCertificateId;
	private String medicalCertificateId;
	private String fitnessCertificateDesc;
	private String medicalCertificateDesc;
	private String unitCode;
	private String patName;
	
	
	
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getFitnessCertificateId() {
		return fitnessCertificateId;
	}
	public void setFitnessCertificateId(String fitnessCertificateId) {
		this.fitnessCertificateId = fitnessCertificateId;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getSNo() {
		return sNo;
	}
	public void setSNo(String no) {
		sNo = no;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getSufferingFrom() {
		return sufferingFrom;
	}
	public void setSufferingFrom(String sufferingFrom) {
		this.sufferingFrom = sufferingFrom;
	}
	public String getFitnessDate() {
		return fitnessDate;
	}
	public void setFitnessDate(String fitnessDate) {
		this.fitnessDate = fitnessDate;
	}
	public String getMedicalCertificateId() {
		return medicalCertificateId;
	}
	public void setMedicalCertificateId(String medicalCertificateId) {
		this.medicalCertificateId = medicalCertificateId;
	}
	public String getFitnessCertificateDesc() {
		return fitnessCertificateDesc;
	}
	public void setFitnessCertificateDesc(String fitnessCertificateDesc) {
		this.fitnessCertificateDesc = fitnessCertificateDesc;
	}
	public String getMedicalCertificateDesc() {
		return medicalCertificateDesc;
	}
	public void setMedicalCertificateDesc(String medicalCertificateDesc) {
		this.medicalCertificateDesc = medicalCertificateDesc;
	}
}
