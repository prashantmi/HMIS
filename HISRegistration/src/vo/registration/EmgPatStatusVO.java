package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 05-May-2014
 */

public class EmgPatStatusVO extends ValueObject{
	
	private String strEmgPatStatusCode;
	private String strEmgPatStatusDesc;
	
	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldEmgPatStatusDesc;

	
	public void reset()
	{
		strEmgPatStatusCode="";
		strEmgPatStatusDesc="";
		strIsValid="1";
	}	
	
	
	public String getStrEmgPatStatusCode() {
		return strEmgPatStatusCode;
	}
	public void setStrEmgPatStatusCode(String strEmgPatStatusCode) {
		this.strEmgPatStatusCode = strEmgPatStatusCode;
	}
	public String getStrEmgPatStatusDesc() {
		return strEmgPatStatusDesc;
	}
	public void setStrEmgPatStatusDesc(String strEmgPatStatusDesc) {
		this.strEmgPatStatusDesc = strEmgPatStatusDesc;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public void setStrErrMsg(String string) {
		// TODO Auto-generated method stub
		
	}
	public String getStrOldEmgPatStatusDesc() {
		return strOldEmgPatStatusDesc;
	}
	public void setStrOldEmgPatStatusDesc(String strOldEmgPatStatusDesc) {
		this.strOldEmgPatStatusDesc = strOldEmgPatStatusDesc;
	}
	
	
	
	
}
