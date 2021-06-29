package vo.registration;

import java.util.List;

/**
 * @author s.singaravelan
 * DATE : 07-Feb-2013
 */

public class PatCatDocVO {	
	
	
	private String strPatCategoryCode;
	private String strPatCategoryName;
	private String strDocCode;
	private String strPatCatSlNo;
	private String strIsReq;
	
	private String strMappedDocCodes[];
	private String strUnMappedDocCodes[];
	
	private String strHospitalCode;
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private List strMappedDocCodesLog;
	private List strUnMappedDocCodeLog;
	
	
	public String getStrPatCategoryCode() {
		return strPatCategoryCode;
	}
	public void setStrPatCategoryCode(String strPatCategoryCode) {
		this.strPatCategoryCode = strPatCategoryCode;
	}
	public String getStrPatCategoryName() {
		return strPatCategoryName;
	}
	public void setStrPatCategoryName(String strPatCategoryName) {
		this.strPatCategoryName = strPatCategoryName;
	}
	public String[] getStrMappedDocCodes() {
		return strMappedDocCodes;
	}
	public void setStrMappedDocCodes(String[] strMappedDocCodes) {
		this.strMappedDocCodes = strMappedDocCodes;
	}
	public String[] getStrUnMappedDocCodes() {
		return strUnMappedDocCodes;
	}
	public void setStrUnMappedDocCodes(String[] strUnMappedDocCodes) {
		this.strUnMappedDocCodes = strUnMappedDocCodes;
	}
	public String getStrPatCatSlNo() {
		return strPatCatSlNo;
	}
	public void setStrPatCatSlNo(String strPatCatSlNo) {
		this.strPatCatSlNo = strPatCatSlNo;
	}
	public String getStrIsReq() {
		return strIsReq;
	}
	public void setStrIsReq(String strIsReq) {
		this.strIsReq = strIsReq;
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
	public String getStrDocCode() {
		return strDocCode;
	}
	public void setStrDocCode(String strDocCode) {
		this.strDocCode = strDocCode;
	}
	public List getStrMappedDocCodesLog() {
		return strMappedDocCodesLog;
	}
	public void setStrMappedDocCodesLog(List strMappedDocCodesLog) {
		this.strMappedDocCodesLog = strMappedDocCodesLog;
	}
	public List getStrUnMappedDocCodeLog() {
		return strUnMappedDocCodeLog;
	}
	public void setStrUnMappedDocCodeLog(List strUnMappedDocCodeLog) {
		this.strUnMappedDocCodeLog = strUnMappedDocCodeLog;
	}
	
	
	
}
