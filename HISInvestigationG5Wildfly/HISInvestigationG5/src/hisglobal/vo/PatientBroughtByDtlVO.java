package hisglobal.vo;

public class PatientBroughtByDtlVO extends ValueObject
{
	private String patCrNo;
	private String serialNo;
	private String episodeCode;
	private String broughtByName;
	private String broughtByAddress;
	private String broughtByPhone;
	private String isRelative;
	private String broughtByGenderCode;
	private String broughtByRelationCode;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String systemIPAddress;
	private String broughtByLocation;
	private String isAddedEarlier;
	private String isBroughtBy;
	private String episodeVisitNo;
	private String constableDesig;
	private String constableBadgeNo;
	private String	patVehicleNo ="";


	public String getPatVehicleNo() {
		return patVehicleNo;
	}

	public void setPatVehicleNo(String patVehicleNo) {
		this.patVehicleNo = patVehicleNo;
	}

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getBroughtByLocation()
	{
		return broughtByLocation;
	}

	public void setBroughtByLocation(String broughtByLocation)
	{
		this.broughtByLocation = broughtByLocation;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getBroughtByName()
	{
		return broughtByName;
	}

	public void setBroughtByName(String broughtByName)
	{
		this.broughtByName = broughtByName;
	}

	public String getBroughtByAddress()
	{
		return broughtByAddress;
	}

	public void setBroughtByAddress(String broughtByAddress)
	{
		this.broughtByAddress = broughtByAddress;
	}

	public String getBroughtByPhone()
	{
		return broughtByPhone;
	}

	public void setBroughtByPhone(String broughtByPhone)
	{
		this.broughtByPhone = broughtByPhone;
	}

	public String getIsRelative()
	{
		return isRelative;
	}

	public void setIsRelative(String isRelative)
	{
		this.isRelative = isRelative;
	}

	public String getBroughtByGenderCode()
	{
		return broughtByGenderCode;
	}

	public void setBroughtByGenderCode(String broughtByGenderCode)
	{
		this.broughtByGenderCode = broughtByGenderCode;
	}

	public String getBroughtByRelationCode()
	{
		return broughtByRelationCode;
	}

	public void setBroughtByRelationCode(String broughtByRelationCode)
	{
		this.broughtByRelationCode = broughtByRelationCode;
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

	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	public String getIsAddedEarlier() {
		return isAddedEarlier;
	}

	public void setIsAddedEarlier(String isAddedEarlier) {
		this.isAddedEarlier = isAddedEarlier;
	}

	public String getIsBroughtBy() {
		return isBroughtBy;
	}

	public void setIsBroughtBy(String isBroughtBy) {
		this.isBroughtBy = isBroughtBy;
	}

	public String getConstableDesig() {
		return constableDesig;
	}

	public void setConstableDesig(String constableDesig) {
		this.constableDesig = constableDesig;
	}

	public String getConstableBadgeNo() {
		return constableBadgeNo;
	}

	public void setConstableBadgeNo(String constableBadgeNo) {
		this.constableBadgeNo = constableBadgeNo;
	}
}
