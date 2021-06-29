package hisglobal.vo;

import java.util.Comparator;

import hisglobal.hisconfig.Config;

public class CommonEnquiryVO extends ValueObject implements Comparable, Comparator
{

	private String hrgnum_puk = "";
	private String hrgstr_house_no = "";
	private String hrgstr_street_no = "";
	private String hrgstr_district = "";
	private String gnum_city_loc_code = "";
	private String hrgstr_city_location = "";
	private String hrgstr_city = "";
	private String gnum_state_code = "";
	private String hrgstr_state_name = "";
	private String gnum_country_code = "";
	private String hrgnum_pincode = "";
	private String hrgstr_contact_no = "";
	private String hrgstr_fname = "";
	private String hrgstr_mname = "";
	private String hrgstr_lname = "";
	private String gnum_gender_code = "";
	private String hrgstr_fhname = "";
	private String hrgstr_husname = "";
	private String gdt_entry_date = "";
	private String gnum_dept_code = "";
	private String gstr_dept_name;
	private String hgnum_ward_code = "";
	private String hrgnum_episode_code = "";
	private String hrgnum_visit_no = "";
	private String hrgdt_register_date = "";
	private String hrgstr_age = "";
	private String gstr_gender_name = "";
	private String gstr_countryname = "";
	private String hrgnum_is_imageuploaded;
	private String hgnum_pat_status_code;
	private String hrgdt_dob;
	private String hrgnum_is_urban;
	private String hrgdt_episode_date;
	private String sortType;
	private String orderBy;
	private String patientImage;
	private String hrgstr_spousename;
	private String gstr_tehsil ="";
	private String gnum_hospital_code;
	private String gstr_hospital_name;

	public String getGstr_tehsil() {
		return gstr_tehsil;
	}

	public void setGstr_tehsil(String gstr_tehsil) {
		this.gstr_tehsil = gstr_tehsil;
	}

	public String getHrgstr_spousename() {
		return hrgstr_spousename;
	}

	public void setHrgstr_spousename(String hrgstr_spousename) {
		this.hrgstr_spousename = hrgstr_spousename;
	}

	public String getGstr_countryname()
	{
		return gstr_countryname;
	}

	public void setGstr_countryname(String gstr_countryname)
	{
		this.gstr_countryname = gstr_countryname;
	}

	public String getGstr_gender_name()
	{
		return gstr_gender_name;
	}

	public void setGstr_gender_name(String gstr_gender_name)
	{
		this.gstr_gender_name = gstr_gender_name;
	}

	public String getHrgstr_age()
	{
		return hrgstr_age;
	}

	public void setHrgstr_age(String hrgstr_age)
	{
		this.hrgstr_age = hrgstr_age;
	}

	public String getHrgnum_episode_code()
	{
		return hrgnum_episode_code;
	}

	public void setHrgnum_episode_code(String hrgnum_episode_code)
	{
		this.hrgnum_episode_code = hrgnum_episode_code;
	}

	public String getHrgnum_visit_no()
	{
		return hrgnum_visit_no;
	}

	public void setHrgnum_visit_no(String hrgnum_visit_no)
	{
		this.hrgnum_visit_no = hrgnum_visit_no;
	}

	public String getHrgdt_register_date()
	{
		return hrgdt_register_date;
	}

	public void setHrgdt_register_date(String hrgdt_register_date)
	{
		this.hrgdt_register_date = hrgdt_register_date;
	}

	public String getHrgnum_puk()
	{
		return hrgnum_puk;
	}

	public void setHrgnum_puk(String hrgnum_puk)
	{
		this.hrgnum_puk = hrgnum_puk;
	}

	public String getHrgstr_house_no()
	{
		return hrgstr_house_no;
	}

	public void setHrgstr_house_no(String hrgstr_house_no)
	{
		this.hrgstr_house_no = hrgstr_house_no;
	}

	public String getHrgstr_street_no()
	{
		return hrgstr_street_no;
	}

