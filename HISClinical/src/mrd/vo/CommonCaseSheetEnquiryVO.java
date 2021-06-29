package mrd.vo;

import hisglobal.hisconfig.Config;
import hisglobal.vo.ValueObject;

import java.util.Comparator;

public class CommonCaseSheetEnquiryVO extends ValueObject implements Comparable, Comparator
{
	
	private String hrgnum_puk="";
	private String hrgstr_fname="";
	private String hrgstr_mname="";
	private String hrgstr_lname="";
	private String hipnum_admno="";
	private String hrgnum_mlc_no="";
	private String hgnum_discharge_type_code="";
	private String hrgnum_isunknown="";
	private String hrgnum_is_broughtdead="";
	private String deathCase="";
	private String hrgnum_age="";
	private String gstr_gender_name="";
	private String hrgstr_ageunit="";
	private String hrgstr_address ="";
	private String hipdt_admdatetime="";
	private String hipdt_disdatetime="";
	private String dischargeType="";
	private String count="";
	private String recordStatus="";
	private String recordStatusLabel="";
	private String mrdRecordId="";
	private String profileId="";
	private String profileStatus="";
	private String recordType="";
	private String recordTypeLabel="";
	private String sortType;
	private String orderBy;
	private String hrgstr_city="";
	private String hrgstr_state_name="";
	private String gstr_countryname="";
	
	private String hrgstr_file_no="";
	private String hrgdt_register_date="";
	private String hrgnum_episode_code="";
	private String gender="";
	private String age="";
	private String deptUnit="";
	private String deptname="";
	private String deptunitname="";
	private String hodName="";
	private String deptcode="";
	private String patage = "";
	
	
	public String getHrgnum_puk() {
		return hrgnum_puk;
	}
	public void setHrgnum_puk(String hrgnum_puk) {
		this.hrgnum_puk = hrgnum_puk;
	}
	public String getHrgstr_fname() {
		return hrgstr_fname;
	}
	public void setHrgstr_fname(String hrgstr_fname) {
		this.hrgstr_fname = hrgstr_fname;
	}
	public String getHrgstr_mname() {
		return hrgstr_mname;
	}
	public void setHrgstr_mname(String hrgstr_mname) {
		this.hrgstr_mname = hrgstr_mname;
	}
	public String getHrgstr_lname() {
		return hrgstr_lname;
	}
	public void setHrgstr_lname(String hrgstr_lname) {
		this.hrgstr_lname = hrgstr_lname;
	}
	
	public String getHrgnum_mlc_no() {
		return hrgnum_mlc_no;
	}
	public void setHrgnum_mlc_no(String hrgnum_mlc_no) {
		this.hrgnum_mlc_no = hrgnum_mlc_no;
	}
	public String getHgnum_discharge_type_code() {
		return hgnum_discharge_type_code;
	}
	public void setHgnum_discharge_type_code(String hgnum_discharge_type_code) {
		this.hgnum_discharge_type_code = hgnum_discharge_type_code;
	}
	public String getHrgnum_isunknown() {
		return hrgnum_isunknown;
	}
	public void setHrgnum_isunknown(String hrgnum_isunknown) {
		this.hrgnum_isunknown = hrgnum_isunknown;
	}
	public String getHrgnum_is_broughtdead() {
		return hrgnum_is_broughtdead;
	}
	public void setHrgnum_is_broughtdead(String hrgnum_is_broughtdead) {
		this.hrgnum_is_broughtdead = hrgnum_is_broughtdead;
	}
	public String getDeathCase() {
		return deathCase;
	}
	public void setDeathCase(String deathCase) {
		this.deathCase = deathCase;
	}
	
