package inpatient.transaction.vo;

import hisglobal.vo.ValueObject;

public class PendingSampleCollectionVO extends ValueObject
{
	private String patCrNo;
	private String requisitionNo;
	private String proposedTestDate;
	private String testName;
	private String labName;
	private String patAdmNo;
	private String remarks;
	private String strRejectionAction;
	private String strRejectionReason;

	public String getStrRejectionAction()
	{
		return strRejectionAction;
	}

	public void setStrRejectionAction(String strRejectionAction)
	{
		this.strRejectionAction = strRejectionAction;
	}

	public String getStrRejectionReason()
	{
		return strRejectionReason;
	}

	public void setStrRejectionReason(String strRejectionReason)
	{
		this.strRejectionReason = strRejectionReason;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getRequisitionNo()
	{
		return requisitionNo;
	}

	public void setRequisitionNo(String requisitionNo)
	{
		this.requisitionNo = requisitionNo;
	}

	public String getProposedTestDate()
	{
		return proposedTestDate;
	}

	public void setProposedTestDate(String proposedTestDate)
	{
		this.proposedTestDate = proposedTestDate;
	}

	public String getTestName()
	{
		return testName;
	}

	public void setTestName(String testName)
	{
		this.testName = testName;
	}

	public String getLabName()
	{
		return labName;
	}

	public void setLabName(String labName)
	{
		this.labName = labName;
	}

	public String getPatAdmNo()
	{
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo)
	{
		this.patAdmNo = patAdmNo;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

}
