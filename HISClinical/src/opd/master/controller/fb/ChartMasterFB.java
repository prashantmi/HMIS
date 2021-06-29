package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChartMasterFB extends ActionForm
{
	private String chartDescription;
	private String chartName;
	private String chartCategory;
	private String generationType;
	private String isGraph;
	private String bodyQuery;
	private String footerQuery;
	private String hmode;
	private String chk;
	private String chartId;
	private String slNo;
	private String isActive;
	private String isValid;
	private String generationTypeLabel;
	private String categoryLabel;
	
	
	public String getCategoryLabel() {
		return categoryLabel;
	}
	public void setCategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}
	public String getGenerationTypeLabel() {
		return generationTypeLabel;
	}
	public void setGenerationTypeLabel(String generationTypeLabel) {
		this.generationTypeLabel = generationTypeLabel;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	public String getChartDescription() {
		return chartDescription;
	}
	public void setChartDescription(String chartDescription) {
		this.chartDescription = chartDescription;
	}
	public String getGenerationType() {
		return generationType;
	}
	public void setGenerationType(String generationType) {
		this.generationType = generationType;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public String getChartCategory() {
		return chartCategory;
	}
	public void setChartCategory(String chartCategory) {
		this.chartCategory = chartCategory;
	}
	
	public String getIsGraph() {
		return isGraph;
	}
	public void setIsGraph(String isGraph) {
		this.isGraph = isGraph;
	}
	public String getBodyQuery() {
		return bodyQuery;
	}
	public void setBodyQuery(String bodyQuery) {
		this.bodyQuery = bodyQuery;
	}
	public String getFooterQuery() {
		return footerQuery;
	}
	public void setFooterQuery(String footerQuery) {
		this.footerQuery = footerQuery;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.chartName="";
		this.bodyQuery="";
		this.isGraph="0";
		this.chartCategory="-1";
		this.chartDescription="";
		this.footerQuery="";
		this.footerQuery="";
		this.generationType="-1";
		
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}
