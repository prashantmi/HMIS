package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class PendingTaskListFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String taskName;
	private String taskCode;
	private int pendingTaskListSize[]=new int[4];
	/*private int pendingTreatmentListSize;
	private int pendingInstructionListSize=0;
	private int pendingSampleListSize=0;*/
	private int currentPage=1;
	
	private String isDirectCall;

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.hmode = "";
		this.patCrNo = "";
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getIsDirectCall() {
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int[] getPendingTaskListSize() {
		return pendingTaskListSize;
	}

	public void setPendingTaskListSize(int[] pendingTaskListSize) {
		this.pendingTaskListSize = pendingTaskListSize;
	}



}
