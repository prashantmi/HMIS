/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.patientreferral.vo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hisglobal.utility.HelperMethods;
import hisglobal.vo.ValueObject;

	public class EHRSection_PatientReferralVO extends ValueObject
	{
		private String fromDeptCode;
		private String fromDepartment;
		private String fromDeptUnitCode;
		private String fromDepartmentUnit;
		private String toDeptCode;
		private String toDepartment;
		private String toDeptUnitCode;
		private String toDepartmentUnit;
		private String extHospitalCode;
		private String extHospitalName;
		private String extHospitalDocName;
		private String extHospDept;
		private String extHospDeptUnit;
		private String referDateTime;
		private String acceptanceDate;
		private String remarks;
		
		//Added by Vasu on 16.Dec.2019
		
		private String referdeptCode;
		private String referdeptName;
		private String referTypeCode;
		private String referTypeName;
		private String referReason;
		private String referDeptUnitCode;
		
		private String slNo;
		
		public String getFromDeptCode() {
			return fromDeptCode;
		}
		public void setFromDeptCode(String fromDeptCode) {
			this.fromDeptCode = fromDeptCode;
		}
		public String getFromDepartment() {
			return fromDepartment;
		}
		public void setFromDepartment(String fromDepartment) {
			this.fromDepartment = fromDepartment;
		}
		public String getFromDeptUnitCode() {
			return fromDeptUnitCode;
		}
		public void setFromDeptUnitCode(String fromDeptUnitCode) {
			this.fromDeptUnitCode = fromDeptUnitCode;
		}
		public String getFromDepartmentUnit() {
			return fromDepartmentUnit;
		}
		public void setFromDepartmentUnit(String fromDepartmentUnit) {
			this.fromDepartmentUnit = fromDepartmentUnit;
		}
		public String getToDeptCode() {
			return toDeptCode;
		}
		public void setToDeptCode(String toDeptCode) {
			this.toDeptCode = toDeptCode;
		}
		public String getToDepartment() {
			return toDepartment;
		}
		public void setToDepartment(String toDepartment) {
			this.toDepartment = toDepartment;
		}
		public String getToDeptUnitCode() {
			return toDeptUnitCode;
		}
		public void setToDeptUnitCode(String toDeptUnitCode) {
			this.toDeptUnitCode = toDeptUnitCode;
		}
		public String getToDepartmentUnit() {
			return toDepartmentUnit;
		}
		public void setToDepartmentUnit(String toDepartmentUnit) {
			this.toDepartmentUnit = toDepartmentUnit;
		}
		public String getExtHospitalCode() {
			return extHospitalCode;
		}
		public void setExtHospitalCode(String extHospitalCode) {
			this.extHospitalCode = extHospitalCode;
		}
		public String getExtHospitalName() {
			return extHospitalName;
		}
		public void setExtHospitalName(String extHospitalName) {
			this.extHospitalName = extHospitalName;
		}
		public String getExtHospitalDocName() {
			return extHospitalDocName;
		}
		public void setExtHospitalDocName(String extHospitalDocName) {
			this.extHospitalDocName = extHospitalDocName;
		}
		public String getExtHospDept() {
			return extHospDept;
		}
		public void setExtHospDept(String extHospDept) {
			this.extHospDept = extHospDept;
		}
		public String getExtHospDeptUnit() {
			return extHospDeptUnit;
		}
		public void setExtHospDeptUnit(String extHospDeptUnit) {
			this.extHospDeptUnit = extHospDeptUnit;
		}
		public String getReferDateTime() {
			return referDateTime;
		}
		public void setReferDateTime(String referDateTime) {
			this.referDateTime = referDateTime;
		}
		public String getAcceptanceDate() {
			return acceptanceDate;
		}
		public void setAcceptanceDate(String acceptanceDate) {
			this.acceptanceDate = acceptanceDate;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getReferdeptCode() {
			return referdeptCode;
		}
		public void setReferdeptCode(String referdeptCode) {
			this.referdeptCode = referdeptCode;
		}
		public String getReferdeptName() {
			return referdeptName;
		}
		public void setReferdeptName(String referdeptName) {
			this.referdeptName = referdeptName;
		}
		public String getReferTypeCode() {
			return referTypeCode;
		}
		public void setReferTypeCode(String referTypeCode) {
			this.referTypeCode = referTypeCode;
		}
		public String getReferTypeName() {
			return referTypeName;
		}
		public void setReferTypeName(String referTypeName) {
			this.referTypeName = referTypeName;
		}
		public String getReferReason() {
			return referReason;
		}
		public void setReferReason(String referReason) {
			this.referReason = referReason;
		}
		public String getReferDeptUnitCode() {
			return referDeptUnitCode;
		}
		public void setReferDeptUnitCode(String referDeptUnitCode) {
			this.referDeptUnitCode = referDeptUnitCode;
		}
		public String getSlNo() {
			return slNo;
		}
		public void setSlNo(String slNo) {
			this.slNo = slNo;
		}
		
		
	}
