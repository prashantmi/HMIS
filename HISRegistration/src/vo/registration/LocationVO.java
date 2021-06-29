package vo.registration;

/**
 * @author s.singaravelan
 * DATE : 02-Jan-2014
 */

public class LocationVO {
	
	private String strLocCode;
	private String strLocDescription;

	private String strFloor;
	private String strLocTypeCode;
	private String strBuilding;

	private String strLandmark;
	private String strRoom;
	private String strBlock;
	
	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldLocDescription;

	
	public void reset()
	{
		strLocDescription="";
		strLocTypeCode="-1";
		strLandmark="";
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
	
	public String getStrLocDescription() {
		return strLocDescription;
	}
	public void setStrLocDescription(String strLocDescription) {
		this.strLocDescription = strLocDescription;
	}
	public String getStrFloor() {
		return strFloor;
	}
	public void setStrFloor(String strFloor) {
		this.strFloor = strFloor;
	}
	public String getStrLocTypeCode() {
		return strLocTypeCode;
	}
	public void setStrLocTypeCode(String strLocTypeCode) {
		this.strLocTypeCode = strLocTypeCode;
	}
	public String getStrBuilding() {
		return strBuilding;
	}
	public void setStrBuilding(String strBuilding) {
		this.strBuilding = strBuilding;
	}
	public String getStrLandmark() {
		return strLandmark;
	}
	public void setStrLandmark(String strLandmark) {
		this.strLandmark = strLandmark;
	}
	public String getStrRoom() {
		return strRoom;
	}
	public void setStrRoom(String strRoom) {
		this.strRoom = strRoom;
	}
	public String getStrBlock() {
		return strBlock;
	}
	public void setStrBlock(String strBlock) {
		this.strBlock = strBlock;
	}


	public String getStrOldLocDescription() {
		return strOldLocDescription;
	}


	public void setStrOldLocDescription(String strOldLocDescription) {
		this.strOldLocDescription = strOldLocDescription;
	}
	
	
	
}
