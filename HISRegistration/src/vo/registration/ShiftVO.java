package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 21-Jan-2014
 */

public class ShiftVO extends ValueObject{
	
	private String strShiftCode;
	private String strShiftDesc;

	private String strShiftStartTime;
	private String strShiftEndTime;
	private String strShiftType;

	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldShiftDesc;

	
	public void reset()
	{
		strShiftDesc="";
		strShiftStartTime="";
		strShiftEndTime="";
		strShiftType="-1";
		strIsValid="1";
	}	
	
	public String getStrShiftCode() {
		return strShiftCode;
	}
	public void setStrShiftCode(String strShiftCode) {
		this.strShiftCode = strShiftCode;
	}
	public String getStrShiftDesc() {
		return strShiftDesc;
	}
	public void setStrShiftDesc(String strShiftDesc) {
		this.strShiftDesc = strShiftDesc;
		System.out.println("strShiftDesc :"+this.strShiftDesc);
	}
	public String getStrShiftStartTime() {
		return strShiftStartTime;
	}
	public void setStrShiftStartTime(String strShiftStartTime) {
		this.strShiftStartTime = strShiftStartTime;
	}
	public String getStrShiftEndTime() {
		return strShiftEndTime;
	}
	public void setStrShiftEndTime(String strShiftEndTime) {
		this.strShiftEndTime = strShiftEndTime;
	}
	public String getStrShiftType() {
		return strShiftType;
	}
	public void setStrShiftType(String strShiftType) {
		this.strShiftType = strShiftType;
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

	public String getStrOldShiftDesc() {
		return strOldShiftDesc;
	}

	public void setStrOldShiftDesc(String strOldShiftDesc) {
		this.strOldShiftDesc = strOldShiftDesc;
	}
	
	
	
	
}
