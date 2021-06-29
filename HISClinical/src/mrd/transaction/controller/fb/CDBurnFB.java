package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CDBurnFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String fileName;
	private String patAdmNo;
	private String selectedRecord[];
	private String winPathToBurn;
	private String linuxPathToBurn;
	private String serverOS;
	private String remarks;
	private String hiddenRemarks;

	@Override public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.selectedRecord = new String[]
		{};
		this.remarks = "";
	}

	public String getPatAdmNo()
	{
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo)
	{
		this.patAdmNo = patAdmNo;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String[] getSelectedRecord()
	{
		return selectedRecord;
	}

	public void setSelectedRecord(String[] selectedRecord)
	{
		this.selectedRecord = selectedRecord;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getWinPathToBurn()
	{
		return winPathToBurn;
	}

	public void setWinPathToBurn(String winPathToBurn)
	{
		this.winPathToBurn = winPathToBurn;
	}

	public String getLinuxPathToBurn()
	{
		return linuxPathToBurn;
	}

	public void setLinuxPathToBurn(String linuxPathToBurn)
	{
		this.linuxPathToBurn = linuxPathToBurn;
	}

	public String getServerOS()
	{
		return serverOS;
	}

	public void setServerOS(String serverOS)
	{
		this.serverOS = serverOS;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getHiddenRemarks()
	{
		return hiddenRemarks;
	}

	public void setHiddenRemarks(String hiddenRemarks)
	{
		this.hiddenRemarks = hiddenRemarks;
	}

}
