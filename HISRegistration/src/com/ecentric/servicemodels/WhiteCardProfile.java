package com.ecentric.servicemodels;

import org.codehaus.jackson.annotate.JsonSetter;

/**
 * 
 * @author s.singaravelan
 *
 */

public class WhiteCardProfile {
	
	
	private String uid;
	private String age;
	private String cardno;
	private String buildingName;
	private String careof;
	private String district;
	private String district_name;
	private String dob;
	private String eid;
	private String gender;
	private String mandal;
	private String mandal_name;
	private String name;
	private String phoneNo;
	private String pincode;
	private String srdhwstxn;
	private String statecode;
	private String status;
	private String street;
	
	private String village;
	private String village_name;

	
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getCareof() {
		return careof;
	}
	public void setCareof(String careof) {
		this.careof = careof;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getMandal_name() {
		return mandal_name;
	}
	public void setMandal_name(String mandal_name) {
		this.mandal_name = mandal_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getSrdhwstxn() {
		return srdhwstxn;
	}
	public void setSrdhwstxn(String srdhwstxn) {
		this.srdhwstxn = srdhwstxn;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getVillage_name() {
		return village_name;
	}
	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}
	
	
	@JsonSetter("AGE")
	public void setAgeCaps(String age) {
		this.age = age;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
	@JsonSetter("CARDNO")
	public void setCardnoCaps(String cardno) {
		this.cardno = cardno;
	}

	
}