	public void setHrgstr_street_no(String hrgstr_street_no)
	{
		this.hrgstr_street_no = hrgstr_street_no;
	}

	public String getHrgstr_district()
	{
		return hrgstr_district;
	}

	public void setHrgstr_district(String hrgstr_district)
	{
		this.hrgstr_district = hrgstr_district;
	}

	public String getGnum_city_loc_code()
	{
		return gnum_city_loc_code;
	}

	public void setGnum_city_loc_code(String gnum_city_loc_code)
	{
		this.gnum_city_loc_code = gnum_city_loc_code;
	}

	public String getHrgstr_city_location()
	{
		return hrgstr_city_location;
	}

	public void setHrgstr_city_location(String hrgstr_city_location)
	{
		this.hrgstr_city_location = hrgstr_city_location;
	}

	public String getHrgstr_city()
	{
		return hrgstr_city;
	}

	public void setHrgstr_city(String hrgstr_city)
	{
		this.hrgstr_city = hrgstr_city;
	}

	public String getGnum_state_code()
	{
		return gnum_state_code;
	}

	public void setGnum_state_code(String gnum_state_code)
	{
		this.gnum_state_code = gnum_state_code;
	}

	public String getHrgstr_state_name()
	{
		return hrgstr_state_name;
	}

	public void setHrgstr_state_name(String hrgstr_state_name)
	{
		this.hrgstr_state_name = hrgstr_state_name;
	}

	public String getGnum_country_code()
	{
		return gnum_country_code;
	}

	public void setGnum_country_code(String gnum_country_code)
	{
		this.gnum_country_code = gnum_country_code;
	}

	public String getHrgnum_pincode()
	{
		return hrgnum_pincode;
	}

	public void setHrgnum_pincode(String hrgnum_pincode)
	{
		this.hrgnum_pincode = hrgnum_pincode;
	}

	public String getHrgstr_contact_no()
	{
		return hrgstr_contact_no;
	}

