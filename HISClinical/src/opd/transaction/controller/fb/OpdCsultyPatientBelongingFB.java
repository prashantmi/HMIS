package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class OpdCsultyPatientBelongingFB extends CRNoFB
{
	private String hmode;
	private String isDirectCall;
	
	private String episodeCode;
	private String episodeVisitNo;

	private String belongingItemCode;
	private String quantity;
	private String remarks;
	private String removeBelongingCode;	
	private String[] chk;
	private String selectedMode;
	private String handOverTo = "";
	private String relation;
	
	public OpdCsultyPatientBelongingFB()
	{
		this.hmode="";
		this.isDirectCall="";
		
		this.episodeCode="";
		this.episodeVisitNo="";

		this.belongingItemCode="";
		this.quantity="";
		this.remarks="";
		this.removeBelongingCode="";	
		this.chk= new String[0];
		this.selectedMode="";
		this.handOverTo="";
		this.relation="";
	}
	
	public void reset(ActionMapping _mapping, HttpServletRequest _request)
	{
		this.belongingItemCode="";
		this.quantity="";
		this.remarks="";
		this.removeBelongingCode="";	
		this.chk= new String[0];
		this.selectedMode="";
		this.handOverTo="";
		this.relation="";
	}
	
	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
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
	public String getBelongingItemCode()
	{
		return belongingItemCode;
	}
	public void setBelongingItemCode(String belongingItemCode)
	{
		this.belongingItemCode = belongingItemCode;
	}
	public String getQuantity()
	{
		return quantity;
	}
	public void setQuantity(String quantity)
	{
		this.quantity = quantity;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public String getRemoveBelongingCode()
	{
		return removeBelongingCode;
	}
	public void setRemoveBelongingCode(String removeBelongingCode)
	{
		this.removeBelongingCode = removeBelongingCode;
	}
	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
	}
	public String getSelectedMode()
	{
		return selectedMode;
	}
	public void setSelectedMode(String selectedMode)
	{
		this.selectedMode = selectedMode;
	}
	public String getHandOverTo()
	{
		return handOverTo;
	}
	public void setHandOverTo(String handOverTo)
	{
		this.handOverTo = handOverTo;
	}
	public String getRelation()
	{
		return relation;
	}
	public void setRelation(String relation)
	{
		this.relation = relation;
	}

	public String getIsDirectCall()
	{
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall)
	{
		this.isDirectCall = isDirectCall;
	}

}
