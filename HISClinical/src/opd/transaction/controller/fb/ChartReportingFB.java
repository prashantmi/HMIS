package opd.transaction.controller.fb;

/**
 * @copyright CDAC
 * @author Pragya Sharma
 */

import hisglobal.hisconfig.Config;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChartReportingFB extends ActionForm
{
	private String hmode;

	private String deskType;
	private String deptUnitCode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String epiAdmStartDate;
	
	private String entryDate;
	private String fromDate;
	private String toDate;
	private String filterCriteria;
	

	private String chartId;
	private String generationType;
	private String chartHeader;
	private String isGraph;
	private String[] chartPara;
	
	private boolean haveDefault;
	private String targetPage;
	private String sortType;
	private String oldSortType;
	private String sortChartId;
	private String graphChartId;
	private String graphChartName;

	// For Pagination
	private int currentPage;
	private int maxRowsCount;

	public ChartReportingFB()
	{
		this.filterCriteria = OpdConfig.CHOICE_EPISODE_WISE;
		this.chartId = "";
		this.chartPara = new String[0];
		this.haveDefault = false;
		this.oldSortType = Config.SORT_TYPE_ASC;
		this.sortChartId = Config.SORT_TYPE_ASC;
		this.currentPage = 1;
		this.maxRowsCount = 15;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.filterCriteria = OpdConfig.CHOICE_EPISODE_WISE;
		this.chartId = "";
		this.chartPara = new String[0];
		this.haveDefault = false;		
		this.oldSortType = Config.SORT_TYPE_ASC;
		this.sortChartId = Config.SORT_TYPE_ASC;
		this.currentPage = 1;
		this.maxRowsCount = 15;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
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

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public String getChartId()
	{
		return chartId;
	}

	public void setChartId(String chartId)
	{
		this.chartId = chartId;
	}

	public String getFilterCriteria()
	{
		return filterCriteria;
	}

	public void setFilterCriteria(String filterCriteria)
	{
		this.filterCriteria = filterCriteria;
	}

	public String getEpiAdmStartDate()
	{
		return epiAdmStartDate;
	}

	public void setEpiAdmStartDate(String epiAdmStartDate)
	{
		this.epiAdmStartDate = epiAdmStartDate;
	}

	public String getChartHeader()
	{
		return chartHeader;
	}

	public void setChartHeader(String chartHeader)
	{
		this.chartHeader = chartHeader;
	}

	public String[] getChartPara()
	{
		return chartPara;
	}

	public void setChartPara(String[] chartPara)
	{
		this.chartPara = chartPara;
	}

	public boolean isHaveDefault()
	{
		return haveDefault;
	}

	public void setHaveDefault(boolean haveDefault)
	{
		this.haveDefault = haveDefault;
	}

	public String getTargetPage()
	{
		return targetPage;
	}

	public void setTargetPage(String targetPage)
	{
		this.targetPage = targetPage;
	}

	public String getSortType()
	{
		return sortType;
	}

	public void setSortType(String sortType)
	{
		this.sortType = sortType;
	}

	public String getSortChartId()
	{
		return sortChartId;
	}

	public void setSortChartId(String sortChartId)
	{
		this.sortChartId = sortChartId;
	}

	public String getOldSortType()
	{
		return oldSortType;
	}

	public void setOldSortType(String oldSortType)
	{
		this.oldSortType = oldSortType;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getMaxRowsCount()
	{
		return maxRowsCount;
	}

	public void setMaxRowsCount(int maxRowsCount)
	{
		this.maxRowsCount = maxRowsCount;
	}

	public String getGraphChartId()
	{
		return graphChartId;
	}

	public void setGraphChartId(String graphChartId)
	{
		this.graphChartId = graphChartId;
	}

	public String getGraphChartName()
	{
		return graphChartName;
	}

	public void setGraphChartName(String graphChartName)
	{
		this.graphChartName = graphChartName;
	}

	public String getIsGraph()
	{
		return isGraph;
	}

	public void setIsGraph(String isGraph)
	{
		this.isGraph = isGraph;
	}

	public String getGenerationType()
	{
		return generationType;
	}

	public void setGenerationType(String generationType)
	{
		this.generationType = generationType;
	}
}
