package hisglobal.vo;

public class ConsultantDetailVO extends ValueObject
{
	/*private String psrstr_fname = "";
	private String psrstr_mname = "";
	private String psrstr_lname = "";
	private String psrstr_add1 = "";
	private String psrstr_city = "";
	private String psrstr_district = "";*/
	
	private String psrstr_add1 = "";
	private String str_first_name;
	private String str_middle_name;
	private String str_last_name;
	private String str_cur_locality;
	private String num_cur_city_id;
	private String num_cur_dist_id;
	
	private String psrnum_pincode = "";
	private String patgenderage = "";
	private String patfathername = "";
	private String gstr_statename = "";
	private String gstr_countryname = "";
	private String gstr_city_loc_name = "";
	//private String gnum_state_code;
	private String num_cur_state_id;
	//private String gnum_country_code;
	private String num_cur_country_id;
	private String gnum_location_code = "";

	public String getPsrnum_pincode()
	{
		return psrnum_pincode;
	}

	public void setPsrnum_pincode(String psrnum_pincode)
	{
		this.psrnum_pincode = psrnum_pincode;
	}

	public String getPatgenderage()
	{
		return patgenderage;
	}

	public void setPatgenderage(String patgenderage)
	{
		this.patgenderage = patgenderage;
	}

	public String getPatfathername()
	{
		return patfathername;
	}

	public void setPatfathername(String patfathername)
	{
		this.patfathername = patfathername;
	}

	public String getGstr_statename()
	{
		return gstr_statename;
	}

	public void setGstr_statename(String gstr_statename)
	{
		this.gstr_statename = gstr_statename;
	}

	public String getGstr_countryname()
	{
		return gstr_countryname;
	}

	public void setGstr_countryname(String gstr_countryname)
	{
		this.gstr_countryname = gstr_countryname;
	}

	public String getGstr_city_loc_name()
	{
		return gstr_city_loc_name;
	}

	public void setGstr_city_loc_name(String gstr_city_loc_name)
	{
		this.gstr_city_loc_name = gstr_city_loc_name;
	}

	public String getGnum_location_code()
	{
		return gnum_location_code;
	}

	public void setGnum_location_code(String gnum_location_code)
	{
		this.gnum_location_code = gnum_location_code;
	}

	public String getStr_first_name() {
		return str_first_name;
	}

	public void setStr_first_name(String str_first_name) {
		this.str_first_name = str_first_name;
	}

	public String getStr_middle_name() {
		return str_middle_name;
	}

	public void setStr_middle_name(String str_middle_name) {
		this.str_middle_name = str_middle_name;
	}

	public String getStr_last_name() {
		return str_last_name;
	}

	public void setStr_last_name(String str_last_name) {
		this.str_last_name = str_last_name;
	}

	public String getStr_cur_locality() {
		return str_cur_locality;
	}

	public void setStr_cur_locality(String str_cur_locality) {
		this.str_cur_locality = str_cur_locality;
	}

	public String getNum_cur_city_id() {
		return num_cur_city_id;
	}

	public void setNum_cur_city_id(String num_cur_city_id) {
		this.num_cur_city_id = num_cur_city_id;
	}

	public String getNum_cur_dist_id() {
		return num_cur_dist_id;
	}

	public void setNum_cur_dist_id(String num_cur_dist_id) {
		this.num_cur_dist_id = num_cur_dist_id;
	}

	public String getNum_cur_state_id() {
		return num_cur_state_id;
	}

	public void setNum_cur_state_id(String num_cur_state_id) {
		this.num_cur_state_id = num_cur_state_id;
	}

	public String getNum_cur_country_id() {
		return num_cur_country_id;
	}

	public void setNum_cur_country_id(String num_cur_country_id) {
		this.num_cur_country_id = num_cur_country_id;
	}

	public String getPsrstr_add1() {
		return psrstr_add1;
	}

	public void setPsrstr_add1(String psrstr_add1) {
		this.psrstr_add1 = psrstr_add1;
	}

}
