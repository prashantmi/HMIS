package com.ecentric.servicemodels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import registration.dao.RegEssentialDAO;

/**
 * 
 * @author s.singaravelan
 *
 */

public class AadhaarIntoRegistration {
	
	
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGuardianName;

	private String patGender;
	private String patGenderCode;
	private String patAge;
	private String patDOB;
	private String patAgeUnit;    

	private String patAddHNo;
	private String patAddStreet;
	private String patAddCity;
	private String patAddCityLoc;
	private String patAddDistrict;
	private String patAddDistrictCode;
	private String patAddState;
	private String patAddStateCode;
	private String patAddPIN;
	
	private String patAddMobileNo;
	private String patNationalId;
	private String status;

	
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName.trim();
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName.trim();
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName.trim();
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName.trim();
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPatAddHNo() {
		return patAddHNo;
	}
	public void setPatAddHNo(String patAddHNo) {
		this.patAddHNo = patAddHNo;
	}
	public String getPatAddStreet() {
		return patAddStreet;
	}
	public void setPatAddStreet(String patAddStreet) {
		this.patAddStreet = patAddStreet;
	}
	public String getPatAddCity() {
		return patAddCity;
	}
	public void setPatAddCity(String patAddCity) {
		this.patAddCity = patAddCity;
	}
	public String getPatAddDistrict() {
		return patAddDistrict;
	}
	public void setPatAddDistrict(String patAddDistrict) {
		this.patAddDistrict = patAddDistrict;
	}
	public String getPatAddDistrictCode() {
		return patAddDistrictCode;
	}
	public void setPatAddDistrictCode(String patAddDistrictCode) {
		this.patAddDistrictCode = patAddDistrictCode;
	}
	public String getPatAddState() {
		return patAddState;
	}
	public void setPatAddState(String patAddState) {
		this.patAddState = patAddState;
	}
	public String getPatAddStateCode() {
		return patAddStateCode;
	}
	public void setPatAddStateCode(String patAddStateCode) {
		this.patAddStateCode = (patAddStateCode.equals("1")?"37":(patAddStateCode.equals("2")?"33":patAddStateCode));
	}
	public String getPatAddPIN() {
		return patAddPIN;
	}
	public void setPatAddPIN(String patAddPIN) {
		this.patAddPIN = patAddPIN;
	}

	public void setPatAgeFromDOB(String dateOfBirth) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("dd-MMM-yyyy");

		Date _dobDate=sdf.parse(dateOfBirth);

		Calendar _dob = Calendar.getInstance();
		_dob.setTime(_dobDate);
		
		Calendar today = Calendar.getInstance();  

		int dobMonth=_dob.get(Calendar.MONTH)+1;
		int todayMonth=today.get(Calendar.MONTH)+1;

		System.out.println("Toady :: " +today.get(Calendar.DAY_OF_MONTH)+"-"+todayMonth+"-"+today.get(Calendar.YEAR));
		System.out.println("DOB Date :: " +_dob.get(Calendar.DAY_OF_MONTH)+"-"+dobMonth+"-"+_dob.get(Calendar.YEAR));
		
		
		String ageunit="Yr";
		int age = today.get(Calendar.YEAR) - _dob.get(Calendar.YEAR);  
		if (todayMonth < dobMonth) {
		  age--;  
		} else if (todayMonth == dobMonth
		    && today.get(Calendar.DAY_OF_MONTH) < _dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		
		if(age<=0){
			if(todayMonth > dobMonth){	
				age=todayMonth-dobMonth;
				ageunit="Mth";
			}
			else if (todayMonth == dobMonth
				    && today.get(Calendar.DAY_OF_MONTH) > _dob.get(Calendar.DAY_OF_MONTH)) {
				age=today.get(Calendar.DAY_OF_MONTH)-_dob.get(Calendar.DAY_OF_MONTH);
				ageunit="D";	
			}
		}
		
		
		this.patAge=String.valueOf(age);
		this.patAgeUnit=String.valueOf(ageunit);
		this.patDOB=sdfMonth.format(_dobDate);


	}
	
	public void setName(String name) {
		if(name.trim().indexOf(" ")>0){
			String names[]=name.trim().replace(" ", "#").split("#");
			String lastName="";
			this.patFirstName= names[0];
			this.patMiddleName= names[1];
			for(int i=2;i<names.length;i++)
				lastName+=names[i]+" ";
			this.patLastName=lastName.trim();
		}
		else{
			this.patFirstName = name.trim();
			this.patMiddleName="";
			this.patLastName="";
		}
	}
	
	
	public String getPatAddMobileNo() {
		return patAddMobileNo;
	}
	public void setPatAddMobileNo(String patAddMobileNo) {
		this.patAddMobileNo = patAddMobileNo.equals("101")?"":patAddMobileNo;
	}
	public String getPatNationalId() {
		return patNationalId;
	}
	public void setPatNationalId(String patNationalId) {
		this.patNationalId = patNationalId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPatAddCityLoc() {
		return patAddCityLoc;
	}
	public void setPatAddCityLoc(String patAddCityLoc) {
		this.patAddCityLoc = patAddCityLoc;
	}
public String getDistrictFromMapper(String patAddDistrictCode) {
		
		if(patAddDistrictCode.equals("1"))
			return "542";
		if(patAddDistrictCode.equals("2"))
			return "543";
		if(patAddDistrictCode.equals("3"))
			return "544";
		if(patAddDistrictCode.equals("4"))
			return "545";
		if(patAddDistrictCode.equals("5"))
			return "546";
		if(patAddDistrictCode.equals("6"))
			return "547";
		if(patAddDistrictCode.equals("7"))
			return "548";
		if(patAddDistrictCode.equals("8"))
			return "549";
		if(patAddDistrictCode.equals("9"))
			return "550";
		if(patAddDistrictCode.equals("10"))
			return "554";
		if(patAddDistrictCode.equals("11"))
			return "551";
		if(patAddDistrictCode.equals("12"))
			return "553";
		if(patAddDistrictCode.equals("13"))
			return "552";
		if(patAddDistrictCode.equals("14"))
			return "538";
		if(patAddDistrictCode.equals("15"))
			return "537";
		if(patAddDistrictCode.equals("16"))
			return "536";
		if(patAddDistrictCode.equals("17"))
			return "535";
		if(patAddDistrictCode.equals("18"))
			return "533";
		if(patAddDistrictCode.equals("19"))
			return "532";
		if(patAddDistrictCode.equals("20"))
			return "534";
		if(patAddDistrictCode.equals("21"))
			return "540";
		if(patAddDistrictCode.equals("22"))
			return "541";
		if(patAddDistrictCode.equals("23"))
			return "539";
		else
			return patAddDistrictCode;
		
		
		
	}


}
