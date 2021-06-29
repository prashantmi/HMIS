package bmed.global.vo;

import javax.sql.rowset.WebRowSet;

public class MainteWarrantyContractVO {
	
    private String strBatchNo;
    private String strItemSlNo;
	private String strDisplayMode;
	private String strProcessMode;
	private String strItemId;
	private String strHospitalCode;
	private WebRowSet webRowSetWarranty;
	private WebRowSet webRowSetMaintenance;

	public String getStrDisplayMode() {
		return strDisplayMode;
	}

	public void setStrDisplayMode(String strDisplayMode) {
		this.strDisplayMode = strDisplayMode;
	}

	public String getStrProcessMode() {
		return strProcessMode;
	}

	public void setStrProcessMode(String strProcessMode) {
		this.strProcessMode = strProcessMode;
	}

	public String getStrItemId() {
		return strItemId;
	}

	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	public WebRowSet getWebRowSetWarranty() {
		return webRowSetWarranty;
	}

	public void setWebRowSetWarranty(WebRowSet webRowSetWarranty) {
		this.webRowSetWarranty = webRowSetWarranty;
	}

	public WebRowSet getWebRowSetMaintenance() {
		return webRowSetMaintenance;
	}

	public void setWebRowSetMaintenance(WebRowSet webRowSetMaintenance) {
		this.webRowSetMaintenance = webRowSetMaintenance;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public String getStrBatchNo() {
		return strBatchNo;
	}

	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}

	public String getStrItemSlNo() {
		return strItemSlNo;
	}

	public void setStrItemSlNo(String strItemSlNo) {
		this.strItemSlNo = strItemSlNo;
	}

}
