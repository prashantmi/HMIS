package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class FilmMstVO extends ValueObject {

	private String testCode;
	private String filmType;
	private String filmSizeLength;
	private String filmSizeBreadth;
	private String remarks;
	private String noOfFilmReqd;
	private String itemStoreId;
	private String slNO;
	private String storeid;
	
	
	private String testName;
	private String filmid;
	
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

	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
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
