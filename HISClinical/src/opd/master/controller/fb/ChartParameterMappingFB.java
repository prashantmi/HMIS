package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChartParameterMappingFB extends ActionForm
{
	private String chart;
	private String hmode;
	private String chk;
	private String isActive;
	private String isValid;
	private String[] para;
	private String[] selectedPara;
	private String fetchedList;

	private String[] arrInvPara;
	private String[] arrSelectedInvPara;
	private String[] arrInOutPara;
	private String[] arrSelectedInOutPara;
	
	private String value;
	private String chartNameList;
	private String displayOrder;

	private String chartId;
	private String chartName;
	private String chartDescription;
	private String chartCategory;
	private String generationType;
	private String isGraph;
 
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.chartId = "";
		this.chartCategory = "";

		this.generationType = "";
		this.chart = "0";
		this.fetchedList="";
		this.chartName = "";
		this.chartDescription = "";
		this.isGraph = "";
	}

	public String getDisplayOrder()
	{
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public String getChartNameList()
	{
		return chartNameList;
	}

	public void setChartNameList(String chartNameList)
	{
		this.chartNameList = chartNameList;
	}

	public String getChart()
	{
		return chart;
	}

	public void setChart(String chart)
	{
		this.chart = chart;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getChk()
	{
		return chk;
	}

	public void setChk(String chk)
	{
		this.chk = chk;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String[] getPara()
	{
		return para;
	}

	public void setPara(String[] para)
	{
		this.para = para;
	}

	public String[] getSelectedPara()
	{
		return selectedPara;
	}

	public void setSelectedPara(String[] selectedPara)
	{
		this.selectedPara = selectedPara;
	}

	public String getFetchedList()
	{
		return fetchedList;
	}

	public void setFetchedList(String fetchedList)
	{
		this.fetchedList = fetchedList;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getGenerationType()
	{
		return generationType;
	}

	public void setGenerationType(String generationType)
	{
		this.generationType = generationType;
	}

	public String getChartId()
	{
		return chartId;
	}

	public void setChartId(String chartId)
	{
		this.chartId = chartId;
	}

	public String[] getArrInvPara()
	{
		return arrInvPara;
	}

	public void setArrInvPara(String[] arrInvPara)
	{
		this.arrInvPara = arrInvPara;
	}

	public String[] getArrSelectedInvPara()
	{
		return arrSelectedInvPara;
	}

	public void setArrSelectedInvPara(String[] arrSelectedInvPara)
	{
		this.arrSelectedInvPara = arrSelectedInvPara;
	}

	public String[] getArrInOutPara()
	{
		return arrInOutPara;
	}

	public void setArrInOutPara(String[] arrInOutPara)
	{
		this.arrInOutPara = arrInOutPara;
	}

	public String[] getArrSelectedInOutPara()
	{
		return arrSelectedInOutPara;
	}

	public void setArrSelectedInOutPara(String[] arrSelectedInOutPara)
	{
		this.arrSelectedInOutPara = arrSelectedInOutPara;
	}

	public String getChartName()
	{
		return chartName;
	}

	public void setChartName(String chartName)
	{
		this.chartName = chartName;
	}

	public String getChartDescription()
	{
		return chartDescription;
	}

	public void setChartDescription(String chartDescription)
	{
		this.chartDescription = chartDescription;
	}

	public String getChartCategory()
	{
		return chartCategory;
	}

	public void setChartCategory(String chartCategory)
	{
		this.chartCategory = chartCategory;
	}

	public String getIsGraph()
	{
		return isGraph;
	}

	public void setIsGraph(String isGraph)
	{
		this.isGraph = isGraph;
	}
}
