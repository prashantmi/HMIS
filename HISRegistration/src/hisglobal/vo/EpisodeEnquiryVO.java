package hisglobal.vo;

public class EpisodeEnquiryVO extends ValueObject
{
	private String gnum_dept_code;
	private String hgnum_ward_code;
	private String unit;
	private String roomNo;
	private String lastVisitDate;

	public String getGnum_dept_code()
	{
		return gnum_dept_code;
	}

	public void setGnum_dept_code(String gnum_dept_code)
	{
		this.gnum_dept_code = gnum_dept_code;
	}

	public String getHgnum_ward_code()
	{
		return hgnum_ward_code;
	}

	public void setHgnum_ward_code(String hgnum_ward_code)
	{
		this.hgnum_ward_code = hgnum_ward_code;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
}
