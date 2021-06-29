package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class filmMstFB extends ActionForm
{
	private String testCode;
	private String filmType;
	private String filmSizeLength;
	private String filmSizeBreadth;
	private String remarks;
	private String noOfFilmReqd;
	private String itemStoreId;
	private String slNO;
	private String hmode;
	private String chk[];
	private String selectedChk;
    private String storeid; 
    private String filmid;

	 



	 
	 



	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.testCode="-1";
		this.filmSizeBreadth="";
		this.filmSizeLength="";
		this.filmType="";
		this.itemStoreId="-1";
		this.noOfFilmReqd="";
		this.remarks="";
		this.storeid="-1";
		this.noOfFilmReqd="";
		
		
		   
	}











	public String getTestCode() {
		return testCode;
	}











	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}











	public String getFilmType() {
		return filmType;
	}











	public void setFilmType(String filmType) {
		this.filmType = filmType;
	}











	public String getFilmSizeLength() {
		return filmSizeLength;
	}











	public void setFilmSizeLength(String filmSizeLength) {
		this.filmSizeLength = filmSizeLength;
	}











	public String getFilmSizeBreadth() {
		return filmSizeBreadth;
	}











	public void setFilmSizeBreadth(String filmSizeBreadth) {
		this.filmSizeBreadth = filmSizeBreadth;
	}











	public String getRemarks() {
		return remarks;
	}











	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}











	public String getNoOfFilmReqd() {
		return noOfFilmReqd;
	}











	public void setNoOfFilmReqd(String noOfFilmReqd) {
		this.noOfFilmReqd = noOfFilmReqd;
	}











	public String getItemStoreId() {
		return itemStoreId;
	}











	public void setItemStoreId(String itemStoreId) {
		this.itemStoreId = itemStoreId;
	}











	public String getSlNO() {
		return slNO;
	}











	public void setSlNO(String slNO) {
		this.slNO = slNO;
	}











	public String getHmode() {
		return hmode;
	}











	public void setHmode(String hmode) {
		this.hmode = hmode;
	}











	public String[] getChk() {
		return chk;
	}











	public void setChk(String[] chk) {
		this.chk = chk;
	}











	public String getSelectedChk() {
		return selectedChk;
	}











	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}











	public String getStoreid() {
		return storeid;
	}











	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}











	public String getFilmid() {
		return filmid;
	}











	public void setFilmid(String filmid) {
		this.filmid = filmid;
	}
		
		



}