	public void setHrgstr_contact_no(String hrgstr_contact_no)
	{
		this.hrgstr_contact_no = hrgstr_contact_no;
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

	public String getHrgstr_fhname()
	{
		return hrgstr_fhname;
	}

	public void setHrgstr_fhname(String hrgstr_fhname)
	{
		this.hrgstr_fhname = hrgstr_fhname;
	}

	public String getHrgstr_husname()
	{
		return hrgstr_husname;
	}

	public void setHrgstr_husname(String hrgstr_husname)
	{
		this.hrgstr_husname = hrgstr_husname;
	}

	public String getGdt_entry_date()
	{
		return gdt_entry_date;
	}

	public void setGdt_entry_date(String gdt_entry_date)
	{
		this.gdt_entry_date = gdt_entry_date;
	}

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

	public String getGstr_dept_name()
	{
		return gstr_dept_name;
	}

	public void setGstr_dept_name(String gstr_dept_name)
	{
		this.gstr_dept_name = gstr_dept_name;
	}

	public String getHrgnum_is_imageuploaded()
	{
		return hrgnum_is_imageuploaded;
	}

	public void setHrgnum_is_imageuploaded(String hrgnum_is_imageuploaded)
	{
		this.hrgnum_is_imageuploaded = hrgnum_is_imageuploaded;
	}

	public String getHgnum_pat_status_code()
	{
		return hgnum_pat_status_code;
	}

	public void setHgnum_pat_status_code(String hgnum_pat_status_code)
	{
		this.hgnum_pat_status_code = hgnum_pat_status_code;
	}

	public String getHrgdt_dob()
	{
		return hrgdt_dob;
	}

	public void setHrgdt_dob(String hrgdt_dob)
	{
		this.hrgdt_dob = hrgdt_dob;
	}

	public String getHrgnum_is_urban() {
		return hrgnum_is_urban;
	}

	public void setHrgnum_is_urban(String hrgnum_is_urban) {
		this.hrgnum_is_urban = hrgnum_is_urban;
	}

	public String getHrgdt_episode_date() {
		return hrgdt_episode_date;
	}

	public void setHrgdt_episode_date(String hrgdt_episode_date) {
		this.hrgdt_episode_date = hrgdt_episode_date;
	}
	
	public int compareByCrNo(CommonEnquiryVO commonEnquiryVO)
	{
		int flag = 0;
		if (Long.parseLong(this.getHrgnum_puk()) == Long.parseLong(commonEnquiryVO.getHrgnum_puk()))
		{
			flag = 0;
		}
		else
		{
			if (Long.parseLong(this.getHrgnum_puk()) < Long.parseLong(commonEnquiryVO.getHrgnum_puk())
					&& commonEnquiryVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = -1;
			}
			else
			{
				if (Long.parseLong(this.getHrgnum_puk()) < Long.parseLong(commonEnquiryVO.getHrgnum_puk())
						&& commonEnquiryVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = 1;
				}

			}

			if (Long.parseLong(this.getHrgnum_puk()) > Long.parseLong(commonEnquiryVO.getHrgnum_puk())
					&& commonEnquiryVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = 1;
			}
			else
			{
				if (Long.parseLong(this.getHrgnum_puk()) > Long.parseLong(commonEnquiryVO.getHrgnum_puk())
						&& commonEnquiryVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = -1;
				}
			}
		}

		return flag;
	}

	public int compareByName(CommonEnquiryVO commonEnquiryVO)
	{
		int flag = 0;
		String name1 = this.getHrgstr_fname()+ this.getHrgstr_mname() + this.getHrgstr_mname();
		String name2 = commonEnquiryVO.getHrgstr_fname() + commonEnquiryVO.getHrgstr_mname() + commonEnquiryVO.getHrgstr_mname();
		if (commonEnquiryVO.getSortType().equals(Config.SORT_TYPE_ASC)) flag = name1.compareTo(name2);
		else if (commonEnquiryVO.getSortType().equals(Config.SORT_TYPE_DSC))
		{
			int check = name1.compareTo(name2);
			if (check == 0)
			{
				flag = 0;
			}
			else
			{
				if (check == 1) flag = -1;
				else
				{
					flag = 1;
				}
			}

		}

		return flag;

	}

	public int compareTo(Object o)
	{
		int flag = 0;
		CommonEnquiryVO commonEnquiryVO = (CommonEnquiryVO) o;
		if (commonEnquiryVO.getOrderBy().equals(Config.OPD_PATIENT_OREDRE_BY_CR_NO)) flag = compareByCrNo(commonEnquiryVO);
		if (commonEnquiryVO.getOrderBy().equals(Config.OPD_PATIENT_OREDRE_BY_NAME)) flag = compareByName(commonEnquiryVO);
		
		
		return flag;
	}

	public int compare(Object o1, Object o2)
	{
		int flag = 0;
		CommonEnquiryVO commonEnquiryVO = (CommonEnquiryVO) o2;
		if (commonEnquiryVO.getOrderBy().equals(Config.OPD_PATIENT_OREDRE_BY_CR_NO)) flag = compareByCrNo(commonEnquiryVO);
		if (commonEnquiryVO.getOrderBy().equals(Config.OPD_PATIENT_OREDRE_BY_NAME)) flag = compareByName(commonEnquiryVO);
		
		return flag;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getPatientImage() {
		return patientImage;
	}

	public void setPatientImage(String patientImage) {
		this.patientImage = patientImage;
	}

	public String getGnum_hospital_code() {
		return gnum_hospital_code;
	}

	public void setGnum_hospital_code(String gnum_hospital_code) {
		this.gnum_hospital_code = gnum_hospital_code;
	}

	public String getGstr_hospital_name() {
		return gstr_hospital_name;
	}

	public void setGstr_hospital_name(String gstr_hospital_name) {
		this.gstr_hospital_name = gstr_hospital_name;
	}
	
}
