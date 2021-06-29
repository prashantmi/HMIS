package opd.master.controller.fb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsentMappingMasterFB extends ActionForm
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
	private String hmode;
	private String choice;
	private ArrayList templateList;
	private String[] selectedTemplate;
	private String serviceDesc;
	private String chk;
	private String templateDesc;
	private String serviceTypeDesc;
	private ArrayList serviceIdLst;
	private String[] selServiceIdLst;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.serviceTypeId="";
		this.serviceId="-1";
	}
	public ConsentMappingMasterFB()
	{
		this.serviceTypeId="";
		this.serviceId="-1";
		
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
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public String[] getSelectedTemplate() {
		return selectedTemplate;
	}
	public void setSelectedTemplate(String[] selectedTemplate) {
		this.selectedTemplate = selectedTemplate;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public ArrayList getTemplateList() {
		return templateList;
	}
	public void setTemplateList(ArrayList templateList) {
		this.templateList = templateList;
	}
	public ArrayList getServiceIdLst() {
		return serviceIdLst;
	}
	public void setServiceIdLst(ArrayList serviceIdLst) {
		this.serviceIdLst = serviceIdLst;
	}
	public String[] getSelServiceIdLst() {
		return selServiceIdLst;
	}
	public void setSelServiceIdLst(String[] selServiceIdLst) {
		this.selServiceIdLst = selServiceIdLst;
	}

}
