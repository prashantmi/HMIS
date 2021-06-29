package new_investigation.vo;
import hisglobal.vo.ValueObject;



public class Inv_EpisodeVO extends ValueObject
{
	                    
	private static final long serialVersionUID = -4294089437863920752L;
	private String patCRNo;
	private String patepisodecode;
	private String patvisitdate;
	private String departmentcode;
	private String patdeptunitcode;
	private String patdeptunit;
	private String department;
	private String room;
	private String patepisodetypecode;
	private String patvisittypecode;
	private String hospitalcode;
	private String patmlcno;
	private String diagnosis;
	
	private String patVisitNo;
	
	
	private String dignosisName;

	//add by chandan gupta on 10-june-2016
	private String visitReason;
	private String patCategoryCode;
	private String visit_reason_new;
	private String visit_reason_code;
	private String newdiagnosis;
	
	public String getPatCategoryCode() {
		return patCategoryCode;
	}
	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}
	public String getPatepisodecode() {
		return patepisodecode;
	}
	public void setPatepisodecode(String patepisodecode) {
		this.patepisodecode = patepisodecode;
	}
	public String getPatvisitdate() {
		return patvisitdate;
	}
	public void setPatvisitdate(String patvisitdate) {
		this.patvisitdate = patvisitdate;
	}
	public String getDepartmentcode() {
		return departmentcode;
	}
	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}
	public String getPatdeptunitcode() {
		return patdeptunitcode;
	}
	public void setPatdeptunitcode(String patdeptunitcode) {
		this.patdeptunitcode = patdeptunitcode;
	}
	public String getPatdeptunit() {
		return patdeptunit;
	}
	public void setPatdeptunit(String patdeptunit) {
		this.patdeptunit = patdeptunit;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getPatepisodetypecode() {
		return patepisodetypecode;
	}
	public void setPatepisodetypecode(String patepisodetypecode) {
		this.patepisodetypecode = patepisodetypecode;
	}
	public String getPatvisittypecode() {
		return patvisittypecode;
	}
	public void setPatvisittypecode(String patvisittypecode) {
		this.patvisittypecode = patvisittypecode;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String getPatmlcno() {
		return patmlcno;
	}
	public void setPatmlcno(String patmlcno) {
		this.patmlcno = patmlcno;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	
	public String getVisit_reason_code() {
		return visit_reason_code;
	}
	public void setVisit_reason_code(String visit_reason_code) {
		this.visit_reason_code = visit_reason_code;
	}
	public String getVisit_reason_new() {
		return visit_reason_new;
	}
	public void setVisit_reason_new(String visit_reason_new) {
		this.visit_reason_new = visit_reason_new;
	}
	public String getNewdiagnosis() {
		return newdiagnosis;
	}
	public void setNewdiagnosis(String newdiagnosis) {
		this.newdiagnosis = newdiagnosis;
	}
	


}

