package hisglobal.vo;

import java.io.Serializable;

public class DeptUnitRoom implements Serializable
{

	private String roomCode;
	private String roomName;
	private String roomsequence;
	private String hospitalCode;

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public DeptUnitRoom(String roomCode, String roomName, String roomsequence)
	{
		this.roomCode = roomCode;
		this.roomName = roomName;
		this.roomsequence = roomsequence;
	}

	public String getRoomCode()
	{
		return roomCode;
	}

	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}

	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	public String getRoomsequence()
	{
		return roomsequence;
	}

	public void setRoomsequence(String roomsequence)
	{
		this.roomsequence = roomsequence;
	}
}
