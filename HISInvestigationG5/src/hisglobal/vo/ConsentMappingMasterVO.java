package hisglobal.vo;

import java.util.ArrayList;

public class ConsentMappingMasterVO extends ValueObject
{
	
	private String serviceTypeId;
	private String serviceId;
	private String templateId;
	private String templateType;
	private String serialNo;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifySeatID;
	private String hospitalCode;
	private String choice;
	private ArrayList templateList;
	private String[] selectedTemplate;
	private String serviceDesc;
	private String templateDesc;
	private String serviceTypeDesc;
	private String templateCategory;
	
	public String getTemplateCategory() {
		return templateCategory;
	}
	public void setTemplateCategory(String templateCategory) {
		this.templateCategory = templateCategory;
	}
	public String getTemplateDesc() {
		return templateDesc;
	}
	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}
	public String getServiceTypeDesc() {
		return serviceTypeDesc;
	}
	public void setServiceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	
	public ArrayList getTemplateList() {
		return templateList;
	}
	public void setTemplateList(ArrayList templateList) {
		this.templateList = templateList;
	}
	public String[] getSelectedTemplate() {
		return selectedTemplate;
	}
	public void setSelectedTemplate(String[] selectedTemplate) {
		this.selectedTemplate = selectedTemplate;
	}
	public String getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifySeatID() {
		return lastModifySeatID;
	}
	public void setLastModifySeatID(String lastModifySeatID) {
		this.lastModifySeatID = lastModifySeatID;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

}
