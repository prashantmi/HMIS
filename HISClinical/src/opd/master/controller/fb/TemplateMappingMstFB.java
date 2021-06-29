package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TemplateMappingMstFB extends ActionForm
{
	private String isValid;
	private String hmode;
	private String controls[];
	private String chk[];

	private String templateMappingId;
	private String templateCategory;
	private String templateCategoryName;
	private String templateId[];
	private String selectedTemplateId[];
	private String deptCode;
	private String deptName;
	private String deptUnitName;
	private String deptUnitCode;
	private String wardCode;
	private String wardName;
	private String isDefault;
	private String deptRadio;
	private String unitRadio;
	private String wardRadio;
	private String fetchedList;
	
	public TemplateMappingMstFB(){
		
		controls=new String[1];
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.templateMappingId="";
		//this.templateCategory="";
		this.deptCode="-1";
		this.deptUnitCode="-1";
		this.wardCode="-1";
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getTemplateMappingId() {
		return templateMappingId;
	}

	public void setTemplateMappingId(String templateMappingId) {
		this.templateMappingId = templateMappingId;
	}

	public String getTemplateCategory() {
		return templateCategory;
	}

	public void setTemplateCategory(String templateCategory) {
		this.templateCategory = templateCategory;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptUnitCode() {
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String[] getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String[] templateId) {
		this.templateId = templateId;
	}

	public String[] getSelectedTemplateId() {
		return selectedTemplateId;
	}

	public void setSelectedTemplateId(String[] selectedTemplateId) {
		this.selectedTemplateId = selectedTemplateId;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getDeptRadio() {
		return deptRadio;
	}

	public void setDeptRadio(String deptRadio) {
		this.deptRadio = deptRadio;
	}

	public String getUnitRadio() {
		return unitRadio;
	}

	public void setUnitRadio(String unitRadio) {
		this.unitRadio = unitRadio;
	}

	public String getWardRadio() {
		return wardRadio;
	}

	public void setWardRadio(String wardRadio) {
		this.wardRadio = wardRadio;
	}

	public String getTemplateCategoryName() {
		return templateCategoryName;
	}

	public void setTemplateCategoryName(String templateCategoryName) {
		this.templateCategoryName = templateCategoryName;
	}

	public String getFetchedList() {
		return fetchedList;
	}

	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptUnitName() {
		return deptUnitName;
	}

	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
		
}
