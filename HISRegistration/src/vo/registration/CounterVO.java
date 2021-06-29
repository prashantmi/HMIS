/**
 * 
 */
package vo.registration;

/**
 * @author s.singaravelan
 * DATE : 17-Feb-2014
 */
public class CounterVO 
{
	
	private String strCounterCode;
	private String strCounterName;
	private String strCounterType;
	private String strLocationCode;	
	private String strIPAddress;	
	private String strRemarks;
	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldCounterName;

	public void reset() {
		this.strCounterName="";
		this.strCounterType="-1";
		this.strLocationCode="-1";	
		this.strIPAddress="";
		this.strRemarks="";		
	}
	
	public String getStrCounterCode() {
		return strCounterCode;
	}
	public void setStrCounterCode(String strCounterCode) {
		this.strCounterCode = strCounterCode;
	}
	public String getStrCounterName() {
		return strCounterName;
	}
	public void setStrCounterName(String strCounterName) {
		this.strCounterName = strCounterName;
	}
	public String getStrCounterType() {
		return strCounterType;
	}
	public void setStrCounterType(String strCounterType) {
		this.strCounterType = strCounterType;
	}
	public String getStrLocationCode() {
		return strLocationCode;
	}
	public void setStrLocationCode(String strLocationCode) {
		this.strLocationCode = strLocationCode;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
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
	public String getStrIPAddress() {
		return strIPAddress;
	}
	public void setStrIPAddress(String strIPAddress) {
		this.strIPAddress = strIPAddress;
	}
	public String getStrOldCounterName() {
		return strOldCounterName;
	}
	public void setStrOldCounterName(String strOldCounterName) {
		this.strOldCounterName = strOldCounterName;
	}

}
