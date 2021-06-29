package hisglobal.vo;

public class MrdCheckListVO extends ValueObject
{
	private String checkListId;
	private String slNo;
	private String checkList;
	private String isValid;
	private String seatId;
	private String lastModifyDate;
	private String lastModifySeatId;
	private String hospitalCode;
	private String entryDate;
	
	
	public String getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(String checkListId) {
		this.checkListId = checkListId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getCheckList() {
		return checkList;
	}
	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifySeatId() {
		return lastModifySeatId;
	}
	public void setLastModifySeatId(String lastModifySeatId) {
		this.lastModifySeatId = lastModifySeatId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	
}
