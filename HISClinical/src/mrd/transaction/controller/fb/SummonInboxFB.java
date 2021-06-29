package mrd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class SummonInboxFB extends ActionForm
{
	private String hmode;
	private int currentPage;
	private String selectedSummonId;
	
	public String getSelectedSummonId() {
		return selectedSummonId;
	}

	public void setSelectedSummonId(String selectedSummonId) {
		this.selectedSummonId = selectedSummonId;
	}

	public SummonInboxFB()
	{
		this.currentPage=1;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
