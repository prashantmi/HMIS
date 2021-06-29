package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class OpdReferPatientFB extends CRNoFB
{
	private String hmode;
	private String episodeCode;
	private String episodeVisitNo;
	private String isPatDead;
	private String deskType;
	private String deskId;
	
	private String fromDepartment;
	private String fromDepartmentUnit;
	private String fromDepartmentCode;
	private String fromDepartmentUnitCode;

	private String isRefferInOut;
	private String referringInstType;
	private String isAssociated;
	private String patRefGnctdHospitalCode;
	private String patRefGnctdHospitalDept;
	private String patRefGnctdHospitalDeptUnit;
	private String patRefHospitalName;
	private String remarks;

	private String choice;
	private String[] departmentCode;
	private String[] deptRemarks;
	private String[] departmentUnitCode;
	private String[] unitRemarks;
	private String[] sameDeptUnitCode;
	private String[] sameDeptUnitRemarks;

	private String numberOfRow;
	private boolean setInternalRefer;
	private boolean setExternalRefer;
	private boolean setReferDept;
	private boolean setReferSpecialUnit;
	private boolean setReferSameDeptUnit;
	private boolean setReferOtherHos;
	
	private String unitCode;
	private String isReferred;
	private String patRefDoctor;
	private String patRefGnctdHospitalCrno;
	private String refferringOPDEpisode;
	private String profileType;
	private String admissionNo;
	private String profileGenerationMode;
	
	

	public OpdReferPatientFB()
	{
		this.fromDepartment = "";
		this.fromDepartmentUnit = "";
		this.fromDepartmentCode = "";
		this.fromDepartmentUnitCode = "";
		
		this.isRefferInOut = "";
		this.referringInstType = "";
		this.isAssociated = "";
		this.patRefGnctdHospitalCode = "";
		this.patRefGnctdHospitalDept = "";
		this.patRefGnctdHospitalDeptUnit = "";
		this.patRefHospitalName = "";
		
		
		this.choice = "";
		this.departmentCode = new String[0];
		this.departmentUnitCode = new String[0];
		this.sameDeptUnitCode = new String[0];
		
		this.setInternalRefer = true;
		this.setExternalRefer = true;
		this.setReferDept = true;
		this.setReferSpecialUnit = true;
		this.setReferSameDeptUnit = true;
		this.setReferOtherHos = true;

		this.setIsReferred("0");
		this.setPatRefDoctor("");
		this.setRefferringOPDEpisode("");
		this.setPatRefGnctdHospitalCrno("");
	}
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.fromDepartment = "";
		this.fromDepartmentUnit = "";
		this.fromDepartmentCode = "";
		this.fromDepartmentUnitCode = "";
		
		this.isRefferInOut = "";
		this.referringInstType = "";
		this.isAssociated = "";
		this.patRefGnctdHospitalCode = "";
		this.patRefGnctdHospitalDept = "";
		this.patRefGnctdHospitalDeptUnit = "";
		this.patRefHospitalName = "";
		
		this.choice = "";
		this.departmentCode = new String[0];
		this.departmentUnitCode = new String[0];
		this.sameDeptUnitCode = new String[0];
		
		this.setInternalRefer = true;
		this.setExternalRefer = true;
		this.setReferDept = true;
		this.setReferSpecialUnit = true;
		this.setReferSameDeptUnit = true;
		this.setReferOtherHos = true;

		this.setIsReferred("0");
		this.setPatRefDoctor("");
		this.setRefferringOPDEpisode("");
		this.setPatRefGnctdHospitalCrno("");
	}

	public String getNumberOfRow()
	{
		return numberOfRow;
	}

	public void setNumberOfRow(String numberOfRow)
	{
		this.numberOfRow = numberOfRow;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String[] getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String[] departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getChoice()
	{
		return choice;
	}

	public void setChoice(String choice)
	{
		this.choice = choice;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String[] getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String[] departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getUnitCode()
	{
		return unitCode;
	}

	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}

	public String getIsReferred()
	{
		return isReferred;
	}

	public void setIsReferred(String isReferred)
	{
		this.isReferred = isReferred;
	}

	public String getIsRefferInOut()
	{
		return isRefferInOut;
	}

	public void setIsRefferInOut(String isRefferInOut)
	{
		this.isRefferInOut = isRefferInOut;
	}

	public String getReferringInstType()
	{
		return referringInstType;
	}

	public void setReferringInstType(String referringInstType)
	{
		this.referringInstType = referringInstType;
	}

	public String getIsAssociated()
	{
		return isAssociated;
	}

	public void setIsAssociated(String isAssociated)
	{
		this.isAssociated = isAssociated;
	}

	public String getPatRefGnctdHospitalDept()
	{
		return patRefGnctdHospitalDept;
	}

	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept)
	{
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}

	public String getPatRefDoctor()
	{
		return patRefDoctor;
	}

	public void setPatRefDoctor(String patRefDoctor)
	{
		this.patRefDoctor = patRefDoctor;
	}

	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}

	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}

	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}

	public String getPatRefGnctdHospitalCrno()
	{
		return patRefGnctdHospitalCrno;
	}

	public void setPatRefGnctdHospitalCrno(String patRefGnctdHospitalCrno)
	{
		this.patRefGnctdHospitalCrno = patRefGnctdHospitalCrno;
	}

	public String getPatRefGnctdHospitalDeptUnit()
	{
		return patRefGnctdHospitalDeptUnit;
	}

	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit)
	{
		this.patRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	public String getFromDepartment()
	{
		return fromDepartment;
	}

	public void setFromDepartment(String fromDepartment)
	{
		this.fromDepartment = fromDepartment;
	}

	public String getRefferringOPDEpisode()
	{
		return refferringOPDEpisode;
	}

	public void setRefferringOPDEpisode(String refferringOPDEpisode)
	{
		this.refferringOPDEpisode = refferringOPDEpisode;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getFromDepartmentUnit()
	{
		return fromDepartmentUnit;
	}

	public void setFromDepartmentUnit(String fromDepartmentUnit)
	{
		this.fromDepartmentUnit = fromDepartmentUnit;
	}

	public String getFromDepartmentCode()
	{
		return fromDepartmentCode;
	}

	public void setFromDepartmentCode(String fromDepartmentCode)
	{
		this.fromDepartmentCode = fromDepartmentCode;
	}

	public String getFromDepartmentUnitCode()
	{
		return fromDepartmentUnitCode;
	}

	public void setFromDepartmentUnitCode(String fromDepartmentUnitCode)
	{
		this.fromDepartmentUnitCode = fromDepartmentUnitCode;
	}

	public String[] getDeptRemarks()
	{
		return deptRemarks;
	}

	public void setDeptRemarks(String[] deptRemarks)
	{
		this.deptRemarks = deptRemarks;
	}

	public String[] getUnitRemarks()
	{
		return unitRemarks;
	}

	public void setUnitRemarks(String[] unitRemarks)
	{
		this.unitRemarks = unitRemarks;
	}

	public String getIsPatDead()
	{
		return isPatDead;
	}

	public void setIsPatDead(String isPatDead)
	{
		this.isPatDead = isPatDead;
	}

	public boolean isSetInternalRefer()
	{
		return setInternalRefer;
	}

	public void setSetInternalRefer(boolean setInternalRefer)
	{
		this.setInternalRefer = setInternalRefer;
	}

	public boolean isSetExternalRefer()
	{
		return setExternalRefer;
	}

	public void setSetExternalRefer(boolean setExternalRefer)
	{
		this.setExternalRefer = setExternalRefer;
	}

	public boolean isSetReferDept()
	{
		return setReferDept;
	}

	public void setSetReferDept(boolean setReferDept)
	{
		this.setReferDept = setReferDept;
	}

	public boolean isSetReferSpecialUnit()
	{
		return setReferSpecialUnit;
	}

	public void setSetReferSpecialUnit(boolean setReferSpecialUnit)
	{
		this.setReferSpecialUnit = setReferSpecialUnit;
	}

	public boolean isSetReferSameDeptUnit()
	{
		return setReferSameDeptUnit;
	}

	public void setSetReferSameDeptUnit(boolean setReferSameDeptUnit)
	{
		this.setReferSameDeptUnit = setReferSameDeptUnit;
	}

	public boolean isSetReferOtherHos()
	{
		return setReferOtherHos;
	}

	public void setSetReferOtherHos(boolean setReferOtherHos)
	{
		this.setReferOtherHos = setReferOtherHos;
	}

	public String[] getSameDeptUnitCode()
	{
		return sameDeptUnitCode;
	}

	public void setSameDeptUnitCode(String[] sameDeptUnitCode)
	{
		this.sameDeptUnitCode = sameDeptUnitCode;
	}

	public String[] getSameDeptUnitRemarks()
	{
		return sameDeptUnitRemarks;
	}

	public void setSameDeptUnitRemarks(String[] sameDeptUnitRemarks)
	{
		this.sameDeptUnitRemarks = sameDeptUnitRemarks;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getDeskType() {
		return deskType;
	}

	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getProfileGenerationMode() {
		return profileGenerationMode;
	}

	public void setProfileGenerationMode(String profileGenerationMode) {
		this.profileGenerationMode = profileGenerationMode;
	}

}
