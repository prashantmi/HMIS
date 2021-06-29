package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CsultyPatientDeskFB extends ActionForm
{
	private String mode;
	private String patCrNo;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGender;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String queNo;
	private String patGenderAge;
	private String departmentUnitCode;
	private String hmode;
	private String orderBy;
	private String episodeNextVisitDate;
	private String episodeIsOpen;
	private String patChoice;
	private String selectedPatCrNo;
	private String pageChoice;
	
	// For Pagination
	private int currentPage;

	public CsultyPatientDeskFB()
	{
		this.currentPage=0;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.setPatCrNo("");
		this.setPatFirstName("");
		this.setPatMiddleName("");
		this.setPatLastName("");
		this.setPatGender("");
		this.setPatPrimaryCat("");
		this.setPatPrimaryCatCode("");
		this.setDepartmentUnitCode("");
		this.setOrderBy("");
		this.setMode("");
		this.setHmode("");
		this.setPatChoice("");
		this.setPageChoice("");
		super.reset(arg0, arg1);
	}

	public String getSelectedPatCrNo()
	{
		return selectedPatCrNo;
	}

	public void setSelectedPatCrNo(String selectedPatCrNo)
	{
		this.selectedPatCrNo = selectedPatCrNo;
	}

	public String getEpisodeIsOpen()
	{
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen)
	{
		this.episodeIsOpen = episodeIsOpen;
	}

	public String getEpisodeNextVisitDate()
	{
		return episodeNextVisitDate;
	}

	public void setEpisodeNextVisitDate(String episodeNextVisitDate)
	{
		this.episodeNextVisitDate = episodeNextVisitDate;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getPatGenderAge()
	{
		return patGenderAge;
	}

	public void setPatGenderAge(String patGenderAge)
	{
		this.patGenderAge = patGenderAge;
	}

	public String getMode()
	{
		return mode;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getPatFirstName()
	{
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	public String getPatGender()
	{
		return patGender;
	}

	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	public String getPatLastName()
	{
		return patLastName;
	}

	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	public String getPatPrimaryCat()
	{
		return patPrimaryCat;
	}

	public void setPatPrimaryCat(String patPrimaryCat)
	{
		this.patPrimaryCat = patPrimaryCat;
	}

	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getQueNo()
	{
		return queNo;
	}

	public void setQueNo(String queNo)
	{
		this.queNo = queNo;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	public String getPatChoice()
	{
		return patChoice;
	}

	public void setPatChoice(String patChoice)
	{
		this.patChoice = patChoice;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public String getPageChoice()
	{
		return pageChoice;
	}

	public void setPageChoice(String pageChoice)
	{
		this.pageChoice = pageChoice;
	}
}