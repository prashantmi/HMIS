/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.allergies.vo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hisglobal.utility.HelperMethods;
import hisglobal.vo.ValueObject;

	public class EHRSection_AllergiesVO extends ValueObject
	{
		private String patCrNo;
		private String serialNo;
		private String allergyTypeName;
		private String allergyTypeCode;
		private String allergyID;
		private String allergyName;
		private String revokeRemarks;
		private String effectiveFrm;
		private String effectiveTo;
		private String duration;
		private String entryDate;
		private String durationDays;

		
		public String getPatCrNo() {
			return patCrNo;
		}
		public void setPatCrNo(String patCrNo) {
			this.patCrNo = patCrNo;
		}
		public String getSerialNo() {
			return serialNo;
		}
		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
		}
		public String getAllergyTypeName() {
			return allergyTypeName;
		}
		public void setAllergyTypeName(String allergyTypeName) {
			this.allergyTypeName = allergyTypeName;
		}
		public String getAllergyTypeCode() {
			return allergyTypeCode;
		}
		public void setAllergyTypeCode(String allergyTypeCode) {
			this.allergyTypeCode = allergyTypeCode;
		}
		public String getAllergyID() {
			return allergyID;
		}
		public void setAllergyID(String allergyID) {
			this.allergyID = allergyID;
		}
		public String getAllergyName() {
			return allergyName;
		}
		public void setAllergyName(String allergyName) {
			this.allergyName = allergyName;
		}
		public String getRevokeRemarks() {
			return revokeRemarks;
		}
		public void setRevokeRemarks(String revokeRemarks) {
			this.revokeRemarks = revokeRemarks;
		}
		public String getEffectiveFrm() {
			return effectiveFrm;
		}
		public void setEffectiveFrm(String effectiveFrm) {
			this.effectiveFrm = effectiveFrm;
		}
		public String getEffectiveTo() {
			return effectiveTo;
		}
		public void setEffectiveTo(String effectiveTo) {
			this.effectiveTo = effectiveTo;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		

		public String getDurationDays() {
			return durationDays;
		}
		public void setDurationDays(String durationDays) {
			this.durationDays = durationDays;
		}

		
		}
