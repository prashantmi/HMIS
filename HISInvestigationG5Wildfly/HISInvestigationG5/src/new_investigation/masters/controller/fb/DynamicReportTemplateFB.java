package new_investigation.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class DynamicReportTemplateFB extends ActionForm {
	String hmode;
	String testCode;
	String templateType="0";
	
	String tableNo;
	String rowNo;
	String colNo;
	String cell1ColNo;
	String cell1RowNo;
	String cell2ColNo;
	String cell2RowNo;
	String splitNo;
	String rowNo1;
	String rowNo2;
	String functionPerformed;
	String exsistingTemplate;
	String currmode;
	private static final long serialVersionUID = 1L;
	String mode;
	String cancelMode;
	String headerHeight;
	private String addTestOrGroup = "1";
	String groupCode;	
	String paraType;
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getAddTestOrGroup() {
		return addTestOrGroup;
	}
	public void setAddTestOrGroup(String addTestOrGroup) {
		this.addTestOrGroup = addTestOrGroup;
	}
	public String getHeaderHeight() {
		return headerHeight;
	}
	public void setHeaderHeight(String headerHeight) {
		this.headerHeight = headerHeight;
	}
	public String getFooterHeight() {
		return footerHeight;
	}
	public void setFooterHeight(String footerHeight) {
		this.footerHeight = footerHeight;
	}
	public String getHeaderWidth() {
		return headerWidth;
	}
	public void setHeaderWidth(String headerWidth) {
		this.headerWidth = headerWidth;
	}
	public String getFooterWidth() {
		return footerWidth;
	}
	public void setFooterWidth(String footerWidth) {
		this.footerWidth = footerWidth;
	}
	public String getPageHeight() {
		return pageHeight;
	}
	public void setPageHeight(String pageHeight) {
		this.pageHeight = pageHeight;
	}
	public String getPageWidth() {
		return pageWidth;
	}
	public void setPageWidth(String pageWidth) {
		this.pageWidth = pageWidth;
	}
	public String getTemplateSeqId() {
		return templateSeqId;
	}
	public void setTemplateSeqId(String templateSeqId) {
		this.templateSeqId = templateSeqId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	String footerHeight;
	String headerWidth;
	String footerWidth;
	String pageHeight;
	String pageWidth;
	String templateSeqId;
	String templateName;
	

		
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCancelMode() {
		return cancelMode;
	}
	public void setCancelMode(String cancelMode) {
		this.cancelMode = cancelMode;
	}
	public String getCurrmode() {
		return currmode;
	}
	public void setCurrmode(String currmode) {
		this.currmode = currmode;
	}
	public String getExsistingTemplate() {
		return exsistingTemplate;
	}
	public void setExsistingTemplate(String exsistingTemplate) {
		this.exsistingTemplate = exsistingTemplate;
	}
	public String getFunctionPerformed() {
		return functionPerformed;
	}
	public void setFunctionPerformed(String functionPerformed) {
		this.functionPerformed = functionPerformed;
	}
	public String getRowNo1() {
		return rowNo1;
	}
	public void setRowNo1(String rowNo1) {
		this.rowNo1 = rowNo1;
	}
	public String getRowNo2() {
		return rowNo2;
	}
	public void setRowNo2(String rowNo2) {
		this.rowNo2 = rowNo2;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getTableNo() {
		return tableNo;
	}
	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getColNo() {
		return colNo;
	}
	public void setColNo(String colNo) {
		this.colNo = colNo;
	}
	public String getCell1ColNo() {
		return cell1ColNo;
	}
	public void setCell1ColNo(String cell1ColNo) {
		this.cell1ColNo = cell1ColNo;
	}
	public String getCell1RowNo() {
		return cell1RowNo;
	}
	public void setCell1RowNo(String cell1RowNo) {
		this.cell1RowNo = cell1RowNo;
	}
	public String getCell2ColNo() {
		return cell2ColNo;
	}
	public void setCell2ColNo(String cell2ColNo) {
		this.cell2ColNo = cell2ColNo;
	}
	public String getCell2RowNo() {
		return cell2RowNo;
	}
	public void setCell2RowNo(String cell2RowNo) {
		this.cell2RowNo = cell2RowNo;
	}
	public String getSplitNo() {
		return splitNo;
	}
	public void setSplitNo(String splitNo) {
		this.splitNo = splitNo;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
}
