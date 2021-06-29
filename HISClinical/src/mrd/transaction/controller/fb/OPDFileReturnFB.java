package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OPDFileReturnFB extends ActionForm {
	
	private String hmode;
	private String mrdCode;
	private String mrdRecordId[];
	private String handOverById;
	//private String handOverByName;
	private String remarks;

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.setMrdRecordId(new String[]{});
		this.setHandOverById("-1");
		this.setRemarks("");
		super.reset(mapping, request);
	}
	
	public String getMrdCode() {
		return mrdCode;
	}

	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}

	public String[] getMrdRecordId() {
		return mrdRecordId;
	}

	public void setMrdRecordId(String[] mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
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

	public String getHandOverById() {
		return handOverById;
	}

	public void setHandOverById(String handOverById) {
		this.handOverById = handOverById;
	}

	/*public String getHandOverByName() {
		return handOverByName;
	}

	public void setHandOverByName(String handOverByName) {
		this.handOverByName = handOverByName;
	}*/

}
