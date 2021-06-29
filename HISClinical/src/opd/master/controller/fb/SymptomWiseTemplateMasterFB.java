package opd.master.controller.fb;

/**
 * @author  CDAC
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SymptomWiseTemplateMasterFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isActive;
	private String entryDate;
	private String isValid;
	private String seatId;
	private String allergyTypeId;
	private String allergyTypeName;
	private ArrayList symptomList;
	private String[] selectedSymptoms;
	private String symptomCode;
	private String prefferedTerm;
	private String conceptId;
	private String[] selectedTemplate;
	private String templateCode;
	private String isSnomedFlagValue;
	private String isSnomedConcept;
	private String templateList;
	private String symptomTypeName;



	


	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{     
		
		this.entryDate = "";
		this.isValid = "";
		this.allergyTypeId="-1";
		this.symptomCode="";

	}

	
	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	
	public String getChk() {
		return chk;
	}


	public void setChk(String chk) {
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
	
	public String getAllergyTypeId() {
		return allergyTypeId;
	}

	public void setAllergyTypeId(String allergyTypeId) {
		this.allergyTypeId = allergyTypeId;
	}

	public String[] getSelectedSymptoms() {
		return selectedSymptoms;
	}

	public void setSelectedSymptoms(String[] selectedSymptoms) {
		this.selectedSymptoms = selectedSymptoms;
	}

	

	public String getSeatId() {
		return seatId;
	}

	public ArrayList getSymptomList() {
		return symptomList;
	}


	public void setSymptomList(ArrayList symptomList) {
		this.symptomList = symptomList;
	}


	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	
	public String getSymptomCode() {
		return symptomCode;
	}

	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
	}

	public String getAllergyTypeName() {
		return allergyTypeName;
	}

	public void setAllergyTypeName(String allergyTypeName) {
		this.allergyTypeName = allergyTypeName;
	}


	public String getPrefferedTerm() {
		return prefferedTerm;
	}


	public void setPrefferedTerm(String prefferedTerm) {
		this.prefferedTerm = prefferedTerm;
	}


	public String getConceptId() {
		return conceptId;
	}


	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}


	public String[] getSelectedTemplate() {
		return selectedTemplate;
	}


	public void setSelectedTemplate(String[] selectedTemplate) {
		this.selectedTemplate = selectedTemplate;
	}


	public String getTemplateCode() {
		return templateCode;
	}


	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}


	public String getIsSnomedConcept() {
		return isSnomedConcept;
	}


	public void setIsSnomedConcept(String isSnomedConcept) {
		this.isSnomedConcept = isSnomedConcept;
	}


	public String getIsSnomedFlagValue() {
		return isSnomedFlagValue;
	}


	public void setIsSnomedFlagValue(String isSnomedFlagValue) {
		this.isSnomedFlagValue = isSnomedFlagValue;
	}


	public String getTemplateList() {
		return templateList;
	}


	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}


	public String getSymptomTypeName() {
		return symptomTypeName;
	}


	public void setSymptomTypeName(String symptomTypeName) {
		this.symptomTypeName = symptomTypeName;
	}
}
