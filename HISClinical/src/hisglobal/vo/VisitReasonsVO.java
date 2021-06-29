package hisglobal.vo;

public class VisitReasonsVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String serialNo;
	private String patAdmNo;
	private String episodeVisitNo;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String episodeDate;
	private String departmentUnitCode;
	private String visitReasons;

	private String episodeIsOpen;

	private String hospitalCode;
	private String hospitalName;
	
	public String getEpisodeIsOpen() {
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen) {
		this.episodeIsOpen = episodeIsOpen;
	}

	

	public String getEpisodeDate()
	{
		return episodeDate;
	}


	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
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

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}


	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}



	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getVisitReasons() {
		return visitReasons;
	}

	public void setVisitReasons(String visitReasons) {
		this.visitReasons = visitReasons;
	}

}
