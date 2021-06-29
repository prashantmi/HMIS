package hisglobal.vo;

public class DepartmentLocationEnquiryVO extends ValueObject
{
	private String hgstr_description;
	private String hgstr_building;
	private String hgstr_block;
	private String hgstr_floor;
	private String hgstr_room;
	private String hgstr_landmark;
	private String hgnum_location_code;
	private String gnum_dept_code;

	public String getHgstr_description()
	{
		return hgstr_description;
	}

	public void setHgstr_description(String hgstr_description)
	{
		this.hgstr_description = hgstr_description;
	}

	public String getHgstr_floor()
	{
		return hgstr_floor;
	}

	public void setHgstr_floor(String hgstr_floor)
	{
		this.hgstr_floor = hgstr_floor;
	}

	public String getHgstr_building()
	{
		return hgstr_building;
	}

	public void setHgstr_building(String hgstr_building)
	{
		this.hgstr_building = hgstr_building;
	}

	public String getHgstr_landmark()
	{
		return hgstr_landmark;
	}

	public void setHgstr_landmark(String hgstr_landmark)
	{
		this.hgstr_landmark = hgstr_landmark;
	}

	public String getHgnum_location_code()
	{
		return hgnum_location_code;
	}

	public void setHgnum_location_code(String hgnum_location_code)
	{
		this.hgnum_location_code = hgnum_location_code;
	}

	public String getGnum_dept_code()
	{
		return gnum_dept_code;
	}

	public void setGnum_dept_code(String gnum_dept_code)
	{
		this.gnum_dept_code = gnum_dept_code;
	}

	public String getHgstr_block() {
		return hgstr_block;
	}

	public void setHgstr_block(String hgstr_block) {
		this.hgstr_block = hgstr_block;
	}

	public String getHgstr_room() {
		return hgstr_room;
	}

	public void setHgstr_room(String hgstr_room) {
		this.hgstr_room = hgstr_room;
	}

	
}
