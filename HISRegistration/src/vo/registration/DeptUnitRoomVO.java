/**
 * 
 */
package vo.registration;

import java.util.Map;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 08-Jan-2014
 */
public class DeptUnitRoomVO extends ValueObject
{
	
	private String strDeptUnitCode;
	private String strDeptCode;
	private String strUnitCode;
	private String strRoomCode;
	private String strCapacity;
	private String strRoomSequence;
	private String strRoomName;
	private String strHospitalCode;
	private String strCapacityMode;
	
	private String strDeptName;
	private String strUnitName;
	private String strRemoveRoom;
	
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String[] existingRoomName=new String[]{};
	private String[] existingRoomCode;
	private String[] existingsequenceNo=new String[]{};
	private String[] newRoomCode=new String[]{};
	private String[] newSequenceNo=new String[]{};
	private String[] capacity=new String[]{};
	private String[] capacityMode=new String[]{};
	//private Map<Integer, Boolean> capacityMode;
	
	public DeptUnitRoomVO()
	{			
		//this.strCapacity="";
		//this.strCapacityMode="";
	}
	
	public DeptUnitRoomVO(String roomCode, String roomName, String roomsequence,String capacity,String capacityMode)
	{
		this.strRoomCode = roomCode;
		this.strRoomName = roomName;
		this.strRoomSequence = roomsequence;
		this.strCapacity = capacity;
		this.strCapacityMode = capacityMode;
	}
	
	public DeptUnitRoomVO(String _str, String _str1)
	{
		strDeptUnitCode = _str;
		strRoomCode = _str1;
	}
	
	public void reset() 
	{
		
	}
		
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrRoomCode() {
		return strRoomCode;
	}
	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}
	public String getStrCapacity() {
		return strCapacity;
	}
	public void setStrCapacity(String strCapacity) {
		this.strCapacity = strCapacity;
	}
	public String getStrRoomSequence() {
		return strRoomSequence;
	}
	public void setStrRoomSequence(String strRoomSequence) {
		this.strRoomSequence = strRoomSequence;
	}
	public String getStrRoomName() {
		return strRoomName;
	}
	public void setStrRoomName(String strRoomName) {
		this.strRoomName = strRoomName;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrCapacityMode() {
		return strCapacityMode;
	}
	public void setStrCapacityMode(String strCapacityMode) {
		this.strCapacityMode = strCapacityMode;
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
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}
	public String[] getExistingRoomName() {
		return existingRoomName;
	}
	public void setExistingRoomName(String[] existingRoomName) {
		this.existingRoomName = existingRoomName;
	}
	public String[] getExistingRoomCode() {
		return existingRoomCode;
	}
	public void setExistingRoomCode(String[] existingRoomCode) {
		this.existingRoomCode = existingRoomCode;
	}
	public String[] getExistingsequenceNo() {
		return existingsequenceNo;
	}
	public void setExistingsequenceNo(String[] existingsequenceNo) {
		this.existingsequenceNo = existingsequenceNo;
	}
	public String[] getNewRoomCode() {
		return newRoomCode;
	}
	public void setNewRoomCode(String[] newRoomCode) {
		this.newRoomCode = newRoomCode;
	}
	public String[] getNewSequenceNo() {
		return newSequenceNo;
	}
	public void setNewSequenceNo(String[] newSequenceNo) {
		this.newSequenceNo = newSequenceNo;
	}
	public String[] getCapacity() {
		return capacity;
	}
	public void setCapacity(String[] capacity) {
		this.capacity = capacity;
	}
	

	public String getStrRemoveRoom() {
		return strRemoveRoom;
	}

	public void setStrRemoveRoom(String strRemoveRoom) {
		this.strRemoveRoom = strRemoveRoom;
	}

//	public Map<Integer, Boolean> getCapacityMode() {
//		return capacityMode;
//	}
//
//	public void setCapacityMode(Map<Integer, Boolean> capacityMode) {
//		this.capacityMode = capacityMode;
//	}

	public String[] getCapacityMode() {
		return capacityMode;
	}

	public void setCapacityMode(String[] capacityMode) {
		this.capacityMode = capacityMode;
	}

	
}
