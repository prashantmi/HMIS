package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class MonitorVitalsFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String userSeatId;
	private String wardCode;
	private String deptUnitCode;
	private String deskType;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String deskId;
	private String deskMenuId;
	private String deskMenuName;
	private String paraId;
	private String paraName;
	private String[] duration;
	private String[] chk;
	private String[] arrRemarks;
	private String instructionMode;
	private String varifyFlag;
	private String[] modChk;
	private String[] modDuration;
	private String[] modRemarks;
	private String[] arrSerialNo;
	private String[] modchk1;
	private String vitalDuration;
	private String vitalRemarks;
	private String hiddenParaId;
	private String hiddenParaName;
	private String revokeParaId;
	private String monitorMode;
	private String[] modMonitorMode;
	
	private String vitalChartFlag;
	private String htmlVitalChartData;
	
	private String hiddenPatDeadStatus;
	

	public String getHiddenPatDeadStatus() {
		return hiddenPatDeadStatus;
	}

	public void setHiddenPatDeadStatus(String hiddenPatDeadStatus) {
		this.hiddenPatDeadStatus = hiddenPatDeadStatus;
	}

	public String getMonitorMode()
	{
		return monitorMode;
	}

	public void setMonitorMode(String monitorMode)
	{
		this.monitorMode = monitorMode;
	}

	public String getRevokeParaId()
	{
		return revokeParaId;
	}

	public void setRevokeParaId(String revokeParaId)
	{
		this.revokeParaId = revokeParaId;
	}

	public String[] getModchk1()
	{
		return modchk1;
	}

	public void setModchk1(String[] modchk1)
	{
		this.modchk1 = modchk1;
	}

	public String[] getModChk()
	{
		return modChk;
	}

	public void setModChk(String[] modChk)
	{
		this.modChk = modChk;
	}

	public String[] getModDuration()
	{
		return modDuration;
	}

	public void setModDuration(String[] modDuration)
	{
		this.modDuration = modDuration;
	}

	public String[] getModRemarks()
	{
		return modRemarks;
	}

	public void setModRemarks(String[] modRemarks)
	{
		this.modRemarks = modRemarks;
	}

	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		this.paraId = null;
		this.monitorMode = "-1";
		this.vitalRemarks = "";

	}

	public String getInstructionMode()
	{
		return instructionMode;
	}

	public void setInstructionMode(String instructionMode)
	{
		this.instructionMode = instructionMode;
	}

	public String getVarifyFlag()
	{
		return varifyFlag;
	}

	public void setVarifyFlag(String varifyFlag)
	{
		this.varifyFlag = varifyFlag;
	}

	public String[] getArrRemarks()
	{
		return arrRemarks;
	}

	public void setArrRemarks(String[] remarks)
	{
		this.arrRemarks = remarks;
	}

	public String[] getChk()
	{
		return chk;
	}

	public void setChk(String[] chk)
	{
		this.chk = chk;
	}

	public String getParaId()
	{
		return paraId;
	}

	public void setParaId(String paraId)
	{
		this.paraId = paraId;
	}

	public String getParaName()
	{
		return paraName;
	}

	public void setParaName(String paraName)
	{
		this.paraName = paraName;
	}

	public String[] getDuration()
	{
		return duration;
	}

	public void setDuration(String[] duration)
	{
		this.duration = duration;
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

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
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

	public String getDeskId()
	{
		return deskId;
	}

	public void setDeskId(String deskId)
	{
		this.deskId = deskId;
	}

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}

	public String getDeskMenuName()
	{
		return deskMenuName;
	}

	public void setDeskMenuName(String deskMenuName)
	{
		this.deskMenuName = deskMenuName;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String[] getArrSerialNo()
	{
		return arrSerialNo;
	}

	public void setArrSerialNo(String[] serialNo)
	{
		this.arrSerialNo = serialNo;
	}

	public String getVitalDuration()
	{
		return vitalDuration;
	}

	public void setVitalDuration(String vitalDuration)
	{
		this.vitalDuration = vitalDuration;
	}

	public String getVitalRemarks()
	{
		return vitalRemarks;
	}

	public void setVitalRemarks(String vitalRemarks)
	{
		this.vitalRemarks = vitalRemarks;
	}

	public String getHiddenParaId()
	{
		return hiddenParaId;
	}

	public void setHiddenParaId(String hiddenParaId)
	{
		this.hiddenParaId = hiddenParaId;
	}

	public String getHiddenParaName()
	{
		return hiddenParaName;
	}

	public void setHiddenParaName(String hiddenParaName)
	{
		this.hiddenParaName = hiddenParaName;
	}

	public String[] getModMonitorMode()
	{
		return modMonitorMode;
	}

	public void setModMonitorMode(String[] modMonitorMode)
	{
		this.modMonitorMode = modMonitorMode;
	}

	public String getVitalChartFlag()
	{
		return vitalChartFlag;
	}

	public void setVitalChartFlag(String vitalChartFlag)
	{
		this.vitalChartFlag = vitalChartFlag;
	}

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getHtmlVitalChartData()
	{
		return htmlVitalChartData;
	}

	public void setHtmlVitalChartData(String htmlVitalChartData)
	{
		this.htmlVitalChartData = htmlVitalChartData;
	}

}
