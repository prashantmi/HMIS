package hisglobal.vo;

import java.util.Comparator;

import hisglobal.hisconfig.Config;
import enquiry.enquiryConfig;

public class PatientBulletinDetailVO extends ValueObject  implements Comparable, Comparator{
	
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String bulletinSerialNo;
	private String patStatusCode;
	private String patStatusName;
	private String patAdmNo;
	private String patRemarks;	
	private String effectiveFromDate;	
	private String patEmpNo;	
	private String enterByName;	
	private String isValid;
	private String effectiveToDate;
	private String seatID;
	private String entryDate;
	private String hospitalCode;
	private String patIpAddr;
	private String lstModifiedDate;
	
	private String patName;
	private String patAge;
	private String departmentName;
	private String departmentUnitName;
	private String wardType;
	private String sortType;
	private String orderBy;
	
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
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	
	public String getBulletinSerialNo() {
		return bulletinSerialNo;
	}
	public void setBulletinSerialNo(String bulletinSerialNo) {
		this.bulletinSerialNo = bulletinSerialNo;
	}
	public String getPatStatusCode() {
		return patStatusCode;
	}
	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getPatRemarks() {
		return patRemarks;
	}
	public void setPatRemarks(String patRemarks) {
		this.patRemarks = patRemarks;
	}
	public String getEffectiveFromDate() {
		return effectiveFromDate;
	}
	public void setEffectiveFromDate(String effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}
	public String getPatEmpNo() {
		return patEmpNo;
	}
	public void setPatEmpNo(String patEmpNo) {
		this.patEmpNo = patEmpNo;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getEffectiveToDate() {
		return effectiveToDate;
	}
	public void setEffectiveToDate(String effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getPatIpAddr() {
		return patIpAddr;
	}
	public void setPatIpAddr(String patIpAddr) {
		this.patIpAddr = patIpAddr;
	}
	public String getLstModifiedDate() {
		return lstModifiedDate;
	}
	public void setLstModifiedDate(String lstModifiedDate) {
		this.lstModifiedDate = lstModifiedDate;
	}
	public String getPatStatusName() {
		return patStatusName;
	}
	public void setPatStatusName(String patStatusName) {
		this.patStatusName = patStatusName;
	}
	public String getEnterByName() {
		return enterByName;
	}
	public void setEnterByName(String enterByName) {
		this.enterByName = enterByName;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentUnitName() {
		return departmentUnitName;
	}
	public void setDepartmentUnitName(String departmentUnitName) {
		this.departmentUnitName = departmentUnitName;
	}
	public String getWardType() {
		return wardType;
	}
	public void setWardType(String wardType) {
		this.wardType = wardType;
	}
	  
	public int compareByName(PatientBulletinDetailVO patientBulletinVO)
	{
		int flag = 0;
		String name1 = this.getPatName();
		String name2 = patientBulletinVO.getPatName();
		if (patientBulletinVO.getSortType().equals(Config.SORT_TYPE_ASC)) flag = name1.compareTo(name2);
		else if (patientBulletinVO.getSortType().equals(Config.SORT_TYPE_DSC))
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
	
	public int compareByWardType(PatientBulletinDetailVO patientBulletinVO)
	{
		int flag = 0;
		String wardType1 = this.getWardType();
		String wardType2 = patientBulletinVO.getWardType();
		if (patientBulletinVO.getSortType().equals(Config.SORT_TYPE_ASC)) flag = wardType1.compareTo(wardType2);
		else if (patientBulletinVO.getSortType().equals(Config.SORT_TYPE_DSC))
		{
			int check = wardType1.compareTo(wardType2);
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
		PatientBulletinDetailVO patientBulletinVO = (PatientBulletinDetailVO) o;
		if (patientBulletinVO.getOrderBy().equals(enquiryConfig.ORDER_BY_WARD_TYPE)) flag = compareByWardType(patientBulletinVO);
		if (patientBulletinVO.getOrderBy().equals(enquiryConfig.ORDER_BY_NAME)) flag = compareByName(patientBulletinVO);
		
		
		return flag;
	}

	public int compare(Object o1, Object o2)
	{
		int flag = 0;
		PatientBulletinDetailVO patientBulletinVO = (PatientBulletinDetailVO) o2;
		if (patientBulletinVO.getOrderBy().equals(enquiryConfig.ORDER_BY_WARD_TYPE)) flag = compareByWardType(patientBulletinVO);
		if (patientBulletinVO.getOrderBy().equals(enquiryConfig.ORDER_BY_NAME)) flag = compareByName(patientBulletinVO);
		
		return flag;
	}

}
