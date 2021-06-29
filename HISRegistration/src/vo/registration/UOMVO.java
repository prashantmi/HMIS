package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 26-Feb-2014
 */

public class UOMVO extends ValueObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strUOMId;
	private String strUOMName;
	private String strUOMShortName;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldUOMName;

	public void reset() {
		this.strUOMName="";
		this.strUOMShortName="";
	}
	public String getStrUOMId() {
		return strUOMId;
	}
	public void setStrUOMId(String strUOMId) {
		this.strUOMId = strUOMId;
	}
	public String getStrUOMName() {
		return strUOMName;
	}
	public void setStrUOMName(String strUOMName) {
		this.strUOMName = strUOMName;
	}
	public String getStrUOMShortName() {
		return strUOMShortName;
	}
	public void setStrUOMShortName(String strUOMShortName) {
		this.strUOMShortName = strUOMShortName;
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
	public String getStrOldUOMName() {
		return strOldUOMName;
	}
	public void setStrOldUOMName(String strOldUOMName) {
		this.strOldUOMName = strOldUOMName;
	}
	
	
	
}
