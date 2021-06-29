/**
 * 
 */
package vo.registration;

/**
 * @author s.singaravelan
 * DATE : 19-Dec-2013
 */
public class VerificationDocVO {
	
	private String strDocCode;
	private String strDocName;
	private String strIsAlternate;
	private String strIdSize;
	private String strIdValidationType;
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	private String strIsAuth;
	
	public void reset()
	{
		strDocName="";
		strIsAlternate="";
		strIdSize="";
		strIdValidationType="";
		strIsAuth="";
	}

	public String getStrDocCode() {
		return strDocCode;
	}
	public void setStrDocCode(String strDocCode) {
		this.strDocCode = strDocCode;
	}
	public String getStrDocName() {
		return strDocName;
	}
	public void setStrDocName(String strDocName) {
		this.strDocName = strDocName;
	}
	public String getStrIsAlternate() {
		return strIsAlternate;
	}
	public void setStrIsAlternate(String strIsAlternate) {
		this.strIsAlternate = strIsAlternate;
	}
	public String getStrIdSize() {
		return strIdSize;
	}
	public void setStrIdSize(String strIdSize) {
		this.strIdSize = strIdSize;
	}
	public String getStrIdValidationType() {
		return strIdValidationType;
	}
	public void setStrIdValidationType(String strIdValidationType) {
		this.strIdValidationType = strIdValidationType;
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

	public String getStrIsAuth() {
		return strIsAuth;
	}

	public void setStrIsAuth(String strIsAuth) {
		this.strIsAuth = strIsAuth;
	}

}
