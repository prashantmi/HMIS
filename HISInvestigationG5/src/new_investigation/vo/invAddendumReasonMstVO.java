package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class invAddendumReasonMstVO extends ValueObject {

	
	private String reason;
	private String reasonCode;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	
	
	
}
