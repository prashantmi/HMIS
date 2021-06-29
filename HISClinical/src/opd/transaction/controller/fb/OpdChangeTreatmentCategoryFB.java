package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpdChangeTreatmentCategoryFB extends ActionForm {
	
	private String patCrNo;
	private String episodeCode;
	private String department;
	private String departmentCode;
	private String patPrimaryCatCode;
	private String patPrimaryCat;
	private String patSecondaryCatCode;
    private String patSecondaryCat;  
    private String patPrevSecondaryCatCode;
    private String patPrevSecondaryCat;  	
    private String patNewSecondaryCatCode;
    private String patNewSecondaryCat; 
    private String remarks;
    private String hmode;
    private String[] selectEpisode;
    private String[] episodeToChangeSecondaryCategory;
    private String[] newSecCatCode;
    private String[] arrRemarks;
    private String[] arrValidUpto;
    private String[] categoryExpiryDate;
    private String[] identifySecCatCode;
    private String SecCatCodeAndExpiryDay;
    private String[] arrExpiryText;
    
    private String isIpdFlag;
    private String[] selectEpisodeVisitNo;
    private String[] hiddenNewSecCatCode;
    private String sysDate="";
    private String[] revokeChk; 
    private String isInvalidCatCode;
	
    
    public String getIsIpdFlag() {
		return isIpdFlag;
	}
	public void setIsIpdFlag(String isIpdFlag) {
		this.isIpdFlag = isIpdFlag;
	}
	public String[] getSelectEpisodeVisitNo() {
		return selectEpisodeVisitNo;
	}
	public void setSelectEpisodeVisitNo(String[] selectEpisodeVisitNo) {
		this.selectEpisodeVisitNo = selectEpisodeVisitNo;
	}
	public String[] getHiddenNewSecCatCode() {
		return hiddenNewSecCatCode;
	}
	public void setHiddenNewSecCatCode(String[] hiddenNewSecCatCode) {
		this.hiddenNewSecCatCode = hiddenNewSecCatCode;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String[] getRevokeChk() {
		return revokeChk;
	}
	public void setRevokeChk(String[] revokeChk) {
		this.revokeChk = revokeChk;
	}
	public String getIsInvalidCatCode() {
		return isInvalidCatCode;
	}
	public void setIsInvalidCatCode(String isInvalidCatCode) {
		this.isInvalidCatCode = isInvalidCatCode;
	}
	public String[] getArrValidUpto() {
		return arrValidUpto;
	}
	public void setArrValidUpto(String[] arrValidUpto) {
		this.arrValidUpto = arrValidUpto;
	}
	public String[] getCategoryExpiryDate() {
		return categoryExpiryDate;
	}
	public void setCategoryExpiryDate(String[] categoryExpiryDate) {
		this.categoryExpiryDate = categoryExpiryDate;
	}
	public String getSecCatCodeAndExpiryDay() {
		return SecCatCodeAndExpiryDay;
	}
	public void setSecCatCodeAndExpiryDay(String secCatCodeAndExpiryDay) {
		SecCatCodeAndExpiryDay = secCatCodeAndExpiryDay;
	}
	public String[] getArrExpiryText() {
		return arrExpiryText;
	}
	public void setArrExpiryText(String[] arrExpiryText) {
		this.arrExpiryText = arrExpiryText;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}
	public String getPatSecondaryCatCode() {
		return patSecondaryCatCode;
	}
	public void setPatSecondaryCatCode(String patSecondaryCatCode) {
		this.patSecondaryCatCode = patSecondaryCatCode;
	}
	public String getPatSecondaryCat() {
		return patSecondaryCat;
	}
	public void setPatSecondaryCat(String patSecondaryCat) {
		this.patSecondaryCat = patSecondaryCat;
	}
	public String getPatPrevSecondaryCatCode() {
		return patPrevSecondaryCatCode;
	}
	public void setPatPrevSecondaryCatCode(String patPrevSecondaryCatCode) {
		this.patPrevSecondaryCatCode = patPrevSecondaryCatCode;
	}
	public String getPatPrevSecondaryCat() {
		return patPrevSecondaryCat;
	}
	public void setPatPrevSecondaryCat(String patPrevSecondaryCat) {
		this.patPrevSecondaryCat = patPrevSecondaryCat;
	}
	public String getPatNewSecondaryCatCode() {
		return patNewSecondaryCatCode;
	}
	public void setPatNewSecondaryCatCode(String patNewSecondaryCatCode) {
		this.patNewSecondaryCatCode = patNewSecondaryCatCode;
	}
	public String getPatNewSecondaryCat() {
		return patNewSecondaryCat;
	}
	public void setPatNewSecondaryCat(String patNewSecondaryCat) {
		this.patNewSecondaryCat = patNewSecondaryCat;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getSelectEpisode() {
		return selectEpisode;
	}
	public void setSelectEpisode(String[] selectEpisode) {
		this.selectEpisode = selectEpisode;
	}
	public String[] getEpisodeToChangeSecondaryCategory() {
		return episodeToChangeSecondaryCategory;
	}
	public void setEpisodeToChangeSecondaryCategory(
			String[] episodeToChangeSecondaryCategory) {
		this.episodeToChangeSecondaryCategory = episodeToChangeSecondaryCategory;
	}
	public String[] getNewSecCatCode() {
		return newSecCatCode;
	}
	public void setNewSecCatCode(String[] newSecCatCode) {
		this.newSecCatCode = newSecCatCode;
	}
	public String[] getArrRemarks() {
		return arrRemarks;
	}
	public void setArrRemarks(String[] arrRemarks) {
		this.arrRemarks = arrRemarks;
	}
	public String[] getIdentifySecCatCode() {
		return identifySecCatCode;
	}
	public void setIdentifySecCatCode(String[] identifySecCatCode) {
		this.identifySecCatCode = identifySecCatCode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request){
		
		super.reset(mapping, request);
		
		this.setEpisodeCode("");
		this.setPatPrimaryCatCode("");
		this.setPatPrimaryCat("");
	    this.setPatSecondaryCatCode("");
	    this.setPatSecondaryCat("");  
	    this.setDepartmentCode("");
		this.setDepartment("");
	    this.setPatPrevSecondaryCatCode("");
	    this.setPatPrevSecondaryCat("");  	
	    this.setPatNewSecondaryCatCode("");
	    this.setPatNewSecondaryCat(""); 
	    this.setRemarks("");
	    this.setHmode("");
	    this.setSelectEpisode(new String[] {});
	    this.setNewSecCatCode(new String[] {});
	    this.setEpisodeToChangeSecondaryCategory(new String[] {});
	    this.setIdentifySecCatCode(new String[] {});
	    this.setArrRemarks(new String[] {});
	    this.setArrValidUpto(new String[] {});
	    this.setCategoryExpiryDate(new String[] {});
	    
	    //this.setEpisodeCode(new String[] {});
	    //this.setPatSecondaryCatCode(new String[] {});
	    //this.setRemarks(new String[] {});
	    //this.setPatSecondaryCat(new String[] {}); 
	}

}
