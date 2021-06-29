package inpatient.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class ANCTrimesterChecklistDetailFB extends ActionForm
{
	private String hmode;

	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String userSeatId;
	private String wardCode;
	private String departmentUnitCode;
	private String entryDate;

	// ANC Details
	private String gravidaNo;
	private String trimester;
	private String ancNo;
	private String gestationPeriod;
	private String gestationStartDate;

	// CheckList Details
	private String checklistId;
	private String conductDate;
	private String result;

	// Add Drug CheckList
	private String drugChecklistId;
	private String drugChecklistName;
	private String drugConductDate;

	private String delDrugCheckListIndex;
	
	// Add Drug CheckList
	private String invstChecklistId;
	private String invstChecklistName;
	private String invstConductDate;
	private String invstResult;

	private String delInvstCheckListIndex;

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
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

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getUserSeatId()
	{
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId)
	{
		this.userSeatId = userSeatId;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getGravidaNo()
	{
		return gravidaNo;
	}

	public void setGravidaNo(String gravidaNo)
	{
		this.gravidaNo = gravidaNo;
	}

	public String getTrimester()
	{
		return trimester;
	}

	public void setTrimester(String trimester)
	{
		this.trimester = trimester;
	}

	public String getAncNo()
	{
		return ancNo;
	}

	public void setAncNo(String ancNo)
	{
		this.ancNo = ancNo;
	}

	public String getGestationPeriod()
	{
		return gestationPeriod;
	}

	public void setGestationPeriod(String gestationPeriod)
	{
		this.gestationPeriod = gestationPeriod;
	}

	public String getChecklistId()
	{
		return checklistId;
	}

	public void setChecklistId(String checklistId)
	{
		this.checklistId = checklistId;
	}

	public String getConductDate()
	{
		return conductDate;
	}

	public void setConductDate(String conductDate)
	{
		this.conductDate = conductDate;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getDrugChecklistId()
	{
		return drugChecklistId;
	}

	public void setDrugChecklistId(String drugChecklistId)
	{
		this.drugChecklistId = drugChecklistId;
	}

	public String getDrugConductDate()
	{
		return drugConductDate;
	}

	public void setDrugConductDate(String drugConductDate)
	{
		this.drugConductDate = drugConductDate;
	}

	public String getInvstChecklistId()
	{
		return invstChecklistId;
	}

	public void setInvstChecklistId(String invstChecklistId)
	{
		this.invstChecklistId = invstChecklistId;
	}

	public String getInvstConductDate()
	{
		return invstConductDate;
	}

	public void setInvstConductDate(String invstConductDate)
	{
		this.invstConductDate = invstConductDate;
	}

	public String getInvstResult()
	{
		return invstResult;
	}

	public void setInvstResult(String invstResult)
	{
		this.invstResult = invstResult;
	}

	public String getDrugChecklistName()
	{
		return drugChecklistName;
	}

	public void setDrugChecklistName(String drugChecklistName)
	{
		this.drugChecklistName = drugChecklistName;
	}

	public String getInvstChecklistName()
	{
		return invstChecklistName;
	}

	public void setInvstChecklistName(String invstChecklistName)
	{
		this.invstChecklistName = invstChecklistName;
	}

	public String getDelDrugCheckListIndex()
	{
		return delDrugCheckListIndex;
	}

	public void setDelDrugCheckListIndex(String delDrugCheckListIndex)
	{
		this.delDrugCheckListIndex = delDrugCheckListIndex;
	}

	public String getGestationStartDate()
	{
		return gestationStartDate;
	}

	public void setGestationStartDate(String gestationStartDate)
	{
		this.gestationStartDate = gestationStartDate;
	}

	public String getDelInvstCheckListIndex()
	{
		return delInvstCheckListIndex;
	}

	public void setDelInvstCheckListIndex(String delInvstCheckListIndex)
	{
		this.delInvstCheckListIndex = delInvstCheckListIndex;
	}
}
