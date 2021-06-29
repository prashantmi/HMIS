package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TestGroupInfoMstFB extends ActionForm
{
	
	
	private	String[] unmappedList;
	private	String[] mappedList;

	private String groupCode;
	private String hmode;
	private String chk[];
	private String testCode;
	private String remarks;
	private String labCode;
	private String hospitalCode;
	private String selectedChk;

	private String isActive;
	private String isDynamicResult;
	private String isDynamicTemplate;
	private String printingTemplateName;
	private String printingTemplateCode;

	public String getPrintingTemplateCode() {
		return printingTemplateCode;
	}



	public void setPrintingTemplateCode(String printingTemplateCode) {
		this.printingTemplateCode = printingTemplateCode;
	}



	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}





	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		this.groupCode="-1";
		this.labCode="-1";
		this.remarks="";
		this.testCode="-1";
		this.isDynamicTemplate="0";
		this.isDynamicResult="0";
		this.printingTemplateCode="-1";
	}




	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
	}



	

	public String getHospitalCode() {
		return hospitalCode;
	}



	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}



	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}




	public String getLabCode() {
		return labCode;
	}



	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}



	public String[] getUnmappedList() {
		return unmappedList;
	}



	public void setUnmappedList(String[] unmappedList) {
		this.unmappedList = unmappedList;
	}



	public String[] getMappedList() {
		return mappedList;
	}



	public void setMappedList(String[] mappedList) {
		this.mappedList = mappedList;
	}



	public String getGroupCode() {
		return groupCode;
	}



	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}



	public String getTestCode() {
		return testCode;
	}



	public String getIsDynamicResult() {
		return isDynamicResult;
	}



	public void setIsDynamicResult(String isDynamicResult) {
		this.isDynamicResult = isDynamicResult;
	}



	public String getIsDynamicTemplate() {
		return isDynamicTemplate;
	}



	public void setIsDynamicTemplate(String isDynamicTemplate) {
		this.isDynamicTemplate = isDynamicTemplate;
	}



	public String getPrintingTemplateName() {
		return printingTemplateName;
	}



	public void setPrintingTemplateName(String printingTemplateName) {
		this.printingTemplateName = printingTemplateName;
	}



	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}



}