	public String getGstr_gender_name() {
		return gstr_gender_name;
	}
	public void setGstr_gender_name(String gstr_gender_name) {
		this.gstr_gender_name = gstr_gender_name;
	}
	public String getHipnum_admno() {
		return hipnum_admno;
	}
	public void setHipnum_admno(String hipnum_admno) {
		this.hipnum_admno = hipnum_admno;
	}
	public String getHrgstr_ageunit() {
		return hrgstr_ageunit;
	}
	public void setHrgstr_ageunit(String hrgstr_ageunit) {
		this.hrgstr_ageunit = hrgstr_ageunit;
	}
	public String getHrgnum_age() {
		return hrgnum_age;
	}
	public void setHrgnum_age(String hrgnum_age) {
		this.hrgnum_age = hrgnum_age;
	}
	public String getHrgstr_address() {
		return hrgstr_address;
	}
	public void setHrgstr_address(String hrgstr_address) {
		this.hrgstr_address = hrgstr_address;
	}
	public String getHipdt_admdatetime() {
		return hipdt_admdatetime;
	}
	public void setHipdt_admdatetime(String hipdt_admdatetime) {
		this.hipdt_admdatetime = hipdt_admdatetime;
	}
	public String getHipdt_disdatetime() {
		return hipdt_disdatetime;
	}
	public void setHipdt_disdatetime(String hipdt_disdatetime) {
		this.hipdt_disdatetime = hipdt_disdatetime;
	}
	public String getDischargeType() {
		return dischargeType;
	}
	public void setDischargeType(String dischargeType) {
		this.dischargeType = dischargeType;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getRecordStatusLabel() {
		return recordStatusLabel;
	}
	public void setRecordStatusLabel(String recordStatusLabel) {
		this.recordStatusLabel = recordStatusLabel;
	}
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getRecordTypeLabel() {
		return recordTypeLabel;
	}
	public void setRecordTypeLabel(String recordTypeLabel) {
		this.recordTypeLabel = recordTypeLabel;
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

	public int compareByCrNo(CommonCaseSheetEnquiryVO commonEnquiryVO)
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

	public int compareByName(CommonCaseSheetEnquiryVO commonEnquiryVO)
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
		CommonCaseSheetEnquiryVO commonEnquiryVO = (CommonCaseSheetEnquiryVO) o;
		if (commonEnquiryVO.getOrderBy().equals(Config.OPD_PATIENT_OREDRE_BY_CR_NO)) flag = compareByCrNo(commonEnquiryVO);
		if (commonEnquiryVO.getOrderBy().equals(Config.OPD_PATIENT_OREDRE_BY_NAME)) flag = compareByName(commonEnquiryVO);
		
		
		return flag;
	}

	public int compare(Object o1, Object o2)
	{
		int flag = 0;
		CommonCaseSheetEnquiryVO commonEnquiryVO = (CommonCaseSheetEnquiryVO) o2;
		if (commonEnquiryVO.getOrderBy().equals(Config.OPD_PATIENT_OREDRE_BY_CR_NO)) flag = compareByCrNo(commonEnquiryVO);
		if (commonEnquiryVO.getOrderBy().equals(Config.OPD_PATIENT_OREDRE_BY_NAME)) flag = compareByName(commonEnquiryVO);
		
		return flag;
	}
	public String getHrgstr_city() {
		return hrgstr_city;
	}
	public void setHrgstr_city(String hrgstr_city) {
		this.hrgstr_city = hrgstr_city;
	}
	public String getHrgstr_state_name() {
		return hrgstr_state_name;
	}
	public void setHrgstr_state_name(String hrgstr_state_name) {
		this.hrgstr_state_name = hrgstr_state_name;
	}
	public String getGstr_countryname() {
		return gstr_countryname;
	}
	public void setGstr_countryname(String gstr_countryname) {
		this.gstr_countryname = gstr_countryname;
	}
	public String getHrgstr_file_no() {
		return hrgstr_file_no;
	}
	public void setHrgstr_file_no(String hrgstr_file_no) {
		this.hrgstr_file_no = hrgstr_file_no;
	}
	public String getHrgdt_register_date() {
		return hrgdt_register_date;
	}
	public void setHrgdt_register_date(String hrgdt_register_date) {
		this.hrgdt_register_date = hrgdt_register_date;
	}
	public String getHrgnum_episode_code() {
		return hrgnum_episode_code;
	}
	public void setHrgnum_episode_code(String hrgnum_episode_code) {
		this.hrgnum_episode_code = hrgnum_episode_code;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDeptUnit() {
		return deptUnit;
	}
	public void setDeptUnit(String deptUnit) {
		this.deptUnit = deptUnit;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptunitname() {
		return deptunitname;
	}
	public void setDeptunitname(String deptunitname) {
		this.deptunitname = deptunitname;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getPatage() {
		return patage;
	}
	public void setPatage(String patage) {
		this.patage = patage;
	}
}
