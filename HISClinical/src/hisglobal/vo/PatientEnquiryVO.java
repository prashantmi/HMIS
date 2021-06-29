package hisglobal.vo;

public class PatientEnquiryVO extends ValueObject
{

	private String hrgnum_puk;
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	private String gnum_gender_code;
	private String hrgstr_father_name;
	private String hrgstr_spousename;
	private String hrgnum_isunknown;
	private String hrgnum_is_broughtdead;
	private String hrgnum_is_mlc;
	private String fromDate;
	private String toDate;
	private String hrgstr_age;
	private String isCurrentHospitalSearch;

	private String gdt_entry_date;

	public String getHrgnum_puk()
	{
		return hrgnum_puk;
	}

	public void setHrgnum_puk(String hrgnum_puk)
	{
		this.hrgnum_puk = hrgnum_puk;
	}

	public String getHrgstr_fname()
	{
		return hrgstr_fname;
	}

	public void setHrgstr_fname(String hrgstr_fname)
	{
		this.hrgstr_fname = hrgstr_fname;
	}

	public String getHrgstr_mname()
	{
		return hrgstr_mname;
	}

	public void setHrgstr_mname(String hrgstr_mname)
	{
		this.hrgstr_mname = hrgstr_mname;
	}

	public String getHrgstr_lname()
	{
		return hrgstr_lname;
	}

	public void setHrgstr_lname(String hrgstr_lname)
	{
		this.hrgstr_lname = hrgstr_lname;
	}

	public String getGnum_gender_code()
	{
		return gnum_gender_code;
	}

	public void setGnum_gender_code(String gnum_gender_code)
	{
		this.gnum_gender_code = gnum_gender_code;
	}

	

	public String getGdt_entry_date()
	{
		return gdt_entry_date;
	}

	public void setGdt_entry_date(String gdt_entry_date)
	{
		this.gdt_entry_date = gdt_entry_date;
	}

	public String getHrgnum_isunknown()
	{
		return hrgnum_isunknown;
	}

	public void setHrgnum_isunknown(String hrgnum_isunknown)
	{
		this.hrgnum_isunknown = hrgnum_isunknown;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public String getHrgnum_is_broughtdead() {
		return hrgnum_is_broughtdead;
	}

	public void setHrgnum_is_broughtdead(String hrgnum_is_broughtdead) {
		this.hrgnum_is_broughtdead = hrgnum_is_broughtdead;
	}

	public String getHrgnum_is_mlc() {
		return hrgnum_is_mlc;
	}

	public void setHrgnum_is_mlc(String hrgnum_is_mlc) {
		this.hrgnum_is_mlc = hrgnum_is_mlc;
	}

	public String getHrgstr_father_name() {
		return hrgstr_father_name;
	}

	public void setHrgstr_father_name(String hrgstr_father_name) {
		this.hrgstr_father_name = hrgstr_father_name;
	}

	public String getHrgstr_spousename() {
		return hrgstr_spousename;
	}

	public void setHrgstr_spousename(String hrgstr_spousename) {
		this.hrgstr_spousename = hrgstr_spousename;
	}

	public String getHrgstr_age() {
		return hrgstr_age;
	}

	public void setHrgstr_age(String hrgstr_age) {
		this.hrgstr_age = hrgstr_age;
	}

	public String getIsCurrentHospitalSearch() {
		return isCurrentHospitalSearch;
	}

	public void setIsCurrentHospitalSearch(String isCurrentHospitalSearch) {
		this.isCurrentHospitalSearch = isCurrentHospitalSearch;
	}

}
