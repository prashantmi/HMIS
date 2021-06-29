package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OPDFileMovementFB extends ActionForm {

	
	private String hmode;
	private String mrdCode;
	private String mrdRecordId[];
	private String handOverToId;
	//private String handOverToName;
	private String remarks;

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.setMrdRecordId(new String[]{});
		this.setHandOverToId("-1");
		this.setRemarks("");
		super.reset(mapping, request);
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getHandOverToId() {
		return handOverToId;
	}

	public void setHandOverToId(String handOverToId) {
		this.handOverToId = handOverToId;
	}

	/*public String getHandOverToName() {
		return handOverToName;
	}

	public void setHandOverToName(String handOverToName) {
		this.handOverToName = handOverToName;
	}*/

	public String getMrdCode() {
		return mrdCode;
	}

	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}

	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String[] getMrdRecordId() {
		return mrdRecordId;
	}

	public void setMrdRecordId(String[] mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}

	

}
