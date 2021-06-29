package vo.registration;

/**
 * @author s.singaravelan
 * DATE : 02-Jan-2014
 */

public class RoomVO {
	
	private String strRoomCode;
	private String strRoomName;

	private String strRoomDescription;
	private String strLocCode;
	private String strRoomId;

	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldRoomName;

	
	public void reset()
	{
		strRoomName="";
		strLocCode="-1";
		strRoomDescription="";
	}

	public String getStrLocCode() {
		return strLocCode;
	}
	public void setStrLocCode(String strLocCode) {
		this.strLocCode = strLocCode;
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
	public String getStrRoomCode() {
		return strRoomCode;
	}
	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}
	public String getStrRoomName() {
		return strRoomName;
	}
	public void setStrRoomName(String strRoomName) {
		this.strRoomName = strRoomName;
	}
	public String getStrRoomDescription() {
		return strRoomDescription;
	}
	public void setStrRoomDescription(String strRoomDescription) {
		this.strRoomDescription = strRoomDescription;
	}
	public String getStrRoomId() {
		return strRoomId;
	}
	public void setStrRoomId(String strRoomId) {
		this.strRoomId = strRoomId;
	}

	public String getStrOldRoomName() {
		return strOldRoomName;
	}

	public void setStrOldRoomName(String strOldRoomName) {
		this.strOldRoomName = strOldRoomName;
	}
	
		
	
}
