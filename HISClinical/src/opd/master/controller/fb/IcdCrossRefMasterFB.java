package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class IcdCrossRefMasterFB extends ActionForm 
{
	private static final long serialVersionUID = 1L;
	private String hmode;
	private String strCheck;
	
	private String indexCode;
	private String strModifier1;
	private String strModifier2;
	private String strModifier3;
	private String strModifier4;
	private String strModifier5;
	private String strModifier6;
	private String strModifier7;
	private String strModifier8;
	private String strModifier9;
	
	private String strIndexTerm;
	private String strModTerm1;
	private String strModTerm2;
	private String strModTerm3;
	private String strModTerm4;
	private String strModTerm5;
	private String strModTerm6;
	private String strModTerm7;
	private String strModTerm8;
	private String strModTerm9;
	
	private String seeTermRadio;
	private String strCheckSee;
	private String refIndexCode;
	private String strRefModifier1;
	private String strIndexTermPlus;
	private String pageFlag;
	private String indexModifierID;

	private String seeIndexModifierId;
	private String seeAlsoIndexModifierId;
	
	private String seeTermsFlag;
	

	
	
	
	private String strSlNo;
	private String seeTerm;
	private String seeAlsoTerm;
	private String seeTermCode;
	private String seeAlsoTermCode;
	private String isValid;

	public IcdCrossRefMasterFB()
	{
		this.strCheck = "0";
		this.strCheckSee = "0";
		this.seeTermRadio = "0";
		this.seeTerm = null;
		this.seeAlsoTerm = null;
	}
	
	
	public String getStrCheck() {
		return strCheck;
	}
	public void setStrCheck(String strCheck) {
		this.strCheck = strCheck;
	}
	public String getStrModifier1() {
		return strModifier1;
	}
	public void setStrModifier1(String strModifier1) {
		this.strModifier1 = strModifier1;
	}
	public String getStrModifier2() {
		return strModifier2;
	}
	public void setStrModifier2(String strModifier2) {
		this.strModifier2 = strModifier2;
	}
	public String getStrModifier3() {
		return strModifier3;
	}
	public void setStrModifier3(String strModifier3) {
		this.strModifier3 = strModifier3;
	}
	public String getStrModifier4() {
		return strModifier4;
	}
	public void setStrModifier4(String strModifier4) {
		this.strModifier4 = strModifier4;
	}
	public String getStrModifier5() {
		return strModifier5;
	}
	public void setStrModifier5(String strModifier5) {
		this.strModifier5 = strModifier5;
	}
	public String getStrModifier6() {
		return strModifier6;
	}
	public void setStrModifier6(String strModifier6) {
		this.strModifier6 = strModifier6;
	}
	public String getStrModifier7() {
		return strModifier7;
	}
	public void setStrModifier7(String strModifier7) {
		this.strModifier7 = strModifier7;
	}
	public String getStrModifier8() {
		return strModifier8;
	}
	public void setStrModifier8(String strModifier8) {
		this.strModifier8 = strModifier8;
	}
	public String getStrModifier9() {
		return strModifier9;
	}
	public void setStrModifier9(String strModifier9) {
		this.strModifier9 = strModifier9;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public String getSeeTerm() {
		return seeTerm;
	}


	public void setSeeTerm(String seeTerm) {
		this.seeTerm = seeTerm;
	}


	public String getSeeAlsoTerm() {
		return seeAlsoTerm;
	}


	public void setSeeAlsoTerm(String seeAlsoTerm) {
		this.seeAlsoTerm = seeAlsoTerm;
	}


	public String getStrCheckSee() {
		return strCheckSee;
	}


	public void setStrCheckSee(String strCheckSee) {
		this.strCheckSee = strCheckSee;
	}


	public String getStrIndexTerm() {
		return strIndexTerm;
	}


	public void setStrIndexTerm(String strIndexTerm) {
		this.strIndexTerm = strIndexTerm;
	}


	public String getStrModTerm1() {
		return strModTerm1;
	}


	public void setStrModTerm1(String strModTerm1) {
		this.strModTerm1 = strModTerm1;
	}


	public String getStrModTerm2() {
		return strModTerm2;
	}


	public void setStrModTerm2(String strModTerm2) {
		this.strModTerm2 = strModTerm2;
	}


	public String getStrModTerm3() {
		return strModTerm3;
	}


	public void setStrModTerm3(String strModTerm3) {
		this.strModTerm3 = strModTerm3;
	}


	public String getStrModTerm4() {
		return strModTerm4;
	}


	public void setStrModTerm4(String strModTerm4) {
		this.strModTerm4 = strModTerm4;
	}


	public String getStrModTerm5() {
		return strModTerm5;
	}


	public void setStrModTerm5(String strModTerm5) {
		this.strModTerm5 = strModTerm5;
	}


	public String getStrModTerm6() {
		return strModTerm6;
	}


	public void setStrModTerm6(String strModTerm6) {
		this.strModTerm6 = strModTerm6;
	}


	public String getStrModTerm7() {
		return strModTerm7;
	}


	public void setStrModTerm7(String strModTerm7) {
		this.strModTerm7 = strModTerm7;
	}


	public String getStrModTerm8() {
		return strModTerm8;
	}


	public void setStrModTerm8(String strModTerm8) {
		this.strModTerm8 = strModTerm8;
	}


	public String getStrModTerm9() {
		return strModTerm9;
	}


	public void setStrModTerm9(String strModTerm9) {
		this.strModTerm9 = strModTerm9;
	}


	public String getSeeTermRadio() {
		return seeTermRadio;
	}


	public void setSeeTermRadio(String seeTermRadio) {
		this.seeTermRadio = seeTermRadio;
	}


	public String getPageFlag() {
		return pageFlag;
	}


	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	
	/*
	 * Reset the Page Components
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{		
			this.indexCode="";
			this.seeTermsFlag="0";
			
			this.strCheck="0";
			this.strModifier1="";
			this.strModifier2="";
			this.strModifier3="";
			this.strModifier4="";
			this.strModifier5="";
			this.strModifier6="";
			this.strModifier7="";
			this.strModifier8="";
			this.strModifier9="";
			
//			this.modifierLevel ="";
//			this.modifier="";
//			this.isWith="0";
//			this.parentIndexModifierCode="";
//			
//			this.diseaseCodeChk="";
//			this.icdGroupCode="";
//			this.icdSubgroupCode="";
//			
//			this.dualDiseaseCodeChk="";
//			
//			this.diseaseCode="";
//			this.dualDiseaseCode="";
//			this.remark="";
	}


	public String getSeeTermCode() {
		return seeTermCode;
	}


	public void setSeeTermCode(String seeTermCode) {
		this.seeTermCode = seeTermCode;
	}


	public String getSeeAlsoTermCode() {
		return seeAlsoTermCode;
	}


	public void setSeeAlsoTermCode(String seeAlsoTermCode) {
		this.seeAlsoTermCode = seeAlsoTermCode;
	}


	public String getStrSlNo() {
		return strSlNo;
	}


	public void setStrSlNo(String strSlNo) {
		this.strSlNo = strSlNo;
	}


	public String getIndexModifierID() {
		return indexModifierID;
	}


	public void setIndexModifierID(String indexModifierID) {
		this.indexModifierID = indexModifierID;
	}


	public String getRefIndexCode() {
		return refIndexCode;
	}


	public void setRefIndexCode(String refIndexCode) {
		this.refIndexCode = refIndexCode;
	}


	public String getStrRefModifier1() {
		return strRefModifier1;
	}


	public void setStrRefModifier1(String strRefModifier1) {
		this.strRefModifier1 = strRefModifier1;
	}


	public String getIsValid() {
		return isValid;
	}


	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}


	public String getStrIndexTermPlus() {
		return strIndexTermPlus;
	}


	public void setStrIndexTermPlus(String strIndexTermPlus) {
		this.strIndexTermPlus = strIndexTermPlus;
	}


	public String getSeeTermsFlag()
	{
		return seeTermsFlag;
	}


	public void setSeeTermsFlag(String seeTermsFlag)
	{
		this.seeTermsFlag = seeTermsFlag;
	}


	public String getSeeIndexModifierId()
	{
		return seeIndexModifierId;
	}


	public void setSeeIndexModifierId(String seeIndexModifierId)
	{
		this.seeIndexModifierId = seeIndexModifierId;
	}


	public String getSeeAlsoIndexModifierId()
	{
		return seeAlsoIndexModifierId;
	}


	public void setSeeAlsoIndexModifierId(String seeAlsoIndexModifierId)
	{
		this.seeAlsoIndexModifierId = seeAlsoIndexModifierId;
	}
}
