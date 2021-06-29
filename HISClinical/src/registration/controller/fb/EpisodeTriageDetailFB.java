package registration.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class EpisodeTriageDetailFB extends CRNoFB
{
	private String hmode;
	private String isDirectCall;
	
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	
	private String entryMode;	// ALL, OUT, TOMODIFY
	
	// Patient Detail
	private String patCondition;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patDOB;
	private String patGender;
	private String patGenderCode;
	private String patMaritalStatus;
	private String patMaritalStatusCode;
	private String patReligion;
	private String patReligionCode;
	private String patGuardianName;
	private String patMomName;
	private String isReferred;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;
	private String patRefDoctor;
	private String isUnknown;


	private String emergencyCode;
	private String inDate;
	private String inTimeHr;
	private String inTimeMin;
	private String inTime;
	private String inPatStatus;
	private String inCondition;
	private String outDate;
	private String outTimeHr;
	private String outTimeMin;
	private String outTime;
	private String outPatStatus;
	private String outCondition;
	private String remarks;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String systemIPAddress;
	
	
	public EpisodeTriageDetailFB()
	{
		this.isDirectCall="";
		
		this.entryMode="ALL";
		
		this.patCondition="";
		this.patFirstName="";
		this.patMiddleName="";
		this.patLastName="";
		this.patDOB="";
		this.patGender="";
		this.patGenderCode="";
		this.patMaritalStatus="";
		this.patMaritalStatusCode="";
		this.patReligion="";
		this.patReligionCode="";
		this.patGuardianName="";
		this.patMomName="";
		this.isReferred="";
		this.patRefGnctdHospitalCode="";
		this.patRefHospitalName="";
		this.patRefDoctor="";
		this.isUnknown="";


		this.episodeCode="";
		this.episodeVisitNo="";
		
		this.emergencyCode="";
		this.inDate="";
		this.inTimeHr="00";
		this.inTimeMin="00";
		this.inTime="00:00";
		this.inPatStatus="";
		this.inCondition="";
		this.outDate="";
		this.outTimeHr="";
		this.outTimeMin="";
		this.outTime="";
		this.outPatStatus="";
		this.outCondition="";
		this.remarks="";
		this.entryDate="";
		this.seatId="";
		this.isValid="";
		this.lastModifyDate="";
		this.lastModifiedSeatID="";
		this.systemIPAddress="";
	}
	
	public void reset(ActionMapping _mapping, HttpServletRequest _request)
	{
		this.entryMode="ALL";
		
		this.patCondition="";
		this.patFirstName="";
		this.patMiddleName="";
		this.patLastName="";
		this.patDOB="";
		this.patGender="";
		this.patGenderCode="";
		this.patMaritalStatus="";
		this.patMaritalStatusCode="";
		this.patReligion="";
		this.patReligionCode="";
		this.patGuardianName="";
		this.patMomName="";
		this.isReferred="";
		this.patRefGnctdHospitalCode="";
		this.patRefHospitalName="";
		this.patRefDoctor="";
		this.isUnknown="";

		this.emergencyCode="";
		this.inDate="";
		this.inTimeHr="00";
		this.inTimeMin="00";
		this.inTime="00:00";
		this.inPatStatus="";
		this.inCondition="";
		this.outDate="";
		this.outTimeHr="";
		this.outTimeMin="";
		this.outTime="";
		this.outPatStatus="";
		this.outCondition="";
		this.remarks="";
		this.entryDate="";
		this.seatId="";
		this.isValid="";
		this.lastModifyDate="";
		this.lastModifiedSeatID="";
		this.systemIPAddress="";
	}
	

	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String getPatCondition()
	{
		return patCondition;
	}
	public void setPatCondition(String patCondition)
	{
		this.patCondition = patCondition;
	}
	public String getPatFirstName()
	{
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName()
	{
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}
	public String getPatLastName()
	{
		return patLastName;
	}
	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}
	public String getPatDOB()
	{
		return patDOB;
	}
	public void setPatDOB(String patDOB)
	{
		this.patDOB = patDOB;
	}
	public String getPatGender()
	{
		return patGender;
	}
	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}
	public String getPatGenderCode()
	{
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode)
	{
		this.patGenderCode = patGenderCode;
	}
	public String getPatMaritalStatus()
	{
		return patMaritalStatus;
	}
	public void setPatMaritalStatus(String patMaritalStatus)
	{
		this.patMaritalStatus = patMaritalStatus;
	}
	public String getPatMaritalStatusCode()
	{
		return patMaritalStatusCode;
	}
	public void setPatMaritalStatusCode(String patMaritalStatusCode)
	{
		this.patMaritalStatusCode = patMaritalStatusCode;
	}
	public String getPatReligion()
	{
		return patReligion;
	}
	public void setPatReligion(String patReligion)
	{
		this.patReligion = patReligion;
	}
	public String getPatReligionCode()
	{
		return patReligionCode;
	}
	public void setPatReligionCode(String patReligionCode)
	{
		this.patReligionCode = patReligionCode;
	}
	public String getPatGuardianName()
	{
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}
	public String getPatMomName()
	{
		return patMomName;
	}
	public void setPatMomName(String patMomName)
	{
		this.patMomName = patMomName;
	}
	public String getIsReferred()
	{
		return isReferred;
	}
	public void setIsReferred(String isReferred)
	{
		this.isReferred = isReferred;
	}
	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}
	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}
	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}
	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}
	public String getPatRefDoctor()
	{
		return patRefDoctor;
	}
	public void setPatRefDoctor(String patRefDoctor)
	{
		this.patRefDoctor = patRefDoctor;
	}
	public String getIsUnknown()
	{
		return isUnknown;
	}
	public void setIsUnknown(String isUnknown)
	{
		this.isUnknown = isUnknown;
	}
	public String getEpisodeCode()
	{
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getInDate()
	{
		return inDate;
	}
	public void setInDate(String inDate)
	{
		this.inDate = inDate;
	}
	public String getInTime()
	{
		return inTime;
	}
	public void setInTime(String inTime)
	{
		this.inTime = inTime;
	}
	public String getInPatStatus()
	{
		return inPatStatus;
	}
	public void setInPatStatus(String inPatStatus)
	{
		this.inPatStatus = inPatStatus;
	}
	public String getInCondition()
	{
		return inCondition;
	}
	public void setInCondition(String inCondition)
	{
		this.inCondition = inCondition;
	}
	public String getOutDate()
	{
		return outDate;
	}
	public void setOutDate(String outDate)
	{
		this.outDate = outDate;
	}
	public String getOutTime()
	{
		return outTime;
	}
	public void setOutTime(String outTime)
	{
		this.outTime = outTime;
	}
	public String getOutPatStatus()
	{
		return outPatStatus;
	}
	public void setOutPatStatus(String outPatStatus)
	{
		this.outPatStatus = outPatStatus;
	}
	public String getOutCondition()
	{
		return outCondition;
	}
	public void setOutCondition(String outCondition)
	{
		this.outCondition = outCondition;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public String getEntryDate()
	{
		return entryDate;
	}
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}
	public String getSeatId()
	{
		return seatId;
	}
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}
	public String getIsValid()
	{
		return isValid;
	}
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}
	public String getLastModifyDate()
	{
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}
	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}
	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}
	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	public String getInTimeHr()
	{
		return inTimeHr;
	}

	public void setInTimeHr(String inTimeHr)
	{
		this.inTimeHr = inTimeHr;
	}

	public String getInTimeMin()
	{
		return inTimeMin;
	}

	public void setInTimeMin(String inTimeMin)
	{
		this.inTimeMin = inTimeMin;
	}

	public String getOutTimeHr()
	{
		return outTimeHr;
	}

	public void setOutTimeHr(String outTimeHr)
	{
		this.outTimeHr = outTimeHr;
	}

	public String getOutTimeMin()
	{
		return outTimeMin;
	}

	public void setOutTimeMin(String outTimeMin)
	{
		this.outTimeMin = outTimeMin;
	}

	public String getEmergencyCode()
	{
		return emergencyCode;
	}

	public void setEmergencyCode(String emergencyCode)
	{
		this.emergencyCode = emergencyCode;
	}

	public String getEntryMode()
	{
		return entryMode;
	}

	public void setEntryMode(String entryMode)
	{
		this.entryMode = entryMode;
	}

	public String getIsDirectCall()
	{
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall)
	{
		this.isDirectCall = isDirectCall;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}
}