package in.cdacnoida.idgenerator;

import java.util.Random;

import org.apache.commons.codec.language.Soundex;
public class GenerateID {



	private Soundex soundex;

	private HealthId healthId;

	private String secondaryHealthID;

	// Constants
	private int yearDifference=1000;
	private int maxStateCodeLength=2;
	private int maxDistrictCodeLength=3;
	private int maxYearCodeLength=3;
	private int maxMobileNolength=10;
	private String defaultSoundexCode="Z000";
	private String defaultEncodedYear="000";
	private String defaultStateCode="00";
	private String defaultDistrictCode="000";
	private String defaultMobileNo="0000000000";

	public GenerateID() {
		this.soundex = new Soundex();
	}

	
	public GenerateID(HealthId healthId) {
		super();
		this.healthId=healthId;
		this.soundex = new Soundex();
	}

	
	
	/**
	 * Function to generate Secondary Health ID
	 */
	public String generateSecondaryHealthID(){
		
		this.secondaryHealthID = encodeState(this.healthId.getStateCode()) +
				encodeDistrict(this.healthId.getDistrictCode()) +
				Integer.toString(mapGenderToInt(this.healthId.getGender()))+
				encodeYear(this.healthId.getYearOfBirth())+
				getSoundexCode(this.healthId.getFirstName().toLowerCase())+
//				getSoundexCode(this.healthId.getLastName())+" "+
				getSoundexCode(getLongestWord(this.healthId.getFathersName()))+
				getSoundexCode(getLongestWord(this.healthId.getMothersName()))+
				encodeMobileNo(this.healthId.getMobileNo());
//				randomNumberToString();
				
		return this.secondaryHealthID;
	}

	/**
	 * Method to encode year of birth
	 * This method takes the year of birth and encodes the year of birth to a 3 digit number that is represented by yearDifference(1000) - yearOfBirth
	 * @param yearOfBirth : Input year of birth
	 * @return yearDifference-yearOfBirth
	 */
	private String encodeYear(int yearOfBirth){
		int yearEncoded=yearDifference - (yearOfBirth%1000);
		if (yearEncoded > 0){
			String yearEncodedString=Integer.toString(yearEncoded);
			if (yearEncodedString.length()<maxYearCodeLength){
				return String.format("%0"+maxYearCodeLength+"d", yearEncoded);
			}
			else
				return yearEncodedString;
		}
		else
			return defaultEncodedYear;
	}


	/**
	 * This method returns the soundex code of an input string
	 * @param inputString The string whose soundex code is required
	 * @return Soundex code as string
	 */
	private String getSoundexCode(String inputString){
//		soundex=new Soundex();
		String soundexCode = soundex.encode(inputString);
		if (soundexCode==null){
			return defaultSoundexCode;
		}
		else{
			return soundexCode;
		}
	}


	/**
	 * This method computes the longest substring in the input string
	 * @param inputString The input string
	 * @return Longest substring in the given string
	 */
	private String getLongestWord(String inputString){
		//		Split string based on separators
		String[] tokens = inputString.split("[,. ]+");
		//		Find longest substring
		int maxLength = 0;
		String longestString = null;
		for (String s : tokens)
		{
			if (s.length() > maxLength)
			{
				maxLength = s.length();
				longestString = s;
			}
		}
		return longestString;

	}

	/**
	 * This function maps the gender to a numeric value, 0 for male, 1 for female, 2 for others
	 * @param gender Input gender string
	 * @return numeric gender encoding
	 */
	private int mapGenderToInt(String gender){
		if (gender.equalsIgnoreCase("Male")||gender.equalsIgnoreCase("M"))
			return 0;
		else if (gender.equalsIgnoreCase("Female")||gender.equalsIgnoreCase("F"))
			return 1;
		else
			return 2;
	}

	/**
	 * This function encodes a state code
	 * @param stateCode
	 * @return
	 */
	private String encodeState(int stateCode){
		if (stateCode==-1)
			return defaultStateCode;
		else {
			String stateEncoded=Integer.toString(stateCode);
			if (stateEncoded.length()<maxStateCodeLength){
				return String.format("%0"+maxStateCodeLength+"d", stateCode);
			}
			return stateEncoded;
		}
	}

	/**
	 * This function encodes the district code
	 * @param districtCode
	 * @return
	 */
	private String encodeDistrict(int districtCode){
		if (districtCode==-1)
			return defaultDistrictCode;
		else{
			String districtEncoded=Integer.toString(districtCode);
			if (districtEncoded.length()<maxDistrictCodeLength){
				return String.format("%0"+maxDistrictCodeLength+"d", districtCode);
			}
			return districtEncoded;
		}
	}
	/**
	 * This function encodes the mobile number
	 * @param mobileNo
	 * @return
	 */
	private String encodeMobileNo(String mobileNo){
		if (mobileNo!=null && !mobileNo.equals(""))
		{
			String mobileNoEncoded = mobileNo;
			return mobileNoEncoded;
		}
		else
		{
			return defaultMobileNo;
		}

	}
	/**
	 * Method to generate 3 digit serial number
	 */
	private String randomNumberToString(){
		Random r = new Random();
		int Low = 0;
		int High = 1000;
		int randnum = r.nextInt(High-Low) + Low;
		
//		Checking random number length
		if (randnum/10==0)
			return String.format("%0"+"3d", randnum);
		else if (randnum/100==0)
			return String.format("%0"+"3d", randnum);
		else
			return Integer.toString(randnum);
	}
	
	/*
	 * Getters and Setters
	 */

	public String getSecondaryHealthID(){
		return this.secondaryHealthID;
	}

}
