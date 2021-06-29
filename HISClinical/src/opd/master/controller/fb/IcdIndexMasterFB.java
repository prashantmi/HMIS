package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class IcdIndexMasterFB extends ActionForm 
{
	private static final long serialVersionUID = 1L;
	
	
	private String indexCode;
	private String diagnosticTerm;
	private String hospitalSynonym;
	
	private String seatId;
	//private String dualDiseaseCode;
	private String entryDate;
	private String seeTerm;
	private String seeTermCode;
	private String seeTermModiCode;
	private String seeAlsoTerm;
	private String seeAlsoTermCode;
	private String seeAlsoTermModiCode;
	private String isValid;
	private String isNECTerm;
	private String remark;
	private String hospitalCode;
	private String section;
	
	
	private String hmode ;
	private String chk ;
	private String slNo;
	private String isActive;
	
	private String strDiseaseCodeChk;
	private String strDualDiseaseCodeChk;
	private String strRemark;
	
	
	private String icdSubgroupCode;
	private String icdGroupCode;
	private String diseaseCode;
	
	private String strDualGroupCode;
	private String strDualSubGroupCode;
	private String strDualDiseaseCode;
	
	
	
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public String getDiagnosticTerm() {
		return diagnosticTerm;
	}
	public void setDiagnosticTerm(String diagnosticTerm) {
		this.diagnosticTerm = diagnosticTerm;
	}
	public String getHospitalSynonym() {
		return hospitalSynonym;
	}
	public void setHospitalSynonym(String hospitalSynonym) {
		this.hospitalSynonym = hospitalSynonym;
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
	public String getSeeTerm() {
		return seeTerm;
	}
	public void setSeeTerm(String seeTerm) {
		this.seeTerm = seeTerm;
	}
	public String getSeeTermCode() {
		return seeTermCode;
	}
	public void setSeeTermCode(String seeTermCode) {
		this.seeTermCode = seeTermCode;
	}
	public String getSeeTermModiCode() {
		return seeTermModiCode;
	}
	public void setSeeTermModiCode(String seeTermModiCode) {
		this.seeTermModiCode = seeTermModiCode;
	}
	public String getSeeAlsoTerm() {
		return seeAlsoTerm;
	}
	public void setSeeAlsoTerm(String seeAlsoTerm) {
		this.seeAlsoTerm = seeAlsoTerm;
	}
	public String getSeeAlsoTermCode() {
		return seeAlsoTermCode;
	}
	public void setSeeAlsoTermCode(String seeAlsoTermCode) {
		this.seeAlsoTermCode = seeAlsoTermCode;
	}
	public String getSeeAlsoTermModiCode() {
		return seeAlsoTermModiCode;
	}
	public void setSeeAlsoTermModiCode(String seeAlsoTermModiCode) {
		this.seeAlsoTermModiCode = seeAlsoTermModiCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getIsNECTerm() {
		return isNECTerm;
	}
	public void setIsNECTerm(String isNECTerm) {
		this.isNECTerm = isNECTerm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
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
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getIcdSubgroupCode() {
		return icdSubgroupCode;
	}
	public void setIcdSubgroupCode(String icdSubgroupCode) {
		this.icdSubgroupCode = icdSubgroupCode;
	}
	public String getIcdGroupCode() {
		return icdGroupCode;
	}
	public void setIcdGroupCode(String icdGroupCode) {
		this.icdGroupCode = icdGroupCode;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getStrDualGroupCode() {
		return strDualGroupCode;
	}
	public void setStrDualGroupCode(String strDualGroupCode) {
		this.strDualGroupCode = strDualGroupCode;
	}
	public String getStrDualSubGroupCode() {
		return strDualSubGroupCode;
	}
	public void setStrDualSubGroupCode(String strDualSubGroupCode) {
		this.strDualSubGroupCode = strDualSubGroupCode;
	}
	public String getStrDualDiseaseCode() {
		return strDualDiseaseCode;
	}
	public void setStrDualDiseaseCode(String strDualDiseaseCode) {
		this.strDualDiseaseCode = strDualDiseaseCode;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getStrDiseaseCodeChk() {
		return strDiseaseCodeChk;
	}
	public void setStrDiseaseCodeChk(String strDiseaseCodeChk) {
		this.strDiseaseCodeChk = strDiseaseCodeChk;
	}
	public String getStrDualDiseaseCodeChk() {
		return strDualDiseaseCodeChk;
	}
	public void setStrDualDiseaseCodeChk(String strDualDiseaseCodeChk) {
		this.strDualDiseaseCodeChk = strDualDiseaseCodeChk;
	}
	public String getStrRemark() {
		return strRemark;
	}
	public void setStrRemark(String strRemark) {
		this.strRemark = strRemark;
	}
	/*
	 * Reset the Add Page Components
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{		
			this.diagnosticTerm="";
			this.hospitalSynonym="";
			
			this.strDiseaseCodeChk="";
			this.strDualDiseaseCodeChk="";
			this.strDualDiseaseCodeChk="";
			
			this.icdGroupCode="-1";
			this.icdSubgroupCode="-1";
			this.diseaseCode="-1";
			
			this.strDualGroupCode="";
			this.strDualSubGroupCode="";
			this.strDualDiseaseCode="";
			
			this.strRemark="";
	}
}
