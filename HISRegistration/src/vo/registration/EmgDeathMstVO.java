package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 06-May-2014
 */

public class EmgDeathMstVO extends ValueObject{
	
	private String strEmgDeathMannerCode;
	private String strEmgDeathManner;
	private String strEmgDeathMannerDesc;
	private String strIsPostMortem;
	private String strIsAccidental;

	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldEmgDeathManner;

	
	/*public void reset()
	{
		strEmgDeathManner="";
		strEmgDeathMannerDesc="";
		strIsPostMortem="";
		strIsAccidental="";
	}	*/
	
	
	
	public String getStrEmgDeathMannerCode() {
		return strEmgDeathMannerCode;
	}
	public void setStrEmgDeathMannerCode(String strEmgDeathMannerCode) {
		this.strEmgDeathMannerCode = strEmgDeathMannerCode;
	}
	public String getStrEmgDeathMannerDesc() {
		return strEmgDeathMannerDesc;
	}
	public void setStrEmgDeathMannerDesc(String strEmgDeathMannerDesc) {
		this.strEmgDeathMannerDesc = strEmgDeathMannerDesc;
	}
	public String getStrIsPostMortem() {
		return strIsPostMortem;
	}
	public void setStrIsPostMortem(String strIsPostMortem) {
		this.strIsPostMortem = strIsPostMortem;
	}
	public String getStrIsAccidental() {
		return strIsAccidental;
	}
	public void setStrIsAccidental(String strIsAccidental) {
		this.strIsAccidental = strIsAccidental;
	}
	public String getStrOldEmgDeathManner() {
		return strOldEmgDeathManner;
	}
	public void setStrOldEmgDeathManner(String strOldEmgDeathManner) {
		this.strOldEmgDeathManner = strOldEmgDeathManner;
	}
	public String getStrEmgDeathManner() {
		return strEmgDeathManner;
	}
	public void setStrEmgDeathManner(String strEmgDeathManner) {
		this.strEmgDeathManner = strEmgDeathManner;
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
		
	
}
