package hisglobal.vo;

import hisglobal.hisconfig.Config;
import java.util.Comparator;

public class RoomChangeVO extends ValueObject{

	
	
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	private String fromRoomCode;
	private String toRoomCode;
	private String changeReason;
	private String seatID;	
	private String entryDate;	
	private String isValid;
	
	private String fromDeprUnitCode;
	private String toDeptUnitCode;
	private String changeType;
	private String qNo;
	private String roomName;
	private String chnageToRoomCode;
	
	
	public String getChnageToRoomCode() {
		return chnageToRoomCode;
	}
	public void setChnageToRoomCode(String chnageToRoomCode) {
		this.chnageToRoomCode = chnageToRoomCode;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getQNo() {
		return qNo;
	}
	public void setQNo(String no) {
		qNo = no;
	}
	public String getFromDeprUnitCode() {
		return fromDeprUnitCode;
	}
	public void setFromDeprUnitCode(String fromDeprUnitCode) {
		this.fromDeprUnitCode = fromDeprUnitCode;
	}
	public String getToDeptUnitCode() {
		return toDeptUnitCode;
	}
	public void setToDeptUnitCode(String toDeptUnitCode) {
		this.toDeptUnitCode = toDeptUnitCode;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getFromRoomCode() {
		return fromRoomCode;
	}
	public void setFromRoomCode(String fromRoomCode) {
		this.fromRoomCode = fromRoomCode;
	}
	public String getToRoomCode() {
		return toRoomCode;
	}
	public void setToRoomCode(String toRoomCode) {
		this.toRoomCode = toRoomCode;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}	
	
	
	
	
	
}
