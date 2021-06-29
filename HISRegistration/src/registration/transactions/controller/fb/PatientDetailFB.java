package registration.transactions.controller.fb;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;


public class PatientDetailFB  extends CRNoFB{
	

		private String crNoToRetrieve;
		private String patCat;
	    private String patPrimaryCatCode;		
	    private String patSecondaryCatCode;
		private String hmode;
		private String patFirstName;
	    private String patMiddleName;
	    private String patLastName;
	    private String patGender;
		private String patAge;
	    private String patGuardianName;
	    private String patAgeUnit;		
		private String valid;		
		private String patRegCatCode;		
	    private String patPrimaryCat;		
	    private String patSecondaryCat;		
	    private String patGenderCode;
	    private String patRegCat;
	    private String registerDate;
	    private String mlcNo;
	    private String patHusbandName;
	    private String patStatusCode;
	    
		public String getPatStatusCode() {
			return patStatusCode;
		}

		public void setPatStatusCode(String patStatusCode) {
			this.patStatusCode = patStatusCode;
		}

		public String getPatHusbandName() {
			return patHusbandName;
		}

		public void setPatHusbandName(String patHusbandName) {
			this.patHusbandName = patHusbandName;
		}

		public String getMlcNo() {
			return mlcNo;
		}

		public void setMlcNo(String mlcNo) {
			this.mlcNo = mlcNo;
		}

		public String getPatRegCat() {
			return patRegCat;
		}
		
		public void setPatRegCat(String patRegCat) {
			this.patRegCat = patRegCat;
		}
		
		public String getRegisterDate() {
			return registerDate;
		}

		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}

		public void reset(ActionMapping mapping,HttpServletRequest request){
			
			this.setPatGenderCode("");
			this.setPatCrNo("            ");
			this.setPatPrimaryCatCode("");
			this.setPatSecondaryCatCode("");
			this.setPatFirstName("");
			this.setPatMiddleName("");
			this.setPatLastName("");
			this.setPatAge("");
			this.setPatGender("");
			this.setPatGuardianName("");
			this.setPatHusbandName("");
		}
		
		public String getPatFirstName() {
			return patFirstName;
		}

		public void setPatFirstName(String patFirstName) {
			this.patFirstName = patFirstName;
			
		}

		public String getPatGenderCode() {
			return patGenderCode;
		}

		public void setPatGenderCode(String patGenderCode) {
			this.patGenderCode = patGenderCode;
			
		}

		public String getPatGuardianName() {
			return patGuardianName;
		}

		public void setPatGuardianName(String patGuardianName) {
			this.patGuardianName = patGuardianName;
		}
		
		public String getPatLastName() {
			return patLastName;
		}

		public void setPatLastName(String patLastName) {
			this.patLastName = patLastName;
		}	

		public String getPatMiddleName() {
			return patMiddleName;
		}

		public void setPatMiddleName(String patMiddleName) {
			this.patMiddleName = patMiddleName;
		}		

		public String getPatPrimaryCatCode() {
			return patPrimaryCatCode;
		}

		public void setPatPrimaryCatCode(String patPrimaryCatCode) {
			this.patPrimaryCatCode = patPrimaryCatCode;
		}	

		public String getPatRegCatCode() {
			return patRegCatCode;
		}

		public void setPatRegCatCode(String patRegCatCode) {
			this.patRegCatCode = patRegCatCode;
		}	

		public String getPatSecondaryCatCode() {
			return patSecondaryCatCode;
		}

		public void setPatSecondaryCatCode(String patSecondaryCatCode) {
			this.patSecondaryCatCode = patSecondaryCatCode;
		}

		public String getPatAgeUnit() {
			return patAgeUnit;
		}

		public void setPatAgeUnit(String patAgeUnit) {
			this.patAgeUnit = patAgeUnit;
		}

		public String getHmode() {
			return hmode;
		}

		public void setHmode(String hmode) {
			this.hmode = hmode;
		}		

		public String getValid() {
			return valid;
		}

		public void setValid(String valid) {
			this.valid = valid;
		}

		public String getPatGender() {
			return patGender;
		}
		
		public void setPatGender(String patGender) {
			this.patGender = patGender;
		}

		public String getPatPrimaryCat() {
			return patPrimaryCat;
		}

		public void setPatPrimaryCat(String patPrimaryCat) {
			this.patPrimaryCat = patPrimaryCat;
		}

		public String getPatSecondaryCat() {
			return patSecondaryCat;
		}
		
		public void setPatSecondaryCat(String patSecondaryCat) {
			this.patSecondaryCat = patSecondaryCat;
		}

		public String getDatePicker(String fieldName){
			String dateString = new String("");
			
		dateString = "	<input type=\"text\" name=\""+fieldName+"\" id=\"f_date_c\" > "+
		     		 "	<img src=\"../../hisglobal/images/iconPicDate.gif\" id=\"f_trigger_c\" style=\"cursor: pointer; border: 1px solid red;\"  "+					
	                 "	title=\"Date selector\"  "+
		    		 "	 onmouseover=\"this.style.background='red';\"  "+
		          	 "	onmouseout=\"this.style.background=''\"> "+					
	     	         "	 <script language=\"JavaScript\" src=\"../hisglobal/js/dateSetup.js\"></script> ";
			return dateString;
		}
		
		public String getPatAge() {
			return patAge;
		}

		public void setPatAge(String patAge) {
			this.patAge = patAge;
		}

		public String getPatCat() {
			return patCat;
		}

		public void setPatCat(String patCat) {
			this.patCat = patCat;
		}

		public String getCrNoToRetrieve() {
			return crNoToRetrieve;
		}

		public void setCrNoToRetrieve(String crNoToRetrieve) {
			this.crNoToRetrieve = crNoToRetrieve;
		}
}


